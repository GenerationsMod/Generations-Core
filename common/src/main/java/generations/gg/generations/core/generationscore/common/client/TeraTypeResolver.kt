package generations.gg.generations.core.generationscore.common.client

import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.api.types.tera.TeraTypes

fun resolveTeraType(aspects: Set<String>): TeraType? {
    TeraTypes.forEach { teraType ->
        if (aspects.contains("tera_${teraType.id.path}")) return teraType
    }
    return null
}