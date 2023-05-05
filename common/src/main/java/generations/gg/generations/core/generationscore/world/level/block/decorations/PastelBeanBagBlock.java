package generations.gg.generations.core.generationscore.world.level.block.decorations;

import generations.gg.generations.core.generationscore.world.entity.block.SittableEntity;
import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.PokeModDecorationBlocks;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntityModels;
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
        super(PastelBeanBagBlock::getBlock, PokeModBlockEntities.GENERIC_DYED_VARIANT, props, PokeModBlockEntityModels.PASTEL_BEAN_BAG);
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
            case WHITE -> PokeModDecorationBlocks.WHITE_PASTEL_BEAN_BAG;
            case LIGHT_GRAY -> PokeModDecorationBlocks.LIGHT_GRAY_PASTEL_BEAN_BAG;
            case GRAY -> PokeModDecorationBlocks.GRAY_PASTEL_BEAN_BAG;
            case BLACK -> PokeModDecorationBlocks.BLACK_PASTEL_BEAN_BAG;
            case BROWN -> PokeModDecorationBlocks.BROWN_PASTEL_BEAN_BAG;
            case RED -> PokeModDecorationBlocks.RED_PASTEL_BEAN_BAG;
            case ORANGE -> PokeModDecorationBlocks.ORANGE_PASTEL_BEAN_BAG;
            case YELLOW -> PokeModDecorationBlocks.YELLOW_PASTEL_BEAN_BAG;
            case LIME -> PokeModDecorationBlocks.LIME_PASTEL_BEAN_BAG;
            case GREEN -> PokeModDecorationBlocks.GREEN_PASTEL_BEAN_BAG;
            case CYAN -> PokeModDecorationBlocks.CYAN_PASTEL_BEAN_BAG;
            case LIGHT_BLUE -> PokeModDecorationBlocks.LIGHT_BLUE_PASTEL_BEAN_BAG;
            case BLUE -> PokeModDecorationBlocks.BLUE_PASTEL_BEAN_BAG;
            case PURPLE -> PokeModDecorationBlocks.PURPLE_PASTEL_BEAN_BAG;
            case MAGENTA -> PokeModDecorationBlocks.MAGENTA_PASTEL_BEAN_BAG;
            case PINK -> PokeModDecorationBlocks.PINK_PASTEL_BEAN_BAG;
        }).get();
    }
}
