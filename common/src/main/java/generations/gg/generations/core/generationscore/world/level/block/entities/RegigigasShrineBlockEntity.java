package generations.gg.generations.core.generationscore.world.level.block.entities;

import com.pokemod.pokemod.api.data.pixelmon.BuiltinPixelmonSpecies;
import com.pokemod.pokemod.registries.types.PixelmonData;
import com.pokemod.pokemod.world.entity.pixelmon.PixelmonEntity;
import com.pokemod.pokemod.world.item.PokeModItems;
import com.pokemod.pokemod.world.item.RegiOrbItem;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.RegiOrbItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.OptionalInt;

public class RegigigasShrineBlockEntity extends InteractShrineBlockEntity {
    private RegigigasItemStackHandler handler = new RegigigasItemStackHandler();

    public RegigigasShrineBlockEntity(BlockPos arg2, BlockState arg3) {
        super(PokeModBlockEntities.REGIGIGAS_SHRINE.get(), arg2, arg3);
    }

    public ItemStackHandler getRegiOrbs() {
        return handler;
    }

    @Override
    protected void readNbt(CompoundTag nbt) {
        handler.deserializeNBT(nbt.getCompound("regiOrbs"));
    }

    @Override
    protected void writeNbt(CompoundTag nbt) {
        nbt.put("regiOrbs", handler.serializeNBT());
    }

    public boolean activate(ServerPlayer player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.getItem() instanceof RegiOrbItem item && !handler.contains(item)) {
            int index = OptionalInt.of(getRegiOrbIndex(item)).getAsInt();
            var remainder = handler.insertItem(index, stack, false);
            player.setItemInHand(hand, remainder);

            if (handler.isFull()) {
                toggleActive();

                level.addFreshEntity(new PixelmonEntity(level, PixelmonData.of(BuiltinPixelmonSpecies.REGIGIGAS.location()), getBlockPos()));
                handler.clear();
                toggleActive();
                sync();
            }

            return true;
        } else {
            for (int i = 0; i < 5; i++) {
                if (!handler.getStackInSlot(i).isEmpty()) {
                    player.getInventory().placeItemBackInInventory(handler.extractItem(i, 1, false));
                    return true;
                }
            }
        }

        return false;
    }

    public static int getRegiOrbIndex(RegiOrbItem item) {
        return switch (item.getSpeciesId().toString()) {
            case "pokemod:regice" -> 0;
            case "pokemod:regirock" -> 1;
            case "pokemod:registeel" -> 2;
            case "pokemod:regidrago" -> 3;
            case "pokemod:regieleki" -> 4;
            default -> -1;
        };
    }

    private Optional<RegiOrbItem> getRegiItem(int index) {
        return Optional.ofNullable(switch (index) {
            case 0 -> GenerationsItems.REGICE_ORB;
            case 1 -> GenerationsItems.REGIROCK_ORB;
            case 2 -> GenerationsItems.REGISTEEL_ORB;
            case 3 -> GenerationsItems.REGIDRAGO_ORB;
            case 4 -> GenerationsItems.REGIELEKI_ORB;
            default -> null;
        }).map(RegistrySupplier::get).map(RegiOrbItem.class::cast);
    }

    public class RegigigasItemStackHandler extends ItemStackHandler {
        public RegigigasItemStackHandler() {
            super(5);
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if(stack.getItem() instanceof RegiOrbItem orb) {
                return stacks.stream().map(ItemStack::getItem).filter(RegiOrbItem.class::isInstance).map(RegiOrbItem.class::cast).map(RegiOrbItem::getSpeciesId).noneMatch(a -> a.equals(orb.getSpeciesId()));
            }

            return false;
        }

        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            sync();
        }

        public boolean isFull() {
            return stacks.stream().noneMatch(ItemStack::isEmpty);
        }

        public void clear() {
            stacks.clear();
        }

        public boolean contains(Item item) {
            return stacks.stream().anyMatch(stack -> stack.is(item));
        }
    }
}
