package generations.gg.generations.core.generationscore.world.level.block.decorations;

import com.pokemod.pokemod.world.item.DyedBlockItem;
import com.pokemod.pokemod.world.level.block.PokeModDecorationBlocks;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntityModels;
import com.pokemod.pokemod.world.level.block.entities.generic.GenericDyedVariantBlockEntity;
import com.pokemod.pokemod.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PokeballRugBlock extends DyeableBlock<GenericDyedVariantBlockEntity, PokeballRugBlock> {
    private static VoxelShape SHAPE = Shapes.box(0, 0, 0, 1, 0.071875, 1);

    public PokeballRugBlock(BlockBehaviour.Properties properties) {
        super(PokeballRugBlock::getBlock, PokeModBlockEntities.GENERIC_DYED_VARIANT, properties, PokeModBlockEntityModels.POKEBALL_RUG, 1, 0, 0);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public static DyedBlockItem<PokeballRugBlock> getBlock(DyeColor dyeColor) {
        return (switch (dyeColor) {
            case WHITE -> PokeModDecorationBlocks.WHITE_POKEBALL_RUG;
            case LIGHT_GRAY -> PokeModDecorationBlocks.LIGHT_GRAY_POKEBALL_RUG;
            case GRAY -> PokeModDecorationBlocks.GRAY_POKEBALL_RUG;
            case BLACK -> PokeModDecorationBlocks.BLACK_POKEBALL_RUG;
            case BROWN -> PokeModDecorationBlocks.BROWN_POKEBALL_RUG;
            case RED -> PokeModDecorationBlocks.RED_POKEBALL_RUG;
            case ORANGE -> PokeModDecorationBlocks.ORANGE_POKEBALL_RUG;
            case YELLOW -> PokeModDecorationBlocks.YELLOW_POKEBALL_RUG;
            case LIME -> PokeModDecorationBlocks.LIME_POKEBALL_RUG;
            case GREEN -> PokeModDecorationBlocks.GREEN_POKEBALL_RUG;
            case CYAN -> PokeModDecorationBlocks.CYAN_POKEBALL_RUG;
            case LIGHT_BLUE -> PokeModDecorationBlocks.LIGHT_BLUE_POKEBALL_RUG;
            case BLUE -> PokeModDecorationBlocks.BLUE_POKEBALL_RUG;
            case PURPLE -> PokeModDecorationBlocks.PURPLE_POKEBALL_RUG;
            case MAGENTA -> PokeModDecorationBlocks.MAGENTA_POKEBALL_RUG;
            case PINK -> PokeModDecorationBlocks.PINK_POKEBALL_RUG;
        }).get();
    }
}
