package generations.gg.generations.core.generationscore.common.world.entity.block;

import dev.architectury.networking.NetworkManager;
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SittableEntity extends Entity {

    public SittableEntity(EntityType<? extends Entity> type, Level level) {
        super(type, level);
        this.noPhysics = true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    private SittableEntity(Level level, BlockPos pos, double offset, float yaw) {
        this(GenerationsEntities.SEAT.get(), level);
        this.setPos(pos.getX() + 0.5, pos.getY() + offset, pos.getZ() + 0.5);
        this.setRot(yaw, 0F);
    }

    private int removalDelay = 5; // Wait 5 ticks before removal

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            if (this.getPassengers().isEmpty()) {
                if (removalDelay > 0) {
                    removalDelay--; // Countdown before deletion
                } else {
                    this.remove(RemovalReason.DISCARDED);
                }
            } else {
                removalDelay = 5; // Reset countdown if occupied
            }

            if (this.level().isEmptyBlock(this.blockPosition())) {
                this.remove(RemovalReason.DISCARDED);
                this.level().updateNeighbourForOutputSignal(blockPosition(), this.level().getBlockState(blockPosition()).getBlock());
            }
        }
    }

//    @Override
//    public void tick() {
//        super.tick();
//        if(!this.level().isClientSide) {
//            if(this.getPassengers().isEmpty() || this.level().isEmptyBlock(this.blockPosition())) {
//                this.remove(RemovalReason.DISCARDED);
//                this.level().updateNeighbourForOutputSignal(blockPosition(), this.level().getBlockState(blockPosition()).getBlock());
//            }
//        }
//    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag compound) {}

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag compound) {}

    @Override
    public Vec3 getPassengerRidingPosition(Entity entity) {
        return super.getPassengerRidingPosition(entity).add(0.0, getPassengersRidingOffset(), 0.0);
    }

    public double getPassengersRidingOffset()
    {
        return 0.0;
    }

    @Override
    protected boolean canRide(@NotNull Entity entity)
    {
        return true;
    }

    // Call to mount the player to a newly created SittableEntity
    public static ItemInteractionResult mount(Level level, BlockPos pos, double yOffset, Player player, float direction) {
        if(level instanceof ServerLevel serverLevel) {
            List<SittableEntity> seats = level.getEntitiesOfClass(SittableEntity.class, new AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 1.0));
            if(seats.isEmpty()) {
                SittableEntity seat = new SittableEntity(level, pos, yOffset, direction);
                if(level.addFreshEntity(seat)) {
                    player.startRiding(seat);
                    return ItemInteractionResult.SUCCESS;
                } else {
                    return ItemInteractionResult.FAIL;
                }
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }


    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entity) {
        return NetworkManager.createAddEntityPacket(this, entity);
    }

    // Tick the key and check if the block is removed or if there are no more passengers
    @Override
    public @NotNull Vec3 getDismountLocationForPassenger(@NotNull LivingEntity entity) {
        Direction original = this.getDirection();
        Direction[] offsets = {original, original.getClockWise(), original.getCounterClockWise(), original.getOpposite()};
        for(Direction dir : offsets) {
            Vec3 safeVec = DismountHelper.findSafeDismountLocation(entity.getType(), this.level(), this.blockPosition().relative(dir), false);
            if(safeVec != null)
            {
                return safeVec.add(0, 0.25, 0);
            }
        }

        return super.getDismountLocationForPassenger(entity);
    }

    @Override
    protected void addPassenger(@NotNull Entity entity) {
        super.addPassenger(entity);
        entity.setYRot(this.getYRot());
    }

    @Override
    public void positionRider(@NotNull Entity entity, Entity.@NotNull MoveFunction function) {
        super.positionRider(entity, function);
        this.clampYaw(entity);
    }

    @Override
    public void onPassengerTurned(@NotNull Entity entity) {
        this.clampYaw(entity);
    }

    private void clampYaw(Entity passenger) {
        passenger.setYBodyRot(this.getYRot());
        float wrappedYaw = Mth.wrapDegrees(passenger.getYRot() - this.getYRot());
        float clampedYaw = Mth.clamp(wrappedYaw, -120.0F, 120.0F);
        passenger.yRotO += clampedYaw - wrappedYaw;
        passenger.setYRot(passenger.getYRot() + clampedYaw - wrappedYaw);
        passenger.setYHeadRot(passenger.getYRot());
    }
}