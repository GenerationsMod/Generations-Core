package generations.gg.generations.core.generationscore.common.world.item.legends

import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.util.DataKeys
import generations.gg.generations.core.generationscore.common.util.add
import generations.gg.generations.core.generationscore.common.util.extensions.distance
import generations.gg.generations.core.generationscore.common.util.extensions.get
import generations.gg.generations.core.generationscore.common.util.extensions.set
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsItemComponents
import net.minecraft.core.component.DataComponentType
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.Mth
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

abstract class DistanceTraveledImplItem(properties: Properties, override val maxDistance: Double) :
    Item(properties.distance()), DistanceTraveled {
    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!level.isClientSide()) {
            if (remainingNeededDistance(player.getItemInHand(usedHand)) <= 0) {
                onCompletion(level as ServerLevel, player, usedHand)
                player.getItemInHand(usedHand).shrink(1)
                return InteractionResultHolder.success(player.getItemInHand(usedHand))
            }
        }

        return super.use(level, player, usedHand)
    }

    protected abstract fun onCompletion(level: ServerLevel?, player: Player?, usedHand: InteractionHand?)

    override fun incrementDistance(player: ServerPlayer, stack: ItemStack, distance: Double) {
        if (checkPlayerState(player)) {
            setDistance(stack, getDistance(stack) + distance)
        }
    }

    open fun checkPlayerState(player: ServerPlayer): Boolean {
        return true
    }


    override fun appendHoverText(
        stack: ItemStack,
        level: TooltipContext,
        tooltipComponents: MutableList<Component>,
        isAdvanced: TooltipFlag
    ) {
        tooltipComponents.add("Distance: ${getDistance(stack)}")
    }

    override fun getDistance(stack: ItemStack): Double {
        return stack.get(GenerationsItemComponents.DISTANCE.componentType()) ?: 0.0
    }

    override fun setDistance(stack: ItemStack, distance: Double) {
        stack.set(GenerationsItemComponents.DISTANCE.componentType(), distance.coerceIn(0.0, maxDistance))
    }

    override fun remainingNeededDistance(itemInHand: ItemStack): Double {
        return maxDistance - getDistance(itemInHand)
    }
}


