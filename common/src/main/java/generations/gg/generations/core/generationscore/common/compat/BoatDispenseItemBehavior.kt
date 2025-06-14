package generations.gg.generations.core.generationscore.common.compat

import generations.gg.generations.core.generationscore.common.world.entity.GenerationsBoatEntity
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsChestBoatEntity
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities
import net.minecraft.core.dispenser.BlockSource
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior
import net.minecraft.tags.FluidTags
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.DispenserBlock

class BoatDispenseItemBehavior @JvmOverloads constructor(
    private val type: GenerationsBoatEntity.Type,
    private val isChestBoat: Boolean = false
) :
    DefaultDispenseItemBehavior() {
    private val defaultDispenseItemBehavior = DefaultDispenseItemBehavior()

    public override fun execute(source: BlockSource, stack: ItemStack): ItemStack {
        val direction = source.state().getValue(DispenserBlock.FACING)
        val level: Level = source.level()
        val d = 0.5625 + GenerationsEntities.BOAT_ENTITY.getWidth() as Double / 2.0
        val e = source.pos().x + direction.stepX.toDouble() * d
        val f = source.pos().y + (direction.stepY.toFloat() * 1.125f).toDouble()
        val g = source.pos().z + direction.stepZ.toDouble() * d
        val blockPos = source.pos().relative(direction)
        val h: Double
        if (level.getFluidState(blockPos).`is`(FluidTags.WATER)) h = 1.0
        else {
            if (!level.getBlockState(blockPos).isAir || !level.getFluidState(blockPos.below())
                    .`is`(FluidTags.WATER)
            ) return defaultDispenseItemBehavior.dispense(source, stack)

            h = 0.0
        }

        if (this.isChestBoat) {
            val entity = GenerationsChestBoatEntity(level, e, f + h, g)
            entity.setBoatType(this.type)
            entity.yRot = direction.toYRot()
            level.addFreshEntity(entity)
        } else {
            val entity = GenerationsBoatEntity(level, e, f + h, g)
            entity.setBoatType(this.type)
            entity.yRot = direction.toYRot()
            level.addFreshEntity(entity)
        }
        stack.shrink(1)
        return stack
    }
}
