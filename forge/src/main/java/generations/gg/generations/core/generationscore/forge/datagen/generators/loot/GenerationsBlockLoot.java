package generations.gg.generations.core.generationscore.forge.datagen.generators.loot;

import com.cobblemon.mod.common.CobblemonItems;
import generations.gg.generations.core.generationscore.forge.datagen.generators.blocks.BlockDatagen;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.*;
import generations.gg.generations.core.generationscore.world.level.block.set.GenerationsBlockSet;
import generations.gg.generations.core.generationscore.world.level.block.set.GenerationsFullBlockSet;
import generations.gg.generations.core.generationscore.world.level.block.set.GenerationsOreSet;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GenerationsBlockLoot extends BlockLootSubProvider {

    private final List<Block> knownBlocks = new ArrayList<>();

    protected GenerationsBlockLoot() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        GenerationsBlocks.STONE.forEach(block -> dropSelfStoneCobble(block.get()));
        GenerationsWood.WOOD_BLOCKS.forEach(block -> dropSelfUpdated(block.get()));

        GenerationsBlocks.ULTRA_BLOCKS.forEach(block -> dropSelfUpdated(block.get()));
        //createDoubleDyedBlock(GenerationsUtilityBlocks.PC.get(), PCBlock.HALF, DoubleBlockHalf.LOWER);
        //createDyedBlock(GenerationsUtilityBlocks.CLOCK.get());
        //createDyedBlock(GenerationsDecorationBlocks.UMBRELLA.get());
        //createDyedBlock(GenerationsDecorationBlocks.PASTEL_BEAN_BAG.get());
        //createDyedBlock(GenerationsDecorationBlocks.VENDING_MACHINE.get());
        //GenerationsDecorationBlocks.VENDING_MACHINE_BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> add(block, createSinglePropConditionTable(block, DoubleDyeableBlock.HALF, DoubleBlockHalf.LOWER)));
        GenerationsPokeDolls.POKEDOLLS.forEach(block -> dropSelf(block.get()));
        BlockDatagen.dropSelfList.stream().map(block -> (Block) block).forEach(this::dropSelf);
        BlockDatagen.MUSHROOM_BLOCKS.forEach(block -> add(block, createMushroomBlockDrop(block, block.asItem())));
        GenerationsFullBlockSet.getFullBlockSets().forEach(generationsFullBlockSet -> generationsFullBlockSet.getAllBlocks().forEach(this::dropSelfUpdated));
        GenerationsBlockSet.getBlockSets().forEach(generationsBlockSet -> generationsBlockSet.getAllBlocks().forEach(this::dropSelfUpdated));

        add(GenerationsBlocks.CHARGE_STONE_SET.getBaseBlock(), createSingleItemTable(GenerationsBlocks.CHARGE_COBBLESTONE_SET.getBaseBlock()));

        dropSelf(GenerationsBlocks.VOLCANIC_FIRESTONE.get());

        dropSelf(GenerationsBlocks.GOLDEN_TEMPLE_SAND.get());

        //Ores
        addOreSet(GenerationsOres.CRYSTAL_ORE_SET);
        addOreSet(GenerationsOres.RUBY_ORE_SET);
        addOreSet(GenerationsOres.SAPPHIRE_ORE_SET);
        addOreSet(GenerationsOres.SILICON_ORE_SET);

        dropSelf(GenerationsOres.Z_CRYSTAL_ORE_SET.getOre());
        dropSelf(GenerationsOres.Z_CRYSTAL_ORE_SET.getDeepslateOre());
        //dropSelf(GenerationsOres.CHARGE_STONE_Z_CRYSTAL_ORE.get());

        addAll(GenerationsBlocks.RUBY_BLOCK.get(), GenerationsBlocks.RUBY_SLAB.get(), GenerationsBlocks.RUBY_STAIRS.get(), GenerationsBlocks.RUBY_WALL.get());

        addAll(GenerationsBlocks.SAPPHIRE_BLOCK.get(), GenerationsBlocks.SAPPHIRE_SLAB.get(), GenerationsBlocks.SAPPHIRE_STAIRS.get(), GenerationsBlocks.SAPPHIRE_WALL.get());

        addAll(GenerationsBlocks.CRYSTAL_BLOCK.get(), GenerationsBlocks.CRYSTAL_SLAB.get(), GenerationsBlocks.CRYSTAL_STAIRS.get(), GenerationsBlocks.CRYSTAL_WALL.get());

        dropSelf(GenerationsBlocks.SILICON_BLOCK.get());

        //Ultra Space
        dropSelf(GenerationsBlocks.ULTRA_SAND.get());
        dropWhenSilkTouch(GenerationsBlocks.GHOST_LANTERN.get());
        add(GenerationsBlocks.MACHINE_BLOCK.get(), createSingleItemTable(GenerationsBlocks.MACHINE_BLOCK.get()));
        dropSelf(GenerationsBlocks.RUINS_SAND.get());
        dropSelf(GenerationsBlocks.RICH_SOIL_1.get());
        dropSelf(GenerationsBlocks.RICH_SOIL_2.get());
        dropSelf(GenerationsBlocks.RICH_SOIL_3.get());
        dropSelf(GenerationsBlocks.RICH_SOIL_4.get());

        add(GenerationsBlocks.POKEBALL_CHEST.get(), createNameableBlockEntityTable(GenerationsBlocks.POKEBALL_CHEST.get()));
        add(GenerationsBlocks.GREATBALL_CHEST.get(), createNameableBlockEntityTable(GenerationsBlocks.GREATBALL_CHEST.get()));
        add(GenerationsBlocks.ULTRABALL_CHEST.get(), createNameableBlockEntityTable(GenerationsBlocks.ULTRABALL_CHEST.get()));
        add(GenerationsBlocks.MASTERBALL_CHEST.get(), createNameableBlockEntityTable(GenerationsBlocks.MASTERBALL_CHEST.get()));


        addOreSetWithRandomAmountDrops(GenerationsOres.MEGASTONE_ORE_SET, GenerationsItems.MEGASTONE_SHARD.get());
        addOreSetWithRandomAmountDrops(GenerationsOres.METEORITE_ORE_SET, GenerationsItems.METEORITE_SHARD.get());

        //Pumpkins
        dropSelf(GenerationsBlocks.CURSED_PUMPKIN.get());
        dropSelf(GenerationsBlocks.CURSED_CARVED_PUMPKIN.get());
        dropSelf(GenerationsBlocks.CURSED_JACK_O_LANTERN.get());

        //Furnaces
        add(GenerationsUtilityBlocks.CHARGE_STONE_FURNACE.get(), createNameableBlockEntityTable(GenerationsUtilityBlocks.CHARGE_STONE_FURNACE.get()));
        add(GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE.get(), createNameableBlockEntityTable(GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE.get()));
        add(GenerationsUtilityBlocks.CHARGE_STONE_SMOKER.get(), createNameableBlockEntityTable(GenerationsUtilityBlocks.CHARGE_STONE_SMOKER.get()));
        add(GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE.get(), createNameableBlockEntityTable(GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE.get()));
        add(GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE.get(), createNameableBlockEntityTable(GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE.get()));
        add(GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER.get(), createNameableBlockEntityTable(GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER.get()));


        dropSelf(GenerationsOres.FOSSIL_ORE_SET.getOre());

        dropSelf(GenerationsOres.FOSSIL_ORE_SET.getDeepslateOre());


        dropSelf(GenerationsBlocks.POKEMART_SIGN.get());
        dropSelf(GenerationsBlocks.POKECENTER_SIGN.get());

        dropSelf(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get());

        dropDisplayStandWithBall(GenerationsDecorationBlocks.POKE_BALL_DISPLAY.get(), CobblemonItems.POKE_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.GREAT_BALL_DISPLAY.get(), CobblemonItems.GREAT_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.ULTRA_BALL_DISPLAY.get(), CobblemonItems.ULTRA_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.MASTER_BALL_DISPLAY.get(), CobblemonItems.MASTER_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.CHERISH_BALL_DISPLAY.get(), CobblemonItems.CHERISH_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.DIVE_BALL_DISPLAY.get(), CobblemonItems.DIVE_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.DUSK_BALL_DISPLAY.get(), CobblemonItems.DUSK_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.FAST_BALL_DISPLAY.get(), CobblemonItems.FAST_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.FRIEND_BALL_DISPLAY.get(), CobblemonItems.FRIEND_BALL.asItem());
        //dropDisplayStandWithBall(GenerationsDecorationBlocks.GS_BALL_DISPLAY.get(), null);
        dropDisplayStandWithBall(GenerationsDecorationBlocks.HEAL_BALL_DISPLAY.get(), CobblemonItems.HEAL_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.HEAVY_BALL_DISPLAY.get(), CobblemonItems.HEAVY_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.LEVEL_BALL_DISPLAY.get(), CobblemonItems.LEVEL_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.LOVE_BALL_DISPLAY.get(), CobblemonItems.LOVE_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.LURE_BALL_DISPLAY.get(), CobblemonItems.LURE_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.LUXURY_BALL_DISPLAY.get(), CobblemonItems.LUXURY_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.MOON_BALL_DISPLAY.get(), CobblemonItems.MOON_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.NEST_BALL_DISPLAY.get(), CobblemonItems.NEST_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.NET_BALL_DISPLAY.get(), CobblemonItems.NET_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.PARK_BALL_DISPLAY.get(), CobblemonItems.PARK_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.PREMIER_BALL_DISPLAY.get(), CobblemonItems.PREMIER_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.QUICK_BALL_DISPLAY.get(), CobblemonItems.QUICK_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.REPEAT_BALL_DISPLAY.get(), CobblemonItems.REPEAT_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.SAFARI_BALL_DISPLAY.get(), CobblemonItems.SAFARI_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.SPORT_BALL_DISPLAY.get(), CobblemonItems.SPORT_BALL.asItem());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.TIMER_BALL_DISPLAY.get(), CobblemonItems.TIMER_BALL.asItem());
    }

    private void dropDisplayStandWithBall(Block block, Item item) {
        add(block, LootTable
                .lootTable()
                .withPool(applyExplosionCondition(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get(),
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get()))))
                .withPool(applyExplosionCondition(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get(),
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(item)))));
    }


    private void addOreSetWithRandomAmountDrops(GenerationsOreSet oreSet, Item drop) {
        add(
                oreSet.getOre(),
                createSilkTouchDispatchTable(
                        oreSet.getOre(),
                        applyExplosionDecay(
                                oreSet.getOre(),
                                LootItem.lootTableItem(drop)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between((float) 1, (float) 3)))
                                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                        )
                )
        );
        add(
                oreSet.getDeepslateOre(),
                createSilkTouchDispatchTable(
                        oreSet.getDeepslateOre(),
                        applyExplosionDecay(
                                oreSet.getDeepslateOre(),
                                LootItem.lootTableItem(drop)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between((float) 1, (float) 3)))
                                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                        )
                )
        );
    }

    private void addOreSet(GenerationsOreSet oreSet) {
        if (oreSet.getDrop() == null) return;
        add(oreSet.getOre(), createOreDrop(oreSet.getOre(), oreSet.getDrop()));
        add(oreSet.getDeepslateOre(), createOreDrop(oreSet.getDeepslateOre(), oreSet.getDrop()));
    }

    private void addOreSet(Block ore, Item drop) {
        add(ore, createOreDrop(ore, drop));
    }

    private void addAll(Block block, Block slab, Block stair, Block wall) {
        if (block != null) dropSelf(block);
        if (slab != null) add(slab, createSlabItemTable(slab));
        if (stair != null) dropSelf(stair);
        if (wall != null) dropSelf(wall);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {return knownBlocks;}

    private void dropSelfUpdated(@NotNull Block block) {
        if (block instanceof SlabBlock) add(block, createSlabItemTable(block));
        else if (block instanceof DoorBlock) add(block, createDoorTable(block));
        else if (block instanceof InfestedBlock) this.otherWhenSilkTouch(block, ((InfestedBlock) block).getHostBlock());
        else dropSelf(block);
    }

    private void dropSelfStoneCobble(@NotNull Block block) {
        if (block == GenerationsBlocks.VOLCANIC_STONE.get())
            add(block, createSingleItemTable(GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.getBaseBlock()));
        else dropSelfUpdated(block);
    }
    @Override
    protected void add(@NotNull Block block, LootTable.@NotNull Builder lootTableBuilder) {
        knownBlocks.add(block);
        super.add(block, lootTableBuilder);
    }

    /*
    protected void createDyedBlock(Block block) {
        add(block, LootTable.lootTable().withPool(
                this.applyExplosionCondition(block,
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0f))
                                .add(LootItem.lootTableItem(block)
                                        .apply(DyedBlockLootFunction.DUMMY)))));
    }

    protected <T extends Comparable<T> & StringRepresentable> void createDoubleDyedBlock(Block block, Property<T> property, T value) {
        add(block, LootTable.lootTable().withPool(
                this.applyExplosionCondition(block,
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0f))
                                .add(LootItem.lootTableItem(block)
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(property, value)))
                                        .apply(DyedBlockLootFunction.DUMMY)))));
    }

     */
}