package generations.gg.generations.core.generationscore.world.item;

import com.pokemod.pokemod.client.model.inventory.GenericChestItemStackRenderer;
import com.pokemod.pokemod.world.level.block.generic.GenericChestBlock;
import com.pokemod.pokemod.world.level.block.entities.generic.GenericChestBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class GenericChestBlockItem extends BlockItem {
    public GenericChestBlockItem(GenericChestBlock arg, Properties arg2) {
        super(arg, arg2);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new GenericChestItemStackRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels(), () -> new GenericChestBlockEntity(BlockPos.ZERO, getBlock().defaultBlockState()));
            }
        });
    }
}
