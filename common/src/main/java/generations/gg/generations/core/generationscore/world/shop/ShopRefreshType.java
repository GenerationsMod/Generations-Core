package generations.gg.generations.core.generationscore.world.shop;

public enum ShopRefreshType {
    NEVER,
    PER_MC_DAY,
    PER_DAY,
    CUSTOM_INGAME,
    CUSTOM_IRL;

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
}