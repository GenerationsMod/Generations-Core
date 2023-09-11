package generations.gg.generations.core.generationscore.forge.datagen.generators.loot;

import generations.gg.generations.core.generationscore.forge.datagen.generators.blocks.BlockDatagen;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.*;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
        GenerationsBlocks.POKEBRICKS.forEach(block -> dropSelfUpdated(block.get()));
        GenerationsBlocks.MARBLE.forEach(block -> dropSelfUpdated(block.get()));

        dropSelf(GenerationsBlocks.VOLCANIC_FIRESTONE.get());

        dropSelf(GenerationsBlocks.GOLDEN_TEMPLE_SAND.get());

        //Ores
        addOre(GenerationsOres.TUMBLESTONE_ORE.get(), GenerationsOres.DEEPSLATE_TUMBLESTONE_ORE.get(), GenerationsOres.CHARGE_STONE_TUMBLESTONE_ORE.get(), GenerationsItems.TUMBLESTONE.get());
        addOre(GenerationsOres.BLACK_TUMBLESTONE_ORE.get(), GenerationsOres.DEEPSLATE_BLACK_TUMBLESTONE_ORE.get(), GenerationsOres.CHARGE_STONE_BLACK_TUMBLESTONE_ORE.get(), GenerationsItems.BLACK_TUMBLESTONE.get());
        addOre(GenerationsOres.SKY_TUMBLESTONE_ORE.get(), GenerationsOres.DEEPSLATE_SKY_TUMBLESTONE_ORE.get(), GenerationsOres.CHARGE_STONE_SKY_TUMBLESTONE_ORE.get(), GenerationsItems.SKY_TUMBLESTONE.get());
        addOre(GenerationsOres.RARE_TUMBLESTONE_ORE.get(), GenerationsOres.DEEPSLATE_RARE_TUMBLESTONE_ORE.get(), GenerationsOres.CHARGE_STONE_RARE_TUMBLESTONE_ORE.get(), GenerationsItems.RARE_TUMBLESTONE.get());
        addOre(GenerationsOres.ALUMINUM_ORE.get(), GenerationsOres.DEEPSLATE_ALUMINUM_ORE.get(), GenerationsOres.CHARGE_STONE_ALUMINUM_ORE.get(), GenerationsItems.RAW_ALUMINUM.get());
        addOre(GenerationsOres.CRYSTAL_ORE.get(), GenerationsOres.DEEPSLATE_CRYSTAL_ORE.get(), GenerationsOres.CHARGE_STONE_CRYSTAL_ORE.get(), GenerationsItems.CRYSTAL.get());
        addOre(GenerationsOres.RUBY_ORE.get(), GenerationsOres.DEEPSLATE_RUBY_ORE.get(), GenerationsOres.CHARGE_STONE_RUBY_ORE.get(), GenerationsItems.RUBY.get());
        addOre(GenerationsOres.SAPPHIRE_ORE.get(), GenerationsOres.DEEPSLATE_SAPPHIRE_ORE.get(), GenerationsOres.CHARGE_STONE_SAPPHIRE_ORE.get(), GenerationsItems.SAPPHIRE.get());
        addOre(GenerationsOres.SILICON_ORE.get(), GenerationsOres.DEEPSLATE_SILICON_ORE.get(), GenerationsOres.CHARGE_STONE_SILICON_ORE.get(), GenerationsItems.SILICON.get());
        dropSelf(GenerationsOres.Z_CRYSTAL_ORE.get());
        dropSelf(GenerationsOres.DEEPSLATE_Z_CRYSTAL_ORE.get());
        dropSelf(GenerationsOres.CHARGE_STONE_Z_CRYSTAL_ORE.get());

        addOre(GenerationsOres.CHARGE_STONE_COAL_ORE.get(), Items.COAL);
        add(GenerationsOres.CHARGE_STONE_REDSTONE_ORE.get(), createRedstoneOreDrops(GenerationsOres.CHARGE_STONE_REDSTONE_ORE.get()));
        addOre(GenerationsOres.CHARGE_STONE_IRON_ORE.get(), Items.RAW_IRON);
        addOre(GenerationsOres.CHARGE_STONE_GOLD_ORE.get(), Items.RAW_GOLD);
        addOre(GenerationsOres.CHARGE_STONE_COPPER_ORE.get(), Items.RAW_COPPER);
        addOre(GenerationsOres.CHARGE_STONE_DIAMOND_ORE.get(), Items.DIAMOND);
        addOre(GenerationsOres.CHARGE_STONE_EMERALD_ORE.get(), Items.EMERALD);
        add(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE.get(), createLapisOreDrops(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE.get()));

        dropSelf(GenerationsBlocks.RAW_ALUMINUM_BLOCK.get());
        dropSelf(GenerationsBlocks.ALUMINUM_BLOCK.get());

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


        addOreWithRandomAmountDrops(GenerationsOres.MEGASTONE_ORE.get(), GenerationsOres.DEEPSLATE_MEGASTONE_ORE.get(), GenerationsOres.CHARGE_STONE_MEGASTONE_ORE.get(), GenerationsItems.MEGASTONE_SHARD.get());
        addOreWithRandomAmountDrops(GenerationsOres.METEORITE_ORE.get(), GenerationsOres.DEEPSLATE_METEORITE_ORE.get(), GenerationsOres.CHARGE_STONE_METEORITE_ORE.get(), GenerationsItems.METEORITE_SHARD.get());

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


        dropSelf(GenerationsOres.FOSSIL_ORE.get());

        dropSelf(GenerationsOres.DEEPSLATE_FOSSIL_ORE.get());

        dropSelf(GenerationsOres.CHARGE_STONE_FOSSIL_ORE.get());

        dropSelf(GenerationsBlocks.POKEMART_SIGN.get());
        dropSelf(GenerationsBlocks.POKECENTER_SIGN.get());

        dropSelf(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get());

        dropDisplayStandWithBall(GenerationsDecorationBlocks.POKE_BALL_DISPLAY.get(), GenerationsItems.POKE_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.GREAT_BALL_DISPLAY.get(), GenerationsItems.GREAT_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.ULTRA_BALL_DISPLAY.get(), GenerationsItems.ULTRA_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.MASTER_BALL_DISPLAY.get(), GenerationsItems.MASTER_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.CHERISH_BALL_DISPLAY.get(), GenerationsItems.CHERISH_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.DIVE_BALL_DISPLAY.get(), GenerationsItems.DIVE_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.DUSK_BALL_DISPLAY.get(), GenerationsItems.DUSK_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.FAST_BALL_DISPLAY.get(), GenerationsItems.FAST_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.FRIEND_BALL_DISPLAY.get(), GenerationsItems.FRIEND_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.GS_BALL_DISPLAY.get(), GenerationsItems.GS_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.HEAL_BALL_DISPLAY.get(), GenerationsItems.HEAL_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.HEAVY_BALL_DISPLAY.get(), GenerationsItems.HEAVY_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.LEVEL_BALL_DISPLAY.get(), GenerationsItems.LEVEL_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.LOVE_BALL_DISPLAY.get(), GenerationsItems.LOVE_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.LURE_BALL_DISPLAY.get(), GenerationsItems.LURE_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.LUXURY_BALL_DISPLAY.get(), GenerationsItems.LUXURY_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.MOON_BALL_DISPLAY.get(), GenerationsItems.MOON_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.NEST_BALL_DISPLAY.get(), GenerationsItems.NEST_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.NET_BALL_DISPLAY.get(), GenerationsItems.NET_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.PARK_BALL_DISPLAY.get(), GenerationsItems.PARK_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.PREMIER_BALL_DISPLAY.get(), GenerationsItems.PREMIER_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.QUICK_BALL_DISPLAY.get(), GenerationsItems.QUICK_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.REPEAT_BALL_DISPLAY.get(), GenerationsItems.REPEAT_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.SAFARI_BALL_DISPLAY.get(), GenerationsItems.SAFARI_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.SPORT_BALL_DISPLAY.get(), GenerationsItems.SPORT_BALL.get());
        dropDisplayStandWithBall(GenerationsDecorationBlocks.TIMER_BALL_DISPLAY.get(), GenerationsItems.TIMER_BALL.get());
    }

    private void dropDisplayStandWithBall(Block block, Item item) {
        add(block, LootTable
                .lootTable()
                .withPool(applyExplosionCondition(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get(),
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get()))))
                .withPool(applyExplosionCondition(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get(),
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(item)))));
    }


    private void addOreWithRandomAmountDrops(Block ore, Block deepslateOre, Block chargestoneOre, Item drop) {
        add(
                ore,
                createSilkTouchDispatchTable(
                        ore,
                        applyExplosionDecay(
                                ore,
                                LootItem.lootTableItem(drop)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between((float) 1, (float) 3)))
                                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                        )
                )
        );
        add(
                deepslateOre,
                createSilkTouchDispatchTable(
                        deepslateOre,
                        applyExplosionDecay(
                                deepslateOre,
                                LootItem.lootTableItem(drop)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between((float) 1, (float) 3)))
                                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                        )
                )
        );
        add(
                chargestoneOre,
                createSilkTouchDispatchTable(
                        chargestoneOre,
                        applyExplosionDecay(
                                chargestoneOre,
                                LootItem.lootTableItem(drop)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between((float) 1, (float) 3)))
                                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                        )
                )
        );
    }

    private void addOre(Block ore, Block Deepslateore, Block chargeStoneOre, Item drop) {
        add(ore, createOreDrop(ore, drop));
        add(Deepslateore, createOreDrop(Deepslateore, drop));
        add(chargeStoneOre, createOreDrop(chargeStoneOre, drop));
    }

    private void addOre(Block ore, Item drop) {
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
        if (block == GenerationsBlocks.CHARGE_STONE.get())
            add(block, createSingleItemTable(GenerationsBlocks.CHARGE_COBBLESTONE.get()));
        else if (block == GenerationsBlocks.VOLCANIC_STONE.get())
            add(block, createSingleItemTable(GenerationsBlocks.VOLCANIC_COBBLESTONE.get()));
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