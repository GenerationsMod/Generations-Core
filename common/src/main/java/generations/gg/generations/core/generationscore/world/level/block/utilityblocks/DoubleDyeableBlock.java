package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import com.pokemod.pokemod.world.item.DyedBlockItem;
import com.pokemod.pokemod.world.level.block.entities.DyedVariantBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public abstract class DoubleDyeableBlock<T extends DyedVariantBlockEntity, V extends DoubleDyeableBlock<T, V>> extends DyeableBlock<T, V> {
    public DoubleDyeableBlock(Function<DyeColor, DyedBlockItem<V>> function, RegistryObject<BlockEntityType<T>> biFunction, Properties arg, ResourceLocation model) {
        super(function, biFunction, arg, model, 0, 1, 0);
    }
}
