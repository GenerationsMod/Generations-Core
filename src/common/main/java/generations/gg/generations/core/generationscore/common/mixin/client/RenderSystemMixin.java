package generations.gg.generations.core.generationscore.common.mixin.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.common.client.render.RenderStateRecord;
import net.minecraft.client.renderer.RenderStateShard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSystem.class)
public class RenderSystemMixin {
    @Inject(method = "enableBlend", at = @At("HEAD"))
    private static void onEnableBlend(CallbackInfo ci) {
        if(!isActive()) RenderStateRecord.INSTANCE.setBlendEnabled(true);
    }

    @Inject(method = "disableBlend", at = @At("HEAD"))
    private static void onDisableBlend(CallbackInfo ci) {
        if(!isActive()) RenderStateRecord.INSTANCE.setBlendEnabled(false);
    }

    @Inject(method = "blendFuncSeparate(Lcom/mojang/blaze3d/platform/GlStateManager$SourceFactor;Lcom/mojang/blaze3d/platform/GlStateManager$DestFactor;Lcom/mojang/blaze3d/platform/GlStateManager$SourceFactor;Lcom/mojang/blaze3d/platform/GlStateManager$DestFactor;)V", at = @At("HEAD"))
    private static void onBlendFuncSeparateEnum(GlStateManager.SourceFactor srcFactor, GlStateManager.DestFactor dstFactor, GlStateManager.SourceFactor srcFactor2, GlStateManager.DestFactor destFactor2, CallbackInfo ci) {
        if(!isActive()) {
            RenderStateRecord.INSTANCE.setSrcRgb(srcFactor.value);
            RenderStateRecord.INSTANCE.setDstRgb(dstFactor.value);
            RenderStateRecord.INSTANCE.setSrcAlpha(srcFactor2.value);
            RenderStateRecord.INSTANCE.setDstAlpha(destFactor2.value);
        }
    }

    @Inject(method = "blendFuncSeparate(IIII)V", at = @At("HEAD"))
    private static void onBlendFuncSeparate(int srcFactor, int dstFactor, int srcFactor2, int destFactor2, CallbackInfo ci) {
        if(!isActive()) {
            RenderStateRecord.INSTANCE.setSrcRgb(srcFactor);
            RenderStateRecord.INSTANCE.setDstRgb(dstFactor);
            RenderStateRecord.INSTANCE.setSrcAlpha(srcFactor2);
            RenderStateRecord.INSTANCE.setDstAlpha(destFactor2);
        }
    }

    @Inject(method = "enableDepthTest", at = @At("HEAD"))
    private static void onEnableDepth(CallbackInfo ci) {
        if(!isActive()) RenderStateRecord.INSTANCE.setDepthTestEnabled(true);
    }

    @Inject(method = "disableDepthTest", at = @At("HEAD"))
    private static void onDisableDepth(CallbackInfo ci) {
        if(!isActive()) RenderStateRecord.INSTANCE.setBlendEnabled(false);
    }

    @Inject(method = "depthMask", at = @At("HEAD"))
    private static void setDepthMask(boolean depthMask, CallbackInfo ci) {
        if(!isActive()) RenderStateRecord.INSTANCE.setDepthMask(depthMask);
    }

    @Inject(method = "depthFunc", at = @At("HEAD"))
    private static void setDepthFunc(int depthFunc, CallbackInfo ci) {
        if(!isActive()) RenderStateRecord.INSTANCE.setDepthFunc(depthFunc);
    }

    @Inject(method = "enableCull", at = @At("HEAD"))
    private static void onEnableCull(CallbackInfo ci) {
        if(!isActive()) RenderStateRecord.INSTANCE.setCullEnabled(true);
    }

    @Inject(method = "disableCull", at = @At("HEAD"))
    private static void onDisableCull(CallbackInfo ci) {
        if(!isActive()) RenderStateRecord.INSTANCE.setBlendEnabled(false);
    }

    @Unique
    private static boolean isActive() {
        return RenderStateRecord.INSTANCE.isActive();
    }

}
