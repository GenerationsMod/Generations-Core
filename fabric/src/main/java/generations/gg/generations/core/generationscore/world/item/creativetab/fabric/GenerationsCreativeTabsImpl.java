package generations.gg.generations.core.generationscore.world.item.creativetab.fabric;

import dev.architectury.registry.registries.DeferredRegister;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.creativetab.GenerationsCreativeTabs;
import generations.gg.generations.core.generationscore.world.level.block.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class GenerationsCreativeTabsImpl {
    public static void init() {
        GenerationsCore.LOGGER.info("Registering Generations Creative Tabs...");
        GenerationsCreativeTabs.BUILDING_BLOCKS = create("building_blocks", () -> GenerationsBlocks.LIGHT_BLUE_POKE_BRICK_SET.getBaseBlockSupplier().get().asItem().getDefaultInstance(), GenerationsWood.WOOD_BLOCKS);
        GenerationsCreativeTabs.RESTORATION = create("restoration", () -> GenerationsItems.POTION.get().getDefaultInstance(), GenerationsItems.RESTORATION);
        GenerationsCreativeTabs.AWARDS = create("awards", () -> GenerationsItems.RAINBOW_BADGE.get().getDefaultInstance(), GenerationsItems.BADGES, GenerationsItems.RIBBONS);
        GenerationsCreativeTabs.HELD_ITEMS = create("held_items", () -> GenerationsItems.AMULET_COIN.get().getDefaultInstance(), GenerationsItems.HELD_ITEMS);
        GenerationsCreativeTabs.PLAYER_ITEMS = create("player_items", () -> GenerationsItems.POKEDEX.get().getDefaultInstance(), GenerationsItems.HELD_ITEMS);
        GenerationsCreativeTabs.LEGENDARY_ITEMS = create("legendary_items", () -> GenerationsItems.DNA_SPLICERS.get().getDefaultInstance(), GenerationsItems.LEGENDARY_ITEMS);
        GenerationsCreativeTabs.DECORATIONS = create("decorations", () -> GenerationsDecorationBlocks.SWITCH.get().asItem().getDefaultInstance(), GenerationsDecorationBlocks.DECORATIONS);
        GenerationsCreativeTabs.NATURAL = create("natural", () -> GenerationsOres.SILICON_ORE_SET.getOreSupplier().get().asItem().getDefaultInstance(), GenerationsOres.ORES);
        GenerationsCreativeTabs.UTILITY = create("utility", () -> GenerationsUtilityBlocks.RKS_MACHINE.get().asItem().getDefaultInstance(), GenerationsItems.UTILITY);
        GenerationsCreativeTabs.FORM_ITEMS = create("form_items", () -> GenerationsItems.METEORITE.get().getDefaultInstance(), GenerationsItems.FORM_ITEMS);
        GenerationsCreativeTabs.POKEMAIL = create("pokemail", () -> GenerationsItems.POKEMAIL_AIR.get().getDefaultInstance(), GenerationsItems.POKEMAIL);
        GenerationsCreativeTabs.VALUABLES = create("valuables", () -> GenerationsItems.STRANGE_SOUVENIR.get().getDefaultInstance(), GenerationsItems.VALUABLES);
        GenerationsCreativeTabs.POKEDOLLS = create("pokedolls", () -> GenerationsPokeDolls.PIKACHU_POKEDOLL.get().asItem().getDefaultInstance(), GenerationsPokeDolls.POKEDOLLS);
        GenerationsCreativeTabs.CUISINE = create("cuisine", () -> GenerationsItems.GIGANTAMIX.get().getDefaultInstance(), GenerationsItems.CUISINE);
        GenerationsCreativeTabs.UNIMPLEMENTED = create("unimplemented", () -> GenerationsItems.ABILITY_URGE.get().getDefaultInstance(), GenerationsItems.UNIMPLEMENTED);
        GenerationsCreativeTabs.SHRINES = create("shrines", () -> GenerationsShrines.FROZEN_SHRINE.get().asItem().getDefaultInstance(), GenerationsShrines.SHRINES);
        GenerationsCore.LOGGER.info("Registered Generations Creative Tabs!");
    }

//    private static CreativeModeTab create(String name, Supplier<ItemStack> icon) {
//        return register(name, FabricItemGroup.builder()
//                .title(Component.translatable("itemGroup." + GenerationsCore.MOD_ID + "." + name))
//                .icon(icon)
//                .build());
//    }

    @SafeVarargs
    private static <T extends ItemLike> Supplier<CreativeModeTab> create(String name, @NotNull Supplier<ItemStack> icon, @NotNull DeferredRegister<? extends ItemLike>... items) {
        return register(name, FabricItemGroup.builder()
                .title(Component.translatable("itemGroup." + GenerationsCore.MOD_ID + "." + name))
                .icon(icon)
                .displayItems((entry, context) -> {
                    for (DeferredRegister<? extends ItemLike> item : items)
                        item.forEach((itemEntry) -> context.accept(itemEntry.get().asItem().getDefaultInstance()));
                })
                .build());
    }

    private static Supplier<CreativeModeTab> register(String name, CreativeModeTab tab) {
        return () -> Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, GenerationsCore.id(name), tab);
    }
}
