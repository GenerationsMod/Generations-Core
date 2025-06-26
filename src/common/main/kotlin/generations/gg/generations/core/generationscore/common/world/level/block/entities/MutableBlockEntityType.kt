package generations.gg.generations.core.generationscore.common.world.level.block.entities

import com.mojang.datafixers.types.Type
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

//Direct copy of https://github.com/DimensionalDevelopment/DimDoors/blob/1.19/src/main/java/org/dimdev/dimdoors/api/block/entity/MutableBlockEntityType.java
class MutableBlockEntityType<T : BlockEntity>(factory: (BlockPos, BlockState) -> T, blocks: Set<Block>, type: Type<*>?) :
    BlockEntityType<T>(factory, HashSet(blocks), type) {
    fun addBlock(block: Block?): Boolean {
        return validBlocks.add(block)
    }

    fun removeBlock(block: Block?): Boolean {
        return validBlocks.remove(block)
    }


    companion object {
        @JvmField
		var blocksToAdd: MutableSet<GenericModelBlock> = HashSet()
    }
}