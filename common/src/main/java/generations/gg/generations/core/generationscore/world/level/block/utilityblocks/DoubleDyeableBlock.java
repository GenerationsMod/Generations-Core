package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.entities.DyedVariantBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Function;

public abstract class DoubleDyeableBlock<T extends DyedVariantBlockEntity<?>, V extends DoubleDyeableBlock<T, V>> extends DyeableBlock<T, V> {
    public DoubleDyeableBlock(Function<DyeColor, DyedBlockItem<T, V>> function, RegistrySupplier<BlockEntityType<T>> biFunction, Properties arg, ResourceLocation model) {
        super(function, biFunction, arg, model, 0, 1, 0);
    }
}
