package generations.gg.generations.core.generationscore.common.world.level.block

import com.cobblemon.mod.common.api.pokeball.PokeBalls
import com.cobblemon.mod.common.pokeball.PokeBall
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils.registerBlock
import generations.gg.generations.core.generationscore.common.world.GenerationsPokeBalls.STRANGE_BALL
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericBlastFurnaceBlock
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericFurnaceBlock
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericSmokerBlock
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.*
import net.minecraft.core.Holder
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour.Properties

object GenerationsUtilityBlocks: BlockPlatformRegistry() {

    val BALL_LOOTS: MutableList<Holder<Block>> = ArrayList()

    /**
     * Utility Blocks
     */
    val COOKING_POT = registerBlockItem("cooking_pot") { CookingPotBlock(Properties.of().strength(2.5f).randomTicks().noOcclusion()) }

    //PC Blocks
    val TABLE_PC = registerBlockItem("table_pc") { TablePc(Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(2f).lightLevel(PcBlock.Companion::lumiance)) }
    val ROTOM_PC = registerBlockItem("rotom_pc") { RotomPc(Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(2f).lightLevel(PcBlock.Companion::lumiance)) }


    val TRASH_CAN = registerBlockItem("trash_can") { TrashCanBlock(Properties.of().destroyTime(1.0f).sound(SoundType.METAL)) }

    val BOX = registerBlockItem("box") { BoxBlock(Properties.ofFullCopy(Blocks.OAK_WOOD)) }

    val RKS_MACHINE = registerBlockItem("rks_machine") { RksMachineBlock(Properties.ofFullCopy(Blocks.IRON_BLOCK)) }

    val SCARECROW = registerBlockItem("scarecrow") { ScarecrowBlock(Properties.ofFullCopy(Blocks.OAK_WOOD).dynamicShape().noOcclusion()) }

    val PC = registerDyed("pc") { color, dyeColorRegistrySupplierMap -> DyedPcBlock(Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(2f).lightLevel(PcBlock.Companion::lumiance), color, dyeColorRegistrySupplierMap) }

    val WHITE_ELEVATOR = registerBlockItem("white_elevator", ::ElevatorBlock)
    val LIGHT_GRAY_ELEVATOR = registerBlockItem("light_gray_elevator", ::ElevatorBlock)
    val GRAY_ELEVATOR = registerBlockItem("gray_elevator", ::ElevatorBlock)
    val BLACK_ELEVATOR = registerBlockItem("black_elevator", ::ElevatorBlock)
    val BROWN_ELEVATOR = registerBlockItem("brown_elevator", ::ElevatorBlock)
    val RED_ELEVATOR = registerBlockItem("red_elevator", ::ElevatorBlock)
    val ORANGE_ELEVATOR = registerBlockItem("orange_elevator", ::ElevatorBlock)
    val YELLOW_ELEVATOR = registerBlockItem("yellow_elevator", ::ElevatorBlock)
    val LIME_ELEVATOR = registerBlockItem("lime_elevator", ::ElevatorBlock)
    val GREEN_ELEVATOR = registerBlockItem("green_elevator", ::ElevatorBlock)
    val CYAN_ELEVATOR = registerBlockItem("cyan_elevator", ::ElevatorBlock)
    val LIGHT_BLUE_ELEVATOR = registerBlockItem("light_blue_elevator", ::ElevatorBlock)
    val BLUE_ELEVATOR = registerBlockItem("blue_elevator", ::ElevatorBlock)
    val PURPLE_ELEVATOR = registerBlockItem("purple_elevator", ::ElevatorBlock)
    val MAGENTA_ELEVATOR = registerBlockItem("magenta_elevator", ::ElevatorBlock)
    val PINK_ELEVATOR = registerBlockItem("pink_elevator", ::ElevatorBlock)

    val BEAST_BALL_LOOT = registerLoot("beast", PokeBalls.BEAST_BALL)
    val CHERISH_BALL_LOOT = registerLoot("cherish", PokeBalls.CHERISH_BALL)
    val DIVE_BALL_LOOT = registerLoot("dive", PokeBalls.DIVE_BALL)
    val DREAM_BALL_LOOT = registerLoot("dream", PokeBalls.DREAM_BALL)
    val DUSK_BALL_LOOT = registerLoot("dusk", PokeBalls.DUSK_BALL)
    val FAST_BALL_LOOT = registerLoot("fast", PokeBalls.FAST_BALL)
    val FRIEND_BALL_LOOT = registerLoot("friend", PokeBalls.FRIEND_BALL)
    val GIGATON_BALL_LOOT = registerLoot("gigaton", PokeBalls.ANCIENT_GIGATON_BALL)
    val GREAT_BALL_LOOT = registerLoot("great", PokeBalls.GREAT_BALL)
    val HEAL_BALL_LOOT = registerLoot("heal", PokeBalls.HEAL_BALL)
    val HEAVY_BALL_LOOT = registerLoot("heavy", PokeBalls.HEAVY_BALL)
    val JET_BALL_LOOT = registerLoot("jet", PokeBalls.ANCIENT_JET_BALL)
    val LEADEN_BALL_LOOT = registerLoot("leaden", PokeBalls.ANCIENT_LEADEN_BALL)
    val LEVEL_BALL_LOOT = registerLoot("level", PokeBalls.LEVEL_BALL)
    val LOVE_BALL_LOOT = registerLoot("love", PokeBalls.LOVE_BALL)
    val LURE_BALL_LOOT = registerLoot("lure", PokeBalls.LURE_BALL)
    val LUXURY_BALL_LOOT = registerLoot("luxury", PokeBalls.LUXURY_BALL)
    val MASTER_BALL_LOOT = registerLoot("master", PokeBalls.MASTER_BALL)
    val MOON_BALL_LOOT = registerLoot("moon", PokeBalls.MOON_BALL)
    val NEST_BALL_LOOT = registerLoot("nest", PokeBalls.NEST_BALL)
    val NET_BALL_LOOT = registerLoot("net", PokeBalls.NET_BALL)
    val ORIGIN_BALL_LOOT = registerLoot("origin", PokeBalls.ANCIENT_ORIGIN_BALL)
    val PARK_BALL_LOOT = registerLoot("park", PokeBalls.PARK_BALL)
    val POKE_BALL_LOOT = registerLoot("poke", PokeBalls.POKE_BALL)
    val PREMIER_BALL_LOOT = registerLoot("premier", PokeBalls.PREMIER_BALL)
    val QUICK_BALL_LOOT = registerLoot("quick", PokeBalls.QUICK_BALL)
    val REPEAT_BALL_LOOT = registerLoot("repeat", PokeBalls.REPEAT_BALL)
    val SAFARI_BALL_LOOT = registerLoot("safari", PokeBalls.SAFARI_BALL)
    val SPORT_BALL_LOOT = registerLoot("sport", PokeBalls.SPORT_BALL)
    val STRANGE_BALL_LOOT = registerLoot("strange", STRANGE_BALL)
    val TIMER_BALL_LOOT = registerLoot("timer", PokeBalls.TIMER_BALL)
    val ULTRA_BALL_LOOT = registerLoot("ultra", PokeBalls.ULTRA_BALL)
    val WING_BALL_LOOT = registerLoot("wing", PokeBalls.ANCIENT_WING_BALL)

    val CHARGE_STONE_FURNACE = registerBlockItem("charge_stone_furnace", ::GenericFurnaceBlock)

    val CHARGE_STONE_BLAST_FURNACE = registerBlockItem("charge_stone_blast_furnace", ::GenericBlastFurnaceBlock)

    val CHARGE_STONE_SMOKER = registerBlockItem("charge_stone_smoker", ::GenericSmokerBlock)

    val VOLCANIC_STONE_FURNACE = registerBlockItem("volcanic_stone_furnace", ::GenericFurnaceBlock)

    val VOLCANIC_STONE_BLAST_FURNACE = registerBlockItem("volcanic_stone_blast_furnace", ::GenericBlastFurnaceBlock)

    @JvmField
    val VOLCANIC_STONE_SMOKER = registerBlockItem("volcanic_stone_smoker", ::GenericSmokerBlock)

    private fun <T : BlockItem> register(
        name: String,
        itemSupplier: (Item.Properties) -> T
    ): Holder<Item> = GenerationsItems.ITEMS.create(name) { itemSupplier.invoke(Item.Properties()) }

    private fun <T : Block> registerBlockItem(name: String, blockSupplier: () -> T): Holder<Block> {
        val block = registerBlock(name, blockSupplier)
        register(name) { properties -> GenerationsBlockItem(block, properties) }
        return block
    }

private fun registerLoot(name: String, ball: PokeBall): Holder<Block> {
    val properties = Properties.of().randomTicks().sound(SoundType.METAL).strength(-1.0f, 3600000.0f).noOcclusion().noLootTable()

    val block = registerBlockItem(name + "_ball_loot") { BallLootBlock(properties, name, ball) }
    BALL_LOOTS.add(block)
    return block
}


    private fun <T : Block> registerBlock(name: String, blockSupplier: () -> T): Holder<Block> {
        return registerBlock(this, name, blockSupplier)
    }

    private fun registerDyed(name: String, blockSupplier: (DyeColor, MutableMap<DyeColor, Holder<Block>>) -> Block): DyedGroup {
        val dyeMap = mutableMapOf<DyeColor, Holder<Block>>()
        DyeColor.entries.forEach { dyeColor: DyeColor ->
            val properName = dyeColor.serializedName + "_" + name
            val block = registerBlock(properName, { blockSupplier.invoke(dyeColor, dyeMap) })
            register(properName) { GenerationsBlockItem(block, it) }
            dyeMap[dyeColor] = block
        }

        return DyedGroup(dyeMap)
    }

    override fun init() {
        GenerationsCore.LOGGER.info("Registering Generations Utility Blocks")
        super.init()
    }
}
