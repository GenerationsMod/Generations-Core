package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.PokeModUtilityBlocks;
import generations.gg.generations.core.generationscore.world.level.block.entities.HealerBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntityModels;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class HealerBlock extends DyeableBlock<HealerBlockEntity, HealerBlock> {

    public HealerBlock(BlockBehaviour.Properties props) {
        super(HealerBlock::getBlock, PokeModBlockEntities.HEALER, props, PokeModBlockEntityModels.HEALER);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (!world.isClientSide()) {
//            var party = PixelmonParty.of(player);
//            world.playSound(null, pos, PokeModSounds.HEALER_ACTIVATE.get(), SoundSource.BLOCKS, 0.7F, 1.0F);
//            for (var pixelmon : party) {
//                if (pixelmon != null) pixelmon.hp = pixelmon.getMaxHp();
//            }
//
//            party.sync();
        }
        return InteractionResult.SUCCESS;
    }

    public static DyedBlockItem<HealerBlock> getBlock(DyeColor color) {
        return (switch (color) {
            case RED -> PokeModUtilityBlocks.RED_HEALER;
            case WHITE -> PokeModUtilityBlocks.WHITE_HEALER;
            case ORANGE -> PokeModUtilityBlocks.ORANGE_HEALER;
            case PINK -> PokeModUtilityBlocks.PINK_HEALER;
            case YELLOW -> PokeModUtilityBlocks.YELLOW_HEALER;
            case LIME -> PokeModUtilityBlocks.LIME_HEALER;
            case GREEN -> PokeModUtilityBlocks.GREEN_HEALER;
            case LIGHT_BLUE -> PokeModUtilityBlocks.LIGHT_BLUE_HEALER;
            case CYAN -> PokeModUtilityBlocks.CYAN_HEALER;
            case BLUE -> PokeModUtilityBlocks.BLUE_HEALER;
            case MAGENTA -> PokeModUtilityBlocks.MAGENTA_HEALER;
            case PURPLE -> PokeModUtilityBlocks.PURPLE_HEALER;
            case BROWN -> PokeModUtilityBlocks.BROWN_HEALER;
            case GRAY -> PokeModUtilityBlocks.GRAY_HEALER;
            case LIGHT_GRAY -> PokeModUtilityBlocks.LIGHT_GRAY_HEALER;
            case BLACK -> PokeModUtilityBlocks.BLACK_HEALER;
        }).get();
    }
}
