package generations.gg.generations.core.generationscore.common.world.recipe

import com.cobblemon.mod.common.Cobblemon.storage
import com.cobblemon.mod.common.api.moves.BenchedMove
import com.cobblemon.mod.common.api.moves.MoveTemplate
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies.getByIdentifier
import com.cobblemon.mod.common.api.pokemon.moves.Learnset
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.item.PokemonItem
import com.cobblemon.mod.common.pokemon.Pokemon
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.util.StreamCodecs
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.level.block.entities.RksMachineBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock.Companion.FACING
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.phys.Vec3
import org.joml.Vector4f

private val Learnset.allMoves: MutableList<MoveTemplate>
    get() {
        val moveList = mutableListOf<MoveTemplate>()
        moveList += this.levelUpMoves.values.flatten().toMutableList()
        moveList += this.tmMoves
        moveList += this.tutorMoves
        moveList += this.evolutionMoves
        moveList += this.eggMoves
        moveList += this.formChangeMoves


        return moveList
    }

data class PokemonResult(
    val species: ResourceLocation, val aspects: Set<String>, val level: Int, val spawnInWorld: Boolean,
    val usePokemonInCapsule: Boolean
) : RksResult<PokemonResult> {
    override val stack: ItemStack
        get() = PokemonItem.from(getByIdentifier(species)!!, aspects, 1, Vector4f(1f, 1f, 1f, 1f))

    override fun type(): RksResultType<PokemonResult> {
        return GenerationsRksTypes.POKEMON.value() as RksResultType<PokemonResult>
    }

    override fun process(player: Player, rksMachineBlockEntity: RksMachineBlockEntity, stack: ItemStack) {
        val pokemon: Pokemon?

        if (usePokemonInCapsule && rksMachineBlockEntity.pokemon.isPresent) {
            val properties = PokemonProperties()
            properties.aspects = aspects
            properties.species = species.toString()

            pokemon = rksMachineBlockEntity.pokemon.get()
            properties.apply(pokemon)
            val list = pokemon.form.moves.allMoves

            pokemon.benchedMoves.doThenEmit {
                val movesToRemove = mutableListOf<BenchedMove>()

                pokemon.benchedMoves.forEach {
                    if(!list.contains(it.moveTemplate)) movesToRemove.add(it)
                }

                movesToRemove.forEach { pokemon.benchedMoves.remove(it) }

            }

            var slot: Int? = null

            pokemon.moveSet.doWithoutEmitting {
                val moveset = pokemon.moveSet

                moveset[0]?.takeIf { !list.contains(it.template) }?.let {
                    if (slot != null) {
                        slot = 0
                    } else {
                        moveset.setMove(0, null)
                    }
                }
                moveset[1]?.takeIf { !list.contains(it.template) }?.let {
                    if (slot != null) {
                        slot = 1
                    } else {
                        moveset.setMove(1, null)
                    }
                }
                moveset[2]?.takeIf { !list.contains(it.template) }?.let {
                    if (slot != null) {
                        slot = 2
                    } else {
                        moveset.setMove(2, null)
                    }
                }
                moveset[3]?.takeIf { !list.contains(it.template) }?.let {
                    if (slot != null) {
                        slot = 3
                    } else {
                        moveset.setMove(3, null)
                    }
                }
            }

            val moveset = pokemon.moveSet

            if(slot != null && moveset.filterIndexed { i,_ -> i != slot }.isEmpty()) {
                val first = pokemon.allAccessibleMoves.first()

                pokemon.exchangeMove(moveset[slot!!]!!.template, first)
            } else {
                pokemon.initializeMoveset()
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
            val dir = rksMachineBlockEntity.blockState.getValue(FACING)
            PokemonUtil.spawn(
                PokemonEntity(rksMachineBlockEntity.level!!, pokemon),
                rksMachineBlockEntity.level,
                Vec3.atCenterOf(pos.above(2)),
                dir.toYRot()
            )
        } else {
            try {
                storage.getParty(player as ServerPlayer).add(pokemon)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        stack.count = 0
    }

    override val isPokemon: Boolean = true

    companion object {
        val CODEC = RecordCodecBuilder.mapCodec { instance ->
                instance.group(
                    ResourceLocation.CODEC.fieldOf("species").forGetter(PokemonResult::species),
                    Codec.STRING.listOf().xmap({ it.toSet() }, { it.toList()})
                        .optionalFieldOf("aspects", java.util.HashSet()).forGetter(PokemonResult::aspects),
                    Codec.INT.optionalFieldOf("level", 1).forGetter(PokemonResult::level),
                    Codec.BOOL.optionalFieldOf("spawnInWorld", true).forGetter(PokemonResult::spawnInWorld),
                    Codec.BOOL.optionalFieldOf("usePokemonInCapsule", false).forGetter(PokemonResult::usePokemonInCapsule)
                ).apply(instance, ::PokemonResult)
            }
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, PokemonResult> = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC,
            PokemonResult::species,
            ByteBufCodecs.STRING_UTF8.apply(StreamCodecs.set()),
            PokemonResult::aspects,
            ByteBufCodecs.VAR_INT,
            PokemonResult::level,
            ByteBufCodecs.BOOL,
            PokemonResult::spawnInWorld,
            ByteBufCodecs.BOOL,
            PokemonResult::usePokemonInCapsule,
            ::PokemonResult
        )
    }
}
