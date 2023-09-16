package generations.gg.generations.core.generationscore.world.entity;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.entity.block.MagmaCrystalEntity;
import generations.gg.generations.core.generationscore.world.entity.block.SittableEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class GenerationsEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ENTITY_TYPE);

//    public static final RegistrySupplier<EntityType<PixelmonEntity>> PIXELMON = createEntityType("pixelmon", MobCategory.CREATURE, 0.6f, 1.8f, PixelmonEntity::new);
//    public static final RegistrySupplier<EntityType<StarterPickEntity>> STARTER_PICK = createEntityType("starter_pick", MobCategory.CREATURE, 0.6f, 0.6f, StarterPickEntity::new);
    public static final RegistrySupplier<EntityType<PlayerNpcEntity>> PLAYER_NPC = createEntityType("player_npc", MobCategory.MISC, 0.6f, 1.8f, PlayerNpcEntity::new);
//    public static final RegistrySupplier<EntityType<PokeBallEntity>> POKEBALL_ENTITY = createEntityType("pokeball", MobCategory.MISC, 0.2f, 0.2f, PokeBallEntity::new);
    public static final RegistrySupplier<EntityType<SittableEntity>> SEAT = createEntityType("seat", MobCategory.MISC, 0.0f, 0.0f, SittableEntity::new);
//    public static final RegistrySupplier<EntityType<StatueEntity>> STATUE = createEntityType("statue", MobCategory.MISC, 1.0f, 1.0f, StatueEntity::new);
    public static final RegistrySupplier<EntityType<TieredFishingHookEntity>> TIERED_FISHING_BOBBER = ENTITIES.register("tiered_fishing_bobber", () -> EntityType.Builder.<TieredFishingHookEntity>of(TieredFishingHookEntity::new, MobCategory.MISC).noSave().sized(0.25f, 0.25f).clientTrackingRange(4).updateInterval(5).build("tiered_fishing_bobber"));
    public static final RegistrySupplier<EntityType<MagmaCrystalEntity>> MAGMA_CRYSTAL = ENTITIES.register("magma_crystal", () -> EntityType.Builder.<MagmaCrystalEntity>of((arg, arg2) -> new MagmaCrystalEntity(arg2), MobCategory.MISC).noSave().sized(0.25f, 0.025f).clientTrackingRange(4).updateInterval(10).build("magma_crystal"));
    public static final RegistrySupplier<EntityType<GenerationsBoatEntity>> BOAT_ENTITY =
            ENTITIES.register("boat", () -> EntityType.Builder.<GenerationsBoatEntity>of(GenerationsBoatEntity::new,
                            MobCategory.MISC).fireImmune().sized(1.375F, 0.5625F)
//                    .setCustomClientFactory((spawnEntity, world) -> new PokeModBoatEntity(world, 0, 0, 0))
                    .build("boat"));

    public static final RegistrySupplier<EntityType<GenerationsChestBoatEntity>> CHEST_BOAT_ENTITY =
            ENTITIES.register("chest_boat", () -> EntityType.Builder.<GenerationsChestBoatEntity>of(GenerationsChestBoatEntity::new,
                            MobCategory.MISC).fireImmune().sized(1.375F, 0.5625F)
//                    .setCustomClientFactory((spawnEntity, world) -> new PokeModChestBoatEntity(world, 0, 0, 0))
                    .build("chest_boat"));
    public static RegistrySupplier<EntityType<StatueEntity>> STATUE_ENTITY =
            ENTITIES.register("statue", () -> EntityType.Builder.<StatueEntity>of(StatueEntity::new, MobCategory.MISC).build("statue"));

    public static void init() {
        GenerationsCore.LOGGER.info("Registering Generations entities");
        ENTITIES.register();
        EntityAttributeRegistry.register(STATUE_ENTITY, StatueEntity::createLivingAttributes);
        EntityAttributeRegistry.register(PLAYER_NPC, PlayerNpcEntity::createMobAttributes);
//        eventBus.addListener(PokeModEntities::registerEntityAttributes);
    }


//    private static void registerEntityAttributes(EntityAttributeCreationEvent event) {
//        event.put(PIXELMON.get(), PixelmonEntity.createMobAttributes().build());
//        event.put(STARTER_PICK.get(), StarterPickEntity.createAttributes().build());
//        event.put(PLAYER_NPC.get(), PlayerNpcEntity.createAttributes().build());
//        event.put(STATUE.get(), StatueEntity.createLivingAttributes().build());
//    }

    private static <T extends Entity> RegistrySupplier<EntityType<T>> createEntityType(String name, MobCategory category, float width, float height, EntityType.EntityFactory<T> factory) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(factory, category).sized(width, height).build(name));
    }
}