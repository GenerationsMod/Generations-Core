package generations.gg.generations.core.generationscore.common.world.level.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class GenerationsSignBlockEntity extends SignBlockEntity {
    public GenerationsSignBlockEntity(BlockPos arg, BlockState arg2) {
        super(arg, arg2);
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return GenerationsBlockEntities.SIGN_BLOCK_ENTITIES.get();
    }
}
