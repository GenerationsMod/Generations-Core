package generations.gg.generations.core.generationscore.common.world.level.block;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.world.level.block.decorations.SittableBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericDyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class SwivelChairBlock extends DyeableBlock<GenericDyedVariantBlockEntity, SwivelChairBlock> implements SittableBlock {
    private final static GenerationsVoxelShapes.DirectionalShapes SHAPE = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(Shapes.box(0.125, 0, 0.1875, 0.875, 0.5, 0.875),
                    Shapes.join(Shapes.box(0.0625, 0, 0.125, 0.9375, 0.1875, 0.9375),
                            Shapes.join(Shapes.box(0.125, 0.4375, 0.125, 0.875, 1, 0.3125),
                                    Shapes.join(Shapes.box(0.125, 0.5625, 0.0625, 0.875, 1, 0.125),
                                            Shapes.box(0.125, 0.5625, 0.03125, 0.875, 1, 0.0625), OR), OR), OR), OR), Direction.SOUTH);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.getShape(state);
    }

    public SwivelChairBlock(DyeColor color, Map<DyeColor, RegistrySupplier<SwivelChairBlock>> function, BlockBehaviour.Properties props) {
        super(color, function, GenerationsBlockEntities.GENERIC_DYED_VARIANT, props, GenerationsBlockEntityModels.SWIVEL_CHAIR);
    }

    @Override
    public double getOffset() {
        return 0.4;
    }

    @Override
    protected InteractionResult serverUse(BlockState state, ServerLevel world, BlockPos pos, ServerPlayer player, InteractionHand handIn, BlockHitResult hit) {
        return SittableBlock.super.use(state, world, pos, player, handIn, hit);
    }
}
