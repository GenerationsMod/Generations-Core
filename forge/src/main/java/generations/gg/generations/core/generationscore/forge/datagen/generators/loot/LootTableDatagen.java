package generations.gg.generations.core.generationscore.forge.datagen.generators.loot;

import com.google.common.collect.ImmutableList;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public class LootTableDatagen extends LootTableProvider {

    public LootTableDatagen(PackOutput output) {
        super(output, Set.of(), ImmutableList.of(
                new SubProviderEntry(GenerationsBlockLoot::new, LootContextParamSets.BLOCK)
//            Pair.of(ChestLoot::new, LootContextParamSets.CHEST),
//            Pair.of(EntityLoot::new, LootContextParamSets.ENTITY),
//            Pair.of(PiglinBarterLoot::new, LootContextParamSets.PIGLIN_BARTER),
//            Pair.of(GiftLoot::new, LootContextParamSets.GIFT)
        ));
        GenerationsCore.LOGGER.info("LootTableDatagen");
    }

    protected void validate(Map<ResourceLocation, LootTable> map, @NotNull ValidationContext validationContext) {
        map.forEach((arg, arg2) -> LootTables.validate(validationContext, arg, arg2));
    }
}
