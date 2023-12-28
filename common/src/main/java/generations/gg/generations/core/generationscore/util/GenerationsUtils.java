package generations.gg.generations.core.generationscore.util;

import com.cobblemon.mod.common.api.berry.Flavor;
import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.google.gson.*;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.entities.MutableBlockEntityType;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericModelBlock;
import net.minecraft.locale.Language;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class GenerationsUtils {

    public static int getIndex(double translatedCoord, int startCoord, int validSegment, int clickSegment, int sizeMax) {
        double testY = translatedCoord - startCoord;

        if (translatedCoord >= startCoord && (testY % validSegment <= clickSegment)) {
            int slot = (int) (testY / validSegment);

            if (MathUtils.between(slot, 0, sizeMax)) return slot;
        }

        return -1;
    }

    public static <T extends Enum<T>> Optional<T> getByName(String name, Class<T> tClass) {
        return EnumSet.allOf(tClass).stream().filter(arg -> (arg instanceof StringRepresentable representable ? representable.getSerializedName() : arg.name()).equals(name)).findFirst();
    }

    public static String getFlavorLocalizedName(@Nullable Flavor flavor) {
        return flavor != null ? Language.getInstance().getOrDefault("enum.flavor." + flavor.toString().toLowerCase(Locale.ENGLISH)) : "";
    }


    public static Vector3f rgbFromInt(int color) {
        float d = (color >> 16 & 0xFF) / 255.0f;
        float e = (color >> 8 & 0xFF) / 255.0f;
        float f = (color & 0xFF) / 255.0f;
        return new Vector3f(d, e, f);
    }

    public static Time getInGameDayTime(Level level) {
        int inGameDayTime = (int) ((level.getDayTime() % 24000) + 6000);
        byte hours = (byte) (inGameDayTime / 1000);
        double minutesAndSeconds = ((inGameDayTime % 1000) / 1000.0 * 60.0);
        byte minutes = (byte) Math.floor(minutesAndSeconds);
        byte seconds = (byte) ((minutesAndSeconds - minutes) * 60);
        return Time.of(hours, minutes, seconds);
    }

    public static CompoundTag toCompoundTag(ItemStack stack) {
        var compound = new CompoundTag();
        compound.putString("id", stack.getItem().arch$registryName().toString());
        compound.putInt("Count", stack.getCount());
        if(stack.getTag() != null) compound.put("tag", stack.getTag());
        return compound;
    }

    public static JsonElement jsonToNbt(Tag tag) {
        return NbtOps.INSTANCE.convertTo(JsonOps.INSTANCE, tag);
    }

    public static Tag jsonToNbt(JsonElement json) {
        return JsonOps.INSTANCE.convertTo(NbtOps.INSTANCE, json);
    }

    public static <T, K, V> V processIfNotNull(T t, K k, BiFunction<T, K, V> function) {
        return t != null ? function.apply(t, k) : null;
    }

    public static <T> Serializer<T> codec(Codec<T> codec) {
        return new Serializer<>(codec);
    }

    public static PokemonProperties parseProperties(String data) {
        return PokemonProperties.Companion.parse(data, " ", "=");
    }

    //Because BiMap dump and pass null if new entry.
    public static <K, V> V ensureMapReturn(Map<K, V> map, K key, V value) {
        var output = map.put(key, value);
        return output != null ? output : map.get(key);
    }

    public static BigDecimal readBigDecimal(FriendlyByteBuf buf) {
        var scale = buf.readInt();
        var unscaled = new byte[buf.readInt()];
        buf.readBytes(unscaled);
        return new BigDecimal(new BigInteger(unscaled), scale);
    }

    public static void writeBigDecimal(FriendlyByteBuf buf, BigDecimal value) {
        buf.writeInt(value.scale());
        var unscaled = value.unscaledValue().toByteArray();
        buf.writeInt(unscaled.length);
        buf.writeBytes(unscaled);
    }

    public static void giveItem(ServerPlayer player, ItemStack stack) {
        if(!player.getInventory().add(stack)) {
            Containers.dropItemStack(player.level(), player.position().x, player.position().y, player.position().z, stack);
        }
    }

    public static <T extends Block> RegistrySupplier<T> registerBlock(DeferredRegister<Block> deferredRegister, String name, Supplier<T> blockSupplier) {
        return deferredRegister.register(name, applyMutable(name, blockSupplier));
    }

    private static <T extends Block> Supplier<T> applyMutable(String name, Supplier<T> blockSupplier) {
        return () -> {
            var block = blockSupplier.get();

            if(block instanceof GenericModelBlock<?> genericModelBlock) {
                MutableBlockEntityType.blocksToAdd.add(genericModelBlock);
            }

            return block;
        };
    }

    public record Serializer<T>(Codec<T> codec) implements JsonSerializer<T>, JsonDeserializer<T> {

        @Override
        public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return JsonOps.INSTANCE.withDecoder(codec()).andThen(DataResult::result).apply(json).orElseThrow().getFirst();
        }

        @Override
        public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
            return JsonOps.INSTANCE.withEncoder(codec()).andThen(DataResult::result).apply(src).orElseThrow();
        }
    }

    @ExpectPlatform
    public static CompoundTag serializeStack(ItemStack itemStack) {
        throw new RuntimeException();
    }
}
