package generations.gg.generations.core.generationscore.forge.datagen;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.forge.datagen.cobblemon.PokemonModelsProvider;
import net.minecraft.data.PackOutput;
import org.apache.commons.lang3.function.TriFunction;

import java.util.List;
import java.util.function.BiConsumer;

public class GenerationsPokemonModelsProvider extends PokemonModelsProvider {
    public GenerationsPokemonModelsProvider(PackOutput arg) {
        super(arg);
    }

    @Override
    public void build(BiConsumer<PokemonModelsProvider.Resolver, List<PokemonModelsProvider.PoserBuilder>> provider) {
        alcremie(); //Because special sweet tart. 0.o
        mega(provider, "absol");
        base(provider, "accelgor");
        mega(provider, "aerodactyl");
        mega(provider, "aggron");
        base(provider, "aipom");
        mega(provider, "alakazam");
        base(provider, "alomomola"); //TODO: Add swim
        mega(provider, "altaria");
        base(provider, "amaura");
        base(provider, "ambipom");
        base(provider, "amoonguss");
        mega(provider, "ampharos");
        base(provider, "anorith");
        gigantamax(provider, "appletun");
        base(provider, "applin");
        base(provider, "arbok");
        hisuian(provider, "arcanine");
        elemental(provider, "arceus");
        base(provider, "archen");
        base(provider, "archeops");
        base(provider, "arctovish");
        base(provider, "arctozolt");
        base(provider, "ariados");
        base(provider, "armaldo");
        base(provider, "aromatisse");
        base(provider, "aron");
        base(provider, "arrokuda", true);
        mega(provider, "audino");
        base(provider, "aurorus");
        hisuian(provider, "avalugg");
        base(provider, "axew");
        base(provider, "azelf");
        base(provider, "azumarill");
        base(provider, "azurill", true);
        base(provider, "yamper");
        base(provider, "yungoos");
        base(provider, "yveltal");
        base(provider, "xatu");
        bulkForms(provider, "xerneas", "active", "neutral");
        base(provider, "xurkitree");
        base(provider, "cacnea");
        base(provider, "cacturne");
        bulkForms(provider, "calyrex", "icerider", "shadowrider");
        base(provider, "camerupt");
        base(provider, "carbink");
        base(provider, "carkol");
        base(provider, "carnivine");
        base(provider, "carracosta");
        base(provider, "carvanha");
        base(provider, "cascoon");
        base(provider, "caterpie");
        base(provider, "celebi", true); //TODO Fix to do fly
        base(provider, "celesteela");
        base(provider, "centiskorch");
        base(provider, "chandelure");
        base(provider, "chansey");
        base(provider, "charjabug");
        base(provider, "chatot");
        bulkFormsModel(provider, "cherrim", "sunshine", "overcast");
        base(provider, "cherubi");
        base(provider, "chesnaught");
        base(provider, "chespin");
        base(provider, "chewtle");
        base(provider, "chikorita");
        base(provider, "chimchar");
        base(provider, "chimecho");
        base(provider, "chinchou");
        base(provider, "chingling");
        base(provider, "cinccino");
        base(provider, "cinderace");
        base(provider, "clamperl");
        base(provider, "clauncher");
        base(provider, "clawitzer");
        base(provider, "claydol");
        base(provider, "clefable");
        base(provider, "clefairy");
        base(provider, "cleffa");
        base(provider, "clobbopus");
        base(provider, "cloyster");
        base(provider, "coalossal");
        base(provider, "cobalion");
        base(provider, "cofagrigus");
        base(provider, "combee");
        base(provider, "combusken");
        base(provider, "comfey");
        base(provider, "conkeldurr");
        base(provider, "copperajah");
        base(provider, "corphish");
        base(provider, "corviknight");
        base(provider, "corvisquire");
        base(provider, "cosmoem");
        base(provider, "cosmog");
        base(provider, "cottonee");
        base(provider, "crabominable");
        base(provider, "crabrawler");
        base(provider, "cradily");
        base(provider, "cramorant");
        base(provider, "cranidos");
        base(provider, "crawdaunt");
        base(provider, "cresselia");
        base(provider, "croagunk");
        base(provider, "crobat");
        base(provider, "croconaw");
        base(provider, "crustle");
        base(provider, "cryogonal");
        base(provider, "cubchoo");
        base(provider, "cubone");
        base(provider, "cufant");
        base(provider, "cutiefly");
        base(provider, "cyndaquil");
        base(provider, "darkrai");
        base(provider, "dartrix");
        base(provider, "dedenne");
        base(provider, "deerling");
        base(provider, "deino");
        base(provider, "delcatty");
        base(provider, "delibird");
        base(provider, "delphox");
        bulkFormsModel(provider, "deoxys", "attack", "defense", "normal", "speed");
        base(provider, "dewgong");
        base(provider, "dewott");
        base(provider, "dhelmise");
        base(provider, "diancie");
        base(provider, "diggersby");
        base(provider, "ditto");
        base(provider, "dodrio");
        base(provider, "doduo");
        base(provider, "donphan");
        base(provider, "dottler");
        base(provider, "doublade");
        base(provider, "dracovish");
        base(provider, "dracozolt");
        base(provider, "dragalge");
        base(provider, "dragapult");
        base(provider, "dragonair");
        base(provider, "dragonite");
        base(provider, "drakloak");
        base(provider, "drampa");
        base(provider, "drapion");
        base(provider, "dratini");
        base(provider, "drednaw");
        base(provider, "dreepy");
        base(provider, "drifblim");
        base(provider, "drifloon");
        base(provider, "drilbur");
        base(provider, "drizzile");
        base(provider, "drowzee");
        base(provider, "druddigon");
        base(provider, "dubwool");
        base(provider, "ducklett");
        base(provider, "dunsparce");
        base(provider, "duraludon");
        base(provider, "durant");
        base(provider, "dusclops");
        base(provider, "dusknoir");
        base(provider, "duskull");
        base(provider, "dustox");
        base(provider, "dwebble");
    }

    private void alcremie() {
        //TODO
    }

    //TODO: Implment similar to bulkForm but its done with models instead.
    private void bulkFormsModel(BiConsumer<PokemonModelsProvider.Resolver, List<PokemonModelsProvider.PoserBuilder>> provider, String name, String... forms) {
        var resolver = simple(GenerationsCore.id(name), 1);
        for (String type : forms) {
            var id = GenerationsCore.id(name);
            resolver.variation(base().aspect(type).variant(type).poser(id).model(id));
            resolver.variation(shiny().aspect(type).variant(type).poser(id).model(id));
        }

        provider.accept(resolver, resolver(name, false));
    }

    private void elemental(BiConsumer<PokemonModelsProvider.Resolver, List<PokemonModelsProvider.PoserBuilder>> provider, String name) {
        bulkForms(provider, name, "normal",
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

    private void bulkForms(BiConsumer<PokemonModelsProvider.Resolver, List<PokemonModelsProvider.PoserBuilder>> provider, String name, String... forms) {
        var id = GenerationsCore.id(name);
        var resolver = simple(GenerationsCore.id(name), 1);
        for (String type : forms) {
            resolver.variation(base().aspect(type).variant(type).poser(id).model(id));
            resolver.variation(shiny().aspect(type).variant("shiny-" + type).poser(id).model(id));
        }

        provider.accept(resolver, resolver(name, false));
    }

    private void gigantamax(BiConsumer<PokemonModelsProvider.Resolver, List<PokemonModelsProvider.PoserBuilder>> provider, String name) {
        form(provider, "gigantamax", name, true);
    }

    private void hisuian(BiConsumer<PokemonModelsProvider.Resolver, List<PokemonModelsProvider.PoserBuilder>> provider, String name) {
        form(provider, "hisuian", name);
    }

    private void base(BiConsumer<PokemonModelsProvider.Resolver, List<PokemonModelsProvider.PoserBuilder>> provider, String name) {
        base(provider, name, false);
    }

    private void base(BiConsumer<PokemonModelsProvider.Resolver, List<PokemonModelsProvider.PoserBuilder>> provider, String name, boolean idleOnly) {
        provider.accept(base(name), resolver(name, idleOnly));
    }

    private void form(BiConsumer<PokemonModelsProvider.Resolver, List<PokemonModelsProvider.PoserBuilder>> provider, String form, String name) {
        form(provider, form, name, false);
    }

    private void form(BiConsumer<PokemonModelsProvider.Resolver, List<PokemonModelsProvider.PoserBuilder>> provider, String form, String name, boolean idleOnly) {
        provider.accept(form(form, name, idleOnly), formResolver(form, name, idleOnly));
    }

    private void mega(BiConsumer<PokemonModelsProvider.Resolver, List<PokemonModelsProvider.PoserBuilder>> provider, String name) {
        form(provider, "mega", name);
    }

    public static PokemonModelsProvider.Resolver base(String name) {
        return simple(GenerationsCore.id(name), 1)
                .variation(base().variant("normal"))
                .variation(shiny().variant("shiny"));
    }

    private PokemonModelsProvider.Resolver form(String form, String name, boolean idleOnly) {
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

    private List<PokemonModelsProvider.PoserBuilder> formResolver(String form, String name, boolean idleOnly) {
        return List.of(gensPoser(name, false),
                gensPoser(name + "-" + form, idleOnly));
    }

    private List<PokemonModelsProvider.PoserBuilder> resolver(String name, boolean idleOnly) {
        return List.of(gensPoser(name, idleOnly));
    }

    public PokemonModelsProvider.PoserBuilder gensPoser(String name, boolean idleOnly) {
        var poser = poser(GenerationsCore.id(name)).standing();
        if(!idleOnly) poser.walking();
        return poser;
    }
}
