package generations.gg.generations.core.generationscore.world.item.creativetab;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

/**
 * Generations Creative Tabs
 * @author J.T. McQuigg
 * @author WaterPicker
 */
public class GenerationsCreativeTabs {

    public static DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.CREATIVE_MODE_TAB);
    public static CreativeModeTab POKEBALLS = create("pokeballs", () -> GenerationsItems.POKE_BALL.get().getDefaultInstance());
    public static CreativeModeTab RESTORATION = create("restoration", () -> GenerationsItems.POTION.get().getDefaultInstance());
    //public static CreativeModeTab TMS = create("tms", () -> GenerationsItems.TM_1);
    public static CreativeModeTab BADGES = create("badges", () -> GenerationsItems.RAINBOW_BADGE.get().getDefaultInstance());
    public static CreativeModeTab RIBBONS = create("ribbons", () -> GenerationsItems.COOLNESS_MASTER_RIBBON.get().getDefaultInstance());
    public static CreativeModeTab HELD_ITEMS = create("held_items", () -> GenerationsItems.AMULET_COIN.get().getDefaultInstance());
    public static CreativeModeTab PLAYER_ITEMS = create("player_items", () -> GenerationsItems.POKEDEX.get().getDefaultInstance());
    public static CreativeModeTab LEGENDARY_ITEMS = create("legendary_items", () -> GenerationsItems.DNA_SPLICERS.get().getDefaultInstance());
    public static CreativeModeTab BUILDING_BLOCKS = create("building_blocks", () -> GenerationsBlocks.LIGHT_BLUE_POKE_BRICK.get().asItem().getDefaultInstance());
    public static CreativeModeTab DECORATIONS = create("decorations", () -> GenerationsDecorationBlocks.SWITCH.get().asItem().getDefaultInstance());
    public static CreativeModeTab NATURAL = create("natural", () -> GenerationsOres.ALUMINUM_ORE.get().asItem().getDefaultInstance());
    public static CreativeModeTab UTILITY = create("utility", () -> GenerationsUtilityBlocks.RED_PC.get().asItem().getDefaultInstance());
    public static CreativeModeTab FORM_ITEMS = create("form_items", () -> GenerationsItems.METEORITE.get().getDefaultInstance());
    public static CreativeModeTab POKEMAIL = create("pokemail", () -> GenerationsItems.POKEMAIL_AIR.get().getDefaultInstance());
    public static CreativeModeTab VALUABLES = create("valuables", () -> GenerationsItems.STRANGE_SOUVENIR.get().getDefaultInstance());
    public static CreativeModeTab POKEDOLLS = create("pokedolls", () -> GenerationsPokeDolls.PIKACHU_POKEDOLL.get().asItem().getDefaultInstance());
    public static CreativeModeTab CUISINE = create("cuisine", () -> GenerationsItems.GIGANTAMIX.get().getDefaultInstance());
    public static CreativeModeTab UNIMPLEMENTED = create("unimplemented", () -> GenerationsItems.ABILITY_URGE.get().getDefaultInstance());
    public static CreativeModeTab SHRINES = create("shrines", () -> GenerationsShrines.FROZEN_SHRINE.get().asItem().getDefaultInstance());

    public static CreativeModeTab create(String name, Supplier<ItemStack> itemStackSupplier) {
        return CreativeTabRegistry.create(Component.translatable(name + "." + GenerationsCore.MOD_ID), itemStackSupplier);
    }

    public static void init() {
        CREATIVE_TABS.register();
    }
}
