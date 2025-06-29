package generations.gg.generations.core.generationscore.common.world.level.block;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

@SuppressWarnings("unused")
public class GenerationsPaintings {
    public static ResourceKey<PaintingVariant> BLUE_POSTER = create("blue_poster");
    public static ResourceKey<PaintingVariant> BLUE_POSTER_SPRITE = create("blue_poster_sprite");
    public static ResourceKey<PaintingVariant> BLUE_SCROLL = create("blue_scroll");
    public static ResourceKey<PaintingVariant> CLEFAIRY_POSTER_SPRITE = create("clefairy_poster_sprite");
    public static ResourceKey<PaintingVariant> CUTE_POSTER = create("cute_poster");
    public static ResourceKey<PaintingVariant> CUTE_POSTER_SPRITE = create("cute_poster_sprite");
    public static ResourceKey<PaintingVariant> DADS_SCROLL = create("dads_scroll");
    public static ResourceKey<PaintingVariant> GREEN_POSTER = create("green_poster");
    public static ResourceKey<PaintingVariant> GREEN_POSTER_SPRITE = create("green_poster_sprite");
    public static ResourceKey<PaintingVariant> GREEN_SCROLL = create("green_scroll");
    public static ResourceKey<PaintingVariant> JIGGLYPUFF_POSTER_SPRITE = create("jigglypuff_poster_sprite");
    public static ResourceKey<PaintingVariant> KISS_POSTER_SPRITE = create("kiss_poster_sprite");
    public static ResourceKey<PaintingVariant> LONG_POSTER = create("long_poster");
    public static ResourceKey<PaintingVariant> LONG_POSTER_SPRITE = create("long_poster_sprite");
    public static ResourceKey<PaintingVariant> NATIONAL_AWARD = create("national_award");
    public static ResourceKey<PaintingVariant> PIKA_POSTER = create("pika_poster");
    public static ResourceKey<PaintingVariant> PIKA_POSTER_SPRITE = create("pika_poster_sprite");
    public static ResourceKey<PaintingVariant> PIKACHU_POSTER_SPRITE = create("pikachu_poster_sprite");
    public static ResourceKey<PaintingVariant> POKE_BALL_POSTER = create("poke_ball_poster");
    public static ResourceKey<PaintingVariant> RED_POSTER = create("red_poster");

    private static ResourceKey<PaintingVariant> create(String name) {
        return ResourceKey.create(Registries.PAINTING_VARIANT, GenerationsCore.id(name));
    }

    public static ResourceKey<PaintingVariant> RED_POSTER_SPRITE = create("red_poster_sprite");
    public static ResourceKey<PaintingVariant> RED_SCROLL = create("red_scroll");
    public static ResourceKey<PaintingVariant> REGIONAL_AWARD = create("regional_award");
    public static ResourceKey<PaintingVariant> SEA_POSTER = create("sea_poster");
    public static ResourceKey<PaintingVariant> SEA_POSTER_SPRITE = create("sea_poster_sprite");
    public static ResourceKey<PaintingVariant> SKY_POSTER = create("sky_poster");
    public static ResourceKey<PaintingVariant> SKY_POSTER_SPRITE = create("sky_poster_sprite");
    public static ResourceKey<PaintingVariant> TIME_TRAVEL_AWARD = create("time_travel_award");
    public static ResourceKey<PaintingVariant> TOWN_MAP_SPRITE = create("town_map_sprite");

    private static ResourceLocation getDirectory(String name) {
        return GenerationsCore.id(name);
    }

    public static void init(BootstrapContext<PaintingVariant> context) {
        GenerationsCore.LOGGER.info("Registering Generations Paintings");
        context.register(BLUE_POSTER, new PaintingVariant(4, 4, getDirectory("blue_poster")));
        context.register(BLUE_POSTER_SPRITE, new PaintingVariant(1, 1, getDirectory("blue_poster_sprite")));
        context.register(BLUE_SCROLL, new PaintingVariant(4, 2, getDirectory("blue_scroll")));
        context.register(CLEFAIRY_POSTER_SPRITE, new PaintingVariant(1, 1, getDirectory("clefairy_poster_sprite")));
        context.register(CUTE_POSTER, new PaintingVariant(4, 4, getDirectory("cute_poster")));
        context.register(CUTE_POSTER_SPRITE, new PaintingVariant(1, 1, getDirectory("cute_poster_sprite")));
        context.register(DADS_SCROLL, new PaintingVariant(4, 2, getDirectory("dads_scroll")));
        context.register(GREEN_POSTER, new PaintingVariant(4, 4, getDirectory("green_poster")));
        context.register(GREEN_POSTER_SPRITE, new PaintingVariant(1, 1, getDirectory("green_poster_sprite")));
        context.register(GREEN_SCROLL, new PaintingVariant(4, 2, getDirectory("green_scroll")));
        context.register(JIGGLYPUFF_POSTER_SPRITE, new PaintingVariant(1, 1, getDirectory("jigglypuff_poster_sprite")));
        context.register(KISS_POSTER_SPRITE, new PaintingVariant(4, 2, getDirectory("kiss_poster_sprite")));
        context.register(LONG_POSTER, new PaintingVariant(4, 2, getDirectory("long_poster")));
        context.register(LONG_POSTER_SPRITE, new PaintingVariant(2, 1, getDirectory("long_poster_sprite")));
        context.register(NATIONAL_AWARD, new PaintingVariant(4, 2, getDirectory("national_award")));
        context.register(PIKA_POSTER, new PaintingVariant(4, 2, getDirectory("pika_poster")));
        context.register(PIKA_POSTER_SPRITE, new PaintingVariant(2, 1, getDirectory("pika_poster_sprite")));
        context.register(PIKACHU_POSTER_SPRITE, new PaintingVariant(1, 1, getDirectory("pikachu_poster_sprite")));
        context.register(POKE_BALL_POSTER, new PaintingVariant(4, 4, getDirectory("poke_ball_poster")));
        context.register(RED_POSTER, new PaintingVariant(4, 4, getDirectory("red_poster")));
        context.register(RED_POSTER_SPRITE, new PaintingVariant(1, 1, getDirectory("red_poster_sprite")));
        context.register(RED_SCROLL, new PaintingVariant(4, 2, getDirectory("red_scroll")));
        context.register(REGIONAL_AWARD, new PaintingVariant(4, 2, getDirectory("regional_award")));
        context.register(SEA_POSTER, new PaintingVariant(4, 2, getDirectory("sea_poster")));
        context.register(SEA_POSTER_SPRITE, new PaintingVariant(2, 1, getDirectory("sea_poster_sprite")));
        context.register(SKY_POSTER, new PaintingVariant(4, 2, getDirectory("sky_poster")));
        context.register(SKY_POSTER_SPRITE, new PaintingVariant(2, 1, getDirectory("sky_poster_sprite")));
        context.register(TIME_TRAVEL_AWARD, new PaintingVariant(4, 2, getDirectory("time_travel_award")));
        context.register(TOWN_MAP_SPRITE, new PaintingVariant(1, 1, getDirectory("town_map_sprite")));
    }
}
