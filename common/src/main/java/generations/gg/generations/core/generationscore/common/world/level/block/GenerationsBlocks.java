package generations.gg.generations.core.generationscore.common.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.item.GenericChestBlockItem;
import generations.gg.generations.core.generationscore.common.world.level.block.decorations.PokecenterScarletSignBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericChestBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.pumpkin.CursedCarvedPumpkinBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.pumpkin.CursedPumpkinBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.pumpkin.EquipableCursedCarvedPumpkinBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsBlockSet;
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsFullBlockSet;
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsUltraBlockSet;
import generations.gg.generations.core.generationscore.common.world.level.block.state.properties.GenerationsBlockSetTypes;
import generations.gg.generations.core.generationscore.common.world.level.levelgen.GenerationsFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;

import java.util.function.BiFunction;
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
    public static final RegistrySupplier<Block> POKECENTER_SCARLET_SIGN = registerBlockItem("pokecenter_scarlet_sign", () -> new PokecenterScarletSignBlock(BlockBehaviour.Properties.of().lightLevel((state) -> 15)));
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


    public static final GenerationsBlockSet TEMPLE_BLOCK_SET = new GenerationsBlockSet("temple_block");
    public static final GenerationsBlockSet TEMPLE_BRICK_SET = new GenerationsBlockSet("temple_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet CASTLE_BLOCK_SET = new GenerationsBlockSet("castle_block");
    public static final GenerationsBlockSet CRACKED_CASTLE_BLOCK_SET = new GenerationsBlockSet("cracked_castle_block");
    public static final GenerationsBlockSet CASTLE_BRICK_SET = new GenerationsBlockSet("castle_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet CASTLE_BRICK_2_SET = new GenerationsBlockSet("castle_brick_2", BlockBehaviour.Properties.copy(Blocks.BRICKS));

    public static final GenerationsBlockSet GRAY_CASTLE_BRICK_SET = new GenerationsBlockSet("gray_castle_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet GRAY_CASTLE_BRICK_2_SET = new GenerationsBlockSet("gray_castle_brick_2", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet WHITE_CASTLE_BRICK_SET = new GenerationsBlockSet("white_castle_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet WHITE_CASTLE_BRICK_2_SET = new GenerationsBlockSet("white_castle_brick_2", BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet CASTLE_WALL_SET = new GenerationsBlockSet("castle_wall");
    public static final GenerationsBlockSet CASTLE_WALL_2_SET = new GenerationsBlockSet("castle_wall_2");
    public static final GenerationsBlockSet CASTLE_WALL_3_SET = new GenerationsBlockSet("castle_wall_3");
    public static final GenerationsBlockSet CASTLE_WALL_4_SET = new GenerationsBlockSet("castle_wall_4");
    public static final GenerationsBlockSet CASTLE_FLOOR_SET = new GenerationsBlockSet("castle_floor");
    public static final GenerationsBlockSet ICE_BRICK_SET = new GenerationsBlockSet("ice_brick", BlockBehaviour.Properties.copy(Blocks.ICE));
    public static final RegistrySupplier<Block> ICE_PILLAR = registerBlockItem("ice_pillar", () -> new PillarBlock(BlockBehaviour.Properties.copy(Blocks.ICE)));
    public static final RegistrySupplier<Block> BROKEN_ICE_PILLAR = registerBlockItem("ice_pillar_broken", () -> new PillarBlock(BlockBehaviour.Properties.copy(Blocks.ICE)));

    public static final GenerationsBlockSet ICE_PILLAR_SIDE_SET = new GenerationsBlockSet("ice_pillar_side", BlockBehaviour.Properties.copy(Blocks.ICE));
    public static final GenerationsBlockSet ICE_PILLAR_TOP_SET = new GenerationsBlockSet("ice_pillar_top", BlockBehaviour.Properties.copy(Blocks.ICE));
    public static final GenerationsBlockSet ROCK_SET = new GenerationsBlockSet("rock");
    public static final GenerationsBlockSet CAVE_ROCK_SET = new GenerationsBlockSet("cave_rock");
    public static final GenerationsBlockSet CAVE_ROCK_FLOOR_SET = new GenerationsBlockSet("cave_rock_floor");
    public static final GenerationsBlockSet GRAY_CAVE_ROCK_FLOOR_SET = new GenerationsBlockSet("gray_cave_rock_floor");
    public static final GenerationsBlockSet ICE_CAVE_ROCK_FLOOR_SET = new GenerationsBlockSet("ice_cave_rock_floor", BlockBehaviour.Properties.copy(Blocks.ICE));
    public static final GenerationsBlockSet BRIDGE_BLOCK_SET = new GenerationsBlockSet("bridge_block", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS));


    public static final RegistrySupplier<Block> CASTLE_PILLAR = registerBlockItem("castle_pillar", () -> new PillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> BROKEN_CASTLE_PILLAR = registerBlockItem("broken_castle_pillar", () -> new PillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> PRISMARINE_PILLAR = registerBlockItem("prismarine_pillar", () -> new PillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> BROKEN_PRISMARINE_PILLAR = registerBlockItem("prismarine_pillar_broken", () -> new PillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> DARK_PRISMARINE_PILLAR = registerBlockItem("dark_prismarine_pillar", () -> new PillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> BROKEN_DARK_PRISMARINE_PILLAR = registerBlockItem("dark_prismarine_pillar_broken", () -> new PillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> HAUNTED_PILLAR = registerBlockItem("haunted_pillar", () -> new PillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> BROKEN_HAUNTED_PILLAR = registerBlockItem("haunted_pillar_broken", () -> new PillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

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

    public static final RegistrySupplier<Block> ULTRITE_BLOCK = registerBlockItem("ultrite_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));

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
    public static final RegistrySupplier<PumpkinBlock> CURSED_PUMPKIN = registerBlockItem("cursed_pumpkin", CursedPumpkinBlock::new);
    public static final RegistrySupplier<EquipableCursedCarvedPumpkinBlock> CURSED_CARVED_PUMPKIN = registerBlockItem("cursed_carved_pumpkin", () -> new EquipableCursedCarvedPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.CARVED_PUMPKIN)));
    public static final RegistrySupplier<CursedCarvedPumpkinBlock> CURSED_JACK_O_LANTERN = registerBlockItem("cursed_jack_o_lantern", () -> new CursedCarvedPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.JACK_O_LANTERN)));

    public static final GenerationsBlockSet COBBLE_RUINS_1_SET = new GenerationsBlockSet("cobble_ruins_1");
    public static final GenerationsBlockSet COBBLE_RUINS_2_SET = new GenerationsBlockSet("cobble_ruins_2");
    public static final GenerationsBlockSet COBBLE_RUINS_3_SET = new GenerationsBlockSet("cobble_ruins_3");
    public static final GenerationsBlockSet COBBLE_RUINS_4_SET = new GenerationsBlockSet("cobble_ruins_4");

    /**
     * Ultra Blocks
     */

    public static final GenerationsUltraBlockSet ULTRA_WHITE_SET = new GenerationsUltraBlockSet("ultra_white", registerUltraBlock("ultra_white", () -> new Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.WHITE))));
    public static final GenerationsUltraBlockSet ULTRA_LIGHT_GRAY_SET = new GenerationsUltraBlockSet("ultra_light_gray", registerUltraBlock("ultra_light_gray", () -> new Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.LIGHT_GRAY))));
    public static final GenerationsUltraBlockSet ULTRA_GRAY_SET = new GenerationsUltraBlockSet("ultra_gray", registerUltraBlock("ultra_gray", () -> new Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.GRAY))));
    public static final GenerationsUltraBlockSet ULTRA_BLACK_SET = new GenerationsUltraBlockSet("ultra_black", registerUltraBlock("ultra_black", () -> new Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.BLACK))));
    public static final GenerationsUltraBlockSet ULTRA_BROWN_SET = new GenerationsUltraBlockSet("ultra_brown", registerUltraBlock("ultra_brown", () -> new Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.BROWN))));
    public static final GenerationsUltraBlockSet ULTRA_RED_SET = new GenerationsUltraBlockSet("ultra_red", registerUltraBlock("ultra_red", () -> new Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.RED))));
    public static final GenerationsUltraBlockSet ULTRA_ORANGE_SET = new GenerationsUltraBlockSet("ultra_orange", registerUltraBlock("ultra_orange", () -> new Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.ORANGE))));
    public static final GenerationsUltraBlockSet ULTRA_YELLOW_SET = new GenerationsUltraBlockSet("ultra_yellow", registerUltraBlock("ultra_yellow", () -> new Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.YELLOW))));
    public static final GenerationsUltraBlockSet ULTRA_LIME_SET = new GenerationsUltraBlockSet("ultra_lime", registerUltraBlock("ultra_lime", () -> new Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.LIME))));
    public static final GenerationsUltraBlockSet ULTRA_GREEN_SET = new GenerationsUltraBlockSet("ultra_green", registerUltraBlock("ultra_green", () -> new Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.GREEN))));
    public static final GenerationsUltraBlockSet ULTRA_CYAN_SET = new GenerationsUltraBlockSet("ultra_cyan", registerUltraBlock("ultra_cyan", () -> new Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.CYAN))));
    public static final GenerationsUltraBlockSet ULTRA_LIGHT_BLUE_SET = new GenerationsUltraBlockSet("ultra_light_blue", registerUltraBlock("ultra_light_blue", () -> new Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.LIGHT_BLUE))));
    public static final GenerationsUltraBlockSet ULTRA_BLUE_SET = new GenerationsUltraBlockSet("ultra_blue", registerUltraBlock("ultra_blue", () -> new Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.BLUE))));
    public static final GenerationsUltraBlockSet ULTRA_PURPLE_SET = new GenerationsUltraBlockSet("ultra_purple", registerUltraBlock("ultra_purple", () -> new Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.PURPLE))));
    public static final GenerationsUltraBlockSet ULTRA_MAGENTA_SET = new GenerationsUltraBlockSet("ultra_magenta", registerUltraBlock("ultra_magenta", () -> new Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.MAGENTA))));
    public static final GenerationsUltraBlockSet ULTRA_PINK_SET = new GenerationsUltraBlockSet("ultra_pink", registerUltraBlock("ultra_pink", () -> new Block(ULTRA_BLOCK_SETTINGS.mapColor(DyeColor.PINK))));

    public static final RegistrySupplier<Block> ULTRA_SAND = registerBlockItem("ultra_sand", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));


    /**
     * Marble block sets
     */

    public static final GenerationsFullBlockSet WHITE_MARBLE_SET = new GenerationsFullBlockSet("white_marble", DyeColor.WHITE, GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet LIGHT_GRAY_MARBLE_SET = new GenerationsFullBlockSet("light_gray_marble", DyeColor.GRAY, GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet GRAY_MARBLE_SET = new GenerationsFullBlockSet("gray_marble", DyeColor.GRAY, GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet BLACK_MARBLE_SET = new GenerationsFullBlockSet("black_marble", DyeColor.BLACK, GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet BROWN_MARBLE_SET = new GenerationsFullBlockSet("brown_marble", DyeColor.BROWN, GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet RED_MARBLE_SET = new GenerationsFullBlockSet("red_marble", DyeColor.RED, GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet ORANGE_MARBLE_SET = new GenerationsFullBlockSet("orange_marble", DyeColor.ORANGE, GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet YELLOW_MARBLE_SET = new GenerationsFullBlockSet("yellow_marble", DyeColor.YELLOW, GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet LIME_MARBLE_SET = new GenerationsFullBlockSet("lime_marble", DyeColor.LIME, GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet GREEN_MARBLE_SET = new GenerationsFullBlockSet("green_marble", DyeColor.GREEN, GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet CYAN_MARBLE_SET = new GenerationsFullBlockSet("cyan_marble", DyeColor.CYAN, GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet POWDER_BLUE_MARBLE_SET = new GenerationsFullBlockSet("powder_blue_marble", BlockBehaviour.Properties.copy(Blocks.STONE), GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet LIGHT_BLUE_MARBLE_SET = new GenerationsFullBlockSet("light_blue_marble", DyeColor.LIGHT_BLUE, GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet BLUE_MARBLE_SET = new GenerationsFullBlockSet("blue_marble", DyeColor.BLUE, GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet PURPLE_MARBLE_SET = new GenerationsFullBlockSet("purple_marble", DyeColor.PURPLE, GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet MAGENTA_MARBLE_SET = new GenerationsFullBlockSet("magenta_marble", DyeColor.MAGENTA, GenerationsBlockSetTypes.MARBLE);
    public static final GenerationsFullBlockSet PINK_MARBLE_SET = new GenerationsFullBlockSet("pink_marble", DyeColor.PINK, GenerationsBlockSetTypes.MARBLE);

    /**
     * Unown Blocks
     */
    public static final RegistrySupplier<Block> UNOWN_BLOCK_A = registerBlockItem("unown_block_a", () -> new UnownBlock("A", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_B = registerBlockItem("unown_block_b", () -> new UnownBlock("B", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_C = registerBlockItem("unown_block_c", () -> new UnownBlock("C", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_D = registerBlockItem("unown_block_d", () -> new UnownBlock("D", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_E = registerBlockItem("unown_block_e", () -> new UnownBlock("E", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_F = registerBlockItem("unown_block_f", () -> new UnownBlock("F", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_G = registerBlockItem("unown_block_g", () -> new UnownBlock("G", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_H = registerBlockItem("unown_block_h", () -> new UnownBlock("H", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_I = registerBlockItem("unown_block_i", () -> new UnownBlock("I", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_J = registerBlockItem("unown_block_j", () -> new UnownBlock("J", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_K = registerBlockItem("unown_block_k", () -> new UnownBlock("K", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_L = registerBlockItem("unown_block_l", () -> new UnownBlock("L", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_M = registerBlockItem("unown_block_m", () -> new UnownBlock("M", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_N = registerBlockItem("unown_block_n", () -> new UnownBlock("N", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_O = registerBlockItem("unown_block_o", () -> new UnownBlock("O", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_P = registerBlockItem("unown_block_p", () -> new UnownBlock("P", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_Q = registerBlockItem("unown_block_q", () -> new UnownBlock("Q", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_R = registerBlockItem("unown_block_r", () -> new UnownBlock("R", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_S = registerBlockItem("unown_block_s", () -> new UnownBlock("S", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_T = registerBlockItem("unown_block_t", () -> new UnownBlock("T", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_U = registerBlockItem("unown_block_u", () -> new UnownBlock("U", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_V = registerBlockItem("unown_block_v", () -> new UnownBlock("V", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_W = registerBlockItem("unown_block_w", () -> new UnownBlock("W", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_X = registerBlockItem("unown_block_x", () -> new UnownBlock("X", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_Y = registerBlockItem("unown_block_y", () -> new UnownBlock("Y", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_Z = registerBlockItem("unown_block_z", () -> new UnownBlock("Z", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_BLANK = registerBlockItem("unown_block_blank", () -> new UnownBlock(" ", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_EXCLAMATION_MARK = registerBlockItem("unown_block_exclamation_mark", () -> new UnownBlock("!", BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_QUESTION_MARK = registerBlockItem("unown_block_question_mark", () -> new UnownBlock("?", BlockBehaviour.Properties.copy(Blocks.STONE)));

    /**
     * Full ChargeStone Pallet
     */
    public static final GenerationsFullBlockSet CHARGE_STONE_SET = new GenerationsFullBlockSet("charge_stone", GenerationsBlockSetTypes.CHARGE_STONE);
    public static final GenerationsBlockSet CHARGE_COBBLESTONE_SET = new GenerationsBlockSet("charge_cobblestone", BlockBehaviour.Properties.copy(Blocks.COBBLESTONE));
    public static final GenerationsBlockSet MOSSY_CHARGE_COBBLESTONE_SET = new GenerationsBlockSet("mossy_charge_cobblestone", BlockBehaviour.Properties.copy(Blocks.MOSSY_COBBLESTONE));
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

    public static final GenerationsBlockSet MOSSY_CHARGE_STONE_BRICKS_SET = new GenerationsBlockSet("mossy_charge_stone_brick", registerBlockItem("mossy_charge_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS))), BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS));
    //Infested blocks
    public static final RegistrySupplier<InfestedBlock> INFESTED_CHARGE_STONE = registerStone("infested_charge_stone", () -> new InfestedBlock(CHARGE_STONE_SET.getBaseBlock(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CHARGE_COBBLESTONE = registerStone("infested_charge_cobblestone", () -> new InfestedBlock(CHARGE_COBBLESTONE_SET.getBaseBlock(), BlockBehaviour.Properties.copy(Blocks.INFESTED_COBBLESTONE)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CHARGE_STONE_BRICKS = registerStone("infested_charge_stone_bricks", () -> new InfestedBlock(CHARGE_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_MOSSY_CHARGE_STONE_BRICKS = registerStone("infested_mossy_charge_stone_bricks", () -> new InfestedBlock(MOSSY_CHARGE_STONE_BRICKS_SET.getBaseBlock(), BlockBehaviour.Properties.copy(Blocks.INFESTED_MOSSY_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CRACKED_CHARGE_STONE_BRICKS = registerStone("infested_cracked_charge_stone_bricks", () -> new InfestedBlock(CRACKED_CHARGE_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_CRACKED_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CHISELED_CHARGE_STONE_BRICKS = registerStone("infested_chiseled_charge_stone_bricks", () -> new InfestedBlock(CHISELED_CHARGE_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_CHISELED_STONE_BRICKS)));
    //Full ChargeStone Pallet ^

    public static final GenerationsBlockSet BRIGHT_CHARGE_COBBLESTONE_SET = new GenerationsBlockSet("bright_charge_cobblestone", BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).lightLevel(lightLevel -> 30));
    public static final GenerationsBlockSet GLOWING_CHARGE_COBBLESTONE_SET = new GenerationsBlockSet("glowing_charge_cobblestone", BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).lightLevel(lightLevel -> 15));
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
    public static final GenerationsBlockSet VOLCANIC_COBBLESTONE_SET = new GenerationsBlockSet("volcanic_cobblestone", BlockBehaviour.Properties.copy(Blocks.COBBLESTONE));

    //Mossy Volcanic CobbleStone
    public static final GenerationsBlockSet MOSSY_VOLCANIC_COBBLESTONE_SET = new GenerationsBlockSet("mossy_volcanic_cobblestone", BlockBehaviour.Properties.copy(Blocks.MOSSY_COBBLESTONE));

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
    public static final GenerationsBlockSet MOSSY_VOLCANIC_STONE_BRICKS_SET = new GenerationsBlockSet("mossy_volcanic_stone_brick", registerBlockItem("mossy_volcanic_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS))), BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS));
    //Infested Volcanic Stone Bricks
    public static final RegistrySupplier<InfestedBlock> INFESTED_VOLCANIC_STONE = registerStone("infested_volcanic_stone", () -> new InfestedBlock(VOLCANIC_STONE.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_VOLCANIC_COBBLESTONE = registerStone("infested_volcanic_cobblestone", () -> new InfestedBlock(VOLCANIC_COBBLESTONE_SET.getBaseBlock(), BlockBehaviour.Properties.copy(Blocks.INFESTED_COBBLESTONE)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_VOLCANIC_STONE_BRICKS = registerStone("infested_volcanic_stone_bricks", () -> new InfestedBlock(VOLCANIC_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_MOSSY_VOLCANIC_STONE_BRICKS = registerStone("infested_mossy_volcanic_stone_bricks", () -> new InfestedBlock(MOSSY_VOLCANIC_STONE_BRICKS_SET.getBaseBlock(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CRACKED_VOLCANIC_STONE_BRICKS = registerStone("infested_cracked_volcanic_stone_bricks", () -> new InfestedBlock(CRACKED_VOLCANIC_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CHISELED_VOLCANIC_STONE_BRICKS = registerStone("infested_chiseled_volcanic_stone_bricks", () -> new InfestedBlock(CHISELED_VOLCANIC_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE_BRICKS)));
    //Full Volcanic Stone Pallet^

    public static final RegistrySupplier<Block> VOLCANIC_FIRESTONE = registerBlockItem("volcanic_firestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final GenerationsBlockSet VOLCANIC_ROCK_SET = new GenerationsBlockSet("volcanic_rock", BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_BROWN));

    public static final RegistrySupplier<Block> LIGHTNING_LANTERN = registerBlockItem("lightning_lantern", () -> new Block(BlockBehaviour.Properties.copy(Blocks.LANTERN)));

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
    public static final GenerationsBlockSet FLOOR_1_SET = new GenerationsBlockSet("floor_1");
    public static final GenerationsBlockSet FLOOR_2_SET = new GenerationsBlockSet("floor_2");
    public static final GenerationsBlockSet FLOOR_3_SET = new GenerationsBlockSet("floor_3");
    public static final GenerationsBlockSet FLOOR_4_SET = new GenerationsBlockSet("floor_4");
    public static final GenerationsBlockSet MIRRORED_FLOOR_1_SET = new GenerationsBlockSet("mirrored_floor_1");
    public static final GenerationsBlockSet MIRRORED_FLOOR_2_SET = new GenerationsBlockSet("mirrored_floor_2");
    public static final GenerationsBlockSet MIRRORED_FLOOR_3_SET = new GenerationsBlockSet("mirrored_floor_3");

    public static final GenerationsFullBlockSet ENCHANTED_OBSIDIAN_SET = new GenerationsFullBlockSet("enchanted_obsidian", BlockBehaviour.Properties.copy(Blocks.OBSIDIAN), GenerationsBlockSetTypes.ENCHANTED_OBISIDIAN, registerBlockItem("enchanted_obsidian", () -> new EnchantedObsidianBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN))));

    /**
     * Poke Bricks
     */

    public static final GenerationsFullBlockSet WHITE_POKE_BRICK_SET = new GenerationsFullBlockSet("white_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS).mapColor(DyeColor.WHITE), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet LIGHT_GRAY_POKE_BRICK_SET = new GenerationsFullBlockSet("light_gray_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS).mapColor(DyeColor.LIGHT_GRAY), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet GRAY_POKE_BRICK_SET = new GenerationsFullBlockSet("gray_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS).mapColor(DyeColor.GRAY), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet BLACK_POKE_BRICK_SET = new GenerationsFullBlockSet("black_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS).mapColor(DyeColor.BLACK), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet BROWN_POKE_BRICK_SET = new GenerationsFullBlockSet("brown_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS).mapColor(DyeColor.BROWN), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet RED_POKE_BRICK_SET = new GenerationsFullBlockSet("red_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS).mapColor(DyeColor.RED), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet ORANGE_POKE_BRICK_SET = new GenerationsFullBlockSet("orange_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS).mapColor(DyeColor.ORANGE), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet YELLOW_POKE_BRICK_SET = new GenerationsFullBlockSet("yellow_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS).mapColor(DyeColor.YELLOW), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet LIME_POKE_BRICK_SET = new GenerationsFullBlockSet("lime_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS).mapColor(DyeColor.LIME), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet GREEN_POKE_BRICK_SET = new GenerationsFullBlockSet("green_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS).mapColor(DyeColor.GREEN), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet CYAN_POKE_BRICK_SET = new GenerationsFullBlockSet("cyan_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS).mapColor(DyeColor.CYAN), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet LIGHT_BLUE_POKE_BRICK_SET = new GenerationsFullBlockSet("light_blue_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS).mapColor(DyeColor.LIGHT_BLUE), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet BLUE_POKE_BRICK_SET = new GenerationsFullBlockSet("blue_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS).mapColor(DyeColor.BLUE), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet PURPLE_POKE_BRICK_SET = new GenerationsFullBlockSet("purple_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS).mapColor(DyeColor.PURPLE), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet MAGENTA_POKE_BRICK_SET = new GenerationsFullBlockSet("magenta_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS).mapColor(DyeColor.MAGENTA), GenerationsBlockSetTypes.POKE_BRICK);
    public static final GenerationsFullBlockSet PINK_POKE_BRICK_SET = new GenerationsFullBlockSet("pink_poke_brick", BlockBehaviour.Properties.copy(Blocks.BRICKS).mapColor(DyeColor.PINK), GenerationsBlockSetTypes.POKE_BRICK);
    /**
     * Ore Blocks
     */
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
    public static final RegistrySupplier<Block> Z_BLOCK = registerBlockItem("z_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK)));
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
    public static final GenerationsBlockSet GHOST_BRICKS_SET = new GenerationsBlockSet("ghost_brick", registerBlockItem("ghost_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS))), BlockBehaviour.Properties.copy(Blocks.BRICKS));
    public static final GenerationsBlockSet GHOST_OBELISK_SET = new GenerationsBlockSet("ghost_obelisk");

    public static final RegistrySupplier<RotatedPillarBlock> GHOST_PILLAR = registerBlockItem("ghost_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final GenerationsBlockSet MIRROR_GLASS_SET = new GenerationsBlockSet("mirror_glass", BlockBehaviour.Properties.copy(Blocks.GLASS).strength(.5f));
    public static final GenerationsBlockSet NORMAL_SANDSTONE_SET = new GenerationsBlockSet("normal_sandstone", BlockBehaviour.Properties.copy(Blocks.SANDSTONE));
    public static final GenerationsBlockSet BRIGHT_SANDSTONE_SET = new GenerationsBlockSet("bright_sandstone", BlockBehaviour.Properties.copy(Blocks.SANDSTONE).lightLevel((lightLevel) -> 15));
    public static final RegistrySupplier<Block> MACHINE_BLOCK = registerBlockItem("machine_block", () -> new MachineBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final GenerationsBlockSet OCEAN_BLOCK_SET = new GenerationsBlockSet("ocean_block");

    public static final GenerationsBlockSet WATER_QUARTZ_SET = new GenerationsBlockSet("water_quartz", BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK));
    public static final RegistrySupplier<Block> RUINS_SAND = registerBlockItem("ruins_sand", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> BURST_TURF = registerBlockItem("burst_turf", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));

    //Rich Soil
    public static final RegistrySupplier<Block> RICH_SOIL_1 = registerBlockItem("rich_soil_1", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistrySupplier<Block> RICH_SOIL_2 = registerBlockItem("rich_soil_2", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistrySupplier<Block> RICH_SOIL_3 = registerBlockItem("rich_soil_3", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistrySupplier<Block> RICH_SOIL_4 = registerBlockItem("rich_soil_4", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));

    //Bleach Stone
    public static final GenerationsBlockSet BLEACH_STONE_SET = new GenerationsBlockSet("bleach_stone");

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

    public static final GenerationsBlockSet COOL_STONE_SET = new GenerationsBlockSet("cool_stone");

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

    public static final RegistrySupplier<FlabebeFlowerBlock> FLABEBE_FLOWER_AZ = registerBlockItem("flabebe_flower_az", () -> new FlabebeFlowerBlock(BlockBehaviour.Properties.copy(Blocks.VINE).strength(0.8f).sound(SoundType.FLOWERING_AZALEA)));
    public static final RegistrySupplier<FlabebeFlowerBlock> FLABEBE_FLOWER_BLUE = registerBlockItem("flabebe_flower_blue", () -> new FlabebeFlowerBlock(BlockBehaviour.Properties.copy(Blocks.VINE).strength(0.8f).sound(SoundType.FLOWERING_AZALEA)));
    public static final RegistrySupplier<FlabebeFlowerBlock> FLABEBE_FLOWER_ORANGE = registerBlockItem("flabebe_flower_orange", () -> new FlabebeFlowerBlock(BlockBehaviour.Properties.copy(Blocks.VINE).strength(0.8f).sound(SoundType.FLOWERING_AZALEA)));
    public static final RegistrySupplier<FlabebeFlowerBlock> FLABEBE_FLOWER_RED = registerBlockItem("flabebe_flower_red", () -> new FlabebeFlowerBlock(BlockBehaviour.Properties.copy(Blocks.VINE).strength(0.8f).sound(SoundType.FLOWERING_AZALEA)));
    public static final RegistrySupplier<FlabebeFlowerBlock> FLABEBE_FLOWER_WHITE = registerBlockItem("flabebe_flower_white", () -> new FlabebeFlowerBlock(BlockBehaviour.Properties.copy(Blocks.VINE).strength(0.8f).sound(SoundType.FLOWERING_AZALEA)));
    public static final RegistrySupplier<FlabebeFlowerBlock> FLABEBE_FLOWER_YELLOW = registerBlockItem("flabebe_flower_yellow", () -> new FlabebeFlowerBlock(BlockBehaviour.Properties.copy(Blocks.VINE).strength(0.8f).sound(SoundType.FLOWERING_AZALEA)));

    /**
     * PokeBall Chests
     **/
    public static final RegistrySupplier<GenericChestBlock> POKEBALL_CHEST = registerChestBlockItem("pokeball_chest", () -> new GenericChestBlock(9, 4, "pokeball_chest"));
    public static final RegistrySupplier<GenericChestBlock> GREATBALL_CHEST = registerChestBlockItem("greatball_chest", () -> new GenericChestBlock(9, 5, "greatball_chest"));
    public static final RegistrySupplier<GenericChestBlock> ULTRABALL_CHEST = registerChestBlockItem("ultraball_chest", () -> new GenericChestBlock(9, 6, "ultraball_chest"));
    public static final RegistrySupplier<GenericChestBlock> MASTERBALL_CHEST = registerChestBlockItem("masterball_chest", () -> new GenericChestBlock(12, 8, "masterball_chest"));

    public static <T extends Block> RegistrySupplier<T> registerBlockItem(String name, Supplier<T> blockSupplier) {
        return registerBlockItem(name, blockSupplier, BlockItem::new, GenerationsBlocks.BLOCK_ITEMS);
    }

    public static <T extends Block> RegistrySupplier<T> registerBlockItem(String name, Supplier<T> blockSupplier, BiFunction<Block, Item.Properties, Item> function, DeferredRegister<Item> register) {
        RegistrySupplier<T> block = BLOCKS.register(name, blockSupplier);
        register(name, properties -> function.apply(block.get(), properties), register);
        return block;
    }

    public static <T extends Block> RegistrySupplier<T> registerUltraBlock(String name, Supplier<T> blockSupplier) {
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
        register(name, properties -> itemSupplier.apply(new Item.Properties()), BLOCK_ITEMS);
    }


    private static void register(String name, Function<Item.Properties, Item> itemSupplier, DeferredRegister<Item> register) {
        register.register(name, () -> itemSupplier.apply(new Item.Properties()));
    }

    public static void init() {
        GenerationsCore.LOGGER.info("Registering Generations Blocks");
        BLOCKS.register();
        ULTRA_BLOCKS.register();
        STONE.register();
        BLOCK_ITEMS.register();
    }
}
