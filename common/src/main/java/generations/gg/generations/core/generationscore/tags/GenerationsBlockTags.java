package generations.gg.generations.core.generationscore.tags;

import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class GenerationsBlockTags {
    public static final TagKey<Block> POKEBRICKS = create(GenerationsCore.id("pokebricks"));
    public static final TagKey<Block> GENERATIONSORES = create(GenerationsCore.id("generationsores"));
    public static final TagKey<Block> SHRINES = create(GenerationsCore.id("shrines"));
    public static final TagKey<Block> MARBLE = create(GenerationsCore.id("marble"));

    public static final TagKey<Block> ALUMINUM_ORES = create(GenerationsCore.id("aluminum_ores"));
    public static final TagKey<Block> SAPPHIRE_ORES = create(GenerationsCore.id("sapphire_ores"));
    public static final TagKey<Block> RUBY_ORES = create(GenerationsCore.id("ruby_ores"));
    public static final TagKey<Block> SILICON_ORES = create(GenerationsCore.id("silicon_ores"));
    public static final TagKey<Block> Z_CRYSTAL_ORES = create(GenerationsCore.id("z_crystal_ores"));
    public static final TagKey<Block> FOSSIL_ORES = create(GenerationsCore.id("fossil_ores"));
    public static final TagKey<Block> ULTRA_DARK_LOGS = create(GenerationsCore.id("ultra_dark_logs"));
    public static final TagKey<Block> ULTRA_JUNGLE_LOGS = create(GenerationsCore.id("ultra_jungle_logs"));
    public static final TagKey<Block> GHOST_LOGS = create(GenerationsCore.id("ghost_logs"));
    public static final TagKey<Block> CHARGE_STONE_BRICKS = create(GenerationsCore.id("charge_stone_bricks"));
    public static final TagKey<Block> VOLCANIC_STONE_BRICKS = create(GenerationsCore.id("volcanic_stone_bricks"));
    public static final TagKey<Block> POKEBALL_CHESTS = create(GenerationsCore.id("pokeball_chests"));

    private static TagKey<Block> create(ResourceLocation name) {
        return TagKey.create(Registries.BLOCK, name);
    }

}
