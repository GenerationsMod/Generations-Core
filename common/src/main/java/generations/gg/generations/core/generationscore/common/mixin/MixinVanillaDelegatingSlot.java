package generations.gg.generations.core.generationscore.common.mixin;

import earth.terrarium.common_storage_lib.item.impl.vanilla.VanillaDelegatingSlot;
import earth.terrarium.common_storage_lib.resources.ResourceStack;
import earth.terrarium.common_storage_lib.resources.item.ItemResource;
import earth.terrarium.common_storage_lib.storage.util.ModifiableItemSlot;
import generations.gg.generations.core.generationscore.common.world.container.LockableSlot;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VanillaDelegatingSlot.class)
abstract public class MixinVanillaDelegatingSlot implements ModifiableItemSlot, LockableSlot {
    @Shadow @Final private Container container;

    @Shadow @Final private int slot;

    @Unique private boolean locked;

    @Override
    public void setAmount(long l) {
        container.getItem(slot).setCount((int) l);
    }

    @Override
    public void setResource(ItemResource itemResource) {
        container.setItem(slot, itemResource.toStack());
    }

    @Inject(method = "extract(Learth/terrarium/common_storage_lib/resources/item/ItemResource;JZ)J", at = @At("HEAD"), cancellable = true)
    public void extract(ItemResource resource, long amount, boolean simulate, CallbackInfoReturnable<Long> cir) {
        if(locked) cir.setReturnValue(0L);

    }

    @Override
    public void set(ItemStack stack) {
        container.setItem(slot, stack);
        container.setChanged();
    }

    public void set(ResourceStack<ItemResource> data) {
        container.setItem(slot, ResourceStack.toItemStack(data));
    }

    @Override
    public ItemStack toItemStack() {
        return container.getItem(slot);
    }

    @Override
    public int getMaxAllowed(ItemResource resource) {
        return resource.getCachedStack().getMaxStackSize();
    }

    @Override
    public boolean isEmpty() {
        return container.getItem(slot).isEmpty();
    }

    @Override
    public void lock() {
        this.locked = true;
    }
}
