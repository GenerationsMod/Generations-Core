package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;

public class GenerationsBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);

    public static void init() {
        BLOCKS.register();
    }

}
