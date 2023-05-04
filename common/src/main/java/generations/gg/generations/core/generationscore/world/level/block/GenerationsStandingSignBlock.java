package generations.gg.generations.core.generationscore.world.level.block;

import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;

public class GenerationsStandingSignBlock extends StandingSignBlock {

    public GenerationsStandingSignBlock(Properties arg, WoodType arg2) {
        super(arg, arg2);
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos arg, @NotNull BlockState arg2) {
        return new GenerationsSignBlockEntity(arg, arg2);
    }
}
