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

    public static final TagKey<Block> ALUMINUM_ORES = create("aluminum_ores");
    public static final TagKey<Block> SAPPHIRE_ORES = create("sapphire_ores");
    public static final TagKey<Block> RUBY_ORES = create("ruby_ores");
    public static final TagKey<Block> SILICON_ORES = create("silicon_ores");
    public static final TagKey<Block> Z_CRYSTAL_ORES = create("z_crystal_ores");
    public static final TagKey<Block> FOSSIL_ORES = create("fossil_ores");
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
