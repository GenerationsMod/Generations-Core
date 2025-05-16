package generations.gg.generations.core.generationscore.common.world.level.block.set

import com.google.common.collect.ImmutableList
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsOres
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DropExperienceBlock
import net.minecraft.world.level.block.state.BlockBehaviour

/**
 * A set of OreBlocks
 * @author Joseph T. McQuigg
 */
class GenerationsOreSet {
    /**
     * Gets the name of the Ore
     * @return The name of the Ore
     */
    val name: String

    /**
     * Gets the Ore as a RegistrySupplier
     * @return The Ore as a RegistrySupplier
     */
    val oreSupplier: RegistrySupplier<DropExperienceBlock>
    private val deepslateOre: RegistrySupplier<DropExperienceBlock>

    //private final RegistrySupplier<DropExperienceBlock> chargeStoneOre;
    private val drop: RegistrySupplier<Item>?

    /**
     * Creates a new OreSet
     *
     * @param name The name of the Ore
     */
    constructor(name: String) {
        this.name = name
        drop = null
        oreSupplier = GenerationsOres.registerOreBlockItem(
            name
        ) {
            DropExperienceBlock(
                ConstantInt.of(20),
                BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
            )
        }
        deepslateOre = GenerationsOres.registerOreBlockItem(
            "deepslate_$name"
        ) {
            DropExperienceBlock(
                ConstantInt.of(20),
                BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)
            )
        }
        //chargeStoneOre = GenerationsOres.registerOreBlockItem("charge_stone_" + name, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).dropsLike(ore.get())));
    }

    /**
     * Creates a new OreSet
     *
     * @param name The name of the Ore
     * @param drop The drop of the Ore
     * @param xpRange The xp range of the Ore
     */
    constructor(name: String, drop: RegistrySupplier<Item>, xpRange: IntProvider) {
        this.name = name
        this.drop = drop
        oreSupplier = GenerationsOres.registerOreBlockItem(
            name
        ) {
            DropExperienceBlock(
                xpRange,
                BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
            )
        }
        deepslateOre = GenerationsOres.registerOreBlockItem(
            "deepslate_$name"
        ) {
            DropExperienceBlock(
                xpRange,
                BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)
            )
        }
        //chargeStoneOre = GenerationsOres.registerOreBlockItem("charge_stone_" + name, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).dropsLike(ore.get())));
    }

    /**
     * Creates a new OreSet
     *
     * @param name The name of the Ore
     * @param drop The drop of the Ore
     */
    constructor(name: String, drop: RegistrySupplier<Item>) {
        this.name = name
        this.drop = drop
        oreSupplier = GenerationsOres.registerOreBlockItem(
            name
        ) {
            DropExperienceBlock(
                ConstantInt.of(20),
                BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
            )
        }
        deepslateOre = GenerationsOres.registerOreBlockItem(
            "deepslate_$name"
        ) {
            DropExperienceBlock(
                ConstantInt.of(20),
                BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)
            )
        }
        //chargeStoneOre = GenerationsOres.registerOreBlockItem("charge_stone_" + name, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).dropsLike(ore.get())));
    }

    /**
     * Gets the Ore
     * @return The Ore
     */
    fun getOre(): DropExperienceBlock {
        return oreSupplier.get()
    }

    /**
     * Gets the Deepslate Ore
     * @return The Deepslate Ore
     */
    fun getDeepslateOre(): DropExperienceBlock {
        return deepslateOre.get()
    }

    /*
    public RegistrySupplier<DropExperienceBlock> getChargeStoneOre() {
        return chargeStoneOre;
    }
     */
    val immutableList: ImmutableList<ItemLike>
        /**
         * Makes an ImmutableList of the Ore and Deepslate Ore
         * @return An ImmutableList of the Ore and Deepslate Ore
         */
        get() = ImmutableList.of(
            oreSupplier.get(),
            deepslateOre.get()
        )

    /**
     * Gets the drop of the Ore
     * @return The drop of the Ore
     */
    fun getDrop(): Item? {
        return drop!!.get()
    }
}
