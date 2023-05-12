package generations.gg.generations.core.generationscore.forge.datagen.generators.loot;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.forge.datagen.generators.blocks.BlockDatagen;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsOres;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWood;
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

    private final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private final List<Block> knownBlocks = new ArrayList<>();

    protected GenerationsBlockLoot() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        GenerationsWood.WOOD_BLOCKS.forEach(block -> dropSelfUpdated(block.get()));

        GenerationsBlocks.ULTRA_BLOCKS.forEach(block -> dropSelfUpdated(block.get()));
        //createDoubleDyedBlock(GenerationsUtilityBlocks.PC.get(), PCBlock.HALF, DoubleBlockHalf.LOWER);
        //createDyedBlock(GenerationsUtilityBlocks.CLOCK.get());
        //createDyedBlock(GenerationsDecorationBlocks.UMBRELLA.get());
        //createDyedBlock(GenerationsDecorationBlocks.PASTEL_BEAN_BAG.get());
        //createDyedBlock(GenerationsDecorationBlocks.VENDING_MACHINE.get());
        //GenerationsDecorationBlocks.VENDING_MACHINE_BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> add(block, createSinglePropConditionTable(block, DoubleDyeableBlock.HALF, DoubleBlockHalf.LOWER)));
        //GenerationsPokeDolls.POKEDOLLS.getEntries().stream().map(RegistryObject::get).forEach(this::dropSelf);
        BlockDatagen.dropSelfList.stream().map(block -> (Block) block).forEach(this::dropSelf);
        BlockDatagen.MUSHROOM_BLOCKS.forEach(block -> add(block, createMushroomBlockDrop(block, block.asItem())));
        GenerationsBlocks.POKEBRICKS.forEach(block -> dropSelfUpdated(block.get()));
        GenerationsBlocks.MARBLE.forEach(block -> dropSelfUpdated(block.get()));
        //GenerationsBlocks.STONE.getEntries().stream().map(RegistryObject::get).forEach(this::dropSelfStoneCobble);


        add(GenerationsBlocks.BLACK_APRICORN_LEAVES.get(), createLeavesDrops(GenerationsBlocks.BLACK_APRICORN_LEAVES.get(), GenerationsBlocks.BLACK_APRICORN.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        add(GenerationsBlocks.WHITE_APRICORN_LEAVES.get(), createLeavesDrops(GenerationsBlocks.WHITE_APRICORN_LEAVES.get(), GenerationsBlocks.WHITE_APRICORN.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        add(GenerationsBlocks.PINK_APRICORN_LEAVES.get(), createLeavesDrops(GenerationsBlocks.PINK_APRICORN_LEAVES.get(), GenerationsBlocks.PINK_APRICORN.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        add(GenerationsBlocks.GREEN_APRICORN_LEAVES.get(), createLeavesDrops(GenerationsBlocks.GREEN_APRICORN_LEAVES.get(), GenerationsBlocks.GREEN_APRICORN.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        add(GenerationsBlocks.BLUE_APRICORN_LEAVES.get(), createLeavesDrops(GenerationsBlocks.BLUE_APRICORN_LEAVES.get(), GenerationsBlocks.BLUE_APRICORN.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        add(GenerationsBlocks.YELLOW_APRICORN_LEAVES.get(), createLeavesDrops(GenerationsBlocks.YELLOW_APRICORN_LEAVES.get(), GenerationsBlocks.YELLOW_APRICORN.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        add(GenerationsBlocks.RED_APRICORN_LEAVES.get(), createLeavesDrops(GenerationsBlocks.RED_APRICORN_LEAVES.get(), GenerationsBlocks.RED_APRICORN.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        dropSelf(GenerationsBlocks.VOLCANIC_FIRESTONE.get());

        dropSelf(GenerationsBlocks.BLUE_PICKET_FENCE.get());
        dropSelf(GenerationsBlocks.BLUE_PICKET_FENCE_GATE.get());
        dropSelf(GenerationsBlocks.WHITE_PICKET_FENCE.get());
        dropSelf(GenerationsBlocks.WHITE_PICKET_FENCE_GATE.get());
        dropSelf(GenerationsBlocks.RED_PICKET_FENCE.get());
        dropSelf(GenerationsBlocks.RED_PICKET_FENCE_GATE.get());
        dropSelf(GenerationsBlocks.YELLOW_PICKET_FENCE.get());
        dropSelf(GenerationsBlocks.YELLOW_PICKET_FENCE_GATE.get());

        dropSelf(GenerationsBlocks.GOLDEN_TEMPLE_SAND.get());

        //Ores
        addOre(GenerationsOres.TUMBLESTONE_ORE, GenerationsOres.DEEPSLATE_TUMBLESTONE_ORE, GenerationsOres.CHARGE_STONE_TUMBLESTONE_ORE, GenerationsItems.TUMBLESTONE);
        addOre(GenerationsOres.BLACK_TUMBLESTONE_ORE, GenerationsOres.DEEPSLATE_BLACK_TUMBLESTONE_ORE, GenerationsOres.CHARGE_STONE_BLACK_TUMBLESTONE_ORE, GenerationsItems.BLACK_TUMBLESTONE);
        addOre(GenerationsOres.SKY_TUMBLESTONE_ORE, GenerationsOres.DEEPSLATE_SKY_TUMBLESTONE_ORE, GenerationsOres.CHARGE_STONE_SKY_TUMBLESTONE_ORE, GenerationsItems.SKY_TUMBLESTONE);
        addOre(GenerationsOres.RARE_TUMBLESTONE_ORE, GenerationsOres.DEEPSLATE_RARE_TUMBLESTONE_ORE, GenerationsOres.CHARGE_STONE_RARE_TUMBLESTONE_ORE, GenerationsItems.RARE_TUMBLESTONE);
        addOre(GenerationsOres.ALUMINUM_ORE, GenerationsOres.DEEPSLATE_ALUMINUM_ORE, GenerationsOres.CHARGE_STONE_ALUMINUM_ORE, GenerationsItems.RAW_ALUMINUM);
        addOre(GenerationsOres.CRYSTAL_ORE, GenerationsOres.DEEPSLATE_CRYSTAL_ORE, GenerationsOres.CHARGE_STONE_CRYSTAL_ORE, GenerationsItems.CRYSTAL);
        addOre(GenerationsOres.RUBY_ORE, GenerationsOres.DEEPSLATE_RUBY_ORE, GenerationsOres.CHARGE_STONE_RUBY_ORE, GenerationsItems.RUBY);
        addOre(GenerationsOres.SAPPHIRE_ORE, GenerationsOres.DEEPSLATE_SAPPHIRE_ORE, GenerationsOres.CHARGE_STONE_SAPPHIRE_ORE, GenerationsItems.SAPPHIRE);
        addOre(GenerationsOres.SILICON_ORE, GenerationsOres.DEEPSLATE_SILICON_ORE, GenerationsOres.CHARGE_STONE_SILICON_ORE, GenerationsItems.SILICON);
        dropSelf(GenerationsOres.Z_CRYSTAL_ORE.get());
        dropSelf(GenerationsOres.DEEPSLATE_Z_CRYSTAL_ORE.get());
        dropSelf(GenerationsOres.CHARGE_STONE_Z_CRYSTAL_ORE.get());

        addOre(GenerationsOres.CHARGE_STONE_COAL_ORE, Items.COAL);
        add(GenerationsOres.CHARGE_STONE_REDSTONE_ORE.get(), createRedstoneOreDrops(GenerationsOres.CHARGE_STONE_REDSTONE_ORE.get()));
        addOre(GenerationsOres.CHARGE_STONE_IRON_ORE, Items.RAW_IRON);
        addOre(GenerationsOres.CHARGE_STONE_GOLD_ORE, Items.RAW_GOLD);
        addOre(GenerationsOres.CHARGE_STONE_COPPER_ORE, Items.RAW_COPPER);
        addOre(GenerationsOres.CHARGE_STONE_DIAMOND_ORE, Items.DIAMOND);
        addOre(GenerationsOres.CHARGE_STONE_EMERALD_ORE, Items.EMERALD);
        add(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE.get(), createLapisOreDrops(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE.get()));

        dropSelf(GenerationsBlocks.RAW_ALUMINUM_BLOCK.get());
        dropSelf(GenerationsBlocks.ALUMINUM_BLOCK.get());

        addAll(GenerationsBlocks.RUBY_BLOCK, GenerationsBlocks.RUBY_SLAB, GenerationsBlocks.RUBY_STAIRS, GenerationsBlocks.RUBY_WALL);

        addAll(GenerationsBlocks.SAPPHIRE_BLOCK, GenerationsBlocks.SAPPHIRE_SLAB, GenerationsBlocks.SAPPHIRE_STAIRS, GenerationsBlocks.SAPPHIRE_WALL);

        addAll(GenerationsBlocks.CRYSTAL_BLOCK, GenerationsBlocks.CRYSTAL_SLAB, GenerationsBlocks.CRYSTAL_STAIRS, GenerationsBlocks.CRYSTAL_WALL);

        dropSelf(GenerationsBlocks.SILICON_BLOCK.get());

        //Ultra Space
        dropSelf(GenerationsBlocks.ULTRA_SAND.get());
        dropWhenSilkTouch(GenerationsBlocks.GHOST_LANTERN.get());
        add(GenerationsBlocks.MACHINE_BLOCK.get(), createSingleItemTable(GenerationsBlocks.MACHINE_BLOCK.get()));
        dropSelf(GenerationsBlocks.RUINS_SAND.get());
        dropSelf(GenerationsBlocks.SOFT_SOIL.get());
        dropSelf(GenerationsBlocks.RICH_SOIL_1.get());
        dropSelf(GenerationsBlocks.RICH_SOIL_2.get());
        dropSelf(GenerationsBlocks.RICH_SOIL_3.get());
        dropSelf(GenerationsBlocks.RICH_SOIL_4.get());

        add(GenerationsBlocks.POKEBALL_CHEST.get(), createNameableBlockEntityTable(GenerationsBlocks.POKEBALL_CHEST.get()));
        add(GenerationsBlocks.GREATBALL_CHEST.get(), createNameableBlockEntityTable(GenerationsBlocks.GREATBALL_CHEST.get()));
        add(GenerationsBlocks.ULTRABALL_CHEST.get(), createNameableBlockEntityTable(GenerationsBlocks.ULTRABALL_CHEST.get()));
        add(GenerationsBlocks.MASTERBALL_CHEST.get(), createNameableBlockEntityTable(GenerationsBlocks.MASTERBALL_CHEST.get()));


        addOreWithRandomAmountDrops(GenerationsOres.DAWN_STONE_ORE, GenerationsOres.DEEPSLATE_DAWN_STONE_ORE, GenerationsOres.CHARGE_STONE_DAWN_STONE_ORE, GenerationsItems.DAWN_STONE_SHARD);
        addOreWithRandomAmountDrops(GenerationsOres.DUSK_STONE_ORE, GenerationsOres.DEEPSLATE_DUSK_STONE_ORE, GenerationsOres.CHARGE_STONE_DUSK_STONE_ORE, GenerationsItems.DUSK_STONE_SHARD);
        addOreWithRandomAmountDrops(GenerationsOres.FIRE_STONE_ORE, GenerationsOres.DEEPSLATE_FIRE_STONE_ORE, GenerationsOres.CHARGE_STONE_FIRE_STONE_ORE, GenerationsItems.FIRE_STONE_SHARD);
        addOreWithRandomAmountDrops(GenerationsOres.ICE_STONE_ORE, GenerationsOres.DEEPSLATE_ICE_STONE_ORE, GenerationsOres.CHARGE_STONE_ICE_STONE_ORE, GenerationsItems.ICE_STONE_SHARD);
        addOreWithRandomAmountDrops(GenerationsOres.LEAF_STONE_ORE, GenerationsOres.DEEPSLATE_LEAF_STONE_ORE, GenerationsOres.CHARGE_STONE_LEAF_STONE_ORE, GenerationsItems.LEAF_STONE_SHARD);
        addOreWithRandomAmountDrops(GenerationsOres.MOON_STONE_ORE, GenerationsOres.DEEPSLATE_MOON_STONE_ORE, GenerationsOres.CHARGE_STONE_MOON_STONE_ORE,GenerationsItems.MOON_STONE_SHARD);
        addOreWithRandomAmountDrops(GenerationsOres.SUN_STONE_ORE, GenerationsOres.DEEPSLATE_SUN_STONE_ORE, GenerationsOres.CHARGE_STONE_SUN_STONE_ORE, GenerationsItems.SUN_STONE_SHARD);
        addOreWithRandomAmountDrops(GenerationsOres.THUNDER_STONE_ORE, GenerationsOres.DEEPSLATE_THUNDER_STONE_ORE, GenerationsOres.CHARGE_STONE_THUNDER_STONE_ORE, GenerationsItems.THUNDER_STONE_SHARD);
        addOreWithRandomAmountDrops(GenerationsOres.WATER_STONE_ORE, GenerationsOres.DEEPSLATE_WATER_STONE_ORE, GenerationsOres.CHARGE_STONE_WATER_STONE_ORE, GenerationsItems.WATER_STONE_SHARD);
        addOreWithRandomAmountDrops(GenerationsOres.SHINY_STONE_ORE, GenerationsOres.DEEPSLATE_SHINY_STONE_ORE, GenerationsOres.CHARGE_STONE_SHINY_STONE_ORE, GenerationsItems.SHINY_STONE_SHARD);
        addOreWithRandomAmountDrops(GenerationsOres.MEGASTONE_ORE, GenerationsOres.DEEPSLATE_MEGASTONE_ORE, GenerationsOres.CHARGE_STONE_MEGASTONE_ORE, GenerationsItems.MEGASTONE_SHARD);
        addOreWithRandomAmountDrops(GenerationsOres.METEORITE_ORE, GenerationsOres.DEEPSLATE_METEORITE_ORE, GenerationsOres.CHARGE_STONE_METEORITE_ORE, GenerationsItems.METEORITE_SHARD);

        //Pumpkins
        dropSelf(GenerationsBlocks.CURSED_PUMPKIN.get());
        dropSelf(GenerationsBlocks.CURSED_CARVED_PUMPKIN.get());
        dropSelf(GenerationsBlocks.CURSED_JACK_O_LANTERN.get());

        add(
                GenerationsOres.FOSSIL_ORE.get(),
                LootTable
                        .lootTable()
                        .withPool(
                                applyExplosionCondition(GenerationsOres.FOSSIL_ORE.get(),
                                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_OLD_AMBER.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_ARMOR_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_BIRD_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_CLAW_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_COVER_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_DINO_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_DOME_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_DRAKE_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_FISH_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_HELIX_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_JAW_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_PLUME_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_ROOT_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_SAIL_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_SKULL_FOSSIL.get()))
                                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                                ))
        );
        add(
                GenerationsOres.DEEPSLATE_FOSSIL_ORE.get(),
                LootTable
                        .lootTable()
                        .withPool(
                                applyExplosionCondition(GenerationsOres.FOSSIL_ORE.get(),
                                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_OLD_AMBER.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_ARMOR_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_BIRD_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_CLAW_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_COVER_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_DINO_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_DOME_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_DRAKE_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_FISH_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_HELIX_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_JAW_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_PLUME_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_ROOT_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_SAIL_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_SKULL_FOSSIL.get()))
                                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                                ))
        );

        add(
                GenerationsOres.CHARGE_STONE_FOSSIL_ORE.get(),
                LootTable
                        .lootTable()
                        .withPool(
                                applyExplosionCondition(GenerationsOres.FOSSIL_ORE.get(),
                                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_OLD_AMBER.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_ARMOR_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_BIRD_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_CLAW_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_COVER_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_DINO_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_DOME_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_DRAKE_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_FISH_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_HELIX_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_JAW_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_PLUME_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_ROOT_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_SAIL_FOSSIL.get()))
                                                .add(LootItem.lootTableItem(GenerationsItems.COVERED_SKULL_FOSSIL.get()))
                                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                                ))
        );

        dropSelf(GenerationsBlocks.POKE_GRASS.get());
        dropSelf(GenerationsBlocks.POKE_DIRT.get());
        dropSelf(GenerationsBlocks.POKE_SAND.get());
        dropSelf(GenerationsBlocks.POKE_SAND_SMALL_CORNER_1.get());
        dropSelf(GenerationsBlocks.POKE_SAND_SMALL_CORNER_2.get());
        dropSelf(GenerationsBlocks.POKE_SAND_CORNER_1.get());
        dropSelf(GenerationsBlocks.POKE_SAND_CORNER_2.get());
        dropSelf(GenerationsBlocks.POKE_SAND_CORNER_3.get());
        dropSelf(GenerationsBlocks.POKE_SAND_CORNER_4.get());
        dropSelf(GenerationsBlocks.POKE_SAND_SIDE_1.get());
        dropSelf(GenerationsBlocks.POKE_SAND_SIDE_2.get());
        dropSelf(GenerationsBlocks.POKE_SAND_SIDE_3.get());
        dropSelf(GenerationsBlocks.POKE_SAND_SIDE_4.get());
        dropSelf(GenerationsBlocks.SANDY_GRASS.get());
        dropSelf(GenerationsBlocks.SHINGLES.get());
        dropSelf(GenerationsBlocks.SHINGLES_CORNER_1.get());
        dropSelf(GenerationsBlocks.SHINGLES_CORNER_2.get());
        dropSelf(GenerationsBlocks.INSIDE_WALL.get());
        dropSelf(GenerationsBlocks.INSIDE_WALL_MOLDING.get());
        dropSelf(GenerationsBlocks.TREE_TOP.get());
        dropSelf(GenerationsBlocks.TREE_BOTTOM.get());
        dropSelf(GenerationsBlocks.WOODEN_FLOORING.get());
        dropSelf(GenerationsBlocks.POKEMART_SIGN.get());
        dropSelf(GenerationsBlocks.POKECENTER_SIGN.get());

        dropWhenSilkTouch(GenerationsBlocks.WINDOW_1.get());
        dropWhenSilkTouch(GenerationsBlocks.WINDOW_2.get());
    }



    private void addOreWithRandomAmountDrops(RegistrySupplier<DropExperienceBlock> ore, RegistrySupplier<DropExperienceBlock> deepslateOre, RegistrySupplier<DropExperienceBlock> chargestoneOre, RegistrySupplier<Item> drop) {
        add(
                ore.get(),
                createSilkTouchDispatchTable(
                        ore.get(),
                        applyExplosionDecay(
                                ore.get(),
                                LootItem.lootTableItem(drop.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between((float) 1, (float) 3)))
                                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                        )
                )
        );
        add(
                deepslateOre.get(),
                createSilkTouchDispatchTable(
                        deepslateOre.get(),
                        applyExplosionDecay(
                                deepslateOre.get(),
                                LootItem.lootTableItem(drop.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between((float) 1, (float) 3)))
                                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                        )
                )
        );
        add(
                chargestoneOre.get(),
                createSilkTouchDispatchTable(
                        chargestoneOre.get(),
                        applyExplosionDecay(
                                chargestoneOre.get(),
                                LootItem.lootTableItem(drop.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between((float) 1, (float) 3)))
                                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                        )
                )
        );
    }

    private void addOre(RegistrySupplier<DropExperienceBlock> ore, RegistrySupplier<DropExperienceBlock> Deepslateore, RegistrySupplier<DropExperienceBlock> chargeStoneOre, RegistrySupplier<Item> drop) {
        add(ore.get(), createOreDrop(ore.get(), drop.get()));
        add(Deepslateore.get(), createOreDrop(Deepslateore.get(), drop.get()));
        add(chargeStoneOre.get(), createOreDrop(chargeStoneOre.get(), drop.get()));
    }

    private <T extends Block> void addOre(RegistrySupplier<T> ore, Item drop) {
        add(ore.get(), createOreDrop(ore.get(), drop));
    }

    private void addAll(RegistrySupplier<Block> block, RegistrySupplier<SlabBlock> slab, RegistrySupplier<StairBlock> stair, RegistrySupplier<WallBlock> wall) {
        if (block != null) dropSelf(block.get());
        if (slab != null) add(slab.get(), createSlabItemTable(slab.get()));
        if (stair != null) dropSelf(stair.get());
        if (wall != null) dropSelf(wall.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {return knownBlocks;}

    protected void dropSelfUpdated(@NotNull Block block) {
        if (block instanceof SlabBlock) add(block, createSlabItemTable(block));
        else if (block instanceof DoorBlock) add(block, createDoorTable(block));
        else if (block instanceof InfestedBlock) this.otherWhenSilkTouch(block, ((InfestedBlock) block).getHostBlock());
        else dropSelf(block);
    }

    protected void dropSelfStoneCobble(Block block) {
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