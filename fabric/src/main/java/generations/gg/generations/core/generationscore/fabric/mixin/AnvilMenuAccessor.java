package generations.gg.generations.core.generationscore.fabric.mixin;

import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AnvilMenu.class)
public interface AnvilMenuAccessor {
    @Accessor("repairItemCountCost")
    void setMaterialCost(int cost);
}
