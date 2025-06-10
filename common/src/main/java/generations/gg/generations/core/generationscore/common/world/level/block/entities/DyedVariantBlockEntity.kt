package generations.gg.generations.core.generationscore.common.world.level.block.entities

import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.TintProvider
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.DyeableBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.item.DyeColor
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import org.joml.Vector3f

fun Int.toVec3f(): Vector3f = Vector3f(
    ((this shr 16) and 0xFF) / 255f,
    ((this shr 8) and 0xFF) / 255f,
    (this and 0xFF) / 255f
)

abstract class DyedVariantBlockEntity<T : DyedVariantBlockEntity<T>>(
    arg: BlockEntityType<T>,
    arg2: BlockPos,
    arg3: BlockState,
) : ModelProvidingBlockEntity(arg, arg2, arg3), TintProvider {
    override fun getVariant(): String? {
        return (blockState.block as DyeableBlock<*, *>).variant
    }

    val color: DyeColor
        get() = (blockState.block as DyeableBlock<*, *>).color

    override fun getTint(): Vector3f? {
        return COLOR_MAP[color]
    }

    companion object {
        @JvmField
        val COLOR_MAP: Map<DyeColor, Vector3f?> = DyeColor.entries.associateWith { it.textureDiffuseColor.toVec3f() }
    }
}
