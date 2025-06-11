package generations.gg.generations.core.generationscore.common.mixin.client;

import com.cobblemon.mod.common.client.gui.summary.Summary;
import com.cobblemon.mod.common.pokemon.Pokemon;
import generations.gg.generations.core.generationscore.common.client.screen.TeraTypeIcon;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Summary.class)
public class SummaryMixin extends Screen {
    @Shadow public Pokemon selectedPokemon;

    @Shadow @Final
    public static int BASE_WIDTH;

    @Shadow @Final public static int BASE_HEIGHT;

    protected SummaryMixin(Component title) {
        super(title);
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void renderThings(GuiGraphics context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if(selectedPokemon != null) {
            var x = (width - BASE_WIDTH) / 2;
            var y = (height - BASE_HEIGHT) / 2;

            new TeraTypeIcon(x + 6 + 1, y + 32 + 66 - 18 - 1, selectedPokemon.getTeraType(), false, false, 1f).render(context);
        }
    }
}