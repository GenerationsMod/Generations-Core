package generations.gg.generations.core.generationscore;

public class Config {
    public VanillaTabsToAdd addItemsToVanillaTabs = new VanillaTabsToAdd();

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
