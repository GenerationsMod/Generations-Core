package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.pokemon.Species
import net.minecraft.world.item.ItemStack
import org.joml.Vector4f

interface PokemonProvidingItem {
    fun getSpeciesAndAspectsPair(stack: ItemStack): Pair<Species, Set<String>>?
    fun stackTint(stack: ItemStack): Vector4f = Vector4f(1f, 1f, 1f, 1f)
}
