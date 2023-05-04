package generations.gg.generations.core.generationscore.world.level.block;

import com.pokemod.pokemod.PokeMod;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class PokeModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, PokeMod.MOD_ID);
    public static RegistryObject<PaintingVariant> ALAKAZAM = register("alakazam", 32, 16);
    public static RegistryObject<PaintingVariant> ARTICUNO = register("articuno", 32, 16);
    public static RegistryObject<PaintingVariant> BUTTERFREE = register("butterfree", 16, 32);
    public static RegistryObject<PaintingVariant> CHARIZARD_X = register("charizard_x", 32, 32);
    public static RegistryObject<PaintingVariant> ELECTRIC = register("electric", 16, 16);
    public static RegistryObject<PaintingVariant> FIGHTING = register("fighting", 16, 16);
    public static RegistryObject<PaintingVariant> FIRE = register("fire", 16, 16);
    public static RegistryObject<PaintingVariant> GARDEVOIR = register("gardevoir", 32, 32);
    public static RegistryObject<PaintingVariant> GENGAR = register("gengar", 16, 16);
    public static RegistryObject<PaintingVariant> GRASS = register("grass", 16, 16);
    public static RegistryObject<PaintingVariant> GROUDON = register("groudon", 64, 48);
    public static RegistryObject<PaintingVariant> HOOPA = register("hoopa", 64, 64);
    public static RegistryObject<PaintingVariant> KYOGRE = register("kyogre", 32, 32);
    public static RegistryObject<PaintingVariant> LUGIA = register("lugia", 32, 16);
    public static RegistryObject<PaintingVariant> MEGA_LUCARIO = register("mega_lucario", 64, 64);
    public static RegistryObject<PaintingVariant> MEWTWO = register("mewtwo", 64, 32);
    public static RegistryObject<PaintingVariant> MOLTRES = register("moltres", 32, 16);
    public static RegistryObject<PaintingVariant> POKEBALL = register("pokeball", 16, 16);
    public static RegistryObject<PaintingVariant> PSYCHIC = register("psychic", 16, 16);
    public static RegistryObject<PaintingVariant> RAYQUAZA = register("rayquaza", 32, 32);
    public static RegistryObject<PaintingVariant> RED_GYRADOS = register("red_gyrados", 64, 48);
    public static RegistryObject<PaintingVariant> RETRO_RED = register("retro_red", 32, 32);
    public static RegistryObject<PaintingVariant> SHINY_BUTTERFREE = register("shiny_butterfree", 16, 32);
    public static RegistryObject<PaintingVariant> TYRANITAR = register("tyranitar", 64, 64);
    public static RegistryObject<PaintingVariant> WATER = register("water", 16, 16);
    public static RegistryObject<PaintingVariant> ZAPDOS = register("zapdos", 32, 16);
    public static RegistryObject<PaintingVariant> SCARLET_HOUSE = register("house", 96, 64);

    private static RegistryObject<PaintingVariant> register(String name, int width, int height) {
        return PAINTINGS.register(name, () -> new PaintingVariant(width, height));
    }

    public static void onInitialize(IEventBus eventBus) {
        PokeMod.LOGGER.info("Registering PokeMod Paintings");
        PAINTINGS.register(eventBus);
    }
}
