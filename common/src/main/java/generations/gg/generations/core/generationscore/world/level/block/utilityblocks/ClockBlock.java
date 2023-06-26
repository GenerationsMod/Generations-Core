package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsUtilityBlocks;
import generations.gg.generations.core.generationscore.world.level.block.entities.ClockBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class ClockBlock extends DyeableBlock<ClockBlockEntity, ClockBlock> {
    private static final Map<Direction, VoxelShape> SHAPE = Util.make(new HashMap<>(), map -> {
        map.put(Direction.SOUTH, Shapes.box(0.25, 0.25, 0, 0.75, 0.75, 0.0625));
        map.put(Direction.EAST, Shapes.box(0, 0.25, 0.25, 0.0625, 0.75, 0.75));
        map.put(Direction.NORTH, Shapes.box(0.25, 0.25, 0.9375, 0.75, 0.75, 1));
        map.put(Direction.WEST, Shapes.box(0.9375, 0.25, 0.25, 1, 0.75, 0.75));
    });

    public ClockBlock(Properties arg) {
        super(ClockBlock::getBlock, GenerationsBlockEntities.CLOCK, arg, GenerationsBlockEntityModels.CLOCK);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos blockPos = pos.relative(direction.getOpposite());
        BlockState blockState = level.getBlockState(blockPos);
        return blockState.isFaceSturdy(level, blockPos, direction);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.get(state.getValue(FACING));
    }

    public static DyedBlockItem<ClockBlock> getBlock(DyeColor color) {
        return (switch (color) {
            case WHITE -> GenerationsUtilityBlocks.WHITE_CLOCK;
            case ORANGE -> GenerationsUtilityBlocks.ORANGE_CLOCK;
            case MAGENTA -> GenerationsUtilityBlocks.MAGENTA_CLOCK;
            case LIGHT_BLUE -> GenerationsUtilityBlocks.LIGHT_BLUE_CLOCK;
            case YELLOW -> GenerationsUtilityBlocks.YELLOW_CLOCK;
            case LIME -> GenerationsUtilityBlocks.LIME_CLOCK;
            case PINK -> GenerationsUtilityBlocks.PINK_CLOCK;
            case GRAY -> GenerationsUtilityBlocks.GRAY_CLOCK;
            case LIGHT_GRAY -> GenerationsUtilityBlocks.LIGHT_GRAY_CLOCK;
            case CYAN -> GenerationsUtilityBlocks.CYAN_CLOCK;
            case PURPLE -> GenerationsUtilityBlocks.PURPLE_CLOCK;
            case BLUE -> GenerationsUtilityBlocks.BLUE_CLOCK;
            case BROWN -> GenerationsUtilityBlocks.BROWN_CLOCK;
            case GREEN -> GenerationsUtilityBlocks.GREEN_CLOCK;
            case RED -> GenerationsUtilityBlocks.RED_CLOCK;
            case BLACK -> GenerationsUtilityBlocks.BLACK_CLOCK;
        }).get();
    }
}

