package generations.gg.generations.core.generationscore.common.world.level.block

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.util.GenerationsItemUtils
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.decorations.*
import generations.gg.generations.core.generationscore.common.world.level.block.entities.BallDisplayBlock
import generations.gg.generations.core.generationscore.common.world.level.block.entities.BallDisplayBlock.DisplayState
import generations.gg.generations.core.generationscore.common.world.level.block.entities.VendingMachineBlock
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockBehaviour.Properties.of
import net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy
import java.util.*

object GenerationsDecorationBlocks: BlockPlatformRegistry() {

    @JvmField val BALL_DISPLAY_BLOCKS = mutableListOf<BallDisplayBlock>()

    /**
     * Decoration Blocks
     */
    @JvmField
    val HOUSE_LAMP = registerDecorationItem("house_lamp", HouseLampBlock(of().destroyTime(1.0f).sound(SoundType.METAL).lightLevel { 15 }))

    @JvmField
    val SWITCH = registerDecorationItem("switch", SwitchBlock(of().destroyTime(1.0f).sound(SoundType.METAL)))
    @JvmField
    val LITWICK_CANDLE = registerDecorationItem("litwick_candle", LitwickCandleBlock(ofFullCopy(Blocks.CANDLE).lightLevel { 11 }.destroyTime(0.7f)))

    @JvmField
    val LITWICK_CANDLES = registerDecorationItem("litwick_candles", LitwickCandlesBlock(ofFullCopy(Blocks.CANDLE).lightLevel { 15 }.destroyTime(0.5f)))

    @JvmField
    val VENDING_MACHINE = registerDyed("vending_machine", of().destroyTime(1.0f).sound(SoundType.METAL).requiresCorrectToolForDrops(), ::VendingMachineBlock)

    @JvmField
    val STREET_LAMP = registerDyed("street_lamp", of().destroyTime(1.0f).sound(SoundType.METAL).lightLevel { value -> if (value.block.instanceOrNull<StreetLampBlock>()?.getHeightValue(value) == 1) 15 else 0 }.requiresCorrectToolForDrops(), ::StreetLampBlock)
    @JvmField val DOUBLE_STREET_LAMP = registerDecorationItem("double_street_lamp", DoubleStreetLampBlock(of()))

    private fun registerCouch(variant: VariantCouchBlock.Variant): DyedGroup {
        return registerDyed(
            "couch_" + variant.name.lowercase(),
            ofFullCopy(Blocks.WHITE_WOOL)
        ) { properties, color, function -> VariantCouchBlock(properties, color, function, variant) }
    }

    @JvmField
    val COUCH_ARM_LEFT = registerCouch(VariantCouchBlock.Variant.ARM_LEFT)
    @JvmField
    val COUCH_ARM_RIGHT = registerCouch(VariantCouchBlock.Variant.ARM_RIGHT)
    @JvmField
    val COUCH_CORNER_LEFT = registerCouch(VariantCouchBlock.Variant.CORNER_LEFT)
    @JvmField
    val COUCH_CORNER_RIGHT = registerCouch(VariantCouchBlock.Variant.CORNER_RIGHT)
    @JvmField
    val COUCH_MIDDLE = registerCouch(VariantCouchBlock.Variant.MIDDLE)
    @JvmField
    val COUCH_OTTOMAN = registerCouch(VariantCouchBlock.Variant.OTTOMAN)

    @JvmField
    val SNORLAX_BEAN_BAG = registerDecorationItem("snorlax_bean_bag", BeanBagBlock(of().sound(SoundType.WOOL).strength(.5f).ignitedByLava()))
    @JvmField
    val PASTEL_BEAN_BAG = registerDyed("pastel_bean_bag", of().sound(SoundType.WOOL).strength(.5f).ignitedByLava(), ::PastelBeanBagBlock)
    @JvmField
    val SWIVEL_CHAIR = registerDyed("swivel_chair", of().sound(SoundType.WOOL).strength(.5f).ignitedByLava(), ::SwivelChairBlock)
    @JvmField
    val BENCH = registerDecorationItem("bench", BenchBlock(ofFullCopy(Blocks.OAK_WOOD).sound(SoundType.WOOL).strength(1.0f).sound(SoundType.WOOD)))

    @JvmField
    val COUCH = registerDecorationItem("couch", CouchBlock(ofFullCopy(Blocks.OAK_WOOD).sound(SoundType.WOOL).strength(1.0f).sound(SoundType.WOOD)))
    @JvmField
    val BUSH = registerDecorationItem("bush", BushBlock(ofFullCopy(Blocks.OAK_WOOD).sound(SoundType.GRASS).strength(1.0f)))

    @JvmField
    val DESK = registerDecorationItem("desk", DeskBlock(ofFullCopy(Blocks.OAK_WOOD).strength(1.0f).sound(SoundType.WOOD)))

    @JvmField
    val FRIDGE = registerDecorationItem("fridge", FridgeBlock(of().sound(SoundType.WOOD).strength(.5f)))

    @JvmField
    val WORK_DESK = registerDecorationItem("work_desk", WorkDeskBlock(of().sound(SoundType.WOOD).strength(1.0f)))
    @JvmField
    val SHELF = registerDecorationItem("shelf", ShelfBlock(ofFullCopy(Blocks.IRON_BLOCK).strength(1.0f)))
    @JvmField
    val BOOK_SHELF = registerDecorationItem("book_shelf", BookShelfBlock(of().sound(SoundType.WOOD).strength(1.0f).requiresCorrectToolForDrops()))
    @JvmField val POKEBALL_PILLAR = registerDecorationItem("pokeball_pillar", PokeballPillarBlock(ofFullCopy(Blocks.STONE).sound(SoundType.STONE).strength(1.0f)))

    @JvmField
    val HDTV = registerDecorationItem("hdtv", HdTvBlock(of().sound(SoundType.WOOD).strength(.5f)))

    val SHOP_DISPLAY_CASE_1 = registerDecorationItem("shop_display_case_1", ShopDisplayBlock(of(), 1, 0, 0, "case_1"))
    val SHOP_DISPLAY_CASE_2 = registerDecorationItem("shop_display_case_2", ShopDisplayBlock(of(), 1, 0, 0, "case_2"))
    val SHOP_DISPLAY_SMALL_1 = registerDecorationItem("shop_display_small_1", ShopDisplayBlock(of(), 1, 1, 0, "round_1"))
    val SHOP_DISPLAY_SMALL_2 = registerDecorationItem("shop_display_small_2", ShopDisplayBlock(of(), 1, 1, 0, "round_2"))
    @JvmField val SHOP_DISPLAY_LARGE_SHELF_1 = registerDecorationItem("shop_display_large_shelf_1", ShopDisplayBlock(of(), 1, 2, 0, "shelf_1"))
    @JvmField val SHOP_DISPLAY_LARGE_SHELF_2 = registerDecorationItem("shop_display_large_shelf_2", ShopDisplayBlock(of(), 1, 2, 0, "shelf_2"))

    //Cushion
    val FOONGUS_CUSHION = registerDecorationItem("foongus_cushion", CushionBlock(ofFullCopy(Blocks.WHITE_WOOL), "foongus"))
    val GREATBALL_CUSHION = registerDecorationItem("greatball_cushion", CushionBlock(ofFullCopy(Blocks.WHITE_WOOL), "greatball"))
    val POKEBALL_CUSHION = registerDecorationItem("pokeball_cushion", CushionBlock(ofFullCopy(Blocks.WHITE_WOOL), "pokeball"))
    val MASTERBALL_CUSHION = registerDecorationItem("masterball_cushion", CushionBlock(ofFullCopy(Blocks.WHITE_WOOL), "masterball"))

    fun registerDyed(
        name: String,
        properties: BlockBehaviour.Properties,
        blockSupplier: (BlockBehaviour.Properties, DyeColor, Map<DyeColor, Block>) -> Block
    ): DyedGroup {
        val dyeMap = mutableMapOf<DyeColor, Block>()

        DyeColor.entries.forEach { dyeColor: DyeColor ->
            val properName = "${dyeColor.serializedName}_$name"
            val block = registerBlock(properName, blockSupplier.invoke(properties, dyeColor, dyeMap))

            register(properName) { properties: Item.Properties -> BlockItem(block, properties) }
            dyeMap[dyeColor] = block
        }

        return DyedGroup(dyeMap)
    }


    /**
     * Decoration Blocks (Vending Machine)
     */
    //Ball Displays
    @JvmField val EMPTY_BALL_DISPLAY = registerBallDisplay(DisplayState.EMPTY)
    @JvmField val POKE_BALL_DISPLAY = registerBallDisplay(DisplayState.POKE)
    @JvmField val GREAT_BALL_DISPLAY = registerBallDisplay(DisplayState.GREAT)
    @JvmField val ULTRA_BALL_DISPLAY = registerBallDisplay(DisplayState.ULTRA)
    @JvmField val MASTER_BALL_DISPLAY = registerBallDisplay(DisplayState.MASTER)
    @JvmField val CHERISH_BALL_DISPLAY = registerBallDisplay(DisplayState.CHERISH)
    @JvmField val DIVE_BALL_DISPLAY = registerBallDisplay(DisplayState.DIVE)
    @JvmField val DUSK_BALL_DISPLAY = registerBallDisplay(DisplayState.DUSK)
    @JvmField val FAST_BALL_DISPLAY = registerBallDisplay(DisplayState.FAST)
    @JvmField val FRIEND_BALL_DISPLAY = registerBallDisplay(DisplayState.FRIEND)
    @JvmField val HEAVY_BALL_DISPLAY = registerBallDisplay(DisplayState.HEAVY)
    @JvmField val LEVEL_BALL_DISPLAY = registerBallDisplay(DisplayState.LEVEL)
    @JvmField val LOVE_BALL_DISPLAY = registerBallDisplay(DisplayState.LOVE)
    @JvmField val LURE_BALL_DISPLAY = registerBallDisplay(DisplayState.LURE)
    @JvmField val LUXURY_BALL_DISPLAY = registerBallDisplay(DisplayState.LUXURY)
    @JvmField val MOON_BALL_DISPLAY = registerBallDisplay(DisplayState.MOON)
    @JvmField val NEST_BALL_DISPLAY = registerBallDisplay(DisplayState.NEST)
    @JvmField val NET_BALL_DISPLAY = registerBallDisplay(DisplayState.NET)
    @JvmField val PARK_BALL_DISPLAY = registerBallDisplay(DisplayState.PARK)
    @JvmField val PREMIER_BALL_DISPLAY = registerBallDisplay(DisplayState.PREMIER)
    @JvmField val QUICK_BALL_DISPLAY = registerBallDisplay(DisplayState.QUICK)
    @JvmField val REPEAT_BALL_DISPLAY = registerBallDisplay(DisplayState.REPEAT)
    @JvmField val SAFARI_BALL_DISPLAY = registerBallDisplay(DisplayState.SAFARI)
    @JvmField val SPORT_BALL_DISPLAY = registerBallDisplay(DisplayState.SPORT)
    @JvmField val TIMER_BALL_DISPLAY = registerBallDisplay(DisplayState.TIMER)
    @JvmField val HEAL_BALL_DISPLAY = registerBallDisplay(DisplayState.HEAL)
    //public static final RegistrySupplier<BallDisplayBlock> GS_BALL_DISPLAY = registerBallDisplay(DisplayState.GS);

    private fun registerBallDisplay(state: DisplayState): BallDisplayBlock = registerDecorationItem(state.name.lowercase(Locale.getDefault()) + "_ball_display", BallDisplayBlock(state).also {
            BALL_DISPLAY_BLOCKS.add(it)
        })

    private fun <T : Block> registerDecorationItem(name: String, blockSupplier: T): T = registerBlock(name, blockSupplier).also {
        register(name) { properties -> GenerationsItemUtils.generateBlockItem(it, properties) }
    }

    private fun <T : Block> registerBlock(name: String, blockSupplier: T): T = GenerationsUtils.registerBlock(this, name, blockSupplier)

    private fun <T : BlockItem> register(name: String, itemSupplier: (Item.Properties) -> T): T = GenerationsItems.ITEMS.create(name.generationsResource(), itemSupplier.invoke(Item.Properties()))

    override fun init(consumer: (ResourceLocation, Block) -> Unit) {
        GenerationsCore.LOGGER.info("Registering Generations Decorations")
        super.init(consumer)
    }
}