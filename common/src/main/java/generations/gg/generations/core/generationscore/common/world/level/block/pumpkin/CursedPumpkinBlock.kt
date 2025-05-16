package generations.gg.generations.core.generationscore.common.world.level.block.pumpkin

import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.BlockHitResult

/**
 * @author JT122406
 * @see PumpkinBlock
 * Custom Cursed Pumpkin Block
 */
class CursedPumpkinBlock : PumpkinBlock(Properties.ofFullCopy(Blocks.PUMPKIN)) {
    override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hit: BlockHitResult
    ): ItemInteractionResult {
        if (stack.`is`(GenerationsItemTags.SHEARS)) {
            if (!level.isClientSide) {
                val direction = hit.direction
                val direction1 = if (direction.axis === Direction.Axis.Y) player.direction.opposite else direction
                level.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0f, 1.0f)
                level.setBlock(
                    pos, GenerationsBlocks.CURSED_CARVED_PUMPKIN.get().defaultBlockState().setValue(
                        CarvedPumpkinBlock.FACING, direction1
                    ), 11
                )
                val itementity = ItemEntity(
                    level,
                    pos.x.toDouble() + 0.5 + direction1.stepX.toDouble() * 0.65,
                    pos.y.toDouble() + 0.1,
                    pos.z.toDouble() + 0.5 + direction1.stepZ.toDouble() * 0.65,
                    ItemStack(
                        Items.PUMPKIN_SEEDS, 4
                    )
                )
                itementity.setDeltaMovement(
                    0.05 * direction1.stepX.toDouble() + level.random.nextDouble() * 0.02,
                    0.05,
                    0.05 * direction1.stepZ.toDouble() + level.random.nextDouble() * 0.02
                )
                level.addFreshEntity(itementity)
                stack.hurtAndBreak(
                    1,
                    player,
                    if (hand == InteractionHand.MAIN_HAND) EquipmentSlot.MAINHAND else EquipmentSlot.OFFHAND
                )
                level.gameEvent(player, GameEvent.SHEAR, pos)
                player.awardStat(Stats.ITEM_USED[Items.SHEARS])
            }

            return ItemInteractionResult.sidedSuccess(level.isClientSide)
        } else return super.useItemOn(stack, state, level, pos, player, hand, hit)
    }

    val stem: StemBlock
        get() = Blocks.PUMPKIN_STEM as StemBlock

    val attachedStem: AttachedStemBlock
        get() = Blocks.ATTACHED_PUMPKIN_STEM as AttachedStemBlock
}
