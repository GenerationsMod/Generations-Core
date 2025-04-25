package generations.gg.generations.core.generationscore.common.world.loot;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class GenerationCoreLootTables {
    //FISHING
    public static final ResourceKey<LootTable> FISHING_OLD = ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("gameplay/fishing/old"));
    public static final ResourceKey<LootTable> FISHING_GOOD = ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("gameplay/fishing/good"));
    public static final ResourceKey<LootTable> FISHING_SUPER = ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("gameplay/fishing/super"));
    public static final ResourceKey<LootTable> FISHING_RUBY = ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("gameplay/fishing/ruby"));
}