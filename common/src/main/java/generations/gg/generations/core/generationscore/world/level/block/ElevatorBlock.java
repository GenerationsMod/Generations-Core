package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

@SuppressWarnings("deprecation")
public class ElevatorBlock extends Block {
    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);

    public ElevatorBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(0.8f));
    }

    @Override
    public void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Entity entity) {
        if (entity instanceof ServerPlayer player && player.isShiftKeyDown())
            this.takeElevator(level, entity.blockPosition().below(), player, Direction.DOWN);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @NotNull BlockState updateShape(BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
        return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    public void takeElevator(BlockGetter world, BlockPos pos, ServerPlayer player, Direction direction) {
        findNearestElevator(world, pos, direction).map(a -> new ElevatorEvents.UseElevator(player, world, ElevatorBlock.this, direction, pos, a)).map(useElevator -> {
            ElevatorEvents.USE_ELEVATOR.invoker().accept(useElevator);
            return useElevator;
        }).map(ElevatorEvents.UseElevator::getDestination).filter(Objects::nonNull).map(BlockPos::above).ifPresent(blockPos -> {
            player.serverLevel().playSound(null, blockPos, SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS);
            player.teleportTo(player.position().x(), blockPos.getY() - 0.5, player.position().z());
        });
    }

    private Optional<BlockPos> findNearestElevator(BlockGetter world, BlockPos pos, Direction direction) {
        int range = GenerationsCore.CONFIG.blocks.elevatorRange;

        if(range < 3) return Optional.empty();

        for (int i = 2; i < range; i++) {
            var current = pos.relative(direction, i);

            if(world.getBlockState(current).getBlock() instanceof ElevatorBlock && world.getBlockState(current.above()).isAir() && world.getBlockState(current.above(2)).isAir())
                return Optional.of(current);
        }

        return Optional.empty();
    }

    public interface ElevatorEvents {
        Event<Consumer<UseElevator>> USE_ELEVATOR = EventFactory.createConsumerLoop();

        class UseElevator {
            private final ServerPlayer player;
            private final BlockGetter blockGetter;
            private final ElevatorBlock block;
            private final Direction direction;
            private final BlockPos origin;
            private final BlockPos defaultDestination;
            private BlockPos destination;

            UseElevator(ServerPlayer player, BlockGetter blockGetter, ElevatorBlock block, Direction direction, BlockPos origin, BlockPos defaultDestination) {
                this.player = player;
                this.blockGetter = blockGetter;
                this.block = block;
                this.direction = direction;
                this.origin = origin;
                this.defaultDestination = defaultDestination;
                this.destination = defaultDestination;
            }

            public BlockPos getDefaultDestination() {
                return defaultDestination;
            }

            public BlockPos getOrigin() {
                return origin;
            }

            public Direction getDirection() {
                return direction;
            }

            public ElevatorBlock getBlock() {
                return block;
            }

            public BlockGetter getBlockGetter() {
                return blockGetter;
            }

            public ServerPlayer getPlayer() {
                return player;
            }

            public void setDestination(BlockPos destination) {
                this.destination = destination;
            }

            public BlockPos getDestination() {
                return destination;
            }
        }
    }
}
