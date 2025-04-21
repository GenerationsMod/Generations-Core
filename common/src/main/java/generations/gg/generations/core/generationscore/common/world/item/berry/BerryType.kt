package generations.gg.generations.core.generationscore.common.world.item.berry

import com.cobblemon.mod.common.api.berry.Berry
import generations.gg.generations.core.generationscore.common.world.item.curry.ICurryRarity
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.material.MapColor
import java.util.*
import java.util.stream.Stream

enum class BerryType(private val rarity: Int, val color: EnumBerryColor) : ICurryRarity {
    CHERI(1, EnumBerryColor.RED),
    CHESTO(1, EnumBerryColor.PURPLE),
    PECHA(1, EnumBerryColor.PINK),
    RAWST(1, EnumBerryColor.GREEN),
    ASPEAR(1, EnumBerryColor.YELLOW),
    LEPPA(1, EnumBerryColor.RED),
    ORAN(1, EnumBerryColor.BLUE),
    PERSIM(1, EnumBerryColor.PINK),
    LUM(2, EnumBerryColor.GREEN),
    SITRUS(2, EnumBerryColor.YELLOW),
    FIGY(2, EnumBerryColor.RED),
    WIKI(2, EnumBerryColor.PURPLE),
    MAGO(2, EnumBerryColor.PINK),
    AGUAV(2, EnumBerryColor.GREEN),
    IAPAPA(2, EnumBerryColor.YELLOW),
    BAZZ(4, EnumBerryColor.RED),
    BLUK(4, EnumBerryColor.PURPLE),
    NANAB(0, EnumBerryColor.PINK),
    WEPEAR(0, EnumBerryColor.GREEN),
    PINAP(0, EnumBerryColor.YELLOW),
    POMEG(0, EnumBerryColor.RED),
    KELPSY(0, EnumBerryColor.BLUE),
    QUALOT(0, EnumBerryColor.YELLOW),
    HONDEW(0, EnumBerryColor.GREEN),
    GREPA(0, EnumBerryColor.YELLOW),
    TAMATO(0, EnumBerryColor.RED),
    CORNN(0, EnumBerryColor.PURPLE),
    MAGOST(0, EnumBerryColor.PINK),
    RABUTA(0, EnumBerryColor.GREEN),
    NOMEL(0, EnumBerryColor.YELLOW),
    SPELON(0, EnumBerryColor.RED),
    PAMTRE(0, EnumBerryColor.PURPLE),
    WATMEL(0, EnumBerryColor.PINK),
    DURIN(0, EnumBerryColor.GREEN),
    BELUE(0, EnumBerryColor.PURPLE),
    OCCA(5, EnumBerryColor.RED),
    PASSHO(5, EnumBerryColor.BLUE),
    WACAN(5, EnumBerryColor.YELLOW),
    RINDO(5, EnumBerryColor.YELLOW),
    YACHE(5, EnumBerryColor.BLUE),
    CHOPLE(5, EnumBerryColor.RED),
    KEBIA(5, EnumBerryColor.GREEN),
    SHUCA(5, EnumBerryColor.YELLOW),
    COBA(5, EnumBerryColor.BLUE),
    PAYAPA(5, EnumBerryColor.PURPLE),
    TANGA(5, EnumBerryColor.GREEN),
    CHARTI(5, EnumBerryColor.YELLOW),
    KASIB(5, EnumBerryColor.PURPLE),
    HABAN(5, EnumBerryColor.RED),
    COLBUR(5, EnumBerryColor.PURPLE),
    BABIRI(5, EnumBerryColor.GREEN),
    CHILAN(5, EnumBerryColor.YELLOW),
    ROSELI(5, EnumBerryColor.PINK),
    LIECHI(10, EnumBerryColor.RED),
    GANLON(10, EnumBerryColor.PURPLE),
    SALAC(10, EnumBerryColor.GREEN),
    PETAYA(10, EnumBerryColor.PINK),
    APICOT(10, EnumBerryColor.BLUE),
    LANSAT(15, EnumBerryColor.RED),
    STARF(30, EnumBerryColor.GREEN),
    PUMKIN(0, EnumBerryColor.YELLOW),
    DRASH(0, EnumBerryColor.PINK),
    EGGANT(0, EnumBerryColor.BLUE),
    STRIB(0, EnumBerryColor.GREEN),
    NUTPEA(0, EnumBerryColor.YELLOW),
    GINEMA(0, EnumBerryColor.YELLOW),
    KUO(0, EnumBerryColor.GREEN),
    YAGO(0, EnumBerryColor.GREEN),
    TOUGA(0, EnumBerryColor.RED),
    NINIKU(0, EnumBerryColor.BLUE),
    TOPO(0, EnumBerryColor.PINK),
    ENIGMA(20, EnumBerryColor.PURPLE),
    MICLE(20, EnumBerryColor.GREEN),
    CUSTAP(20, EnumBerryColor.RED),
    JABOCA(20, EnumBerryColor.YELLOW),
    ROWAP(20, EnumBerryColor.BLUE),
    KEE(10, EnumBerryColor.PINK),
    MARANGA(10, EnumBerryColor.BLUE);

    private val id = ResourceLocation.fromNamespaceAndPath("cobbelmon", name.lowercase(Locale.getDefault()) + "_berry")

    override fun getRarity(): Int {
        return rarity
    }

    enum class EnumBerryColor(val mapColor: MapColor) {
        RED(MapColor.COLOR_RED),
        BLUE(MapColor.COLOR_BLUE),
        PURPLE(MapColor.COLOR_PURPLE),
        GREEN(MapColor.COLOR_GREEN),
        YELLOW(MapColor.COLOR_YELLOW),
        PINK(MapColor.COLOR_PINK)
    }

    companion object {
        fun fromCobblemonBerry(berry: Berry): BerryType? {
            return entries.filter { b -> b.id == berry.identifier }.firstOrNull()
        }
    }
}
