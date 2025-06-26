package generations.gg.generations.core.generationscore.common.world.level.block.entities

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.AngleProvider
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.VariantProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.level.block.decorations.PokeDollBlock
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.state.BlockState

class PokeDollBlockEntity(pos: BlockPos, state: BlockState) :
    ModelProvidingBlockEntity(GenerationsBlockEntities.POKE_DOLL, pos, state), VariantProvider, AngleProvider {
    override fun getModel(): ResourceLocation {
        return pokeModDollBlock?.getModel() ?: DEFAULT_MODEL
    }

    override fun getVariant(): String? {
        val dollBlock = pokeModDollBlock
        return if (dollBlock != null) dollBlock.variant else "regular"
    }

    override fun getAngle(): Float {
        val blockState = blockState
        return (if (blockState.hasProperty(PokeDollBlock.CARDINAL)) blockState.getValue(PokeDollBlock.CARDINAL)
            .getAngle() else 0f)
    }

    val pokeModDollBlock: PokeDollBlock?
        get() = blockState.block.instanceOrNull<PokeDollBlock>()

    companion object {
        //Simplify
        val DEFAULT_MODEL: ResourceLocation = GenerationsCore.id("models/block/pokedolls/charizard.pk")
    }
}