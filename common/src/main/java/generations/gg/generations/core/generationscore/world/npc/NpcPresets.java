package generations.gg.generations.core.generationscore.world.npc;

import com.cobblemon.mod.common.api.data.DataRegistry;
import com.cobblemon.mod.common.api.data.JsonDataRegistry;
import com.cobblemon.mod.common.api.reactive.SimpleObservable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.shop.NpcPresetsRegistrySyncPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class NpcPresets implements JsonDataRegistry<NpcPreset> {
    private static final ResourceLocation id = GenerationsCore.id("npc_presets");

    private static final Gson gson = new GsonBuilder()
            .serializeNulls()
            .create();

    private static final TypeToken<NpcPreset> typeToken = TypeToken.get(NpcPreset.class);

    private final SimpleObservable<NpcPresets> observable = new SimpleObservable<NpcPresets>();

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
    public TypeToken<NpcPreset> getTypeToken() {
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
        new NpcPresetsRegistrySyncPacket(graphsByIdentifier).sendToPlayer(player);
    }

    private final Map<ResourceLocation, NpcPreset> graphsByIdentifier = new HashMap<>();

    public void reload(Map<ResourceLocation, ? extends NpcPreset> data) {
        graphsByIdentifier.clear();
        data.entrySet().stream().filter(a -> a.getValue() != null).forEach(it -> graphsByIdentifier.put(it.getKey(), it.getValue()));
    }

    public NpcPreset getOrElse(ResourceLocation location, NpcPreset dialogueGraph) {
        return graphsByIdentifier.getOrDefault(location, dialogueGraph);
    }

    public NpcPreset get(ResourceLocation id) {
        return graphsByIdentifier.get(id);
    }

    public void receiveSyncPacket(Map<ResourceLocation, NpcPreset> graphs) {
        graphsByIdentifier.putAll(graphs);
    }

    private static final NpcPresets instance = new NpcPresets();

    public static NpcPresets instance() {
        return instance;
    }

    @Override
    public void reload(@NotNull ResourceManager manager) {
        var data = new HashMap<ResourceLocation, NpcPreset>();
        var map = manager.listResources(this.getResourcePath(), path -> path.toString().endsWith(".json")).entrySet();

        for (Map.Entry<ResourceLocation, Resource> entry : map) {
            ResourceLocation identifier = entry.getKey();
            Resource resource = entry.getValue();
            try (var reader = resource.openAsReader()) {
                var resolvedIdentifier = new ResourceLocation(identifier.getNamespace(), FilenameUtils.removeExtension(Path.of(identifier.getPath()).getFileName().toString()));
                var json = gson.fromJson(reader, JsonObject.class);
                var graph = JsonOps.INSTANCE.withDecoder(NpcPreset.CODEC).andThen(DataResult::result).andThen(Optional::orElseThrow).andThen(Pair::getFirst).apply(json);
                data.put(resolvedIdentifier, graph);
            } catch (Exception exception) {
                throw new RuntimeException("Error loading JSON for data: %s".formatted(identifier), exception);
            }
        }

        this.reload(data);
    }

    public boolean contains(ResourceLocation id) {
        return this.graphsByIdentifier.containsKey(id);
    }

    public Set<ResourceLocation> getIds() {
        return graphsByIdentifier.keySet();
    }
}
