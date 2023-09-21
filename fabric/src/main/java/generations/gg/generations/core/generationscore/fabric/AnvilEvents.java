package generations.gg.generations.core.generationscore.fabric;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AnvilEvents {
    public static final Event<AnvilChange> ANVIL_CHANGE = EventFactory.createArrayBacked(AnvilChange.class, callbacks -> (result, left, right, name, baseCost, player) -> {
        for(var callback : callbacks)
            if (callback.change(result, left, right, name, baseCost, player)) return true;
        return false;
    });

    public static interface AnvilChange {
        boolean change(Result result, @NotNull ItemStack left, @NotNull ItemStack right, String name, int baseCost, Player player);

        public class Result {
            private ItemStack output;
            private int cost;
            private int materialCost;

            public Result(int cost) {
                this.output = ItemStack.EMPTY;
                this.cost = cost;
                this.materialCost = 0;
            }

            public int getCost() {
                return cost;
            }

            public void setCost(int cost) {
                this.cost = cost;
            }

            public int getMaterialCost() {
                return materialCost;
            }

            public void setMaterialCost(int materialCost) {
                this.materialCost = materialCost;
            }

            public ItemStack getOutput() {
                return output;
            }

            public void setOutput(ItemStack output) {
                this.output = output;
            }
        }
    }
}
