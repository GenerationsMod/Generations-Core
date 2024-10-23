package generations.gg.generations.core.generationscore.common.world.item.legends;

import generations.gg.generations.core.generationscore.common.util.DataKeys;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class DistanceTraveledImplItem extends Item implements DistanceTraveled {
    private final double maxDistance;

    public DistanceTraveledImplItem(Properties properties, double maxDistance) {
        super(properties);
        this.maxDistance = maxDistance;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if(!level.isClientSide()) {
            if(remainingNeededDistance(player.getItemInHand(usedHand)) <= 0) {
                onCompletion((ServerLevel) level, player, usedHand);
                player.getItemInHand(usedHand).shrink(1);
                return InteractionResultHolder.success(player.getItemInHand(usedHand));
            }
        }

        return super.use(level, player, usedHand);
    }

    protected abstract void onCompletion(ServerLevel level, Player player, InteractionHand usedHand);

    @Override
    public void incrementDistance(ServerPlayer player, ItemStack stack, double distance) {
        if (checkPlayerState(player)) {
           setDistance(stack, getDistance(stack) + distance);
        }
    }

    public boolean checkPlayerState(Player player) {
        return true;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, @NotNull TooltipFlag isAdvanced) {
        tooltipComponents.add(Component.nullToEmpty("Distance: %.2f".formatted(getDistance(stack))));
    }

    @Override
    public double getDistance(ItemStack stack) {
        return stack.getOrCreateTag().getDouble(DataKeys.DISTANCE);
    }

    @Override
    public void setDistance(ItemStack stack, double distance) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putDouble(DataKeys.DISTANCE, Mth.clamp(distance, 0, getMaxDistance()));
    }

    @Override
    public double getMaxDistance() {
        return maxDistance;
    }

    @Override
    public double remainingNeededDistance(ItemStack itemInHand) {
        return getMaxDistance() - getDistance(itemInHand);
    }
}
