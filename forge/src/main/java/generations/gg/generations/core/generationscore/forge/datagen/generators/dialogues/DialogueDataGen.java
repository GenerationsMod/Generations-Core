package generations.gg.generations.core.generationscore.forge.datagen.generators.dialogues;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.dialogue.BuiltinDialogues;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueGraph;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNodeTypes;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.ChooseNode;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.SpawnPokemonNode;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.BlockLocationLogic;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.BlockYawLogic;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.LocationLogicTypes;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.YawLogicTypes;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsShrines;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class DialogueDataGen implements DataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

    private final PackOutput.PathProvider pathResolver;
    private static Function<DialogueGraph, Optional<JsonElement>> function = null;//JsonOps.INSTANCE.withEncoder(DialogueGraph.CODEC).andThen(DataResult::result);

    public DialogueDataGen(PackOutput output) {
        AbstractNodeTypes.init();
        LocationLogicTypes.init();
        YawLogicTypes.init();
        pathResolver = output.createPathProvider(PackOutput.Target.DATA_PACK, "dialogues");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput arg) {
        Set<ResourceLocation> generatedDialogues = Sets.newHashSet();
        List<CompletableFuture<?>> list = new ArrayList<>();

        BiConsumer<ResourceLocation, JsonObject> consumer = (id, json) -> {
            Path outputPath = pathResolver.json(id);
            list.add(DataProvider.saveStable(arg, json, outputPath));
        };

        generateDialogues(consumer);

        return CompletableFuture.allOf(list.toArray(CompletableFuture[]::new));
    }

    protected void generateDialogues(BiConsumer<ResourceLocation, JsonObject> consumer) {
        convertGraph(consumer, BuiltinDialogues.TAPU_SPAWN, new DialogueGraph(new ChooseNode("Choose which Tapu to Spawn:",
                Map.of("Tapu Koko", new SpawnPokemonNode(
                        GenerationsUtils.parseProperties("species=tapukoko level=70"),
                        20,
                        new BlockLocationLogic(GenerationsShrines.TAPU_SHRINE.getKey()),
                        new BlockYawLogic(GenerationsShrines.TAPU_SHRINE.getKey())),
                        "Tapu Lele", new SpawnPokemonNode(
                                GenerationsUtils.parseProperties("species=tapulele level=70"),
                                20,
                                new BlockLocationLogic(GenerationsShrines.TAPU_SHRINE.getKey()),
                                new BlockYawLogic(GenerationsShrines.TAPU_SHRINE.getKey())),
                        "Tapu Bulu", new SpawnPokemonNode(
                                GenerationsUtils.parseProperties("species=tapubulu level=70"),
                                20,
                                new BlockLocationLogic(GenerationsShrines.TAPU_SHRINE.getKey()),
                                new BlockYawLogic(GenerationsShrines.TAPU_SHRINE.getKey())),
                        "Tapu Fini", new SpawnPokemonNode(
                                GenerationsUtils.parseProperties("species=tapufini level=70"),
                                20,
                                new BlockLocationLogic(GenerationsShrines.TAPU_SHRINE.getKey()),
                                new BlockYawLogic(GenerationsShrines.TAPU_SHRINE.getKey()))))
                ));
        convertGraph(consumer, BuiltinDialogues.THERIAN_SPAWN, new DialogueGraph(new ChooseNode("Choose which Tapu to Spawn:",
                Map.of("Tornadus", new SpawnPokemonNode(
                                GenerationsUtils.parseProperties("species=tornadus level=70"),
                                20,
                                new BlockLocationLogic(GenerationsShrines.ABUNDANT_SHRINE.getKey()),
                                new BlockYawLogic(GenerationsShrines.ABUNDANT_SHRINE.getKey())),
                        "Thundurus", new SpawnPokemonNode(
                                GenerationsUtils.parseProperties("species=thundurus level=70"),
                                20,
                                new BlockLocationLogic(GenerationsShrines.ABUNDANT_SHRINE.getKey()),
                                new BlockYawLogic(GenerationsShrines.ABUNDANT_SHRINE.getKey())),
                        "Landorus", new SpawnPokemonNode(
                                GenerationsUtils.parseProperties("species=landorus level=70"),
                                20,
                                new BlockLocationLogic(GenerationsShrines.ABUNDANT_SHRINE.getKey()),
                                new BlockYawLogic(GenerationsShrines.ABUNDANT_SHRINE.getKey())),
                        "Enamorus", new SpawnPokemonNode(
                                GenerationsUtils.parseProperties("species=enamorus level=70"),
                                20,
                                new BlockLocationLogic(GenerationsShrines.ABUNDANT_SHRINE.getKey()),
                                new BlockYawLogic(GenerationsShrines.ABUNDANT_SHRINE.getKey()))))
        ));
    }

    private void convertGraph(BiConsumer<ResourceLocation, JsonObject> consumer, ResourceLocation id, DialogueGraph graph) {
        consumer.accept(id, graph.toJson());
    }

    @Override
    public String getName() {
        return "Generations Dialogues";
    }
}
