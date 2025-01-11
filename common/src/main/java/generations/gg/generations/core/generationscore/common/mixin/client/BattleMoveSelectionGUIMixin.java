package generations.gg.generations.core.generationscore.common.mixin.client;

import com.cobblemon.mod.common.api.moves.MoveTemplate;
import com.cobblemon.mod.common.api.moves.categories.DamageCategories;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleMoveSelection.MoveTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.contents.LiteralContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import javax.script.ScriptException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Mixin(MoveTile.class)
public abstract class BattleMoveSelectionGUIMixin {

    @Shadow
    public abstract boolean isHovered(double mouseX, double mouseY);

    @Shadow
    private MoveTemplate moveTemplate;

    @Inject(method = "render", at = @At(value = "TAIL"))
    public void tooltipRenderMixin(GuiGraphics context, int mouseX, int mouseY, float delta, CallbackInfo ci) throws ScriptException, IOException {
        if (this.isHovered(mouseX, mouseY)) {
            List<Component> tooltipInfo = new ArrayList<>();

            List<FormattedText> description = Minecraft.getInstance().font.getSplitter().splitLines(
                    moveTemplate.getDescription(), 250, Style.EMPTY.withColor(moveTemplate.getElementalType().getHue()));

            for (FormattedText formattedText : description) {
                tooltipInfo.add(MutableComponent.create(new LiteralContents(formattedText.getString())).withStyle(ChatFormatting.GRAY));
            }

            context.renderComponentTooltip(Minecraft.getInstance().font, tooltipInfo, mouseX, mouseY);
        }
    }
}