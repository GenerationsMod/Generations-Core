package generations.gg.generations.core.generationscore.fabric.mixin;

import generations.gg.generations.core.generationscore.fabric.AnvilEvents;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin extends ItemCombinerMenuMixin {

    @Shadow @Final private DataSlot cost;

    @Shadow private @Nullable String itemName;

    @Shadow private int repairItemCountCost;

    public void setMaximumCost(long cost) {
        this.cost.set((int) cost);
    }

    @Accessor("repairItemCountCost")
    public abstract void setMaterialCost(int cost);

    @Inject(method = "createResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z", ordinal = 1, shift = At.Shift.BEFORE), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    public void createResult(CallbackInfo ci, ItemStack itemStack, int i, long l, int j, ItemStack itemStack2, ItemStack itemStack3, ItemEnchantments.Mutable mutable) {
        var left = this.inputSlots.getItem(0).copy();
        var right = this.inputSlots.getItem(1);

        if(!onAnvilChange(left, right, getResultSlot(), this.itemName, l, this.player)) ci.cancel();
    }

    private boolean onAnvilChange(@NotNull ItemStack left, @NotNull ItemStack right, Container outputSlot, String name, long baseCost, Player player) {
        var e = new AnvilEvents.AnvilChange.Result(baseCost);
        if (AnvilEvents.ANVIL_CHANGE.invoker().change(e, left, right, name, baseCost, player)) return false;
        if (e.getOutput().isEmpty()) return true;

        outputSlot.setItem(0, e.getOutput());
        this.setMaximumCost(e.getCost());
        setMaterialCost(e.getMaterialCost());
        return false;
    }
}
