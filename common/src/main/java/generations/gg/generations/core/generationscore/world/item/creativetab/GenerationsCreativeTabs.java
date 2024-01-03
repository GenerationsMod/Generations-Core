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
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * Generations Creative Tabs
 * @author J.T. McQuigg
 * @author WaterPicker
 */
public class GenerationsCreativeTabs {

    public static DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.CREATIVE_MODE_TAB);
    public static RegistrySupplier<CreativeModeTab> RESTORATION = create("restoration", () -> GenerationsItems.POTION);
    //public static CreativeModeTab TMS = create("tms", () -> GenerationsItems.TM_1);
    public static RegistrySupplier<CreativeModeTab> BADGES = create("badges", () -> GenerationsItems.RAINBOW_BADGE);
    public static RegistrySupplier<CreativeModeTab> RIBBONS = create("ribbons", () -> GenerationsItems.COOLNESS_MASTER_RIBBON);
    public static RegistrySupplier<CreativeModeTab> HELD_ITEMS = create("held_items", () -> GenerationsItems.AMULET_COIN);
    public static RegistrySupplier<CreativeModeTab> PLAYER_ITEMS = create("player_items", () -> GenerationsItems.POKEDEX);
    public static RegistrySupplier<CreativeModeTab> LEGENDARY_ITEMS = create("legendary_items", () -> GenerationsItems.DNA_SPLICERS);
    public static RegistrySupplier<CreativeModeTab> BUILDING_BLOCKS = create("building_blocks", GenerationsBlocks.LIGHT_BLUE_POKE_BRICK_SET::getBaseBlockSupplier);
    public static RegistrySupplier<CreativeModeTab> DECORATIONS = create("decorations", () -> GenerationsDecorationBlocks.SWITCH);
    public static RegistrySupplier<CreativeModeTab> NATURAL = create("natural", GenerationsOres.SILICON_ORE_SET::getOreSupplier);
    public static RegistrySupplier<CreativeModeTab> UTILITY = create("utility", () -> GenerationsUtilityBlocks.RKS_MACHINE);
    public static RegistrySupplier<CreativeModeTab> FORM_ITEMS = create("form_items", () -> GenerationsItems.METEORITE);
    public static RegistrySupplier<CreativeModeTab> POKEMAIL = create("pokemail", () -> GenerationsItems.POKEMAIL_AIR);
    public static RegistrySupplier<CreativeModeTab> VALUABLES = create("valuables", () -> GenerationsItems.STRANGE_SOUVENIR);
    public static RegistrySupplier<CreativeModeTab> POKEDOLLS = create("pokedolls", () -> GenerationsPokeDolls.PIKACHU_POKEDOLL);
    public static RegistrySupplier<CreativeModeTab> CUISINE = create("cuisine", () -> GenerationsItems.GIGANTAMIX);
    public static RegistrySupplier<CreativeModeTab> UNIMPLEMENTED = create("unimplemented", () -> GenerationsItems.ABILITY_URGE);
    public static RegistrySupplier<CreativeModeTab> SHRINES = create("shrines", () -> GenerationsShrines.FROZEN_SHRINE);

    private static <T extends ItemLike> RegistrySupplier<CreativeModeTab> create(String name, @NotNull Supplier<Supplier<T>> item) {
        return CREATIVE_TABS.register(name, () -> CreativeTabRegistry.create(builder -> builder.title(Component.translatable(name + "." + GenerationsCore.MOD_ID)).icon(() -> item.get().get().asItem().getDefaultInstance())));
    }

    public static void init() {
        CREATIVE_TABS.register();
    }
}
