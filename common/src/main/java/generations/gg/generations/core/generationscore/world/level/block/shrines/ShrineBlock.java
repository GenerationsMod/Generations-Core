package generations.gg.generations.core.generationscore.world.level.block.shrines;

import com.pokemod.pokemod.world.level.block.entities.ShrineBlockEntity;
import com.pokemod.pokemod.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class ShrineBlock<T extends ShrineBlockEntity> extends GenericRotatableModelBlock<T> {

    protected ShrineBlock(Properties materialIn, RegistryObject<BlockEntityType<T>> blockEntityFunction, ResourceLocation model) {
        super(materialIn, blockEntityFunction, model);
    }

    public String getActiveVariant(boolean active) {
        return active ? "active" : "inactive";
    }
}
