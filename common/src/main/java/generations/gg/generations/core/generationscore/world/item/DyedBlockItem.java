package generations.gg.generations.core.generationscore.world.item;

import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class DyedBlockItem<T extends DyeableBlock<?, ?>> extends ItemNameBlockItem {
    private final DyeColor color;
    private String descriptionId;

    public DyedBlockItem(T block, DyeColor color, Properties properties) {
        super(block, properties);
        this.color = color;
    }

    @Override
    protected boolean updateCustomBlockEntityTag(@NotNull BlockPos pos, @NotNull Level level, @Nullable Player player, @NotNull ItemStack stack, @NotNull BlockState state) {
        boolean bl = super.updateCustomBlockEntityTag(pos, level, player, stack, state);
        if (!level.isClientSide) {
            ((DyeableBlock<?, ?>) getBlock()).getAssoicatedBlockEntity(level, pos).ifPresent(a -> a.setColor(color));
        }
        return bl;
    }

    protected @NotNull String getOrCreateDescriptionId() {
        if (this.descriptionId == null) {
            this.descriptionId = Util.makeDescriptionId("item", Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(this)).withPrefix(color.getName() + "_"));
        }
        return this.descriptionId;
    }
}
