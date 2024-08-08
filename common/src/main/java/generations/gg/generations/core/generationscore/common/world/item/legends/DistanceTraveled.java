package generations.gg.generations.core.generationscore.common.world.item.legends;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public interface DistanceTraveled {
    void incrementDistance(ServerPlayer player, ItemStack stack, double distance);
    double getDistance(ItemStack stack);
    void setDistance(ItemStack stack, double distance);
    double getMaxDistance();
    double remainingNeededDistance(ItemStack itemInHand);
}
