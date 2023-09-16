package generations.gg.generations.core.generationscore.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.client.NpcSkinUtils;
import generations.gg.generations.core.generationscore.util.NpcUtils;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class PlayerNpcEntityRenderer extends LivingEntityRenderer<PlayerNpcEntity, PlayerModel<PlayerNpcEntity>> {
    private static PlayerModel<PlayerNpcEntity> STEVE_MODEL;
    private static PlayerModel<PlayerNpcEntity> ALEX_MODEL;

    private LivingEntity currentRenderedEntity;
    private final Map<String, EntityRenderer> rendererMap = new HashMap<>();
    private EntityRenderer currentRenderer;

    public PlayerNpcEntityRenderer(EntityRendererProvider.Context arg, boolean slim) {
        super(arg, new PlayerModel<>(arg.bakeLayer(slim ? ModelLayers.PLAYER_SLIM : ModelLayers.PLAYER), slim), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(arg.bakeLayer(slim ? ModelLayers.PLAYER_SLIM_INNER_ARMOR : ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel<>(arg.bakeLayer(slim ? ModelLayers.PLAYER_SLIM_OUTER_ARMOR : ModelLayers.PLAYER_OUTER_ARMOR)), arg.getModelManager()));
        this.addLayer(new PlayerNpcItemInHandLayer<>(this, arg.getItemInHandRenderer()));
        this.addLayer(new ArrowLayer<>(arg, this));
        this.addLayer(new CustomHeadLayer<>(this, arg.getModelSet(), arg.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer<>(this, arg.getModelSet()));
        this.addLayer(new SpinAttackEffectLayer<>(this, arg.getModelSet()));
        this.addLayer(new BeeStingerLayer<>(this));

        STEVE_MODEL = new PlayerModel<>(arg.bakeLayer(ModelLayers.PLAYER), false);
        ALEX_MODEL = new PlayerModel<>(arg.bakeLayer(ModelLayers.PLAYER_SLIM), true);
    }

    public void render(@NotNull PlayerNpcEntity entity, float entityYaw, float partialTicks, @NotNull PoseStack matrixStack, @NotNull MultiBufferSource buffer, int packedLight) {
        entity.setRotationFromDisplayData();
        /*if (entity.getRenderedEntityTypeKey().equals("pokemod:pixelmon") && entity.getRenderedEntity() instanceof PixelmonEntity pixelmonEntity) {
            matrixStack.pushPose();
            matrixStack.mulPose(Axis.YP.rotationDegrees(-Mth.rotLerp(partialTicks, entity.yHeadRotO, entity.yHeadRot)));
            float scale = entity.getRenderedScale();
            matrixStack.scale(scale, scale, scale);
            PixelmonEntityRenderer.renderInWorld(pixelmonEntity, matrixStack, RenderSystem.getProjectionMatrix());
            matrixStack.popPose();
        } else */if (!shouldRenderAsPlayer(entity)) {
            setRenderer(entity);
            this.currentRenderedEntity.tickCount = entity.tickCount;
            copyRotationsAndAnim(entity);
            this.shadowRadius = currentRenderer.shadowRadius * entity.getRenderedScale();
            this.shadowStrength = currentRenderer.shadowStrength;
            matrixStack.pushPose();
            float scale = entity.getRenderedScale();
            matrixStack.scale(scale, scale, scale);
            currentRenderer.render(currentRenderedEntity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
            matrixStack.popPose();
        } else {
            if (entity.getRenderedEntityTypeKey().equals(NpcUtils.MODEL_STEVE)) {
                this.model = STEVE_MODEL;
            } else {
                this.model = ALEX_MODEL;
            }
            this.shadowRadius = 0.5f * entity.getRenderedScale();
            this.shadowStrength = 1.0f;
            this.setModelProperties(entity);
            renderPlayerModel(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
        }
    }

    private void copyRotationsAndAnim(PlayerNpcEntity npcEntity) {
        if (currentRenderedEntity != null) {
            currentRenderedEntity.setYRot(npcEntity.getYRot());
            currentRenderedEntity.yRotO = npcEntity.yRotO;
            currentRenderedEntity.setYBodyRot(npcEntity.yBodyRot);
            currentRenderedEntity.yBodyRotO = npcEntity.yBodyRotO;

            currentRenderedEntity.setYHeadRot(npcEntity.getYHeadRot());
            currentRenderedEntity.yHeadRotO = npcEntity.yHeadRotO;

            currentRenderedEntity.setXRot(npcEntity.getXRot());
            currentRenderedEntity.xRotO = npcEntity.xRotO;
        }
    }

    public @NotNull Vec3 getRenderOffset(@NotNull PlayerNpcEntity entity, float partialTicks) {
        if (shouldRenderAsPlayer(entity)) {
            return entity.isCrouching() ? new Vec3(0.0, -0.125, 0.0) : super.getRenderOffset(entity, partialTicks);
        }
        return Vec3.ZERO;
    }

    private void setModelProperties(PlayerNpcEntity clientPlayer) {
        var playermodel = this.getModel();
        if (clientPlayer.isSpectator()) {
            playermodel.setAllVisible(false);
            playermodel.head.visible = true;
            playermodel.hat.visible = true;
        } else {
            playermodel.setAllVisible(true);
            playermodel.hat.visible = clientPlayer.isModelPartShown(PlayerModelPart.HAT);
            playermodel.jacket.visible = clientPlayer.isModelPartShown(PlayerModelPart.JACKET);
            playermodel.leftPants.visible = clientPlayer.isModelPartShown(PlayerModelPart.LEFT_PANTS_LEG);
            playermodel.rightPants.visible = clientPlayer.isModelPartShown(PlayerModelPart.RIGHT_PANTS_LEG);
            playermodel.leftSleeve.visible = clientPlayer.isModelPartShown(PlayerModelPart.LEFT_SLEEVE);
            playermodel.rightSleeve.visible = clientPlayer.isModelPartShown(PlayerModelPart.RIGHT_SLEEVE);
            playermodel.crouching = clientPlayer.isCrouching();
            var mainHand = getArmPose(clientPlayer, InteractionHand.MAIN_HAND);
            var offHand = getArmPose(clientPlayer, InteractionHand.OFF_HAND);
            if (mainHand.isTwoHanded()) {
                offHand = clientPlayer.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
            }

            if (clientPlayer.getMainArm() == HumanoidArm.RIGHT) {
                playermodel.rightArmPose = mainHand;
                playermodel.leftArmPose = offHand;
            } else {
                playermodel.rightArmPose = offHand;
                playermodel.leftArmPose = mainHand;
            }
        }
    }

    private static HumanoidModel.ArmPose getArmPose(PlayerNpcEntity player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.isEmpty()) {
            return HumanoidModel.ArmPose.EMPTY;
        } else {
            if (player.getUsedItemHand() == hand && player.getUseItemRemainingTicks() > 0) {
                UseAnim useanim = itemstack.getUseAnimation();
                if (useanim == UseAnim.BLOCK) {
                    return HumanoidModel.ArmPose.BLOCK;
                }

                if (useanim == UseAnim.BOW) {
                    return HumanoidModel.ArmPose.BOW_AND_ARROW;
                }

                if (useanim == UseAnim.SPEAR) {
                    return HumanoidModel.ArmPose.THROW_SPEAR;
                }

                if (useanim == UseAnim.CROSSBOW && hand == player.getUsedItemHand()) {
                    return HumanoidModel.ArmPose.CROSSBOW_CHARGE;
                }

                if (useanim == UseAnim.SPYGLASS) {
                    return HumanoidModel.ArmPose.SPYGLASS;
                }

                if (useanim == UseAnim.TOOT_HORN) {
                    return HumanoidModel.ArmPose.TOOT_HORN;
                }
            } else if (!player.swinging && itemstack.getItem() instanceof CrossbowItem && CrossbowItem.isCharged(itemstack)) {
                return HumanoidModel.ArmPose.CROSSBOW_HOLD;
            }
            return HumanoidModel.ArmPose.ITEM;
        }
    }

    private ResourceLocation getPlayerSkinTexture(@NotNull PlayerNpcEntity entity) {
        if (entity.getTexture() == null || entity.getTexture().isBlank()) {
            return NpcSkinUtils.DEFAULT_SKIN;
        }
        NpcSkinUtils.loadSkin(entity);
        return entity.getTextureResourceLocation() == null ? NpcSkinUtils.DEFAULT_SKIN : entity.getTextureResourceLocation();
    }

    /**
     * Returns the location of an entity's texture.
     */
    public @NotNull ResourceLocation getTextureLocation(@NotNull PlayerNpcEntity entity) {
        if (shouldRenderAsPlayer(entity)) {
            return getPlayerSkinTexture(entity);
        }
        LivingEntity renderedEntity = entity.getRenderedEntity();
        return this.entityRenderDispatcher.getRenderer(renderedEntity).getTextureLocation(renderedEntity);
    }

    protected void scale(@NotNull PlayerNpcEntity livingEntity, PoseStack matrixStack, float partialTickTime) {
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }

    protected void renderNameTag(@NotNull PlayerNpcEntity entity, @NotNull Component displayName, @NotNull PoseStack matrixStack, @NotNull MultiBufferSource buffer, int packedLight) {
        if (entity.isCustomNameVisible() && this.entityRenderDispatcher.distanceToSqr(entity) < 100.0) {
            matrixStack.pushPose();
            super.renderNameTag(entity, displayName, matrixStack, buffer, packedLight);
            matrixStack.popPose();
        }
    }

    protected void setupRotations(PlayerNpcEntity entityLiving, @NotNull PoseStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
        float f = entityLiving.getSwimAmount(partialTicks);
        float f3;
        float f2;
        if (entityLiving.isFallFlying()) {
            super.setupRotations(entityLiving, matrixStack, ageInTicks, rotationYaw, partialTicks);
            f3 = (float) entityLiving.getFallFlyingTicks() + partialTicks;
            f2 = Mth.clamp(f3 * f3 / 100.0F, 0.0F, 1.0F);
            if (!entityLiving.isAutoSpinAttack()) {
                matrixStack.mulPose(Axis.XP.rotationDegrees(f2 * (-90.0F - entityLiving.getXRot())));
            }

            Vec3 vec3 = entityLiving.getViewVector(partialTicks);
            Vec3 vec31 = entityLiving.getDeltaMovement();
            double d0 = vec31.horizontalDistanceSqr();
            double d1 = vec3.horizontalDistanceSqr();
            if (d0 > 0.0 && d1 > 0.0) {
                double d2 = (vec31.x * vec3.x + vec31.z * vec3.z) / Math.sqrt(d0 * d1);
                double d3 = vec31.x * vec3.z - vec31.z * vec3.x;
                matrixStack.mulPose(Axis.YP.rotation((float) (Math.signum(d3) * Math.acos(d2))));
            }
        } else if (f > 0.0F) {
            super.setupRotations(entityLiving, matrixStack, ageInTicks, rotationYaw, partialTicks);
            f3 = !entityLiving.isInWater() ? -90.0F : -90.0F - entityLiving.getXRot();
            f2 = Mth.lerp(f, 0.0F, f3);
            matrixStack.mulPose(Axis.XP.rotationDegrees(f2));
            if (entityLiving.isVisuallySwimming()) {
                matrixStack.translate(0.0F, -1.0F, 0.3F);
            }
        } else {
            super.setupRotations(entityLiving, matrixStack, ageInTicks, rotationYaw, partialTicks);
        }

    }

    private boolean shouldRenderAsPlayer(PlayerNpcEntity npcEntity) {
        return npcEntity.getRenderedEntityType().equals(npcEntity.getType()) || npcEntity.getRenderedEntity() == null;
    }

    private void setRenderer(PlayerNpcEntity npcEntity) {
        LivingEntity renderedEntity = npcEntity.getRenderedEntity();
        if (currentRenderedEntity != renderedEntity) {
            if (rendererMap.containsKey(npcEntity.getRenderedEntityTypeKey())) {
                currentRenderer = rendererMap.get(npcEntity.getRenderedEntityTypeKey());
                currentRenderedEntity = renderedEntity;
            } else {
                var renderer = this.entityRenderDispatcher.getRenderer(renderedEntity);
                if (renderer instanceof LivingEntityRenderer) {
                    currentRenderedEntity = renderedEntity;
                    currentRenderer = renderer;
                    rendererMap.put(npcEntity.getRenderedEntityTypeKey(), renderer);
                } else {
                    rendererMap.put(npcEntity.getRenderedEntityTypeKey(), null);
                    npcEntity.getDisplayData().getRendererInfo().setEntityType(NpcUtils.MODEL_STEVE);
                    npcEntity.updateDisplayData();
                    currentRenderedEntity = null;
                    currentRenderer = null;
                }
            }
        }
    }

    private void renderPlayerModel(PlayerNpcEntity entity, float entityYaw, float partialTicks, PoseStack matrixStack,
                                   MultiBufferSource buffer, int packedLight) {
        float k;
        Direction direction;
        matrixStack.pushPose();
        this.model.attackTime = this.getAttackAnim(entity, partialTicks);
        this.model.riding = entity.isPassenger();
        this.model.young = entity.isBaby();
        float f = Mth.rotLerp(partialTicks, entity.yBodyRotO, entity.yBodyRot);
        float g = Mth.rotLerp(partialTicks, entity.yHeadRotO, entity.yHeadRot);
        float h = g - f;
        if (entity.isPassenger() && entity.getVehicle() instanceof LivingEntity livingEntity) {
            f = Mth.rotLerp(partialTicks, livingEntity.yBodyRotO, livingEntity.yBodyRot);
            h = g - f;
            float i = Mth.wrapDegrees(h);
            if (i < -85.0f) {
                i = -85.0f;
            }
            if (i >= 85.0f) {
                i = 85.0f;
            }
            f = g - i;
            if (i * i > 2500.0f) {
                f += i * 0.2f;
            }
            h = g - f;
        }
        float j = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
        if (LivingEntityRenderer.isEntityUpsideDown(entity)) {
            j *= -1.0f;
            h *= -1.0f;
        }
        if (entity.hasPose(Pose.SLEEPING) && (direction = entity.getBedOrientation()) != null) {
            k = entity.getEyeHeight(Pose.STANDING) - 0.1f;
            matrixStack.translate((float)(-direction.getStepX()) * k, 0.0f, (float)(-direction.getStepZ()) * k);
        }
        float i = this.getBob(entity, partialTicks);
        this.setupRotations(entity, matrixStack, i, f, partialTicks);
        matrixStack.scale(-1.0f, -1.0f, 1.0f);
        this.scale(entity, matrixStack, partialTicks);
        matrixStack.translate(0.0f, -1.501f, 0.0f);
        k = 0.0f;
        float l = 0.0f;
        if (!entity.isPassenger() && entity.isAlive()) {
            k = entity.walkAnimation.speed(partialTicks);
            l = entity.walkAnimation.position(partialTicks);
            if (entity.isBaby()) {
                l *= 3.0f;
            }
            if (k > 1.0f) {
                k = 1.0f;
            }
        }
        ((EntityModel)this.model).prepareMobModel(entity, l, k, partialTicks);
        ((EntityModel)this.model).setupAnim(entity, l, k, i, h, j);
        Minecraft minecraft = Minecraft.getInstance();
        boolean bl = this.isBodyVisible(entity);
        boolean bl2 = !bl && !entity.isInvisibleTo(minecraft.player);
        boolean bl3 = minecraft.shouldEntityAppearGlowing(entity);
        RenderType renderType = this.getRenderType(entity, bl, bl2, bl3);
        if (renderType != null) {
            VertexConsumer vertexConsumer = buffer.getBuffer(renderType);
            int m = LivingEntityRenderer.getOverlayCoords(entity, this.getWhiteOverlayProgress(entity, partialTicks));
            ((Model)this.model).renderToBuffer(matrixStack, vertexConsumer, packedLight, m, 1.0f, 1.0f, 1.0f, bl2 ? 0.15f : 1.0f);
        }
        if (!entity.isSpectator()) {
            for (RenderLayer<PlayerNpcEntity, PlayerModel<PlayerNpcEntity>> renderLayer : this.layers) {
                renderLayer.render(matrixStack, buffer, packedLight, entity, l, k, partialTicks, i, h, j);
            }
        }
        matrixStack.popPose();

        if (!this.shouldShowName(entity)) {
            return;
        }
        this.renderNameTag(entity, (entity).getDisplayName(), matrixStack, buffer, packedLight);
    }
}