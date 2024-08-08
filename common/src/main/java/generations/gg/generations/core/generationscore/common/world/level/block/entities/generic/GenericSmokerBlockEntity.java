package generations.gg.generations.core.generationscore.common.world.level.block.entities.generic;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsCookers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SmokerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class GenericSmokerBlockEntity extends SmokerBlockEntity implements GenerationsCookers {
    private final String name;
    public GenericSmokerBlockEntity(BlockPos arg, BlockState arg2) {
        super(arg, arg2);
        this.name = arg2.getBlock().getDescriptionId().replace("block.generations_core.", "");
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