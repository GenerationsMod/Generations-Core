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

        schema.registerType(false, References.DATA_COMPONENTS) {
            vanillaSchema.getType(References.DATA_COMPONENTS).buildTemplate()
        }

        schema.registerType(false, References.ITEM_NAME) { DSL.constType(NamespacedSchema.namespacedString()) }

        schema.registerType(false, GenerationsReferences.TERRAIUM_FABRIC_INVENTORY, {
            DSL.optionalFields("fabric:attachments", DSL.optionalFields("generations_core:inventory", GenerationsReferences.ITEM_RESOURCE.`in`(schema)))
        })

        schema.registerType(false, GenerationsReferences.TERRAIUM_FORGE_INVENTORY) {
            DSL.optionalFields("fabric:attachments", DSL.optionalFields("generations_core:inventory", GenerationsReferences.ITEM_RESOURCE.`in`(schema))) //TODO: Get back this when I have how this will look in 1.21.1
        }

        schema.registerType(false, GenerationsReferences.ITEM_RESOURCE, {
            DSL.optionalFields("id", References.ITEM_NAME.`in`(schema), "components", References.DATA_COMPONENTS.`in`(schema), "amount", DSL.longType().template())
        })
    }
}