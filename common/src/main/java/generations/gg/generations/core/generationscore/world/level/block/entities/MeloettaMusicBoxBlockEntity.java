package generations.gg.generations.core.generationscore.world.level.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class MeloettaMusicBoxBlockEntity extends ShrineBlockEntity {
    public MeloettaMusicBoxBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.MELOETTA_MUSIC_BOX.get(), pos, state);
    }
}
