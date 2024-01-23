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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class DoubleStreetLampBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    private static BlockPos getBase(BlockPos pos, BlockState state) {
        if(state.getBlock() instanceof GenericRotatableModelBlock<?> block) {
            var facing = state.getValue(FACING);
            var x = processWidth(block.getWidthValue(state));
            var y = block.getHeightValue(state);
            var z = block.getLengthValue(state);

            return pos.relative(facing.getClockWise(), x).relative(Direction.DOWN, y).relative(facing, z);
        }

        return pos;
    }

    private static int processWidth(int widthValue) {
        return switch (widthValue) {
            case 0 -> -1;
            case 1 -> 0;
            case 2 -> 1;
            default -> throw new IllegalStateException("Unexpected value: " + widthValue);
        };
    }

    ;

    protected DoubleStreetLampBlock(Properties materialIn) {
        super(materialIn, GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, DoubleStreetLampBlock::getBase, GenerationsBlockEntityModels.DOUBLE_STREET_LAMP, 2, 4, 0);
    }

    protected BlockState createDefaultState() {
        return setSize(super.createDefaultState().setValue(FACING, Direction.NORTH), 1, 0, 0);
    }


    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        var facing = state.getValue(FACING);
        var rightDir = facing.getCounterClockWise();

        for (int x = -1; x <= 1; x++) {
            for (int y = 0; y <= height; y++) {
                if((x == 0 && y == 0) || (y <= 2 && x != 0)) continue;
                var blockPos = pos.relative(rightDir, x).relative(Direction.UP, y);
                level.setBlock(blockPos, setSize(state.setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER), x, y, 0), 2);
            }
        }
    }

    public Stream<BlockPos> getEncompassingPositions(BlockPos pos, Direction dir) {
        var list = new ArrayList<BlockPos>();
        for (int x = -1; x <= 1; x++) {
            for (int y = 0; y <= height; y++) {
                if ((y <= 2 && x != 0)) continue;
                list.add(pos.relative(dir.getCounterClockWise(), x).relative(Direction.UP, y));
            }
        }

        return list.stream();
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var facing = state.getValue(FACING);
        var x = getWidthValue(state);
        var y = getHeightValue(state);
        var z = getLengthValue(state);

        var rightDir = facing.getClockWise();

//        if(x == 0 && y == 0 && z == 0) {
//            return needsSupport(level, pos.below()); //TODO: Do we want the blocks to require solid foundation?
//        }

        return checkDirection(level, pos, rightDir, Size.WIDTH, x) &&
                (x != 0 && y > 2 ? checkDirection(level, pos, Direction.DOWN, Size.HEIGHT, 3, y) : checkDirection(level, pos, Direction.DOWN, Size.HEIGHT, y));
    }

}
