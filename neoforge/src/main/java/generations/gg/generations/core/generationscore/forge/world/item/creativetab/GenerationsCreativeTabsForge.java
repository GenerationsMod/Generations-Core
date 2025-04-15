package generations.gg.generations.core.generationscore.forge.world.item.creativetab;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.item.creativetab.GenerationsCreativeTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * Generations Creative Tabs (Forge)
 * @author Joseph T. McQuigg
 * @author WaterPicker
 * @see GenerationsCreativeTabs
 * Uses ExpectPlatform to register the creative tabs with Minecraft using Forge.
 */
public class GenerationsCreativeTabsForge {

    private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GenerationsCore.MOD_ID);

    @SafeVarargs
    public static Supplier<CreativeModeTab> create(String name, @NotNull Supplier<ItemStack> icon, @NotNull dev.architectury.registry.registries.DeferredRegister<? extends ItemLike>... items) {
        return register(name, CreativeModeTab.builder()
                .title(Component.translatable("itemGroup." + GenerationsCore.MOD_ID + "." + name))
                .icon(icon)
                .displayItems((entry, context) -> {
                    for (dev.architectury.registry.registries.DeferredRegister<? extends ItemLike> item : items)
                        item.forEach((itemEntry) -> context.accept(itemEntry.get().asItem().getDefaultInstance()));
                })
                .withSearchBar()
                .build());
    }

    private static DeferredHolder<CreativeModeTab, CreativeModeTab> register(String name, CreativeModeTab tab) {
        return CREATIVE_TABS.register(name, () -> tab);
    }

    public static void init(IEventBus bus) {
        CREATIVE_TABS.register(bus);
    }
}
