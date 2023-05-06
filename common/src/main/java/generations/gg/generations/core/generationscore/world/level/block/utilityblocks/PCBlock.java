package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsUtilityBlocks;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
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
        super(PCBlock::getBlock, GenerationsBlockEntities.GENERIC_DYED_VARIANT, arg, PokeModBlockEntityModels.PC);
    }

    @Override
    protected InteractionResult serverUse(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        world.playSound(null, pos, PokeModSounds.PC_OPEN.get(), SoundSource.BLOCKS, 0.7F, 1.0F);

//        PixelmonPc.openPc((ServerPlayer) player);
        return InteractionResult.SUCCESS;
    }

    public static DyedBlockItem<PCBlock> getBlock(DyeColor color) {
        return (switch (color) {
            case WHITE -> GenerationsUtilityBlocks.WHITE_PC;
            case ORANGE -> GenerationsUtilityBlocks.ORANGE_PC;
            case MAGENTA -> GenerationsUtilityBlocks.MAGENTA_PC;
            case LIGHT_BLUE -> GenerationsUtilityBlocks.LIGHT_BLUE_PC;
            case YELLOW -> GenerationsUtilityBlocks.YELLOW_PC;
            case LIME -> GenerationsUtilityBlocks.LIME_PC;
            case PINK -> GenerationsUtilityBlocks.PINK_PC;
            case GRAY -> GenerationsUtilityBlocks.GRAY_PC;
            case LIGHT_GRAY -> GenerationsUtilityBlocks.LIGHT_GRAY_PC;
            case CYAN -> GenerationsUtilityBlocks.CYAN_PC;
            case PURPLE -> GenerationsUtilityBlocks.PURPLE_PC;
            case BLUE -> GenerationsUtilityBlocks.BLUE_PC;
            case BROWN -> GenerationsUtilityBlocks.BROWN_PC;
            case GREEN -> GenerationsUtilityBlocks.GREEN_PC;
            case RED -> GenerationsUtilityBlocks.RED_PC;
            case BLACK -> GenerationsUtilityBlocks.BLACK_PC;
        }).get();
    }

    @Override
    protected void spawnDestroyParticles(Level level, Player player, BlockPos pos, BlockState state) {
        super.spawnDestroyParticles(level, player, pos, state);
    }
}

