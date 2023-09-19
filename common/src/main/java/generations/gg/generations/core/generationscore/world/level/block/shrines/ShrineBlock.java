package generations.gg.generations.core.generationscore.world.level.block.shrines;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.ShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ShrineBlock<T extends ShrineBlockEntity> extends GenericRotatableModelBlock<T> {

    protected ShrineBlock(Properties materialIn, RegistrySupplier<BlockEntityType<T>> blockEntityFunction, ResourceLocation model) {
        super(materialIn, blockEntityFunction, model);
    }

    public String getActiveVariant(boolean active) {
        return null;
    }
}
