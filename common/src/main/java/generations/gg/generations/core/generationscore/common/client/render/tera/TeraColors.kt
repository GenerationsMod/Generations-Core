package generations.gg.generations.core.generationscore.common.client.render.tera

import org.joml.Vector3f

private val TERA_TYPE_COLORS = mapOf(
    "bug" to Vector3f(0.6f, 0.8f, 0.3f),
    "dark" to Vector3f(0.2f, 0.2f, 0.25f),
    "dragon" to Vector3f(0.4f, 0.2f, 1f),
    "electric" to Vector3f(1f, 1f, 0.3f),
    "fairy" to Vector3f(1f, 0.7f, 1f),
    "fighting" to Vector3f(0.8f, 0.2f, 0.2f),
    "fire" to Vector3f(1f, 0.4f, 0.3f),
    "flying" to Vector3f(0.6f, 0.8f, 1f),
    "ghost" to Vector3f(0.4f, 0.3f, 0.6f),
    "grass" to Vector3f(0.3f, 0.9f, 0.2f),
    "ground" to Vector3f(0.7f, 0.6f, 0.2f),
    "ice" to Vector3f(0.6f, 0.8f, 1f),
    "normal" to Vector3f(1f, 1f, 1f),
    "poison" to Vector3f(0.7f, 0.3f, 0.7f),
    "psychic" to Vector3f(0.8f, 0.3f, 0.8f),
    "rock" to Vector3f(0.6f, 0.5f, 0.3f),
    "steel" to Vector3f(0.7f, 0.7f, 0.8f),
    "stellar" to Vector3f(1.0f, 0.75f, 0.9f),
    "water" to Vector3f(0.2f, 0.5f, 1f)
)

fun lookupTeraColor(type: String): Vector3f =
    TERA_TYPE_COLORS[type.lowercase()] ?: Vector3f(1f, 1f, 1f)