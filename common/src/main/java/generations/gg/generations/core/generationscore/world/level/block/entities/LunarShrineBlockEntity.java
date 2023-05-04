package generations.gg.generations.core.generationscore.world.level.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;

import static com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntityModels.LIGHT_MODEL;

public class LunarShrineBlockEntity extends ShrineBlockEntity {
    public LunarShrineBlockEntity(BlockPos pos, BlockState state) {
        super(PokeModBlockEntities.LUNAR_SHRINE.get(), pos, state);
    }

    @Override
    public ResourceLocation getModel() {
        if (hasLevel()) Objects.requireNonNull(getLevel()).getLightEmission(getBlockPos());
        return LIGHT_MODEL;
    }
}
