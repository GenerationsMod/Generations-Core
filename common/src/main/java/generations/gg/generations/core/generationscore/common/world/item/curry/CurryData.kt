package generations.gg.generations.core.generationscore.common.world.item.curry

import com.cobblemon.mod.common.api.berry.Berry
import com.cobblemon.mod.common.api.berry.Flavor
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.util.Codecs
import generations.gg.generations.core.generationscore.common.util.StreamCodecs
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.asRegistryFriendly
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.optional
import generations.gg.generations.core.generationscore.common.world.item.berry.BerryType
import generations.gg.generations.core.generationscore.common.world.level.block.entities.CookingPotBlockEntity.Companion.getDominantFlavor
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.StringRepresentable
import java.util.*
import java.util.stream.Stream
import kotlin.jvm.optionals.getOrNull

data class CurryData(
    var flavor: Flavor? = null,
    var curryType: CurryType = CurryType.None,
    var experience: Int = 0,
    var healthPercentage: Double = 0.0,
    var canHealStatus: Boolean = false,
    var canRestorePP: Boolean = false,
    var friendship: Int = 0,
    var rating: CurryTasteRating = CurryTasteRating.Unknown) {

    constructor(
        flavor: Optional<Flavor>,
        curryType: CurryType,
        experience: Int,
        healthPercentage: Double,
        canHealStatus: Boolean,
        canRestorePP: Boolean,
        friendship: Int,
        rating: CurryTasteRating
    ): this(
        flavor.getOrNull(),
        curryType,
        experience,
        healthPercentage,
        canHealStatus,
        canRestorePP,
        friendship,
        rating
        )


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
                CurryType.CODEC.fieldOf("type").forGetter { it.curryType },
                Codec.INT.fieldOf("experience").forGetter { it.experience },
                Codec.DOUBLE.fieldOf("healthPercentage").forGetter { it.healthPercentage },
                Codec.BOOL.fieldOf("canHealStatus").forGetter { it.canHealStatus },
                Codec.BOOL.fieldOf("canRestorePP").forGetter { it.canRestorePP },
                Codec.INT.fieldOf("friendship").forGetter { it.friendship },
                CurryTasteRating.CODEC.fieldOf("rating").forGetter { it.rating },

                ).apply(it, ::CurryData)
        }

        val STREAM_CODEC = ByteBufCodecs.fromCodecTrusted(CODEC).asRegistryFriendly()

        @JvmOverloads
        fun create(
            mainIngredient: CurryType,
            berries: List<Berry>,
            rating: CurryTasteRating = CurryTasteRating.Unknown
        ): CurryData {
            var data = CurryData(rating = rating)

            var flavor = getDominantFlavor(berries)
            val friendship = berries.map(BerryType::fromCobblemonBerry)
                .filterNotNull()
                .map { it.rarity }.sum() + mainIngredient.rarity
            if (mainIngredient == CurryType.Gigantamax) flavor = null

            data.curryType = mainIngredient
            data.flavor = flavor
            data.friendship = friendship

            rating.configureData(data)

            return data
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
