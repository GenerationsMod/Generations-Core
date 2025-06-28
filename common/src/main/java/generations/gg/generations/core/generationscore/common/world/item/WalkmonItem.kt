package generations.gg.generations.core.generationscore.common.world.item

import generations.gg.generations.core.generationscore.common.world.container.GenericContainer
import generations.gg.generations.core.generationscore.common.world.item.components.DiscContainer
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.component.ItemContainerContents
import net.minecraft.world.level.Level

class WalkmonItem(properties: Properties, private val row: Int, type: String) : Item(properties.component(GenerationsDataComponents.ROWS.value(), row)) {

    override fun appendHoverText(stack: ItemStack, context: TooltipContext, tooltipComponents: MutableList<Component>, tooltipFlag: TooltipFlag) {
        stack.populate(tooltipComponents, context)
    }

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!level.isClientSide() && usedHand == InteractionHand.MAIN_HAND) {
            val stack = player.getItemInHand(usedHand);

            if (player.isShiftKeyDown) {
                val discContainer = DiscContainer(9 * stack.rows)

                stack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY)
                    .copyInto(discContainer.items)

                GenericContainer.openScreen(
                    discContainer,
                    9,
                    stack.rows,
                    stack.title,
                    player,
                    player.inventory.selected
                ) {
                    stack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(discContainer.items))
                }
            } else {
                stack.toggle(player as ServerPlayer)
            }

        }

        return super.use(level, player, usedHand)
    }

    override fun inventoryTick(stack: ItemStack, level: Level, entity: Entity, slotId: Int, isSelected: Boolean) {
        if (!level.isClientSide && entity is ServerPlayer) {
            stack.tick(entity)
        }
    }
}