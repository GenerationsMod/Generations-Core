package generations.gg.generations.core.generationscore.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Function;

public class GenerationsUtils {

    public static int getIndex(double translatedCoord, int startCoord, int validSegment, int clickSegment, int sizeMax) {
        double testY = translatedCoord - startCoord;

        if (translatedCoord >= startCoord && (testY % validSegment <= clickSegment)) {
            int slot = (int) (testY / validSegment);

            if (MathUtils.between(slot, 0, sizeMax)) return slot;
        }

        return -1;
    }

    public static <T extends Enum<T> & StringRepresentable> Optional<T> getByName(String name, Class<T> tClass) {
        return EnumSet.allOf(tClass).stream().filter(arg -> arg.getSerializedName().equals(name)).findFirst();
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
    };
}
