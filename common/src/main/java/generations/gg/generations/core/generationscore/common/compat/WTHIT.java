package generations.gg.generations.core.generationscore.common.compat;

import com.cobblemon.mod.common.item.PokemonItem;
import generations.gg.generations.core.generationscore.common.client.entity.StatueClientDelegate;
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.DyeableBlock;
import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.component.ItemComponent;
import net.minecraft.core.registries.BuiltInRegistries;
import org.jetbrains.annotations.Nullable;

public class WTHIT implements IWailaClientPlugin {

    @Override
    public void register(IClientRegistrar iClientRegistrar) {
        iClientRegistrar.icon(DyeableBlockPrvoider.INSTANCE, DyeableBlock.class);
        iClientRegistrar.head(DyeableBlockPrvoider.INSTANCE, DyeableBlock.class);
//        iClientRegistrar.icon(StatueEntityProvider.INSTANCE, StatueEntity.class);
//        iClientRegistrar.head(StatueEntityProvider.INSTANCE, StatueEntity.class);
    }

    public enum StatueEntityProvider implements IEntityComponentProvider {
        INSTANCE;

        @Override
        public @Nullable ITooltipComponent getIcon(IEntityAccessor accessor, IPluginConfig config) {
            return accessor.getEntity() instanceof StatueEntity statue ? new ItemComponent(PokemonItem.from(statue.getProperties())) : null;
        }

        @Override
        public void appendHead(ITooltip tooltip, IEntityAccessor accessor, IPluginConfig config) {
            if(accessor.getEntity() instanceof StatueEntity statue) {

                IWailaConfig.Formatter formatter = IWailaConfig.get().getFormatter();
                var name = formatter.entityName(statue.getDisplayName().getString());
                tooltip.setLine(WailaConstants.OBJECT_NAME_TAG, name);
            }
        }
    }

    public enum DyeableBlockPrvoider implements IBlockComponentProvider {
        INSTANCE;

        @Override
        public @Nullable ITooltipComponent getIcon(IBlockAccessor accessor, IPluginConfig config) {
            return accessor.getBlock() instanceof DyeableBlock dyeableBlock ? new ItemComponent(dyeableBlock.getItemFromDyeColor(dyeableBlock.color)) : null;
        }

        @Override
        public void appendHead(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
            if(accessor.getBlock() instanceof DyeableBlock dyeableBlock) {
                var stack = dyeableBlock.getItemFromDyeColor(dyeableBlock.color).getDefaultInstance();
                IWailaConfig.Formatter formatter = IWailaConfig.get().getFormatter();
                tooltip.setLine(WailaConstants.OBJECT_NAME_TAG, formatter.entityName(stack.getHoverName().getString()));
                if (config.getBoolean(WailaConstants.CONFIG_SHOW_REGISTRY))
                    tooltip.setLine(WailaConstants.REGISTRY_NAME_TAG, formatter.registryName(BuiltInRegistries.ITEM.getKey(stack.getItem())));
            }
        }
    }
}