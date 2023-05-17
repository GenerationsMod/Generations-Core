package generations.gg.generations.core.generationscore.world.entity;

import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class GenerationsBoatEntity extends Boat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE =
            SynchedEntityData.defineId(GenerationsBoatEntity.class, EntityDataSerializers.INT);

    public GenerationsBoatEntity(EntityType<? extends GenerationsBoatEntity> entityType, Level level) {
        super(entityType, level);
        this.blocksBuilding = true;
    }

    public GenerationsBoatEntity(Level worldIn, double x, double y, double z) {
        this(GenerationsEntities.BOAT_ENTITY.get(), worldIn);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putString("Type", this.getModBoatType().getName());
    }

    protected void readAdditionalSaveData(CompoundTag compound) {
        if (compound.contains("Type", 8))
            this.setBoatType(Type.byName(compound.getString("Type")));

    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, Type.GHOST.ordinal());
    }

    @Override
    public @NotNull Item getDropItem() {
        return switch (this.getModBoatType()) {
            case GHOST -> GenerationsItems.GHOST_BOAT_ITEM.get();
            case ULTRA_DARK -> GenerationsItems.ULTRA_DARK_BOAT_ITEM.get();
            case ULTRA_JUNGLE -> GenerationsItems.ULTRA_JUNGLE_BOAT_ITEM.get();
        };
    }

    public void setBoatType(Type boatType) {
        this.entityData.set(DATA_ID_TYPE, boatType.ordinal());
    }

    public Type getModBoatType() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    public enum Type {
        GHOST("ghost"),
        ULTRA_DARK("ultra_dark"),
        ULTRA_JUNGLE("ultra_jungle");

        private final String name;
        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public static Type byId(int id) {
            Type[] types = values();
            if (id < 0 || id >= types.length) id = 0;

            return types[id];
        }

        public static Type byName(String nameIn) {
            Type[] types = values();
            for (Type type : types)
                if (type.getName().equals(nameIn)) return type;

            return types[0];
        }
    }
}
