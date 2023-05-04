package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class GenerationsBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<Block> BLOCKS_WITHOUT_ITEMS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Block> CURSED_PUMPKIN = register("cursed_pumpkin", () -> new CursedPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.PUMPKIN)));
    public static final RegistrySupplier<Block> CURSED_CARVED_PUMPKIN = register("cursed_carved_pumpkin", () -> new CursedCarvedPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.CARVED_PUMPKIN)));
    public static final RegistrySupplier<Block> CURSED_JACK_O_LANTERN = register("cursed_jack_o_lantern", () -> new CursedCarvedPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.JACK_O_LANTERN)));

    private static <T extends Block> RegistrySupplier<T> register(String name, RegistrySupplier<T> block) {
        BLOCKS.register(name, block);
        return block;
    }

    public static void init() {
        BLOCKS.register();
        BLOCKS_WITHOUT_ITEMS.register();

        for (RegistrySupplier<Block> block : BLOCKS)
            ITEMS.register(block.getId(),
                    () -> new BlockItem(block.get(), new Item.Properties().arch$tab((CreativeModeTab) null)));


        ITEMS.register();
    }



}
