package generations.gg.generations.core.generationscore.common.world.recipe

import com.cobblemon.mod.common.Cobblemon.storage
import com.cobblemon.mod.common.api.moves.BenchedMove
import com.cobblemon.mod.common.api.moves.MoveTemplate
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies.getByIdentifier
import com.cobblemon.mod.common.api.pokemon.moves.Learnset
import com.cobblemon.mod.common.api.storage.NoPokemonStoreException
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.item.PokemonItem
import com.cobblemon.mod.common.pokemon.Pokemon
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.level.block.RksMachineBlock
import generations.gg.generations.core.generationscore.common.world.level.block.entities.RksMachineBlockEntity
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.phys.Vec3
import org.joml.Vector4f
import java.util.function.BiConsumer
import java.util.function.Function

private val Learnset.allMoves: MutableList<MoveTemplate>
    get() {
        var moveList = mutableListOf<MoveTemplate>()
        moveList += this.levelUpMoves.values.flatMap { it }.toMutableList()
        moveList += this.tmMoves
        moveList += this.tutorMoves
        moveList += this.evolutionMoves
        moveList += this.eggMoves
        moveList += this.formChangeMoves


        return moveList
    }

@JvmRecord
data class PokemonResult(
    val species: ResourceLocation, val aspects: Set<String>, val level: Int, val spawnInWorld: Boolean,
    val usePokemonInCapsule: Boolean
) : RksResult<PokemonResult?> {
    override fun getStack(): ItemStack {
        return PokemonItem.from(getByIdentifier(species)!!, aspects, 1, Vector4f(1f, 1f, 1f, 1f))
    }

    override fun type(): RksResultType<PokemonResult?>? {
        return RksResultType.POKEMON.get()
    }

    override fun process(player: Player, rksMachineBlockEntity: RksMachineBlockEntity, stack: ItemStack) {
        var pokemon: Pokemon? = null

        if (usePokemonInCapsule && rksMachineBlockEntity.pokemon.isPresent) {
            val properties = PokemonProperties()
            properties.aspects = aspects
            properties.species = species.toString()

            pokemon = rksMachineBlockEntity.pokemon.get()
            properties.apply(pokemon)
            var list = pokemon.species.moves.allMoves

            pokemon.benchedMoves.doThenEmit {
                val movesToRemove = mutableListOf<BenchedMove>()

                pokemon!!.benchedMoves.forEach {
                    if(!list.contains(it.moveTemplate)) movesToRemove.add(it)
                }

                movesToRemove.forEach { pokemon!!.benchedMoves.remove(it) }
            }

            pokemon.moveSet.doWithoutEmitting {
                var moveset = pokemon!!.moveSet;

                moveset[0]?.takeIf { !list.contains(it.template) }?.let { moveset.setMove(0, null) }
                moveset[1]?.takeIf { !list.contains(it.template) }?.let { moveset.setMove(1, null) }
                moveset[2]?.takeIf { !list.contains(it.template) }?.let { moveset.setMove(2, null) }
                moveset[3]?.takeIf { !list.contains(it.template) }?.let { moveset.setMove(3, null) }
            }
        } else {
            val properties = PokemonProperties()
            properties.aspects = aspects
            properties.species = species.toString()
            properties.level = level
            pokemon = properties.create()
        }

        if (spawnInWorld) {
            val pos = rksMachineBlockEntity.blockPos
            val dir = rksMachineBlockEntity.blockState.getValue(RksMachineBlock.FACING)
            PokemonUtil.spawn(
                PokemonEntity(rksMachineBlockEntity.level!!, pokemon),
                rksMachineBlockEntity.level,
                Vec3.atCenterOf(pos.above(2)),
                dir.toYRot()
            )
        } else {
            try {
                storage.getParty(player.uuid).add(pokemon)
            } catch (e: NoPokemonStoreException) {
                e.printStackTrace()
            }
        }

        stack.count = 0
    }

    override fun isPokemon(): Boolean {
        return true
    }

    companion object {
        @JvmField
        val CODEC: Codec<PokemonResult> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    ResourceLocation.CODEC.fieldOf("species").forGetter(PokemonResult::species),
                    Codec.STRING.listOf().xmap({ it.toSet() }, { it.toList()})
                        .optionalFieldOf("aspects", java.util.HashSet()).forGetter(PokemonResult::aspects),
                    Codec.INT.optionalFieldOf("level", 1).forGetter(PokemonResult::level),
                    Codec.BOOL.optionalFieldOf("spawnInWorld", true).forGetter(PokemonResult::spawnInWorld),
                    Codec.BOOL.optionalFieldOf("usePokemonInCapsule", false).forGetter(PokemonResult::usePokemonInCapsule)
                ).apply(instance, ::PokemonResult)
            }

        @JvmField
        val FROM_BUFFER: Function<FriendlyByteBuf, PokemonResult> =
            Function { buffer: FriendlyByteBuf ->
                val species = buffer.readResourceLocation()
                val aspects = buffer.readCollection(
                    { initialCapacity: Int -> HashSet(initialCapacity) },
                    { obj: FriendlyByteBuf -> obj.readUtf() })
                val level = buffer.readVarInt()
                val spawnInWorld = buffer.readBoolean()
                val usePokemonInCapsule = buffer.readBoolean()
                PokemonResult(species, aspects, level, spawnInWorld, usePokemonInCapsule)
            }

        @JvmField
        val TO_BUFFER: BiConsumer<FriendlyByteBuf, PokemonResult> =
            BiConsumer { buffer: FriendlyByteBuf, result: PokemonResult ->
                buffer.writeResourceLocation(result.species)
                buffer.writeCollection(result.aspects) { obj: FriendlyByteBuf, value -> obj.writeUtf(value) }
                buffer.writeVarInt(result.level)
                buffer.writeBoolean(result.spawnInWorld)
                buffer.writeBoolean(result.usePokemonInCapsule)
            }
    }
}
