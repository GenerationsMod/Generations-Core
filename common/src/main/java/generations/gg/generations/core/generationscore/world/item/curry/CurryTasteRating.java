package generations.gg.generations.core.generationscore.world.item.curry;

import net.minecraft.ChatFormatting;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.Consumer;

public enum CurryTasteRating implements StringRepresentable {
    Unknown(0, 0, false, false, ChatFormatting.BLACK),
    Koffing(25, 0.25, false, false, ChatFormatting.DARK_RED),
    Wobbuffet(200, 0.5, false, false, ChatFormatting.RED),
    Milcery(1000, 1.0, true, false, ChatFormatting.WHITE),
    Copperajah(5000, 1.0, true, true, ChatFormatting.DARK_GREEN),
    Charizard(10000, 1.0, true, true, ChatFormatting.GREEN);

    private final Consumer<CurryData> consumer;
    private final ChatFormatting textFormatting;

    CurryTasteRating(int exp, double healthPercent, boolean healStatus, boolean restorePP, ChatFormatting textFormatting) {
        this.textFormatting = textFormatting;
        consumer = curryData -> curryData.setExperience(exp).setHealthPercentage(healthPercent).setCanHealStatus(healStatus).setCanRestorePP(restorePP);
    }

    public static CurryTasteRating fromId(int id) {
        try {
            return values()[id];
        } catch (Exception e) {
            return Koffing;
        }
    }

    public void configureData(CurryData data) {
        consumer.accept(data);
    }

    public String getName() {
        return textFormatting + name();
    }

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
