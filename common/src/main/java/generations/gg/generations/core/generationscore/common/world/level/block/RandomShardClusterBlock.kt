package generations.gg.generations.core.generationscore.common.world.level.block

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.util.RandomSource
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.block.AmethystClusterBlock
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.loot.LootParams
import net.minecraft.world.level.storage.loot.parameters.LootContextParams
import kotlin.math.min

class RandomShardClusterBlock(
    private val shardTag: TagKey<Item>,
    height: Float = 7.0f,
    width: Float = 3.0f,
    props: BlockBehaviour.Properties = BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER)
) : AmethystClusterBlock(height, width, props) {

    override fun getDrops(state: BlockState, builder: LootParams.Builder): List<ItemStack> {
        val registryAccess = builder.level.registryAccess()
        val tool = builder.getOptionalParameter(LootContextParams.TOOL)

        val enchantReg = registryAccess.registryOrThrow(Registries.ENCHANTMENT)
        val silkTouch = enchantReg.getHolderOrThrow(Enchantments.SILK_TOUCH)
        val fortune = enchantReg.getHolderOrThrow(Enchantments.FORTUNE)

        val enchantments = tool?.enchantments
        if (enchantments?.getLevel(silkTouch) != 0) {
            return listOf(ItemStack(this.asItem()))
        }

        val fortuneLevel = enchantments.getLevel(fortune)

        val tagSet = BuiltInRegistries.ITEM.getTag(shardTag).orElse(null)
            ?: return super.getDrops(state, builder)

        val tagList = tagSet.stream().toList().shuffled()
        val random = RandomSource.create()
        val shardTypesToDrop = random.nextInt(4) + 1

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