package generations.gg.generations.core.generationscore.common.world.level.block.shrines;

import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.common.world.item.legends.TaoTrioStoneItem;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.TaoTrioShrineBlockEntity;
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.common.world.item.legends.TaoTrioStoneItem;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.TaoTrioShrineBlockEntity;
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.common.world.item.legends.TaoTrioStoneItem;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.TaoTrioShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class TaoTrioShrineBlock extends InteractShrineBlock<TaoTrioShrineBlockEntity> {
    private static final GenerationsVoxelShapes.DirectionalShapes SHAPE = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(Shapes.box(0, 0, 0, 1, 0.15625, 1),
                    Shapes.join(Shapes.box(0.09375, 0.15625, 0.21875, 0.90625, 0.25625, 0.90625),
                            Shapes.join(Shapes.box(0.0625, 0.15625, 0.34375, 0.9375, 1, 0.53125),
                                    Shapes.box(0.09375, 0.15625, 0.21875, 0.90625, 0.94375, 0.46875), OR), OR), OR), Direction.SOUTH);



    public TaoTrioShrineBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.TAO_TRIO_SHRINE, GenerationsBlockEntityModels.TAO_TRIO_SHRINE);
    }

    @Override
    protected boolean activate(Level level, BlockPos pos, BlockState state, ServerPlayer player, InteractionHand hand, ActivationState activationState) {
        var stack = player.getItemInHand(hand);
        PokemonUtil.spawn(((TaoTrioStoneItem) stack.getItem()).getSpecies().createProperties(70), level, player.getOnPos());
        stack.shrink(1);
        return true;
    }

    @Override
    public boolean isStackValid(ItemStack stack) {
        return stack.getItem() instanceof TaoTrioStoneItem && stack.getDamageValue() >= stack.getMaxDamage();
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE.getShape(state);
    }
}
