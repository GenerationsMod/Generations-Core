package com.pokemod.pokemod.world.item.berry;

import com.pokemod.pokemod.PokeMod;
import com.pokemod.pokemod.api.data.pixelmon.curry.Flavor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.Locale;

public enum BerryType implements ICurryRarity {
    CHERI(new int[]{10, 0, 0, 0, 0}, 4, 15, 24, 1, 1, 3, 1, EnumBerryColor.RED),
    CHESTO(new int[]{0, 10, 0, 0, 0}, 4, 15, 24, 1, 1, 3, 1, EnumBerryColor.PURPLE),
    PECHA(new int[]{0, 0, 10, 0, 0}, 4, 15, 24, 1, 2, 3, 1, EnumBerryColor.PINK),
    RAWST(new int[]{0, 0, 0, 10, 0}, 4, 15, 24, 1, 1, 3, 1, EnumBerryColor.GREEN),
    ASPEAR(new int[]{0, 0, 0, 0, 10}, 4, 15, 24, 1, 1, 3, 1, EnumBerryColor.YELLOW),
    LEPPA(new int[]{10, 0, 10, 10, 10}, 4, 15, 24, 1.5, 1.5, 3, 1, EnumBerryColor.RED),
    ORAN(new int[]{10, 10, 0, 10, 10}, 4, 15, 24, 1, 2, 3, 1, EnumBerryColor.BLUE),
    PERSIM(new int[]{10, 10, 10, 0, 10}, 4, 15, 24, 1, 1, 3, 1, EnumBerryColor.PINK),
    LUM(new int[]{10, 10, 10, 10, 0}, 3, 20, 48, 1.2, 0.5, 3, 2, EnumBerryColor.GREEN),
    SITRUS(new int[]{0, 10, 10, 10, 10}, 3, 20, 48, 1.2, 0.5, 3, 2, EnumBerryColor.YELLOW),
    FIGY(new int[]{15, 0, 0, 0, 0}, 3, 15, 24, 1.5, 1, 3, 2, EnumBerryColor.RED),
    WIKI(new int[]{0, 15, 0, 0, 0}, 3, 15, 24, 1.5, 1, 3, 2, EnumBerryColor.PURPLE),
    MAGO(new int[]{0, 0, 15, 0, 0}, 3, 15, 24, 1.5, 1, 3, 2, EnumBerryColor.PINK),
    AGUAV(new int[]{0, 0, 0, 15, 0}, 3, 15, 24, 1.5, 1, 3, 2, EnumBerryColor.GREEN),
    IAPAPA(new int[]{0, 0, 0, 0, 15}, 3, 15, 24, 1.5, 1, 3, 2, EnumBerryColor.YELLOW),
    BAZZ(new int[]{10, 10, 0, 0, 0}, 3, 15, 24, 1, 1, 3, 4, EnumBerryColor.RED),
    BLUK(new int[]{0, 10, 10, 0, 0}, 3, 15, 24, 1, 1, 3, 4, EnumBerryColor.PURPLE),
    NANAB(new int[]{0, 0, 10, 10, 0}, 3, 15, 24, 1, 1, 3, 0, EnumBerryColor.PINK),
    WEPEAR(new int[]{0, 0, 0, 10, 10}, 3, 15, 24, 1, 1, 3, 0, EnumBerryColor.GREEN),
    PINAP(new int[]{10, 0, 0, 0, 10}, 3, 15, 24, 1, 1, 3, 0, EnumBerryColor.YELLOW),
    POMEG(new int[]{10, 0, 10, 10, 0}, 1, 20, 48, 0.5, 1.5, 3, 0, EnumBerryColor.RED),
    KELPSY(new int[]{0, 10, 0, 10, 10}, 1, 20, 48, 0.5, 1.5, 3, 0, EnumBerryColor.BLUE),
    QUALOT(new int[]{10, 0, 10, 0, 10}, 1, 20, 48, 0.5, 1.5, 3, 0, EnumBerryColor.YELLOW),
    HONDEW(new int[]{10, 10, 0, 10, 0}, 1, 20, 48, 0.5, 1.5, 3, 0, EnumBerryColor.GREEN),
    GREPA(new int[]{0, 10, 10, 0, 10}, 1, 20, 48, 0.5, 1.5, 3, 0, EnumBerryColor.YELLOW),
    TAMATO(new int[]{20, 10, 0, 0, 0}, 1, 20, 48, 0.5, 1.5, 3, 0, EnumBerryColor.RED),
    CORNN(new int[]{0, 20, 10, 0, 0}, 3, 15, 24, 1, 1, 3, 0, EnumBerryColor.PURPLE),
    MAGOST(new int[]{0, 0, 20, 10, 0}, 3, 15, 24, 1, 1, 3, 0, EnumBerryColor.PINK),
    RABUTA(new int[]{0, 0, 0, 20, 10}, 3, 15, 24, 1, 1, 3, 0, EnumBerryColor.GREEN),
    NOMEL(new int[]{10, 0, 0, 0, 20}, 3, 15, 24, 1, 1, 3, 0, EnumBerryColor.YELLOW),
    SPELON(new int[]{30, 10, 0, 0, 0}, 3, 15, 24, 1, 1, 3, 0, EnumBerryColor.RED),
    PAMTRE(new int[]{0, 30, 10, 0, 0}, 3, 15, 24, 1, 1, 3, 0, EnumBerryColor.PURPLE),
    WATMEL(new int[]{0, 0, 30, 10, 0}, 3, 15, 24, 1, 1, 3, 0, EnumBerryColor.PINK),
    DURIN(new int[]{0, 0, 0, 30, 10}, 3, 15, 24, 1, 1, 3, 0, EnumBerryColor.GREEN),
    BELUE(new int[]{10, 0, 0, 0, 30}, 3, 15, 24, 1, 1, 3, 0, EnumBerryColor.PURPLE),
    OCCA(new int[]{15, 0, 10, 0, 0}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.RED),
    PASSHO(new int[]{0, 15, 0, 10, 0}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.BLUE),
    WACAN(new int[]{0, 0, 15, 0, 10}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.YELLOW),
    RINDO(new int[]{10, 0, 0, 15, 0}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.YELLOW),
    YACHE(new int[]{0, 10, 0, 0, 15}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.BLUE),
    CHOPLE(new int[]{15, 0, 0, 10, 0}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.RED),
    KEBIA(new int[]{0, 15, 0, 0, 10}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.GREEN),
    SHUCA(new int[]{10, 0, 15, 0, 0}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.YELLOW),
    COBA(new int[]{0, 10, 0, 15, 0}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.BLUE),
    PAYAPA(new int[]{0, 0, 10, 0, 15}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.PURPLE),
    TANGA(new int[]{20, 0, 0, 0, 10}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.GREEN),
    CHARTI(new int[]{10, 20, 0, 0, 0}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.YELLOW),
    KASIB(new int[]{0, 10, 20, 0, 0}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.PURPLE),
    HABAN(new int[]{0, 0, 10, 20, 0}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.RED),
    COLBUR(new int[]{0, 0, 0, 10, 20}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.PURPLE),
    BABIRI(new int[]{25, 10, 0, 0, 0}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.GREEN),
    CHILAN(new int[]{0, 25, 10, 0, 0}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.YELLOW),
    ROSELI(new int[]{0, 0, 25, 10, 0}, 3, 20, 48, 1, 0.5, 2, 5, EnumBerryColor.PINK),
    LIECHI(new int[]{30, 10, 30, 0, 0}, 1, 10, 96, 0.2, 0.1, 1, 10, EnumBerryColor.RED),
    GANLON(new int[]{0, 30, 10, 30, 0}, 1, 10, 96, 0.2, 0.1, 1, 10, EnumBerryColor.PURPLE),
    SALAC(new int[]{0, 0, 30, 10, 30}, 1, 10, 96, 0.2, 0.1, 1, 10, EnumBerryColor.GREEN),
    PETAYA(new int[]{30, 0, 0, 30, 10}, 1, 10, 96, 0.2, 0.1, 1, 10, EnumBerryColor.PINK),
    APICOT(new int[]{10, 30, 0, 0, 30}, 1, 10, 96, 0.2, 0.1, 1, 10, EnumBerryColor.BLUE),
    LANSAT(new int[]{30, 10, 30, 10, 30}, 1, 5, 120, 0.1, 0.1, 0.5, 15, EnumBerryColor.RED),
    STARF(new int[]{30, 10, 30, 10, 30}, 1, 5, 120, 0.1, 0.1, 0.5, 30, EnumBerryColor.GREEN),
    PUMKIN(new int[]{0, 0, 0, 0, 50}, 1, 5, 72, 0.2, 0.1, 0.2, 0, EnumBerryColor.YELLOW),
    DRASH(new int[]{0, 0, 50, 0, 0}, 1, 5, 72, 0.2, 0.1, 0.2, 0, EnumBerryColor.PINK),
    EGGANT(new int[]{0, 50, 0, 0, 0}, 1, 5, 72, 0.2, 0.1, 0.2, 0, EnumBerryColor.BLUE),
    STRIB(new int[]{20, 0, 0, 20, 0}, 1, 5, 72, 0.2, 0.1, 0.2, 0, EnumBerryColor.GREEN),
    NUTPEA(new int[]{0, 0, 0, 0, 10}, 1, 5, 72, 0.2, 0.1, 0.2, 0, EnumBerryColor.YELLOW),
    GINEMA(new int[]{0, 20, 0, 0, 20}, 1, 5, 72, 0.2, 0.1, 0.2, 0, EnumBerryColor.YELLOW),
    KUO(new int[]{0, 0, 0, 10, 0}, 1, 5, 72, 0.2, 0.1, 0.2, 0, EnumBerryColor.GREEN),
    YAGO(new int[]{0, 0, 0, 0, 50}, 1, 5, 72, 0.2, 0.1, 0.2, 0, EnumBerryColor.GREEN),
    TOUGA(new int[]{50, 0, 0, 0, 0}, 1, 5, 72, 0.2, 0.1, 0.2, 0, EnumBerryColor.RED),
    NINIKU(new int[]{10, 0, 0, 0, 0}, 1, 5, 72, 0.2, 0.1, 0.2, 0, EnumBerryColor.BLUE),
    TOPO(new int[]{0, 0, 20, 0, 20}, 1, 5, 72, 0.2, 0.1, 0.2, 0, EnumBerryColor.PINK),
    ENIGMA(new int[]{0, 20, 0, 20, 0}, 1, 5, 72, 0.2, 0.1, 0.2, 20, EnumBerryColor.PURPLE),
    MICLE(new int[]{0, 40, 10, 0, 0}, 1, 5, 72, 0.2, 0.1, 0.2, 20, EnumBerryColor.GREEN),
    CUSTAP(new int[]{0, 0, 40, 10, 0}, 1, 5, 72, 0.2, 0.1, 0.2, 20, EnumBerryColor.RED),
    JABOCA(new int[]{0, 0, 0, 40, 10}, 1, 5, 72, 0.2, 0.1, 0.2, 20, EnumBerryColor.YELLOW),
    ROWAP(new int[]{10, 0, 0, 0, 40}, 1, 5, 72, 0.2, 0.1, 0.2, 20, EnumBerryColor.BLUE),
    KEE(new int[]{30, 30, 10, 10, 10}, 1, 10, 96, 0.2, 0.1, 1, 10, EnumBerryColor.PINK),
    MARANGA(new int[]{10, 10, 30, 30, 10}, 1, 10, 96, 0.2, 0.1, 1, 10, EnumBerryColor.BLUE);

    private final int[] flavors;
    public final byte minYield;
    public final byte maxYield;
    public final byte growthTime;
    private final double waterAmount;
    private final double weedingAmount;
    private final double pestRemovalAmount;
    private final int rarity;
    private final EnumBerryColor color;

    BerryType(int[] flavors, int minYield, int maxYield, int growthTime, double waterAmount, double weedingAmount, double pestRemovalAmount, int rarity, EnumBerryColor color) {
        this.flavors = flavors;
        this.minYield = (byte) minYield;
        this.maxYield = (byte) maxYield;
        this.growthTime = (byte) growthTime;
        this.waterAmount = waterAmount;
        this.weedingAmount = weedingAmount;
        this.pestRemovalAmount = pestRemovalAmount;
        this.rarity = rarity;
        this.color = color;
    }

    public Block getBush() {
        return ForgeRegistries.BLOCKS.getValue(PokeMod.id(this.name().toLowerCase(Locale.ENGLISH) + "_berry_bush"));
    }

    public Item getBerry() {
        return ForgeRegistries.ITEMS.getValue(PokeMod.id(this.name().toLowerCase(Locale.ENGLISH) + "_berry"));
    }

    public static BerryType fromId(int id) {
        try {
            return values()[id];
        } catch (Exception e) {
            return null;
        }
    }

    public static Flavor getDominantFlavor(BerryType... berries) {
        int[] output = new int[5];

        for (var berryType : berries) {
            for (int j = 0; j < 5; j++) {
                output[j] += berryType.flavors[j];
            }
        }

        if (Arrays.stream(output).distinct().count() <= 1) return Flavor.NONE;
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;

        for (int i = 0; i < output.length; i++) {
            if (max < output[i]) {
                max = output[i];
                maxIndex = i;
            }
        }

        return getFlavorFromBerryFlavorIndex(maxIndex);
    }

    public static Flavor getFlavorFromBerryFlavorIndex(int value) {
        return switch (value) {
            case 0 -> Flavor.SPICY;
            case 1 -> Flavor.DRY;
            case 2 -> Flavor.SWEET;
            case 3 -> Flavor.BITTER;
            case 4 -> Flavor.SOUR;
            default -> Flavor.NONE;
        };
    }

    public int getFlavorValue(Flavor flavor) {
        return switch (flavor) {
            case SPICY -> flavors[0];
            case DRY -> flavors[1];
            case SWEET -> flavors[2];
            case BITTER -> flavors[3];
            case SOUR -> flavors[4];
            default -> 0;
        };
    }

    public double getWaterAmount() {
        return waterAmount;
    }

    public double getWeedingAmount() {
        return weedingAmount;
    }

    public double getPestRemovalAmount() {
        return pestRemovalAmount;
    }

    public EnumBerryColor getColor() {
        return color;
    }

    public int getRarity() {
        return rarity;
    }

    public enum EnumBerryColor {
        RED(MaterialColor.COLOR_RED),
        BLUE(MaterialColor.COLOR_BLUE),
        PURPLE(MaterialColor.COLOR_PURPLE),
        GREEN(MaterialColor.COLOR_GREEN),
        YELLOW(MaterialColor.COLOR_YELLOW),
        PINK(MaterialColor.COLOR_PINK);

        private final MaterialColor color;

        EnumBerryColor(MaterialColor color) {
            this.color = color;
        }

        public MaterialColor getMaterialColor() {
            return color;
        }
    }
}
