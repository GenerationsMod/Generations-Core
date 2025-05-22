package generations.gg.generations.core.generationscore.forge.datagen.generators.blocks;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsUltraBlockSet;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;

public class UltraBlockModelDataGen extends GenerationsBlockStateProvider.Proxied {

	public UltraBlockModelDataGen(GenerationsBlockStateProvider provider) {
		super(provider);
	}

	@Override
	public void registerStatesAndModels() {
		GenerationsUltraBlockSet.ultraBlockSets.forEach(arg -> registerBlockFamily(arg.blockFamily));
	}
	private void registerBlockFamily(BlockFamily family) {
		registerUltraBlock(family.getBaseBlock());
		family.getVariants().keySet().forEach(variant -> proccessVariant(variant, family));
	}

	private void proccessVariant(BlockFamily.Variant variant, BlockFamily family) {
		Block original = family.getBaseBlock();
		Block variantTarget = family.getVariants().get(variant);
		switch (variant) {
			case BUTTON -> registerUltraButton((ButtonBlock) variantTarget, original);
			case SLAB -> registerUltraSlab((SlabBlock) variantTarget, original);
			case STAIRS -> registerUltraStairs((StairBlock) variantTarget, original);
			case WALL -> registerUltraWall((WallBlock) variantTarget, original);
			case PRESSURE_PLATE -> registerUltraPressurePlate((PressurePlateBlock) variantTarget, original);
		}
	}

	private void registerUltraBlock(Block block) {
		String name = block.getDescriptionId().replace("block.generations_core.", "");
		simpleBlockWithItem(block, models().singleTexture(name, GenerationsCore.id("block/ultra/ultra_block"), "all", GenerationsCore.id("block/ultra/" + name)));
	}
	private void registerUltraStairs(StairBlock stair, Block block) {
		String name = block.getDescriptionId().replace("block.generations_core.", "");
		String stairName = stair.getDescriptionId().replace("block.generations_core.", "");
		BlockModelBuilder ultrastair = models().singleTexture(stairName, GenerationsCore.id("block/ultra/ultra_stairs"), "bottom", GenerationsCore.id("block/ultra/" + name)).texture("top", GenerationsCore.id("block/ultra/" + name)).texture("side", GenerationsCore.id("block/ultra/" + name));
		BlockModelBuilder ultrastairInner = models().singleTexture(stairName + "_inner", GenerationsCore.id("block/ultra/ultra_stairs_inner"), "bottom", GenerationsCore.id("block/ultra/" + name)).texture("side", GenerationsCore.id("block/ultra/" + name)).texture("top", GenerationsCore.id("block/ultra/" + name));
		BlockModelBuilder ultrastairOuter = models().singleTexture(stairName + "_outer", GenerationsCore.id("block/ultra/ultra_stairs_outer"), "bottom", GenerationsCore.id("block/ultra/" + name)).texture("side", GenerationsCore.id("block/ultra/" + name)).texture("top", GenerationsCore.id("block/ultra/" + name));
		stairsBlock(stair, ultrastair, ultrastairInner, ultrastairOuter);
		simpleBlockItem(stair, ultrastair);
	}

	private void registerUltraSlab(SlabBlock slab, Block block) {
		String name = block.getDescriptionId().replace("block.generations_core.", "");
		String slabname = slab.getDescriptionId().replace("block.generations_core.", "");
		BlockModelBuilder ultraSlab = models().singleTexture(slabname, GenerationsCore.id("block/ultra/ultra_slab"), "bottom", GenerationsCore.id("block/ultra/" + name)).texture("top", GenerationsCore.id("block/ultra/" + name)).texture("side", GenerationsCore.id("block/ultra/" + name));
		BlockModelBuilder ultraSlabTop = models().singleTexture(slabname + "_top", GenerationsCore.id("block/ultra/ultra_slab_top"), "bottom", GenerationsCore.id("block/ultra/" + name)).texture("top", GenerationsCore.id("block/ultra/" + name)).texture("side", GenerationsCore.id("block/ultra/" + name));
		BlockModelBuilder ultraSlabDouble = models().singleTexture(slabname + "_double", GenerationsCore.id("block/ultra/ultra_slab_double"), "end", GenerationsCore.id("block/ultra/" + name)).texture("side", GenerationsCore.id("block/ultra/" + name));
		slabBlock(slab, ultraSlab, ultraSlabTop, ultraSlabDouble);
		simpleBlockItem(slab, ultraSlab);
	}

	private void registerUltraButton(ButtonBlock button, Block block) {
		String name = block.getDescriptionId().replace("block.generations_core.", "");
		String buttonname = button.getDescriptionId().replace("block.generations_core.", "");
		BlockModelBuilder ultraBlock = models().singleTexture(buttonname, GenerationsCore.id("block/ultra/ultra_button"), "texture", GenerationsCore.id("block/ultra/" + name));
		BlockModelBuilder ultraButtonPressed = models().singleTexture(buttonname + "_pressed", GenerationsCore.id("block/ultra/ultra_button_pressed"), "texture", GenerationsCore.id("block/ultra/" + name));
		buttonBlock(button, ultraBlock, ultraButtonPressed);
		itemModels().buttonInventory(buttonname, GenerationsCore.id("block/ultra/" + name));
	}

	private void registerUltraWall(WallBlock wall, Block block) {
		String name = block.getDescriptionId().replace("block.generations_core.", "");
		String wallname = wall.getDescriptionId().replace("block.generations_core.", "");
		BlockModelBuilder ultraWallPost = models().singleTexture(wallname + "_post", GenerationsCore.id("block/ultra/ultra_wall_post"), "wall", GenerationsCore.id("block/ultra/" + name));
		BlockModelBuilder ultraWallSide = models().singleTexture(wallname + "_side", GenerationsCore.id("block/ultra/ultra_wall_side"), "wall", GenerationsCore.id("block/ultra/" + name));
		BlockModelBuilder ultraWallSideTall = models().singleTexture(wallname + "_side_tall", GenerationsCore.id("block/ultra/ultra_wall_side_tall"), "wall", GenerationsCore.id("block/ultra/" + name));
		wallBlock(wall, ultraWallPost, ultraWallSide, ultraWallSideTall);
		itemModels().wallInventory(wallname, GenerationsCore.id("block/ultra/" + name));
	}

	private void registerUltraPressurePlate(PressurePlateBlock pressurePlate, Block block) {
		String name = block.getDescriptionId().replace("block.generations_core.", "");
		String pressureplatename = pressurePlate.getDescriptionId().replace("block.generations_core.", "");
		BlockModelBuilder ultraPressurePlateUp = models().singleTexture(pressureplatename + "_up", GenerationsCore.id("block/ultra/ultra_pressure_plate_up"), "texture", GenerationsCore.id("block/ultra/" + name));
		BlockModelBuilder ultraPressurePlateDown = models().singleTexture(pressureplatename + "_down", GenerationsCore.id("block/ultra/ultra_pressure_plate_down"), "texture", GenerationsCore.id("block/ultra/" + name));
		pressurePlateBlock(pressurePlate, ultraPressurePlateUp, ultraPressurePlateDown);
		itemModels().pressurePlate(pressureplatename, GenerationsCore.id("block/ultra/" + name));
	}
}
