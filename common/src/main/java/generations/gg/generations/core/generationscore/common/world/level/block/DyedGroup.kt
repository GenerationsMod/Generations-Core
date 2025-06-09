package generations.gg.generations.core.generationscore.common.world.level.block

import net.minecraft.core.Holder
import net.minecraft.world.item.DyeColor
import net.minecraft.world.level.block.Block

@JvmRecord
data class DyedGroup(@JvmField val block: Map<DyeColor, Holder<out Block>>) {
    fun toArray(): Array<Holder<out Block>> = block.values.toTypedArray()
    fun toExposedArray(): Array<out Block> = block.values.map(Holder<out Block>::value).toTypedArray()
}
