package generations.gg.generations.core.generationscore.config;

import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public class LegendKeys {
    public static SpeciesKey ARTICUNO = create("articuno");
    public static SpeciesKey GALARIAN_ARTICUNO = create("articuno", Set.of("galarian"));
    public static SpeciesKey ZAPDOS = create("zapdos");
    public static SpeciesKey GALARIAN_ZAPDOS = create("zapdos", Set.of("galarian"));
    public static SpeciesKey MOLTRES = create("moltres");
    public static SpeciesKey GALARIAN_MOLTRES = create("moltres", Set.of("galarian"));

    public static SpeciesKey MEWTWO = create("mewtwo");

    public static SpeciesKey SUICUNE = create("suicune");
    public static SpeciesKey ENTEI = create("entei");
    public static SpeciesKey RAIKOU = create("raikou");

    public static SpeciesKey LUGIA = create("lugia");
    public static SpeciesKey HO_OH = create("hooh");
    public static SpeciesKey CELEBI = create("celebi");
    public static SpeciesKey REGIROCK = create("regirock");
    public static SpeciesKey REGICE = create("regice");
    public static SpeciesKey REGISTEEL = create("registeel");
    public static SpeciesKey REGIELEKI = create("regieleki");
    public static SpeciesKey REGIDRAGO = create("regidrago");

    public static SpeciesKey LATIAS = create("latias");
    public static SpeciesKey LATIOS = create("latias");

    public static SpeciesKey KYORGRE = create("kyorgre");
    public static SpeciesKey GROUDON = create("groudon");
    public static SpeciesKey RAYQUAZA = create("rayquaza");
    public static SpeciesKey DEOXYS = create("deoxys");

    public static SpeciesKey UXIE = create("uxie");
    public static SpeciesKey AZELF = create("azelf");
    public static SpeciesKey MESPRIT = create("mesprit");

    public static SpeciesKey DIALGA = create("dialga");
    public static SpeciesKey PALKIA = create("palkia");
    public static SpeciesKey GIRATINA = create("giratina");
    public static SpeciesKey HEATRAN = create("heatran");
    public static SpeciesKey REGIGIGAS = create("regigigas");
    public static SpeciesKey CRESSELIA = create("cresselia");
    public static SpeciesKey DARKRAI = create("darkrai");
    public static SpeciesKey PHIONE = create("phione");
    public static SpeciesKey MANAPHY = create("manaphy");
    public static SpeciesKey TORNADUS = create("tornadus");
    public static SpeciesKey THUNDURUS = create("thundurus");
    public static SpeciesKey LANDORUS = create("landorus");
    public static SpeciesKey ENAMORUS = create("enamorus");
    public static SpeciesKey RESHIRAM = create("reshiram");
    public static SpeciesKey ZEKROM = create("zekrom");
    public static SpeciesKey KYUREM = create("kyurem");
    public static SpeciesKey MELOETTA = create("meloetta");
    public static SpeciesKey HOOPA = create("hoopa");
    public static SpeciesKey TYPE_NULL = create("typenull");
    public static SpeciesKey TAPU_KOKO = create("tapukoko");
    public static SpeciesKey TAPU_LELE = create("tapulele");
    public static SpeciesKey TAPU_BULU = create("tapubulu");
    public static SpeciesKey TAPU_FINI = create("tapufini");
    public static SpeciesKey MAGEARNA = create("magearna");
    public static SpeciesKey MELMETAL = create("melmetal");
    public static SpeciesKey KUBFU = create("kubfu");
    public static SpeciesKey GLASTRIER = create("glastrier");
    public static SpeciesKey SPECTRIER = create("spectrier");
    public static SpeciesKey ZAMAZENTA = create("zamazenta");
    public static SpeciesKey ZACIAN = create("zacian");

    private static SpeciesKey create(String species) {
        return new SpeciesKey(new ResourceLocation("cobblemon", species));
    }

    private static SpeciesKey create(String species, Set<String> aspects) {
        return new SpeciesKey(new ResourceLocation("cobblemon", species), aspects);
    }
}
