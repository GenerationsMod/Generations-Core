package generations.gg.generations.core.generationscore.common.mixin;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.EncoderException;
import net.minecraft.network.Connection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Connection.class)
public class TeraPacketExceptionMixin {
    // CODE COPIED FROM MEGA SHOWDOWN MOD TO FIX CRASH
    @Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
    private void handleTeraPacketException(ChannelHandlerContext context, Throwable ex, CallbackInfo ci) {
        if (ex instanceof EncoderException) {
            String errorMessage = ex.getMessage();

            if (errorMessage != null && errorMessage.contains("Failed to encode packet") && errorMessage.contains("cobblemon:tera_type_update")) {
                System.out.println("[CobblemonMod] Suppressed Tera packet encoder exception: " + errorMessage);
                ci.cancel();
            }
        }
    }
}
