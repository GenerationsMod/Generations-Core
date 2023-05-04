package generations.gg.generations.core.generationscore.world.level.block.entities;

import com.pokemod.pokemod.PokeMod;
import com.pokemod.pokemod.api.events.CurryEvents;
import com.pokemod.pokemod.client.model.ModelContextProviders;
import com.pokemod.pokemod.world.container.CookingPotContainer;
import com.pokemod.pokemod.world.container.PixelmonContainers;
import com.pokemod.pokemod.world.item.PokeModItems;
import com.pokemod.pokemod.world.item.berry.BerryItem;
import com.pokemod.pokemod.world.item.berry.BerryType;
import com.pokemod.pokemod.world.item.curry.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.IContainerFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CookingPotBlockEntity extends ModelProvidingBlockEntity implements IContainerFactory<CookingPotContainer>, MenuProvider, ModelContextProviders.VariantProvider {
    public static final ResourceLocation MODEL = PokeMod.id("models/block/utility_blocks/cooking_pot.pk");

    public CookingPotBlockEntity(BlockPos arg2, BlockState arg3) {
        super(PokeModBlockEntities.COOKING_POT.get(), arg2, arg3);
    }

    //0-9: berries
    //10: main ingredient
    //11: bowl
    //12: log
    //13: out
    private final ItemStackHandler handler = new ItemStackHandler(14) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
        }
    };

    private String customName;
    private boolean isCooking;
    private int cookTime;

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
            return (LazyOptional<T>) LazyOptional.of(() -> handler);
        return super.getCapability(cap);
    }

    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public @NotNull MutableComponent getDisplayName() {
        return this.hasCustomName() ? Component.literal(this.customName) : Component.translatable("container.cooking_pot");
    }

    @Override
    protected void writeNbt(@NotNull CompoundTag tag) {
        tag.putBoolean("isCooking", isCooking);
        tag.putInt("cookTime", this.cookTime);
        tag.put("inventory", this.handler.serializeNBT());

        if (this.hasCustomName()) tag.putString("customName", this.customName);
    }

    @Override
    public void readNbt(@NotNull CompoundTag nbt) {
        this.isCooking = nbt.getBoolean("isCooking");
        this.cookTime = nbt.getInt("cookTime");
        this.handler.deserializeNBT(nbt.getCompound("inventory"));

        if (nbt.contains("customName", 8)) this.setCustomName(nbt.getString("customName"));
    }

    public void serverTick() {
        ItemStack[] berries = IntStream.rangeClosed(0, 9).mapToObj(handler::getStackInSlot).toArray(ItemStack[]::new);
        ItemStack bowl = handler.getStackInSlot(10);
        ItemStack mainIngredient = handler.getStackInSlot(11);
        ItemStack log = handler.getStackInSlot(12);

        boolean hasEverything = isCooking && Stream.of(berries).anyMatch(a -> !a.isEmpty()) && !bowl.isEmpty() && !log.isEmpty();
        if (hasEverything) {
            boolean hasInserted = false;
            cookTime++;

            if (cookTime >= 200) {

                boolean hasBerry = Stream.of(berries).anyMatch(a -> a.getItem() instanceof BerryItem);
                boolean hasMaxMushrooms = Stream.of(berries).anyMatch(a -> a.getItem() == PokeModItems.MAX_MUSHROOMS.get());

                if (hasBerry && !hasMaxMushrooms) {
                    CurryType type = !mainIngredient.isEmpty() && mainIngredient.getItem() instanceof CurryIngredient ? ((CurryIngredient) mainIngredient.getItem()).getType() : CurryType.None;
                    List<BerryType> berriesTypes = Arrays.stream(berries).filter(berry -> !berry.isEmpty() && berry.getItem() instanceof BerryItem).map(a -> ((BerryItem) a.getItem()).getBerry()).collect(Collectors.toList());

                    CurryEvents.Cook event = new CurryEvents.Cook(type, berriesTypes, new CurryData(type, berriesTypes));

                    if (!MinecraftForge.EVENT_BUS.post(event)) {
                        hasInserted = handler.insertItem(13, ItemCurry.createStack(event.getOutput()), false).isEmpty();
                    }
                } else if (hasMaxMushrooms && !hasBerry && (mainIngredient.isEmpty() || mainIngredient.getItem() == PokeModItems.MAX_HONEY.get())) {
                    AtomicInteger count = new AtomicInteger();
                    Stream.of(berries).filter(a -> a.getItem() == PokeModItems.MAX_MUSHROOMS.get()).forEach(b -> count.addAndGet(b.getCount()));
                    if (count.get() > 2) {
                        int amountTaken = 0;

                        // Take the mushrooms
                        for (ItemStack itemStack : berries) {
                            int amountLeft = 3 - amountTaken;
                            if (itemStack.getCount() > amountLeft) {
                                itemStack.shrink(amountLeft);
                                amountTaken = 3;
                                break;
                            } else {
                                amountTaken += itemStack.getCount();
                                itemStack.setCount(0);
                            }
                        }

                        ItemStack maxSoupStack = new ItemStack(PokeModItems.MAX_SOUP.get());

                        if (!mainIngredient.isEmpty() && mainIngredient.getItem() == PokeModItems.MAX_HONEY.get()) {
                            CompoundTag compound = new CompoundTag();
                            compound.putBoolean("MaxSoupHoney", true);
                            compound.putBoolean("GlowEffect", true);
                            maxSoupStack.setTag(compound);
                            log.shrink(1);
                            mainIngredient.shrink(1);
                            bowl.shrink(1);
                            cookTime = 0;
                        }

                        handler.insertItem(13, maxSoupStack, false);
                    } else {
                        setCooking(false);
                    }
                }
            }

            if (hasInserted) {
                Arrays.stream(berries).forEach(berry -> berry.shrink(1));
                log.shrink(1);
                mainIngredient.shrink(1);
                bowl.shrink(1);
                cookTime = 0;
            }
        } else {
            setCooking(false);
        }

        sync();
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, CookingPotBlockEntity blockEntity) {
        blockEntity.serverTick();
    }

    public void setCooking(boolean isCooking) {
        this.isCooking = isCooking;
        this.cookTime = 0;
        sync();
    }

    public int getCookTime() {
        return cookTime;
    }

    public boolean isCooking() {
        return isCooking;
    }

    public ItemStack getIngredient() {
        return handler.getStackInSlot(11);
    }

    public ItemStack getOuput() {
        return handler.getStackInSlot(13);
    }

    public ItemStack getBerry(int index) {
        return index >= 0 && index < 10 ? handler.getStackInSlot(index) : ItemStack.EMPTY;
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, a -> {
            CompoundTag compound = new CompoundTag();
            compound.putBoolean("isCooking", isCooking);
            compound.putInt("cookTime", this.cookTime);
            return compound;
        });
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.isCooking = pkt.getTag().getBoolean("isCooking");
        this.cookTime = pkt.getTag().getInt("cookTime");
    }

    @Override
    public CookingPotContainer create(int i, Inventory arg, FriendlyByteBuf arg2) {
        if (arg.player.getLevel().getBlockEntity(arg2.readBlockPos()) instanceof CookingPotBlockEntity cookingPot) {
            return new CookingPotContainer(new PixelmonContainers.CreationContext<>(i, arg, cookingPot));
        } else {
            return null;
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, @NotNull Inventory arg, @NotNull Player arg2) {
        return new CookingPotContainer(new PixelmonContainers.CreationContext<>(i, arg, this));
    }

    @Override
    public String getVariant() {
        return isCooking() ? "lit" : "unlit";
    }
}
