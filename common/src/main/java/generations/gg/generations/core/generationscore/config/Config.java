package generations.gg.generations.core.generationscore.config;

import java.time.Duration;

public class Config {
    public VanillaTabsToAdd addItemsToVanillaTabs = new VanillaTabsToAdd();
    public Duration lootTime = Duration.ofHours(1);

    public Config() {}

    public static class VanillaTabsToAdd {
        public boolean coloredBlocks = true;
        public boolean combat = true;
        public boolean toolsAndUtilities = true;
        public boolean functionalBlocks = true;
        public boolean naturalBlocks = true;
        public boolean buildingBlocks = true;
    }
}
