package generations.gg.generations.core.generationscore.common.mixin;

import com.cobblemon.mod.common.api.pokedex.PokedexEntryProgress;
import com.cobblemon.mod.common.client.battle.ActiveClientBattlePokemon;
import com.cobblemon.mod.common.client.battle.ClientBallDisplay;
import com.cobblemon.mod.common.client.gui.battle.BattleOverlay;
import com.cobblemon.mod.common.client.render.models.blockbench.PosableState;
import com.cobblemon.mod.common.pokemon.Gender;
import com.cobblemon.mod.common.pokemon.Species;
import com.cobblemon.mod.common.pokemon.status.PersistentStatus;
import generations.gg.generations.core.generationscore.common.client.BattleOverlayProxy;
import gg.generations.rarecandy.shaded.commons.lang3.tuple.Triple;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BattleOverlay.class)
public abstract class BattleOverlayMixin {

//    @Shadow public abstract float getPassedSeconds(); TODO: In a future version, figure out how to get TeraType to be availabel to display.
//
//    @Shadow public abstract double getOpacity();
//
//    @Inject(
//            method = "drawTile",
//            at = @At("RETURN")
//    )
//    private void onDrawBattleTilePost(GuiGraphics context, float tickDelta, ActiveClientBattlePokemon activeBattlePokemon, boolean left, int rank, PokedexEntryProgress dexState, boolean hasCommand, boolean isHovered, boolean isCompact, CallbackInfo ci
//    ) {
//        BattleOverlayProxy.render(context, tickDelta, activeBattlePokemon, left, rank, dexState, hasCommand, isHovered, isCompact, getPassedSeconds(), getOpacity());
//    }
}