package generations.gg.generations.core.generationscore.world.feature;

import net.minecraft.world.level.levelgen.feature.Feature;

public class PokeModCaveFeatures {
    /* All cave biome features *///TODO: Enable once we get to it.
    public static Feature<NoisySphereReplaceConfig> NOISY_SPHERE_REPLACE = new NoisySphereReplaceFeature(NoisySphereReplaceConfig.CODEC);
//
//    /**
//     * Subscribes event listener(s).
//     */
//    public static void onInitialize(IEventBus eventBus) {
//        eventBus.addListener(PokeModCaveFeatures::registerFeatures);
//    }
//
//
//    /**
//     * Safely registers all features.
//     */
//    private static void registerFeatures(RegisterEvent event) {
//        event.register(Registries.FEATURE, helper -> register(helper, NOISY_SPHERE_REPLACE, "noisy_sphere_replace"));
//    }
//
//    /**
//     * Utility method for registering features.
//     */
//    private static void register(RegisterEvent.RegisterHelper<Feature<?>> helper, Feature<?> feature, String name) {
//        helper.register(PokeMod.id(name), feature);
//    }
}
