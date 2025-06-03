package generations.gg.generations.core.generationscore.common.mixin;

import earth.terrarium.common_storage_lib.item.impl.vanilla.VanillaDelegatingSlot;
import earth.terrarium.common_storage_lib.item.impl.vanilla.WrappedVanillaContainer;
import earth.terrarium.common_storage_lib.resources.ResourceStack;
import earth.terrarium.common_storage_lib.resources.item.ItemResource;
import earth.terrarium.common_storage_lib.storage.util.ModifiableItemSlot;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(VanillaDelegatingSlot.class)
public class MixinVanillaDelegatingSlot implements ModifiableItemSlot {
    @Shadow @Final private Container container;

    @Shadow @Final private int slot;

    @Override
    public void setAmount(long l) {
        container.getItem(slot).setCount((int) l);
    }

    @Override
    public void setResource(ItemResource itemResource) {
        container.setItem(slot, itemResource.toStack());
    }

    @Override
    public void set(ItemStack stack) {
        container.setItem(slot, stack);
    }

    public void set(ResourceStack<ItemResource> data) {
        container.setItem(slot, ResourceStack.toItemStack(data));
    }


    @Override
    public ItemStack toItemStack() {
        return container.getItem(slot).copy();
    }

    @Override
    public int getMaxAllowed(ItemResource resource) {
        return resource.getCachedStack().getMaxStackSize();
    }

    @Override
    public boolean isEmpty() {
        return container.getItem(slot).isEmpty();
    }
}
