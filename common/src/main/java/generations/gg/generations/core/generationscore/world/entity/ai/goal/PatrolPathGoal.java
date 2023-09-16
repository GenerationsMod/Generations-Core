package generations.gg.generations.core.generationscore.world.entity.ai.goal;

import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class PatrolPathGoal extends Goal {
    protected int nextStartTick;
    protected int tryTicks;
    protected int index;
    protected int direction;
    protected int pathSize;
    protected final boolean isLooping;
    protected BlockPos[] path;
    protected final PlayerNpcEntity npcEntity;

    public PatrolPathGoal(PlayerNpcEntity npc) {
        this.npcEntity = npc;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP));
        this.index = 0;
        this.path = this.npcEntity.getDisplayData().getMovementInfo().getPath().toArray(BlockPos[]::new);
        this.pathSize = this.path.length;
        this.direction = 1;
        this.isLooping = path[0].equals(path[pathSize - 1]);
    }

    /** For some ungodly reason, the path navigator checks if the manhattan distance is less than 1.0f
     * which is not necessarily true for the tick check. So if accepted distance is < 3, the npc will get
     * stuck just outside the range accepted check.
     */
    public double acceptedDistance() {
        return 3.0;
    }

    public boolean canUse() {
        if (this.nextStartTick > 0) {
            --this.nextStartTick;
            return false;
        } else {
            this.nextStartTick = this.nextStartTick();
            return true;
        }
    }

    public void start() {
        BlockPos blockpos = this.getMoveToTarget();
        this.moveMobToBlock(blockpos);
        this.tryTicks = 0;
    }

    protected void moveMobToBlock(BlockPos blockPos) {
        this.npcEntity.getNavigation().moveTo((float)blockPos.getX() + 0.5D, blockPos.getY()+0.5D, (float)blockPos.getZ() + 0.5D, this.npcEntity.getDisplayData().getMovementInfo().getMoveSpeed());
    }

    protected int nextStartTick() {
        return npcEntity.getDisplayData().getMovementInfo().getCooldownTicks();
    }

    protected BlockPos getMoveToTarget() {
        if (pathSize <= 0 || this.index >= pathSize) return this.npcEntity.blockPosition();
        return path[index].above();
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        BlockPos blockpos = this.getMoveToTarget();
        if (!blockpos.closerToCenterThan(this.npcEntity.position(), this.acceptedDistance())) {
            ++this.tryTicks;
            if (this.tryTicks % 40 == 0 || this.npcEntity.getNavigation().isDone()) { // Limit recalculating.
                moveMobToBlock(blockpos);
            }
        } else {
            index += direction;
            if (index >= pathSize - 1) {
                if (isLooping) {
                    this.index = 0;
                } else {
                    this.direction = -direction;
                }
            } else if (index <= 0) {
                this.direction = -direction;
            }
            --this.tryTicks;
        }
    }
}