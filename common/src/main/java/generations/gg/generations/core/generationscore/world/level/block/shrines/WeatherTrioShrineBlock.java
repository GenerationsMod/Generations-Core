package generations.gg.generations.core.generationscore.world.level.block.shrines;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.WeatherTrioShrineBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class WeatherTrioShrineBlock extends InteractShrineBlock<WeatherTrioShrineBlockEntity> {
    private final PokemonProperties species;
    private final RegistrySupplier<Item> requiredItem;

    public WeatherTrioShrineBlock(BlockBehaviour.Properties properties, ResourceLocation model, String species, RegistrySupplier<Item> requiredItem) {
        super(properties, GenerationsBlockEntities.WEATHER_TRIO, model, WeatherTrioShrineBlockEntity.class);
        this.species = GenerationsUtils.parseProperties(species);
        this.requiredItem = requiredItem;
    }

    @Override
    public boolean isStackValid(ItemStack stack) {
        return stack.is(requiredItem.get())  && stack.getDamageValue() >= stack.getMaxDamage();
    }

    public PokemonProperties getSpecies() {
        return species;
    }
}
