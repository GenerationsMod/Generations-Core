package generations.gg.generations.core.generationscore.common.mixin.client;

import com.cobblemon.mod.common.client.render.layer.PokemonOnShoulderRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import generations.gg.generations.core.generationscore.common.client.render.entity.GenerationsPokemonOnShoulderProxy;
import generations.gg.generations.core.generationscore.common.client.render.entity.PokemonOnShoulderRenderAccess;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.checkerframework.common.reflection.qual.Invoke;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(PokemonOnShoulderRenderer.class)
public abstract class PokemonOnShoulderRenderMixin<T extends Player> extends RenderLayer<T, PlayerModel<T>> implements PokemonOnShoulderRenderAccess {
    public PokemonOnShoulderRenderMixin(RenderLayerParent<T, PlayerModel<T>> renderer) {
        super(renderer);
    }

    @Invoker(value = "extractUuid", remap = false) public abstract UUID invokeExtractUuid(CompoundTag tag);

    @Invoker(value = "extractData", remap = false) public abstract PokemonOnShoulderRenderer.ShoulderData invokeExtractData(CompoundTag shoulderNbt, UUID pokemonUUID);

    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/player/Player;FFFFFFZ)V", at = @At("HEAD"), cancellable = true)
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, boolean pLeftShoulder, CallbackInfo ci) {
        GenerationsPokemonOnShoulderProxy.render(this, matrixStack, buffer, packedLight, livingEntity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, pLeftShoulder);
        ci.cancel();
    }
}
