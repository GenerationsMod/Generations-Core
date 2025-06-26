package generations.gg.generations.core.generationscore.common.tags;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class GenerationsBlockTags {
    public static final TagKey<Block> POKEBRICKS = create("pokebricks");
    public static final TagKey<Block> SHRINES = create("shrines");
    public static final TagKey<Block> MARBLE = create("marble");
    public static final TagKey<Block> ULTRA = create("ultra");

    public static final TagKey<Block> GENERATIONSORES = create("generationsores");
    public static final TagKey<Block> SAPPHIRE_ORES = create("ores/sapphire_ores");
    public static final TagKey<Block> RUBY_ORES = create("ores/ruby_ores");
    public static final TagKey<Block> CRYSTAL_ORES = create("ores/crystal_ores");
    public static final TagKey<Block> SILICON_ORES = create("ores/silicon_ores");
    public static final TagKey<Block> Z_CRYSTAL_ORES = create("ores/z_crystal_ores");
    public static final TagKey<Block> MEGASTONE_ORES = create("ores/megastone_ores");
    public static final TagKey<Block> METEORITE_ORES = create("ores/meteorite_ores");
    public static final TagKey<Block> ULTRA_DARK_LOGS = create("ultra_dark_logs");
    public static final TagKey<Block> ULTRA_JUNGLE_LOGS = create("ultra_jungle_logs");
    public static final TagKey<Block> GHOST_LOGS = create("ghost_logs");
    public static final TagKey<Block> CHARGE_STONE_BRICKS = create("charge_stone_bricks");
    public static final TagKey<Block> VOLCANIC_STONE_BRICKS = create("volcanic_stone_bricks");
    public static final TagKey<Block> POKEBALL_CHESTS = create("pokeball_chests");
    public static final TagKey<Block> BALL_DISPLAY_BLOCKS = create("ball_display_blocks");
    public static final TagKey<Block> BALL_LOOTS = create("ball_loots");
    public static final TagKey<Block> POKEDOLLS = create("pokedolls");
    public static final TagKey<Block> REGI_STANDS = create("regi_stands");

    public static final TagKey<Block> TAPU_SUMMONING = create("tapu_summoning");

    public static final TagKey<Block> INCORRECT_FOR_CHARGE_STONE_TOOL = create("incorrect_for_charge_stone_tool");
    public static final TagKey<Block> INCORRECT_FOR_VOLCANIC_STONE_TOOL = create("incorrect_for_volcanic_stone_tool");
    public static final TagKey<Block> INCORRECT_FOR_AMETHYST_TOOL = create("incorrect_for_amethyst_tool");
    public static final TagKey<Block> INCORRECT_FOR_CRYSTAL_TOOL = create("incorrect_for_crystal_tool");
    public static final TagKey<Block> INCORRECT_FOR_SAPPHIRE_TOOL = create("incorrect_for_sapphire_tool");
    public static final TagKey<Block> INCORRECT_FOR_SILICON_TOOL = create("incorrect_for_silicon_tool");
    public static final TagKey<Block> INCORRECT_FOR_RUBY_TOOL = create("incorrect_for_ruby_tool");
    public static final TagKey<Block> INCORRECT_FOR_DAWN_STONE_TOOL = create("incorrect_for_dawn_stone_tool");
    public static final TagKey<Block> INCORRECT_FOR_DUSK_STONE_TOOL = create("incorrect_for_dusk_stone_tool");
    public static final TagKey<Block> INCORRECT_FOR_FIRE_STONE_TOOL = create("incorrect_for_fire_stone_tool");
    public static final TagKey<Block> INCORRECT_FOR_ICE_STONE_TOOL = create("incorrect_for_ice_stone_tool");
    public static final TagKey<Block> INCORRECT_FOR_LEAF_STONE_TOOL = create("incorrect_for_leaf_stone_tool");
    public static final TagKey<Block> INCORRECT_FOR_MOON_STONE_TOOL = create("incorrect_for_moon_stone_tool");
    public static final TagKey<Block> INCORRECT_FOR_SUN_STONE_TOOL = create("incorrect_for_sun_stone_tool");
    public static final TagKey<Block> INCORRECT_FOR_THUNDER_STONE_TOOL = create("incorrect_for_thunder_stone_tool");
    public static final TagKey<Block> INCORRECT_FOR_WATER_STONE_TOOL = create("incorrect_for_water_stone_tool");
    public static final TagKey<Block> INCORRECT_FOR_ULTRITE_TOOL = create("incorrect_for_ultrite_tool");

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, GenerationsCore.id(name));
    }

}
