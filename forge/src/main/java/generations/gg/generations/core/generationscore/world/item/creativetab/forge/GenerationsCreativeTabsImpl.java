package generations.gg.generations.core.generationscore.world.item.creativetab.forge;

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

public class GenerationsCreativeTabsImpl {

    private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GenerationsCore.MOD_ID);

    private static RegistryObject<CreativeModeTab> create(String name, Supplier<ItemStack> icon) {
        return register(name, CreativeModeTab.builder()
                .title(Component.translatable("itemGroup." + GenerationsCore.MOD_ID + "." + name))
                .icon(icon)
                .build());
    }

    @SafeVarargs
    public static <T extends ItemLike> Supplier<CreativeModeTab> create(String name, @NotNull Supplier<ItemStack> icon, @NotNull dev.architectury.registry.registries.DeferredRegister<? extends ItemLike>... items) {
        return register(name, CreativeModeTab.builder()
                .title(Component.translatable("itemGroup." + GenerationsCore.MOD_ID + "." + name))
                .icon(icon)
                .displayItems((entry, context) -> {
                    for (dev.architectury.registry.registries.DeferredRegister<? extends ItemLike> item : items)
                        item.forEach((itemEntry) -> {
                            context.accept(itemEntry.get().asItem().getDefaultInstance());
                        });
                })
                .withSearchBar()
                .build());
    }

    private static RegistryObject<CreativeModeTab> register(String name, CreativeModeTab tab) {
        return CREATIVE_TABS.register(name, () -> tab);
    }

    public static void init(IEventBus bus) {
        CREATIVE_TABS.register(bus);
    }
}
