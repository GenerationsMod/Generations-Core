package generations.gg.generations.core.generationscore.common.config

import net.minecraft.resources.ResourceLocation

object LegendKeys {
    @JvmField
    val WALKING_WAKE: SpeciesKey = create("walkingwake")
    @JvmField
    var ARTICUNO: SpeciesKey = create("articuno")
    @JvmField
    var GALARIAN_ARTICUNO: SpeciesKey = create("articuno", setOf("galarian"))
    @JvmField
    var ZAPDOS: SpeciesKey = create("zapdos")
    @JvmField
    var GALARIAN_ZAPDOS: SpeciesKey = create("zapdos", setOf("galarian"))
    @JvmField
    var MOLTRES: SpeciesKey = create("moltres")
    @JvmField
    var GALARIAN_MOLTRES: SpeciesKey = create("moltres", setOf("galarian"))

    @JvmField
    var MEWTWO: SpeciesKey = create("mewtwo")

    @JvmField
    var SUICUNE: SpeciesKey = create("suicune")
    @JvmField
    var ENTEI: SpeciesKey = create("entei")
    @JvmField
    var RAIKOU: SpeciesKey = create("raikou")

    @JvmField
    var LUGIA: SpeciesKey = create("lugia")
    @JvmField
    var HO_OH: SpeciesKey = create("hooh")
    @JvmField
    var CELEBI: SpeciesKey = create("celebi")
    @JvmField
    var REGIROCK: SpeciesKey = create("regirock")
    @JvmField
    var REGICE: SpeciesKey = create("regice")
    @JvmField
    var REGISTEEL: SpeciesKey = create("registeel")
    @JvmField
    var REGIELEKI: SpeciesKey = create("regieleki")
    @JvmField
    var REGIDRAGO: SpeciesKey = create("regidrago")

    @JvmField
    var LATIAS: SpeciesKey = create("latias")
    @JvmField
    var LATIOS: SpeciesKey = create("latias")

    @JvmField
    var KYOGRE: SpeciesKey = create("kyogre")
    @JvmField
    var GROUDON: SpeciesKey = create("groudon")
    @JvmField
    var RAYQUAZA: SpeciesKey = create("rayquaza")
    @JvmField
    var DEOXYS: SpeciesKey = create("deoxys")

    @JvmField
    var UXIE: SpeciesKey = create("uxie")
    @JvmField
    var AZELF: SpeciesKey = create("azelf")
    @JvmField
    var MESPRIT: SpeciesKey = create("mesprit")

    @JvmField
    var DIALGA: SpeciesKey = create("dialga")
    @JvmField
    var PALKIA: SpeciesKey = create("palkia")
    @JvmField
    var GIRATINA: SpeciesKey = create("giratina")
    @JvmField
    var HEATRAN: SpeciesKey = create("heatran")
    @JvmField
    var REGIGIGAS: SpeciesKey = create("regigigas")
    @JvmField
    var CRESSELIA: SpeciesKey = create("cresselia")
    @JvmField
    var DARKRAI: SpeciesKey = create("darkrai")
    var PHIONE: SpeciesKey = create("phione")
    @JvmField
    var MANAPHY: SpeciesKey = create("manaphy")
    @JvmField
    var TORNADUS: SpeciesKey = create("tornadus")
    @JvmField
    var THUNDURUS: SpeciesKey = create("thundurus")
    @JvmField
    var LANDORUS: SpeciesKey = create("landorus")
    @JvmField
    var ENAMORUS: SpeciesKey = create("enamorus")
    var RESHIRAM: SpeciesKey = create("reshiram")
    var ZEKROM: SpeciesKey = create("zekrom")
    var KYUREM: SpeciesKey = create("kyurem")
    @JvmField
    var GENESECT: SpeciesKey = create("genesect")
    @JvmField
    var MELOETTA: SpeciesKey = create("meloetta")
    @JvmField
    var HOOPA: SpeciesKey = create("hoopa")
    @JvmField
    var TYPE_NULL: SpeciesKey = create("typenull")
    @JvmField
    var TAPU_KOKO: SpeciesKey = create("tapukoko")
    @JvmField
    var TAPU_LELE: SpeciesKey = create("tapulele")
    @JvmField
    var TAPU_BULU: SpeciesKey = create("tapubulu")
    @JvmField
    var TAPU_FINI: SpeciesKey = create("tapufini")
    @JvmField
    var MAGEARNA: SpeciesKey = create("magearna")
    @JvmField
    var MELMETAL: SpeciesKey = create("melmetal")
    var KUBFU: SpeciesKey = create("kubfu")
    @JvmField
    var GLASTRIER: SpeciesKey = create("glastrier")
    @JvmField
    var SPECTRIER: SpeciesKey = create("spectrier")
    var ZAMAZENTA: SpeciesKey = create("zamazenta")
    var ZACIAN: SpeciesKey = create("zacian")
    @JvmField
    var ZYGARDE: SpeciesKey = create("zygarde")

    private fun create(species: String): SpeciesKey {
        return SpeciesKey(ResourceLocation.fromNamespaceAndPath("cobblemon", species))
    }

    private fun create(species: String, aspects: Set<String>): SpeciesKey {
        return SpeciesKey(ResourceLocation.fromNamespaceAndPath("cobblemon", species), aspects)
    }
}
