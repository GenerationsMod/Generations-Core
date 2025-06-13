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
     * Creates a new OreSet
     *
     * @param name The name of the Ore
     */
    constructor(name: String) {
        this.name = name
        drop = null
        ore = GenerationsOres.registerOreBlockItem(name,
            DropExperienceBlock(
                ConstantInt.of(20),
                BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
            ))
        deepslateOre = GenerationsOres.registerOreBlockItem("deepslate_$name",
            DropExperienceBlock(
                ConstantInt.of(20),
                BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)
            ))

        //chargeStoneOre = GenerationsOres.registerOreBlockItem("charge_stone_" + name, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).dropsLike(ore.get())));
    }

    val ore: DropExperienceBlock
    val deepslateOre: DropExperienceBlock
    val drop: Item?
    //private final RegistrySupplier<DropExperienceBlock> chargeStoneOre;

    /**
     * Creates a new OreSet
     *
     * @param name The name of the Ore
     * @param drop The drop of the Ore
     * @param xpRange The xp range of the Ore
     */
    constructor(name: String, drop: Item, xpRange: IntProvider) {
        this.name = name
        this.drop = drop
        ore = GenerationsOres.registerOreBlockItem(name,
            DropExperienceBlock(
                xpRange,
                BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
            ))
        deepslateOre = GenerationsOres.registerOreBlockItem("deepslate_$name",
            DropExperienceBlock(
                xpRange,
                BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)
            ))

        //chargeStoneOre = GenerationsOres.registerOreBlockItem("charge_stone_" + name, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).dropsLike(ore.get())));
    }

    /**
     * Creates a new OreSet
     *
     * @param name The name of the Ore
     * @param drop The drop of the Ore
     */
    constructor(name: String, drop: Item) {
        this.name = name
        this.drop = drop
        ore = GenerationsOres.registerOreBlockItem(name,
            DropExperienceBlock(
                ConstantInt.of(20),
                BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
            ))
        deepslateOre = GenerationsOres.registerOreBlockItem("deepslate_$name",
            DropExperienceBlock(
                ConstantInt.of(20),
                BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)
            ))
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
