package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.entities.DyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.ModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.function.Supplier;

public record DyedGroup<V extends DyeableBlock<T, V>, T extends ModelProvidingBlockEntity>(Map<DyeColor, RegistrySupplier<V>> block)  {
    public Block[] toArray() {
        return block().values().stream().map(Supplier::get).map(a -> (Block) a).toArray(Block[]::new);
    }
}
