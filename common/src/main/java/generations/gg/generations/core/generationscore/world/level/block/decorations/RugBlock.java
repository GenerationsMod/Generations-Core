package generations.gg.generations.core.generationscore.world.level.block.decorations;

import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsDecorationBlocks;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericDyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class RugBlock extends DyeableBlock<GenericDyedVariantBlockEntity, RugBlock> {
    private static final VoxelShape SHAPE = Shapes.box(0, 0, 0, 1, 0.071875, 1);

    public RugBlock(BlockBehaviour.Properties properties) {
        super(RugBlock::getBlock, GenerationsBlockEntities.GENERIC_DYED_VARIANT, properties, GenerationsBlockEntityModels.POKEBALL_RUG, 1, 0, 0);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public static DyedBlockItem<RugBlock> getBlock(DyeColor dyeColor) {
        return (switch (dyeColor) {
            case WHITE -> GenerationsDecorationBlocks.WHITE_POKEBALL_RUG;
            case LIGHT_GRAY -> GenerationsDecorationBlocks.LIGHT_GRAY_POKEBALL_RUG;
            case GRAY -> GenerationsDecorationBlocks.GRAY_POKEBALL_RUG;
            case BLACK -> GenerationsDecorationBlocks.BLACK_POKEBALL_RUG;
            case BROWN -> GenerationsDecorationBlocks.BROWN_POKEBALL_RUG;
            case RED -> GenerationsDecorationBlocks.RED_POKEBALL_RUG;
            case ORANGE -> GenerationsDecorationBlocks.ORANGE_POKEBALL_RUG;
            case YELLOW -> GenerationsDecorationBlocks.YELLOW_POKEBALL_RUG;
            case LIME -> GenerationsDecorationBlocks.LIME_POKEBALL_RUG;
            case GREEN -> GenerationsDecorationBlocks.GREEN_POKEBALL_RUG;
            case CYAN -> GenerationsDecorationBlocks.CYAN_POKEBALL_RUG;
            case LIGHT_BLUE -> GenerationsDecorationBlocks.LIGHT_BLUE_POKEBALL_RUG;
            case BLUE -> GenerationsDecorationBlocks.BLUE_POKEBALL_RUG;
            case PURPLE -> GenerationsDecorationBlocks.PURPLE_POKEBALL_RUG;
            case MAGENTA -> GenerationsDecorationBlocks.MAGENTA_POKEBALL_RUG;
            case PINK -> GenerationsDecorationBlocks.PINK_POKEBALL_RUG;
        }).get();
    }
}
