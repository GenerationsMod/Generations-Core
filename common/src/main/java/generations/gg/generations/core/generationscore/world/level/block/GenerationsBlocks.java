package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class GenerationsBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<Block> BLOCKS_WITHOUT_ITEMS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ITEM);



    public static void init() {
        BLOCKS.register();
        BLOCKS_WITHOUT_ITEMS.register();

        for (RegistrySupplier<Block> block : BLOCKS)
            ITEMS.register(block.getId(),
                    () -> new BlockItem(block.get(), new Item.Properties().arch$tab((CreativeModeTab) null)));


        ITEMS.register();
    }



}
