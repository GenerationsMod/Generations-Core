package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.world.item.RegiOrbItem;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.RegigigasShrineBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RegigigasShrineBlock extends InteractShrineBlock<RegigigasShrineBlockEntity> {
    public RegigigasShrineBlock(BlockBehaviour.Properties materialIn) {
        super(materialIn, GenerationsBlockEntities.REGIGIGAS_SHRINE, GenerationsBlockEntityModels.REGIGIGAS_SHRINE, RegigigasShrineBlockEntity.class);
    }

    @Override
    public boolean isStackValid(ItemStack stack) {
        return stack.getItem() instanceof RegiOrbItem;
    }
}
