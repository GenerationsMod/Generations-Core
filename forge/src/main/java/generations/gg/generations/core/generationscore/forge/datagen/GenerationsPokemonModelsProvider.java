package generations.gg.generations.core.generationscore.forge.datagen;

import com.mojang.datafixers.util.Pair;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.forge.datagen.cobblemon.PokemonModelsProvider;
import net.minecraft.data.PackOutput;
import org.apache.commons.lang3.function.TriFunction;

import java.util.List;

public class GenerationsPokemonModelsProvider extends PokemonModelsProvider {
    public GenerationsPokemonModelsProvider(PackOutput arg) {
        super(arg);
    }

    @Override
    public void build() {
        alcremie(); //Because special sweet tart. 0.o
        mega("absol");
        base("accelgor");
        mega("aerodactyl");
        mega("aggron");
        base("aipom");
        mega("alakazam");
        base("alomomola"); //TODO: Add swim
        mega("altaria");
        base("amaura");
        base("ambipom");
        base("amoonguss");
        mega("ampharos");
        base("anorith");
        gigantamax("appletun");
        base("applin");
        base("arbok");
        hisuian("arcanine");
        elemental("arceus");
        base("archen");
        base("archeops");
        base("arctovish");
        base("arctozolt");
        base("ariados");
        base("armaldo");
        base("aromatisse");
        base("aron");
        baseOnlyIdle("arrokuda");
        mega("audino");
        base("aurorus");
        hisuian("avalugg");
        base("axew");
        base("azelf");
        base("azumarill");
        baseOnlyIdle("azurill");
        base("yamper");
        base("yungoos");
        base("yveltal");
        base("xatu");
        bulkForms("xerneas", "active", "neutral");
        base("xurkitree");
        base("cacnea");
        base("cacturne");
        bulkForms("calyrex", "icerider", "shadowrider");
        base("camerupt");
        base("carbink");
        base("carkol");
        base("carnivine");
        base("carracosta");
        base("carvanha");
        base("cascoon");
        base("caterpie");
        base("celebi", true); //TODO Fix to do fly
        base("celesteela");
        base("centiskorch");
        base("chandelure");
        base("chansey");
        base("charjabug");
        base("chatot");
        bulkFormsModel("cherrim", "sunshine", "overcast");
        base("cherubi");
        base("chesnaught");
        base("chespin");
        base("chewtle");
        base("chikorita");
        base("chimchar");
        base("chimecho");
        base("chinchou");
        base("chingling");
        base("cinccino");
        base("cinderace");
        base("clamperl");
        base("clauncher");
        base("clawitzer");
        base("claydol");
        base("clefable");
        base("clefairy");
        base("cleffa");
        base("clobbopus");
        base("cloyster");
        base("coalossal");
        base("cobalion");
        base("cofagrigus");
        base("combee");
        base("combusken");
        base("comfey");
        base("conkeldurr");
        base("copperajah");
        base("corphish");
        base("corviknight");
        base("corvisquire");
        base("cosmoem");
        base("cosmog");
        base("cottonee");
        base("crabominable");
        base("crabrawler");
        base("cradily");
        base("cramorant");
        base("cranidos");
        base("crawdaunt");
        base("cresselia");
        base("croagunk");
        base("crobat");
        base("croconaw");
        base("crustle");
        base("cryogonal");
        base("cubchoo");
        base("cubone");
        base("cufant");
        base("cutiefly");
        base("cyndaquil");
        base("darkrai");
        base("dartrix");
        base("dedenne");
        base("deerling");
        base("deino");
        base("delcatty");
        base("delibird");
        base("delphox");
        bulkFormsModel("deoxys", "attack", "defense", "normal", "speed");
        base("dewgong");
        base("dewott");
        base("dhelmise");
        base("diancie");
        base("diggersby");
        base("ditto");
        base("dodrio");
        base("doduo");
        base("donphan");
        base("dottler");
        base("doublade");
        base("dracovish");
        base("dracozolt");
        base("dragalge");
        base("dragapult");
        base("dragonair");
        base("dragonite");
        base("drakloak");
        base("drampa");
        base("drapion");
        base("dratini");
        base("drednaw");
        base("dreepy");
        base("drifblim");
        base("drifloon");
        base("drilbur");
        base("drizzile");
        base("drowzee");
        base("druddigon");
        base("dubwool");
        base("ducklett");
        base("dunsparce");
        base("duraludon");
        base("durant");
        base("dusclops");
        base("dusknoir");
        base("duskull");
        base("dustox");
        base("dwebble");
        bulkFormsModel("dialga", "altered", "origin");
        darmanitan();
        form("galarian", "darumaka");
        hisuian("decidueye");
        alolan("diglett");
        alolan("dugtrio");
        base("eelektrik");
        base("eelektross");
        base("eevee");
        bulkFormsModel("eiscue", "ice", "noice");
        base("ekans");
        base("eldegoss");
        base("electabuzz");
        base("electivire");
        base("electrike");
        hisuian("electrode");
        base("elekid");
        base("elgyem");
        base("emboar");
        base("emolga");
        base("empoleon");
        form("therian", "enamorus", false);
        base("entei");
        base("escavalier");
        base("espeon");
        base("espurr");
        base("excadrill");
        base("exeggcute");
        alolan("exeggutor");
        base("exploud");
        base("falinks");
        form("galarian", "farfetchd");
        base("fearow");
        base("feebas");
        base("fennekin");
        base("feraligatr");
        base("flareon");
        base("fletchinder");
        base("fletchling");
        bulkForms("floatzel", "male", "female");
        bulkForms("floette", "blue", "orange", "red", "white", "yellow"); //TODO: Add Azure;
        bulkForms("florges", "blue", "orange", "red", "white", "yellow");
        base("flygon");
        base("fomantis");
        base("foongus");
        base("forretress");
        base("fraxure");
        bulkFormsModel("frillish", "female", "male");
        base("froakie");
        base("frogadier");
        base("froslass");
        base("frosmoth");
        bulkFormsModel("furfrou", "dandy", "debutante", "diamond", "heart", "kabuki", "lareine", "matron", "natural", "pharaoh", "star");
        base("furret");
        base("gabite");
        base("gallade");
        base("galvantula");
        base("garbodor");
        base("garchomp");
        base("gardevoir");
        base("gastly");
        bulkFormsModel("gastrodon", "east", "west");
        base("genesect");
        base("gengar");
        alolan("geodude");
        base("gible");
        base("girafarig");
        bulkFormsModel("giratina", "altered", "origin");
        base("glaceon");
        base("glalie");
        base("glameow");
        base("glastrier");
        base("gligar");
        base("gliscor");
        base("gloom");
        base("gogoat");
        base("golbat");
        base("goldeen");
        base("golduck");
        alolan("golem");
        base("golett");
        base("golisopod");
        base("golurk");
        hisuian("goodra");
        base("goomy");
        base("gorebyss");
        base("gossifleur");
        base("gothita");
        base("gothitelle");
        base("gothorita");
        base("gourgeist");
        base("granbull");
        base("grapploct");
        alolan("graveler");
        base("greedent");
        base("greninja");
        alolan("grimer");
        base("grimmsnarl");
        base("grookey");
        base("grotle");
        base("groudon");
        base("grovyle");
        hisuian("growlithe");
        base("grubbin");
        base("grumpig");
        base("gulpin");
        base("gumshoos");
        base("gurdurr");
        base("guzzlord");
        base("gyarados");
    }

    private void darmanitan() {
        //TODO
    }

    private void alolan(String name) {
        form("alolan", name, false);
    }

    private void alcremie() {
        //TODO
    }

    //TODO: Implment similar to bulkForm but its done with models instead.
    private void bulkFormsModel(String name, String... forms) {
        var resolver = simple(GenerationsCore.id(name), 1);
        for (String type : forms) {
            var id = GenerationsCore.id(name);
            resolver.variation(base().aspect(type).variant(type).poser(id).model(id));
            resolver.variation(shiny().aspect(type).variant(type).poser(id).model(id));
        }

//        pairs.add(new Pair<>(resolver, poserBuilder(name, false)));
    }

    private void elemental(String name) {
        bulkForms(name, "normal",
                "fire",
                "water",
                "grass",
                "electric",
                "ice",
                "fighting",
                "poison",
                "ground",
                "flying",
                "psychic",
                "bug",
                "rock",
                "ghost",
                "dragon",
                "dark",
                "steel",
                "fairy");
    }

    private void bulkForms(String name, String... forms) {
        var id = GenerationsCore.id(name);
        var resolver = simple(GenerationsCore.id(name), 1);
        for (String type : forms) {
            resolver.variation(base().aspect(type).variant(type).poser(id).model(id));
            resolver.variation(shiny().aspect(type).variant("shiny-" + type).poser(id).model(id));
        }

//        pairs.add(new Pair<>(resolver, poserBuilder(name, false)));
    }

    private void gigantamax(String name) {
        form("gigantamax", name, true);
    }

    private void hisuian(String name) {
        form("hisuian", name);
    }

    private void base(String name) {
        base(name, false);
    }

    private void baseOnlyIdle(String name) {
        base(name, true);
    }

    private void base(String name, boolean idleOnly) {
//        pairs.add(new Pair<>(baseResolver(name), poserBuilder(name, idleOnly)));
    }

    private void form(String form, String name) {
        form(form, name, false);
    }

    private void form(String form, String name, boolean idleOnly) {
//        pairs.add(new Pair<>(formResolver(form, name, idleOnly), formPoserBuilder(form, name, idleOnly)));
    }

    private void mega(String name) {
        form("mega", name);
    }

    public static PokemonModelsProvider.Resolver baseResolver(String name) {
        return simple(GenerationsCore.id(name), 1)
                .variation(base().variant("normal"))
                .variation(shiny().variant("shiny"));
    }

    private PokemonModelsProvider.Resolver formResolver(String form, String name, boolean idleOnly) {
        TriFunction<String, Boolean, Boolean, PokemonModelsProvider.Resolver.ModelAssetVariationBuilder> function = (name1, shiny, mega) -> {

            var builder = shiny ? shiny() : base();
            var id = GenerationsCore.id(name1 + (mega ? "-" + form : ""));

            if(!shiny) {
                builder.poser(id).model(id);
            }

            if(mega) {
                builder.aspect(form);
            }

            return builder.variant(shiny ? "shiny" : "normal");
        };

        return simple(GenerationsCore.id(name), 1)
                .variation(function.apply(name, false, false))
                .variation(function.apply(name, true, false))
                .variation(function.apply(name, false, true))
                .variation(function.apply(name, true, true));
    }

//    private List<PokemonModelsProvider.PoserBuilder> formPoserBuilder(String form, String name, boolean idleOnly) {
//        return List.of(gensPoser(name, false),
//                gensPoser(name + "-" + form, idleOnly));
//    }

//    private List<PokemonModelsProvider.PoserBuilder> poserBuilder(String name, boolean idleOnly) {
//        return List.of(gensPoser(name, idleOnly));
//    }

//    public PokemonModelsProvider.PoserBuilder gensPoser(String name, boolean idleOnly) {
//        var poser = poser(GenerationsCore.id(name)).standing();
//        if(!idleOnly) poser.walking();
//        return poser;
//    }
}
