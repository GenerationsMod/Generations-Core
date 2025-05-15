package generations.gg.generations.core.generationscore.common.world.loot

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.entries.LootPoolEntry
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer
import net.minecraft.world.level.storage.loot.functions.LootItemFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import java.util.function.Consumer

class ResouceKeyEntry internal constructor(
    weight: Int,
    quality: Int,
    conditions: List<LootItemCondition?>,
    functions: List<LootItemFunction?>,
    private val key: ResourceKey<Item>,
    private val expand: Boolean
) :
    LootPoolSingletonContainer(weight, quality, conditions, functions) {
    override fun getType(): LootPoolEntryType {
        return LootPoolEntryTypes.RESOURCE_KEY.get()
    }

    public override fun createItemStack(stackConsumer: Consumer<ItemStack>, lootContext: LootContext) {
        BuiltInRegistries.ITEM.getHolder(this.key).ifPresent { arg: Holder.Reference<Item> ->
            stackConsumer.accept(
                ItemStack(arg)
            )
        }
    }

    private fun expandTag(context: LootContext, generatorConsumer: Consumer<LootPoolEntry>): Boolean {
        if (this.canRun(context)) {
            BuiltInRegistries.ITEM.getHolder(this.key).ifPresent { holder: Holder.Reference<Item?>? ->
                generatorConsumer.accept(object :
                    EntryBase() {
                    override fun createItemStack(
                        stackConsumer: Consumer<ItemStack>,
                        lootContext: LootContext
                    ) {
                        stackConsumer.accept(ItemStack(holder))
                    }
                })
            }


            return true
        }
        return false
    }

    override fun expand(arg: LootContext, consumer: Consumer<LootPoolEntry>): Boolean {
        if (this.expand) {
            return this.expandTag(arg, consumer)
        }
        return super.expand(arg, consumer)
    }

    companion object {
        fun resourceKeyContents(key: ResourceKey<Item>): Builder<*> {
            return simpleBuilder { i: Int, j: Int, args: List<LootItemCondition?>, args2: List<LootItemFunction?> ->
                ResouceKeyEntry(
                    i,
                    j,
                    args,
                    args2,
                    key,
                    false
                )
            }
        }

        fun resourceKeyContents(key: String): Builder<*> =
            resourceKeyContents(ResourceKey.create(Registries.ITEM, ResourceLocation.parse(key)))

        fun expandResouceKey(key: ResourceKey<Item>): Builder<*> =
            simpleBuilder { i: Int, j: Int, args: List<LootItemCondition>, args2: List<LootItemFunction?> ->
                ResouceKeyEntry(
                    i,
                    j,
                    args,
                    args2,
                    key,
                    true
                )
            }

        val CODEC: MapCodec<ResouceKeyEntry> =
            RecordCodecBuilder.mapCodec { instance -> singletonFields(instance)
                    .and(
                        ResourceKey.codec(
                            Registries.ITEM
                        ).fieldOf("item").forGetter { a: ResouceKeyEntry -> a.key })
                    .and(
                        Codec.BOOL.optionalFieldOf("expand", false)
                            .forGetter { a: ResouceKeyEntry -> a.expand }).apply(
                        instance
                    ) { weight: Int, quality: Int, conditions: List<LootItemCondition?>, functions: List<LootItemFunction?>, key: ResourceKey<Item>, expand: Boolean ->
                        ResouceKeyEntry(
                            weight,
                            quality,
                            conditions,
                            functions,
                            key,
                            expand
                        )
                    }
            }
    }
}

