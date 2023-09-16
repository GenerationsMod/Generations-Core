package generations.gg.generations.core.generationscore.world.entity.ai.goal;

import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class RandomWanderAroundPosGoal extends Goal {

    private final PlayerNpcEntity npcEntity;
    private BlockPos wantedPos;
    private final double minimumWalk;
    private int nextStartTick;
    private final BlockPos origin;
    private final int radius;
    private final int delay;
    private final float moveSpeed;

    public RandomWanderAroundPosGoal(PlayerNpcEntity npcEntity) {
        this.npcEntity = npcEntity;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        this.minimumWalk = 2.0D;
        this.origin = npcEntity.getDisplayData().getMovementInfo().getOrigin();
        this.radius = npcEntity.getDisplayData().getMovementInfo().getRadius();
        this.delay = npcEntity.getDisplayData().getMovementInfo().getCooldownTicks();
        this.moveSpeed = npcEntity.getDisplayData().getMovementInfo().getMoveSpeed();
    }

    public boolean canUse() {
        if (this.npcEntity.isVehicle()) {
            return false;
        } else if (this.nextStartTick > 0) {
            --this.nextStartTick;
            return false;
        } else {
            // LandRandomPos generates a random position. 15 horizontal 7 vertical The function at the end takes in a block position
            // and returns a weight that this should be taken. After 10 attempts, it will always take the one that
            // was most suitable.
            Vec3 vec3 = LandRandomPos.getPos(this.npcEntity, 15, 7, (blockPos) ->
                    blockPos.distManhattan(origin) > radius ? -1 : 0);
            if (vec3 == null) {
                this.wantedPos = null;
            } else {
                // Final pass to reject all positions that were too high.
                BlockPos blockPos = new BlockPos((int) vec3.x, (int) vec3.y, (int) vec3.z);
                this.wantedPos = origin.distManhattan(blockPos) > radius ? null : blockPos;
                this.nextStartTick = this.delay;
            }
            return this.wantedPos != null;
        }
    }

    public boolean canContinueToUse() {
        return this.wantedPos != null && !this.npcEntity.getNavigation().isDone() && this.npcEntity.getNavigation().getTargetPos().equals(this.wantedPos);
    }

    public void tick() {
        if (this.wantedPos != null) {
            PathNavigation pathnavigation = this.npcEntity.getNavigation();
            if (pathnavigation.isDone() && !this.wantedPos.closerThan(this.npcEntity.blockPosition(), minimumWalk)) {
                Vec3 vec3 = Vec3.atBottomCenterOf(this.wantedPos);
                Vec3 vec31 = this.npcEntity.position();
                Vec3 vec32 = vec31.subtract(vec3);
                vec3 = vec32.scale(0.4D).add(vec3);
                Vec3 vec33 = vec3.subtract(vec31).normalize().scale(10.0D).add(vec31);
                BlockPos blockpos = new BlockPos((int) vec33.x, (int) vec33.y, (int) vec33.z);
                blockpos = this.npcEntity.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, blockpos);
                if (!pathnavigation.moveTo(blockpos.getX(), blockpos.getY(), blockpos.getZ(), moveSpeed)) {
                    this.moveRandomly();
                }
            }

        }
    }

    private void moveRandomly() {
        RandomSource random = this.npcEntity.getRandom();
        BlockPos blockpos = this.npcEntity.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, this.npcEntity.blockPosition().offset(-8 + random.nextInt(16), 0, -8 + random.nextInt(16)));
        this.npcEntity.getNavigation().moveTo(blockpos.getX(), blockpos.getY(), blockpos.getZ(), moveSpeed);
    }
}