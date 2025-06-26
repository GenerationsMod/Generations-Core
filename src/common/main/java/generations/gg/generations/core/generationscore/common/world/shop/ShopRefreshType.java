package generations.gg.generations.core.generationscore.common.world.shop;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;

public enum ShopRefreshType implements StringRepresentable {
    NEVER("never"),
    PER_MC_DAY("per_mc_day"),
    PER_DAY("per_day"),
    CUSTOM_INGAME("custom_ingame"),
    CUSTOM_IRL("custom_irl");

    public static Codec<ShopRefreshType> CODEC = StringRepresentable.fromValues(ShopRefreshType::values);

    private final String name;

    ShopRefreshType(String name) {
        this.name = name;
    }

    public static ShopRefreshType get(String name) {
        return getOrDefault(name, NEVER);
    }

    public static ShopRefreshType getOrDefault(String name, ShopRefreshType defaultValue) {
        for (ShopRefreshType rate : values()) {
            if (rate.name().equals(name.toUpperCase()))
                return rate;
        }

        return defaultValue;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}