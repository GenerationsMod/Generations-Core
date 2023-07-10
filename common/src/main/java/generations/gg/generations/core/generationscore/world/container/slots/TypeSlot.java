package generations.gg.generations.core.generationscore.world.container.slots;

import com.mojang.datafixers.util.Pair;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.DoubleSupplier;

import static net.minecraft.world.inventory.InventoryMenu.BLOCK_ATLAS;

public class TypeSlot extends Slot {
    private final RegistrySupplier<Item> candy;
    private final DoubleSupplier supplier;
    private final Pair<ResourceLocation, ResourceLocation> pair;
    public int originalX;
    public int originalY;

    public TypeSlot(Container itemHandler, int index, int xPosition, int yPosition, RegistrySupplier<Item> candy, String elementName, DoubleSupplier supplier) {
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
