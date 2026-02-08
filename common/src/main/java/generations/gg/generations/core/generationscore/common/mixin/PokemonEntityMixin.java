package generations.gg.generations.core.generationscore.common.mixin;

import com.cobblemon.mod.common.entity.PlatformType;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.util.PlayerExtensionsKt;
import generations.gg.generations.core.generationscore.common.network.packets.GensInteractPokemonUIPacket;
import generations.gg.generations.core.generationscore.common.util.PokemonEntityFunctionsKt;
import generations.gg.generations.core.generationscore.common.world.entity.PokemonInteractProxy;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
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

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void generations$customShoulderMega(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        PokemonEntity self = (PokemonEntity) (Object) this;

        if (hand == InteractionHand.MAIN_HAND && player instanceof ServerPlayer serverPlayer && self.getPokemon().getOwnerPlayer() == player) {
            if (player.isShiftKeyDown()) {
                ItemStack itemStack = player.getItemInHand(hand);

                boolean canMount = self.canSitOnShoulder() && PlayerExtensionsKt.party(serverPlayer).toGappyList().contains(self.getPokemon());

                boolean canGiveHeld = !(self.getPokemon().heldItem().isEmpty() && itemStack.isEmpty());

                var cosmeticItemDefinition = com.cobblemon.mod.common.CobblemonCosmeticItems.findValidCosmeticForPokemonAndItem(
                        serverPlayer.level().registryAccess(),
                        self.getPokemon(),
                        itemStack
                );

                boolean canGiveCosmetic =
                        (!self.getPokemon().getCosmeticItem().isEmpty() && itemStack.isEmpty())
                                || cosmeticItemDefinition != null;

                boolean canRide = self.ifRidingAvailableSupply(false, (behaviour, settings, state) -> {
                    if (self.getPlatform() != PlatformType.NONE) return false;
                    if (self.getTethering() != null) return false;
                    if (self.getSeats().isEmpty()) return false;
                    if ((self.getOwner() instanceof ServerPlayer sp) && PlayerExtensionsKt.isInBattle(serverPlayer)) return false;
                    if (self.getOwner() != serverPlayer && self.getPassengers().isEmpty()) return false;
                    return behaviour.isActive(settings, state, self);
                });

                boolean canChangeForm = PokemonEntityFunctionsKt.canChangeForm(self).getFirst();
                String aspect = PokemonEntityFunctionsKt.canChangeForm(self).getSecond();

                if (!aspect.isEmpty() && self.getAspects().contains(aspect)
                        || self.getAspects().contains("primal")
                        || self.getAspects().contains("ultra-fusion")) {
                    aspect = "revert";
                }

                Pair<Boolean, String> changeFormData = new Pair<>(canChangeForm, aspect);

                new GensInteractPokemonUIPacket(
                        self.getUUID(),
                        canMount,
                        canGiveHeld,
                        canGiveCosmetic,
                        canRide,
                        changeFormData
                ).sendToPlayer(serverPlayer);

                cir.setReturnValue(InteractionResult.SUCCESS);
                return;
            }

        }
    }
}
