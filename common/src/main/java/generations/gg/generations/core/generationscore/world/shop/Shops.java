package generations.gg.generations.core.generationscore.world.shop;

import com.cobblemon.mod.common.api.data.DataRegistry;
import com.cobblemon.mod.common.api.data.JsonDataRegistry;
import com.cobblemon.mod.common.api.reactive.SimpleObservable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import generations.gg.generations.core.generationscore.GenerationsCore;
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

public class Shops implements JsonDataRegistry<Shop> {
    private static final ResourceLocation id = GenerationsCore.id("shops");

    private static final Gson gson = new GsonBuilder()
            .serializeNulls()
            .create();

    private static final TypeToken<Shop> typeToken = TypeToken.get(Shop.class);

    private final SimpleObservable<Shops> observable = new SimpleObservable<Shops>();

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



    @Override
    public TypeToken<Shop> getTypeToken() {
        return typeToken;
    }

    @NotNull
    @Override
    public String getResourcePath() {
        return id.getPath();
    }

    @Override
    public SimpleObservable<? extends DataRegistry> getObservable() {
        return observable;
    }

    @Override
    public void sync(@NotNull ServerPlayer player) {
        new ShopRegistrySyncPacket(graphsByIdentifier).sendToPlayer(player);
    }

    private final Map<ResourceLocation, Shop> graphsByIdentifier = new HashMap<>();

    @Override
    public void reload(@NotNull Map<ResourceLocation, ? extends Shop> data) {
        graphsByIdentifier.clear();
        data.entrySet().stream().filter(a -> a.getValue() != null).forEach(it -> graphsByIdentifier.put(it.getKey(), it.getValue()));
    }

    public Shop getOrElse(ResourceLocation location, Shop dialogueGraph) {
        return graphsByIdentifier.getOrDefault(location, dialogueGraph);
    }

    public Shop get(ResourceLocation id) {
        return graphsByIdentifier.get(id);
    }

    public void receiveSyncPacket(Map<ResourceLocation, Shop> graphs) {
        graphsByIdentifier.putAll(graphs);
    }

    private static final Shops instance = new Shops();

    public static Shops instance() {
        return instance;
    }

    @Override
    public void reload(@NotNull ResourceManager manager) {
        Map<ResourceLocation, Shop> data = new HashMap<ResourceLocation, Shop>();
        var map = manager.listResources(this.getResourcePath(), path -> path.toString().endsWith(".json")).entrySet();

        for (Map.Entry<ResourceLocation, Resource> entry : map) {
            ResourceLocation identifier = entry.getKey();
            Resource resource = entry.getValue();
            try (var reader = resource.openAsReader()) {
                var resolvedIdentifier = new ResourceLocation(identifier.getNamespace(), FilenameUtils.removeExtension(Path.of(identifier.getPath()).getFileName().toString()));
                var json = gson.fromJson(reader, JsonObject.class);
                var shop = JsonOps.INSTANCE.withDecoder(Shop.CODEC).andThen(a -> a.result()).andThen(a -> a.orElseThrow()).andThen(Pair::getFirst).apply(json);
                data.put(resolvedIdentifier, shop);
            } catch (Exception exception) {
                throw new RuntimeException("Error loading JSON for data: %s".formatted(identifier), exception);
            }
        }

        this.reload(data);
    }
}
