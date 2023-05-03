package com.pokemod.pokemod.api.data.pixelmon.curry;

import com.pokemod.pokemod.api.data.pixelmon.StatisticsType;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.util.StringRepresentable;

import java.awt.*;
import java.util.Locale;

public enum Flavor implements StringRepresentable {
    NONE(StatisticsType.NONE, Color.BLACK),
    SPICY(StatisticsType.ATTACK, Color.decode("#f08030")),
    DRY(StatisticsType.SPECIAL_ATTACK, Color.decode("#6890f0")),
    SWEET(StatisticsType.SPEED, Color.decode("#f85888")),
    BITTER(StatisticsType.SPECIAL_DEFENSE, Color.decode("#78c850")),
    SOUR(StatisticsType.DEFENSE, Color.decode("#f8d030"));

    private final StatisticsType type;
    private final Color color;

    Flavor(StatisticsType type, Color color) {
        this.type = type;
        this.color = color;
    }

    public StatisticsType getType() {
        return type;
    }

    public String getLocalizedName() {
        return this != NONE ? I18n.get("enum.flavor." + this.toString().toLowerCase(Locale.ENGLISH)) : "";
    }

    public static Flavor getFlavorFromIndex(int index) {
        try {
            return Flavor.values()[index];
        } catch (Exception npe) {
            return null;
        }
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
