package generations.gg.generations.core.generationscore.common.util

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import net.minecraft.network.FriendlyByteBuf

 fun FriendlyByteBuf.writeNullableString(value: String?): Unit = this.writeNullable(value, FriendlyByteBuf::writeUtf)
fun FriendlyByteBuf.readNullableString(): String? = this.readNullable(FriendlyByteBuf::readUtf)
 fun FriendlyByteBuf.writePokemonProperties(value: PokemonProperties) {
  this.writeUtf(value.asString())
 }
fun FriendlyByteBuf.readPokemonProperties(): PokemonProperties = PokemonProperties.parse(this.readUtf())