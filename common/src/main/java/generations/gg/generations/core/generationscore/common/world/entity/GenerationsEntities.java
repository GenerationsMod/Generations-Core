package generations.gg.generations.core.generationscore.common.world.entity;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.entity.block.MagmaCrystalEntity;
import generations.gg.generations.core.generationscore.common.world.entity.block.SittableEntity;
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class GenerationsEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<SittableEntity>> SEAT = createEntityType("seat", MobCategory.MISC, 0.0f, 0.0f, SittableEntity::new);
    public static final RegistrySupplier<EntityType<TieredFishingHookEntity>> TIERED_FISHING_BOBBER = ENTITIES.register("tiered_fishing_bobber", () -> EntityType.Builder.<TieredFishingHookEntity>of(TieredFishingHookEntity::new, MobCategory.MISC).noSave().sized(0.25f, 0.25f).clientTrackingRange(4).updateInterval(5).build("tiered_fishing_bobber"));
    public static final RegistrySupplier<EntityType<MagmaCrystalEntity>> MAGMA_CRYSTAL = ENTITIES.register("magma_crystal", () -> EntityType.Builder.<MagmaCrystalEntity>of((arg, arg2) -> new MagmaCrystalEntity(arg2), MobCategory.MISC).noSave().sized(0.25f, 0.025f).clientTrackingRange(4).updateInterval(10).build("magma_crystal"));
    public static final RegistrySupplier<EntityType<GenerationsBoatEntity>> BOAT_ENTITY = createEntityType("boat", MobCategory.MISC, EntityType.BOAT.getWidth(), EntityType.BOAT.getHeight(), GenerationsBoatEntity::new);
    public static final RegistrySupplier<EntityType<GenerationsChestBoatEntity>> CHEST_BOAT_ENTITY = createEntityType("chest_boat", MobCategory.MISC, EntityType.CHEST_BOAT.getWidth(), EntityType.CHEST_BOAT.getHeight(), GenerationsChestBoatEntity::new);

    public static RegistrySupplier<EntityType<StatueEntity>> STATUE_ENTITY = ENTITIES.register("statue", () -> EntityType.Builder.<StatueEntity>of((entityType, level) -> new StatueEntity(level), MobCategory.MISC).build("statue"));

    public static RegistrySupplier<EntityType<ZygardeCellEntity>> ZYGARDE_CELL = ENTITIES.register("zygarde_cell", () -> EntityType.Builder.<ZygardeCellEntity>of(ZygardeCellEntity::new, MobCategory.MISC).build("zygarde_cell"));


    public static void init() {
        GenerationsCore.LOGGER.info("Registering Generations entities");
        ENTITIES.register();
    }

    private static <T extends Entity> RegistrySupplier<EntityType<T>> createEntityType(String name, MobCategory category, float width, float height, EntityType.EntityFactory<T> factory) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(factory, category).sized(width, height).build(name));
    }
}