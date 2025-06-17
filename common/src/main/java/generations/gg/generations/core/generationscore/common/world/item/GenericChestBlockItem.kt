package generations.gg.generations.core.generationscore.common.world.item

import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlockItem
import net.minecraft.core.Holder
import net.minecraft.world.level.block.Block

class GenericChestBlockItem(arg: Holder<Block>, arg2: Properties) : GenerationsBlockItem(arg, arg2) {
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
