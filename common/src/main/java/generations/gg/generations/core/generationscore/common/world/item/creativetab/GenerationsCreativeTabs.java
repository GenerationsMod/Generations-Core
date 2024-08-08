package generations.gg.generations.core.generationscore.common.world.item.creativetab;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.registry.registries.DeferredRegister;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.level.block.*;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * Generations Creative Tabs
 * @author Joseph T. McQuigg
 * @author WaterPicker
 * @see CreativeModeTab
 */
public class GenerationsCreativeTabs {

    public static Supplier<CreativeModeTab> RESTORATION;
    public static Supplier<CreativeModeTab> AWARDS;
    public static Supplier<CreativeModeTab> HELD_ITEMS;
    public static Supplier<CreativeModeTab> PLAYER_ITEMS;
    public static Supplier<CreativeModeTab> LEGENDARY_ITEMS;
    public static Supplier<CreativeModeTab> BUILDING_BLOCKS;
    public static Supplier<CreativeModeTab> DECORATIONS;
    public static Supplier<CreativeModeTab> UTILITY;
    public static Supplier<CreativeModeTab> FORM_ITEMS;
    public static Supplier<CreativeModeTab> POKEMAIL;
    public static Supplier<CreativeModeTab> VALUABLES;
    public static Supplier<CreativeModeTab> POKEDOLLS;
    public static Supplier<CreativeModeTab> CUISINE;
    public static Supplier<CreativeModeTab> UNIMPLEMENTED;
    public static Supplier<CreativeModeTab> SHRINES;

    public static void init() {
        GenerationsCore.LOGGER.info("Registering Generations Creative Tabs...");
        GenerationsCreativeTabs.BUILDING_BLOCKS = GenerationsCore.getImplementation().create("building_blocks", () -> GenerationsBlocks.LIGHT_BLUE_POKE_BRICK_SET.getBaseBlockSupplier().get().asItem().getDefaultInstance(), GenerationsWood.WOOD_BLOCKS, GenerationsBlocks.BLOCK_ITEMS, GenerationsOres.ORES, GenerationsItems.BUILDING_BLOCKS);
        GenerationsCreativeTabs.RESTORATION = GenerationsCore.getImplementation().create("restoration", () -> GenerationsItems.LEMONADE.get().getDefaultInstance(), GenerationsItems.RESTORATION);
        GenerationsCreativeTabs.AWARDS = GenerationsCore.getImplementation().create("awards", () -> GenerationsItems.RAINBOW_BADGE.get().getDefaultInstance(), GenerationsItems.BADGES, GenerationsItems.RIBBONS);
        GenerationsCreativeTabs.HELD_ITEMS = GenerationsCore.getImplementation().create("held_items", () -> GenerationsItems.AMULET_COIN.get().getDefaultInstance(), GenerationsItems.HELD_ITEMS);
        GenerationsCreativeTabs.PLAYER_ITEMS = GenerationsCore.getImplementation().create("player_items", () -> GenerationsItems.POKEDEX.get().getDefaultInstance(), GenerationsItems.PLAYER_ITEMS, GenerationsItems.NATURAL);
        GenerationsCreativeTabs.LEGENDARY_ITEMS = GenerationsCore.getImplementation().create("legendary_items", () -> GenerationsItems.DNA_SPLICERS.get().getDefaultInstance(), GenerationsItems.LEGENDARY_ITEMS);
        GenerationsCreativeTabs.DECORATIONS = GenerationsCore.getImplementation().create("decorations", () -> GenerationsDecorationBlocks.SWITCH.get().asItem().getDefaultInstance(), GenerationsDecorationBlocks.DECORATIONS);
        GenerationsCreativeTabs.UTILITY = GenerationsCore.getImplementation().create("utility", () -> GenerationsUtilityBlocks.RKS_MACHINE.get().asItem().getDefaultInstance(), GenerationsItems.UTILITY, GenerationsUtilityBlocks.UTILITY_BLOCKS);
        GenerationsCreativeTabs.FORM_ITEMS = GenerationsCore.getImplementation().create("form_items", () -> GenerationsItems.METEORITE.get().getDefaultInstance(), GenerationsItems.FORM_ITEMS);
        GenerationsCreativeTabs.POKEMAIL = GenerationsCore.getImplementation().create("pokemail", () -> GenerationsItems.POKEMAIL_AIR.get().getDefaultInstance(), GenerationsItems.POKEMAIL);
        GenerationsCreativeTabs.VALUABLES = GenerationsCore.getImplementation().create("valuables", () -> GenerationsItems.STRANGE_SOUVENIR.get().getDefaultInstance(), GenerationsItems.VALUABLES);
        GenerationsCreativeTabs.POKEDOLLS = GenerationsCore.getImplementation().create("pokedolls", () -> GenerationsPokeDolls.PIKACHU_POKEDOLL.get().asItem().getDefaultInstance(), GenerationsPokeDolls.POKEDOLLS);
        GenerationsCreativeTabs.CUISINE = GenerationsCore.getImplementation().create("cuisine", () -> GenerationsItems.GIGANTAMIX.get().getDefaultInstance(), GenerationsItems.CUISINE);
        GenerationsCreativeTabs.UNIMPLEMENTED = GenerationsCore.getImplementation().create("unimplemented", () -> GenerationsItems.ABILITY_URGE.get().getDefaultInstance(), GenerationsItems.UNIMPLEMENTED);
        GenerationsCreativeTabs.SHRINES = GenerationsCore.getImplementation().create("shrines", () -> GenerationsShrines.FROZEN_SHRINE.get().asItem().getDefaultInstance(), GenerationsShrines.SHRINES);
        GenerationsCore.LOGGER.info("Registered Generations Creative Tabs!");
    }

    @ExpectPlatform
    @SafeVarargs
    private static Supplier<CreativeModeTab> create(String name, @NotNull Supplier<ItemStack> icon, @NotNull DeferredRegister<? extends ItemLike>... items) {
        throw new RuntimeException("Something");
    }
}
