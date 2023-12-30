package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.world.item.legends.CreationTrioItem;
import generations.gg.generations.core.generationscore.world.item.legends.RedChainItem;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.altar.TimeSpaceAltarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class TimespaceAltarBlock extends InteractShrineBlock<TimeSpaceAltarBlockEntity> {
    private static final GenerationsVoxelShapes.DirectionalShapes SHAPE = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(Shapes.box(0, 0, 0.1875, 1, 0.125, 0.75),
                    Shapes.join(Shapes.box(0, 0.125, 0.3125, 1, 0.1875, 0.6875),
                            Shapes.box(0.03749999999999998, 0.1875, 0.3125, 0.975, 0.89375, 0.625), OR), OR));

    public TimespaceAltarBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.TIMESPACE_ALTAR, GenerationsBlockEntityModels.TIME_SPACE_ALTAR, TimeSpaceAltarBlockEntity.class);
    }

    @Override
    public boolean isStackValid(ItemStack stack) {
        return stack.getItem() instanceof RedChainItem || stack.getItem() instanceof CreationTrioItem;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.getShape(state);
    }
}
