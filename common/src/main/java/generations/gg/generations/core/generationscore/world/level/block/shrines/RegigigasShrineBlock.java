package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.world.item.legends.RegiOrbItem;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.RegigigasShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class RegigigasShrineBlock extends InteractShrineBlock<RegigigasShrineBlockEntity> {
    private static final GenerationsVoxelShapes.DirectionalShapes SHAPE = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(Shapes.box(0, 0, 0, 1, 0.3125, 1),
                    Shapes.box(0.125, 0, 0.25, 0.875, 0.875, 0.75), OR));

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.getShape(state);
    }

    public RegigigasShrineBlock(BlockBehaviour.Properties materialIn) {
        super(materialIn, GenerationsBlockEntities.REGIGIGAS_SHRINE, GenerationsBlockEntityModels.REGIGIGAS_SHRINE, RegigigasShrineBlockEntity.class);
    }

    @Override
    public boolean isStackValid(ItemStack stack) {
        return stack.getItem() instanceof RegiOrbItem;
    }
}
