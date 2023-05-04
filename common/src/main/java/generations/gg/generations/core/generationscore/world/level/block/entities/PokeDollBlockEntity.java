package generations.gg.generations.core.generationscore.world.level.block.entities;

import com.pokemod.pokemod.PokeMod;
import com.pokemod.pokemod.client.model.ModelContextProviders;
import com.pokemod.pokemod.world.level.block.decorations.PokeModDollBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

public class PokeDollBlockEntity extends ModelProvidingBlockEntity implements ModelContextProviders.VariantProvider, ModelContextProviders.AngleProvider { //Simplify
    public static final ResourceLocation DEFAULT_MODEL = PokeMod.id("models/block/pokedolls/charizard.pk");

    public PokeDollBlockEntity(BlockPos pos, BlockState state) {
        super(PokeModBlockEntities.POKE_DOLL.get(), pos, state);
    }

    public ResourceLocation getModel() {
        var dollBlock = getPokeModDollBlock();

        if(dollBlock != null) return dollBlock.getModel();
        return DEFAULT_MODEL;
    }

    @Override
    public String getVariant() {
        var dollBlock = getPokeModDollBlock();
        return dollBlock != null ? dollBlock.getVariant() : "regular";
    }

    @Override
    public float getAngle() {
        var blockState = getBlockState();
        return (blockState.hasProperty(PokeModDollBlock.CARDINAL) ? blockState.getValue(PokeModDollBlock.CARDINAL).getAngle() : 0f);
    }

    public PokeModDollBlock getPokeModDollBlock() {
        return getBlockState().getBlock() instanceof PokeModDollBlock dollBlock ? dollBlock : null;
    }
}