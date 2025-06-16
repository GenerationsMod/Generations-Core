package generations.gg.generations.core.generationscore.common.mixin.client;

import com.cobblemon.mod.common.battles.BattleRegistry;
import com.cobblemon.mod.common.client.CobblemonClient;
import com.cobblemon.mod.common.client.battle.ClientBattle;
import com.cobblemon.mod.common.client.net.battle.BattleTransformPokemonHandler;
import com.cobblemon.mod.common.net.messages.client.battle.BattleTransformPokemonPacket;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(BattleTransformPokemonHandler.class)
public class BattleTransformPokemonHandlerMixin {
    @Inject(method = "handle", at = @At("TAIL"))
    private void transformMixinTest (BattleTransformPokemonPacket packet, Minecraft client, CallbackInfo ci) {
        System.out.println("[Tera DEBUG] Received BattleTransformPokemonPacket -> " +
                "PNX: " + packet.getPnx() +
                ", IsAlly: " + packet.isAlly() +
                ", UUID: " + packet.getUpdatedPokemon().getUuid() +
                ", Name: " + packet.getUpdatedPokemon().getDisplayName()
        );

        ClientBattle battle = CobblemonClient.INSTANCE.getBattle();
        if (battle == null) return;

        var pair = battle.getPokemonFromPNX(packet.getPnx());
        var activeBattlePokemon = pair.getSecond();
    }
}
