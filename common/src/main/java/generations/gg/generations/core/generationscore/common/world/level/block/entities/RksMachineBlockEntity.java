package generations.gg.generations.core.generationscore.common.world.level.block.entities;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.google.common.collect.Lists;
import generations.gg.generations.core.generationscore.common.recipe.RksInput;
import generations.gg.generations.core.generationscore.common.world.container.RksMachineContainer;
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsCoreRecipeTypes;
import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe;
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RksMachineBlockEntity extends ModelProvidingBlockEntity implements MenuProvider, Container, Toggleable {

    private static final int[] OUTPUT_SLOTS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final int[] INPUT_SLOTS = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    private static final int DEFAULT_PROCESSING_TIME = 200;
    private static final String INVENTORY_TAG = "Inventory";
    private static final String PROCESSING_TIME_TAG = "ProcessingTime";
    private static final String PROCESSING_TIME_TOTAL_TAG = "ProcessingTimeTotal";
    private static final String IS_PROCESSING_TAG = "IsProcessing";
    private final ContainerData dataAccess;

    public int processingTime;
    public int processTimeTotal;
    public Optional<Pokemon> pokemon = Optional.empty();

    private boolean isProcessing = false;

    public NonNullList<ItemStack> inventory;
    public ItemStack output = ItemStack.EMPTY;
    private RecipeHolder<RksRecipe> lastRecipe;
    private final List<RksMachineContainer> openContainers = new ArrayList<>();

    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();

    public RksMachineBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.RKS_MACHINE.get(), pos, state);
        this.inventory = NonNullList.withSize(9, ItemStack.EMPTY);

        this.dataAccess = new ContainerData() {

            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> RksMachineBlockEntity.this.processingTime;
                    case 1 -> RksMachineBlockEntity.this.processTimeTotal;
                    case 2 -> RksMachineBlockEntity.this.isProcessing ? 1 : 0;
                    case 3 -> RksMachineBlockEntity.this.pokemon.isPresent() ? 1 : 0;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 2 -> RksMachineBlockEntity.this.isProcessing = value == 1;
                }
                RksMachineBlockEntity.this.setChanged();
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt, @NotNull HolderLookup.Provider provider) {
        super.saveAdditional(nbt, provider);
        CompoundTag inventoryTag = new CompoundTag();
        ContainerHelper.saveAllItems(inventoryTag, inventory, provider);
        if(!output.isEmpty()) inventoryTag.put("Output", output.save(provider));
        nbt.put(INVENTORY_TAG, inventoryTag);
        nbt.putInt(PROCESSING_TIME_TAG, this.processingTime);
        nbt.putInt(PROCESSING_TIME_TOTAL_TAG, this.processTimeTotal);
        nbt.putBoolean(IS_PROCESSING_TAG, this.isProcessing);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag nbt, @NotNull HolderLookup.Provider provider) {
        super.loadAdditional(nbt, provider);
        CompoundTag inventoryTag = nbt.getCompound(INVENTORY_TAG);
        ContainerHelper.loadAllItems(inventoryTag, this.inventory, provider);
        this.output = ItemStack.CODEC.parse(NbtOps.INSTANCE, inventoryTag.getCompound("Output")).result().orElse(ItemStack.EMPTY);
        this.processingTime = nbt.getInt(PROCESSING_TIME_TAG);
        this.processTimeTotal = nbt.getInt(PROCESSING_TIME_TOTAL_TAG);
        this.isProcessing = nbt.getBoolean(IS_PROCESSING_TAG);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable(getBlockState().getBlock().getDescriptionId());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, @NotNull Inventory inv, @NotNull Player player) {
        return new RksMachineContainer(syncId, inv, this, dataAccess);
    }

//    @Override
//    public int[] getSlotsForFace(Direction dir) {
//        return (dir == Direction.DOWN && (!output.isEmpty() || getCurrentRecipe().isPresent())) ? OUTPUT_SLOTS : INPUT_SLOTS;
//    }
//
//    @Override
//    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, Direction dir) {
//        return slot > 0 && getItem(slot).isEmpty();
//    }
//
//    @Override
//    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
//        return slot != 0 || !output.isEmpty() || getCurrentRecipe().isPresent();
//    }

    @Override
    public boolean canPlaceItem(int slot, @NotNull ItemStack stack) {
        return slot != 0 && slot <= getContainerSize();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.inventory) {
            if (!stack.isEmpty()) return false;
        }
        return output.isEmpty();
    }

    @Override
    public int getContainerSize() {
        return 10;
    }

    public boolean isInputEmpty() {
        for (ItemStack stack : this.inventory) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }


    @Override
    public @NotNull ItemStack getItem(int slot) {
        if (slot > 0) return this.inventory.get(slot - 1);
        if (!output.isEmpty()) return output;
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack removeItem(int slot, int amount) {
        if (slot == 0) {
            return output.split(amount);
        }
        return ContainerHelper.removeItem(this.inventory, slot - 1, amount);
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int slot) {
        if (slot == 0) {
            ItemStack output = this.output;
            this.output = ItemStack.EMPTY;
            return output;
        }
        return ContainerHelper.takeItem(this.inventory, slot - 1);
    }

    @Override
    public void setItem(int slot, @NotNull ItemStack stack) {
        if (slot == 0) {
            output = stack;
            return;
        }
        inventory.set(slot - 1, stack);
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return player.getOnPos().distSqr(this.worldPosition) <= 64.0D;
    }

//    @Override
//    public void fillStackedContents(@NotNull StackedContents finder) {
//        for (ItemStack stack : this.inventory) finder.accountStack(stack);
//    }



    public boolean setLastRecipe(RecipeHolder<?> recipe) {
        if(recipe.value() instanceof RksRecipe) {
            this.lastRecipe = (RecipeHolder<RksRecipe>) recipe;
            return true;
        } else {
            return false;
        }
    }

    public RecipeHolder<RksRecipe> getLastRecipe() {
        return lastRecipe;
    }

    @Override
    public void clearContent() {
        this.inventory.clear();
    }

    private Optional<RecipeHolder<RksRecipe>> getCurrentRecipe() {
        // No need to find recipes if the inventory is empty. Cannot craft anything.
        if (this.level == null || this.isEmpty()) return Optional.empty();

        RecipeHolder<RksRecipe> lastRecipe = getLastRecipe();
        RecipeManager manager = this.level.getRecipeManager();

        if (lastRecipe != null) {
            Optional<RecipeHolder<RksRecipe>> mapRecipe = getMappedRecipe(manager, lastRecipe.id());
            if (mapRecipe.map(RecipeHolder::value).filter(a -> a.matches(getCraftingInput(), level)).isPresent()) {
                return Optional.of(lastRecipe);
            }
        }
        return getMappedRecipe(manager);
    }

    private Optional<RecipeHolder<RksRecipe>> getMappedRecipe(RecipeManager manager) {
        return manager.<RksInput, RksRecipe>getRecipeFor(GenerationsCoreRecipeTypes.RKS.get(), getCraftingInput(), level);
    }

    private Optional<RecipeHolder<RksRecipe>> getMappedRecipe(RecipeManager manager, ResourceLocation id) {
        return manager.byKey(id).filter(a -> a.value() instanceof RksRecipe).map(a -> (RecipeHolder<RksRecipe>) a);
    }

    private Optional<ItemStack> getResult() {
        Optional<ItemStack> maybe_result = getCurrentRecipe().map(recipe -> recipe.value().assemble(getCraftingInput(), null));

        return Optional.of(maybe_result.orElse(ItemStack.EMPTY));
    }

    protected boolean canSmelt(ItemStack result, RksRecipe recipe) {
        if (recipe.matches(getCraftingInput(), null)) {
            ItemStack outstack = output;
            if (outstack.isEmpty()) {
                return true;
            } else if (!ItemStack.isSameItem(outstack, result)) {
                return false;
            } else {
                return (outstack.getCount() + result.getCount() <= (recipe.isPokemonResult() ? 1 :outstack.getMaxStackSize()));
            }
        } else {
            return false;
        }
    }

    private int getProcessingTime() {
        return getCurrentRecipe().map(RecipeHolder::value).map(RksRecipe::processingTime).orElse(DEFAULT_PROCESSING_TIME);
    }

    protected void smelt(ItemStack result, RecipeHolder<RksRecipe> holder) {
//        if(recipe.isIncomplete()) return;

        var recipe = holder.value();


        if (!result.isEmpty() && this.canSmelt(result, recipe)) {
            ItemStack outstack = output.copy();

            if (outstack.isEmpty()) {
                output = result.copy();
            } else if (outstack.getItem() == result.getItem()) {
                output.grow(result.getCount());
            }

            if (!this.level.isClientSide()) {
                this.setLastRecipe(holder);
            }

            NonNullList<ItemStack> remaining = recipe.getRemainingItems(this);
            NonNullList<ItemStack> drops = NonNullList.create();

            for (int i = 0; i < 9; i++) {
                ItemStack current = inventory.get(i);
                ItemStack remainingStack = remaining.get(i);
                if (!current.isEmpty()) current.shrink(1);
                if (!remainingStack.isEmpty()) {
                    if (current.isEmpty()) {
                        inventory.set(i, remainingStack);
                    } else if (ItemStack.matches(current, remainingStack)) {
                        current.grow(remainingStack.getCount());
                    } else {
                        drops.add(remainingStack);
                    }
                }
            }

            Containers.dropContents(level, worldPosition, drops);
        }
    }

    public static void serverTick(Level level, BlockPos blockpos, BlockState blockstate, RksMachineBlockEntity tile) {
        boolean flag1 = false;

        if (tile.isToggled()) {
            ItemStack result = tile.getResult().orElse(ItemStack.EMPTY);

            Optional<RecipeHolder<RksRecipe>> recipe = tile.getCurrentRecipe();

            if (recipe.isPresent() && (!tile.isInputEmpty())) {
                if (tile.canSmelt(result, recipe.get().value())) {
                    if (tile.processingTime <= 0) {
                        tile.processTimeTotal = tile.getProcessingTime();
                        tile.processingTime = 0;
                    }
                }
                ++tile.processingTime;

                if(tile.processingTime % 60 == 0) {
                    level.playSound(null, blockpos, GenerationsSounds.RKS_MACHINE.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
                }

                if (tile.processingTime >= tile.processTimeTotal) {
                    tile.smelt(result, recipe.get());
                    tile.processingTime = 0;

                    tile.processTimeTotal = !tile.isInputEmpty() ? tile.getProcessingTime() : 0;

                    tile.setToggled(false);
                }
            }
            else {
                tile.processingTime = 0;
                tile.setToggled(false);
            }
        }/* else if (tile.processingTime > 0) {
            tile.processingTime = Mth.clamp(tile.processingTime - 2, 0, tile.processTimeTotal);
        }*/

        tile.sync();
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(@NotNull HolderLookup.Provider registries) {
        return this.saveWithFullMetadata(registries);
    }

    public void awardUsedRecipesAndPopExperience(ServerPlayer player) {
        List<RecipeHolder<?>> list = this.getRecipesToAwardAndPopExperience(player.serverLevel(), player.position());
        player.awardRecipes(list);
        this.recipesUsed.clear();
    }

    public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel level, Vec3 popVec) {
        ArrayList<RecipeHolder<?>> list = Lists.newArrayList();
        for (Object2IntMap.Entry<?> entry : this.recipesUsed.object2IntEntrySet()) {
            level.getRecipeManager().byKey((ResourceLocation)entry.getKey()).ifPresent(recipe -> {
                list.add(recipe);
                createExperience(level, popVec, entry.getIntValue(), ((RksRecipe)recipe.value()).experience());
            });
        }
        return list;
    }

    private static void createExperience(ServerLevel level, Vec3 popVec, int recipeIndex, float experience) {
        int i = Mth.floor((float)recipeIndex * experience);
        float f = Mth.frac((float)recipeIndex * experience);
        if (f != 0.0f && Math.random() < (double)f) {
            ++i;
        }
        ExperienceOrb.award(level, popVec, i);
    }

    @Override
    public void setToggled(boolean toggle) {
        isProcessing = toggle;
    }

    @Override
    public boolean isToggled() {
        return isProcessing;
    }

    public void addMenu(RksMachineContainer rksMachineContainer) {
        openContainers.add(rksMachineContainer);
    }

    public void removeMenu(RksMachineContainer rksMachineContainer) {
        openContainers.remove(rksMachineContainer);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        openContainers.forEach(AbstractContainerMenu::broadcastChanges);
    }

    public RksInput getCraftingInput() {
        return RksInput.of(3, 3, this.inventory); //TODO: finish this.
    }
}
