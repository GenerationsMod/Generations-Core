package generations.gg.generations.core.generationscore.world.level.block.decorations;

import com.pokemod.pokemod.world.level.block.PokeModVoxelShapes;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import com.pokemod.pokemod.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntityModels;
import com.pokemod.pokemod.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

@SuppressWarnings("deprecation")
public class SwitchBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    private final static PokeModVoxelShapes.DirectionalShapes SHAPES = PokeModVoxelShapes.generateDirectionVoxelShape(Shapes.join(Shapes.join(Shapes.empty(), Shapes.box(0.15625, 0, 0.375, 0.875, 0.41875, 0.58125), BooleanOp.OR), Shapes.box(0, 0.084375, 0.46875, 1.015625, 0.521875, 0.53125), BooleanOp.OR), Direction.SOUTH);

    public SwitchBlock(BlockBehaviour.Properties props) {
        super(props, PokeModBlockEntities.GENERIC_MODEL_PROVIDING, PokeModBlockEntityModels.SWITCH);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.getShape(state);
    }
}