package generations.gg.generations.core.generationscore.common.world.level.block

import net.minecraft.core.Holder
import net.minecraft.core.HolderGetter
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderSet
import net.minecraft.core.component.DataComponentHolder
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.server.ReloadableServerRegistries
import net.minecraft.tags.TagKey
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.item.enchantment.ItemEnchantments
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DropExperienceBlock
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.loot.LootParams
import net.minecraft.world.level.storage.loot.parameters.LootContextParams
import kotlin.math.min

class RandomShardOreBlock(
    xpRange: IntProvider,
    private val shardTag: TagKey<Item>
) : DropExperienceBlock(xpRange, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)) {

    override fun getDrops(state: BlockState, builder: LootParams.Builder): List<ItemStack> {
        println("test call")
        val registryAccess = builder.level.registryAccess()
        val tool = builder.getOptionalParameter(LootContextParams.TOOL)
        val silkTouch = registryAccess
            .registryOrThrow(Registries.ENCHANTMENT)
            .getHolderOrThrow(Enchantments.SILK_TOUCH)
        val fortune = registryAccess
            .registryOrThrow(Registries.ENCHANTMENT)
            .getHolderOrThrow(Enchantments.FORTUNE)
        val enchantments = tool?.enchantments
        if (enchantments?.getLevel(silkTouch) != 0) {
            println("silk touch")
            return listOf(ItemStack(this.asItem()))
        }
        val fortuneLevel = enchantments.getLevel(fortune)

        val tagSet = BuiltInRegistries.ITEM.getTag(shardTag).orElse(null)
            ?: return super.getDrops(state, builder)

        val tagList = tagSet.stream().toList().shuffled()
        val random = RandomSource.create()
        val shardTypesToDrop = random.nextInt(4) + 1 // 1 to 4 types

        val drops = mutableListOf<ItemStack>()

        for (i in 0 until min(shardTypesToDrop, tagList.size)) {
            val item = tagList[i].value()
            val baseAmount = random.nextInt(2) + 1
            val fortuneBonus = if (fortuneLevel > 0) random.nextInt(fortuneLevel + 2) else 0
            drops.add(ItemStack(item, baseAmount + fortuneBonus))
        }

        return drops
    }
}
