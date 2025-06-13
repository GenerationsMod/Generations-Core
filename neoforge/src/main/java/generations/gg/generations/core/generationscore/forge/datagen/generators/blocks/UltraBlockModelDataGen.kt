package generations.gg.generations.core.generationscore.forge.datagen.generators.blocks

import generations.gg.generations.core.generationscore.common.GenerationsCore.id
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsUltraBlockSet
import net.minecraft.data.BlockFamily
import net.minecraft.world.level.block.*
import java.util.function.Consumer

class UltraBlockModelDataGen(provider: GenerationsBlockStateProvider) :
    GenerationsBlockStateProvider.Proxied(provider) {
    override fun registerStatesAndModels() {
        GenerationsUltraBlockSet.ultraBlockSets.forEach(Consumer { arg: GenerationsUltraBlockSet ->
            registerBlockFamily(
                arg.blockFamily!!
            )
        })
    }

    private fun registerBlockFamily(family: BlockFamily) {
        registerUltraBlock(family.baseBlock)
        family.variants.keys.forEach(Consumer { variant: BlockFamily.Variant -> proccessVariant(variant, family) })
    }

    private fun proccessVariant(variant: BlockFamily.Variant, family: BlockFamily) {
        val original = family.baseBlock
        val variantTarget = family.variants[variant]
        when (variant) {
            BlockFamily.Variant.BUTTON -> registerUltraButton((variantTarget as ButtonBlock), original)
            BlockFamily.Variant.SLAB -> registerUltraSlab((variantTarget as SlabBlock), original)
            BlockFamily.Variant.STAIRS -> registerUltraStairs((variantTarget as StairBlock), original)
            BlockFamily.Variant.WALL -> registerUltraWall((variantTarget as WallBlock), original)
            BlockFamily.Variant.PRESSURE_PLATE -> registerUltraPressurePlate((variantTarget as PressurePlateBlock), original)
            else -> {}
        }
    }

    private fun registerUltraBlock(block: Block) {
        val name = block.descriptionId.replace("block.generations_core.", "")
        simpleBlockWithItem(
            block, models().singleTexture(
                name, id("block/ultra/ultra_block"), "all", id(
                    "block/ultra/$name"
                )
            )
        )
    }

    private fun registerUltraStairs(stair: StairBlock, block: Block) {
        val name = block.descriptionId.replace("block.generations_core.", "")
        val stairName = stair.descriptionId.replace("block.generations_core.", "")
        val ultrastair = models().singleTexture(
            stairName, id("block/ultra/ultra_stairs"), "bottom", id(
                "block/ultra/$name"
            )
        ).texture("top", id("block/ultra/$name")).texture("side", id("block/ultra/$name"))
        val ultrastairInner = models().singleTexture(
            stairName + "_inner", id("block/ultra/ultra_stairs_inner"), "bottom", id(
                "block/ultra/$name"
            )
        ).texture("side", id("block/ultra/$name")).texture("top", id("block/ultra/$name"))
        val ultrastairOuter = models().singleTexture(
            stairName + "_outer", id("block/ultra/ultra_stairs_outer"), "bottom", id(
                "block/ultra/$name"
            )
        ).texture("side", id("block/ultra/$name")).texture("top", id("block/ultra/$name"))
        stairsBlock(stair, ultrastair, ultrastairInner, ultrastairOuter)
        simpleBlockItem(stair, ultrastair)
    }

    private fun registerUltraSlab(slab: SlabBlock, block: Block) {
        val name = block.descriptionId.replace("block.generations_core.", "")
        val slabname = slab.descriptionId.replace("block.generations_core.", "")
        val ultraSlab = models().singleTexture(
            slabname, id("block/ultra/ultra_slab"), "bottom", id(
                "block/ultra/$name"
            )
        ).texture("top", id("block/ultra/$name")).texture("side", id("block/ultra/$name"))
        val ultraSlabTop = models().singleTexture(
            slabname + "_top", id("block/ultra/ultra_slab_top"), "bottom", id(
                "block/ultra/$name"
            )
        ).texture("top", id("block/ultra/$name")).texture("side", id("block/ultra/$name"))
        val ultraSlabDouble = models().singleTexture(
            slabname + "_double", id("block/ultra/ultra_slab_double"), "end", id(
                "block/ultra/$name"
            )
        ).texture("side", id("block/ultra/$name"))
        slabBlock(slab, ultraSlab, ultraSlabTop, ultraSlabDouble)
        simpleBlockItem(slab, ultraSlab)
    }

    private fun registerUltraButton(button: ButtonBlock, block: Block) {
        val name = block.descriptionId.replace("block.generations_core.", "")
        val buttonname = button.descriptionId.replace("block.generations_core.", "")
        val ultraBlock = models().singleTexture(
            buttonname, id("block/ultra/ultra_button"), "texture", id(
                "block/ultra/$name"
            )
        )
        val ultraButtonPressed = models().singleTexture(
            buttonname + "_pressed", id("block/ultra/ultra_button_pressed"), "texture", id(
                "block/ultra/$name"
            )
        )
        buttonBlock(button, ultraBlock, ultraButtonPressed)
        itemModels().buttonInventory(buttonname, id("block/ultra/$name"))
    }

    private fun registerUltraWall(wall: WallBlock, block: Block) {
        val name = block.descriptionId.replace("block.generations_core.", "")
        val wallname = wall.descriptionId.replace("block.generations_core.", "")
        val ultraWallPost = models().singleTexture(
            wallname + "_post", id("block/ultra/ultra_wall_post"), "wall", id(
                "block/ultra/$name"
            )
        )
        val ultraWallSide = models().singleTexture(
            wallname + "_side", id("block/ultra/ultra_wall_side"), "wall", id(
                "block/ultra/$name"
            )
        )
        val ultraWallSideTall = models().singleTexture(
            wallname + "_side_tall", id("block/ultra/ultra_wall_side_tall"), "wall", id(
                "block/ultra/$name"
            )
        )
        wallBlock(wall, ultraWallPost, ultraWallSide, ultraWallSideTall)
        itemModels().wallInventory(wallname, id("block/ultra/$name"))
    }

    private fun registerUltraPressurePlate(pressurePlate: PressurePlateBlock, block: Block) {
        val name = block.descriptionId.replace("block.generations_core.", "")
        val pressureplatename = pressurePlate.descriptionId.replace("block.generations_core.", "")
        val ultraPressurePlateUp = models().singleTexture(
            pressureplatename + "_up", id("block/ultra/ultra_pressure_plate_up"), "texture", id(
                "block/ultra/$name"
            )
        )
        val ultraPressurePlateDown = models().singleTexture(
            pressureplatename + "_down", id("block/ultra/ultra_pressure_plate_down"), "texture", id(
                "block/ultra/$name"
            )
        )
        pressurePlateBlock(pressurePlate, ultraPressurePlateUp, ultraPressurePlateDown)
        itemModels().pressurePlate(pressureplatename, id("block/ultra/$name"))
    }
}
