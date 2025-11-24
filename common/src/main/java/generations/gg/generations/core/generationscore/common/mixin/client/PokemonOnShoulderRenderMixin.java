package generations.gg.generations.core.generationscore.common.mixin.client;

import com.cobblemon.mod.common.client.render.layer.PokemonOnShoulderRenderer;
import com.cobblemon.mod.common.client.render.models.blockbench.PosableModel;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
//import generations.gg.generations.core.generationscore.common.client.render.entity.GenerationsPokemonOnShoulderProxy;
//import generations.gg.generations.core.generationscore.common.client.render.entity.GenerationsPokemonOnShoulderProxy;
import generations.gg.generations.core.generationscore.common.client.model.RareCandyBone;
//import generations.gg.generations.core.generationscore.common.client.render.entity.GenerationsPokemonOnShoulderProxy;
import generations.gg.generations.core.generationscore.common.client.render.entity.PokemonOnShoulderRenderAccess;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(PokemonOnShoulderRenderer.class)
public abstract class PokemonOnShoulderRenderMixin<T extends Player> extends RenderLayer<T, PlayerModel<T>> implements PokemonOnShoulderRenderAccess {
    public PokemonOnShoulderRenderMixin(RenderLayerParent<T, PlayerModel<T>> renderer) {
        super(renderer);
    }

//    @Invoker(value = "extractUuid", remap = false) public abstract UUID invokeExtractUuid(CompoundTag tag);
//
//    @Invoker(value = "extractData", remap = false) public abstract PokemonOnShoulderRenderer.ShoulderData shoulderDataFrom(CompoundTag shoulderNbt, UUID pokemonUUID);

    @Redirect(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/player/Player;FFFFFFZ)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V"))
    public void renderTranslate(PoseStack instance, double x, double y, double z, @Local(argsOnly = true) boolean pLeftShoulder, @Local(name = "model") PosableModel model) {
        if(model.getRootPart() instanceof RareCandyBone) {
            x += pLeftShoulder ? -0.175 : 0.175;
        }

        instance.translate(x, y, z);
    }

//    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/player/Player;FFFFFFZ)V", at = @At("HEAD"), cancellable = true)
//    public void render(PoseStack matrixStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, boolean pLeftShoulder, CallbackInfo ci) {
//        GenerationsPokemonOnShoulderProxy.render(this, matrixStack, buffer, packedLight, livingEntity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, pLeftShoulder);
//        ci.cancel();
//    }
}
