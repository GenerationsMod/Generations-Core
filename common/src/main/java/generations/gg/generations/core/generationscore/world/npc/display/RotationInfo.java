package generations.gg.generations.core.generationscore.world.npc.display;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RotationInfo {

    public static Codec<RotationInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("type").forGetter(RotationInfo::getTypeName),
            Codec.FLOAT.optionalFieldOf("bodyY", 0.0F).forGetter(RotationInfo::getBodyY),
            Codec.FLOAT.optionalFieldOf("headY", 0.0F).forGetter(RotationInfo::getHeadY),
            Codec.FLOAT.optionalFieldOf("headX", 0.0F).forGetter(RotationInfo::getHeadX)
    ).apply(instance, RotationInfo::new));

    private static final RotationType DEFAULT = RotationType.NEAREST_PLAYER;

    private RotationType type;
    private float bodyY;
    private float headX;
    private float headY;

    public RotationInfo(RotationType type, float bodyY, float headX, float headY) {
        this.type = type;
        this.bodyY = bodyY;
        this.headX = headX;
        this.headY = headY;
    }

    public RotationInfo(String type, float bodyY, float headX, float headY) {
        this(RotationType.getOrDefault(type, DEFAULT), bodyY, headX, headY);
    }
    public RotationInfo(CompoundTag tag) {
        this(tag.getString("type"),
                tag.getFloat("bodyY"),
                tag.getFloat("headX"),
                tag.getFloat("headY"));
    }

    public RotationInfo(FriendlyByteBuf buf) {
        this(buf.readUtf(),
                buf.readFloat(),
                buf.readFloat(),
                buf.readFloat());
    }

    public CompoundTag serializeToNbt() {
        CompoundTag tag = new CompoundTag();

        tag.putString("type", type.getName());

        tag.putFloat("bodyY", bodyY);
        tag.putFloat("headX", headX);
        tag.putFloat("headY", headY);

        return tag;
    }

    public void serializeToByteBuf(FriendlyByteBuf buf) {
        buf.writeUtf(type.getName());
        buf.writeFloat(bodyY);
        buf.writeFloat(headX);
        buf.writeFloat(headY);
    }

    public RotationType getType() {
        return type;
    }

    public String getTypeName() {
        return type.getName();
    }

    public void setType(String type) {
        this.type = RotationType.getOrDefault(type, DEFAULT);
    }

    public void setType(RotationType type) {
        this.type = type;
    }

    public float getBodyY() {
        return bodyY;
    }

    public float getHeadX() {
        return headX;
    }

    public float getHeadY() {
        return headY;
    }

    public void setBodyY(float bodyY) {
        this.bodyY = bodyY;
    }

    public void setHeadX(float headX) {
        this.headX = headX;
    }

    public void setHeadY(float headY) {
        this.headY = headY;
    }


    public RotationInfo copy() {
        return new RotationInfo(type, bodyY, headX, headY);
    }

    public enum RotationType {
        STATIC("static"),
        NEAREST_PLAYER("player");

        private final String name;
        RotationType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static RotationType get(@NotNull String name) {
            return getOrDefault(name, null);
        }

        public static RotationType getOrDefault(@NotNull String name, @Nullable RotationType defaultRotation) {
            for (RotationType type : values()) {
                if (type.name().equals(name) || type.getName().equals(name)) {
                    return type;
                }
            }
            return defaultRotation;
        }
    }
}