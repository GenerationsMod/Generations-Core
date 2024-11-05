package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.ModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MutableBlockEntityType;
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.InteractShrineBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.ShrineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class InteractShrineBlockEntity extends ShrineBlockEntity {
    private int countdown;

    public InteractShrineBlockEntity(BlockPos arg2, BlockState arg3) {
        super(GenerationsBlockEntities.INTERACT_SHRINE.get(), arg2, arg3);
    }

    public InteractShrineBlockEntity(MutableBlockEntityType<? extends ModelProvidingBlockEntity> arg, BlockPos arg2, BlockState arg3) {
        super(arg, arg2, arg3);
    }

    @Override
    public String getVariant() {
        return getBlockState().getBlock() instanceof InteractShrineBlock<?> shrine ? shrine.getVariant(getBlockState()) : null;
    }

    public void triggerCountDown() {
        if(getBlockState().getBlock() instanceof InteractShrineBlock<?> shrine) {
            if(shrine.waitToDeactivateTime() > 0) {
                System.out.println("Activating countdown");

                this.countdown = shrine.waitToDeactivateTime();
            }
        }
    }

    protected void tick() {
        if(getBlockState().getBlock() instanceof InteractShrineBlock<?> shrine) {
            if(this.countdown > 0) {
                System.out.println(getClass().getSimpleName() + ": " + countdown);

                this.countdown--;

                if (countdown == 0) {
                    shrine.deactivate(getLevel(), getBlockPos(), getBlockState());
                }
            }
        }
    }

    public static <T extends InteractShrineBlockEntity> void serverTick(Level level, BlockPos pos, BlockState blockState, T t) {
        t.tick();
    }
}
