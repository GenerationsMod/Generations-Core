package generations.gg.generations.core.generationscore.common.world.entity

import generations.gg.generations.core.generationscore.common.world.entity.GenerationsBoatEntity
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import net.minecraft.core.Holder
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.vehicle.Boat
import net.minecraft.world.item.Item
import net.minecraft.world.level.Level

open class GenerationsBoatEntity(entityType: EntityType<out GenerationsBoatEntity>, level: Level) :
    Boat(entityType, level) {
    init {
        this.blocksBuilding = true
    }

    constructor(worldIn: Level, x: Double, y: Double, z: Double) : this(
        GenerationsEntities.BOAT_ENTITY.asValue(),
        worldIn
    ) {
        this.setPos(x, y, z)
        this.xo = x
        this.yo = y
        this.zo = z
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        compound.putString("Type", modBoatType.name)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        if (compound.contains("Type", 8)) this.setBoatType(Type.byName(compound.getString("Type")))
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(DATA_ID_TYPE, Type.GHOST.ordinal)
    }

    override fun getDropItem(): Item {
        return when (this.modBoatType) {
            Type.GHOST -> GenerationsItems.GHOST_BOAT_ITEM.value()
            Type.ULTRA_DARK -> GenerationsItems.ULTRA_DARK_BOAT_ITEM.value()
            Type.ULTRA_JUNGLE -> GenerationsItems.ULTRA_JUNGLE_BOAT_ITEM.value()
        }
    }

    fun setBoatType(boatType: Type) {
        entityData.set(DATA_ID_TYPE, boatType.ordinal)
    }

    val modBoatType: Type
        get() = Type.byId(
            entityData.get(DATA_ID_TYPE)
        )

    enum class Type(val id: String) {
        GHOST("ghost"),
        ULTRA_DARK("ultra_dark"),
        ULTRA_JUNGLE("ultra_jungle");

        companion object {
            fun byId(id: Int): Type {
                var id = id
                val types = entries.toTypedArray()
                if (id < 0 || id >= types.size) id = 0

                return types[id]
            }

            fun byName(nameIn: String): Type {
                val types = entries.toTypedArray()
                for (type in types) if (type.id == nameIn) return type

                return types[0]
            }
        }
    }

    companion object {
        private val DATA_ID_TYPE: EntityDataAccessor<Int> = SynchedEntityData.defineId(
            GenerationsBoatEntity::class.java, EntityDataSerializers.INT
        )
    }
}

fun <T: Entity> Holder<EntityType<*>>.asValue(): EntityType<T> = this.value() as EntityType<T>
