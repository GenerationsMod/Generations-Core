package generations.gg.generations.core.generationscore.tags;

import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class GenerationsBlockTags {
    public static final TagKey<Block> POKEBRICKS = create("pokebricks");
    public static final TagKey<Block> GENERATIONSORES = create("generationsores");
    public static final TagKey<Block> SHRINES = create("shrines");
    public static final TagKey<Block> MARBLE = create("marble");
    public static final TagKey<Block> ULTRA = create("ultra");

    public static final TagKey<Block> ALUMINUM_ORES = create("aluminum_ores");
    public static final TagKey<Block> SAPPHIRE_ORES = create("sapphire_ores");
    public static final TagKey<Block> RUBY_ORES = create("ruby_ores");
    public static final TagKey<Block> CRYSTAL_ORES = create("crystal_ores");
    public static final TagKey<Block> SILICON_ORES = create("silicon_ores");
    public static final TagKey<Block> Z_CRYSTAL_ORES = create("z_crystal_ores");
    public static final TagKey<Block> FOSSIL_ORES = create("fossil_ores");
    public static final TagKey<Block> DAWN_STONE_ORES = create("dawn_stone_ores");
    public static final TagKey<Block> DUSK_STONE_ORES = create("dusk_stone_ores");
    public static final TagKey<Block> FIRE_STONE_ORES = create("fire_stone_ores");
    public static final TagKey<Block> ICE_STONE_ORES = create("ice_stone_ores");
    public static final TagKey<Block> LEAF_STONE_ORES = create("leaf_stone_ores");
    public static final TagKey<Block> SHINY_STONE_ORES = create("shiny_stone_ores");
    public static final TagKey<Block> SUN_STONE_ORES = create("sun_stone_ores");
    public static final TagKey<Block> THUNDER_STONE_ORES = create("thunder_stone_ores");
    public static final TagKey<Block> WATER_STONE_ORES = create("water_stone_ores");
    public static final TagKey<Block> MOON_STONE_ORES = create("moon_stone_ores");
    public static final TagKey<Block> BLACK_TUMBLESTONE_ORES = create("black_tumblestone_ores");
    public static final TagKey<Block> RARE_TUMBLESTONE_ORES = create("rare_tumblestone_ores");
    public static final TagKey<Block> SKY_TUMBLESTONE_ORES = create("sky_tumblestone_ores");
    public static final TagKey<Block> TUMBLESTONE_ORES = create("tumblestone_ores");
    public static final TagKey<Block> MEGASTONE_ORES = create("megastone_ores");
    public static final TagKey<Block> METEORITE_ORES = create("meteorite_ores");
    public static final TagKey<Block> ULTRA_DARK_LOGS = create("ultra_dark_logs");
    public static final TagKey<Block> ULTRA_JUNGLE_LOGS = create("ultra_jungle_logs");
    public static final TagKey<Block> GHOST_LOGS = create("ghost_logs");
    public static final TagKey<Block> CHARGE_STONE_BRICKS = create("charge_stone_bricks");
    public static final TagKey<Block> VOLCANIC_STONE_BRICKS = create("volcanic_stone_bricks");
    public static final TagKey<Block> POKEBALL_CHESTS = create("pokeball_chests");

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, GenerationsCore.id(name));
    }

}
