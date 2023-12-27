package generations.gg.generations.core.generationscore.world.level.block.set;

import com.google.common.collect.ImmutableList;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsOres;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class GenerationsOreSet {
    private final String name;
    private final RegistrySupplier<DropExperienceBlock> ore;
    private final RegistrySupplier<DropExperienceBlock> deepslateOre;
    //private final RegistrySupplier<DropExperienceBlock> chargeStoneOre;

    public GenerationsOreSet(String name) {
        this.name = name;
        ore = GenerationsOres.registerOreBlockItem(name, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
        deepslateOre = GenerationsOres.registerOreBlockItem("deepslate_" + name , () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
        //chargeStoneOre = GenerationsOres.registerOreBlockItem("charge_stone_" + name, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).dropsLike(ore.get())));
    }

    public String getName() {
        return name;
    }

    public DropExperienceBlock getOre() {
        return ore.get();
    }

    public DropExperienceBlock getDeepslateOre() {
        return deepslateOre.get();
    }

    /*
    public RegistrySupplier<DropExperienceBlock> getChargeStoneOre() {
        return chargeStoneOre;
    }
     */

    public ImmutableList<ItemLike> getImmutableList() {
        return ImmutableList.of(ore.get(), deepslateOre.get());
    }
}
