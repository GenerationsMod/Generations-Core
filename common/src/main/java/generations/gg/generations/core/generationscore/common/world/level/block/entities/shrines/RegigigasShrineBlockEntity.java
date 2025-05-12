package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines;

import dev.architectury.registry.registries.RegistrySupplier;
import earth.terrarium.common_storage_lib.item.impl.SimpleItemSlot;
import earth.terrarium.common_storage_lib.item.util.ItemProvider;
import earth.terrarium.common_storage_lib.resources.item.ItemResource;
import earth.terrarium.common_storage_lib.storage.base.CommonStorage;
import generations.gg.generations.core.generationscore.common.util.ExtendedsimpleItemContainer;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.item.legends.RegiOrbItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class RegigigasShrineBlockEntity extends InteractShrineBlockEntity implements ItemProvider.Block {
    private RegigigasItemStackHandler handler;

    public RegigigasShrineBlockEntity(BlockPos arg2, BlockState arg3) {
        super(GenerationsBlockEntities.REGIGIGAS_SHRINE.get(), arg2, arg3);
        handler = new RegigigasItemStackHandler();
    }

    public RegigigasItemStackHandler getRegiOrbs() {
        return handler;
    }

    @Override
    public CommonStorage<ItemResource> getItems(Level level, BlockPos blockPos, @Nullable BlockState blockState, @Nullable BlockEntity blockEntity, @Nullable Direction direction) {
        return getContainer();
    }

    public RegigigasItemStackHandler getContainer() {
        return handler;
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
        public boolean isResourceValid(int index, ItemResource resource) {
            if(resource.getItem() instanceof RegiOrbItem orb) {
                Optional<RegiOrbItem> regiOrbItem = getRegiItem(index).filter(regiOrb -> regiOrb.equals(orb));

                if (regiOrbItem.isPresent()) return regiOrbItem.get().equals(orb);
            }

            return false;
        }

        @Override
        public long getLimit(int index, ItemResource resource) {
            return 1;
        }

        public boolean isFull() {
            return getContainer().slots.stream().map(SimpleItemSlot::getResource).noneMatch(ItemResource::isBlank);
        }

        public void clear() { //TODO: work on
//            getItems().clear();
//            setChanged();
        }

        public boolean contains(Item item) {
            return getContainer().slots.stream().map(SimpleItemSlot::getResource).anyMatch(stack -> stack.isOf(item));
        }
    }

//    @Override
//    public @NotNull CompoundTag getUpdateTag() {
//        return getContainer().serialize(super.getUpdateTag());
//    }

}
