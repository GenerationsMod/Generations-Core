package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.state.properties.GenerationsBlockSetTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author JT122406
 * Generations wood blocks
 */
public class GenerationsWood {

    public static final DeferredRegister<Block> WOOD_BLOCKS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<Block> WOOD_SIGN = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);


    public static final RegistrySupplier<RotatedPillarBlock> ULTRA_JUNGLE_LOG = registerBlockItem("ultra_jungle_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_LOG)));
    public static final RegistrySupplier<RotatedPillarBlock> STRIPPED_ULTRA_JUNGLE_LOG = registerBlockItem("stripped_ultra_jungle_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_JUNGLE_LOG)));
    public static final RegistrySupplier<Block> ULTRA_JUNGLE_WOOD = registerBlockItem("ultra_jungle_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_WOOD)));
    public static final RegistrySupplier<Block> STRIPPED_ULTRA_JUNGLE_WOOD = registerBlockItem("stripped_ultra_jungle_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_JUNGLE_WOOD)));
    public static final RegistrySupplier<Block> ULTRA_JUNGLE_PLANKS = registerBlockItem("ultra_jungle_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.JUNGLE_PLANKS)));
    public static final RegistrySupplier<SlabBlock> ULTRA_JUNGLE_SLAB = registerBlockItem("ultra_jungle_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_SLAB)));
    public static final RegistrySupplier<StairBlock> ULTRA_JUNGLE_STAIRS =  registerBlockItem("ultra_jungle_stairs", () -> new StairBlock(Blocks.JUNGLE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.JUNGLE_STAIRS)));
    public static final RegistrySupplier<ButtonBlock> ULTRA_JUNGLE_BUTTON = registerBlockItem("ultra_jungle_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_BUTTON), GenerationsBlockSetTypes.ULTRA_JUNGLE, 30, true));
    public static final RegistrySupplier<PressurePlateBlock>  ULTRA_JUNGLE_PRESSURE_PLATE = registerBlockItem("ultra_jungle_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.JUNGLE_PRESSURE_PLATE), GenerationsBlockSetTypes.ULTRA_JUNGLE));
    public static final RegistrySupplier<GenerationsCraftingTableBlock> ULTRA_JUNGLE_CRAFTING_TABLE = registerBlockItem("ultra_jungle_crafting_table", GenerationsCraftingTableBlock::new);
    public static final RegistrySupplier<TrapDoorBlock> ULTRA_JUNGLE_TRAPDOOR = registerBlockItem("ultra_jungle_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_TRAPDOOR), GenerationsBlockSetTypes.ULTRA_JUNGLE));
    public static final RegistrySupplier<DoorBlock> ULTRA_JUNGLE_DOOR = registerBlockItem("ultra_jungle_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_DOOR), GenerationsBlockSetTypes.ULTRA_JUNGLE));
    public static final RegistrySupplier<FenceBlock>  ULTRA_JUNGLE_FENCE = registerBlockItem("ultra_jungle_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_FENCE)));
    public static final RegistrySupplier<FenceGateBlock> ULTRA_JUNGLE_FENCE_GATE = registerBlockItem("ultra_jungle_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_FENCE_GATE), GenerationsWoodTypes.ULTRA_JUNGLE));
    public static final RegistrySupplier<StandingSignBlock> ULTRA_JUNGLE_SIGN = registerBlockWithoutItem("ultra_jungle_sign", () -> new GenerationsStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_SIGN), GenerationsWoodTypes.ULTRA_JUNGLE));
    public static final RegistrySupplier<WallSignBlock> ULTRA_JUNGLE_WALL_SIGN = registerSignWithoutItem("ultra_jungle_wall_sign", () -> new GenerationsWallSignBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_WALL_SIGN), GenerationsWoodTypes.ULTRA_JUNGLE));
    public static final RegistrySupplier<CeilingHangingSignBlock> ULTRA_JUNGLE_HANGING_SIGN = registerBlockWithoutItem("ultra_jungle_hanging_sign", () -> new GenerationsCeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_HANGING_SIGN), GenerationsWoodTypes.ULTRA_JUNGLE));
    public static final RegistrySupplier<WallHangingSignBlock> ULTRA_JUNGLE_WALL_HANGING_SIGN = registerSignWithoutItem("ultra_jungle_wall_hanging_sign", () -> new GenerationsWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_WALL_HANGING_SIGN), GenerationsWoodTypes.ULTRA_JUNGLE));
    public static final RegistrySupplier<Block> ULTRA_JUNGLE_BOOKSHELF = registerBlockItem("ultra_jungle_bookshelf", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    public static final RegistrySupplier<RotatedPillarBlock> ULTRA_DARK_LOG = registerBlockItem("ultra_dark_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LOG)));
    public static final RegistrySupplier<RotatedPillarBlock> STRIPPED_ULTRA_DARK_LOG = registerBlockItem("stripped_ultra_dark_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_DARK_OAK_LOG)));
    public static final RegistrySupplier<Block> ULTRA_DARK_WOOD = registerBlockItem("ultra_dark_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_WOOD)));
    public static final RegistrySupplier<Block> STRIPPED_ULTRA_DARK_WOOD = registerBlockItem("stripped_ultra_dark_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_DARK_OAK_WOOD)));
    public static final RegistrySupplier<Block> ULTRA_DARK_PLANKS = registerBlockItem("ultra_dark_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_PLANKS)));
    public static final RegistrySupplier<SlabBlock> ULTRA_DARK_SLAB = registerBlockItem("ultra_dark_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_SLAB)));
    public static final RegistrySupplier<StairBlock> ULTRA_DARK_STAIRS =  registerBlockItem("ultra_dark_stairs", () -> new StairBlock(Blocks.DARK_OAK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.DARK_OAK_STAIRS)));
    public static final RegistrySupplier<ButtonBlock> ULTRA_DARK_BUTTON = registerBlockItem("ultra_dark_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_BUTTON), GenerationsBlockSetTypes.ULTRA_DARK, 30, true));
    public static final RegistrySupplier<PressurePlateBlock>  ULTRA_DARK_PRESSURE_PLATE = registerBlockItem("ultra_dark_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.DARK_OAK_PRESSURE_PLATE), GenerationsBlockSetTypes.ULTRA_DARK));
    public static final RegistrySupplier<GenerationsCraftingTableBlock> ULTRA_DARK_CRAFTING_TABLE = registerBlockItem("ultra_dark_crafting_table", GenerationsCraftingTableBlock::new);
    public static final RegistrySupplier<TrapDoorBlock> ULTRA_DARK_TRAPDOOR = registerBlockItem("ultra_dark_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_TRAPDOOR), GenerationsBlockSetTypes.ULTRA_DARK));
    public static final RegistrySupplier<DoorBlock> ULTRA_DARK_DOOR = registerBlockItem("ultra_dark_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_DOOR), GenerationsBlockSetTypes.ULTRA_DARK));
    public static final RegistrySupplier<FenceBlock>  ULTRA_DARK_FENCE = registerBlockItem("ultra_dark_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_FENCE)));
    public static final RegistrySupplier<FenceGateBlock> ULTRA_DARK_FENCE_GATE = registerBlockItem("ultra_dark_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_FENCE_GATE), GenerationsWoodTypes.ULTRA_DARK));
    public static final RegistrySupplier<StandingSignBlock> ULTRA_DARK_SIGN = registerBlockWithoutItem("ultra_dark_sign", () -> new GenerationsStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_SIGN), GenerationsWoodTypes.ULTRA_DARK));
    public static final RegistrySupplier<WallSignBlock> ULTRA_DARK_WALL_SIGN = registerSignWithoutItem("ultra_dark_wall_sign", () -> new GenerationsWallSignBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_WALL_SIGN), GenerationsWoodTypes.ULTRA_DARK));
    public static final RegistrySupplier<CeilingHangingSignBlock> ULTRA_DARK_HANGING_SIGN = registerBlockWithoutItem("ultra_dark_hanging_sign", () -> new GenerationsCeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_HANGING_SIGN), GenerationsWoodTypes.ULTRA_DARK));
    public static final RegistrySupplier<WallHangingSignBlock> ULTRA_DARK_WALL_HANGING_SIGN = registerSignWithoutItem("ultra_dark_wall_hanging_sign", () -> new GenerationsWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_WALL_HANGING_SIGN), GenerationsWoodTypes.ULTRA_DARK));
    public static final RegistrySupplier<Block> ULTRA_DARK_BOOKSHELF = registerBlockItem("ultra_dark_bookshelf", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));

    public static final RegistrySupplier<RotatedPillarBlock> GHOST_LOG = registerBlockItem("ghost_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LOG)));
    public static final RegistrySupplier<RotatedPillarBlock> STRIPPED_GHOST_LOG = registerBlockItem("stripped_ghost_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_DARK_OAK_LOG)));
    public static final RegistrySupplier<Block> GHOST_WOOD = registerBlockItem("ghost_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_WOOD)));
    public static final RegistrySupplier<Block> STRIPPED_GHOST_WOOD = registerBlockItem("stripped_ghost_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_DARK_OAK_WOOD)));
    public static final RegistrySupplier<Block> GHOST_PLANKS = registerBlockItem("ghost_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_PLANKS)));
    public static final RegistrySupplier<SlabBlock> GHOST_SLAB = registerBlockItem("ghost_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_SLAB)));
    public static final RegistrySupplier<StairBlock> GHOST_STAIRS =  registerBlockItem("ghost_stairs", () -> new StairBlock(Blocks.DARK_OAK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.DARK_OAK_STAIRS)));
    public static final RegistrySupplier<ButtonBlock> GHOST_BUTTON = registerBlockItem("ghost_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_BUTTON), GenerationsBlockSetTypes.GHOST, 30, true));
    public static final RegistrySupplier<PressurePlateBlock>  GHOST_PRESSURE_PLATE = registerBlockItem("ghost_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.DARK_OAK_PRESSURE_PLATE), GenerationsBlockSetTypes.GHOST));
    public static final RegistrySupplier<GenerationsCraftingTableBlock> GHOST_CRAFTING_TABLE = registerBlockItem("ghost_crafting_table", GenerationsCraftingTableBlock::new);
    public static final RegistrySupplier<TrapDoorBlock> GHOST_TRAPDOOR = registerBlockItem("ghost_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_TRAPDOOR), GenerationsBlockSetTypes.GHOST));
    public static final RegistrySupplier<DoorBlock> GHOST_DOOR = registerBlockItem("ghost_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_DOOR), GenerationsBlockSetTypes.GHOST));
    public static final RegistrySupplier<FenceBlock>  GHOST_FENCE = registerBlockItem("ghost_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_FENCE)));
    public static final RegistrySupplier<FenceGateBlock> GHOST_FENCE_GATE = registerBlockItem("ghost_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_FENCE_GATE), GenerationsWoodTypes.GHOST));
    public static final RegistrySupplier<StandingSignBlock> GHOST_SIGN = registerBlockWithoutItem("ghost_sign", () -> new GenerationsStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_SIGN), GenerationsWoodTypes.GHOST));
    public static final RegistrySupplier<WallSignBlock> GHOST_WALL_SIGN = registerSignWithoutItem("ghost_wall_sign", () -> new GenerationsWallSignBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_WALL_SIGN), GenerationsWoodTypes.GHOST));
    public static final RegistrySupplier<CeilingHangingSignBlock> GHOST_HANGING_SIGN = registerBlockWithoutItem("ghost_hanging_sign", () -> new GenerationsCeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_HANGING_SIGN), GenerationsWoodTypes.GHOST));
    public static final RegistrySupplier<WallHangingSignBlock> GHOST_WALL_HANGING_SIGN = registerSignWithoutItem("ghost_wall_hanging_sign", () -> new GenerationsWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_WALL_HANGING_SIGN), GenerationsWoodTypes.GHOST));
    public static final RegistrySupplier<Block> GHOST_BOOKSHELF = registerBlockItem("ghost_bookshelf", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));

    private static void register(String name, Function<Item.Properties, Item> itemSupplier) {
        GenerationsItems.ITEMS.register(name, () -> itemSupplier.apply(new Item.Properties()));
    }

    private static <T extends Block> RegistrySupplier<T> registerBlockItem(String name, Supplier<T> blockSupplier) {
        RegistrySupplier<T> block = WOOD_BLOCKS.register(name, blockSupplier);
        register(name, properties -> new BlockItem(block.get(), properties));
        return block;
    }

    private static <T extends Block> RegistrySupplier<T> registerBlockWithoutItem(String name, Supplier<T> blockSupplier) {
        return WOOD_BLOCKS.register(name, blockSupplier);
    }

    private static <T extends Block> RegistrySupplier<T> registerSignWithoutItem(String name, Supplier<T> blockSupplier) {
        return WOOD_SIGN.register(name, blockSupplier);
    }
    public static void init() {
        GenerationsCore.LOGGER.info("Registering Generations Wood");
        WOOD_BLOCKS.register();
        WOOD_SIGN.register();
    }

}
