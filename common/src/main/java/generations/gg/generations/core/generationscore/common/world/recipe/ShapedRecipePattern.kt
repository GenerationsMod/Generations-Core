package generations.gg.generations.core.generationscore.common.world.recipe

import com.google.common.annotations.VisibleForTesting
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents.EMPTY
import generations.gg.generations.core.generationscore.common.recipe.RksInput
import it.unimi.dsi.fastutil.chars.CharArraySet
import it.unimi.dsi.fastutil.chars.CharSet
import net.minecraft.Util
import net.minecraft.core.NonNullList
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.util.ExtraCodecs
import java.util.*
import java.util.function.Consumer
import java.util.function.Function
import kotlin.math.max
import kotlin.math.min

class ShapedRecipePattern(
    private val width: Int,
    private val height: Int,
    private val ingredients: NonNullList<GenerationsIngredient>,
    private val data: Optional<Data>
) {
    private val ingredientCount: Int
    private val symmetrical: Boolean

    fun matches(input: RksInput): Boolean {
        if (input.ingredientCount() != this.ingredientCount) {
            return false
        } else {
            if (input.width() == this.width && input.height() == this.height) {
                if (!this.symmetrical && this.matches(input, true)) {
                    return true
                }

                if (this.matches(input, false)) {
                    return true
                }
            }

            return false
        }
    }

    private fun matches(input: RksInput, symmetrical: Boolean): Boolean {
        for (i in 0 until this.height) {
            for (j in 0 until this.width) {
                var ingredient = if (symmetrical) {
                    ingredients[width - j - 1 + i * this.width]
                } else {
                    ingredients[j + i * this.width]
                }

                val itemStack = input.getItem(j, i)
                if (!ingredient.matches(itemStack)) {
                    return false
                }
            }
        }

        return true
    }

    private fun toNetwork(buffer: RegistryFriendlyByteBuf) {
        buffer.writeVarInt(this.width)
        buffer.writeVarInt(this.height)

        buffer.writeVarInt(ingredients.size)

        ingredients.forEach(Consumer { ingredient: GenerationsIngredient ->
            GenerationsIngredidents.STREAM_CODEC.encode(
                buffer,
                ingredient
            )
        })
    }

    fun width(): Int {
        return this.width
    }

    fun height(): Int {
        return this.height
    }

    fun ingredients(): NonNullList<GenerationsIngredient> {
        return this.ingredients
    }

    init {
        var i = 0

        for (ingredient in ingredients) {
            if (!ingredient.isEmpty) {
                ++i
            }
        }

        this.ingredientCount = i
        this.symmetrical = Util.isSymmetrical(
            width,
            height,
            ingredients
        )
    }

    @JvmRecord
    data class Data(val key: Map<Char, GenerationsIngredient>, val pattern: List<String>) {
        companion object {
            private val PATTERN_CODEC: Codec<List<String>> =
                Codec.STRING.listOf().comapFlatMap<List<String>>(
                    { list: List<String> ->
                        if (list.size > 3) {
                            return@comapFlatMap DataResult.error<List<String>> { "Invalid pattern: too many rows, 3 is maximum" }
                        } else if (list.isEmpty()) {
                            return@comapFlatMap DataResult.error<List<String>> { "Invalid pattern: empty pattern not allowed" }
                        } else {
                            val width: Int = list.first().length

                            for (row in list) {
                                if (row.length > 3) {
                                    return@comapFlatMap DataResult.error<List<String>> { "Invalid pattern: too many columns, 3 is maximum" }
                                }
                                if (row.length != width) {
                                    return@comapFlatMap DataResult.error<List<String>> { "Invalid pattern: each row must be the same width" }
                                }
                            }

                            return@comapFlatMap DataResult.success<List<String>>(list)
                        }
                    }, Function.identity<List<String>>()
                )
            private val SYMBOL_CODEC: Codec<Char> = Codec.STRING.comapFlatMap(
                { string: String ->
                    if (string.length != 1) {
                        return@comapFlatMap DataResult.error<Char> { "Invalid key entry: '$string' is an invalid symbol (must be 1 character only)." }
                    } else {
                        return@comapFlatMap if (" " == string) DataResult.error<Char> { "Invalid key entry: ' ' is a reserved symbol." } else DataResult.success<Char>(
                            string[0]
                        )
                    }
                },
                { obj: Char? -> java.lang.String.valueOf(obj) })
            val MAP_CODEC: MapCodec<Data> =
                RecordCodecBuilder.mapCodec { instance: RecordCodecBuilder.Instance<Data> ->
                    instance.group(
                        ExtraCodecs.strictUnboundedMap(
                            SYMBOL_CODEC,
                            GenerationsIngredidents.CODEC.validate { ingredient: GenerationsIngredient ->
                                if (ingredient.type == EMPTY) DataResult.error { "Ingrident can't be empty" } else DataResult.success(
                                    ingredient
                                )
                            }).fieldOf("key")
                            .forGetter(
                                Data::key
                            ),
                        PATTERN_CODEC.fieldOf(
                            "pattern"
                        )
                            .forGetter(
                                Data::pattern
                            )
                    )
                        .apply(
                            instance
                        ) { key: Map<Char, GenerationsIngredient>, pattern: List<String> ->
                            Data(
                                key,
                                pattern
                            )
                        }
                }
        }
    }

    companion object {
        private const val MAX_SIZE = 3
        val MAP_CODEC: MapCodec<ShapedRecipePattern>
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, ShapedRecipePattern>
        fun of(key: Map<Char, GenerationsIngredient>, vararg pattern: String): ShapedRecipePattern {
            return of(key, pattern.toList())
        }

        fun of(key: Map<Char, GenerationsIngredient>, pattern: List<String>): ShapedRecipePattern {
            val data = Data(key, pattern)
            return unpack(data).getOrThrow()
        }

        private fun unpack(data: Data): DataResult<ShapedRecipePattern> {
            val strings = shrink(data.pattern)
            val i = strings[0]!!.length
            val j = strings.size
            val nonNullList = NonNullList.withSize<GenerationsIngredient>(i * j, EmptyIngredient)
            val charSet: CharSet = CharArraySet(data.key.keys)

            for (k in strings.indices) {
                val string = strings[k]

                for (l in 0 until string!!.length) {
                    val c = string[l]
                    val ingredient = if (c == ' ') EmptyIngredient else data.key[c]
                    if (ingredient == null) {
                        return DataResult.error { "Pattern references symbol '$c' but it's not defined in the key" }
                    }

                    charSet.remove(c)
                    nonNullList[l + i * k] = ingredient
                }
            }

            return if (!charSet.isEmpty()) {
                DataResult.error { "Key defines symbols that aren't used in pattern: $charSet" }
            } else {
                DataResult.success(ShapedRecipePattern(i, j, nonNullList, Optional.of(data)))
            }
        }

        @VisibleForTesting
        fun shrink(pattern: List<String>): Array<String?> {
            var i = Int.MAX_VALUE
            var j = 0
            var k = 0
            var l = 0

            for (m in pattern.indices) {
                val string = pattern[m]
                i = min(
                    i.toDouble(),
                    firstNonSpace(
                        string
                    ).toDouble()
                ).toInt()
                val n = lastNonSpace(string)
                j = max(j.toDouble(), n.toDouble()).toInt()
                if (n < 0) {
                    if (k == m) {
                        ++k
                    }

                    ++l
                } else {
                    l = 0
                }
            }

            if (pattern.size == l) {
                return arrayOfNulls(0)
            } else {
                val strings = arrayOfNulls<String>(pattern.size - l - k)

                for (o in strings.indices) {
                    strings[o] = pattern[o + k].substring(i, j + 1)
                }

                return strings
            }
        }

        private fun firstNonSpace(row: String): Int {
            var i = 0
            while (i < row.length && row[i] == ' ') {
                ++i
            }

            return i
        }

        private fun lastNonSpace(row: String): Int {
            var i = row.length - 1
            while (i >= 0 && row[i] == ' ') {
                --i
            }

            return i
        }

        private fun fromNetwork(buffer: RegistryFriendlyByteBuf): ShapedRecipePattern {
            val i = buffer.readVarInt()
            val j = buffer.readVarInt()
            val nonNullList = NonNullList.withSize<GenerationsIngredient>(i * j, EmptyIngredient)
            nonNullList.replaceAll { ingredient: GenerationsIngredient? ->
                GenerationsIngredidents.STREAM_CODEC.decode(
                    buffer
                )
            }
            return ShapedRecipePattern(i, j, nonNullList, Optional.empty())
        }

        init {
            MAP_CODEC = Data.MAP_CODEC.flatXmap(
                { data: Data -> unpack(data) },
                { shapedRecipePattern: ShapedRecipePattern ->
                    shapedRecipePattern.data.map<DataResult<Data>> { result: Data? ->
                        DataResult.success(
                            result
                        )
                    }.orElseGet { DataResult.error { "Cannot encode unpacked recipe" } }
                })
            STREAM_CODEC = StreamCodec.ofMember(
                { obj: ShapedRecipePattern, buffer: RegistryFriendlyByteBuf -> obj.toNetwork(buffer) },
                { buffer: RegistryFriendlyByteBuf -> fromNetwork(buffer) })
        }
    }
}
