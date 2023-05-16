package generations.gg.generations.core.generationscore.compat;

import generations.gg.generations.core.generationscore.world.level.block.entities.DyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.component.ItemComponent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class WTHIT implements IWailaPlugin {
    @Override
    public void register(IRegistrar iRegistrar) {
        iRegistrar.addIcon(DyeableBlockPrvoider.INSTANCE, DyeableBlock.class);
        iRegistrar.addComponent(DyeableBlockPrvoider.INSTANCE, TooltipPosition.HEAD, DyeableBlock.class);
    }

    public enum DyeableBlockPrvoider implements IBlockComponentProvider {
        INSTANCE;

        @Override
        public @Nullable ITooltipComponent getIcon(IBlockAccessor accessor, IPluginConfig config) {
            if(accessor.getBlock() instanceof DyeableBlock<?,?> dyeableBlock)
                return dyeableBlock.getAssoicatedBlockEntity(accessor.getWorld(), accessor.getPosition()).map(DyedVariantBlockEntity::getColor).map(dyeableBlock::getItemFromDyeColor).map(ItemComponent::new).orElse(null);


            return null;
        }

        @Override
        public void appendHead(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
            if(accessor.getBlock() instanceof DyeableBlock<?,?> dyeableBlock)
                dyeableBlock.getAssoicatedBlockEntity(accessor.getWorld(), accessor.getPosition()).map(DyedVariantBlockEntity::getColor).map(dyeableBlock::getItemFromDyeColor).map(ItemStack::new).ifPresent(stack -> {
                    IWailaConfig.Formatter formatter = IWailaConfig.get().getFormatter();
                    tooltip.setLine(WailaConstants.OBJECT_NAME_TAG, formatter.entityName(stack.getHoverName().getString()));
                    if (config.getBoolean(WailaConstants.CONFIG_SHOW_REGISTRY))
                        tooltip.setLine(WailaConstants.REGISTRY_NAME_TAG, formatter.registryName(BuiltInRegistries.ITEM.getKey(stack.getItem())));
                });
        }
    }
}