package generations.gg.generations.core.generationscore.common.world.level.block.shrines

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.item.legends.TaoTrioStoneItem
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.DirectionalShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.TaoTrioShrineBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class TaoTrioShrineBlock(properties: Properties) : InteractShrineBlock<TaoTrioShrineBlockEntity>(
        properties,
        GenerationsBlockEntities.TAO_TRIO_SHRINE,
        GenerationsBlockEntityModels.TAO_TRIO_SHRINE
    ) {
    override fun interact(
        level: Level,
        pos: BlockPos,
        state: BlockState,
        player: ServerPlayer,
        hand: InteractionHand,
        activationState: Boolean
    ): Boolean {
        val stack = player.getItemInHand(hand)
        PokemonUtil.spawn((stack.item as TaoTrioStoneItem).species.createProperties(70), level, player.onPos)
        stack.shrink(1)
        return true
    }

    override fun codec(): MapCodec<TaoTrioShrineBlock> = CODEC

    override fun isStackValid(stack: ItemStack): Boolean {
        return stack.item is TaoTrioStoneItem && stack.damageValue >= stack.maxDamage
    }

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE.getShape(state)
    }

    override fun getVariant(blockState: BlockState): String? {
        return null
    }

    companion object {
        private val SHAPE: DirectionalShapes = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(
                Shapes.box(0.0, 0.0, 0.0, 1.0, 0.15625, 1.0),
                Shapes.join(
                    Shapes.box(0.09375, 0.15625, 0.21875, 0.90625, 0.25625, 0.90625),
                    Shapes.join(
                        Shapes.box(0.0625, 0.15625, 0.34375, 0.9375, 1.0, 0.53125),
                        Shapes.box(0.09375, 0.15625, 0.21875, 0.90625, 0.94375, 0.46875), BooleanOp.OR
                    ), BooleanOp.OR
                ), BooleanOp.OR
            ), Direction.SOUTH
        )
        val CODEC: MapCodec<TaoTrioShrineBlock> = simpleCodec(::TaoTrioShrineBlock)
    }
}
