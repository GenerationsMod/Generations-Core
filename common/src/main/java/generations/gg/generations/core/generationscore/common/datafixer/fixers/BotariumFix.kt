package generations.gg.generations.core.generationscore.common.datafixer.fixers

import com.mojang.datafixers.DataFix
import com.mojang.datafixers.TypeRewriteRule
import com.mojang.datafixers.schemas.Schema
import generations.gg.generations.core.generationscore.common.datafixer.schema.GenerationsReferences

class BotariumFix(outputSchema: Schema) : DataFix(outputSchema, false) {
    override fun makeRule(): TypeRewriteRule {
        var oldForge = inputSchema.getType(GenerationsReferences.TERRAIUM_FORGE_INVENTORY)
        var oldFabric = inputSchema.getType(GenerationsReferences.TERRAIUM_FORGE_INVENTORY)

//        return this.fixTypeEverywhereTyped()

        return TypeRewriteRule.nop()
    }
}