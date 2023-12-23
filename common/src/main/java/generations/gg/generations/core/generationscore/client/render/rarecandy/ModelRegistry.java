package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.google.common.cache.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import de.javagl.jgltf.model.GltfModel;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.world.level.block.entities.ModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;

import gg.generations.rarecandy.renderer.animation.Animation;
import gg.generations.rarecandy.renderer.components.AnimatedMeshObject;
import gg.generations.rarecandy.renderer.components.MeshObject;
import gg.generations.rarecandy.renderer.rendering.RareCandy;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModelRegistry {
    private static final Function<GltfModel, Supplier<MeshObject>> MESH_OBJECT_SUPPLIER = gltfModel -> () -> {
        if (gltfModel.getSkinModels().isEmpty()) return new MeshObject();
        return new AnimatedMeshObject();
    };
    public static final LoadingCache<ResourceLocation, CompiledModel> LOADER = CacheBuilder.newBuilder().removalListener((RemovalListener<ResourceLocation, CompiledModel>) notification -> notification.getValue().delete()).build(new CacheLoader<>() {
        @Override
        public @NotNull CompiledModel load(@NotNull ResourceLocation pair) {
            try {
                var resourceManager = Minecraft.getInstance().getResourceManager();
                var is = resourceManager.getResource(pair).orElseGet(() -> {
                    System.out.println("Failed to get Pokemon model: " + pair);
                    return resourceManager.getResource(GenerationsCore.id("models/pokemon/substitute.pk")).orElseThrow();
                }).open();
                return new CompiledModel(pair, is, MESH_OBJECT_SUPPLIER);
            } catch (Exception e) {
                var path = pair.toString();
                if (path.endsWith(".smdx") || path.endsWith(".pqc")) throw new RuntimeException("Tried reading a 1.12 .smdx or .pqc");
                throw new RuntimeException("Failed to load " + path, e);
            }
        }
    });
    private static RareCandy WORLD_RENDER;
    private static RareCandy GUI_RENDER;
    private static final PixelmonInstance guiInstance = new PixelmonInstance(new Matrix4f(), new Matrix4f(), "");

    public static CompiledModel get(ModelContextProviders.ModelProvider modelProvider) {
        return get(modelProvider.getModel());
    }

    public static CompiledModel get(ResourceLocation location) {
        try {
            return LOADER.get(location);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void prepForBER(PoseStack stack, ModelContextProviders.AngleProvider supplier) {

        stack.mulPose(Axis.YN.rotationDegrees(supplier.getAngle()));

        if(supplier instanceof ModelProvidingBlockEntity blockEntity && blockEntity.getBlockState().getBlock() instanceof GenericRotatableModelBlock<?> block) {
            var dir = blockEntity.getBlockState().getValue(GenericRotatableModelBlock.FACING);
            var opposite = dir.getOpposite();
            var counterClockwise = dir.getCounterClockWise();

            var displaceX = (block.width()+1) / 2f;
            var dispalceZ = (block.length()+1) / 2f;


            stack.translate(counterClockwise.getStepX() * displaceX, 0.0f, opposite.getStepZ() * dispalceZ);
        }

//        stack.translate(0.5f, 0.0f, 0.5f);
    }

    public static RareCandy getWorldRareCandy() {
        //RareCandy.DEBUG_THREADS = true;
        if (WORLD_RENDER == null) WORLD_RENDER = new RareCandy();
        Animation.animationModifier = (animation, s) -> {
            if (s.equals("gfb")) animation.ticksPerSecond = 60_000; // 60 fps. 1000 ticks per frame?
        };
        return WORLD_RENDER;
    }

    public static RareCandy getGuiRareCandy() {
        //RareCandy.DEBUG_THREADS = true;
        if (GUI_RENDER == null) GUI_RENDER = new RareCandy();
        Animation.animationModifier = (animation, s) -> {
            if (s.equals("gfb")) animation.ticksPerSecond = 60_000; // 60 fps. 1000 ticks per frame?
        };
        return GUI_RENDER;
    }


    public static PixelmonInstance getGuiInstance() {
        return guiInstance;
    }
}