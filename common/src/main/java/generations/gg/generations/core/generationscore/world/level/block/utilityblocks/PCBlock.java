package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.PokeModUtilityBlocks;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericDyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.world.sound.PokeModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class PCBlock extends DoubleDyeableBlock<GenericDyedVariantBlockEntity, PCBlock> {
    public PCBlock(Properties arg) {
        super(PCBlock::getBlock, PokeModBlockEntities.GENERIC_DYED_VARIANT, arg, PokeModBlockEntityModels.PC);
    }

    @Override
    protected InteractionResult serverUse(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        world.playSound(null, pos, PokeModSounds.PC_OPEN.get(), SoundSource.BLOCKS, 0.7F, 1.0F);

//        PixelmonPc.openPc((ServerPlayer) player);
        return InteractionResult.SUCCESS;
    }

    public static DyedBlockItem<PCBlock> getBlock(DyeColor color) {
        return (switch (color) {
            case WHITE -> PokeModUtilityBlocks.WHITE_PC;
            case ORANGE -> PokeModUtilityBlocks.ORANGE_PC;
            case MAGENTA -> PokeModUtilityBlocks.MAGENTA_PC;
            case LIGHT_BLUE -> PokeModUtilityBlocks.LIGHT_BLUE_PC;
            case YELLOW -> PokeModUtilityBlocks.YELLOW_PC;
            case LIME -> PokeModUtilityBlocks.LIME_PC;
            case PINK -> PokeModUtilityBlocks.PINK_PC;
            case GRAY -> PokeModUtilityBlocks.GRAY_PC;
            case LIGHT_GRAY -> PokeModUtilityBlocks.LIGHT_GRAY_PC;
            case CYAN -> PokeModUtilityBlocks.CYAN_PC;
            case PURPLE -> PokeModUtilityBlocks.PURPLE_PC;
            case BLUE -> PokeModUtilityBlocks.BLUE_PC;
            case BROWN -> PokeModUtilityBlocks.BROWN_PC;
            case GREEN -> PokeModUtilityBlocks.GREEN_PC;
            case RED -> PokeModUtilityBlocks.RED_PC;
            case BLACK -> PokeModUtilityBlocks.BLACK_PC;
        }).get();
    }

    @Override
    protected void spawnDestroyParticles(Level level, Player player, BlockPos pos, BlockState state) {
        super.spawnDestroyParticles(level, player, pos, state);
    }
}

