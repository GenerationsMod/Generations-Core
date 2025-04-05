package generations.gg.generations.core.generationscore.common.world.level.block.entities;

import com.cobblemon.mod.common.api.berry.Berry;
import com.cobblemon.mod.common.api.berry.Flavor;
import com.cobblemon.mod.common.item.berry.BerryItem;
import dev.architectury.registry.menu.ExtendedMenuProvider;
import dev.architectury.registry.menu.MenuRegistry;
import earth.terrarium.botarium.common.item.ItemContainerBlock;
import earth.terrarium.common_storage_lib.item.ItemApi;
import generations.gg.generations.core.generationscore.common.api.events.CurryEvents;
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.common.util.ExtendedsimpleItemContainer;
import generations.gg.generations.core.generationscore.common.world.container.CookingPotContainer;
import generations.gg.generations.core.generationscore.common.world.container.GenerationsContainers;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryData;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryIngredient;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryType;
import generations.gg.generations.core.generationscore.common.world.item.curry.ItemCurry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CookingPotBlockEntity extends ModelProvidingBlockEntity implements MenuRegistry.ExtendedMenuTypeFactory<CookingPotContainer>, ExtendedMenuProvider, ModelContextProviders.VariantProvider, Toggleable {

    public CookingPotBlockEntity(BlockPos arg2, BlockState arg3) {
        super(GenerationsBlockEntities.COOKING_POT.get(), arg2, arg3);
    }

    //0-9: berries
    //10: main ingredient
    //11: bowl
    //12: log
    //13: out
    private ExtendedsimpleItemContainer handler;

    private String customName;
    private boolean isCooking;
    private int cookTime;

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
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);

        tag.putBoolean("isCooking", isCooking);
        tag.putInt("cookTime", this.cookTime);
//        tag.put("inventory", this.handler.serializeNBT());

        if (this.hasCustomName()) tag.putString("customName", this.customName);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        this.isCooking = nbt.getBoolean("isCooking");
        this.cookTime = nbt.getInt("cookTime");
//        this.handler.deserializeNBT(nbt.getCompound("inventory"));

        if (nbt.contains("customName", 8)) this.setCustomName(nbt.getString("customName"));
    }

    public void serverTick() {
        var container = getContainer();
        ItemStack[] berries = IntStream.rangeClosed(0, 9).mapToObj(container::getItem).toArray(ItemStack[]::new);
        ItemStack bowl = container.getItem(10);
        ItemStack mainIngredient = container.getItem(11);
        ItemStack log = container.getItem(12);

        boolean hasEverything = isCooking && Stream.of(berries).anyMatch(a -> !a.isEmpty()) && !bowl.isEmpty() && !log.isEmpty() && container.getItem(13).isEmpty();
        if (hasEverything) {
            boolean hasInserted = false;
            cookTime++;

            if (cookTime >= 200) {

                boolean hasBerry = Stream.of(berries).anyMatch(a -> a.getItem() instanceof BerryItem);
                boolean hasMaxMushrooms = Stream.of(berries).anyMatch(a -> a.getItem() == GenerationsItems.MAX_MUSHROOMS.get());

                if (hasBerry && !hasMaxMushrooms) {
                    CurryType type = !mainIngredient.isEmpty() && mainIngredient.getItem() instanceof CurryIngredient ? ((CurryIngredient) mainIngredient.getItem()).getType() : CurryType.None;
                    List<Berry> berriesTypes = Arrays.stream(berries).filter(berry -> !berry.isEmpty() && berry.getItem() instanceof BerryItem).map(a -> ((BerryItem) a.getItem()).berry()).collect(Collectors.toList());

                    CurryEvents.Cook event = new CurryEvents.Cook(type, berriesTypes, new CurryData(type, berriesTypes));

                    if (!CurryEvents.COOK.invoker().act(event).isTrue()) {
                        hasInserted = container.insertItem(13, ItemCurry.createStack(event.getOutput()), false).isEmpty();
                    }
                } else if (hasMaxMushrooms && !hasBerry && (mainIngredient.isEmpty() || mainIngredient.getItem() == GenerationsItems.MAX_HONEY.get())) {
                    AtomicInteger count = new AtomicInteger();
                    Stream.of(berries).filter(a -> a.getItem() == GenerationsItems.MAX_MUSHROOMS.get()).forEach(b -> count.addAndGet(b.getCount()));
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

                        ItemStack maxSoupStack = new ItemStack(GenerationsItems.MAX_SOUP.get());

                        if (!mainIngredient.isEmpty() && mainIngredient.getItem() == GenerationsItems.MAX_HONEY.get()) {
                            CompoundTag compound = new CompoundTag();
                            compound.putBoolean("MaxSoupHoney", true);
                            compound.putBoolean("GlowEffect", true);
                            maxSoupStack.setTag(compound);
                            log.shrink(1);
                            mainIngredient.shrink(1);
                            bowl.shrink(1);
                            cookTime = 0;
                        }

                        container.insertItem(13, maxSoupStack, false);
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
        return getContainer().getItem(11);
    }

    public ItemStack getOuput() {
        return getContainer().getItem(13);
    }

    public ItemStack getBerry(int index) {
        return index >= 0 && index < 10 ? getContainer().getItem(index) : ItemStack.EMPTY;
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

//    @Override
//    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
//        this.isCooking = pkt.getTag().getBoolean("isCooking");
//        this.cookTime = pkt.getTag().getInt("cookTime");
//    }

    @Override
    public ExtendedsimpleItemContainer getContainer() {
        return this.handler == null ? (handler = new ExtendedsimpleItemContainer(this, 14)) : handler;
    }

    @Override
    public CookingPotContainer create(int i, Inventory arg, FriendlyByteBuf arg2) {
        if (arg.player.level().getBlockEntity(arg2.readBlockPos()) instanceof CookingPotBlockEntity cookingPot) {
            return new CookingPotContainer(new GenerationsContainers.CreationContext<>(i, arg, cookingPot));
        } else {
            return null;
        }
    }

    @Override
    public void saveExtraData(FriendlyByteBuf buf) {
        buf.writeBlockPos(getBlockPos());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, @NotNull Inventory arg, @NotNull Player arg2) {
        return new CookingPotContainer(new GenerationsContainers.CreationContext<>(i, arg, this));
    }

    @Override
    public String getVariant() {
        if(hasLogs()) {
            return isCooking() ? "lit" : "unlit";
        } else {
            return "no_logs";
        }
    }

    public boolean hasLogs() {
        return !getContainer().getItem(12).isEmpty();
    }

    public static Flavor getDominantFlavor(Berry... berries) {
        Map<Flavor, Integer> output = new HashMap<>();

//        int[] output = new int[5];

        for (var berryType : berries)
            for (var flavor : com.cobblemon.mod.common.api.berry.Flavor.values())
                output.compute(flavor, (flavor1, integer) -> {
                    var i = berryType.flavor(flavor1);
                    return integer == null ? i : integer + i;
                });

        if (output.values().stream().distinct().count() <= 1) return null;

        return output.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }

    @Override
    public void setToggled(boolean toggle) {
        setCooking(toggle);
    }

    @Override
    public boolean isToggled() {
        return isCooking();
    }
}
