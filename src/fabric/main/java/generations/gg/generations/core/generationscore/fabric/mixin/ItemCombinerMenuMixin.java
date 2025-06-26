package generations.gg.generations.core.generationscore.fabric.mixin;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ResultContainer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemCombinerMenu.class)
public abstract class ItemCombinerMenuMixin {
    @Shadow @Final protected ResultContainer resultSlots;

    @Shadow @Final protected Player player;

    @Shadow @Final protected Container inputSlots;

    public ResultContainer getResultSlot() {
        return resultSlots;
    }
}
