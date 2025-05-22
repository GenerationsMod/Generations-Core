package generations.gg.generations.core.generationscore.common.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

@SuppressWarnings("unused")
public class GenerationsPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.PAINTING_VARIANT);
    public static RegistrySupplier<PaintingVariant> BLUE_POSTER = register("blue_poster", 64, 64);
    public static RegistrySupplier<PaintingVariant> BLUE_POSTER_SPRITE = register("blue_poster_sprite", 16, 16);
    public static RegistrySupplier<PaintingVariant> BLUE_SCROLL = register("blue_scroll", 64, 32);
    public static RegistrySupplier<PaintingVariant> CLEFAIRY_POSTER_SPRITE = register("clefairy_poster_sprite", 16, 16);
    public static RegistrySupplier<PaintingVariant> CUTE_POSTER = register("cute_poster", 64, 64);
    public static RegistrySupplier<PaintingVariant> CUTE_POSTER_SPRITE = register("cute_poster_sprite", 16, 16);
    public static RegistrySupplier<PaintingVariant> DADS_SCROLL = register("dads_scroll", 64, 32);
    public static RegistrySupplier<PaintingVariant> GREEN_POSTER = register("green_poster", 64, 64);
    public static RegistrySupplier<PaintingVariant> GREEN_POSTER_SPRITE = register("green_poster_sprite", 16, 16);
    public static RegistrySupplier<PaintingVariant> GREEN_SCROLL = register("green_scroll", 64, 32);
    public static RegistrySupplier<PaintingVariant> JIGGLYPUFF_POSTER_SPRITE = register("jigglypuff_poster_sprite", 16, 16);
    public static RegistrySupplier<PaintingVariant> KISS_POSTER_SPRITE = register("kiss_poster_sprite", 64, 32);
    public static RegistrySupplier<PaintingVariant> LONG_POSTER = register("long_poster", 64, 32);
    public static RegistrySupplier<PaintingVariant> LONG_POSTER_SPRITE = register("long_poster_sprite", 32, 16);
    public static RegistrySupplier<PaintingVariant> NATIONAL_AWARD = register("national_award", 64, 32);
    public static RegistrySupplier<PaintingVariant> PIKA_POSTER = register("pika_poster", 64, 32);
    public static RegistrySupplier<PaintingVariant> PIKA_POSTER_SPRITE = register("pika_poster_sprite", 32, 16);
    public static RegistrySupplier<PaintingVariant> PIKACHU_POSTER_SPRITE = register("pikachu_poster_sprite", 16, 16);
    public static RegistrySupplier<PaintingVariant> POKE_BALL_POSTER = register("poke_ball_poster", 64, 64);
    public static RegistrySupplier<PaintingVariant> RED_POSTER = register("red_poster", 64, 64);
    public static RegistrySupplier<PaintingVariant> RED_POSTER_SPRITE = register("red_poster_sprite", 16, 16);
    public static RegistrySupplier<PaintingVariant> RED_SCROLL = register("red_scroll", 64, 32);
    public static RegistrySupplier<PaintingVariant> REGIONAL_AWARD = register("regional_award", 64, 32);
    public static RegistrySupplier<PaintingVariant> SEA_POSTER = register("sea_poster", 64, 32);
    public static RegistrySupplier<PaintingVariant> SEA_POSTER_SPRITE = register("sea_poster_sprite", 32, 16);
    public static RegistrySupplier<PaintingVariant> SKY_POSTER = register("sky_poster", 64, 32);
    public static RegistrySupplier<PaintingVariant> SKY_POSTER_SPRITE = register("sky_poster_sprite", 32, 16);
    public static RegistrySupplier<PaintingVariant> TIME_TRAVEL_AWARD = register("time_travel_award", 64, 32);
    public static RegistrySupplier<PaintingVariant> TOWN_MAP_SPRITE = register("town_map_sprite", 16, 16);

    private static RegistrySupplier<PaintingVariant> register(String name, int width, int height) {
        return PAINTINGS.register(name, () -> new PaintingVariant(width, height, ResourceLocation.parse(""))); //TODO: REminder needed proper path for paintings here. Blank location is tempt
    }

    public static void init() {
        GenerationsCore.LOGGER.info("Registering Generations Paintings");
        PAINTINGS.register();
    }
}
