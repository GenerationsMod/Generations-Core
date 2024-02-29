package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.google.common.cache.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.world.level.block.entities.ModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class ModelRegistry {
    public static final LoadingCache<ResourceLocation, CompiledModel> LOADER = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.SECONDS).removalListener((RemovalListener<ResourceLocation, CompiledModel>) notification -> notification.getValue().delete()).build(new CacheLoader<>() {
        @Override
        public @NotNull CompiledModel load(@NotNull ResourceLocation pair) {
            try {
                var resourceManager = Minecraft.getInstance().getResourceManager();
                var is = resourceManager.getResource(pair).orElseGet(() -> {
                    System.out.println("Failed to get Pokemon model: " + pair);
                    return resourceManager.getResource(GenerationsCore.id("models/pokemon/substitute.pk")).orElseThrow();
                }).open();
                return new CompiledModel(pair, is);
            } catch (Exception e) {
                var path = pair.toString();
                if (path.endsWith(".smdx") || path.endsWith(".pqc")) throw new RuntimeException("Tried reading a 1.12 .smdx or .pqc");
                throw new RuntimeException("Failed to load " + path, e);
            }
        }
    });
    private static GenerationsRenderGraph WORLD_RENDER;
    private static GenerationsRenderGraph GUI_RENDER;

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

    public static GenerationsRenderGraph getWorldRareCandy() {
        //RareCandy.DEBUG_THREADS = true;
        if (WORLD_RENDER == null) WORLD_RENDER = new GenerationsRenderGraph();
//        Animation.animationModifier = (animation, s) -> {
//            if (s.equals("gfb")) animation.ticksPerSecond = 60_000; // 60 fps. 1000 ticks per frame?
//        };
        return WORLD_RENDER;
    }

    public static GenerationsRenderGraph getGuiRareCandy() {
        //RareCandy.DEBUG_THREADS = true;
        if (GUI_RENDER == null) GUI_RENDER = new GenerationsRenderGraph();
        return GUI_RENDER;
    }


}