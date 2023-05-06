package generations.gg.generations.core.generationscore.world.level.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GenerationsVoxelShapes {
    public static VoxelShape rotateShape(Direction from, Direction to, VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{ shape, Shapes.empty() };
        int times = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.join(buffer[1], Shapes.box(1-maxZ, minY, minX, 1-minZ, maxY, maxX), BooleanOp.OR));
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }
        return buffer[0];
    }

    public static DirectionalShapes generateDirectionVoxelShape(VoxelShape shape, Direction source) {
        return new DirectionalShapes(rotateShape(source, Direction.NORTH, shape), rotateShape(source, Direction.EAST, shape), rotateShape(source, Direction.SOUTH, shape), rotateShape(source, Direction.WEST, shape));
    }

    public record DirectionalShapes(VoxelShape north, VoxelShape east, VoxelShape south, VoxelShape west) {
        public VoxelShape getShape(BlockState state) {
            return switch (state.getValue(HorizontalDirectionalBlock.FACING)) {
                case EAST -> east();
                case SOUTH -> south();
                case WEST -> west();
                default -> north();
            };
        }
    }
}
