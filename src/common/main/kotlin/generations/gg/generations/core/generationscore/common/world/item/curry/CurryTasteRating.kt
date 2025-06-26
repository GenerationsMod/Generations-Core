package generations.gg.generations.core.generationscore.common.world.item.curry

import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.asRegistryFriendly
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryType.Companion
import net.minecraft.ChatFormatting
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.util.StringRepresentable
import java.util.function.Consumer
import java.util.stream.Stream
import kotlin.math.exp

enum class CurryTasteRating(
    private val exp: Int,
    private val healthPercent: Double,
    private val healStatus: Boolean,
    private val restorePP: Boolean,
    private val textFormatting: ChatFormatting,

) : StringRepresentable {
    Unknown(0, 0.0, false, false, ChatFormatting.GRAY),
    Koffing(25, 0.25, false, false, ChatFormatting.DARK_RED),
    Wobbuffet(200, 0.5, false, false, ChatFormatting.RED),
    Milcery(1000, 1.0, true, false, ChatFormatting.WHITE),
    Copperajah(5000, 1.0, true, true, ChatFormatting.DARK_GREEN),
    Charizard(10000, 1.0, true, true, ChatFormatting.GREEN);

    fun configureData(data: CurryData) {
        data.experience = exp
        data.healthPercentage = healthPercent
        data.canHealStatus = healStatus
        data.canRestorePP = restorePP
    }

    override fun getSerializedName(): String {
        return name.lowercase()
    }

    companion object {
        val STREAM_CODEC = ByteBufCodecs.STRING_UTF8.map(CurryTasteRating::get, CurryTasteRating::getSerializedName).asRegistryFriendly()
        val CODEC = StringRepresentable.fromEnum { CurryTasteRating.entries.toTypedArray() }

        fun fromId(id: Int): CurryTasteRating = id.tryCatchWithAlt({ entries[it] }, Koffing)

        fun get(name: String): CurryTasteRating = entries.firstOrNull { rating -> rating.serializedName == name } ?: Unknown
    }

}

fun <T, V> T.tryCatchWithAlt(block: (T) -> V, defaultValue: V): V = try {
    block(this)
} catch (e: Exception) {
    defaultValue
}