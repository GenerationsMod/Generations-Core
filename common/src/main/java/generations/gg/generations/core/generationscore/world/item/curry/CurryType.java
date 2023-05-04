package generations.gg.generations.core.generationscore.world.item.curry;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.berry.ICurryRarity;
import net.minecraft.locale.Language;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum CurryType implements ICurryRarity, StringRepresentable {
    None("plain", "none", "default_haze", 0),
    Sausage("sausage", "sausages", "default_haze", 2),
    Juicy("juicy", "bobs_food_tin", "default_haze", 3),
    Rich("rich", "bachs_food_tin", "default_haze", 3),
    BeanMedley("bean_medley", "tin_of_beans", "default_haze", 1),
    Toast("toast", "bread", "default_haze", 1),
    Pasta("pasta", "pasta", "default_haze", 1),
    MushroomMedley("mushroom_medley", "mixed_mushrooms", "default_haze", 1),
    SmokedTail("smoked_tail", "smoked_poke_tail", "smoked_tail_haze", 4),
    Leek("leek", "large_leek", "leek_haze", 4),
    Apple("apple", "fancy_apple", "apple_haze", 4),
    Bone("bone", "brittle_bones", "bone_haze", 4),
    PlentyOfPotato("potato", "pack_of_potatoes", "default_haze", 2),
    HerbMedley("herb_medley", "pungent_root", "default_haze", 4),
    Salad("salad", "salad_mix", "default_haze", 2),
    FriedFood("fried_food", "fried_food", "fried_food_haze", 1),
    BoiledEgg("boiled_egg", "boiled_egg", "boiled_egg_haze", 4),
    Tropical("tropical", "fruit_bunch", "default_haze", 4),
    CheeseCovered("cheese_covered", "moomoo_cheese", "default_haze", 4),
    Seasoned("seasoned", "spice_mix", "default_haze", 2),
    WhippedCream("whipped_cream", "fresh_cream", "whipped_cream_haze", 3),
    Decorative("decorative", "packaged_curry", "default_haze", 3),
    Coconut("coconut", "coconut_milk", "default_haze", 3),
    InstantNoodle("instant_noodle", "instant_noodles", "instant_noodles_haze", 1),
    BurgerSteak("burger_steak", "precooked_burger", "default_haze", 1),
    Gigantamax("gigantamax", "gigantamix", "none_haze", 10);

    private final ResourceLocation typeLocation;
    private final int rarity;
    private final String type;

    CurryType(String type, String ingredient, String haze, int rarity) {
        this.typeLocation = GenerationsCore.id("item/curry/item/" + type);
        this.type = type;

        this.rarity = rarity;
    }

    public String getLocalizedName() {
        return Language.getInstance().getOrDefault("pixelmon.curry." + this.toString().toLowerCase(Locale.ENGLISH));
    }

    public static CurryType getCurryTypeFromIndex(int index) {
        return CurryType.values()[index];
    }

    public ResourceLocation getResourceLocation() {
        return typeLocation;
    }

    public int getRarity() {
        return rarity;
    }

    @Override
    public @NotNull String getSerializedName() {
        return type;
    }
}
