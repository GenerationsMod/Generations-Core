package generations.gg.generations.core.generationscore.world.level.block.decorations;

import com.pokemod.pokemod.world.item.DyedBlockItem;
import com.pokemod.pokemod.world.level.block.PokeModDecorationBlocks;
import com.pokemod.pokemod.world.level.block.PokeModVoxelShapes;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntityModels;
import com.pokemod.pokemod.world.level.block.entities.generic.GenericDyedVariantBlockEntity;
import com.pokemod.pokemod.world.level.block.utilityblocks.DyeableBlock;
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

import static com.pokemod.pokemod.world.level.block.decorations.UmbrellaBlock.Pole.*;
import static generations.gg.generations.core.generationscore.world.level.block.decorations.UmbrellaBlock.Pole.*;

@SuppressWarnings("deprecation")
public class UmbrellaBlock extends DyeableBlock<GenericDyedVariantBlockEntity, UmbrellaBlock> {
    private static final Map<Pole, Map<PokeModDollBlock.Cardinal, VoxelShape>> SHAPES = Util.make(new HashMap<>(), map -> {
        //Mid
        var current = map.computeIfAbsent(MID, a -> new HashMap<>());
        current.put(PokeModDollBlock.Cardinal.NONE, Shapes.join(Shapes.join(Shapes.box(0.46875, 0, 0.46875, 0.53125, 1, 0.53125), Shapes.box(0.45625, 0.5625, 0.45625, 0.54375, 1, 0.54375), BooleanOp.OR), Shapes.box(0, 0.921875, 0, 1, 1, 1), BooleanOp.OR));

        var mid_corner = Shapes.box(0.3843749999999999, 0.921875, 0.3843749999999999, 1, 1, 1);
        var mid_side = Shapes.box(0.009374999999999911, 0.921875, 0.3843749999999999, 1, 1, 1);
        var fromCorner = Direction.WEST;
        var fromSide = Direction.NORTH;

        current.put(PokeModDollBlock.Cardinal.NORTH, createShape(mid_side, fromSide, Direction.NORTH));
        current.put(PokeModDollBlock.Cardinal.NORTH_EAST, createShape(mid_corner, fromCorner, Direction.NORTH));
        current.put(PokeModDollBlock.Cardinal.EAST, createShape(mid_side, fromSide, Direction.EAST));
        current.put(PokeModDollBlock.Cardinal.SOUTH_EAST, createShape(mid_corner, fromCorner, Direction.EAST));
        current.put(PokeModDollBlock.Cardinal.SOUTH, createShape(mid_side, fromSide, Direction.SOUTH));
        current.put(PokeModDollBlock.Cardinal.SOUTH_WEST, createShape(mid_corner, fromCorner, Direction.SOUTH));
        current.put(PokeModDollBlock.Cardinal.WEST, createShape(mid_side, fromSide, Direction.WEST));
        current.put(PokeModDollBlock.Cardinal.NORTH_WEST, createShape(mid_corner, fromCorner, Direction.WEST));


        //Top
        current = map.computeIfAbsent(TOP, a -> new HashMap<>());
        current.put(PokeModDollBlock.Cardinal.NONE, Shapes.join(Shapes.join(Shapes.box(0.46875, 0.3687499999999999, 0.46875, 0.53125, 0.3812500000000001, 0.53125), Shapes.box(0.0625, 0.28125, 0.0625, 0.9375, 0.3687499999999999, 0.9375), BooleanOp.OR), Shapes.box(0, 0, 0, 1, 0.3, 1), BooleanOp.OR));

        mid_corner = Shapes.join(Shapes.box(0.625, 0.14374999999999982, 0.625, 1, 0.2999999999999998, 1), Shapes.box(0.5, 0, 0.5, 1, 0.14374999999999982, 1), BooleanOp.OR);
        mid_side = Shapes.join(Shapes.box(0, 0.14374999999999982, 0.625, 1, 0.2999999999999998, 1), Shapes.box(0, 0, 0.5, 1, 0.14374999999999982, 1), BooleanOp.OR);

        current.put(PokeModDollBlock.Cardinal.NORTH, createShape(mid_side, fromSide, Direction.NORTH));
        current.put(PokeModDollBlock.Cardinal.NORTH_EAST, createShape(mid_corner, fromCorner, Direction.NORTH));
        current.put(PokeModDollBlock.Cardinal.EAST, createShape(mid_side, fromSide, Direction.EAST));
        current.put(PokeModDollBlock.Cardinal.SOUTH_EAST, createShape(mid_corner, fromCorner, Direction.EAST));
        current.put(PokeModDollBlock.Cardinal.SOUTH, createShape(mid_side, fromSide, Direction.SOUTH));
        current.put(PokeModDollBlock.Cardinal.SOUTH_WEST, createShape(mid_corner, fromCorner, Direction.SOUTH));
        current.put(PokeModDollBlock.Cardinal.WEST, createShape(mid_side, fromSide, Direction.WEST));
        current.put(PokeModDollBlock.Cardinal.NORTH_WEST, createShape(mid_corner, fromCorner, Direction.WEST));
    });

    private static VoxelShape createShape(VoxelShape shape, Direction from, Direction to) {
        return PokeModVoxelShapes.rotateShape(from, to, shape);
    }
    
    private static final VoxelShape BASE_SHAPE = Shapes.join(Shapes.join(Shapes.box(0.375, 0.046875, 0.375, 0.625, 0.084375, 0.625), Shapes.box(0.46875, 0.0625, 0.46875, 0.53125, 1, 0.53125), BooleanOp.OR), Shapes.box(0.35, 0, 0.35, 0.65, 0.046875, 0.65), BooleanOp.OR);

    public static EnumProperty<PokeModDollBlock.Cardinal> CARDINAL = EnumProperty.create("cardinal", PokeModDollBlock.Cardinal.class);
    public static EnumProperty<Pole> POLE = EnumProperty.create("pole", Pole.class);
    public static BiFunction<BlockPos, BlockState, BlockPos> FUNCTION = (pos, state) -> pos.offset(state.getValue(CARDINAL).rotation(Rotation.CLOCKWISE_180).offset()).below(state.getValue(POLE).ordinal());

    public UmbrellaBlock(BlockBehaviour.Properties props) {
        super(UmbrellaBlock::getBlock, PokeModBlockEntities.GENERIC_DYED_VARIANT, FUNCTION, props, PokeModBlockEntityModels.UMBRELLA);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return state.getValue(POLE) == BASE ? BASE_SHAPE : SHAPES.get(state.getValue(POLE)).get(state.getValue(CARDINAL));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();

        if(pos.getY() < level.getMaxBuildHeight() - 2 && isAreaClear(level, pos)) {
            return this.defaultBlockState();
        } else {
            return null;
        }
    }

    @Override
    protected boolean isAreaClear(Level level, BlockPos pos) {
        var isFree = level.getBlockState(pos).canBeReplaced();

        for (int i = 1; i < 3; i++) {
            var blockPos = pos.above(i);
            isFree = isFree && Stream.of(PokeModDollBlock.Cardinal.values())
                    .map(PokeModDollBlock.Cardinal::offset)
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
        var state = block.defaultBlockState().setValue(POLE, pole);
        level.setBlock(pos, state.setValue(CARDINAL, PokeModDollBlock.Cardinal.NONE), 2);

        if(pole != Pole.BASE) {
            level.setBlock(pos = pos.north(), state.setValue(CARDINAL, PokeModDollBlock.Cardinal.NORTH), 2);
            level.setBlock(pos = pos.east(), state.setValue(CARDINAL, PokeModDollBlock.Cardinal.NORTH_EAST), 2);
            level.setBlock(pos = pos.south(), state.setValue(CARDINAL, PokeModDollBlock.Cardinal.EAST), 2);
            level.setBlock(pos = pos.south(), state.setValue(CARDINAL, PokeModDollBlock.Cardinal.SOUTH_EAST), 2);
            level.setBlock(pos = pos.west(), state.setValue(CARDINAL, PokeModDollBlock.Cardinal.SOUTH), 2);
            level.setBlock(pos = pos.west(), state.setValue(CARDINAL, PokeModDollBlock.Cardinal.SOUTH_WEST), 2);
            level.setBlock(pos = pos.north(), state.setValue(CARDINAL, PokeModDollBlock.Cardinal.WEST), 2);
            level.setBlock(pos.north(), state.setValue(CARDINAL, PokeModDollBlock.Cardinal.NORTH_WEST), 2);
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var pole = state.getValue(POLE);
        var cardinal = state.getValue(CARDINAL);

        if (pole == TOP && level.getBlockState(pos.below()).is(this) && level.getBlockState(pos.below()).getValue(POLE) == MID) {
            return cardinal.getAnchorDirections().stream().map(pos::relative).map(level::getBlockState).allMatch(a -> a.is(this));
        } else if (pole == MID) {
            if(cardinal == PokeModDollBlock.Cardinal.NONE) return level.getBlockState(pos.below()).is(this) && level.getBlockState(pos.below()).getValue(POLE) == BASE;
            else return cardinal.getAnchorDirections().stream().map(pos::relative).map(level::getBlockState).allMatch(a -> a.is(this));
        }

        if(pole == BASE) {
            return canSupportRigidBlock(level, pos.below());
        }

        return false;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if(canSurvive(state, level, currentPos)) return state;
        else return Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean canRender(Level level, BlockPos blockPos, BlockState blockState) {
        return blockState.getValue(UmbrellaBlock.POLE) == Pole.BASE;
    }

    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CARDINAL, POLE);
    }

    @Override
    protected BlockState createDefaultState() {
        return this.getStateDefinition().any()
                .setValue(CARDINAL, PokeModDollBlock.Cardinal.NONE)
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
            case BLACK -> PokeModDecorationBlocks.BLACK_UMBRELLA;
            case BLUE -> PokeModDecorationBlocks.BLUE_UMBRELLA;
            case BROWN -> PokeModDecorationBlocks.BROWN_UMBRELLA;
            case CYAN -> PokeModDecorationBlocks.CYAN_UMBRELLA;
            case GRAY -> PokeModDecorationBlocks.GRAY_UMBRELLA;
            case GREEN -> PokeModDecorationBlocks.GREEN_UMBRELLA;
            case LIGHT_BLUE -> PokeModDecorationBlocks.LIGHT_BLUE_UMBRELLA;
            case LIGHT_GRAY -> PokeModDecorationBlocks.LIGHT_GRAY_UMBRELLA;
            case LIME -> PokeModDecorationBlocks.LIME_UMBRELLA;
            case MAGENTA -> PokeModDecorationBlocks.MAGENTA_UMBRELLA;
            case ORANGE -> PokeModDecorationBlocks.ORANGE_UMBRELLA;
            case PINK -> PokeModDecorationBlocks.PINK_UMBRELLA;
            case PURPLE -> PokeModDecorationBlocks.PURPLE_UMBRELLA;
            case RED -> PokeModDecorationBlocks.RED_UMBRELLA;
            case WHITE -> PokeModDecorationBlocks.WHITE_UMBRELLA;
            case YELLOW -> PokeModDecorationBlocks.YELLOW_UMBRELLA;
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
