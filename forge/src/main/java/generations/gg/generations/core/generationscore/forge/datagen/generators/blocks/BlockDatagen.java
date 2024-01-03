package generations.gg.generations.core.generationscore.forge.datagen.generators.blocks;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.forge.datagen.data.families.GenerationsBlockFamilies;
import generations.gg.generations.core.generationscore.world.level.block.*;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericChestBlock;
import generations.gg.generations.core.generationscore.world.level.block.set.GenerationsBlockSet;
import generations.gg.generations.core.generationscore.world.level.block.set.GenerationsFullBlockSet;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.DataProvider;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class BlockDatagen extends GenerationsBlockStateProvider.Proxied {
    public static final ArrayList<Object> dropSelfList = new ArrayList<>();
    public static final ArrayList<GenerationsMushroomBlock> MUSHROOM_BLOCKS = new ArrayList<>();


    public BlockDatagen(GenerationsBlockStateProvider provider) {
        super(provider);
    }

    public void registerStatesAndModels() {
        registerOreBlocks();
        GenerationsBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateModel).forEach(this::registerBlockFamily);
        GenerationsBlockSet.getBlockSets().forEach(
                blockSet -> {
                    BlockFamily family = blockSet.getBlockFamily();
                    if (family.shouldGenerateModel()) registerBlockFamily(family);
                }
        );
        GenerationsFullBlockSet.getFullBlockSets().forEach(
                blockSet -> {
                    BlockFamily family = blockSet.getBlockFamily();
                    if (family.shouldGenerateModel()) registerBlockFamily(family);
                }
        );
        registerWoodPallet(GenerationsWood.ULTRA_DARK_LOG, GenerationsWood.STRIPPED_ULTRA_DARK_LOG, GenerationsWood.ULTRA_DARK_PLANKS, GenerationsWood.ULTRA_DARK_SLAB, GenerationsWood.ULTRA_DARK_STAIRS, GenerationsWood.ULTRA_DARK_BUTTON, GenerationsWood.ULTRA_DARK_PRESSURE_PLATE, GenerationsWood.ULTRA_DARK_DOOR, GenerationsWood.ULTRA_DARK_TRAPDOOR, GenerationsWood.ULTRA_DARK_WOOD, GenerationsWood.STRIPPED_ULTRA_DARK_WOOD, GenerationsWood.ULTRA_DARK_FENCE, GenerationsWood.ULTRA_DARK_FENCE_GATE, GenerationsWood.ULTRA_DARK_SIGN, GenerationsWood.ULTRA_DARK_WALL_SIGN, GenerationsWood.ULTRA_DARK_CRAFTING_TABLE, GenerationsWood.ULTRA_DARK_HANGING_SIGN, GenerationsWood.ULTRA_DARK_WALL_HANGING_SIGN, GenerationsWood.ULTRA_DARK_BOOKSHELF);
        registerWoodPallet(GenerationsWood.ULTRA_JUNGLE_LOG, GenerationsWood.STRIPPED_ULTRA_JUNGLE_LOG, GenerationsWood.ULTRA_JUNGLE_PLANKS, GenerationsWood.ULTRA_JUNGLE_SLAB, GenerationsWood.ULTRA_JUNGLE_STAIRS, GenerationsWood.ULTRA_JUNGLE_BUTTON, GenerationsWood.ULTRA_JUNGLE_PRESSURE_PLATE, GenerationsWood.ULTRA_JUNGLE_DOOR, GenerationsWood.ULTRA_JUNGLE_TRAPDOOR, GenerationsWood.ULTRA_JUNGLE_WOOD, GenerationsWood.STRIPPED_ULTRA_JUNGLE_WOOD, GenerationsWood.ULTRA_JUNGLE_FENCE, GenerationsWood.ULTRA_JUNGLE_FENCE_GATE, GenerationsWood.ULTRA_JUNGLE_SIGN, GenerationsWood.ULTRA_JUNGLE_WALL_SIGN, GenerationsWood.ULTRA_JUNGLE_CRAFTING_TABLE, GenerationsWood.ULTRA_JUNGLE_HANGING_SIGN, GenerationsWood.ULTRA_JUNGLE_WALL_HANGING_SIGN, GenerationsWood.ULTRA_JUNGLE_BOOKSHELF);
        registerWoodPallet(GenerationsWood.GHOST_LOG, GenerationsWood.STRIPPED_GHOST_LOG, GenerationsWood.GHOST_PLANKS, GenerationsWood.GHOST_SLAB, GenerationsWood.GHOST_STAIRS, GenerationsWood.GHOST_BUTTON, GenerationsWood.GHOST_PRESSURE_PLATE, GenerationsWood.GHOST_DOOR, GenerationsWood.GHOST_TRAPDOOR, GenerationsWood.GHOST_WOOD, GenerationsWood.STRIPPED_GHOST_WOOD, GenerationsWood.GHOST_FENCE, GenerationsWood.GHOST_FENCE_GATE, GenerationsWood.GHOST_SIGN, GenerationsWood.GHOST_WALL_SIGN, GenerationsWood.GHOST_CRAFTING_TABLE, GenerationsWood.GHOST_HANGING_SIGN, GenerationsWood.GHOST_WALL_HANGING_SIGN, GenerationsWood.GHOST_BOOKSHELF);

        //House Floors
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_1);
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_2);
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_3);
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_4);
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_5);
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_6);
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_7);
        registerBlockItem(GenerationsBlocks.HOUSE_FLOOR_8);

        //ChargeStone
        registerBlockItem(GenerationsBlocks.CHARGE_DRIPSTONE_BLOCK);

        //Volcanic
        registerBlockItem(GenerationsBlocks.VOLCANIC_FIRESTONE);

        //Golden Temple
        registerSandStonePallet(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE, GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_SLAB, GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_STAIRS, GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_WALL, GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE, GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_SLAB, GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_STAIRS, GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_WALL, GenerationsBlocks.GOLDEN_TEMPLE_CHISELED_SANDSTONE, GenerationsBlocks.GOLDEN_TEMPLE_CUT_SANDSTONE, GenerationsBlocks.GOLDEN_TEMPLE_CUT_SANDSTONE_SLAB);

        registerPallet(GenerationsBlocks.GOLDEN_TEMPLE_DARK_PRISMARINE, GenerationsBlocks.GOLDEN_TEMPLE_DARK_PRISMARINE_SLAB, GenerationsBlocks.GOLDEN_TEMPLE_DARK_PRISMARINE_STAIRS, GenerationsBlocks.GOLDEN_TEMPLE_DARK_PRISMARINE_WALL, null, null, true);
        registerPallet(GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE, GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_SLAB, GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_STAIRS, GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_WALL, null, null, true);
        registerPallet(GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_BRICKS, GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_BRICK_SLAB, GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_BRICK_STAIRS, GenerationsBlocks.GOLDEN_TEMPLE_PRISMARINE_BRICK_WALL, null, null, true);

        //Subway
        registerPallet(GenerationsBlocks.SUBWAY_WALL, GenerationsBlocks.SUBWAY_WALL_SLAB, GenerationsBlocks.SUBWAY_WALL_STAIRS, null, null, null, true);
        registerPallet(GenerationsBlocks.SUBWAY_WALL_2, GenerationsBlocks.SUBWAY_WALL_2_SLAB, GenerationsBlocks.SUBWAY_WALL_2_STAIRS, null, null, null, true);

        registerBlockItem(GenerationsBlocks.POKEMART_SIGN);
        registerBlockItem(GenerationsBlocks.POKECENTER_SIGN);

        registerBlockItem(GenerationsBlocks.RUINS_WALL);
        registerBlockItem(GenerationsBlocks.DUSTY_RUINS_WALL);

        registerBlockItem(GenerationsBlocks.CASTLE_PILLAR);
        registerBlockItem(GenerationsBlocks.BROKEN_CASTLE_PILLAR);

        registerBlockItem(GenerationsBlocks.PRISMARINE_PILLAR);
        registerBlockItem(GenerationsBlocks.BROKEN_PRISMARINE_PILLAR);

        registerBlockItem(GenerationsBlocks.DARK_PRISMARINE_PILLAR);
        registerBlockItem(GenerationsBlocks.BROKEN_DARK_PRISMARINE_PILLAR);

        registerBlockItem(GenerationsBlocks.ICE_PILLAR);
        registerBlockItem(GenerationsBlocks.BROKEN_ICE_PILLAR);

        registerBlockItem(GenerationsBlocks.HAUNTED_PILLAR);
        registerBlockItem(GenerationsBlocks.BROKEN_HAUNTED_PILLAR);

        registerBlockItem(GenerationsBlocks.DAWN_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.DUSK_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.FIRE_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.ICE_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.LEAF_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.MOON_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.SHINY_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.SUN_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.THUNDER_STONE_BLOCK);
        registerBlockItem(GenerationsBlocks.WATER_STONE_BLOCK);

        unownBlock(GenerationsBlocks.UNOWN_BLOCK_A);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_B);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_C);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_D);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_E);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_F);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_G);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_H);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_I);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_J);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_K);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_L);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_M);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_N);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_O);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_P);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_Q);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_R);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_S);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_T);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_U);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_V);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_W);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_X);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_Y);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_Z);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_EXCLAMATION_MARK);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_QUESTION_MARK);
        unownBlock(GenerationsBlocks.UNOWN_BLOCK_BLANK);

        registerBlockItem(GenerationsBlocks.ULTRA_SAND);

        registerBlockItem(GenerationsBlocks.CRATE);

        registerBlockItem(GenerationsBlocks.GOLDEN_TEMPLE_SAND);

        registerPallet(GenerationsBlocks.RUBY_BLOCK, GenerationsBlocks.RUBY_SLAB, GenerationsBlocks.RUBY_STAIRS, GenerationsBlocks.RUBY_WALL, null, null, true);
        registerPallet(GenerationsBlocks.SAPPHIRE_BLOCK, GenerationsBlocks.SAPPHIRE_SLAB, GenerationsBlocks.SAPPHIRE_STAIRS, GenerationsBlocks.SAPPHIRE_WALL, null, null, true);
        registerPallet(GenerationsBlocks.CRYSTAL_BLOCK, GenerationsBlocks.CRYSTAL_SLAB, GenerationsBlocks.CRYSTAL_STAIRS, GenerationsBlocks.CRYSTAL_WALL, null, null, true);
        registerBlockItem(GenerationsBlocks.CRYSTAL_LIGHT);
        registerBlockItem(GenerationsBlocks.SILICON_BLOCK);


        registerCrossBlock(GenerationsBlocks.BALLONLEA_BLUE_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.BALLONLEA_GREEN_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.BALLONLEA_PINK_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.BALLONLEA_YELLOW_MUSHROOM);

        registerCrossBlock(GenerationsBlocks.GROUP_BALLONLEA_BLUE_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.GROUP_BALLONLEA_GREEN_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.GROUP_BALLONLEA_PINK_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.GROUP_BALLONLEA_YELLOW_MUSHROOM);

        registerCrossBlock(GenerationsBlocks.TALL_BALLONLEA_BLUE_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.TALL_BALLONLEA_GREEN_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.TALL_BALLONLEA_PINK_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.TALL_BALLONLEA_YELLOW_MUSHROOM);

        registerCrossBlock(GenerationsBlocks.DOUBLE_BALLONLEA_BLUE_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.DOUBLE_BALLONLEA_GREEN_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.DOUBLE_BALLONLEA_PINK_MUSHROOM);
        registerCrossBlock(GenerationsBlocks.DOUBLE_BALLONLEA_YELLOW_MUSHROOM);


        //Ultra Space
        registerSandStonePallet(GenerationsBlocks.ULTRA_SANDSTONE, GenerationsBlocks.ULTRA_SANDSTONE_SLAB, GenerationsBlocks.ULTRA_SANDSTONE_STAIRS, GenerationsBlocks.ULTRA_SANDSTONE_WALL, GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE, GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_SLAB, GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_STAIRS, GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_WALL, GenerationsBlocks.ULTRA_CHISELED_SANDSTONE, GenerationsBlocks.ULTRA_CUT_SANDSTONE, GenerationsBlocks.ULTRA_CUT_SANDSTONE_SLAB);
        registerBlockItem(GenerationsBlocks.GHOST_LANTERN);
        registerBlockItem(GenerationsBlocks.MACHINE_BLOCK);
        registerBlockItem(GenerationsBlocks.RUINS_SAND);
        registerBlockItem(GenerationsBlocks.BURST_TURF);

        registerBlockItem(GenerationsBlocks.WARNING_BLOCK);

        registerPumpkin(GenerationsBlocks.CURSED_PUMPKIN);
        registerCarvedPumpkin(GenerationsBlocks.CURSED_CARVED_PUMPKIN, GenerationsBlocks.CURSED_PUMPKIN);
        registerCarvedPumpkin(GenerationsBlocks.CURSED_JACK_O_LANTERN, GenerationsBlocks.CURSED_PUMPKIN);

        registerDoor(GenerationsBlocks.POKECENTER_DOOR.get());


        pokeBallChests(GenerationsBlocks.POKEBALL_CHEST, "poke_ball");
        pokeBallChests(GenerationsBlocks.GREATBALL_CHEST, "great_ball");
        pokeBallChests(GenerationsBlocks.ULTRABALL_CHEST, "ultra_ball");
        pokeBallChests(GenerationsBlocks.MASTERBALL_CHEST, "master_ball");

        //Furnaces
        registerFurnace(GenerationsUtilityBlocks.CHARGE_STONE_FURNACE);
        registerFurnace(GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE);
        registerFurnace(GenerationsUtilityBlocks.CHARGE_STONE_SMOKER);
        registerFurnace(GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE);
        registerFurnace(GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE);
        registerFurnace(GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER);

        registerBlockItem(GenerationsBlocks.RICH_SOIL_1);
        registerBlockItem(GenerationsBlocks.RICH_SOIL_2);
        registerBlockItem(GenerationsBlocks.RICH_SOIL_3);
        registerBlockItem(GenerationsBlocks.RICH_SOIL_4);

        registerLog(GenerationsBlocks.GHOST_PILLAR);

        GenerationsPokeDolls.POKEDOLLS.forEach(this::registerPokeDoll);

        registerNoModel(GenerationsDecorationBlocks.VENDING_MACHINE.block());
        registerNoModel(GenerationsDecorationBlocks.PASTEL_BEAN_BAG.block());
        GenerationsDecorationBlocks.BALL_DISPLAY_BLOCKS.forEach(block -> registerBlockItemParticle(block.get(), "ball_displays"));


        registerBlockItemParticle(GenerationsBlocks.POKECENTER_SCARLET_SIGN.get(), "sign");


        GenerationsShrines.SHRINES.forEach(block -> registerBlockItemParticle(block.get(), "shrines"));
        GenerationsUtilityBlocks.BALL_LOOTS.forEach(block -> registerBlockItemParticle(block.get(), "ball_loots"));
        registerBlockItemParticle(GenerationsUtilityBlocks.TRASH_CAN.get(), "utility_blocks");
        registerBlockItemParticle(GenerationsUtilityBlocks.COOKING_POT.get(), "utility_blocks");
        registerNoModel(GenerationsUtilityBlocks.ROTOM_PC);
        registerNoModel(GenerationsUtilityBlocks.TABLE_PC);
        registerNoModel(GenerationsUtilityBlocks.CLOCK.block());
        registerNoModel(GenerationsUtilityBlocks.HEALER.block());

        registerBlockItemParticle(GenerationsDecorationBlocks.HOUSE_LAMP.get(), "decorations");
        registerBlockItemParticle(GenerationsDecorationBlocks.SWITCH.get(), "decorations");
        registerBlockItemParticle(GenerationsDecorationBlocks.LITWICK_CANDLE.get(), "decorations");
        registerBlockItemParticle(GenerationsDecorationBlocks.LITWICK_CANDLES.get(), "decorations");
        registerBlockItemParticle(GenerationsDecorationBlocks.SNORLAX_BEAN_BAG.get(), "decorations");
        registerBlockItemParticle(GenerationsUtilityBlocks.RKS_MACHINE.get(), "utility_blocks");
        registerBlockItemParticle(GenerationsUtilityBlocks.SCARECROW.get(), "utility_blocks");


        registerInfestedBlock(GenerationsBlocks.INFESTED_CHARGE_STONE);
        registerInfestedBlock(GenerationsBlocks.INFESTED_VOLCANIC_STONE);
        registerInfestedBlock(GenerationsBlocks.INFESTED_CHARGE_COBBLESTONE);
        registerInfestedBlock(GenerationsBlocks.INFESTED_VOLCANIC_COBBLESTONE);
        registerInfestedBlock(GenerationsBlocks.INFESTED_CHARGE_STONE_BRICKS);
        registerInfestedBlock(GenerationsBlocks.INFESTED_VOLCANIC_STONE_BRICKS);
        registerInfestedBlock(GenerationsBlocks.INFESTED_MOSSY_CHARGE_STONE_BRICKS);
        registerInfestedBlock(GenerationsBlocks.INFESTED_MOSSY_VOLCANIC_STONE_BRICKS);
        registerInfestedBlock(GenerationsBlocks.INFESTED_CRACKED_CHARGE_STONE_BRICKS);
        registerInfestedBlock(GenerationsBlocks.INFESTED_CRACKED_VOLCANIC_STONE_BRICKS);
        registerInfestedBlock(GenerationsBlocks.INFESTED_CHISELED_CHARGE_STONE_BRICKS);
        registerInfestedBlock(GenerationsBlocks.INFESTED_CHISELED_VOLCANIC_STONE_BRICKS);

        registerDripStone(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE);
    }

    private <T extends Block> void registerNoModel( RegistrySupplier<T> block) {
        this.simpleBlock(block.get(), ConfiguredModel.builder().modelFile(models().getBuilder(block.getId().getPath())).build());
    }

    private void registerPallet(@NotNull RegistrySupplier<Block> block, RegistrySupplier<SlabBlock> slab, RegistrySupplier<StairBlock> stairs, RegistrySupplier<WallBlock> wall, RegistrySupplier<ButtonBlock> button, RegistrySupplier<PressurePlateBlock> pressurePlate, boolean dropSelf){
        if(!registered(block.get())) registerBlockItem(block);
        if (dropSelf) dropSelfList.add(block.get());

        if (slab != null) {
            if(!registered(slab.get())) registerSlab(slab.get(), block.get());
            if (dropSelf) dropSelfList.add(slab.get());
        }
        if (stairs != null) {
            if(!registered(stairs.get())) registerStairs(stairs.get(), block.get());
            if (dropSelf) dropSelfList.add(stairs.get());
        }
        if (wall != null) {
            if(!registered(wall.get())) registerWall(wall.get(), block.get());
            if (dropSelf) dropSelfList.add(wall.get());
        }
        if (button != null) {
            if(!registered(button.get())) registerButton(button.get(), block.get());
            if (dropSelf) dropSelfList.add(button.get());
        }
        if (pressurePlate != null) {
            if(!registered(pressurePlate.get())) registerPressurePlate(pressurePlate.get(), block.get());
            if (dropSelf) dropSelfList.add(pressurePlate.get());
        }
    }


    private void registerSandStonePallet(@NotNull RegistrySupplier<Block> sandstone, RegistrySupplier<SlabBlock> slab, RegistrySupplier<StairBlock> stairs, RegistrySupplier<WallBlock> wall, RegistrySupplier<Block> smooth, RegistrySupplier<SlabBlock> smoothslab, RegistrySupplier<StairBlock> smoothstair, RegistrySupplier<WallBlock> smoothWall, RegistrySupplier<Block> chiseled, RegistrySupplier<Block> cutSandstone, RegistrySupplier<SlabBlock> cutSlab){
        if(!registered(sandstone.get())) registerSandStone(sandstone);
        dropSelfList.add(sandstone.get());
        if (slab != null) {
            if(!registered(slab.get())) registerSandStoneSlab(slab, sandstone);
            dropSelfList.add(slab.get());
        }
        if (stairs != null) {
            if(!registered(stairs.get())) registerSandStoneStairs(stairs, sandstone);
            dropSelfList.add(stairs.get());
        }
        if (wall != null) {
            if(!registered(wall.get())) registerWall(wall.get(), sandstone.get());
            dropSelfList.add(wall.get());
        }
        ResourceLocation top = GenerationsCore.id("block/" + sandstone.getId().getPath() + "_top");
        if (smooth != null){

            BlockModelBuilder smoothsandstoneBlock = models().cubeAll(smooth.getId().getPath() ,GenerationsCore.id("block/" + sandstone.getId().getPath() + "_top"));
            if(!registered(smooth.get()))
                simpleBlockWithItem(smooth.get(), smoothsandstoneBlock);

            dropSelfList.add(smooth.get());
            if (smoothslab != null){
                if(!registered(smoothslab.get())) {
                    slabBlock(smoothslab.get(), GenerationsCore.id("block/" + smooth.getId().getPath()), top, top, top);
                    simpleBlockItem(smoothslab.get(), models().slab(smoothslab.getId().getPath(), top, top, top));
                }
                dropSelfList.add(smoothslab.get());
            }
            if (smoothstair != null){
                if(!registered(smoothstair.get())) {
                    stairsBlock(smoothstair.get(), top);
                    simpleBlockItem(smoothstair.get(), models().stairs(smoothstair.getId().getPath(), top, top, top));
                }
                dropSelfList.add(smoothstair.get());
            }
            if (smoothWall != null){
                if(!registered(smoothWall.get())) {
                    wallBlock(smoothWall.get(), top);
                    simpleBlockItem(smoothWall.get(), models().wallInventory(smoothWall.getId().getPath(), top));
                }
                dropSelfList.add(smoothWall.get());
            }

            if(!registered(chiseled.get())) cubeColumn(chiseled, top);
        if (cutSandstone != null){
            if(!registered(cutSandstone.get())) cubeColumn(cutSandstone, top);
            if (cutSlab != null){
                if(!registered(cutSlab.get())) {
                    slabBlock(cutSlab.get(), GenerationsCore.id("block/" + cutSandstone.getId().getPath()), top, GenerationsCore.id("block/" + cutSandstone.getId().getPath()), top);
                    simpleBlockItem(cutSlab.get(), models().slab(cutSlab.getId().getPath(), GenerationsCore.id("block/" + cutSandstone.getId().getPath()), top, top));
                }
                dropSelfList.add(cutSlab.get());
            }
        }
        }
    }

    private void cubeColumn(RegistrySupplier<Block> cutSandstone, ResourceLocation top) {
        if (cutSandstone != null){
            BlockModelBuilder cutBlock = models().cubeColumn(cutSandstone.getId().getPath(), GenerationsCore.id("block/" + cutSandstone.getId().getPath()) , top);
            simpleBlockWithItem(cutSandstone.get(), cutBlock);
            dropSelfList.add(cutSandstone.get());
        }
    }

    private void registerStairs(StairBlock stairs, Block texturedBlock) {
        ResourceLocation texture = ModelLocationUtils.getModelLocation(texturedBlock);
        stairsBlock(stairs, texture);
        simpleBlockItem(stairs, itemModels().stairs("block/" + key(stairs).getPath(), texture, texture, texture));
    }

    private void registerSlab(SlabBlock slab, Block texturedBlock) {
        ResourceLocation texture = ModelLocationUtils.getModelLocation(texturedBlock);
        slabBlock(slab, texture, texture);
        simpleBlockItem(slab, itemModels().slab("block/" + key(slab).getPath(), texture, texture, texture));
    }

    private void registerWall(WallBlock wall, Block texturedBlock) {
        ResourceLocation texture = ModelLocationUtils.getModelLocation(texturedBlock);
        wallBlock(wall, texture);
        simpleBlockItem(wall, itemModels().wallInventory("block/" + key(wall).getPath(), texture));
    }

    private void registerFence(FenceBlock fence, Block block) {
        ResourceLocation texture;
        if (block == null) texture = ModelLocationUtils.getModelLocation(fence);
        else texture = ModelLocationUtils.getModelLocation(block);

        fenceBlock(fence, texture);
        simpleBlockItem(fence, itemModels().fenceInventory("block/" + key(fence).getPath(), texture));
    }

    private void registerGate(FenceGateBlock gate, FenceBlock fence, Block block) {
        ResourceLocation texture;
        if (block == null)
        {
            ResourceLocation fenceId = key(fence);
            texture = ModelLocationUtils.getModelLocation(fence);
            simpleBlockItem(gate, itemModels().fenceGate("block/" + fenceId.getPath(), texture));
        }
        else
        {
            ResourceLocation gateId = key(gate);
            texture = ModelLocationUtils.getModelLocation(block);
            simpleBlockItem(gate, itemModels().fenceGate("block/" + gateId.getPath(), texture));
        }

        fenceGateBlock(gate, texture);
    }

    private void registerDoor(DoorBlock door) {
        ResourceLocation blockId = key(door);
        doorBlockWithRenderType(door, new ResourceLocation(blockId.getNamespace(), "block/door/" + blockId.getPath() + "_bottom"), new ResourceLocation(blockId.getNamespace(), "block/door/" + blockId.getPath() + "_top"), "cutout");
    }

    private void registerPressurePlate(PressurePlateBlock pressurePlate, Block texturedBlock) {
        ResourceLocation texture = ModelLocationUtils.getModelLocation(texturedBlock);
        pressurePlateBlock(pressurePlate, texture);
        itemModels().pressurePlate(key(pressurePlate).getPath(), texture);
    }

    private void registerButton(Block button, Block texturedBlock) {
        ResourceLocation buttonId = key(button);
        ResourceLocation textureBlockId = key(texturedBlock);

        ResourceLocation texture = new ResourceLocation(textureBlockId.getNamespace(), "block/" + textureBlockId.getPath());
        buttonBlock((ButtonBlock) button, texture);
        itemModels().buttonInventory(buttonId.getPath(), texture);
    }

    private void registerTrapDoor(TrapDoorBlock trapDoor, Block texturedBlock){
        ResourceLocation texture = ModelLocationUtils.getModelLocation(texturedBlock);
        ResourceLocation trapDoorId = key(trapDoor);
        trapdoorBlockWithRenderType(trapDoor, texture,true, "cutout");
        itemModels().trapdoorBottom(trapDoorId.getPath(), texture);
    }

    private void registerTrapDoor(RegistrySupplier<TrapDoorBlock> trapDoor){registerTrapDoor(trapDoor.get(), trapDoor.get());}

    private void registerSign(StandingSignBlock sign, WallSignBlock wallsign, Block plank){signBlock(sign, wallsign, blockTexture(plank));}

    private void registerHangingSign(CeilingHangingSignBlock sign, WallHangingSignBlock wallsign, Block plank){
        ModelFile thing = this.models().sign(key(sign).getPath(), blockTexture(plank));
        simpleBlock(sign, thing);
        simpleBlock(wallsign, thing);
    }

    private void registerCrossBlock(RegistrySupplier<GenerationsMushroomBlock> crossBlock) {
        getVariantBuilder(crossBlock.get()).forAllStates(state ->
                ConfiguredModel.builder()
                        .modelFile(models().cross(crossBlock.getId().getPath(), blockTexture(crossBlock.get())).renderType("cutout"))
                        .build());

        MUSHROOM_BLOCKS.add(crossBlock.get());
    }

    private void registerLog(RegistrySupplier<RotatedPillarBlock> log) {
        String name = log.getId().getPath();
        BlockModelBuilder logBlock = models().withExistingParent(log.getId().toString(), mcLoc("block/oak_log"))
                .texture("side", GenerationsCore.id("block/" + name))
                .texture("top", GenerationsCore.id("block/" + name + "_top"));
        logBlock(log.get());
        simpleBlockItem(log.get(), logBlock);
    }

    private void registerBlockItem(RegistrySupplier<Block> block) {registerBlockItem(block.get());}

    private void registerBlockItem(Block block) {simpleBlockWithItem(block, cubeAll(block));}

    private void unownBlock(RegistrySupplier<? extends Block> block) {
        BlockModelBuilder model = models().cubeBottomTop(block.getId().getPath(),
                block.getId().withPrefix("block/"),
                GenerationsCore.id("block/unown_block_bottom"),
                GenerationsCore.id("block/unown_block_top"));

        simpleBlockWithItem(block.get(), model);
        dropSelfList.add(block.get());
    }
    private void registerBlockItemParticle(Block block, String name) {
        ResourceLocation blockId = key(block);
        ResourceLocation textureId = blockId.withPrefix("item/blocks/" + name + "/");
        try {
            simpleBlock(block, models().sign(blockId.getPath(), textureId));
            itemModels().getBuilder(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(block.asItem())).toString()).parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", textureId);
        } catch (Exception ignored) {
            DataProvider.LOGGER.error("Block: " + name + " -> " + textureId);
            simpleBlock(block, models().sign(blockId.getPath(), GenerationsCore.id("item/placeholder")));
            itemModels().getBuilder(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(block.asItem())).toString()).parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", GenerationsCore.id("item/placeholder"));

        }
    }

    private void registerPokeDoll(RegistrySupplier<Block> block) {
        ResourceLocation blockId = ForgeRegistries.BLOCKS.getKey(block.get());
        assert blockId != null;
        ResourceLocation textureId =  new ResourceLocation(blockId.getNamespace(), "item/dolls/" + blockId.getPath().replace("shiny_", "shiny/"));

        simpleBlock(block.get(), models().sign(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath(), textureId));

        ResourceLocation key = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get()), "Tried to create json model for unregistered Item.");
        ResourceLocation texPath = GenerationsCore.id("item/dolls/" + key.getPath());
        if(key.getPath().startsWith("shiny_"))
            texPath = GenerationsCore.id("item/dolls/shiny/" + key.getPath().replace("shiny_", ""));


        itemModels().getBuilder(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(block.get().asItem())).toString()).parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", texPath);
    }

    private void registerSandStone(RegistrySupplier<Block> block) {
        String name = block.getId().getPath();
        simpleBlockWithItem(block.get(), models().cubeBottomTop(name, GenerationsCore.id("block/" + name), GenerationsCore.id("block/" + name + "_bottom"), GenerationsCore.id("block/" + name + "_top")));
    }

    private void registerSandStoneStairs(RegistrySupplier<StairBlock> stairs, RegistrySupplier<Block> block) {
        stairsBlock(stairs.get(),  GenerationsCore.id("block/" + block.getId().getPath()), GenerationsCore.id("block/" + block.getId().getPath() + "_bottom"), GenerationsCore.id("block/" + block.getId().getPath() +"_top"));
        simpleBlockItem(stairs.get(), itemModels().stairs("block/" + stairs.getId().getPath(), GenerationsCore.id("block/" + block.getId().getPath()), GenerationsCore.id("block/" + block.getId().getPath() + "_bottom"), GenerationsCore.id("block/" + block.getId().getPath() +"_top")));
    }

    private void registerSandStoneSlab(RegistrySupplier<SlabBlock> slab, RegistrySupplier<Block> block) {
        slabBlock(slab.get(), GenerationsCore.id("block/" + block.getId().getPath()), GenerationsCore.id("block/" + block.getId().getPath()), GenerationsCore.id("block/" + block.getId().getPath() + "_bottom"), GenerationsCore.id("block/" + block.getId().getPath() +"_top"));
        simpleBlockItem(slab.get(), itemModels().slab("block/" + slab.getId().getPath(), GenerationsCore.id("block/" + block.getId().getPath()), GenerationsCore.id("block/" + block.getId().getPath() + "_bottom"), GenerationsCore.id("block/" + block.getId().getPath() +"_top")));
    }

    private void registerGlassBlock(RegistrySupplier<GlassBlock> block) {
        getVariantBuilder(block.get()).forAllStates(state ->
                ConfiguredModel.builder()
                        .modelFile(models().cubeAll(block.getId().getPath(), modLoc("block/" + block.getId().getPath())).renderType("cutout_mipped"))
                        .build());
        simpleBlockItem(block.get(), cubeAll(block.get()));
    }

    private void registerOreBlocks() {
        GenerationsOres.ORES.forEach(ore -> {
            String name = ore.getId().getPath();
            simpleBlockWithItem(ore.get(), models().singleTexture(name, mcLoc("block/cube_all"), "all", GenerationsCore.id("block/ores/" + name)));
        });
    }

    private void pokeBallChests(RegistrySupplier<GenericChestBlock> chest, String ballName){
        simpleBlock(chest.get(), models().getBuilder(chest.getId().getPath()).texture("particle", GenerationsCore.id("item/pokeballs/" + ballName)));
    }


    @SuppressWarnings("SameParameterValue")
    private void registerPumpkin(RegistrySupplier<PumpkinBlock> pumpkin) {
        BlockModelBuilder pumpkinModel = models().withExistingParent(pumpkin.getId().toString(), mcLoc("block/pumpkin"))
                .texture("side", GenerationsCore.id("block/" + pumpkin.getId().getPath() + "_side"))
                .texture("end", GenerationsCore.id("block/" + pumpkin.getId().getPath() + "_end"));
        simpleBlockWithItem(pumpkin.get(), pumpkinModel);
    }


    private void registerFurnace(RegistrySupplier<? extends Block> furnace) {
        BlockModelBuilder off = models().orientable(furnace.getId().getPath(), GenerationsCore.id("block/furnace/" + furnace.getId().getPath() + "_side"), GenerationsCore.id("block/furnace/" + furnace.getId().getPath() + "_front"), GenerationsCore.id("block/furnace/" + furnace.getId().getPath() + "_top"));
        BlockModelBuilder on = models().orientable(furnace.getId().getPath() + "_on", GenerationsCore.id("block/furnace/" + furnace.getId().getPath() + "_side"), GenerationsCore.id("block/furnace/" + furnace.getId().getPath() + "_front_on"), GenerationsCore.id("block/furnace/" + furnace.getId().getPath() + "_top"));
        horizontalBlock(furnace.get(), state -> state.getValue(FurnaceBlock.LIT) ? on : off);
        simpleBlockItem(furnace.get(), off);
    }

    @SuppressWarnings("SameParameterValue")
    private void registerCarvedPumpkin(RegistrySupplier<CursedCarvedPumpkinBlock> carvedPumpkin, RegistrySupplier<PumpkinBlock> pumpkin){
        BlockModelBuilder pumpkinModel = models().withExistingParent(carvedPumpkin.getId().toString(), mcLoc("block/carved_pumpkin"))
                .texture("side", GenerationsCore.id("block/" + pumpkin.getId().getPath() + "_side"))
                .texture("top", GenerationsCore.id("block/" + pumpkin.getId().getPath() + "_end"))
                .texture("front", GenerationsCore.id("block/" + carvedPumpkin.getId().getPath()));
        horizontalBlock(carvedPumpkin.get(), pumpkinModel);
        simpleBlockItem(carvedPumpkin.get(), pumpkinModel);
    }

    private void registerCraftingTable(RegistrySupplier<GenerationsCraftingTableBlock> table, RegistrySupplier<Block> plank){
        BlockModelBuilder tableModel = models().withExistingParent(table.getId().toString(), mcLoc("block/cube"))
                .texture("down", GenerationsCore.id("block/" + plank.getId().getPath()))
                .texture("east", GenerationsCore.id("block/crafting_table/" + table.getId().getPath() + "_side"))
                .texture("north", GenerationsCore.id("block/crafting_table/" + table.getId().getPath() + "_front"))
                .texture("particle", GenerationsCore.id("block/crafting_table/" + table.getId().getPath() + "_front"))
                .texture("south", GenerationsCore.id("block/crafting_table/" + table.getId().getPath() + "_side"))
                .texture("up", GenerationsCore.id("block/crafting_table/" + table.getId().getPath() + "_top"))
                .texture("west", GenerationsCore.id("block/crafting_table/" + table.getId().getPath() + "_front"));
        simpleBlockWithItem(table.get(), tableModel);
    }

    private void registerBookShelf(RegistrySupplier<Block> bookshelf, RegistrySupplier<Block> plank) {
        BlockModelBuilder bookshelfModel = models().withExistingParent(bookshelf.getId().toString(), mcLoc("block/cube_column"))
                .texture("side", GenerationsCore.id("block/" + bookshelf.getId().getPath()))
                .texture("end", GenerationsCore.id("block/" + plank.getId().getPath()));
        simpleBlockWithItem(bookshelf.get(), bookshelfModel);
    }

    private void registerInfestedBlock(RegistrySupplier<InfestedBlock> block){
        this.models().withExistingParent(block.getId().toString().replace("infested_", "").concat("_mirrored"), mcLoc("block/cube_mirrored_all"))
                .texture("all", blockTexture(block.get().getHostBlock()));
        ModelFile.UncheckedModelFile normal = new ModelFile.UncheckedModelFile(ModelLocationUtils.getModelLocation(block.get().getHostBlock()));
        ModelFile.UncheckedModelFile mirrored = new ModelFile.UncheckedModelFile(blockTexture(block.get().getHostBlock()) + "_mirrored");

        getVariantBuilder(block.get()).partialState()
                .addModels(ConfiguredModel.builder().modelFile(normal)
                        .nextModel().modelFile(mirrored)
                        .nextModel().modelFile(normal).rotationY(180)
                        .nextModel().modelFile(mirrored).rotationY(180)
                        .build());
        itemModels().withExistingParent(block.getId().getPath(), blockTexture(block.get().getHostBlock()));
    }

    private void registerDripStone(RegistrySupplier<PointedChargeDripstoneBlock> dripstone) {
        String name = dripstone.getId().toString();
        String[] parts = {"down_base", "down_middle", "down_tip", "down_tip_merge", "down_frustum", "up_base", "up_middle", "up_tip", "up_tip_merge", "up_frustum"};

        for (String part : parts)
            this.models().withExistingParent(name.concat("_" + part), mcLoc("block/pointed_dripstone"))
                    .texture("cross", GenerationsCore.id("block/" + dripstone.getId().getPath() + "_" + part))
                    .renderType("cutout");

        this.models().withExistingParent(name, mcLoc("block/pointed_dripstone"));
    }


    private void registerBlockFamily(BlockFamily family) {
        registerBlockItem(family.getBaseBlock());
        family.getVariants().keySet().forEach(variant -> proccessVariant(variant, family));
    }

    private void proccessVariant(BlockFamily.Variant variant, BlockFamily family) {
        Block original = family.getBaseBlock();
        Block variantTarget = family.getVariants().get(variant);
        switch (variant) {
            case BUTTON -> registerButton(variantTarget, original);
            case CHISELED, CRACKED, CUT -> simpleBlockWithItem(variantTarget, cubeAll(variantTarget));
            case DOOR -> registerDoor((DoorBlock) variantTarget);
            case FENCE -> registerFence((FenceBlock) variantTarget, original);
            case FENCE_GATE -> registerGate((FenceGateBlock) variantTarget, (FenceBlock) family.get(BlockFamily.Variant.FENCE), original);
            case SIGN -> registerSign((StandingSignBlock) variantTarget, (WallSignBlock) family.get(BlockFamily.Variant.WALL_SIGN), original);
            case SLAB -> registerSlab((SlabBlock) variantTarget, original);
            case STAIRS -> registerStairs((StairBlock) variantTarget, original);
            case PRESSURE_PLATE -> registerPressurePlate((PressurePlateBlock) variantTarget, original);
            case TRAPDOOR -> registerTrapDoor((TrapDoorBlock) variantTarget, original);
            case WALL -> registerWall((WallBlock) variantTarget, original);
        }
    }

    private void registerWoodPallet(RegistrySupplier<RotatedPillarBlock> Log, RegistrySupplier<RotatedPillarBlock> StrippedLog, RegistrySupplier<Block> Plank, RegistrySupplier<SlabBlock> slab, RegistrySupplier<StairBlock> stair, RegistrySupplier<ButtonBlock> button, RegistrySupplier<PressurePlateBlock> pressurePlate, RegistrySupplier<DoorBlock> door, RegistrySupplier<TrapDoorBlock> trapDoor, RegistrySupplier<Block> wood, RegistrySupplier<Block> StrippedWood, RegistrySupplier<FenceBlock> fence, RegistrySupplier<FenceGateBlock> gate, RegistrySupplier<StandingSignBlock> sign, RegistrySupplier<WallSignBlock> wallSign, RegistrySupplier<GenerationsCraftingTableBlock> craftingTable, RegistrySupplier<CeilingHangingSignBlock> hangingSignBlock, RegistrySupplier<WallHangingSignBlock> wallHangingSignBlock, RegistrySupplier<Block> bookshelf){
        if(!registered(Log.get())) registerLog(Log);
        if(!registered(StrippedLog.get())) registerLog(StrippedLog);
        if(!registered(Plank.get())) registerBlockItem(Plank);
        if(!registered(wood.get())) registerBlockItem(wood);
        if(!registered(StrippedWood.get())) registerBlockItem(StrippedWood);
        if(!registered(slab.get())) registerSlab(slab.get(), Plank.get());
        if(!registered(stair.get())) registerStairs(stair.get(), Plank.get());
        if(!registered(button.get())) registerButton(button.get(), Plank.get());
        if(!registered(pressurePlate.get())) registerPressurePlate(pressurePlate.get(), Plank.get());
        if(!registered(door.get())) registerDoor(door.get());
        if(!registered(trapDoor.get())) registerTrapDoor(trapDoor);
        if(!registered(gate.get())) registerGate(gate.get(), null, Plank.get());
        if(!registered(fence.get())) registerFence(fence.get(), Plank.get());
        if(!registered(sign.get())) registerSign(sign.get(), wallSign.get(), Plank.get());
        if(!registered(hangingSignBlock.get())) registerHangingSign(hangingSignBlock.get(), wallHangingSignBlock.get(), StrippedLog.get());
        if(!registered(craftingTable.get())) registerCraftingTable(craftingTable, Plank);
        if (!registered(bookshelf.get())) registerBookShelf(bookshelf, Plank);
    }
}
