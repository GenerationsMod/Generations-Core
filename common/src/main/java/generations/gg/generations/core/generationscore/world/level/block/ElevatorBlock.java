package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;

import java.util.Optional;
import java.util.function.Consumer;

public class ElevatorBlock extends Block {

    public ElevatorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {
        if (entity instanceof Player player && player.isShiftKeyDown()) {
            this.takeElevator(level, entity.blockPosition().below(), player, Direction.DOWN);
        }
    }

    public void takeElevator(BlockGetter world, BlockPos pos, Player player, Direction direction) {
        findNearestElevator(world, pos, direction).map(a -> ElevatorEvents.USE_ELEVATOR.invoker().useElevator(player, world, this, direction, a)).ifPresent(blockPos -> player.teleportTo(player.position().x, blockPos.getY() + 0.5, player.position().z));
    }

    private Optional<BlockPos> findNearestElevator(BlockGetter world, BlockPos pos, Direction direction) {
        int searchRangeY = 15; //TODO add range

        if(searchRangeY < 3) return Optional.empty();

        for (int i = 2; i < searchRangeY; i++) {
            var current = pos.relative(direction, i);

            if(world.getBlockState(current).getBlock() instanceof ElevatorBlock && world.getBlockState(current.above()).isAir() && world.getBlockState(current.above(2)).isAir()) {
                return Optional.of(current);
            }
        }

        return Optional.empty();
    }

    public static interface ElevatorEvents {
        public static final Event<UseElevator> USE_ELEVATOR = EventFactory.createLoop();

        public interface UseElevator {
            public BlockPos useElevator(Player player, BlockGetter blockGetter, ElevatorBlock block, Direction direction, BlockPos pos);
        }
    }
}
