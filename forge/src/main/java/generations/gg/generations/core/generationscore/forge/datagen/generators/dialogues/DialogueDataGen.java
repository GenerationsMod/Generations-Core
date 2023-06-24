package generations.gg.generations.core.generationscore.forge.datagen.generators.dialogues;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.brigadier.RedirectModifier;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import generations.gg.generations.core.generationscore.world.dialogue.BuiltinDialogues;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueGraph;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.SayNode;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class DialogueDataGen implements DataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

    private final PackOutput.PathProvider pathResolver;
    private static Function<DialogueGraph, Optional<JsonElement>> function = JsonOps.INSTANCE.withEncoder(DialogueGraph.CODEC).andThen(DataResult::result);

    public DialogueDataGen(PackOutput output) {
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
        convertGraph(consumer, BuiltinDialogues.TAPU_SPAWN, new DialogueGraph(new SayNode(List.of("Rawr!"), null)));
    }

    private void convertGraph(BiConsumer<ResourceLocation, JsonObject> consumer, ResourceLocation id, DialogueGraph graph) {
        var optional = function.apply(graph);

        optional.ifPresentOrElse(a -> consumer.accept(id, a.getAsJsonObject()), () -> LOGGER.warn("Error: Couldn't create dialog " + id));
    }

    @Override
    public String getName() {
        return "Generations Dialogues";
    }
}
