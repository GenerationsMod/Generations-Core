package generations.gg.generations.core.generationscore.world.level.block.entities;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.world.level.block.decorations.PokeDollBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

public class PokeDollBlockEntity extends ModelProvidingBlockEntity implements ModelContextProviders.VariantProvider, ModelContextProviders.AngleProvider { //Simplify
    public static final ResourceLocation DEFAULT_MODEL = GenerationsCore.id("models/block/pokedolls/charizard.pk");

    public PokeDollBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.POKE_DOLL.get(), pos, state);
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
        return (blockState.hasProperty(PokeDollBlock.CARDINAL) ? blockState.getValue(PokeDollBlock.CARDINAL).getAngle() : 0f);
    }

    public PokeDollBlock getPokeModDollBlock() {
        return getBlockState().getBlock() instanceof PokeDollBlock dollBlock ? dollBlock : null;
    }
}