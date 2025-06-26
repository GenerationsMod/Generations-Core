package generations.gg.generations.core.generationscore.common.mixin;

import com.cobblemon.mod.common.item.PokemonItem;
import com.cobblemon.mod.common.pokemon.Species;
import generations.gg.generations.core.generationscore.common.world.item.PokemonProvidingItem;
import kotlin.Pair;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Set;

@Mixin(PokemonItem.class)
abstract public class PokemonItemMixin implements PokemonProvidingItem {
   @Shadow public abstract Vector4f tint(ItemStack stack);
   @Shadow @Nullable public abstract Pair<Species, Set<String>> getSpeciesAndAspects(@NotNull ItemStack stack);

   @Override
   public @Nullable Pair<Species, Set<String>> getSpeciesAndAspectsPair(@NotNull ItemStack stack) {
       return getSpeciesAndAspects(stack);
   }

    @Override
    public @NotNull Vector4f stackTint(@NotNull ItemStack stack) {
        return this.tint(stack);
    }
}
