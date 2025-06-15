package generations.gg.generations.core.generationscore.common.world.level.block.decorations

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.GenericRotatableShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class BookShelfBlock(properties: Properties) :
    GenericRotatableModelBlock<GenericModelProvidingBlockEntity>(
        properties = properties,
        model = GenerationsBlockEntityModels.BOOKSHELF,
        width = 2,
        height = 2,
        length = 0
    ) {
    override val blockEntityType: BlockEntityType<GenericModelProvidingBlockEntity>
        get() = GenerationsBlockEntities.GENERIC_MODEL_PROVIDING

    override fun codec(): MapCodec<BookShelfBlock> {
        return CODEC
    }

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPES.getShape(state)
    }

    companion object {
        private val SHAPES: GenericRotatableShapes = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.box(
                -0.9312499999999999,
                0.0,
                0.05625000000000002,
                1.93125,
                2.8375,
                0.9625
            ).move(1.0, 0.0, 0.0), Direction.SOUTH, 3, 3, 1
        )

        val CODEC: MapCodec<BookShelfBlock> = simpleCodec { props: Properties ->
            BookShelfBlock(
                props
            )
        }
    }
}