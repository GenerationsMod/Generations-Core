package generations.gg.generations.core.generationscore.common.world.shop

import net.minecraft.util.RandomSource
import net.minecraft.util.random.WeightedRandomList
import java.lang.System.arraycopy
import java.util.*
import kotlin.math.min

object ShopGenerator {
    @JvmStatic
    fun refresh(shop: Shop): Array<SimpleShopEntry> {
        val presets = shop.presets
        var maxEntries = shop.maxEntries
        val generatedEntries = arrayOfNulls<SimpleShopEntry>(maxEntries)
        var entryIndex = 0

        for ((_, entries, maxAppearingItems) in presets) {
            val maxAppearingEntries = maxAppearingItems.coerceAtMost(maxEntries).toInt()
            val presetEntries = generateEntries(entries, maxAppearingEntries)
            arraycopy(presetEntries, 0, generatedEntries, entryIndex, presetEntries.size)
            maxEntries -= maxAppearingEntries
            entryIndex += maxAppearingEntries
        }

        if (maxEntries > 0) {
            val entries = generateEntries(shop.entries, maxEntries)
            arraycopy(entries, 0, generatedEntries, entryIndex, entries.size)
        }

        val out: MutableList<SimpleShopEntry> = mutableListOf()
        generatedEntries.asSequence().filterNotNull().filterNot(out::contains).forEach(out::add)
        return out.toTypedArray()
    }

    private fun generateEntries(entries: List<ShopEntry>, maxEntries: Int): Array<SimpleShopEntry> {
        val rnd = RandomSource.create()
        val weightedEntries = WeightedRandomList.create(entries.stream()
            .map { obj: ShopEntry -> obj.toWeightedEntry() }.toList()
        )
        val out = arrayOfNulls<SimpleShopEntry>(maxEntries)
        for (i in 0 until maxEntries) {
            val entry = weightedEntries.getRandom(rnd)
            if (entry.isEmpty) {
                throw RuntimeException("Unable to generate entries")
            }
            val simpleEntry = entry.get().data.toSimpleEntry()
            out[i] = simpleEntry
        }
        return out.filterNotNull().toTypedArray()
    }
}