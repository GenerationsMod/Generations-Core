package generations.gg.generations.core.generationscore.world.entity.block;

import dev.architectury.networking.NetworkManager;
import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SittableEntity extends Entity {

    private BlockState blockState;

    public SittableEntity(EntityType<? extends Entity> type, Level level) {
        super(type, level);
    }

    private SittableEntity(Level level, BlockPos pos, double offset) {
        this(GenerationsEntities.SEAT.get(), level);
        this.blockState = level.getBlockState(pos);
        this.setPos(pos.getX() + 0.5, pos.getY() + offset, pos.getZ() + 0.5);
    }

    // Call to mount the player to a newly created SittableEntity
    public static InteractionResult mount(Level level, BlockPos pos, double offset, Player player) {
        if (!level.isClientSide()) {
            List<SittableEntity> list = level.getEntitiesOfClass(SittableEntity.class, new AABB(pos.getX(), pos.getY() - 1, pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1));
            if (list.isEmpty()) {
                SittableEntity seat = new SittableEntity(level, pos, offset);
                level.addFreshEntity(seat);
                player.startRiding(seat);
            }
        }
        return InteractionResult.SUCCESS;
    }

    // Tick the key and check if the block is removed or if there are no more passengers
    @Override
    public void tick() {
        super.tick();

        if (blockState == null)
            blockState = level().getBlockState(blockPosition());

        if (!level().isClientSide)
            if (getPassengers().isEmpty() || blockState.isAir()) kill();
    }

    @Override
    public double getPassengersRidingOffset() {
        return 0.0;
    }

    @Override
    protected boolean canRide(@NotNull Entity entity) {
        return entity instanceof Player;
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkManager.createAddEntityPacket(this);
    }

    // Not sure what these do, but they are here
    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag compound) {

    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag compound) {

    }
}