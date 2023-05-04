package generations.gg.generations.core.generationscore.world.level.block.decorations;

import com.pokemod.pokemod.world.level.block.PokeModVoxelShapes;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntityModels;
import com.pokemod.pokemod.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import com.pokemod.pokemod.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.RegistryObject;

public class LitwickCandleBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    public static final PokeModVoxelShapes.DirectionalShapes SHAPES = PokeModVoxelShapes.generateDirectionVoxelShape(Shapes.join(Shapes.box(0.3125, 0, 0.3125, 0.6875, 0.3125, 0.6875), Shapes.box(0.4375, 0.34375, 0.4375, 0.5625, 0.5, 0.5625), BooleanOp.OR), Direction.NORTH);

    public LitwickCandleBlock(Properties properties) {
        super(properties, PokeModBlockEntities.GENERIC_MODEL_PROVIDING, PokeModBlockEntityModels.LITWICK_CANDLE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.getShape(state);
    }
}
