package generations.gg.generations.core.generationscore.fabric.mixin;

import generations.gg.generations.core.generationscore.fabric.AnvilEvents;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin extends ItemCombinerMenuMixin {

    @Shadow @Final private DataSlot cost;

    @Shadow private @Nullable String itemName;

    public void setMaximumCost(int cost) {
        this.cost.set(cost);
    }

    @Accessor("repairItemCountCost")
    public abstract void setMaterialCost(int cost);

    @Inject(method = "createResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z", ordinal = 2, shift = At.Shift.BEFORE), cancellable = true)
    public void createResult(CallbackInfo ci) {
        var left = this.inputSlots.getItem(0).copy();
        var right = this.inputSlots.getItem(1);
        var baseCost = left.getBaseRepairCost() + (right.isEmpty() ? 0 : right.getBaseRepairCost());

        if(!onAnvilChange(left, right, getResultSlot(), this.itemName, baseCost, this.player)) ci.cancel();
    }

    private boolean onAnvilChange(@NotNull ItemStack left, @NotNull ItemStack right, Container outputSlot, String name, int baseCost, Player player) {
        var e = new AnvilEvents.AnvilChange.Result(baseCost);
        if (AnvilEvents.ANVIL_CHANGE.invoker().change(e, left, right, name, baseCost, player)) return false;
        if (e.getOutput().isEmpty()) return true;

        outputSlot.setItem(0, e.getOutput());
        this.setMaximumCost(e.getCost());
        setMaterialCost(e.getMaterialCost());
        return false;
    }

}
