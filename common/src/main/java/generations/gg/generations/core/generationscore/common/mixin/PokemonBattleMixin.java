package generations.gg.generations.core.generationscore.common.mixin;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature;
import com.cobblemon.mod.common.api.tags.CobblemonItemTags;
import com.cobblemon.mod.common.battles.BattleFormat;
import com.cobblemon.mod.common.battles.BattleSide;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import generations.gg.generations.core.generationscore.common.battle.BattleConditionsProcessor;
import generations.gg.generations.core.generationscore.common.battle.GenerationsInstructionProcessor;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.function.Consumer;
import java.util.stream.Stream;

@Mixin(PokemonBattle.class)
public abstract class PokemonBattleMixin {

    @Shadow public abstract void setMute(boolean b);

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    private void onConstruct(BattleFormat format, BattleSide side1, BattleSide side2, CallbackInfo ci) {
//        this.setMute(false);
    }

    @Inject(method = "end", at = @At("TAIL"), remap = false)
    private void injectEnd(CallbackInfo ci) {
        BattleConditionsProcessor.processBattleEnd((PokemonBattle) (Object) this);
        GenerationsInstructionProcessor.processBattleEnd((PokemonBattle) (Object) this);
    }

    @ModifyArgs(
            method = "end",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/cobblemon/mod/common/api/pokemon/experience/ExperienceCalculator;calculate(Lcom/cobblemon/mod/common/battles/pokemon/BattlePokemon;Lcom/cobblemon/mod/common/battles/pokemon/BattlePokemon;D)I"
            ),
            remap = false
    )
    private void interceptExpShareArgs(Args args) {
        BattlePokemon opponent = args.get(0);

        ServerPlayer player = opponent.getEffectedPokemon().getOwnerPlayer();

        boolean hasExpAll = false;
        if (player != null) {
            hasExpAll = player.getInventory().items.stream()
                    .anyMatch(item -> item.is(GenerationsItems.INSTANCE.getEXP_ALL())) ||
                    player.getOffhandItem().is(GenerationsItems.INSTANCE.getEXP_ALL());
        }

        boolean hasExpShare = opponent.getEffectedPokemon().heldItemNoCopy$common().is(CobblemonItemTags.EXPERIENCE_SHARE);

        if (hasExpAll && hasExpShare) {
            args.set(2, -1.0D);
            System.out.println("neutralized xp gain from xp share");
        }
    }
}