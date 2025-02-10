package generations.gg.generations.core.generationscore.forge.datagen.generators.loot;

import com.google.common.collect.ImmutableList;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.config.LegendKeys;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.loot.SpeciesKeyCondition;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static generations.gg.generations.core.generationscore.forge.datagen.generators.loot.GenerationsChestLoot.CALYREX_ROOTS;
import static net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition.hasBlockStateProperties;

public class LootTableDatagen extends LootTableProvider {

    public LootTableDatagen(PackOutput output) {
        super(output, Set.of(), ImmutableList.of(
                new SubProviderEntry(GenerationsBlockLoot::new, LootContextParamSets.BLOCK),
        new SubProviderEntry(GenerationsChestLoot::new, LootContextParamSets.CHEST),
        new SubProviderEntry(GenerationsCoreFishingLoot::new, LootContextParamSets.FISHING),
        new SubProviderEntry(() -> op -> op.accept(CALYREX_ROOTS, LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(1/1000f))
                        .when(hasBlockStateProperties(Blocks.CARROTS).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7)))
                        .add(AlternativesEntry.alternatives(
                                LootItem.lootTableItem(GenerationsItems.SHADEROOT_CARROT.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.5f))
                                        .when(new SpeciesKeyCondition.Builder().key(LegendKeys.SPECTRIER)),
                                LootItem.lootTableItem(GenerationsItems.ICEROOT_CARROT.get())
                                        .when(new SpeciesKeyCondition.Builder().key(LegendKeys.GLASTRIER))

                        ))
                )
        ), LootContextParamSets.BLOCK)
//            Pair.of(ChestLoot::new, LootContextParamSets.CHEST),
//            Pair.of(EntityLoot::new, LootContextParamSets.ENTITY),
//            Pair.of(PiglinBarterLoot::new, LootContextParamSets.PIGLIN_BARTER),
//            Pair.of(GiftLoot::new, LootContextParamSets.GIFT)
        ));
        GenerationsCore.LOGGER.info("LootTableDatagen");
    }
}
