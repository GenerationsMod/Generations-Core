package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.world.item.TaoTrioStoneItem;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.TaoTrioShrineBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class TaoTrioShrineBlock extends InteractShrineBlock<TaoTrioShrineBlockEntity> {

    public TaoTrioShrineBlock(BlockBehaviour.Properties properties) {
        super(properties, PokeModBlockEntities.TAO_TRIO_SHRINE, PokeModBlockEntityModels.TAO_TRIO_SHRINE, TaoTrioShrineBlockEntity.class);
    }

    @Override
    public boolean isStackValid(ItemStack stack) {
        return stack.getItem() instanceof TaoTrioStoneItem && stack.getDamageValue() >= stack.getMaxDamage();
    }
}
