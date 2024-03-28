package generations.gg.generations.core.generationscore.world.loot;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;

import java.util.function.Supplier;

public class LootPoolEntryTypes {
    public static final DeferredRegister<LootPoolEntryType> REGISTER = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.LOOT_POOL_ENTRY_TYPE);

    public static <T extends LootPoolEntryContainer> RegistrySupplier<LootPoolEntryType> register(String name, Supplier<Serializer<T>> supplier) {
        return REGISTER.register(name, () -> new LootPoolEntryType(supplier.get()));
    }

    public static final RegistrySupplier<LootPoolEntryType> RESOUCE_KEY = register("resource_key", ResouceKeyEntry.Serializer::new);

    public static void init() {
        REGISTER.register();
    }

}
