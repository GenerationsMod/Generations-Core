package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.entities.DyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.ModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.world.item.DyeColor;

import java.util.Map;

public record DyedGroup<V extends DyeableBlock<T, V>, T extends ModelProvidingBlockEntity>(Map<DyeColor, RegistrySupplier<V>> block)  {
}
