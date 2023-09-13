package generations.gg.generations.core.generationscore.world.level.block.entities;

import generations.gg.generations.core.generationscore.world.dialogue.DialogueGraph;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.OpenShopNode;
import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsDecorationBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DoubleDyeableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
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
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class VendingMachineBlock extends DoubleDyeableBlock<VendingMachineBlockEntity, VendingMachineBlock> {
    public static GenerationsVoxelShapes.DirectionalShapes LOWER = GenerationsVoxelShapes.generateDirectionVoxelShape(Shapes.join(Shapes.join(Shapes.join(Shapes.box(0.0625, 0, 0.15625, 0.9375, 0.0625, 0.875), Shapes.box(0, 0.026875, 0.04999999999999999, 1, 1, 0.9375), BooleanOp.OR), Shapes.box(0.14687499999999998, 0.21875, 0, 0.865625, 0.546875, 0.0625), BooleanOp.OR), Shapes.box(0.15000000000000002, 0.671875, 0.01874999999999999, 0.86875, 1, 0.08124999999999999), BooleanOp.OR), Direction.SOUTH);
    public static GenerationsVoxelShapes.DirectionalShapes UPPER = GenerationsVoxelShapes.generateDirectionVoxelShape(Shapes.join(Shapes.join(Shapes.box(0.0625, 0, 0.15625, 0.9375, 0.0625, 0.875), Shapes.box(0, 0, 0.04999999999999999, 1, 1, 0.9375), BooleanOp.OR), Shapes.box(0.15000000000000002, 0, 0.01874999999999999, 0.86875, 0.84375, 0.08124999999999999), BooleanOp.OR), Direction.SOUTH);

    public VendingMachineBlock(BlockBehaviour.Properties properties) {
        super(VendingMachineBlock::getBlock, GenerationsBlockEntities.VENDING_MACHINE, properties, GenerationsBlockEntityModels.VENDING_MACHINE);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.is(this) ? (getHeightValue(state) == 1 ? UPPER : LOWER).getShape(state) : Shapes.block();
    }

    @Override
    protected InteractionResult serverUse(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        var graph = new DialogueGraph(new OpenShopNode(null));
        new DialoguePlayer(graph, null, (ServerPlayer) player, false);
        return InteractionResult.SUCCESS;
    }

    public static DyedBlockItem<VendingMachineBlock> getBlock(DyeColor dyeColor) {
        return (switch (dyeColor) {
            case BLACK -> GenerationsDecorationBlocks.BLACK_VENDING_MACHINE;
            case BLUE -> GenerationsDecorationBlocks.BLUE_VENDING_MACHINE;
            case BROWN -> GenerationsDecorationBlocks.BROWN_VENDING_MACHINE;
            case CYAN -> GenerationsDecorationBlocks.CYAN_VENDING_MACHINE;
            case GRAY -> GenerationsDecorationBlocks.GRAY_VENDING_MACHINE;
            case GREEN -> GenerationsDecorationBlocks.GREEN_VENDING_MACHINE;
            case LIGHT_BLUE -> GenerationsDecorationBlocks.LIGHT_BLUE_VENDING_MACHINE;
            case LIGHT_GRAY -> GenerationsDecorationBlocks.LIGHT_GRAY_VENDING_MACHINE;
            case LIME -> GenerationsDecorationBlocks.LIME_VENDING_MACHINE;
            case MAGENTA -> GenerationsDecorationBlocks.MAGENTA_VENDING_MACHINE;
            case ORANGE -> GenerationsDecorationBlocks.ORANGE_VENDING_MACHINE;
            case PINK -> GenerationsDecorationBlocks.PINK_VENDING_MACHINE;
            case PURPLE -> GenerationsDecorationBlocks.PURPLE_VENDING_MACHINE;
            case RED -> GenerationsDecorationBlocks.RED_VENDING_MACHINE;
            case WHITE -> GenerationsDecorationBlocks.WHITE_VENDING_MACHINE;
            case YELLOW -> GenerationsDecorationBlocks.YELLOW_VENDING_MACHINE;
        }).get();
    }
}
