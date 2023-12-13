package generations.gg.generations.core.generationscore.client.render;

import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository;
import generations.gg.generations.core.generationscore.client.render.rarecandy.PixelmonInstance;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public interface PixelmonInstanceProvider {
    public PixelmonInstance getInstance();
    void setInstance(PixelmonInstance instance);

    public ResourceLocation species();

    public Set<String> aspects();
}