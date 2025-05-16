package generations.gg.generations.core.generationscore.common.util

import com.cobblemon.mod.common.api.berry.Flavor
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.PokemonProperties.Companion.parse
import com.google.gson.*
import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.JsonOps
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MutableBlockEntityType
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericModelBlock
import net.minecraft.locale.Language
import net.minecraft.nbt.NbtOps
import net.minecraft.nbt.Tag
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.StringRepresentable
import net.minecraft.world.Containers
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.projectile.ProjectileUtil
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.phys.HitResult
import org.joml.Vector3f
import java.lang.reflect.Type
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*
import java.util.function.BiFunction
import java.util.function.Predicate
import java.util.function.Supplier
import kotlin.math.floor

object GenerationsUtils {
    fun getIndex(translatedCoord: Double, startCoord: Int, validSegment: Int, clickSegment: Int, sizeMax: Int): Int {
        val testY = translatedCoord - startCoord

        if (translatedCoord >= startCoord && (testY % validSegment <= clickSegment)) {
            val slot = (testY / validSegment).toInt()

            if (MathUtils.between(slot.toDouble(), 0, sizeMax)) return slot
        }

        return -1
    }

    fun <T : Enum<T>?> getByName(name: String, tClass: Class<T>?): Optional<T> {
        return EnumSet.allOf(tClass).stream()
            .filter { arg: T -> (if (arg is StringRepresentable) arg.serializedName else arg!!.name) == name }
            .findFirst()
    }

    fun getFlavorLocalizedName(flavor: Flavor?): String {
        return if (flavor != null) Language.getInstance()
            .getOrDefault("enum.flavor." + flavor.toString().lowercase()) else ""
    }

    fun <T> decode(codec: Codec<T>, v: JsonObject): T {
        return codec.decode(JsonOps.INSTANCE, v).getOrThrow().first
    }

    @JvmStatic
    fun rgbFromInt(color: Int): Vector3f {
        val d = (color shr 16 and 0xFF) / 255.0f
        val e = (color shr 8 and 0xFF) / 255.0f
        val f = (color and 0xFF) / 255.0f
        return Vector3f(d, e, f)
    }

    @JvmStatic
    fun getInGameDayTime(level: Level): Time {
        val inGameDayTime = ((level.dayTime % 24000) + 6000).toInt()
        val hours = (inGameDayTime / 1000).toByte()
        val minutesAndSeconds = ((inGameDayTime % 1000) / 1000.0 * 60.0)
        val minutes = floor(minutesAndSeconds) as Byte
        val seconds = ((minutesAndSeconds - minutes) * 60).toInt().toByte()
        return Time.of(hours, minutes, seconds)
    }

    fun jsonToNbt(tag: Tag): JsonElement {
        return NbtOps.INSTANCE.convertTo(JsonOps.INSTANCE, tag)
    }

    fun jsonToNbt(json: JsonElement?): Tag {
        return JsonOps.INSTANCE.convertTo(NbtOps.INSTANCE, json)
    }

    fun <T, K, V> processIfNotNull(t: T?, k: K, function: BiFunction<T, K, V>): V? {
        return if (t != null) function.apply(t, k) else null
    }

    fun <T> codec(codec: Codec<T>): Serializer<T> {
        return Serializer(codec)
    }

    @JvmStatic
    fun parseProperties(data: String): PokemonProperties {
        return parse(data, " ", "=")
    }

    fun readBigDecimal(buf: FriendlyByteBuf): BigDecimal {
        val scale = buf.readInt()
        val unscaled = ByteArray(buf.readInt())
        buf.readBytes(unscaled)
        return BigDecimal(BigInteger(unscaled), scale)
    }

    fun writeBigDecimal(buf: FriendlyByteBuf, value: BigDecimal) {
        buf.writeInt(value.scale())
        val unscaled = value.unscaledValue().toByteArray()
        buf.writeInt(unscaled.size)
        buf.writeBytes(unscaled)
    }

    fun giveItem(player: ServerPlayer, stack: ItemStack) {
        if (!player.inventory.add(stack)) {
            Containers.dropItemStack(
                player.level(),
                player.position().x,
                player.position().y,
                player.position().z,
                stack
            )
        }
    }

    fun <T : Block> registerBlock(
        deferredRegister: DeferredRegister<Block>,
        name: String,
        blockSupplier: Supplier<T>
    ): RegistrySupplier<T> {
        return deferredRegister.register(name, applyMutable(blockSupplier))
    }

    private fun <T : Block?> applyMutable(blockSupplier: Supplier<T>): Supplier<T> {
        return Supplier {
            val block = blockSupplier.get()
            if (block is GenericModelBlock<*>) {
                MutableBlockEntityType.blocksToAdd.add(block)
            }
            block
        }
    }

    fun raycast(entity: Entity, maxDistance: Double, tickDelta: Float, predicate: Predicate<Entity?>): HitResult? {
        val vec3d = entity.getEyePosition(tickDelta)
        val vec3d2 = entity.getViewVector(tickDelta)
        val vec3d3 = vec3d.add(vec3d2.x * maxDistance, vec3d2.y * maxDistance, vec3d2.z * maxDistance)
        val box = entity.boundingBox.expandTowards(vec3d2.scale(maxDistance)).inflate(1.0, 1.0, 1.0)
        return ProjectileUtil.getEntityHitResult(entity, vec3d, vec3d3, box, predicate, maxDistance)
    }

    @JvmRecord
    data class Serializer<T>(val codec: Codec<T>) : JsonSerializer<T>,
        JsonDeserializer<T> {
        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): T {
            return JsonOps.INSTANCE.withDecoder(codec)
                .andThen { obj: DataResult<Pair<T, JsonElement?>> -> obj.result() }
                .apply(json).orElseThrow().first
        }

        override fun serialize(src: T, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
            return JsonOps.INSTANCE.withEncoder(codec).andThen { obj: DataResult<JsonElement> -> obj.result() }
                .apply(src).orElseThrow()
        }
    }
}
