package generations.gg.generations.core.generationscore.common.world.item.legends

import com.mojang.serialization.Codec
import generations.gg.generations.core.generationscore.common.util.Codecs
import generations.gg.generations.core.generationscore.common.world.entity.TieredFishingHookEntity.Teir
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.LangTooltip
import generations.gg.generations.core.generationscore.common.world.item.TieredFishingRodItem
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import net.minecraft.network.chat.Component
import net.minecraft.util.StringRepresentable
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import java.util.*
import kotlin.math.min

class RubyRodItem(properties: Properties?, tier: Teir?) :
    TieredFishingRodItem(properties, tier), LangTooltip {
    override fun isEnchantable(stack: ItemStack): Boolean {
        return false
    }

    override fun appendHoverText(
        stack: ItemStack,
        level: TooltipContext,
        tooltipComponents: MutableList<Component>,
        isAdvanced: TooltipFlag
    ) {
        super.addText(stack, level, tooltipComponents, isAdvanced)
    }

    enum class LakeTrioShardType : StringRepresentable {
        WILLPOWER,
        KNOWLEDGE,
        EMOTION;

        override fun getSerializedName(): String {
            return name.lowercase(Locale.getDefault())
        }

        companion object {
            val CODEC: Codec<LakeTrioShardType> =  StringRepresentable.fromEnum { LakeTrioShardType.entries.toTypedArray() }
        }
    }

    data class FishedShards(var willpower: Int, var knowledge: Int, var emotion: Int, var show: Boolean) {
        operator fun get(shardType: LakeTrioShardType): Int = when(shardType) {
            LakeTrioShardType.WILLPOWER -> willpower
            LakeTrioShardType.KNOWLEDGE -> knowledge
            LakeTrioShardType.EMOTION -> emotion
        }

        operator fun set(shardType: LakeTrioShardType, value: Int) = when(shardType) {
            LakeTrioShardType.WILLPOWER -> willpower = value
            LakeTrioShardType.KNOWLEDGE -> knowledge = value
            LakeTrioShardType.EMOTION -> emotion = value
        }

        companion object {
            val EMPTY: FishedShards = FishedShards(0, 0, 0, true)

            val CODEC = Codecs.codec4(
                "willpower", Codec.INT, FishedShards::willpower,
                "knowledge", Codec.INT, FishedShards::knowledge,
                "emotion", Codec.INT, FishedShards::emotion,
                "show", Codec.BOOL, FishedShards::show,
                ::FishedShards
            )
        }
    }


    companion object {
        fun sanitizeList(
            list: ObjectArrayList<ItemStack>,
            currentShards: FishedShards
        ): ObjectArrayList<ItemStack> {
            list.removeIf { itemStack: ItemStack -> !isShard(itemStack) }

            val sanitizedList = ObjectArrayList<ItemStack>()

            for (itemStack in list) {
                val shardType = getShardType(itemStack.item)

                if (shardType != null) {
                    val currentCount = currentShards[shardType]
                    val stackCount = itemStack.count

                    if (currentCount < 9) {
                        val allowableCount = min(stackCount.toDouble(), (9 - currentCount).toDouble()).toInt()
                        val cappedStack = itemStack.copy()
                        cappedStack.count = allowableCount

                        sanitizedList.add(cappedStack)
                        currentShards[shardType] = (currentCount + allowableCount) // Update currentShards in place
                    }
                }
            }

            return sanitizedList
        }

        private fun getShardType(item: Item): LakeTrioShardType? {
            if (item === GenerationsItems.SHARD_OF_EMOTION) {
                return LakeTrioShardType.EMOTION
            } else if (item === GenerationsItems.SHARD_OF_KNOWLEDGE) {
                return LakeTrioShardType.KNOWLEDGE
            } else if (item === GenerationsItems.SHARD_OF_WILLPOWER) {
                return LakeTrioShardType.WILLPOWER
            }
            return null
        }

        @JvmStatic
        fun getFishedShard(stack: ItemStack): FishedShards? = stack.get(GenerationsDataComponents.FISHED_SHARDS.value())

        private fun isShard(itemStack: ItemStack): Boolean {
            val item = itemStack.item
            return item === GenerationsItems.SHARD_OF_EMOTION || item === GenerationsItems.SHARD_OF_KNOWLEDGE || item === GenerationsItems.SHARD_OF_WILLPOWER
        }

        @JvmStatic
        fun isFinished(itemstack: ItemStack): Boolean = getFishedShard(itemstack)?.let { shards -> shards.willpower >= 9 && shards.knowledge >= 9 && shards.emotion >= 9 } ?: false
    }
}