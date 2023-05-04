package generations.gg.generations.core.generationscore.world.level.block.shrines;

import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntityModels;
import com.pokemod.pokemod.world.level.block.entities.RegigigasShrineBlockEntity;
import com.pokemod.pokemod.world.item.RegiOrbItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RegigigasShrineBlock extends InteractShrineBlock<RegigigasShrineBlockEntity> {
    public RegigigasShrineBlock(BlockBehaviour.Properties materialIn) {
        super(materialIn, PokeModBlockEntities.REGIGIGAS_SHRINE, PokeModBlockEntityModels.REGIGIGAS_SHRINE, RegigigasShrineBlockEntity.class);
    }

    @Override
    public boolean isStackValid(ItemStack stack) {
        return stack.getItem() instanceof RegiOrbItem;
    }
}
