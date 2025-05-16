package generations.gg.generations.core.generationscore.common.world.level.block

import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.state.properties.GenerationsBlockSetTypes
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.block.state.properties.WoodType
import java.util.function.Function
import java.util.function.Supplier

/**
 * @author JT122406
 * Generations wood blocks
 */
object GenerationsWood {
    @JvmField
    val WOOD_BLOCKS: DeferredRegister<Block> = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK)
    val WOOD_SIGN: DeferredRegister<Block> = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK)

    @JvmField val ULTRA_JUNGLE_LOG: RegistrySupplier<RotatedPillarBlock> = registerRotatedPillar("ultra_jungle_log", Blocks.JUNGLE_LOG)
    @JvmField val STRIPPED_ULTRA_JUNGLE_LOG: RegistrySupplier<RotatedPillarBlock> = registerRotatedPillar("stripped_ultra_jungle_log", Blocks.STRIPPED_JUNGLE_LOG)
    @JvmField val ULTRA_JUNGLE_WOOD: RegistrySupplier<RotatedPillarBlock> = registerRotatedPillar("ultra_jungle_wood", Blocks.JUNGLE_WOOD)
    @JvmField val STRIPPED_ULTRA_JUNGLE_WOOD: RegistrySupplier<RotatedPillarBlock> = registerRotatedPillar("stripped_ultra_jungle_wood", Blocks.STRIPPED_JUNGLE_WOOD)
    @JvmField val ULTRA_JUNGLE_PLANKS: RegistrySupplier<Block> = registerBlock("ultra_jungle_planks", Blocks.JUNGLE_PLANKS)
    @JvmField val ULTRA_JUNGLE_SLAB: RegistrySupplier<SlabBlock> = registerSlab("ultra_jungle_slab", Blocks.JUNGLE_SLAB)
    @JvmField val ULTRA_JUNGLE_STAIRS: RegistrySupplier<StairBlock> = registerStair("ultra_jungle_stairs", Blocks.JUNGLE_STAIRS)
    @JvmField val ULTRA_JUNGLE_BUTTON: RegistrySupplier<ButtonBlock> = registerButton("ultra_jungle_button", GenerationsBlockSetTypes.ULTRA_JUNGLE, Blocks.JUNGLE_BUTTON, 30)
    @JvmField val ULTRA_JUNGLE_PRESSURE_PLATE: RegistrySupplier<PressurePlateBlock> = registerPressurePlate("ultra_jungle_pressure_plate", Blocks.JUNGLE_PRESSURE_PLATE, GenerationsBlockSetTypes.ULTRA_JUNGLE)
    @JvmField val ULTRA_JUNGLE_CRAFTING_TABLE: RegistrySupplier<GenerationsCraftingTableBlock> = registerBlockItem("ultra_jungle_crafting_table") { GenerationsCraftingTableBlock() }
    @JvmField val ULTRA_JUNGLE_TRAPDOOR: RegistrySupplier<TrapDoorBlock> = registerTrapDoor("ultra_jungle_trapdoor", Blocks.JUNGLE_TRAPDOOR, GenerationsBlockSetTypes.ULTRA_JUNGLE)
    @JvmField val ULTRA_JUNGLE_DOOR: RegistrySupplier<DoorBlock> = registerDoor("ultra_jungle_door", Blocks.JUNGLE_DOOR, GenerationsBlockSetTypes.ULTRA_JUNGLE)
    @JvmField val ULTRA_JUNGLE_FENCE: RegistrySupplier<FenceBlock> = registerFence("ultra_jungle_fence", Blocks.JUNGLE_FENCE)
    @JvmField val ULTRA_JUNGLE_FENCE_GATE: RegistrySupplier<FenceGateBlock> = registerFenceGate("ultra_jungle_fence_gate", GenerationsWoodTypes.ULTRA_JUNGLE, Blocks.JUNGLE_FENCE_GATE)
    @JvmField val ULTRA_JUNGLE_SIGN: RegistrySupplier<StandingSignBlock> = registerStandingSign("ultra_jungle_sign", Blocks.JUNGLE_SIGN, GenerationsWoodTypes.ULTRA_JUNGLE)
    @JvmField val ULTRA_JUNGLE_WALL_SIGN: RegistrySupplier<WallSignBlock> = registerWallSign("ultra_jungle_wall_sign", Blocks.JUNGLE_WALL_SIGN, GenerationsWoodTypes.ULTRA_JUNGLE)
    val ULTRA_JUNGLE_HANGING_SIGN: RegistrySupplier<CeilingHangingSignBlock> = registerCeilingHangingSign("ultra_jungle_hanging_sign", Blocks.JUNGLE_HANGING_SIGN, GenerationsWoodTypes.ULTRA_JUNGLE)
    val ULTRA_JUNGLE_WALL_HANGING_SIGN: RegistrySupplier<WallHangingSignBlock> = registerWallHangingSign("ultra_jungle_wall_hanging_sign", Blocks.JUNGLE_WALL_HANGING_SIGN, GenerationsWoodTypes.ULTRA_JUNGLE)
    @JvmField val ULTRA_JUNGLE_BOOKSHELF: RegistrySupplier<Block> = registerBlock("ultra_jungle_bookshelf", Blocks.BOOKSHELF)

    @JvmField val ULTRA_DARK_LOG: RegistrySupplier<RotatedPillarBlock> = registerRotatedPillar("ultra_dark_log", Blocks.DARK_OAK_LOG)
    @JvmField val STRIPPED_ULTRA_DARK_LOG: RegistrySupplier<RotatedPillarBlock> = registerRotatedPillar("stripped_ultra_dark_log", Blocks.STRIPPED_DARK_OAK_LOG)
    @JvmField val ULTRA_DARK_WOOD: RegistrySupplier<RotatedPillarBlock> = registerRotatedPillar("ultra_dark_wood", Blocks.DARK_OAK_WOOD)
    @JvmField val STRIPPED_ULTRA_DARK_WOOD: RegistrySupplier<RotatedPillarBlock> = registerRotatedPillar("stripped_ultra_dark_wood", Blocks.STRIPPED_DARK_OAK_WOOD)
    @JvmField val ULTRA_DARK_PLANKS: RegistrySupplier<Block> = registerBlock("ultra_dark_planks", Blocks.DARK_OAK_PLANKS)
    @JvmField val ULTRA_DARK_SLAB: RegistrySupplier<SlabBlock> = registerSlab("ultra_dark_slab", Blocks.DARK_OAK_SLAB)
    @JvmField val ULTRA_DARK_STAIRS: RegistrySupplier<StairBlock> = registerStair("ultra_dark_stairs", Blocks.DARK_OAK_STAIRS)
    @JvmField val ULTRA_DARK_BUTTON: RegistrySupplier<ButtonBlock> = registerButton("ultra_dark_button", GenerationsBlockSetTypes.ULTRA_DARK, Blocks.DARK_OAK_BUTTON, 30)
    @JvmField val ULTRA_DARK_PRESSURE_PLATE: RegistrySupplier<PressurePlateBlock> = registerPressurePlate("ultra_dark_pressure_plate", Blocks.DARK_OAK_PRESSURE_PLATE, GenerationsBlockSetTypes.ULTRA_DARK)
    @JvmField val ULTRA_DARK_CRAFTING_TABLE: RegistrySupplier<GenerationsCraftingTableBlock> = registerBlockItem("ultra_dark_crafting_table") { GenerationsCraftingTableBlock() }
    @JvmField val ULTRA_DARK_TRAPDOOR: RegistrySupplier<TrapDoorBlock> = registerTrapDoor("ultra_dark_trapdoor", Blocks.DARK_OAK_TRAPDOOR, GenerationsBlockSetTypes.ULTRA_DARK)
    @JvmField val ULTRA_DARK_DOOR: RegistrySupplier<DoorBlock> = registerDoor("ultra_dark_door", Blocks.DARK_OAK_DOOR, GenerationsBlockSetTypes.ULTRA_DARK)
    @JvmField val ULTRA_DARK_FENCE: RegistrySupplier<FenceBlock> = registerFence("ultra_dark_fence", Blocks.DARK_OAK_FENCE, )
    @JvmField val ULTRA_DARK_FENCE_GATE: RegistrySupplier<FenceGateBlock> = registerFenceGate("ultra_dark_fence_gate", GenerationsWoodTypes.ULTRA_DARK, Blocks.DARK_OAK_FENCE_GATE)
    @JvmField val ULTRA_DARK_SIGN: RegistrySupplier<StandingSignBlock> = registerStandingSign("ultra_dark_sign", Blocks.DARK_OAK_SIGN, GenerationsWoodTypes.ULTRA_DARK)
    @JvmField val ULTRA_DARK_WALL_SIGN: RegistrySupplier<WallSignBlock> = registerWallSign("ultra_dark_wall_sign", Blocks.DARK_OAK_WALL_SIGN, GenerationsWoodTypes.ULTRA_DARK)
    val ULTRA_DARK_HANGING_SIGN: RegistrySupplier<CeilingHangingSignBlock> = registerCeilingHangingSign("ultra_dark_hanging_sign", Blocks.DARK_OAK_HANGING_SIGN, GenerationsWoodTypes.ULTRA_DARK)
    val ULTRA_DARK_WALL_HANGING_SIGN: RegistrySupplier<WallHangingSignBlock> = registerWallHangingSign("ultra_dark_wall_hanging_sign", Blocks.DARK_OAK_WALL_HANGING_SIGN, GenerationsWoodTypes.ULTRA_DARK)
    @JvmField val ULTRA_DARK_BOOKSHELF: RegistrySupplier<Block> = registerBlock("ultra_dark_bookshelf", Blocks.BOOKSHELF)

    @JvmField val GHOST_LOG: RegistrySupplier<RotatedPillarBlock> = registerRotatedPillar("ghost_log", Blocks.DARK_OAK_LOG)
    @JvmField val STRIPPED_GHOST_LOG: RegistrySupplier<RotatedPillarBlock> = registerRotatedPillar("stripped_ghost_log", Blocks.STRIPPED_DARK_OAK_LOG)
    @JvmField val GHOST_WOOD: RegistrySupplier<RotatedPillarBlock> = registerRotatedPillar("ghost_wood", Blocks.DARK_OAK_WOOD)
    @JvmField val STRIPPED_GHOST_WOOD: RegistrySupplier<RotatedPillarBlock> = registerRotatedPillar("stripped_ghost_wood", Blocks.STRIPPED_DARK_OAK_WOOD)
    @JvmField val GHOST_PLANKS: RegistrySupplier<Block> = registerBlock("ghost_planks", Blocks.DARK_OAK_PLANKS)
    @JvmField val GHOST_SLAB: RegistrySupplier<SlabBlock> = registerSlab("ghost_slab", Blocks.DARK_OAK_SLAB)
    @JvmField val GHOST_STAIRS: RegistrySupplier<StairBlock> = registerStair("ghost_stairs", Blocks.DARK_OAK_STAIRS)
    @JvmField val GHOST_BUTTON: RegistrySupplier<ButtonBlock> = registerButton("ghost_button", GenerationsBlockSetTypes.GHOST, Blocks.DARK_OAK_BUTTON, 30)
    @JvmField val GHOST_PRESSURE_PLATE: RegistrySupplier<PressurePlateBlock> = registerPressurePlate("ghost_pressure_plate", Blocks.DARK_OAK_PRESSURE_PLATE, GenerationsBlockSetTypes.GHOST)
    @JvmField val GHOST_CRAFTING_TABLE: RegistrySupplier<GenerationsCraftingTableBlock> = registerBlockItem("ghost_crafting_table") { GenerationsCraftingTableBlock() }
    @JvmField val GHOST_TRAPDOOR: RegistrySupplier<TrapDoorBlock> = registerTrapDoor("ghost_trapdoor", Blocks.DARK_OAK_TRAPDOOR, GenerationsBlockSetTypes.GHOST)
    @JvmField val GHOST_DOOR: RegistrySupplier<DoorBlock> = registerDoor("ghost_door", Blocks.DARK_OAK_DOOR, GenerationsBlockSetTypes.GHOST)
    @JvmField val GHOST_FENCE: RegistrySupplier<FenceBlock> = registerFence("ghost_fence", Blocks.DARK_OAK_FENCE)
    @JvmField val GHOST_FENCE_GATE: RegistrySupplier<FenceGateBlock> = registerFenceGate("ghost_fence_gate", GenerationsWoodTypes.GHOST, Blocks.DARK_OAK_FENCE_GATE)
    @JvmField val GHOST_SIGN: RegistrySupplier<StandingSignBlock> = registerStandingSign("ghost_sign", Blocks.DARK_OAK_SIGN, GenerationsWoodTypes.GHOST)
    @JvmField val GHOST_WALL_SIGN: RegistrySupplier<WallSignBlock> = registerWallSign("ghost_wall_sign", Blocks.DARK_OAK_WALL_SIGN, GenerationsWoodTypes.GHOST)
    val GHOST_HANGING_SIGN: RegistrySupplier<CeilingHangingSignBlock> = registerCeilingHangingSign("ghost_hanging_sign", Blocks.DARK_OAK_HANGING_SIGN, GenerationsWoodTypes.GHOST)
    val GHOST_WALL_HANGING_SIGN: RegistrySupplier<WallHangingSignBlock> = registerWallHangingSign("ghost_wall_hanging_sign", Blocks.DARK_OAK_WALL_HANGING_SIGN, GenerationsWoodTypes.GHOST)
    @JvmField val GHOST_BOOKSHELF: RegistrySupplier<Block> = registerBlock("ghost_bookshelf", Blocks.BOOKSHELF)

    private fun registerStair(name: String, block: Block): RegistrySupplier<StairBlock> = registerBlockItem(name) { StairBlock(block.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(block)) }
    private fun registerSlab(name: String, block: Block): RegistrySupplier<SlabBlock> = registerBlockItem(name) { SlabBlock(BlockBehaviour.Properties.ofFullCopy(block)) }
    private fun registerRotatedPillar(name: String, block: Block): RegistrySupplier<RotatedPillarBlock> = registerBlockItem(name) { RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(block)) }
    private fun registerStandingSign(name: String, block: Block, type: WoodType, ): RegistrySupplier<StandingSignBlock> = registerSignWithoutItem(name) { GenerationsStandingSignBlock(type, BlockBehaviour.Properties.ofFullCopy(block)) }
    private fun registerWallSign(name: String, block: Block, type: WoodType): RegistrySupplier<WallSignBlock> = registerSignWithoutItem(name) { GenerationsWallSignBlock(type, BlockBehaviour.Properties.ofFullCopy(block)) }
    private fun registerCeilingHangingSign(name: String, block: Block, type: WoodType): RegistrySupplier<CeilingHangingSignBlock> = registerSignWithoutItem(name) { GenerationsCeilingHangingSignBlock(type, BlockBehaviour.Properties.ofFullCopy(block)) }
    private fun registerWallHangingSign(name: String, block: Block, type: WoodType): RegistrySupplier<WallHangingSignBlock> = registerSignWithoutItem(name) { GenerationsWallHangingSignBlock(type, BlockBehaviour.Properties.ofFullCopy(block)) }
    private fun registerDoor(name: String, block: Block, type: BlockSetType): RegistrySupplier<DoorBlock> = registerBlockItem(name) { DoorBlock(type, BlockBehaviour.Properties.ofFullCopy(block)) }
    private fun registerTrapDoor(name: String, block: Block, type: BlockSetType): RegistrySupplier<TrapDoorBlock> = registerBlockItem(name) { TrapDoorBlock(type, BlockBehaviour.Properties.ofFullCopy(block)) }
    private fun registerFence(name: String, block: Block): RegistrySupplier<FenceBlock> = registerBlockItem(name) { FenceBlock(BlockBehaviour.Properties.ofFullCopy(block)) }
    private fun registerPressurePlate(name: String, block: Block, type: BlockSetType): RegistrySupplier<PressurePlateBlock> = registerBlockItem(name) { PressurePlateBlock(type, BlockBehaviour.Properties.ofFullCopy(block)) }
    private fun registerButton(name: String, type: BlockSetType, block: Block, delay: Int): RegistrySupplier<ButtonBlock> = registerBlockItem<ButtonBlock>(name) { ButtonBlock(type, delay, BlockBehaviour.Properties.ofFullCopy(block)) }
    private fun registerBlock(name: String, block: Block) = registerBlockItem(name) { Block(BlockBehaviour.Properties.ofFullCopy(block)) }
    private fun registerFenceGate(name: String, type: WoodType, block: Block): RegistrySupplier<FenceGateBlock> = registerBlockItem(name) { FenceGateBlock(type, BlockBehaviour.Properties.ofFullCopy(block)) }

    private fun register(name: String, itemSupplier: Function<Item.Properties, Item>) { GenerationsItems.ITEMS.register(name) { itemSupplier.apply(Item.Properties()) }
    }

    private fun <T : Block> registerBlockItem(name: String, blockSupplier: Supplier<T>): RegistrySupplier<T> {
        val block = WOOD_BLOCKS.register(name, blockSupplier)
        register(
            name
        ) { properties: Item.Properties ->
            BlockItem(
                block.get(),
                properties
            )
        }
        return block
    }

    private fun <T : Block> registerBlockWithoutItem(name: String, blockSupplier: Supplier<T>): RegistrySupplier<T> {
        return WOOD_BLOCKS.register(name, blockSupplier)
    }

    private fun <T : Block> registerSignWithoutItem(name: String, blockSupplier: Supplier<T>): RegistrySupplier<T> {
        return WOOD_SIGN.register(name, blockSupplier)
    }

    fun init() {
        GenerationsCore.LOGGER.info("Registering Generations Wood")
        WOOD_BLOCKS.register()
        WOOD_SIGN.register()
    }
}
