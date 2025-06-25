package generations.gg.generations.core.generationscore.common.world.item

import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.item.components.CarrotHolder
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.ItemContainerContents
import net.minecraft.world.level.Level

private var ItemStack.carrotHolder: CarrotHolder
    get() {
        val holder = CarrotHolder()
        container.copyInto(holder.items)
        return holder
    }
    set(value) {
        container = ItemContainerContents.fromItems(value.items)
    }

class CalyrexSteedItem(name: String, arg: Properties, private val speices: SpeciesKey) : Item(arg) {
    private val defaultTranslation = "container." + name + "_carrot"

    //    @Override
    //    public boolean isEdible() {
    //        return true;
    //    }
    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!level.isClientSide() && usedHand == InteractionHand.MAIN_HAND) {
            val stack = player.getItemInHand(usedHand)

            val carrots = stack.carrotHolder ?: return InteractionResultHolder.pass(player.getItemInHand(usedHand))

            val isFull = (0..< 18).map(carrots::getItem).all { it.count >= 64 }

            if (!isFull) {


                carrots.also { GenericContainer.openScreen(carrots, 9, 2, Component.translatable(defaultTranslation), player, player.inventory.selected) { stack.carrotHolder = it} }
                return InteractionResultHolder.success(player.getItemInHand(usedHand))
            }
            return super.use(level, player, usedHand)
        }

        return InteractionResultHolder.pass(player.getItemInHand(usedHand))
    }

    override fun finishUsingItem(stack: ItemStack, level: Level, livingEntity: LivingEntity): ItemStack {
        if (!level.isClientSide()) {
            PokemonUtil.spawn(speices.createPokemon(70), level, livingEntity.position(), livingEntity.yRot)
        }

        return ItemStack.EMPTY
    }
}
