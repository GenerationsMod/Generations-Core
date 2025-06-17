package generations.gg.generations.core.generationscore.common.world.level.block.set

import com.google.common.collect.ImmutableList
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsOres
import net.minecraft.core.Holder
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
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
     * Creates a new OreSet
     *
     * @param name The name of the Ore
     */
    constructor(name: String) {
        this.name = name
        dropHolder = null
        oreHolder = GenerationsOres.registerOreBlockItem(name, {
            DropExperienceBlock(
                ConstantInt.of(20),
                BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
            ) })
        deepslateOreHolder = GenerationsOres.registerOreBlockItem("deepslate_$name", {
            DropExperienceBlock(
                ConstantInt.of(20),
                BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)
            ) })

        //chargeStoneOre = GenerationsOres.registerOreBlockItem("charge_stone_" + name, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).dropsLike(ore.get())));
    }

    val oreHolder: Holder<Block>
    val deepslateOreHolder: Holder<Block>
    val dropHolder: Holder<Item>?

    //private final RegistrySupplier<DropExperienceBlock> chargeStoneOre;

    val ore: Block get() = oreHolder.value()
    val deepslateOre: Block get() = deepslateOreHolder.value()
    val drop: Item? get() = dropHolder?.value()

    /**
     * Creates a new OreSet
     *
     * @param name The name of the Ore
     * @param drop The drop of the Ore
     * @param xpRange The xp range of the Ore
     */
    constructor(name: String, drop: Holder<Item>, xpRange: IntProvider) {
        this.name = name
        this.dropHolder = drop
        oreHolder = GenerationsOres.registerOreBlockItem(name,
            { DropExperienceBlock(
                xpRange,
                BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
            ) })
        deepslateOreHolder = GenerationsOres.registerOreBlockItem("deepslate_$name",
            { DropExperienceBlock(
                xpRange,
                BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)
            ) })

        //chargeStoneOre = GenerationsOres.registerOreBlockItem("charge_stone_" + name, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).dropsLike(ore.get())));
    }

    /**
     * Creates a new OreSet
     *
     * @param name The name of the Ore
     * @param drop The drop of the Ore
     */
    constructor(name: String, drop: Holder<Item>) {
        this.name = name
        this.dropHolder = drop
        oreHolder = GenerationsOres.registerOreBlockItem(name, {
            DropExperienceBlock(
                ConstantInt.of(20),
                BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
            ) })
        deepslateOreHolder = GenerationsOres.registerOreBlockItem("deepslate_$name", {
            DropExperienceBlock(
                ConstantInt.of(20),
                BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)
            ) })
        //chargeStoneOre = GenerationsOres.registerOreBlockItem("charge_stone_" + name, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).dropsLike(ore.get())));
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
            ore,
            deepslateOre
        )
}
