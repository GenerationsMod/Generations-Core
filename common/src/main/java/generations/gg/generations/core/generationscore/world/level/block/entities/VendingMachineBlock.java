package generations.gg.generations.core.generationscore.world.level.block.entities;

import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.PokeModDecorationBlocks;
import generations.gg.generations.core.generationscore.world.level.block.PokeModVoxelShapes;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DoubleDyeableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class VendingMachineBlock extends DoubleDyeableBlock<VendingMachineBlockEntity, VendingMachineBlock> {
    public static PokeModVoxelShapes.DirectionalShapes LOWER = PokeModVoxelShapes.generateDirectionVoxelShape(Shapes.join(Shapes.join(Shapes.join(Shapes.box(0.0625, 0, 0.15625, 0.9375, 0.0625, 0.875), Shapes.box(0, 0.026875, 0.04999999999999999, 1, 1, 0.9375), BooleanOp.OR), Shapes.box(0.14687499999999998, 0.21875, 0, 0.865625, 0.546875, 0.0625), BooleanOp.OR), Shapes.box(0.15000000000000002, 0.671875, 0.01874999999999999, 0.86875, 1, 0.08124999999999999), BooleanOp.OR), Direction.SOUTH);
    public static PokeModVoxelShapes.DirectionalShapes UPPER = PokeModVoxelShapes.generateDirectionVoxelShape(Shapes.join(Shapes.join(Shapes.box(0.0625, 0, 0.15625, 0.9375, 0.0625, 0.875), Shapes.box(0, 0, 0.04999999999999999, 1, 1, 0.9375), BooleanOp.OR), Shapes.box(0.15000000000000002, 0, 0.01874999999999999, 0.86875, 0.84375, 0.08124999999999999), BooleanOp.OR), Direction.SOUTH);

    public VendingMachineBlock(BlockBehaviour.Properties properties) {
        super(VendingMachineBlock::getBlock, PokeModBlockEntities.VENDING_MACHINE, properties, PokeModBlockEntityModels.VENDING_MACHINE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.is(this) ? (getHeightValue(state) == 1 ? UPPER : LOWER).getShape(state) : Shapes.block();
    }

    @Override
    protected InteractionResult serverUse(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
//        var graph = new DialogueGraph(new OpenShopNode(null)); //TODO: Shopes
//        new DialoguePlayer(graph, null, (ServerPlayer) player, false);
        return InteractionResult.SUCCESS;
    }

    public static DyedBlockItem<VendingMachineBlock> getBlock(DyeColor dyeColor) {
        return (switch (dyeColor) {
            case BLACK -> PokeModDecorationBlocks.BLACK_VENDING_MACHINE;
            case BLUE -> PokeModDecorationBlocks.BLUE_VENDING_MACHINE;
            case BROWN -> PokeModDecorationBlocks.BROWN_VENDING_MACHINE;
            case CYAN -> PokeModDecorationBlocks.CYAN_VENDING_MACHINE;
            case GRAY -> PokeModDecorationBlocks.GRAY_VENDING_MACHINE;
            case GREEN -> PokeModDecorationBlocks.GREEN_VENDING_MACHINE;
            case LIGHT_BLUE -> PokeModDecorationBlocks.LIGHT_BLUE_VENDING_MACHINE;
            case LIGHT_GRAY -> PokeModDecorationBlocks.LIGHT_GRAY_VENDING_MACHINE;
            case LIME -> PokeModDecorationBlocks.LIME_VENDING_MACHINE;
            case MAGENTA -> PokeModDecorationBlocks.MAGENTA_VENDING_MACHINE;
            case ORANGE -> PokeModDecorationBlocks.ORANGE_VENDING_MACHINE;
            case PINK -> PokeModDecorationBlocks.PINK_VENDING_MACHINE;
            case PURPLE -> PokeModDecorationBlocks.PURPLE_VENDING_MACHINE;
            case RED -> PokeModDecorationBlocks.RED_VENDING_MACHINE;
            case WHITE -> PokeModDecorationBlocks.WHITE_VENDING_MACHINE;
            case YELLOW -> PokeModDecorationBlocks.YELLOW_VENDING_MACHINE;
        }).get();
    }
}
