package generations.gg.generations.core.generationscore.world.item;

import generations.gg.generations.core.generationscore.world.level.block.generic.GenericChestBlock;
import net.minecraft.world.item.BlockItem;

public class GenericChestBlockItem extends BlockItem {
    public <T extends GenericChestBlock> GenericChestBlockItem(T arg, Properties arg2) {
        super(arg, arg2);
    }

//    @Override TODO: figure this out
//    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
//        consumer.accept(new IClientItemExtensions() {
//            @Override
//            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
//                return new GenericChestItemStackRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels(), () -> new GenericChestBlockEntity(BlockPos.ZERO, getBlock().defaultBlockState()));
//            }
//        });
//    }
}
