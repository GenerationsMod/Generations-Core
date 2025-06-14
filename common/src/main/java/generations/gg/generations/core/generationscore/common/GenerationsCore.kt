/**
 * The Main Class of the Generations-Core mod. (Common)
 * Registers the mod's items and blocks with Minecraft using Architectury.
 * @author Joseph T. McQuigg
 *
 * CopyRight (c) 2023 Generations-Mod
 */
package generations.gg.generations.core.generationscore.common

import com.cobblemon.mod.common.api.data.DataProvider
import com.cobblemon.mod.common.api.spawning.detail.SpawnDetail.Companion.registerSpawnType
import com.cobblemon.mod.common.api.storage.player.PlayerDataExtensionRegistry.register
import com.mojang.logging.LogUtils
import com.mojang.serialization.MapCodec
import dev.architectury.event.events.common.LootEvent
import generations.gg.generations.core.generationscore.common.api.GenerationsMolangFunctions
import generations.gg.generations.core.generationscore.common.api.data.GenerationsCoreEntityDataSerializers
import generations.gg.generations.core.generationscore.common.api.player.AccountInfo
import generations.gg.generations.core.generationscore.common.api.player.BiomesVisited
import generations.gg.generations.core.generationscore.common.api.player.Caught
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.config.Config
import generations.gg.generations.core.generationscore.common.config.ConfigLoader.loadConfig
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents
import generations.gg.generations.core.generationscore.common.world.container.GenerationsContainers
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities
import generations.gg.generations.core.generationscore.common.world.item.GenerationsArmor
import generations.gg.generations.core.generationscore.common.world.item.GenerationsCobblemonInteractions.registerDefaultCustomInteractions
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.GenerationsTools
import generations.gg.generations.core.generationscore.common.world.item.armor.GenerationsArmorMaterials
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import generations.gg.generations.core.generationscore.common.world.item.creativetab.GenerationsCreativeTabs
import generations.gg.generations.core.generationscore.common.world.item.legends.EnchantableItem
import generations.gg.generations.core.generationscore.common.world.item.legends.EnchantableItem.Companion.isEnchanted
import generations.gg.generations.core.generationscore.common.world.item.legends.EnchantableItem.Companion.isUsed
import generations.gg.generations.core.generationscore.common.world.item.legends.EnchantableItem.Companion.setEnchanted
import generations.gg.generations.core.generationscore.common.world.level.block.*
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.loot.LootItemConditionTypes
import generations.gg.generations.core.generationscore.common.world.loot.LootPoolEntryTypes
import generations.gg.generations.core.generationscore.common.world.recipe.*
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds
import generations.gg.generations.core.generationscore.common.world.spawning.ZygardeCellDetail
import net.minecraft.core.registries.Registries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.packs.PackType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.NestedLootTable
import org.apache.logging.log4j.util.TriConsumer
import org.slf4j.Logger
import java.util.function.Consumer
import java.util.function.IntConsumer

/**
 * The Main Class of the Generations-Core mod. (Common)
 * Registers the mod's items and blocks with Minecraft using Architectury.
 * @author Joseph T. McQuigg, WaterPicker
 */
object GenerationsCore {

    /** The mod id of the Generations-Core mod.  */
    const val MOD_ID: String = "generations_core"

    /** The logger for the Generations-Core mod.  */
	@JvmField
	val LOGGER: Logger = LogUtils.getLogger()

    /** The config for the Generations-Core mod.  */

	lateinit var CONFIG: Config
	lateinit var implementation: GenerationsImplementation

    @JvmField
	var dataProvider: DataProvider = GenerationsDataProvider.INSTANCE

    lateinit var RKS_RESULT_TYPE: RegistryContainer<RksResultType<*>, RksResult<*>>

    /**
     * Initializes the Generations-Core mod.
     */
	@JvmStatic
	fun init(implementation: GenerationsImplementation) {
        CONFIG = loadConfig(
            Config::class.java, "core", "main"
        )
        GenerationsCore.implementation = implementation

        RKS_RESULT_TYPE = RegistryContainer( "rks_result", RksResult<*>::type, RksResultType<*>::codec, RksResultType<*>::streamCodec)

        //		GenerationsDataProvider.INSTANCE.register(ShopPresets.instance());
//		GenerationsDataProvider.INSTANCE.register(Shops.instance());
        LootEvent.MODIFY_LOOT_TABLE.register(LootEvent.ModifyLootTable { lootId: ResourceKey<LootTable?>, context: LootEvent.LootTableModificationContext, builtin: Boolean ->
            val id = lootId.location()
            if (id.namespace == "minecraft" && id.path.contains("chests") && !id.path.contains("inject")) {
                val inject =
                    ResourceLocation.fromNamespaceAndPath(id.namespace, id.path.replace("chests", "chests/inject"))
                context.addPool(
                    LootPool.lootPool()
                        .add(NestedLootTable.lootTableReference(ResourceKey.create(Registries.LOOT_TABLE, inject)))
                )
            } else if (id.toString() == "minecraft:blocks/carrots") {
                context.addPool(
                    LootPool.lootPool().add(
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                                id("blocks/calyrex_roots")
                            )
                        )
                    )
                )
            }
        })

        registerSpawnType(
            ZygardeCellDetail.TYPE,
            ZygardeCellDetail::class.java
        )

        GenerationsCoreEntityDataSerializers.init()
        GenerationsStorage.init()
        GenerationsResources.init()
        GenerationsIngredidents.init()

        registerRegistires()

        GenerationsDataProvider.INSTANCE.registerDefaults()

        //TODO: Convert to our data thingy.
        register(
            AccountInfo.KEY,
            AccountInfo::class.java, false
        )
        register(
            Caught.KEY,
            Caught::class.java, false
        )
        register(
            BiomesVisited.KEY,
            BiomesVisited::class.java, false
        )

        //		PlayerDataExtensionRegistry.INSTANCE.register(CurryDex.KEY, CurryDex.class, false);
        GenerationsMolangFunctions.init()

        GenerationsCobblemonEvents.init()
        GenerationsArchitecturyEvents.init()

        registerDefaultCustomInteractions()


    }

    fun initBuiltinPacks(consumer: TriConsumer<PackType?, ResourceLocation?, MutableComponent?>?) {
//		consumer.accept(PackType.CLIENT_RESOURCES, GenerationsCore.id("smooth_pokemon"), Component.literal("Smooth Pokemon Models"));
    }

    fun onAnvilChange(
        left: ItemStack,
        right: ItemStack,
        player: Player,
        output: Consumer<ItemStack>,
        cost: IntConsumer,
        materialCost: IntConsumer
    ) {
        if (player is ServerPlayer && left.item.instanceOrNull<EnchantableItem>()?.neededEnchantmentLevel(player).orFalse { it  > 0 } && !isEnchanted(
                left
            ) && !isUsed(left) && right.isEmpty
        ) {
            output.accept(setEnchanted(left.copy(), true))
            materialCost.accept(0)
        }
    }

    /**
     * Creates a [ResourceLocation] with the Generations-Core Mod id.
     */
	@JvmStatic
	fun id(path: String): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path)
    }

    fun registerRegistires() {
        implementation.register(GenerationsDataComponents)
        implementation.register(GenerationsSounds)
        GenerationsBlocks.init(implementation::register)
        implementation.register(GenerationsPokeDolls)
        GenerationsWood.init(implementation::register)
        implementation.register(GenerationsOres)
        implementation.register(GenerationsDecorationBlocks)
        implementation.register(LootPoolEntryTypes)
        implementation.register(LootItemConditionTypes)
        implementation.register(GenerationsUtilityBlocks)
        implementation.register(GenerationsShrines)
        GenerationsItems.init(implementation::register)
        GenerationsBlocks.initItems(implementation::register)
        implementation.register(GenerationsBlockEntities)
        implementation.register(GenerationsEntities)
        implementation.register(GenerationsArmorMaterials)
        implementation.register(GenerationsArmor)
        implementation.register(GenerationsTools)
        implementation.register(GenerationsCreativeTabs)
        implementation.register(GenerationsContainers)
        implementation.register(GenerationsCoreRecipeTypes)
        implementation.register(GenerationsCoreRecipeSerializers)
        implementation.register(GenerationsCoreStats)
        implementation.register(GenerationsRksTypes)
    }
}

class RegistryContainer<T: Any, V: Any>(name: String, from: (V) -> T, mapCodec: (T) -> MapCodec<out V>, streamCodec: (T) -> StreamCodec<RegistryFriendlyByteBuf, out V>) {
    val key = ResourceKey.createRegistryKey<T>(name.generationsResource())
    val registry = GenerationsCore.implementation.createRegistry(key, true)
    val codec = registry.byNameCodec().dispatch(from::invoke, mapCodec::invoke)
    val streamCodec = ByteBufCodecs.registry(key).dispatch(from::invoke, streamCodec::invoke)
}

fun <T> T?.orFalse(predicate: (T) -> Boolean): Boolean = this?.let(predicate) ?: false
fun String.generationsResource() = GenerationsCore.id(this)