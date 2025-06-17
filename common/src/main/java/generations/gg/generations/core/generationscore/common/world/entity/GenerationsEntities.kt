package generations.gg.generations.core.generationscore.common.world.entity

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import generations.gg.generations.core.generationscore.common.world.entity.block.MagmaCrystalEntity
import generations.gg.generations.core.generationscore.common.world.entity.block.SittableEntity
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory

object GenerationsEntities: PlatformRegistry<EntityType<*>>() {
    override val registry: Registry<EntityType<*>> = BuiltInRegistries.ENTITY_TYPE
    override val resourceKey: ResourceKey<Registry<EntityType<*>>> = Registries.ENTITY_TYPE

    val SEAT = createEntityType("seat", MobCategory.MISC, 0.0f, 0.0f, ::SittableEntity)
    val TIERED_FISHING_BOBBER = register("tiered_fishing_bobber") { EntityType.Builder.of(::TieredFishingHookEntity, MobCategory.MISC).noSave().sized(0.25f, 0.25f).clientTrackingRange(4).updateInterval(5).build("tiered_fishing_bobber") }
    val MAGMA_CRYSTAL = register("magma_crystal") { EntityType.Builder.of(::MagmaCrystalEntity, MobCategory.MISC).noSave().sized(0.25f, 0.025f).clientTrackingRange(4).updateInterval(10).build("magma_crystal") }
    val BOAT_ENTITY = createEntityType("boat", MobCategory.MISC, EntityType.BOAT.width, EntityType.BOAT.height, ::GenerationsBoatEntity)
    val CHEST_BOAT_ENTITY = createEntityType("chest_boat", MobCategory.MISC, EntityType.CHEST_BOAT.width, EntityType.CHEST_BOAT.height, ::GenerationsChestBoatEntity)

    var STATUE_ENTITY = register("statue") { EntityType.Builder.of(::StatueEntity, MobCategory.MISC).build("statue") }

    var ZYGARDE_CELL = register("zygarde_cell") { EntityType.Builder.of(::ZygardeCellEntity, MobCategory.MISC).build("zygarde_cell") }

    fun <T: Entity> register(name: String, type: () -> EntityType<T>): Holder<EntityType<*>> = create(name, type)

    private fun <T : Entity> createEntityType(
        name: String,
        category: MobCategory,
        width: Float,
        height: Float,
        factory: EntityType.EntityFactory<T>
    ): Holder<EntityType<*>> {
        return create(name) { EntityType.Builder.of(factory, category).sized(width, height).build(name) }
    }

    override fun init() {
        GenerationsCore.LOGGER.info("Registering Generations entities")
        super.init()
    }

}