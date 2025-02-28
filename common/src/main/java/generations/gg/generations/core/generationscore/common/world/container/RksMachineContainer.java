
package generations.gg.generations.core.generationscore.common.world.container;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.RksMachineBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.Toggleable;
import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class RksMachineContainer extends AbstractContainerMenu implements Toggleable {
	public static final int INPUT1_SLOT = 0;
	public static final int INPUT2_SLOT = 1;
	public static final int INPUT3_SLOT = 2;
	public static final int OUTPUT_SLOT = 3;

	public static final int DATA_WEAVE_TIME = 0;
	public static final int DATA_WEAVE_TIME_TOAL = 1;
	public static final int NUM_DATA_VALUES = 2;

	protected Inventory playerInventory;
	protected Container rksMachine;
    private final ContainerData data;

	public RksMachineContainer(int id, Inventory playerInventory) {
		this(id, playerInventory, new SimpleContainer(10), new SimpleContainerData(4));
	}

    public RksMachineContainer(int id, Inventory playerInventory, Container container, ContainerData data) {
		super(GenerationsContainers.RKS_MACHINE.get(), id);

		this.playerInventory = playerInventory;
		this.rksMachine = container;
        this.data = data;

		if(container instanceof RksMachineBlockEntity rks) rks.addMenu(this);

        this.addSlot(new ResultSlot(playerInventory.player, rksMachine, 0, 124, 35));

		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				this.addSlot(new Slot(this.rksMachine, j + i * 3 + 1, 30 + j * 18, 17 + i * 18));
			}
		}

		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for(int i = 0; i < 9; ++i) {
			this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
		}

		this.addDataSlots(data);
	}

	@Override
	public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
		{
			ItemStack returnStack = ItemStack.EMPTY;
			final Slot slot = this.slots.get(index);
			if (slot != null && slot.hasItem()) {
				final ItemStack slotStack = slot.getItem();
				returnStack = slotStack.copy();

				if(index == 0 && isPokemonPresent()) {
					slot.onTake(player, returnStack);
					slot.set(ItemStack.EMPTY);
					return ItemStack.EMPTY;
				}

				final int containerSlots = this.slots.size() - player.getInventory().getContainerSize();
				if (index < containerSlots) {
					if (!moveItemStackTo(slotStack, containerSlots, this.slots.size(), true)) {
							return ItemStack.EMPTY;
					}
				} else if (!moveItemStackTo(slotStack, 0, containerSlots, false)) {
					return ItemStack.EMPTY;
				}
				if (slotStack.getCount() == 0) {
					slot.set(ItemStack.EMPTY);
				} else {
					slot.setChanged();
				}

				if (slotStack.getCount() == returnStack.getCount()) {
					return ItemStack.EMPTY;
				}
				slot.onTake(player, slotStack);
			}
			return returnStack;
		} // end transferStackInSlot()
	}

	@Override
	public boolean stillValid(@NotNull Player player) {
		return rksMachine.stillValid(player);
	}

	public int getBurnProgress(int pixels) {
		int i = this.data.get(0);
		int j = this.data.get(1);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}

	public boolean isWeaving() {
		return this.data.get(0) > 0;
	}

	@Override
	public void setToggled(boolean toggle) {
		data.set(2, toggle ? 1 : 0);
	}

	@Override
	public boolean isToggled() {
		return data.get(2) == 1;
	}

	public boolean isPokemonPresent() {
		return data.get(3) == 1;
	}

	public void close() {
		if(rksMachine instanceof RksMachineBlockEntity rks) rks.removeMenu(this);
	}

	public static class ResultSlot extends Slot {
		private final Player player;
		private int removeCount;

		public ResultSlot(Player player, Container rksMachineBlockEntity, int slot, int x, int y) {
			super(rksMachineBlockEntity, slot, x, y);
			this.player = player;
		}

		@Override
		public boolean mayPlace(@NotNull ItemStack stack) {
			return false;
		}

		@Override
		public @NotNull ItemStack remove(int amount) {
			if (this.hasItem()) {
				this.removeCount += Math.min(amount, this.getItem().getCount());
			}

			return super.remove(amount);
		}

		@Override
		public void onTake(@NotNull Player player, @NotNull ItemStack stack) {
			if(container instanceof RksMachineBlockEntity rks && rks.getRecipeUsed() instanceof RksRecipe recipe) recipe.process(player, rks, stack);
			this.checkTakeAchievements(stack);
			super.onTake(player, stack);
		}

		@Override
		protected void checkTakeAchievements(ItemStack stack) {
			stack.onCraftedBy(this.player.level(), this.player, this.removeCount);
			if(this.player instanceof ServerPlayer serverPlayer && this.container instanceof RksMachineBlockEntity blockEntity) blockEntity.awardUsedRecipesAndPopExperience(serverPlayer);
		}
	}
}
