package generations.gg.generations.core.generationscore.world.level.block.entities.generic;

import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModCookers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SmokerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class GenericSmokerBlockEntity extends SmokerBlockEntity implements PokeModCookers {
    private final String name;
    public GenericSmokerBlockEntity(BlockPos arg, BlockState arg2) {
        super(arg, arg2);
        this.name = arg2.getBlock().getDescriptionId().replace("block.pokemod.", "");
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container." + name);
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return GenerationsBlockEntities.GENERIC_SMOKER.get();
    }
}