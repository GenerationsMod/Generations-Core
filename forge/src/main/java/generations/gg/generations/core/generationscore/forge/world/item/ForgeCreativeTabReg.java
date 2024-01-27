package generations.gg.generations.core.generationscore.forge.world.item;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.creativetab.GenerationsCreativeTabs;
import generations.gg.generations.core.generationscore.world.level.block.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ForgeCreativeTabReg {

    private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GenerationsCore.MOD_ID);

    private static RegistryObject<CreativeModeTab> BUILDING_BLOCKS;
    private static RegistryObject<CreativeModeTab> RESTORATION;
    private static RegistryObject<CreativeModeTab> AWARDS;
    private static RegistryObject<CreativeModeTab> HELD_ITEMS;
    private static RegistryObject<CreativeModeTab> PLAYER_ITEMS;
    private static RegistryObject<CreativeModeTab> LEGENDARY_ITEMS;
    private static RegistryObject<CreativeModeTab> DECORATIONS;
    private static RegistryObject<CreativeModeTab> NATURAL;
    private static RegistryObject<CreativeModeTab> UTILITY;
    private static RegistryObject<CreativeModeTab> FORM_ITEMS;
    private static RegistryObject<CreativeModeTab> POKEMAIL;
    private static RegistryObject<CreativeModeTab> VALUABLES;
    private static RegistryObject<CreativeModeTab> POKEDOLLS;
    private static RegistryObject<CreativeModeTab> CUISINE;
    private static RegistryObject<CreativeModeTab> UNIMPLEMENTED;
    private static RegistryObject<CreativeModeTab> SHRINES;
    
    public static void init(IEventBus MOD_BUS) {
        GenerationsCore.LOGGER.info("Registering Generations Creative Tabs...");
        BUILDING_BLOCKS = create("building_blocks", () -> GenerationsBlocks.LIGHT_BLUE_POKE_BRICK_SET.getBaseBlockSupplier().get().asItem().getDefaultInstance(), GenerationsWood.WOOD_BLOCKS);
        RESTORATION = create("restoration", () -> GenerationsItems.POTION.get().getDefaultInstance());
        AWARDS = create("badges", () -> GenerationsItems.RAINBOW_BADGE.get().getDefaultInstance(), GenerationsItems.BADGES, GenerationsItems.RIBBONS);
        HELD_ITEMS = create("held_items", () -> GenerationsItems.AMULET_COIN.get().getDefaultInstance());
        PLAYER_ITEMS = create("player_items", () -> GenerationsItems.POKEDEX.get().getDefaultInstance());
        LEGENDARY_ITEMS = create("legendary_items", () -> GenerationsItems.DNA_SPLICERS.get().getDefaultInstance());
        DECORATIONS = create("decorations", () -> GenerationsDecorationBlocks.SWITCH.get().asItem().getDefaultInstance(), GenerationsDecorationBlocks.DECORATIONS);
        NATURAL = create("natural", () -> GenerationsOres.SILICON_ORE_SET.getOreSupplier().get().asItem().getDefaultInstance(), GenerationsOres.ORES);
        UTILITY = create("utility", () -> GenerationsUtilityBlocks.RKS_MACHINE.get().asItem().getDefaultInstance());
        FORM_ITEMS = create("form_items", () -> GenerationsItems.METEORITE.get().getDefaultInstance());
        POKEMAIL = create("pokemail", () -> GenerationsItems.POKEMAIL_AIR.get().getDefaultInstance());
        VALUABLES = create("valuables", () -> GenerationsItems.STRANGE_SOUVENIR.get().getDefaultInstance());
        POKEDOLLS = create("pokedolls", () -> GenerationsPokeDolls.PIKACHU_POKEDOLL.get().asItem().getDefaultInstance(), GenerationsPokeDolls.POKEDOLLS);
        CUISINE = create("cuisine", () -> GenerationsItems.GIGANTAMIX.get().getDefaultInstance());
        UNIMPLEMENTED = create("unimplemented", () -> GenerationsItems.ABILITY_URGE.get().getDefaultInstance());
        SHRINES = create("shrines", () -> GenerationsShrines.FROZEN_SHRINE.get().asItem().getDefaultInstance(), GenerationsShrines.SHRINES);
        CREATIVE_TABS.register(MOD_BUS);
        GenerationsCore.LOGGER.info("Registered Generations Creative Tabs!");
    }

    public static void syncTabs() {
        GenerationsCore.LOGGER.info("Syncing Generations Creative Tabs... (Forge Only)");
        GenerationsCreativeTabs.BUILDING_BLOCKS = BUILDING_BLOCKS.get();
        GenerationsCreativeTabs.RESTORATION = RESTORATION.get();
        GenerationsCreativeTabs.AWARDS = AWARDS.get();
        GenerationsCreativeTabs.HELD_ITEMS = HELD_ITEMS.get();
        GenerationsCreativeTabs.PLAYER_ITEMS = PLAYER_ITEMS.get();
        GenerationsCreativeTabs.LEGENDARY_ITEMS = LEGENDARY_ITEMS.get();
        GenerationsCreativeTabs.DECORATIONS = DECORATIONS.get();
        GenerationsCreativeTabs.NATURAL = NATURAL.get();
        GenerationsCreativeTabs.UTILITY = UTILITY.get();
        GenerationsCreativeTabs.FORM_ITEMS = FORM_ITEMS.get();
        GenerationsCreativeTabs.POKEMAIL = POKEMAIL.get();
        GenerationsCreativeTabs.VALUABLES = VALUABLES.get();
        GenerationsCreativeTabs.POKEDOLLS = POKEDOLLS.get();
        GenerationsCreativeTabs.CUISINE = CUISINE.get();
        GenerationsCreativeTabs.UNIMPLEMENTED = UNIMPLEMENTED.get();
        GenerationsCreativeTabs.SHRINES = SHRINES.get();
        GenerationsCore.LOGGER.info("Synced Generations Creative Tabs!");
    }

    private static RegistryObject<CreativeModeTab> create(String name, Supplier<ItemStack> icon) {
        return register(name, CreativeModeTab.builder()
                .title(Component.translatable("itemGroup." + GenerationsCore.MOD_ID + "." + name))
                .icon(icon)
                .build());
    }

    @SafeVarargs
    private static <T extends ItemLike> RegistryObject<CreativeModeTab> create(String name, @NotNull Supplier<ItemStack> icon, @NotNull dev.architectury.registry.registries.DeferredRegister<T>... items) {
        return register(name, CreativeModeTab.builder()
                .title(Component.translatable("itemGroup." + GenerationsCore.MOD_ID + "." + name))
                .icon(icon)
                .displayItems((entry, context) -> {
                    for (dev.architectury.registry.registries.DeferredRegister<T> item : items)
                        item.forEach((itemEntry) -> context.accept(itemEntry.get().asItem().getDefaultInstance()));
                })
                .build());
    }

    private static RegistryObject<CreativeModeTab> register(String name, CreativeModeTab tab) {
        return CREATIVE_TABS.register(name, () -> tab);
    }
}
