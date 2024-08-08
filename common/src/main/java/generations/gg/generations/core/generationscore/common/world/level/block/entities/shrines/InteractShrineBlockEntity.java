package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.ModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MutableBlockEntityType;
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.ShrineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.block.state.BlockState;

public abstract class InteractShrineBlockEntity extends ShrineBlockEntity {
    public InteractShrineBlockEntity(MutableBlockEntityType<? extends ModelProvidingBlockEntity> arg, BlockPos arg2, BlockState arg3) {
        super(arg, arg2, arg3);
    }

    public abstract boolean activate(ServerPlayer player, InteractionHand hand, ShrineBlock.ActivationState activationState);
}
