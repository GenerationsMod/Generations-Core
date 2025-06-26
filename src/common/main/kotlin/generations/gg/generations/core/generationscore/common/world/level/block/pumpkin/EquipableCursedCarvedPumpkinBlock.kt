package generations.gg.generations.core.generationscore.common.world.level.block.pumpkin

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Equipable

class EquipableCursedCarvedPumpkinBlock(arg: Properties) : CursedCarvedPumpkinBlock(arg), Equipable {
    override fun getEquipmentSlot(): EquipmentSlot = EquipmentSlot.HEAD
}
