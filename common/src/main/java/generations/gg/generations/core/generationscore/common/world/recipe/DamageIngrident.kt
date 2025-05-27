package generations.gg.generations.core.generationscore.common.world.recipe

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredientType
import generations.gg.generations.core.generationscore.common.util.Codecs.codec
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike

class DamageIngredient(var item: Item, var damage: Int) : GenerationsIngredient {
    override val id = ID
    override fun matches(stack: ItemStack): Boolean = stack.`is` (item) && stack.damageValue == damage

    override fun matchingStacks(): List<ItemStack> = listOf(item.defaultInstance)

    override val type: GenerationsIngredientType<*>
        get() = GenerationsIngredidents.DAMAGE

    companion object {
        val ID = "damage"

        val CODEC: MapCodec<DamageIngredient> = RecordCodecBuilder.mapCodec {
            it.group(
                BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(DamageIngredient::item),
                Codec.INT.fieldOf("damage").forGetter(DamageIngredient::damage)
            ).apply(it, ::DamageIngredient)
        }
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, DamageIngredient> = StreamCodec.composite(ByteBufCodecs.registry(Registries.ITEM), DamageIngredient::item, ByteBufCodecs.VAR_INT, DamageIngredient::damage, ::DamageIngredient)
    }
}

private fun ResourceKey<Item>.get(): Item? = BuiltInRegistries.ITEM.get(this)
