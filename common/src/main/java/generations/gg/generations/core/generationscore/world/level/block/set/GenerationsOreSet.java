package generations.gg.generations.core.generationscore.world.level.block.set;

import com.google.common.collect.ImmutableList;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsOres;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import javax.annotation.Nullable;

/**
 * A set of OreBlocks
 * @author Joseph T. McQuigg
 */
public class GenerationsOreSet {
    private final String name;
    private final RegistrySupplier<DropExperienceBlock> ore;
    private final RegistrySupplier<DropExperienceBlock> deepslateOre;
    //private final RegistrySupplier<DropExperienceBlock> chargeStoneOre;
    private final RegistrySupplier<Item> drop;

    /**
     * Creates a new OreSet
     *
     * @param name The name of the Ore
     */
    public GenerationsOreSet(String name) {
        this.name = name;
        drop = null;
        ore = GenerationsOres.registerOreBlockItem(name, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
        deepslateOre = GenerationsOres.registerOreBlockItem("deepslate_" + name , () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
        //chargeStoneOre = GenerationsOres.registerOreBlockItem("charge_stone_" + name, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).dropsLike(ore.get())));
    }

    /**
     * Creates a new OreSet
     *
     * @param name The name of the Ore
     * @param drop The drop of the Ore
     * @param xpRange The xp range of the Ore
     */
    public GenerationsOreSet(String name, RegistrySupplier<Item> drop, IntProvider xpRange) {
        this.name = name;
        this.drop = drop;
        ore = GenerationsOres.registerOreBlockItem(name, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE), xpRange));
        deepslateOre = GenerationsOres.registerOreBlockItem("deepslate_" + name , () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE), xpRange));
        //chargeStoneOre = GenerationsOres.registerOreBlockItem("charge_stone_" + name, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).dropsLike(ore.get())));
    }

    /**
     * Creates a new OreSet
     *
     * @param name The name of the Ore
     * @param drop The drop of the Ore
     */
    public GenerationsOreSet(String name, RegistrySupplier<Item> drop) {
        this.name = name;
        this.drop = drop;
        ore = GenerationsOres.registerOreBlockItem(name, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
        deepslateOre = GenerationsOres.registerOreBlockItem("deepslate_" + name , () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
        //chargeStoneOre = GenerationsOres.registerOreBlockItem("charge_stone_" + name, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).dropsLike(ore.get())));
    }

    /**
     * Gets the name of the Ore
     * @return The name of the Ore
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the Ore
     * @return The Ore
     */
    public DropExperienceBlock getOre() {
        return ore.get();
    }

    /**
     * Gets the Ore as a RegistrySupplier
     * @return The Ore as a RegistrySupplier
     */
    public RegistrySupplier<DropExperienceBlock> getOreSupplier() {
        return ore;
    }

    /**
     * Gets the Deepslate Ore
     * @return The Deepslate Ore
     */
    public DropExperienceBlock getDeepslateOre() {
        return deepslateOre.get();
    }

    /*
    public RegistrySupplier<DropExperienceBlock> getChargeStoneOre() {
        return chargeStoneOre;
    }
     */

    /**
     * Makes an ImmutableList of the Ore and Deepslate Ore
     * @return An ImmutableList of the Ore and Deepslate Ore
     */
    public ImmutableList<ItemLike> getImmutableList() {
        return ImmutableList.of(ore.get(), deepslateOre.get());
    }

    /**
     * Gets the drop of the Ore
     * @return The drop of the Ore
     */
    public @Nullable Item getDrop() {
        return drop.get();
    }
}
