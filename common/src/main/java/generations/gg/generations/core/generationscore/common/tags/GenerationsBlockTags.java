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

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, GenerationsCore.id(name));
    }

}
