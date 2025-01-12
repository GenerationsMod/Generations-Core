package generations.gg.generations.core.generationscore.common.mixin;

import generations.gg.generations.core.generationscore.common.world.entity.TieredFishingHookEntity;
import generations.gg.generations.core.generationscore.common.world.item.TieredFishingRodItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingHook.class)
public class FishingHookMixin {
    //    //TODO: This is some jank to get over minecraft's <redacted> hardcoding of fishing rods. Supposedly won't be required in 1.21.
    @Inject(method = "shouldStopFishing", at = @At("HEAD"), cancellable = true)
    public void shouldGenerationsFishingRodContinue(Player player, CallbackInfoReturnable<Boolean> cir) {
        if(((FishingHook) (Object) this) instanceof TieredFishingHookEntity bobber) {

            ItemStack itemStack = player.getMainHandItem();
            ItemStack itemStack2 = player.getOffhandItem();

            boolean bl = itemStack.getItem() instanceof TieredFishingRodItem item && item.getTier() == bobber.getTier();
            boolean bl2 = itemStack2.getItem() instanceof TieredFishingRodItem item && item.getTier() == bobber.getTier();
            if (!player.isRemoved() && player.isAlive() && (bl || bl2) && !(bobber.distanceToSqr(player) > 1024.0)) {
                cir.setReturnValue(false);
            } else {
                bobber.discard();
                cir.setReturnValue(true);
            }
        }
    }
}
