package generations.gg.generations.core.generationscore.world.npc.display;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MovementInfo {

    public static Codec<MovementInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("type").forGetter(MovementInfo::getTypeName),
            BlockPos.CODEC.fieldOf("origin").forGetter(MovementInfo::getOrigin),
            Codec.INT.fieldOf("radius").forGetter(MovementInfo::getRadius),
            Codec.list(BlockPos.CODEC).fieldOf("path").forGetter(MovementInfo::getPath),
            Codec.FLOAT.fieldOf("moveSpeed").forGetter(MovementInfo::getMoveSpeed),
            Codec.INT.fieldOf("cooldownTicks").forGetter(MovementInfo::getCooldownTicks)
    ).apply(instance, MovementInfo::new));

    private static final MovementType DEFAULT = MovementType.STILL;

    private MovementType type;
    private BlockPos origin;
    private List<BlockPos> path;
    private int radius;
    private float moveSpeed;
    private int cooldownTicks;

    public MovementInfo(MovementType type, BlockPos origin, int radius, List<BlockPos> path, float moveSpeed, int cooldownTicks) {
        this.type = type;
        this.origin = origin;
        this.radius = radius;
        this.path = path;
        this.moveSpeed = moveSpeed;
        this.cooldownTicks = cooldownTicks;
    }

    public MovementInfo(String type, BlockPos origin, int radius, List<BlockPos> path, float moveSpeed, int cooldownTicks) {
        this(MovementType.getOrDefault(type, DEFAULT), origin, radius, path, moveSpeed, cooldownTicks);
    }

    public MovementInfo(CompoundTag tag) {
        this.type = MovementType.getOrDefault(tag.getString("type"), DEFAULT);
        this.path = Arrays.stream(tag.getLongArray("path")).mapToObj(BlockPos::of).toList();
        this.origin = BlockPos.of(tag.getLong("origin"));
        this.radius = tag.getInt("radius");
        this.moveSpeed = tag.getFloat("moveSpeed");
        this.cooldownTicks = tag.getInt("cooldownTicks");
    }

    public MovementInfo(FriendlyByteBuf buf) {
        this.type = MovementType.getOrDefault(buf.readUtf(), DEFAULT);
        try {
            this.path = buf.readList(FriendlyByteBuf::readBlockPos);
        } catch (Exception e) {
            this.path = Collections.emptyList();
        }
        this.origin = buf.readBlockPos();
        this.radius = buf.readInt();
        this.moveSpeed = buf.readFloat();
        this.cooldownTicks = buf.readInt();
    }

    public CompoundTag serializeToNbt() {
        CompoundTag tag = new CompoundTag();

        tag.putString("type", type.getName());
        tag.putLongArray("path", path.stream().mapToLong(BlockPos::asLong).toArray());
        tag.putLong("origin", Objects.requireNonNullElse(origin, BlockPos.ZERO).asLong());
        tag.putInt("radius", radius);
        tag.putFloat("moveSpeed", moveSpeed);
        tag.putInt("cooldownTicks", cooldownTicks);

        return tag;
    }

    public void serializeToByteBuf(FriendlyByteBuf buf) {
        buf.writeUtf(type.getName());
        buf.writeCollection(Objects.requireNonNullElse(path, Collections.emptyList()), FriendlyByteBuf::writeBlockPos);
        buf.writeBlockPos(Objects.requireNonNullElse(origin, BlockPos.ZERO));
        buf.writeInt(radius);
        buf.writeFloat(moveSpeed);
        buf.writeInt(cooldownTicks);
    }

    public MovementType getType() {
        return type;
    }

    public String getTypeName() {
        return type.getName();
    }

    public BlockPos getOrigin() {
        return origin;
    }

    public int getRadius() {
        return radius;
    }

    public List<BlockPos> getPath() {
        return path;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public int getCooldownTicks() {
        return cooldownTicks;
    }

    public void setType(String type) {
        this.type = MovementType.getOrDefault(type, DEFAULT);
    }

    public void setOrigin(BlockPos origin) {
        this.origin = origin;
    }

    public void setOriginX(int x) {
        int y = this.origin == null ? 0 : origin.getY();
        int z = this.origin == null ? 0 : origin.getZ();
        this.origin = new BlockPos(x, y, z);
    }

    public void setOriginY(int y) {
        int x = this.origin == null ? 0 : origin.getX();
        int z = this.origin == null ? 0 : origin.getZ();
        this.origin = new BlockPos(x, y, z);
    }

    public void setOriginZ(int z) {
        int x = this.origin == null ? 0 : origin.getX();
        int y = this.origin == null ? 0 : origin.getY();
        this.origin = new BlockPos(x, y, z);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setPath(List<BlockPos> path) {
        this.path = path;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void setCooldownTicks(int cooldownTicks) {
        this.cooldownTicks = cooldownTicks;
    }

    public MovementInfo copy() {
        return new MovementInfo(type, origin, radius, path, moveSpeed, cooldownTicks);
    }

    public enum MovementType {
        STILL("still"),
        WANDER_RANDOMLY("area"),
        WANDER_PATH("patrol");

        private final String name;

        MovementType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static MovementType get(@NotNull String name) {
            return getOrDefault(name, null);
        }

        public static MovementType getOrDefault(@NotNull String name, @Nullable MovementType defaultMovement) {
            for (MovementType type : values()) {
                if (type.name().equals(name) || type.getName().equals(name)) {
                    return type;
                }
            }
            return defaultMovement;
        }
    }
}