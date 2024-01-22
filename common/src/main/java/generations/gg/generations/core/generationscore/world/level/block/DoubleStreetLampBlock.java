package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.MutableBlockEntityType;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;

public class DoubleStreetLampBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    private static final BiFunction<BlockPos, BlockState, BlockPos> DEFAULT_BLOCK_ROTATE_POS_FUNCTION = (pos, state) -> {
        if(state.getBlock() instanceof GenericRotatableModelBlock<?> block) {
            var facing = state.getValue(FACING);
            var x = block.getWidthValue(state) - 1;
            var y = block.getHeightValue(state);
            var z = block.getLengthValue(state);

            return pos.relative(facing.getClockWise(), x).relative(Direction.DOWN, y).relative(facing, z);
        }

        return pos;
    };

    protected DoubleStreetLampBlock(Properties materialIn) {
        super(materialIn, GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, DEFAULT_BLOCK_ROTATE_POS_FUNCTION, GenerationsBlockEntityModels.DOUBLE_STREET_LAMP, 2, 5, 0);
    }

}
