package generations.gg.generations.core.generationscore.world.level.block.shrines;

import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntityModels;
import com.pokemod.pokemod.world.level.block.entities.TimeSpaceAltarBlockEntity;
import com.pokemod.pokemod.world.item.CreationTrioItem;
import com.pokemod.pokemod.world.item.RedChainItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class TimespaceAltarBlock extends InteractShrineBlock<TimeSpaceAltarBlockEntity> {

    public TimespaceAltarBlock(BlockBehaviour.Properties properties) {
        super(properties, PokeModBlockEntities.TIMESPACE_ALTAR, PokeModBlockEntityModels.TIME_SPACE_ALTAR, TimeSpaceAltarBlockEntity.class);
    }

    @Override
    public boolean isStackValid(ItemStack stack) {
        return stack.getItem() instanceof RedChainItem || stack.getItem() instanceof CreationTrioItem;
    }
}
