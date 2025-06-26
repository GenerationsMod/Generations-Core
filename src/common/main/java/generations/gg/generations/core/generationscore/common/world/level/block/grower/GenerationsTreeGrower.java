//package generations.gg.generations.core.generationscore.common.world.level.block.grower;
//
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.level.block.grower.AbstractTreeGrower;
//import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
//import org.jetbrains.annotations.NotNull;
//
//public class GenerationsTreeGrower extends AbstractTreeGrower {
//    private final ResourceKey<ConfiguredFeature<?, ?>> configuredFeature;
//
//    public GenerationsTreeGrower(ResourceKey<ConfiguredFeature<?, ?>> configuredFeature) {
//        this.configuredFeature = configuredFeature;
//    }
//
//    @Override
//    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource random, boolean largeHive) {
//        return configuredFeature;
//    }
//}
