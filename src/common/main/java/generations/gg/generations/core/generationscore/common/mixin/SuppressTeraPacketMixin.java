package generations.gg.generations.core.generationscore.common.mixin;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.Connection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import generations.gg.generations.core.generationscore.common.GenerationsCore;

@Mixin(Connection.class)
public class SuppressTeraPacketMixin {
    @Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
    private void suppressTeraCrash(ChannelHandlerContext context, Throwable ex, CallbackInfo ci) {
        if (ex.getMessage() != null && ex.getMessage().contains("tera_type_update")) {
            GenerationsCore.LOGGER.warn("Suppressed encoder crash from tera_type_update: {}", ex.getMessage());
            ci.cancel();
        }
    }
}