package generations.gg.generations.core.generationscore.mixin.client;

import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import generations.gg.generations.core.generationscore.client.model.RareCandyBone;
import generations.gg.generations.core.generationscore.client.render.PixelmonInstanceProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.PixelmonInstance;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;

import java.util.HashSet;
import java.util.Set;

@Mixin(PokemonEntity.class)
public abstract class PokemonEntityMixin implements PixelmonInstanceProvider {
    private PixelmonInstance instance;
    private ResourceLocation currentSpecies = new ResourceLocation("");
    private Set<String> currentAspects = new HashSet<>();

    public PixelmonInstance getInstance() {
        if(!currentSpecies.equals(species()) || !currentAspects.equals(aspects())) instance = null;
        if (instance == null) {
            var model = PokemonModelRepository.INSTANCE.getPoser(species(), aspects()).getRootPart() instanceof RareCandyBone bone ? bone.compiledModel() : null;
            if(model != null && model.isReady()) {
                instance = new PixelmonInstance(model.renderObject, new Matrix4f(), null);
                currentSpecies = species();
                currentAspects = aspects();
            }
        }

        return instance;
    }

    private PokemonEntity self() {
        return (PokemonEntity) (Object) this;
    }

    @Override
    public void setInstance(PixelmonInstance instance) {
        this.instance = instance;
    }

    @Override
    public Set<String> aspects() {
        return self().getPokemon().getAspects();
    }

    @Override
    public ResourceLocation species() {
        return self().getPokemon().getSpecies().getResourceIdentifier();
    }
}