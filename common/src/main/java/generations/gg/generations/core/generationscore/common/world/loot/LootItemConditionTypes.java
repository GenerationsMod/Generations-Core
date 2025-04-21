package generations.gg.generations.core.generationscore.common.world.loot;

import com.mojang.serialization.MapCodec;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import java.util.function.Supplier;

public class LootItemConditionTypes {
    public static final DeferredRegister<LootItemConditionType> REGISTER = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.LOOT_CONDITION_TYPE);

    public static <T extends LootItemCondition> RegistrySupplier<LootItemConditionType> register(String name, MapCodec<T> supplier) {
        return REGISTER.register(name, () -> new LootItemConditionType(supplier));
    }

    public static final RegistrySupplier<LootItemConditionType> SPECIES_KEY = register("species_key", SpeciesKeyCondition.CODEC);

    public static void init() {
        REGISTER.register();
    }

}
