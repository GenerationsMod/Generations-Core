package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.world.level.block.entities.CelestialAltarBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class CelestialAltarBlock extends GenericRotatableModelBlock<CelestialAltarBlockEntity> {

    public CelestialAltarBlock(BlockBehaviour.Properties properties) {
        super(properties, PokeModBlockEntities.CELESTIAL_ALTAR, PokeModBlockEntityModels.CELESTIAL_ALTAR);
    }
}
