package generations.gg.generations.core.generationscore.common.mixin.client;

import com.cobblemon.mod.common.client.gui.TypeIcon;
import com.cobblemon.mod.common.client.gui.pc.PCGUI;
import com.cobblemon.mod.common.client.gui.summary.widgets.ModelWidget;
import com.cobblemon.mod.common.pokemon.Pokemon;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.client.screen.TeraTypeIcon;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import generations.gg.generations.core.generationscore.common.client.screen.SpecialAspectIcon;
import java.util.Set;

@Mixin(PCGUI.class)
public abstract class PcGuiMixin extends Screen {
    @Shadow @Nullable private Pokemon previewPokemon;

    @Shadow @Final public static int BASE_WIDTH;

    @Shadow @Final public static int BASE_HEIGHT;

    protected PcGuiMixin(Component title) {
        super(title);
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void renderThings(GuiGraphics context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if(previewPokemon != null) {
            var x = (width - BASE_WIDTH) / 2;
            var y = (height - BASE_HEIGHT) / 2;

            new TeraTypeIcon(x + 6 + 1, y + 27 + 66 - 9 - 1, previewPokemon.getTeraType(), false, true, 1f).render(context);
			if (generations$hasSpecialAspect(previewPokemon)) {
				new SpecialAspectIcon(x + 62, y + 83, false, true, 1f).render(context);
			}
        }
    }
	@Unique
    private boolean generations$hasSpecialAspect(Pokemon pokemon) {
        Set<String> pokemonAspectsString = pokemon.getAspects();
        return pokemonAspectsString.contains("vintage") || pokemonAspectsString.contains("pastel") || pokemonAspectsString.contains("galaxy") || pokemonAspectsString.contains("sketch") || pokemonAspectsString.contains("shadow");
    }
}