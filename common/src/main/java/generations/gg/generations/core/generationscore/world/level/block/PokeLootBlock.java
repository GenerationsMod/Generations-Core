package generations.gg.generations.core.generationscore.world.level.block;

import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeLootBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;

public class PokeLootBlock extends GenericRotatableModelBlock<PokeLootBlockEntity> {
    private final String name;

    protected PokeLootBlock(String name, Properties properties) {
        super(properties, GenerationsBlockEntities.POKE_LOOT, GenerationsBlockEntityModels.POKEBALL);
        this.name = name;
    }

    public String getType() {
        return name;
    }
}
