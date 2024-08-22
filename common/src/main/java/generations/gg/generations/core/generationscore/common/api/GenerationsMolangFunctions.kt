package generations.gg.generations.core.generationscore.common.api

import com.bedrockk.molang.runtime.MoParams
import com.bedrockk.molang.runtime.value.DoubleValue
import com.cobblemon.mod.common.api.molang.MoLangFunctions
import com.cobblemon.mod.common.api.molang.MoLangFunctions.addFunctions
import com.cobblemon.mod.common.api.molang.ObjectValue
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.util.asResource
import com.cobblemon.mod.common.util.getStringOrNull
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerPlayer
import net.minecraft.tags.TagKey
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.phys.Vec3
import java.util.function.Function
import kotlin.jvm.optionals.getOrNull

private fun MoParams.getVec3OrNull(index: Int): Vec3? {
    return getObjectOrNull(index, Vec3::class.java)
}

private fun MoParams.getServerPlayerOrNull(index: Int): ServerPlayer? {
    return getObjectOrNull(index, ServerPlayer::class.java)
}


inline fun <reified T> MoParams.getObjectOrNull(index: Int, java: Class<T> ): T? {
    return if (params.size > index) {
        getStruct(index).takeIf { it is ObjectValue<*> }.let { it as ObjectValue<*> }.takeIf { it.obj is T }?.obj as T
    } else {
        null
    }
}

object GenerationsMolangFunctions {
    @JvmStatic
    fun init() {
        MoLangFunctions.playerFunctions.add { player ->
            hashMapOf(
                "capped" to Function<MoParams, Any> {
                    val speciesKey = it.getStringOrNull(0)?.let { SpeciesKey.fromString(it) }

                    if(speciesKey == null) return@Function DoubleValue(1.0)

                    return@Function DoubleValue(if (GenerationsCore.CONFIG.caught.capped(player, speciesKey)) 1.0 else 0.0)
                },
                "main_hand" to Function<MoParams, Any> {
                    player.mainHandItem.toMolang()
                })
            //TODO: Add money support
        }

        MoLangFunctions.generalFunctions["spawn_pokemon"] = Function<MoParams, Any> {
            val player = it.getServerPlayerOrNull(0) ?: return@Function Unit
            val properties = it.getStringOrNull(1)?.asProperties() ?: return@Function Unit
            val pos = (it.getStringOrNull(2)?.parsePos(player) ?: player.position()).add(0.0, 1.0, 0.0)
            val yaw = it.getStringOrNull(3)?.parseYaw(player) ?: player.yRot

            PokemonUtil.spawn(properties, player.serverLevel(), pos, yaw)
            return@Function Unit
        }
    }

}

private fun String.parseYaw(player: ServerPlayer): Float =
    if (startsWith("#")) TagKey.create(Registries.BLOCK, substring(1).asResource()).findNearestYaw(player)
    else ResourceKey.create(Registries.BLOCK, substring(1).asResource()).findNearestYaw(player)

private fun TagKey<Block>.findNearestYaw(player: ServerPlayer): Float {
    return BlockPos.withinManhattanStream(player.onPos, 10, 10, 10)
        .filter { it: BlockPos -> player.serverLevel().getBlockState(it).block.`arch$holder`().`is`(this) }
        .findFirst().getOrNull()?.let {
            var blockEntity = player.serverLevel().getBlockEntity(it)

            if(blockEntity is ModelContextProviders.AngleProvider) {
                blockEntity.angle
            } else {
                val state = if(blockEntity != null) blockEntity.blockState else player.serverLevel().getBlockState(it)
                if(state.hasProperty(HorizontalDirectionalBlock.FACING)) state.getValue(HorizontalDirectionalBlock.FACING).toYRot() else 0.0F
            }

        } ?: 0F
}

private fun ResourceKey<Block>.findNearestYaw(player: ServerPlayer): Float {
    return BlockPos.withinManhattanStream(player.onPos, 10, 10, 10)
        .filter { it: BlockPos -> player.serverLevel().getBlockState(it).block.`arch$holder`().`is`(this) }
        .findFirst().getOrNull()?.let {
            var blockEntity = player.serverLevel().getBlockEntity(it)

            if(blockEntity is ModelContextProviders.AngleProvider) {
                blockEntity.angle
            } else {
                val state = if(blockEntity != null) blockEntity.blockState else player.serverLevel().getBlockState(it)
                if(state.hasProperty(HorizontalDirectionalBlock.FACING)) state.getValue(HorizontalDirectionalBlock.FACING).toYRot() else 0.0F
            }

        } ?: 0F
}

private fun String.parsePos(player: ServerPlayer): Vec3 =
    if (startsWith("#")) TagKey.create(Registries.BLOCK, substring(1).asResource()).findNearestPos(player)
    else ResourceKey.create(Registries.BLOCK, substring(0).asResource()).findNearestPos(player)

private fun TagKey<Block>.findNearestPos(player: ServerPlayer): Vec3 {
    return BlockPos.withinManhattanStream(player.onPos, 10, 10, 10)
        .filter { it: BlockPos -> player.serverLevel().getBlockState(it).block.`arch$holder`().`is`(this) }
        .findFirst().orElse(player.onPos).center
}

private fun ResourceKey<Block>.findNearestPos(player: ServerPlayer): Vec3 {
    return BlockPos.withinManhattanStream(player.onPos, 10, 10, 10)
        .filter { it: BlockPos -> player.serverLevel().getBlockState(it).block.`arch$holder`().`is`(this) }
        .findFirst().orElse(player.onPos).center
}

private fun String.asProperties(): PokemonProperties {
    return PokemonProperties.parse(this)
}

private fun ItemStack.toMolang(): ObjectValue<ItemStack> {
    return ObjectValue(this).addFunctions(hashMapOf(
        "shrink" to Function<MoParams, Any> {
            val amount = it.getInt(0)

            this.shrink(amount)
            return@Function Unit
        }
    ))
}
