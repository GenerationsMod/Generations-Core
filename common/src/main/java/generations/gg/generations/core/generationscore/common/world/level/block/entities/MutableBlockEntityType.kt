package generations.gg.generations.core.generationscore.common.world.level.block.entities

import com.mojang.datafixers.types.Type
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import java.util.*

//Direct copy of https://github.com/DimensionalDevelopment/DimDoors/blob/1.19/src/main/java/org/dimdev/dimdoors/api/block/entity/MutableBlockEntityType.java
class MutableBlockEntityType<T : BlockEntity>(factory: (BlockPos, BlockState) -> T, blocks: Set<Block>, type: Type<*>?) :
    BlockEntityType<T>(factory, HashSet(blocks), type) {
    fun addBlock(block: Block?): Boolean {
        return validBlocks.add(block)
    }

    fun removeBlock(block: Block?): Boolean {
        return validBlocks.remove(block)
    }


    class Builder<T : BlockEntity> private constructor(
        private val factory: (BlockPos, BlockState) -> T,
        private val blocks: Set<Block>
    ) {
        @JvmOverloads
        fun build(type: Type<*>? = null): MutableBlockEntityType<T> {
            return MutableBlockEntityType(this.factory, this.blocks, type)
        }

        companion object {
            fun <T : BlockEntity> create(factory: (BlockPos, BlockState) -> T, vararg blocks: Block): Builder<T> {
                // ensure mutability
                return Builder(factory, mutableSetOf(*blocks))
            }
        }
    }


    // exists for convenience so that no access widener for BlockEntityType.BlockEntityFactory is necessary
    @FunctionalInterface
    interface BlockEntityFactory<T : BlockEntity> : BlockEntitySupplier<T>

    companion object {
        @JvmField
		var blocksToAdd: MutableSet<GenericModelBlock<*>> = HashSet()
    }
}