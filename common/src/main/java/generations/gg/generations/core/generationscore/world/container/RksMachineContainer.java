
package generations.gg.generations.core.generationscore.world.container;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.level.block.RksMachineBlock;
import generations.gg.generations.core.generationscore.world.level.block.entities.RksMachineBlockEntity;
import generations.gg.generations.core.generationscore.world.recipe.RksRecipe;
import generations.gg.generations.core.generationscore.world.recipe.RksResult;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public class RksMachineContainer extends AbstractContainerMenu {
	public static final int INPUT1_SLOT = 0;
	public static final int INPUT2_SLOT = 1;
	public static final int INPUT3_SLOT = 2;
	public static final int OUTPUT_SLOT = 3;

	public static final int DATA_WEAVE_TIME = 0;
	public static final int DATA_WEAVE_TIME_TOAL = 1;
	public static final int NUM_DATA_VALUES = 2;

	protected Inventory playerInventory;
	protected RksMachineBlockEntity rksMachine;

	public RksMachineContainer(GenerationsContainers.CreationContext<RksMachineBlockEntity> ctx) {
		super(GenerationsContainers.RKS_MACHINE.get(), ctx.id());
		this.playerInventory = ctx.playerInv();
		this.rksMachine = ctx.blockEntity();

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
	}

	public RksMachineBlockEntity getRksMachine() {
		return rksMachine;
	}


	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		{
			ItemStack returnStack = ItemStack.EMPTY;
			final Slot slot = this.slots.get(index);
			if (slot != null && slot.hasItem()) {
				final ItemStack slotStack = slot.getItem();
				returnStack = slotStack.copy();

				final int containerSlots =
						this.slots.size() - player.getInventory().getContainerSize();
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
	public boolean stillValid(Player player) {
		return rksMachine.stillValid(player);
	}

	public int getBurnProgress(int pixels) {
		int i = this.rksMachine.processingTime;
		int j = this.rksMachine.processTimeTotal;
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}

	public boolean isWeaving() {
		return this.rksMachine.processingTime > 0;
	}

	public void toggle(boolean b) {
		rksMachine.setToggled(b);
	}

	public static class ResultSlot extends Slot {
		private final Player player;
		private int removeCount;

		public ResultSlot(Player player, RksMachineBlockEntity container, int slot, int x, int y) {
			super(container, slot, x, y);
			this.player = player;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}

		@Override
		public ItemStack remove(int amount) {
			if (this.hasItem()) {
				this.removeCount += Math.min(amount, this.getItem().getCount());
			}

			return super.remove(amount);
		}

		@Override
		public void onTake(Player player, ItemStack stack) {
			if(((RksMachineBlockEntity) container).getRecipeUsed() instanceof RksRecipe recipe && recipe.isPokemonResult()) {
				var rksmachine = ((RksMachineBlockEntity) container);
				var pos = rksmachine.getBlockPos();
				var dir = rksmachine.getBlockState().getValue(RksMachineBlock.FACING);

				var result = ((RksResult.PokemonResult) recipe.getResult());
				var properties = new PokemonProperties();
				properties.setSpecies(result.species().toString());
				properties.setAspects(result.aspects());
				properties.setLevel(result.level());
				PokemonUtil.spawn(properties, rksmachine.getLevel(), pos.above(2), dir.toYRot());
				stack.setCount(0);
			} else {
				this.checkTakeAchievements(stack);
			}
			super.onTake(player, stack);
		}

		@Override
		protected void checkTakeAchievements(ItemStack stack) {
			stack.onCraftedBy(this.player.level(), this.player, this.removeCount);
			if(this.player instanceof ServerPlayer serverPlayer && this.container instanceof RksMachineBlockEntity container) container.awardUsedRecipesAndPopExperience(serverPlayer);
		}
	}
}
