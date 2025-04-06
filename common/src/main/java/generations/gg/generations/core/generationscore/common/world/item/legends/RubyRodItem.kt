package generations.gg.generations.core.generationscore.common.world.item.legends

import com.cobblemon.mod.common.api.text.gray
import com.cobblemon.mod.common.api.text.red
import generations.gg.generations.core.generationscore.common.world.entity.TieredFishingHookEntity.Teir
import generations.gg.generations.core.generationscore.common.world.item.LangTooltip
import generations.gg.generations.core.generationscore.common.world.item.TieredFishingRodItem
import net.minecraft.network.chat.Component
import net.minecraft.util.StringRepresentable
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import java.util.*

class RubyRodItem(properties: Properties?, tier: Teir?) :
    TieredFishingRodItem(properties, tier), LangTooltip {
    override fun isEnchantable(stack: ItemStack): Boolean {
        return false
    }

    override fun appendHoverText(
        stack: ItemStack,
        level: Level?,
        tooltipComponents: MutableList<Component>,
        isAdvanced: TooltipFlag
    ) {
        val map = getFishedShard(stack)

        if (map.isEmpty()) super<LangTooltip>.appendHoverText(stack, level, tooltipComponents, isAdvanced)
        else {
            tooltipComponents.add("Shards Obtained:".red())
            if (map.containsKey(LakeTrioShardType.WILLPOWER)) tooltipComponents.add("Willpower: (${map[LakeTrioShardType.WILLPOWER]}/9)".gray())
            if (map.containsKey(LakeTrioShardType.EMOTION)) tooltipComponents.add("Emotion: (${map[LakeTrioShardType.EMOTION]}/9)".gray())
            if (map.containsKey(LakeTrioShardType.KNOWLEDGE)) tooltipComponents.add("Knowledge: (${map[LakeTrioShardType.KNOWLEDGE]}/9)".gray())
        }
    }

    enum class LakeTrioShardType : StringRepresentable {
        WILLPOWER,
        KNOWLEDGE,
        EMOTION;

        override fun getSerializedName(): String {
            return name.lowercase(Locale.getDefault())
        }
    }

    companion object {
        @JvmStatic
        fun getFishedShard(stack: ItemStack): Map<LakeTrioShardType, Byte> {
            val tag = stack.getOrCreateTagElement("fished_shards")

            val map = HashMap<LakeTrioShardType, Byte>()

            map[LakeTrioShardType.WILLPOWER] = tag.getByte("willpower")
            map[LakeTrioShardType.KNOWLEDGE] = tag.getByte("knowledge")
            map[LakeTrioShardType.EMOTION] = tag.getByte("emotion")

            return map
        }

        @JvmStatic
        fun saveShardCounts(stack: ItemStack, map: Map<LakeTrioShardType?, Byte?>) {
            val tag = stack.getOrCreateTagElement("fished_shards")

            tag.putByte("willpower", map.getOrDefault(LakeTrioShardType.WILLPOWER, 0.toByte())!!)
            tag.putByte("knowledge", map.getOrDefault(LakeTrioShardType.KNOWLEDGE, 0.toByte())!!)
            tag.putByte("emotion", map.getOrDefault(LakeTrioShardType.EMOTION, 0.toByte())!!)
        }

        @JvmStatic
        fun isFinished(itemstack: ItemStack): Boolean {
            val map = getFishedShard(itemstack)

            return map[LakeTrioShardType.WILLPOWER]!! >= 9 && map[LakeTrioShardType.KNOWLEDGE]!! >= 9 && map[LakeTrioShardType.EMOTION]!! >= 9
        }
    }
}
