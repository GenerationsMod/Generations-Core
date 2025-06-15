package generations.gg.generations.core.generationscore.common.world.entity.block

import dev.architectury.networking.SpawnEntityPacket
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities
import generations.gg.generations.core.generationscore.common.world.entity.block.SittableEntity
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerEntity
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.Mth
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.vehicle.DismountHelper
import net.minecraft.world.level.Level
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3

class SittableEntity(type: EntityType<out Entity?>, level: Level) :
    Entity(type, level) {
    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
    }

    private constructor(level: Level, pos: BlockPos, offset: Double, yaw: Float) : this(
        GenerationsEntities.SEAT,
        level
    ) {
        this.setPos(pos.x + 0.5, pos.y + offset, pos.z + 0.5)
        this.setRot(yaw, 0f)
    }

    private var removalDelay = 5 // Wait 5 ticks before removal

    init {
        this.noPhysics = true
    }

    override fun tick() {
        super.tick()
        if (!level().isClientSide) {
            if (passengers.isEmpty()) {
                if (removalDelay > 0) {
                    removalDelay-- // Countdown before deletion
                } else {
                    this.remove(RemovalReason.DISCARDED)
                }
            } else {
                removalDelay = 5 // Reset countdown if occupied
            }

            if (level().isEmptyBlock(this.blockPosition())) {
                this.remove(RemovalReason.DISCARDED)
                level().updateNeighbourForOutputSignal(blockPosition(), level().getBlockState(blockPosition()).block)
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
    override fun readAdditionalSaveData(compound: CompoundTag) {}

    override fun addAdditionalSaveData(compound: CompoundTag) {}

    override fun getPassengerRidingPosition(entity: Entity): Vec3 {
        return super.getPassengerRidingPosition(entity).add(0.0, passengersRidingOffset, 0.0)
    }

    val passengersRidingOffset: Double
        get() = 0.0

    override fun canRide(entity: Entity): Boolean {
        return true
    }

    override fun getAddEntityPacket(entity: ServerEntity): Packet<ClientGamePacketListener> {
        return SpawnEntityPacket.create(this, entity)
    }

    // Tick the key and check if the block is removed or if there are no more passengers
    override fun getDismountLocationForPassenger(entity: LivingEntity): Vec3 {
        val original = this.direction
        val offsets = arrayOf(original, original.clockWise, original.counterClockWise, original.opposite)
        for (dir in offsets) {
            val safeVec = DismountHelper.findSafeDismountLocation(
                entity.type, this.level(),
                blockPosition().relative(dir), false
            )
            if (safeVec != null) {
                return safeVec.add(0.0, 0.25, 0.0)
            }
        }

        return super.getDismountLocationForPassenger(entity)
    }

    override fun addPassenger(entity: Entity) {
        super.addPassenger(entity)
        entity.yRot = yRot
    }

    public override fun positionRider(entity: Entity, function: MoveFunction) {
        super.positionRider(entity, function)
        this.clampYaw(entity)
    }

    override fun onPassengerTurned(entity: Entity) {
        this.clampYaw(entity)
    }

    private fun clampYaw(passenger: Entity) {
        passenger.setYBodyRot(this.yRot)
        val wrappedYaw = Mth.wrapDegrees(passenger.yRot - this.yRot)
        val clampedYaw = Mth.clamp(wrappedYaw, -120.0f, 120.0f)
        passenger.yRotO += clampedYaw - wrappedYaw
        passenger.yRot = passenger.yRot + clampedYaw - wrappedYaw
        passenger.yHeadRot = passenger.yRot
    }

    companion object {
        // Call to mount the player to a newly created SittableEntity
        @JvmStatic
        fun mount(
            level: Level,
            pos: BlockPos,
            yOffset: Double,
            player: Player,
            direction: Float
        ): Boolean? {
            if (level is ServerLevel) {
                val seats = level.getEntitiesOfClass(
                    SittableEntity::class.java,
                    AABB(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), pos.x + 1.0, pos.y + 1.0, pos.z + 1.0)
                )
                if (seats.isEmpty()) {
                    val seat = SittableEntity(level, pos, yOffset, direction)
                    if (level.addFreshEntity(seat)) {
                        player.startRiding(seat)
                        return true
                    } else {
                        return false
                    }
                }
            }

            return null
        }
    }
}