package generations.gg.generations.core.generationscore.common.client.render.tera

import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.api.types.tera.TeraTypes
import org.joml.Vector3f

private val TERA_TYPE_COLORS = mapOf(
    TeraTypes.BUG to Vector3f(0.6f, 0.8f, 0.3f),
    TeraTypes.DARK to Vector3f(0.2f, 0.2f, 0.25f),
    TeraTypes.DRAGON to Vector3f(0.4f, 0.2f, 1f),
    TeraTypes.ELECTRIC to Vector3f(1f, 1f, 0.3f),
    TeraTypes.FAIRY to Vector3f(1f, 0.7f, 1f),
    TeraTypes.FIGHTING to Vector3f(0.8f, 0.2f, 0.2f),
    TeraTypes.FIRE to Vector3f(1f, 0.4f, 0.3f),
    TeraTypes.FLYING to Vector3f(0.6f, 0.8f, 1f),
    TeraTypes.GHOST to Vector3f(0.4f, 0.3f, 0.6f),
    TeraTypes.GRASS to Vector3f(0.3f, 0.9f, 0.2f),
    TeraTypes.GROUND to Vector3f(0.7f, 0.6f, 0.2f),
    TeraTypes.ICE to Vector3f(0.6f, 0.8f, 1f),
    TeraTypes.NORMAL to Vector3f(1f, 1f, 1f),
    TeraTypes.POISON to Vector3f(0.7f, 0.3f, 0.7f),
    TeraTypes.PSYCHIC to Vector3f(0.8f, 0.3f, 0.8f),
    TeraTypes.ROCK to Vector3f(0.6f, 0.5f, 0.3f),
    TeraTypes.STEEL to Vector3f(0.7f, 0.7f, 0.8f),
    TeraTypes.STELLAR to Vector3f(1.0f, 0.75f, 0.9f),
    TeraTypes.WATER to Vector3f(0.2f, 0.5f, 1f)
)

val ONE = Vector3f(1f,1f,1f);

val TeraType?.tint: Vector3f
    get() = TERA_TYPE_COLORS[this] ?: ONE
