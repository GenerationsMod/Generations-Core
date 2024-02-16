package generations.gg.generations.core.generationscore.world.level.block.decorations;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeDollBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Math;

import java.util.List;

@SuppressWarnings({"deprecation"})
public class PokeDollBlock extends GenericModelBlock<PokeDollBlockEntity> {
    public static final EnumProperty<Cardinal> CARDINAL = EnumProperty.create("cardinal", Cardinal.class);

    public final String name;
    private final boolean shiny;
    private final float scale;
    private final String variant;

    public PokeDollBlock(String name, boolean shiny, float scale) {
        super(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(1.0f), GenerationsBlockEntities.POKE_DOLL, GenerationsCore.id("models/block/pokedolls/" + name + ".pk"));
        this.name = name;
        this.shiny = shiny;
        this.scale = scale;
        this.variant = shiny ? "shiny" : "regular";
    }

    public static float calculateAngle(Vec3 source, Vec3 target) {
        return (float) Math.toDegrees(Math.atan2((target.x - source.x), (target.z - source.z)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CARDINAL).add(WATERLOGGED);
    }

    @Override
    public @NotNull BlockState mirror(BlockState state, @NotNull Mirror mirrorIn) {
        return state.setValue(CARDINAL, state.getValue(CARDINAL).mirror(mirrorIn));
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, @NotNull Rotation rot) {
        return state.setValue(CARDINAL, state.getValue(CARDINAL).rotation(rot));
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (!world.isClientSide() && player.isShiftKeyDown())
            world.setBlockAndUpdate(pos, state.setValue(CARDINAL, Cardinal.values()[(state.getValue(CARDINAL).ordinal() + 1) % 8]));
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        Cardinal cardinal;
        if (context.getPlayer() != null) {
            cardinal = Cardinal.getFromAngle((context.getRotation() - 180) % 360);
        } else {
            cardinal = Cardinal.NORTH;
        }
        return this.defaultBlockState().setValue(CARDINAL, cardinal);
    }

    public String getVariant() {
        return variant;
    }

    public float getScale() {
        return scale;
    }

    public boolean isShiny() {
        return shiny;
    }

    public enum Cardinal implements StringRepresentable {
        SOUTH(0, "south", new Vec3i(0, 0, 1), Direction.NORTH),
        SOUTH_WEST(45, "south_west", new Vec3i(-1, 0, 1), Direction.NORTH, Direction.EAST),
        WEST(90, "west", new Vec3i(-1, 0, 0), Direction.EAST),
        NORTH_WEST(135, "north_west", new Vec3i(-1, 0, -1), Direction.SOUTH, Direction.EAST),
        NORTH(180, "north", new Vec3i(0, 0, -1), Direction.SOUTH),
        NORTH_EAST(225, "north_east", new Vec3i(1, 0, -1), Direction.SOUTH, Direction.WEST),
        EAST(270, "east", new Vec3i(1, 0, 0), Direction.WEST),
        SOUTH_EAST(315, "south_east", new Vec3i(1, 0, 1), Direction.NORTH, Direction.WEST),
        NONE(-1, "none", new Vec3i(0, 0, 0)); //This one can be ignored for rotating. It is just filler for multiblock stuff.

        private final int angle;
        private final String name;
        private final Vec3i offset;
        private final List<Direction> directions;

        Cardinal(int angle, String name, Vec3i offset, Direction... directions) {
            this.angle = angle;
            this.name = name;
            this.offset = offset;
            this.directions = List.of(directions);
        }

        public static Cardinal getFromAngle(float angle) {
            return Cardinal.values()[Mth.floor(angle / 45.0 + 0.5) & 7];
        }

        public static Cardinal fromDirection(Direction direction) {
            return switch (direction) {
                case NORTH -> NORTH;
                case EAST -> EAST;
                case SOUTH -> SOUTH;
                case WEST -> WEST;
                default -> NONE;
            };
        }

        public Cardinal mirror(Mirror mirror) {
            if(this == NONE) return this;
            if (mirror == Mirror.NONE) {
                return Cardinal.values()[(this.ordinal() + 4) % 8];
            } else {
                return this;
            }
        }

        public Cardinal rotation(Rotation rotation) {
            if(this == NONE) return this;

            return switch (rotation) {
                case NONE -> this;
                case CLOCKWISE_90 -> Cardinal.values()[(this.ordinal() + 2) % 8];
                case CLOCKWISE_180 -> Cardinal.values()[(this.ordinal() + 4) % 8];
                case COUNTERCLOCKWISE_90 -> Cardinal.values()[(this.ordinal() + 6) % 8];
            };
        }

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }

        public float getAngle() {
            return angle;
        }

        public Vec3i offset() {
            return offset;
        }

        public List<Direction> getAnchorDirections() {
            return directions;
        }
    }
}