package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import de.javagl.jgltf.model.GltfModel;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import gg.generations.rarecandy.animation.Animation;
import gg.generations.rarecandy.components.AnimatedMeshObject;
import gg.generations.rarecandy.components.MeshObject;
import gg.generations.rarecandy.rendering.RareCandy;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModelRegistry {
    private static final Function<GltfModel, Supplier<MeshObject>> MESH_OBJECT_SUPPLIER = gltfModel -> () -> {
        if (gltfModel.getSkinModels().isEmpty()) return new MeshObject();
        return new AnimatedMeshObject();
    };
    public static final LoadingCache<Pair<ResourceLocation, String>, CompiledModel> LOADER = CacheBuilder.newBuilder().build(new CacheLoader<>() {
        @Override
        public @NotNull CompiledModel load(@NotNull Pair<ResourceLocation, String> pair) {
            try {
                var resourceManager = Minecraft.getInstance().getResourceManager();
                var is = resourceManager.getResource(pair.a()).orElseGet(() -> resourceManager.getResource(GenerationsCore.id("models/pokemon/substitute.pk")).orElseThrow()).open();
                return new CompiledModel(pair.a, is, Pipelines.getPipeline(pair.b()), MESH_OBJECT_SUPPLIER);
            } catch (Exception e) {
                var path = pair.a().toString();
                if (path.endsWith(".smdx") || path.endsWith(".pqc")) throw new RuntimeException("Tried reading a 1.12 .smdx or .pqc");
                throw new RuntimeException("Failed to load " + path, e);
            }
        }
    });
    private static RareCandy RENDERER;

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

    public static void all() {
        var map = LOADER.asMap().keySet();
        System.out.println("Amount: " + map.size() + " " + map);
    }

    private record Pair<A, B>(A a, B b) {}

    public static RareCandy getRareCandy() {
        //RareCandy.DEBUG_THREADS = true;
        if (RENDERER == null) RENDERER = new RareCandy();
        Animation.animationModifier = (animation, s) -> {
            if (s.equals("gfb")) animation.ticksPerSecond = 60_000; // 60 fps. 1000 ticks per frame?
        };
        return RENDERER;
    }

}