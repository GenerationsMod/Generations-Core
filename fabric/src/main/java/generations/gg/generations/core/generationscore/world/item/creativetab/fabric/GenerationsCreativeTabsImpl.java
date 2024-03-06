package generations.gg.generations.core.generationscore.world.item.creativetab.fabric;

import dev.architectury.registry.registries.DeferredRegister;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.creativetab.GenerationsCreativeTabs;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * Generations Creative Tabs (Fabric)
 * @author Joseph T. McQuigg
 * @author WaterPicker
 * @see GenerationsCreativeTabs
 * Uses ExpectPlatform to register the creative tabs with Minecraft using Fabric.
 */
public class GenerationsCreativeTabsImpl {

    @SafeVarargs
    public static Supplier<CreativeModeTab> create(String name, @NotNull Supplier<ItemStack> icon, @NotNull DeferredRegister<? extends ItemLike>... items) {
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
        CreativeModeTab tab1 = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, GenerationsCore.id(name), tab);
        return () -> tab1;
    }
}
