package generations.gg.generations.core.generationscore.common.world.level.block

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.state.properties.GenerationsBlockSetTypes
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.block.state.properties.WoodType
import kotlin.reflect.KFunction1

/**
 * @author JT122406
 * Generations wood blocks
 */
object GenerationsWood {
    @JvmField
    val WOOD_BLOCKS = object: BlockPlatformRegistry() {}
    val WOOD_SIGN = object: BlockPlatformRegistry() {}

    val ULTRA_JUNGLE_LOG = registerRotatedPillar("ultra_jungle_log", Blocks.JUNGLE_LOG)
    val STRIPPED_ULTRA_JUNGLE_LOG = registerRotatedPillar("stripped_ultra_jungle_log", Blocks.STRIPPED_JUNGLE_LOG)
    val ULTRA_JUNGLE_WOOD = registerRotatedPillar("ultra_jungle_wood", Blocks.JUNGLE_WOOD)
    val STRIPPED_ULTRA_JUNGLE_WOOD = registerRotatedPillar("stripped_ultra_jungle_wood", Blocks.STRIPPED_JUNGLE_WOOD)
    val ULTRA_JUNGLE_PLANKS = registerBlock("ultra_jungle_planks", Blocks.JUNGLE_PLANKS)
    val ULTRA_JUNGLE_SLAB = registerSlab("ultra_jungle_slab", Blocks.JUNGLE_SLAB)
    val ULTRA_JUNGLE_STAIRS = registerStair("ultra_jungle_stairs", Blocks.JUNGLE_STAIRS)
    val ULTRA_JUNGLE_BUTTON = registerButton("ultra_jungle_button", GenerationsBlockSetTypes.ULTRA_JUNGLE, Blocks.JUNGLE_BUTTON, 30)
    val ULTRA_JUNGLE_PRESSURE_PLATE = registerPressurePlate("ultra_jungle_pressure_plate", Blocks.JUNGLE_PRESSURE_PLATE, GenerationsBlockSetTypes.ULTRA_JUNGLE)
    val ULTRA_JUNGLE_CRAFTING_TABLE = registerBlockItem("ultra_jungle_crafting_table", GenerationsCraftingTableBlock())
    val ULTRA_JUNGLE_TRAPDOOR = registerTrapDoor("ultra_jungle_trapdoor", Blocks.JUNGLE_TRAPDOOR, GenerationsBlockSetTypes.ULTRA_JUNGLE)
    val ULTRA_JUNGLE_DOOR = registerDoor("ultra_jungle_door", Blocks.JUNGLE_DOOR, GenerationsBlockSetTypes.ULTRA_JUNGLE)
    val ULTRA_JUNGLE_FENCE = registerFence("ultra_jungle_fence", Blocks.JUNGLE_FENCE)
    val ULTRA_JUNGLE_FENCE_GATE = registerFenceGate("ultra_jungle_fence_gate", GenerationsWoodTypes.ULTRA_JUNGLE, Blocks.JUNGLE_FENCE_GATE)
    val ULTRA_JUNGLE_SIGN = registerStandingSign("ultra_jungle_sign", Blocks.JUNGLE_SIGN, GenerationsWoodTypes.ULTRA_JUNGLE)
    val ULTRA_JUNGLE_WALL_SIGN = registerWallSign("ultra_jungle_wall_sign", Blocks.JUNGLE_WALL_SIGN, GenerationsWoodTypes.ULTRA_JUNGLE)
    val ULTRA_JUNGLE_HANGING_SIGN = registerCeilingHangingSign("ultra_jungle_hanging_sign", Blocks.JUNGLE_HANGING_SIGN, GenerationsWoodTypes.ULTRA_JUNGLE)
    val ULTRA_JUNGLE_WALL_HANGING_SIGN = registerWallHangingSign("ultra_jungle_wall_hanging_sign", Blocks.JUNGLE_WALL_HANGING_SIGN, GenerationsWoodTypes.ULTRA_JUNGLE)
    val ULTRA_JUNGLE_BOOKSHELF = registerBlock("ultra_jungle_bookshelf", Blocks.BOOKSHELF)

    val ULTRA_DARK_LOG = registerRotatedPillar("ultra_dark_log", Blocks.DARK_OAK_LOG)
    val STRIPPED_ULTRA_DARK_LOG = registerRotatedPillar("stripped_ultra_dark_log", Blocks.STRIPPED_DARK_OAK_LOG)
    val ULTRA_DARK_WOOD = registerRotatedPillar("ultra_dark_wood", Blocks.DARK_OAK_WOOD)
    val STRIPPED_ULTRA_DARK_WOOD = registerRotatedPillar("stripped_ultra_dark_wood", Blocks.STRIPPED_DARK_OAK_WOOD)
    val ULTRA_DARK_PLANKS = registerBlock("ultra_dark_planks", Blocks.DARK_OAK_PLANKS)
    val ULTRA_DARK_SLAB = registerSlab("ultra_dark_slab", Blocks.DARK_OAK_SLAB)
    val ULTRA_DARK_STAIRS = registerStair("ultra_dark_stairs", Blocks.DARK_OAK_STAIRS)
    val ULTRA_DARK_BUTTON = registerButton("ultra_dark_button", GenerationsBlockSetTypes.ULTRA_DARK, Blocks.DARK_OAK_BUTTON, 30)
    val ULTRA_DARK_PRESSURE_PLATE = registerPressurePlate("ultra_dark_pressure_plate", Blocks.DARK_OAK_PRESSURE_PLATE, GenerationsBlockSetTypes.ULTRA_DARK)
    val ULTRA_DARK_CRAFTING_TABLE = registerBlockItem("ultra_dark_crafting_table", GenerationsCraftingTableBlock())
    val ULTRA_DARK_TRAPDOOR = registerTrapDoor("ultra_dark_trapdoor", Blocks.DARK_OAK_TRAPDOOR, GenerationsBlockSetTypes.ULTRA_DARK)
    val ULTRA_DARK_DOOR = registerDoor("ultra_dark_door", Blocks.DARK_OAK_DOOR, GenerationsBlockSetTypes.ULTRA_DARK)
    val ULTRA_DARK_FENCE = registerFence("ultra_dark_fence", Blocks.DARK_OAK_FENCE, )
    val ULTRA_DARK_FENCE_GATE = registerFenceGate("ultra_dark_fence_gate", GenerationsWoodTypes.ULTRA_DARK, Blocks.DARK_OAK_FENCE_GATE)
    val ULTRA_DARK_SIGN = registerStandingSign("ultra_dark_sign", Blocks.DARK_OAK_SIGN, GenerationsWoodTypes.ULTRA_DARK)
    val ULTRA_DARK_WALL_SIGN = registerWallSign("ultra_dark_wall_sign", Blocks.DARK_OAK_WALL_SIGN, GenerationsWoodTypes.ULTRA_DARK)
    val ULTRA_DARK_HANGING_SIGN = registerCeilingHangingSign("ultra_dark_hanging_sign", Blocks.DARK_OAK_HANGING_SIGN, GenerationsWoodTypes.ULTRA_DARK)
    val ULTRA_DARK_WALL_HANGING_SIGN = registerWallHangingSign("ultra_dark_wall_hanging_sign", Blocks.DARK_OAK_WALL_HANGING_SIGN, GenerationsWoodTypes.ULTRA_DARK)
    val ULTRA_DARK_BOOKSHELF = registerBlock("ultra_dark_bookshelf", Blocks.BOOKSHELF)

    val GHOST_LOG = registerRotatedPillar("ghost_log", Blocks.DARK_OAK_LOG)
    val STRIPPED_GHOST_LOG = registerRotatedPillar("stripped_ghost_log", Blocks.STRIPPED_DARK_OAK_LOG)
    val GHOST_WOOD = registerRotatedPillar("ghost_wood", Blocks.DARK_OAK_WOOD)
    val STRIPPED_GHOST_WOOD = registerRotatedPillar("stripped_ghost_wood", Blocks.STRIPPED_DARK_OAK_WOOD)
    val GHOST_PLANKS = registerBlock("ghost_planks", Blocks.DARK_OAK_PLANKS)
    val GHOST_SLAB = registerSlab("ghost_slab", Blocks.DARK_OAK_SLAB)
    val GHOST_STAIRS = registerStair("ghost_stairs", Blocks.DARK_OAK_STAIRS)
    val GHOST_BUTTON = registerButton("ghost_button", GenerationsBlockSetTypes.GHOST, Blocks.DARK_OAK_BUTTON, 30)
    val GHOST_PRESSURE_PLATE = registerPressurePlate("ghost_pressure_plate", Blocks.DARK_OAK_PRESSURE_PLATE, GenerationsBlockSetTypes.GHOST)
    val GHOST_CRAFTING_TABLE = registerBlockItem("ghost_crafting_table", GenerationsCraftingTableBlock())
    val GHOST_TRAPDOOR = registerTrapDoor("ghost_trapdoor", Blocks.DARK_OAK_TRAPDOOR, GenerationsBlockSetTypes.GHOST)
    val GHOST_DOOR = registerDoor("ghost_door", Blocks.DARK_OAK_DOOR, GenerationsBlockSetTypes.GHOST)
    val GHOST_FENCE = registerFence("ghost_fence", Blocks.DARK_OAK_FENCE)
    val GHOST_FENCE_GATE = registerFenceGate("ghost_fence_gate", GenerationsWoodTypes.GHOST, Blocks.DARK_OAK_FENCE_GATE)
    val GHOST_SIGN = registerStandingSign("ghost_sign", Blocks.DARK_OAK_SIGN, GenerationsWoodTypes.GHOST)
    val GHOST_WALL_SIGN = registerWallSign("ghost_wall_sign", Blocks.DARK_OAK_WALL_SIGN, GenerationsWoodTypes.GHOST)
    val GHOST_HANGING_SIGN = registerCeilingHangingSign("ghost_hanging_sign", Blocks.DARK_OAK_HANGING_SIGN, GenerationsWoodTypes.GHOST)
    val GHOST_WALL_HANGING_SIGN = registerWallHangingSign("ghost_wall_hanging_sign", Blocks.DARK_OAK_WALL_HANGING_SIGN, GenerationsWoodTypes.GHOST)
    @JvmField val GHOST_BOOKSHELF = registerBlock("ghost_bookshelf", Blocks.BOOKSHELF)

    private fun registerStair(name: String, block: Block) = registerBlockItem(name, StairBlock(block.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(block)))
    private fun registerSlab(name: String, block: Block)  = registerBlockItem(name, SlabBlock(BlockBehaviour.Properties.ofFullCopy(block)))
    private fun registerRotatedPillar(name: String, block: Block) = registerBlockItem(name, RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(block)))
    private fun registerStandingSign(name: String, block: Block, type: WoodType) = registerSignWithoutItem(name, GenerationsStandingSignBlock(type, BlockBehaviour.Properties.ofFullCopy(block)))
    private fun registerWallSign(name: String, block: Block, type: WoodType) = registerSignWithoutItem(name, GenerationsWallSignBlock(type, BlockBehaviour.Properties.ofFullCopy(block)))
    private fun registerCeilingHangingSign(name: String, block: Block, type: WoodType) = registerSignWithoutItem(name, GenerationsCeilingHangingSignBlock(type, BlockBehaviour.Properties.ofFullCopy(block)))
    private fun registerWallHangingSign(name: String, block: Block, type: WoodType) = registerSignWithoutItem(name, GenerationsWallHangingSignBlock(type, BlockBehaviour.Properties.ofFullCopy(block)))
    private fun registerDoor(name: String, block: Block, type: BlockSetType) = registerBlockItem(name, DoorBlock(type, BlockBehaviour.Properties.ofFullCopy(block)))
    private fun registerTrapDoor(name: String, block: Block, type: BlockSetType) = registerBlockItem(name, TrapDoorBlock(type, BlockBehaviour.Properties.ofFullCopy(block)))
    private fun registerFence(name: String, block: Block) = registerBlockItem(name, FenceBlock(BlockBehaviour.Properties.ofFullCopy(block)))
    private fun registerPressurePlate(name: String, block: Block, type: BlockSetType) = registerBlockItem(name, PressurePlateBlock(type, BlockBehaviour.Properties.ofFullCopy(block)))
    private fun registerButton(name: String, type: BlockSetType, block: Block, delay: Int) = registerBlockItem(name, ButtonBlock(type, delay, BlockBehaviour.Properties.ofFullCopy(block)))
    private fun registerBlock(name: String, block: Block) = registerBlockItem(name, Block(BlockBehaviour.Properties.ofFullCopy(block)))
    private fun registerFenceGate(name: String, type: WoodType, block: Block) = registerBlockItem(name, FenceGateBlock(type, BlockBehaviour.Properties.ofFullCopy(block)))

    private fun register(name: String, itemSupplier: (Item.Properties) -> Item) { GenerationsItems.ITEMS.create(name.generationsResource(), itemSupplier.invoke(Item.Properties())) }

    private fun <T : Block> registerBlockItem(name: String, blockSupplier: T): T {
        val block = WOOD_BLOCKS.create(name.generationsResource(), blockSupplier)
        register(
            name
        ) { properties: Item.Properties ->
            BlockItem(
                block,
                properties
            )
        }
        return block
    }

    private fun <T : Block> registerBlockWithoutItem(name: String, blockSupplier: T): T {
        return WOOD_BLOCKS.create(name.generationsResource(), blockSupplier)
    }

    private fun <T : Block> registerSignWithoutItem(name: String, blockSupplier: T): T {
        return WOOD_SIGN.create(name.generationsResource(), blockSupplier)
    }

    fun init(consumer: (PlatformRegistry<Block>) -> Unit) {
        GenerationsCore.LOGGER.info("Registering Generations Wood")
        consumer.invoke(WOOD_BLOCKS)
        consumer.invoke(WOOD_SIGN)
    }
}
