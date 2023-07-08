package generations.gg.generations.core.generationscore.world.item.creativetab;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

/**
 * Generations Creative Tabs
 * @author J.T. McQuigg
 * @author WaterPicker
 */
public class GenerationsCreativeTabs {

    public static DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.CREATIVE_MODE_TAB);
    public static RegistrySupplier<CreativeModeTab> POKEBALLS = create("pokeballs", GenerationsItems.POKE_BALL.get());
    public static RegistrySupplier<CreativeModeTab> RESTORATION = create("restoration", GenerationsItems.POTION.get());
    //public static CreativeModeTab TMS = create("tms", () -> GenerationsItems.TM_1);
    public static RegistrySupplier<CreativeModeTab> BADGES = create("badges", GenerationsItems.RAINBOW_BADGE.get());
    public static RegistrySupplier<CreativeModeTab> RIBBONS = create("ribbons", GenerationsItems.COOLNESS_MASTER_RIBBON.get());
    public static RegistrySupplier<CreativeModeTab> HELD_ITEMS = create("held_items", GenerationsItems.AMULET_COIN.get());
    public static RegistrySupplier<CreativeModeTab> PLAYER_ITEMS = create("player_items", GenerationsItems.POKEDEX.get());
    public static RegistrySupplier<CreativeModeTab> LEGENDARY_ITEMS = create("legendary_items", GenerationsItems.DNA_SPLICERS.get());
    public static RegistrySupplier<CreativeModeTab> BUILDING_BLOCKS = create("building_blocks", GenerationsBlocks.LIGHT_BLUE_POKE_BRICK.get().asItem());
    public static RegistrySupplier<CreativeModeTab> DECORATIONS = create("decorations", GenerationsDecorationBlocks.SWITCH.get().asItem());
    public static RegistrySupplier<CreativeModeTab> NATURAL = create("natural", GenerationsOres.ALUMINUM_ORE.get().asItem());
    public static RegistrySupplier<CreativeModeTab> UTILITY = create("utility", GenerationsUtilityBlocks.RED_PC.get().asItem());
    public static RegistrySupplier<CreativeModeTab> FORM_ITEMS = create("form_items", GenerationsItems.METEORITE.get());
    public static RegistrySupplier<CreativeModeTab> POKEMAIL = create("pokemail", GenerationsItems.POKEMAIL_AIR.get());
    public static RegistrySupplier<CreativeModeTab> VALUABLES = create("valuables", GenerationsItems.STRANGE_SOUVENIR.get());
    public static RegistrySupplier<CreativeModeTab> POKEDOLLS = create("pokedolls", GenerationsPokeDolls.PIKACHU_POKEDOLL.get().asItem());
    public static RegistrySupplier<CreativeModeTab> CUISINE = create("cuisine", GenerationsItems.GIGANTAMIX.get());
    public static RegistrySupplier<CreativeModeTab> UNIMPLEMENTED = create("unimplemented", GenerationsItems.ABILITY_URGE.get());
    public static RegistrySupplier<CreativeModeTab> SHRINES = create("shrines", GenerationsShrines.FROZEN_SHRINE.get().asItem());

    private static RegistrySupplier<CreativeModeTab> create(String name, @NotNull Item item) {
        return CREATIVE_TABS.register(name, () -> CreativeTabRegistry.create(Component.translatable(name + "." + GenerationsCore.MOD_ID), item::getDefaultInstance));
    }

    public static void init() {
        CREATIVE_TABS.register();
    }
}
