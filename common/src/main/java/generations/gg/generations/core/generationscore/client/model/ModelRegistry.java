package generations.gg.generations.core.generationscore.client.model;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.pokemod.pokemod.PokeMod;
import com.pokemod.pokemod.client.render.Pipelines;
import com.pokemod.rarecandy.components.AnimatedMeshObject;
import com.pokemod.rarecandy.components.MeshObject;
import de.javagl.jgltf.model.GltfModel;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class ModelRegistry {
    private static Function<GltfModel, Supplier<MeshObject>> MESH_OBJECT_SUPPLIER = gltfModel -> () -> {
        if (gltfModel.getSkinModels().isEmpty()) return new MeshObject();
        return new AnimatedMeshObject();
    };
    public static final LoadingCache<Pair<ResourceLocation, String>, CompiledModel> LOADER = CacheBuilder.newBuilder().build(new CacheLoader<>() {
        @Override
        public @NotNull CompiledModel load(@NotNull Pair<ResourceLocation, String> pair) {
            try {
                var resourceManager = Minecraft.getInstance().getResourceManager();
                var is = resourceManager.getResource(pair.a()).orElseGet(() -> resourceManager.getResource(PokeMod.id("models/pokemon/substitute.pk")).orElseThrow()).open();
                return new CompiledModel(pair.a, is, Pipelines.getPipeline(pair.b()), MESH_OBJECT_SUPPLIER);
            } catch (Exception e) {
                var path = pair.a().toString();
                if (path.endsWith(".smdx") || path.endsWith(".pqc")) throw new RuntimeException("Tried reading a 1.12 .smdx or .pqc");
                throw new RuntimeException("Failed to load " + path, e);
            }
        }
    });

    public static CompiledModel get(ModelContextProviders.ModelProvider modelProvider, String pipeline) {
        return get(modelProvider.getModel(), pipeline);
    }

    public static CompiledModel get(ResourceLocation location, String pipeline) {
        try {
            return LOADER.get(new Pair<>(location, pipeline));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void prepForBER(PoseStack stack, ModelContextProviders.AngleProvider supplier) {
        stack.translate(0.5f, 0.0f, 0.5f);
        stack.mulPose(Axis.YN.rotationDegrees(supplier.getAngle()));
    }

    private record Pair<A, B>(A a, B b) {}
}
