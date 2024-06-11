package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.google.common.cache.*;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.mojang.blaze3d.pipeline.RenderCall;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
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

import java.io.FileNotFoundException;
import java.util.AbstractMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.google.common.util.concurrent.Futures.immediateFuture;

public class ModelRegistry {
    private static final String DUMMY = "dummy";

    public static final LoadingCache<ResourceLocation, CompiledModel> LOADER = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.SECONDS).removalListener((RemovalListener<ResourceLocation, CompiledModel>) notification -> notification.getValue().delete()).build(new CacheLoader<>() {
        @Override
        public @NotNull CompiledModel load(@NotNull ResourceLocation pair) {
            var resourceManager = Minecraft.getInstance().getResourceManager();

            try {
                return CompiledModel.of(pair, resourceManager.getResourceOrThrow(pair));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    });
    private static RareCandy WORLD_RENDER;
    private static RareCandy GUI_RENDER;

    public static CompiledModel get(ModelContextProviders.ModelProvider modelProvider) {
        return get(modelProvider.getModel());
    }

    public static CompiledModel get(ResourceLocation location) {
        try {
            return LOADER.get(location);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void prepForBER(PoseStack stack, ModelContextProviders.AngleProvider supplier) {

        stack.translate(0.5f, 0.0f, 0.5f);

        if(supplier instanceof ModelProvidingBlockEntity blockEntity && blockEntity.getBlockState().getBlock() instanceof GenericRotatableModelBlock<?> block && block.shouldRotateSpecial()) {
            var forward = blockEntity.getBlockState().getValue(GenericRotatableModelBlock.FACING);

            var x = block.getWidthValue(blockEntity.getBlockState());
            var z = block.getLengthValue(blockEntity.getBlockState());


            var width = (block.width() * 0.5f) - x;
            var length = (block.length() * 0.5f) - z;

            switch (forward) {
                case SOUTH -> stack.translate(width, 0, -length);
                case EAST -> stack.translate(-length, 0, -width);
                case NORTH -> stack.translate(-width, 0, length);
                case WEST -> stack.translate(length, 0, width);
            }

            stack.mulPose(Axis.YN.rotationDegrees(supplier.getAngle()));
        } else {
           stack.mulPose(Axis.YN.rotationDegrees(supplier.getAngle()));
        }

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
}