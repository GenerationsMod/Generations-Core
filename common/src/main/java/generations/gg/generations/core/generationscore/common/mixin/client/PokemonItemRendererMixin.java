package generations.gg.generations.core.generationscore.common.mixin.client;

import com.cobblemon.mod.common.client.render.item.CobblemonBuiltinItemRenderer;
import com.cobblemon.mod.common.client.render.item.PokemonItemRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
//import generations.gg.generations.core.generationscore.common.client.render.TimeCapsuleItemRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

//TODO: Remove once on cobblemon version with whole context needed.

//@Mixin(PokemonItemRenderer.class)
//public class PokemonItemRendererMixin implements CobblemonBuiltinItemRenderer {
//    public void render(ItemStack stack, ItemDisplayContext mode, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
//        TimeCapsuleItemRenderer.INSTANCE.render(stack, mode, matrices, vertexConsumers, light, overlay);
//    }
//}
