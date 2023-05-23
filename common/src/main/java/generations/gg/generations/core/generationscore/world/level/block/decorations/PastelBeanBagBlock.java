package generations.gg.generations.core.generationscore.world.level.block.decorations;

import generations.gg.generations.core.generationscore.world.entity.block.SittableEntity;
import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsDecorationBlocks;
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

@SuppressWarnings("deprecation")
public class PastelBeanBagBlock extends DyeableBlock<GenericDyedVariantBlockEntity, PastelBeanBagBlock> {
    public PastelBeanBagBlock(BlockBehaviour.Properties props) {
        super(PastelBeanBagBlock::getBlock, GenerationsBlockEntities.GENERIC_DYED_VARIANT, props, GenerationsBlockEntityModels.PASTEL_BEAN_BAG);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Shapes.box(0.1, 0.0, 0.1, 0.9, 0.5, 0.9);
    }

    @Override
    protected InteractionResult serverUse(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!world.isClientSide && !player.isShiftKeyDown())
            return SittableEntity.mount(world, pos, 0.5f, player);

        return InteractionResult.PASS;
    }

    public static DyedBlockItem<PastelBeanBagBlock> getBlock(DyeColor dyeColor) {
        return (switch (dyeColor) {
            case WHITE -> GenerationsDecorationBlocks.WHITE_PASTEL_BEAN_BAG;
            case LIGHT_GRAY -> GenerationsDecorationBlocks.LIGHT_GRAY_PASTEL_BEAN_BAG;
            case GRAY -> GenerationsDecorationBlocks.GRAY_PASTEL_BEAN_BAG;
            case BLACK -> GenerationsDecorationBlocks.BLACK_PASTEL_BEAN_BAG;
            case BROWN -> GenerationsDecorationBlocks.BROWN_PASTEL_BEAN_BAG;
            case RED -> GenerationsDecorationBlocks.RED_PASTEL_BEAN_BAG;
            case ORANGE -> GenerationsDecorationBlocks.ORANGE_PASTEL_BEAN_BAG;
            case YELLOW -> GenerationsDecorationBlocks.YELLOW_PASTEL_BEAN_BAG;
            case LIME -> GenerationsDecorationBlocks.LIME_PASTEL_BEAN_BAG;
            case GREEN -> GenerationsDecorationBlocks.GREEN_PASTEL_BEAN_BAG;
            case CYAN -> GenerationsDecorationBlocks.CYAN_PASTEL_BEAN_BAG;
            case LIGHT_BLUE -> GenerationsDecorationBlocks.LIGHT_BLUE_PASTEL_BEAN_BAG;
            case BLUE -> GenerationsDecorationBlocks.BLUE_PASTEL_BEAN_BAG;
            case PURPLE -> GenerationsDecorationBlocks.PURPLE_PASTEL_BEAN_BAG;
            case MAGENTA -> GenerationsDecorationBlocks.MAGENTA_PASTEL_BEAN_BAG;
            case PINK -> GenerationsDecorationBlocks.PINK_PASTEL_BEAN_BAG;
        }).get();
    }
}
