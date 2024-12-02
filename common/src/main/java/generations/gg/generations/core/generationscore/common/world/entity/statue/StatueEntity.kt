package generations.gg.generations.core.generationscore.common.world.entity.statue

import com.cobblemon.mod.common.api.net.serializers.PoseTypeDataSerializer
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.PokemonProperties.Companion.parse
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.api.scheduling.Schedulable
import com.cobblemon.mod.common.api.scheduling.SchedulingTracker
import com.cobblemon.mod.common.api.text.text
import com.cobblemon.mod.common.entity.PoseType
import com.cobblemon.mod.common.entity.Poseable
import generations.gg.generations.core.generationscore.common.api.data.GenerationsCoreEntityDataSerializers
import generations.gg.generations.core.generationscore.common.api.events.general.StatueEvents
import generations.gg.generations.core.generationscore.common.network.GenerationsNetwork
import generations.gg.generations.core.generationscore.common.network.packets.statue.S2COpenStatueEditorScreenPacket
import generations.gg.generations.core.generationscore.common.network.spawn.SpawnStatuePacket
import generations.gg.generations.core.generationscore.common.util.DataKeys
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3

class StatueEntity(level: Level) : Entity(GenerationsEntities.STATUE_ENTITY.get(), level), Poseable, Schedulable {
    companion object {
        val PROPERTIES = SynchedEntityData.defineId(StatueEntity::class.java, GenerationsCoreEntityDataSerializers.PROPERTIES)
        val LABEL = SynchedEntityData.defineId(StatueEntity::class.java, GenerationsCoreEntityDataSerializers.NULLABLE_STRING)
        val SCALE = SynchedEntityData.defineId(StatueEntity::class.java, EntityDataSerializers.FLOAT)
        val POSE_TYPE = SynchedEntityData.defineId(StatueEntity::class.java, PoseTypeDataSerializer)
        val STATIC_TOGGLE = SynchedEntityData.defineId(StatueEntity::class.java, EntityDataSerializers.BOOLEAN)
        val STATIC_PARTIAL = SynchedEntityData.defineId(StatueEntity::class.java, EntityDataSerializers.FLOAT)
        val STATIC_AGE = SynchedEntityData.defineId(StatueEntity::class.java, EntityDataSerializers.INT)
        val INTERACTABLE = SynchedEntityData.defineId(StatueEntity::class.java, EntityDataSerializers.BOOLEAN)
        val MATERIAL = SynchedEntityData.defineId(StatueEntity::class.java, GenerationsCoreEntityDataSerializers.NULLABLE_STRING)
        val ORIENTATION = SynchedEntityData.defineId(StatueEntity::class.java, EntityDataSerializers.FLOAT)
    }

    var savesToWorld = true

    override val schedulingTracker = SchedulingTracker()

    override fun isCustomNameVisible(): Boolean {
        return true
    }


    override val delegate = if (level.isClientSide) {
        generations.gg.generations.core.generationscore.common.client.entity.StatueClientDelegate()
    } else {
        StatueServerDelegate()
    }

    override fun onSyncedDataUpdated(key: EntityDataAccessor<*>) {
        super.onSyncedDataUpdated(key)

        when(key) {
            PROPERTIES -> delegate.updatePoke(properties)
            SCALE -> refreshDimensions()
            MATERIAL -> delegate.updateMaterial(material)
        }
    }

    var properties: PokemonProperties
        get() = this.getEntityData()[PROPERTIES]
        set(value) {
            this.entityData.set(PROPERTIES, value)
        }
    var label : String?
        get() = this.entityData[LABEL]
        set(value) = this.entityData.set(LABEL, value)
    @Deprecated("Marked for removal in 1.21 due to native entity scaling being available")
    var scale: Float
        get() = this.entityData[SCALE]
        set(value) {
            this.entityData.set(SCALE, value)
        }

    var poseType: PoseType
        get() = this.entityData[POSE_TYPE]
        set(value) {
            this.entityData[POSE_TYPE] = value
        }

    var staticToggle: Boolean
        get() = this.entityData[STATIC_TOGGLE]
        set(value) {
            this.entityData[STATIC_TOGGLE] = value
        }

    var staticPartial: Float
        get() = this.entityData[STATIC_PARTIAL]
        set(value) {
            this.entityData[STATIC_PARTIAL] = value
        }

    var staticAge: Int
        get() = this.entityData[STATIC_AGE]
        set(value) {
            this.entityData[STATIC_AGE] = value
        }

    var interactable: Boolean
        get() = this.entityData[INTERACTABLE]
        set(value) {
            this.entityData[INTERACTABLE] = value
        }

    var material: String?
        get() = this.entityData[MATERIAL]
        set(value) {
            this.entityData[MATERIAL] = value
        }

    var orientation: Float
        get() = this.entityData[ORIENTATION]
        set(value) {
            this.entityData[ORIENTATION] = value
        }

    override fun defineSynchedData() {
        this.entityData.define(PROPERTIES, parse("charizard"))
        this.entityData.define(LABEL, "Statue")
        this.entityData.define(SCALE, 1.0f)
        this.entityData.define(POSE_TYPE, PoseType.NONE)
        this.entityData.define(STATIC_TOGGLE, false)
        this.entityData.define(STATIC_PARTIAL, 0.0f)
        this.entityData.define(STATIC_AGE, 0)
        this.entityData.define(INTERACTABLE, false)
        this.entityData.define(MATERIAL, "")
        this.entityData.define(ORIENTATION, 0.0f)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        this.properties = compound.getPokemonProperties(DataKeys.PROPERTIES)
        this.label = compound.getStringOrNull(DataKeys.LABEL)
        this.scale = compound.getFloat(DataKeys.SCALE)
        this.poseType = PoseType.entries[compound.getByte(DataKeys.POSE_TYPE).toInt()]
        this.staticToggle = compound.getBoolean(DataKeys.STATIC_TOGGLE)
        this.staticPartial = compound.getFloat(DataKeys.STATIC_PARTIAL)
        this.staticAge = compound.getInt(DataKeys.STATIC_AGE)
        this.interactable = compound.getBoolean(DataKeys.INTERACTABLE)
        this.material = compound.getStringOrNull(DataKeys.MATERIAL)
        this.orientation = compound.getFloat(DataKeys.ORIENTATION)
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        compound.putPokemonProperties(DataKeys.PROPERTIES, this.properties)
        compound.putNullableString(DataKeys.LABEL, label)
        compound.putFloat(DataKeys.SCALE, this.scale)
        compound.putByte(DataKeys.POSE_TYPE, this.poseType.ordinal.toByte())
        compound.putBoolean(DataKeys.STATIC_TOGGLE, this.staticToggle)
        compound.putFloat(DataKeys.STATIC_PARTIAL, this.staticPartial)
        compound.putInt(DataKeys.STATIC_AGE, this.staticAge)
        compound.putBoolean(DataKeys.INTERACTABLE, this.interactable)
        compound.putNullableString(DataKeys.MATERIAL, this.material)
        compound.putFloat(DataKeys.ORIENTATION, this.orientation)
    }

    init {
        isNoGravity = true
        isInvulnerable = true
        noPhysics = true
//        EnvExecutor.runInEnv(Env.CLIENT) { Runnable { delegate = StatueEntityClient(this) } }
    }

    override fun isPickable(): Boolean = true

    override fun canBeCollidedWith(): Boolean = true

    override fun shouldBeSaved(): Boolean = super.shouldBeSaved() && this.savesToWorld

    override fun getDimensions(pose: Pose): EntityDimensions = properties.getHitBox() ?: this.type.dimensions

    override fun getCurrentPoseType(): PoseType = poseType

    override fun getAddEntityPacket() = GenerationsNetwork.asVanillaClientBound(
        SpawnStatuePacket(
            properties = properties,
            label = label,
            scale = scale,
            poseType = poseType,
            staticToggle = staticToggle,
            staticPartial = staticPartial,
            staticAge =  staticAge,
            interactable = interactable,
            material = material,
            orientation = orientation,
            vanillaSpawnPacket = super.getAddEntityPacket() as ClientboundAddEntityPacket
        )
    )

    override fun tick() {
        super.tick()
        delegate.tick(this)
        schedulingTracker.update(1/20F)
    }

    override fun isAttackable(): Boolean {
        return false
    }


    override fun interact(player: Player, hand: InteractionHand): InteractionResult {
        if (!level().isClientSide()) {
            val stack = player.getItemInHand(hand)
            if (stack.`is`(GenerationsItems.SACRED_ASH.get()) && interactable /* && SacredAshItem.isFullyCharged(stack)*/) {
                val entity = properties.createEntity(level())
                entity.setPos(Vec3.atBottomCenterOf(onPos.above()))
                level().addFreshEntity(entity)
                stack.shrink(1)
                return InteractionResult.SUCCESS
            } else if (player.getItemInHand(hand).item == GenerationsItems.CHISEL.get()) {
                if (!StatueEvents.CAN_USE_CHISEL.invoker().canUse(player as ServerPlayer?, player.isCreative())) return InteractionResult.PASS

                if (player.isShiftKeyDown) {
                    this.remove(RemovalReason.KILLED)
                } else {
                    GenerationsNetwork.sendPacketToPlayer(player, S2COpenStatueEditorScreenPacket(id))
                }
                return InteractionResult.SUCCESS
            }
        }
        return super.interact(player, hand)
    }

    override fun isPushable(): Boolean {
        return false
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    fun renderablePokemon() = properties.asRenderablePokemon()

    override fun hasCustomName(): Boolean {
        return label?.isNotBlank() != null
    }

    override fun getCustomName(): Component? = label?.text()
}

private fun CompoundTag.getPokemonProperties(key: String): PokemonProperties = parse(this.getString(key))
private fun CompoundTag.getStringOrNull(key: String): String? = if(this.contains(key)) this.getString(key) else null
private fun CompoundTag.putPokemonProperties(key: String, properties: PokemonProperties) = this.putString(key, properties.asString())
private fun CompoundTag.putNullableString(key: String, value: String?) = value?.run { this@putNullableString.putString(key, this) }
fun PokemonProperties.getHitBox(): EntityDimensions? = this.species?.let { PokemonSpecies.getByName(it) }?.getForm(this.aspects)?.hitbox
