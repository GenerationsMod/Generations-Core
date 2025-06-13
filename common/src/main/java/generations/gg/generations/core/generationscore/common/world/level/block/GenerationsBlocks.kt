package generations.gg.generations.core.generationscore.common.world.level.block

import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.util.ItemPlatformRegistry
import generations.gg.generations.core.generationscore.common.world.item.GenericChestBlockItem
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks.BLOCKS
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks.BLOCK_ITEMS
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks.STONE
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks.ULTRA_BLOCKS
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks.VOLCANIC_STONE
import generations.gg.generations.core.generationscore.common.world.level.block.Properties.STONE_PROPERTY
import generations.gg.generations.core.generationscore.common.world.level.block.decorations.PokecenterScarletSignBlock
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericChestBlock
import generations.gg.generations.core.generationscore.common.world.level.block.pumpkin.CursedCarvedPumpkinBlock
import generations.gg.generations.core.generationscore.common.world.level.block.pumpkin.EquipableCursedCarvedPumpkinBlock
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsBlockSet
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsFullBlockSet
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsUltraBlockSet
import generations.gg.generations.core.generationscore.common.world.level.block.state.properties.GenerationsBlockSetTypes
import generations.gg.generations.core.generationscore.common.world.level.levelgen.GenerationsFeatures
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.ColorRGBA
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.material.MapColor
import java.util.function.BiFunction
import java.util.function.Function
import java.util.function.Supplier

@Suppress("unused")
object GenerationsBlocks {
    val BLOCKS = BlockPlatformRegistry()
    val ULTRA_BLOCKS = BlockPlatformRegistry()
    val STONE = BlockPlatformRegistry()
    val BLOCK_ITEMS = ItemPlatformRegistry()

    val ULTRA_BLOCK_SETTINGS = BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).lightLevel { 15 }
    val POKEMART_SIGN = registerBlockItem("pokemart_sign", Block(STONE_PROPERTY))

    /**
     * PokeCenter
     */
    val POKECENTER_SIGN = registerBlockItem("pokecenter_sign", Block(STONE_PROPERTY))
    val POKECENTER_SCARLET_SIGN = registerBlockItem("pokecenter_scarlet_sign", PokecenterScarletSignBlock(BlockBehaviour.Properties.of().lightLevel { 15 }))
    val POKECENTER_DOOR = registerBlockItem("pokecenter_door", DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).sound(SoundType.AMETHYST).noOcclusion()))

    val POKECENTER_ROOF_SET = GenerationsBlockSet("pokecenter_roof", STONE_PROPERTY.lightLevel { 5 })
    val POKECENTER_ROOF_2_SET = GenerationsBlockSet("pokecenter_roof_2", STONE_PROPERTY.lightLevel { 5 })
    val HOUSE_FLOOR_1 = registerBlockItem("house_floor_1", Block(STONE_PROPERTY))
    val HOUSE_FLOOR_2 = registerBlockItem("house_floor_2", Block(STONE_PROPERTY))
    val HOUSE_FLOOR_3 = registerBlockItem("house_floor_3", Block(STONE_PROPERTY))
    val HOUSE_FLOOR_4 = registerBlockItem("house_floor_4", Block(STONE_PROPERTY))
    val HOUSE_FLOOR_5 = registerBlockItem("house_floor_5", Block(STONE_PROPERTY))
    val HOUSE_FLOOR_6 = registerBlockItem("house_floor_6", Block(STONE_PROPERTY))
    val HOUSE_FLOOR_7 = registerBlockItem("house_floor_7", Block(STONE_PROPERTY))
    val HOUSE_FLOOR_8 = registerBlockItem("house_floor_8", Block(STONE_PROPERTY))
    val RUINS_WALL = registerBlockItem("ruins_wall", Block(STONE_PROPERTY))
    val DUSTY_RUINS_WALL = registerBlockItem("dusty_ruins_wall", Block(STONE_PROPERTY))

    val TEMPLE_BLOCK_SET = GenerationsBlockSet("temple_block")
    val TEMPLE_BRICK_SET = GenerationsBlockSet("temple_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS     ))
    val CASTLE_BLOCK_SET = GenerationsBlockSet("castle_block")
    val CRACKED_CASTLE_BLOCK_SET = GenerationsBlockSet("cracked_castle_block")
    val CASTLE_BRICK_SET = GenerationsBlockSet("castle_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val CASTLE_BRICK_2_SET = GenerationsBlockSet("castle_brick_2", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))

    val GRAY_CASTLE_BRICK_SET = GenerationsBlockSet("gray_castle_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val GRAY_CASTLE_BRICK_2_SET = GenerationsBlockSet("gray_castle_brick_2", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val WHITE_CASTLE_BRICK_SET = GenerationsBlockSet("white_castle_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val WHITE_CASTLE_BRICK_2_SET = GenerationsBlockSet("white_castle_brick_2", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val CASTLE_WALL_SET = GenerationsBlockSet("castle_wall")
    val CASTLE_WALL_2_SET = GenerationsBlockSet("castle_wall_2")
    val CASTLE_WALL_3_SET = GenerationsBlockSet("castle_wall_3")
    val CASTLE_WALL_4_SET = GenerationsBlockSet("castle_wall_4")
    val CASTLE_FLOOR_SET = GenerationsBlockSet("castle_floor")
    val ICE_BRICK_SET = GenerationsBlockSet("ice_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.ICE))
    val ICE_PILLAR = registerBlockItem("ice_pillar", PillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ICE)))
    val BROKEN_ICE_PILLAR = registerBlockItem("ice_pillar_broken", PillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ICE)))

    val ICE_PILLAR_SIDE_SET: GenerationsBlockSet = GenerationsBlockSet("ice_pillar_side", BlockBehaviour.Properties.ofFullCopy(Blocks.ICE))
    val ICE_PILLAR_TOP_SET = GenerationsBlockSet("ice_pillar_top", BlockBehaviour.Properties.ofFullCopy(Blocks.ICE))
    val ROCK_SET = GenerationsBlockSet("rock")
    val CAVE_ROCK_SET = GenerationsBlockSet("cave_rock")
    val CAVE_ROCK_FLOOR_SET = GenerationsBlockSet("cave_rock_floor")
    val GRAY_CAVE_ROCK_FLOOR_SET = GenerationsBlockSet("gray_cave_rock_floor")
    val ICE_CAVE_ROCK_FLOOR_SET = GenerationsBlockSet("ice_cave_rock_floor", BlockBehaviour.Properties.ofFullCopy(Blocks.ICE))
    val BRIDGE_BLOCK_SET = GenerationsBlockSet("bridge_block", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS))

    val CASTLE_PILLAR = registerBlockItem("castle_pillar", PillarBlock(STONE_PROPERTY))
    val BROKEN_CASTLE_PILLAR = registerBlockItem("broken_castle_pillar", PillarBlock(STONE_PROPERTY))
    val PRISMARINE_PILLAR = registerBlockItem("prismarine_pillar", PillarBlock(STONE_PROPERTY))
    val BROKEN_PRISMARINE_PILLAR = registerBlockItem("prismarine_pillar_broken", PillarBlock(STONE_PROPERTY))
    val DARK_PRISMARINE_PILLAR = registerBlockItem("dark_prismarine_pillar", PillarBlock(STONE_PROPERTY))
    val BROKEN_DARK_PRISMARINE_PILLAR = registerBlockItem("dark_prismarine_pillar_broken", PillarBlock(STONE_PROPERTY))
    val HAUNTED_PILLAR = registerBlockItem("haunted_pillar", PillarBlock(STONE_PROPERTY))
    val BROKEN_HAUNTED_PILLAR = registerBlockItem("haunted_pillar_broken", PillarBlock(STONE_PROPERTY))

    val DAWN_STONE_BLOCK = registerBlockItem("dawn_stone_block", Block(STONE_PROPERTY))
    val DUSK_STONE_BLOCK = registerBlockItem("dusk_stone_block", Block(STONE_PROPERTY))
    val FIRE_STONE_BLOCK = registerBlockItem("fire_stone_block", Block(STONE_PROPERTY))
    val ICE_STONE_BLOCK = registerBlockItem("ice_stone_block", Block(STONE_PROPERTY))
    val LEAF_STONE_BLOCK = registerBlockItem("leaf_stone_block", Block(STONE_PROPERTY))
    val MOON_STONE_BLOCK = registerBlockItem("moon_stone_block", Block(STONE_PROPERTY))
    val SHINY_STONE_BLOCK = registerBlockItem("shiny_stone_block", Block(STONE_PROPERTY))
    val SUN_STONE_BLOCK = registerBlockItem("sun_stone_block", Block(STONE_PROPERTY))
    val THUNDER_STONE_BLOCK = registerBlockItem("thunder_stone_block", Block(STONE_PROPERTY))
    val WATER_STONE_BLOCK = registerBlockItem("water_stone_block", Block(STONE_PROPERTY))

    val ULTRITE_BLOCK = registerBlockItem("ultrite_block", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)))

    val CRATE = registerBlockItem("crate", Block(STONE_PROPERTY))

    /**
     * Compressed Polished Andesite Pallet
     */
    val COMPRESSED_POLISHED_ANDESITE_SET: GenerationsBlockSet = GenerationsBlockSet("compressed_polished_andesite", BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE))
    val COMPRESSED_POLISHED_DIORITE_SET: GenerationsBlockSet = GenerationsBlockSet("compressed_polished_diorite", BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DIORITE))
    val COMPRESSED_POLISHED_GRANITE_SET: GenerationsBlockSet = GenerationsBlockSet("compressed_polished_granite", BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_GRANITE))
    val COMPRESSED_POLISHED_DEEPSLATE_SET: GenerationsBlockSet = GenerationsBlockSet("compressed_polished_deepslate", BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE))

    val WARNING_BLOCK = registerBlockItem("warning_block", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BEDROCK)))


    //Cursed Pumpkin Based Blocks
    val CURSED_PUMPKIN = registerBlockItem("cursed_pumpkin", CursedCarvedPumpkinBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARVED_PUMPKIN)))

    val CURSED_CARVED_PUMPKIN = registerBlockItem("cursed_carved_pumpkin", EquipableCursedCarvedPumpkinBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARVED_PUMPKIN)))
    val CURSED_JACK_O_LANTERN = registerBlockItem("cursed_jack_o_lantern", CursedCarvedPumpkinBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JACK_O_LANTERN)))

    val COBBLE_RUINS_1_SET = GenerationsBlockSet("cobble_ruins_1")
    val COBBLE_RUINS_2_SET = GenerationsBlockSet("cobble_ruins_2")
    val COBBLE_RUINS_3_SET = GenerationsBlockSet("cobble_ruins_3")
    val COBBLE_RUINS_4_SET = GenerationsBlockSet("cobble_ruins_4")

    /**
     * Ultra Blocks
     */
    val ULTRA_WHITE_SET = GenerationsUltraBlockSet("ultra_white", registerUltraBlock("ultra_white", Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.WHITE))))
    val ULTRA_LIGHT_GRAY_SET = GenerationsUltraBlockSet("ultra_light_gray", registerUltraBlock("ultra_light_gray", Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.LIGHT_GRAY))))
    val ULTRA_GRAY_SET = GenerationsUltraBlockSet("ultra_gray", registerUltraBlock("ultra_gray", Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.GRAY))))
    val ULTRA_BLACK_SET = GenerationsUltraBlockSet("ultra_black", registerUltraBlock("ultra_black", Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.BLACK))))
    val ULTRA_BROWN_SET = GenerationsUltraBlockSet("ultra_brown", registerUltraBlock("ultra_brown", Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.BROWN))))
    val ULTRA_RED_SET = GenerationsUltraBlockSet("ultra_red", registerUltraBlock("ultra_red", Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.RED))))
    val ULTRA_ORANGE_SET = GenerationsUltraBlockSet("ultra_orange", registerUltraBlock("ultra_orange", Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.ORANGE))))
    val ULTRA_YELLOW_SET = GenerationsUltraBlockSet("ultra_yellow", registerUltraBlock("ultra_yellow", Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.YELLOW))))
    val ULTRA_LIME_SET = GenerationsUltraBlockSet("ultra_lime", registerUltraBlock("ultra_lime", Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.LIME))))
    val ULTRA_GREEN_SET = GenerationsUltraBlockSet("ultra_green", registerUltraBlock("ultra_green", Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.GREEN))))
    val ULTRA_CYAN_SET = GenerationsUltraBlockSet("ultra_cyan", registerUltraBlock("ultra_cyan", Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.CYAN))))
    val ULTRA_LIGHT_BLUE_SET = GenerationsUltraBlockSet("ultra_light_blue", registerUltraBlock("ultra_light_blue", Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.LIGHT_BLUE))))
    val ULTRA_BLUE_SET = GenerationsUltraBlockSet("ultra_blue", registerUltraBlock("ultra_blue", Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.BLUE))))
    val ULTRA_PURPLE_SET = GenerationsUltraBlockSet("ultra_purple", registerUltraBlock("ultra_purple", Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.PURPLE))))
    val ULTRA_MAGENTA_SET = GenerationsUltraBlockSet("ultra_magenta", registerUltraBlock("ultra_magenta", Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.MAGENTA))))
    val ULTRA_PINK_SET = GenerationsUltraBlockSet("ultra_pink", registerUltraBlock("ultra_pink", Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.PINK))))

    val ULTRA_SAND = registerBlockItem("ultra_sand", ColoredFallingBlock(0xdbd3a0.rgba(), BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)))

    /**
     * Marble block sets
     */
    val WHITE_MARBLE_SET = GenerationsFullBlockSet("white_marble", DyeColor.WHITE, GenerationsBlockSetTypes.MARBLE)
    val LIGHT_GRAY_MARBLE_SET = GenerationsFullBlockSet("light_gray_marble", DyeColor.GRAY, GenerationsBlockSetTypes.MARBLE)
    val GRAY_MARBLE_SET = GenerationsFullBlockSet("gray_marble", DyeColor.GRAY, GenerationsBlockSetTypes.MARBLE)
    val BLACK_MARBLE_SET = GenerationsFullBlockSet("black_marble", DyeColor.BLACK, GenerationsBlockSetTypes.MARBLE)
    val BROWN_MARBLE_SET = GenerationsFullBlockSet("brown_marble", DyeColor.BROWN, GenerationsBlockSetTypes.MARBLE)
    val RED_MARBLE_SET = GenerationsFullBlockSet("red_marble", DyeColor.RED, GenerationsBlockSetTypes.MARBLE)
    val ORANGE_MARBLE_SET = GenerationsFullBlockSet("orange_marble", DyeColor.ORANGE, GenerationsBlockSetTypes.MARBLE)
    val YELLOW_MARBLE_SET = GenerationsFullBlockSet("yellow_marble", DyeColor.YELLOW, GenerationsBlockSetTypes.MARBLE)
    val LIME_MARBLE_SET = GenerationsFullBlockSet("lime_marble", DyeColor.LIME, GenerationsBlockSetTypes.MARBLE)
    val GREEN_MARBLE_SET = GenerationsFullBlockSet("green_marble", DyeColor.GREEN, GenerationsBlockSetTypes.MARBLE)
    val CYAN_MARBLE_SET = GenerationsFullBlockSet("cyan_marble", DyeColor.CYAN, GenerationsBlockSetTypes.MARBLE)
    val POWDER_BLUE_MARBLE_SET: GenerationsFullBlockSet = GenerationsFullBlockSet("powder_blue_marble", STONE_PROPERTY, GenerationsBlockSetTypes.MARBLE)
    val LIGHT_BLUE_MARBLE_SET: GenerationsFullBlockSet = GenerationsFullBlockSet("light_blue_marble", DyeColor.LIGHT_BLUE, GenerationsBlockSetTypes.MARBLE)
    val BLUE_MARBLE_SET: GenerationsFullBlockSet = GenerationsFullBlockSet("blue_marble", DyeColor.BLUE, GenerationsBlockSetTypes.MARBLE)
    val PURPLE_MARBLE_SET: GenerationsFullBlockSet = GenerationsFullBlockSet("purple_marble", DyeColor.PURPLE, GenerationsBlockSetTypes.MARBLE)
    val MAGENTA_MARBLE_SET: GenerationsFullBlockSet = GenerationsFullBlockSet("magenta_marble", DyeColor.MAGENTA, GenerationsBlockSetTypes.MARBLE)
    val PINK_MARBLE_SET: GenerationsFullBlockSet = GenerationsFullBlockSet("pink_marble", DyeColor.PINK, GenerationsBlockSetTypes.MARBLE)

    /**
     * Unown Blocks
     */
    val UNOWN_BLOCK_A = registerUnownBlock("a")
    val UNOWN_BLOCK_B = registerUnownBlock("b")
    val UNOWN_BLOCK_C = registerUnownBlock("c")
    val UNOWN_BLOCK_D = registerUnownBlock("d")
    val UNOWN_BLOCK_E = registerUnownBlock("e")
    val UNOWN_BLOCK_F = registerUnownBlock("f")
    val UNOWN_BLOCK_G = registerUnownBlock("g")
    val UNOWN_BLOCK_H = registerUnownBlock("h")
    val UNOWN_BLOCK_I = registerUnownBlock("i")
    val UNOWN_BLOCK_J = registerUnownBlock("j")
    val UNOWN_BLOCK_K = registerUnownBlock("k")
    val UNOWN_BLOCK_L = registerUnownBlock("l")
    val UNOWN_BLOCK_M = registerUnownBlock("m")
    val UNOWN_BLOCK_N = registerUnownBlock("n")
    val UNOWN_BLOCK_O = registerUnownBlock("o")
    val UNOWN_BLOCK_P = registerUnownBlock("p")
    val UNOWN_BLOCK_Q = registerUnownBlock("q")
    val UNOWN_BLOCK_R = registerUnownBlock("r")
    val UNOWN_BLOCK_S = registerUnownBlock("s")
    val UNOWN_BLOCK_T = registerUnownBlock("t")
    val UNOWN_BLOCK_U = registerUnownBlock("u")
    val UNOWN_BLOCK_V = registerUnownBlock("v")
    val UNOWN_BLOCK_W = registerUnownBlock("w")
    val UNOWN_BLOCK_X = registerUnownBlock("x")
    val UNOWN_BLOCK_Y = registerUnownBlock("y")
    val UNOWN_BLOCK_Z = registerUnownBlock("z")
    val UNOWN_BLOCK_BLANK = registerUnownBlock("blank", " ")
    val UNOWN_BLOCK_EXCLAMATION_MARK = registerUnownBlock("exclamation_mark", "!")
    val UNOWN_BLOCK_QUESTION_MARK = registerUnownBlock("question_mark", "?")

    /**
     * Full ChargeStone Pallet
     */
    val CHARGE_STONE_SET: GenerationsFullBlockSet = GenerationsFullBlockSet("charge_stone", GenerationsBlockSetTypes.CHARGE_STONE)
    val CHARGE_COBBLESTONE_SET: GenerationsBlockSet = GenerationsBlockSet("charge_cobblestone", BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE))
    val MOSSY_CHARGE_COBBLESTONE_SET: GenerationsBlockSet = GenerationsBlockSet("mossy_charge_cobblestone", BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE))

    //Smooth ChargeStone
    val SMOOTH_CHARGE_STONE = registerStone("smooth_charge_stone", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE)))
    val SMOOTH_CHARGE_STONE_SLAB = registerStone("smooth_charge_stone_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE_SLAB)))

    //ChargeStoneBricks
    val CHARGE_STONE_BRICKS = registerStone("charge_stone_bricks", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)))
    val CRACKED_CHARGE_STONE_BRICKS = registerStone("cracked_charge_stone_bricks", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_STONE_BRICKS)))
    val CHARGE_STONE_BRICK_STAIRS = registerStone("charge_stone_brick_stairs", StairBlock(Blocks.STONE_BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CHARGE_STONE_BRICKS)))
    val CHARGE_STONE_BRICK_SLAB = registerStone("charge_stone_brick_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(CHARGE_STONE_BRICKS)))
    val CHARGE_STONE_BRICK_WALL = registerStone("charge_stone_brick_wall", WallBlock(BlockBehaviour.Properties.ofFullCopy(CHARGE_STONE_BRICKS)))
    val CHISELED_CHARGE_STONE_BRICKS = registerStone("chiseled_charge_stone_bricks", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS)))

    val MOSSY_CHARGE_STONE_BRICKS_SET = GenerationsBlockSet("mossy_charge_stone_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS), registerBlockItem("mossy_charge_stone_bricks", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS))))

    //Infested blocks
    val INFESTED_CHARGE_STONE = registerStone("infested_charge_stone", InfestedBlock(CHARGE_STONE_SET.baseBlock, BlockBehaviour.Properties.ofFullCopy(Blocks.INFESTED_STONE)))
    val INFESTED_CHARGE_COBBLESTONE = registerStone("infested_charge_cobblestone", InfestedBlock(CHARGE_COBBLESTONE_SET.baseBlock, BlockBehaviour.Properties.ofFullCopy(Blocks.INFESTED_COBBLESTONE)))
    val INFESTED_CHARGE_STONE_BRICKS = registerStone("infested_charge_stone_bricks", InfestedBlock(CHARGE_STONE_BRICKS, BlockBehaviour.Properties.ofFullCopy(Blocks.INFESTED_STONE_BRICKS)))
    val INFESTED_MOSSY_CHARGE_STONE_BRICKS = registerStone("infested_mossy_charge_stone_bricks", InfestedBlock(MOSSY_CHARGE_STONE_BRICKS_SET.baseBlock, BlockBehaviour.Properties.ofFullCopy(Blocks.INFESTED_MOSSY_STONE_BRICKS)))
    val INFESTED_CRACKED_CHARGE_STONE_BRICKS = registerStone("infested_cracked_charge_stone_bricks", InfestedBlock(CRACKED_CHARGE_STONE_BRICKS, BlockBehaviour.Properties.ofFullCopy(Blocks.INFESTED_CRACKED_STONE_BRICKS)))
    val INFESTED_CHISELED_CHARGE_STONE_BRICKS = registerStone("infested_chiseled_charge_stone_bricks", InfestedBlock(CHISELED_CHARGE_STONE_BRICKS, BlockBehaviour.Properties.ofFullCopy(Blocks.INFESTED_CHISELED_STONE_BRICKS)))

    //Full ChargeStone Pallet ^
    val BRIGHT_CHARGE_COBBLESTONE_SET = GenerationsBlockSet("bright_charge_cobblestone", BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE).lightLevel { 30 })
    val GLOWING_CHARGE_COBBLESTONE_SET = GenerationsBlockSet("glowing_charge_cobblestone", BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE).lightLevel { 15 })
    val POINTED_CHARGE_DRIPSTONE = registerStone<PointedChargeDripstoneBlock>("pointed_charge_dripstone", PointedChargeDripstoneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POINTED_DRIPSTONE)))
    val CHARGE_DRIPSTONE_BLOCK = registerStone("charge_dripstone_block", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK)))


    /**
     * Volcanic Stone Pallet
     */
    val VOLCANIC_STONE = registerStone("volcanic_stone", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)))
    val VOLCANIC_STONE_STAIRS = registerStone("volcanic_stone_stairs", StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLCANIC_STONE)))
    val VOLCANIC_STONE_SLAB = registerStone("volcanic_stone_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(VOLCANIC_STONE)))
    val VOLCANIC_STONE_WALL = registerStone("volcanic_stone_wall", WallBlock(BlockBehaviour.Properties.ofFullCopy(VOLCANIC_STONE)))
    val VOLCANIC_STONE_BUTTON = registerStone("volcanic_stone_button", ButtonBlock(GenerationsBlockSetTypes.VOLCANIC_STONE, 20, BlockBehaviour.Properties.ofFullCopy(VOLCANIC_STONE)))
    val VOLCANIC_STONE_PRESSURE_PLATE = registerStone("volcanic_stone_pressure_plate", PressurePlateBlock(GenerationsBlockSetTypes.VOLCANIC_STONE, BlockBehaviour.Properties.ofFullCopy(VOLCANIC_STONE)))

    //Volcanic CobbleStone
    val VOLCANIC_COBBLESTONE_SET = GenerationsBlockSet("volcanic_cobblestone", BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE))

    //Mossy Volcanic CobbleStone
    val MOSSY_VOLCANIC_COBBLESTONE_SET = GenerationsBlockSet("mossy_volcanic_cobblestone", BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE))

    //Smooth Volcanic Stone
    val SMOOTH_VOLCANIC_STONE = registerStone("smooth_volcanic_stone", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE)))
    val SMOOTH_VOLCANIC_STONE_SLAB = registerStone("smooth_volcanic_stone_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(SMOOTH_VOLCANIC_STONE)))

    //Volcanic Stone Bricks
    val VOLCANIC_STONE_BRICKS = registerStone("volcanic_stone_bricks", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)))
    val CRACKED_VOLCANIC_STONE_BRICKS = registerStone("cracked_volcanic_stone_bricks", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_STONE_BRICKS)))
    val VOLCANIC_STONE_BRICK_STAIRS = registerStone("volcanic_stone_brick_stairs", StairBlock(Blocks.STONE_BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLCANIC_STONE_BRICKS)))
    val VOLCANIC_STONE_BRICK_SLAB = registerStone("volcanic_stone_brick_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(VOLCANIC_STONE_BRICKS)))
    val VOLCANIC_STONE_BRICK_WALL = registerStone("volcanic_stone_brick_wall", WallBlock(BlockBehaviour.Properties.ofFullCopy(VOLCANIC_STONE_BRICKS)))
    val CHISELED_VOLCANIC_STONE_BRICKS = registerStone("chiseled_volcanic_stone_bricks", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS)))

    //Mossy Volcanic Stone Bricks
    val MOSSY_VOLCANIC_STONE_BRICKS_SET = GenerationsBlockSet("mossy_volcanic_stone_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS), registerBlockItem("mossy_volcanic_stone_bricks", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS))))

    //Infested Volcanic Stone Bricks
    val INFESTED_VOLCANIC_STONE = registerStone("infested_volcanic_stone", InfestedBlock(VOLCANIC_STONE, BlockBehaviour.Properties.ofFullCopy(Blocks.INFESTED_STONE)))
    val INFESTED_VOLCANIC_COBBLESTONE = registerStone("infested_volcanic_cobblestone", InfestedBlock(VOLCANIC_COBBLESTONE_SET.baseBlock, BlockBehaviour.Properties.ofFullCopy(Blocks.INFESTED_COBBLESTONE)))
    val INFESTED_VOLCANIC_STONE_BRICKS = registerStone("infested_volcanic_stone_bricks", InfestedBlock(VOLCANIC_STONE_BRICKS, BlockBehaviour.Properties.ofFullCopy(Blocks.INFESTED_STONE_BRICKS)))
    val INFESTED_MOSSY_VOLCANIC_STONE_BRICKS = registerStone("infested_mossy_volcanic_stone_bricks", InfestedBlock(MOSSY_VOLCANIC_STONE_BRICKS_SET.baseBlock, BlockBehaviour.Properties.ofFullCopy(Blocks.INFESTED_STONE_BRICKS)))
    val INFESTED_CRACKED_VOLCANIC_STONE_BRICKS = registerStone("infested_cracked_volcanic_stone_bricks", InfestedBlock(CRACKED_VOLCANIC_STONE_BRICKS, BlockBehaviour.Properties.ofFullCopy(Blocks.INFESTED_STONE_BRICKS)))
    val INFESTED_CHISELED_VOLCANIC_STONE_BRICKS = registerStone("infested_chiseled_volcanic_stone_bricks", InfestedBlock(CHISELED_VOLCANIC_STONE_BRICKS, BlockBehaviour.Properties.ofFullCopy(Blocks.INFESTED_STONE_BRICKS)))

    //Full Volcanic Stone Pallet^
    val VOLCANIC_FIRESTONE = registerBlockItem("volcanic_firestone", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)))
    val VOLCANIC_ROCK_SET = GenerationsBlockSet("volcanic_rock", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).mapColor(MapColor.COLOR_BROWN))

    val LIGHTNING_LANTERN = registerBlockItem("lightning_lantern", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)))


    /**
     * Battle Subway Blocks
     */
    val SUBWAY_FLOOR_CROSS_SET = GenerationsBlockSet("subway_floor_cross", BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE))
    val SUBWAY_FLOOR_FULL_CROSS_SET = GenerationsBlockSet("subway_floor_full_cross", BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE))
    val SUBWAY_FLOOR_PATH_SET = GenerationsBlockSet("subway_floor_path", BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE))
    val SUBWAY_FLOOR_PLAIN_SET = GenerationsBlockSet("subway_floor_plain", BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE))


    //public static final GenerationsBlockSet SUBWAY_WALL_SET = new GenerationsBlockSet("subway_wall", BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE));
    //public static final GenerationsBlockSet SUBWAY_WALL_2_SET = new GenerationsBlockSet("subway_wall_2", BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE));
    val SUBWAY_WALL = registerBlockItem("subway_wall", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)))
    val SUBWAY_WALL_2 = registerBlockItem("subway_wall_2", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)))
    val SUBWAY_WALL_SLAB = registerBlockItem("subway_wall_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(SUBWAY_WALL)))
    val SUBWAY_WALL_2_SLAB = registerBlockItem("subway_wall_2_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(SUBWAY_WALL_2)))
    val SUBWAY_WALL_STAIRS = registerBlockItem("subway_wall_stairs", StairBlock(SUBWAY_WALL.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SUBWAY_WALL)))
    val SUBWAY_WALL_2_STAIRS = registerBlockItem("subway_wall_2_stairs", StairBlock(SUBWAY_WALL_2.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SUBWAY_WALL_2)))

    /**
     * Golden Temple (Pokelantis)
     */
    val GOLDEN_TEMPLE_SAND = registerBlockItem("golden_temple_sand", ColoredFallingBlock(0xdbd3a0.rgba(), BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)))
    val GOLDEN_TEMPLE_SANDSTONE = registerBlockItem("golden_temple_sandstone", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE)))
    val GOLDEN_TEMPLE_SANDSTONE_STAIRS = registerBlockItem("golden_temple_sandstone_stairs", StairBlock(Blocks.SANDSTONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_STAIRS)))
    val GOLDEN_TEMPLE_SANDSTONE_SLAB = registerBlockItem("golden_temple_sandstone_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE)))
    val GOLDEN_TEMPLE_SANDSTONE_WALL = registerBlockItem("golden_temple_sandstone_wall", WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE)))
    val GOLDEN_TEMPLE_CHISELED_SANDSTONE = registerBlockItem("golden_temple_chiseled_sandstone", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_SANDSTONE)))
    val GOLDEN_TEMPLE_CUT_SANDSTONE = registerBlockItem("golden_temple_cut_sandstone", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_SANDSTONE)))
    val GOLDEN_TEMPLE_CUT_SANDSTONE_SLAB = registerBlockItem("golden_temple_cut_sandstone_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_SANDSTONE)))
    val GOLDEN_TEMPLE_SMOOTH_SANDSTONE = registerBlockItem("golden_temple_smooth_sandstone", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE)))
    val GOLDEN_TEMPLE_SMOOTH_SANDSTONE_STAIRS = registerBlockItem("golden_temple_smooth_sandstone_stairs", StairBlock(GOLDEN_TEMPLE_SMOOTH_SANDSTONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(GOLDEN_TEMPLE_SMOOTH_SANDSTONE)))
    val GOLDEN_TEMPLE_SMOOTH_SANDSTONE_SLAB = registerBlockItem("golden_temple_smooth_sandstone_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_TEMPLE_SMOOTH_SANDSTONE)))
    val GOLDEN_TEMPLE_SMOOTH_SANDSTONE_WALL = registerBlockItem("golden_temple_smooth_sandstone_wall", WallBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_TEMPLE_SMOOTH_SANDSTONE)))
    val GOLDEN_TEMPLE_PRISMARINE = registerBlockItem("golden_temple_prismarine", Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).requiresCorrectToolForDrops().strength(1.5f, 6.0f)))
    val GOLDEN_TEMPLE_PRISMARINE_STAIRS = registerBlockItem("golden_temple_prismarine_stairs", StairBlock(Blocks.PRISMARINE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_STAIRS)))
    val GOLDEN_TEMPLE_PRISMARINE_SLAB = registerBlockItem("golden_temple_prismarine_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE)))
    val GOLDEN_TEMPLE_PRISMARINE_WALL = registerBlockItem("golden_temple_prismarine_wall", WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE)))
    val GOLDEN_TEMPLE_DARK_PRISMARINE = registerBlockItem("golden_temple_dark_prismarine", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE)))
    val GOLDEN_TEMPLE_DARK_PRISMARINE_STAIRS = registerBlockItem("golden_temple_dark_prismarine_stairs", StairBlock(Blocks.DARK_PRISMARINE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE_STAIRS)))
    val GOLDEN_TEMPLE_DARK_PRISMARINE_SLAB = registerBlockItem("golden_temple_dark_prismarine_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE)))
    val GOLDEN_TEMPLE_DARK_PRISMARINE_WALL = registerBlockItem("golden_temple_dark_prismarine_wall", WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE)))
    val GOLDEN_TEMPLE_PRISMARINE_BRICKS = registerBlockItem("golden_temple_prismarine_bricks", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICKS)))
    val GOLDEN_TEMPLE_PRISMARINE_BRICK_STAIRS = registerBlockItem("golden_temple_prismarine_brick_stairs", StairBlock(Blocks.PRISMARINE_BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICK_STAIRS)))
    val GOLDEN_TEMPLE_PRISMARINE_BRICK_SLAB = registerBlockItem("golden_temple_prismarine_brick_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICKS)))
    val GOLDEN_TEMPLE_PRISMARINE_BRICK_WALL = registerBlockItem("golden_temple_prismarine_brick_wall", WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICKS)))

    /**
     * Floor
     */
    val FLOOR_1_SET = GenerationsBlockSet("floor_1")
    val FLOOR_2_SET = GenerationsBlockSet("floor_2")
    val FLOOR_3_SET = GenerationsBlockSet("floor_3")
    val FLOOR_4_SET = GenerationsBlockSet("floor_4")
    val MIRRORED_FLOOR_1_SET = GenerationsBlockSet("mirrored_floor_1")
    val MIRRORED_FLOOR_2_SET = GenerationsBlockSet("mirrored_floor_2")
    val MIRRORED_FLOOR_3_SET = GenerationsBlockSet("mirrored_floor_3")

    val ENCHANTED_OBSIDIAN_SET = GenerationsFullBlockSet("enchanted_obsidian", BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN), GenerationsBlockSetTypes.ENCHANTED_OBISIDIAN, registerBlockItem("enchanted_obsidian", EnchantedObsidianBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN))))

    /**
     * Poke Bricks
     */
    val WHITE_POKE_BRICK_SET = GenerationsFullBlockSet("white_poke_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(DyeColor.WHITE), GenerationsBlockSetTypes.POKE_BRICK)
    val LIGHT_GRAY_POKE_BRICK_SET = GenerationsFullBlockSet("light_gray_poke_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(DyeColor.LIGHT_GRAY), GenerationsBlockSetTypes.POKE_BRICK)
    val GRAY_POKE_BRICK_SET = GenerationsFullBlockSet("gray_poke_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(DyeColor.GRAY), GenerationsBlockSetTypes.POKE_BRICK)
    val BLACK_POKE_BRICK_SET = GenerationsFullBlockSet("black_poke_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(DyeColor.BLACK), GenerationsBlockSetTypes.POKE_BRICK)
    val BROWN_POKE_BRICK_SET = GenerationsFullBlockSet("brown_poke_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(DyeColor.BROWN), GenerationsBlockSetTypes.POKE_BRICK)
    val RED_POKE_BRICK_SET = GenerationsFullBlockSet("red_poke_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(DyeColor.RED), GenerationsBlockSetTypes.POKE_BRICK)
    val ORANGE_POKE_BRICK_SET = GenerationsFullBlockSet("orange_poke_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(DyeColor.ORANGE), GenerationsBlockSetTypes.POKE_BRICK)
    val YELLOW_POKE_BRICK_SET = GenerationsFullBlockSet("yellow_poke_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(DyeColor.YELLOW), GenerationsBlockSetTypes.POKE_BRICK)
    val LIME_POKE_BRICK_SET = GenerationsFullBlockSet("lime_poke_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(DyeColor.LIME), GenerationsBlockSetTypes.POKE_BRICK)
    val GREEN_POKE_BRICK_SET = GenerationsFullBlockSet("green_poke_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(DyeColor.GREEN), GenerationsBlockSetTypes.POKE_BRICK)
    val CYAN_POKE_BRICK_SET = GenerationsFullBlockSet("cyan_poke_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(DyeColor.CYAN), GenerationsBlockSetTypes.POKE_BRICK)
    val LIGHT_BLUE_POKE_BRICK_SET = GenerationsFullBlockSet("light_blue_poke_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(DyeColor.LIGHT_BLUE), GenerationsBlockSetTypes.POKE_BRICK)
    val BLUE_POKE_BRICK_SET = GenerationsFullBlockSet("blue_poke_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(DyeColor.BLUE), GenerationsBlockSetTypes.POKE_BRICK)
    val PURPLE_POKE_BRICK_SET = GenerationsFullBlockSet("purple_poke_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(DyeColor.PURPLE), GenerationsBlockSetTypes.POKE_BRICK)
    val MAGENTA_POKE_BRICK_SET = GenerationsFullBlockSet("magenta_poke_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(DyeColor.MAGENTA), GenerationsBlockSetTypes.POKE_BRICK)
    val PINK_POKE_BRICK_SET = GenerationsFullBlockSet("pink_poke_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).mapColor(DyeColor.PINK), GenerationsBlockSetTypes.POKE_BRICK)

    /**
     * Ore Blocks
     */
    val SAPPHIRE_BLOCK = registerBlockItem("sapphire_block", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)))
    val SAPPHIRE_STAIRS = registerBlockItem("sapphire_stairs", StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SAPPHIRE_BLOCK)))
    val SAPPHIRE_SLAB = registerBlockItem("sapphire_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(SAPPHIRE_BLOCK)))
    val SAPPHIRE_WALL = registerBlockItem("sapphire_wall", WallBlock(BlockBehaviour.Properties.ofFullCopy(SAPPHIRE_BLOCK)))


    val RUBY_BLOCK = registerBlockItem("ruby_block", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)))
    val RUBY_STAIRS = registerBlockItem("ruby_stairs", StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(RUBY_BLOCK)))
    val RUBY_SLAB = registerBlockItem("ruby_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(RUBY_BLOCK)))
    val RUBY_WALL = registerBlockItem("ruby_wall", WallBlock(BlockBehaviour.Properties.ofFullCopy(RUBY_BLOCK)))

    val CRYSTAL_BLOCK = registerBlockItem("crystal_block", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)))
    val CRYSTAL_STAIRS = registerBlockItem("crystal_stairs", StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BLOCK)))
    val CRYSTAL_SLAB = registerBlockItem("crystal_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BLOCK)))
    val CRYSTAL_WALL = registerBlockItem("crystal_wall", WallBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BLOCK)))
    val CRYSTAL_LIGHT = registerBlockItem("crystal_light", Block(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BLOCK).lightLevel { 15 }))
    val SILICON_BLOCK = registerBlockItem("silicon_block", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)))
    val Z_BLOCK = registerBlockItem("z_block", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK)))

    /**
     * Ultra Space
     */
    val ULTRA_SANDSTONE = registerBlockItem("ultra_sandstone", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE)))
    val ULTRA_SANDSTONE_STAIRS = registerBlockItem("ultra_sandstone_stairs", StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ULTRA_SANDSTONE)))
    val ULTRA_SANDSTONE_SLAB = registerBlockItem("ultra_sandstone_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(ULTRA_SANDSTONE)))
    val ULTRA_SANDSTONE_WALL = registerBlockItem("ultra_sandstone_wall", WallBlock(BlockBehaviour.Properties.ofFullCopy(ULTRA_SANDSTONE)))
    val ULTRA_SMOOTH_SANDSTONE = registerBlockItem("ultra_smooth_sandstone", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE)))
    val ULTRA_SMOOTH_SANDSTONE_STAIRS = registerBlockItem("ultra_smooth_sandstone_stairs", StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ULTRA_SMOOTH_SANDSTONE)))
    val ULTRA_SMOOTH_SANDSTONE_SLAB = registerBlockItem("ultra_smooth_sandstone_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(ULTRA_SMOOTH_SANDSTONE)))
    val ULTRA_SMOOTH_SANDSTONE_WALL = registerBlockItem("ultra_smooth_sandstone_wall", WallBlock(BlockBehaviour.Properties.ofFullCopy(ULTRA_SMOOTH_SANDSTONE)))
    val ULTRA_CHISELED_SANDSTONE = registerBlockItem("ultra_chiseled_sandstone", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_SANDSTONE)))
    val ULTRA_CUT_SANDSTONE = registerBlockItem("ultra_cut_sandstone", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_SANDSTONE)))
    val ULTRA_CUT_SANDSTONE_SLAB = registerBlockItem("ultra_cut_sandstone_slab", SlabBlock(BlockBehaviour.Properties.ofFullCopy(ULTRA_CUT_SANDSTONE)))
    val GHOST_LANTERN = registerBlockItem("ghost_lantern", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(.5f).lightLevel { 15 }))
    val GHOST_BRICKS_SET = GenerationsBlockSet("ghost_brick", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS), registerBlockItem("ghost_bricks", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))))
    val GHOST_OBELISK_SET = GenerationsBlockSet("ghost_obelisk")
    val GHOST_PILLAR = registerBlockItem("ghost_pillar", RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)))
    val MIRROR_GLASS_SET = GenerationsBlockSet("mirror_glass", BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(.5f))
    val NORMAL_SANDSTONE_SET = GenerationsBlockSet("normal_sandstone", BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE))
    val BRIGHT_SANDSTONE_SET = GenerationsBlockSet("bright_sandstone", BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).lightLevel { 15 })
    val MACHINE_BLOCK = registerBlockItem("machine_block", MachineBlock(STONE_PROPERTY))
    val OCEAN_BLOCK_SET = GenerationsBlockSet("ocean_block")
    val WATER_QUARTZ_SET = GenerationsBlockSet("water_quartz", BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK))
    val RUINS_SAND = registerBlockItem("ruins_sand", ColoredFallingBlock(0xdbd3a0.rgba(), BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)))
    val BURST_TURF = registerBlockItem("burst_turf", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)))


    //Rich Soil
    val RICH_SOIL_1 = registerBlockItem("rich_soil_1", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)))
    val RICH_SOIL_2 = registerBlockItem("rich_soil_2", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)))
    val RICH_SOIL_3 = registerBlockItem("rich_soil_3", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)))
    val RICH_SOIL_4 = registerBlockItem("rich_soil_4", Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)))

    //Bleach Stone
    val BLEACH_STONE_SET = GenerationsBlockSet("bleach_stone")

    //Shingles
    val WHITE_SHINGLES_SET = GenerationsBlockSet("white_shingles", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val LIGHT_GRAY_SHINGLES_SET = GenerationsBlockSet("light_gray_shingles", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val GRAY_SHINGLES_SET = GenerationsBlockSet("gray_shingles", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val BLACK_SHINGLES_SET = GenerationsBlockSet("black_shingles", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val BROWN_SHINGLES_SET = GenerationsBlockSet("brown_shingles", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val RED_SHINGLES_SET = GenerationsBlockSet("red_shingles", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val ORANGE_SHINGLES_SET = GenerationsBlockSet("orange_shingles", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val YELLOW_SHINGLES_SET = GenerationsBlockSet("yellow_shingles", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val LIME_SHINGLES_SET = GenerationsBlockSet("lime_shingles", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val GREEN_SHINGLES_SET = GenerationsBlockSet("green_shingles", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val CYAN_SHINGLES_SET = GenerationsBlockSet("cyan_shingles", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val LIGHT_BLUE_SHINGLES_SET = GenerationsBlockSet("light_blue_shingles", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val BLUE_SHINGLES_SET = GenerationsBlockSet("blue_shingles", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val PURPLE_SHINGLES_SET = GenerationsBlockSet("purple_shingles", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val MAGENTA_SHINGLES_SET = GenerationsBlockSet("magenta_shingles", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    val PINK_SHINGLES_SET = GenerationsBlockSet("pink_shingles", BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))

    val COOL_STONE_SET = GenerationsBlockSet("cool_stone")

    /**
     * 1 Block Tall Ballonlea Mushrooms
     */
    val BALLONLEA_BLUE_MUSHROOM = registerBlockItem("ballonlea_blue_mushroom", GenerationsMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel { 6 }, GenerationsFeatures.BALLONLEA_BLUE_MUSHROOM))
    val BALLONLEA_GREEN_MUSHROOM = registerBlockItem("ballonlea_green_mushroom", GenerationsMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel { 6 }, GenerationsFeatures.BALLONLEA_GREEN_MUSHROOM))
    val BALLONLEA_PINK_MUSHROOM = registerBlockItem("ballonlea_pink_mushroom", GenerationsMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel { 6 }, GenerationsFeatures.BALLONLEA_PINK_MUSHROOM))
    val BALLONLEA_YELLOW_MUSHROOM = registerBlockItem("ballonlea_yellow_mushroom", GenerationsMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel { 6 }, GenerationsFeatures.BALLONLEA_YELLOW_MUSHROOM))

    val GROUP_BALLONLEA_BLUE_MUSHROOM = registerBlockItem("group_ballonlea_blue_mushroom", GenerationsMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel { 6 }, GenerationsFeatures.GROUP_BALLONLEA_BLUE_MUSHROOM))
    val GROUP_BALLONLEA_GREEN_MUSHROOM = registerBlockItem("group_ballonlea_green_mushroom", GenerationsMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel { 6 }, GenerationsFeatures.GROUP_BALLONLEA_GREEN_MUSHROOM))
    val GROUP_BALLONLEA_PINK_MUSHROOM = registerBlockItem("group_ballonlea_pink_mushroom", GenerationsMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel { 6 }, GenerationsFeatures.GROUP_BALLONLEA_PINK_MUSHROOM))
    val GROUP_BALLONLEA_YELLOW_MUSHROOM = registerBlockItem("group_ballonlea_yellow_mushroom", GenerationsMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel { 6 }, GenerationsFeatures.GROUP_BALLONLEA_YELLOW_MUSHROOM))

    val TALL_BALLONLEA_BLUE_MUSHROOM = registerBlockItem("tall_ballonlea_blue_mushroom", GenerationsMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel { 6 }, GenerationsFeatures.TALL_BALLONLEA_BLUE_MUSHROOM))
    val TALL_BALLONLEA_GREEN_MUSHROOM = registerBlockItem("tall_ballonlea_green_mushroom", GenerationsMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel { 6 }, GenerationsFeatures.TALL_BALLONLEA_GREEN_MUSHROOM))
    val TALL_BALLONLEA_PINK_MUSHROOM = registerBlockItem("tall_ballonlea_pink_mushroom", GenerationsMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel { 6 }, GenerationsFeatures.TALL_BALLONLEA_PINK_MUSHROOM))
    val TALL_BALLONLEA_YELLOW_MUSHROOM = registerBlockItem("tall_ballonlea_yellow_mushroom", GenerationsMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel { 6 }, GenerationsFeatures.TALL_BALLONLEA_YELLOW_MUSHROOM))

    val DOUBLE_BALLONLEA_BLUE_MUSHROOM = registerBlockItem("double_ballonlea_blue_mushroom", GenerationsMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel { 6 }, GenerationsFeatures.DOUBLE_BALLONLEA_BLUE_MUSHROOM))
    val DOUBLE_BALLONLEA_GREEN_MUSHROOM = registerBlockItem("double_ballonlea_green_mushroom", GenerationsMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel { 6 }, GenerationsFeatures.DOUBLE_BALLONLEA_GREEN_MUSHROOM))
    val DOUBLE_BALLONLEA_PINK_MUSHROOM = registerBlockItem("double_ballonlea_pink_mushroom", GenerationsMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel { 6 }, GenerationsFeatures.DOUBLE_BALLONLEA_PINK_MUSHROOM))
    val DOUBLE_BALLONLEA_YELLOW_MUSHROOM = registerBlockItem("double_ballonlea_yellow_mushroom", GenerationsMushroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel { 6 }, GenerationsFeatures.DOUBLE_BALLONLEA_YELLOW_MUSHROOM))

    val FLABEBE_FLOWER_AZ = registerBlockItem("flabebe_flower_az", FlabebeFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).strength(0.8f).sound(SoundType.FLOWERING_AZALEA)))
    val FLABEBE_FLOWER_BLUE = registerBlockItem("flabebe_flower_blue", FlabebeFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).strength(0.8f).sound(SoundType.FLOWERING_AZALEA)))
    val FLABEBE_FLOWER_ORANGE = registerBlockItem("flabebe_flower_orange", FlabebeFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).strength(0.8f).sound(SoundType.FLOWERING_AZALEA)))
    val FLABEBE_FLOWER_RED = registerBlockItem("flabebe_flower_red", FlabebeFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).strength(0.8f).sound(SoundType.FLOWERING_AZALEA)))
    val FLABEBE_FLOWER_WHITE = registerBlockItem("flabebe_flower_white", FlabebeFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).strength(0.8f).sound(SoundType.FLOWERING_AZALEA)))
    val FLABEBE_FLOWER_YELLOW = registerBlockItem("flabebe_flower_yellow", FlabebeFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).strength(0.8f).sound(SoundType.FLOWERING_AZALEA)))

    /**
     * PokeBall Chests
     */
    val POKEBALL_CHEST = registerChestBlockItem("pokeball_chest", GenericChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST), 9, 4, "pokeball_chest"))
    val GREATBALL_CHEST = registerChestBlockItem("greatball_chest", GenericChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST), 9, 5, "greatball_chest"))
    val ULTRABALL_CHEST = registerChestBlockItem("ultraball_chest", GenericChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST), 9, 6, "ultraball_chest"))
    val MASTERBALL_CHEST = registerChestBlockItem("masterball_chest", GenericChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST), 12, 8, "masterball_chest"))

    fun <T : Block> registerBlockItem(
        name: String,
        blockSupplier: T,
        function: (Block, Item.Properties) -> Item = ::BlockItem,
        register: ItemPlatformRegistry = BLOCK_ITEMS
    ): T {
        val block = BLOCKS.create(name.generationsResource(), blockSupplier)
        register(name, { properties -> function.invoke(block, properties) }, register)
        return block
    }

    fun <T : Block> registerUltraBlock(name: String, blockSupplier: T): T {
        val block = ULTRA_BLOCKS.create(name.generationsResource(), blockSupplier)
        register(name) { properties -> BlockItem(block, properties) }
        return block
    }

    private fun <T : GenericChestBlock> registerChestBlockItem(name: String, blockSupplier: T): T {
        val block = BLOCKS.create(name.generationsResource(), blockSupplier)
        register(name) { properties -> GenericChestBlockItem(block, properties) }
        return block
    }

    private fun <T : Block> registerStone(name: String, blockSupplier: T): T {
        val block = STONE.create(name.generationsResource(), blockSupplier)
        register(name) { properties -> BlockItem(block, properties) }
        return block
    }

    private fun register(name: String, itemSupplier: Function<Item.Properties, Item>) = register(name, { properties -> itemSupplier.apply(properties) }, BLOCK_ITEMS)

    private fun register(
        name: String,
        itemSupplier: (Item.Properties) -> Item,
        register: ItemPlatformRegistry
    ) = register.create(name.generationsResource(), itemSupplier.invoke(Item.Properties()))

    private fun registerUnownBlock(name: String, glyph: String? = null): UnownBlock = registerBlockItem("unown_block_$name", UnownBlock(glyph ?: name.uppercase(), STONE_PROPERTY))

    @JvmStatic
    fun init(consumer: (ResourceLocation, Block) -> Unit) {
        GenerationsCore.LOGGER.info("Registering Generations Blocks")
        BLOCKS.register(consumer)
        ULTRA_BLOCKS.register(consumer)
        STONE.register(consumer)
    }

    fun initItems(consumer: (ResourceLocation, Item) -> Unit) {
        BLOCK_ITEMS.register(consumer)
    }
}

object Properties {
    val STONE_PROPERTY = BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
}

private fun Int.rgba() = ColorRGBA(this)
