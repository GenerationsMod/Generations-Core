package generations.gg.generations.core.generationscore.client.render;

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.pokemon.FormData;
import generations.gg.generations.core.generationscore.client.render.rarecandy.GenerationsObjectInstance;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public interface PixelmonInstanceProvider {
    GenerationsObjectInstance getInstance();
    void setInstance(GenerationsObjectInstance instance);

    ResourceLocation species();

    Set<String> aspects();

    default FormData getFormData() {
        return PokemonSpecies.INSTANCE.getByIdentifier(species()).getForm(aspects());
    }
}