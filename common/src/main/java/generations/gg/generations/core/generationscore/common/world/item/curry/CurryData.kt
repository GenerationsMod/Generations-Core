package generations.gg.generations.core.generationscore.common.world.item.curry

import com.cobblemon.mod.common.api.berry.Berry
import com.cobblemon.mod.common.api.berry.Flavor
import generations.gg.generations.core.generationscore.common.world.item.berry.BerryType
import generations.gg.generations.core.generationscore.common.world.level.block.entities.CookingPotBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.entities.CookingPotBlockEntity.getDominantFlavor
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag
import net.minecraft.resources.ResourceLocation
import java.util.*
import java.util.stream.Stream

class CurryData {
    var flavor: Flavor? = null
    var curryType: CurryType = CurryType.None
    var experience: Int = 0
    var healthPercentage: Double = 0.0
    var canHealStatus = false
    var canRestorePP = false
    var friendship: Int = 0
    var rating: CurryTasteRating

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
        val friendship = Stream.concat(berries.stream().map { berry: Berry? -> BerryType.fromCobblemonBerry(berry) }
            .filter { obj: BerryType? -> Objects.nonNull(obj) }, Stream.of(mainIngredient))
            .filter { obj: Enum<out Enum<*>?>? -> Objects.nonNull(obj) }
            .map { obj: Enum<out Enum<*>?>? -> ICurryRarity::class.java.cast(obj) }
            .mapToInt { obj: ICurryRarity -> obj.rarity }.sum()
        if (mainIngredient == CurryType.Gigantamax) flavor = null

        this.curryType = mainIngredient
        this.flavor = flavor
        this.friendship = friendship
    }

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
        this.experience = experience
        this.healthPercentage = healthPercentage
        this.canHealStatus = canHealStatus
        this.canRestorePP = canRestorePP
        this.friendship = friendship
        this.rating = rating
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
        nbt.putInt("flavor", if (flavor == null) -1 else flavor!!.ordinal)
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
