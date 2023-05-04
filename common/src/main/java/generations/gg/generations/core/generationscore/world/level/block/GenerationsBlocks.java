package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.GenericChestBlockItem;
import generations.gg.generations.core.generationscore.world.item.berry.BerryType;
import generations.gg.generations.core.generationscore.world.item.creativetab.GenerationsCreativeTabs;
import generations.gg.generations.core.generationscore.world.level.block.decorations.PokecenterScarletSignBlock;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericChestBlock;
import generations.gg.generations.core.generationscore.world.level.block.grower.PokeModTreeGrower;
import generations.gg.generations.core.generationscore.world.level.levelgen.PokeModFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public class GenerationsBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK);
    public static final BlockBehaviour.Properties ULTRA_BLOCK_SETTINGS = BlockBehaviour.Properties.copy(Blocks.GLASS).lightLevel(value -> 15);

    public static final RegistrySupplier<Block> POKE_GRASS = registerBlockItem("poke_grass", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
    public static final RegistrySupplier<Block> POKE_DIRT = registerBlockItem("poke_dirt", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistrySupplier<Block> POKE_SAND = registerBlockItem("poke_sand", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> POKE_SAND_SMALL_CORNER_1 = registerBlockItem("poke_sand_small_corner1", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> POKE_SAND_SMALL_CORNER_2 = registerBlockItem("poke_sand_small_corner2", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> POKE_SAND_CORNER_1 = registerBlockItem("poke_sand_corner1", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> POKE_SAND_CORNER_2 = registerBlockItem("poke_sand_corner2", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> POKE_SAND_CORNER_3 = registerBlockItem("poke_sand_corner3", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> POKE_SAND_CORNER_4 = registerBlockItem("poke_sand_corner4", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> POKE_SAND_SIDE_1 = registerBlockItem("poke_sand_side1", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> POKE_SAND_SIDE_2 = registerBlockItem("poke_sand_side2", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> POKE_SAND_SIDE_3 = registerBlockItem("poke_sand_side3", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> POKE_SAND_SIDE_4 = registerBlockItem("poke_sand_side4", () -> new SandBlock(0xdbd3a0,BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> SANDY_GRASS = registerBlockItem("sandy_grass", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> SHINGLES = registerBlockItem("shingles", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> SHINGLES_CORNER_1 = registerBlockItem("shingles_corner1", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> SHINGLES_CORNER_2 = registerBlockItem("shingles_corner2", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> OUTSIDE_WALL = registerBlockItem("outside_wall", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> INSIDE_WALL = registerBlockItem("inside_wall", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> INSIDE_WALL_MOLDING = registerBlockItem("inside_wall_molding", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> TREE_TOP = registerBlockItem("tree_top", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD)));
    public static final RegistrySupplier<Block> TREE_BOTTOM = registerBlockItem("tree_bottom", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD)));
    public static final RegistrySupplier<Block> WOODEN_FLOORING = registerBlockItem("wooden_flooring", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD)));
    public static final RegistrySupplier<Block> POKEMART_SIGN = registerBlockItem("pokemart_sign", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<GlassBlock> WINDOW_1 = registerBlockItem("window_1", () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).noOcclusion()));
    public static final RegistrySupplier<GlassBlock> WINDOW_2 = registerBlockItem("window_2", () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).noOcclusion()));

    /**
     * PokeCenter
     */
    public static final RegistrySupplier<Block> POKECENTER_SIGN = registerBlockItem("pokecenter_sign", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> POKECENTER_SCARLET_SIGN = registerBlockItem("pokecenter_scarlet_sign", () -> new PokecenterScarletSignBlock(BlockBehaviour.Properties.of(Material.METAL).lightLevel((state) -> 15)));
    public static final RegistrySupplier<DoorBlock> POKECENTER_DOOR = registerBlockItem("pokecenter_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).sound(SoundType.AMETHYST).noOcclusion(), BlockSetType.OAK));
    public static final RegistrySupplier<Block> POKECENTER_ROOF = registerBlockItem("pokecenter_roof", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).lightLevel(value -> 5)));
    public static final RegistrySupplier<StairBlock> POKECENTER_ROOF_STAIRS = registerBlockItem("pokecenter_roof_stairs", () -> new StairBlock(POKECENTER_ROOF.get().defaultBlockState(), BlockBehaviour.Properties.copy(POKECENTER_ROOF.get())));
    public static final RegistrySupplier<SlabBlock> POKECENTER_ROOF_SLAB = registerBlockItem("pokecenter_roof_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(POKECENTER_ROOF.get())));
    public static final RegistrySupplier<WallBlock> POKECENTER_ROOF_WALL = registerBlockItem("pokecenter_roof_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(POKECENTER_ROOF.get())));
    public static final RegistrySupplier<Block> POKECENTER_ROOF_2 = registerBlockItem("pokecenter_roof_2", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).lightLevel(value -> 5)));
    public static final RegistrySupplier<StairBlock> POKECENTER_ROOF_2_STAIRS = registerBlockItem("pokecenter_roof_2_stairs", () -> new StairBlock(POKECENTER_ROOF_2.get().defaultBlockState(), BlockBehaviour.Properties.copy(POKECENTER_ROOF_2.get())));
    public static final RegistrySupplier<SlabBlock> POKECENTER_ROOF_2_SLAB = registerBlockItem("pokecenter_roof_2_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(POKECENTER_ROOF_2.get())));
    public static final RegistrySupplier<WallBlock> POKECENTER_ROOF_2_WALL = registerBlockItem("pokecenter_roof_2_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(POKECENTER_ROOF_2.get())));
    /**
     * Building Blocks
     */
    public static final RegistrySupplier<Block> HOUSE_FLOOR_1 = registerBlockItem("house_floor_1", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> HOUSE_FLOOR_2 = registerBlockItem("house_floor_2", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> HOUSE_FLOOR_3 = registerBlockItem("house_floor_3", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> HOUSE_FLOOR_4 = registerBlockItem("house_floor_4", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> HOUSE_FLOOR_5 = registerBlockItem("house_floor_5", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> HOUSE_FLOOR_6 = registerBlockItem("house_floor_6", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> HOUSE_FLOOR_7 = registerBlockItem("house_floor_7", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> HOUSE_FLOOR_8 = registerBlockItem("house_floor_8", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD)));
    public static final RegistrySupplier<Block> INSIDE_WALL_BOTTOM = registerBlockItem("inside_wall_bottom", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> INSIDE_WALL_TOP = registerBlockItem("inside_wall_top", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> INSIDE_WALL_MIDDLE = registerBlockItem("inside_wall_middle", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> RUINS_WALL = registerBlockItem("ruins_wall", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> DUSTY_RUINS_WALL = registerBlockItem("dusty_ruins_wall", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

    /*
      Temple Blocks
     */

    /**
     * Temple Block Pallet
     */
    public static final RegistrySupplier<Block> TEMPLE_BLOCK = registerBlockItem("temple_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<StairBlock> TEMPLE_BLOCK_STAIRS = registerBlockItem("temple_block_stairs", () -> new StairBlock(Blocks.STONE.defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<SlabBlock> TEMPLE_BLOCK_SLAB = registerBlockItem("temple_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(TEMPLE_BLOCK.get())));
    public static final RegistrySupplier<WallBlock> TEMPLE_BLOCK_WALL = registerBlockItem("temple_block_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(TEMPLE_BLOCK.get())));
    /**
     * Temple Brick Pallet
     */
    public static final RegistrySupplier<Block> TEMPLE_BRICK = registerBlockItem("temple_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> TEMPLE_BRICK_STAIRS = registerBlockItem("temple_brick_stairs", () -> new StairBlock(TEMPLE_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.copy(TEMPLE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> TEMPLE_BRICK_SLAB = registerBlockItem("temple_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(TEMPLE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> TEMPLE_BRICK_WALL = registerBlockItem("temple_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(TEMPLE_BRICK.get())));

    /*
      Castle Blocks
     */

    /**
     * Castle Block Pallet
     */
    public static final RegistrySupplier<Block> CASTLE_BLOCK = registerBlockItem("castle_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<StairBlock> CASTLE_BLOCK_STAIRS = registerBlockItem("castle_block_stairs", () -> new StairBlock(Blocks.STONE.defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<SlabBlock> CASTLE_BLOCK_SLAB = registerBlockItem("castle_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CASTLE_BLOCK.get())));
    public static final RegistrySupplier<WallBlock> CASTLE_BLOCK_WALL = registerBlockItem("castle_block_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CASTLE_BLOCK.get())));

    /**
     * Cracked Castle Block Pallet
     */
    public static final RegistrySupplier<Block> CRACKED_CASTLE_BLOCK = registerBlockItem("cracked_castle_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<StairBlock> CRACKED_CASTLE_BLOCK_STAIRS = registerBlockItem("cracked_castle_block_stairs", () -> new StairBlock(CRACKED_CASTLE_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(CRACKED_CASTLE_BLOCK.get())));
    public static final RegistrySupplier<SlabBlock> CRACKED_CASTLE_BLOCK_SLAB = registerBlockItem("cracked_castle_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CRACKED_CASTLE_BLOCK.get())));
    public static final RegistrySupplier<WallBlock> CRACKED_CASTLE_BLOCK_WALL = registerBlockItem("cracked_castle_block_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CRACKED_CASTLE_BLOCK.get())));

    /**
     * Castle Brick Pallet
     */
    public static final RegistrySupplier<Block> CASTLE_BRICK = registerBlockItem("castle_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> CASTLE_BRICK_STAIRS = registerBlockItem("castle_brick_stairs", () -> new StairBlock(CASTLE_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.copy(CASTLE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> CASTLE_BRICK_SLAB = registerBlockItem("castle_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CASTLE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> CASTLE_BRICK_WALL = registerBlockItem("castle_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CASTLE_BRICK.get())));

    /**
     * Castle Brick 2 Pallet
     */
    public static final RegistrySupplier<Block> CASTLE_BRICK_2 = registerBlockItem("castle_brick_2", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> CASTLE_BRICK_2_STAIRS = registerBlockItem("castle_brick_2_stairs", () -> new StairBlock(CASTLE_BRICK_2.get().defaultBlockState(), BlockBehaviour.Properties.copy(CASTLE_BRICK_2.get())));
    public static final RegistrySupplier<SlabBlock> CASTLE_BRICK_2_SLAB = registerBlockItem("castle_brick_2_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CASTLE_BRICK_2.get())));
    public static final RegistrySupplier<WallBlock> CASTLE_BRICK_2_WALL = registerBlockItem("castle_brick_2_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CASTLE_BRICK_2.get())));

    /**
     * Gray Castle Brick Pallet
     */
    public static final RegistrySupplier<Block> GRAY_CASTLE_BRICK = registerBlockItem("gray_castle_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> GRAY_CASTLE_BRICK_STAIRS = registerBlockItem("gray_castle_brick_stairs", () -> new StairBlock(GRAY_CASTLE_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.copy(GRAY_CASTLE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> GRAY_CASTLE_BRICK_SLAB = registerBlockItem("gray_castle_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GRAY_CASTLE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> GRAY_CASTLE_BRICK_WALL = registerBlockItem("gray_castle_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GRAY_CASTLE_BRICK.get())));
    /**
     * Gray Castle Brick 2 Pallet
     */
    public static final RegistrySupplier<Block> GRAY_CASTLE_BRICK_2 = registerBlockItem("gray_castle_brick_2", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> GRAY_CASTLE_BRICK_2_STAIRS = registerBlockItem("gray_castle_brick_2_stairs", () -> new StairBlock(GRAY_CASTLE_BRICK_2.get().defaultBlockState(), BlockBehaviour.Properties.copy(GRAY_CASTLE_BRICK_2.get())));
    public static final RegistrySupplier<SlabBlock> GRAY_CASTLE_BRICK_2_SLAB = registerBlockItem("gray_castle_brick_2_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GRAY_CASTLE_BRICK_2.get())));
    public static final RegistrySupplier<WallBlock> GRAY_CASTLE_BRICK_2_WALL = registerBlockItem("gray_castle_brick_2_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GRAY_CASTLE_BRICK_2.get())));

    /**
     * White Castle Brick Pallet
     */

    public static final RegistrySupplier<Block> WHITE_CASTLE_BRICK = registerBlockItem("white_castle_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> WHITE_CASTLE_BRICK_STAIRS = registerBlockItem("white_castle_brick_stairs", () -> new StairBlock(WHITE_CASTLE_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<SlabBlock> WHITE_CASTLE_BRICK_SLAB = registerBlockItem("white_castle_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(WHITE_CASTLE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> WHITE_CASTLE_BRICK_WALL = registerBlockItem("white_castle_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(WHITE_CASTLE_BRICK.get())));

    /**
     * White Castle Brick 2 Pallet
     */

    public static final RegistrySupplier<Block> WHITE_CASTLE_BRICK_2 = registerBlockItem("white_castle_brick_2", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> WHITE_CASTLE_BRICK_2_STAIRS = registerBlockItem("castle_white_brick_2_stairs", () -> new StairBlock(WHITE_CASTLE_BRICK_2.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<SlabBlock> WHITE_CASTLE_BRICK_2_SLAB = registerBlockItem("white_castle_brick_2_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(WHITE_CASTLE_BRICK_2.get())));
    public static final RegistrySupplier<WallBlock> WHITE_CASTLE_BRICK_2_WALL = registerBlockItem("white_castle_brick_2_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(WHITE_CASTLE_BRICK_2.get())));

    /**
     * Castle Wall Pallet
     */

    public static final RegistrySupplier<Block> CASTLE_WALL = registerBlockItem("castle_wall", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<StairBlock> CASTLE_WALL_STAIRS = registerBlockItem("castle_wall_stairs", () -> new StairBlock(CASTLE_WALL.get().defaultBlockState(), BlockBehaviour.Properties.copy(CASTLE_WALL.get())));
    public static final RegistrySupplier<SlabBlock> CASTLE_WALL_SLAB = registerBlockItem("castle_wall_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CASTLE_WALL.get())));
    public static final RegistrySupplier<WallBlock> CASTLE_WALL_WALL = registerBlockItem("castle_wall_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CASTLE_WALL.get())));

    /**
     * Castle Wall 2 Pallet
     */
    public static final RegistrySupplier<Block> CASTLE_WALL_2 = registerBlockItem("castle_wall_2", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<StairBlock> CASTLE_WALL_2_STAIRS = registerBlockItem("castle_wall_2_stairs", () -> new StairBlock(CASTLE_WALL_2.get().defaultBlockState(), BlockBehaviour.Properties.copy(CASTLE_WALL_2.get())));
    public static final RegistrySupplier<SlabBlock> CASTLE_WALL_2_SLAB = registerBlockItem("castle_wall_2_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CASTLE_WALL_2.get())));
    public static final RegistrySupplier<WallBlock> CASTLE_WALL_2_WALL = registerBlockItem("castle_wall_2_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CASTLE_WALL_2.get())));

    /**
     * Castle Wall 3 Pallet
     */
    public static final RegistrySupplier<Block> CASTLE_WALL_3 = registerBlockItem("castle_wall_3", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<StairBlock> CASTLE_WALL_3_STAIRS = registerBlockItem("castle_wall_3_stairs", () -> new StairBlock(CASTLE_WALL_3.get().defaultBlockState(), BlockBehaviour.Properties.copy(CASTLE_WALL_3.get())));
    public static final RegistrySupplier<SlabBlock> CASTLE_WALL_3_SLAB = registerBlockItem("castle_wall_3_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CASTLE_WALL_3.get())));
    public static final RegistrySupplier<WallBlock> CASTLE_WALL_3_WALL = registerBlockItem("castle_wall_3_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CASTLE_WALL_3.get())));

    /**
     * Castle Wall 4 Pallet
     */
    public static final RegistrySupplier<Block> CASTLE_WALL_4 = registerBlockItem("castle_wall_4", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<StairBlock> CASTLE_WALL_4_STAIRS = registerBlockItem("castle_wall_4_stairs", () -> new StairBlock(CASTLE_WALL_4.get().defaultBlockState(), BlockBehaviour.Properties.copy(CASTLE_WALL_4.get())));
    public static final RegistrySupplier<SlabBlock> CASTLE_WALL_4_SLAB = registerBlockItem("castle_wall_4_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CASTLE_WALL_4.get())));
    public static final RegistrySupplier<WallBlock> CASTLE_WALL_4_WALL = registerBlockItem("castle_wall_4_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CASTLE_WALL_4.get())));

    /**
     * Castle Floor Pallet
     */
    public static final RegistrySupplier<Block> CASTLE_FLOOR = registerBlockItem("castle_floor", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<StairBlock> CASTLE_FLOOR_STAIRS = registerBlockItem("castle_floor_stairs", () -> new StairBlock(CASTLE_FLOOR.get().defaultBlockState(), BlockBehaviour.Properties.copy(CASTLE_FLOOR.get())));
    public static final RegistrySupplier<SlabBlock> CASTLE_FLOOR_SLAB = registerBlockItem("castle_floor_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CASTLE_FLOOR.get())));
    public static final RegistrySupplier<WallBlock> CASTLE_FLOOR_WALL = registerBlockItem("castle_floor_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CASTLE_FLOOR.get())));


    /*
      Ice Blocks
     */


    /**
     * Ice Brick Pallet
     */
    public static final RegistrySupplier<Block> ICE_BRICK = registerBlockItem("ice_brick", () -> new Block(BlockBehaviour.Properties.of(Material.ICE_SOLID)));
    public static final RegistrySupplier<StairBlock> ICE_BRICK_STAIRS = registerBlockItem("ice_brick_stairs", () -> new StairBlock(ICE_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.copy(ICE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> ICE_BRICK_SLAB = registerBlockItem("ice_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ICE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> ICE_BRICK_WALL = registerBlockItem("ice_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ICE_BRICK.get())));

    public static final RegistrySupplier<Block> ICE_PILLAR = registerBlockItem("ice_pillar", () -> new Block(BlockBehaviour.Properties.of(Material.ICE_SOLID)));
    public static final RegistrySupplier<Block> BROKEN_ICE_PILLAR = registerBlockItem("ice_pillar_broken", () -> new Block(BlockBehaviour.Properties.of(Material.ICE_SOLID)));

    public static final RegistrySupplier<Block> ICE_PILLAR_SIDE = registerBlockItem("ice_pillar_side", () -> new Block(BlockBehaviour.Properties.of(Material.ICE_SOLID)));
    public static final RegistrySupplier<StairBlock> ICE_PILLAR_SIDE_STAIRS = registerBlockItem("ice_pillar_side_stairs", () -> new StairBlock(ICE_PILLAR_SIDE.get().defaultBlockState(), BlockBehaviour.Properties.copy(ICE_PILLAR_SIDE.get())));
    public static final RegistrySupplier<SlabBlock> ICE_PILLAR_SIDE_SLAB = registerBlockItem("ice_pillar_side_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ICE_PILLAR_SIDE.get())));
    public static final RegistrySupplier<WallBlock> ICE_PILLAR_SIDE_WALL = registerBlockItem("ice_pillar_side_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ICE_PILLAR_SIDE.get())));

    public static final RegistrySupplier<Block> ICE_PILLAR_TOP = registerBlockItem("ice_pillar_top", () -> new Block(BlockBehaviour.Properties.of(Material.ICE_SOLID)));
    public static final RegistrySupplier<StairBlock> ICE_PILLAR_TOP_STAIRS = registerBlockItem("ice_pillar_top_stairs", () -> new StairBlock(ICE_PILLAR_TOP.get().defaultBlockState(), BlockBehaviour.Properties.copy(ICE_PILLAR_TOP.get())));
    public static final RegistrySupplier<SlabBlock> ICE_PILLAR_TOP_SLAB = registerBlockItem("ice_pillar_top_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ICE_PILLAR_TOP.get())));
    public static final RegistrySupplier<WallBlock> ICE_PILLAR_TOP_WALL = registerBlockItem("ice_pillar_top_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ICE_PILLAR_TOP.get())));



    /**
     * Rock Pallets
     */

    public static final RegistrySupplier<Block> ROCK = registerBlockItem("rock", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> ROCK_STAIRS = registerBlockItem("rock_stairs", () -> new StairBlock(ROCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(ROCK.get())));
    public static final RegistrySupplier<SlabBlock> ROCK_SLAB = registerBlockItem("rock_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ROCK.get())));
    public static final RegistrySupplier<WallBlock> ROCK_WALL = registerBlockItem("rock_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ROCK.get())));
    /**
     * Cave Rock Pallet
     */

    public static final RegistrySupplier<Block> CAVE_ROCK = registerBlockItem("cave_rock", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

    public static final RegistrySupplier<StairBlock> CAVE_ROCK_STAIRS = registerBlockItem("cave_rock_stairs", () -> new StairBlock(CAVE_ROCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(CAVE_ROCK.get())));
    public static final RegistrySupplier<SlabBlock> CAVE_ROCK_SLAB = registerBlockItem("cave_rock_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CAVE_ROCK.get())));
    public static final RegistrySupplier<WallBlock> CAVE_ROCK_WALL = registerBlockItem("cave_rock_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CAVE_ROCK.get())));

    /**
     * Cave Rock Floor Pallet
     */
    public static final RegistrySupplier<Block> CAVE_ROCK_FLOOR = registerBlockItem("cave_rock_floor", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<StairBlock> CAVE_ROCK_FLOOR_STAIRS = registerBlockItem("cave_rock_floor_stairs", () -> new StairBlock(CAVE_ROCK_FLOOR.get().defaultBlockState(), BlockBehaviour.Properties.copy(CAVE_ROCK_FLOOR.get())));
    public static final RegistrySupplier<SlabBlock> CAVE_ROCK_FLOOR_SLAB = registerBlockItem("cave_rock_floor_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CAVE_ROCK_FLOOR.get())));
    public static final RegistrySupplier<WallBlock> CAVE_ROCK_FLOOR_WALL = registerBlockItem("cave_rock_floor_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CAVE_ROCK_FLOOR.get())));

    /**
     * Gray Cave Rock Floor Pallet
     */
    public static final RegistrySupplier<Block> GRAY_CAVE_ROCK_FLOOR = registerBlockItem("gray_cave_rock_floor", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<StairBlock> GRAY_CAVE_ROCK_FLOOR_STAIRS = registerBlockItem("gray_cave_rock_floor_stairs", () -> new StairBlock(GRAY_CAVE_ROCK_FLOOR.get().defaultBlockState(), BlockBehaviour.Properties.copy(GRAY_CAVE_ROCK_FLOOR.get())));
    public static final RegistrySupplier<SlabBlock> GRAY_CAVE_ROCK_FLOOR_SLAB = registerBlockItem("gray_cave_rock_floor_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GRAY_CAVE_ROCK_FLOOR.get())));
    public static final RegistrySupplier<WallBlock> GRAY_CAVE_ROCK_FLOOR_WALL = registerBlockItem("gray_cave_rock_floor_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GRAY_CAVE_ROCK_FLOOR.get())));

    /**
     * Ice Cave Rock Floor Pallet
     */
    public static final RegistrySupplier<Block> ICE_CAVE_ROCK_FLOOR = registerBlockItem("ice_cave_rock_floor", () -> new Block(BlockBehaviour.Properties.of(Material.ICE_SOLID)));
    public static final RegistrySupplier<StairBlock> ICE_CAVE_ROCK_FLOOR_STAIRS = registerBlockItem("ice_cave_rock_floor_stairs", () -> new StairBlock(ICE_CAVE_ROCK_FLOOR.get().defaultBlockState(), BlockBehaviour.Properties.copy(ICE_CAVE_ROCK_FLOOR.get())));
    public static final RegistrySupplier<SlabBlock> ICE_CAVE_ROCK_FLOOR_SLAB = registerBlockItem("ice_cave_rock_floor_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ICE_CAVE_ROCK_FLOOR.get())));
    public static final RegistrySupplier<WallBlock> ICE_CAVE_ROCK_FLOOR_WALL = registerBlockItem("ice_cave_rock_floor_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ICE_CAVE_ROCK_FLOOR.get())));


    /**
     * Bridge Block Pallet
     */
    public static final RegistrySupplier<Block> BRIDGE_BLOCK = registerBlockItem("bridge_block", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD)));
    public static final RegistrySupplier<StairBlock> BRIDGE_BLOCK_STAIRS = registerBlockItem("bridge_block_stairs", () -> new StairBlock(BRIDGE_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(BRIDGE_BLOCK.get())));
    public static final RegistrySupplier<SlabBlock> BRIDGE_BLOCK_SLAB = registerBlockItem("bridge_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BRIDGE_BLOCK.get())));
    public static final RegistrySupplier<WallBlock> BRIDGE_BLOCK_WALL = registerBlockItem("bridge_block_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(BRIDGE_BLOCK.get())));

    public static final RegistrySupplier<Block> CASTLE_PILLAR = registerBlockItem("castle_pillar", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BROKEN_CASTLE_PILLAR = registerBlockItem("broken_castle_pillar", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> PRISMARINE_PILLAR = registerBlockItem("prismarine_pillar", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BROKEN_PRISMARINE_PILLAR = registerBlockItem("prismarine_pillar_broken", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> DARK_PRISMARINE_PILLAR = registerBlockItem("dark_prismarine_pillar", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BROKEN_DARK_PRISMARINE_PILLAR = registerBlockItem("dark_prismarine_pillar_broken", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> HAUNTED_PILLAR = registerBlockItem("haunted_pillar", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BROKEN_HAUNTED_PILLAR = registerBlockItem("haunted_pillar_broken", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

    public static final RegistrySupplier<Block> DAWN_STONE_BLOCK = registerBlockItem("dawn_stone_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> DUSK_STONE_BLOCK = registerBlockItem("dusk_stone_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> FIRE_STONE_BLOCK = registerBlockItem("fire_stone_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> ICE_STONE_BLOCK = registerBlockItem("ice_stone_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> LEAF_STONE_BLOCK = registerBlockItem("leaf_stone_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> MOON_STONE_BLOCK = registerBlockItem("moon_stone_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> SHINY_STONE_BLOCK = registerBlockItem("shiny_stone_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> SUN_STONE_BLOCK = registerBlockItem("sun_stone_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> THUNDER_STONE_BLOCK = registerBlockItem("thunder_stone_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> WATER_STONE_BLOCK = registerBlockItem("water_stone_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> CRATE = registerBlockItem("crate", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

    /**
     * Compressed Polished Andesite Pallet
     */
    public static final RegistrySupplier<Block> COMPRESSED_POLISHED_ANDESITE = registerBlockItem("compressed_polished_andesite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.POLISHED_ANDESITE)));
    public static final RegistrySupplier<StairBlock> COMPRESSED_POLISHED_ANDESITE_STAIRS = registerBlockItem("compressed_polished_andesite_stairs", () -> new StairBlock(COMPRESSED_POLISHED_ANDESITE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.POLISHED_ANDESITE)));
    public static final RegistrySupplier<SlabBlock> COMPRESSED_POLISHED_ANDESITE_SLAB = registerBlockItem("compressed_polished_andesite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_ANDESITE)));
    public static final RegistrySupplier<WallBlock> COMPRESSED_POLISHED_ANDESITE_WALL = registerBlockItem("compressed_polished_andesite_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_ANDESITE)));

    /**
     * Compressed Polished Diorite Pallet
     */
    public static final RegistrySupplier<Block> COMPRESSED_POLISHED_DIORITE = registerBlockItem("compressed_polished_diorite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.POLISHED_DIORITE)));
    public static final RegistrySupplier<StairBlock> COMPRESSED_POLISHED_DIORITE_STAIRS = registerBlockItem("compressed_polished_diorite_stairs", () -> new StairBlock(COMPRESSED_POLISHED_DIORITE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.POLISHED_DIORITE)));
    public static final RegistrySupplier<SlabBlock> COMPRESSED_POLISHED_DIORITE_SLAB = registerBlockItem("compressed_polished_diorite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_DIORITE)));
    public static final RegistrySupplier<WallBlock> COMPRESSED_POLISHED_DIORITE_WALL = registerBlockItem("compressed_polished_diorite_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_DIORITE)));

    /**
     * Compressed Polished Granite Pallet
     */
    public static final RegistrySupplier<Block> COMPRESSED_POLISHED_GRANITE = registerBlockItem("compressed_polished_granite", () -> new Block(BlockBehaviour.Properties.copy(Blocks.POLISHED_GRANITE)));
    public static final RegistrySupplier<StairBlock> COMPRESSED_POLISHED_GRANITE_STAIRS = registerBlockItem("compressed_polished_granite_stairs", () -> new StairBlock(COMPRESSED_POLISHED_GRANITE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.POLISHED_GRANITE)));
    public static final RegistrySupplier<SlabBlock> COMPRESSED_POLISHED_GRANITE_SLAB = registerBlockItem("compressed_polished_granite_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_GRANITE)));
    public static final RegistrySupplier<WallBlock> COMPRESSED_POLISHED_GRANITE_WALL = registerBlockItem("compressed_polished_granite_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_GRANITE)));

    /**
     * Compressed Polished Deepslate Pallet
     */
    public static final RegistrySupplier<Block> COMPRESSED_POLISHED_DEEPSLATE = registerBlockItem("compressed_polished_deepslate", () -> new Block(BlockBehaviour.Properties.copy(Blocks.POLISHED_DEEPSLATE)));
    public static final RegistrySupplier<StairBlock> COMPRESSED_POLISHED_DEEPSLATE_STAIRS = registerBlockItem("compressed_polished_deepslate_stairs", () -> new StairBlock(COMPRESSED_POLISHED_DEEPSLATE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.POLISHED_DEEPSLATE)));
    public static final RegistrySupplier<SlabBlock> COMPRESSED_POLISHED_DEEPSLATE_SLAB = registerBlockItem("compressed_polished_deepslate_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_DEEPSLATE)));
    public static final RegistrySupplier<WallBlock> COMPRESSED_POLISHED_DEEPSLATE_WALL = registerBlockItem("compressed_polished_deepslate_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_DEEPSLATE)));


    public static final RegistrySupplier<Block> WARNING_BLOCK = registerBlockItem("warning_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BEDROCK)));


    //Cursed Pumpkin Based Blocks
    public static final RegistrySupplier<PumpkinBlock> CURSED_PUMPKIN = registerBlockItem("cursed_pumpkin", () -> new CursedPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.PUMPKIN)));
    public static final RegistrySupplier<CursedCarvedPumpkinBlock> CURSED_CARVED_PUMPKIN = registerBlockItem("cursed_carved_pumpkin", () -> new CursedCarvedPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.CARVED_PUMPKIN)));
    public static final RegistrySupplier<CursedCarvedPumpkinBlock> CURSED_JACK_O_LANTERN = registerBlockItem("cursed_jack_o_lantern", () -> new CursedCarvedPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.JACK_O_LANTERN)));


    public static final RegistrySupplier<Block> COBBLE_RUINS_1 = registerBlockItem("cobble_ruins_1", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<StairBlock> COBBLE_RUINS_1_STAIRS = registerBlockItem("cobble_ruins_1_stairs", () -> new StairBlock(COBBLE_RUINS_1.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<SlabBlock> COBBLE_RUINS_1_SLAB = registerBlockItem("cobble_ruins_1_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(COBBLE_RUINS_1.get())));
    public static final RegistrySupplier<WallBlock> COBBLE_RUINS_1_WALL = registerBlockItem("cobble_ruins_1_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(COBBLE_RUINS_1.get())));
    public static final RegistrySupplier<Block> COBBLE_RUINS_2 = registerBlockItem("cobble_ruins_2", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<StairBlock> COBBLE_RUINS_2_STAIRS = registerBlockItem("cobble_ruins_2_stairs", () -> new StairBlock(COBBLE_RUINS_2.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<SlabBlock> COBBLE_RUINS_2_SLAB = registerBlockItem("cobble_ruins_2_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(COBBLE_RUINS_2.get())));
    public static final RegistrySupplier<WallBlock> COBBLE_RUINS_2_WALL = registerBlockItem("cobble_ruins_2_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(COBBLE_RUINS_2.get())));
    public static final RegistrySupplier<Block> COBBLE_RUINS_3 = registerBlockItem("cobble_ruins_3", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<StairBlock> COBBLE_RUINS_3_STAIRS = registerBlockItem("cobble_ruins_3_stairs", () -> new StairBlock(COBBLE_RUINS_3.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<SlabBlock> COBBLE_RUINS_3_SLAB = registerBlockItem("cobble_ruins_3_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(COBBLE_RUINS_3.get())));
    public static final RegistrySupplier<WallBlock> COBBLE_RUINS_3_WALL = registerBlockItem("cobble_ruins_3_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(COBBLE_RUINS_3.get())));
    public static final RegistrySupplier<Block> COBBLE_RUINS_4 = registerBlockItem("cobble_ruins_4", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<StairBlock> COBBLE_RUINS_4_STAIRS = registerBlockItem("cobble_ruins_4_stairs", () -> new StairBlock(COBBLE_RUINS_4.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<SlabBlock> COBBLE_RUINS_4_SLAB = registerBlockItem("cobble_ruins_4_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(COBBLE_RUINS_4.get())));
    public static final RegistrySupplier<WallBlock> COBBLE_RUINS_4_WALL = registerBlockItem("cobble_ruins_4_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(COBBLE_RUINS_4.get())));
    /**
     * Ultra Blocks
     */

    public static final RegistrySupplier<Block> ULTRA_WHITE = registerBlockItem("ultra_white", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_WHITE_STAIRS = registerBlockItem("ultra_white_stairs", () -> new StairBlock(ULTRA_WHITE.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_WHITE_SLAB = registerBlockItem("ultra_white_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_WHITE_WALL = registerBlockItem("ultra_white_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_WHITE_BUTTON = registerBlockItem("ultra_white_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_WHITE_PRESSURE_PLATE = registerBlockItem("ultra_white_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, BlockSetType.STONE));

    public static final RegistrySupplier<Block> ULTRA_LIGHT_GRAY = registerBlockItem("ultra_light_gray", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_LIGHT_GRAY_STAIRS = registerBlockItem("ultra_light_gray_stairs", () -> new StairBlock(ULTRA_LIGHT_GRAY.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_LIGHT_GRAY_SLAB = registerBlockItem("ultra_light_gray_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_LIGHT_GRAY_WALL = registerBlockItem("ultra_light_gray_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_LIGHT_GRAY_BUTTON = registerBlockItem("ultra_light_gray_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_LIGHT_GRAY_PRESSURE_PLATE = registerBlockItem("ultra_light_gray_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, BlockSetType.STONE));

    public static final RegistrySupplier<Block> ULTRA_GRAY = registerBlockItem("ultra_gray", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_GRAY_STAIRS = registerBlockItem("ultra_gray_stairs", () -> new StairBlock(ULTRA_GRAY.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_GRAY_SLAB = registerBlockItem("ultra_gray_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_GRAY_WALL = registerBlockItem("ultra_gray_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_GRAY_BUTTON = registerBlockItem("ultra_gray_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_GRAY_PRESSURE_PLATE = registerBlockItem("ultra_gray_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, BlockSetType.STONE));

    public static final RegistrySupplier<Block> ULTRA_BLACK = registerBlockItem("ultra_black", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_BLACK_STAIRS = registerBlockItem("ultra_black_stairs", () -> new StairBlock(ULTRA_BLACK.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_BLACK_SLAB = registerBlockItem("ultra_black_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_BLACK_WALL = registerBlockItem("ultra_black_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_BLACK_BUTTON = registerBlockItem("ultra_black_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_BLACK_PRESSURE_PLATE = registerBlockItem("ultra_black_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, BlockSetType.STONE));

    public static final RegistrySupplier<Block> ULTRA_BROWN = registerBlockItem("ultra_brown", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_BROWN_STAIRS = registerBlockItem("ultra_brown_stairs", () -> new StairBlock(ULTRA_BROWN.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_BROWN_SLAB = registerBlockItem("ultra_brown_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_BROWN_WALL = registerBlockItem("ultra_brown_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_BROWN_BUTTON = registerBlockItem("ultra_brown_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_BROWN_PRESSURE_PLATE = registerBlockItem("ultra_brown_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, BlockSetType.STONE));

    public static final RegistrySupplier<Block> ULTRA_RED = registerBlockItem("ultra_red", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_RED_STAIRS = registerBlockItem("ultra_red_stairs", () -> new StairBlock(ULTRA_RED.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_RED_SLAB = registerBlockItem("ultra_red_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_RED_WALL = registerBlockItem("ultra_red_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_RED_BUTTON = registerBlockItem("ultra_red_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_RED_PRESSURE_PLATE = registerBlockItem("ultra_red_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, BlockSetType.STONE));

    public static final RegistrySupplier<Block> ULTRA_ORANGE = registerBlockItem("ultra_orange", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_ORANGE_STAIRS = registerBlockItem("ultra_orange_stairs", () -> new StairBlock(ULTRA_ORANGE.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_ORANGE_SLAB = registerBlockItem("ultra_orange_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_ORANGE_WALL = registerBlockItem("ultra_orange_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_ORANGE_BUTTON = registerBlockItem("ultra_orange_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_ORANGE_PRESSURE_PLATE = registerBlockItem("ultra_orange_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, BlockSetType.STONE));

    public static final RegistrySupplier<Block> ULTRA_YELLOW = registerBlockItem("ultra_yellow", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_YELLOW_STAIRS = registerBlockItem("ultra_yellow_stairs", () -> new StairBlock(ULTRA_YELLOW.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_YELLOW_SLAB = registerBlockItem("ultra_yellow_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_YELLOW_WALL = registerBlockItem("ultra_yellow_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_YELLOW_BUTTON = registerBlockItem("ultra_yellow_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_YELLOW_PRESSURE_PLATE = registerBlockItem("ultra_yellow_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, BlockSetType.STONE));

    public static final RegistrySupplier<Block> ULTRA_LIME = registerBlockItem("ultra_lime", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_LIME_STAIRS = registerBlockItem("ultra_lime_stairs", () -> new StairBlock(ULTRA_LIME.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_LIME_SLAB = registerBlockItem("ultra_lime_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_LIME_WALL = registerBlockItem("ultra_lime_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_LIME_BUTTON = registerBlockItem("ultra_lime_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_LIME_PRESSURE_PLATE = registerBlockItem("ultra_lime_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, BlockSetType.STONE));

    public static final RegistrySupplier<Block> ULTRA_GREEN = registerBlockItem("ultra_green", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_GREEN_STAIRS = registerBlockItem("ultra_green_stairs", () -> new StairBlock(ULTRA_GREEN.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_GREEN_SLAB = registerBlockItem("ultra_green_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_GREEN_WALL = registerBlockItem("ultra_green_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_GREEN_BUTTON = registerBlockItem("ultra_green_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_GREEN_PRESSURE_PLATE = registerBlockItem("ultra_green_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, BlockSetType.STONE));

    public static final RegistrySupplier<Block> ULTRA_CYAN = registerBlockItem("ultra_cyan", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_CYAN_STAIRS = registerBlockItem("ultra_cyan_stairs", () -> new StairBlock(ULTRA_CYAN.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_CYAN_SLAB = registerBlockItem("ultra_cyan_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_CYAN_WALL = registerBlockItem("ultra_cyan_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_CYAN_BUTTON = registerBlockItem("ultra_cyan_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_CYAN_PRESSURE_PLATE = registerBlockItem("ultra_cyan_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, BlockSetType.STONE));

    public static final RegistrySupplier<Block> ULTRA_LIGHT_BLUE = registerBlockItem("ultra_light_blue", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_LIGHT_BLUE_STAIRS = registerBlockItem("ultra_light_blue_stairs", () -> new StairBlock(ULTRA_LIGHT_BLUE.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_LIGHT_BLUE_SLAB = registerBlockItem("ultra_light_blue_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_LIGHT_BLUE_WALL = registerBlockItem("ultra_light_blue_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_LIGHT_BLUE_BUTTON = registerBlockItem("ultra_light_blue_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_LIGHT_BLUE_PRESSURE_PLATE = registerBlockItem("ultra_light_blue_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, BlockSetType.STONE));

    public static final RegistrySupplier<Block> ULTRA_BLUE = registerBlockItem("ultra_blue", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_BLUE_STAIRS = registerBlockItem("ultra_blue_stairs", () -> new StairBlock(ULTRA_BLUE.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_BLUE_SLAB = registerBlockItem("ultra_blue_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_BLUE_WALL = registerBlockItem("ultra_blue_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_BLUE_BUTTON = registerBlockItem("ultra_blue_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_BLUE_PRESSURE_PLATE = registerBlockItem("ultra_blue_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, BlockSetType.STONE));

    public static final RegistrySupplier<Block> ULTRA_PURPLE = registerBlockItem("ultra_purple", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_PURPLE_STAIRS = registerBlockItem("ultra_purple_stairs", () -> new StairBlock(ULTRA_PURPLE.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_PURPLE_SLAB = registerBlockItem("ultra_purple_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_PURPLE_WALL = registerBlockItem("ultra_purple_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_PURPLE_BUTTON = registerBlockItem("ultra_purple_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_PURPLE_PRESSURE_PLATE = registerBlockItem("ultra_purple_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, BlockSetType.STONE));

    public static final RegistrySupplier<Block> ULTRA_MAGENTA = registerBlockItem("ultra_magenta", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_MAGENTA_STAIRS = registerBlockItem("ultra_magenta_stairs", () -> new StairBlock(ULTRA_MAGENTA.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_MAGENTA_SLAB = registerBlockItem("ultra_magenta_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_MAGENTA_WALL = registerBlockItem("ultra_magenta_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_MAGENTA_BUTTON = registerBlockItem("ultra_magenta_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_MAGENTA_PRESSURE_PLATE = registerBlockItem("ultra_magenta_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, BlockSetType.STONE));
    public static final RegistrySupplier<Block> ULTRA_PINK = registerBlockItem("ultra_pink", () -> new Block(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<StairBlock> ULTRA_PINK_STAIRS = registerBlockItem("ultra_pink_stairs", () -> new StairBlock(ULTRA_PINK.get().defaultBlockState(), ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<SlabBlock> ULTRA_PINK_SLAB = registerBlockItem("ultra_pink_slab", () -> new SlabBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<WallBlock> ULTRA_PINK_WALL = registerBlockItem("ultra_pink_wall", () -> new WallBlock(ULTRA_BLOCK_SETTINGS));
    public static final RegistrySupplier<ButtonBlock> ULTRA_PINK_BUTTON = registerBlockItem("ultra_pink_button", () -> new ButtonBlock(ULTRA_BLOCK_SETTINGS, BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ULTRA_PINK_PRESSURE_PLATE = registerBlockItem("ultra_pink_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, ULTRA_BLOCK_SETTINGS, BlockSetType.STONE));


    public static final RegistrySupplier<Block> ULTRA_SAND = registerBlockItem("ultra_sand", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));


    /**
     * Marble blocks
     */

    public static final RegistrySupplier<Block> WHITE_MARBLE = registerBlockItem("white_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> WHITE_MARBLE_STAIRS = registerBlockItem("white_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(WHITE_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> WHITE_MARBLE_SLAB = registerBlockItem("white_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(WHITE_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> WHITE_MARBLE_WALL = registerBlockItem("white_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(WHITE_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> WHITE_MARBLE_BUTTON = registerBlockItem("white_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(WHITE_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> WHITE_MARBLE_PRESSURE_PLATE = registerBlockItem("white_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(WHITE_MARBLE.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> LIGHT_GRAY_MARBLE = registerBlockItem("light_gray_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> LIGHT_GRAY_MARBLE_STAIRS = registerBlockItem("light_gray_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(LIGHT_GRAY_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> LIGHT_GRAY_MARBLE_SLAB = registerBlockItem("light_gray_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(LIGHT_GRAY_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> LIGHT_GRAY_MARBLE_WALL = registerBlockItem("light_gray_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(LIGHT_GRAY_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> LIGHT_GRAY_MARBLE_BUTTON = registerBlockItem("light_gray_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(LIGHT_GRAY_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> LIGHT_GRAY_MARBLE_PRESSURE_PLATE = registerBlockItem("light_gray_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(LIGHT_GRAY_MARBLE.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> GRAY_MARBLE = registerBlockItem("gray_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> GRAY_MARBLE_STAIRS = registerBlockItem("gray_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(GRAY_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> GRAY_MARBLE_SLAB = registerBlockItem("gray_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GRAY_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> GRAY_MARBLE_WALL = registerBlockItem("gray_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GRAY_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> GRAY_MARBLE_BUTTON = registerBlockItem("gray_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(GRAY_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> GRAY_MARBLE_PRESSURE_PLATE = registerBlockItem("gray_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(GRAY_MARBLE.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> BLACK_MARBLE = registerBlockItem("black_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> BLACK_MARBLE_STAIRS = registerBlockItem("black_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(BLACK_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> BLACK_MARBLE_SLAB = registerBlockItem("black_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BLACK_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> BLACK_MARBLE_WALL = registerBlockItem("black_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(BLACK_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> BLACK_MARBLE_BUTTON = registerBlockItem("black_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(BLACK_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> BLACK_MARBLE_PRESSURE_PLATE = registerBlockItem("black_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(BLACK_MARBLE.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> BROWN_MARBLE = registerBlockItem("brown_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> BROWN_MARBLE_STAIRS = registerBlockItem("brown_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(BROWN_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> BROWN_MARBLE_SLAB = registerBlockItem("brown_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BROWN_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> BROWN_MARBLE_WALL = registerBlockItem("brown_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(BROWN_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> BROWN_MARBLE_BUTTON = registerBlockItem("brown_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(BROWN_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> BROWN_MARBLE_PRESSURE_PLATE = registerBlockItem("brown_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(BROWN_MARBLE.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> RED_MARBLE = registerBlockItem("red_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> RED_MARBLE_STAIRS = registerBlockItem("red_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(RED_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> RED_MARBLE_SLAB = registerBlockItem("red_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(RED_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> RED_MARBLE_WALL = registerBlockItem("red_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(RED_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> RED_MARBLE_BUTTON = registerBlockItem("red_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(RED_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> RED_MARBLE_PRESSURE_PLATE = registerBlockItem("red_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(RED_MARBLE.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> ORANGE_MARBLE = registerBlockItem("orange_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> ORANGE_MARBLE_STAIRS = registerBlockItem("orange_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(ORANGE_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> ORANGE_MARBLE_SLAB = registerBlockItem("orange_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ORANGE_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> ORANGE_MARBLE_WALL = registerBlockItem("orange_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ORANGE_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> ORANGE_MARBLE_BUTTON = registerBlockItem("orange_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(ORANGE_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ORANGE_MARBLE_PRESSURE_PLATE = registerBlockItem("orange_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(ORANGE_MARBLE.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> YELLOW_MARBLE = registerBlockItem("yellow_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> YELLOW_MARBLE_STAIRS = registerBlockItem("yellow_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(YELLOW_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> YELLOW_MARBLE_SLAB = registerBlockItem("yellow_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(YELLOW_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> YELLOW_MARBLE_WALL = registerBlockItem("yellow_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(YELLOW_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> YELLOW_MARBLE_BUTTON = registerBlockItem("yellow_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(YELLOW_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> YELLOW_MARBLE_PRESSURE_PLATE = registerBlockItem("yellow_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(YELLOW_MARBLE.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> LIME_MARBLE = registerBlockItem("lime_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> LIME_MARBLE_STAIRS = registerBlockItem("lime_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(LIME_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> LIME_MARBLE_SLAB = registerBlockItem("lime_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(LIME_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> LIME_MARBLE_WALL = registerBlockItem("lime_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(LIME_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> LIME_MARBLE_BUTTON = registerBlockItem("lime_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(LIME_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> LIME_MARBLE_PRESSURE_PLATE = registerBlockItem("lime_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(LIME_MARBLE.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> GREEN_MARBLE = registerBlockItem("green_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> GREEN_MARBLE_STAIRS = registerBlockItem("green_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(GREEN_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> GREEN_MARBLE_SLAB = registerBlockItem("green_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GREEN_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> GREEN_MARBLE_WALL = registerBlockItem("green_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GREEN_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> GREEN_MARBLE_BUTTON = registerBlockItem("green_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(GREEN_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> GREEN_MARBLE_PRESSURE_PLATE = registerBlockItem("green_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(GREEN_MARBLE.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> CYAN_MARBLE = registerBlockItem("cyan_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> CYAN_MARBLE_STAIRS = registerBlockItem("cyan_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(CYAN_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> CYAN_MARBLE_SLAB = registerBlockItem("cyan_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CYAN_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> CYAN_MARBLE_WALL = registerBlockItem("cyan_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CYAN_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> CYAN_MARBLE_BUTTON = registerBlockItem("cyan_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(CYAN_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> CYAN_MARBLE_PRESSURE_PLATE = registerBlockItem("cyan_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(CYAN_MARBLE.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> POWDER_BLUE_MARBLE = registerBlockItem("powder_blue_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> POWDER_BLUE_MARBLE_STAIRS = registerBlockItem("powder_blue_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(POWDER_BLUE_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> POWDER_BLUE_MARBLE_SLAB = registerBlockItem("powder_blue_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(POWDER_BLUE_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> POWDER_BLUE_MARBLE_WALL = registerBlockItem("powder_blue_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(POWDER_BLUE_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> POWDER_BLUE_MARBLE_BUTTON = registerBlockItem("powder_blue_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(POWDER_BLUE_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> POWDER_BLUE_MARBLE_PRESSURE_PLATE = registerBlockItem("powder_blue_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(POWDER_BLUE_MARBLE.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> LIGHT_BLUE_MARBLE = registerBlockItem("light_blue_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> LIGHT_BLUE_MARBLE_STAIRS = registerBlockItem("light_blue_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(LIGHT_BLUE_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> LIGHT_BLUE_MARBLE_SLAB = registerBlockItem("light_blue_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(LIGHT_BLUE_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> LIGHT_BLUE_MARBLE_WALL = registerBlockItem("light_blue_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(LIGHT_BLUE_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> LIGHT_BLUE_MARBLE_BUTTON = registerBlockItem("light_blue_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(LIGHT_BLUE_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> LIGHT_BLUE_MARBLE_PRESSURE_PLATE = registerBlockItem("light_blue_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(LIGHT_BLUE_MARBLE.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> BLUE_MARBLE = registerBlockItem("blue_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> BLUE_MARBLE_STAIRS = registerBlockItem("blue_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(BLUE_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> BLUE_MARBLE_SLAB = registerBlockItem("blue_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BLUE_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> BLUE_MARBLE_WALL = registerBlockItem("blue_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(BLUE_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> BLUE_MARBLE_BUTTON = registerBlockItem("blue_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(BLUE_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> BLUE_MARBLE_PRESSURE_PLATE = registerBlockItem("blue_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(BLUE_MARBLE.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> PURPLE_MARBLE = registerBlockItem("purple_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> PURPLE_MARBLE_STAIRS = registerBlockItem("purple_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(PURPLE_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> PURPLE_MARBLE_SLAB = registerBlockItem("purple_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(PURPLE_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> PURPLE_MARBLE_WALL = registerBlockItem("purple_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(PURPLE_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> PURPLE_MARBLE_BUTTON = registerBlockItem("purple_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(PURPLE_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> PURPLE_MARBLE_PRESSURE_PLATE = registerBlockItem("purple_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(PURPLE_MARBLE.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> MAGENTA_MARBLE = registerBlockItem("magenta_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> MAGENTA_MARBLE_STAIRS = registerBlockItem("magenta_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(MAGENTA_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> MAGENTA_MARBLE_SLAB = registerBlockItem("magenta_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MAGENTA_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> MAGENTA_MARBLE_WALL = registerBlockItem("magenta_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MAGENTA_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> MAGENTA_MARBLE_BUTTON = registerBlockItem("magenta_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(MAGENTA_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> MAGENTA_MARBLE_PRESSURE_PLATE = registerBlockItem("magenta_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(MAGENTA_MARBLE.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> PINK_MARBLE = registerBlockItem("pink_marble", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> PINK_MARBLE_STAIRS = registerBlockItem("pink_marble_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(PINK_MARBLE.get())));
    public static final RegistrySupplier<SlabBlock> PINK_MARBLE_SLAB = registerBlockItem("pink_marble_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(PINK_MARBLE.get())));
    public static final RegistrySupplier<WallBlock> PINK_MARBLE_WALL = registerBlockItem("pink_marble_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(PINK_MARBLE.get())));
    public static final RegistrySupplier<ButtonBlock> PINK_MARBLE_BUTTON = registerBlockItem("pink_marble_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(PINK_MARBLE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> PINK_MARBLE_PRESSURE_PLATE = registerBlockItem("pink_marble_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(PINK_MARBLE.get()), BlockSetType.STONE));

    /**
     * Unown Blocks
     */
    public static final RegistrySupplier<Block> UNOWN_BLOCK_A = registerBlockItem("unown_block_a", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_B = registerBlockItem("unown_block_b", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_C = registerBlockItem("unown_block_c", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_D = registerBlockItem("unown_block_d", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_E = registerBlockItem("unown_block_e", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_F = registerBlockItem("unown_block_f", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_G = registerBlockItem("unown_block_g", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_H = registerBlockItem("unown_block_h", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_I = registerBlockItem("unown_block_i", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_J = registerBlockItem("unown_block_j", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_K = registerBlockItem("unown_block_k", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_L = registerBlockItem("unown_block_l", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_M = registerBlockItem("unown_block_m", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_N = registerBlockItem("unown_block_n", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_O = registerBlockItem("unown_block_o", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_P = registerBlockItem("unown_block_p", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_Q = registerBlockItem("unown_block_q", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_R = registerBlockItem("unown_block_r", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_S = registerBlockItem("unown_block_s", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_T = registerBlockItem("unown_block_t", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_U = registerBlockItem("unown_block_u", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_V = registerBlockItem("unown_block_v", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_W = registerBlockItem("unown_block_w", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_X = registerBlockItem("unown_block_x", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_Y = registerBlockItem("unown_block_y", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_Z = registerBlockItem("unown_block_z", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_BLANK = registerBlockItem("unown_block_blank", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_EXCLAMATION_MARK = registerBlockItem("unown_block_exclamation_mark", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> UNOWN_BLOCK_QUESTION_MARK = registerBlockItem("unown_block_question_mark", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

    /**
     * Braille Blocks
     */
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_A = registerBlockItem("a_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_B = registerBlockItem("b_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_C = registerBlockItem("c_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_D = registerBlockItem("d_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_E = registerBlockItem("e_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_F = registerBlockItem("f_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_G = registerBlockItem("g_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_H = registerBlockItem("h_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_I = registerBlockItem("i_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_J = registerBlockItem("j_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_K = registerBlockItem("k_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_L = registerBlockItem("l_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_M = registerBlockItem("m_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_N = registerBlockItem("n_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_O = registerBlockItem("o_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_P = registerBlockItem("p_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_Q = registerBlockItem("q_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_R = registerBlockItem("r_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_S = registerBlockItem("s_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_T = registerBlockItem("t_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_U = registerBlockItem("u_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_V = registerBlockItem("v_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_W = registerBlockItem("w_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_X = registerBlockItem("x_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_Y = registerBlockItem("y_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_Z = registerBlockItem("z_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_COMMA = registerBlockItem("comma_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_PERIOD = registerBlockItem("period_braille_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistrySupplier<Block> BRAILLE_BLOCK_UNDERSCORE = registerBlockItem("braille_block_underscore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

    /**
     * Full ChargeStone Pallet
     */
    public static final RegistrySupplier<Block> CHARGE_STONE = registerBlockItem("charge_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> CHARGE_STONE_STAIRS = registerBlockItem("charge_stone_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(CHARGE_STONE.get())));
    public static final RegistrySupplier<SlabBlock> CHARGE_STONE_SLAB = registerBlockItem("charge_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CHARGE_STONE.get())));
    public static final RegistrySupplier<WallBlock> CHARGE_STONE_WALL = registerBlockItem("charge_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CHARGE_STONE.get())));
    public static final RegistrySupplier<PressurePlateBlock> CHARGE_STONE_PRESSURE_PLATE = registerBlockItem("charge_stone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(CHARGE_STONE.get()), BlockSetType.STONE));
    public static final RegistrySupplier<ButtonBlock> CHARGE_STONE_BUTTON = registerBlockItem("charge_stone_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(CHARGE_STONE.get()), BlockSetType.STONE, 20, false));

    //ChargeCobbleStone
    public static final RegistrySupplier<Block> CHARGE_COBBLESTONE = registerBlockItem("charge_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistrySupplier<StairBlock> CHARGE_COBBLESTONE_STAIRS = registerBlockItem("charge_cobblestone_stairs", () -> new StairBlock(Blocks.COBBLESTONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<SlabBlock> CHARGE_COBBLESTONE_SLAB = registerBlockItem("charge_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<WallBlock> CHARGE_COBBLESTONE_WALL = registerBlockItem("charge_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CHARGE_COBBLESTONE.get())));
    //Mossy ChargeCobbleStone
    public static final RegistrySupplier<Block> MOSSY_CHARGE_COBBLESTONE = registerBlockItem("mossy_charge_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSSY_COBBLESTONE)));
    public static final RegistrySupplier<StairBlock> MOSSY_CHARGE_COBBLESTONE_STAIRS = registerBlockItem("mossy_charge_cobblestone_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(MOSSY_CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<SlabBlock> MOSSY_CHARGE_COBBLESTONE_SLAB = registerBlockItem("mossy_charge_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MOSSY_CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<WallBlock> MOSSY_CHARGE_COBBLESTONE_WALL = registerBlockItem("mossy_charge_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MOSSY_CHARGE_COBBLESTONE.get())));
    //Smooth ChargeStone
    public static final RegistrySupplier<Block> SMOOTH_CHARGE_STONE = registerBlockItem("smooth_charge_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
    public static final RegistrySupplier<SlabBlock> SMOOTH_CHARGE_STONE_SLAB = registerBlockItem("smooth_charge_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE_SLAB)));
    //ChargeStoneBricks
    public static final RegistrySupplier<Block> CHARGE_STONE_BRICKS = registerBlockItem("charge_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistrySupplier<Block> CRACKED_CHARGE_STONE_BRICKS = registerBlockItem("cracked_charge_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS)));
    public static final RegistrySupplier<StairBlock> CHARGE_STONE_BRICK_STAIRS = registerBlockItem("charge_stone_brick_stairs", () -> new StairBlock(Blocks.STONE_BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(CHARGE_STONE_BRICKS.get())));
    public static final RegistrySupplier<SlabBlock> CHARGE_STONE_BRICK_SLAB = registerBlockItem("charge_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CHARGE_STONE_BRICKS.get())));
    public static final RegistrySupplier<WallBlock> CHARGE_STONE_BRICK_WALL = registerBlockItem("charge_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CHARGE_STONE_BRICKS.get())));
    public static final RegistrySupplier<Block> CHISELED_CHARGE_STONE_BRICKS = registerBlockItem("chiseled_charge_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS)));

    public static final RegistrySupplier<Block> MOSSY_CHARGE_STONE_BRICKS = registerBlockItem("mossy_charge_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS)));
    public static final RegistrySupplier<StairBlock> MOSSY_CHARGE_STONE_BRICK_STAIRS = registerBlockItem("mossy_charge_stone_brick_stairs", () -> new StairBlock(Blocks.MOSSY_STONE_BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(MOSSY_CHARGE_STONE_BRICKS.get())));
    public static final RegistrySupplier<SlabBlock> MOSSY_CHARGE_STONE_BRICK_SLAB = registerBlockItem("mossy_charge_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MOSSY_CHARGE_STONE_BRICKS.get())));
    public static final RegistrySupplier<WallBlock> MOSSY_CHARGE_STONE_BRICK_WALL = registerBlockItem("mossy_charge_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MOSSY_CHARGE_STONE_BRICKS.get())));
    //Infested blocks
    public static final RegistrySupplier<InfestedBlock> INFESTED_CHARGE_STONE = registerBlockItem("infested_charge_stone", () -> new InfestedBlock(CHARGE_STONE.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CHARGE_COBBLESTONE = registerBlockItem("infested_charge_cobblestone", () -> new InfestedBlock(CHARGE_COBBLESTONE.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_COBBLESTONE)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CHARGE_STONE_BRICKS = registerBlockItem("infested_charge_stone_bricks", () -> new InfestedBlock(CHARGE_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_MOSSY_CHARGE_STONE_BRICKS = registerBlockItem("infested_mossy_charge_stone_bricks", () -> new InfestedBlock(MOSSY_CHARGE_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_MOSSY_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CRACKED_CHARGE_STONE_BRICKS = registerBlockItem("infested_cracked_charge_stone_bricks", () -> new InfestedBlock(CRACKED_CHARGE_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_CRACKED_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CHISELED_CHARGE_STONE_BRICKS = registerBlockItem("infested_chiseled_charge_stone_bricks", () -> new InfestedBlock(CHISELED_CHARGE_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_CHISELED_STONE_BRICKS)));
    //Full ChargeStone Pallet ^

    public static final RegistrySupplier<Block> BRIGHT_CHARGE_COBBLESTONE = registerBlockItem("bright_charge_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).lightLevel(lightLevel -> 30)));
    public static final RegistrySupplier<SlabBlock> BRIGHT_CHARGE_COBBLESTONE_SLAB = registerBlockItem("bright_charge_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BRIGHT_CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<StairBlock> BRIGHT_CHARGE_COBBLESTONE_STAIRS = registerBlockItem("bright_charge_cobblestone_stairs", () -> new StairBlock(Blocks.COBBLESTONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(BRIGHT_CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<WallBlock> BRIGHT_CHARGE_COBBLESTONE_WALL = registerBlockItem("bright_charge_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(BRIGHT_CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<Block> GLOWING_CHARGE_COBBLESTONE = registerBlockItem("glowing_charge_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).lightLevel(lightLevel -> 15)));
    public static final RegistrySupplier<SlabBlock> GLOWING_CHARGE_COBBLESTONE_SLAB = registerBlockItem("glowing_charge_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GLOWING_CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<StairBlock> GLOWING_CHARGE_COBBLESTONE_STAIRS = registerBlockItem("glowing_charge_cobblestone_stairs", () -> new StairBlock(Blocks.COBBLESTONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(GLOWING_CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<WallBlock> GLOWING_CHARGE_COBBLESTONE_WALL = registerBlockItem("glowing_charge_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GLOWING_CHARGE_COBBLESTONE.get())));
    public static final RegistrySupplier<PointedChargeDripstoneBlock> POINTED_CHARGE_DRIPSTONE = registerBlockItem("pointed_charge_dripstone", () -> new PointedChargeDripstoneBlock(BlockBehaviour.Properties.copy(Blocks.POINTED_DRIPSTONE)));
    public static final RegistrySupplier<Block> CHARGE_DRIPSTONE_BLOCK = registerBlockItem("charge_dripstone_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DRIPSTONE_BLOCK)));

    /**
     * Volcanic Stone Pallet
     */
    public static final RegistrySupplier<Block> VOLCANIC_STONE = registerBlockItem("volcanic_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> VOLCANIC_STONE_STAIRS = registerBlockItem("volcanic_stone_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(VOLCANIC_STONE.get())));
    public static final RegistrySupplier<SlabBlock> VOLCANIC_STONE_SLAB = registerBlockItem("volcanic_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(VOLCANIC_STONE.get())));
    public static final RegistrySupplier<WallBlock> VOLCANIC_STONE_WALL = registerBlockItem("volcanic_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(VOLCANIC_STONE.get())));
    public static final RegistrySupplier<ButtonBlock> VOLCANIC_STONE_BUTTON = registerBlockItem("volcanic_stone_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(VOLCANIC_STONE.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> VOLCANIC_STONE_PRESSURE_PLATE = registerBlockItem("volcanic_stone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(VOLCANIC_STONE.get()), BlockSetType.STONE));

    //Volcanic CobbleStone
    public static final RegistrySupplier<Block> VOLCANIC_COBBLESTONE = registerBlockItem("volcanic_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistrySupplier<StairBlock> VOLCANIC_COBBLESTONE_STAIRS = registerBlockItem("volcanic_cobblestone_stairs", () -> new StairBlock(Blocks.COBBLESTONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(VOLCANIC_COBBLESTONE.get())));
    public static final RegistrySupplier<SlabBlock> VOLCANIC_COBBLESTONE_SLAB = registerBlockItem("volcanic_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(VOLCANIC_COBBLESTONE.get())));
    public static final RegistrySupplier<WallBlock> VOLCANIC_COBBLESTONE_WALL = registerBlockItem("volcanic_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(VOLCANIC_COBBLESTONE.get())));

    //Mossy Volcanic CobbleStone
    public static final RegistrySupplier<Block> MOSSY_VOLCANIC_COBBLESTONE = registerBlockItem("mossy_volcanic_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(VOLCANIC_COBBLESTONE.get())));
    public static final RegistrySupplier<StairBlock> MOSSY_VOLCANIC_COBBLESTONE_STAIRS = registerBlockItem("mossy_volcanic_cobblestone_stairs", () -> new StairBlock(Blocks.COBBLESTONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(MOSSY_VOLCANIC_COBBLESTONE.get())));
    public static final RegistrySupplier<SlabBlock> MOSSY_VOLCANIC_COBBLESTONE_SLAB = registerBlockItem("mossy_volcanic_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MOSSY_VOLCANIC_COBBLESTONE.get())));
    public static final RegistrySupplier<WallBlock> MOSSY_VOLCANIC_COBBLESTONE_WALL = registerBlockItem("mossy_volcanic_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MOSSY_VOLCANIC_COBBLESTONE.get())));

    //Smooth Volcanic Stone
    public static final RegistrySupplier<Block> SMOOTH_VOLCANIC_STONE = registerBlockItem("smooth_volcanic_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
    public static final RegistrySupplier<SlabBlock> SMOOTH_VOLCANIC_STONE_SLAB = registerBlockItem("smooth_volcanic_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SMOOTH_VOLCANIC_STONE.get())));

    //Volcanic Stone Bricks
    public static final RegistrySupplier<Block> VOLCANIC_STONE_BRICKS = registerBlockItem("volcanic_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistrySupplier<Block> CRACKED_VOLCANIC_STONE_BRICKS = registerBlockItem("cracked_volcanic_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS)));
    public static final RegistrySupplier<StairBlock> VOLCANIC_STONE_BRICK_STAIRS = registerBlockItem("volcanic_stone_brick_stairs", () -> new StairBlock(Blocks.STONE_BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(VOLCANIC_STONE_BRICKS.get())));
    public static final RegistrySupplier<SlabBlock> VOLCANIC_STONE_BRICK_SLAB = registerBlockItem("volcanic_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(VOLCANIC_STONE_BRICKS.get())));
    public static final RegistrySupplier<WallBlock> VOLCANIC_STONE_BRICK_WALL = registerBlockItem("volcanic_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(VOLCANIC_STONE_BRICKS.get())));
    public static final RegistrySupplier<Block> CHISELED_VOLCANIC_STONE_BRICKS = registerBlockItem("chiseled_volcanic_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS)));

    //Mossy Volcanic Stone Bricks
    public static final RegistrySupplier<Block> MOSSY_VOLCANIC_STONE_BRICKS = registerBlockItem("mossy_volcanic_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS)));
    public static final RegistrySupplier<StairBlock> MOSSY_VOLCANIC_STONE_BRICK_STAIRS = registerBlockItem("mossy_volcanic_stone_brick_stairs", () -> new StairBlock(Blocks.STONE_BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(MOSSY_VOLCANIC_STONE_BRICKS.get())));
    public static final RegistrySupplier<SlabBlock> MOSSY_VOLCANIC_STONE_BRICK_SLAB = registerBlockItem("mossy_volcanic_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MOSSY_VOLCANIC_STONE_BRICKS.get())));
    public static final RegistrySupplier<WallBlock> MOSSY_VOLCANIC_STONE_BRICK_WALL = registerBlockItem("mossy_volcanic_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MOSSY_VOLCANIC_STONE_BRICKS.get())));
    //Infested Volcanic Stone Bricks
    public static final RegistrySupplier<InfestedBlock> INFESTED_VOLCANIC_STONE = registerBlockItem("infested_volcanic_stone", () -> new InfestedBlock(VOLCANIC_STONE.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_VOLCANIC_COBBLESTONE = registerBlockItem("infested_volcanic_cobblestone", () -> new InfestedBlock(VOLCANIC_COBBLESTONE.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_COBBLESTONE)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_VOLCANIC_STONE_BRICKS = registerBlockItem("infested_volcanic_stone_bricks", () -> new InfestedBlock(VOLCANIC_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_MOSSY_VOLCANIC_STONE_BRICKS = registerBlockItem("infested_mossy_volcanic_stone_bricks", () -> new InfestedBlock(MOSSY_VOLCANIC_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CRACKED_VOLCANIC_STONE_BRICKS = registerBlockItem("infested_cracked_volcanic_stone_bricks", () -> new InfestedBlock(CRACKED_VOLCANIC_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE_BRICKS)));
    public static final RegistrySupplier<InfestedBlock> INFESTED_CHISELED_VOLCANIC_STONE_BRICKS = registerBlockItem("infested_chiseled_volcanic_stone_bricks", () -> new InfestedBlock(CHISELED_VOLCANIC_STONE_BRICKS.get(), BlockBehaviour.Properties.copy(Blocks.INFESTED_STONE_BRICKS)));
    //Full Volcanic Stone Pallet^

    public static final RegistrySupplier<Block> VOLCANIC_FIRESTONE = registerBlockItem("volcanic_firestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistrySupplier<Block> VOLCANIC_ROCK = registerBlockItem("volcanic_rock", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<SlabBlock> VOLCANIC_ROCK_SLAB = registerBlockItem("volcanic_rock_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(VOLCANIC_ROCK.get())));
    public static final RegistrySupplier<StairBlock> VOLCANIC_ROCK_STAIRS = registerBlockItem("volcanic_rock_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(VOLCANIC_ROCK.get())));
    public static final RegistrySupplier<WallBlock> VOLCANIC_ROCK_WALL = registerBlockItem("volcanic_rock_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(VOLCANIC_ROCK.get())));

    /**
     * Battle Subway Blocks
     */
    public static final RegistrySupplier<Block> SUBWAY_FLOOR_CROSS = registerBlockItem("subway_floor_cross", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistrySupplier<Block> SUBWAY_FLOOR_FULL_CROSS = registerBlockItem("subway_floor_full_cross", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistrySupplier<Block> SUBWAY_FLOOR_PATH = registerBlockItem("subway_floor_path", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistrySupplier<Block> SUBWAY_FLOOR_PLAIN = registerBlockItem("subway_floor_plain", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistrySupplier<Block> SUBWAY_WALL = registerBlockItem("subway_wall", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistrySupplier<Block> SUBWAY_WALL_2 = registerBlockItem("subway_wall_2", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));


    public static final RegistrySupplier<SlabBlock> SUBWAY_FLOOR_CROSS_SLAB = registerBlockItem("subway_floor_cross_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SUBWAY_FLOOR_CROSS.get())));
    public static final RegistrySupplier<SlabBlock> SUBWAY_FLOOR_FULL_CROSS_SLAB = registerBlockItem("subway_floor_full_cross_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SUBWAY_FLOOR_FULL_CROSS.get())));
    public static final RegistrySupplier<SlabBlock> SUBWAY_FLOOR_PATH_SLAB = registerBlockItem("subway_floor_path_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SUBWAY_FLOOR_PATH.get())));
    public static final RegistrySupplier<SlabBlock> SUBWAY_FLOOR_PLAIN_SLAB = registerBlockItem("subway_floor_plain_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SUBWAY_FLOOR_PLAIN.get())));
    public static final RegistrySupplier<SlabBlock> SUBWAY_WALL_SLAB = registerBlockItem("subway_wall_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SUBWAY_WALL.get())));
    public static final RegistrySupplier<SlabBlock> SUBWAY_WALL_2_SLAB = registerBlockItem("subway_wall_2_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SUBWAY_WALL_2.get())));

    public static final RegistrySupplier<StairBlock> SUBWAY_FLOOR_CROSS_STAIRS = registerBlockItem("subway_floor_cross_stairs", () -> new StairBlock(SUBWAY_FLOOR_CROSS.get().defaultBlockState(), BlockBehaviour.Properties.copy(SUBWAY_FLOOR_CROSS.get())));
    public static final RegistrySupplier<StairBlock> SUBWAY_FLOOR_FULL_CROSS_STAIRS = registerBlockItem("subway_floor_full_cross_stairs", () -> new StairBlock(SUBWAY_FLOOR_FULL_CROSS.get().defaultBlockState(), BlockBehaviour.Properties.copy(SUBWAY_FLOOR_FULL_CROSS.get())));
    public static final RegistrySupplier<StairBlock> SUBWAY_FLOOR_PATH_STAIRS = registerBlockItem("subway_floor_path_stairs", () -> new StairBlock(SUBWAY_FLOOR_PATH.get().defaultBlockState(), BlockBehaviour.Properties.copy(SUBWAY_FLOOR_PATH.get())));
    public static final RegistrySupplier<StairBlock> SUBWAY_FLOOR_PLAIN_STAIRS = registerBlockItem("subway_floor_plain_stairs", () -> new StairBlock(SUBWAY_FLOOR_PLAIN.get().defaultBlockState(), BlockBehaviour.Properties.copy(SUBWAY_FLOOR_PLAIN.get())));
    public static final RegistrySupplier<StairBlock> SUBWAY_WALL_STAIRS = registerBlockItem("subway_wall_stairs", () -> new StairBlock(SUBWAY_WALL.get().defaultBlockState(), BlockBehaviour.Properties.copy(SUBWAY_WALL.get())));
    public static final RegistrySupplier<StairBlock> SUBWAY_WALL_2_STAIRS = registerBlockItem("subway_wall_2_stairs", () -> new StairBlock(SUBWAY_WALL_2.get().defaultBlockState(), BlockBehaviour.Properties.copy(SUBWAY_WALL_2.get())));

    public static final RegistrySupplier<WallBlock> SUBWAY_FLOOR_CROSS_WALL = registerBlockItem("subway_floor_cross_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SUBWAY_FLOOR_CROSS.get())));
    public static final RegistrySupplier<WallBlock> SUBWAY_FLOOR_FULL_CROSS_WALL = registerBlockItem("subway_floor_full_cross_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SUBWAY_FLOOR_FULL_CROSS.get())));
    public static final RegistrySupplier<WallBlock> SUBWAY_FLOOR_PATH_WALL = registerBlockItem("subway_floor_path_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SUBWAY_FLOOR_PATH.get())));
    public static final RegistrySupplier<WallBlock> SUBWAY_FLOOR_PLAIN_WALL = registerBlockItem("subway_floor_plain_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SUBWAY_FLOOR_PLAIN.get())));

    /**
     * Golden Temple (Pokelantis)
     */
    public static final RegistrySupplier<Block> GOLDEN_TEMPLE_SAND = registerBlockItem("golden_temple_sand", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> GOLDEN_TEMPLE_SANDSTONE = registerBlockItem("golden_temple_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistrySupplier<StairBlock> GOLDEN_TEMPLE_SANDSTONE_STAIRS = registerBlockItem("golden_temple_sandstone_stairs", () -> new StairBlock(Blocks.SANDSTONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.SANDSTONE_STAIRS)));
    public static final RegistrySupplier<SlabBlock> GOLDEN_TEMPLE_SANDSTONE_SLAB = registerBlockItem("golden_temple_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistrySupplier<WallBlock> GOLDEN_TEMPLE_SANDSTONE_WALL = registerBlockItem("golden_temple_sandstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistrySupplier<Block> GOLDEN_TEMPLE_CHISELED_SANDSTONE = registerBlockItem("golden_temple_chiseled_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_SANDSTONE)));
    public static final RegistrySupplier<Block> GOLDEN_TEMPLE_CUT_SANDSTONE = registerBlockItem("golden_temple_cut_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE)));
    public static final RegistrySupplier<SlabBlock> GOLDEN_TEMPLE_CUT_SANDSTONE_SLAB = registerBlockItem("golden_temple_cut_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE)));
    public static final RegistrySupplier<Block> GOLDEN_TEMPLE_SMOOTH_SANDSTONE = registerBlockItem("golden_temple_smooth_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE)));
    public static final RegistrySupplier<StairBlock> GOLDEN_TEMPLE_SMOOTH_SANDSTONE_STAIRS = registerBlockItem("golden_temple_smooth_sandstone_stairs", () -> new StairBlock(GOLDEN_TEMPLE_SMOOTH_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(GOLDEN_TEMPLE_SMOOTH_SANDSTONE.get())));
    public static final RegistrySupplier<SlabBlock> GOLDEN_TEMPLE_SMOOTH_SANDSTONE_SLAB = registerBlockItem("golden_temple_smooth_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GOLDEN_TEMPLE_SMOOTH_SANDSTONE.get())));
    public static final RegistrySupplier<WallBlock> GOLDEN_TEMPLE_SMOOTH_SANDSTONE_WALL = registerBlockItem("golden_temple_smooth_sandstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GOLDEN_TEMPLE_SMOOTH_SANDSTONE.get())));
    public static final RegistrySupplier<Block> GOLDEN_TEMPLE_PRISMARINE = registerBlockItem("golden_temple_prismarine", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_YELLOW).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistrySupplier<StairBlock> GOLDEN_TEMPLE_PRISMARINE_STAIRS = registerBlockItem("golden_temple_prismarine_stairs", () -> new StairBlock(Blocks.PRISMARINE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.PRISMARINE_STAIRS)));
    public static final RegistrySupplier<SlabBlock> GOLDEN_TEMPLE_PRISMARINE_SLAB = registerBlockItem("golden_temple_prismarine_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PRISMARINE)));
    public static final RegistrySupplier<WallBlock> GOLDEN_TEMPLE_PRISMARINE_WALL = registerBlockItem("golden_temple_prismarine_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.PRISMARINE)));
    public static final RegistrySupplier<Block> GOLDEN_TEMPLE_DARK_PRISMARINE = registerBlockItem("golden_temple_dark_prismarine", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DARK_PRISMARINE)));
    public static final RegistrySupplier<StairBlock> GOLDEN_TEMPLE_DARK_PRISMARINE_STAIRS = registerBlockItem("golden_temple_dark_prismarine_stairs", () -> new StairBlock(Blocks.DARK_PRISMARINE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.DARK_PRISMARINE_STAIRS)));
    public static final RegistrySupplier<SlabBlock> GOLDEN_TEMPLE_DARK_PRISMARINE_SLAB = registerBlockItem("golden_temple_dark_prismarine_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DARK_PRISMARINE)));
    public static final RegistrySupplier<WallBlock> GOLDEN_TEMPLE_DARK_PRISMARINE_WALL = registerBlockItem("golden_temple_dark_prismarine_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.DARK_PRISMARINE)));
    public static final RegistrySupplier<Block> GOLDEN_TEMPLE_PRISMARINE_BRICKS = registerBlockItem("golden_temple_prismarine_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.PRISMARINE_BRICKS)));
    public static final RegistrySupplier<StairBlock> GOLDEN_TEMPLE_PRISMARINE_BRICKS_STAIRS = registerBlockItem("golden_temple_prismarine_bricks_stairs", () -> new StairBlock(Blocks.PRISMARINE_BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.PRISMARINE_BRICK_STAIRS)));
    public static final RegistrySupplier<SlabBlock> GOLDEN_TEMPLE_PRISMARINE_BRICKS_SLAB = registerBlockItem("golden_temple_prismarine_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PRISMARINE_BRICKS)));
    public static final RegistrySupplier<WallBlock> GOLDEN_TEMPLE_PRISMARINE_BRICKS_WALL = registerBlockItem("golden_temple_prismarine_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.PRISMARINE_BRICKS)));

    /**
     * Floor
     */
    public static final RegistrySupplier<Block> FLOOR_1 = registerBlockItem("floor_1", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> FLOOR_1_STAIRS = registerBlockItem("floor_1_stairs", () -> new StairBlock(FLOOR_1.get().defaultBlockState(), BlockBehaviour.Properties.copy(FLOOR_1.get())));
    public static final RegistrySupplier<SlabBlock> FLOOR_1_SLAB = registerBlockItem("floor_1_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(FLOOR_1.get())));
    public static final RegistrySupplier<WallBlock> FLOOR_1_WALL = registerBlockItem("floor_1_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(FLOOR_1.get())));
    public static final RegistrySupplier<Block> FLOOR_2 = registerBlockItem("floor_2", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> FLOOR_2_STAIRS = registerBlockItem("floor_2_stairs", () -> new StairBlock(FLOOR_2.get().defaultBlockState(), BlockBehaviour.Properties.copy(FLOOR_2.get())));
    public static final RegistrySupplier<SlabBlock> FLOOR_2_SLAB = registerBlockItem("floor_2_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(FLOOR_2.get())));
    public static final RegistrySupplier<WallBlock> FLOOR_2_WALL = registerBlockItem("floor_2_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(FLOOR_2.get())));
    public static final RegistrySupplier<Block> FLOOR_3 = registerBlockItem("floor_3", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> FLOOR_3_STAIRS = registerBlockItem("floor_3_stairs", () -> new StairBlock(FLOOR_3.get().defaultBlockState(), BlockBehaviour.Properties.copy(FLOOR_3.get())));
    public static final RegistrySupplier<SlabBlock> FLOOR_3_SLAB = registerBlockItem("floor_3_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(FLOOR_3.get())));
    public static final RegistrySupplier<WallBlock> FLOOR_3_WALL = registerBlockItem("floor_3_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(FLOOR_3.get())));
    public static final RegistrySupplier<Block> FLOOR_4 = registerBlockItem("floor_4", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> FLOOR_4_STAIRS = registerBlockItem("floor_4_stairs", () -> new StairBlock(FLOOR_4.get().defaultBlockState(), BlockBehaviour.Properties.copy(FLOOR_4.get())));
    public static final RegistrySupplier<SlabBlock> FLOOR_4_SLAB = registerBlockItem("floor_4_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(FLOOR_4.get())));
    public static final RegistrySupplier<WallBlock> FLOOR_4_WALL = registerBlockItem("floor_4_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(FLOOR_4.get())));
    public static final RegistrySupplier<Block> MIRRORED_FLOOR_1 = registerBlockItem("mirrored_floor_1", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> MIRRORED_FLOOR_1_STAIRS = registerBlockItem("mirrored_floor_1_stairs", () -> new StairBlock(MIRRORED_FLOOR_1.get().defaultBlockState(), BlockBehaviour.Properties.copy(MIRRORED_FLOOR_1.get())));
    public static final RegistrySupplier<SlabBlock> MIRRORED_FLOOR_1_SLAB = registerBlockItem("mirrored_floor_1_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MIRRORED_FLOOR_1.get())));
    public static final RegistrySupplier<WallBlock> MIRRORED_FLOOR_1_WALL = registerBlockItem("mirrored_floor_1_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MIRRORED_FLOOR_1.get())));

    public static final RegistrySupplier<Block> MIRRORED_FLOOR_2 = registerBlockItem("mirrored_floor_2", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> MIRRORED_FLOOR_2_STAIRS = registerBlockItem("mirrored_floor_2_stairs", () -> new StairBlock(MIRRORED_FLOOR_2.get().defaultBlockState(), BlockBehaviour.Properties.copy(MIRRORED_FLOOR_2.get())));
    public static final RegistrySupplier<SlabBlock> MIRRORED_FLOOR_2_SLAB = registerBlockItem("mirrored_floor_2_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MIRRORED_FLOOR_2.get())));
    public static final RegistrySupplier<WallBlock> MIRRORED_FLOOR_2_WALL = registerBlockItem("mirrored_floor_2_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MIRRORED_FLOOR_2.get())));

    public static final RegistrySupplier<Block> MIRRORED_FLOOR_3 = registerBlockItem("mirrored_floor_3", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> MIRRORED_FLOOR_3_STAIRS = registerBlockItem("mirrored_floor_3_stairs", () -> new StairBlock(MIRRORED_FLOOR_3.get().defaultBlockState(), BlockBehaviour.Properties.copy(MIRRORED_FLOOR_3.get())));
    public static final RegistrySupplier<SlabBlock> MIRRORED_FLOOR_3_SLAB = registerBlockItem("mirrored_floor_3_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MIRRORED_FLOOR_3.get())));
    public static final RegistrySupplier<WallBlock> MIRRORED_FLOOR_3_WALL = registerBlockItem("mirrored_floor_3_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MIRRORED_FLOOR_3.get())));

    public static final RegistrySupplier<Block> ENCHANTED_OBSIDIAN = registerBlockItem("enchanted_obsidian", () -> new EnchantedObsidianBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)));
    public static final RegistrySupplier<StairBlock> ENCHANTED_OBSIDIAN_STAIRS = registerBlockItem("enchanted_obsidian_stairs", () -> new StairBlock(Blocks.OBSIDIAN.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)));
    public static final RegistrySupplier<SlabBlock> ENCHANTED_OBSIDIAN_SLAB = registerBlockItem("enchanted_obsidian_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)));
    public static final RegistrySupplier<WallBlock> ENCHANTED_OBSIDIAN_WALL = registerBlockItem("enchanted_obsidian_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)));
    public static final RegistrySupplier<ButtonBlock> ENCHANTED_OBSIDIAN_BUTTON = registerBlockItem("enchanted_obsidian_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ENCHANTED_OBSIDIAN_PRESSURE_PLATE = registerBlockItem("enchanted_obsidian_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OBSIDIAN), BlockSetType.STONE));

    /**
     * Fences and Gates
     */
    public static final RegistrySupplier<FenceBlock> BLUE_PICKET_FENCE = registerBlockItem("blue_picket_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f, 3.0f)));
    public static final RegistrySupplier<FenceGateBlock> BLUE_PICKET_FENCE_GATE = registerBlockItem("blue_picket_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f, 3.0f), WoodType.OAK));
    public static final RegistrySupplier<FenceBlock> RED_PICKET_FENCE = registerBlockItem("red_picket_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f, 3.0f)));
    public static final RegistrySupplier<FenceGateBlock> RED_PICKET_FENCE_GATE = registerBlockItem("red_picket_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f, 3.0f), WoodType.OAK));
    public static final RegistrySupplier<FenceBlock> WHITE_PICKET_FENCE = registerBlockItem("white_picket_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f, 3.0f)));
    public static final RegistrySupplier<FenceGateBlock> WHITE_PICKET_FENCE_GATE = registerBlockItem("white_picket_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f, 3.0f), WoodType.OAK));
    public static final RegistrySupplier<FenceBlock> YELLOW_PICKET_FENCE = registerBlockItem("yellow_picket_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f, 3.0f)));
    public static final RegistrySupplier<FenceGateBlock> YELLOW_PICKET_FENCE_GATE = registerBlockItem("yellow_picket_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f, 3.0f), WoodType.OAK));
    /**
     * Poke Brick
     */

    public static final RegistrySupplier<Block> WHITE_POKE_BRICK = registerBlockItem("white_poke_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> WHITE_POKE_BRICK_STAIRS = registerBlockItem("white_poke_brick_stairs", () -> new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(WHITE_POKE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> WHITE_POKE_BRICK_SLAB = registerBlockItem("white_poke_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(WHITE_POKE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> WHITE_POKE_BRICK_WALL = registerBlockItem("white_poke_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(WHITE_POKE_BRICK.get())));
    public static final RegistrySupplier<ButtonBlock> WHITE_POKE_BRICK_BUTTON = registerBlockItem("white_poke_brick_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(WHITE_POKE_BRICK.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> WHITE_POKE_BRICK_PRESSURE_PLATE = registerBlockItem("white_poke_brick_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(WHITE_POKE_BRICK.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> LIGHT_GRAY_POKE_BRICK = registerBlockItem("light_gray_poke_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> LIGHT_GRAY_POKE_BRICK_STAIRS = registerBlockItem("light_gray_poke_brick_stairs", () -> new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(LIGHT_GRAY_POKE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> LIGHT_GRAY_POKE_BRICK_SLAB = registerBlockItem("light_gray_poke_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(LIGHT_GRAY_POKE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> LIGHT_GRAY_POKE_BRICK_WALL = registerBlockItem("light_gray_poke_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(LIGHT_GRAY_POKE_BRICK.get())));
    public static final RegistrySupplier<ButtonBlock> LIGHT_GRAY_POKE_BRICK_BUTTON = registerBlockItem("light_gray_poke_brick_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(LIGHT_GRAY_POKE_BRICK.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> LIGHT_GRAY_POKE_BRICK_PRESSURE_PLATE = registerBlockItem("light_gray_poke_brick_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(LIGHT_GRAY_POKE_BRICK.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> GRAY_POKE_BRICK = registerBlockItem("gray_poke_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> GRAY_POKE_BRICK_STAIRS = registerBlockItem("gray_poke_brick_stairs", () -> new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(GRAY_POKE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> GRAY_POKE_BRICK_SLAB = registerBlockItem("gray_poke_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GRAY_POKE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> GRAY_POKE_BRICK_WALL = registerBlockItem("gray_poke_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GRAY_POKE_BRICK.get())));
    public static final RegistrySupplier<ButtonBlock> GRAY_POKE_BRICK_BUTTON = registerBlockItem("gray_poke_brick_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(GRAY_POKE_BRICK.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> GRAY_POKE_BRICK_PRESSURE_PLATE = registerBlockItem("gray_poke_brick_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(GRAY_POKE_BRICK.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> BLACK_POKE_BRICK = registerBlockItem("black_poke_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> BLACK_POKE_BRICK_STAIRS = registerBlockItem("black_poke_brick_stairs", () -> new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(BLACK_POKE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> BLACK_POKE_BRICK_SLAB = registerBlockItem("black_poke_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BLACK_POKE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> BLACK_POKE_BRICK_WALL = registerBlockItem("black_poke_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(BLACK_POKE_BRICK.get())));
    public static final RegistrySupplier<ButtonBlock> BLACK_POKE_BRICK_BUTTON = registerBlockItem("black_poke_brick_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(BLACK_POKE_BRICK.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> BLACK_POKE_BRICK_PRESSURE_PLATE = registerBlockItem("black_poke_brick_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(BLACK_POKE_BRICK.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> BROWN_POKE_BRICK = registerBlockItem("brown_poke_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> BROWN_POKE_BRICK_STAIRS = registerBlockItem("brown_poke_brick_stairs", () -> new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(BROWN_POKE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> BROWN_POKE_BRICK_SLAB = registerBlockItem("brown_poke_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BROWN_POKE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> BROWN_POKE_BRICK_WALL = registerBlockItem("brown_poke_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(BROWN_POKE_BRICK.get())));
    public static final RegistrySupplier<ButtonBlock> BROWN_POKE_BRICK_BUTTON = registerBlockItem("brown_poke_brick_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(BROWN_POKE_BRICK.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> BROWN_POKE_BRICK_PRESSURE_PLATE = registerBlockItem("brown_poke_brick_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(BROWN_POKE_BRICK.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> RED_POKE_BRICK = registerBlockItem("red_poke_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> RED_POKE_BRICK_STAIRS = registerBlockItem("red_poke_brick_stairs", () -> new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(RED_POKE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> RED_POKE_BRICK_SLAB = registerBlockItem("red_poke_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(RED_POKE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> RED_POKE_BRICK_WALL = registerBlockItem("red_poke_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(RED_POKE_BRICK.get())));
    public static final RegistrySupplier<ButtonBlock> RED_POKE_BRICK_BUTTON = registerBlockItem("red_poke_brick_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(RED_POKE_BRICK.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> RED_POKE_BRICK_PRESSURE_PLATE = registerBlockItem("red_poke_brick_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(RED_POKE_BRICK.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> ORANGE_POKE_BRICK = registerBlockItem("orange_poke_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> ORANGE_POKE_BRICK_STAIRS = registerBlockItem("orange_poke_brick_stairs", () -> new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(ORANGE_POKE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> ORANGE_POKE_BRICK_SLAB = registerBlockItem("orange_poke_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ORANGE_POKE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> ORANGE_POKE_BRICK_WALL = registerBlockItem("orange_poke_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ORANGE_POKE_BRICK.get())));
    public static final RegistrySupplier<ButtonBlock> ORANGE_POKE_BRICK_BUTTON = registerBlockItem("orange_poke_brick_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(ORANGE_POKE_BRICK.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> ORANGE_POKE_BRICK_PRESSURE_PLATE = registerBlockItem("orange_poke_brick_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(ORANGE_POKE_BRICK.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> YELLOW_POKE_BRICK = registerBlockItem("yellow_poke_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> YELLOW_POKE_BRICK_STAIRS = registerBlockItem("yellow_poke_brick_stairs", () -> new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(YELLOW_POKE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> YELLOW_POKE_BRICK_SLAB = registerBlockItem("yellow_poke_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(YELLOW_POKE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> YELLOW_POKE_BRICK_WALL = registerBlockItem("yellow_poke_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(YELLOW_POKE_BRICK.get())));
    public static final RegistrySupplier<ButtonBlock> YELLOW_POKE_BRICK_BUTTON = registerBlockItem("yellow_poke_brick_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(YELLOW_POKE_BRICK.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> YELLOW_POKE_BRICK_PRESSURE_PLATE = registerBlockItem("yellow_poke_brick_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(YELLOW_POKE_BRICK.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> LIME_POKE_BRICK = registerBlockItem("lime_poke_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> LIME_POKE_BRICK_STAIRS = registerBlockItem("lime_poke_brick_stairs", () -> new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(LIME_POKE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> LIME_POKE_BRICK_SLAB = registerBlockItem("lime_poke_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(LIME_POKE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> LIME_POKE_BRICK_WALL = registerBlockItem("lime_poke_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(LIME_POKE_BRICK.get())));
    public static final RegistrySupplier<ButtonBlock> LIME_POKE_BRICK_BUTTON = registerBlockItem("lime_poke_brick_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(LIME_POKE_BRICK.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> LIME_POKE_BRICK_PRESSURE_PLATE = registerBlockItem("lime_poke_brick_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(LIME_POKE_BRICK.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> GREEN_POKE_BRICK = registerBlockItem("green_poke_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> GREEN_POKE_BRICK_STAIRS = registerBlockItem("green_poke_brick_stairs", () -> new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(GREEN_POKE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> GREEN_POKE_BRICK_SLAB = registerBlockItem("green_poke_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GREEN_POKE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> GREEN_POKE_BRICK_WALL = registerBlockItem("green_poke_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GREEN_POKE_BRICK.get())));
    public static final RegistrySupplier<ButtonBlock> GREEN_POKE_BRICK_BUTTON = registerBlockItem("green_poke_brick_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(GREEN_POKE_BRICK.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> GREEN_POKE_BRICK_PRESSURE_PLATE = registerBlockItem("green_poke_brick_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(GREEN_POKE_BRICK.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> CYAN_POKE_BRICK = registerBlockItem("cyan_poke_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> CYAN_POKE_BRICK_STAIRS = registerBlockItem("cyan_poke_brick_stairs", () -> new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(CYAN_POKE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> CYAN_POKE_BRICK_SLAB = registerBlockItem("cyan_poke_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CYAN_POKE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> CYAN_POKE_BRICK_WALL = registerBlockItem("cyan_poke_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CYAN_POKE_BRICK.get())));
    public static final RegistrySupplier<ButtonBlock> CYAN_POKE_BRICK_BUTTON = registerBlockItem("cyan_poke_brick_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(CYAN_POKE_BRICK.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> CYAN_POKE_BRICK_PRESSURE_PLATE = registerBlockItem("cyan_poke_brick_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(CYAN_POKE_BRICK.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> LIGHT_BLUE_POKE_BRICK = registerBlockItem("light_blue_poke_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> LIGHT_BLUE_POKE_BRICK_STAIRS = registerBlockItem("light_blue_poke_brick_stairs", () -> new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(LIGHT_BLUE_POKE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> LIGHT_BLUE_POKE_BRICK_SLAB = registerBlockItem("light_blue_poke_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(LIGHT_BLUE_POKE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> LIGHT_BLUE_POKE_BRICK_WALL = registerBlockItem("light_blue_poke_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(LIGHT_BLUE_POKE_BRICK.get())));
    public static final RegistrySupplier<ButtonBlock> LIGHT_BLUE_POKE_BRICK_BUTTON = registerBlockItem("light_blue_poke_brick_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(LIGHT_BLUE_POKE_BRICK.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> LIGHT_BLUE_POKE_BRICK_PRESSURE_PLATE = registerBlockItem("light_blue_poke_brick_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(LIGHT_BLUE_POKE_BRICK.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> BLUE_POKE_BRICK = registerBlockItem("blue_poke_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> BLUE_POKE_BRICK_STAIRS = registerBlockItem("blue_poke_brick_stairs", () -> new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(BLUE_POKE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> BLUE_POKE_BRICK_SLAB = registerBlockItem("blue_poke_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BLUE_POKE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> BLUE_POKE_BRICK_WALL = registerBlockItem("blue_poke_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(BLUE_POKE_BRICK.get())));
    public static final RegistrySupplier<ButtonBlock> BLUE_POKE_BRICK_BUTTON = registerBlockItem("blue_poke_brick_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(BLUE_POKE_BRICK.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> BLUE_POKE_BRICK_PRESSURE_PLATE = registerBlockItem("blue_poke_brick_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(BLUE_POKE_BRICK.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> PURPLE_POKE_BRICK = registerBlockItem("purple_poke_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> PURPLE_POKE_BRICK_STAIRS = registerBlockItem("purple_poke_brick_stairs", () -> new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(PURPLE_POKE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> PURPLE_POKE_BRICK_SLAB = registerBlockItem("purple_poke_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(PURPLE_POKE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> PURPLE_POKE_BRICK_WALL = registerBlockItem("purple_poke_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(PURPLE_POKE_BRICK.get())));
    public static final RegistrySupplier<ButtonBlock> PURPLE_POKE_BRICK_BUTTON = registerBlockItem("purple_poke_brick_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(PURPLE_POKE_BRICK.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> PURPLE_POKE_BRICK_PRESSURE_PLATE = registerBlockItem("purple_poke_brick_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(PURPLE_POKE_BRICK.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> MAGENTA_POKE_BRICK = registerBlockItem("magenta_poke_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> MAGENTA_POKE_BRICK_STAIRS = registerBlockItem("magenta_poke_brick_stairs", () -> new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(MAGENTA_POKE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> MAGENTA_POKE_BRICK_SLAB = registerBlockItem("magenta_poke_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MAGENTA_POKE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> MAGENTA_POKE_BRICK_WALL = registerBlockItem("magenta_poke_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MAGENTA_POKE_BRICK.get())));
    public static final RegistrySupplier<ButtonBlock> MAGENTA_POKE_BRICK_BUTTON = registerBlockItem("magenta_poke_brick_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(MAGENTA_POKE_BRICK.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> MAGENTA_POKE_BRICK_PRESSURE_PLATE = registerBlockItem("magenta_poke_brick_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(MAGENTA_POKE_BRICK.get()), BlockSetType.STONE));

    public static final RegistrySupplier<Block> PINK_POKE_BRICK = registerBlockItem("pink_poke_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> PINK_POKE_BRICK_STAIRS = registerBlockItem("pink_poke_brick_stairs", () -> new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(PINK_POKE_BRICK.get())));
    public static final RegistrySupplier<SlabBlock> PINK_POKE_BRICK_SLAB = registerBlockItem("pink_poke_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(PINK_POKE_BRICK.get())));
    public static final RegistrySupplier<WallBlock> PINK_POKE_BRICK_WALL = registerBlockItem("pink_poke_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(PINK_POKE_BRICK.get())));
    public static final RegistrySupplier<ButtonBlock> PINK_POKE_BRICK_BUTTON = registerBlockItem("pink_poke_brick_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(PINK_POKE_BRICK.get()), BlockSetType.STONE, 20, false));
    public static final RegistrySupplier<PressurePlateBlock> PINK_POKE_BRICK_PRESSURE_PLATE = registerBlockItem("pink_poke_brick_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(PINK_POKE_BRICK.get()), BlockSetType.STONE));


    /**
     * Ore Blocks
     */
    public static final RegistrySupplier<Block> RAW_ALUMINUM_BLOCK = registerBlockItem("raw_aluminum_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
    public static final RegistrySupplier<Block> ALUMINUM_BLOCK = registerBlockItem("aluminum_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistrySupplier<Block> SAPPHIRE_BLOCK = registerBlockItem("sapphire_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistrySupplier<StairBlock> SAPPHIRE_STAIRS = registerBlockItem("sapphire_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(SAPPHIRE_BLOCK.get())));
    public static final RegistrySupplier<SlabBlock> SAPPHIRE_SLAB = registerBlockItem("sapphire_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SAPPHIRE_BLOCK.get())));
    public static final RegistrySupplier<WallBlock> SAPPHIRE_WALL = registerBlockItem("sapphire_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SAPPHIRE_BLOCK.get())));

    public static final RegistrySupplier<Block> RUBY_BLOCK = registerBlockItem("ruby_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistrySupplier<StairBlock> RUBY_STAIRS = registerBlockItem("ruby_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(RUBY_BLOCK.get())));
    public static final RegistrySupplier<SlabBlock> RUBY_SLAB = registerBlockItem("ruby_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(RUBY_BLOCK.get())));
    public static final RegistrySupplier<WallBlock> RUBY_WALL = registerBlockItem("ruby_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(RUBY_BLOCK.get())));



    public static final RegistrySupplier<Block> CRYSTAL_BLOCK = registerBlockItem("crystal_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistrySupplier<StairBlock> CRYSTAL_STAIRS = registerBlockItem("crystal_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(CRYSTAL_BLOCK.get())));
    public static final RegistrySupplier<SlabBlock> CRYSTAL_SLAB = registerBlockItem("crystal_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CRYSTAL_BLOCK.get())));
    public static final RegistrySupplier<WallBlock> CRYSTAL_WALL = registerBlockItem("crystal_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CRYSTAL_BLOCK.get())));
    public static final RegistrySupplier<Block> CRYSTAL_LIGHT = registerBlockItem("crystal_light", () -> new Block(BlockBehaviour.Properties.copy(CRYSTAL_BLOCK.get()).lightLevel((state) -> 15)));
    public static final RegistrySupplier<Block> SILICON_BLOCK = registerBlockItem("silicon_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    /**
     * Ultra Space
     */
    public static final RegistrySupplier<Block> ULTRA_SANDSTONE = registerBlockItem("ultra_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistrySupplier<StairBlock> ULTRA_SANDSTONE_STAIRS = registerBlockItem("ultra_sandstone_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(ULTRA_SANDSTONE.get())));
    public static final RegistrySupplier<SlabBlock> ULTRA_SANDSTONE_SLAB = registerBlockItem("ultra_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ULTRA_SANDSTONE.get())));
    public static final RegistrySupplier<WallBlock> ULTRA_SANDSTONE_WALL = registerBlockItem("ultra_sandstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ULTRA_SANDSTONE.get())));
    public static final RegistrySupplier<Block> ULTRA_SMOOTH_SANDSTONE = registerBlockItem("ultra_smooth_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE)));
    public static final RegistrySupplier<StairBlock> ULTRA_SMOOTH_SANDSTONE_STAIRS = registerBlockItem("ultra_smooth_sandstone_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(ULTRA_SMOOTH_SANDSTONE.get())));
    public static final RegistrySupplier<SlabBlock> ULTRA_SMOOTH_SANDSTONE_SLAB = registerBlockItem("ultra_smooth_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ULTRA_SMOOTH_SANDSTONE.get())));
    public static final RegistrySupplier<WallBlock> ULTRA_SMOOTH_SANDSTONE_WALL = registerBlockItem("ultra_smooth_sandstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ULTRA_SMOOTH_SANDSTONE.get())));
    public static final RegistrySupplier<Block> ULTRA_CHISELED_SANDSTONE = registerBlockItem("ultra_chiseled_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_SANDSTONE)));
    public static final RegistrySupplier<Block> ULTRA_CUT_SANDSTONE = registerBlockItem("ultra_cut_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE)));
    public static final RegistrySupplier<SlabBlock> ULTRA_CUT_SANDSTONE_SLAB = registerBlockItem("ultra_cut_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ULTRA_CUT_SANDSTONE.get())));
    public static final RegistrySupplier<Block> GHOST_LANTERN = registerBlockItem("ghost_lantern", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GLASS).strength(.5f).lightLevel((lightLevel) -> 15)));
    public static final RegistrySupplier<Block> GHOST_BRICKS = registerBlockItem("ghost_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> GHOST_BRICK_STAIRS = registerBlockItem("ghost_brick_stair", () -> new StairBlock(GHOST_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(GHOST_BRICKS.get())));
    public static final RegistrySupplier<SlabBlock> GHOST_BRICK_SLAB = registerBlockItem("ghost_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GHOST_BRICKS.get())));
    public static final RegistrySupplier<WallBlock> GHOST_BRICK_WALL = registerBlockItem("ghost_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GHOST_BRICKS.get())));
    public static final RegistrySupplier<Block> GHOST_OBELISK = registerBlockItem("ghost_obelisk", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> GHOST_OBELISK_STAIRS = registerBlockItem("ghost_obelisk_stairs", () -> new StairBlock(GHOST_OBELISK.get().defaultBlockState(), BlockBehaviour.Properties.copy(GHOST_OBELISK.get())));
    public static final RegistrySupplier<SlabBlock> GHOST_OBELISK_SLAB = registerBlockItem("ghost_obelisk_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GHOST_OBELISK.get())));
    public static final RegistrySupplier<WallBlock> GHOST_OBELISK_WALL = registerBlockItem("ghost_obelisk_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GHOST_OBELISK.get())));
    public static final RegistrySupplier<Block> GHOST_PILLAR_SIDE = registerBlockItem("ghost_pillar_side", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> GHOST_PILLAR_SIDE_STAIRS = registerBlockItem("ghost_pillar_side_stairs", () -> new StairBlock(GHOST_PILLAR_SIDE.get().defaultBlockState(), BlockBehaviour.Properties.copy(GHOST_PILLAR_SIDE.get())));
    public static final RegistrySupplier<SlabBlock> GHOST_PILLAR_SIDE_SLAB = registerBlockItem("ghost_pillar_side_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GHOST_PILLAR_SIDE.get())));
    public static final RegistrySupplier<WallBlock> GHOST_PILLAR_SIDE_WALL = registerBlockItem("ghost_pillar_side_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GHOST_PILLAR_SIDE.get())));
    public static final RegistrySupplier<Block> GHOST_PILLAR_TOP = registerBlockItem("ghost_pillar_top", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> GHOST_PILLAR_TOP_STAIRS = registerBlockItem("ghost_pillar_top_stairs", () -> new StairBlock(GHOST_PILLAR_TOP.get().defaultBlockState(), BlockBehaviour.Properties.copy(GHOST_PILLAR_TOP.get())));
    public static final RegistrySupplier<SlabBlock> GHOST_PILLAR_TOP_SLAB = registerBlockItem("ghost_pillar_top_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GHOST_PILLAR_TOP.get())));
    public static final RegistrySupplier<WallBlock> GHOST_PILLAR_TOP_WALL = registerBlockItem("ghost_pillar_top_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(GHOST_PILLAR_TOP.get())));
    public static final RegistrySupplier<Block> MIRROR_GLASS = registerBlockItem("mirror_glass", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GLASS).strength(.5f)));
    public static final RegistrySupplier<StairBlock>  MIRROR_GLASS_STAIRS = registerBlockItem("mirror_glass_stairs", () -> new StairBlock(MIRROR_GLASS.get().defaultBlockState(), BlockBehaviour.Properties.copy(MIRROR_GLASS.get())));
    public static final RegistrySupplier<SlabBlock> MIRROR_GLASS_SLAB = registerBlockItem("mirror_glass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MIRROR_GLASS.get())));
    public static final RegistrySupplier<WallBlock> MIRROR_GLASS_WALL = registerBlockItem("mirror_glass_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MIRROR_GLASS.get())));

    public static final RegistrySupplier<Block> NORMAL_SANDSTONE = registerBlockItem("normal_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistrySupplier<StairBlock> NORMAL_SANDSTONE_STAIRS = registerBlockItem("normal_sandstone_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(NORMAL_SANDSTONE.get())));
    public static final RegistrySupplier<SlabBlock> NORMAL_SANDSTONE_SLAB = registerBlockItem("normal_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(NORMAL_SANDSTONE.get())));
    public static final RegistrySupplier<WallBlock> NORMAL_SANDSTONE_WALL = registerBlockItem("normal_sandstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(NORMAL_SANDSTONE.get())));
    public static final RegistrySupplier<Block> BRIGHT_SANDSTONE = registerBlockItem("bright_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE).lightLevel((lightLevel) -> 15)));
    public static final RegistrySupplier<StairBlock> BRIGHT_SANDSTONE_STAIRS = registerBlockItem("bright_sandstone_stairs", () -> new StairBlock(Blocks.STONE_STAIRS.defaultBlockState(), BlockBehaviour.Properties.copy(BRIGHT_SANDSTONE.get())));
    public static final RegistrySupplier<SlabBlock> BRIGHT_SANDSTONE_SLAB = registerBlockItem("bright_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BRIGHT_SANDSTONE.get())));
    public static final RegistrySupplier<WallBlock> BRIGHT_SANDSTONE_WALL = registerBlockItem("bright_sandstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(BRIGHT_SANDSTONE.get())));
    public static final RegistrySupplier<Block> MACHINE_BLOCK = registerBlockItem("machine_block", () -> new MachineBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> OCEAN_BLOCK = registerBlockItem("ocean_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> OCEAN_BLOCK_STAIRS = registerBlockItem("ocean_block_stairs", () -> new StairBlock(OCEAN_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(OCEAN_BLOCK.get())));
    public static final RegistrySupplier<SlabBlock> OCEAN_BLOCK_SLAB = registerBlockItem("ocean_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(OCEAN_BLOCK.get())));
    public static final RegistrySupplier<WallBlock> OCEAN_BLOCK_WALL = registerBlockItem("ocean_block_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(OCEAN_BLOCK.get())));

    public static final RegistrySupplier<Block> WATER_QUARTZ = registerBlockItem("water_quartz", () -> new Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)));
    public static final RegistrySupplier<StairBlock> WATER_QUARTZ_STAIRS = registerBlockItem("water_quartz_stairs", () -> new StairBlock(WATER_QUARTZ.get().defaultBlockState(), BlockBehaviour.Properties.copy(WATER_QUARTZ.get())));
    public static final RegistrySupplier<SlabBlock> WATER_QUARTZ_SLAB = registerBlockItem("water_quartz_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(WATER_QUARTZ.get())));
    public static final RegistrySupplier<WallBlock> WATER_QUARTZ_WALL = registerBlockItem("water_quartz_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(WATER_QUARTZ.get())));
    public static final RegistrySupplier<Block> CONCRETE_TOP = registerBlockItem("concrete_top", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));
    public static final RegistrySupplier<StairBlock> CONCRETE_TOP_STAIRS = registerBlockItem("concrete_top_stairs", () -> new StairBlock(CONCRETE_TOP.get().defaultBlockState(), BlockBehaviour.Properties.copy(CONCRETE_TOP.get())));
    public static final RegistrySupplier<SlabBlock> CONCRETE_TOP_SLAB = registerBlockItem("concrete_top_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CONCRETE_TOP.get())));
    public static final RegistrySupplier<WallBlock> CONCRETE_TOP_WALL = registerBlockItem("concrete_top_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(CONCRETE_TOP.get())));
    public static final RegistrySupplier<Block> RUINS_SAND = registerBlockItem("ruins_sand", () -> new SandBlock(0xdbd3a0, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> BURST_TURF = registerBlockItem("burst_turf", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
    public static final RegistrySupplier<Block> SOFT_SOIL = registerBlockItem("soft_soil", () -> new SoftSoilBlock(BlockBehaviour.Properties.copy(Blocks.FARMLAND).randomTicks()));

    //Rich Soil
    public static final RegistrySupplier<Block> RICH_SOIL_1 = registerBlockItem("rich_soil_1", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistrySupplier<Block> RICH_SOIL_2 = registerBlockItem("rich_soil_2", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistrySupplier<Block> RICH_SOIL_3 = registerBlockItem("rich_soil_3", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistrySupplier<Block> RICH_SOIL_4 = registerBlockItem("rich_soil_4", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));

    //Bleach Stone
    public static final RegistrySupplier<Block> BLEACH_STONE = registerBlockItem("bleach_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> BLEACH_STONE_STAIRS = registerBlockItem("bleach_stone_stairs", () -> new StairBlock(BLEACH_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(BLEACH_STONE.get())));
    public static final RegistrySupplier<SlabBlock> BLEACH_STONE_SLAB = registerBlockItem("bleach_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BLEACH_STONE.get())));
    public static final RegistrySupplier<WallBlock> BLEACH_STONE_WALL = registerBlockItem("bleach_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(BLEACH_STONE.get())));

    //Shingles
    public static final RegistrySupplier<Block> WHITE_SHINGLES = registerBlockItem("white_shingles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> WHITE_SHINGLES_STAIRS = registerBlockItem("white_shingles_stairs", () -> new StairBlock(WHITE_SHINGLES.get().defaultBlockState(), BlockBehaviour.Properties.copy(WHITE_SHINGLES.get())));
    public static final RegistrySupplier<SlabBlock> WHITE_SHINGLES_SLAB = registerBlockItem("white_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(WHITE_SHINGLES.get())));
    public static final RegistrySupplier<Block> LIGHT_GRAY_SHINGLES = registerBlockItem("light_gray_shingles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> LIGHT_GRAY_SHINGLES_STAIRS = registerBlockItem("light_gray_shingles_stairs", () -> new StairBlock(LIGHT_GRAY_SHINGLES.get().defaultBlockState(), BlockBehaviour.Properties.copy(LIGHT_GRAY_SHINGLES.get())));
    public static final RegistrySupplier<Block> LIGHT_GRAY_SHINGLES_SLAB = registerBlockItem("light_gray_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(LIGHT_GRAY_SHINGLES.get())));
    public static final RegistrySupplier<Block> GRAY_SHINGLES = registerBlockItem("gray_shingles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> GRAY_SHINGLES_STAIRS = registerBlockItem("gray_shingles_stairs", () -> new StairBlock(GRAY_SHINGLES.get().defaultBlockState(), BlockBehaviour.Properties.copy(GRAY_SHINGLES.get())));
    public static final RegistrySupplier<Block> GRAY_SHINGLES_SLAB = registerBlockItem("gray_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GRAY_SHINGLES.get())));
    public static final RegistrySupplier<Block> BLACK_SHINGLES = registerBlockItem("black_shingles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> BLACK_SHINGLES_STAIRS = registerBlockItem("black_shingles_stairs", () -> new StairBlock(BLACK_SHINGLES.get().defaultBlockState(), BlockBehaviour.Properties.copy(BLACK_SHINGLES.get())));
    public static final RegistrySupplier<Block> BLACK_SHINGLES_SLAB = registerBlockItem("black_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BLACK_SHINGLES.get())));
    public static final RegistrySupplier<Block> BROWN_SHINGLES = registerBlockItem("brown_shingles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> BROWN_SHINGLES_STAIRS = registerBlockItem("brown_shingles_stairs", () -> new StairBlock(BROWN_SHINGLES.get().defaultBlockState(), BlockBehaviour.Properties.copy(BROWN_SHINGLES.get())));
    public static final RegistrySupplier<Block> BROWN_SHINGLES_SLAB = registerBlockItem("brown_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BROWN_SHINGLES.get())));
    public static final RegistrySupplier<Block> RED_SHINGLES = registerBlockItem("red_shingles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> RED_SHINGLES_STAIRS = registerBlockItem("red_shingles_stairs", () -> new StairBlock(RED_SHINGLES.get().defaultBlockState(), BlockBehaviour.Properties.copy(RED_SHINGLES.get())));
    public static final RegistrySupplier<Block> RED_SHINGLES_SLAB = registerBlockItem("red_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(RED_SHINGLES.get())));
    public static final RegistrySupplier<Block> ORANGE_SHINGLES = registerBlockItem("orange_shingles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> ORANGE_SHINGLES_STAIRS = registerBlockItem("orange_shingles_stairs", () -> new StairBlock(ORANGE_SHINGLES.get().defaultBlockState(), BlockBehaviour.Properties.copy(ORANGE_SHINGLES.get())));
    public static final RegistrySupplier<Block> ORANGE_SHINGLES_SLAB = registerBlockItem("orange_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ORANGE_SHINGLES.get())));
    public static final RegistrySupplier<Block> YELLOW_SHINGLES = registerBlockItem("yellow_shingles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> YELLOW_SHINGLES_STAIRS = registerBlockItem("yellow_shingles_stairs", () -> new StairBlock(YELLOW_SHINGLES.get().defaultBlockState(), BlockBehaviour.Properties.copy(YELLOW_SHINGLES.get())));
    public static final RegistrySupplier<Block> YELLOW_SHINGLES_SLAB = registerBlockItem("yellow_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(YELLOW_SHINGLES.get())));
    public static final RegistrySupplier<Block> LIME_SHINGLES = registerBlockItem("lime_shingles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> LIME_SHINGLES_STAIRS = registerBlockItem("lime_shingles_stairs", () -> new StairBlock(LIME_SHINGLES.get().defaultBlockState(), BlockBehaviour.Properties.copy(LIME_SHINGLES.get())));
    public static final RegistrySupplier<Block> LIME_SHINGLES_SLAB = registerBlockItem("lime_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(LIME_SHINGLES.get())));
    public static final RegistrySupplier<Block> GREEN_SHINGLES = registerBlockItem("green_shingles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> GREEN_SHINGLES_STAIRS = registerBlockItem("green_shingles_stairs", () -> new StairBlock(GREEN_SHINGLES.get().defaultBlockState(), BlockBehaviour.Properties.copy(GREEN_SHINGLES.get())));
    public static final RegistrySupplier<Block> GREEN_SHINGLES_SLAB = registerBlockItem("green_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(GREEN_SHINGLES.get())));
    public static final RegistrySupplier<Block> CYAN_SHINGLES = registerBlockItem("cyan_shingles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> CYAN_SHINGLES_STAIRS = registerBlockItem("cyan_shingles_stairs", () -> new StairBlock(CYAN_SHINGLES.get().defaultBlockState(), BlockBehaviour.Properties.copy(CYAN_SHINGLES.get())));
    public static final RegistrySupplier<Block> CYAN_SHINGLES_SLAB = registerBlockItem("cyan_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CYAN_SHINGLES.get())));
    public static final RegistrySupplier<Block> LIGHT_BLUE_SHINGLES = registerBlockItem("light_blue_shingles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> LIGHT_BLUE_SHINGLES_STAIRS = registerBlockItem("light_blue_shingles_stairs", () -> new StairBlock(LIGHT_BLUE_SHINGLES.get().defaultBlockState(), BlockBehaviour.Properties.copy(LIGHT_BLUE_SHINGLES.get())));
    public static final RegistrySupplier<Block> LIGHT_BLUE_SHINGLES_SLAB = registerBlockItem("light_blue_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(LIGHT_BLUE_SHINGLES.get())));
    public static final RegistrySupplier<Block> BLUE_SHINGLES = registerBlockItem("blue_shingles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> BLUE_SHINGLES_STAIRS = registerBlockItem("blue_shingles_stairs", () -> new StairBlock(BLUE_SHINGLES.get().defaultBlockState(), BlockBehaviour.Properties.copy(BLUE_SHINGLES.get())));
    public static final RegistrySupplier<Block> BLUE_SHINGLES_SLAB = registerBlockItem("blue_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(BLUE_SHINGLES.get())));
    public static final RegistrySupplier<Block> PURPLE_SHINGLES = registerBlockItem("purple_shingles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> PURPLE_SHINGLES_STAIRS = registerBlockItem("purple_shingles_stairs", () -> new StairBlock(PURPLE_SHINGLES.get().defaultBlockState(), BlockBehaviour.Properties.copy(PURPLE_SHINGLES.get())));
    public static final RegistrySupplier<Block> PURPLE_SHINGLES_SLAB = registerBlockItem("purple_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(PURPLE_SHINGLES.get())));
    public static final RegistrySupplier<Block> MAGENTA_SHINGLES = registerBlockItem("magenta_shingles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> MAGENTA_SHINGLES_STAIRS = registerBlockItem("magenta_shingles_stairs", () -> new StairBlock(MAGENTA_SHINGLES.get().defaultBlockState(), BlockBehaviour.Properties.copy(MAGENTA_SHINGLES.get())));
    public static final RegistrySupplier<Block> MAGENTA_SHINGLES_SLAB = registerBlockItem("magenta_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MAGENTA_SHINGLES.get())));
    public static final RegistrySupplier<Block> PINK_SHINGLES = registerBlockItem("pink_shingles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<StairBlock> PINK_SHINGLES_STAIRS = registerBlockItem("pink_shingles_stairs", () -> new StairBlock(PINK_SHINGLES.get().defaultBlockState(), BlockBehaviour.Properties.copy(PINK_SHINGLES.get())));
    public static final RegistrySupplier<Block> PINK_SHINGLES_SLAB = registerBlockItem("pink_shingles_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(PINK_SHINGLES.get())));

    public static final RegistrySupplier<Block> COOL_STONE = registerBlockItem("cool_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<StairBlock> COOL_STONE_STAIRS = registerBlockItem("cool_stone_stairs", () -> new StairBlock(COOL_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(COOL_STONE.get())));
    public static final RegistrySupplier<SlabBlock> COOL_STONE_SLAB = registerBlockItem("cool_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(COOL_STONE.get())));
    public static final RegistrySupplier<WallBlock> COOL_STONE_WALL = registerBlockItem("cool_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(COOL_STONE.get())));
    /**
     * Apricorn Trees
     */
    public static final RegistrySupplier<Block> BLACK_APRICORN = registerApricornSapling("black_apricorn", () -> new SaplingBlock(new PokeModTreeGrower(PokeModFeatures.BLACK_APRICORN_TREE), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistrySupplier<Block> BLUE_APRICORN = registerApricornSapling("blue_apricorn", () -> new SaplingBlock(new PokeModTreeGrower(PokeModFeatures.BLUE_APRICORN_TREE), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistrySupplier<Block> GREEN_APRICORN = registerApricornSapling("green_apricorn", () -> new SaplingBlock(new PokeModTreeGrower(PokeModFeatures.GREEN_APRICORN_TREE), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistrySupplier<Block> PINK_APRICORN = registerApricornSapling("pink_apricorn", () -> new SaplingBlock(new PokeModTreeGrower(PokeModFeatures.PINK_APRICORN_TREE), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistrySupplier<Block> RED_APRICORN = registerApricornSapling("red_apricorn", () -> new SaplingBlock(new PokeModTreeGrower(PokeModFeatures.RED_APRICORN_TREE), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistrySupplier<Block> WHITE_APRICORN = registerApricornSapling("white_apricorn", () -> new SaplingBlock(new PokeModTreeGrower(PokeModFeatures.WHITE_APRICORN_TREE), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistrySupplier<Block> YELLOW_APRICORN = registerApricornSapling("yellow_apricorn", () -> new SaplingBlock(new PokeModTreeGrower(PokeModFeatures.YELLOW_APRICORN_TREE), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));

    /**
     * Apricorn Trees Leaves
     */
    public static final RegistrySupplier<Block> BLACK_APRICORN_LEAVES = registerBlockItem("black_apricorn_leaves", GenerationsBlocks::apricornLeaves);
    public static final RegistrySupplier<Block> BLUE_APRICORN_LEAVES = registerBlockItem("blue_apricorn_leaves", GenerationsBlocks::apricornLeaves);
    public static final RegistrySupplier<Block> GREEN_APRICORN_LEAVES = registerBlockItem("green_apricorn_leaves", GenerationsBlocks::apricornLeaves);
    public static final RegistrySupplier<Block> PINK_APRICORN_LEAVES = registerBlockItem("pink_apricorn_leaves", GenerationsBlocks::apricornLeaves);
    public static final RegistrySupplier<Block> RED_APRICORN_LEAVES = registerBlockItem("red_apricorn_leaves", GenerationsBlocks::apricornLeaves);
    public static final RegistrySupplier<Block> WHITE_APRICORN_LEAVES = registerBlockItem("white_apricorn_leaves", GenerationsBlocks::apricornLeaves);
    public static final RegistrySupplier<Block> YELLOW_APRICORN_LEAVES = registerBlockItem("yellow_apricorn_leaves", GenerationsBlocks::apricornLeaves);


    /**
     * 1 Block Tall Ballonlea Mushrooms
     */
    public static final RegistrySupplier<PokeModMushroomBlock> BALLONLEA_BLUE_MUSHROOM = registerBlockItem("ballonlea_blue_mushroom", () -> new PokeModMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), PokeModFeatures.BALLONLEA_BLUE_MUSHROOM));
    public static final RegistrySupplier<PokeModMushroomBlock> BALLONLEA_GREEN_MUSHROOM = registerBlockItem("ballonlea_green_mushroom", () -> new PokeModMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), PokeModFeatures.BALLONLEA_GREEN_MUSHROOM));
    public static final RegistrySupplier<PokeModMushroomBlock> BALLONLEA_PINK_MUSHROOM = registerBlockItem("ballonlea_pink_mushroom", () -> new PokeModMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), PokeModFeatures.BALLONLEA_PINK_MUSHROOM));
    public static final RegistrySupplier<PokeModMushroomBlock> BALLONLEA_YELLOW_MUSHROOM = registerBlockItem("ballonlea_yellow_mushroom", () -> new PokeModMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), PokeModFeatures.BALLONLEA_YELLOW_MUSHROOM));

    public static final RegistrySupplier<PokeModMushroomBlock> GROUP_BALLONLEA_BLUE_MUSHROOM = registerBlockItem("group_ballonlea_blue_mushroom", () -> new PokeModMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), PokeModFeatures.GROUP_BALLONLEA_BLUE_MUSHROOM));
    public static final RegistrySupplier<PokeModMushroomBlock> GROUP_BALLONLEA_GREEN_MUSHROOM = registerBlockItem("group_ballonlea_green_mushroom", () -> new PokeModMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), PokeModFeatures.GROUP_BALLONLEA_GREEN_MUSHROOM));
    public static final RegistrySupplier<PokeModMushroomBlock> GROUP_BALLONLEA_PINK_MUSHROOM = registerBlockItem("group_ballonlea_pink_mushroom", () -> new PokeModMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), PokeModFeatures.GROUP_BALLONLEA_PINK_MUSHROOM));
    public static final RegistrySupplier<PokeModMushroomBlock> GROUP_BALLONLEA_YELLOW_MUSHROOM = registerBlockItem("group_ballonlea_yellow_mushroom", () -> new PokeModMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), PokeModFeatures.GROUP_BALLONLEA_YELLOW_MUSHROOM));

    public static final RegistrySupplier<PokeModMushroomBlock> TALL_BALLONLEA_BLUE_MUSHROOM = registerBlockItem("tall_ballonlea_blue_mushroom", () -> new PokeModMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), PokeModFeatures.TALL_BALLONLEA_BLUE_MUSHROOM));
    public static final RegistrySupplier<PokeModMushroomBlock> TALL_BALLONLEA_GREEN_MUSHROOM = registerBlockItem("tall_ballonlea_green_mushroom", () -> new PokeModMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), PokeModFeatures.TALL_BALLONLEA_GREEN_MUSHROOM));
    public static final RegistrySupplier<PokeModMushroomBlock> TALL_BALLONLEA_PINK_MUSHROOM = registerBlockItem("tall_ballonlea_pink_mushroom", () -> new PokeModMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), PokeModFeatures.TALL_BALLONLEA_PINK_MUSHROOM));
    public static final RegistrySupplier<PokeModMushroomBlock> TALL_BALLONLEA_YELLOW_MUSHROOM = registerBlockItem("tall_ballonlea_yellow_mushroom", () -> new PokeModMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), PokeModFeatures.TALL_BALLONLEA_YELLOW_MUSHROOM));

    public static final RegistrySupplier<PokeModMushroomBlock> DOUBLE_BALLONLEA_BLUE_MUSHROOM = registerBlockItem("double_ballonlea_blue_mushroom", () -> new PokeModMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), PokeModFeatures.DOUBLE_BALLONLEA_BLUE_MUSHROOM));
    public static final RegistrySupplier<PokeModMushroomBlock> DOUBLE_BALLONLEA_GREEN_MUSHROOM = registerBlockItem("double_ballonlea_green_mushroom", () -> new PokeModMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), PokeModFeatures.DOUBLE_BALLONLEA_GREEN_MUSHROOM));
    public static final RegistrySupplier<PokeModMushroomBlock> DOUBLE_BALLONLEA_PINK_MUSHROOM = registerBlockItem("double_ballonlea_pink_mushroom", () -> new PokeModMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), PokeModFeatures.DOUBLE_BALLONLEA_PINK_MUSHROOM));
    public static final RegistrySupplier<PokeModMushroomBlock> DOUBLE_BALLONLEA_YELLOW_MUSHROOM = registerBlockItem("double_ballonlea_yellow_mushroom", () -> new PokeModMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).dynamicShape().lightLevel(value -> 6), PokeModFeatures.DOUBLE_BALLONLEA_YELLOW_MUSHROOM));

    /**
     * PokeBall Chests
     **/
    public static final RegistrySupplier<GenericChestBlock> POKEBALL_CHEST = registerChestBlockItem("pokeball_chest", () -> new GenericChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST),  9, 4, "pokeball_chest"));
    public static final RegistrySupplier<GenericChestBlock> GREATBALL_CHEST = registerChestBlockItem("greatball_chest", () -> new GenericChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST),  9, 5, "greatball_chest"));
    public static final RegistrySupplier<GenericChestBlock> ULTRABALL_CHEST = registerChestBlockItem("ultraball_chest", () -> new GenericChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST),  9, 6, "ultraball_chest"));
    public static final RegistrySupplier<GenericChestBlock> MASTERBALL_CHEST = registerChestBlockItem("masterball_chest", () -> new GenericChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST),  12, 8, "masterball_chest"));

    /**
     * Berry Bushes
     */
    public static final RegistrySupplier<Block> AGUAV_BERRY_BUSH = createBerryBush(BerryType.AGUAV);
    public static final RegistrySupplier<Block> APICOT_BERRY_BUSH = createBerryBush(BerryType.APICOT);
    public static final RegistrySupplier<Block> ASPEAR_BERRY_BUSH = createBerryBush(BerryType.ASPEAR);
    public static final RegistrySupplier<Block> BABIRI_BERRY_BUSH = createBerryBush(BerryType.BABIRI);
    public static final RegistrySupplier<Block> BELUE_BERRY_BUSH = createBerryBush(BerryType.BELUE);
    public static final RegistrySupplier<Block> BLUK_BERRY_BUSH = createBerryBush(BerryType.BLUK);
    public static final RegistrySupplier<Block> CHARTI_BERRY_BUSH = createBerryBush(BerryType.CHARTI);
    public static final RegistrySupplier<Block> CHERI_BERRY_BUSH = createBerryBush(BerryType.CHERI);
    public static final RegistrySupplier<Block> CHESTO_BERRY_BUSH = createBerryBush(BerryType.CHESTO);
    public static final RegistrySupplier<Block> CHILAN_BERRY_BUSH = createBerryBush(BerryType.CHILAN);
    public static final RegistrySupplier<Block> CHOPLE_BERRY_BUSH = createBerryBush(BerryType.CHOPLE);
    public static final RegistrySupplier<Block> COBA_BERRY_BUSH = createBerryBush(BerryType.COBA);
    public static final RegistrySupplier<Block> COLBUR_BERRY_BUSH = createBerryBush(BerryType.COLBUR);
    public static final RegistrySupplier<Block> CORNN_BERRY_BUSH = createBerryBush(BerryType.CORNN);
    public static final RegistrySupplier<Block> CUSTAP_BERRY_BUSH = createBerryBush(BerryType.CUSTAP);
    public static final RegistrySupplier<Block> DRASH_BERRY_BUSH = createBerryBush(BerryType.DRASH);
    public static final RegistrySupplier<Block> DURIN_BERRY_BUSH = createBerryBush(BerryType.DURIN);
    public static final RegistrySupplier<Block> EGGANT_BERRY_BUSH = createBerryBush(BerryType.EGGANT);
    public static final RegistrySupplier<Block> ENIGMA_BERRY_BUSH = createBerryBush(BerryType.ENIGMA);
    public static final RegistrySupplier<Block> FIGY_BERRY_BUSH = createBerryBush(BerryType.FIGY);
    public static final RegistrySupplier<Block> GANLON_BERRY_BUSH = createBerryBush(BerryType.GANLON);
    public static final RegistrySupplier<Block> GINEMA_BERRY_BUSH = createBerryBush(BerryType.GINEMA);
    public static final RegistrySupplier<Block> GREPA_BERRY_BUSH = createBerryBush(BerryType.GREPA);
    public static final RegistrySupplier<Block> HABAN_BERRY_BUSH = createBerryBush(BerryType.HABAN);
    public static final RegistrySupplier<Block> HONDEW_BERRY_BUSH = createBerryBush(BerryType.HONDEW);
    public static final RegistrySupplier<Block> IAPAPA_BERRY_BUSH = createBerryBush(BerryType.IAPAPA);
    public static final RegistrySupplier<Block> JABOCA_BERRY_BUSH = createBerryBush(BerryType.JABOCA);
    public static final RegistrySupplier<Block> KASIB_BERRY_BUSH = createBerryBush(BerryType.KASIB);
    public static final RegistrySupplier<Block> KEBIA_BERRY_BUSH = createBerryBush(BerryType.KEBIA);
    public static final RegistrySupplier<Block> KEE_BERRY_BUSH = createBerryBush(BerryType.KEE);
    public static final RegistrySupplier<Block> KELPSY_BERRY_BUSH = createBerryBush(BerryType.KELPSY);
    public static final RegistrySupplier<Block> KUO_BERRY_BUSH = createBerryBush(BerryType.KUO);
    public static final RegistrySupplier<Block> LANSAT_BERRY_BUSH = createBerryBush(BerryType.LANSAT);
    public static final RegistrySupplier<Block> LEPPA_BERRY_BUSH = createBerryBush(BerryType.LEPPA);
    public static final RegistrySupplier<Block> LIECHI_BERRY_BUSH = createBerryBush(BerryType.LIECHI);
    public static final RegistrySupplier<Block> LUM_BERRY_BUSH = createBerryBush(BerryType.LUM);
    public static final RegistrySupplier<Block> MAGO_BERRY_BUSH = createBerryBush(BerryType.MAGO);
    public static final RegistrySupplier<Block> MAGOST_BERRY_BUSH = createBerryBush(BerryType.MAGOST);
    public static final RegistrySupplier<Block> MARANGA_BERRY_BUSH = createBerryBush(BerryType.MARANGA);
    public static final RegistrySupplier<Block> MICLE_BERRY_BUSH = createBerryBush(BerryType.MICLE);
    public static final RegistrySupplier<Block> NANAB_BERRY_BUSH = createBerryBush(BerryType.NANAB);
    public static final RegistrySupplier<Block> NINIKU_BERRY_BUSH = createBerryBush(BerryType.NINIKU);
    public static final RegistrySupplier<Block> NOMEL_BERRY_BUSH = createBerryBush(BerryType.NOMEL);
    public static final RegistrySupplier<Block> NUTPEA_BERRY_BUSH = createBerryBush(BerryType.NUTPEA);
    public static final RegistrySupplier<Block> OCCA_BERRY_BUSH = createBerryBush(BerryType.OCCA);
    public static final RegistrySupplier<Block> ORAN_BERRY_BUSH = createBerryBush(BerryType.ORAN);
    public static final RegistrySupplier<Block> PAMTRE_BERRY_BUSH = createBerryBush(BerryType.PAMTRE);
    public static final RegistrySupplier<Block> PASSHO_BERRY_BUSH = createBerryBush(BerryType.PASSHO);
    public static final RegistrySupplier<Block> PAYAPA_BERRY_BUSH = createBerryBush(BerryType.PAYAPA);
    public static final RegistrySupplier<Block> PECHA_BERRY_BUSH = createBerryBush(BerryType.PECHA);
    public static final RegistrySupplier<Block> PERSIM_BERRY_BUSH = createBerryBush(BerryType.PERSIM);
    public static final RegistrySupplier<Block> PETAYA_BERRY_BUSH = createBerryBush(BerryType.PETAYA);
    public static final RegistrySupplier<Block> PINAP_BERRY_BUSH = createBerryBush(BerryType.PINAP);
    public static final RegistrySupplier<Block> POMEG_BERRY_BUSH = createBerryBush(BerryType.POMEG);
    public static final RegistrySupplier<Block> PUMKIN_BERRY_BUSH = createBerryBush(BerryType.PUMKIN);
    public static final RegistrySupplier<Block> QUALOT_BERRY_BUSH = createBerryBush(BerryType.QUALOT);
    public static final RegistrySupplier<Block> RABUTA_BERRY_BUSH = createBerryBush(BerryType.RABUTA);
    public static final RegistrySupplier<Block> RAWST_BERRY_BUSH = createBerryBush(BerryType.RAWST);
    public static final RegistrySupplier<Block> RAZZ_BERRY_BUSH = createBerryBush(BerryType.BAZZ);
    public static final RegistrySupplier<Block> RINDO_BERRY_BUSH = createBerryBush(BerryType.RINDO);
    public static final RegistrySupplier<Block> ROSELI_BERRY_BUSH = createBerryBush(BerryType.ROSELI);
    public static final RegistrySupplier<Block> ROWAP_BERRY_BUSH = createBerryBush(BerryType.ROWAP);
    public static final RegistrySupplier<Block> SALAC_BERRY_BUSH = createBerryBush(BerryType.SALAC);
    public static final RegistrySupplier<Block> SHUCA_BERRY_BUSH = createBerryBush(BerryType.SHUCA);
    public static final RegistrySupplier<Block> SITRUS_BERRY_BUSH = createBerryBush(BerryType.SITRUS);
    public static final RegistrySupplier<Block> SPELON_BERRY_BUSH = createBerryBush(BerryType.SPELON);
    public static final RegistrySupplier<Block> STARF_BERRY_BUSH = createBerryBush(BerryType.STARF);
    public static final RegistrySupplier<Block> STRIB_BERRY_BUSH = createBerryBush(BerryType.STRIB);
    public static final RegistrySupplier<Block> TAMATO_BERRY_BUSH = createBerryBush(BerryType.TAMATO);
    public static final RegistrySupplier<Block> TANGA_BERRY_BUSH = createBerryBush(BerryType.TANGA);
    public static final RegistrySupplier<Block> TOPO_BERRY_BUSH = createBerryBush(BerryType.TOPO);
    public static final RegistrySupplier<Block> TOUGA_BERRY_BUSH = createBerryBush(BerryType.TOUGA);
    public static final RegistrySupplier<Block> WACAN_BERRY_BUSH = createBerryBush(BerryType.WACAN);
    public static final RegistrySupplier<Block> WATMEL_BERRY_BUSH = createBerryBush(BerryType.WATMEL);
    public static final RegistrySupplier<Block> WEPEAR_BERRY_BUSH = createBerryBush(BerryType.WEPEAR);
    public static final RegistrySupplier<Block> WIKI_BERRY_BUSH = createBerryBush(BerryType.WIKI);
    public static final RegistrySupplier<Block> YACHE_BERRY_BUSH = createBerryBush(BerryType.YACHE);
    public static final RegistrySupplier<Block> YAGO_BERRY_BUSH = createBerryBush(BerryType.YAGO);



    private static Block apricornLeaves() {
        return new LeavesBlock(
                BlockBehaviour.Properties
                        .of(Material.LEAVES)
                        .strength(0.2f)
                        .randomTicks()
                        .sound(SoundType.GRASS)
                        .noOcclusion()
                        .isValidSpawn(Blocks::ocelotOrParrot)
                        .isSuffocating(Blocks::never)
                        .isViewBlocking(Blocks::never));
    }

    private static RegistrySupplier<Block> createBerryBush(BerryType berryType) {
        return BLOCKS.register(berryType.name().toLowerCase(Locale.ENGLISH) + "_berry_bush", () -> new PokeModBerryBushBlock(berryType, BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH)));
    }

    private static <T extends Block> RegistrySupplier<T> registerApricornSapling(String name, Supplier<T> blockSupplier) {
        RegistrySupplier<T> block = BLOCKS.register(name, blockSupplier);
        register(name, properties -> new ItemNameBlockItem(block.get(), properties));
        return block;
    }
    
    private static <T extends Block> RegistrySupplier<T> registerBlockItem(String name, Supplier<T> blockSupplier) {
        RegistrySupplier<T> block = BLOCKS.register(name, blockSupplier);
        register(name, properties -> new BlockItem(block.get(), properties));
        return block;
    }

    private static <T extends GenericChestBlock> RegistrySupplier<T> registerChestBlockItem(String name, Supplier<T> blockSupplier) {
        RegistrySupplier<T> block = BLOCKS.register(name, blockSupplier);
        register(name, properties -> new GenericChestBlockItem(block.get(), properties));
        return block;
    }

    private static void register(String name, Function<Item.Properties, Item> itemSupplier) {
        GenerationsItems.ITEMS.register(name, () -> itemSupplier.apply(new Item.Properties().arch$tab(GenerationsCreativeTabs.BUILDING_BLOCKS)));
    }

//    public static void onInitialize(IEventBus eventBus) {
//        PokeMod.LOGGER.info("Registering PokeMod Blocks");
//        PokeMod.LOGGER.info("Registering PokeMod PokeBricks");
//        PokeMod.LOGGER.info("Registering PokeMod Marble");
//        PokeMod.LOGGER.info("Registering PokeMod Charge and Volocanic Stone");
//        PokeMod.LOGGER.info("Registering PokeMod Ultra Blocks");
//    }
    public static void init() {
        GenerationsCore.LOGGER.info("Registering PokeMod Blocks");
        BLOCKS.register();
        BLOCKS_WITHOUT_ITEMS.register();

        for (RegistrySupplier<Block> block : BLOCKS)
            ITEMS.register(block.getId(),
                    () -> new BlockItem(block.get(), new Item.Properties().arch$tab((CreativeModeTab) null)));


        ITEMS.register();
    }



}
