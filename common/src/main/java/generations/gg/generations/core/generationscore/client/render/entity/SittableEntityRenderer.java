package generations.gg.generations.core.generationscore.client.render.entity;

import generations.gg.generations.core.generationscore.world.entity.block.SittableEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SittableEntityRenderer extends EntityRenderer<SittableEntity> {

    public SittableEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SittableEntity entity) {
        return null;
    }

}
