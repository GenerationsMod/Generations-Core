package generations.gg.generations.core.generationscore.common.world.item.curry

import net.minecraft.ChatFormatting
import net.minecraft.util.StringRepresentable
import java.util.function.Consumer
import java.util.stream.Stream

enum class CurryTasteRating(
    exp: Int,
    healthPercent: Double,
    healStatus: Boolean,
    restorePP: Boolean,
    private val textFormatting: ChatFormatting
) :
    StringRepresentable {
    Unknown(0, 0, false, false, ChatFormatting.GRAY),
    Koffing(25, 0.25, false, false, ChatFormatting.DARK_RED),
    Wobbuffet(200, 0.5, false, false, ChatFormatting.RED),
    Milcery(1000, 1.0, true, false, ChatFormatting.WHITE),
    Copperajah(5000, 1.0, true, true, ChatFormatting.DARK_GREEN),
    Charizard(10000, 1.0, true, true, ChatFormatting.GREEN);

    private val consumer =
        Consumer { curryData: CurryData ->
            curryData.setExperience(exp).setHealthPercentage(healthPercent).setCanHealStatus(healStatus)
                .setCanRestorePP(restorePP)
        }

    fun configureData(data: CurryData) {
        consumer.accept(data)
    }

    override fun getSerializedName(): String {
        return name.lowercase()
    }

    companion object {
        fun fromId(id: Int): CurryTasteRating {
            return try {
                entries[id]
            } catch (e: Exception) {
                Koffing
            }
        }

        fun get(name: String): CurryTasteRating {
            return Stream.of(*entries.toTypedArray()).filter { a: CurryTasteRating -> a.serializedName == name }
                .findAny().orElse(
                    Unknown
                )
        }
    }
}
