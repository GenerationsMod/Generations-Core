package generations.gg.generations.core.generationscore.config;

import java.util.Set;

import static com.cobblemon.mod.common.util.MiscUtilsKt.cobblemonResource;

public class LegendKeys {
    public static Key ARTICUNO = create("articuno");
    public static Key GALARIAN_ARTICUNO = create("articuno", Set.of("galarian"));
    public static Key ZAPDOS = create("zapdos");
    public static Key GALARIAN_ZAPDOS = create("zapdos", Set.of("galarian"));
    public static Key MOLTRES = create("moltres");
    public static Key GALARIAN_MOLTRES = create("moltres", Set.of("galarian"));

    public static Key MEWTWO = create("mewtwo");

    public static Key SUICUNE = create("suicune");
    public static Key ENTEI = create("entei");
    public static Key RAIKOU = create("raikou");

    public static Key LUGIA = create("lugia");
    public static Key HO_OH = create("hooh");
    public static Key CELEBI = create("celebi");
    public static Key REGIROCK = create("regirock");
    public static Key REGICE = create("regiice");
    public static Key REGISTEEL = create("registeel");
    public static Key REGIELEKI = create("regieleki");
    public static Key REGIDRAGO = create("regidrago");

    public static Key LATIAS = create("latias");
    public static Key LATIOS = create("latias");

    public static Key KYORGRE = create("kyorgre");
    public static Key GROUDON = create("groudon");
    public static Key RAYQUAZA = create("rayquaza");
    public static Key DEOXYS = create("deoxys");

    public static Key UXIE = create("uxie");
    public static Key AZELF = create("azelf");
    public static Key MESPRIT = create("mesprit");

    public static Key DIALGA = create("dialga");
    public static Key PALKIA = create("palkia");
    public static Key GIRATINA = create("giratina");
    public static Key HEATRAN = create("heatran");
    public static Key REGIGIGAS = create("regigigas");
    public static Key CRESSELIA = create("cresselia");
    public static Key DARKRAI = create("darkrai");
    public static Key PHIONE = create("phione");
    public static Key MANAPHY = create("manaphy");
    public static Key TORNADUS = create("tornadus");
    public static Key THUNDURUS = create("thundurus");
    public static Key LANDORUS = create("landorus");
    public static Key ENAMORUS = create("enamorus");
    public static Key RESHIRAM = create("reshiram");
    public static Key ZEKROM = create("zekrom");
    public static Key KYUREM = create("kyurem");
    public static Key MELOETTA = create("meloetta");
    public static Key HOOPA = create("hoopa");
    public static Key TYPE_NULL = create("typenull");
    public static Key TAPU_KOKO = create("tapukoko");
    public static Key TAPU_LELE = create("tapulele");
    public static Key TAPU_BULU = create("tapubulu");
    public static Key TAPU_FINI = create("tapufini");
    public static Key MAGEARNA = create("magearna");
    public static Key MELMETAL = create("melmetal");
    public static Key KUBFU = create("kubfu");
    public static Key GLASTRIER = create("glastrier");
    public static Key SPECTRIER = create("spectrier");

    private static Key create(String species) {
        return new Key(cobblemonResource(species));
    }

    private static Key create(String species, Set<String> aspects) {
        return new Key(cobblemonResource(species), aspects);
    }
}
