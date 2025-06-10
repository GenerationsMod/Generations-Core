package generations.gg.generations.core.generationscore.common.world.level.block

import com.cobblemon.mod.common.api.pokeball.PokeBalls
import com.cobblemon.mod.common.pokeball.PokeBall
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.GenerationsItemUtils.generateBlockItem
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils.registerBlock
import generations.gg.generations.core.generationscore.common.world.GenerationsPokeBalls.STRANGE_BALL
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.entities.ModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericBlastFurnaceBlock
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericFurnaceBlock
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericSmokerBlock
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.*
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.BlockState
import java.util.*
import java.util.function.BiFunction
import java.util.function.Function
import java.util.function.Supplier

object GenerationsUtilityBlocks {
    @JvmField
    val UTILITY_BLOCKS: DeferredRegister<Block> = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK)

    val BALL_LOOTS: MutableList<RegistrySupplier<BallLootBlock>> = ArrayList()

    /**
     * Utility Blocks
     */
    @JvmField
    val COOKING_POT: RegistrySupplier<Block> = registerBlockItem(
        "cooking_pot"
    ) {
        CookingPotBlock(
            BlockBehaviour.Properties.of().strength(2.5f).randomTicks()
                .noOcclusion()
        )
    }

    //PC Blocks
    @JvmField
    val TABLE_PC: RegistrySupplier<TablePc> = registerBlockItem("table_pc") {
        TablePc(
            BlockBehaviour.Properties.of().noOcclusion()
                .requiresCorrectToolForDrops().strength(2f)
                .lightLevel { state: BlockState? ->
                    PcBlock.lumiance(
                        state!!
                    )
                })
    }
    @JvmField
    val ROTOM_PC: RegistrySupplier<RotomPc> = registerBlockItem(
        "rotom_pc"
    ) {
        RotomPc(
            BlockBehaviour.Properties.of().noOcclusion()
                .requiresCorrectToolForDrops().strength(2f)
                .lightLevel { state: BlockState? ->
                    PcBlock.lumiance(
                        state!!
                    )
                })
    }

    @JvmField
    val TRASH_CAN: RegistrySupplier<Block> = registerBlockItem(
        "trash_can"
    ) {
        TrashCanBlock(
            BlockBehaviour.Properties.of().destroyTime(1.0f)
                .sound(SoundType.METAL)
        )
    }

    @JvmField
    val BOX: RegistrySupplier<Block> = registerBlockItem(
        "box"
    ) { BoxBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)) }

    @JvmField
    val RKS_MACHINE: RegistrySupplier<RksMachineBlock> = registerBlockItem(
        "rks_machine"
    ) { RksMachineBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)) }

    @JvmField
    val SCARECROW: RegistrySupplier<Block> = registerBlockItem(
        "scarecrow"
    ) {
        ScarecrowBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                .dynamicShape().noOcclusion()
        )
    }

    val PC = registerDyed<ModelProvidingBlockEntity>("pc") { color: DyeColor, dyeColorRegistrySupplierMap: Map<DyeColor, Holder<Block>> ->
        Supplier {
            DyedPcBlock(Properties.of().noOcclusion()
                .requiresCorrectToolForDrops().strength(2f)
                .lightLevel { state: BlockState ->
                    PcBlock.lumiance(
                        state
                    )
                },
                color,
                dyeColorRegistrySupplierMap)
        }
    }

    @JvmField val WHITE_ELEVATOR: RegistrySupplier<Block> = registerBlockItem("white_elevator") { ElevatorBlock() }
    @JvmField val LIGHT_GRAY_ELEVATOR: RegistrySupplier<Block> = registerBlockItem("light_gray_elevator") { ElevatorBlock() }
    @JvmField val GRAY_ELEVATOR: RegistrySupplier<Block> = registerBlockItem("gray_elevator") { ElevatorBlock() }
    @JvmField val BLACK_ELEVATOR: RegistrySupplier<Block> = registerBlockItem("black_elevator") { ElevatorBlock() }
    @JvmField val BROWN_ELEVATOR: RegistrySupplier<Block> = registerBlockItem("brown_elevator") { ElevatorBlock() }
    @JvmField val RED_ELEVATOR: RegistrySupplier<Block> = registerBlockItem("red_elevator") { ElevatorBlock() }
    @JvmField val ORANGE_ELEVATOR: RegistrySupplier<Block> = registerBlockItem("orange_elevator") { ElevatorBlock() }
    @JvmField val YELLOW_ELEVATOR: RegistrySupplier<Block> = registerBlockItem("yellow_elevator") { ElevatorBlock() }
    @JvmField val LIME_ELEVATOR: RegistrySupplier<Block> = registerBlockItem("lime_elevator") { ElevatorBlock() }
    @JvmField val GREEN_ELEVATOR: RegistrySupplier<Block> = registerBlockItem("green_elevator") { ElevatorBlock() }
    @JvmField val CYAN_ELEVATOR: RegistrySupplier<Block> = registerBlockItem("cyan_elevator") { ElevatorBlock() }
    @JvmField val LIGHT_BLUE_ELEVATOR: RegistrySupplier<Block> = registerBlockItem("light_blue_elevator") { ElevatorBlock() }
    @JvmField val BLUE_ELEVATOR: RegistrySupplier<Block> = registerBlockItem("blue_elevator") { ElevatorBlock() }
    @JvmField val PURPLE_ELEVATOR: RegistrySupplier<Block> = registerBlockItem("purple_elevator") { ElevatorBlock() }
    @JvmField val MAGENTA_ELEVATOR: RegistrySupplier<Block> = registerBlockItem("magenta_elevator") { ElevatorBlock() }
    @JvmField val PINK_ELEVATOR: RegistrySupplier<Block> = registerBlockItem("pink_elevator") { ElevatorBlock() }

    @JvmField var BEAST_BALL_LOOT = registerLoot("beast", PokeBalls.BEAST_BALL)
    @JvmField var CHERISH_BALL_LOOT = registerLoot("cherish", PokeBalls.CHERISH_BALL)
    @JvmField var DIVE_BALL_LOOT = registerLoot("dive", PokeBalls.DIVE_BALL)
    @JvmField var DREAM_BALL_LOOT = registerLoot("dream", PokeBalls.DREAM_BALL)
    @JvmField var DUSK_BALL_LOOT = registerLoot("dusk", PokeBalls.DUSK_BALL)
    @JvmField var FAST_BALL_LOOT = registerLoot("fast", PokeBalls.FAST_BALL)
    @JvmField var FRIEND_BALL_LOOT = registerLoot("friend", PokeBalls.FRIEND_BALL)
    @JvmField var GIGATON_BALL_LOOT = registerLoot("gigaton", PokeBalls.ANCIENT_GIGATON_BALL)
    @JvmField var GREAT_BALL_LOOT = registerLoot("great", PokeBalls.GREAT_BALL)
    @JvmField var HEAL_BALL_LOOT = registerLoot("heal", PokeBalls.HEAL_BALL)
    @JvmField var HEAVY_BALL_LOOT = registerLoot("heavy", PokeBalls.HEAVY_BALL)
    @JvmField var JET_BALL_LOOT = registerLoot("jet", PokeBalls.ANCIENT_JET_BALL)
    @JvmField var LEADEN_BALL_LOOT = registerLoot("leaden", PokeBalls.ANCIENT_LEADEN_BALL)
    @JvmField var LEVEL_BALL_LOOT = registerLoot("level", PokeBalls.LEVEL_BALL)
    @JvmField var LOVE_BALL_LOOT = registerLoot("love", PokeBalls.LOVE_BALL)
    @JvmField var LURE_BALL_LOOT = registerLoot("lure", PokeBalls.LURE_BALL)
    @JvmField var LUXURY_BALL_LOOT = registerLoot("luxury", PokeBalls.LUXURY_BALL)
    @JvmField var MASTER_BALL_LOOT = registerLoot("master", PokeBalls.MASTER_BALL)
    @JvmField var MOON_BALL_LOOT = registerLoot("moon", PokeBalls.MOON_BALL)
    @JvmField var NEST_BALL_LOOT = registerLoot("nest", PokeBalls.NEST_BALL)
    @JvmField var NET_BALL_LOOT = registerLoot("net", PokeBalls.NET_BALL)
    @JvmField var ORIGIN_BALL_LOOT = registerLoot("origin", PokeBalls.ANCIENT_ORIGIN_BALL)
    @JvmField var PARK_BALL_LOOT = registerLoot("park", PokeBalls.PARK_BALL)
    @JvmField var POKE_BALL_LOOT = registerLoot("poke", PokeBalls.POKE_BALL)
    @JvmField var PREMIER_BALL_LOOT = registerLoot("premier", PokeBalls.PREMIER_BALL)
    @JvmField var QUICK_BALL_LOOT = registerLoot("quick", PokeBalls.QUICK_BALL)
    @JvmField var REPEAT_BALL_LOOT = registerLoot("repeat", PokeBalls.REPEAT_BALL)
    @JvmField var SAFARI_BALL_LOOT = registerLoot("safari", PokeBalls.SAFARI_BALL)
    @JvmField var SPORT_BALL_LOOT = registerLoot("sport", PokeBalls.SPORT_BALL)
    @JvmField var STRANGE_BALL_LOOT = registerLoot("strange", STRANGE_BALL)
    @JvmField var TIMER_BALL_LOOT = registerLoot("timer", PokeBalls.TIMER_BALL)
    @JvmField var ULTRA_BALL_LOOT = registerLoot("ultra", PokeBalls.ULTRA_BALL)
    @JvmField var WING_BALL_LOOT = registerLoot("wing", PokeBalls.ANCIENT_WING_BALL)

    private fun registerLoot(name: String, ball: PokeBall): RegistrySupplier<BallLootBlock> {
        val properties = Properties.of().randomTicks().sound(SoundType.METAL).strength(-1.0f, 3600000.0f).noOcclusion().noLootTable()

        val block = registerBlockItem(
            name + "_ball_loot"
        ) { BallLootBlock(properties, name, ball) }
        BALL_LOOTS.add(block)
        return block
    }

    @JvmField
    val CHARGE_STONE_FURNACE: RegistrySupplier<GenericFurnaceBlock> = registerBlockItem("charge_stone_furnace", ::GenericFurnaceBlock)

    @JvmField
    val CHARGE_STONE_BLAST_FURNACE: RegistrySupplier<GenericBlastFurnaceBlock> = registerBlockItem("charge_stone_blast_furnace", ::GenericBlastFurnaceBlock)

    @JvmField
    val CHARGE_STONE_SMOKER: RegistrySupplier<GenericSmokerBlock> = registerBlockItem("charge_stone_smoker", ::GenericSmokerBlock)

    @JvmField
    val VOLCANIC_STONE_FURNACE: RegistrySupplier<GenericFurnaceBlock> = registerBlockItem("volcanic_stone_furnace", ::GenericFurnaceBlock)

    @JvmField
    val VOLCANIC_STONE_BLAST_FURNACE: RegistrySupplier<GenericBlastFurnaceBlock> = registerBlockItem("volcanic_stone_blast_furnace", ::GenericBlastFurnaceBlock)

    @JvmField
    val VOLCANIC_STONE_SMOKER: RegistrySupplier<GenericSmokerBlock> = registerBlockItem(
        "volcanic_stone_smoker"
    ) { GenericSmokerBlock() }

    private fun <T : BlockItem?> register(
        name: String,
        itemSupplier: Function<Item.Properties, T>
    ): RegistrySupplier<T> {
        return GenerationsItems.ITEMS.register(
            name
        ) { itemSupplier.apply(Item.Properties()) }
    }

    private fun <T : Block> registerBlockItem(name: String, blockSupplier: Supplier<T>): RegistrySupplier<T> {
        val block = registerBlock(name, blockSupplier)
        register(
            name
        ) { properties: Item.Properties? ->
            generateBlockItem(
                block.get(),
                properties!!
            )
        }
        return block
    }

    private fun <T : Block> registerBlock(name: String, blockSupplier: Supplier<T>): RegistrySupplier<T> {
        return registerBlock(UTILITY_BLOCKS, name, blockSupplier)
    }

    fun <T : ModelProvidingBlockEntity> registerDyed(
        name: String,
        blockSupplier: BiFunction<DyeColor, MutableMap<DyeColor, Holder<Block>>, Supplier<Block>>
    ): DyedGroup {
        val dyeMap = mutableMapOf<DyeColor, Holder<Block>>()
        DyeColor.entries.forEach { dyeColor: DyeColor ->
            val properName = dyeColor.serializedName + "_" + name
            val block = registerBlock(properName, blockSupplier.apply(dyeColor, dyeMap))
            register(properName) { BlockItem(block.get(), it) }
            dyeMap[dyeColor] = block
        }

        return DyedGroup(dyeMap)
    }


    fun init() {
        GenerationsCore.LOGGER.info("Registering Generations Utility Blocks")
        UTILITY_BLOCKS.register()
    }
}
