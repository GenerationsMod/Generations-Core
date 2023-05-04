package generations.gg.generations.core.generationscore.world.level.block;

import com.pokemod.pokemod.world.level.block.entities.PokeModSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;

public class PokeModStandingSignBlock extends StandingSignBlock {

    public PokeModStandingSignBlock(Properties arg, WoodType arg2) {
        super(arg, arg2);
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos arg, @NotNull BlockState arg2) {
        return new PokeModSignBlockEntity(arg, arg2);
    }
}
