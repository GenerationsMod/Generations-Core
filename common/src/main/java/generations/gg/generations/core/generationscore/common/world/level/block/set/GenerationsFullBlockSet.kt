package generations.gg.generations.core.generationscore.common.world.level.block.set

import dev.architectury.registry.registries.RegistrySupplier
import net.minecraft.data.BlockFamily
import net.minecraft.world.item.DyeColor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.ButtonBlock
import net.minecraft.world.level.block.PressurePlateBlock
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.BlockSetType
import org.jetbrains.annotations.ApiStatus

open class GenerationsFullBlockSet : GenerationsBlockSet {
    private val button: RegistrySupplier<ButtonBlock>
    private val pressurePlate: RegistrySupplier<PressurePlateBlock>

    constructor(
        name: String,
        properties: BlockBehaviour.Properties,
        type: BlockSetType,
        baseBlock: RegistrySupplier<Block>
    ) : super(name, baseBlock, properties) {
        button = registerBlockItem(
            name + "_button"
        ) { ButtonBlock(type, 20, properties) }
        pressurePlate = registerBlockItem(
            name + "_pressure_plate"
        ) { PressurePlateBlock(type, properties) }
        blockSets.remove(this)
        fullBlockSets.add(this)
    }

    constructor(name: String, properties: BlockBehaviour.Properties, type: BlockSetType) : super(name, properties) {
        button = registerBlockItem(
            name + "_button"
        ) { ButtonBlock(type, 20, properties) }
        pressurePlate = registerBlockItem(
            name + "_pressure_plate"
        ) { PressurePlateBlock(type, properties) }
        blockSets.remove(this)
        fullBlockSets.add(this)
    }

    /**
     * Creates a new Generations block set with the default properties from Blocks#STONE.
     * @param name The name of the base block.
     * @param type The type of the block set.
     */
    constructor(name: String, type: BlockSetType) : this(name, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE), type)

    /**
     * Creates a new Generations block set with the default properties from Blocks#STONE.
     * @param name The name of the base block.
     * @param type The type of the block set.
     * @param color The map color of the block set.
     */
    constructor(name: String, color: DyeColor, type: BlockSetType) : this(
        name, BlockBehaviour.Properties.ofFullCopy(
            Blocks.STONE
        ).mapColor(color), type
    )

    /**
     * Gets the button.
     * @return The button.
     */
    fun getButton(): ButtonBlock {
        return button.get()
    }

    /**
     * Gets the pressure plate.
     * @return The pressure plate.
     */
    fun getPressurePlate(): PressurePlateBlock {
        return pressurePlate.get()
    }

    /**
     * Returns a list of the full family
     * @return The full family
     */
    override fun getAllBlocks(): List<Block> {
        return java.util.List.of(
            baseBlock,
            slab, stairs, wall, getButton(), getPressurePlate()
        )
    }


    fun updateBlockFamily() {
        this.blockFamily =
            BlockFamily.Builder(baseBlock).slab(slab).stairs(stairs).wall(
                wall
            ).button(getButton()).pressurePlate(getPressurePlate()).recipeGroupPrefix(name)
                .recipeUnlockedBy("has_$name").family
    }

    companion object {
        @JvmField
        val fullBlockSets: ArrayList<GenerationsFullBlockSet> = ArrayList()

        @JvmStatic
        @ApiStatus.Internal
        fun updateBlockFamilies() {
            for (blockSet in fullBlockSets) blockSet.updateBlockFamily()
        }
    }
}
