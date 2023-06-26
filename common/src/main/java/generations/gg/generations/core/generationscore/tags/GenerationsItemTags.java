package generations.gg.generations.core.generationscore.tags;

import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class GenerationsItemTags {

    public static final TagKey<Item> GENERATIONSITEMS = create("generationsitems");
    public static final TagKey<Item> PC = create("pc");
    public static final TagKey<Item> CLOCK = create("clock");
    public static final TagKey<Item> HEALER = create("healer");
    public static final TagKey<Item> VENDING_MACHINE = create("vending_machine");
    public static final TagKey<Item> UMBRELLA = create("umbrella");
    public static final TagKey<Item> PASTEL_BEAN_BAG = create("pastel_bean_bag");
    public static final TagKey<Item> POKEBALL_RUG = create("pokeball_rug");
    public static final TagKey<Item> ULTRA_DARK_LOGS = create("ultra_dark_logs");
    public static final TagKey<Item> ULTRA_JUNGLE_LOGS = create("ultra_jungle_logs");
    public static final TagKey<Item> GHOST_LOGS = create("ghost_logs");
    public static final TagKey<Item> CHARGE_STONE_BRICKS = create("charge_stone_bricks");
    public static final TagKey<Item> VOLCANIC_STONE_BRICKS = create("volcanic_stone_bricks");

    public static final TagKey<Item> POKEMAIL = create("pokemail");
    public static final TagKey<Item> CLOSED_POKEMAIL = create("sealed_pokemail");
    public static final TagKey<Item> POKEBALLS = create("pokeballs");

    //Ore Tags
    public static final TagKey<Item> GENERATIONSORES = create("generationsores");
    public static final TagKey<Item> ALUMINUM_ORES = create("ores/aluminum_ores");
    public static final TagKey<Item> SAPPHIRE_ORES = create("ores/sapphire_ores");
    public static final TagKey<Item> RUBY_ORES = create("ores/ruby_ores");
    public static final TagKey<Item> CRYSTAL_ORES = create("ores/crystal_ores");
    public static final TagKey<Item> SILICON_ORES = create("ores/silicon_ores");
    public static final TagKey<Item> Z_CRYSTAL_ORES = create("ores/z_crystal_ores");
    public static final TagKey<Item> FOSSIL_ORES = create("ores/fossil_ores");
    public static final TagKey<Item> DAWN_STONE_ORES = create("ores/dawn_stone_ores");
    public static final TagKey<Item> DUSK_STONE_ORES = create("ores/dusk_stone_ores");
    public static final TagKey<Item> FIRE_STONE_ORES = create("ores/fire_stone_ores");
    public static final TagKey<Item> ICE_STONE_ORES = create("ores/ice_stone_ores");
    public static final TagKey<Item> LEAF_STONE_ORES = create("ores/leaf_stone_ores");
    public static final TagKey<Item> SHINY_STONE_ORES = create("ores/shiny_stone_ores");
    public static final TagKey<Item> SUN_STONE_ORES = create("ores/sun_stone_ores");
    public static final TagKey<Item> THUNDER_STONE_ORES = create("ores/thunder_stone_ores");
    public static final TagKey<Item> WATER_STONE_ORES = create("ores/water_stone_ores");
    public static final TagKey<Item> MOON_STONE_ORES = create("ores/moon_stone_ores");
    public static final TagKey<Item> BLACK_TUMBLESTONE_ORES = create("ores/black_tumblestone_ores");
    public static final TagKey<Item> RARE_TUMBLESTONE_ORES = create("ores/rare_tumblestone_ores");
    public static final TagKey<Item> SKY_TUMBLESTONE_ORES = create("ores/sky_tumblestone_ores");
    public static final TagKey<Item> TUMBLESTONE_ORES = create("ores/tumblestone_ores");
    public static final TagKey<Item> MEGASTONE_ORES = create("ores/megastone_ores");
    public static final TagKey<Item> METEORITE_ORES = create("ores/meteorite_ores");
    
    public static final TagKey<Item> MARBLE = create("marble");
    public static final TagKey<Item> POKEBRICKS = create("pokebricks");
    public static final TagKey<Item> ULTRA = create("ultra");

    public static final TagKey<Item> WALKMONS = create("walkmons");
    public static final TagKey<Item> HAMMERS = create("hammers");
    public static final TagKey<Item> SHEARS = create("shears");

    private static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM, GenerationsCore.id(name));
    }
}
