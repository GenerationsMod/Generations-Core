package generations.gg.generations.core.generationscore.common.world.level.block.pumpkin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;
import org.jetbrains.annotations.NotNull;

public class EquipableCursedCarvedPumpkinBlock extends CursedCarvedPumpkinBlock implements Equipable {
    public EquipableCursedCarvedPumpkinBlock(Properties arg) {
        super(arg);
    }

    @Override
    public @NotNull EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }
}
