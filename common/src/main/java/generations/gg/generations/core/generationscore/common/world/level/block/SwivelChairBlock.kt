package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.util.extensions.toInteractionResult
import generations.gg.generations.core.generationscore.common.util.extensions.toItemInteractionResult
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.DirectionalShapes
import generations.gg.generations.core.generationscore.common.world.level.block.decorations.SittableBlock
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericDyedVariantBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.DyeableBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class SwivelChairBlock(properties: Properties, color: DyeColor, function: Map<DyeColor, Block>) : DyeableBlock<GenericDyedVariantBlockEntity, SwivelChairBlock>(color, function, GenerationsBlockEntities.GENERIC_DYED_VARIANT, properties, GenerationsBlockEntityModels.SWIVEL_CHAIR), SittableBlock {

    override fun codec(): MapCodec<SwivelChairBlock> = CODEC

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE.getShape(state)
    }

    override val offset: Double = 0.5

    override fun getYaw(state: BlockState): Float {
        return state.getValue(FACING).toYRot()
    }

    override fun serverUse(
        stack: ItemStack,
        state: BlockState,
        world: ServerLevel,
        pos: BlockPos,
        player: ServerPlayer,
        handIn: InteractionHand,
        hit: BlockHitResult
    ): ItemInteractionResult {
        return super.sit(state, world, pos, player, hit).toItemInteractionResult()
    }

    companion object {
        val CODEC = simpleDyedCodec(::SwivelChairBlock)

        private val SHAPE: DirectionalShapes = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.or(
                Shapes.box(0.125, 0.0, 0.1875, 0.875, 0.5, 0.875),
                Shapes.box(0.0625, 0.0, 0.125, 0.9375, 0.1875, 0.9375),
                Shapes.box(0.125, 0.4375, 0.125, 0.875, 1.0, 0.3125),
                Shapes.box(0.125, 0.5625, 0.0625, 0.875, 1.0, 0.125),
                Shapes.box(0.125, 0.5625, 0.03125, 0.875, 1.0, 0.0625)
            ), Direction.SOUTH
        )
    }
}
