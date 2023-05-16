package generations.gg.generations.core.generationscore.client.render.rarecandy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

/**
 * Used to control the lighting settings of {@link com.pokemod.pokemod.world.entity.pixelmon.PixelmonEntity}
 */
public record LightingSettings(int lightColor, float reflectivity, float shineDamper, float diffuseColorMix) {
    public static final LightingSettings NORMAL_SHADING = new LightingSettings(0xFFFFFFFF, 0.001f, 0.1f, 1);
    public static final LightingSettings SILHOUETTE_SHADING = new LightingSettings(0x00000000, 0, 0, 0);
    public static final LightingSettings SHINY_SHADING = coloredShinyShading(0xFFFFFF, 0.4f);

    public static LightingSettings coloredShinyShading(int color, float diffuseColorMix) {
        return new LightingSettings(color, 0.3f, 0.3f, diffuseColorMix);
    }

    public void serializeToByteBuf(FriendlyByteBuf buf) {
        buf.writeInt(lightColor);
        buf.writeFloat(reflectivity);
        buf.writeFloat(shineDamper);
        buf.writeFloat(diffuseColorMix);
    }

    public CompoundTag serializeToNbt() {
        var tag = new CompoundTag();
        tag.putInt("light_color", lightColor);
        tag.putFloat("reflectivity", reflectivity);
        tag.putFloat("shine_damper", shineDamper);
        tag.putFloat("diffuse_color_mix", diffuseColorMix);
        return tag;
    }

    public static LightingSettings deserializeFromByteBuf(FriendlyByteBuf buf) {
        return new LightingSettings(buf.readInt(), buf.readFloat(), buf.readFloat(), buf.readFloat());
    }

    public static LightingSettings deserializeFromNbt(CompoundTag tag) {
        return new LightingSettings(tag.getInt("light_color"), tag.getFloat("reflectivity"), tag.getFloat("shine_damper"), tag.getFloat("diffuse_color_mix"));
    }
}