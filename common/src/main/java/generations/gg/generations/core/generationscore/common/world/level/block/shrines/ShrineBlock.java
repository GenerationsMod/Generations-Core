package generations.gg.generations.core.generationscore.common.world.level.block.shrines;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MutableBlockEntityType;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.ShrineBlockEntity;
import net.minecraft.resources.ResourceLocation;

public class ShrineBlock<T extends ShrineBlockEntity> extends GenericRotatableModelBlock<T> {
    protected ShrineBlock(Properties materialIn, RegistrySupplier<MutableBlockEntityType<T>> blockEntityFunction, ResourceLocation model, int width, int height, int length) {
        super(materialIn, blockEntityFunction, model, width, height, length);
        reassignStateDefinition();
        this.registerDefaultState(createDefaultState());
    }

    protected ShrineBlock(Properties materialIn, RegistrySupplier<MutableBlockEntityType<T>> blockEntityFunction, ResourceLocation model) {
        super(materialIn, blockEntityFunction, model);
    }

    public boolean isActivatable() {
        return false;
    }
}
