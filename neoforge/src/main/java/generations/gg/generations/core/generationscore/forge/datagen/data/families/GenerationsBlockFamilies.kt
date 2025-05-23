package generations.gg.generations.core.generationscore.forge.datagen.data.families

import com.google.common.collect.Maps
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsWood
import generations.gg.generations.core.generationscore.forge.datagen.data.families.GenerationsBlockFamilies.familyBuilder
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.BlockFamilies
import net.minecraft.data.BlockFamily
import net.minecraft.data.BlockFamily.Builder
import net.minecraft.world.level.block.Block
import java.util.stream.Stream

/**
 * Generations Core Block Families
 * Used during Data Generation
 * @see BlockFamilies
 *
 * @author Joseph T. McQuigg
 */
@Suppress("unused")
object GenerationsBlockFamilies : BlockFamilies() {
    //Wood
    private val MAP: MutableMap<Block, BlockFamily> = Maps.newHashMap()
    val GHOST_PLANKS: BlockFamily =
        familyBuilder(GenerationsWood.GHOST_PLANKS) {
            button(GenerationsWood.GHOST_BUTTON)
            fence(GenerationsWood.GHOST_FENCE)
            fenceGate(GenerationsWood.GHOST_FENCE_GATE)
            pressurePlate(GenerationsWood.GHOST_PRESSURE_PLATE)
            sign(GenerationsWood.GHOST_SIGN, GenerationsWood.GHOST_WALL_SIGN)
            slab(GenerationsWood.GHOST_SLAB)
            stairs(GenerationsWood.GHOST_STAIRS)
            door(GenerationsWood.GHOST_DOOR)
            trapdoor(GenerationsWood.GHOST_TRAPDOOR)
            recipeGroupPrefix("wooden")
            recipeUnlockedBy("has_planks").family
        }
    val ULTRA_DARK_PLANKS: BlockFamily = familyBuilder(GenerationsWood.ULTRA_DARK_PLANKS) {
        button(GenerationsWood.ULTRA_DARK_BUTTON)
        fence(GenerationsWood.ULTRA_DARK_FENCE)
        fenceGate(GenerationsWood.ULTRA_DARK_FENCE_GATE)
        pressurePlate(GenerationsWood.ULTRA_DARK_PRESSURE_PLATE)
        sign(GenerationsWood.ULTRA_DARK_SIGN, GenerationsWood.ULTRA_DARK_WALL_SIGN)
        slab(GenerationsWood.ULTRA_DARK_SLAB)
        stairs(GenerationsWood.ULTRA_DARK_STAIRS)
        door(GenerationsWood.ULTRA_DARK_DOOR)
        trapdoor(GenerationsWood.ULTRA_DARK_TRAPDOOR)
        recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks")
    }
    val ULTRA_JUNGLE_PLANKS: BlockFamily = familyBuilder(GenerationsWood.ULTRA_JUNGLE_PLANKS) {
        button(GenerationsWood.ULTRA_JUNGLE_BUTTON)
        fence(GenerationsWood.ULTRA_JUNGLE_FENCE)
        fenceGate(GenerationsWood.ULTRA_JUNGLE_FENCE_GATE)
        pressurePlate(GenerationsWood.ULTRA_JUNGLE_PRESSURE_PLATE)
        sign(GenerationsWood.ULTRA_JUNGLE_SIGN, GenerationsWood.ULTRA_JUNGLE_WALL_SIGN)
        slab(GenerationsWood.ULTRA_JUNGLE_SLAB)
        stairs(GenerationsWood.ULTRA_JUNGLE_STAIRS)
        door(GenerationsWood.ULTRA_JUNGLE_DOOR)
        trapdoor(GenerationsWood.ULTRA_JUNGLE_TRAPDOOR)
        recipeGroupPrefix("wooden")
        recipeUnlockedBy("has_planks")
    }

    //Building Block Families
    val SMOOTH_CHARGE_STONE: BlockFamily = familyBuilder(GenerationsBlocks.SMOOTH_CHARGE_STONE) {
        slab(GenerationsBlocks.SMOOTH_CHARGE_STONE_SLAB)
        recipeGroupPrefix("smooth_charge_stone")
        recipeUnlockedBy("has_smooth_charge_stone")
    }

    val CHARGE_STONE_BRICKS: BlockFamily = familyBuilder(GenerationsBlocks.CHARGE_STONE_BRICKS) {
        cracked(GenerationsBlocks.CRACKED_CHARGE_STONE_BRICKS)
        slab(GenerationsBlocks.CHARGE_STONE_BRICK_SLAB)
        stairs(GenerationsBlocks.CHARGE_STONE_BRICK_STAIRS)
        wall(GenerationsBlocks.CHARGE_STONE_BRICK_WALL)
        chiseled(GenerationsBlocks.CHISELED_CHARGE_STONE_BRICKS)
        recipeGroupPrefix("charge_stone_bricks")
        recipeUnlockedBy("has_charge_stone_bricks")
    }

    val VOLCANIC_STONE: BlockFamily = familyBuilder(GenerationsBlocks.VOLCANIC_STONE) {
        slab(GenerationsBlocks.VOLCANIC_STONE_SLAB)
        stairs(GenerationsBlocks.VOLCANIC_STONE_STAIRS)
        wall(GenerationsBlocks.VOLCANIC_STONE_WALL)
        pressurePlate(GenerationsBlocks.VOLCANIC_STONE_PRESSURE_PLATE)
        button(GenerationsBlocks.VOLCANIC_STONE_BUTTON).recipeGroupPrefix("volcanic_stone")
        recipeUnlockedBy("has_volcanic_stone")
    }

    val SMOOTH_VOLCANIC_STONE: BlockFamily = familyBuilder(GenerationsBlocks.SMOOTH_VOLCANIC_STONE) {
        slab(GenerationsBlocks.SMOOTH_VOLCANIC_STONE_SLAB)
        recipeGroupPrefix("smooth_volcanic_stone")
        recipeUnlockedBy("has_smooth_volcanic_stone")
    }

    val VOLCANIC_STONE_BRICKS: BlockFamily = familyBuilder(GenerationsBlocks.VOLCANIC_STONE_BRICKS) {
        cracked(GenerationsBlocks.CRACKED_VOLCANIC_STONE_BRICKS)
        slab(GenerationsBlocks.VOLCANIC_STONE_BRICK_SLAB)
        stairs(GenerationsBlocks.VOLCANIC_STONE_BRICK_STAIRS)
        wall(GenerationsBlocks.VOLCANIC_STONE_BRICK_WALL)
        chiseled(GenerationsBlocks.CHISELED_VOLCANIC_STONE_BRICKS)
        recipeGroupPrefix("volcanic_stone_bricks")
        recipeUnlockedBy("has_volcanic_stone_bricks")
    }

    val SUBWAY_WALL: BlockFamily = familyBuilder(GenerationsBlocks.SUBWAY_WALL) {
        slab(GenerationsBlocks.SUBWAY_WALL_SLAB)
        stairs(GenerationsBlocks.SUBWAY_WALL_STAIRS)
        recipeGroupPrefix("subway_wall")
        recipeUnlockedBy("has_subway_wall")
    }

    val SUBWAY_WALL_2: BlockFamily = familyBuilder(GenerationsBlocks.SUBWAY_WALL_2) {
        slab(GenerationsBlocks.SUBWAY_WALL_2_SLAB)
        stairs(GenerationsBlocks.SUBWAY_WALL_2_STAIRS)
        recipeGroupPrefix("subway_wall_2")
        recipeUnlockedBy("has_subway_wall_2")
    }

    val ULTRA_SANDSTONE: BlockFamily = familyBuilder(GenerationsBlocks.ULTRA_SANDSTONE) {
        slab(GenerationsBlocks.ULTRA_SANDSTONE_SLAB)
        stairs(GenerationsBlocks.ULTRA_SANDSTONE_STAIRS)
        wall(GenerationsBlocks.ULTRA_SANDSTONE_WALL)
        recipeGroupPrefix("ultra_sandstone")
        recipeUnlockedBy("has_ultra_sandstone")
    }

    val ULTRA_SMOOTH_SANDSTONE: BlockFamily = familyBuilder(GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE) {
        slab(GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_SLAB)
        stairs(GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_STAIRS)
        wall(GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_WALL)
        recipeGroupPrefix("ultra_smooth_sandstone")
        recipeUnlockedBy("has_ultra_smooth_sandstone")
        dontGenerateModel()
    }

    val GOLDEN_TEMPLE_SANDSTONE: BlockFamily = familyBuilder(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE) {
        slab(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_SLAB)
        stairs(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_STAIRS)
        wall(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_WALL)
        chiseled(GenerationsBlocks.GOLDEN_TEMPLE_CHISELED_SANDSTONE)
        cut(GenerationsBlocks.GOLDEN_TEMPLE_CUT_SANDSTONE)
        recipeGroupPrefix("golden_temple_sandstone")
        recipeUnlockedBy("has_golden_temple_sandstone")
        dontGenerateModel()
    }

    val GOLDEN_TEMPLE_CUT_SANDSTONE: BlockFamily = familyBuilder(GenerationsBlocks.GOLDEN_TEMPLE_CUT_SANDSTONE) {
        slab(GenerationsBlocks.GOLDEN_TEMPLE_CUT_SANDSTONE_SLAB)
        recipeGroupPrefix("golden_temple_cut_sandstone")
        recipeUnlockedBy("has_golden_temple_cut_sandstone")
        dontGenerateRecipe().dontGenerateModel()
    }

    val GOLDEN_TEMPLE_SMOOTH_SANDSTONE: BlockFamily = familyBuilder(GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE) {
        slab(GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_SLAB)
        stairs(GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_STAIRS)
        wall(GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_WALL)
        recipeGroupPrefix("golden_temple_smooth_sandstone")
        recipeUnlockedBy("has_golden_temple_smooth_sandstone")
        dontGenerateModel()
    }

    private fun familyBuilder(baseBlock: Holder<out Block>, dsl: Builder.() -> Unit): BlockFamily {
        val builder = Builder(baseBlock.value())
        dsl.invoke(builder)

        if(MAP.containsKey(baseBlock.value())) throw IllegalStateException("Duplicate family definition for" + baseBlock.key)

        MAP[baseBlock.value()] = builder.family
        return builder.family
    }

    val allGenerationsFamilies: Stream<BlockFamily>
        get() = MAP.values.stream()

    fun Builder.button(button: Holder<out Block>): Builder = this.button(button.value())
    fun Builder.fence(fence: Holder<out Block>): Builder = this.fence(fence.value())
    fun Builder.fenceGate(fenceGate: Holder<out Block>): Builder = this.fenceGate(fenceGate.value())
    fun Builder.pressurePlate(pressurePlate: Holder<out Block>): Builder = this.pressurePlate(pressurePlate.value())
    fun Builder.sign(sign: Holder<out Block>, wallSign: Holder<out Block>): Builder = this.sign(sign.value(), wallSign.value())
    fun Builder.slab(slab: Holder<out Block>): Builder = this.slab(slab.value())
    fun Builder.stairs(stairs: Holder<out Block>): Builder = this.stairs(stairs.value())
    fun Builder.door(door: Holder<out Block>): Builder = this.door(door.value())
    fun Builder.trapdoor(trapdoor: Holder<out Block>): Builder = this.trapdoor(trapdoor.value())
    fun Builder.cracked(cracked: Holder<out Block>): Builder = this.cracked(cracked.value())
    fun Builder.wall(wall: Holder<out Block>): Builder = this.wall(wall.value())
    fun Builder.chiseled(chiseled: Holder<out Block>): Builder = this.chiseled(chiseled.value())
    fun Builder.cut(cut: Holder<out Block>): Builder = this.cut(cut.value())
}
