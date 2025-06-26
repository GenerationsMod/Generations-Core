package generations.gg.generations.core.generationscore.common.world.item.curry

import com.cobblemon.mod.common.api.berry.Flavor
import generations.gg.generations.core.generationscore.common.GenerationsCore.id
import generations.gg.generations.core.generationscore.common.util.StreamCodecs
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.asRegistryFriendly
import net.minecraft.locale.Language
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.StringRepresentable
import java.util.*
import java.util.function.Function
import java.util.stream.Stream

enum class CurryType(private val type: String, ingredient: String, haze: String, private val rarity: Int) :
    ICurryRarity, StringRepresentable {
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

    val resourceLocation: ResourceLocation = id("item/curry/item/$type")

    val localizedName: String
        get() = Language.getInstance()
            .getOrDefault("generations_core.curry." + toString().lowercase())

    override fun getRarity(): Int {
        return rarity
    }

    override fun getSerializedName(): String {
        return type
    }

    companion object {
        val CODEC = StringRepresentable.fromEnum { CurryType.entries.toTypedArray() }
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, CurryType> = ByteBufCodecs.STRING_UTF8.map(CurryType::get, CurryType::getSerializedName).asRegistryFriendly()

        fun getCurryTypeFromIndex(index: Int): CurryType {
            return entries[index]
        }

        fun get(name: String): CurryType {
            return Stream.of(*entries.toTypedArray()).filter { a: CurryType -> a.serializedName == name }.findAny()
                .orElse(
                    None
                )
        }
    }
}
