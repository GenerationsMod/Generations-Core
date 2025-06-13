package generations.gg.generations.core.generationscore.fabric.mixin;

import com.cobblemon.mod.common.api.events.CobblemonEvents;
import generations.gg.generations.core.generationscore.common.api.events.general.InteractionEvents;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class MixinPlayer {
    @Inject(method = "interactOn", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Player;getItemInHand(Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/item/ItemStack;",
            ordinal = 0),
            cancellable = true)
    private void entityInteract(Entity entity, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
        var result = InteractionEvents.fireEntityInteract((Player) (Object) this, entity, interactionHand);
        if(result != InteractionResult.PASS) cir.setReturnValue(result);
    }
}
