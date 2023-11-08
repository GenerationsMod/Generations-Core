package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.entities.DyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.world.item.DyeColor;

import java.util.HashMap;

public record DyedGroup<V extends DyeableBlock<T, V>, T extends DyedVariantBlockEntity<?>>(RegistrySupplier<DyeableBlock<T, V>> block, HashMap<DyeColor, RegistrySupplier<DyedBlockItem<T, V>>> dyeMap)  {
}
