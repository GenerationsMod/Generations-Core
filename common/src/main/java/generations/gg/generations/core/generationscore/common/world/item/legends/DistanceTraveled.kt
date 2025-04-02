package generations.gg.generations.core.generationscore.common.world.item.legends

import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.ItemStack

interface DistanceTraveled {
    fun incrementDistance(player: ServerPlayer, stack: ItemStack, distance: Double)
    fun getDistance(stack: ItemStack): Double
    fun setDistance(stack: ItemStack, distance: Double)
    val maxDistance: Double

    fun remainingNeededDistance(itemInHand: ItemStack): Double
}
