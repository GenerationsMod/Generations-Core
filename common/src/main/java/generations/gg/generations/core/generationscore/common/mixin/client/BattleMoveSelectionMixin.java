package generations.gg.generations.core.generationscore.common.mixin.client;

import com.cobblemon.mod.common.battles.ShowdownMoveset;
import com.cobblemon.mod.common.battles.pokemon.BattleMove;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleGimmickButton;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleMoveSelection;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(BattleMoveSelection.class)
public class BattleMoveSelectionMixin {

    @Redirect(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/cobblemon/mod/common/client/gui/battle/subscreen/BattleGimmickButton$Companion;create(Lcom/cobblemon/mod/common/battles/ShowdownMoveset$Gimmick;Lcom/cobblemon/mod/common/client/gui/battle/subscreen/BattleMoveSelection;FF)Lcom/cobblemon/mod/common/client/gui/battle/subscreen/BattleGimmickButton;"
            ),
            remap = false
    )
    private BattleGimmickButton redirectGimmickButtonCreate(
            ShowdownMoveset.Gimmick gimmick,
            BattleMoveSelection battleMoveSelection,
            float x,
            float y
    ) {
        if (gimmick == ShowdownMoveset.Gimmick.ULTRA_BURST) {
            return new BattleGimmickButton(gimmick, x, y) {
                @Override
                public java.util.List<BattleMoveSelection.MoveTile> getTiles() {
                    return battleMoveSelection.getBaseTiles().stream()
                            .map(tile -> new GimmickTile(gimmick, battleMoveSelection, tile.getMove(), tile.getX(), tile.getY()))
                            .collect(Collectors.toUnmodifiableList());
                }
            };
        }

        return BattleGimmickButton.Companion.create(gimmick, battleMoveSelection, x, y);
    }
}
