package generations.gg.generations.core.generationscore.common.world.recipe;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.RksMachineBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface RksResult<U extends RksResult<U>> {
    ItemStack getStack();

    RksResultType<U> type();

    default void process(Player player, RksMachineBlockEntity rksMachineBlockEntity, ItemStack stack) {}

    default boolean consumeTimeCapsules() {
        return true;
    }

    boolean isPokemon();

}
