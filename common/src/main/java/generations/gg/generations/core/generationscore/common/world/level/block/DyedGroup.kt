package generations.gg.generations.core.generationscore.common.world.level.block

import net.minecraft.core.Holder
import net.minecraft.world.item.DyeColor
import net.minecraft.world.level.block.Block

@JvmRecord
data class DyedGroup(@JvmField val block: Map<DyeColor, Holder<Block>>) {
    fun toArray(): Array<Block> = block.values.map { it.value() }.toTypedArray()
}
