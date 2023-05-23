package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsUtilityBlocks;
import generations.gg.generations.core.generationscore.world.level.block.entities.HealerBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
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
        super(HealerBlock::getBlock, GenerationsBlockEntities.HEALER, props, GenerationsBlockEntityModels.HEALER);
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
            case RED -> GenerationsUtilityBlocks.RED_HEALER;
            case WHITE -> GenerationsUtilityBlocks.WHITE_HEALER;
            case ORANGE -> GenerationsUtilityBlocks.ORANGE_HEALER;
            case PINK -> GenerationsUtilityBlocks.PINK_HEALER;
            case YELLOW -> GenerationsUtilityBlocks.YELLOW_HEALER;
            case LIME -> GenerationsUtilityBlocks.LIME_HEALER;
            case GREEN -> GenerationsUtilityBlocks.GREEN_HEALER;
            case LIGHT_BLUE -> GenerationsUtilityBlocks.LIGHT_BLUE_HEALER;
            case CYAN -> GenerationsUtilityBlocks.CYAN_HEALER;
            case BLUE -> GenerationsUtilityBlocks.BLUE_HEALER;
            case MAGENTA -> GenerationsUtilityBlocks.MAGENTA_HEALER;
            case PURPLE -> GenerationsUtilityBlocks.PURPLE_HEALER;
            case BROWN -> GenerationsUtilityBlocks.BROWN_HEALER;
            case GRAY -> GenerationsUtilityBlocks.GRAY_HEALER;
            case LIGHT_GRAY -> GenerationsUtilityBlocks.LIGHT_GRAY_HEALER;
            case BLACK -> GenerationsUtilityBlocks.BLACK_HEALER;
        }).get();
    }
}
