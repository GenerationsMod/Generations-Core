package generations.gg.generations.core.generationscore.world.container.slots;

import com.mojang.datafixers.util.Pair;
import com.pokemod.pokemod.PokeMod;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.DoubleSupplier;

import static net.minecraft.world.inventory.InventoryMenu.BLOCK_ATLAS;

public class TypeSlot extends SlotItemHandler {
    private final RegistrySupplier<Item> candy;
    private final DoubleSupplier supplier;
    private final Pair<ResourceLocation, ResourceLocation> pair;
    public int offsetX;
    public int offsetY;

    public TypeSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, RegistrySupplier<Item> candy, String elementName, DoubleSupplier supplier) {
        super(itemHandler, index, xPosition, yPosition);
        this.candy = candy;
        this.supplier = supplier;
        pair = new Pair<>(BLOCK_ATLAS, GenerationsCore.id("type_slots/" + elementName));
    }

    public static void interpolateVectors(TypeSlot slot, int x1, int y1, int x2, int y2, double t) {
        slot.offsetX = Math.round((float) (x1 + (x2 - x1) * t));
        slot.offsetY = Math.round((float) (y1 + (y2 - y1) * t));
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
