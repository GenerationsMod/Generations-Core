package generations.gg.generations.core.generationscore.client.render.entity;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.entity.GenerationsBoatEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.stream.Stream;

public class GenerationsBoatRenderer extends EntityRenderer<GenerationsBoatEntity> {
    private final Map<GenerationsBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public GenerationsBoatRenderer(EntityRendererProvider.Context context, boolean bl) {
        super(context);
        this.shadowRadius = 0.8F;
        this.boatResources = Stream.of(GenerationsBoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap((type) -> type, (type) -> Pair.of(GenerationsCore.id(getTextureLocation(type, bl)), this.createBoatModel(context, type, bl))));
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context context, GenerationsBoatEntity.Type type, boolean bl) {
        ModelLayerLocation modelLayerLocation = bl ? createChestBoatModelName(type) : createBoatModelName(type);
        ModelPart modelPart = context.bakeLayer(modelLayerLocation);
        return bl ? new ChestBoatModel(modelPart) : new BoatModel(modelPart);
    }

    public static ModelLayerLocation createBoatModelName(GenerationsBoatEntity.Type type) {
        return createLocation("boat/" + type.getName());
    }

    public static ModelLayerLocation createChestBoatModelName(GenerationsBoatEntity.Type type) {
        return createLocation("chest_boat/" + type.getName());
    }

    private static ModelLayerLocation createLocation(String string) {
        return new ModelLayerLocation(GenerationsCore.id(string), "main");
    }

    private static String getTextureLocation(GenerationsBoatEntity.Type type, boolean bl) {
        return bl ? "textures/key/chest_boat/" + type.getName() + ".png" : "textures/key/boat/" + type.getName() + ".png";
    }

    public void render(GenerationsBoatEntity boat, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.375F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - f));
        float h = (float)boat.getHurtTime() - g;
        float j = boat.getDamage() - g;
        if (j < 0.0F) {
            j = 0.0F;
        }

        if (h > 0.0F) {
            poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(h) * h * j / 10.0F * (float)boat.getHurtDir()));
        }

        float k = boat.getBubbleAngle(g);
        if (!Mth.equal(k, 0.0F)) {
            poseStack.mulPose((new Quaternionf()).setAngleAxis(boat.getBubbleAngle(g) * 0.017453292F, 1.0F, 0.0F, 1.0F));
        }

        Pair<ResourceLocation, ListModel<Boat>> pair = this.boatResources.get(boat.getModBoatType());
        ResourceLocation resourceLocation = pair.getFirst();
        ListModel<Boat> listModel = pair.getSecond();
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        listModel.setupAnim(boat, g, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(listModel.renderType(resourceLocation));
        listModel.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!boat.isUnderWater()) {
            VertexConsumer vertexConsumer2 = multiBufferSource.getBuffer(RenderType.waterMask());
            if (listModel instanceof WaterPatchModel waterPatchModel) {
                waterPatchModel.waterPatch().render(poseStack, vertexConsumer2, i, OverlayTexture.NO_OVERLAY);
            }
        }

        poseStack.popPose();
        super.render(boat, f, g, poseStack, multiBufferSource, i);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(GenerationsBoatEntity boat) {
        return this.boatResources.get(boat.getModBoatType()).getFirst();
    }
}
