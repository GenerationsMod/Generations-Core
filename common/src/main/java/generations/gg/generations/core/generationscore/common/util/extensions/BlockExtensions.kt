package generations.gg.generations.core.generationscore.common.util.extensions

fun Int.between(min: Int, max: Int): Boolean {
    return this in min..max
}
