package generations.gg.generations.core.generationscore.client.render.entity;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.render.rarecandy.BlockObjectInstance;
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry;
import generations.gg.generations.core.generationscore.world.entity.ZygardeCellEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public class ZygardeCellRenderer extends EntityRenderer<ZygardeCellEntity> {
    public static ResourceLocation MODEL = GenerationsCore.id("models/pokemon/zygarde_cell.pk");

    public ZygardeCellRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(ZygardeCellEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        var model = ModelRegistry.get(MODEL);

        if(!model.isReady()) {
            System.out.println("Blep3");
            return;
        }

        var instance = entity.instance;

        if(instance == null) {
            instance = new BlockObjectInstance(model.renderObject, new Matrix4f(), null);
        }



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
