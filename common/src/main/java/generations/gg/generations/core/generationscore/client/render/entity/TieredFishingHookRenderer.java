package generations.gg.generations.core.generationscore.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.world.entity.TieredFishingHookEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class TieredFishingHookRenderer extends EntityRenderer<TieredFishingHookEntity> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("textures/key/fishing_hook.png");
    private static final RenderType RENDER_TYPE = RenderType.entityCutout(TEXTURE_LOCATION);
    private static final double VIEW_BOBBING_SCALE = 960.0;

    public TieredFishingHookRenderer(EntityRendererProvider.Context arg) {
        super(arg);
    }

    @Override
    public void render(TieredFishingHookEntity entity, float entityYaw, float partialTicks, @NotNull PoseStack matrixStack, @NotNull MultiBufferSource buffer, int packedLight) {
        Player player = entity.getPlayerOwner();
        if (player != null) {
            float f3;
            double d6;
            double d5;
            double d4;
            matrixStack.pushPose();
            matrixStack.pushPose();
            matrixStack.scale(0.5f, 0.5f, 0.5f);
            matrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            matrixStack.mulPose(Axis.YP.rotationDegrees(180.0f));
            PoseStack.Pose posestack$pose = matrixStack.last();
            Matrix4f matrix4f = posestack$pose.pose();
            Matrix3f matrix3f = posestack$pose.normal();
            VertexConsumer vertexconsumer = buffer.getBuffer(RENDER_TYPE);
            net.minecraft.client.renderer.entity.FishingHookRenderer.vertex(vertexconsumer, matrix4f, matrix3f, packedLight, 0.0f, 0, 0, 1);
            net.minecraft.client.renderer.entity.FishingHookRenderer.vertex(vertexconsumer, matrix4f, matrix3f, packedLight, 1.0f, 0, 1, 1);
            net.minecraft.client.renderer.entity.FishingHookRenderer.vertex(vertexconsumer, matrix4f, matrix3f, packedLight, 1.0f, 1, 1, 0);
            net.minecraft.client.renderer.entity.FishingHookRenderer.vertex(vertexconsumer, matrix4f, matrix3f, packedLight, 0.0f, 1, 0, 0);
            matrixStack.popPose();
            int i = player.getMainArm() == HumanoidArm.RIGHT ? 1 : -1;
            ItemStack itemstack = player.getMainHandItem();
//            if (!itemstack.canPerformAction(ToolActions.FISHING_ROD_CAST)) { TODO: Figure out multiplat stuffs
//                i = -i;
//            }
            float f = player.getAttackAnim(partialTicks);
            float f1 = Mth.sin(Mth.sqrt(f) * (float)Math.PI);
            float f2 = Mth.lerp(partialTicks, player.yBodyRotO, player.yBodyRot) * ((float)Math.PI / 180);
            double d0 = Mth.sin(f2);
            double d1 = Mth.cos(f2);
            double d2 = (double)i * 0.35;
            double d3 = 0.8;
            if ((this.entityRenderDispatcher.options == null || this.entityRenderDispatcher.options.getCameraType().isFirstPerson()) && player == Minecraft.getInstance().player) {
                double d7 = 960.0 / (double) this.entityRenderDispatcher.options.fov().get();
                Vec3 vec3 = this.entityRenderDispatcher.camera.getNearPlane().getPointOnPlane((float)i * 0.525f, -0.1f);
                vec3 = vec3.scale(d7);
                vec3 = vec3.yRot(f1 * 0.5f);
                vec3 = vec3.xRot(-f1 * 0.7f);
                d4 = Mth.lerp(partialTicks, player.xo, player.getX()) + vec3.x;
                d5 = Mth.lerp(partialTicks, player.yo, player.getY()) + vec3.y;
                d6 = Mth.lerp(partialTicks, player.zo, player.getZ()) + vec3.z;
                f3 = player.getEyeHeight();
            } else {
                d4 = Mth.lerp(partialTicks, player.xo, player.getX()) - d1 * d2 - d0 * 0.8;
                d5 = player.yo + (double)player.getEyeHeight() + (player.getY() - player.yo) * (double)partialTicks - 0.45;
                d6 = Mth.lerp(partialTicks, player.zo, player.getZ()) - d0 * d2 + d1 * 0.8;
                f3 = player.isCrouching() ? -0.1875f : 0.0f;
            }
            double d9 = Mth.lerp(partialTicks, entity.xo, entity.getX());
            double d10 = Mth.lerp(partialTicks, entity.yo, entity.getY()) + 0.25;
            double d8 = Mth.lerp(partialTicks, entity.zo, entity.getZ());
            float f4 = (float)(d4 - d9);
            float f5 = (float)(d5 - d10) + f3;
            float f6 = (float)(d6 - d8);
            VertexConsumer vertexconsumer1 = buffer.getBuffer(RenderType.lineStrip());
            PoseStack.Pose posestack$pose1 = matrixStack.last();
            for (float k = 0; k <= 16f; ++k) {
                net.minecraft.client.renderer.entity.FishingHookRenderer.stringVertex(f4, f5, f6, vertexconsumer1, posestack$pose1, k/16f, (k + 1)/16f);
            }
            matrixStack.popPose();
            super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(TieredFishingHookEntity entity) {
        return TEXTURE_LOCATION;
    }
}

