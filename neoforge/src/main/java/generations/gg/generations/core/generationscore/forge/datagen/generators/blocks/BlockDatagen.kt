package generations.gg.generations.core.generationscore.forge.datagen.generators.blocks

import generations.gg.generations.core.generationscore.common.GenerationsCore.id
import generations.gg.generations.core.generationscore.common.util.extensions.id
import generations.gg.generations.core.generationscore.common.world.level.block.*
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericChestBlock
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsBlockSet
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsFullBlockSet
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.CelestialAltarBlock
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.LunarShrineBlock
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.PrisonBottleStemBlock
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.BoxBlock
import generations.gg.generations.core.generationscore.forge.datagen.data.families.GenerationsBlockFamilies
import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.BlockFamily
import net.minecraft.data.DataProvider
import net.minecraft.data.models.model.ModelLocationUtils
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.neoforge.client.model.generators.*
import net.neoforged.neoforge.client.model.generators.ModelFile.UncheckedModelFile
import java.util.*
import java.util.function.Consumer

class BlockDatagen(provider: GenerationsBlockStateProvider) : GenerationsBlockStateProvider.Proxied(provider) {
    
    override fun registerStatesAndModels() {
        registerOreBlocks()
        GenerationsBlockFamilies.allGenerationsFamilies
            .filter(BlockFamily::shouldGenerateModel)
            .forEach(this::registerBlockFamily)
        (GenerationsBlockSet.blockSets + GenerationsFullBlockSet.fullBlockSets)
            .asSequence()
            .mapNotNull(GenerationsBlockSet::blockFamily)
            .filter(BlockFamily::shouldGenerateModel)
            .forEach(::registerBlockFamily)

        registerWoodPallet(
            GenerationsWood.ULTRA_DARK_LOG.asValue(),
            GenerationsWood.STRIPPED_ULTRA_DARK_LOG.asValue(),
            GenerationsWood.ULTRA_DARK_PLANKS.asValue(),
            GenerationsWood.ULTRA_DARK_SLAB.asValue(),
            GenerationsWood.ULTRA_DARK_STAIRS.asValue(),
            GenerationsWood.ULTRA_DARK_BUTTON.asValue(),
            GenerationsWood.ULTRA_DARK_PRESSURE_PLATE.asValue(),
            GenerationsWood.ULTRA_DARK_DOOR.asValue(),
            GenerationsWood.ULTRA_DARK_TRAPDOOR.asValue(),
            GenerationsWood.ULTRA_DARK_WOOD.asValue(),
            GenerationsWood.STRIPPED_ULTRA_DARK_WOOD.asValue(),
            GenerationsWood.ULTRA_DARK_FENCE.asValue(),
            GenerationsWood.ULTRA_DARK_FENCE_GATE.asValue(),
            GenerationsWood.ULTRA_DARK_SIGN.asValue(),
            GenerationsWood.ULTRA_DARK_WALL_SIGN.asValue(),
            GenerationsWood.ULTRA_DARK_CRAFTING_TABLE.asValue(),
            GenerationsWood.ULTRA_DARK_HANGING_SIGN.asValue(),
            GenerationsWood.ULTRA_DARK_WALL_HANGING_SIGN.asValue(),
            GenerationsWood.ULTRA_DARK_BOOKSHELF.asValue()
        )
        registerWoodPallet(
            GenerationsWood.ULTRA_JUNGLE_LOG.asValue(),
            GenerationsWood.STRIPPED_ULTRA_JUNGLE_LOG.asValue(),
            GenerationsWood.ULTRA_JUNGLE_PLANKS.asValue(),
            GenerationsWood.ULTRA_JUNGLE_SLAB.asValue(),
            GenerationsWood.ULTRA_JUNGLE_STAIRS.asValue(),
            GenerationsWood.ULTRA_JUNGLE_BUTTON.asValue(),
            GenerationsWood.ULTRA_JUNGLE_PRESSURE_PLATE.asValue(),
            GenerationsWood.ULTRA_JUNGLE_DOOR.asValue(),
            GenerationsWood.ULTRA_JUNGLE_TRAPDOOR.asValue(),
            GenerationsWood.ULTRA_JUNGLE_WOOD.asValue(),
            GenerationsWood.STRIPPED_ULTRA_JUNGLE_WOOD.asValue(),
            GenerationsWood.ULTRA_JUNGLE_FENCE.asValue(),
            GenerationsWood.ULTRA_JUNGLE_FENCE_GATE.asValue(),
            GenerationsWood.ULTRA_JUNGLE_SIGN.asValue(),
            GenerationsWood.ULTRA_JUNGLE_WALL_SIGN.asValue(),
            GenerationsWood.ULTRA_JUNGLE_CRAFTING_TABLE.asValue(),
            GenerationsWood.ULTRA_JUNGLE_HANGING_SIGN.asValue(),
            GenerationsWood.ULTRA_JUNGLE_WALL_HANGING_SIGN.asValue(),
            GenerationsWood.ULTRA_JUNGLE_BOOKSHELF.asValue()
        )
        registerWoodPallet(
            GenerationsWood.GHOST_LOG.asValue(),
            GenerationsWood.STRIPPED_GHOST_LOG.asValue(),
            GenerationsWood.GHOST_PLANKS.asValue(),
            GenerationsWood.GHOST_SLAB.asValue(),
            GenerationsWood.GHOST_STAIRS.asValue(),
            GenerationsWood.GHOST_BUTTON.asValue(),
            GenerationsWood.GHOST_PRESSURE_PLATE.asValue(),
            GenerationsWood.GHOST_DOOR.asValue(),
            GenerationsWood.GHOST_TRAPDOOR.asValue(),
            GenerationsWood.GHOST_WOOD.asValue(),
            GenerationsWood.STRIPPED_GHOST_WOOD.asValue(),
            GenerationsWood.GHOST_FENCE.asValue(),
            GenerationsWood.GHOST_FENCE_GATE.asValue(),
            GenerationsWood.GHOST_SIGN.asValue(),
            GenerationsWood.GHOST_WALL_SIGN.asValue(),
            GenerationsWood.GHOST_CRAFTING_TABLE.asValue(),
            GenerationsWood.GHOST_HANGING_SIGN.asValue(),
            GenerationsWood.GHOST_WALL_HANGING_SIGN.asValue(),
            GenerationsWood.GHOST_BOOKSHELF.asValue()
        )

        //House Floors
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_1.asValue())
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_2.asValue())
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_3.asValue())
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_4.asValue())
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_5.asValue())
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_6.asValue())
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_7.asValue())
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_8.asValue())

        registerBlockItem(GenerationsBlocks.ULTRITE_BLOCK.asValue())

        //ChargeStone
        registerBlockItem(GenerationsBlocks.CHARGE_DRIPSTONE_BLOCK.asValue())

        //Volcanic
        registerBlockItem(GenerationsBlocks.VOLCANIC_FIRESTONE.asValue())

        //Golden Temple
        registerSandStonePallet(
            GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_SLAB.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_STAIRS.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_WALL.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_SLAB.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_STAIRS.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_WALL.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_CHISELED_SANDSTONE.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_CUT_SANDSTONE.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_CUT_SANDSTONE_SLAB.asValue()
        )

        registerPallet(
            GenerationsBlocks.GOLDEN_TEMPLE_DARK_PRISMARINE.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_DARK_PRISMARINE_SLAB.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_DARK_PRISMARINE_STAIRS.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_DARK_PRISMARINE_WALL.asValue(),
            null,
            null,
            true
        )
        registerPallet(
            GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_SLAB.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_STAIRS.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_WALL.asValue(),
            null,
            null,
            true
        )
        registerPallet(
            GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_BRICKS.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_BRICK_SLAB.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_BRICK_STAIRS.asValue(),
            GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_BRICK_WALL.asValue(),
            null,
            null,
            true
        )

        //Subway
        registerPallet(
            GenerationsBlocks.SUBWAY_WALL.asValue(),
            GenerationsBlocks.SUBWAY_WALL_SLAB.asValue(),
            GenerationsBlocks.SUBWAY_WALL_STAIRS.asValue(),
            null,
            null,
            null,
            true
        )
        registerPallet(
            GenerationsBlocks.SUBWAY_WALL_2.asValue(),
            GenerationsBlocks.SUBWAY_WALL_2_SLAB.asValue(),
            GenerationsBlocks.SUBWAY_WALL_2_STAIRS.asValue(),
            null,
            null,
            null,
            true
        )

        registerBlockItem(GenerationsBlocks.POKEMART_SIGN.asValue())
        registerBlockItem(GenerationsBlocks.POKECENTER_SIGN.asValue())

        registerBlockItem(GenerationsBlocks.RUINS_WALL.asValue())
        registerBlockItem(GenerationsBlocks.DUSTY_RUINS_WALL.asValue())

        registerPillar(GenerationsBlocks.CASTLE_PILLAR.asValue())
        registerPillar(GenerationsBlocks.BROKEN_CASTLE_PILLAR.asValue())

        registerPillar(GenerationsBlocks.PRISMARINE_PILLAR.asValue())
        registerPillar(GenerationsBlocks.BROKEN_PRISMARINE_PILLAR.asValue())

        registerPillar(GenerationsBlocks.DARK_PRISMARINE_PILLAR.asValue())
        registerPillar(GenerationsBlocks.BROKEN_DARK_PRISMARINE_PILLAR.asValue())

        registerPillar(GenerationsBlocks.ICE_PILLAR.asValue())
        registerPillar(GenerationsBlocks.BROKEN_ICE_PILLAR.asValue())

        registerPillar(GenerationsBlocks.HAUNTED_PILLAR.asValue())
        registerPillar(GenerationsBlocks.BROKEN_HAUNTED_PILLAR.asValue())

        registerBlockItem(GenerationsBlocks.DAWN_STONE_BLOCK.asValue())
        registerBlockItem(GenerationsBlocks.DUSK_STONE_BLOCK.asValue())
        registerBlockItem(GenerationsBlocks.FIRE_STONE_BLOCK.asValue())
        registerBlockItem(GenerationsBlocks.ICE_STONE_BLOCK.asValue())
        registerBlockItem(GenerationsBlocks.LEAF_STONE_BLOCK.asValue())
        registerBlockItem(GenerationsBlocks.MOON_STONE_BLOCK.asValue())
        registerBlockItem(GenerationsBlocks.SHINY_STONE_BLOCK.asValue())
        registerBlockItem(GenerationsBlocks.SUN_STONE_BLOCK.asValue())
        registerBlockItem(GenerationsBlocks.THUNDER_STONE_BLOCK.asValue())
        registerBlockItem(GenerationsBlocks.WATER_STONE_BLOCK.asValue())

        unownBlock(GenerationsBlocks.UNOWN_BLOCK_A.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_B.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_C.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_D.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_E.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_F.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_G.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_H.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_I.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_J.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_K.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_L.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_M.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_N.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_O.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_P.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_Q.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_R.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_S.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_T.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_U.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_V.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_W.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_X.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_Y.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_Z.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_EXCLAMATION_MARK.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_QUESTION_MARK.asValue())
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_BLANK.asValue())

        registerBlockItem(GenerationsBlocks.ULTRA_SAND.asValue())

        registerBlockItem(GenerationsBlocks.CRATE.asValue())

        registerBlockItem(GenerationsBlocks.GOLDEN_TEMPLE_SAND.asValue())

        registerPallet(
            GenerationsBlocks.RUBY_BLOCK.asValue(),
            GenerationsBlocks.RUBY_SLAB.asValue(),
            GenerationsBlocks.RUBY_STAIRS.asValue(),
            GenerationsBlocks.RUBY_WALL.asValue(),
            null,
            null,
            true
        )
        registerPallet(
            GenerationsBlocks.SAPPHIRE_BLOCK.asValue(),
            GenerationsBlocks.SAPPHIRE_SLAB.asValue(),
            GenerationsBlocks.SAPPHIRE_STAIRS.asValue(),
            GenerationsBlocks.SAPPHIRE_WALL.asValue(),
            null,
            null,
            true
        )
        registerPallet(
            GenerationsBlocks.CRYSTAL_BLOCK.asValue(),
            GenerationsBlocks.CRYSTAL_SLAB.asValue(),
            GenerationsBlocks.CRYSTAL_STAIRS.asValue(),
            GenerationsBlocks.CRYSTAL_WALL.asValue(),
            null,
            null,
            true
        )
        registerBlockItem(GenerationsBlocks.CRYSTAL_LIGHT.asValue())
        registerBlockItem(GenerationsBlocks.SILICON_BLOCK.asValue())
        registerBlockItem(GenerationsBlocks.Z_BLOCK.asValue())

        registerMushroom(GenerationsBlocks.BALLONLEA_BLUE_MUSHROOM.asValue())
        registerMushroom(GenerationsBlocks.BALLONLEA_GREEN_MUSHROOM.asValue())
        registerMushroom(GenerationsBlocks.BALLONLEA_PINK_MUSHROOM.asValue())
        registerMushroom(GenerationsBlocks.BALLONLEA_YELLOW_MUSHROOM.asValue())

        registerMushroom(GenerationsBlocks.GROUP_BALLONLEA_BLUE_MUSHROOM.asValue())
        registerMushroom(GenerationsBlocks.GROUP_BALLONLEA_GREEN_MUSHROOM.asValue())
        registerMushroom(GenerationsBlocks.GROUP_BALLONLEA_PINK_MUSHROOM.asValue())
        registerMushroom(GenerationsBlocks.GROUP_BALLONLEA_YELLOW_MUSHROOM.asValue())

        registerMushroom(GenerationsBlocks.TALL_BALLONLEA_BLUE_MUSHROOM.asValue())
        registerMushroom(GenerationsBlocks.TALL_BALLONLEA_GREEN_MUSHROOM.asValue())
        registerMushroom(GenerationsBlocks.TALL_BALLONLEA_PINK_MUSHROOM.asValue())
        registerMushroom(GenerationsBlocks.TALL_BALLONLEA_YELLOW_MUSHROOM.asValue())

        registerMushroom(GenerationsBlocks.DOUBLE_BALLONLEA_BLUE_MUSHROOM.asValue())
        registerMushroom(GenerationsBlocks.DOUBLE_BALLONLEA_GREEN_MUSHROOM.asValue())
        registerMushroom(GenerationsBlocks.DOUBLE_BALLONLEA_PINK_MUSHROOM.asValue())
        registerMushroom(GenerationsBlocks.DOUBLE_BALLONLEA_YELLOW_MUSHROOM.asValue())


        registerFlabebeFlower(GenerationsBlocks.FLABEBE_FLOWER_AZ.asValue())
        registerFlabebeFlower(GenerationsBlocks.FLABEBE_FLOWER_BLUE.asValue())
        registerFlabebeFlower(GenerationsBlocks.FLABEBE_FLOWER_ORANGE.asValue())
        registerFlabebeFlower(GenerationsBlocks.FLABEBE_FLOWER_RED.asValue())
        registerFlabebeFlower(GenerationsBlocks.FLABEBE_FLOWER_WHITE.asValue())
        registerFlabebeFlower(GenerationsBlocks.FLABEBE_FLOWER_YELLOW.asValue())

        //Ultra Space
        registerSandStonePallet(
            GenerationsBlocks.ULTRA_SANDSTONE.asValue(),
            GenerationsBlocks.ULTRA_SANDSTONE_SLAB.asValue(),
            GenerationsBlocks.ULTRA_SANDSTONE_STAIRS.asValue(),
            GenerationsBlocks.ULTRA_SANDSTONE_WALL.asValue(),
            GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE.asValue(),
            GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_SLAB.asValue(),
            GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_STAIRS.asValue(),
            GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_WALL.asValue(),
            GenerationsBlocks.ULTRA_CHISELED_SANDSTONE.asValue(),
            GenerationsBlocks.ULTRA_CUT_SANDSTONE.asValue(),
            GenerationsBlocks.ULTRA_CUT_SANDSTONE_SLAB.asValue()
        )
        registerBlockItem(GenerationsBlocks.GHOST_LANTERN.asValue())
        registerBlockItem(GenerationsBlocks.MACHINE_BLOCK.asValue())
        registerBlockItem(GenerationsBlocks.RUINS_SAND.asValue())
        registerBlockItem(GenerationsBlocks.BURST_TURF.asValue())

        registerBlockItem(GenerationsBlocks.WARNING_BLOCK.asValue())

        registerPumpkin(GenerationsBlocks.CURSED_PUMPKIN.asValue())
        registerCarvedPumpkin(GenerationsBlocks.CURSED_CARVED_PUMPKIN.asValue(), GenerationsBlocks.CURSED_PUMPKIN.asValue())
        registerCarvedPumpkin(GenerationsBlocks.CURSED_JACK_O_LANTERN.asValue(), GenerationsBlocks.CURSED_PUMPKIN.asValue())

        registerDoor(GenerationsBlocks.POKECENTER_DOOR.asValue())


        pokeBallChests(GenerationsBlocks.POKEBALL_CHEST.asValue(), "poke_ball")
        pokeBallChests(GenerationsBlocks.GREATBALL_CHEST.asValue(), "great_ball")
        pokeBallChests(GenerationsBlocks.ULTRABALL_CHEST.asValue(), "ultra_ball")
        pokeBallChests(GenerationsBlocks.MASTERBALL_CHEST.asValue(), "master_ball")

        //Furnaces
        registerFurnace(GenerationsUtilityBlocks.CHARGE_STONE_FURNACE.asValue())
        registerFurnace(GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE.asValue())
        registerFurnace(GenerationsUtilityBlocks.CHARGE_STONE_SMOKER.asValue())
        registerFurnace(GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE.asValue())
        registerFurnace(GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE.asValue())
        registerFurnace(GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER.asValue())

        registerBlockItem(GenerationsBlocks.RICH_SOIL_1.asValue())
        registerBlockItem(GenerationsBlocks.RICH_SOIL_2.asValue())
        registerBlockItem(GenerationsBlocks.RICH_SOIL_3.asValue())
        registerBlockItem(GenerationsBlocks.RICH_SOIL_4.asValue())

        registerLog(GenerationsBlocks.GHOST_PILLAR.asValue())

        GenerationsPokeDolls.all().forEach(Consumer(this::registerPokeDoll))

        //        registerNoModel(GenerationsDecorationBlocks.VENDING_MACHINE.block());
//        registerNoModel(GenerationsDecorationBlocks.PASTEL_BEAN_BAG.block());
        GenerationsDecorationBlocks.BALL_DISPLAY_BLOCKS.forEach { block ->
            registerBlockItemParticle(
                block,
                "ball_displays",
                true
            )
        }


        registerBlockItemParticleWithDrop(GenerationsBlocks.POKECENTER_SCARLET_SIGN, "sign")


        GenerationsShrines.all().forEach(Consumer { block: Block ->
            val block1 = block
            if (block1 is PrisonBottleStemBlock || block1 is PrisonBottleBlock) registerBlockItemParticle(
                block1,
                "legend_items",
                true
            )
            else registerBlockItemParticle(
                block1,
                "shrines",
                !(block1 is LunarShrineBlock || block1 is CelestialAltarBlock)
            )
        })
        GenerationsUtilityBlocks.BALL_LOOTS.forEach { block ->
            registerBlockItemParticle(
                block,
                "ball_loots",
                true
            )
        }
        registerBlockItemParticleWithDrop(GenerationsUtilityBlocks.TRASH_CAN, "utility_blocks")
        registerBlockItemParticleWithDrop(GenerationsUtilityBlocks.COOKING_POT, "utility_blocks")
        registerBlockItemParticleWithDrop(GenerationsUtilityBlocks.ROTOM_PC, "utility_blocks")
        registerBlockItemParticleWithDrop(GenerationsUtilityBlocks.TABLE_PC, "utility_blocks")

        registerDyeGroup(GenerationsDecorationBlocks.PASTEL_BEAN_BAG, "bean_bags")
        registerDyeGroup(GenerationsDecorationBlocks.VENDING_MACHINE, "vending_machines")
        registerDyeGroup(GenerationsDecorationBlocks.SWIVEL_CHAIR, "swivel_chairs")
        registerDyeGroup(GenerationsDecorationBlocks.STREET_LAMP, "street_lamp")
        registerDyeGroup(GenerationsDecorationBlocks.COUCH_CORNER_LEFT, "couch")
        registerDyeGroup(GenerationsDecorationBlocks.COUCH_CORNER_RIGHT, "couch")
        registerDyeGroup(GenerationsDecorationBlocks.COUCH_ARM_LEFT, "couch")
        registerDyeGroup(GenerationsDecorationBlocks.COUCH_ARM_RIGHT, "couch")
        registerDyeGroup(GenerationsDecorationBlocks.COUCH_OTTOMAN, "couch")
        registerDyeGroup(GenerationsDecorationBlocks.COUCH_MIDDLE, "couch")
        registerDyeGroup(GenerationsUtilityBlocks.PC, "pcs")


        //        registerNoModel(GenerationsUtilityBlocks.CLOCK.block());
//        registerNoModel(GenerationsUtilityBlocks.HEALER.block());
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.HOUSE_LAMP, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.BENCH, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.GREATBALL_CUSHION, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.POKEBALL_CUSHION, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.FOONGUS_CUSHION, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.MASTERBALL_CUSHION, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.COUCH, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.BUSH, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.HDTV, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.DESK, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.FRIDGE, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.WORK_DESK, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SHELF, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.BOOK_SHELF, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.POKEBALL_PILLAR, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SHOP_DISPLAY_CASE_1, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SHOP_DISPLAY_CASE_2, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SHOP_DISPLAY_SMALL_1, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SHOP_DISPLAY_SMALL_2, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SHOP_DISPLAY_LARGE_SHELF_1, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SHOP_DISPLAY_LARGE_SHELF_2, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SWITCH, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.LITWICK_CANDLE, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.LITWICK_CANDLES, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.DOUBLE_STREET_LAMP, "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SNORLAX_BEAN_BAG, "bean_bags")
        registerBlockItemParticleWithDrop(GenerationsUtilityBlocks.RKS_MACHINE, "utility_blocks")
        registerBlockItemParticleWithDrop(GenerationsUtilityBlocks.SCARECROW, "utility_blocks")

        registerBlockItem(GenerationsBlocks.LIGHTNING_LANTERN.asValue())

        registerInfestedBlock(GenerationsBlocks.INFESTED_CHARGE_STONE.asValue())
        registerInfestedBlock(GenerationsBlocks.INFESTED_VOLCANIC_STONE.asValue())
        registerInfestedBlock(GenerationsBlocks.INFESTED_CHARGE_COBBLESTONE.asValue())
        registerInfestedBlock(GenerationsBlocks.INFESTED_VOLCANIC_COBBLESTONE.asValue())
        registerInfestedBlock(GenerationsBlocks.INFESTED_CHARGE_STONE_BRICKS.asValue())
        registerInfestedBlock(GenerationsBlocks.INFESTED_VOLCANIC_STONE_BRICKS.asValue())
        registerInfestedBlock(GenerationsBlocks.INFESTED_MOSSY_CHARGE_STONE_BRICKS.asValue())
        registerInfestedBlock(GenerationsBlocks.INFESTED_MOSSY_VOLCANIC_STONE_BRICKS.asValue())
        registerInfestedBlock(GenerationsBlocks.INFESTED_CRACKED_CHARGE_STONE_BRICKS.asValue())
        registerInfestedBlock(GenerationsBlocks.INFESTED_CRACKED_VOLCANIC_STONE_BRICKS.asValue())
        registerInfestedBlock(GenerationsBlocks.INFESTED_CHISELED_CHARGE_STONE_BRICKS.asValue())
        registerInfestedBlock(GenerationsBlocks.INFESTED_CHISELED_VOLCANIC_STONE_BRICKS.asValue())

        registerDripStone(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE.asValue())

        registerCarpetLike(
            GenerationsUtilityBlocks.BLACK_ELEVATOR.asValue(), GenerationsUtilityBlocks.BLUE_ELEVATOR.asValue(),
            GenerationsUtilityBlocks.BROWN_ELEVATOR.asValue(), GenerationsUtilityBlocks.CYAN_ELEVATOR.asValue(),
            GenerationsUtilityBlocks.GRAY_ELEVATOR.asValue(), GenerationsUtilityBlocks.GREEN_ELEVATOR.asValue(),
            GenerationsUtilityBlocks.LIGHT_BLUE_ELEVATOR.asValue(), GenerationsUtilityBlocks.LIGHT_GRAY_ELEVATOR.asValue(),
            GenerationsUtilityBlocks.LIME_ELEVATOR.asValue(), GenerationsUtilityBlocks.MAGENTA_ELEVATOR.asValue(),
            GenerationsUtilityBlocks.ORANGE_ELEVATOR.asValue(), GenerationsUtilityBlocks.PINK_ELEVATOR.asValue(),
            GenerationsUtilityBlocks.PURPLE_ELEVATOR.asValue(), GenerationsUtilityBlocks.RED_ELEVATOR.asValue(),
            GenerationsUtilityBlocks.WHITE_ELEVATOR.asValue(), GenerationsUtilityBlocks.YELLOW_ELEVATOR.asValue()
        )

        registerBox()
    }

    private fun registerBox() {
        val closed = UncheckedModelFile("generations_core:block/utility_blocks/box_closed")
        val open = UncheckedModelFile("generations_core:block/utility_blocks/box_open")

        val box = GenerationsUtilityBlocks.BOX

        getVariantBuilder(box.value())
            .partialState().with(BoxBlock.OPEN, true).with(BoxBlock.FACING, Direction.NORTH)
            .modelForState().modelFile(open).addModel()
            .partialState().with(BoxBlock.OPEN, true).with(BoxBlock.FACING, Direction.EAST)
            .modelForState().modelFile(open).rotationY(90).addModel()
            .partialState().with(BoxBlock.OPEN, true).with(BoxBlock.FACING, Direction.SOUTH)
            .modelForState().modelFile(open).rotationY(180).addModel()
            .partialState().with(BoxBlock.OPEN, true).with(BoxBlock.FACING, Direction.WEST)
            .modelForState().modelFile(open).rotationY(270).addModel()
            .partialState().with(BoxBlock.OPEN, false).with(BoxBlock.FACING, Direction.NORTH)
            .modelForState().modelFile(closed).addModel()
            .partialState().with(BoxBlock.OPEN, false).with(BoxBlock.FACING, Direction.EAST)
            .modelForState().modelFile(closed).rotationY(90).addModel()
            .partialState().with(BoxBlock.OPEN, false).with(BoxBlock.FACING, Direction.SOUTH)
            .modelForState().modelFile(closed).rotationY(180).addModel()
            .partialState().with(BoxBlock.OPEN, false).with(BoxBlock.FACING, Direction.WEST)
            .modelForState().modelFile(closed).rotationY(270).addModel()

        itemModels().getBuilder(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(box.value().asItem())).toString())
            .parent(UncheckedModelFile("item/generated"))
            .texture("layer0", key(box.value()).withPrefix("item/blocks/utility_blocks/"))

        dropSelfList.add(box)
    }

    private fun registerDyeGroup(
        group: DyedGroup,
        dir: String
    ) {
        group.block.values.forEach { block ->
            registerBlockItemParticle(block, dir, true)
            dropSelfList.add(block)
        }
    }

    private fun <T : Block> registerNoModel(block: T) {
        this.simpleBlock(block, *ConfiguredModel.builder().modelFile(models().getBuilder(block.id.path)).build())
    }

    private fun registerPallet(
        block: Block,
        slab: SlabBlock?,
        stairs: StairBlock?,
        wall: WallBlock?,
        button: ButtonBlock?,
        pressurePlate: PressurePlateBlock?,
        dropSelf: Boolean
    ) {
        if (!registered(block)) registerBlockItem(block)
        if (dropSelf) dropSelfList.add(block)

        if (slab != null) {
            if (!registered(slab)) registerSlab(slab, block)
            if (dropSelf) dropSelfList.add(slab)
        }
        if (stairs != null) {
            if (!registered(stairs)) registerStairs(stairs, block)
            if (dropSelf) dropSelfList.add(stairs)
        }
        if (wall != null) {
            if (!registered(wall)) registerWall(wall, block)
            if (dropSelf) dropSelfList.add(wall)
        }
        if (button != null) {
            if (!registered(button)) registerButton(button, block)
            if (dropSelf) dropSelfList.add(button)
        }
        if (pressurePlate != null) {
            if (!registered(pressurePlate)) registerPressurePlate(pressurePlate, block)
            if (dropSelf) dropSelfList.add(pressurePlate)
        }
    }


    private fun registerSandStonePallet(
        sandstone: Block,
        slab: SlabBlock?,
        stairs: StairBlock?,
        wall: WallBlock?,
        smooth: Block?,
        smoothslab: SlabBlock?,
        smoothstair: StairBlock?,
        smoothWall: WallBlock?,
        chiseled: Block,
        cutSandstone: Block?,
        cutSlab: SlabBlock?
    ) {
        if (!registered(sandstone)) registerSandStone(sandstone)
        dropSelfList.add(sandstone)
        if (slab != null) {
            if (!registered(slab)) registerSandStoneSlab(slab, sandstone)
            dropSelfList.add(slab)
        }
        if (stairs != null) {
            if (!registered(stairs)) registerSandStoneStairs(stairs, sandstone)
            dropSelfList.add(stairs)
        }
        if (wall != null) {
            if (!registered(wall)) registerWall(wall, sandstone)
            dropSelfList.add(wall)
        }
        val top = id("block/" + sandstone.id.path + "_top")
        if (smooth != null) {
            val smoothsandstoneBlock =
                models().cubeAll(smooth.id.path, id("block/" + sandstone.id.path + "_top"))
            if (!registered(smooth)) simpleBlockWithItem(smooth, smoothsandstoneBlock)

            dropSelfList.add(smooth)
            if (smoothslab != null) {
                if (!registered(smoothslab)) {
                    slabBlock(smoothslab, id("block/" + smooth.id.path), top, top, top)
                    simpleBlockItem(smoothslab, models().slab(smoothslab.id.path, top, top, top))
                }
                dropSelfList.add(smoothslab)
            }
            if (smoothstair != null) {
                if (!registered(smoothstair)) {
                    stairsBlock(smoothstair, top)
                    simpleBlockItem(smoothstair, models().stairs(smoothstair.id.path, top, top, top))
                }
                dropSelfList.add(smoothstair)
            }
            if (smoothWall != null) {
                if (!registered(smoothWall)) {
                    wallBlock(smoothWall, top)
                    simpleBlockItem(smoothWall, models().wallInventory(smoothWall.id.path, top))
                }
                dropSelfList.add(smoothWall)
            }

            if (!registered(chiseled)) cubeColumn(chiseled, top)
            if (cutSandstone != null) {
                if (!registered(cutSandstone)) cubeColumn(cutSandstone, top)
                if (cutSlab != null) {
                    if (!registered(cutSlab)) {
                        slabBlock(
                            cutSlab,
                            id("block/" + cutSandstone.id.path),
                            top,
                            id("block/" + cutSandstone.id.path),
                            top
                        )
                        simpleBlockItem(
                            cutSlab,
                            models().slab(
                                cutSlab.id.path,
                                id("block/" + cutSandstone.id.path),
                                top,
                                top
                            )
                        )
                    }
                    dropSelfList.add(cutSlab)
                }
            }
        }
    }

    private fun cubeColumn(cutSandstone: Block, top: ResourceLocation) {
        if (cutSandstone != null) {
            val cutBlock =
                models().cubeColumn(cutSandstone.id.path, id("block/" + cutSandstone.id.path), top)
            simpleBlockWithItem(cutSandstone, cutBlock)
            dropSelfList.add(cutSandstone)
        }
    }

    private fun registerStairs(stairs: StairBlock, texturedBlock: Block) {
        val texture = ModelLocationUtils.getModelLocation(texturedBlock)
        stairsBlock(stairs, texture)
        simpleBlockItem(stairs, itemModels().stairs("block/" + key(stairs).path, texture, texture, texture))
    }

    private fun registerSlab(slab: SlabBlock, texturedBlock: Block) {
        val texture = ModelLocationUtils.getModelLocation(texturedBlock)
        slabBlock(slab, texture, texture)
        simpleBlockItem(slab, itemModels().slab("block/" + key(slab).path, texture, texture, texture))
    }

    private fun registerWall(wall: WallBlock, texturedBlock: Block) {
        val texture = ModelLocationUtils.getModelLocation(texturedBlock)
        wallBlock(wall, texture)
        simpleBlockItem(wall, itemModels().wallInventory("block/" + key(wall).path, texture))
    }

    private fun registerFence(fence: FenceBlock, block: Block) {
        val texture = if (block == null) ModelLocationUtils.getModelLocation(fence)
        else ModelLocationUtils.getModelLocation(block)

        fenceBlock(fence, texture)
        simpleBlockItem(fence, itemModels().fenceInventory("block/" + key(fence).path, texture))
    }

    private fun registerGate(gate: FenceGateBlock, fence: FenceBlock?, block: Block?) {
        val texture: ResourceLocation
        if (block == null) {
            val fenceId = key(fence)
            texture = ModelLocationUtils.getModelLocation(fence)
            simpleBlockItem(gate, itemModels().fenceGate("block/" + fenceId.path, texture))
        } else {
            val gateId = key(gate)
            texture = ModelLocationUtils.getModelLocation(block)
            simpleBlockItem(gate, itemModels().fenceGate("block/" + gateId.path, texture))
        }

        fenceGateBlock(gate, texture)
    }

    private fun registerDoor(door: DoorBlock) {
        val blockId = key(door)
        doorBlockWithRenderType(
            door,
            ResourceLocation.fromNamespaceAndPath(blockId.namespace, "block/door/" + blockId.path + "_bottom"),
            ResourceLocation.fromNamespaceAndPath(blockId.namespace, "block/door/" + blockId.path + "_top"),
            "cutout"
        )
    }

    private fun registerPressurePlate(pressurePlate: PressurePlateBlock, texturedBlock: Block) {
        val texture = ModelLocationUtils.getModelLocation(texturedBlock)
        pressurePlateBlock(pressurePlate, texture)
        itemModels().pressurePlate(key(pressurePlate).path, texture)
    }

    private fun registerButton(button: Block, texturedBlock: Block) {
        val buttonId = key(button)
        val textureBlockId = key(texturedBlock)

        val texture = ResourceLocation.fromNamespaceAndPath(textureBlockId.namespace, "block/" + textureBlockId.path)
        buttonBlock(button as ButtonBlock, texture)
        itemModels().buttonInventory(buttonId.path, texture)
    }

    private fun registerTrapDoor(trapDoor: TrapDoorBlock?, texturedBlock: Block) {
        val texture = ModelLocationUtils.getModelLocation(texturedBlock)
        val trapDoorId = key(trapDoor)
        trapdoorBlockWithRenderType(trapDoor, texture, true, "cutout")
        itemModels().trapdoorBottom(trapDoorId.path, texture)
    }

    private fun registerTrapDoor(trapDoor: TrapDoorBlock) {
        registerTrapDoor(trapDoor, trapDoor)
    }

    private fun registerSign(sign: StandingSignBlock?, wallsign: WallSignBlock, plank: Block) {
        signBlock(sign, wallsign, blockTexture(plank))
    }

    private fun registerHangingSign(sign: CeilingHangingSignBlock, wallsign: WallHangingSignBlock, plank: Block) {
        val thing: ModelFile = models().sign(key(sign).path, blockTexture(plank))
        simpleBlock(sign, thing)
        simpleBlock(wallsign, thing)
    }

    private fun registerMushroom(crossBlock: GenerationsMushroomBlock) {
        getVariantBuilder(crossBlock).forAllStates { state: BlockState? ->
            ConfiguredModel.builder()
                .modelFile(models().cross(crossBlock.id.path, blockTexture(crossBlock)).renderType("cutout"))
                .build()
        }

        MUSHROOM_BLOCKS.add(crossBlock)
    }

    private fun registerFlabebeFlower(crossBlock: FlabebeFlowerBlock) {
        val name = key(crossBlock)
        val location = ResourceLocation.fromNamespaceAndPath(name.namespace, ModelProvider.BLOCK_FOLDER + "/flowers/" + name.path)

        getVariantBuilder(crossBlock).forAllStates { state: BlockState? ->
            ConfiguredModel.builder()
                .modelFile(models().cross(crossBlock.id.path, location).renderType("cutout"))
                .build()
        }

        dropSelfList.add(crossBlock)
    }

    private fun registerLog(log: RotatedPillarBlock) {
        val name = log.id.path
        val logBlock = models().withExistingParent(log.id.toString(), mcLoc("block/oak_log"))
            .texture("side", id("block/$name"))
            .texture("top", id("block/" + name + "_top"))
        logBlock(log)
        simpleBlockItem(log, logBlock)
    }

    private fun registerBlockItem(block: Block) {
        simpleBlockWithItem(block, cubeAll(block))
        dropSelfList.add(block)
    }

    private fun registerPillar(block: Block) {
        val texture = id("block/${block.id.path}")
        val item = id("item/blocks/pillars/" + BuiltInRegistries.BLOCK.getKey(block).path)

        val model = models().withExistingParent(block.id.path, id("block/pillar"))
            .texture("texture", texture)
            .texture("particle", item)
        val partialModel = models().withExistingParent("${block.id.path}_partial", id("block/pillar_partial"))
            .texture("texture", texture)
            .texture("particle", item)
        val connectedModel = models().withExistingParent("${block.id.path}_connected", id("block/pillar_connected"))
            .texture("texture", texture)
            .texture("particle", item)

        variantBuilder(block) {
            forAllStates {
                val isTop = it.getValue(PillarBlock.CONNECTED_TOP)
                val isBottom = it.getValue(PillarBlock.CONNECTED_BOTTOM)

                when (it.getValue(PillarBlock.AXIS)) {
                    Direction.Axis.X -> {
                        when {
                            isTop && isBottom ->    connectedModel. configure(90, 90)
                            isTop && !isBottom ->   partialModel.   configure(90, 90)
                            !isTop && isBottom ->   partialModel.   configure(270, 90)
                            else ->                 model.          configure(90, 90)
                        }
                    }
                    Direction.Axis.Y -> {
                        when {
                            isTop && isBottom ->    connectedModel. configure()
                            isTop && !isBottom ->   partialModel.   configure()
                            !isTop && isBottom ->   partialModel.   configure(180, 0)
                            else ->                 model.          configure()
                        }
                    }

                    Direction.Axis.Z -> {
                        when {
                            isTop && isBottom ->    connectedModel. configure(90, 180)
                            isTop && !isBottom ->   partialModel.   configure(90, 180)
                            !isTop && isBottom ->   partialModel.   configure(270, 180)
                            else ->                 model.          configure(90, 180)
                        }
                    }
                    else -> {
                        model.configure()
                    }
                }
            }
        }

        itemModels().getBuilder(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(block.asItem())).toString())
            .parent(UncheckedModelFile("item/generated"))
            .texture("layer0", item)

        dropSelfList.add(block)
    }

    private fun VariantBlockStateBuilder.partialState(block: VariantBlockStateBuilder.PartialBlockstate.() -> Unit): VariantBlockStateBuilder.PartialBlockstate? {
        val partialState = partialState()
        block.invoke(partialState)
        return partialState
    }

    private fun variantBuilder(block: Block, function: VariantBlockStateBuilder.() -> Unit) {
        var builder = getVariantBuilder(block)
        builder.configure(function)

    }

    private fun VariantBlockStateBuilder.configure(function: VariantBlockStateBuilder.() -> Unit) {
        function.invoke(this)
    }


    private fun unownBlock(block: Block) {
        val model = models().cubeBottomTop(
            block.id.path,
            block.id.withPrefix("block/"),
            id("block/unown_block_bottom"),
            id("block/unown_block_top")
        )

        simpleBlockWithItem(block, model)
        dropSelfList.add(block)
    }

    private fun registerBlockItemParticleWithDrop(block: Holder<Block>, name: String) {
        registerBlockItemParticle(block, name, true)
        dropSelfList.add(block.value())
    }

    private fun registerBlockItemParticle(holder: Holder<Block>, name: String, shouldGenerateItems: Boolean) {
        registerBlockItemParticle(holder.value(), name, shouldGenerateItems)
    }

    private fun registerBlockItemParticle(block: Block, name: String, shouldGenerateItems: Boolean) {
        val blockId = key(block)
        val textureId = blockId.withPrefix("item/blocks/$name/")
        try {
            simpleBlock(block, models().sign(blockId.path, textureId))
            if (shouldGenerateItems) itemModels().getBuilder(
                Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(block.asItem())).toString()
            ).parent(UncheckedModelFile("item/generated"))
                .texture("layer0", textureId)
        } catch (ignored: Exception) {
            DataProvider.LOGGER.error("Block: $name -> $textureId")
            simpleBlock(block, models().sign(blockId.path, id("item/placeholder")))
            if (shouldGenerateItems) itemModels().getBuilder(
                Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(block.asItem())).toString()
            ).parent(UncheckedModelFile("item/generated"))
                .texture("layer0", id("item/placeholder"))
        }
    }

    private fun registerPokeDoll(block: Block) {
        val blockId = checkNotNull(BuiltInRegistries.BLOCK.getKey(block))
        val textureId = ResourceLocation.fromNamespaceAndPath(blockId.namespace, "item/dolls/" + blockId.path.replace("shiny_", "shiny/"))

        simpleBlock(
            block,
            models().sign(Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(block))!!.path, textureId)
        )

        val key = Objects.requireNonNull(
            BuiltInRegistries.BLOCK.getKey(block),
            "Tried to create json model for unregistered Item."
        )!!
        var texPath = id("item/dolls/" + key.path)
        if (key.path.startsWith("shiny_")) texPath =
            id("item/dolls/shiny/" + key.path.replace("shiny_", ""))


        itemModels().getBuilder(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(block.asItem())).toString())
            .parent(UncheckedModelFile("item/generated"))
            .texture("layer0", texPath)
    }

    private fun registerSandStone(block: Block) {
        val name = block.id.path
        simpleBlockWithItem(
            block,
            models().cubeBottomTop(
                name,
                id("block/$name"),
                id("block/" + name + "_bottom"),
                id("block/" + name + "_top")
            )
        )
    }

    private fun registerSandStoneStairs(stairs: StairBlock, block: Block) {
        stairsBlock(
            stairs,
            id("block/" + block.id.path),
            id("block/" + block.id.path + "_bottom"),
            id("block/" + block.id.path + "_top")
        )
        simpleBlockItem(
            stairs,
            itemModels().stairs(
                "block/" + stairs.id.path,
                id("block/" + block.id.path),
                id("block/" + block.id.path + "_bottom"),
                id("block/" + block.id.path + "_top")
            )
        )
    }

    private fun registerSandStoneSlab(slab: SlabBlock, block: Block) {
        slabBlock(
            slab,
            id("block/" + block.id.path),
            id("block/" + block.id.path),
            id("block/" + block.id.path + "_bottom"),
            id("block/" + block.id.path + "_top")
        )
        simpleBlockItem(
            slab,
            itemModels().slab(
                "block/" + slab.id.path,
                id("block/" + block.id.path),
                id("block/" + block.id.path + "_bottom"),
                id("block/" + block.id.path + "_top")
            )
        )
    }

    private fun registerGlassBlock(block: TransparentBlock) {
        getVariantBuilder(block).forAllStates { state: BlockState? ->
            ConfiguredModel.builder()
                .modelFile(
                    models().cubeAll(block.id.path, modLoc("block/" + block.id.path)).renderType("cutout_mipped")
                )
                .build()
        }
        simpleBlockItem(block, cubeAll(block))
    }

    @SafeVarargs
    private fun registerCarpetLike(vararg block: Block) {
        for (carpet in block) {
            simpleBlockWithItem(carpet, models().carpet(carpet.id.path, modLoc("block/" + carpet.id.path)))
            dropSelfList.add(carpet)
        }
    }

    private fun registerOreBlocks() {
        GenerationsOres.all().forEach { ore ->
            val name = ore.id.path
            simpleBlockWithItem(ore, models().singleTexture(name, mcLoc("block/cube_all"), "all", id("block/ores/$name")))
        }
    }

    private fun pokeBallChests(chest: GenericChestBlock, ballName: String) {
        simpleBlock(
            chest,
            models().getBuilder(chest.id.path).texture("particle", id("item/pokeballs/$ballName"))
        )
    }


    private fun registerPumpkin(pumpkin: Block) {
        val pumpkinModel = models().withExistingParent(pumpkin.id.toString(), mcLoc("block/pumpkin"))
            .texture("side", id("block/" + pumpkin.id.path + "_side"))
            .texture("end", id("block/" + pumpkin.id.path + "_end"))
        simpleBlockWithItem(pumpkin, pumpkinModel)
    }


    private fun registerFurnace(furnace: Block) {
        val off = models().orientable(
            furnace.id.path,
            id("block/furnace/" + furnace.id.path + "_side"),
            id("block/furnace/" + furnace.id.path + "_front"),
            id("block/furnace/" + furnace.id.path + "_top")
        )
        val on = models().orientable(
            furnace.id.path + "_on",
            id("block/furnace/" + furnace.id.path + "_side"),
            id("block/furnace/" + furnace.id.path + "_front_on"),
            id("block/furnace/" + furnace.id.path + "_top")
        )
        horizontalBlock(
            furnace
        ) { state: BlockState -> if (state.getValue(FurnaceBlock.LIT)) on else off }
        simpleBlockItem(furnace, off)
    }

    private fun registerCarvedPumpkin(
        carvedPumpkin: Block,
        pumpkin: CarvedPumpkinBlock
    ) {
        val pumpkinModel = models().withExistingParent(carvedPumpkin.id.toString(), mcLoc("block/carved_pumpkin"))
            .texture("side", id("block/" + pumpkin.id.path + "_side"))
            .texture("top", id("block/" + pumpkin.id.path + "_end"))
            .texture("front", id("block/" + carvedPumpkin.id.path))
        horizontalBlock(carvedPumpkin, pumpkinModel)
        simpleBlockItem(carvedPumpkin, pumpkinModel)
    }

    private fun registerCraftingTable(
        table: GenerationsCraftingTableBlock,
        plank: Block
    ) {
        val tableModel = models().withExistingParent(table.id.toString(), mcLoc("block/cube"))
            .texture("down", id("block/" + plank.id.path))
            .texture("east", id("block/crafting_table/" + table.id.path + "_side"))
            .texture("north", id("block/crafting_table/" + table.id.path + "_front"))
            .texture("particle", id("block/crafting_table/" + table.id.path + "_front"))
            .texture("south", id("block/crafting_table/" + table.id.path + "_side"))
            .texture("up", id("block/crafting_table/" + table.id.path + "_top"))
            .texture("west", id("block/crafting_table/" + table.id.path + "_front"))
        simpleBlockWithItem(table, tableModel)
    }

    private fun registerBookShelf(bookshelf: Block, plank: Block) {
        val bookshelfModel = models().withExistingParent(bookshelf.id.toString(), mcLoc("block/cube_column"))
            .texture("side", id("block/" + bookshelf.id.path))
            .texture("end", id("block/" + plank.id.path))
        simpleBlockWithItem(bookshelf, bookshelfModel)
    }

    private fun registerInfestedBlock(block: InfestedBlock) {
        models().withExistingParent(
            block.id.toString().replace("infested_", "") + "_mirrored",
            mcLoc("block/cube_mirrored_all")
        )
            .texture("all", blockTexture(block.hostBlock))
        val normal = UncheckedModelFile(ModelLocationUtils.getModelLocation(block.hostBlock))
        val mirrored = UncheckedModelFile(blockTexture(block.hostBlock).toString() + "_mirrored")

        getVariantBuilder(block).partialState()
            .addModels(
                *ConfiguredModel.builder().modelFile(normal)
                    .nextModel().modelFile(mirrored)
                    .nextModel().modelFile(normal).rotationY(180)
                    .nextModel().modelFile(mirrored).rotationY(180)
                    .build()
            )
        itemModels().withExistingParent(block.id.path, blockTexture(block.hostBlock))
    }

    private fun registerDripStone(dripstone: PointedChargeDripstoneBlock) {
        val name = dripstone.id.toString()
        val parts = arrayOf(
            "down_base",
            "down_middle",
            "down_tip",
            "down_tip_merge",
            "down_frustum",
            "up_base",
            "up_middle",
            "up_tip",
            "up_tip_merge",
            "up_frustum"
        )

        for (part in parts) models().withExistingParent(name + "_" + part, mcLoc("block/pointed_dripstone"))
            .texture("cross", id("block/" + dripstone.id.path + "_" + part))
            .renderType("cutout")

        models().withExistingParent(name, mcLoc("block/pointed_dripstone"))
    }


    private fun registerBlockFamily(family: BlockFamily) {
        registerBlockItem(family.baseBlock)
        family.variants.keys.forEach(Consumer { variant: BlockFamily.Variant -> proccessVariant(variant, family) })
    }

    private fun proccessVariant(variant: BlockFamily.Variant, family: BlockFamily) {
        val original = family.baseBlock
        val variantTarget = family.variants[variant] ?: return
        when (variant) {
            BlockFamily.Variant.BUTTON -> registerButton(variantTarget, original)
            BlockFamily.Variant.CHISELED, BlockFamily.Variant.CRACKED, BlockFamily.Variant.CUT -> simpleBlockWithItem(
                variantTarget,
                cubeAll(variantTarget)
            )

            BlockFamily.Variant.DOOR -> registerDoor(variantTarget as DoorBlock)
            BlockFamily.Variant.FENCE -> registerFence(variantTarget as FenceBlock, original)
            BlockFamily.Variant.FENCE_GATE -> registerGate(
                variantTarget as FenceGateBlock,
                family[BlockFamily.Variant.FENCE] as FenceBlock,
                original
            )

            BlockFamily.Variant.SIGN -> registerSign(
                variantTarget as StandingSignBlock,
                family[BlockFamily.Variant.WALL_SIGN] as WallSignBlock,
                original
            )

            BlockFamily.Variant.SLAB -> registerSlab(variantTarget as SlabBlock, original)
            BlockFamily.Variant.STAIRS -> registerStairs(variantTarget as StairBlock, original)
            BlockFamily.Variant.PRESSURE_PLATE -> registerPressurePlate(variantTarget as PressurePlateBlock, original)
            BlockFamily.Variant.TRAPDOOR -> registerTrapDoor(variantTarget as TrapDoorBlock, original)
            BlockFamily.Variant.WALL -> registerWall(variantTarget as WallBlock, original)
            else -> {}
        }
    }

    private fun registerWoodPallet(
        Log: RotatedPillarBlock,
        StrippedLog: RotatedPillarBlock,
        Plank: Block,
        slab: SlabBlock,
        stair: StairBlock,
        button: ButtonBlock,
        pressurePlate: PressurePlateBlock,
        door: DoorBlock,
        trapDoor: TrapDoorBlock,
        wood: Block,
        StrippedWood: Block,
        fence: FenceBlock,
        gate: FenceGateBlock,
        sign: StandingSignBlock,
        wallSign: WallSignBlock,
        craftingTable: GenerationsCraftingTableBlock,
        hangingSignBlock: CeilingHangingSignBlock,
        wallHangingSignBlock: WallHangingSignBlock,
        bookshelf: Block
    ) {
        if (!registered(Log)) registerLog(Log)
        if (!registered(StrippedLog)) registerLog(StrippedLog)
        if (!registered(Plank)) registerBlockItem(Plank)
        if (!registered(wood)) registerBlockItem(wood)
        if (!registered(StrippedWood)) registerBlockItem(StrippedWood)
        if (!registered(slab)) registerSlab(slab, Plank)
        if (!registered(stair)) registerStairs(stair, Plank)
        if (!registered(button)) registerButton(button, Plank)
        if (!registered(pressurePlate)) registerPressurePlate(pressurePlate, Plank)
        if (!registered(door)) registerDoor(door)
        if (!registered(trapDoor)) registerTrapDoor(trapDoor)
        if (!registered(gate)) registerGate(gate, null, Plank)
        if (!registered(fence)) registerFence(fence, Plank)
        if (!registered(sign)) registerSign(sign, wallSign, Plank)
        if (!registered(hangingSignBlock)) registerHangingSign(
            hangingSignBlock,
            wallHangingSignBlock,
            StrippedLog
        )
        if (!registered(craftingTable)) registerCraftingTable(craftingTable, Plank)
        if (!registered(bookshelf)) registerBookShelf(bookshelf, Plank)
    }

    companion object {
        @JvmField
        val dropSelfList: ArrayList<Any> = ArrayList()
        @JvmField
        val MUSHROOM_BLOCKS: ArrayList<GenerationsMushroomBlock> = ArrayList()
    }
}

fun <T> Holder<Block>.asValue(): T {
    return this.value() as T
}

private fun BlockModelBuilder.configure(rotX: Int = 0, rotY: Int = 0): Array<ConfiguredModel> {
    return arrayOf(ConfiguredModel(this, rotX, rotY, false))
}


