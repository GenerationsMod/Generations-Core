package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.util.Codecs
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class LunarCystalBlock(properties: Properties, model: ResourceLocation) : GenericRotatableModelBlock(
        properties,
        model = model
    ) {
    override val blockEntityType
        get() = GenerationsBlockEntities.GENERIC_MODEL_PROVIDING

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE
    }

    override fun codec(): MapCodec<LunarCystalBlock> = CODEC

    companion object {
        private val SHAPE: VoxelShape = Shapes.box(0.3125, 0.0, 0.3125, 0.6875, 1.0, 0.6875)
        val CODEC: MapCodec<LunarCystalBlock> = Codecs.mapCodec { group(
            propertiesCodec(),
            Codecs.modelCodec()).apply(this, ::LunarCystalBlock)
        }
    }
}
