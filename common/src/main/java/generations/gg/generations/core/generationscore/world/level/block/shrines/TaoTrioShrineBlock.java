package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.world.item.TaoTrioStoneItem;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.TaoTrioShrineBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class TaoTrioShrineBlock extends InteractShrineBlock<TaoTrioShrineBlockEntity> {

    public TaoTrioShrineBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.TAO_TRIO_SHRINE, GenerationsBlockEntityModels.TAO_TRIO_SHRINE, TaoTrioShrineBlockEntity.class);
    }

    @Override
    public boolean isStackValid(ItemStack stack) {
        return stack.getItem() instanceof TaoTrioStoneItem && stack.getDamageValue() >= stack.getMaxDamage();
    }
}
