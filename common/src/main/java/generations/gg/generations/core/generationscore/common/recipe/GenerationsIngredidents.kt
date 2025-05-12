package generations.gg.generations.core.generationscore.common.recipe

import com.mojang.serialization.MapCodec
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.Registrar
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.recipe.*
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation

object GenerationsIngredidents {
    val REGISTER: Registrar<GenerationsIngredientType<*>> = DeferredRegister.create(GenerationsCore.MOD_ID, ResourceKey.createRegistryKey<GenerationsIngredientType<*>>(GenerationsCore.id("ingredients"))).registrar

    @JvmField val CODEC = ResourceLocation.CODEC.xmap(REGISTER::get, REGISTER::getId).xmap({ it!! }, { it }).dispatch(GenerationsIngredient::type, GenerationsIngredientType<*>::codec)
    @JvmField val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, GenerationsIngredient> = ByteBufCodecs.registry(REGISTER.key()).dispatch(GenerationsIngredient::type, GenerationsIngredientType<*>::streamCodec)

    val DAMAGE: RegistrySupplier<GenerationsIngredientType<DamageIngredient>> = register(DamageIngredient.ID, DamageIngredient.CODEC, DamageIngredient.STREAM_CODEC)
    val ITEM: RegistrySupplier<GenerationsIngredientType<ItemIngredient>> = register(ItemIngredient.ID, ItemIngredient.CODEC, ItemIngredient.STREAM_CODEC)
    val ITEM_TAG: RegistrySupplier<GenerationsIngredientType<ItemTagIngredient>> = register(ItemTagIngredient.ID, ItemTagIngredient.CODEC, ItemTagIngredient.STREAM_CODEC)
    val POKEMON_ITEM: RegistrySupplier<GenerationsIngredientType<PokemonItemIngredient>> = register(PokemonItemIngredient.ID, PokemonItemIngredient.CODEC, PokemonItemIngredient.STREAM_CODEC)
    val TIME_CAPSULE: RegistrySupplier<GenerationsIngredientType<TimeCapsuleIngredient>> = register(TimeCapsuleIngredient.ID, TimeCapsuleIngredient.CODEC, TimeCapsuleIngredient.STREAM_CODEC)
    val EMPTY = register("empty", MapCodec.unit(EmptyIngredient), StreamCodec.unit(EmptyIngredient));

    public fun <T> register(name: String, codec: MapCodec<T>, streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>): RegistrySupplier<GenerationsIngredientType<T>> where T: GenerationsIngredient = REGISTER.register(GenerationsCore.id(name)) { GenerationsIngredientType<T>(codec, streamCodec) }

    @JvmStatic fun init() {}
}
