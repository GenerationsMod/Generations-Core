package generations.gg.generations.core.generationscore.common.mixin;

import com.cobblemon.mod.common.api.riding.behaviour.RidingBehaviour;
import com.cobblemon.mod.common.api.riding.behaviour.RidingBehaviourSettings;
import com.cobblemon.mod.common.api.riding.behaviour.RidingBehaviourState;
import com.cobblemon.mod.common.entity.PlatformType;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import generations.gg.generations.core.generationscore.common.network.packets.GensInteractPokemonUIPacket;
import generations.gg.generations.core.generationscore.common.util.PokemonEntityFunctionsKt;
import generations.gg.generations.core.generationscore.common.world.entity.PokemonInteractProxy;
import kotlin.jvm.functions.Function3;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import kotlin.Pair;

@Mixin(PokemonEntity.class)
public abstract class PokemonEntityMixin {

    @Inject(method = "showInteractionWheel", at = @At("HEAD"), cancellable = true)
    private void showInteractionWheel(ServerPlayer player, ItemStack itemStack, CallbackInfo ci) {
       PokemonInteractProxy.showInteractWeheel((PokemonEntity) (Object) this, player, itemStack);
       ci.cancel();
    }


//    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
//    private void generations$customShoulderMega(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
//        PokemonEntity self = (PokemonEntity) (Object) this;
//
//        if (hand == InteractionHand.MAIN_HAND && player instanceof ServerPlayer serverPlayer && self.getPokemon().getOwnerPlayer() == player) {
//            if (player.isShiftKeyDown()) {
//                boolean canMount = self.canSitOnShoulder();
//                boolean canChangeForm = PokemonEntityFunctionsKt.canChangeForm(self).getFirst();
//                String aspect = PokemonEntityFunctionsKt.canChangeForm(self).getSecond();
//
//                System.out.println(self.getAspects());
//                if (!aspect.isEmpty() && self.getAspects().contains(aspect) || self.getAspects().contains("primal") || self.getAspects().contains("ultra-fusion")) {
//                    aspect = "revert";
//                }
//
//                Pair<Boolean, String> changeFormData = new Pair<>(canChangeForm, aspect);
//
//                new GensInteractPokemonUIPacket(self.getUUID(), canMount, changeFormData).sendToPlayer(serverPlayer);
//                cir.setReturnValue(InteractionResult.SUCCESS);
//                return;
//            } else {
//                if (((PokemonEntityAccessor) self).invokeAttemptItemInteraction(serverPlayer, player.getItemInHand(hand))) {
//                    cir.setReturnValue(InteractionResult.SUCCESS);
//                    return;
//                }
//            }
//        }
//    }
//

}
