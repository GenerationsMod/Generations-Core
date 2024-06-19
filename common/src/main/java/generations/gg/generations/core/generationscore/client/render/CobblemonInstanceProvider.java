package generations.gg.generations.core.generationscore.client.render;

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.pokemon.FormData;
import generations.gg.generations.core.generationscore.client.render.rarecandy.CobblemonInstance;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public interface CobblemonInstanceProvider {
    CobblemonInstance getInstance();
    void setInstance(CobblemonInstance instance);

    ResourceLocation species();

    Set<String> aspects();

    default FormData getFormData() {
        return PokemonSpecies.INSTANCE.getByIdentifier(species()).getForm(aspects());
    }
}