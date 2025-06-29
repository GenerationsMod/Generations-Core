package generations.gg.generations.core.generationscore.common.datafixer.schema

import com.mojang.datafixers.DSL
import com.mojang.datafixers.schemas.Schema
import com.mojang.datafixers.types.templates.TypeTemplate
import net.minecraft.util.datafix.DataFixers
import net.minecraft.util.datafix.fixes.References
import net.minecraft.util.datafix.schemas.NamespacedSchema
import java.util.function.Supplier

class Generationsv1Schema(versionKey: Int, parent: Schema) : Schema(versionKey, parent) {

    override fun registerTypes(
        schema: Schema,
        entityTypes: MutableMap<String, Supplier<TypeTemplate>>,
        blockEntityTypes: MutableMap<String, Supplier<TypeTemplate>>,
    ) {
        super.registerTypes(schema, entityTypes, blockEntityTypes)

        val vanillaSchema = DataFixers.getDataFixer().getSchema(38185)
        schema.registerType(true, References.ITEM_STACK) {
            vanillaSchema.getType(References.ITEM_STACK).buildTemplate()
        }
    }

    override fun registerBlockEntities(schema: Schema): MutableMap<String, Supplier<TypeTemplate>> {
        return schema.registerBlockEntities(schema).apply {
            putGens("regigigas_shrine") { DSL.optionalFields("Items", DSL.list(References.ITEM_STACK.`in`(schema))) }
            putGens("cooking_pot") { DSL.optionalFields("Items", DSL.list(References.ITEM_STACK.`in`(schema))) }
        }
    }

    fun MutableMap<String, Supplier<TypeTemplate>>.putGens(k: String, function: () -> TypeTemplate) = put("generations_core:$k", function)
}