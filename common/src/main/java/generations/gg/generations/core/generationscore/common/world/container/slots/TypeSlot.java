package generations.gg.generations.core.generationscore.common.world.container.slots;

import com.mojang.datafixers.util.Pair;
import dev.architectury.registry.registries.RegistrySupplier;
import earth.terrarium.common_storage_lib.resources.item.ItemResource;
import earth.terrarium.common_storage_lib.storage.base.CommonStorage;
import earth.terrarium.common_storage_lib.storage.util.MenuStorageSlot;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.DoubleSupplier;

import static net.minecraft.world.inventory.InventoryMenu.BLOCK_ATLAS;

public class TypeSlot extends MenuStorageSlot {
    private final RegistrySupplier<Item> candy;
    private final DoubleSupplier supplier;
    private final Pair<ResourceLocation, ResourceLocation> pair;
    public int originalX;
    public int originalY;

    public TypeSlot(CommonStorage<ItemResource> itemHandler, int index, int xPosition, int yPosition, RegistrySupplier<Item> candy, String elementName, DoubleSupplier supplier) {
        super(itemHandler, index, xPosition, yPosition);
        this.candy = candy;
        this.supplier = supplier;
        pair = new Pair<>(BLOCK_ATLAS, GenerationsCore.id("type_slots/" + elementName));
    }

    public static void interpolateVectors(TypeSlot slot, int x1, int y1, int x2, int y2, double t) {
        slot.x = Math.round((float) (slot.originalX + (x2 - slot.originalX) * t));
        slot.y = Math.round((float) (slot.originalY + (y2 - slot.originalY) * t));
    }

    @Override
    public boolean isActive() {
        return supplier.getAsDouble() < 1.0f;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return stack.is(candy.get());
    }

    @Nullable
    @Override
    public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
        return pair;
    }
}
