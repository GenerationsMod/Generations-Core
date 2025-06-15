package generations.gg.generations.core.generationscore.forge.datagen.generators.tags

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.tags.GenerationsBlockTags
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags
import generations.gg.generations.core.generationscore.common.world.item.GenerationsArmor
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.GenerationsTools
import generations.gg.generations.core.generationscore.common.world.item.tools.GenerationsHammerItem
import generations.gg.generations.core.generationscore.common.world.level.block.*
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsFullBlockSet
import net.minecraft.core.HolderLookup
import net.minecraft.data.DataGenerator
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.data.tags.PaintingVariantTagsProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.*
import net.minecraft.world.level.block.*
import net.neoforged.neoforge.common.Tags
import net.neoforged.neoforge.common.data.BlockTagsProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

object TagsDatagen {
    fun init(
        generator: DataGenerator,
        output: PackOutput,
        lookupProvider: CompletableFuture<HolderLookup.Provider>,
        helper: ExistingFileHelper
    ) {
        val blockProvider = GenerationsBlockTagsProvider(output, lookupProvider, helper)
        generator.addProvider(true, blockProvider)
        generator.addProvider(true, GenerationsItemTagsProvider(output, lookupProvider, blockProvider, helper))
        generator.addProvider(true, GenerationsPaintingTagProvider(output, lookupProvider, helper))
    }

    private class GenerationsBlockTagsProvider(
        output: PackOutput,
        lookupProvider: CompletableFuture<HolderLookup.Provider>,
        existingFileHelper: ExistingFileHelper
    ) :
        BlockTagsProvider(output, lookupProvider, GenerationsCore.MOD_ID, existingFileHelper) {
        override fun addTags(arg: HolderLookup.Provider) {
            GenerationsBlocks.BLOCKS.all().forEach { `object` ->
                val block = `object`
                EasyBlockTags(block)
                if (`object` is FenceBlock) tag(BlockTags.FENCES).add(block)
                else if (`object` is FenceGateBlock) tag(BlockTags.FENCE_GATES).add(block)
                else if (`object` is DoorBlock) tag(BlockTags.DOORS).add(block)
                else if (`object` is ColoredFallingBlock) { //TODO: FInd out if better progrmattical method for sand exists
                    tag(BlockTags.SAND).add(block)
                    tag(BlockTags.MINEABLE_WITH_SHOVEL).add(block)
                }
            }

            GenerationsBlocks.ULTRA_BLOCKS.all().forEach { block ->
                tag(GenerationsBlockTags.ULTRA).add(block)
                EasyBlockTags(block)
            }

            GenerationsFullBlockSet.fullBlockSets.forEach { blockSet ->
                blockSet.allBlocks.forEach { _ ->
                        if (blockSet.name.contains("poke_brick")) tag(GenerationsBlockTags.POKEBRICKS).add(blockSet.baseBlock)
                        else if (blockSet.name.contains("marble")) tag(GenerationsBlockTags.MARBLE).add(blockSet.baseBlock)
                    }
            }

            GenerationsPokeDolls.all().forEach { pokedoll -> tag(GenerationsBlockTags.POKEDOLLS).add(pokedoll)
            }

            val mine_with_pickaxe = tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(GenerationsBlockTags.ULTRA)
                .addTag(GenerationsBlockTags.MARBLE)
                .addTag(GenerationsBlockTags.POKEBRICKS).addTag(GenerationsBlockTags.GENERATIONSORES)
                .addTag(GenerationsBlockTags.POKEBALL_CHESTS)
                .addTag(GenerationsBlockTags.BALL_DISPLAY_BLOCKS).add(GenerationsBlocks.Z_BLOCK)
                .add(GenerationsBlocks.LIGHTNING_LANTERN)

            GenerationsBlocks.STONE.all().forEach { block ->
                mine_with_pickaxe.add(block)
                EasyBlockTags(block)
                if (block is PressurePlateBlock) tag(BlockTags.STONE_PRESSURE_PLATES).add(block)
            }

            mine_with_pickaxe.add(
                GenerationsUtilityBlocks.CHARGE_STONE_FURNACE,
                GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE,
                GenerationsUtilityBlocks.CHARGE_STONE_SMOKER,
                GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE,
                GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE,
                GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER,
                GenerationsShrines.PRISON_BOTTLE,
                GenerationsShrines.PRISON_BOTTLE_STEM,
                GenerationsUtilityBlocks.RKS_MACHINE,
                GenerationsUtilityBlocks.COOKING_POT,
                GenerationsUtilityBlocks.TABLE_PC,
                GenerationsUtilityBlocks.ROTOM_PC,
                GenerationsUtilityBlocks.TRASH_CAN,
                GenerationsUtilityBlocks.WHITE_ELEVATOR,
                GenerationsUtilityBlocks.LIGHT_GRAY_ELEVATOR,
                GenerationsUtilityBlocks.GRAY_ELEVATOR,
                GenerationsUtilityBlocks.BLACK_ELEVATOR,
                GenerationsUtilityBlocks.BROWN_ELEVATOR,
                GenerationsUtilityBlocks.RED_ELEVATOR,
                GenerationsUtilityBlocks.ORANGE_ELEVATOR,
                GenerationsUtilityBlocks.YELLOW_ELEVATOR,
                GenerationsUtilityBlocks.LIME_ELEVATOR,
                GenerationsUtilityBlocks.GREEN_ELEVATOR,
                GenerationsUtilityBlocks.CYAN_ELEVATOR,
                GenerationsUtilityBlocks.LIGHT_BLUE_ELEVATOR,
                GenerationsUtilityBlocks.BLUE_ELEVATOR,
                GenerationsUtilityBlocks.PURPLE_ELEVATOR,
                GenerationsUtilityBlocks.MAGENTA_ELEVATOR,
                GenerationsUtilityBlocks.PINK_ELEVATOR,
                GenerationsDecorationBlocks.DESK,
                GenerationsDecorationBlocks.FRIDGE
            ).add(*GenerationsDecorationBlocks.STREET_LAMP.toArray())
                .add(*GenerationsUtilityBlocks.PC.toArray())
                .add(*GenerationsDecorationBlocks.BALL_DISPLAY_BLOCKS.toTypedArray())



            tag(BlockTags.DIRT).add(
                GenerationsBlocks.ULTRA_SAND,
                GenerationsBlocks.RICH_SOIL_1,
                GenerationsBlocks.RICH_SOIL_2,
                GenerationsBlocks.RICH_SOIL_3,
                GenerationsBlocks.RICH_SOIL_4
            )

            tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                GenerationsBlocks.RUINS_SAND,
                GenerationsBlocks.RICH_SOIL_1,
                GenerationsBlocks.RICH_SOIL_2,
                GenerationsBlocks.RICH_SOIL_3,
                GenerationsBlocks.RICH_SOIL_4
            )

            GenerationsBlocks.ENCHANTED_OBSIDIAN_SET.allBlocks
                .forEach(Consumer { block: Block -> tag(BlockTags.NEEDS_DIAMOND_TOOL).add(block) })


            tag(GenerationsBlockTags.POKEBALL_CHESTS)
                .add(
                    GenerationsBlocks.POKEBALL_CHEST,
                    GenerationsBlocks.GREATBALL_CHEST,
                    GenerationsBlocks.ULTRABALL_CHEST,
                    GenerationsBlocks.MASTERBALL_CHEST
                )
            tag(Tags.Blocks.CHESTS).addTag(GenerationsBlockTags.POKEBALL_CHESTS)

            GenerationsShrines.all().forEach(Consumer { block ->
                tag(GenerationsBlockTags.SHRINES).add(
                    block
                )
            })

            tag(BlockTags.GUARDED_BY_PIGLINS).addTag(GenerationsBlockTags.POKEBALL_CHESTS)
            tag(BlockTags.FEATURES_CANNOT_REPLACE).addTag(GenerationsBlockTags.POKEBALL_CHESTS)

            GenerationsDecorationBlocks.BALL_DISPLAY_BLOCKS.forEach { block ->
                tag(
                    GenerationsBlockTags.BALL_DISPLAY_BLOCKS
                ).add(block)
            }
            GenerationsUtilityBlocks.BALL_LOOTS.forEach { block ->
                tag(
                    GenerationsBlockTags.BALL_LOOTS
                ).add(block)
            }

            tag(GenerationsBlockTags.REGI_STANDS).add(
                GenerationsBlocks.CASTLE_PILLAR,
                GenerationsBlocks.GHOST_PILLAR,
                GenerationsBlocks.PRISMARINE_PILLAR,
                GenerationsBlocks.ICE_PILLAR,
                GenerationsBlocks.DARK_PRISMARINE_PILLAR
            )


            //Ore Specific tags
            tag(GenerationsBlockTags.SAPPHIRE_ORES).add(
                GenerationsOres.SAPPHIRE_ORE_SET.ore,
                GenerationsOres.SAPPHIRE_ORE_SET.deepslateOre
            )
            tag(GenerationsBlockTags.RUBY_ORES).add(
                GenerationsOres.RUBY_ORE_SET.ore,
                GenerationsOres.RUBY_ORE_SET.deepslateOre
            )
            tag(GenerationsBlockTags.CRYSTAL_ORES).add(
                GenerationsOres.CRYSTAL_ORE_SET.ore,
                GenerationsOres.CRYSTAL_ORE_SET.deepslateOre
            )
            tag(GenerationsBlockTags.SILICON_ORES).add(
                GenerationsOres.SILICON_ORE_SET.ore,
                GenerationsOres.SILICON_ORE_SET.deepslateOre
            )
            tag(GenerationsBlockTags.Z_CRYSTAL_ORES).add(
                GenerationsOres.Z_CRYSTAL_ORE_SET.ore,
                GenerationsOres.Z_CRYSTAL_ORE_SET.deepslateOre
            )
            tag(GenerationsBlockTags.MEGASTONE_ORES).add(
                GenerationsOres.MEGASTONE_ORE_SET.ore,
                GenerationsOres.MEGASTONE_ORE_SET.deepslateOre
            )
            tag(GenerationsBlockTags.METEORITE_ORES).add(
                GenerationsOres.METEORITE_ORE_SET.ore,
                GenerationsOres.METEORITE_ORE_SET.deepslateOre
            )

            //Vanilla Ores
            /*
            tag(BlockTags.COAL_ORES).add(GenerationsOres.CHARGE_STONE_COAL_ORE_SET.ore);
            tag(Tags.Blocks.ORES_COAL).add(GenerationsOres.CHARGE_STONE_COAL_ORE_SET.ore);
            tag(BlockTags.REDSTONE_ORES).add(GenerationsOres.CHARGE_STONE_REDSTONE_ORE_SET.ore);
            tag(Tags.Blocks.ORES_REDSTONE).add(GenerationsOres.CHARGE_STONE_REDSTONE_ORE_SET.ore);
            tag(BlockTags.IRON_ORES).add(GenerationsOres.CHARGE_STONE_IRON_ORE_SET.ore);
            tag(Tags.Blocks.ORES_IRON).add(GenerationsOres.CHARGE_STONE_IRON_ORE_SET.ore);
            tag(BlockTags.GOLD_ORES).add(GenerationsOres.CHARGE_STONE_GOLD_ORE_SET.ore);
            tag(Tags.Blocks.ORES_GOLD).add(GenerationsOres.CHARGE_STONE_GOLD_ORE_SET.ore);
            tag(BlockTags.COPPER_ORES).add(GenerationsOres.CHARGE_STONE_COPPER_ORE_SET.ore);
            tag(Tags.Blocks.ORES_COPPER).add(GenerationsOres.CHARGE_STONE_COPPER_ORE_SET.ore);
            tag(BlockTags.LAPIS_ORES).add(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE_SET.ore);
            tag(Tags.Blocks.ORES_LAPIS).add(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE_SET.ore);
            tag(BlockTags.DIAMOND_ORES).add(GenerationsOres.CHARGE_STONE_DIAMOND_ORE_SET.ore);
            tag(Tags.Blocks.ORES_DIAMOND).add(GenerationsOres.CHARGE_STONE_DIAMOND_ORE_SET.ore);
            tag(BlockTags.EMERALD_ORES).add(GenerationsOres.CHARGE_STONE_EMERALD_ORE_SET.ore);
            tag(Tags.Blocks.ORES_EMERALD).add(GenerationsOres.CHARGE_STONE_EMERALD_ORE_SET.ore);

             */
            tag(GenerationsBlockTags.GENERATIONSORES)
                .addTag(GenerationsBlockTags.SAPPHIRE_ORES)
                .addTag(GenerationsBlockTags.RUBY_ORES)
                .addTag(GenerationsBlockTags.CRYSTAL_ORES)
                .addTag(GenerationsBlockTags.SILICON_ORES)
                .addTag(GenerationsBlockTags.Z_CRYSTAL_ORES)
                .addTag(GenerationsBlockTags.MEGASTONE_ORES)
                .addTag(GenerationsBlockTags.METEORITE_ORES)


            /*
                    .add(GenerationsOres.CHARGE_STONE_COAL_ORE_SET.ore)
                    .add(GenerationsOres.CHARGE_STONE_REDSTONE_ORE_SET.ore)
                    .add(GenerationsOres.CHARGE_STONE_IRON_ORE_SET.ore)
                    .add(GenerationsOres.CHARGE_STONE_GOLD_ORE_SET.ore)
                    .add(GenerationsOres.CHARGE_STONE_COPPER_ORE_SET.ore)
                    .add(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE_SET.ore)
                    .add(GenerationsOres.CHARGE_STONE_DIAMOND_ORE_SET.ore)
                    .add(GenerationsOres.CHARGE_STONE_EMERALD_ORE_SET.ore);
                     */
            GenerationsWood.WOOD_BLOCKS.all().forEach { block ->
                val woodBlock = block
                tag(BlockTags.MINEABLE_WITH_AXE).add(woodBlock)
                if (woodBlock.name.string.contains("planks")) tag(BlockTags.PLANKS).add(woodBlock)
                else if (woodBlock is TrapDoorBlock) tag(BlockTags.WOODEN_TRAPDOORS).add(woodBlock)
                else if (woodBlock is DoorBlock) tag(BlockTags.WOODEN_DOORS).add(woodBlock)
                else if (woodBlock is StandingSignBlock) tag(BlockTags.STANDING_SIGNS).add(woodBlock)
                else if (woodBlock is SlabBlock) tag(BlockTags.WOODEN_SLABS).add(woodBlock)
                else if (woodBlock is StairBlock) tag(BlockTags.WOODEN_STAIRS).add(woodBlock)
                else if (woodBlock is FenceBlock) tag(BlockTags.WOODEN_FENCES).add(woodBlock)
                else if (woodBlock is FenceGateBlock) {
                    tag(BlockTags.FENCE_GATES).add(woodBlock)
                    tag(Tags.Blocks.FENCE_GATES_WOODEN).add(woodBlock)
                } else if (woodBlock is PressurePlateBlock) tag(BlockTags.WOODEN_PRESSURE_PLATES).add(woodBlock)
                else if (woodBlock is ButtonBlock) tag(BlockTags.WOODEN_BUTTONS).add(woodBlock)
                else if (woodBlock is CeilingHangingSignBlock) tag(BlockTags.CEILING_HANGING_SIGNS).add(woodBlock)
                else if (woodBlock is WallHangingSignBlock) tag(BlockTags.WALL_HANGING_SIGNS).add(woodBlock)
                else if (woodBlock.name.string.contains("book")) {
                    tag(Tags.Blocks.BOOKSHELVES).add(woodBlock)
                    tag(BlockTags.ENCHANTMENT_POWER_PROVIDER).add(woodBlock)
                }
            }

            tag(GenerationsBlockTags.GHOST_LOGS).add(
                GenerationsWood.GHOST_LOG,
                GenerationsWood.STRIPPED_GHOST_LOG
            )

            tag(GenerationsBlockTags.ULTRA_DARK_LOGS).add(
                GenerationsWood.ULTRA_DARK_LOG,
                GenerationsWood.STRIPPED_ULTRA_DARK_LOG
            )

            tag(GenerationsBlockTags.ULTRA_JUNGLE_LOGS).add(
                GenerationsWood.ULTRA_JUNGLE_LOG,
                GenerationsWood.STRIPPED_ULTRA_JUNGLE_LOG
            )

            tag(BlockTags.LOGS).addTag(GenerationsBlockTags.GHOST_LOGS).addTag(GenerationsBlockTags.ULTRA_DARK_LOGS)
                .addTag(GenerationsBlockTags.ULTRA_JUNGLE_LOGS)
            tag(BlockTags.LOGS_THAT_BURN).addTag(GenerationsBlockTags.GHOST_LOGS)
                .addTag(GenerationsBlockTags.ULTRA_DARK_LOGS).addTag(GenerationsBlockTags.ULTRA_JUNGLE_LOGS)

            GenerationsWood.WOOD_SIGN.all().forEach { sign ->
                if (sign is WallSignBlock) tag(
                    BlockTags.WALL_SIGNS
                ).add(sign)
                else if (sign is WallHangingSignBlock) tag(BlockTags.WALL_HANGING_SIGNS).add(sign)
            }

            //Charge and Volcanic Stone Brick Tags like Vanilla
            tag(GenerationsBlockTags.CHARGE_STONE_BRICKS)
                .add(
                    GenerationsBlocks.CHARGE_STONE_BRICKS,
                    GenerationsBlocks.MOSSY_CHARGE_STONE_BRICKS_SET.baseBlock,
                    GenerationsBlocks.CRACKED_CHARGE_STONE_BRICKS,
                    GenerationsBlocks.CHISELED_CHARGE_STONE_BRICKS
                )

            tag(GenerationsBlockTags.VOLCANIC_STONE_BRICKS)
                .add(
                    GenerationsBlocks.VOLCANIC_STONE_BRICKS,
                    GenerationsBlocks.MOSSY_VOLCANIC_STONE_BRICKS_SET.baseBlock,
                    GenerationsBlocks.CRACKED_VOLCANIC_STONE_BRICKS,
                    GenerationsBlocks.CHISELED_VOLCANIC_STONE_BRICKS
                )

            tag(BlockTags.MINEABLE_WITH_AXE).add(
                GenerationsBlocks.CURSED_PUMPKIN,
                GenerationsBlocks.CURSED_JACK_O_LANTERN,
                GenerationsBlocks.CURSED_CARVED_PUMPKIN,
                GenerationsUtilityBlocks.SCARECROW,
                GenerationsUtilityBlocks.BOX
            )
                .add(*GenerationsDecorationBlocks.COUCH_ARM_LEFT.toArray())
                .add(*GenerationsDecorationBlocks.COUCH_ARM_RIGHT.toArray())
                .add(*GenerationsDecorationBlocks.COUCH_CORNER_LEFT.toArray())
                .add(*GenerationsDecorationBlocks.COUCH_CORNER_RIGHT.toArray())
                .add(*GenerationsDecorationBlocks.COUCH_MIDDLE.toArray())
                .add(*GenerationsDecorationBlocks.COUCH_OTTOMAN.toArray())
            tag(BlockTags.ENDERMAN_HOLDABLE).add(
                GenerationsBlocks.CURSED_PUMPKIN,
                GenerationsBlocks.CURSED_CARVED_PUMPKIN
            )
            tag(BlockTags.SWORD_EFFICIENT).add(
                GenerationsBlocks.CURSED_PUMPKIN,
                GenerationsBlocks.CURSED_JACK_O_LANTERN,
                GenerationsBlocks.CURSED_CARVED_PUMPKIN
            )

            tag(BlockTags.NEEDS_IRON_TOOL).addTag(GenerationsBlockTags.GENERATIONSORES)
            tag(Tags.Blocks.ORES).addTag(GenerationsBlockTags.GENERATIONSORES)
            tag(BlockTags.NEEDS_STONE_TOOL).add(
                GenerationsBlocks.SAPPHIRE_BLOCK,
                GenerationsBlocks.SAPPHIRE_SLAB,
                GenerationsBlocks.SAPPHIRE_STAIRS,
                GenerationsBlocks.SAPPHIRE_WALL,
                GenerationsBlocks.RUBY_BLOCK,
                GenerationsBlocks.RUBY_SLAB,
                GenerationsBlocks.RUBY_STAIRS,
                GenerationsBlocks.RUBY_WALL,
                GenerationsBlocks.CRYSTAL_BLOCK,
                GenerationsBlocks.CRYSTAL_SLAB,
                GenerationsBlocks.CRYSTAL_STAIRS,
                GenerationsBlocks.CRYSTAL_WALL,
                GenerationsBlocks.SILICON_BLOCK
            )
                .addTag(GenerationsBlockTags.BALL_DISPLAY_BLOCKS)

            tag(BlockTags.SNOW_LAYER_CANNOT_SURVIVE_ON).add(GenerationsBlocks.POKECENTER_SCARLET_SIGN)
                .addTag(GenerationsBlockTags.SHRINES).addTag(GenerationsBlockTags.BALL_DISPLAY_BLOCKS)
                .addTag(GenerationsBlockTags.BALL_LOOTS).addTag(GenerationsBlockTags.POKEDOLLS)

            tag(GenerationsBlockTags.TAPU_SUMMONING).add(GenerationsShrines.TAPU_SHRINE)
        }

        fun EasyBlockTags(`object`: Block) {
            if (`object` is SlabBlock) tag(BlockTags.SLABS).add(`object`)
            else if (`object` is StairBlock) tag(BlockTags.STAIRS).add(`object`)
            else if (`object` is WallBlock) tag(BlockTags.WALLS).add(`object`)
            else if (`object` is ButtonBlock) tag(BlockTags.BUTTONS).add(`object`)
            else if (`object` is PressurePlateBlock) tag(BlockTags.PRESSURE_PLATES).add(`object`)

            val type = `object`.defaultBlockState().soundType
            if (type === SoundType.STONE || type === SoundType.DEEPSLATE) tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                `object`
            )
        }
    }

    private class GenerationsItemTagsProvider(
        arg: PackOutput,
        completableFuture: CompletableFuture<HolderLookup.Provider>,
        blockTagsProvider: BlockTagsProvider,
        existingFileHelper: ExistingFileHelper?
    ) :
        ItemTagsProvider(
            arg,
            completableFuture,
            blockTagsProvider.contentsGetter(),
            GenerationsCore.MOD_ID,
            existingFileHelper
        ) {
        override fun addTags(arg: HolderLookup.Provider) {
            //Copy Block tags to item version
            copy(BlockTags.PLANKS, ItemTags.PLANKS)
            copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS)
            copy(BlockTags.BUTTONS, ItemTags.BUTTONS)
            copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS)
            copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS)
            copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS)
            copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES)
            copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES)
            copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN)
            copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES)
            //copy(BlockTags.DOORS, ItemTags.DOORS);
            //copy(BlockTags.SAND, ItemTags.SAND);
            copy(BlockTags.SLABS, ItemTags.SLABS)
            copy(BlockTags.WALLS, ItemTags.WALLS)
            copy(BlockTags.STAIRS, ItemTags.STAIRS)
            copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS)
            //copy(BlockTags.FENCES, ItemTags.FENCES);
            copy(BlockTags.DIRT, ItemTags.DIRT)
//            copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS) TODO: Figure out
            tag(ItemTags.LOGS).addTag(GenerationsItemTags.ULTRA_DARK_LOGS).addTag(GenerationsItemTags.ULTRA_JUNGLE_LOGS)
                .addTag(GenerationsItemTags.GHOST_LOGS)
            tag(ItemTags.LOGS_THAT_BURN).addTag(GenerationsItemTags.ULTRA_DARK_LOGS)
                .addTag(GenerationsItemTags.ULTRA_JUNGLE_LOGS).addTag(GenerationsItemTags.GHOST_LOGS)
            copy(GenerationsBlockTags.ULTRA_DARK_LOGS, GenerationsItemTags.ULTRA_DARK_LOGS)
            copy(GenerationsBlockTags.ULTRA_JUNGLE_LOGS, GenerationsItemTags.ULTRA_JUNGLE_LOGS)
            copy(GenerationsBlockTags.GHOST_LOGS, GenerationsItemTags.GHOST_LOGS)
            copy(Tags.Blocks.BOOKSHELVES, Tags.Items.BOOKSHELVES)

            //PokeBricks
            copy(GenerationsBlockTags.POKEBRICKS, GenerationsItemTags.POKEBRICKS)
            //Marble
            copy(GenerationsBlockTags.MARBLE, GenerationsItemTags.MARBLE)
            //Ultra
            copy(GenerationsBlockTags.ULTRA, GenerationsItemTags.ULTRA)
            //PokeDolls
            copy(GenerationsBlockTags.POKEDOLLS, GenerationsItemTags.POKEDOLLS)

            //Ore Specific tags like Vanilla
            copy(GenerationsBlockTags.GENERATIONSORES, GenerationsItemTags.GENERATIONSORES)
            copy(Tags.Blocks.ORES, Tags.Items.ORES)
            copy(GenerationsBlockTags.SAPPHIRE_ORES, GenerationsItemTags.SAPPHIRE_ORES)
            copy(GenerationsBlockTags.RUBY_ORES, GenerationsItemTags.RUBY_ORES)
            copy(GenerationsBlockTags.CRYSTAL_ORES, GenerationsItemTags.CRYSTAL_ORES)
            copy(GenerationsBlockTags.SILICON_ORES, GenerationsItemTags.SILICON_ORES)
            copy(GenerationsBlockTags.Z_CRYSTAL_ORES, GenerationsItemTags.Z_CRYSTAL_ORES)
            copy(GenerationsBlockTags.MEGASTONE_ORES, GenerationsItemTags.MEGASTONE_ORES)
            copy(GenerationsBlockTags.METEORITE_ORES, GenerationsItemTags.METEORITE_ORES)

            /*
            tag(ItemTags.COAL_ORES).add(GenerationsOres.CHARGE_STONE_COAL_ORE_SET.ore.asItem());
            tag(ItemTags.IRON_ORES).add(GenerationsOres.CHARGE_STONE_IRON_ORE_SET.ore.asItem());
            tag(ItemTags.GOLD_ORES).add(GenerationsOres.CHARGE_STONE_GOLD_ORE_SET.ore.asItem());
            tag(ItemTags.COPPER_ORES).add(GenerationsOres.CHARGE_STONE_COPPER_ORE_SET.ore.asItem());
            tag(ItemTags.DIAMOND_ORES).add(GenerationsOres.CHARGE_STONE_DIAMOND_ORE_SET.ore.asItem());
            tag(ItemTags.EMERALD_ORES).add(GenerationsOres.CHARGE_STONE_EMERALD_ORE_SET.ore.asItem());
            tag(ItemTags.LAPIS_ORES).add(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE_SET.ore.asItem());
            tag(ItemTags.REDSTONE_ORES).add(GenerationsOres.CHARGE_STONE_REDSTONE_ORE_SET.ore.asItem());

             */

            /*
            copy(Tags.Blocks.ORES_COAL, Tags.Items.ORES_COAL);
            copy(Tags.Blocks.ORES_IRON, Tags.Items.ORES_IRON);
            copy(Tags.Blocks.ORES_GOLD, Tags.Items.ORES_GOLD);
            copy(Tags.Blocks.ORES_COPPER, Tags.Items.ORES_COPPER);
            copy(Tags.Blocks.ORES_DIAMOND, Tags.Items.ORES_DIAMOND);
            copy(Tags.Blocks.ORES_EMERALD, Tags.Items.ORES_EMERALD);
            copy(Tags.Blocks.ORES_LAPIS, Tags.Items.ORES_LAPIS);
            copy(Tags.Blocks.ORES_REDSTONE, Tags.Items.ORES_REDSTONE);
             */
            //Charge and Volcanic Stone Brick Tags like Vanilla
            copy(GenerationsBlockTags.CHARGE_STONE_BRICKS, GenerationsItemTags.CHARGE_STONE_BRICKS)
            copy(GenerationsBlockTags.VOLCANIC_STONE_BRICKS, GenerationsItemTags.VOLCANIC_STONE_BRICKS)
            copy(GenerationsBlockTags.POKEBALL_CHESTS, GenerationsItemTags.POKEBALL_CHESTS)
            tag(Tags.Items.CHESTS).addTag(GenerationsItemTags.POKEBALL_CHESTS)

            //Discs
//            tag(ItemTags.MUSIC_DISCS).add(
//                RELIC_SONG,
//                INERT_RELIC_SONG,
//                GenerationsItems.AZALEA_TOWN_DISC,
//                GenerationsItems.CASCARRAFA_CITY_DISC,
//                GenerationsItems.CERULEAN_CITY_DISC,
//                GenerationsItems.ETERNA_CITY_DISC,
//                GenerationsItems.GOLDENROD_CITY_DISC,
//                GenerationsItems.ICIRRUS_CITY_DISC,
//                GenerationsItems.JUBILIFE_VILLAGE_DISC,
//                GenerationsItems.LAKE_OF_RAGE_DISC,
//                GenerationsItems.LAVERRE_CITY_DISC,
//                GenerationsItems.LILLIE_DISC,
//                GenerationsItems.POKEMON_CENTER_DISC,
//                GenerationsItems.ROUTE_228_DISC,
//                GenerationsItems.SLUMBERING_WEALD_DISC,
//                GenerationsItems.SURF_DISC,
//                GenerationsItems.VERMILION_CITY_DISC,
//                GenerationsItems.CYNTHIA_DISC,
//                GenerationsItems.DEOXYS_DISC,
//                GenerationsItems.IRIS_DISC,
//                GenerationsItems.KANTO_DISC,
//                GenerationsItems.LUSAMINE_DISC,
//                GenerationsItems.NEMONA_DISC,
//                GenerationsItems.NESSA_DISC,
//                GenerationsItems.PENNY_DISC,
//                GenerationsItems.RIVAL_DISC,
//                GenerationsItems.SADA_AND_TURO_DISC,
//                GenerationsItems.SOUTH_PROVINCE_DISC,
//                GenerationsItems.TEAM_ROCKET_DISC,
//                GenerationsItems.ULTRA_NECROZMA_DISC,
//                GenerationsItems.XY_LEGENDARY_DISC,
//                GenerationsItems.ZINNIA_DISC,
//                GenerationsItems.LAVENDER_TOWN_DISC,
//                GenerationsItems.LUGIA_DISC,
//                GenerationsItems.MT_PYRE_DISC
//            )

            tag(ItemTags.BOATS).add(GenerationsItems.GHOST_BOAT_ITEM).add(GenerationsItems.ULTRA_DARK_BOAT_ITEM)
                .add(GenerationsItems.ULTRA_JUNGLE_BOAT_ITEM)
            tag(ItemTags.CHEST_BOATS).add(GenerationsItems.GHOST_CHEST_BOAT_ITEM).add(GenerationsItems.ULTRA_DARK_CHEST_BOAT_ITEM)
                .add(GenerationsItems.ULTRA_JUNGLE_CHEST_BOAT_ITEM)

            tag(ItemTags.STONE_TOOL_MATERIALS).add(GenerationsBlocks.CHARGE_COBBLESTONE_SET.baseBlock.asItem())
                .add(GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.baseBlock.asItem())
            GenerationsTools.all().forEach { tool ->
                when (tool) {
                    is PickaxeItem -> tag(ItemTags.PICKAXES).add(tool)
                    is AxeItem -> tag(ItemTags.AXES).add(tool)
                    is ShovelItem -> tag(ItemTags.SHOVELS).add(tool)
                    is HoeItem -> tag(ItemTags.HOES).add(tool)
                    is SwordItem -> tag(ItemTags.SWORDS).add(tool)
                    is GenerationsHammerItem -> tag(GenerationsItemTags.HAMMERS).add(tool)
                }
            }

//            tag(ItemTags.TOOLS).addTag(GenerationsItemTags.HAMMERS) //TODO: Find what is new tag
            tag(Tags.Items.TOOLS).addTag(GenerationsItemTags.HAMMERS)

            tag(GenerationsItemTags.RIBBONS).add(*GenerationsItems.RIBBONS.all().toTypedArray())
            tag(GenerationsItemTags.BADGES).add(*GenerationsItems.BADGES.all().toTypedArray())

            tag(GenerationsItemTags.UNIMPLEMENTED).add(*GenerationsItems.UNIMPLEMENTED.all().toTypedArray())
            tag(GenerationsItemTags.CUISINE).add(*GenerationsItems.CUISINE.all().toTypedArray())
            tag(GenerationsItemTags.NATURAL).add(*GenerationsItems.NATURAL.all().toTypedArray())
            tag(GenerationsItemTags.RESTORATION).add(*GenerationsItems.RESTORATION.all().toTypedArray())
            tag(GenerationsItemTags.PLAYER_ITEMS).add(*GenerationsItems.PLAYER_ITEMS.all().toTypedArray())
            tag(GenerationsItemTags.HELD_ITEMS).add(*GenerationsItems.HELD_ITEMS.all().toTypedArray())
            tag(GenerationsItemTags.LEGENDARY_ITEMS).add(*GenerationsItems.LEGENDARY_ITEMS.all().toTypedArray())
            tag(GenerationsItemTags.UTILITY).add(*GenerationsItems.UTILITY.all().toTypedArray())
            tag(GenerationsItemTags.VALUABLES).add(*GenerationsItems.VALUABLES.all().toTypedArray())
            tag(GenerationsItemTags.FORM_ITEMS).add(*GenerationsItems.FORM_ITEMS.all().toTypedArray())
            tag(GenerationsItemTags.BUILDING_BLOCKS).add(*GenerationsItems.BUILDING_BLOCKS.all().toTypedArray())

            val generationsItemsTag = tag(GenerationsItemTags.GENERATIONSITEMS)
                .addTag(GenerationsItemTags.RIBBONS)
                .addTag(GenerationsItemTags.BADGES)
                .addTag(GenerationsItemTags.UNIMPLEMENTED)
                .addTag(GenerationsItemTags.CUISINE)
                .addTag(GenerationsItemTags.NATURAL)
                .addTag(GenerationsItemTags.RESTORATION)
                .addTag(GenerationsItemTags.PLAYER_ITEMS)
                .addTag(GenerationsItemTags.HELD_ITEMS)
                .addTag(GenerationsItemTags.LEGENDARY_ITEMS)
                .addTag(GenerationsItemTags.UTILITY)
                .addTag(GenerationsItemTags.VALUABLES)
                .addTag(GenerationsItemTags.FORM_ITEMS)
                .addTag(GenerationsItemTags.BUILDING_BLOCKS)
                .addTag(GenerationsItemTags.POKEMAIL)
                .addTag(GenerationsItemTags.CLOSED_POKEMAIL)

            GenerationsItems.ITEMS.all().forEach { item ->
                generationsItemsTag.add(
                    item.builtInRegistryHolder().key()
                )
            }

            tag(GenerationsItemTags.POKEMAIL).add(
                GenerationsItems.POKEMAIL_AIR,
                GenerationsItems.POKEMAIL_BLOOM,
                GenerationsItems.POKEMAIL_BRICK,
                GenerationsItems.POKEMAIL_BRIDGE_D,
                GenerationsItems.POKEMAIL_BRIDGE_M,
                GenerationsItems.POKEMAIL_BRIDGE_S,
                GenerationsItems.POKEMAIL_BRIDGE_T,
                GenerationsItems.POKEMAIL_BRIDGE_V,
                GenerationsItems.POKEMAIL_BUBBLE,
                GenerationsItems.POKEMAIL_DREAM,
                GenerationsItems.POKEMAIL_FAB,
                GenerationsItems.POKEMAIL_FAVORED,
                GenerationsItems.POKEMAIL_FLAME,
                GenerationsItems.POKEMAIL_GLITTER,
                GenerationsItems.POKEMAIL_GRASS,
                GenerationsItems.POKEMAIL_GREET,
                GenerationsItems.POKEMAIL_HARBOR,
                GenerationsItems.POKEMAIL_HEART,
                GenerationsItems.POKEMAIL_INQUIRY,
                GenerationsItems.POKEMAIL_LIKE,
                GenerationsItems.POKEMAIL_MECH,
                GenerationsItems.POKEMAIL_MOSAIC,
                GenerationsItems.POKEMAIL_ORANGE,
                GenerationsItems.POKEMAIL_REPLY,
                GenerationsItems.POKEMAIL_RETRO,
                GenerationsItems.POKEMAIL_RSVP,
                GenerationsItems.POKEMAIL_SHADOW,
                GenerationsItems.POKEMAIL_SNOW,
                GenerationsItems.POKEMAIL_SPACE,
                GenerationsItems.POKEMAIL_STEEL,
                GenerationsItems.POKEMAIL_THANKS,
                GenerationsItems.POKEMAIL_TROPIC,
                GenerationsItems.POKEMAIL_TUNNEL,
                GenerationsItems.POKEMAIL_WAVE,
                GenerationsItems.POKEMAIL_WOOD
            )

            tag(GenerationsItemTags.CLOSED_POKEMAIL).add(
                GenerationsItems.POKEMAIL_AIR_CLOSED,
                GenerationsItems.POKEMAIL_BLOOM_CLOSED,
                GenerationsItems.POKEMAIL_BRICK_CLOSED,
                GenerationsItems.POKEMAIL_BRIDGE_D_CLOSED,
                GenerationsItems.POKEMAIL_BRIDGE_M_CLOSED,
                GenerationsItems.POKEMAIL_BRIDGE_T_CLOSED,
                GenerationsItems.POKEMAIL_BRIDGE_S_CLOSED,
                GenerationsItems.POKEMAIL_BRIDGE_V_CLOSED,
                GenerationsItems.POKEMAIL_BUBBLE_CLOSED,
                GenerationsItems.POKEMAIL_DREAM_CLOSED,
                GenerationsItems.POKEMAIL_FAB_CLOSED,
                GenerationsItems.POKEMAIL_FAVORED_CLOSED,
                GenerationsItems.POKEMAIL_FLAME_CLOSED,
                GenerationsItems.POKEMAIL_GLITTER_CLOSED,
                GenerationsItems.POKEMAIL_GRASS_CLOSED,
                GenerationsItems.POKEMAIL_GREET_CLOSED,
                GenerationsItems.POKEMAIL_HARBOR_CLOSED,
                GenerationsItems.POKEMAIL_HEART_CLOSED,
                GenerationsItems.POKEMAIL_INQUIRY_CLOSED,
                GenerationsItems.POKEMAIL_LIKE_CLOSED,
                GenerationsItems.POKEMAIL_MECH_CLOSED,
                GenerationsItems.POKEMAIL_MOSAIC_CLOSED,
                GenerationsItems.POKEMAIL_ORANGE_CLOSED,
                GenerationsItems.POKEMAIL_REPLY_CLOSED,
                GenerationsItems.POKEMAIL_RETRO_CLOSED,
                GenerationsItems.POKEMAIL_RSVP_CLOSED,
                GenerationsItems.POKEMAIL_SHADOW_CLOSED,
                GenerationsItems.POKEMAIL_SNOW_CLOSED,
                GenerationsItems.POKEMAIL_SPACE_CLOSED,
                GenerationsItems.POKEMAIL_STEEL_CLOSED,
                GenerationsItems.POKEMAIL_THANKS_CLOSED,
                GenerationsItems.POKEMAIL_TROPIC_CLOSED,
                GenerationsItems.POKEMAIL_TUNNEL_CLOSED,
                GenerationsItems.POKEMAIL_WAVE_CLOSED,
                GenerationsItems.POKEMAIL_WOOD_CLOSED
            )

            tag(GenerationsItemTags.SHEARS).add(Items.SHEARS)/*.addOptionalTag(Tags.Items.SHEARS) TODO: Find if still exists*/.addOptionalTag(
                Objects.requireNonNull<ResourceLocation?>(
                    ResourceLocation.tryParse("c:shears")
                )
            )

            //Forge Armor Tags
            GenerationsArmor.all().forEach { armor ->
                if(armor is ArmorItem) {

                    when (armor.type) {
                        ArmorItem.Type.HELMET -> tag(ItemTags.HEAD_ARMOR).add(armor)
                        ArmorItem.Type.CHESTPLATE -> tag(ItemTags.CHEST_ARMOR).add(armor)
                        ArmorItem.Type.LEGGINGS -> tag(ItemTags.LEG_ARMOR).add(armor)
                        ArmorItem.Type.BOOTS -> tag(ItemTags.FOOT_ARMOR).add(armor)
                        else -> {}
                    }
                }
            }

            tag(GenerationsItemTags.KEY_STONES)
                .add(GenerationsItems.KEY_STONE)
                .add(GenerationsItems.KEY_STONE_2)
                .add(GenerationsItems.MEGA_RING)
                .add(GenerationsItems.MEGA_BRACELET)
                .add(GenerationsItems.MEGA_CHARM)
                .add(GenerationsItems.MEGA_CUFF)
            tag(GenerationsItemTags.DYNAMAX_BANDS).add(GenerationsItems.DYNAMAX_BAND)
            tag(GenerationsItemTags.Z_RINGS).add(GenerationsItems.Z_RING).add(GenerationsItems.Z_POWER_RING)
            tag(GenerationsItemTags.TERA_ORBS).add(GenerationsItems.TERA_ORB);
            tag(GenerationsItemTags.MEMORY_DRIVES).add(
                GenerationsItems.BUG_MEMORY_DRIVE,
                GenerationsItems.DARK_MEMORY_DRIVE,
                GenerationsItems.DRAGON_MEMORY_DRIVE,
                GenerationsItems.ELECTRIC_MEMORY_DRIVE,
                GenerationsItems.FAIRY_MEMORY_DRIVE,
                GenerationsItems.FIGHTING_MEMORY_DRIVE,
                GenerationsItems.FIRE_MEMORY_DRIVE,
                GenerationsItems.FLYING_MEMORY_DRIVE,
                GenerationsItems.GHOST_MEMORY_DRIVE,
                GenerationsItems.GRASS_MEMORY_DRIVE,
                GenerationsItems.GROUND_MEMORY_DRIVE,
                GenerationsItems.ICE_MEMORY_DRIVE,
                GenerationsItems.POISON_MEMORY_DRIVE,
                GenerationsItems.PSYCHIC_MEMORY_DRIVE,
                GenerationsItems.ROCK_MEMORY_DRIVE,
                GenerationsItems.STEEL_MEMORY_DRIVE,
                GenerationsItems.WATER_MEMORY_DRIVE
            )

            tag(ItemTags.TRIMMABLE_ARMOR).add(*GenerationsArmor.all().toTypedArray())
        }
    }

    private class GenerationsPaintingTagProvider(
        arg: PackOutput,
        completableFuture: CompletableFuture<HolderLookup.Provider>,
        existingFileHelper: ExistingFileHelper?
    ) :
        PaintingVariantTagsProvider(arg, completableFuture, GenerationsCore.MOD_ID, existingFileHelper) {
        override fun addTags(arg: HolderLookup.Provider) {
//            GenerationsPaintings.PAINTINGS.forEach(Consumer<RegistrySupplier<PaintingVariant>> { painting: RegistrySupplier<PaintingVariant> -> //TODO: Convert over to the new stuf.
//                tag(
//                    PaintingVariantTags.PLACEABLE
//                ).add(TagEntry.element(painting.id))
//            })
        }
    }
}
