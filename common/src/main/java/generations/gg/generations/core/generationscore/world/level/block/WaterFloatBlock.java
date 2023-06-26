package generations.gg.generations.core.generationscore.world.level.block;

import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.decorations.SittableBlock;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericDyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class WaterFloatBlock extends DyeableBlock<GenericDyedVariantBlockEntity, WaterFloatBlock> implements SittableBlock {
    public static VoxelShape AABB = Shapes.box(0, 0, 0, 1, 0.2, 1);

    public WaterFloatBlock(BlockBehaviour.Properties properties) {
        super(WaterFloatBlock::getBlock, GenerationsBlockEntities.GENERIC_DYED_VARIANT, properties, GenerationsBlockEntityModels.WATER_FLOAT, 0, 0, 1);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return AABB;
    }

    @Override
    public double getOffset() {
        return 0.04;
    }

    public static DyedBlockItem<WaterFloatBlock> getBlock(DyeColor dyeColor) {
        return (switch (dyeColor) {
            case BLACK -> GenerationsDecorationBlocks.BLACK_WATER_FLOAT;
            case BLUE -> GenerationsDecorationBlocks.BLUE_WATER_FLOAT;
            case BROWN -> GenerationsDecorationBlocks.BROWN_WATER_FLOAT;
            case CYAN -> GenerationsDecorationBlocks.CYAN_WATER_FLOAT;
            case GRAY -> GenerationsDecorationBlocks.GRAY_WATER_FLOAT;
            case GREEN -> GenerationsDecorationBlocks.GREEN_WATER_FLOAT;
            case LIGHT_BLUE -> GenerationsDecorationBlocks.LIGHT_BLUE_WATER_FLOAT;
            case LIGHT_GRAY -> GenerationsDecorationBlocks.LIGHT_GRAY_WATER_FLOAT;
            case LIME -> GenerationsDecorationBlocks.LIME_WATER_FLOAT;
            case MAGENTA -> GenerationsDecorationBlocks.MAGENTA_WATER_FLOAT;
            case ORANGE -> GenerationsDecorationBlocks.ORANGE_WATER_FLOAT;
            case PINK -> GenerationsDecorationBlocks.PINK_WATER_FLOAT;
            case PURPLE -> GenerationsDecorationBlocks.PURPLE_WATER_FLOAT;
            case RED -> GenerationsDecorationBlocks.RED_WATER_FLOAT;
            case WHITE -> GenerationsDecorationBlocks.WHITE_WATER_FLOAT;
            case YELLOW -> GenerationsDecorationBlocks.YELLOW_WATER_FLOAT;
        }).get();
    }

    @Override
    protected InteractionResult serverUse(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        return SittableBlock.super.use(state, world, pos, player, handIn, hit);
    }
}
