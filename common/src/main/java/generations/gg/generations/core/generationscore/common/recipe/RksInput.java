//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package generations.gg.generations.core.generationscore.common.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public class RksInput implements RecipeInput {
    public static final RksInput EMPTY = new RksInput(0, 0, List.of());
    private final int width;
    private final int height;
    private final List<ItemStack> items;
    private final RksStackedContents stackedContents = new RksStackedContents();
    private final int ingredientCount;

    private RksInput(int width, int height, List<ItemStack> item) {
        this.width = width;
        this.height = height;
        this.items = item;
        int i = 0;

        for (ItemStack itemStack : item) {
            if (!itemStack.isEmpty()) {
                ++i;
                this.stackedContents.account(itemStack);
            }
        }

        this.ingredientCount = i;
    }

    public static RksInput of(int width, int height, List<ItemStack> items) {
        return ofPositioned(width, height, items).input();
    }

    public static Positioned ofPositioned(int width, int height, List<ItemStack> items) {
        if (width != 0 && height != 0) {
            int i = width - 1;
            int j = 0;
            int k = height - 1;
            int l = 0;

            int m;
            for(m = 0; m < height; ++m) {
                boolean bl = true;

                for(int n = 0; n < width; ++n) {
                    ItemStack itemStack = (ItemStack)items.get(n + m * width);
                    if (!itemStack.isEmpty()) {
                        i = Math.min(i, n);
                        j = Math.max(j, n);
                        bl = false;
                    }
                }

                if (!bl) {
                    k = Math.min(k, m);
                    l = Math.max(l, m);
                }
            }

            m = j - i + 1;
            int o = l - k + 1;
            if (m > 0 && o > 0) {
                if (m == width && o == height) {
                    return new Positioned(new RksInput(width, height, items), i, k);
                } else {
                    List<ItemStack> list = new ArrayList<>(m * o);

                    for(int p = 0; p < o; ++p) {
                        for(int q = 0; q < m; ++q) {
                            int r = q + i + (p + k) * width;
                            list.add((ItemStack)items.get(r));
                        }
                    }

                    return new Positioned(new RksInput(m, o, list), i, k);
                }
            } else {
                return RksInput.Positioned.EMPTY;
            }
        } else {
            return RksInput.Positioned.EMPTY;
        }
    }

    public ItemStack getItem(int index) {
        return (ItemStack)this.items.get(index);
    }

    public ItemStack getItem(int row, int column) {
        return (ItemStack)this.items.get(row + column * this.width);
    }

    public int size() {
        return this.items.size();
    }

    public boolean isEmpty() {
        return this.ingredientCount == 0;
    }

    public RksStackedContents stackedContents() {
        return this.stackedContents;
    }

    public List<ItemStack> items() {
        return this.items;
    }

    public int ingredientCount() {
        return this.ingredientCount;
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        } else if (!(object instanceof RksInput)) {
            return false;
        } else {
            RksInput craftingInput = (RksInput)object;
            return this.width == craftingInput.width && this.height == craftingInput.height && this.ingredientCount == craftingInput.ingredientCount && ItemStack.listMatches(this.items, craftingInput.items);
        }
    }

    public int hashCode() {
        int i = ItemStack.hashStackList(this.items);
        i = 31 * i + this.width;
        i = 31 * i + this.height;
        return i;
    }

    public static record Positioned(RksInput input, int left, int top) {
        public static final Positioned EMPTY;

        public RksInput input() {
            return this.input;
        }

        public int left() {
            return this.left;
        }

        public int top() {
            return this.top;
        }

        static {
            EMPTY = new Positioned(RksInput.EMPTY, 0, 0);
        }
    }
}
