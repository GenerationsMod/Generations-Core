package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.world.item.CreationTrioItem;
import generations.gg.generations.core.generationscore.world.item.RedChainItem;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.TimeSpaceAltarBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class TimespaceAltarBlock extends InteractShrineBlock<TimeSpaceAltarBlockEntity> {

    public TimespaceAltarBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.TIMESPACE_ALTAR, GenerationsBlockEntityModels.TIME_SPACE_ALTAR, TimeSpaceAltarBlockEntity.class);
    }

    @Override
    public boolean isStackValid(ItemStack stack) {
        return stack.getItem() instanceof RedChainItem || stack.getItem() instanceof CreationTrioItem;
    }
}
