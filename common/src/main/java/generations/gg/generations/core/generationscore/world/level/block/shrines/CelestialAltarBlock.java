package generations.gg.generations.core.generationscore.world.level.block.shrines;

import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntityModels;
import com.pokemod.pokemod.world.level.block.generic.GenericRotatableModelBlock;
import com.pokemod.pokemod.world.level.block.entities.CelestialAltarBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class CelestialAltarBlock extends GenericRotatableModelBlock<CelestialAltarBlockEntity> {

    public CelestialAltarBlock(BlockBehaviour.Properties properties) {
        super(properties, PokeModBlockEntities.CELESTIAL_ALTAR, PokeModBlockEntityModels.CELESTIAL_ALTAR);
    }
}
