package generations.gg.generations.core.generationscore.world.dialogue

import com.cobblemon.mod.common.api.data.DataRegistry;
import com.cobblemon.mod.common.api.data.JsonDataRegistry;
import com.cobblemon.mod.common.api.reactive.SimpleObservable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.dialogue.network.DialogueGraphRegistrySyncPacket;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNode;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNodeAdapter;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.LocationLogic;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.LocationLogicAdpter;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.YawLogic;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.YawLogicAdapter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Dialogues implements JsonDataRegistry<DialogueGraph> {
    private static final ResourceLocation id = GenerationsCore.id("dialogues");

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(AbstractNode.class, new AbstractNodeAdapter())
            .registerTypeAdapter(LocationLogic.class, new LocationLogicAdpter())
            .registerTypeAdapter(YawLogic.class, new YawLogicAdapter())
            .serializeNulls()
            .create();

    private static final TypeToken<DialogueGraph> typeToken = TypeToken.get(DialogueGraph.class);

    private SimpleObservable<Dialogues> observable = new SimpleObservable<Dialogues>();

    @NotNull
    @Override
    public ResourceLocation getId() {
        return id;
    }

    @NotNull
    @Override
    public Gson getGson() {
        return gson;
    }

    @NotNull
    @Override
    public PackType getType() {
        return PackType.SERVER_DATA; //TODO: Figure out if need client.
    }

    @NotNull
    @Override
    public TypeToken<generations.gg.generations.core.generationscore.world.dialogue.DialogueGraph> getTypeToken() {
        return typeToken;
    }

    @NotNull
    @Override
    public String getResourcePath() {
        return id.getPath();
    }

    @NotNull
    @Override
    public SimpleObservable<? extends DataRegistry> getObservable() {
        return observable;
    }

    @Override
    public void sync(@NotNull ServerPlayer player) {
        new DialogueGraphRegistrySyncPacket(graphsByIdentifier).sendToPlayer(player);
    }

    private final Map<ResourceLocation, DialogueGraph> graphsByIdentifier = new HashMap<>();

    public void reload(Map<ResourceLocation, ? extends DialogueGraph> data) {
        graphsByIdentifier.clear();
        data.entrySet().stream().filter(a -> a.getValue() != null).forEach(it -> graphsByIdentifier.put(it.getKey(), it.getValue()));
    }

    public DialogueGraph getOrElse(ResourceLocation location, DialogueGraph dialogueGraph) {
        return graphsByIdentifier.getOrDefault(location, dialogueGraph);
    }

    public DialogueGraph get(ResourceLocation id) {
        return graphsByIdentifier.get(id);
    }

    public void receiveSyncPacket(Map<ResourceLocation, DialogueGraph> graphs) {
        graphsByIdentifier.putAll(graphs);
    }

    private static final Dialogues instance = new Dialogues();

    public static Dialogues instance() {
        return instance;
    }

    @Override
    public void reload(@NotNull ResourceManager manager) {
        var data = new HashMap<ResourceLocation, DialogueGraph>();
        manager.listResources(this.getResourcePath(), path -> path.toString().endsWith(".json")).forEach((identifier, resource) -> {
            try (var reader = resource.openAsReader()) {
                var resolvedIdentifier = new ResourceLocation(identifier.getPath(), FilenameUtils.removeExtension(identifier.getPath()));

                data.put(resolvedIdentifier, gson.fromJson(reader, typeToken));
            } catch (Exception exception) {
                throw new RuntimeException("Error loading JSON for data: $identifier", exception);
            }
        });

        this.reload(data);
    }

}
