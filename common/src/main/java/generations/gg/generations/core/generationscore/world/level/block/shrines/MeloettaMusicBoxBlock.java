package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.world.level.block.entities.MeloettaMusicBoxBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MeloettaMusicBoxBlock extends ShrineBlock<MeloettaMusicBoxBlockEntity> {
    private static final VoxelShape SHAPE = Shapes.box(0.25f, 0f, 0.25f, 0.75f, 0.375f, 0.75f);
    public MeloettaMusicBoxBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.MELOETTA_MUSIC_BOX, GenerationsBlockEntityModels.MELOETTA_MUSIC_BOX);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
