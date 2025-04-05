package generations.gg.generations.core.generationscore.common.world.item.legends;

import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.common.world.entity.TieredFishingHookEntity;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.item.LangTooltip;
import generations.gg.generations.core.generationscore.common.world.item.TieredFishingRodItem;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RubyRodItem extends TieredFishingRodItem implements LangTooltip {
    public RubyRodItem(Properties properties, TieredFishingHookEntity.Teir tier) {
        super(properties, tier);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    public static ObjectArrayList<ItemStack> sanitizeList(ObjectArrayList<ItemStack> list, Map<RubyRodItem.LakeTrioShardType, Byte> currentShards) {
        list.removeIf(itemStack -> !isShard(itemStack));

        ObjectArrayList<ItemStack> sanitizedList = new ObjectArrayList<>();

        for (ItemStack itemStack : list) {
            var shardType = getShardType(itemStack.getItem());

            if (shardType != null) {
                int currentCount = currentShards.getOrDefault(shardType, (byte) 0);
                int stackCount = itemStack.getCount();

                if (currentCount < 11) {
                    int allowableCount = Math.min(stackCount, 11 - currentCount);
                    ItemStack cappedStack = itemStack.copy();
                    cappedStack.setCount(allowableCount);

                    sanitizedList.add(cappedStack);
                    currentShards.put(shardType, (byte) (currentCount + allowableCount)); // Update currentShards in place
                }
            }
        }

        return sanitizedList;
    }

    @Nullable
    private static LakeTrioShardType getShardType(Item item) {
        if (item == GenerationsItems.SHARD_OF_EMOTION.get()) {
            return LakeTrioShardType.EMOTION;
        } else if (item == GenerationsItems.SHARD_OF_KNOWLEDGE.get()) {
            return LakeTrioShardType.KNOWLEDGE;
        } else if (item == GenerationsItems.SHARD_OF_WILLPOWER.get()) {
            return LakeTrioShardType.WILLPOWER;
        }
        return null;
    }

    public static Map<LakeTrioShardType, Byte> getFishedShard(ItemStack stack) {
        var tag = stack.getOrCreateTagElement("fished_shards");

        var map = new HashMap<LakeTrioShardType, Byte>();

        map.put(LakeTrioShardType.WILLPOWER, tag.getByte("willpower"));
        map.put(LakeTrioShardType.KNOWLEDGE, tag.getByte("knowledge"));
        map.put(LakeTrioShardType.EMOTION, tag.getByte("emotion"));

        return map;
    }

    public static void saveShardCounts(ItemStack stack, Map<LakeTrioShardType, Byte> map) {
        var tag = stack.getOrCreateTagElement("fished_shards");

        tag.putByte("willpower", map.getOrDefault(LakeTrioShardType.WILLPOWER, (byte) 0));
        tag.putByte("knowledge", map.getOrDefault(LakeTrioShardType.KNOWLEDGE, (byte) 0));
        tag.putByte("emotion", map.getOrDefault(LakeTrioShardType.EMOTION, (byte) 0));
    }

    private static boolean isShard(ItemStack itemStack) {
        var item = itemStack.getItem();
        return item == GenerationsItems.SHARD_OF_EMOTION.get() || item == GenerationsItems.SHARD_OF_KNOWLEDGE.get() || item == GenerationsItems.SHARD_OF_WILLPOWER.get();
    }

    public static boolean isFinished(ItemStack itemstack) {
        var map = getFishedShard(itemstack);

        return map.get(LakeTrioShardType.WILLPOWER) >= 11 && map.get(LakeTrioShardType.KNOWLEDGE) >= 11 && map.get(LakeTrioShardType.KNOWLEDGE) >= 11;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        LangTooltip.super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }

    public enum LakeTrioShardType implements StringRepresentable {
        WILLPOWER,
        KNOWLEDGE,
        EMOTION;

        @Override
        public String getSerializedName() {
            return name().toLowerCase();
        }
    }
}
