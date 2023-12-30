package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.world.item.legends.TaoTrioStoneItem;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.TaoTrioShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class TaoTrioShrineBlock extends InteractShrineBlock<TaoTrioShrineBlockEntity> {
    private static final GenerationsVoxelShapes.DirectionalShapes SHAPE = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(Shapes.box(0, 0, 0, 1, 0.15625, 1),
                    Shapes.join(Shapes.box(0.09375, 0.15625, 0.21875, 0.90625, 0.25625, 0.90625),
                            Shapes.join(Shapes.box(0.0625, 0.15625, 0.34375, 0.9375, 1, 0.53125),
                                    Shapes.box(0.09375, 0.15625, 0.21875, 0.90625, 0.94375, 0.46875), OR), OR), OR), Direction.SOUTH);



    public TaoTrioShrineBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.TAO_TRIO_SHRINE, GenerationsBlockEntityModels.TAO_TRIO_SHRINE, TaoTrioShrineBlockEntity.class);
    }

    @Override
    public boolean isStackValid(ItemStack stack) {
        return stack.getItem() instanceof TaoTrioStoneItem && stack.getDamageValue() >= stack.getMaxDamage();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.getShape(state);
    }
}
