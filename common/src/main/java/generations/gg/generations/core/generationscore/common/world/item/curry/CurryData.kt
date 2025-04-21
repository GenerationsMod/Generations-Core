package generations.gg.generations.core.generationscore.common.world.item.curry

import com.cobblemon.mod.common.api.berry.Berry
import com.cobblemon.mod.common.api.berry.Flavor
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.api.data.Codecs
import generations.gg.generations.core.generationscore.common.world.item.berry.BerryType
import generations.gg.generations.core.generationscore.common.world.level.block.entities.CookingPotBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.entities.CookingPotBlockEntity.Companion.getDominantFlavor
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.StringRepresentable
import java.util.*
import java.util.stream.Stream
import kotlin.jvm.optionals.getOrNull

class CurryData {
    var flavor: Flavor? = null
    var curryType: CurryType = CurryType.None
    var experience: Int = 0
    var healthPercentage: Double = 0.0
    var canHealStatus = false
    var canRestorePP = false
    var friendship: Int = 0
    var rating: CurryTasteRating

    constructor(
        flavor: Flavor?,
        curryType: CurryType,
        experience: Int,
        healthPercentage: Double,
        canHealStatus: Boolean,
        canRestorePP: Boolean,
        friendship: Int,
        rating: CurryTasteRating
    ) {
        this.flavor = flavor
        this.curryType = curryType
        this.rating = rating
        this.experience = experience
        this.healthPercentage = healthPercentage
        this.canHealStatus = canHealStatus
        this.canRestorePP = canRestorePP
        this.friendship = friendship
    }

    constructor(
        flavor: Optional<Flavor>,
        curryType: CurryType,
        experience: Int,
        healthPercentage: Double,
        canHealStatus: Boolean,
        canRestorePP: Boolean,
        friendship: Int,
        rating: CurryTasteRating
    ) {
        this.flavor = flavor.getOrNull()
        this.curryType = curryType
        this.rating = rating
        this.experience = experience
        this.healthPercentage = healthPercentage
        this.canHealStatus = canHealStatus
        this.canRestorePP = canRestorePP
        this.friendship = friendship
    }

    @JvmOverloads
    constructor(rating: CurryTasteRating = CurryTasteRating.Unknown) {
        this.rating = rating
        rating.configureData(this)
    }

    @JvmOverloads
    constructor(
        mainIngredient: CurryType,
        berries: List<Berry>,
        rating: CurryTasteRating = CurryTasteRating.Unknown
    ) : this(rating) {
        var flavor = getDominantFlavor(berries)
        val friendship = berries.map(BerryType::fromCobblemonBerry)
            .filterNotNull()
            .map { it.rarity }.sum() + curryType.rarity
        if (mainIngredient == CurryType.Gigantamax) flavor = null

        this.curryType = mainIngredient
        this.flavor = flavor
        this.friendship = friendship
    }

    fun canRestorePP(): Boolean {
        return canRestorePP
    }

    fun canHealStatus(): Boolean {
        return canHealStatus
    }

    fun setFlavor(flavor: Flavor?): CurryData {
        this.flavor = flavor
        return this
    }

    fun setCurryType(curryType: CurryType): CurryData {
        this.curryType = curryType
        return this
    }

    fun setRating(rating: CurryTasteRating): CurryData {
        this.rating = rating
        return this
    }

    fun setExperience(experience: Int): CurryData {
        this.experience = experience
        return this
    }

    fun setHealthPercentage(healthPercentage: Double): CurryData {
        this.healthPercentage = healthPercentage
        return this
    }

    fun setCanHealStatus(canHealStatus: Boolean): CurryData {
        this.canHealStatus = canHealStatus
        return this
    }

    fun setCanRestorePP(canRestorePP: Boolean): CurryData {
        this.canRestorePP = canRestorePP
        return this
    }

    fun setFriendship(friendship: Int): CurryData {
        this.friendship = friendship
        return this
    }

    val resourceLocation: ResourceLocation
        get() = curryType.resourceLocation

    fun toNbt(): Tag {
        val nbt = CompoundTag()
        nbt.putInt("flavor", flavor?.ordinal ?: -1)
        nbt.putInt("type", curryType.ordinal)
        nbt.putInt("rating", rating.ordinal)
        nbt.putInt("experience", experience)
        nbt.putDouble("healthPercentage", healthPercentage)
        nbt.putBoolean("canHealStatus", canHealStatus)
        nbt.putBoolean("canRestorePP", canRestorePP)
        nbt.putInt("friendship", friendship)

        return nbt
    }

    companion object {
        val CODEC: Codec<CurryData> = RecordCodecBuilder.create {
            it.group(
                Codecs.enumCodec(Flavor::class.java).optionalFieldOf("flavor").forGetter { Optional.ofNullable(it.flavor) },
                StringRepresentable.fromEnum({ CurryType.entries.toTypedArray() }).fieldOf("type").forGetter { it.curryType },
                Codec.INT.fieldOf("experience").forGetter { it.experience },
                Codec.DOUBLE.fieldOf("healthPercentage").forGetter { it.healthPercentage },
                Codec.BOOL.fieldOf("canHealStatus").forGetter { it.canHealStatus },
                Codec.BOOL.fieldOf("canRestorePP").forGetter { it.canRestorePP },
                Codec.INT.fieldOf("friendship").forGetter { it.friendship },
                StringRepresentable.fromEnum { CurryTasteRating.entries.toTypedArray() }.fieldOf("rating").forGetter { it.rating },

                ).apply(it, ::CurryData)
        }

        fun fromNbt(nbt: CompoundTag): CurryData {
            val curry = CurryData()
            if (nbt.isEmpty) {
                return curry
            }

            val flavorId = nbt.getInt("flavor")
            val flavor = if (flavorId == -1) null else Flavor.entries[flavorId]
            val type = CurryType.getCurryTypeFromIndex(nbt.getInt("type"))
            val experience = nbt.getInt("experience")
            val healthPercentage = nbt.getDouble("healthPercentage")
            val canHealStatus = nbt.getBoolean("canHealStatus")
            val canRestorePP = nbt.getBoolean("canRestorePP")
            val friendship = nbt.getInt("friendship")
            val rating = CurryTasteRating.fromId(nbt.getInt("rating"))

            return CurryData(
                flavor,
                type,
                experience,
                healthPercentage,
                canHealStatus,
                canRestorePP,
                friendship,
                rating
            )
        }
    }
}
