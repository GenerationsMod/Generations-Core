package generations.gg.generations.core.generationscore.common.world.container;

import generations.gg.generations.core.generationscore.common.world.data.SimpleItemStorage;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MachineBlockEntity;
import generations.gg.generations.core.generationscore.common.world.container.slots.NullCandySlot;
import generations.gg.generations.core.generationscore.common.world.container.slots.TypeSlot;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MachineBlockContainer extends AbstractContainerMenu {

    private final MachineBlockEntity be;

    public MachineBlockContainer(GenerationsContainers.CreationContext<MachineBlockEntity> ctx) {
        super(GenerationsContainers.MACHINE_BLOCK.get(), ctx.id());
        this.be = ctx.blockEntity();

        addSlot(new TypeSlot(be.candies, 0, 92, 9, GenerationsItems.BUG_CANDY, "bug", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 1, 114, 17, GenerationsItems.DARK_CANDY, "dark", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 2, 136, 34, GenerationsItems.DRAGON_CANDY, "dragon", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 3, 148, 55, GenerationsItems.ELECTRIC_CANDY, "electric", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 4, 152, 80, GenerationsItems.FAIRY_CANDY, "fairy", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 5, 148, 105, GenerationsItems.FIGHTING_CANDY, "fighting", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 6, 136, 127, GenerationsItems.FIRE_CANDY, "fire", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 7, 114, 143, GenerationsItems.FLYING_CANDY, "flying", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 8, 92, 151, GenerationsItems.GHOST_CANDY, "ghost", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 9, 68, 151, GenerationsItems.GRASS_CANDY, "grass", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 10, 46, 143, GenerationsItems.GROUND_CANDY, "ground", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 11, 24, 127, GenerationsItems.ICE_CANDY, "ice", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 12, 12, 105, GenerationsItems.NORMAL_CANDY, "normal", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 13, 8, 80, GenerationsItems.POISON_CANDY, "poison", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 14, 12, 55, GenerationsItems.PSYCHIC_CANDY, "psychic", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 15, 24, 33, GenerationsItems.ROCK_CANDY, "rock", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 16, 46, 17, GenerationsItems.STEEL_CANDY, "steel", this::getBakeTime));
        addSlot(new TypeSlot(be.candies, 17, 68, 9, GenerationsItems.WATER_CANDY, "water", this::getBakeTime));

//        addSlot(new NullCandySlot((SimpleItemStorage) be.getCandies(), 80, 80, GenerationsItems.NULL_CANDY, this::getBakeTime));

        populate(ctx.playerInv(), 173, 1, 3);
        populate(ctx.playerInv(), 231, 0, 1);
    }

    private void populate(Container container, int y, int startingRow, int rows) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlot(new Slot(container, column + (startingRow + row) * 9, 8 + column * 18, y + row * 18));
            }
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    public double getBakeTime() {
        return be.getBakeTime() / 100d;
    }
}
