package generations.gg.generations.core.generationscore.common.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ModelRegistry;
import generations.gg.generations.core.generationscore.common.world.entity.ZygardeCellEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ZygardeCellRenderer extends EntityRenderer<ZygardeCellEntity> {
    public static ResourceLocation MODEL = GenerationsCore.id("models/pokemon/zygarde_cell.pk");

    public ZygardeCellRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(ZygardeCellEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        var model = ModelRegistry.get(MODEL);
        if(model == null) return;

        var instance = entity.instance;

        poseStack.pushPose();
        poseStack.mulPose(Axis.YN.rotationDegrees(entityYaw));
        instance.transformationMatrix().set(poseStack.last().pose());
        instance.setLight(packedLight);
        poseStack.popPose();
        model.render(instance);

        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ZygardeCellEntity entity) {
        return null;
    }
}
