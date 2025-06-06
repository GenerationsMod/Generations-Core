package generations.gg.generations.core.generationscore.forge.datagen.generators.loot

import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import net.minecraft.core.registries.Registries
import net.minecraft.data.loot.LootTableSubProvider
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import java.util.*
import java.util.function.BiConsumer

class GenerationsChestLoot : LootTableSubProvider {
    override fun generate(output: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>) {
        ChestLootDataGenA.generate(output)
        ChestLootDataGenB.generate(output)
        ChestLootDataGenC.generate(output)
    }

    companion object {
        val BEAST_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/beast_ball"))
        val CHERISH_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/cherish_ball"))
        val DIVE_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/dive_ball"))
        val DREAM_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/dream_ball"))
        val DUSK_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/dusk_ball"))
        val FAST_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/fast_ball"))
        val FRIEND_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/friend_ball"))
        val GIGATON_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/gigaton_ball"))
        val GREAT_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/great_ball"))
        val HEAL_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/heal_ball"))
        val HEAVY_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/heavy_ball"))
        val JET_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/jet_ball"))
        val LEADEN_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/leaden_ball"))
        val LEVEL_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/level_ball"))
        val LOVE_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/love_ball"))
        val LURE_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/lure_ball"))
        val LUXURY_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/luxury_ball"))
        val MASTER_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/master_ball"))
        val MOON_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/moon_ball"))
        val NEST_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/nest_ball"))
        val NET_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/net_ball"))
        val ORIGIN_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/origin_ball"))
        val PARK_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/park_ball"))
        val POKE_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/poke_ball"))
        val PREMIER_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/premier_ball"))
        val QUICK_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/quick_ball"))
        val REPEAT_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/repeat_ball"))
        val SAFARI_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/safari_ball"))
        val SPORT_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/sport_ball"))
        val STRANGE_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/strange_ball"))
        val TIMER_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/timer_ball"))
        val ULTRA_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/ultra_ball"))
        val WING_BALL: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/wing_ball"))
        val CALYREX_ROOTS: ResourceKey<LootTable> =
            ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("blocks/calyrex_roots"))

        fun registerBallLootPoolUniform(
            output: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>,
            ball: ResourceKey<LootTable>,
            min: Float,
            max: Float,
            vararg items: Any
        ) {
            registerBallLootPool(output, ball, UniformGenerator.between(min, max), *items)
        }

        fun registerBallLootPoolConstant(
            output: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>,
            ball: ResourceKey<LootTable>,
            constant: Float,
            vararg items: Any
        ) {
            registerBallLootPool(output, ball, ConstantValue.exactly(constant), *items)
        }

        fun registerBallLootPool(
            output: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>,
            ball: ResourceKey<LootTable>,
            provider: NumberProvider,
            vararg items: Any
        ) {
            output.accept(ball, LootTable.lootTable().withPool(createPool(provider, *processItems(items))))
        }

        fun processItems(objects: Array<out Any>): Array<Item> = objects.map { `object`: Any -> process(`object`) }.filterNotNull().toTypedArray()

        fun process(`object`: Any): Item? = `object`.instanceOrNull<RegistrySupplier<*>>()?.get()?.instanceOrNull<ItemLike>()?.asItem() ?: `object`.instanceOrNull<ItemLike>()?.asItem()

        fun createPool(rolls: NumberProvider, vararg items: Item): LootPool.Builder {
            val pool = LootPool.lootPool().setRolls(rolls)
            for (item in items) pool.add(LootItem.lootTableItem(item))

            return pool
        }
    }
}

