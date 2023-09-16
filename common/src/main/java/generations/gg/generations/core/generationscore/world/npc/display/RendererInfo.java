package generations.gg.generations.core.generationscore.world.npc.display;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Pose;

import java.util.Arrays;

public class RendererInfo {

    public static Codec<RendererInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("entityType").forGetter(RendererInfo::getEntityTypeKey),
            CompoundTag.CODEC.fieldOf("tag").forGetter(RendererInfo::getTag),
            Codec.FLOAT.fieldOf("scale").forGetter(RendererInfo::getScale),
            Codec.STRING.fieldOf("pose").forGetter(RendererInfo::getPoseName),
            Codec.STRING.fieldOf("texture").forGetter(RendererInfo::getTexture),
            Codec.BOOL.fieldOf("isTextureUsername").forGetter(RendererInfo::isTextureUsername)
    ).apply(instance, RendererInfo::new));

    private String entityType;
    private CompoundTag tag;
    private float scale;
    private Pose pose;
    private String texture;
    private boolean isTextureUsername;

    public RendererInfo(String entityType, CompoundTag tag, float scale, String pose, String texture, boolean isTextureUsername) {
        this.entityType = entityType;
        this.tag = tag;
        this.scale = scale;
        this.pose = Arrays.stream(Pose.values()).filter(p -> p.name().equals(pose)).findFirst().orElse(Pose.STANDING);
        this.texture = texture;
        this.isTextureUsername = isTextureUsername;
    }

    public RendererInfo(CompoundTag tag) {
        this(tag.getString("entityType"),
                tag.getCompound("tag"),
                tag.getFloat("scale"),
                tag.getString("pose"),
                tag.getString("texture"),
                tag.getBoolean("isTextureUsername"));
    }

    public RendererInfo(FriendlyByteBuf buf) {
        this(buf.readUtf(),
                buf.readNbt(),
                buf.readFloat(),
                buf.readUtf(),
                buf.readUtf(),
                buf.readBoolean());
    }

    public CompoundTag serializeToNbt() {
        CompoundTag tag = new CompoundTag();

        tag.putString("entityType", entityType);
        tag.put("tag", this.tag);
        tag.putFloat("scale", scale);
        tag.putString("pose", pose.name());
        tag.putString("texture", texture);
        tag.putBoolean("isTextureUsername", isTextureUsername);

        return tag;
    }

    public void serializeToByteBuf(FriendlyByteBuf buf) {
        buf.writeUtf(entityType);
        buf.writeNbt(tag);
        buf.writeFloat(scale);
        buf.writeUtf(pose.name());
        buf.writeUtf(texture);
        buf.writeBoolean(isTextureUsername);
    }

    public String getEntityTypeKey() {
        return entityType;
    }

    public CompoundTag getTag() {
        return tag;
    }

    public float getScale() {
        return scale;
    }

    public Pose getPose() {
        return pose;
    }

    public String getPoseName() {
        return pose.name();
    }

    public String getTexture() {
        return texture;
    }

    public boolean isTextureUsername() {
        return isTextureUsername;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public void setTag(CompoundTag tag) {
        this.tag = tag;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void setPose(Pose pose) {
        this.pose = pose;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public void setTextureUsername(boolean textureUsername) {
        isTextureUsername = textureUsername;
    }

    public RendererInfo copy() {
        return new RendererInfo(entityType, tag.copy(), scale, pose.name(), texture, isTextureUsername);
    }
}