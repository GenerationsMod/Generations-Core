package generations.gg.generations.core.generationscore.common.compat.rei;

import generations.gg.generations.core.generationscore.common.client.screen.container.RksMachineScreen;
import generations.gg.generations.core.generationscore.common.world.container.RksMachineContainer;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsUtilityBlocks;
import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.client.registry.screen.SimpleClickArea;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.api.client.registry.transfer.simple.SimpleTransferHandler;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;

public class ReiCompatClient implements REIClientPlugin {
    public static final CategoryIdentifier<? extends DefaultRksMachineRecipeDisplay<?>> RKS_MACHINE = CategoryIdentifier.of("generationscore", "rks_machine");

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new DefaultRksCategory(), configuration -> configuration.addWorkstations(EntryStacks.of(GenerationsUtilityBlocks.RKS_MACHINE.get())));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(RksRecipe.class, DefaultRksMachineRecipeDisplay::of);
    }



    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerContainerClickArea (rksMachineScreen -> new Rectangle(90, 35, 22, 15), RksMachineScreen.class, RKS_MACHINE);
    }

    @Override
    public void registerTransferHandlers(TransferHandlerRegistry registry) {
        registry.register(SimpleTransferHandler.create(RksMachineContainer.class, RKS_MACHINE, new SimpleTransferHandler.IntRange(1, 10)));
    }
}
