package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenericChestBlockItem;
import generations.gg.generations.core.generationscore.world.item.creativetab.GenerationsCreativeTabs;
import generations.gg.generations.core.generationscore.world.level.block.decorations.PokecenterScarletSignBlock;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericChestBlock;
import generations.gg.generations.core.generationscore.world.level.block.set.GenerationsBlockSet;
import generations.gg.generations.core.generationscore.world.level.block.set.GenerationsFullBlockSet;
import generations.gg.generations.core.generationscore.world.level.block.state.properties.GenerationsBlockSetTypes;
import generations.gg.generations.core.generationscore.world.level.levelgen.GenerationsFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class GenerationsBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<Block> ULTRA_BLOCKS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<Block> STONE = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ITEM);

    public static final BlockBehaviour.Properties ULTRA_BLOCK_SETTINGS = BlockBehaviour.Properties.copy(Blocks.GLASS).lightLevel(value -> 15);
    public static final RegistrySupplier<Block> POKEMART_SIGN = registerBlockItem("pokemart_sign", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    /**
     * PokeCenter
     */
    public static final RegistrySupplier<Block> POKECENTER_SIGN = registerBlockItem("pokecenter_sign", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> POKECENTER_SCARLET_SIGN = registerBlockItem("pokecenter_scarlet_sign", () -> new PokecenterScarletSignBlock(BlockBehaviour.Properties.of().lightLevel((state) -> 15).noLootTable()));
    public static final RegistrySupplier<DoorBlock> POKECENTER_DOOR = registerBlockItem("pokecenter_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).sound(SoundType.AMETHYST).noOcclusion(), BlockSetType.OAK));

    public static final GenerationsBlockSet POKECENTER_ROOF_SET = new GenerationsBlockSet("pokecenter_roof", BlockBehaviour.Properties.copy(Blocks.STONE).lightLevel(value -> 5));
    public static final GenerationsBlockSet POKECENTER_ROOF_2_SET = new GenerationsBlockSet("pokecenter_roof_2", BlockBehaviour.Properties.copy(Blocks.STONE).lightLevel(value -> 5));
    public static final RegistrySupplier<Block> HOUSE_FLOOR_1 = registerBlockItem("house_floor_1", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> HOUSE_FLOOR_2 = registerBlockItem("house_floor_2", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> HOUSE_FLOOR_3 = registerBlockItem("house_floor_3", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> HOUSE_FLOOR_4 = registerBlockItem("house_floor_4", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> HOUSE_FLOOR_5 = registerBlockItem("house_floor_5", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> HOUSE_FLOOR_6 = registerBlockItem("house_floor_6", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> HOUSE_FLOOR_7 = registerBlockItem("house_floor_7", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> HOUSE_FLOOR_8 = registerBlockItem("house_floor_8", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> RUINS_WALL = registerBlockItem("ruins_wall", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> DUSTY_RUINS_WALL = registerBlockItem("dusty_ruins_wall", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final GenerationsBlockSet TEMPLE_BLOCK_SET = new GenerationsBlockSet("temple_block", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet TEMPLE_BRICK_SET = new GenerationsBlockSet("temple_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet CASTLE_BLOCK_SET = new GenerationsBlockSet("castle_block", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet CRACKED_CASTLE_BLOCK_SET = new GenerationsBlockSet("cracked_castle_block", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet CASTLE_BRICK_SET = new GenerationsBlockSet("castle_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet CASTLE_BRICK_2_SET = new GenerationsBlockSet("castle_brick_2", BlockBehaviour.Properties.copy(Blocks.BRICKS));

    public static final GenerationsBlockSet GRAY_CASTLE_BRICK_SET = new GenerationsBlockSet("gray_castle_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet GRAY_CASTLE_BRICK_2_SET = new GenerationsBlockSet("gray_castle_brick_2", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet WHITE_CASTLE_BRICK_SET = new GenerationsBlockSet("white_castle_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet WHITE_CASTLE_BRICK_2_SET = new GenerationsBlockSet("white_castle_brick_2", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet CASTLE_WALL_SET = new GenerationsBlockSet("castle_wall", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet CASTLE_WALL_2_SET = new GenerationsBlockSet("castle_wall_2", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet CASTLE_WALL_3_SET = new GenerationsBlockSet("castle_wall_3", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet CASTLE_WALL_4_SET = new GenerationsBlockSet("castle_wall_4", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet CASTLE_FLOOR_SET = new GenerationsBlockSet("castle_floor", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet ICE_BRICK_SET = new GenerationsBlockSet("ice_brick", BlockBehaviour.Properties.copy(Blocks.ICE));
    public static final RegistrySupplier<Block> ICE_PILLAR = registerBlockItem("ice_pillar", () -> new Block(BlockBehaviour.Properties.copy(Blocks.ICE)));
    public static final RegistrySupplier<Block> BROKEN_ICE_PILLAR = registerBlockItem("ice_pillar_broken", () -> new Block(BlockBehaviour.Properties.copy(Blocks.ICE)));

    public static final GenerationsBlockSet ICE_PILLAR_SIDE_SET = new GenerationsBlockSet("ice_pillar_side", BlockBehaviour.Properties.copy(Blocks.ICE));
    public static final GenerationsBlockSet ICE_PILLAR_TOP_SET = new GenerationsBlockSet("ice_pillar_top", BlockBehaviour.Properties.copy(Blocks.ICE));
    public static final GenerationsBlockSet ROCK_SET = new GenerationsBlockSet("rock", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet CAVE_ROCK_SET = new GenerationsBlockSet("cave_rock", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet CAVE_ROCK_FLOOR_SET = new GenerationsBlockSet("cave_rock_floor", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet GRAY_CAVE_ROCK_FLOOR_SET = new GenerationsBlockSet("gray_cave_rock_floor", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet ICE_CAVE_ROCK_FLOOR_SET = new GenerationsBlockSet("ice_cave_rock_floor", BlockBehaviour.Properties.copy(Blocks.ICE));
    public static final GenerationsBlockSet BRIDGE_BLOCK_SET = new GenerationsBlockSet("bridge_block", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS));


    public static final RegistrySupplier<Block> CASTLE_PILLAR = registerBlockItem("castle_pillar", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> BROKEN_CASTLE_PILLAR = registerBlockItem("broken_castle_pillar", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> PRISMARINE_PILLAR = registerBlockItem("prismarine_pillar", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> BROKEN_PRISMARINE_PILLAR = registerBlockItem("prismarine_pillar_broken", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> DARK_PRISMARINE_PILLAR = registerBlockItem("dark_prismarine_pillar", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> BROKEN_DARK_PRISMARINE_PILLAR = registerBlockItem("dark_prismarine_pillar_broken", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> HAUNTED_PILLAR = registerBlockItem("haunted_pillar", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> BROKEN_HAUNTED_PILLAR = registerBlockItem("haunted_pillar_broken", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistrySupplier<Block> DAWN_STONE_BLOCK = registerBlockItem("dawn_stone_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> DUSK_STONE_BLOCK = registerBlockItem("dusk_stone_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> FIRE_STONE_BLOCK = registerBlockItem("fire_stone_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> ICE_STONE_BLOCK = registerBlockItem("ice_stone_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> LEAF_STONE_BLOCK = registerBlockItem("leaf_stone_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOON_STONE_BLOCK = registerBlockItem("moon_stone_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> SHINY_STONE_BLOCK = registerBlockItem("shiny_stone_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> SUN_STONE_BLOCK = registerBlockItem("sun_stone_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> THUNDER_STONE_BLOCK = registerBlockItem("thunder_stone_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> WATER_STONE_BLOCK = registerBlockItem("water_stone_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> CRATE = registerBlockItem("crate", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    /**
     * Compressed Polished Andesite Pallet
     */
    public static final GenerationsBlockSet COMPRESSED_POLISHED_ANDESITE_SET = new GenerationsBlockSet("compressed_polished_andesite", BlockBehaviour.Properties.copy(Blocks.POLISHED_ANDESITE));
    public static final GenerationsBlockSet COMPRESSED_POLISHED_DIORITE_SET = new GenerationsBlockSet("compressed_polished_diorite", BlockBehaviour.Properties.copy(Blocks.POLISHED_DIORITE));
    public static final GenerationsBlockSet COMPRESSED_POLISHED_GRANITE_SET = new GenerationsBlockSet("compressed_polished_granite", BlockBehaviour.Properties.copy(Blocks.POLISHED_GRANITE));
    public static final GenerationsBlockSet COMPRESSED_POLISHED_DEEPSLATE_SET = new GenerationsBlockSet("compressed_polished_deepslate", BlockBehaviour.Properties.copy(Blocks.POLISHED_DEEPSLATE));

    public static final RegistrySupplier<Block> WARNING_BLOCK = registerBlockItem("warning_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BEDROCK)));


    //Cursed Pumpkin Based Blocks
    public static final RegistrySupplier<PumpkinBlock> CURSED_PUMPKIN = registerBlockItem("cursed_pumpkin", () -> new CursedPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.PUMPKIN)));
    public static final RegistrySupplier<CursedCarvedPumpkinBlock> CURSED_CARVED_PUMPKIN = registerBlockItem("cursed_carved_pumpkin", () -> new CursedCarvedPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.CARVED_PUMPKIN)));
    public static final RegistrySupplier<CursedCarvedPumpkinBlock> CURSED_JACK_O_LANTERN = registerBlockItem("cursed_jack_o_lantern", () -> new CursedCarvedPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.JACK_O_LANTERN)));

    public static final GenerationsBlockSet COBBLE_RUINS_1_SET = new GenerationsBlockSet("cobble_ruins_1", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet COBBLE_RUINS_2_SET = new GenerationsBlockSet("cobble_ruins_2", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet COBBLE_RUINS_3_SET = new GenerationsBlockSet("cobble_ruins_3", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet COBBLE_RUINS_4_SET = new GenerationsBlockSet("cobble_ruins_4", BlockBehaviour.Properties.copy(Blocks.STONE));

    /**
     * Ultra Blocks
     */

    public static final RegistrySupplier<Block> ULTRA_WHITE = registerUltraBlock("ultra_white", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_WHITE_STAIRS = registerUltraBlock("ultra_white_stairs", () -> new StairBlock(ULTRA_WHITE.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_WHITE_SLAB = registerUltraBlock("ultra_white_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_WHITE_WALL = registerUltraBlock("ultra_white_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_WHITE_BUTTON = registerUltraBlock("ultra_white_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_WHITE_PRESSURE_PLATE = registerUltraBlock("ultra_white_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA));

    public static final RegistrySupplier<Block> ULTRA_LIGHT_GRAY = registerUltraBlock("ultra_light_gray", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_LIGHT_GRAY_STAIRS = registerUltraBlock("ultra_light_gray_stairs", () -> new StairBlock(ULTRA_LIGHT_GRAY.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_LIGHT_GRAY_SLAB = registerUltraBlock("ultra_light_gray_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_LIGHT_GRAY_WALL = registerUltraBlock("ultra_light_gray_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_LIGHT_GRAY_BUTTON = registerUltraBlock("ultra_light_gray_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_LIGHT_GRAY_PRESSURE_PLATE = registerUltraBlock("ultra_light_gray_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA));

    public static final RegistrySupplier<Block> ULTRA_GRAY = registerUltraBlock("ultra_gray", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_GRAY_STAIRS = registerUltraBlock("ultra_gray_stairs", () -> new StairBlock(ULTRA_GRAY.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_GRAY_SLAB = registerUltraBlock("ultra_gray_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_GRAY_WALL = registerUltraBlock("ultra_gray_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_GRAY_BUTTON = registerUltraBlock("ultra_gray_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_GRAY_PRESSURE_PLATE = registerUltraBlock("ultra_gray_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA));

    public static final RegistrySupplier<Block> ULTRA_BLACK = registerUltraBlock("ultra_black", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_BLACK_STAIRS = registerUltraBlock("ultra_black_stairs", () -> new StairBlock(ULTRA_BLACK.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_BLACK_SLAB = registerUltraBlock("ultra_black_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_BLACK_WALL = registerUltraBlock("ultra_black_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_BLACK_BUTTON = registerUltraBlock("ultra_black_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_BLACK_PRESSURE_PLATE = registerUltraBlock("ultra_black_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA));

    public static final RegistrySupplier<Block> ULTRA_BROWN = registerUltraBlock("ultra_brown", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_BROWN_STAIRS = registerUltraBlock("ultra_brown_stairs", () -> new StairBlock(ULTRA_BROWN.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_BROWN_SLAB = registerUltraBlock("ultra_brown_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_BROWN_WALL = registerUltraBlock("ultra_brown_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_BROWN_BUTTON = registerUltraBlock("ultra_brown_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_BROWN_PRESSURE_PLATE = registerUltraBlock("ultra_brown_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA));

    public static final RegistrySupplier<Block> ULTRA_RED = registerUltraBlock("ultra_red", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_RED_STAIRS = registerUltraBlock("ultra_red_stairs", () -> new StairBlock(ULTRA_RED.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_RED_SLAB = registerUltraBlock("ultra_red_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_RED_WALL = registerUltraBlock("ultra_red_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_RED_BUTTON = registerUltraBlock("ultra_red_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_RED_PRESSURE_PLATE = registerUltraBlock("ultra_red_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA));

    public static final RegistrySupplier<Block> ULTRA_ORANGE = registerUltraBlock("ultra_orange", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_ORANGE_STAIRS = registerUltraBlock("ultra_orange_stairs", () -> new StairBlock(ULTRA_ORANGE.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_ORANGE_SLAB = registerUltraBlock("ultra_orange_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_ORANGE_WALL = registerUltraBlock("ultra_orange_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_ORANGE_BUTTON = registerUltraBlock("ultra_orange_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_ORANGE_PRESSURE_PLATE = registerUltraBlock("ultra_orange_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA));

    public static final RegistrySupplier<Block> ULTRA_YELLOW = registerUltraBlock("ultra_yellow", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_YELLOW_STAIRS = registerUltraBlock("ultra_yellow_stairs", () -> new StairBlock(ULTRA_YELLOW.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_YELLOW_SLAB = registerUltraBlock("ultra_yellow_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_YELLOW_WALL = registerUltraBlock("ultra_yellow_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_YELLOW_BUTTON = registerUltraBlock("ultra_yellow_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_YELLOW_PRESSURE_PLATE = registerUltraBlock("ultra_yellow_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA));

    public static final RegistrySupplier<Block> ULTRA_LIME = registerUltraBlock("ultra_lime", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_LIME_STAIRS = registerUltraBlock("ultra_lime_stairs", () -> new StairBlock(ULTRA_LIME.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_LIME_SLAB = registerUltraBlock("ultra_lime_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_LIME_WALL = registerUltraBlock("ultra_lime_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_LIME_BUTTON = registerUltraBlock("ultra_lime_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_LIME_PRESSURE_PLATE = registerUltraBlock("ultra_lime_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA));

    public static final RegistrySupplier<Block> ULTRA_GREEN = registerUltraBlock("ultra_green", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_GREEN_STAIRS = registerUltraBlock("ultra_green_stairs", () -> new StairBlock(ULTRA_GREEN.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_GREEN_SLAB = registerUltraBlock("ultra_green_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_GREEN_WALL = registerUltraBlock("ultra_green_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_GREEN_BUTTON = registerUltraBlock("ultra_green_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_GREEN_PRESSURE_PLATE = registerUltraBlock("ultra_green_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA));

    public static final RegistrySupplier<Block> ULTRA_CYAN = registerUltraBlock("ultra_cyan", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_CYAN_STAIRS = registerUltraBlock("ultra_cyan_stairs", () -> new StairBlock(ULTRA_CYAN.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_CYAN_SLAB = registerUltraBlock("ultra_cyan_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_CYAN_WALL = registerUltraBlock("ultra_cyan_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_CYAN_BUTTON = registerUltraBlock("ultra_cyan_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_CYAN_PRESSURE_PLATE = registerUltraBlock("ultra_cyan_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA));

    public static final RegistrySupplier<Block> ULTRA_LIGHT_BLUE = registerUltraBlock("ultra_light_blue", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_LIGHT_BLUE_STAIRS = registerUltraBlock("ultra_light_blue_stairs", () -> new StairBlock(ULTRA_LIGHT_BLUE.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_LIGHT_BLUE_SLAB = registerUltraBlock("ultra_light_blue_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_LIGHT_BLUE_WALL = registerUltraBlock("ultra_light_blue_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_LIGHT_BLUE_BUTTON = registerUltraBlock("ultra_light_blue_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_LIGHT_BLUE_PRESSURE_PLATE = registerUltraBlock("ultra_light_blue_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA));

    public static final RegistrySupplier<Block> ULTRA_BLUE = registerUltraBlock("ultra_blue", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_BLUE_STAIRS = registerUltraBlock("ultra_blue_stairs", () -> new StairBlock(ULTRA_BLUE.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_BLUE_SLAB = registerUltraBlock("ultra_blue_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_BLUE_WALL = registerUltraBlock("ultra_blue_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_BLUE_BUTTON = registerUltraBlock("ultra_blue_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_BLUE_PRESSURE_PLATE = registerUltraBlock("ultra_blue_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA));

    public static final RegistrySupplier<Block> ULTRA_PURPLE = registerUltraBlock("ultra_purple", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_PURPLE_STAIRS = registerUltraBlock("ultra_purple_stairs", () -> new StairBlock(ULTRA_PURPLE.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_PURPLE_SLAB = registerUltraBlock("ultra_purple_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_PURPLE_WALL = registerUltraBlock("ultra_purple_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_PURPLE_BUTTON = registerUltraBlock("ultra_purple_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_PURPLE_PRESSURE_PLATE = registerUltraBlock("ultra_purple_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA));

    public static final RegistrySupplier<Block> ULTRA_MAGENTA = registerUltraBlock("ultra_magenta", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_MAGENTA_STAIRS = registerUltraBlock("ultra_magenta_stairs", () -> new StairBlock(ULTRA_MAGENTA.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_MAGENTA_SLAB = registerUltraBlock("ultra_magenta_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_MAGENTA_WALL = registerUltraBlock("ultra_magenta_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_MAGENTA_BUTTON = registerUltraBlock("ultra_magenta_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_MAGENTA_PRESSURE_PLATE = registerUltraBlock("ultra_magenta_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA));
    public static final RegistrySupplier<Block> ULTRA_PINK = registerUltraBlock("ultra_pink", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_PINK_STAIRS = registerUltraBlock("ultra_pink_stairs", () -> new StairBlock(ULTRA_PINK.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_PINK_SLAB = registerUltraBlock("ultra_pink_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_PINK_WALL = registerUltraBlock("ultra_pink_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_PINK_BUTTON = registerUltraBlock("ultra_pink_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_PINK_PRESSURE_PLATE = registerUltraBlock("ultra_pink_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA));


    public static final RegistrySupplier<Block> ULTRA_SAND = registerBlockItem("ultra_sand", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));


    /**
     * Marble block sets
     */

    public static final GenerationsFullBlockSet WHITE_MARBLE_SET = new GenerationsFullBlockSet("white_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet LIGHT_GRAY_MARBLE_SET = new GenerationsFullBlockSet("light_gray_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet GRAY_MARBLE_SET = new GenerationsFullBlockSet("gray_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet BLACK_MARBLE_SET = new GenerationsFullBlockSet("black_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet BROWN_MARBLE_SET = new GenerationsFullBlockSet("brown_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet RED_MARBLE_SET = new GenerationsFullBlockSet("red_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet ORANGE_MARBLE_SET = new GenerationsFullBlockSet("orange_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet YELLOW_MARBLE_SET = new GenerationsFullBlockSet("yellow_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet LIME_MARBLE_SET = new GenerationsFullBlockSet("lime_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet GREEN_MARBLE_SET = new GenerationsFullBlockSet("green_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet CYAN_MARBLE_SET = new GenerationsFullBlockSet("cyan_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet POWDER_BLUE_MARBLE_SET = new GenerationsFullBlockSet("powder_blue_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet LIGHT_BLUE_MARBLE_SET = new GenerationsFullBlockSet("light_blue_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet BLUE_MARBLE_SET = new GenerationsFullBlockSet("blue_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet PURPLE_MARBLE_SET = new GenerationsFullBlockSet("purple_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet MAGENTA_MARBLE_SET = new GenerationsFullBlockSet("magenta_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet PINK_MARBLE_SET = new GenerationsFullBlockSet("pink_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);

    /**
     * Unown Blocks
     */
    public static final RegistrySupplier<Block> UNOWN_BLOCK_A = registerBlockItem("unown_block_a", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_B = registerBlockItem("unown_block_b", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_C = registerBlockItem("unown_block_c", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_D = registerBlockItem("unown_block_d", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_E = registerBlockItem("unown_block_e", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_F = registerBlockItem("unown_block_f", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_G = registerBlockItem("unown_block_g", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_H = registerBlockItem("unown_block_h", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_I = registerBlockItem("unown_block_i", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_J = registerBlockItem("unown_block_j", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_K = registerBlockItem("unown_block_k", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_L = registerBlockItem("unown_block_l", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_M = registerBlockItem("unown_block_m", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_N = registerBlockItem("unown_block_n", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_O = registerBlockItem("unown_block_o", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_P = registerBlockItem("unown_block_p", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_Q = registerBlockItem("unown_block_q", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_R = registerBlockItem("unown_block_r", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_S = registerBlockItem("unown_block_s", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_T = registerBlockItem("unown_block_t", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_U = registerBlockItem("unown_block_u", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_V = registerBlockItem("unown_block_v", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_W = registerBlockItem("unown_block_w", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_X = registerBlockItem("unown_block_x", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_Y = registerBlockItem("unown_block_y", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_Z = registerBlockItem("unown_block_z", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_BLANK = registerBlockItem("unown_block_blank", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_EXCLAMATION_MARK = registerBlockItem("unown_block_exclamation_mark", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_QUESTION_MARK = registerBlockItem("unown_block_question_mark", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    /**
     * Full ChargeStone Pallet
     */
    public static final RegistrySupplier<Block> CHARGE_STONE = registerStone("charge_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> CHARGE_STONE_STAIRS = registerStone("charge_stone_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(CHARGE_STONE.get())));
    public static final RegistrySupplier<SlabBlock> CHARGE_STONE_SLAB = registerStone("charge_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CHARGE_STONE.get())));
    public static final RegistrySupplier<WallBlock> CHARGE_STONE_WALL = registerStone("charge_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CHARGE_STONE.get())));
    public static final RegistrySupplier<PressurePlateBlock> CHARGE_STONE_PRESSURE_PLATE = registerStone("charge_stone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(CHARGE_STONE.get()), GenerationsBlockSetTypes.CHARGE_STONE));
    public static final RegistrySupplier<ButtonBlock> CHARGE_STONE_BUTTON = registerStone("charge_stone_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(CHARGE_STONE.get()), GenerationsBlockSetTypes.CHARGE_STONE, 20, false));

    //ChargeCobbleStone
    public static final RegistrySupplier<Block> CHARGE_COBBLESTONE = registerStone("charge_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistrySupplier<StairBlock> CHARGE_COBBLESTONE_STAIRS = registerStone("charge_cobblestone_stairs", () -> new StairBlock(Blocks.COBBLESTONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<SlabBlock> CHARGE_COBBLESTONE_SLAB = registerStone("charge_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<WallBlock> CHARGE_COBBLESTONE_WALL = registerStone("charge_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CHARGE_COBBLESTONE.get())));
    //Mossy ChargeCobbleStone
    public static final RegistrySupplier<Block> MOSSY_CHARGE_COBBLESTONE = registerStone("mossy_charge_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSSY_COBBLESTONE)));
    public static final RegistrySupplier<StairBlock> MOSSY_CHARGE_COBBLESTONE_STAIRS = registerStone("mossy_charge_cobblestone_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(MOSSY_CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<SlabBlock> MOSSY_CHARGE_COBBLESTONE_SLAB = registerStone("mossy_charge_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MOSSY_CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<WallBlock> MOSSY_CHARGE_COBBLESTONE_WALL = registerStone("mossy_charge_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MOSSY_CHARGE_COBBLESTONE.get())));
    //Smooth ChargeStone
    public static final RegistrySupplier<Block> SMOOTH_CHARGE_STONE = registerStone("smooth_charge_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
    public static final RegistrySupplier<SlabBlock> SMOOTH_CHARGE_STONE_SLAB = registerStone("smooth_charge_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE_SLAB)));
    //ChargeStoneBricks
    public static final RegistrySupplier<Block> CHARGE_STONE_BRICKS = registerStone("charge_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistrySupplier<Block> CRACKED_CHARGE_STONE_BRICKS = registerStone("cracked_charge_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS)));
    public static final RegistrySupplier<StairBlock> CHARGE_STONE_BRICK_STAIRS = registerStone("charge_stone_brick_stairs", () -> new StairBlock(Blocks.STONE_BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(CHARGE_STONE_BRICKS.get())));
    public static final RegistrySupplier<SlabBlock> CHARGE_STONE_BRICK_SLAB = registerStone("charge_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CHARGE_STONE_BRICKS.get())));
    public static final RegistrySupplier<WallBlock> CHARGE_STONE_BRICK_WALL = registerStone("charge_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CHARGE_STONE_BRICKS.get())));
    public static final RegistrySupplier<Block> CHISELED_CHARGE_STONE_BRICKS = registerStone("chiseled_charge_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS)));

    public static final RegistrySupplier<Block> MOSSY_CHARGE_STONE_BRICKS = registerStone("mossy_charge_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS)));
    public static final RegistrySupplier<StairBlock> MOSSY_CHARGE_STONE_BRICK_STAIRS = registerStone("mossy_charge_stone_brick_stairs", () -> new StairBlock(Blocks.MOSSY_STONE_BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(MOSSY_CHARGE_STONE_BRICKS.get())));
    public static final RegistrySupplier<SlabBlock> MOSSY_CHARGE_STONE_BRICK_SLAB = registerStone("mossy_charge_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MOSSY_CHARGE_STONE_BRICKS.get())));
    public static final RegistrySupplier<WallBlock> MOSSY_CHARGE_STONE_BRICK_WALL = registerStone("mossy_charge_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MOSSY_CHARGE_STONE_BRICKS.get())));
    //Infested blocks
    public static final RegistrySupplier<InfestedBlock> INFESTED_CHARGE_STONE = registerStone("infested_charge_stone", () -> new InfestedBlock(CHARGE_STONE.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CHARGE_COBBLESTONE = registerStone("infested_charge_cobblestone", () -> new InfestedBlock(CHARGE_COBBLESTONE.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_COBBLESTONE)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CHARGE_STONE_BRICKS = registerStone("infested_charge_stone_bricks", () -> new InfestedBlock(CHARGE_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_MOSSY_CHARGE_STONE_BRICKS = registerStone("infested_mossy_charge_stone_bricks", () -> new InfestedBlock(MOSSY_CHARGE_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_MOSSY_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CRACKED_CHARGE_STONE_BRICKS = registerStone("infested_cracked_charge_stone_bricks", () -> new InfestedBlock(CRACKED_CHARGE_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_CRACKED_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CHISELED_CHARGE_STONE_BRICKS = registerStone("infested_chiseled_charge_stone_bricks", () -> new InfestedBlock(CHISELED_CHARGE_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_CHISELED_STONE_BRICKS)));
    //Full ChargeStone Pallet ^

    public static final RegistrySupplier<Block> BRIGHT_CHARGE_COBBLESTONE = registerStone("bright_charge_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).lightLevel(lightLevel -> 30)));
    public static final RegistrySupplier<SlabBlock> BRIGHT_CHARGE_COBBLESTONE_SLAB = registerStone("bright_charge_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BRIGHT_CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<StairBlock> BRIGHT_CHARGE_COBBLESTONE_STAIRS = registerStone("bright_charge_cobblestone_stairs", () -> new StairBlock(Blocks.COBBLESTONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(BRIGHT_CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<WallBlock> BRIGHT_CHARGE_COBBLESTONE_WALL = registerStone("bright_charge_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(BRIGHT_CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<Block> GLOWING_CHARGE_COBBLESTONE = registerStone("glowing_charge_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).lightLevel(lightLevel -> 15)));
    public static final RegistrySupplier<SlabBlock> GLOWING_CHARGE_COBBLESTONE_SLAB = registerStone("glowing_charge_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GLOWING_CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<StairBlock> GLOWING_CHARGE_COBBLESTONE_STAIRS = registerStone("glowing_charge_cobblestone_stairs", () -> new StairBlock(Blocks.COBBLESTONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(GLOWING_CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<WallBlock> GLOWING_CHARGE_COBBLESTONE_WALL = registerStone("glowing_charge_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GLOWING_CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<PointedChargeDripstoneBlock> POINTED_CHARGE_DRIPSTONE = registerStone("pointed_charge_dripstone", () -> new PointedChargeDripstoneBlock(BlockBehaviour.Properties.copy(Blocks.POINTED_DRIPSTONE)));
    public static final RegistrySupplier<Block> CHARGE_DRIPSTONE_BLOCK = registerStone("charge_dripstone_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DRIPSTONE_BLOCK)));

    /**
     * Volcanic Stone Pallet
     */
    public static final RegistrySupplier<Block> VOLCANIC_STONE = registerStone("volcanic_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> VOLCANIC_STONE_STAIRS = registerStone("volcanic_stone_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(VOLCANIC_STONE.get())));
    public static final RegistrySupplier<SlabBlock> VOLCANIC_STONE_SLAB = registerStone("volcanic_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(VOLCANIC_STONE.get())));
    public static final RegistrySupplier<WallBlock> VOLCANIC_STONE_WALL = registerStone("volcanic_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(VOLCANIC_STONE.get())));
    public static final RegistrySupplier<ButtonBlock> VOLCANIC_STONE_BUTTON = registerStone("volcanic_stone_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(VOLCANIC_STONE.get()), GenerationsBlockSetTypes.VOLCANIC_STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> VOLCANIC_STONE_PRESSURE_PLATE = registerStone("volcanic_stone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(VOLCANIC_STONE.get()), GenerationsBlockSetTypes.VOLCANIC_STONE));

    //Volcanic CobbleStone
    public static final RegistrySupplier<Block> VOLCANIC_COBBLESTONE = registerStone("volcanic_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistrySupplier<StairBlock> VOLCANIC_COBBLESTONE_STAIRS = registerStone("volcanic_cobblestone_stairs", () -> new StairBlock(Blocks.COBBLESTONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(VOLCANIC_COBBLESTONE.get())));
    public static final RegistrySupplier<SlabBlock> VOLCANIC_COBBLESTONE_SLAB = registerStone("volcanic_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(VOLCANIC_COBBLESTONE.get())));
    public static final RegistrySupplier<WallBlock> VOLCANIC_COBBLESTONE_WALL = registerStone("volcanic_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(VOLCANIC_COBBLESTONE.get())));

    //Mossy Volcanic CobbleStone
    public static final RegistrySupplier<Block> MOSSY_VOLCANIC_COBBLESTONE = registerStone("mossy_volcanic_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(VOLCANIC_COBBLESTONE.get())));
    public static final RegistrySupplier<StairBlock> MOSSY_VOLCANIC_COBBLESTONE_STAIRS = registerStone("mossy_volcanic_cobblestone_stairs", () -> new StairBlock(Blocks.COBBLESTONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(MOSSY_VOLCANIC_COBBLESTONE.get())));
    public static final RegistrySupplier<SlabBlock> MOSSY_VOLCANIC_COBBLESTONE_SLAB = registerStone("mossy_volcanic_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MOSSY_VOLCANIC_COBBLESTONE.get())));
    public static final RegistrySupplier<WallBlock> MOSSY_VOLCANIC_COBBLESTONE_WALL = registerStone("mossy_volcanic_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MOSSY_VOLCANIC_COBBLESTONE.get())));

    //Smooth Volcanic Stone
    public static final RegistrySupplier<Block> SMOOTH_VOLCANIC_STONE = registerStone("smooth_volcanic_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
    public static final RegistrySupplier<SlabBlock> SMOOTH_VOLCANIC_STONE_SLAB = registerStone("smooth_volcanic_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SMOOTH_VOLCANIC_STONE.get())));

    //Volcanic Stone Bricks
    public static final RegistrySupplier<Block> VOLCANIC_STONE_BRICKS = registerStone("volcanic_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistrySupplier<Block> CRACKED_VOLCANIC_STONE_BRICKS = registerStone("cracked_volcanic_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS)));
    public static final RegistrySupplier<StairBlock> VOLCANIC_STONE_BRICK_STAIRS = registerStone("volcanic_stone_brick_stairs", () -> new StairBlock(Blocks.STONE_BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(VOLCANIC_STONE_BRICKS.get())));
    public static final RegistrySupplier<SlabBlock> VOLCANIC_STONE_BRICK_SLAB = registerStone("volcanic_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(VOLCANIC_STONE_BRICKS.get())));
    public static final RegistrySupplier<WallBlock> VOLCANIC_STONE_BRICK_WALL = registerStone("volcanic_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(VOLCANIC_STONE_BRICKS.get())));
    public static final RegistrySupplier<Block> CHISELED_VOLCANIC_STONE_BRICKS = registerStone("chiseled_volcanic_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS)));

    //Mossy Volcanic Stone Bricks
    public static final RegistrySupplier<Block> MOSSY_VOLCANIC_STONE_BRICKS = registerStone("mossy_volcanic_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS)));
    public static final RegistrySupplier<StairBlock> MOSSY_VOLCANIC_STONE_BRICK_STAIRS = registerStone("mossy_volcanic_stone_brick_stairs", () -> new StairBlock(Blocks.STONE_BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(MOSSY_VOLCANIC_STONE_BRICKS.get())));
    public static final RegistrySupplier<SlabBlock> MOSSY_VOLCANIC_STONE_BRICK_SLAB = registerStone("mossy_volcanic_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MOSSY_VOLCANIC_STONE_BRICKS.get())));
    public static final RegistrySupplier<WallBlock> MOSSY_VOLCANIC_STONE_BRICK_WALL = registerStone("mossy_volcanic_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MOSSY_VOLCANIC_STONE_BRICKS.get())));
    //Infested Volcanic Stone Bricks
    public static final RegistrySupplier<InfestedBlock> INFESTED_VOLCANIC_STONE = registerStone("infested_volcanic_stone", () -> new InfestedBlock(VOLCANIC_STONE.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_VOLCANIC_COBBLESTONE = registerStone("infested_volcanic_cobblestone", () -> new InfestedBlock(VOLCANIC_COBBLESTONE.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_COBBLESTONE)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_VOLCANIC_STONE_BRICKS = registerStone("infested_volcanic_stone_bricks", () -> new InfestedBlock(VOLCANIC_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_MOSSY_VOLCANIC_STONE_BRICKS = registerStone("infested_mossy_volcanic_stone_bricks", () -> new InfestedBlock(MOSSY_VOLCANIC_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CRACKED_VOLCANIC_STONE_BRICKS = registerStone("infested_cracked_volcanic_stone_bricks", () -> new InfestedBlock(CRACKED_VOLCANIC_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CHISELED_VOLCANIC_STONE_BRICKS = registerStone("infested_chiseled_volcanic_stone_bricks", () -> new InfestedBlock(CHISELED_VOLCANIC_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE_BRICKS)));
    //Full Volcanic Stone Pallet^

    public static final RegistrySupplier<Block> VOLCANIC_FIRESTONE = registerBlockItem("volcanic_firestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistrySupplier<Block> VOLCANIC_ROCK = registerBlockItem("volcanic_rock", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<SlabBlock> VOLCANIC_ROCK_SLAB = registerBlockItem("volcanic_rock_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(VOLCANIC_ROCK.get())));
    public static final RegistrySupplier<StairBlock> VOLCANIC_ROCK_STAIRS = registerBlockItem("volcanic_rock_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(VOLCANIC_ROCK.get())));
    public static final RegistrySupplier<WallBlock> VOLCANIC_ROCK_WALL = registerBlockItem("volcanic_rock_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(VOLCANIC_ROCK.get())));

    /**
     * Battle Subway Blocks
     */

    public static final GenerationsBlockSet SUBWAY_FLOOR_CROSS_SET = new GenerationsBlockSet("subway_floor_cross", BlockBehaviour.Properties.copy(Blocks.COBBLESTONE));
    public static final GenerationsBlockSet SUBWAY_FLOOR_FULL_CROSS_SET = new GenerationsBlockSet("subway_floor_full_cross", BlockBehaviour.Properties.copy(Blocks.COBBLESTONE));
    public static final GenerationsBlockSet SUBWAY_FLOOR_PATH_SET = new GenerationsBlockSet("subway_floor_path", BlockBehaviour.Properties.copy(Blocks.COBBLESTONE));
    public static final GenerationsBlockSet SUBWAY_FLOOR_PLAIN_SET = new GenerationsBlockSet("subway_floor_plain", BlockBehaviour.Properties.copy(Blocks.COBBLESTONE));
    //public static final GenerationsBlockSet SUBWAY_WALL_SET = new GenerationsBlockSet("subway_wall", BlockBehaviour.Properties.copy(Blocks.COBBLESTONE));
    //public static final GenerationsBlockSet SUBWAY_WALL_2_SET = new GenerationsBlockSet("subway_wall_2", BlockBehaviour.Properties.copy(Blocks.COBBLESTONE));
    public static final RegistrySupplier<Block> SUBWAY_WALL = registerBlockItem("subway_wall", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistrySupplier<Block> SUBWAY_WALL_2 = registerBlockItem("subway_wall_2", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistrySupplier<SlabBlock> SUBWAY_WALL_SLAB = registerBlockItem("subway_wall_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SUBWAY_WALL.get())));
    public static final RegistrySupplier<SlabBlock> SUBWAY_WALL_2_SLAB = registerBlockItem("subway_wall_2_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SUBWAY_WALL_2.get())));
    public static final RegistrySupplier<StairBlock> SUBWAY_WALL_STAIRS = registerBlockItem("subway_wall_stairs", () -> new StairBlock(SUBWAY_WALL.get().defaultBlockState(), BlockBehaviour.Properties.copy(SUBWAY_WALL.get())));
    public static final RegistrySupplier<StairBlock> SUBWAY_WALL_2_STAIRS = registerBlockItem("subway_wall_2_stairs", () -> new StairBlock(SUBWAY_WALL_2.get().defaultBlockState(), BlockBehaviour.Properties.copy(SUBWAY_WALL_2.get())));

    /**
     * Golden Temple (Pokelantis)
     */
    public static final RegistrySupplier<Block> GOLDEN_TEMPLE_SAND = registerBlockItem("golden_temple_sand", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> GOLDEN_TEMPLE_SANDSTONE = registerBlockItem("golden_temple_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistrySupplier<StairBlock> GOLDEN_TEMPLE_SANDSTONE_STAIRS = registerBlockItem("golden_temple_sandstone_stairs", () -> new StairBlock(Blocks.SANDSTONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.SANDSTONE_STAIRS)));
    public static final RegistrySupplier<SlabBlock> GOLDEN_TEMPLE_SANDSTONE_SLAB = registerBlockItem("golden_temple_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistrySupplier<WallBlock> GOLDEN_TEMPLE_SANDSTONE_WALL = registerBlockItem("golden_temple_sandstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistrySupplier<Block> GOLDEN_TEMPLE_CHISELED_SANDSTONE = registerBlockItem("golden_temple_chiseled_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_SANDSTONE)));
    public static final RegistrySupplier<Block> GOLDEN_TEMPLE_CUT_SANDSTONE = registerBlockItem("golden_temple_cut_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE)));
    public static final RegistrySupplier<SlabBlock> GOLDEN_TEMPLE_CUT_SANDSTONE_SLAB = registerBlockItem("golden_temple_cut_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE)));
    public static final RegistrySupplier<Block> GOLDEN_TEMPLE_SMOOTH_SANDSTONE = registerBlockItem("golden_temple_smooth_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE)));
    public static final RegistrySupplier<StairBlock> GOLDEN_TEMPLE_SMOOTH_SANDSTONE_STAIRS = registerBlockItem("golden_temple_smooth_sandstone_stairs", () -> new StairBlock(GOLDEN_TEMPLE_SMOOTH_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(GOLDEN_TEMPLE_SMOOTH_SANDSTONE.get())));
    public static final RegistrySupplier<SlabBlock> GOLDEN_TEMPLE_SMOOTH_SANDSTONE_SLAB = registerBlockItem("golden_temple_smooth_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GOLDEN_TEMPLE_SMOOTH_SANDSTONE.get())));
    public static final RegistrySupplier<WallBlock> GOLDEN_TEMPLE_SMOOTH_SANDSTONE_WALL = registerBlockItem("golden_temple_smooth_sandstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GOLDEN_TEMPLE_SMOOTH_SANDSTONE.get())));
    public static final RegistrySupplier<Block> GOLDEN_TEMPLE_PRISMARINE = registerBlockItem("golden_temple_prismarine", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistrySupplier<StairBlock> GOLDEN_TEMPLE_PRISMARINE_STAIRS = registerBlockItem("golden_temple_prismarine_stairs", () -> new StairBlock(Blocks.PRISMARINE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.PRISMARINE_STAIRS)));
    public static final RegistrySupplier<SlabBlock> GOLDEN_TEMPLE_PRISMARINE_SLAB = registerBlockItem("golden_temple_prismarine_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PRISMARINE)));
    public static final RegistrySupplier<WallBlock> GOLDEN_TEMPLE_PRISMARINE_WALL = registerBlockItem("golden_temple_prismarine_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.PRISMARINE)));
    public static final RegistrySupplier<Block> GOLDEN_TEMPLE_DARK_PRISMARINE = registerBlockItem("golden_temple_dark_prismarine", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DARK_PRISMARINE)));
    public static final RegistrySupplier<StairBlock> GOLDEN_TEMPLE_DARK_PRISMARINE_STAIRS = registerBlockItem("golden_temple_dark_prismarine_stairs", () -> new StairBlock(Blocks.DARK_PRISMARINE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.DARK_PRISMARINE_STAIRS)));
    public static final RegistrySupplier<SlabBlock> GOLDEN_TEMPLE_DARK_PRISMARINE_SLAB = registerBlockItem("golden_temple_dark_prismarine_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DARK_PRISMARINE)));
    public static final RegistrySupplier<WallBlock> GOLDEN_TEMPLE_DARK_PRISMARINE_WALL = registerBlockItem("golden_temple_dark_prismarine_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.DARK_PRISMARINE)));
    public static final RegistrySupplier<Block> GOLDEN_TEMPLE_PRISMARINE_BRICKS = registerBlockItem("golden_temple_prismarine_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.PRISMARINE_BRICKS)));
    public static final RegistrySupplier<StairBlock> GOLDEN_TEMPLE_PRISMARINE_BRICK_STAIRS = registerBlockItem("golden_temple_prismarine_brick_stairs", () -> new StairBlock(Blocks.PRISMARINE_BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.PRISMARINE_BRICK_STAIRS)));
    public static final RegistrySupplier<SlabBlock> GOLDEN_TEMPLE_PRISMARINE_BRICK_SLAB = registerBlockItem("golden_temple_prismarine_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PRISMARINE_BRICKS)));
    public static final RegistrySupplier<WallBlock> GOLDEN_TEMPLE_PRISMARINE_BRICK_WALL = registerBlockItem("golden_temple_prismarine_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.PRISMARINE_BRICKS)));

    /**
     * Floor
     */
    public static final GenerationsBlockSet FLOOR_1_SET = new GenerationsBlockSet("floor_1", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet FLOOR_2_SET = new GenerationsBlockSet("floor_2", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet FLOOR_3_SET = new GenerationsBlockSet("floor_3", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet FLOOR_4_SET = new GenerationsBlockSet("floor_4", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet MIRRORED_FLOOR_1_SET = new GenerationsBlockSet("mirrored_floor_1", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet MIRRORED_FLOOR_2_SET = new GenerationsBlockSet("mirrored_floor_2", BlockBehaviour.Properties.copy(Blocks.STONE));
    public static final GenerationsBlockSet MIRRORED_FLOOR_3_SET = new GenerationsBlockSet("mirrored_floor_3", BlockBehaviour.Properties.copy(Blocks.STONE));

    public static final RegistrySupplier<Block> ENCHANTED_OBSIDIAN = registerBlockItem("enchanted_obsidian", () -> new EnchantedObsidianBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)));
    public static final RegistrySupplier<StairBlock> ENCHANTED_OBSIDIAN_STAIRS = registerBlockItem("enchanted_obsidian_stairs", () -> new StairBlock(Blocks.OBSIDIAN.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)));
    public static final RegistrySupplier<SlabBlock> ENCHANTED_OBSIDIAN_SLAB = registerBlockItem("enchanted_obsidian_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)));
    public static final RegistrySupplier<WallBlock> ENCHANTED_OBSIDIAN_WALL = registerBlockItem("enchanted_obsidian_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)));
    public static final RegistrySupplier<ButtonBlock> ENCHANTED_OBSIDIAN_BUTTON = registerBlockItem("enchanted_obsidian_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN), GenerationsBlockSetTypes.ULTRA, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ENCHANTED_OBSIDIAN_PRESSURE_PLATE = registerBlockItem("enchanted_obsidian_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OBSIDIAN), GenerationsBlockSetTypes.ULTRA));

    /**
     * Poke Bricks
     */

    public static final GenerationsFullBlockSet WHITE_POKE_BRICK_SET = new GenerationsFullBlockSet("white_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet LIGHT_GRAY_POKE_BRICK_SET = new GenerationsFullBlockSet("light_gray_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet GRAY_POKE_BRICK_SET = new GenerationsFullBlockSet("gray_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet BLACK_POKE_BRICK_SET = new GenerationsFullBlockSet("black_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet BROWN_POKE_BRICK_SET = new GenerationsFullBlockSet("brown_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet RED_POKE_BRICK_SET = new GenerationsFullBlockSet("red_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet ORANGE_POKE_BRICK_SET = new GenerationsFullBlockSet("orange_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet YELLOW_POKE_BRICK_SET = new GenerationsFullBlockSet("yellow_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet LIME_POKE_BRICK_SET = new GenerationsFullBlockSet("lime_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet GREEN_POKE_BRICK_SET = new GenerationsFullBlockSet("green_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet CYAN_POKE_BRICK_SET = new GenerationsFullBlockSet("cyan_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet LIGHT_BLUE_POKE_BRICK_SET = new GenerationsFullBlockSet("light_blue_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet BLUE_POKE_BRICK_SET = new GenerationsFullBlockSet("blue_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet PURPLE_POKE_BRICK_SET = new GenerationsFullBlockSet("purple_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet MAGENTA_POKE_BRICK_SET = new GenerationsFullBlockSet("magenta_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet PINK_POKE_BRICK_SET = new GenerationsFullBlockSet("pink_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS), GenerationsBlockSetTypes.POKE_BRICK);
    /**
     * Ore Blocks
     */
    public static final RegistrySupplier<Block> RAW_ALUMINUM_BLOCK = registerBlockItem("raw_aluminum_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
    public static final RegistrySupplier<Block> ALUMINUM_BLOCK = registerBlockItem("aluminum_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistrySupplier<Block> SAPPHIRE_BLOCK = registerBlockItem("sapphire_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistrySupplier<StairBlock> SAPPHIRE_STAIRS = registerBlockItem("sapphire_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(SAPPHIRE_BLOCK.get())));
    public static final RegistrySupplier<SlabBlock> SAPPHIRE_SLAB = registerBlockItem("sapphire_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SAPPHIRE_BLOCK.get())));
    public static final RegistrySupplier<WallBlock> SAPPHIRE_WALL = registerBlockItem("sapphire_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SAPPHIRE_BLOCK.get())));

    public static final RegistrySupplier<Block> RUBY_BLOCK = registerBlockItem("ruby_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistrySupplier<StairBlock> RUBY_STAIRS = registerBlockItem("ruby_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(RUBY_BLOCK.get())));
    public static final RegistrySupplier<SlabBlock> RUBY_SLAB = registerBlockItem("ruby_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(RUBY_BLOCK.get())));
    public static final RegistrySupplier<WallBlock> RUBY_WALL = registerBlockItem("ruby_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(RUBY_BLOCK.get())));



    public static final RegistrySupplier<Block> CRYSTAL_BLOCK = registerBlockItem("crystal_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistrySupplier<StairBlock> CRYSTAL_STAIRS = registerBlockItem("crystal_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(CRYSTAL_BLOCK.get())));
    public static final RegistrySupplier<SlabBlock> CRYSTAL_SLAB = registerBlockItem("crystal_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CRYSTAL_BLOCK.get())));
    public static final RegistrySupplier<WallBlock> CRYSTAL_WALL = registerBlockItem("crystal_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CRYSTAL_BLOCK.get())));
    public static final RegistrySupplier<Block> CRYSTAL_LIGHT = registerBlockItem("crystal_light", () -> new Block(BlockBehaviour.Properties.copy(CRYSTAL_BLOCK.get()).lightLevel((state) -> 15)));
    public static final RegistrySupplier<Block> SILICON_BLOCK = registerBlockItem("silicon_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    /**
     * Ultra Space
     */
    public static final RegistrySupplier<Block> ULTRA_SANDSTONE = registerBlockItem("ultra_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistrySupplier<StairBlock> ULTRA_SANDSTONE_STAIRS = registerBlockItem("ultra_sandstone_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(ULTRA_SANDSTONE.get())));
    public static final RegistrySupplier<SlabBlock> ULTRA_SANDSTONE_SLAB = registerBlockItem("ultra_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ULTRA_SANDSTONE.get())));
    public static final RegistrySupplier<WallBlock> ULTRA_SANDSTONE_WALL = registerBlockItem("ultra_sandstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ULTRA_SANDSTONE.get())));
    public static final RegistrySupplier<Block> ULTRA_SMOOTH_SANDSTONE = registerBlockItem("ultra_smooth_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE)));
    public static final RegistrySupplier<StairBlock> ULTRA_SMOOTH_SANDSTONE_STAIRS = registerBlockItem("ultra_smooth_sandstone_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(ULTRA_SMOOTH_SANDSTONE.get())));
    public static final RegistrySupplier<SlabBlock> ULTRA_SMOOTH_SANDSTONE_SLAB = registerBlockItem("ultra_smooth_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ULTRA_SMOOTH_SANDSTONE.get())));
    public static final RegistrySupplier<WallBlock> ULTRA_SMOOTH_SANDSTONE_WALL = registerBlockItem("ultra_smooth_sandstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ULTRA_SMOOTH_SANDSTONE.get())));
    public static final RegistrySupplier<Block> ULTRA_CHISELED_SANDSTONE = registerBlockItem("ultra_chiseled_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_SANDSTONE)));
    public static final RegistrySupplier<Block> ULTRA_CUT_SANDSTONE = registerBlockItem("ultra_cut_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE)));
    public static final RegistrySupplier<SlabBlock> ULTRA_CUT_SANDSTONE_SLAB = registerBlockItem("ultra_cut_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ULTRA_CUT_SANDSTONE.get())));
    public static final RegistrySupplier<Block> GHOST_LANTERN = registerBlockItem("ghost_lantern", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GLASS).strength(.5f).lightLevel((lightLevel) -> 15)));
    public static final GenerationsBlockSet GHOST_BRICKS_SET = new GenerationsBlockSet("ghost_bricks", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet GHOST_OBELISK_SET = new GenerationsBlockSet("ghost_obelisk", BlockBehaviour.Properties.copy(Blocks.STONE));

    public static final RegistrySupplier<Block> GHOST_PILLAR_SIDE = registerBlockItem("ghost_pillar_side", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> GHOST_PILLAR_SIDE_STAIRS = registerBlockItem("ghost_pillar_side_stairs", () -> new StairBlock(GHOST_PILLAR_SIDE.get().defaultBlockState(), BlockBehaviour.Properties.copy(GHOST_PILLAR_SIDE.get())));
    public static final RegistrySupplier<SlabBlock> GHOST_PILLAR_SIDE_SLAB = registerBlockItem("ghost_pillar_side_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GHOST_PILLAR_SIDE.get())));
    public static final RegistrySupplier<WallBlock> GHOST_PILLAR_SIDE_WALL = registerBlockItem("ghost_pillar_side_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GHOST_PILLAR_SIDE.get())));
    public static final RegistrySupplier<Block> GHOST_PILLAR_TOP = registerBlockItem("ghost_pillar_top", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> GHOST_PILLAR_TOP_STAIRS = registerBlockItem("ghost_pillar_top_stairs", () -> new StairBlock(GHOST_PILLAR_TOP.get().defaultBlockState(), BlockBehaviour.Properties.copy(GHOST_PILLAR_TOP.get())));
    public static final RegistrySupplier<SlabBlock> GHOST_PILLAR_TOP_SLAB = registerBlockItem("ghost_pillar_top_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GHOST_PILLAR_TOP.get())));
    public static final RegistrySupplier<WallBlock> GHOST_PILLAR_TOP_WALL = registerBlockItem("ghost_pillar_top_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GHOST_PILLAR_TOP.get())));

    public static final GenerationsBlockSet MIRROR_GLASS_SET = new GenerationsBlockSet("mirror_glass", BlockBehaviour.Properties.copy(Blocks.GLASS).strength(.5f));
    public static final GenerationsBlockSet NORMAL_SANDSTONE_SET = new GenerationsBlockSet("normal_sandstone", BlockBehaviour.Properties.copy(Blocks.SANDSTONE));
    public static final GenerationsBlockSet BRIGHT_SANDSTONE_SET = new GenerationsBlockSet("bright_sandstone", BlockBehaviour.Properties.copy(Blocks.SANDSTONE).lightLevel((lightLevel) -> 15));
    public static final RegistrySupplier<Block> MACHINE_BLOCK = registerBlockItem("machine_block", () -> new MachineBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final GenerationsBlockSet OCEAN_BLOCK_SET = new GenerationsBlockSet("ocean_block", BlockBehaviour.Properties.copy(Blocks.STONE));

    public static final GenerationsBlockSet WATER_QUARTZ_SET = new GenerationsBlockSet("water_quartz", BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK));

    public static final RegistrySupplier<Block> CONCRETE_TOP = registerBlockItem("concrete_top", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));
    public static final RegistrySupplier<StairBlock> CONCRETE_TOP_STAIRS = registerBlockItem("concrete_top_stairs", () -> new StairBlock(CONCRETE_TOP.get().defaultBlockState(), BlockBehaviour.Properties.copy(CONCRETE_TOP.get())));
    public static final RegistrySupplier<SlabBlock> CONCRETE_TOP_SLAB = registerBlockItem("concrete_top_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CONCRETE_TOP.get())));
    public static final RegistrySupplier<WallBlock> CONCRETE_TOP_WALL = registerBlockItem("concrete_top_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CONCRETE_TOP.get())));
    public static final RegistrySupplier<Block> RUINS_SAND = registerBlockItem("ruins_sand", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> BURST_TURF = registerBlockItem("burst_turf", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));

    //Rich Soil
    public static final RegistrySupplier<Block> RICH_SOIL_1 = registerBlockItem("rich_soil_1", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistrySupplier<Block> RICH_SOIL_2 = registerBlockItem("rich_soil_2", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistrySupplier<Block> RICH_SOIL_3 = registerBlockItem("rich_soil_3", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistrySupplier<Block> RICH_SOIL_4 = registerBlockItem("rich_soil_4", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));

    //Bleach Stone
    public static final GenerationsBlockSet BLEACH_STONE_SET = new GenerationsBlockSet("bleach_stone", BlockBehaviour.Properties.copy(Blocks.STONE));

    //Shingles
    public static final GenerationsBlockSet WHITE_SHINGLES_SET = new GenerationsBlockSet("white_shingles", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet LIGHT_GRAY_SHINGLES_SET = new GenerationsBlockSet("light_gray_shingles", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet GRAY_SHINGLES_SET = new GenerationsBlockSet("gray_shingles", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet BLACK_SHINGLES_SET = new GenerationsBlockSet("black_shingles", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet BROWN_SHINGLES_SET = new GenerationsBlockSet("brown_shingles", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet RED_SHINGLES_SET = new GenerationsBlockSet("red_shingles", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet ORANGE_SHINGLES_SET = new GenerationsBlockSet("orange_shingles", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet YELLOW_SHINGLES_SET = new GenerationsBlockSet("yellow_shingles", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet LIME_SHINGLES_SET = new GenerationsBlockSet("lime_shingles", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet GREEN_SHINGLES_SET = new GenerationsBlockSet("green_shingles", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet CYAN_SHINGLES_SET = new GenerationsBlockSet("cyan_shingles", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet LIGHT_BLUE_SHINGLES_SET = new GenerationsBlockSet("light_blue_shingles", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet BLUE_SHINGLES_SET = new GenerationsBlockSet("blue_shingles", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet PURPLE_SHINGLES_SET = new GenerationsBlockSet("purple_shingles", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet MAGENTA_SHINGLES_SET = new GenerationsBlockSet("magenta_shingles", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet PINK_SHINGLES_SET = new GenerationsBlockSet("pink_shingles", BlockBehaviour.Properties.copy(Blocks.BRICKS));

    public static final GenerationsBlockSet COOL_STONE_SET = new GenerationsBlockSet("cool_stone", BlockBehaviour.Properties.copy(Blocks.STONE));

    /**
     * 1 Block Tall Ballonlea Mushrooms
     */
    public static final RegistrySupplier<GenerationsMushroomBlock> BALLONLEA_BLUE_MUSHROOM = registerBlockItem("ballonlea_blue_mushroom", () -> new GenerationsMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), GenerationsFeatures.BALLONLEA_BLUE_MUSHROOM));
    public static final RegistrySupplier<GenerationsMushroomBlock> BALLONLEA_GREEN_MUSHROOM = registerBlockItem("ballonlea_green_mushroom", () -> new GenerationsMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), GenerationsFeatures.BALLONLEA_GREEN_MUSHROOM));
    public static final RegistrySupplier<GenerationsMushroomBlock> BALLONLEA_PINK_MUSHROOM = registerBlockItem("ballonlea_pink_mushroom", () -> new GenerationsMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), GenerationsFeatures.BALLONLEA_PINK_MUSHROOM));
    public static final RegistrySupplier<GenerationsMushroomBlock> BALLONLEA_YELLOW_MUSHROOM = registerBlockItem("ballonlea_yellow_mushroom", () -> new GenerationsMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), GenerationsFeatures.BALLONLEA_YELLOW_MUSHROOM));

    public static final RegistrySupplier<GenerationsMushroomBlock> GROUP_BALLONLEA_BLUE_MUSHROOM = registerBlockItem("group_ballonlea_blue_mushroom", () -> new GenerationsMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), GenerationsFeatures.GROUP_BALLONLEA_BLUE_MUSHROOM));
    public static final RegistrySupplier<GenerationsMushroomBlock> GROUP_BALLONLEA_GREEN_MUSHROOM = registerBlockItem("group_ballonlea_green_mushroom", () -> new GenerationsMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), GenerationsFeatures.GROUP_BALLONLEA_GREEN_MUSHROOM));
    public static final RegistrySupplier<GenerationsMushroomBlock> GROUP_BALLONLEA_PINK_MUSHROOM = registerBlockItem("group_ballonlea_pink_mushroom", () -> new GenerationsMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), GenerationsFeatures.GROUP_BALLONLEA_PINK_MUSHROOM));
    public static final RegistrySupplier<GenerationsMushroomBlock> GROUP_BALLONLEA_YELLOW_MUSHROOM = registerBlockItem("group_ballonlea_yellow_mushroom", () -> new GenerationsMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), GenerationsFeatures.GROUP_BALLONLEA_YELLOW_MUSHROOM));

    public static final RegistrySupplier<GenerationsMushroomBlock> TALL_BALLONLEA_BLUE_MUSHROOM = registerBlockItem("tall_ballonlea_blue_mushroom", () -> new GenerationsMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), GenerationsFeatures.TALL_BALLONLEA_BLUE_MUSHROOM));
    public static final RegistrySupplier<GenerationsMushroomBlock> TALL_BALLONLEA_GREEN_MUSHROOM = registerBlockItem("tall_ballonlea_green_mushroom", () -> new GenerationsMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), GenerationsFeatures.TALL_BALLONLEA_GREEN_MUSHROOM));
    public static final RegistrySupplier<GenerationsMushroomBlock> TALL_BALLONLEA_PINK_MUSHROOM = registerBlockItem("tall_ballonlea_pink_mushroom", () -> new GenerationsMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), GenerationsFeatures.TALL_BALLONLEA_PINK_MUSHROOM));
    public static final RegistrySupplier<GenerationsMushroomBlock> TALL_BALLONLEA_YELLOW_MUSHROOM = registerBlockItem("tall_ballonlea_yellow_mushroom", () -> new GenerationsMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), GenerationsFeatures.TALL_BALLONLEA_YELLOW_MUSHROOM));

    public static final RegistrySupplier<GenerationsMushroomBlock> DOUBLE_BALLONLEA_BLUE_MUSHROOM = registerBlockItem("double_ballonlea_blue_mushroom", () -> new GenerationsMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), GenerationsFeatures.DOUBLE_BALLONLEA_BLUE_MUSHROOM));
    public static final RegistrySupplier<GenerationsMushroomBlock> DOUBLE_BALLONLEA_GREEN_MUSHROOM = registerBlockItem("double_ballonlea_green_mushroom", () -> new GenerationsMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), GenerationsFeatures.DOUBLE_BALLONLEA_GREEN_MUSHROOM));
    public static final RegistrySupplier<GenerationsMushroomBlock> DOUBLE_BALLONLEA_PINK_MUSHROOM = registerBlockItem("double_ballonlea_pink_mushroom", () -> new GenerationsMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), GenerationsFeatures.DOUBLE_BALLONLEA_PINK_MUSHROOM));
    public static final RegistrySupplier<GenerationsMushroomBlock> DOUBLE_BALLONLEA_YELLOW_MUSHROOM = registerBlockItem("double_ballonlea_yellow_mushroom", () -> new GenerationsMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), GenerationsFeatures.DOUBLE_BALLONLEA_YELLOW_MUSHROOM));

    /**
     * PokeBall Chests
     **/
    public static final RegistrySupplier<GenericChestBlock> POKEBALL_CHEST = registerChestBlockItem("pokeball_chest", () -> new GenericChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST),  9, 4, "pokeball_chest"));
    public static final RegistrySupplier<GenericChestBlock> GREATBALL_CHEST = registerChestBlockItem("greatball_chest", () -> new GenericChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST),  9, 5, "greatball_chest"));
    public static final RegistrySupplier<GenericChestBlock> ULTRABALL_CHEST = registerChestBlockItem("ultraball_chest", () -> new GenericChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST),  9, 6, "ultraball_chest"));
    public static final RegistrySupplier<GenericChestBlock> MASTERBALL_CHEST = registerChestBlockItem("masterball_chest", () -> new GenericChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST),  12, 8, "masterball_chest"));

    public static <T extends Block> RegistrySupplier<T> registerBlockItem(String name, Supplier<T> blockSupplier) {
        RegistrySupplier<T> block = BLOCKS.register(name, blockSupplier);
        register(name, properties -> new BlockItem(block.get(), properties));
        return block;
    }

    private static <T extends Block> RegistrySupplier<T> registerUltraBlock(String name, Supplier<T> blockSupplier) {
        RegistrySupplier<T> block = ULTRA_BLOCKS.register(name, blockSupplier);
        register(name, properties -> new BlockItem(block.get(), properties));
        return block;
    }

    private static <T extends GenericChestBlock> RegistrySupplier<T> registerChestBlockItem(String name, Supplier<T> blockSupplier) {
        RegistrySupplier<T> block = BLOCKS.register(name, blockSupplier);
        register(name, properties -> new GenericChestBlockItem(block.get(), properties));
        return block;
    }
    
    private static <T extends Block> RegistrySupplier<T> registerStone(String name, Supplier<T> blockSupplier) {
        RegistrySupplier<T> block = STONE.register(name, blockSupplier);
        register(name, properties -> new BlockItem(block.get(), properties));
        return block;
    }

    private static void register(String name, Function<Item.Properties, Item> itemSupplier) {
        BLOCK_ITEMS.register(name, () -> itemSupplier.apply(new Item.Properties().arch$tab(GenerationsCreativeTabs.BUILDING_BLOCKS)));
    }

    public static void init() {
        GenerationsCore.LOGGER.info("Registering Generations Blocks");
        BLOCKS.register();
        ULTRA_BLOCKS.register();
        STONE.register();
        BLOCK_ITEMS.register();
    }
}
