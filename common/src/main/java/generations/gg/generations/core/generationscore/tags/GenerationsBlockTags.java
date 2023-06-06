package generations.gg.generations.core.generationscore.tags;

import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class GenerationsBlockTags {
    public static final TagKey<Block> POKEBRICKS = create("pokebricks");
    public static final TagKey<Block> SHRINES = create("shrines");
    public static final TagKey<Block> MARBLE = create("marble");
    public static final TagKey<Block> ULTRA = create("ultra");

    public static final TagKey<Block> GENERATIONSORES = create("generationsores");
    public static final TagKey<Block> ALUMINUM_ORES = create("ores/aluminum_ores");
    public static final TagKey<Block> SAPPHIRE_ORES = create("ores/sapphire_ores");
    public static final TagKey<Block> RUBY_ORES = create("ores/ruby_ores");
    public static final TagKey<Block> CRYSTAL_ORES = create("ores/crystal_ores");
    public static final TagKey<Block> SILICON_ORES = create("ores/silicon_ores");
    public static final TagKey<Block> Z_CRYSTAL_ORES = create("ores/z_crystal_ores");
    public static final TagKey<Block> FOSSIL_ORES = create("ores/fossil_ores");
    public static final TagKey<Block> DAWN_STONE_ORES = create("ores/dawn_stone_ores");
    public static final TagKey<Block> DUSK_STONE_ORES = create("ores/dusk_stone_ores");
    public static final TagKey<Block> FIRE_STONE_ORES = create("ores/fire_stone_ores");
    public static final TagKey<Block> ICE_STONE_ORES = create("ores/ice_stone_ores");
    public static final TagKey<Block> LEAF_STONE_ORES = create("ores/leaf_stone_ores");
    public static final TagKey<Block> SHINY_STONE_ORES = create("ores/shiny_stone_ores");
    public static final TagKey<Block> SUN_STONE_ORES = create("ores/sun_stone_ores");
    public static final TagKey<Block> THUNDER_STONE_ORES = create("ores/thunder_stone_ores");
    public static final TagKey<Block> WATER_STONE_ORES = create("ores/water_stone_ores");
    public static final TagKey<Block> MOON_STONE_ORES = create("ores/moon_stone_ores");
    public static final TagKey<Block> BLACK_TUMBLESTONE_ORES = create("ores/black_tumblestone_ores");
    public static final TagKey<Block> RARE_TUMBLESTONE_ORES = create("ores/rare_tumblestone_ores");
    public static final TagKey<Block> SKY_TUMBLESTONE_ORES = create("ores/sky_tumblestone_ores");
    public static final TagKey<Block> TUMBLESTONE_ORES = create("ores/tumblestone_ores");
    public static final TagKey<Block> MEGASTONE_ORES = create("ores/megastone_ores");
    public static final TagKey<Block> METEORITE_ORES = create("ores/meteorite_ores");
    public static final TagKey<Block> ULTRA_DARK_LOGS = create("ultra_dark_logs");
    public static final TagKey<Block> ULTRA_JUNGLE_LOGS = create("ultra_jungle_logs");
    public static final TagKey<Block> GHOST_LOGS = create("ghost_logs");
    public static final TagKey<Block> CHARGE_STONE_BRICKS = create("charge_stone_bricks");
    public static final TagKey<Block> VOLCANIC_STONE_BRICKS = create("volcanic_stone_bricks");
    public static final TagKey<Block> POKEBALL_CHESTS = create("pokeball_chests");
    public static final TagKey<Block> BALL_DISPLAY_BLOCKS = create("ball_display_blocks");

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, GenerationsCore.id(name));
    }

}
