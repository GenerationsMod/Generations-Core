package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.GenericRotatableShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.StreetLampBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.DyeableBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.world.item.DyeColor
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class StreetLampBlock(properties: Properties, color: DyeColor, function: Map<DyeColor, Block>) : DyeableBlock<StreetLampBlockEntity, StreetLampBlock>(properties, color, function, GenerationsBlockEntityModels.STREET_LAMP, 0, 1, 0) {
    override val blockEntityType: BlockEntityType<StreetLampBlockEntity>
        get() = GenerationsBlockEntities.STREET_LAMP

    public override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape = SHAPE.getShape(state)

    override fun getVariant(): String? = null

    companion object {
        val CODEC = simpleDyedCodec(::StreetLampBlock)

        private val SHAPE: GenericRotatableShapes = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.or(
                Shapes.box(0.34375, 0.0, 0.34375, 0.65625, 0.25, 0.65625),
                Shapes.box(0.40625, 0.25, 0.40625, 0.59375, 0.34375, 0.59375),
                Shapes.box(0.4375, 0.3125, 0.4375, 0.5625, 2.0, 0.5625),
                Shapes.box(0.35, 1.45, 0.35, 0.65, 1.58125, 0.65),
                Shapes.box(0.375, 1.575, 0.375, 0.625, 1.89375, 0.625),
                Shapes.box(0.3, 1.80625, 0.3, 0.7, 1.9375, 0.7),
                Shapes.box(0.35, 1.84375, 0.35, 0.65, 1.975, 0.65)
            ),
            Direction.NORTH, 1, 2, 1
        )
    }

    override fun codec(): MapCodec<StreetLampBlock> {
        return CODEC
    }
}
