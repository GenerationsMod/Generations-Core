package generations.gg.generations.core.generationscore.forge.datagen.cobblemon;

import com.cobblemon.mod.common.entity.PoseType;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class PokemonModelsProvider implements DataProvider {
    private final PackOutput.PathProvider resolverPathProvider;
    private final PackOutput.PathProvider poserPathProvider;

    protected List<Pair<Resolver, List<PoserBuilder>>> pairs = new ArrayList<>();

    public PokemonModelsProvider(PackOutput arg) {
        this.resolverPathProvider = arg.createPathProvider(PackOutput.Target.RESOURCE_PACK, "bedrock/pokemon/resolvers");
        this.poserPathProvider = arg.createPathProvider(PackOutput.Target.RESOURCE_PACK, "bedrock/pokemon/posers");
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput output) {
        Set<ResourceLocation> resolvers = Sets.newHashSet();
        Set<ResourceLocation> poser = Sets.newHashSet();
        List<CompletableFuture<?>> list = new ArrayList<>();

        build();

        pairForEach(pairs, (resolver, poserBuilders) -> {
            if (!resolvers.add(resolver.getId())) {
                throw new IllegalArgumentException("Duplicate Resolvers" + resolver.getId());
            } else {
                list.add(DataProvider.saveStable(output, resolver.build(), this.resolverPathProvider.json(resolver.getId())));
            }

            for (var poserBuilder : poserBuilders) {
                if (!poser.add(poserBuilder.getId())) {
                    throw new IllegalArgumentException("Duplicate Posers" + poserBuilder.getId());
                } else {
                    list.add(DataProvider.saveStable(output, poserBuilder.build(), this.poserPathProvider.json(poserBuilder.getId())));
                }
            }
        });



        return CompletableFuture.allOf(list.toArray(CompletableFuture[]::new));
    }

    private static <T, V> void pairForEach(List<Pair<T, V>> list, BiConsumer<T,V> o) {
        list.forEach(pair -> o.accept(pair.getFirst(), pair.getSecond()));
    }

    public abstract void build();

    @Override
    public @NotNull String getName() {
        return "Pokemon Models";
    }

    public static Resolver simple(ResourceLocation id, int order) {
        return simple(id, new ResourceLocation("cobblemon", id.getPath()), order);
    }

    public static Resolver simple(ResourceLocation id, ResourceLocation species, int order) {
        return new Resolver(id, species, order);
    }

    public static PoserBuilder poser(ResourceLocation location) {
        return new PoserBuilder(location);
    }

    public static Resolver.ModelAssetVariationBuilder base() {
        return new Resolver.ModelAssetVariationBuilder();
    }

    public static Resolver.ModelAssetVariationBuilder shiny() {
        return new Resolver.ModelAssetVariationBuilder().aspect("shiny");
    }

    public static class Resolver {
        private final ResourceLocation id;
        private final ResourceLocation species;
        private final int order;

        private List<ModelAssetVariationBuilder> set = new ArrayList<>();

        public Resolver(ResourceLocation id, ResourceLocation species, int order) {
            this.id = id;
            this.species = species;
            this.order = order;
        }

        public ResourceLocation getId() {
            return id;
        }

        public JsonElement build() {
            var json = new JsonObject();
            json.addProperty("name", species.toString());
            json.addProperty("order", order);
            json.add("variations", toArray(set, ModelAssetVariationBuilder::build));
            return json;
        }

        private static <T> JsonElement toArray(Collection<T> set, Function<T, JsonElement> build) {
            var array = new JsonArray();
            set.stream().map(build).forEach(array::add);
            return array;
        }

        public Resolver variation(ModelAssetVariationBuilder builder) {
            set.add(builder);
            return this;
        }

        public Resolver variations(ModelAssetVariationBuilder... builders) {
            set.addAll(Arrays.asList(builders));
            return this;
        }

        public void register(Consumer<Resolver> provider) {
            provider.accept(this);
        }

        public static class ModelAssetVariationBuilder {
            private Set<String> aspects = Sets.newHashSet();

            private ResourceLocation poser;

            private ResourceLocation model;

            private String variant;

            public ModelAssetVariationBuilder aspect(String aspect) {
                this.aspects.add(aspect);
                return this;
            }

            public ModelAssetVariationBuilder poser(ResourceLocation poser) {
                this.poser = poser;
                return this;
            }

            public ModelAssetVariationBuilder model(ResourceLocation model) {
                this.model = model.withSuffix(".pk");
                return this;
            }

            public ModelAssetVariationBuilder variant(String varaint) {
                this.variant = varaint;
                return this;
            }

            public ModelAssetVariationBuilder aspects(String... aspects) {
                Collections.addAll(this.aspects, aspects);
                return this;
            }

            public JsonElement build() {
                var json = new JsonObject();
                json.add("aspects", PoseBuilder.toArray(aspects));
                if(poser != null) json.addProperty("poser", poser.toString());
                if(model != null) json.addProperty("model", model.toString());
                if(variant != null) json.addProperty("texture", "pk:" + variant);
                return json;
            }
        }
    }

    public static class PoseBuilder {
        private final String animation;
        private String poseName;
        private Integer transformationTicks;

        private Set<PoseType> poseTypes = new HashSet<>();
        private Boolean isBattle;

        private Boolean isTouchingWater;

        public ResourceLocation modelReference;

        public PoseBuilder(ResourceLocation modelReference, String poseName, String animation) {
            this.modelReference = modelReference;
            this.poseName = poseName;

            this.animation = animation;
        }

        public JsonElement builder() {
            var json = new JsonObject();

            json.addProperty("poseName", poseName);
            if(transformationTicks != null) json.addProperty("transformationTicks", transformationTicks);
            if(!poseTypes.isEmpty()) json.add("poseTypes", toArray(poseTypes.stream().map(Enum::name).toList()));
            if(isBattle != null) json.addProperty("isBattle", isBattle);
            if(isTouchingWater != null) json.addProperty("isTouchingWater", isTouchingWater);
            var array = new JsonArray();
            array.add("pk(%s, %s.smd)".formatted(modelReference.withSuffix(".pk").toString(), animation));
            json.add("animations", array);

            return json;
        }

        public static JsonArray toArray(Collection<String> elements) {
            var array = new JsonArray();
            elements.forEach(array::add);
            return array;
        }

        public PoseBuilder pose(PoseType poseType) {
            poseTypes.add(poseType);
            return this;
        }

        public PoseBuilder poses(PoseType... poseTypes) {
            Collections.addAll(this.poseTypes, poseTypes);
            return this;
        }
    }

    public static class PoserBuilder {
        private final ResourceLocation model;
        private Map<String, PoseBuilder> poses = new HashMap<>();

        public PoserBuilder(ResourceLocation model) {
            this.model = model;
        }

        public PoserBuilder walking() {
            poses.put("walking", new PoseBuilder(model, "walking", "walk").pose(PoseType.WALK));
            return this;
        }

        public PoserBuilder standing() {
            poses.put("standing", new PoseBuilder(model, "standing", "idle").poses(PoseType.STAND, PoseType.NONE, PoseType.PORTRAIT, PoseType.PROFILE, PoseType.FLOAT));
            return this;
        }

        public PoserBuilder flying() {
            poses.put("flying", new PoseBuilder(model, "flying", "flying").pose(PoseType.FLY));
            return this;
        }

        public JsonElement build() {
            var json = new JsonObject();
            var posesJson = new JsonObject();
            poses.forEach((k, v) -> posesJson.add(k, v.builder()));

            json.add("poses", posesJson);
            return json;
        }

        public void register(Consumer<PoserBuilder> provider) {
            provider.accept(this);
        }

        public ResourceLocation getId() {
            return model;
        }
    }
}