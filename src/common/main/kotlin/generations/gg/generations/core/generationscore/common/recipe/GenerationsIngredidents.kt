package generations.gg.generations.core.generationscore.common.recipe

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.asRegistryFriendly
import generations.gg.generations.core.generationscore.common.world.recipe.*
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceLocation

object GenerationsIngredidents {
//    val REGISTER: Registrar<GenerationsIngredientType<*>> = RegistrarManager.get(GenerationsCore.MOD_ID).builder<GenerationsIngredientType<*>>(GenerationsCore.id("rks_ingredients")).build()
    val MAP = mutableMapOf<ResourceLocation, GenerationsIngredientType<*>>()

//    @JvmField val CODEC = ResourceLocation.CODEC.xmap(REGISTER::get, REGISTER::getId).xmap({ it!! }, { it }).dispatch(GenerationsIngredient::type, GenerationsIngredientType<*>::codec)
//    @JvmField val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, GenerationsIngredient> = ByteBufCodecs.registry(REGISTER.key()).dispatch(GenerationsIngredient::type, GenerationsIngredientType<*>::streamCodec)

    @JvmField val CODEC = ResourceLocation.CODEC.xmap(MAP::get, { it?.id }).xmap({ it!! }, { it }).dispatch(GenerationsIngredient::type, GenerationsIngredientType<*>::codec)
    @JvmField val INGREDIENT_TYPE_STREAM_CODEC = ResourceLocation.STREAM_CODEC.asRegistryFriendly().map(MAP::get, { (it ?: EMPTY).id })
    @JvmField val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, GenerationsIngredient> = INGREDIENT_TYPE_STREAM_CODEC.dispatch({ it.type }, { it?.streamCodec })

    val DAMAGE = register(DamageIngredient.ID, DamageIngredient.CODEC, DamageIngredient.STREAM_CODEC)
    val ITEM = register(ItemIngredient.ID, ItemIngredient.CODEC, ItemIngredient.STREAM_CODEC)
    val ITEM_TAG = register(ItemTagIngredient.ID, ItemTagIngredient.CODEC, ItemTagIngredient.STREAM_CODEC)
    val POKEMON_ITEM = register(PokemonItemIngredient.ID, PokemonItemIngredient.CODEC, PokemonItemIngredient.STREAM_CODEC)
    val TIME_CAPSULE = register(TimeCapsuleIngredient.ID, TimeCapsuleIngredient.CODEC, TimeCapsuleIngredient.STREAM_CODEC)
    val EMPTY = register("empty", MapCodec.unit(EmptyIngredient), StreamCodec.unit(EmptyIngredient));

//    public fun <T> register(name: String, codec: MapCodec<T>, streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>): RegistrySupplier<GenerationsIngredientType<T>> where T: GenerationsIngredient = {
//        REGISTER.register(GenerationsCore.id(name)) { GenerationsIngredientType<T>(codec, streamCodec) }
//    }

    public fun <T> register(name: String, codec: MapCodec<T>, streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>): GenerationsIngredientType<T> where T: GenerationsIngredient {
        val id = GenerationsCore.id(name)
        val type = GenerationsIngredientType<T>(id, codec, streamCodec)

        MAP.put(id, type)
        return type
    }

    @JvmStatic fun init() {
    }
}
