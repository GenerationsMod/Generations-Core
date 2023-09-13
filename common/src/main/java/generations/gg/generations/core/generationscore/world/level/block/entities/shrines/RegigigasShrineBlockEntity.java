package generations.gg.generations.core.generationscore.world.level.block.entities.shrines;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import dev.architectury.registry.registries.RegistrySupplier;
import earth.terrarium.botarium.common.item.ItemContainerBlock;
import earth.terrarium.botarium.common.item.SerializableContainer;
import generations.gg.generations.core.generationscore.util.ExtendedsimpleItemContainer;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.RegiOrbItem;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.OptionalInt;

public class RegigigasShrineBlockEntity extends InteractShrineBlockEntity implements ItemContainerBlock {
    private final RegigigasItemStackHandler handler = new RegigigasItemStackHandler();

    public RegigigasShrineBlockEntity(BlockPos arg2, BlockState arg3) {
        super(GenerationsBlockEntities.REGIGIGAS_SHRINE.get(), arg2, arg3);
    }

    public RegigigasItemStackHandler getRegiOrbs() {
        return handler;
    }

    @Override
    public SerializableContainer getContainer() {
        return handler;
    }

    public boolean activate(ServerPlayer player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.getItem() instanceof RegiOrbItem item && !handler.contains(item)) {
            player.setItemInHand(hand, handler.insertItem(OptionalInt.of(getRegiOrbIndex(item)).getAsInt(), stack, false));

            System.out.println(handler.getItems());

            if (handler.isFull()) {
                toggleActive();
                PokemonUtil.spawn(PokemonProperties.Companion.parse("regigigas", " ", "="), level, getBlockPos());
                handler.clear();
                toggleActive();
            }

            sync();

            return true;
        } else
            for (int i = 0; i < 5; i++)
                if (!handler.getItem(i).isEmpty()) {
                    player.getInventory().placeItemBackInInventory(handler.extractItem(i, 1, false));
                    return true;
                }

        return false;
    }

    public static int getRegiOrbIndex(RegiOrbItem item) {
        return switch (item.getSpeciesId()) {
            case "regice" -> 0;
            case "regirock" -> 1;
            case "registeel" -> 2;
            case "regidrago" -> 3;
            case "regieleki" -> 4;
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

    public class RegigigasItemStackHandler extends ExtendedsimpleItemContainer {
        public RegigigasItemStackHandler() {
            super(RegigigasShrineBlockEntity.this, 5);
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if(stack.getItem() instanceof RegiOrbItem orb) {
                Optional<RegiOrbItem> regiOrbItem = getRegiItem(slot).filter(regiOrb -> regiOrb.equals(orb));

                if (regiOrbItem.isPresent()) return regiOrbItem.get().equals(orb);
            }

            return false;
        }

        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }

        public boolean isFull() {
            return getItems().stream().noneMatch(ItemStack::isEmpty);
        }

        public void clear() {
            getItems().clear();
        }

        public boolean contains(Item item) {
            return getItems().stream().anyMatch(stack -> stack.is(item));
        }
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return getContainer().serialize(super.getUpdateTag());
    }
}
