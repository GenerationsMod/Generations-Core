package generations.gg.generations.core.generationscore.world.npc.display;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NpcDisplayData {
    public static Codec<NpcDisplayData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("displayName").forGetter(NpcDisplayData::getDisplayName),
            Codec.BOOL.fieldOf("nameVisibility").forGetter(NpcDisplayData::isNameVisible),
            Codec.STRING.fieldOf("collision").forGetter(NpcDisplayData::getCollisionName),
            RendererInfo.CODEC.fieldOf("renderer").forGetter(NpcDisplayData::getRendererInfo),
            HeldItemsInfo.CODEC.fieldOf("heldItems").forGetter(NpcDisplayData::getHeldItemsInfo),
            MovementInfo.CODEC.fieldOf("movement").forGetter(NpcDisplayData::getMovementInfo),
            RotationInfo.CODEC.fieldOf("rotation").forGetter(NpcDisplayData::getRotationInfo)
    ).apply(instance, NpcDisplayData::new));

    private static final Collision DEFAULT_COLLISION = Collision.PUSH_OTHERS;

    private String displayName;
    private boolean nameVisibility;
    private Collision collision;
    private final RendererInfo rendererInfo;
    private final HeldItemsInfo heldItemsInfo;
    private final MovementInfo movementInfo;
    private final RotationInfo rotationInfo;

    public NpcDisplayData(String displayName, boolean nameVisibility, Collision collision, RendererInfo rendererInfo,
                          HeldItemsInfo heldItemsInfo, MovementInfo movementInfo, RotationInfo rotationInfo) {
        this.displayName = displayName;
        this.nameVisibility = nameVisibility;
        this.collision = collision;
        this.rendererInfo = rendererInfo;
        this.heldItemsInfo = heldItemsInfo;
        this.movementInfo = movementInfo;
        this.rotationInfo = rotationInfo;
    }

    public NpcDisplayData(String displayName, boolean nameVisibility, String collision, RendererInfo rendererInfo,
                          HeldItemsInfo heldItemsInfo, MovementInfo movementInfo, RotationInfo rotationInfo) {
        this(displayName, nameVisibility, Collision.getOrDefault(collision, DEFAULT_COLLISION), rendererInfo, heldItemsInfo, movementInfo, rotationInfo);
    }

    public NpcDisplayData(CompoundTag tag) {
        this(tag.getString("displayName"),
                tag.getBoolean("nameVisibility"),
                Collision.getOrDefault(tag.getString("collision"), DEFAULT_COLLISION),
                new RendererInfo(tag.getCompound("renderer")),
                new HeldItemsInfo(tag.getCompound("heldItems")),
                new MovementInfo(tag.getCompound("movement")),
                new RotationInfo(tag.getCompound("rotation")));
    }

    public NpcDisplayData(FriendlyByteBuf buf) {
        this(buf.readUtf(),
                buf.readBoolean(),
                Collision.getOrDefault(buf.readUtf(), DEFAULT_COLLISION),
                new RendererInfo(buf),
                new HeldItemsInfo(buf),
                new MovementInfo(buf),
                new RotationInfo(buf));
    }

    public CompoundTag serializeToNbt() {
        CompoundTag tag = new CompoundTag();

        tag.putString("displayName", displayName);
        tag.putBoolean("nameVisibility", nameVisibility);
        tag.putString("collision", collision.getName());
        tag.put("renderer", rendererInfo.serializeToNbt());
        tag.put("heldItems", heldItemsInfo.serializeToNbt());
        tag.put("movement", movementInfo.serializeToNbt());
        tag.put("rotation", rotationInfo.serializeToNbt());

        return tag;
    }

    public void serializeToByteBuf(FriendlyByteBuf buf) {
        buf.writeUtf(displayName);
        buf.writeBoolean(nameVisibility);
        buf.writeUtf(collision.getName());
        rendererInfo.serializeToByteBuf(buf);
        heldItemsInfo.serializeToByteBuf(buf);
        movementInfo.serializeToByteBuf(buf);
        rotationInfo.serializeToByteBuf(buf);
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isNameVisible() {
        return nameVisibility;
    }

    public Collision getCollision() {
        return collision;
    }

    public String getCollisionName() {
        return collision.getName();
    }

    public RendererInfo getRendererInfo() {
        return rendererInfo;
    }

    public HeldItemsInfo getHeldItemsInfo() {
        return heldItemsInfo;
    }

    public MovementInfo getMovementInfo() {
        return movementInfo;
    }

    public RotationInfo getRotationInfo() {
        return rotationInfo;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setNameVisibility(boolean nameVisibility) {
        this.nameVisibility = nameVisibility;
    }

    public void setCollision(Collision collision) {
        this.collision = collision;
    }

    public NpcDisplayData copy() {
        return new NpcDisplayData(displayName, nameVisibility, collision,
                rendererInfo.copy(),
                heldItemsInfo.copy(),
                movementInfo.copy(),
                rotationInfo.copy());
    }

    public enum Collision {
        NONE("none"),
        PUSH_OTHERS("push_others"),
        FULL("full");

        final String name;

        Collision(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static Collision get(@NotNull String name) {
            return getOrDefault(name, null);
        }

        public static Collision getOrDefault(@NotNull String name, @Nullable Collision defaultCollision) {
            for (Collision type : values()) {
                if (type.name().equals(name) || type.getName().equals(name)) {
                    return type;
                }
            }
            return defaultCollision;
        }
    }
}