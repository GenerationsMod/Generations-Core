package generations.gg.generations.core.generationscore.forge.datagen.generators.blocks

import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore.*
import generations.gg.generations.core.generationscore.common.world.level.block.*
import generations.gg.generations.core.generationscore.common.world.level.block.entities.BallDisplayBlock
import generations.gg.generations.core.generationscore.common.world.level.block.entities.ModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericChestBlock
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsBlockSet
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsFullBlockSet
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.CelestialAltarBlock
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.LunarShrineBlock
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.PrisonBottleStemBlock
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.BoxBlock
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.DyeableBlock
import generations.gg.generations.core.generationscore.forge.datagen.data.families.GenerationsBlockFamilies
import net.minecraft.core.Direction
import net.minecraft.data.BlockFamily
import net.minecraft.data.DataProvider
import net.minecraft.data.models.model.ModelLocationUtils
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.client.model.generators.*
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile
import net.minecraftforge.registries.ForgeRegistries
import java.util.*
import java.util.function.Consumer

class BlockDatagen(provider: GenerationsBlockStateProvider) : GenerationsBlockStateProvider.Proxied(provider) {
    override fun registerStatesAndModels() {
        registerOreBlocks()
        GenerationsBlockFamilies.getAllFamilies().filter { obj: BlockFamily -> obj.shouldGenerateModel() }
            .forEach { family: BlockFamily -> this.registerBlockFamily(family) }
        GenerationsBlockSet.getBlockSets().forEach(
            Consumer { blockSet: GenerationsBlockSet ->
                val family = blockSet.blockFamily
                if (family.shouldGenerateModel()) registerBlockFamily(family)
            }
        )
        GenerationsFullBlockSet.getFullBlockSets().forEach(
            Consumer { blockSet: GenerationsFullBlockSet ->
                val family = blockSet.blockFamily
                if (family.shouldGenerateModel()) registerBlockFamily(family)
            }
        )
        registerWoodPallet(
            GenerationsWood.ULTRA_DARK_LOG,
            GenerationsWood.STRIPPED_ULTRA_DARK_LOG,
            GenerationsWood.ULTRA_DARK_PLANKS,
            GenerationsWood.ULTRA_DARK_SLAB,
            GenerationsWood.ULTRA_DARK_STAIRS,
            GenerationsWood.ULTRA_DARK_BUTTON,
            GenerationsWood.ULTRA_DARK_PRESSURE_PLATE,
            GenerationsWood.ULTRA_DARK_DOOR,
            GenerationsWood.ULTRA_DARK_TRAPDOOR,
            GenerationsWood.ULTRA_DARK_WOOD,
            GenerationsWood.STRIPPED_ULTRA_DARK_WOOD,
            GenerationsWood.ULTRA_DARK_FENCE,
            GenerationsWood.ULTRA_DARK_FENCE_GATE,
            GenerationsWood.ULTRA_DARK_SIGN,
            GenerationsWood.ULTRA_DARK_WALL_SIGN,
            GenerationsWood.ULTRA_DARK_CRAFTING_TABLE,
            GenerationsWood.ULTRA_DARK_HANGING_SIGN,
            GenerationsWood.ULTRA_DARK_WALL_HANGING_SIGN,
            GenerationsWood.ULTRA_DARK_BOOKSHELF
        )
        registerWoodPallet(
            GenerationsWood.ULTRA_JUNGLE_LOG,
            GenerationsWood.STRIPPED_ULTRA_JUNGLE_LOG,
            GenerationsWood.ULTRA_JUNGLE_PLANKS,
            GenerationsWood.ULTRA_JUNGLE_SLAB,
            GenerationsWood.ULTRA_JUNGLE_STAIRS,
            GenerationsWood.ULTRA_JUNGLE_BUTTON,
            GenerationsWood.ULTRA_JUNGLE_PRESSURE_PLATE,
            GenerationsWood.ULTRA_JUNGLE_DOOR,
            GenerationsWood.ULTRA_JUNGLE_TRAPDOOR,
            GenerationsWood.ULTRA_JUNGLE_WOOD,
            GenerationsWood.STRIPPED_ULTRA_JUNGLE_WOOD,
            GenerationsWood.ULTRA_JUNGLE_FENCE,
            GenerationsWood.ULTRA_JUNGLE_FENCE_GATE,
            GenerationsWood.ULTRA_JUNGLE_SIGN,
            GenerationsWood.ULTRA_JUNGLE_WALL_SIGN,
            GenerationsWood.ULTRA_JUNGLE_CRAFTING_TABLE,
            GenerationsWood.ULTRA_JUNGLE_HANGING_SIGN,
            GenerationsWood.ULTRA_JUNGLE_WALL_HANGING_SIGN,
            GenerationsWood.ULTRA_JUNGLE_BOOKSHELF
        )
        registerWoodPallet(
            GenerationsWood.GHOST_LOG,
            GenerationsWood.STRIPPED_GHOST_LOG,
            GenerationsWood.GHOST_PLANKS,
            GenerationsWood.GHOST_SLAB,
            GenerationsWood.GHOST_STAIRS,
            GenerationsWood.GHOST_BUTTON,
            GenerationsWood.GHOST_PRESSURE_PLATE,
            GenerationsWood.GHOST_DOOR,
            GenerationsWood.GHOST_TRAPDOOR,
            GenerationsWood.GHOST_WOOD,
            GenerationsWood.STRIPPED_GHOST_WOOD,
            GenerationsWood.GHOST_FENCE,
            GenerationsWood.GHOST_FENCE_GATE,
            GenerationsWood.GHOST_SIGN,
            GenerationsWood.GHOST_WALL_SIGN,
            GenerationsWood.GHOST_CRAFTING_TABLE,
            GenerationsWood.GHOST_HANGING_SIGN,
            GenerationsWood.GHOST_WALL_HANGING_SIGN,
            GenerationsWood.GHOST_BOOKSHELF
        )

        //House Floors
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_1)
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_2)
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_3)
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_4)
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_5)
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_6)
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_7)
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_8)

        registerBlockItem(GenerationsBlocks.ULTRITE_BLOCK)

        //ChargeStone
        registerBlockItem(GenerationsBlocks.CHARGE_DRIPSTONE_BLOCK)

        //Volcanic
        registerBlockItem(GenerationsBlocks.VOLCANIC_FIRESTONE)

        //Golden Temple
        registerSandStonePallet(
            GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE,
            GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_SLAB,
            GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_STAIRS,
            GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_WALL,
            GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE,
            GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_SLAB,
            GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_STAIRS,
            GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_WALL,
            GenerationsBlocks.GOLDEN_TEMPLE_CHISELED_SANDSTONE,
            GenerationsBlocks.GOLDEN_TEMPLE_CUT_SANDSTONE,
            GenerationsBlocks.GOLDEN_TEMPLE_CUT_SANDSTONE_SLAB
        )

        registerPallet(
            GenerationsBlocks.GOLDEN_TEMPLE_DARK_PRISMARINE,
            GenerationsBlocks.GOLDEN_TEMPLE_DARK_PRISMARINE_SLAB,
            GenerationsBlocks.GOLDEN_TEMPLE_DARK_PRISMARINE_STAIRS,
            GenerationsBlocks.GOLDEN_TEMPLE_DARK_PRISMARINE_WALL,
            null,
            null,
            true
        )
        registerPallet(
            GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE,
            GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_SLAB,
            GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_STAIRS,
            GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_WALL,
            null,
            null,
            true
        )
        registerPallet(
            GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_BRICKS,
            GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_BRICK_SLAB,
            GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_BRICK_STAIRS,
            GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_BRICK_WALL,
            null,
            null,
            true
        )

        //Subway
        registerPallet(
            GenerationsBlocks.SUBWAY_WALL,
            GenerationsBlocks.SUBWAY_WALL_SLAB,
            GenerationsBlocks.SUBWAY_WALL_STAIRS,
            null,
            null,
            null,
            true
        )
        registerPallet(
            GenerationsBlocks.SUBWAY_WALL_2,
            GenerationsBlocks.SUBWAY_WALL_2_SLAB,
            GenerationsBlocks.SUBWAY_WALL_2_STAIRS,
            null,
            null,
            null,
            true
        )

        registerBlockItem(GenerationsBlocks.POKEMART_SIGN)
        registerBlockItem(GenerationsBlocks.POKECENTER_SIGN)

        registerBlockItem(GenerationsBlocks.RUINS_WALL)
        registerBlockItem(GenerationsBlocks.DUSTY_RUINS_WALL)

        registerPillar(GenerationsBlocks.CASTLE_PILLAR)
        registerPillar(GenerationsBlocks.BROKEN_CASTLE_PILLAR)

        registerPillar(GenerationsBlocks.PRISMARINE_PILLAR)
        registerPillar(GenerationsBlocks.BROKEN_PRISMARINE_PILLAR)

        registerPillar(GenerationsBlocks.DARK_PRISMARINE_PILLAR)
        registerPillar(GenerationsBlocks.BROKEN_DARK_PRISMARINE_PILLAR)

        registerPillar(GenerationsBlocks.ICE_PILLAR)
        registerPillar(GenerationsBlocks.BROKEN_ICE_PILLAR)

        registerPillar(GenerationsBlocks.HAUNTED_PILLAR)
        registerPillar(GenerationsBlocks.BROKEN_HAUNTED_PILLAR)

        registerBlockItem(GenerationsBlocks.DAWN_STONE_BLOCK)
        registerBlockItem(GenerationsBlocks.DUSK_STONE_BLOCK)
        registerBlockItem(GenerationsBlocks.FIRE_STONE_BLOCK)
        registerBlockItem(GenerationsBlocks.ICE_STONE_BLOCK)
        registerBlockItem(GenerationsBlocks.LEAF_STONE_BLOCK)
        registerBlockItem(GenerationsBlocks.MOON_STONE_BLOCK)
        registerBlockItem(GenerationsBlocks.SHINY_STONE_BLOCK)
        registerBlockItem(GenerationsBlocks.SUN_STONE_BLOCK)
        registerBlockItem(GenerationsBlocks.THUNDER_STONE_BLOCK)
        registerBlockItem(GenerationsBlocks.WATER_STONE_BLOCK)

        unownBlock(GenerationsBlocks.UNOWN_BLOCK_A)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_B)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_C)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_D)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_E)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_F)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_G)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_H)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_I)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_J)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_K)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_L)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_M)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_N)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_O)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_P)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_Q)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_R)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_S)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_T)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_U)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_V)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_W)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_X)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_Y)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_Z)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_EXCLAMATION_MARK)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_QUESTION_MARK)
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_BLANK)

        registerBlockItem(GenerationsBlocks.ULTRA_SAND)

        registerBlockItem(GenerationsBlocks.CRATE)

        registerBlockItem(GenerationsBlocks.GOLDEN_TEMPLE_SAND)

        registerPallet(
            GenerationsBlocks.RUBY_BLOCK,
            GenerationsBlocks.RUBY_SLAB,
            GenerationsBlocks.RUBY_STAIRS,
            GenerationsBlocks.RUBY_WALL,
            null,
            null,
            true
        )
        registerPallet(
            GenerationsBlocks.SAPPHIRE_BLOCK,
            GenerationsBlocks.SAPPHIRE_SLAB,
            GenerationsBlocks.SAPPHIRE_STAIRS,
            GenerationsBlocks.SAPPHIRE_WALL,
            null,
            null,
            true
        )
        registerPallet(
            GenerationsBlocks.CRYSTAL_BLOCK,
            GenerationsBlocks.CRYSTAL_SLAB,
            GenerationsBlocks.CRYSTAL_STAIRS,
            GenerationsBlocks.CRYSTAL_WALL,
            null,
            null,
            true
        )
        registerBlockItem(GenerationsBlocks.CRYSTAL_LIGHT)
        registerBlockItem(GenerationsBlocks.SILICON_BLOCK)
        registerBlockItem(GenerationsBlocks.Z_BLOCK)

        registerMushroom(GenerationsBlocks.BALLONLEA_BLUE_MUSHROOM)
        registerMushroom(GenerationsBlocks.BALLONLEA_GREEN_MUSHROOM)
        registerMushroom(GenerationsBlocks.BALLONLEA_PINK_MUSHROOM)
        registerMushroom(GenerationsBlocks.BALLONLEA_YELLOW_MUSHROOM)

        registerMushroom(GenerationsBlocks.GROUP_BALLONLEA_BLUE_MUSHROOM)
        registerMushroom(GenerationsBlocks.GROUP_BALLONLEA_GREEN_MUSHROOM)
        registerMushroom(GenerationsBlocks.GROUP_BALLONLEA_PINK_MUSHROOM)
        registerMushroom(GenerationsBlocks.GROUP_BALLONLEA_YELLOW_MUSHROOM)

        registerMushroom(GenerationsBlocks.TALL_BALLONLEA_BLUE_MUSHROOM)
        registerMushroom(GenerationsBlocks.TALL_BALLONLEA_GREEN_MUSHROOM)
        registerMushroom(GenerationsBlocks.TALL_BALLONLEA_PINK_MUSHROOM)
        registerMushroom(GenerationsBlocks.TALL_BALLONLEA_YELLOW_MUSHROOM)

        registerMushroom(GenerationsBlocks.DOUBLE_BALLONLEA_BLUE_MUSHROOM)
        registerMushroom(GenerationsBlocks.DOUBLE_BALLONLEA_GREEN_MUSHROOM)
        registerMushroom(GenerationsBlocks.DOUBLE_BALLONLEA_PINK_MUSHROOM)
        registerMushroom(GenerationsBlocks.DOUBLE_BALLONLEA_YELLOW_MUSHROOM)


        registerFlabebeFlower(GenerationsBlocks.FLABEBE_FLOWER_AZ)
        registerFlabebeFlower(GenerationsBlocks.FLABEBE_FLOWER_BLUE)
        registerFlabebeFlower(GenerationsBlocks.FLABEBE_FLOWER_ORANGE)
        registerFlabebeFlower(GenerationsBlocks.FLABEBE_FLOWER_RED)
        registerFlabebeFlower(GenerationsBlocks.FLABEBE_FLOWER_WHITE)
        registerFlabebeFlower(GenerationsBlocks.FLABEBE_FLOWER_YELLOW)

        //Ultra Space
        registerSandStonePallet(
            GenerationsBlocks.ULTRA_SANDSTONE,
            GenerationsBlocks.ULTRA_SANDSTONE_SLAB,
            GenerationsBlocks.ULTRA_SANDSTONE_STAIRS,
            GenerationsBlocks.ULTRA_SANDSTONE_WALL,
            GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE,
            GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_SLAB,
            GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_STAIRS,
            GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_WALL,
            GenerationsBlocks.ULTRA_CHISELED_SANDSTONE,
            GenerationsBlocks.ULTRA_CUT_SANDSTONE,
            GenerationsBlocks.ULTRA_CUT_SANDSTONE_SLAB
        )
        registerBlockItem(GenerationsBlocks.GHOST_LANTERN)
        registerBlockItem(GenerationsBlocks.MACHINE_BLOCK)
        registerBlockItem(GenerationsBlocks.RUINS_SAND)
        registerBlockItem(GenerationsBlocks.BURST_TURF)

        registerBlockItem(GenerationsBlocks.WARNING_BLOCK)

        registerPumpkin(GenerationsBlocks.CURSED_PUMPKIN)
        registerCarvedPumpkin(GenerationsBlocks.CURSED_CARVED_PUMPKIN, GenerationsBlocks.CURSED_PUMPKIN)
        registerCarvedPumpkin(GenerationsBlocks.CURSED_JACK_O_LANTERN, GenerationsBlocks.CURSED_PUMPKIN)

        registerDoor(GenerationsBlocks.POKECENTER_DOOR.get())


        pokeBallChests(GenerationsBlocks.POKEBALL_CHEST, "poke_ball")
        pokeBallChests(GenerationsBlocks.GREATBALL_CHEST, "great_ball")
        pokeBallChests(GenerationsBlocks.ULTRABALL_CHEST, "ultra_ball")
        pokeBallChests(GenerationsBlocks.MASTERBALL_CHEST, "master_ball")

        //Furnaces
        registerFurnace(GenerationsUtilityBlocks.CHARGE_STONE_FURNACE)
        registerFurnace(GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE)
        registerFurnace(GenerationsUtilityBlocks.CHARGE_STONE_SMOKER)
        registerFurnace(GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE)
        registerFurnace(GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE)
        registerFurnace(GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER)

        registerBlockItem(GenerationsBlocks.RICH_SOIL_1)
        registerBlockItem(GenerationsBlocks.RICH_SOIL_2)
        registerBlockItem(GenerationsBlocks.RICH_SOIL_3)
        registerBlockItem(GenerationsBlocks.RICH_SOIL_4)

        registerLog(GenerationsBlocks.GHOST_PILLAR)

        GenerationsPokeDolls.POKEDOLLS.forEach(Consumer { block: RegistrySupplier<Block> -> this.registerPokeDoll(block) })

        //        registerNoModel(GenerationsDecorationBlocks.VENDING_MACHINE.block());
//        registerNoModel(GenerationsDecorationBlocks.PASTEL_BEAN_BAG.block());
        GenerationsDecorationBlocks.BALL_DISPLAY_BLOCKS.forEach(Consumer { block: RegistrySupplier<BallDisplayBlock> ->
            registerBlockItemParticle(
                block.get(),
                "ball_displays",
                true
            )
        })


        registerBlockItemParticleWithDrop(GenerationsBlocks.POKECENTER_SCARLET_SIGN.get(), "sign")


        GenerationsShrines.SHRINES.forEach(Consumer { block: RegistrySupplier<Block> ->
            val block1 = block.get()
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
        GenerationsUtilityBlocks.BALL_LOOTS.forEach(Consumer { block: RegistrySupplier<BallLootBlock> ->
            registerBlockItemParticle(
                block.get(),
                "ball_loots",
                true
            )
        })
        registerBlockItemParticleWithDrop(GenerationsUtilityBlocks.TRASH_CAN.get(), "utility_blocks")
        registerBlockItemParticleWithDrop(GenerationsUtilityBlocks.COOKING_POT.get(), "utility_blocks")
        registerBlockItemParticleWithDrop(GenerationsUtilityBlocks.ROTOM_PC.get(), "utility_blocks")
        registerBlockItemParticleWithDrop(GenerationsUtilityBlocks.TABLE_PC.get(), "utility_blocks")

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
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.HOUSE_LAMP.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.BENCH.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.GREATBALL_CUSHION.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.POKEBALL_CUSHION.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.FOONGUS_CUSHION.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.MASTERBALL_CUSHION.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.COUCH.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.BUSH.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.HDTV.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.DESK.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.FRIDGE.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.WORK_DESK.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SHELF.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.BOOK_SHELF.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.POKEBALL_PILLAR.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SHOP_DISPLAY_CASE_1.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SHOP_DISPLAY_CASE_2.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SHOP_DISPLAY_SMALL_1.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SHOP_DISPLAY_SMALL_2.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SHOP_DISPLAY_LARGE_SHELF_1.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SHOP_DISPLAY_LARGE_SHELF_2.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SWITCH.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.LITWICK_CANDLE.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.LITWICK_CANDLES.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.DOUBLE_STREET_LAMP.get(), "decorations")
        registerBlockItemParticleWithDrop(GenerationsDecorationBlocks.SNORLAX_BEAN_BAG.get(), "bean_bags")
        registerBlockItemParticleWithDrop(GenerationsUtilityBlocks.RKS_MACHINE.get(), "utility_blocks")
        registerBlockItemParticleWithDrop(GenerationsUtilityBlocks.SCARECROW.get(), "utility_blocks")

        registerBlockItem(GenerationsBlocks.LIGHTNING_LANTERN)

        registerInfestedBlock(GenerationsBlocks.INFESTED_CHARGE_STONE)
        registerInfestedBlock(GenerationsBlocks.INFESTED_VOLCANIC_STONE)
        registerInfestedBlock(GenerationsBlocks.INFESTED_CHARGE_COBBLESTONE)
        registerInfestedBlock(GenerationsBlocks.INFESTED_VOLCANIC_COBBLESTONE)
        registerInfestedBlock(GenerationsBlocks.INFESTED_CHARGE_STONE_BRICKS)
        registerInfestedBlock(GenerationsBlocks.INFESTED_VOLCANIC_STONE_BRICKS)
        registerInfestedBlock(GenerationsBlocks.INFESTED_MOSSY_CHARGE_STONE_BRICKS)
        registerInfestedBlock(GenerationsBlocks.INFESTED_MOSSY_VOLCANIC_STONE_BRICKS)
        registerInfestedBlock(GenerationsBlocks.INFESTED_CRACKED_CHARGE_STONE_BRICKS)
        registerInfestedBlock(GenerationsBlocks.INFESTED_CRACKED_VOLCANIC_STONE_BRICKS)
        registerInfestedBlock(GenerationsBlocks.INFESTED_CHISELED_CHARGE_STONE_BRICKS)
        registerInfestedBlock(GenerationsBlocks.INFESTED_CHISELED_VOLCANIC_STONE_BRICKS)

        registerDripStone(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE)

        registerCarpetLike(
            GenerationsUtilityBlocks.BLACK_ELEVATOR, GenerationsUtilityBlocks.BLUE_ELEVATOR,
            GenerationsUtilityBlocks.BROWN_ELEVATOR, GenerationsUtilityBlocks.CYAN_ELEVATOR,
            GenerationsUtilityBlocks.GRAY_ELEVATOR, GenerationsUtilityBlocks.GREEN_ELEVATOR,
            GenerationsUtilityBlocks.LIGHT_BLUE_ELEVATOR, GenerationsUtilityBlocks.LIGHT_GRAY_ELEVATOR,
            GenerationsUtilityBlocks.LIME_ELEVATOR, GenerationsUtilityBlocks.MAGENTA_ELEVATOR,
            GenerationsUtilityBlocks.ORANGE_ELEVATOR, GenerationsUtilityBlocks.PINK_ELEVATOR,
            GenerationsUtilityBlocks.PURPLE_ELEVATOR, GenerationsUtilityBlocks.RED_ELEVATOR,
            GenerationsUtilityBlocks.WHITE_ELEVATOR, GenerationsUtilityBlocks.YELLOW_ELEVATOR
        )

        registerBox()
    }

    private fun registerBox() {
        val closed = UncheckedModelFile("generations_core:block/utility_blocks/box_closed")
        val open = UncheckedModelFile("generations_core:block/utility_blocks/box_open")

        val box = GenerationsUtilityBlocks.BOX

        getVariantBuilder(box.get())
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

        itemModels().getBuilder(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(box.get().asItem())).toString())
            .parent(UncheckedModelFile("item/generated"))
            .texture("layer0", key(box.get()).withPrefix("item/blocks/utility_blocks/"))

        dropSelfList.add(box.get())
    }

    private fun <V : DyeableBlock<T, V>, T : ModelProvidingBlockEntity?> registerDyeGroup(
        group: DyedGroup<V, T>,
        dir: String
    ) {
        group.block.values.stream().map { obj: RegistrySupplier<V> -> obj.get() }.forEach { block: V ->
            registerBlockItemParticle(block, dir, true)
            dropSelfList.add(block as Block)
        }
    }

    private fun <T : Block?> registerNoModel(block: RegistrySupplier<T>) {
        this.simpleBlock(block.get(), *ConfiguredModel.builder().modelFile(models().getBuilder(block.id.path)).build())
    }

    private fun registerPallet(
        block: RegistrySupplier<Block>,
        slab: RegistrySupplier<SlabBlock>?,
        stairs: RegistrySupplier<StairBlock>?,
        wall: RegistrySupplier<WallBlock>?,
        button: RegistrySupplier<ButtonBlock>?,
        pressurePlate: RegistrySupplier<PressurePlateBlock>?,
        dropSelf: Boolean
    ) {
        if (!registered(block.get())) registerBlockItem(block)
        if (dropSelf) dropSelfList.add(block.get())

        if (slab != null) {
            if (!registered(slab.get())) registerSlab(slab.get(), block.get())
            if (dropSelf) dropSelfList.add(slab.get())
        }
        if (stairs != null) {
            if (!registered(stairs.get())) registerStairs(stairs.get(), block.get())
            if (dropSelf) dropSelfList.add(stairs.get())
        }
        if (wall != null) {
            if (!registered(wall.get())) registerWall(wall.get(), block.get())
            if (dropSelf) dropSelfList.add(wall.get())
        }
        if (button != null) {
            if (!registered(button.get())) registerButton(button.get(), block.get())
            if (dropSelf) dropSelfList.add(button.get())
        }
        if (pressurePlate != null) {
            if (!registered(pressurePlate.get())) registerPressurePlate(pressurePlate.get(), block.get())
            if (dropSelf) dropSelfList.add(pressurePlate.get())
        }
    }


    private fun registerSandStonePallet(
        sandstone: RegistrySupplier<Block>,
        slab: RegistrySupplier<SlabBlock>?,
        stairs: RegistrySupplier<StairBlock>?,
        wall: RegistrySupplier<WallBlock>?,
        smooth: RegistrySupplier<Block>?,
        smoothslab: RegistrySupplier<SlabBlock>?,
        smoothstair: RegistrySupplier<StairBlock>?,
        smoothWall: RegistrySupplier<WallBlock>?,
        chiseled: RegistrySupplier<Block>,
        cutSandstone: RegistrySupplier<Block>?,
        cutSlab: RegistrySupplier<SlabBlock>?
    ) {
        if (!registered(sandstone.get())) registerSandStone(sandstone)
        dropSelfList.add(sandstone.get())
        if (slab != null) {
            if (!registered(slab.get())) registerSandStoneSlab(slab, sandstone)
            dropSelfList.add(slab.get())
        }
        if (stairs != null) {
            if (!registered(stairs.get())) registerSandStoneStairs(stairs, sandstone)
            dropSelfList.add(stairs.get())
        }
        if (wall != null) {
            if (!registered(wall.get())) registerWall(wall.get(), sandstone.get())
            dropSelfList.add(wall.get())
        }
        val top = id("block/" + sandstone.id.path + "_top")
        if (smooth != null) {
            val smoothsandstoneBlock =
                models().cubeAll(smooth.id.path, id("block/" + sandstone.id.path + "_top"))
            if (!registered(smooth.get())) simpleBlockWithItem(smooth.get(), smoothsandstoneBlock)

            dropSelfList.add(smooth.get())
            if (smoothslab != null) {
                if (!registered(smoothslab.get())) {
                    slabBlock(smoothslab.get(), id("block/" + smooth.id.path), top, top, top)
                    simpleBlockItem(smoothslab.get(), models().slab(smoothslab.id.path, top, top, top))
                }
                dropSelfList.add(smoothslab.get())
            }
            if (smoothstair != null) {
                if (!registered(smoothstair.get())) {
                    stairsBlock(smoothstair.get(), top)
                    simpleBlockItem(smoothstair.get(), models().stairs(smoothstair.id.path, top, top, top))
                }
                dropSelfList.add(smoothstair.get())
            }
            if (smoothWall != null) {
                if (!registered(smoothWall.get())) {
                    wallBlock(smoothWall.get(), top)
                    simpleBlockItem(smoothWall.get(), models().wallInventory(smoothWall.id.path, top))
                }
                dropSelfList.add(smoothWall.get())
            }

            if (!registered(chiseled.get())) cubeColumn(chiseled, top)
            if (cutSandstone != null) {
                if (!registered(cutSandstone.get())) cubeColumn(cutSandstone, top)
                if (cutSlab != null) {
                    if (!registered(cutSlab.get())) {
                        slabBlock(
                            cutSlab.get(),
                            id("block/" + cutSandstone.id.path),
                            top,
                            id("block/" + cutSandstone.id.path),
                            top
                        )
                        simpleBlockItem(
                            cutSlab.get(),
                            models().slab(
                                cutSlab.id.path,
                                id("block/" + cutSandstone.id.path),
                                top,
                                top
                            )
                        )
                    }
                    dropSelfList.add(cutSlab.get())
                }
            }
        }
    }

    private fun cubeColumn(cutSandstone: RegistrySupplier<Block>?, top: ResourceLocation) {
        if (cutSandstone != null) {
            val cutBlock =
                models().cubeColumn(cutSandstone.id.path, id("block/" + cutSandstone.id.path), top)
            simpleBlockWithItem(cutSandstone.get(), cutBlock)
            dropSelfList.add(cutSandstone.get())
        }
    }

    private fun registerStairs(stairs: StairBlock?, texturedBlock: Block) {
        val texture = ModelLocationUtils.getModelLocation(texturedBlock)
        stairsBlock(stairs, texture)
        simpleBlockItem(stairs, itemModels().stairs("block/" + key(stairs).path, texture, texture, texture))
    }

    private fun registerSlab(slab: SlabBlock?, texturedBlock: Block) {
        val texture = ModelLocationUtils.getModelLocation(texturedBlock)
        slabBlock(slab, texture, texture)
        simpleBlockItem(slab, itemModels().slab("block/" + key(slab).path, texture, texture, texture))
    }

    private fun registerWall(wall: WallBlock?, texturedBlock: Block) {
        val texture = ModelLocationUtils.getModelLocation(texturedBlock)
        wallBlock(wall, texture)
        simpleBlockItem(wall, itemModels().wallInventory("block/" + key(wall).path, texture))
    }

    private fun registerFence(fence: FenceBlock, block: Block?) {
        val texture = if (block == null) ModelLocationUtils.getModelLocation(fence)
        else ModelLocationUtils.getModelLocation(block)

        fenceBlock(fence, texture)
        simpleBlockItem(fence, itemModels().fenceInventory("block/" + key(fence).path, texture))
    }

    private fun registerGate(gate: FenceGateBlock?, fence: FenceBlock?, block: Block?) {
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

    private fun registerDoor(door: DoorBlock?) {
        val blockId = key(door)
        doorBlockWithRenderType(
            door,
            ResourceLocation(blockId.namespace, "block/door/" + blockId.path + "_bottom"),
            ResourceLocation(blockId.namespace, "block/door/" + blockId.path + "_top"),
            "cutout"
        )
    }

    private fun registerPressurePlate(pressurePlate: PressurePlateBlock?, texturedBlock: Block) {
        val texture = ModelLocationUtils.getModelLocation(texturedBlock)
        pressurePlateBlock(pressurePlate, texture)
        itemModels().pressurePlate(key(pressurePlate).path, texture)
    }

    private fun registerButton(button: Block?, texturedBlock: Block) {
        val buttonId = key(button)
        val textureBlockId = key(texturedBlock)

        val texture = ResourceLocation(textureBlockId.namespace, "block/" + textureBlockId.path)
        buttonBlock(button as ButtonBlock?, texture)
        itemModels().buttonInventory(buttonId.path, texture)
    }

    private fun registerTrapDoor(trapDoor: TrapDoorBlock?, texturedBlock: Block) {
        val texture = ModelLocationUtils.getModelLocation(texturedBlock)
        val trapDoorId = key(trapDoor)
        trapdoorBlockWithRenderType(trapDoor, texture, true, "cutout")
        itemModels().trapdoorBottom(trapDoorId.path, texture)
    }

    private fun registerTrapDoor(trapDoor: RegistrySupplier<TrapDoorBlock>) {
        registerTrapDoor(trapDoor.get(), trapDoor.get())
    }

    private fun registerSign(sign: StandingSignBlock?, wallsign: WallSignBlock, plank: Block) {
        signBlock(sign, wallsign, blockTexture(plank))
    }

    private fun registerHangingSign(sign: CeilingHangingSignBlock, wallsign: WallHangingSignBlock, plank: Block) {
        val thing: ModelFile = models().sign(key(sign).path, blockTexture(plank))
        simpleBlock(sign, thing)
        simpleBlock(wallsign, thing)
    }

    private fun registerMushroom(crossBlock: RegistrySupplier<GenerationsMushroomBlock>) {
        getVariantBuilder(crossBlock.get()).forAllStates { state: BlockState? ->
            ConfiguredModel.builder()
                .modelFile(models().cross(crossBlock.id.path, blockTexture(crossBlock.get())).renderType("cutout"))
                .build()
        }

        MUSHROOM_BLOCKS.add(crossBlock.get())
    }

    private fun registerFlabebeFlower(crossBlock: RegistrySupplier<FlabebeFlowerBlock>) {
        val name = key(crossBlock.get())
        val location = ResourceLocation(name.namespace, ModelProvider.BLOCK_FOLDER + "/flowers/" + name.path)

        getVariantBuilder(crossBlock.get()).forAllStates { state: BlockState? ->
            ConfiguredModel.builder()
                .modelFile(models().cross(crossBlock.id.path, location).renderType("cutout"))
                .build()
        }

        dropSelfList.add(crossBlock.get())
    }

    private fun registerLog(log: RegistrySupplier<RotatedPillarBlock>) {
        val name = log.id.path
        val logBlock = models().withExistingParent(log.id.toString(), mcLoc("block/oak_log"))
            .texture("side", id("block/$name"))
            .texture("top", id("block/" + name + "_top"))
        logBlock(log.get())
        simpleBlockItem(log.get(), logBlock)
    }

    private fun registerBlockItem(block: RegistrySupplier<Block>) {
        registerBlockItem(block.get())
        dropSelfList.add(block.get())
    }

    private fun registerBlockItem(block: Block) {
        simpleBlockWithItem(block, cubeAll(block))
        dropSelfList.add(block)
    }

    private fun registerPillar(block: RegistrySupplier<out Block>) {
        val texture = id("block/${block.id.path}")
        val item = id("item/blocks/pillars/" + ForgeRegistries.BLOCKS.getKey(block.get())!!.path)

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

        itemModels().getBuilder(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(block.get().asItem())).toString())
            .parent(UncheckedModelFile("item/generated"))
            .texture("layer0", item)

        dropSelfList.add(block.get())
    }

    private fun VariantBlockStateBuilder.partialState(block: VariantBlockStateBuilder.PartialBlockstate.() -> Unit): VariantBlockStateBuilder.PartialBlockstate? {
        val partialState = partialState()
        block.invoke(partialState)
        return partialState
    }

    private fun <T: Block> variantBuilder(block: RegistrySupplier<T>, function: VariantBlockStateBuilder.() -> Unit) {
        variantBuilder(block.get(), function)
    }

    private fun variantBuilder(block: Block, function: VariantBlockStateBuilder.() -> Unit) {
        var builder = getVariantBuilder(block)
        builder.configure(function)

    }

    private fun VariantBlockStateBuilder.configure(function: VariantBlockStateBuilder.() -> Unit) {
        function.invoke(this)
    }


    private fun unownBlock(block: RegistrySupplier<out Block>) {
        val model = models().cubeBottomTop(
            block.id.path,
            block.id.withPrefix("block/"),
            id("block/unown_block_bottom"),
            id("block/unown_block_top")
        )

        simpleBlockWithItem(block.get(), model)
        dropSelfList.add(block.get())
    }

    private fun registerBlockItemParticleWithDrop(block: Block, name: String) {
        registerBlockItemParticle(block, name, true)
        dropSelfList.add(block)
    }

    private fun <T: Block> registerBlockItemParticle(block: T, name: String, shouldGenerateItems: Boolean) {
        val blockId = key(block)
        val textureId = blockId.withPrefix("item/blocks/$name/")
        try {
            simpleBlock(block, models().sign(blockId.path, textureId))
            if (shouldGenerateItems) itemModels().getBuilder(
                Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(block.asItem())).toString()
            ).parent(UncheckedModelFile("item/generated"))
                .texture("layer0", textureId)
        } catch (ignored: Exception) {
            DataProvider.LOGGER.error("Block: $name -> $textureId")
            simpleBlock(block, models().sign(blockId.path, id("item/placeholder")))
            if (shouldGenerateItems) itemModels().getBuilder(
                Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(block.asItem())).toString()
            ).parent(UncheckedModelFile("item/generated"))
                .texture("layer0", id("item/placeholder"))
        }
    }

    private fun registerPokeDoll(block: RegistrySupplier<Block>) {
        val blockId = checkNotNull(ForgeRegistries.BLOCKS.getKey(block.get()))
        val textureId = ResourceLocation(blockId.namespace, "item/dolls/" + blockId.path.replace("shiny_", "shiny/"))

        simpleBlock(
            block.get(),
            models().sign(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get()))!!.path, textureId)
        )

        val key = Objects.requireNonNull(
            ForgeRegistries.BLOCKS.getKey(block.get()),
            "Tried to create json model for unregistered Item."
        )!!
        var texPath = id("item/dolls/" + key.path)
        if (key.path.startsWith("shiny_")) texPath =
            id("item/dolls/shiny/" + key.path.replace("shiny_", ""))


        itemModels().getBuilder(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(block.get().asItem())).toString())
            .parent(UncheckedModelFile("item/generated"))
            .texture("layer0", texPath)
    }

    private fun registerSandStone(block: RegistrySupplier<Block>) {
        val name = block.id.path
        simpleBlockWithItem(
            block.get(),
            models().cubeBottomTop(
                name,
                id("block/$name"),
                id("block/" + name + "_bottom"),
                id("block/" + name + "_top")
            )
        )
    }

    private fun registerSandStoneStairs(stairs: RegistrySupplier<StairBlock>, block: RegistrySupplier<Block>) {
        stairsBlock(
            stairs.get(),
            id("block/" + block.id.path),
            id("block/" + block.id.path + "_bottom"),
            id("block/" + block.id.path + "_top")
        )
        simpleBlockItem(
            stairs.get(),
            itemModels().stairs(
                "block/" + stairs.id.path,
                id("block/" + block.id.path),
                id("block/" + block.id.path + "_bottom"),
                id("block/" + block.id.path + "_top")
            )
        )
    }

    private fun registerSandStoneSlab(slab: RegistrySupplier<SlabBlock>, block: RegistrySupplier<Block>) {
        slabBlock(
            slab.get(),
            id("block/" + block.id.path),
            id("block/" + block.id.path),
            id("block/" + block.id.path + "_bottom"),
            id("block/" + block.id.path + "_top")
        )
        simpleBlockItem(
            slab.get(),
            itemModels().slab(
                "block/" + slab.id.path,
                id("block/" + block.id.path),
                id("block/" + block.id.path + "_bottom"),
                id("block/" + block.id.path + "_top")
            )
        )
    }

    private fun registerGlassBlock(block: RegistrySupplier<GlassBlock>) {
        getVariantBuilder(block.get()).forAllStates { state: BlockState? ->
            ConfiguredModel.builder()
                .modelFile(
                    models().cubeAll(block.id.path, modLoc("block/" + block.id.path)).renderType("cutout_mipped")
                )
                .build()
        }
        simpleBlockItem(block.get(), cubeAll(block.get()))
    }

    @SafeVarargs
    private fun registerCarpetLike(vararg block: RegistrySupplier<Block>) {
        for (carpet in block) {
            simpleBlockWithItem(carpet.get(), models().carpet(carpet.id.path, modLoc("block/" + carpet.id.path)))
            dropSelfList.add(carpet.get())
        }
    }

    private fun registerOreBlocks() {
        GenerationsOres.ORES.forEach(Consumer { ore: RegistrySupplier<Block?> ->
            val name = ore.id.path
            simpleBlockWithItem(
                ore.get(),
                models().singleTexture(name, mcLoc("block/cube_all"), "all", id("block/ores/$name"))
            )
        })
    }

    private fun pokeBallChests(chest: RegistrySupplier<GenericChestBlock>, ballName: String) {
        simpleBlock(
            chest.get(),
            models().getBuilder(chest.id.path).texture("particle", id("item/pokeballs/$ballName"))
        )
    }


    private fun registerPumpkin(pumpkin: RegistrySupplier<PumpkinBlock>) {
        val pumpkinModel = models().withExistingParent(pumpkin.id.toString(), mcLoc("block/pumpkin"))
            .texture("side", id("block/" + pumpkin.id.path + "_side"))
            .texture("end", id("block/" + pumpkin.id.path + "_end"))
        simpleBlockWithItem(pumpkin.get(), pumpkinModel)
    }


    private fun registerFurnace(furnace: RegistrySupplier<out Block>) {
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
            furnace.get()
        ) { state: BlockState -> if (state.getValue(FurnaceBlock.LIT)) on else off }
        simpleBlockItem(furnace.get(), off)
    }

    private fun registerCarvedPumpkin(
        carvedPumpkin: RegistrySupplier<out Block>,
        pumpkin: RegistrySupplier<PumpkinBlock>
    ) {
        val pumpkinModel = models().withExistingParent(carvedPumpkin.id.toString(), mcLoc("block/carved_pumpkin"))
            .texture("side", id("block/" + pumpkin.id.path + "_side"))
            .texture("top", id("block/" + pumpkin.id.path + "_end"))
            .texture("front", id("block/" + carvedPumpkin.id.path))
        horizontalBlock(carvedPumpkin.get(), pumpkinModel)
        simpleBlockItem(carvedPumpkin.get(), pumpkinModel)
    }

    private fun registerCraftingTable(
        table: RegistrySupplier<GenerationsCraftingTableBlock>,
        plank: RegistrySupplier<Block>
    ) {
        val tableModel = models().withExistingParent(table.id.toString(), mcLoc("block/cube"))
            .texture("down", id("block/" + plank.id.path))
            .texture("east", id("block/crafting_table/" + table.id.path + "_side"))
            .texture("north", id("block/crafting_table/" + table.id.path + "_front"))
            .texture("particle", id("block/crafting_table/" + table.id.path + "_front"))
            .texture("south", id("block/crafting_table/" + table.id.path + "_side"))
            .texture("up", id("block/crafting_table/" + table.id.path + "_top"))
            .texture("west", id("block/crafting_table/" + table.id.path + "_front"))
        simpleBlockWithItem(table.get(), tableModel)
    }

    private fun registerBookShelf(bookshelf: RegistrySupplier<Block>, plank: RegistrySupplier<Block>) {
        val bookshelfModel = models().withExistingParent(bookshelf.id.toString(), mcLoc("block/cube_column"))
            .texture("side", id("block/" + bookshelf.id.path))
            .texture("end", id("block/" + plank.id.path))
        simpleBlockWithItem(bookshelf.get(), bookshelfModel)
    }

    private fun registerInfestedBlock(block: RegistrySupplier<InfestedBlock>) {
        models().withExistingParent(
            block.id.toString().replace("infested_", "") + "_mirrored",
            mcLoc("block/cube_mirrored_all")
        )
            .texture("all", blockTexture(block.get().hostBlock))
        val normal = UncheckedModelFile(ModelLocationUtils.getModelLocation(block.get().hostBlock))
        val mirrored = UncheckedModelFile(blockTexture(block.get().hostBlock).toString() + "_mirrored")

        getVariantBuilder(block.get()).partialState()
            .addModels(
                *ConfiguredModel.builder().modelFile(normal)
                    .nextModel().modelFile(mirrored)
                    .nextModel().modelFile(normal).rotationY(180)
                    .nextModel().modelFile(mirrored).rotationY(180)
                    .build()
            )
        itemModels().withExistingParent(block.id.path, blockTexture(block.get().hostBlock))
    }

    private fun registerDripStone(dripstone: RegistrySupplier<PointedChargeDripstoneBlock>) {
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
        val variantTarget = family.variants[variant]
        when (variant) {
            BlockFamily.Variant.BUTTON -> registerButton(variantTarget, original)
            BlockFamily.Variant.CHISELED, BlockFamily.Variant.CRACKED, BlockFamily.Variant.CUT -> simpleBlockWithItem(
                variantTarget,
                cubeAll(variantTarget)
            )

            BlockFamily.Variant.DOOR -> registerDoor(variantTarget as DoorBlock?)
            BlockFamily.Variant.FENCE -> registerFence((variantTarget as FenceBlock?)!!, original)
            BlockFamily.Variant.FENCE_GATE -> registerGate(
                variantTarget as FenceGateBlock?,
                family[BlockFamily.Variant.FENCE] as FenceBlock,
                original
            )

            BlockFamily.Variant.SIGN -> registerSign(
                variantTarget as StandingSignBlock,
                family[BlockFamily.Variant.WALL_SIGN] as WallSignBlock,
                original
            )

            BlockFamily.Variant.SLAB -> registerSlab(variantTarget as SlabBlock?, original)
            BlockFamily.Variant.STAIRS -> registerStairs(variantTarget as StairBlock?, original)
            BlockFamily.Variant.PRESSURE_PLATE -> registerPressurePlate(variantTarget as PressurePlateBlock?, original)
            BlockFamily.Variant.TRAPDOOR -> registerTrapDoor(variantTarget as TrapDoorBlock?, original)
            BlockFamily.Variant.WALL -> registerWall(variantTarget as WallBlock?, original)
            else -> {}
        }
    }

    private fun registerWoodPallet(
        Log: RegistrySupplier<RotatedPillarBlock>,
        StrippedLog: RegistrySupplier<RotatedPillarBlock>,
        Plank: RegistrySupplier<Block>,
        slab: RegistrySupplier<SlabBlock>,
        stair: RegistrySupplier<StairBlock>,
        button: RegistrySupplier<ButtonBlock>,
        pressurePlate: RegistrySupplier<PressurePlateBlock>,
        door: RegistrySupplier<DoorBlock>,
        trapDoor: RegistrySupplier<TrapDoorBlock>,
        wood: RegistrySupplier<Block>,
        StrippedWood: RegistrySupplier<Block>,
        fence: RegistrySupplier<FenceBlock>,
        gate: RegistrySupplier<FenceGateBlock>,
        sign: RegistrySupplier<StandingSignBlock>,
        wallSign: RegistrySupplier<WallSignBlock>,
        craftingTable: RegistrySupplier<GenerationsCraftingTableBlock>,
        hangingSignBlock: RegistrySupplier<CeilingHangingSignBlock>,
        wallHangingSignBlock: RegistrySupplier<WallHangingSignBlock>,
        bookshelf: RegistrySupplier<Block>
    ) {
        if (!registered(Log.get())) registerLog(Log)
        if (!registered(StrippedLog.get())) registerLog(StrippedLog)
        if (!registered(Plank.get())) registerBlockItem(Plank)
        if (!registered(wood.get())) registerBlockItem(wood)
        if (!registered(StrippedWood.get())) registerBlockItem(StrippedWood)
        if (!registered(slab.get())) registerSlab(slab.get(), Plank.get())
        if (!registered(stair.get())) registerStairs(stair.get(), Plank.get())
        if (!registered(button.get())) registerButton(button.get(), Plank.get())
        if (!registered(pressurePlate.get())) registerPressurePlate(pressurePlate.get(), Plank.get())
        if (!registered(door.get())) registerDoor(door.get())
        if (!registered(trapDoor.get())) registerTrapDoor(trapDoor)
        if (!registered(gate.get())) registerGate(gate.get(), null, Plank.get())
        if (!registered(fence.get())) registerFence(fence.get(), Plank.get())
        if (!registered(sign.get())) registerSign(sign.get(), wallSign.get(), Plank.get())
        if (!registered(hangingSignBlock.get())) registerHangingSign(
            hangingSignBlock.get(),
            wallHangingSignBlock.get(),
            StrippedLog.get()
        )
        if (!registered(craftingTable.get())) registerCraftingTable(craftingTable, Plank)
        if (!registered(bookshelf.get())) registerBookShelf(bookshelf, Plank)
    }

    companion object {
        @JvmField
        val dropSelfList: ArrayList<Any> = ArrayList()
        @JvmField
        val MUSHROOM_BLOCKS: ArrayList<GenerationsMushroomBlock> = ArrayList()
    }
}

private fun BlockModelBuilder.configure(rotX: Int = 0, rotY: Int = 0): Array<ConfiguredModel> {
    return arrayOf(ConfiguredModel(this, rotX, rotY, false))
}


