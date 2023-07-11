package generations.gg.generations.core.generationscore.forge.datagen.generators.loot;

import com.google.common.collect.ImmutableList;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

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
}
