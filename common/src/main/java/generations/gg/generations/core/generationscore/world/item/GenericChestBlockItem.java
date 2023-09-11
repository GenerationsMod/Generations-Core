package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.client.render.item.CobblemonBuiltinItemRenderer;
import com.cobblemon.mod.common.client.render.item.CobblemonBuiltinItemRendererRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import generations.gg.generations.core.generationscore.client.model.inventory.GenericChestItemStackRenderer;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericChestBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericChestBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

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
