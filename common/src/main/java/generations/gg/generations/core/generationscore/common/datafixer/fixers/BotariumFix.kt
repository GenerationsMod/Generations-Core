package generations.gg.generations.core.generationscore.common.datafixer.fixers

import com.mojang.datafixers.DSL
import com.mojang.datafixers.DataFix
import com.mojang.datafixers.TypeRewriteRule
import com.mojang.datafixers.types.Type
import com.mojang.serialization.Dynamic
import generations.gg.generations.core.generationscore.common.orFalse
import net.minecraft.util.datafix.fixes.References

class BotariumFix(outputSchema: com.mojang.datafixers.schemas.Schema) :
    DataFix(outputSchema, false) {

    private val TARGET_IDS = setOf(
        "generations_core:regigigas_shrine",
        "generations_core:cooking_pot"
    )

    override fun makeRule(): TypeRewriteRule {
        val blockEntityType: Type<*> = outputSchema.getType(References.BLOCK_ENTITY)

        return fixTypeEverywhereTyped(
            "BotariumItemsStructureFix",
            blockEntityType
        ) { typed ->
            val dynamic = typed.get(DSL.remainderFinder())

            val idOpt = dynamic.get("id").asString().result()
            if (idOpt != null && idOpt.filter { TARGET_IDS.contains(it) }.isPresent) {
                val updatedDynamic = moveBotariumItems(dynamic)
                typed.update(DSL.remainderFinder()) { updatedDynamic }
            } else {
                typed
            }
        }
    }

    private fun moveBotariumItems(dynamic: Dynamic<*>): Dynamic<*> {
        val itemsOpt = dynamic
            .get("ForgeCaps")
            .get("botarium:item")
            .get("Items")
            .result()

        return if (itemsOpt.isPresent) {
            val items = itemsOpt.get()

            var updated = dynamic.set("Items", items)

            // Clean out ForgeCaps.botarium
            val newForgeCaps = dynamic.get("ForgeCaps").map { forgeCaps ->
                forgeCaps.remove("botarium:item")
            }.result().orElse(dynamic.emptyMap())

            updated.set("ForgeCaps", newForgeCaps)
        } else {
            dynamic
        }
    }
}