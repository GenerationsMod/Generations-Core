package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.config.LegendKeys;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsShrines;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.LunarShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.ShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class LunarShrineBlock extends ShrineBlock<LunarShrineBlockEntity> {

    public LunarShrineBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.LUNAR_SHRINE, null);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (!level.isClientSide()) {
            var key = getSpecies(level, pos);

            var block = (key == LegendKeys.CRESSELIA ? GenerationsShrines.LIGHT_CRYSTAL : GenerationsShrines.DARK_CRYSTAL).get();

            var list = RegiShrineBlock.searchForBlock(level, pos, 15, 5, (level1, blockPos) -> level1.getBlockState(pos).is(block));

            if (!list.isEmpty() && level.getBlockEntity(pos) instanceof ShrineBlockEntity shrine && !shrine.isActive()) {
                if(list.size() == 5) {
                    shrine.toggleActive();
                    list.forEach(a -> level.setBlockAndUpdate(a, Blocks.AIR.defaultBlockState()));
                    player.getItemInHand(hand).shrink(1);
                    PokemonUtil.spawn(key.createProperties(70), level, shrine.getBlockPos());
                    shrine.toggleActive();

                    return InteractionResult.SUCCESS;
                }
            }
        }

        return super.use(state, level, pos, player, hand, hit);
    }

    public SpeciesKey getSpecies(Level level, BlockPos pos) {
        return level.getLightEmission(pos) > 10 ? LegendKeys.CRESSELIA : LegendKeys.DARKRAI;
    }
}
