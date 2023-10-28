package generations.gg.generations.core.generationscore.world.level.block.shrines;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.WeatherTrioShrineBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class WeatherTrioShrineBlock extends InteractShrineBlock<WeatherTrioShrineBlockEntity> {
    private final SpeciesKey speiceskey;
    private final RegistrySupplier<? extends Item> requiredItem;

    public WeatherTrioShrineBlock(BlockBehaviour.Properties properties, ResourceLocation model, SpeciesKey speiceskey, RegistrySupplier<? extends Item> requiredItem) {
        super(properties, GenerationsBlockEntities.WEATHER_TRIO, model, WeatherTrioShrineBlockEntity.class);
        this.speiceskey = speiceskey;
        this.requiredItem = requiredItem;
    }

    @Override
    public boolean isStackValid(ItemStack stack) {
        return stack.is(requiredItem.get())  && stack.getDamageValue() >= stack.getMaxDamage();
    }

    public SpeciesKey getSpecies() {
        return speiceskey;
    }

    @Override
    public String getActiveVariant(boolean active) {
        return active ? "activated" : "deactivated";
    }
}
