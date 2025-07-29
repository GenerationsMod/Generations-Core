package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.pokemon.PokemonPropertyExtractor
import com.cobblemon.mod.common.api.pokemon.PokemonPropertyExtractor.Companion.SPECIES
import com.cobblemon.mod.common.api.text.red
import com.cobblemon.mod.common.api.text.text
import com.cobblemon.mod.common.pokemon.Pokemon
import generations.gg.generations.core.generationscore.common.api.events.general.StatueEvents
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionResult
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3

class StatueSpawnerItem @JvmOverloads constructor(properties: Properties, private val key: SpeciesKey? = null) :
    Item(properties) {
    var pokemon: Pokemon? = null
        get() = if (field == null) (key?.createPokemon(70)).also { field = it } else field
        private set

    override fun useOn(context: UseOnContext): InteractionResult {
        val player = context.player.instanceOrNull<ServerPlayer>() ?: return InteractionResult.PASS

        StatueEvents.CAN_USE_CHISEL.post(StatueEvents.CanUseChisel(player, true), then = {
            val pos = context.clickedPos
            val chunk = player.level().getChunkAt(context.clickedPos)
            val chunkPos = chunk.pos
            val chunkMinX = chunkPos.minBlockX
            val chunkMinZ = chunkPos.minBlockZ
            val chunkMaxX = chunkMinX + 15
            val chunkMaxZ = chunkMinZ + 15

            val aabb = AABB(
                chunkMinX.toDouble(),
                0.0,
                chunkMinZ.toDouble(),
                chunkMaxX.toDouble() + 1,
                256.0,
                chunkMaxZ.toDouble() + 1
            )

            val existingStatues = player.level().getEntitiesOfClass(StatueEntity::class.java, aabb)
                .count { it.chunkPosition() == chunkPos }

            if (existingStatues >= 2) {
                player.sendSystemMessage("Only 2 statues are allowed per chunk.".red())
                return InteractionResult.FAIL
            }

            val statueEntity = StatueEntity(level = player.level())

            if (key != null) {
                statueEntity.properties = key.createPokemon(70).createPokemonProperties(PokemonPropertyExtractor.SPECIES, PokemonPropertyExtractor.LEVEL, PokemonPropertyExtractor.FORM, PokemonPropertyExtractor.ASPECTS)
                statueEntity.interactable = true
                statueEntity.material = "concrete"
                statueEntity.staticToggle = true
                player.setItemInHand(context.hand, ItemStack.EMPTY)
            }

            statueEntity.orientation = context.horizontalDirection.toYRot()

            statueEntity.setPos(Vec3.upFromBottomCenterOf(pos, 1.0))
            player.level().addFreshEntity(statueEntity)
            return InteractionResult.SUCCESS
        })

        return InteractionResult.PASS
    }
}
