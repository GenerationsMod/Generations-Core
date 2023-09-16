package generations.gg.generations.core.generationscore.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NpcPathTool extends Item {
    public NpcPathTool(Properties arg) {
        super(arg);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null) {
            ItemStack stack = context.getItemInHand();
            BlockPos pos = context.getClickedPos();
            List<BlockPos> path = getPath(stack);
            if (path.contains(pos) && context.getPlayer().isDiscrete()) {
                removeFromPath(stack, pos);
            } else {
                addToPath(stack, pos);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if (player.isDiscrete()) {
            clearPath(stack);
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.fail(stack);
    }

//    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag isAdvanced) {
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.literal("- Right-Click a block to add").withStyle(ChatFormatting.GRAY));
            tooltipComponents.add(Component.literal("- Sneak+Right-Click a block to remove").withStyle(ChatFormatting.GRAY));
            tooltipComponents.add(Component.literal("- Sneak+Right-Click in the air to clear").withStyle(ChatFormatting.GRAY));
        } else {
            tooltipComponents.add(Component.literal("Hold ").withStyle(ChatFormatting.GRAY)
                    .append(Component.literal("SHIFT").withStyle(ChatFormatting.YELLOW))
                    .append(Component.literal(" for usage information!").withStyle(ChatFormatting.GRAY)));
        }
        if (Screen.hasControlDown()) {
            tooltipComponents.add(Component.empty());
            tooltipComponents.add(Component.literal("Path:").withStyle(ChatFormatting.GRAY));
            var path = getPath(stack);
            int size = Math.min(path.size(), 8);
            for (int i = 0; i < size; i++) {
                tooltipComponents.add(Component.literal(path.get(i).toShortString()).withStyle(ChatFormatting.GRAY));
            }
            if (size < path.size()) {
                tooltipComponents.add(Component.literal("...").withStyle(ChatFormatting.GRAY));
            }
        } else {
            tooltipComponents.add(Component.literal("Hold ").withStyle(ChatFormatting.GRAY)
                    .append(Component.literal("CONTROL").withStyle(ChatFormatting.AQUA))
                    .append(Component.literal(" for path information!").withStyle(ChatFormatting.GRAY)));
        }
    }

    public static List<BlockPos> getPath(ItemStack stack) {
        var tag = stack.getOrCreateTag();
        if (tag.contains("path")) {
            return Arrays.stream(tag.getLongArray("path")).mapToObj(BlockPos::of).toList();
        }
        return Collections.emptyList();
    }

    public static void setPath(ItemStack stack, @Nullable List<BlockPos> path) {
        var tag = stack.getOrCreateTag();
        if (path == null || path.size() == 0) {
            tag.remove("path");
        } else {
            tag.putLongArray("path", path.stream().mapToLong(BlockPos::asLong).toArray());
        }
    }

    public static void addToPath(ItemStack stack, BlockPos pos) {
        var tag = stack.getOrCreateTag();
        if (!tag.contains("path") || tag.getLongArray("path").length == 0) {
            tag.putLongArray("path", new long[] { pos.asLong() });
            return;
        }

        var currentPath = tag.getLongArray("path");
        var newPath = new long[currentPath.length + 1];
        System.arraycopy(currentPath, 0, newPath, 0, currentPath.length);
        newPath[currentPath.length] = pos.asLong();
        tag.putLongArray("path", newPath);
    }

    public static void removeFromPath(ItemStack stack, BlockPos pos) {
        var tag = stack.getOrCreateTag();
        if (!tag.contains("path"))
            return;

        var currentPath = tag.getLongArray("path");
        var blockpos = pos.asLong();
        int i = -1;
        for (int j = currentPath.length - 1; j >= 0; j--) {
            if (currentPath[j] == blockpos) {
                i = j;
                break;
            }
        }

        if (i != -1) {
            if (currentPath.length == 1) {
                tag.remove("path");
                return;
            }

            var newPath = new long[currentPath.length - 1];
            System.arraycopy(currentPath, 0, newPath, 0, i);
            System.arraycopy(currentPath, i + 1, newPath, i, currentPath.length - i - 1);
            tag.putLongArray("path", newPath);
        }
    }

    public static void clearPath(ItemStack stack) {
        var tag = stack.getOrCreateTag();
        if (tag.contains("path")) {
            tag.remove("path");
        }
    }
}