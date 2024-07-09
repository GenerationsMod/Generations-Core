package generations.gg.generations.core.generationscore.world.entity

import com.cobblemon.mod.common.api.net.serializers.PoseTypeDataSerializer
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.PokemonProperties.Companion.parse
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies.getByIdentifier
import com.cobblemon.mod.common.api.scheduling.Schedulable
import com.cobblemon.mod.common.api.scheduling.SchedulingTracker
import com.cobblemon.mod.common.entity.PoseType
import com.cobblemon.mod.common.entity.Poseable
import com.cobblemon.mod.common.pokemon.RenderablePokemon
import com.cobblemon.mod.common.pokemon.Species
import dev.architectury.utils.Env
import dev.architectury.utils.EnvExecutor
import generations.gg.generations.core.generationscore.api.data.GenerationsCoreEntityDataSerializers
import generations.gg.generations.core.generationscore.client.StatueEntityClient
import generations.gg.generations.core.generationscore.client.render.rarecandy.CobblemonInstance
import generations.gg.generations.core.generationscore.network.GenerationsNetwork
import generations.gg.generations.core.generationscore.network.packets.statue.S2COpenStatueEditorScreenPacket
import generations.gg.generations.core.generationscore.world.entity.statue.StatueClientDelegate
import generations.gg.generations.core.generationscore.world.entity.statue.StatueServerDelegate
import generations.gg.generations.core.generationscore.world.item.GenerationsItems
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3
import java.util.*

class StatueEntity(level: Level, val species: ResourceLocation) : Entity(GenerationsEntities.STATUE_ENTITY.get(), level), Poseable, Schedulable {
    companion object {
        val PROPERTIES = SynchedEntityData.defineId(StatueEntity::class.java, GenerationsCoreEntityDataSerializers.PROPERTIES)
        val LABEL = SynchedEntityData.defineId(StatueEntity::class.java, GenerationsCoreEntityDataSerializers.NULLABLE_STRING)
        val SCALE = SynchedEntityData.defineId(StatueEntity::class.java, EntityDataSerializers.FLOAT)
        val POSE_TYPE = SynchedEntityData.defineId(StatueEntity::class.java, PoseTypeDataSerializer)
        val STATIC = SynchedEntityData.defineId(StatueEntity::class.java, EntityDataSerializers.BOOLEAN)
        val FRAME = SynchedEntityData.defineId(StatueEntity::class.java, EntityDataSerializers.FLOAT)
        val INTERACTABLE = SynchedEntityData.defineId(StatueEntity::class.java, EntityDataSerializers.BOOLEAN)
        val MATERIAL = SynchedEntityData.defineId(StatueEntity::class.java, GenerationsCoreEntityDataSerializers.NULLABLE_STRING)
    }

    var savesToWorld = false
    override val schedulingTracker = SchedulingTracker()
    override val delegate = if (level.isClientSide) {
        // Don't import because scanning for imports is a CI job we'll do later to detect errant access to client from server
        StatueClientDelegate()
    } else {
        StatueServerDelegate()
    }

    private var dimensions: EntityDimensions? = null
    private var sizeChanged = true

    override fun isNoGravity(): Boolean = true

    override fun isInvulnerable(): Boolean = true

    init {
        noPhysics = true
    }

    override fun isAttackable(): Boolean = false

    override fun tick() {
        super.tick()
        EnvExecutor.runInEnv(Env.CLIENT) { Runnable { delegate!!.tick() } }
    }

    var properties: PokemonProperties
        get() = entityData.get(PROPERTIES)
        set(value) = entityData.set(PROPERTIES, value)

    var label: String?
        get() = entityData.get(LABEL)
        set(value) = entityData.set(LABEL, value)

    val pokemon = properties.create()

    var frame: Float
        get() = entityData.get(FRAME)
        set(value) = entityData.set(FRAME, value)

    var static: Boolean
        get() = entityData[STATIC]
        set(value) = entityData.set(STATIC, value)

    var material: String?
        get() = entityData.get(MATERIAL)
        set(value) = entityData.set(MATERIAL, value)

    override fun interact(player: Player, hand: InteractionHand): InteractionResult {
        if (!level().isClientSide()) {
            val stack = player.getItemInHand(hand)
            if (stack.`is`(GenerationsItems.SACRED_ASH.get()) && statueData.isSacredAshInteractable /* && SacredAshItem.isFullyCharged(stack)*/) {
                val entity = statueData.properties.createEntity(level())
                entity.setPos(Vec3.atBottomCenterOf(onPos.above()))
                level().addFreshEntity(entity)
                stack.shrink(1)
                return InteractionResult.SUCCESS
            } else if (player.getItemInHand(hand).item == GenerationsItems.CHISEL.get()) {
                if (player.isShiftKeyDown) {
                    this.remove(RemovalReason.KILLED)
                } else {
                    GenerationsNetwork.INSTANCE.sendPacketToPlayer(
                        player as ServerPlayer, S2COpenStatueEditorScreenPacket(
                            id
                        )
                    )
                }
                return InteractionResult.SUCCESS
            }
        }
        return super.interact(player, hand)
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        return super.hurt(source, amount)
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(STATUE_DATA, StatueInfo())
    }

    override fun addAdditionalSaveData(tag: CompoundTag) {
        tag.put("data", statueData.serializeNbt())
    }

    override fun readAdditionalSaveData(tag: CompoundTag) {
        setStatueInfo(StatueInfo(tag.getCompound("data")))
        refreshInstance()
        sizeChanged = true
    }

    val statueData: StatueInfo
        get() = getEntityData().get(STATUE_DATA)

    fun setStatueInfo(data: StatueInfo) {
        getEntityData().set(STATUE_DATA, data)
        setRotationFromStatueData()
        refreshInstance()
        sizeChanged = true
    }

    private fun refreshInstance() {
        EnvExecutor.runInEnv(Env.CLIENT) { Runnable { delegate!!.setInstance(null) } }
    }

    override fun getDimensions(pose: Pose): EntityDimensions {
        if (sizeChanged) {
            dimensions = info.form.hitbox
            sizeChanged = false
        }
        return dimensions!!
    }

    private fun setRotationFromStatueData() {
        val orientation = statueData.orientation % 360
        yRot = orientation
        yRotO = orientation
    }

    val info = statueData.properties.asRenderablePokemon()

    fun updateStatueData() {
        setStatueInfo(statueData)
    }

    override fun interactAt(player: Player, vec: Vec3, hand: InteractionHand): InteractionResult {
        return super.interactAt(player, vec, hand)
    }

    override fun onSyncedDataUpdated(key: EntityDataAccessor<*>) {
        if (key == STATUE_DATA) {
            updateStatueData()
            refreshDimensions()
        }
        super.onSyncedDataUpdated(key)
    }

    override fun isPushable(): Boolean = false

    override fun isPushedByFluid(): Boolean = false

    override fun getAspects(): Set<String> {
        return statueData.asRenderablePokemon().aspects
    }

    override fun getScale(): Float {
        return statueData.scale
    }

    override fun getDisplayName(): Component {
        return if (label != null) Component.nullToEmpty(label) else super.getDisplayName()
    }

    class StatueInfo @JvmOverloads constructor(
        @JvmField var properties: PokemonProperties = parse("species=charizard", " ", "="),
        label: String? = "Statue",
        orientation: Float = 0.0f,
        scale: Float = 1.0f,
        poseType: PoseType? = PoseType.NONE,
        isStatic: Boolean = false,
        progress: Float = 0.0f,
        sacredAshInteractable: Boolean = false,
        material: String? = null
    ) {
        @JvmField
        var orientation: Float
        @JvmField
        var poseType: PoseType
        @JvmField
        var isStatic: Boolean
        var frame: Float
            private set
        var scale: Float
        var isSacredAshInteractable: Boolean
        var label: String? = null
        private var material: String? = null

        init {
            this.label = label
            this.orientation = orientation
            this.scale = scale
            this.poseType = poseType ?: PoseType.NONE
            this.isStatic = isStatic
            frame = progress
            isSacredAshInteractable = sacredAshInteractable
            this.material = material
        }

        constructor(buf: FriendlyByteBuf) : this(
            parse(buf.readUtf(), " ", "="),
            buf.readNullable<String> { obj: FriendlyByteBuf -> obj.readUtf() },
            buf.readFloat(),
            buf.readFloat(),
            buf.readEnum<PoseType>(PoseType::class.java),
            buf.readBoolean(),
            buf.readFloat(),
            buf.readBoolean(),
            buf.readNullable<String> { obj: FriendlyByteBuf -> obj.readUtf() })

        fun serializeToByteBuf(buf: FriendlyByteBuf) {
            buf.writeUtf(properties.asString(" "))
                .writeNullable(label, FriendlyByteBuf.Writer { obj: FriendlyByteBuf -> obj.writeUtf() })
            buf.writeFloat(orientation).writeFloat(scale)
            buf.writeEnum(poseType)
                .writeBoolean(isStatic)
                .writeFloat(frame)
                .writeBoolean(isSacredAshInteractable)
            buf.writeNullable(material, FriendlyByteBuf.Writer { obj: FriendlyByteBuf -> obj.writeUtf() })
        }

        constructor(tag: CompoundTag) : this(
            parse(tag.getString("properties"), " ", "="),
            tag.getString("label"),
            tag.getFloat("orientation"),
            tag.getFloat("scale"),
            toPoseType(tag.getString("poseType")),
            tag.getBoolean("isStatic"),
            tag.getFloat("progress"),
            tag.getBoolean("sacredAshInteractable"),
            tag.getString("material")
        )

        fun asRenderablePokemon(): RenderablePokemon {
            val properties = properties
            var species: Species? = null
            if (properties.species != null) {
                species = getByIdentifier(ResourceLocation("cobblemon", properties.species))
                if (species == null) {
                    species = getByIdentifier(defaultSpecies)
                }
            } else {
                species = getByIdentifier(defaultSpecies)
            }
            return RenderablePokemon(species!!, properties.aspects)
        }

        val isAnimated: Boolean
            get() = !isStatic

        fun serializeNbt(): CompoundTag {
            val tag = CompoundTag()
            tag.putString("properties", properties.asString(" "))
            tag.putFloat("orientation", orientation)
            tag.putFloat("scale", scale)
            tag.putString("poseType", poseType.toString())
            tag.putBoolean("isStatic", isStatic)
            tag.putFloat("progress", frame)
            tag.putBoolean("sacredAshInteractable", isSacredAshInteractable)
            if (label != null) tag.putString("label", label)
            if (material != null) tag.putString("material", material.toString())
            return tag
        }

        fun setPosType(posType: String) {
            poseType = toPoseType(posType)
        }

        fun setProgress(timestamp: Float) {
            frame = timestamp
        }

        fun material(): String? {
            return material
        }

        fun setMaterial(material: String?) {
            this.material = material
        }

        companion object {
            private val defaultSpecies = ResourceLocation("cobblemon", "charizard")
            private fun toPoseType(poseType: String): PoseType {
                return try {
                    valueOf.valueOf(poseType.uppercase(Locale.getDefault()))
                } catch (e: Exception) {
                    PoseType.NONE
                }
            }

            fun of(properties: PokemonProperties): StatueInfo {
                return StatueInfo(properties, "", 0.0f, 1.0f, PoseType.NONE, true, 0.0f, false, null)
            }
        }
    }
}