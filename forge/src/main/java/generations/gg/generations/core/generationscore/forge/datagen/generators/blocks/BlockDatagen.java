package generations.gg.generations.core.generationscore.forge.datagen.generators.blocks;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.forge.datagen.data.families.GenerationsBlockFamilies;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.berry.BerryType;
import generations.gg.generations.core.generationscore.world.level.block.*;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericChestBlock;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class BlockDatagen extends GenerationsBlockStateProvider.Proxied {
    public static final ArrayList<Object> dropSelfList = new ArrayList<>();
    public static final ArrayList<GenerationsMushroomBlock> MUSHROOM_BLOCKS = new ArrayList<>();


    public BlockDatagen(GenerationsBlockStateProvider provider) {
        super(provider);
    }

    public void registerStatesAndModels() {
        registerOreBlocks();
        GenerationsBlockFamilies.getAllFamilies().forEach(this::registerBlockFamily);
        registerWoodPallet(GenerationsWood.ULTRA_DARK_LOG, GenerationsWood.STRIPPED_ULTRA_DARK_LOG, GenerationsWood.ULTRA_DARK_PLANKS, GenerationsWood.ULTRA_DARK_SLAB, GenerationsWood.ULTRA_DARK_STAIRS, GenerationsWood.ULTRA_DARK_BUTTON, GenerationsWood.ULTRA_DARK_PRESSURE_PLATE, GenerationsWood.ULTRA_DARK_DOOR, GenerationsWood.ULTRA_DARK_TRAPDOOR, GenerationsWood.ULTRA_DARK_WOOD, GenerationsWood.STRIPPED_ULTRA_DARK_WOOD, GenerationsWood.ULTRA_DARK_FENCE, GenerationsWood.ULTRA_DARK_FENCE_GATE, GenerationsWood.ULTRA_DARK_SIGN, GenerationsWood.ULTRA_DARK_WALL_SIGN, GenerationsWood.ULTRA_DARK_CRAFTING_TABLE, GenerationsWood.ULTRA_DARK_HANGING_SIGN, GenerationsWood.ULTRA_DARK_WALL_HANGING_SIGN);
        registerWoodPallet(GenerationsWood.ULTRA_JUNGLE_LOG, GenerationsWood.STRIPPED_ULTRA_JUNGLE_LOG, GenerationsWood.ULTRA_JUNGLE_PLANKS, GenerationsWood.ULTRA_JUNGLE_SLAB, GenerationsWood.ULTRA_JUNGLE_STAIRS, GenerationsWood.ULTRA_JUNGLE_BUTTON, GenerationsWood.ULTRA_JUNGLE_PRESSURE_PLATE, GenerationsWood.ULTRA_JUNGLE_DOOR, GenerationsWood.ULTRA_JUNGLE_TRAPDOOR, GenerationsWood.ULTRA_JUNGLE_WOOD, GenerationsWood.STRIPPED_ULTRA_JUNGLE_WOOD, GenerationsWood.ULTRA_JUNGLE_FENCE, GenerationsWood.ULTRA_JUNGLE_FENCE_GATE, GenerationsWood.ULTRA_JUNGLE_SIGN, GenerationsWood.ULTRA_JUNGLE_WALL_SIGN, GenerationsWood.ULTRA_JUNGLE_CRAFTING_TABLE, GenerationsWood.ULTRA_JUNGLE_HANGING_SIGN, GenerationsWood.ULTRA_JUNGLE_WALL_HANGING_SIGN);
        registerWoodPallet(GenerationsWood.GHOST_LOG, GenerationsWood.STRIPPED_GHOST_LOG, GenerationsWood.GHOST_PLANKS, GenerationsWood.GHOST_SLAB, GenerationsWood.GHOST_STAIRS, GenerationsWood.GHOST_BUTTON, GenerationsWood.GHOST_PRESSURE_PLATE, GenerationsWood.GHOST_DOOR, GenerationsWood.GHOST_TRAPDOOR, GenerationsWood.GHOST_WOOD, GenerationsWood.STRIPPED_GHOST_WOOD, GenerationsWood.GHOST_FENCE, GenerationsWood.GHOST_FENCE_GATE, GenerationsWood.GHOST_SIGN, GenerationsWood.GHOST_WALL_SIGN, GenerationsWood.GHOST_CRAFTING_TABLE, GenerationsWood.GHOST_HANGING_SIGN, GenerationsWood.GHOST_WALL_HANGING_SIGN);

        //Cobble Ruins
        registerPallet(GenerationsBlocks.COBBLE_RUINS_1, GenerationsBlocks.COBBLE_RUINS_1_SLAB, GenerationsBlocks.COBBLE_RUINS_1_STAIRS, GenerationsBlocks.COBBLE_RUINS_1_WALL, null, null, true);
        registerPallet(GenerationsBlocks.COBBLE_RUINS_2, GenerationsBlocks.COBBLE_RUINS_2_SLAB, GenerationsBlocks.COBBLE_RUINS_2_STAIRS, GenerationsBlocks.COBBLE_RUINS_2_WALL, null, null, true);
        registerPallet(GenerationsBlocks.COBBLE_RUINS_3, GenerationsBlocks.COBBLE_RUINS_3_SLAB, GenerationsBlocks.COBBLE_RUINS_3_STAIRS, GenerationsBlocks.COBBLE_RUINS_3_WALL, null, null, true);
        registerPallet(GenerationsBlocks.COBBLE_RUINS_4, GenerationsBlocks.COBBLE_RUINS_4_SLAB, GenerationsBlocks.COBBLE_RUINS_4_STAIRS, GenerationsBlocks.COBBLE_RUINS_4_WALL, null, null, true);

        //House Floors
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_1);
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_2);
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_3);
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_4);
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_5);
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_6);
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_7);
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_8);

        //ChargeStone
        registerPallet(GenerationsBlocks.BRIGHT_CHARGE_COBBLESTONE, GenerationsBlocks.BRIGHT_CHARGE_COBBLESTONE_SLAB, GenerationsBlocks.BRIGHT_CHARGE_COBBLESTONE_STAIRS, GenerationsBlocks.BRIGHT_CHARGE_COBBLESTONE_WALL, null, null, true);
        registerPallet(GenerationsBlocks.GLOWING_CHARGE_COBBLESTONE, GenerationsBlocks.GLOWING_CHARGE_COBBLESTONE_SLAB, GenerationsBlocks.GLOWING_CHARGE_COBBLESTONE_STAIRS, GenerationsBlocks.GLOWING_CHARGE_COBBLESTONE_WALL, null, null, true);
        registerBlockItem(GenerationsBlocks.CHARGE_DRIPSTONE_BLOCK);

        //Volcanic
        registerPallet(GenerationsBlocks.VOLCANIC_ROCK, GenerationsBlocks.VOLCANIC_ROCK_SLAB, GenerationsBlocks.VOLCANIC_ROCK_STAIRS, GenerationsBlocks.VOLCANIC_ROCK_WALL, null, null, true);
        registerBlockItem(GenerationsBlocks.VOLCANIC_FIRESTONE);

        //Golden Temple
        registerSandStonePallet(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE, GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_SLAB, GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_STAIRS, GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_WALL, GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE, GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_SLAB, GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_STAIRS, GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_WALL, GenerationsBlocks.GOLDEN_TEMPLE_CHISELED_SANDSTONE, GenerationsBlocks.GOLDEN_TEMPLE_CUT_SANDSTONE, GenerationsBlocks.GOLDEN_TEMPLE_CUT_SANDSTONE_SLAB);

        registerPallet(GenerationsBlocks.GOLDEN_TEMPLE_DARK_PRISMARINE, GenerationsBlocks.GOLDEN_TEMPLE_DARK_PRISMARINE_SLAB, GenerationsBlocks.GOLDEN_TEMPLE_DARK_PRISMARINE_STAIRS, GenerationsBlocks.GOLDEN_TEMPLE_DARK_PRISMARINE_WALL, null, null, true);
        registerPallet(GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE, GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_SLAB, GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_STAIRS, GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_WALL, null, null, true);
        registerPallet(GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_BRICKS, GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_BRICKS_SLAB, GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_BRICKS_STAIRS, GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_BRICKS_WALL, null, null, true);

        //Subway
        registerPallet(GenerationsBlocks.SUBWAY_FLOOR_PLAIN, GenerationsBlocks.SUBWAY_FLOOR_PLAIN_SLAB, GenerationsBlocks.SUBWAY_FLOOR_PLAIN_STAIRS, GenerationsBlocks.SUBWAY_FLOOR_PLAIN_WALL, null, null, true);
        registerPallet(GenerationsBlocks.SUBWAY_FLOOR_CROSS, GenerationsBlocks.SUBWAY_FLOOR_CROSS_SLAB, GenerationsBlocks.SUBWAY_FLOOR_CROSS_STAIRS, GenerationsBlocks.SUBWAY_FLOOR_CROSS_WALL, null, null, true);
        registerPallet(GenerationsBlocks.SUBWAY_FLOOR_FULL_CROSS, GenerationsBlocks.SUBWAY_FLOOR_FULL_CROSS_SLAB, GenerationsBlocks.SUBWAY_FLOOR_FULL_CROSS_STAIRS, GenerationsBlocks.SUBWAY_FLOOR_FULL_CROSS_WALL, null, null, true);
        registerPallet(GenerationsBlocks.SUBWAY_FLOOR_PATH, GenerationsBlocks.SUBWAY_FLOOR_PATH_SLAB, GenerationsBlocks.SUBWAY_FLOOR_PATH_STAIRS, GenerationsBlocks.SUBWAY_FLOOR_PATH_WALL, null, null, true);
        registerPallet(GenerationsBlocks.SUBWAY_WALL, GenerationsBlocks.SUBWAY_WALL_SLAB, GenerationsBlocks.SUBWAY_WALL_STAIRS, null, null, null, true);
        registerPallet(GenerationsBlocks.SUBWAY_WALL_2, GenerationsBlocks.SUBWAY_WALL_2_SLAB, GenerationsBlocks.SUBWAY_WALL_2_STAIRS, null, null, null, true);

        //Enchanted Obsidian
        registerPallet(GenerationsBlocks.ENCHANTED_OBSIDIAN, GenerationsBlocks.ENCHANTED_OBSIDIAN_SLAB, GenerationsBlocks.ENCHANTED_OBSIDIAN_STAIRS, GenerationsBlocks.ENCHANTED_OBSIDIAN_WALL, GenerationsBlocks.ENCHANTED_OBSIDIAN_BUTTON, GenerationsBlocks.ENCHANTED_OBSIDIAN_PRESSURE_PLATE, true);

        registerBlockItem(GenerationsBlocks.INSIDE_WALL);
        registerBlockItem(GenerationsBlocks.INSIDE_WALL_BOTTOM);
        registerBlockItem(GenerationsBlocks.INSIDE_WALL_MIDDLE);
        registerBlockItem(GenerationsBlocks.INSIDE_WALL_TOP);
        registerBlockItem(GenerationsBlocks.INSIDE_WALL_MOLDING);
        registerBlockItem(GenerationsBlocks.OUTSIDE_WALL);

        registerBlockItem(GenerationsBlocks.TREE_TOP);
        registerBlockItem(GenerationsBlocks.TREE_BOTTOM);
        registerBlockItem(GenerationsBlocks.WOODEN_FLOORING);

        registerBlockItem(GenerationsBlocks.POKEMART_SIGN);
        registerBlockItem(GenerationsBlocks.POKECENTER_SIGN);

        registerGlassBlock(GenerationsBlocks.WINDOW_1);
        registerGlassBlock(GenerationsBlocks.WINDOW_2);

        registerBlockItem(GenerationsBlocks.RUINS_WALL);
        registerBlockItem(GenerationsBlocks.DUSTY_RUINS_WALL);

        registerBlockItem(GenerationsBlocks.CASTLE_PILLAR);
        registerBlockItem(GenerationsBlocks.BROKEN_CASTLE_PILLAR);

        registerBlockItem(GenerationsBlocks.PRISMARINE_PILLAR);
        registerBlockItem(GenerationsBlocks.BROKEN_PRISMARINE_PILLAR);

        registerBlockItem(GenerationsBlocks.DARK_PRISMARINE_PILLAR);
        registerBlockItem(GenerationsBlocks.BROKEN_DARK_PRISMARINE_PILLAR);

        registerBlockItem(GenerationsBlocks.ICE_PILLAR);
        registerBlockItem(GenerationsBlocks.BROKEN_ICE_PILLAR);

        registerBlockItem(GenerationsBlocks.HAUNTED_PILLAR);
        registerBlockItem(GenerationsBlocks.BROKEN_HAUNTED_PILLAR);

        registerBlockItem(GenerationsBlocks.DAWN_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.DUSK_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.FIRE_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.ICE_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.LEAF_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.MOON_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.SHINY_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.SUN_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.THUNDER_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.WATER_STONE_BLOCK);

        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_A);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_B);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_C);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_D);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_E);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_F);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_G);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_H);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_I);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_J);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_K);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_L);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_M);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_N);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_O);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_P);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_Q);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_R);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_S);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_T);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_U);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_V);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_W);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_X);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_Y);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_Z);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_PERIOD);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_COMMA);
        registerBlockItem(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE);

        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_A);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_B);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_C);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_D);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_E);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_F);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_G);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_H);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_I);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_J);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_K);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_L);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_M);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_N);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_O);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_P);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_Q);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_R);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_S);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_T);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_U);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_V);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_W);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_X);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_Y);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_Z);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_EXCLAMATION_MARK);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_QUESTION_MARK);
        registerBlockItem(GenerationsBlocks.UNOWN_BLOCK_BLANK);

        registerBlockItem(GenerationsBlocks.SANDY_GRASS);
        registerBlockItem(GenerationsBlocks.POKE_GRASS);
        registerBlockItem(GenerationsBlocks.POKE_DIRT);
        registerBlockItem(GenerationsBlocks.POKE_SAND);
        registerBlockItem(GenerationsBlocks.POKE_SAND_SMALL_CORNER_1);
        registerBlockItem(GenerationsBlocks.POKE_SAND_SMALL_CORNER_2);
        registerBlockItem(GenerationsBlocks.POKE_SAND_CORNER_1);
        registerBlockItem(GenerationsBlocks.POKE_SAND_CORNER_2);
        registerBlockItem(GenerationsBlocks.POKE_SAND_CORNER_3);
        registerBlockItem(GenerationsBlocks.POKE_SAND_CORNER_4);
        registerBlockItem(GenerationsBlocks.POKE_SAND_SIDE_1);
        registerBlockItem(GenerationsBlocks.POKE_SAND_SIDE_2);
        registerBlockItem(GenerationsBlocks.POKE_SAND_SIDE_3);
        registerBlockItem(GenerationsBlocks.POKE_SAND_SIDE_4);


        registerBlockItem(GenerationsBlocks.SHINGLES);
        registerBlockItem(GenerationsBlocks.SHINGLES_CORNER_1);
        registerBlockItem(GenerationsBlocks.SHINGLES_CORNER_2);

        registerBlockItem(GenerationsBlocks.ULTRA_SAND);

        registerBlockItem(GenerationsBlocks.CRATE);

        registerBlockItem(GenerationsBlocks.GOLDEN_TEMPLE_SAND);


        //floor
        registerPallet(GenerationsBlocks.FLOOR_1, GenerationsBlocks.FLOOR_1_SLAB, GenerationsBlocks.FLOOR_1_STAIRS, GenerationsBlocks.FLOOR_1_WALL, null, null, true);
        registerPallet(GenerationsBlocks.FLOOR_2, GenerationsBlocks.FLOOR_2_SLAB, GenerationsBlocks.FLOOR_2_STAIRS, GenerationsBlocks.FLOOR_2_WALL, null, null, true);
        registerPallet(GenerationsBlocks.FLOOR_3, GenerationsBlocks.FLOOR_3_SLAB, GenerationsBlocks.FLOOR_3_STAIRS, GenerationsBlocks.FLOOR_3_WALL, null, null, true);
        registerPallet(GenerationsBlocks.FLOOR_4, GenerationsBlocks.FLOOR_4_SLAB, GenerationsBlocks.FLOOR_4_STAIRS, GenerationsBlocks.FLOOR_4_WALL, null, null, true);
        registerPallet(GenerationsBlocks.MIRRORED_FLOOR_1, GenerationsBlocks.MIRRORED_FLOOR_1_SLAB, GenerationsBlocks.MIRRORED_FLOOR_1_STAIRS, GenerationsBlocks.MIRRORED_FLOOR_1_WALL, null, null, true);
        registerPallet(GenerationsBlocks.MIRRORED_FLOOR_2, GenerationsBlocks.MIRRORED_FLOOR_2_SLAB, GenerationsBlocks.MIRRORED_FLOOR_2_STAIRS, GenerationsBlocks.MIRRORED_FLOOR_2_WALL, null, null, true);
        registerPallet(GenerationsBlocks.MIRRORED_FLOOR_3, GenerationsBlocks.MIRRORED_FLOOR_3_SLAB, GenerationsBlocks.MIRRORED_FLOOR_3_STAIRS, GenerationsBlocks.MIRRORED_FLOOR_3_WALL, null, null, true);

        registerGate(GenerationsBlocks.YELLOW_PICKET_FENCE_GATE.get(), GenerationsBlocks.YELLOW_PICKET_FENCE.get(), null);
        registerFence(GenerationsBlocks.YELLOW_PICKET_FENCE.get(), null);
        registerGate(GenerationsBlocks.RED_PICKET_FENCE_GATE.get(), GenerationsBlocks.RED_PICKET_FENCE.get(), null);
        registerFence(GenerationsBlocks.RED_PICKET_FENCE.get(), null);
        registerGate(GenerationsBlocks.BLUE_PICKET_FENCE_GATE.get(), GenerationsBlocks.BLUE_PICKET_FENCE.get(), null);
        registerFence(GenerationsBlocks.BLUE_PICKET_FENCE.get(), null);
        registerGate(GenerationsBlocks.WHITE_PICKET_FENCE_GATE.get(), GenerationsBlocks.WHITE_PICKET_FENCE.get(), null);
        registerFence(GenerationsBlocks.WHITE_PICKET_FENCE.get(), null);

/*
        registerApricorn(GenerationsBlocks.BLACK_APRICORN);
        registerApricorn(GenerationsBlocks.WHITE_APRICORN);
        registerApricorn(GenerationsBlocks.PINK_APRICORN);
        registerApricorn(GenerationsBlocks.GREEN_APRICORN);
        registerApricorn(GenerationsBlocks.BLUE_APRICORN);
        registerApricorn(GenerationsBlocks.YELLOW_APRICORN);
        registerApricorn(GenerationsBlocks.RED_APRICORN);

        registerApricornLeaves(GenerationsBlocks.BLACK_APRICORN_LEAVES);
        registerApricornLeaves(GenerationsBlocks.WHITE_APRICORN_LEAVES);
        registerApricornLeaves(GenerationsBlocks.PINK_APRICORN_LEAVES);
        registerApricornLeaves(GenerationsBlocks.GREEN_APRICORN_LEAVES);
        registerApricornLeaves(GenerationsBlocks.BLUE_APRICORN_LEAVES);
        registerApricornLeaves(GenerationsBlocks.YELLOW_APRICORN_LEAVES);
        registerApricornLeaves(GenerationsBlocks.RED_APRICORN_LEAVES);

        registerBerryBush(GenerationsBlocks.AGUAV_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.APICOT_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.ASPEAR_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.BABIRI_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.BELUE_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.BLUK_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.CHARTI_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.CHERI_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.CHESTO_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.CHILAN_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.CHOPLE_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.COBA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.COLBUR_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.CORNN_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.CUSTAP_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.DRASH_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.DURIN_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.EGGANT_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.ENIGMA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.FIGY_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.GANLON_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.GINEMA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.GREPA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.HABAN_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.HONDEW_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.IAPAPA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.JABOCA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.KASIB_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.KEBIA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.KEE_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.KELPSY_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.KUO_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.LANSAT_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.LEPPA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.LIECHI_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.LUM_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.MAGO_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.MAGOST_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.MARANGA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.MICLE_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.NANAB_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.NINIKU_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.NOMEL_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.NUTPEA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.OCCA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.ORAN_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.PAMTRE_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.PASSHO_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.PAYAPA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.PECHA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.PERSIM_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.PETAYA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.PINAP_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.POMEG_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.PUMKIN_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.QUALOT_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.RABUTA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.RAWST_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.RAZZ_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.RINDO_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.ROSELI_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.ROWAP_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.SALAC_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.SHUCA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.SITRUS_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.SPELON_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.STARF_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.STRIB_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.TAMATO_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.TANGA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.TOPO_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.TOUGA_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.WACAN_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.WATMEL_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.WEPEAR_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.WIKI_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.YACHE_BERRY_BUSH);
        registerBerryBush(GenerationsBlocks.YAGO_BERRY_BUSH);

 */

        registerBlockItem(GenerationsBlocks.RAW_ALUMINUM_BLOCK);
        registerBlockItem(GenerationsBlocks.ALUMINUM_BLOCK);
        registerPallet(GenerationsBlocks.RUBY_BLOCK, GenerationsBlocks.RUBY_SLAB, GenerationsBlocks.RUBY_STAIRS, GenerationsBlocks.RUBY_WALL, null, null, true);
        registerPallet(GenerationsBlocks.SAPPHIRE_BLOCK, GenerationsBlocks.SAPPHIRE_SLAB, GenerationsBlocks.SAPPHIRE_STAIRS, GenerationsBlocks.SAPPHIRE_WALL, null, null, true);
        registerPallet(GenerationsBlocks.CRYSTAL_BLOCK, GenerationsBlocks.CRYSTAL_SLAB, GenerationsBlocks.CRYSTAL_STAIRS, GenerationsBlocks.CRYSTAL_WALL, null, null, true);
        registerBlockItem(GenerationsBlocks.CRYSTAL_LIGHT);
        registerBlockItem(GenerationsBlocks.SILICON_BLOCK);


        registerCrossBlock(GenerationsBlocks.BALLONLEA_BLUE_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.BALLONLEA_GREEN_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.BALLONLEA_PINK_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.BALLONLEA_YELLOW_MUSHROOM);

        registerCrossBlock(GenerationsBlocks.GROUP_BALLONLEA_BLUE_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.GROUP_BALLONLEA_GREEN_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.GROUP_BALLONLEA_PINK_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.GROUP_BALLONLEA_YELLOW_MUSHROOM);

        registerCrossBlock(GenerationsBlocks.TALL_BALLONLEA_BLUE_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.TALL_BALLONLEA_GREEN_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.TALL_BALLONLEA_PINK_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.TALL_BALLONLEA_YELLOW_MUSHROOM);

        registerCrossBlock(GenerationsBlocks.DOUBLE_BALLONLEA_BLUE_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.DOUBLE_BALLONLEA_GREEN_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.DOUBLE_BALLONLEA_PINK_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.DOUBLE_BALLONLEA_YELLOW_MUSHROOM);


        //Ultra Space
        //registerSandStonePallet(GenerationsBlocks.ULTRA_SANDSTONE, GenerationsBlocks.ULTRA_SANDSTONE_SLAB, GenerationsBlocks.ULTRA_SANDSTONE_STAIRS, GenerationsBlocks.ULTRA_SANDSTONE_WALL, GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE, GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_SLAB, GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_STAIRS, GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_WALL, GenerationsBlocks.ULTRA_CHISELED_SANDSTONE, GenerationsBlocks.ULTRA_CUT_SANDSTONE, GenerationsBlocks.ULTRA_CUT_SANDSTONE_SLAB);
        registerBlockItem(GenerationsBlocks.GHOST_LANTERN);
        registerBlockItem(GenerationsBlocks.MACHINE_BLOCK);
        registerBlockItem(GenerationsBlocks.RUINS_SAND);
        registerBlockItem(GenerationsBlocks.BURST_TURF);
        registerSoftSoil();

        registerBlockItem(GenerationsBlocks.WARNING_BLOCK);

        registerPumpkin(GenerationsBlocks.CURSED_PUMPKIN);
        registerCarvedPumpkin(GenerationsBlocks.CURSED_CARVED_PUMPKIN, GenerationsBlocks.CURSED_PUMPKIN);
        registerCarvedPumpkin(GenerationsBlocks.CURSED_JACK_O_LANTERN, GenerationsBlocks.CURSED_PUMPKIN);

        registerDoor(GenerationsBlocks.POKECENTER_DOOR.get());

        registerPallet(GenerationsBlocks.POKECENTER_ROOF, GenerationsBlocks.POKECENTER_ROOF_SLAB, GenerationsBlocks.POKECENTER_ROOF_STAIRS, GenerationsBlocks.POKECENTER_ROOF_WALL, null, null, true);
        registerPallet(GenerationsBlocks.POKECENTER_ROOF_2, GenerationsBlocks.POKECENTER_ROOF_2_SLAB, GenerationsBlocks.POKECENTER_ROOF_2_STAIRS, GenerationsBlocks.POKECENTER_ROOF_2_WALL, null, null, true);

        pokeBallChests(GenerationsBlocks.POKEBALL_CHEST, GenerationsItems.POKE_BALL);
        pokeBallChests(GenerationsBlocks.GREATBALL_CHEST, GenerationsItems.GREAT_BALL);
        pokeBallChests(GenerationsBlocks.ULTRABALL_CHEST, GenerationsItems.ULTRA_BALL);
        pokeBallChests(GenerationsBlocks.MASTERBALL_CHEST, GenerationsItems.MASTER_BALL);

        //Furnaces
        registerFurnace(GenerationsUtilityBlocks.CHARGE_STONE_FURNACE);
        registerFurnace(GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE);
        registerFurnace(GenerationsUtilityBlocks.CHARGE_STONE_SMOKER);
        registerFurnace(GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE);
        registerFurnace(GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE);
        registerFurnace(GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER);

        registerBlockItem(GenerationsBlocks.RICH_SOIL_1);
        registerBlockItem(GenerationsBlocks.RICH_SOIL_2);
        registerBlockItem(GenerationsBlocks.RICH_SOIL_3);
        registerBlockItem(GenerationsBlocks.RICH_SOIL_4);

        //GenerationsPokeDolls.POKEDOLLS.forEach(this::registerPokeDoll);

        /*
        registerBlockItemParticle(GenerationsBlocks.POKECENTER_SCARLET_SIGN, "sign");
        GenerationsDecorationBlocks.DECORATIONS.forEach(block -> {
            if (block.get() instanceof BeanBagBlock) registerBlockItemParticle(block, "bean_bags");
            //else if (block.get() instanceof VendingMachineBlock || block.get() instanceof UmbrellaBlock || block.get() instanceof PastelBeanBagBlock) registerNoModel(block);
            else registerBlockItemParticle(block, "decorations");
        });

         */

        //GenerationsShrines.SHRINES.forEach(block -> registerBlockItemParticle(block, "shrines"));
//      GenerationsUtilityBlocks.PC_BLOCKS.forEach(block -> registerBlockItemParticle(block.get().getBlock(), "utility_blocks/pc"));
        //registerBlockItemParticle(GenerationsUtilityBlocks.TRASH_CAN, "utility_blocks");
        //registerBlockItemParticle(GenerationsUtilityBlocks.BREEDER, "utility_blocks");
        //registerBlockItemParticle(GenerationsUtilityBlocks.BOX, "utility_blocks");
        //registerBlockItemParticle(GenerationsUtilityBlocks.COOKING_POT, "utility_blocks");
        //registerNoModel(GenerationsUtilityBlocks.PC);
        //registerNoModel(GenerationsUtilityBlocks.CLOCK);
        //registerNoModel(GenerationsUtilityBlocks.HEALER);

        registerInfestedBlock(GenerationsBlocks.INFESTED_CHARGE_STONE);
        registerInfestedBlock(GenerationsBlocks.INFESTED_VOLCANIC_STONE);
        registerInfestedBlock(GenerationsBlocks.INFESTED_CHARGE_COBBLESTONE);
        registerInfestedBlock(GenerationsBlocks.INFESTED_VOLCANIC_COBBLESTONE);
        registerInfestedBlock(GenerationsBlocks.INFESTED_CHARGE_STONE_BRICKS);
        registerInfestedBlock(GenerationsBlocks.INFESTED_VOLCANIC_STONE_BRICKS);
        registerInfestedBlock(GenerationsBlocks.INFESTED_MOSSY_CHARGE_STONE_BRICKS);
        registerInfestedBlock(GenerationsBlocks.INFESTED_MOSSY_VOLCANIC_STONE_BRICKS);
        registerInfestedBlock(GenerationsBlocks.INFESTED_CRACKED_CHARGE_STONE_BRICKS);
        registerInfestedBlock(GenerationsBlocks.INFESTED_CRACKED_VOLCANIC_STONE_BRICKS);
        registerInfestedBlock(GenerationsBlocks.INFESTED_CHISELED_CHARGE_STONE_BRICKS);
        registerInfestedBlock(GenerationsBlocks.INFESTED_CHISELED_VOLCANIC_STONE_BRICKS);

        registerDripStone(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE);
    }

    private void registerNoModel( RegistrySupplier<? extends Block> block) {
        this.simpleBlock(block.get(), ConfiguredModel.builder().modelFile(models().getBuilder(block.getId().getPath())).build());
    }

    private void registerSoftSoil() {
        getVariantBuilder(GenerationsBlocks.SOFT_SOIL.get()).forAllStates(state -> {
            SoftSoilBlock.Mulch mulch = state.getValue(SoftSoilBlock.MULCH);

            if(mulch != SoftSoilBlock.Mulch.NONE)
                return ConfiguredModel.builder().modelFile(models().cubeAll("block/soft_soil_" + mulch.getSerializedName(), GenerationsCore.id("block/soft_soil_" + mulch.getSerializedName()))).build();
            else return ConfiguredModel.builder().modelFile(models().cubeAll("block/soft_soil", GenerationsCore.id("block/soft_soil"))).build();

        });
    }

    private void registerPallet(@NotNull RegistrySupplier<Block> block, RegistrySupplier<SlabBlock> slab, RegistrySupplier<StairBlock> stairs, RegistrySupplier<WallBlock> wall, RegistrySupplier<ButtonBlock> button, RegistrySupplier<PressurePlateBlock> pressurePlate, boolean dropSelf){
        if(!registered(block.get())) registerBlockItem(block);
        if (dropSelf) dropSelfList.add(block.get());

        if (slab != null) {
            if(!registered(slab.get())) registerSlab(slab.get(), block.get());
            if (dropSelf) dropSelfList.add(slab.get());
        }
        if (stairs != null) {
            if(!registered(stairs.get())) registerStairs(stairs.get(), block.get());
            if (dropSelf) dropSelfList.add(stairs.get());
        }
        if (wall != null) {
            if(!registered(wall.get())) registerWall(wall.get(), block.get());
            if (dropSelf) dropSelfList.add(wall.get());
        }
        if (button != null) {
            if(!registered(button.get())) registerButton(button.get(), block.get());
            if (dropSelf) dropSelfList.add(button.get());
        }
        if (pressurePlate != null) {
            if(!registered(pressurePlate.get())) registerPressurePlate(pressurePlate.get(), block.get());
            if (dropSelf) dropSelfList.add(pressurePlate.get());
        }
    }


    private void registerSandStonePallet(@NotNull RegistrySupplier<Block> sandstone, RegistrySupplier<SlabBlock> slab, RegistrySupplier<StairBlock> stairs, RegistrySupplier<WallBlock> wall, RegistrySupplier<Block> smooth, RegistrySupplier<SlabBlock> smoothslab, RegistrySupplier<StairBlock> smoothstair, RegistrySupplier<WallBlock> smoothWall, RegistrySupplier<Block> chiseled, RegistrySupplier<Block> cutSandstone, RegistrySupplier<SlabBlock> cutSlab){
        if(!registered(sandstone.get())) registerSandStone(sandstone);
        dropSelfList.add(sandstone.get());
        if (slab != null) {
            if(!registered(slab.get())) registerSandStoneSlab(slab, sandstone);
            dropSelfList.add(slab.get());
        }
        if (stairs != null) {
            if(!registered(stairs.get())) registerSandStoneStairs(stairs, sandstone);
            dropSelfList.add(stairs.get());
        }
        if (wall != null) {
            if(!registered(wall.get())) registerWall(wall.get(), sandstone.get());
            dropSelfList.add(wall.get());
        }
        ResourceLocation top = GenerationsCore.id("block/" + sandstone.getId().getPath() + "_top");
        if (smooth != null){

            BlockModelBuilder smoothsandstoneBlock = models().cubeAll(smooth.getId().getPath() ,GenerationsCore.id("block/" + sandstone.getId().getPath() + "_top"));
            if(!registered(smooth.get()))
                simpleBlockWithItem(smooth.get(), smoothsandstoneBlock);

            dropSelfList.add(smooth.get());
            if (smoothslab != null){
                if(!registered(smoothslab.get())) {
                    slabBlock(smoothslab.get(), GenerationsCore.id("block/" + smooth.getId().getPath()), top, top, top);
                    simpleBlockItem(smoothslab.get(), models().slab(smoothslab.getId().getPath(), top, top, top));
                }
                dropSelfList.add(smoothslab.get());
            }
            if (smoothstair != null){
                if(!registered(smoothstair.get())) {
                    stairsBlock(smoothstair.get(), top);
                    simpleBlockItem(smoothstair.get(), models().stairs(smoothstair.getId().getPath(), top, top, top));
                }
                dropSelfList.add(smoothstair.get());
            }
            if (smoothWall != null){
                if(!registered(smoothWall.get())) {
                    wallBlock(smoothWall.get(), top);
                    simpleBlockItem(smoothWall.get(), models().wallInventory(smoothWall.getId().getPath(), top));
                }
                dropSelfList.add(smoothWall.get());
            }

            if(!registered(chiseled.get())) cubeColumn(chiseled, top);
        if (cutSandstone != null){
            if(!registered(cutSandstone.get())) cubeColumn(cutSandstone, top);
            if (cutSlab != null){
                if(!registered(cutSlab.get())) {
                    slabBlock(cutSlab.get(), GenerationsCore.id("block/" + cutSandstone.getId().getPath()), top, GenerationsCore.id("block/" + cutSandstone.getId().getPath()), top);
                    simpleBlockItem(cutSlab.get(), models().slab(cutSlab.getId().getPath(), GenerationsCore.id("block/" + cutSandstone.getId().getPath()), top, top));
                }
                dropSelfList.add(cutSlab.get());
            }
        }
        }
    }

    private void cubeColumn(RegistrySupplier<Block> cutSandstone, ResourceLocation top) {
        if (cutSandstone != null){
            BlockModelBuilder cutBlock = models().cubeColumn(cutSandstone.getId().getPath(), GenerationsCore.id("block/" + cutSandstone.getId().getPath()) , top);
            simpleBlockWithItem(cutSandstone.get(), cutBlock);
            dropSelfList.add(cutSandstone.get());
        }
    }

    private void registerApricorn(RegistrySupplier<Block> apricorn) {
        simpleBlock(apricorn.get(), models().cross("block/" + apricorn.getId().getPath(), GenerationsCore.id("blocks/" + apricorn.getId().getPath())));
    }

    private void registerBerryBush(RegistrySupplier<Block> bush) {
        BerryType.EnumBerryColor color = ((GenerationsBerryBushBlock) bush.get()).getBerryType().getColor();
        getVariantBuilder(bush.get())
                .partialState().with(GenerationsBerryBushBlock.AGE, 0).modelForState().modelFile(models().cross("block/" + color.name().toLowerCase(Locale.ENGLISH) + "_berry_bush_stage0", GenerationsCore.id("block/" + color.name().toLowerCase(Locale.ENGLISH) + "_berry_bush_stage0"))).addModel()
                .partialState().with(GenerationsBerryBushBlock.AGE, 1).modelForState().modelFile(models().cross("block/" + color.name().toLowerCase(Locale.ENGLISH) + "_berry_bush_stage1", GenerationsCore.id("block/" + color.name().toLowerCase(Locale.ENGLISH) + "_berry_bush_stage1"))).addModel()
                .partialState().with(GenerationsBerryBushBlock.AGE, 2).modelForState().modelFile(models().cross("block/" + bush.getId().getPath() + "_stage2", GenerationsCore.id("block/" + bush.getId().getPath() + "_stage2"))).addModel()
                .partialState().with(GenerationsBerryBushBlock.AGE, 3).modelForState().modelFile(models().cross("block/" + bush.getId().getPath() + "_stage3", GenerationsCore.id("block/" + bush.getId().getPath() + "_stage3"))).addModel();
    }

    public void registerStairs(StairBlock stairs, Block texturedBlock) {
        ResourceLocation texture = ModelLocationUtils.getModelLocation(texturedBlock);
        stairsBlock(stairs, texture);
        simpleBlockItem(stairs, itemModels().stairs("block/" + key(stairs).getPath(), texture, texture, texture));
    }

    public void registerSlab(SlabBlock slab, Block texturedBlock) {
        ResourceLocation texture = ModelLocationUtils.getModelLocation(texturedBlock);
        slabBlock(slab, texture, texture);
        simpleBlockItem(slab, itemModels().slab("block/" + key(slab).getPath(), texture, texture, texture));
    }

    public void registerWall(WallBlock wall, Block texturedBlock) {
        ResourceLocation texture = ModelLocationUtils.getModelLocation(texturedBlock);
        wallBlock(wall, texture);
        simpleBlockItem(wall, itemModels().wallInventory("block/" + key(wall).getPath(), texture));
    }

    public void registerFence(FenceBlock fence, Block block) {
        ResourceLocation texture;
        if (block == null) texture = ModelLocationUtils.getModelLocation(fence);
        else texture = ModelLocationUtils.getModelLocation(block);

        fenceBlock(fence, texture);
        simpleBlockItem(fence, itemModels().fenceInventory("block/" + key(fence).getPath(), texture));
    }

    public void registerGate(FenceGateBlock gate, FenceBlock fence, Block block) {
        ResourceLocation texture;
        if (block == null)
        {
            ResourceLocation fenceId = key(fence);
            texture = ModelLocationUtils.getModelLocation(fence);
            simpleBlockItem(gate, itemModels().fenceGate("block/" + fenceId.getPath(), texture));
        }
        else
        {
            ResourceLocation gateId = key(gate);
            texture = ModelLocationUtils.getModelLocation(block);
            simpleBlockItem(gate, itemModels().fenceGate("block/" + gateId.getPath(), texture));
        }

        fenceGateBlock(gate, texture);
    }

    private void registerDoor(DoorBlock door) {
        ResourceLocation blockId = key(door);
        doorBlockWithRenderType(door, new ResourceLocation(blockId.getNamespace(), "block/door/" + blockId.getPath() + "_bottom"), new ResourceLocation(blockId.getNamespace(), "block/door/" + blockId.getPath() + "_top"), "cutout");
    }

    public void registerPressurePlate(PressurePlateBlock pressurePlate, Block texturedBlock) {
        ResourceLocation texture = ModelLocationUtils.getModelLocation(texturedBlock);
        pressurePlateBlock(pressurePlate, texture);
        itemModels().pressurePlate(key(pressurePlate).getPath(), texture);
    }

    public void registerButton(Block button, Block texturedBlock) {
        ResourceLocation buttonId = key(button);
        ResourceLocation textureBlockId = key(texturedBlock);

        ResourceLocation texture = new ResourceLocation(textureBlockId.getNamespace(), "block/" + textureBlockId.getPath());
        buttonBlock((ButtonBlock) button, texture);
        itemModels().buttonInventory(buttonId.getPath(), texture);
    }

    public void registerTrapDoor(TrapDoorBlock trapDoor, Block texturedBlock){
        ResourceLocation texture = ModelLocationUtils.getModelLocation(texturedBlock);
        ResourceLocation trapDoorId = key(trapDoor);
        trapdoorBlockWithRenderType(trapDoor, texture,true, "cutout");
        itemModels().trapdoorBottom(trapDoorId.getPath(), texture);
    }

    public void registerTrapDoor(RegistrySupplier<TrapDoorBlock> trapDoor){registerTrapDoor(trapDoor.get(), trapDoor.get());}

    public void registerSign(StandingSignBlock sign, WallSignBlock wallsign, Block plank){signBlock(sign, wallsign, blockTexture(plank));}

    public void registerHangingSign(CeilingHangingSignBlock sign, WallHangingSignBlock wallsign, Block plank){
        ModelFile thing = this.models().sign(key(sign).getPath(), blockTexture(plank));
        simpleBlock(sign, thing);
        simpleBlock(wallsign, thing);
    }

    public void registerCrossBlock(RegistrySupplier<GenerationsMushroomBlock> crossBlock) {
        getVariantBuilder(crossBlock.get()).forAllStates(state ->
                ConfiguredModel.builder()
                        .modelFile(models().cross(crossBlock.getId().getPath(), blockTexture(crossBlock.get())).renderType("cutout"))
                        .build());

        MUSHROOM_BLOCKS.add(crossBlock.get());
    }

    public void registerLog(RegistrySupplier<RotatedPillarBlock> log) {
        String name = log.getId().getPath();
        BlockModelBuilder logBlock = models().withExistingParent(log.getId().toString(), mcLoc("block/oak_log"))
                .texture("side", GenerationsCore.id("block/" + name))
                .texture("top", GenerationsCore.id("block/" + name + "_top"));
        logBlock(log.get());
        simpleBlockItem(log.get(), logBlock);
    }

    public void registerApricornLeaves(RegistrySupplier<Block> block) {simpleBlockWithItem(block.get(), models().cubeAll(block.getId().getPath(), blockTexture(Blocks.OAK_LEAVES)));}

    public void registerBlockItem(RegistrySupplier<Block> block) {registerBlockItem(block.get());}

    public void registerBlockItem(Block block) {simpleBlockWithItem(block, cubeAll(block));}

    public void registerBlockItemParticle(RegistrySupplier<? extends Block> block, String name) {
        ResourceLocation textureId = key(block.get()).withPrefix("item/blocks/" + name + "/");
        simpleBlock(block.get(), models().sign(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), textureId));
        itemModels().singleTexture(block.getId().getPath(), new ResourceLocation("minecraft:item/generated"), "layer0", textureId);
    }

    public void registerBlockItemParticle(Block block, String name) {
        ResourceLocation blockId = key(block);
        ResourceLocation textureId = blockId.withPrefix("item/blocks/" + name + "/");
        simpleBlock(block, models().sign(blockId.getPath(), textureId));
    }

    public void registerPokeDoll(RegistrySupplier<Block> block) {
        ResourceLocation blockId = ForgeRegistries.BLOCKS.getKey(block.get());
        assert blockId != null;
        ResourceLocation textureId =  new ResourceLocation(blockId.getNamespace(), "item/dolls/" + blockId.getPath().replace("shiny_", "shiny/"));

        simpleBlock(block.get(), models().sign(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath(), textureId));

        ResourceLocation key = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get()), "Tried to create json model for unregistered Item.");
        ResourceLocation texPath = GenerationsCore.id("item/dolls/" + key.getPath());
        if(key.getPath().startsWith("shiny_"))
            texPath = GenerationsCore.id("item/dolls/shiny/" + key.getPath().replace("shiny_", ""));


        itemModels().getBuilder(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(block.get().asItem())).toString()).parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", texPath);
    }

    public void registerSandStone(RegistrySupplier<Block> block) {
        String name = block.getId().getPath();
        simpleBlockWithItem(block.get(), models().cubeBottomTop(name, GenerationsCore.id("block/" + name), GenerationsCore.id("block/" + name + "_bottom"), GenerationsCore.id("block/" + name + "_top")));
    }

    public void registerSandStoneStairs(RegistrySupplier<StairBlock> stairs, RegistrySupplier<Block> block) {
        stairsBlock(stairs.get(),  GenerationsCore.id("block/" + block.getId().getPath()), GenerationsCore.id("block/" + block.getId().getPath() + "_bottom"), GenerationsCore.id("block/" + block.getId().getPath() +"_top"));
        simpleBlockItem(stairs.get(), itemModels().stairs("block/" + stairs.getId().getPath(), GenerationsCore.id("block/" + block.getId().getPath()), GenerationsCore.id("block/" + block.getId().getPath() + "_bottom"), GenerationsCore.id("block/" + block.getId().getPath() +"_top")));
    }

    public void registerSandStoneSlab(RegistrySupplier<SlabBlock> slab, RegistrySupplier<Block> block) {
        slabBlock(slab.get(), GenerationsCore.id("block/" + block.getId().getPath()), GenerationsCore.id("block/" + block.getId().getPath()), GenerationsCore.id("block/" + block.getId().getPath() + "_bottom"), GenerationsCore.id("block/" + block.getId().getPath() +"_top"));
        simpleBlockItem(slab.get(), itemModels().slab("block/" + slab.getId().getPath(), GenerationsCore.id("block/" + block.getId().getPath()), GenerationsCore.id("block/" + block.getId().getPath() + "_bottom"), GenerationsCore.id("block/" + block.getId().getPath() +"_top")));
    }

    private void registerGlassBlock(RegistrySupplier<GlassBlock> block) {
        getVariantBuilder(block.get()).forAllStates(state ->
                ConfiguredModel.builder()
                        .modelFile(models().cubeAll(block.getId().getPath(), modLoc("block/" + block.getId().getPath())).renderType("cutout_mipped"))
                        .build());
        simpleBlockItem(block.get(), cubeAll(block.get()));
    }

    private void registerOreBlocks() {
        GenerationsOres.ORES.forEach(ore -> {
            String name = ore.getId().getPath();
            simpleBlockWithItem(ore.get(), models().singleTexture(name, mcLoc("block/cube_all"), "all", GenerationsCore.id("block/ores/" + name)));
        });
    }

    private void pokeBallChests(RegistrySupplier<GenericChestBlock> chest, RegistrySupplier<Item> pokeball){
        simpleBlock(chest.get(), models().getBuilder(chest.getId().getPath()).texture("particle", GenerationsCore.id("item/pokeballs/" + pokeball.getId().getPath())));
    }


    @SuppressWarnings("SameParameterValue")
    private void registerPumpkin(RegistrySupplier<PumpkinBlock> pumpkin) {
        BlockModelBuilder pumpkinModel = models().withExistingParent(pumpkin.getId().toString(), mcLoc("block/pumpkin"))
                .texture("side", GenerationsCore.id("block/" + pumpkin.getId().getPath() + "_side"))
                .texture("end", GenerationsCore.id("block/" + pumpkin.getId().getPath() + "_end"));
        simpleBlockWithItem(pumpkin.get(), pumpkinModel);
    }


    private void registerFurnace(RegistrySupplier<? extends Block> furnace) {
        BlockModelBuilder off = models().orientable(furnace.getId().getPath(), GenerationsCore.id("block/furnace/" + furnace.getId().getPath() + "_side"), GenerationsCore.id("block/furnace/" + furnace.getId().getPath() + "_front"), GenerationsCore.id("block/furnace/" + furnace.getId().getPath() + "_top"));
        BlockModelBuilder on = models().orientable(furnace.getId().getPath() + "_on", GenerationsCore.id("block/furnace/" + furnace.getId().getPath() + "_side"), GenerationsCore.id("block/furnace/" + furnace.getId().getPath() + "_front_on"), GenerationsCore.id("block/furnace/" + furnace.getId().getPath() + "_top"));
        horizontalBlock(furnace.get(), state -> state.getValue(FurnaceBlock.LIT) ? on : off);
        simpleBlockItem(furnace.get(), off);
    }

    @SuppressWarnings("SameParameterValue")
    private void registerCarvedPumpkin(RegistrySupplier<CursedCarvedPumpkinBlock> carvedPumpkin, RegistrySupplier<PumpkinBlock> pumpkin){
        BlockModelBuilder pumpkinModel = models().withExistingParent(carvedPumpkin.getId().toString(), mcLoc("block/carved_pumpkin"))
                .texture("side", GenerationsCore.id("block/" + pumpkin.getId().getPath() + "_side"))
                .texture("top", GenerationsCore.id("block/" + pumpkin.getId().getPath() + "_end"))
                .texture("front", GenerationsCore.id("block/" + carvedPumpkin.getId().getPath()));
        horizontalBlock(carvedPumpkin.get(), pumpkinModel);
        simpleBlockItem(carvedPumpkin.get(), pumpkinModel);
    }

    private void registerCraftingTable(RegistrySupplier<GenerationsCraftingTableBlock> table, RegistrySupplier<Block> plank){
        BlockModelBuilder tableModel = models().withExistingParent(table.getId().toString(), mcLoc("block/cube"))
                .texture("down", GenerationsCore.id("block/" + plank.getId().getPath()))
                .texture("east", GenerationsCore.id("block/crafting_table/" + table.getId().getPath() + "_side"))
                .texture("north", GenerationsCore.id("block/crafting_table/" + table.getId().getPath() + "_front"))
                .texture("particle", GenerationsCore.id("block/crafting_table/" + table.getId().getPath() + "_front"))
                .texture("south", GenerationsCore.id("block/crafting_table/" + table.getId().getPath() + "_side"))
                .texture("up", GenerationsCore.id("block/crafting_table/" + table.getId().getPath() + "_top"))
                .texture("west", GenerationsCore.id("block/crafting_table/" + table.getId().getPath() + "_front"));
        simpleBlockWithItem(table.get().builtInRegistryHolder().get(), tableModel);
    }

    private void registerInfestedBlock(RegistrySupplier<InfestedBlock> block){
        this.models().withExistingParent(block.getId().toString().replace("infested_", "").concat("_mirrored"), mcLoc("block/cube_mirrored_all"))
                .texture("all", blockTexture(block.get().getHostBlock()));
        ModelFile.UncheckedModelFile normal = new ModelFile.UncheckedModelFile(ModelLocationUtils.getModelLocation(block.get().getHostBlock()));
        ModelFile.UncheckedModelFile mirrored = new ModelFile.UncheckedModelFile(blockTexture(block.get().getHostBlock()) + "_mirrored");

        getVariantBuilder(block.get()).partialState()
                .addModels(ConfiguredModel.builder().modelFile(normal)
                        .nextModel().modelFile(mirrored)
                        .nextModel().modelFile(normal).rotationY(180)
                        .nextModel().modelFile(mirrored).rotationY(180)
                        .build());
        itemModels().withExistingParent(block.getId().getPath(), blockTexture(block.get().getHostBlock()));
    }

    private void registerDripStone(RegistrySupplier<PointedChargeDripstoneBlock> dripstone){
        String name = dripstone.getId().toString();
        this.models().withExistingParent(name.concat("_down_base"), mcLoc("block/pointed_dripstone"))
                .texture("cross", GenerationsCore.id("block/" + dripstone.getId().getPath() + "_down_base")).renderType("cutout");
        this.models().withExistingParent(name.concat("_down_middle"), mcLoc("block/pointed_dripstone"))
                .texture("cross", GenerationsCore.id("block/" + dripstone.getId().getPath() + "_down_middle")).renderType("cutout");
        this.models().withExistingParent(name.concat("_down_tip"), mcLoc("block/pointed_dripstone"))
                .texture("cross", GenerationsCore.id("block/" + dripstone.getId().getPath() + "_down_tip")).renderType("cutout");
        this.models().withExistingParent(name.concat("_down_tip_merge"), mcLoc("block/pointed_dripstone"))
                .texture("cross", GenerationsCore.id("block/" + dripstone.getId().getPath() + "_down_tip_merge")).renderType("cutout");
        this.models().withExistingParent(name.concat("_down_frustum"), mcLoc("block/pointed_dripstone"))
                .texture("cross", GenerationsCore.id("block/" + dripstone.getId().getPath() + "_down_frustum")).renderType("cutout");
        this.models().withExistingParent(name.concat("_up_base"), mcLoc("block/pointed_dripstone"))
                .texture("cross", GenerationsCore.id("block/" + dripstone.getId().getPath() + "_up_base")).renderType("cutout");
        this.models().withExistingParent(name.concat("_up_middle"), mcLoc("block/pointed_dripstone"))
                .texture("cross", GenerationsCore.id("block/" + dripstone.getId().getPath() + "_up_middle")).renderType("cutout");
        this.models().withExistingParent(name.concat("_up_tip"), mcLoc("block/pointed_dripstone"))
                .texture("cross", GenerationsCore.id("block/" + dripstone.getId().getPath() + "_up_tip")).renderType("cutout");
        this.models().withExistingParent(name.concat("_up_tip_merge"), mcLoc("block/pointed_dripstone"))
                .texture("cross", GenerationsCore.id("block/" + dripstone.getId().getPath() + "_up_tip_merge")).renderType("cutout");
        this.models().withExistingParent(name.concat("_up_frustum"), mcLoc("block/pointed_dripstone"))
                .texture("cross", GenerationsCore.id("block/" + dripstone.getId().getPath() + "_up_frustum")).renderType("cutout");
        this.models().withExistingParent(name, mcLoc("block/pointed_dripstone"));
    }

    private void registerBlockFamily(BlockFamily family) {
        registerBlockItem(family.getBaseBlock());
        family.getVariants().keySet().forEach(variant -> proccessVariant(variant, family));
    }

    private void proccessVariant(BlockFamily.Variant variant, BlockFamily family) {
        Block original = family.getBaseBlock();
        Block variantTarget = family.getVariants().get(variant);
        switch (variant) {
            case BUTTON -> registerButton(variantTarget, original);
            case CHISELED, CRACKED, CUT -> simpleBlockWithItem(variantTarget, cubeAll(variantTarget));
            case DOOR -> registerDoor((DoorBlock) variantTarget);
            case FENCE -> registerFence((FenceBlock) variantTarget, original);
            case FENCE_GATE -> registerGate((FenceGateBlock) variantTarget, (FenceBlock) family.get(BlockFamily.Variant.FENCE), original);
            case SIGN -> registerSign((StandingSignBlock) variantTarget, (WallSignBlock) family.get(BlockFamily.Variant.WALL_SIGN), original);
            case SLAB -> registerSlab((SlabBlock) variantTarget, original);
            case STAIRS -> registerStairs((StairBlock) variantTarget, original);
            case PRESSURE_PLATE -> registerPressurePlate((PressurePlateBlock) variantTarget, original);
            case TRAPDOOR -> registerTrapDoor((TrapDoorBlock) variantTarget, original);
            case WALL -> registerWall((WallBlock) variantTarget, original);
        }
    }

    private void registerWoodPallet(RegistrySupplier<RotatedPillarBlock> Log, RegistrySupplier<RotatedPillarBlock> StrippedLog, RegistrySupplier<Block> Plank, RegistrySupplier<SlabBlock> slab, RegistrySupplier<StairBlock> stair, RegistrySupplier<ButtonBlock> button, RegistrySupplier<PressurePlateBlock> pressurePlate, RegistrySupplier<DoorBlock> door, RegistrySupplier<TrapDoorBlock> trapDoor, RegistrySupplier<Block> wood, RegistrySupplier<Block> StrippedWood, RegistrySupplier<FenceBlock> fence, RegistrySupplier<FenceGateBlock> gate, RegistrySupplier<StandingSignBlock> sign, RegistrySupplier<WallSignBlock> wallSign, RegistrySupplier<GenerationsCraftingTableBlock> craftingTable, RegistrySupplier<CeilingHangingSignBlock> hangingSignBlock, RegistrySupplier<WallHangingSignBlock> wallHangingSignBlock){
        if(!registered(Log.get())) registerLog(Log);
        if(!registered(StrippedLog.get())) registerLog(StrippedLog);
        if(!registered(Plank.get())) registerBlockItem(Plank);
        if(!registered(wood.get())) registerBlockItem(wood);
        if(!registered(StrippedWood.get())) registerBlockItem(StrippedWood);
        if(!registered(slab.get())) registerSlab(slab.get(), Plank.get());
        if(!registered(stair.get())) registerStairs(stair.get(), Plank.get());
        if(!registered(button.get())) registerButton(button.get(), Plank.get());
        if(!registered(pressurePlate.get())) registerPressurePlate(pressurePlate.get(), Plank.get());
        if(!registered(door.get())) registerDoor(door.get());
        if(!registered(trapDoor.get())) registerTrapDoor(trapDoor);
        if(!registered(gate.get())) registerGate(gate.get(), null, Plank.get());
        if(!registered(fence.get())) registerFence(fence.get(), Plank.get());
        if(!registered(sign.get())) registerSign(sign.get(), wallSign.get(), Plank.get());
        if(!registered(hangingSignBlock.get())) registerHangingSign(hangingSignBlock.get(), wallHangingSignBlock.get(), StrippedLog.get());
        if(!registered(craftingTable.get())) registerCraftingTable(craftingTable, Plank);
    }
}
