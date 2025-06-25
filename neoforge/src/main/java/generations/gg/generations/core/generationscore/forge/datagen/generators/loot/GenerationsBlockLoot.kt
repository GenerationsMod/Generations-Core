package generations.gg.generations.core.generationscore.forge.datagen.generators.loot

import com.cobblemon.mod.common.CobblemonItems.CHERISH_BALL
import com.cobblemon.mod.common.CobblemonItems.DIVE_BALL
import com.cobblemon.mod.common.CobblemonItems.DUSK_BALL
import com.cobblemon.mod.common.CobblemonItems.FAST_BALL
import com.cobblemon.mod.common.CobblemonItems.FRIEND_BALL
import com.cobblemon.mod.common.CobblemonItems.GREAT_BALL
import com.cobblemon.mod.common.CobblemonItems.HEAL_BALL
import com.cobblemon.mod.common.CobblemonItems.HEAVY_BALL
import com.cobblemon.mod.common.CobblemonItems.LEVEL_BALL
import com.cobblemon.mod.common.CobblemonItems.LOVE_BALL
import com.cobblemon.mod.common.CobblemonItems.LURE_BALL
import com.cobblemon.mod.common.CobblemonItems.LUXURY_BALL
import com.cobblemon.mod.common.CobblemonItems.MASTER_BALL
import com.cobblemon.mod.common.CobblemonItems.MOON_BALL
import com.cobblemon.mod.common.CobblemonItems.NEST_BALL
import com.cobblemon.mod.common.CobblemonItems.NET_BALL
import com.cobblemon.mod.common.CobblemonItems.PARK_BALL
import com.cobblemon.mod.common.CobblemonItems.POKE_BALL
import com.cobblemon.mod.common.CobblemonItems.PREMIER_BALL
import com.cobblemon.mod.common.CobblemonItems.QUICK_BALL
import com.cobblemon.mod.common.CobblemonItems.REPEAT_BALL
import com.cobblemon.mod.common.CobblemonItems.SAFARI_BALL
import com.cobblemon.mod.common.CobblemonItems.SPORT_BALL
import com.cobblemon.mod.common.CobblemonItems.TIMER_BALL
import com.cobblemon.mod.common.CobblemonItems.ULTRA_BALL
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.*
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsBlockSet
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsFullBlockSet
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsOreSet
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsUltraBlockSet
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.PrisonBottleStemBlock
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.PrisonBottleStemBlock.PrisonBottleState
import generations.gg.generations.core.generationscore.forge.datagen.generators.blocks.BlockDatagen
import generations.gg.generations.core.generationscore.forge.datagen.generators.blocks.asValue
import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.resources.ResourceKey
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.item.Item
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.DoorBlock
import net.minecraft.world.level.block.InfestedBlock
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import java.util.*
import java.util.function.BiConsumer
import java.util.function.Consumer

internal class GenerationsBlockLoot(provider: HolderLookup.Provider) : BlockLootSubProvider(setOf<Item>(), FeatureFlags.REGISTRY.allFlags(), provider) {
    private val knownBlocks: MutableList<Block> = ArrayList()
    private var fortune: Holder.Reference<Enchantment>? = null

    override fun generate() {
        this.fortune = registries.lookupOrThrow(Registries.ENCHANTMENT)[Enchantments.FORTUNE].orElseThrow()

        GenerationsBlocks.STONE.all().forEach(::dropSelfStoneCobble)
        GenerationsWood.WOOD_BLOCKS.all().forEach(::dropSelfUpdated)

        GenerationsBlocks.ULTRA_BLOCKS.all().forEach(::dropSelfUpdated)
        //createDoubleDyedBlock(GenerationsUtilityBlocks.PC, PCBlock.HALF, DoubleBlockHalf.LOWER);
        //createDyedBlock(GenerationsUtilityBlocks.CLOCK);
        //createDyedBlock(GenerationsDecorationBlocks.UMBRELLA);
        //createDyedBlock(GenerationsDecorationBlocks.PASTEL_BEAN_BAG);
        //createDyedBlock(GenerationsDecorationBlocks.VENDING_MACHINE);
        //GenerationsDecorationBlocks.VENDING_MACHINE_BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> add(block, createSinglePropConditionTable(block, DoubleDyeableBlock.HALF, DoubleBlockHalf.LOWER)));
        GenerationsPokeDolls.all().forEach(::dropSelf)
        BlockDatagen.dropSelfList.stream().map { block -> block as Block }.forEach(this::dropSelf)
        dropSelf(GenerationsShrines.DARK_CRYSTAL)
        dropSelf(GenerationsShrines.LIGHT_CRYSTAL)
        BlockDatagen.MUSHROOM_BLOCKS.forEach(Consumer { block: GenerationsMushroomBlock ->
            add(
                block,
                createMushroomBlockDrop(block, block.asItem())
            )
        })
        GenerationsFullBlockSet.fullBlockSets
            .forEach(Consumer { generationsFullBlockSet: GenerationsFullBlockSet ->
                generationsFullBlockSet.allBlocks.forEach(
                    Consumer { block: Block -> this.dropSelfUpdated(block) })
            })
        GenerationsBlockSet.blockSets.forEach(Consumer { generationsBlockSet: GenerationsBlockSet ->
            generationsBlockSet.allBlocks.forEach(
                Consumer { block: Block -> this.dropSelfUpdated(block) })
        })
        GenerationsUltraBlockSet.ultraBlockSets.forEach(Consumer { generationsBlockSet: GenerationsUltraBlockSet ->
            generationsBlockSet.allBlocks.forEach(
                Consumer { block: Block -> this.dropSelfUpdated(block) })
        })

        add(
            GenerationsBlocks.CHARGE_STONE_SET.baseBlock,
            createSingleItemTable(GenerationsBlocks.CHARGE_COBBLESTONE_SET.baseBlock)
        )

        dropSelf(GenerationsBlocks.VOLCANIC_FIRESTONE)

        dropSelf(GenerationsBlocks.GOLDEN_TEMPLE_SAND)

        //Ores
        addOreSet(GenerationsOres.CRYSTAL_ORE_SET)
        addOreSet(GenerationsOres.RUBY_ORE_SET)
        addOreSet(GenerationsOres.SAPPHIRE_ORE_SET)
        addOreSet(GenerationsOres.SILICON_ORE_SET)

        dropSelf(GenerationsOres.Z_CRYSTAL_ORE_SET.ore)
        dropSelf(GenerationsOres.Z_CRYSTAL_ORE_SET.deepslateOre)

        //dropSelf(GenerationsOres.CHARGE_STONE_Z_CRYSTAL_ORE);
        addAll(
            GenerationsBlocks.RUBY_BLOCK.asValue(),
            GenerationsBlocks.RUBY_SLAB.asValue(),
            GenerationsBlocks.RUBY_STAIRS.asValue(),
            GenerationsBlocks.RUBY_WALL.asValue()
        )

        addAll(
            GenerationsBlocks.SAPPHIRE_BLOCK.asValue(),
            GenerationsBlocks.SAPPHIRE_SLAB.asValue(),
            GenerationsBlocks.SAPPHIRE_STAIRS.asValue(),
            GenerationsBlocks.SAPPHIRE_WALL.asValue()
        )

        addAll(
            GenerationsBlocks.CRYSTAL_BLOCK.asValue(),
            GenerationsBlocks.CRYSTAL_SLAB.asValue(),
            GenerationsBlocks.CRYSTAL_STAIRS.asValue(),
            GenerationsBlocks.CRYSTAL_WALL.asValue()
        )

        dropSelf(GenerationsBlocks.SILICON_BLOCK)
        dropSelf(GenerationsBlocks.Z_BLOCK)

        //Ultra Space
        dropSelf(GenerationsBlocks.ULTRA_SAND)
        dropWhenSilkTouch(GenerationsBlocks.GHOST_LANTERN.value())
        add(GenerationsBlocks.MACHINE_BLOCK.value(), createSingleItemTable(GenerationsBlocks.MACHINE_BLOCK.value()))
        dropSelf(GenerationsBlocks.RUINS_SAND)
        dropSelf(GenerationsBlocks.RICH_SOIL_1)
        dropSelf(GenerationsBlocks.RICH_SOIL_2)
        dropSelf(GenerationsBlocks.RICH_SOIL_3)
        dropSelf(GenerationsBlocks.RICH_SOIL_4)

        add(
            GenerationsBlocks.POKEBALL_CHEST.value(),
            createNameableBlockEntityTable(GenerationsBlocks.POKEBALL_CHEST.value())
        )
        add(
            GenerationsBlocks.GREATBALL_CHEST.value(),
            createNameableBlockEntityTable(GenerationsBlocks.GREATBALL_CHEST.value())
        )
        add(
            GenerationsBlocks.ULTRABALL_CHEST.value(),
            createNameableBlockEntityTable(GenerationsBlocks.ULTRABALL_CHEST.value())
        )
        add(
            GenerationsBlocks.MASTERBALL_CHEST.value(),
            createNameableBlockEntityTable(GenerationsBlocks.MASTERBALL_CHEST.value())
        )


        addOreSetWithRandomAmountDrops(GenerationsOres.MEGASTONE_ORE_SET, GenerationsItems.MEGASTONE_SHARD.value())
        addOreSetWithRandomAmountDrops(GenerationsOres.METEORITE_ORE_SET, GenerationsItems.METEORITE_SHARD.value())

        //Pumpkins
        dropSelf(GenerationsBlocks.CURSED_PUMPKIN)
        dropSelf(GenerationsBlocks.CURSED_CARVED_PUMPKIN)
        dropSelf(GenerationsBlocks.CURSED_JACK_O_LANTERN)

        //Furnaces
        add(
            GenerationsUtilityBlocks.CHARGE_STONE_FURNACE.value(),
            createNameableBlockEntityTable(GenerationsUtilityBlocks.CHARGE_STONE_FURNACE.value())
        )
        add(
            GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE.value(),
            createNameableBlockEntityTable(GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE.value())
        )
        add(
            GenerationsUtilityBlocks.CHARGE_STONE_SMOKER.value(),
            createNameableBlockEntityTable(GenerationsUtilityBlocks.CHARGE_STONE_SMOKER.value())
        )
        add(
            GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE.value(),
            createNameableBlockEntityTable(GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE.value())
        )
        add(
            GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE.value(),
            createNameableBlockEntityTable(GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE.value())
        )
        add(
            GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER.value(),
            createNameableBlockEntityTable(GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER.value())
        )



        dropSelf(GenerationsBlocks.POKEMART_SIGN)
        dropSelf(GenerationsBlocks.POKECENTER_SIGN)

        dropSelf(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY)

        dropDisplayStandWithBall(GenerationsDecorationBlocks.POKE_BALL_DISPLAY, POKE_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.GREAT_BALL_DISPLAY, GREAT_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.ULTRA_BALL_DISPLAY, ULTRA_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.MASTER_BALL_DISPLAY, MASTER_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.CHERISH_BALL_DISPLAY, CHERISH_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.DIVE_BALL_DISPLAY, DIVE_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.DUSK_BALL_DISPLAY, DUSK_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.FAST_BALL_DISPLAY, FAST_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.FRIEND_BALL_DISPLAY, FRIEND_BALL.asItem())
        //dropDisplayStandWithBall(GenerationsDecorationBlocks.GS_BALL_DISPLAY, null);
        dropDisplayStandWithBall(GenerationsDecorationBlocks.HEAL_BALL_DISPLAY, HEAL_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.HEAVY_BALL_DISPLAY, HEAVY_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.LEVEL_BALL_DISPLAY, LEVEL_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.LOVE_BALL_DISPLAY, LOVE_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.LURE_BALL_DISPLAY, LURE_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.LUXURY_BALL_DISPLAY, LUXURY_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.MOON_BALL_DISPLAY, MOON_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.NEST_BALL_DISPLAY, NEST_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.NET_BALL_DISPLAY, NET_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.PARK_BALL_DISPLAY, PARK_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.PREMIER_BALL_DISPLAY, PREMIER_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.QUICK_BALL_DISPLAY, QUICK_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.REPEAT_BALL_DISPLAY, REPEAT_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.SAFARI_BALL_DISPLAY, SAFARI_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.SPORT_BALL_DISPLAY, SPORT_BALL.asItem())
        dropDisplayStandWithBall(GenerationsDecorationBlocks.TIMER_BALL_DISPLAY, TIMER_BALL.asItem())

        createGenericRotationModelBlockTable(GenerationsUtilityBlocks.RKS_MACHINE.asValue())
        createGenericRotationModelBlockTable(GenerationsDecorationBlocks.DOUBLE_STREET_LAMP.asValue())
        createGenericRotationModelBlockTable(GenerationsShrines.PRISON_BOTTLE.asValue())
        prisonBottleStem()

        dropSelf(GenerationsBlocks.CASTLE_PILLAR)
        dropSelf(GenerationsBlocks.BROKEN_CASTLE_PILLAR)
        dropSelf(GenerationsBlocks.PRISMARINE_PILLAR)
        dropSelf(GenerationsBlocks.BROKEN_PRISMARINE_PILLAR)
        dropSelf(GenerationsBlocks.DARK_PRISMARINE_PILLAR)
        dropSelf(GenerationsBlocks.BROKEN_DARK_PRISMARINE_PILLAR)
        dropSelf(GenerationsBlocks.GHOST_PILLAR)
        dropSelf(GenerationsBlocks.HAUNTED_PILLAR)
        dropSelf(GenerationsBlocks.BROKEN_HAUNTED_PILLAR)
        dropSelf(GenerationsBlocks.DAWN_STONE_BLOCK)
        dropSelf(GenerationsBlocks.DUSK_STONE_BLOCK)
        dropSelf(GenerationsBlocks.FIRE_STONE_BLOCK)
        dropSelf(GenerationsBlocks.ICE_STONE_BLOCK)
        dropSelf(GenerationsBlocks.LEAF_STONE_BLOCK)
        dropSelf(GenerationsBlocks.MOON_STONE_BLOCK)
        dropSelf(GenerationsBlocks.SHINY_STONE_BLOCK)
        dropSelf(GenerationsBlocks.SUN_STONE_BLOCK)
        dropSelf(GenerationsBlocks.THUNDER_STONE_BLOCK)
        dropSelf(GenerationsBlocks.WATER_STONE_BLOCK)
        dropSelf(GenerationsBlocks.CRATE)

        dropSelf(GenerationsBlocks.WARNING_BLOCK)

        dropSelf(GenerationsBlocks.POKECENTER_DOOR)

        createSignDrops(
            GenerationsItems.ULTRA_DARK_SIGN,
            GenerationsWood.ULTRA_DARK_SIGN,
            GenerationsWood.ULTRA_DARK_WALL_SIGN
        )
        createSignDrops(
            GenerationsItems.ULTRA_DARK_HANGING_SIGN,
            GenerationsWood.ULTRA_DARK_HANGING_SIGN,
            GenerationsWood.ULTRA_DARK_WALL_HANGING_SIGN
        )
        createSignDrops(
            GenerationsItems.ULTRA_JUNGLE_SIGN,
            GenerationsWood.ULTRA_JUNGLE_SIGN,
            GenerationsWood.ULTRA_JUNGLE_WALL_SIGN
        )
        createSignDrops(
            GenerationsItems.ULTRA_JUNGLE_HANGING_SIGN,
            GenerationsWood.ULTRA_JUNGLE_HANGING_SIGN,
            GenerationsWood.ULTRA_JUNGLE_WALL_HANGING_SIGN
        )
        createSignDrops(
            GenerationsItems.GHOST_SIGN,
            GenerationsWood.GHOST_SIGN,
            GenerationsWood.GHOST_WALL_SIGN
        )
        createSignDrops(
            GenerationsItems.GHOST_HANGING_SIGN,
            GenerationsWood.GHOST_HANGING_SIGN,
            GenerationsWood.GHOST_WALL_HANGING_SIGN
        )
    }

    private fun createSignDrops(item: Holder<out Item>, standingSignBlock: Holder<out Block>, wallSignBlock: Holder<out Block>) {
        createItemDropTable(item.value(), standingSignBlock.value())
        createItemDropTable(item.value(), wallSignBlock.value())
    }

    private fun dropDisplayStandWithBall(block: Holder<out Block>, item: Item) {
        add(
            block.value(), LootTable
                .lootTable()
                .withPool(
                    applyExplosionCondition(
                        GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.value(),
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                            .add(LootItem.lootTableItem(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.value()))
                    )
                )
                .withPool(
                    applyExplosionCondition(
                        GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.value(),
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(item))
                    )
                )
        )
    }


    private fun addOreSetWithRandomAmountDrops(oreSet: GenerationsOreSet, drop: Item) {
        add(
            oreSet.ore,
            createSilkTouchDispatchTable(
                oreSet.ore,
                applyExplosionDecay(
                    oreSet.ore,
                    LootItem.lootTableItem(drop)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                        .apply(ApplyBonusCount.addOreBonusCount(fortune!!))
                )
            )
        )
        add(
            oreSet.deepslateOre,
            createSilkTouchDispatchTable(
                oreSet.deepslateOre,
                applyExplosionDecay(
                    oreSet.deepslateOre,
                    LootItem.lootTableItem(drop)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                        .apply(ApplyBonusCount.addOreBonusCount(fortune!!))
                )
            )
        )
    }

    private fun addOreSet(oreSet: GenerationsOreSet) {
        if (oreSet.drop == null) return
        add(oreSet.ore, createOreDrop(oreSet.ore, oreSet.drop!!))
        add(oreSet.deepslateOre, createOreDrop(oreSet.deepslateOre, oreSet.drop!!))
    }

    private fun addOreSet(ore: Block, drop: Item) {
        add(ore, createOreDrop(ore, drop))
    }

    private fun addAll(block: Block?, slab: Block?, stair: Block?, wall: Block?) {
        if (block != null) dropSelf(block)
        if (slab != null) add(slab, createSlabItemTable(slab))
        if (stair != null) dropSelf(stair)
        if (wall != null) dropSelf(wall)
    }

    override fun getKnownBlocks(): Iterable<Block> {
        return knownBlocks
    }

    private fun dropSelfUpdated(block: Block) {
        if (block is SlabBlock) add(block, createSlabItemTable(block))
        else if (block is DoorBlock) add(block, createDoorTable(block))
        else if (block is InfestedBlock) this.otherWhenSilkTouch(block, block.hostBlock)
        else dropSelf(block)
    }

    private fun dropSelfStoneCobble(block: Block) {
        if (block === GenerationsBlocks.VOLCANIC_STONE) add(
            block,
            createSingleItemTable(GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.baseBlock)
        )
        else dropSelfUpdated(block)
    }

    override fun add(block: Block, lootTableBuilder: LootTable.Builder) {
        knownBlocks.add(block)
        super.add(block, lootTableBuilder)
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
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(dyeMap)
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(property, value)))
                                        .apply(DyedBlockLootFunction.DUMMY)))));
    }

     */
    override fun generate(biConsumer: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>) {
        this.generate()
        val set: MutableSet<ResourceKey<LootTable>> = HashSet()

        for (block in this.getKnownBlocks()) {
            if (block.isEnabled(this.enabledFeatures)) {
                val resourcelocation = block.lootTable
                if (resourcelocation !== BuiltInLootTables.EMPTY && set.add(resourcelocation)) {
                    val `loottable$builder` = map.remove(resourcelocation)
                    checkNotNull(`loottable$builder`) {
                        String.format(
                            Locale.ROOT,
                            "Missing loottable '%s' for '%s'",
                            resourcelocation,
                            BuiltInRegistries.BLOCK.getKey(block)
                        )
                    }

                    biConsumer.accept(resourcelocation, `loottable$builder`)
                }
            }
        }

        check(!(map.isNotEmpty() && map.size != 1 && map.containsKey(BuiltInLootTables.EMPTY))) { "Created block loot tables for non-blocks: " + map.keys }
    }

    protected fun <T : Block> createItemDropTable(item: Item, block: T) {
        add(block,
            LootTable.lootTable().withPool(
                this.applyExplosionCondition(
                    block, LootPool.lootPool().setRolls(
                        ConstantValue.exactly(1.0f)
                    ).add(LootItem.lootTableItem(item))
                )
            )
        )
    }

    protected fun <T : GenericRotatableModelBlock> createGenericRotationModelBlockTable(block: T) {
        val statePropertiesPredicate = StatePropertiesPredicate.Builder.properties()

        if (block!!.widthProperty != null) {
            statePropertiesPredicate.hasProperty(block.widthProperty, block.baseX)
        }
        if (block.heightProperty != null) {
            statePropertiesPredicate.hasProperty(block.heightProperty, 0)
        }
        if (block.lengthProperty != null) {
            statePropertiesPredicate.hasProperty(block.lengthProperty, block.baseZ)
        }

        add(
            block, LootTable.lootTable().withPool(
                this.applyExplosionCondition(
                    block, LootPool.lootPool().setRolls(
                        ConstantValue.exactly(1.0f)
                    ).add(
                        LootItem.lootTableItem(block).`when`(
                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(statePropertiesPredicate)
                        )
                    )
                )
            )
        )
    }

    protected fun prisonBottleStem() {
        val block = GenerationsShrines.PRISON_BOTTLE_STEM.value()
        val statePropertiesPredicate: () -> StatePropertiesPredicate.Builder = {
            val builder = StatePropertiesPredicate.Builder.properties()

            block.widthProperty?.run { builder.hasProperty(this, block.baseX) }
            block.heightProperty?.run { builder.hasProperty(this, 0) }
            block.lengthProperty?.run { builder.hasProperty(this, block.baseZ) }

            builder
        }

        val lootTable = this.applyExplosionCondition(
            block, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0f))
                .add(
                    LootItem.lootTableItem(block)
                        .`when`(
                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(statePropertiesPredicate.invoke())
                        )
                )
        )

        add(
            block, LootTable.lootTable()
                .withPool(lootTable)
                .withPool(ringLoot(PrisonBottleState.RING_1, 1, statePropertiesPredicate.invoke()))
                .withPool(ringLoot(PrisonBottleState.RING_2, 2, statePropertiesPredicate.invoke()))
                .withPool(ringLoot(PrisonBottleState.RING_3, 3, statePropertiesPredicate.invoke()))
                .withPool(ringLoot(PrisonBottleState.RING_4, 4, statePropertiesPredicate.invoke()))
                .withPool(ringLoot(PrisonBottleState.RING_5, 5, statePropertiesPredicate.invoke()))
        )
    }

    fun ringLoot(state: PrisonBottleState, ring: Int, builder: StatePropertiesPredicate.Builder): LootPool.Builder {
        return this.applyExplosionCondition(
            GenerationsItems.HOOPA_RING.value(), LootPool.lootPool()
                .setRolls(ConstantValue.exactly(ring.toFloat()))
                .add(
                    LootItem.lootTableItem(GenerationsItems.HOOPA_RING.value())
                        .`when`(
                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(GenerationsShrines.PRISON_BOTTLE_STEM.value())
                                .setProperties(builder.hasProperty(PrisonBottleStemBlock.STATE, state))
                        )
                )
        )
    }

    fun dropSelf(block: Holder<out Block>) {
        dropSelf(block.value())
    }
}