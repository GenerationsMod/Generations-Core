package generations.gg.generations.core.generationscore.common.world.level.block

import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.Holder
import net.minecraft.world.item.BlockItem
import net.minecraft.world.level.block.Block

open class GenerationsBlockItem(private val blockHolder: Holder<Block>, properties: Properties) : BlockItem(null, properties) {
    override fun getBlock(): Block {
        return blockHolder.value()
    }

    override fun mustSurvive(): Boolean {
        return blockHolder.value() is GenericRotatableModelBlock
    }
}
