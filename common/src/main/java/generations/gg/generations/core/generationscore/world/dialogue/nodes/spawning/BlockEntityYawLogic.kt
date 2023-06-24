package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning

import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.architectury.utils.value.FloatSupplier
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders.AngleProvider
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import java.util.*
import java.util.function.Predicate

@JvmRecord
data class BlockEntityYawLogic(val blockEntity: ResourceKey<BlockEntityType<*>>) : YawLogic {
    override fun createSupplier(player: Player): FloatSupplier {
        val world = player.getLevel()
        val optional =
            BlockPos.withinManhattanStream(player.onPos, 10, 10, 10).map { a -> world.getBlockEntity(a, blockEntity.value())
            }
                .filter { obj -> obj.isPresent }
                .map { obj -> obj.get() }
                .findFirst()
        if (optional.isPresent) {
            val blockEntity = optional.get()
            val angle = if (blockEntity is AngleProvider) blockEntity.angle else if (blockEntity.blockState.hasProperty(
                    HorizontalDirectionalBlock.FACING
                )
            ) blockEntity.blockState.getValue(HorizontalDirectionalBlock.FACING).toYRot() else 0f
            return FloatSupplier { angle }
        }
        return FloatSupplier { 0f }
    }

    companion object {
        fun of(key: ResourceKey<BlockEntityType<*>>): BlockEntityYawLogic {
            return BlockEntityYawLogic(key)
        }
    }
}