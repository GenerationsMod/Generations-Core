package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.decoration.PaintingVariant;

@SuppressWarnings("unused")
public class GenerationsPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.PAINTING_VARIANT);
    public static RegistrySupplier<PaintingVariant> ALAKAZAM = register("alakazam", 32, 16);
    public static RegistrySupplier<PaintingVariant> ARTICUNO = register("articuno", 32, 16);
    public static RegistrySupplier<PaintingVariant> BUTTERFREE = register("butterfree", 16, 32);
    public static RegistrySupplier<PaintingVariant> CHARIZARD_X = register("charizard_x", 32, 32);
    public static RegistrySupplier<PaintingVariant> ELECTRIC = register("electric", 16, 16);
    public static RegistrySupplier<PaintingVariant> FIGHTING = register("fighting", 16, 16);
    public static RegistrySupplier<PaintingVariant> FIRE = register("fire", 16, 16);
    public static RegistrySupplier<PaintingVariant> GARDEVOIR = register("gardevoir", 32, 32);
    public static RegistrySupplier<PaintingVariant> GENGAR = register("gengar", 16, 16);
    public static RegistrySupplier<PaintingVariant> GRASS = register("grass", 16, 16);
    public static RegistrySupplier<PaintingVariant> GROUDON = register("groudon", 64, 48);
    public static RegistrySupplier<PaintingVariant> HOOPA = register("hoopa", 64, 64);
    public static RegistrySupplier<PaintingVariant> KYOGRE = register("kyogre", 32, 32);
    public static RegistrySupplier<PaintingVariant> LUGIA = register("lugia", 32, 16);
    public static RegistrySupplier<PaintingVariant> MEGA_LUCARIO = register("mega_lucario", 64, 64);
    public static RegistrySupplier<PaintingVariant> MEWTWO = register("mewtwo", 64, 32);
    public static RegistrySupplier<PaintingVariant> MOLTRES = register("moltres", 32, 16);
    public static RegistrySupplier<PaintingVariant> POKEBALL = register("pokeball", 16, 16);
    public static RegistrySupplier<PaintingVariant> PSYCHIC = register("psychic", 16, 16);
    public static RegistrySupplier<PaintingVariant> RAYQUAZA = register("rayquaza", 32, 32);
    public static RegistrySupplier<PaintingVariant> RED_GYRADOS = register("red_gyrados", 64, 48);
    public static RegistrySupplier<PaintingVariant> RETRO_RED = register("retro_red", 32, 32);
    public static RegistrySupplier<PaintingVariant> SHINY_BUTTERFREE = register("shiny_butterfree", 16, 32);
    public static RegistrySupplier<PaintingVariant> TYRANITAR = register("tyranitar", 64, 64);
    public static RegistrySupplier<PaintingVariant> WATER = register("water", 16, 16);
    public static RegistrySupplier<PaintingVariant> ZAPDOS = register("zapdos", 32, 16);
    public static RegistrySupplier<PaintingVariant> SCARLET_HOUSE = register("house", 96, 64);

    private static RegistrySupplier<PaintingVariant> register(String name, int width, int height) {
        return PAINTINGS.register(name, () -> new PaintingVariant(width, height));
    }

    public static void onInitialize() {
        GenerationsCore.LOGGER.info("Registering PokeMod Paintings");
        PAINTINGS.register();
    }
}
