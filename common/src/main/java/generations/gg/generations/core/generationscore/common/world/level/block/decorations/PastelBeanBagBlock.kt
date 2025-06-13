package generations.gg.generations.core.generationscore.common.world.level.block.decorations

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.util.extensions.toItemInteractionResult
import generations.gg.generations.core.generationscore.common.world.entity.block.SittableEntity
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericDyedVariantBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.DyeableBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class PastelBeanBagBlock(properties: Properties, color: DyeColor, function: Map<DyeColor, Block>) : DyeableBlock<GenericDyedVariantBlockEntity, PastelBeanBagBlock>(
        color,
        function,
        GenerationsBlockEntities.GENERIC_DYED_VARIANT,
        properties,
        GenerationsBlockEntityModels.PASTEL_BEAN_BAG
    ) {
    public override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return Shapes.box(0.1, 0.0, 0.1, 0.9, 0.5, 0.9)
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
        if (!world.isClientSide && !player.isShiftKeyDown) return SittableEntity.mount(
            world, pos, 0.5, player, state.getValue(
                FACING
            ).toYRot()
        ).toItemInteractionResult()

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION
    }

    override fun getVariant(): String? {
        return null
    }

    override fun codec(): MapCodec<PastelBeanBagBlock> = CDOEC

    companion object {
        val CDOEC: MapCodec<PastelBeanBagBlock> = simpleDyedCodec(::PastelBeanBagBlock)
    }
}
