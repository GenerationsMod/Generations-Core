package generations.gg.generations.core.generationscore.world.level.block.decorations;

import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsDecorationBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericDyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static generations.gg.generations.core.generationscore.world.level.block.decorations.UmbrellaBlock.Pole.*;

@SuppressWarnings("deprecation")
public class UmbrellaBlock extends DyeableBlock<GenericDyedVariantBlockEntity, UmbrellaBlock> {
    private static final Map<Pole, Map<PokeDollBlock.Cardinal, VoxelShape>> SHAPES = Util.make(new HashMap<>(), map -> {
        //Mid
        var current = map.computeIfAbsent(MID, a -> new HashMap<>());
        current.put(PokeDollBlock.Cardinal.NONE, Shapes.join(Shapes.join(Shapes.box(0.46875, 0, 0.46875, 0.53125, 1, 0.53125), Shapes.box(0.45625, 0.5625, 0.45625, 0.54375, 1, 0.54375), BooleanOp.OR), Shapes.box(0, 0.921875, 0, 1, 1, 1), BooleanOp.OR));

        var mid_corner = Shapes.box(0.3843749999999999, 0.921875, 0.3843749999999999, 1, 1, 1);
        var mid_side = Shapes.box(0.009374999999999911, 0.921875, 0.3843749999999999, 1, 1, 1);
        var fromCorner = Direction.WEST;
        var fromSide = Direction.NORTH;

        current.put(PokeDollBlock.Cardinal.NORTH, createShape(mid_side, fromSide, Direction.NORTH));
        current.put(PokeDollBlock.Cardinal.NORTH_EAST, createShape(mid_corner, fromCorner, Direction.NORTH));
        current.put(PokeDollBlock.Cardinal.EAST, createShape(mid_side, fromSide, Direction.EAST));
        current.put(PokeDollBlock.Cardinal.SOUTH_EAST, createShape(mid_corner, fromCorner, Direction.EAST));
        current.put(PokeDollBlock.Cardinal.SOUTH, createShape(mid_side, fromSide, Direction.SOUTH));
        current.put(PokeDollBlock.Cardinal.SOUTH_WEST, createShape(mid_corner, fromCorner, Direction.SOUTH));
        current.put(PokeDollBlock.Cardinal.WEST, createShape(mid_side, fromSide, Direction.WEST));
        current.put(PokeDollBlock.Cardinal.NORTH_WEST, createShape(mid_corner, fromCorner, Direction.WEST));


        //Top
        current = map.computeIfAbsent(TOP, a -> new HashMap<>());
        current.put(PokeDollBlock.Cardinal.NONE, Shapes.join(Shapes.join(Shapes.box(0.46875, 0.3687499999999999, 0.46875, 0.53125, 0.3812500000000001, 0.53125), Shapes.box(0.0625, 0.28125, 0.0625, 0.9375, 0.3687499999999999, 0.9375), BooleanOp.OR), Shapes.box(0, 0, 0, 1, 0.3, 1), BooleanOp.OR));

        mid_corner = Shapes.join(Shapes.box(0.625, 0.14374999999999982, 0.625, 1, 0.2999999999999998, 1), Shapes.box(0.5, 0, 0.5, 1, 0.14374999999999982, 1), BooleanOp.OR);
        mid_side = Shapes.join(Shapes.box(0, 0.14374999999999982, 0.625, 1, 0.2999999999999998, 1), Shapes.box(0, 0, 0.5, 1, 0.14374999999999982, 1), BooleanOp.OR);

        current.put(PokeDollBlock.Cardinal.NORTH, createShape(mid_side, fromSide, Direction.NORTH));
        current.put(PokeDollBlock.Cardinal.NORTH_EAST, createShape(mid_corner, fromCorner, Direction.NORTH));
        current.put(PokeDollBlock.Cardinal.EAST, createShape(mid_side, fromSide, Direction.EAST));
        current.put(PokeDollBlock.Cardinal.SOUTH_EAST, createShape(mid_corner, fromCorner, Direction.EAST));
        current.put(PokeDollBlock.Cardinal.SOUTH, createShape(mid_side, fromSide, Direction.SOUTH));
        current.put(PokeDollBlock.Cardinal.SOUTH_WEST, createShape(mid_corner, fromCorner, Direction.SOUTH));
        current.put(PokeDollBlock.Cardinal.WEST, createShape(mid_side, fromSide, Direction.WEST));
        current.put(PokeDollBlock.Cardinal.NORTH_WEST, createShape(mid_corner, fromCorner, Direction.WEST));
    });

    private static VoxelShape createShape(VoxelShape shape, Direction from, Direction to) {
        return GenerationsVoxelShapes.rotateShape(from, to, shape);
    }
    
    private static final VoxelShape BASE_SHAPE = Shapes.join(Shapes.join(Shapes.box(0.375, 0.046875, 0.375, 0.625, 0.084375, 0.625), Shapes.box(0.46875, 0.0625, 0.46875, 0.53125, 1, 0.53125), BooleanOp.OR), Shapes.box(0.35, 0, 0.35, 0.65, 0.046875, 0.65), BooleanOp.OR);

    public static EnumProperty<PokeDollBlock.Cardinal> CARDINAL = EnumProperty.create("cardinal", PokeDollBlock.Cardinal.class);
    public static EnumProperty<Pole> POLE = EnumProperty.create("pole", Pole.class);
    public static BiFunction<BlockPos, BlockState, BlockPos> FUNCTION = (pos, state) -> pos.offset(state.getValue(CARDINAL).rotation(Rotation.CLOCKWISE_180).offset()).below(state.getValue(POLE).ordinal());

    public UmbrellaBlock(BlockBehaviour.Properties props) {
        super(UmbrellaBlock::getBlock, GenerationsBlockEntities.GENERIC_DYED_VARIANT, FUNCTION, props, GenerationsBlockEntityModels.UMBRELLA);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return state.getValue(POLE) == BASE ? BASE_SHAPE : SHAPES.get(state.getValue(POLE)).get(state.getValue(CARDINAL));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();

        if(pos.getY() < level.getMaxBuildHeight() - 2 && isAreaClear(level, null, pos)) {
            return this.defaultBlockState();
        } else {
            return null;
        }
    }

    @Override
    protected boolean isAreaClear(Level level, Direction dir, BlockPos pos) {
        var isFree = level.getBlockState(pos).canBeReplaced();

        for (int i = 1; i < 3; i++) {
            var blockPos = pos.above(i);
            isFree = isFree && Stream.of(PokeDollBlock.Cardinal.values())
                    .map(PokeDollBlock.Cardinal::offset)
                    .map(blockPos::offset)
                    .map(level::getBlockState)
                    .allMatch(BlockState::canBeReplaced);
        }

        return isFree;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        setLayer(level, this, pos = pos.above(), MID);
        setLayer(level, this, pos.above(), Pole.TOP);
    }

    private void setLayer(Level level, Block block, BlockPos pos, Pole pole) {
        var state = createDefaultState().setValue(POLE, pole);
        level.setBlock(pos, state.setValue(CARDINAL, PokeDollBlock.Cardinal.NONE).setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER), 2);

        if(pole != Pole.BASE) {
            level.setBlock(pos = pos.north(), state.setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER).setValue(CARDINAL, PokeDollBlock.Cardinal.NORTH), 2);
            level.setBlock(pos = pos.east(), state.setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER).setValue(CARDINAL, PokeDollBlock.Cardinal.NORTH_EAST), 2);
            level.setBlock(pos = pos.south(), state.setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER).setValue(CARDINAL, PokeDollBlock.Cardinal.EAST), 2);
            level.setBlock(pos = pos.south(), state.setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER).setValue(CARDINAL, PokeDollBlock.Cardinal.SOUTH_EAST), 2);
            level.setBlock(pos = pos.west(), state.setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER).setValue(CARDINAL, PokeDollBlock.Cardinal.SOUTH), 2);
            level.setBlock(pos = pos.west(), state.setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER).setValue(CARDINAL, PokeDollBlock.Cardinal.SOUTH_WEST), 2);
            level.setBlock(pos = pos.north(), state.setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER).setValue(CARDINAL, PokeDollBlock.Cardinal.WEST), 2);
            level.setBlock(pos.north(), state.setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER).setValue(CARDINAL, PokeDollBlock.Cardinal.NORTH_WEST), 2);
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var pole = state.getValue(POLE);
        var cardinal = state.getValue(CARDINAL);

        if (pole == TOP && level.getBlockState(pos.below()).is(this) && level.getBlockState(pos.below()).getValue(POLE) == MID) {
            return cardinal.getAnchorDirections().stream().map(pos::relative).map(level::getBlockState).allMatch(a -> a.is(this));
        } else if (pole == MID) {
            if(cardinal == PokeDollBlock.Cardinal.NONE) return level.getBlockState(pos.below()).is(this) && level.getBlockState(pos.below()).getValue(POLE) == BASE;
            else return cardinal.getAnchorDirections().stream().map(pos::relative).map(level::getBlockState).allMatch(a -> a.is(this));
        }

        if(pole == BASE) {
            return canSupportRigidBlock(level, pos.below());
        }

        return false;
    }

    @Override
    public @NotNull BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if(canSurvive(state, level, currentPos)) return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
        else return Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean canRender(Level level, BlockPos blockPos, BlockState blockState) {
        return blockState.getValue(UmbrellaBlock.POLE) == Pole.BASE;
    }

    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CARDINAL, POLE, WATERLOGGED);
    }

    @Override
    protected BlockState createDefaultState() {
        return this.getStateDefinition().any().setValue(WATERLOGGED, false)
                .setValue(CARDINAL, PokeDollBlock.Cardinal.NONE)
                .setValue(POLE, Pole.BASE);
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation direction) {
        return state;
    }

    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state;
    }

    @Override
    public float getAngle(BlockState state) {
        return 0;
    }

    public static DyedBlockItem<UmbrellaBlock> getBlock(DyeColor dyeColor) {
        return (switch (dyeColor) {
            case BLACK -> GenerationsDecorationBlocks.BLACK_UMBRELLA;
            case BLUE -> GenerationsDecorationBlocks.BLUE_UMBRELLA;
            case BROWN -> GenerationsDecorationBlocks.BROWN_UMBRELLA;
            case CYAN -> GenerationsDecorationBlocks.CYAN_UMBRELLA;
            case GRAY -> GenerationsDecorationBlocks.GRAY_UMBRELLA;
            case GREEN -> GenerationsDecorationBlocks.GREEN_UMBRELLA;
            case LIGHT_BLUE -> GenerationsDecorationBlocks.LIGHT_BLUE_UMBRELLA;
            case LIGHT_GRAY -> GenerationsDecorationBlocks.LIGHT_GRAY_UMBRELLA;
            case LIME -> GenerationsDecorationBlocks.LIME_UMBRELLA;
            case MAGENTA -> GenerationsDecorationBlocks.MAGENTA_UMBRELLA;
            case ORANGE -> GenerationsDecorationBlocks.ORANGE_UMBRELLA;
            case PINK -> GenerationsDecorationBlocks.PINK_UMBRELLA;
            case PURPLE -> GenerationsDecorationBlocks.PURPLE_UMBRELLA;
            case RED -> GenerationsDecorationBlocks.RED_UMBRELLA;
            case WHITE -> GenerationsDecorationBlocks.WHITE_UMBRELLA;
            case YELLOW -> GenerationsDecorationBlocks.YELLOW_UMBRELLA;
        }).get();
    }

    @Override
    public AABB computeRenderBoundingBox(Level level, BlockPos pos, BlockState state) {
        return new AABB(pos.offset(-1, 0, -1), pos.offset(2, 2, 2));
    }

    public enum Pole implements StringRepresentable {
        BASE("base"),
        MID("mid"),
        TOP("top");

        private final String name;

        Pole(String name) {
            this.name = name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }
    }
}
