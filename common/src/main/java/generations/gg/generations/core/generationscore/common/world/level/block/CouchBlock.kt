package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.util.extensions.toInteractionResult
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.GenericRotatableShapes
import generations.gg.generations.core.generationscore.common.world.level.block.decorations.SittableBlock
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class CouchBlock(properties: Properties) :
    GenericRotatableModelBlock<GenericModelProvidingBlockEntity>(
        properties = properties,
        model = GenerationsBlockEntityModels.COUCH,
        width = 1
    ), SittableBlock {
    override val blockEntityType: BlockEntityType<GenericModelProvidingBlockEntity>
        get() = GenerationsBlockEntities.GENERIC_MODEL_PROVIDING

    override fun codec(): MapCodec<CouchBlock> = CODEC

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE.getShape(state)
    }

    public override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hit: BlockHitResult
    ): InteractionResult {
        return super.sit(state, level, pos, player, hit).toInteractionResult()
    }

    override val offset: Double = 0.4375

    override fun getYaw(state: BlockState): Float {
        return state.getValue(FACING).toYRot()
    }

    companion object {
        private val SHAPE: GenericRotatableShapes = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.or(
                Shapes.box(0.11562499999999998, 0.0, 0.81875, 0.20937499999999998, 0.1875, 0.94375),
                Shapes.box(1.790625, 0.0, 0.06874999999999998, 1.884375, 0.1875, 0.19374999999999998),
                Shapes.box(
                    0.11562499999999998,
                    0.0,
                    0.06874999999999998,
                    0.20937499999999998,
                    0.1875,
                    0.19374999999999998
                ),
                Shapes.box(1.790625, 0.0, 0.81875, 1.884375, 0.1875, 0.94375),
                Shapes.box(0.06874999999999998, 0.1375, 0.006249999999999978, 1.93125, 0.403125, 0.9874999999999999),
                Shapes.box(
                    0.03749999999999998,
                    0.2625,
                    0.006249999999999978,
                    0.19999999999999996,
                    0.6312500000000001,
                    0.9874999999999999
                ),
                Shapes.box(0.03749999999999998, 0.29375, -0.006249999999999978, 1.9625, 0.98125, 0.3125),
                Shapes.box(1.8, 0.2625, 0.006249999999999978, 1.9625, 0.6312500000000001, 0.9874999999999999)
            ), Direction.SOUTH, 2, 1, 1
        )

        val CODEC: MapCodec<CouchBlock> = simpleCodec(::CouchBlock)
    }
}

