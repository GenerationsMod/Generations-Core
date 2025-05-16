package generations.gg.generations.core.generationscore.common.world.level.block.set

import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks.registerUltraBlock
import generations.gg.generations.core.generationscore.common.world.level.block.state.properties.GenerationsBlockSetTypes
import net.minecraft.world.level.block.Block
import org.jetbrains.annotations.ApiStatus
import java.util.function.Supplier

@ApiStatus.Internal
class GenerationsUltraBlockSet(name: String, baseBlock: RegistrySupplier<Block>) :
    GenerationsFullBlockSet(name, GenerationsBlocks.ULTRA_BLOCK_SETTINGS, GenerationsBlockSetTypes.ULTRA, baseBlock) {
    init {
        fullBlockSets.remove(this)
        blockSets.remove(this)
        ultraBlockSets.add(this)
    }

    override fun <T : Block> registerBlockItem(name: String, blockSupplier: Supplier<T>): RegistrySupplier<T> {
        return registerUltraBlock(name, blockSupplier)
    }

    companion object {
        @JvmField
        var ultraBlockSets: ArrayList<GenerationsUltraBlockSet> = ArrayList()
        fun updateUltraBlockFamilies() {
            for (blockSet in ultraBlockSets) blockSet.updateBlockFamily()
        }
    }
}
