package generations.gg.generations.core.generationscore.common.datafixer.schema

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.ModAPI
import com.mojang.datafixers.DSL
import com.mojang.datafixers.schemas.Schema
import com.mojang.datafixers.types.templates.TypeTemplate
import net.minecraft.util.datafix.DataFixers.getDataFixer
import net.minecraft.util.datafix.fixes.References
import net.minecraft.util.datafix.schemas.NamespacedSchema
import java.util.function.Supplier

class GenerationsRootSchema(versionKey: Int, parent: Schema?) : Schema(versionKey, parent) {

    override fun registerTypes(
        schema: Schema,
        entityTypes: MutableMap<String, Supplier<TypeTemplate>>,
        blockEntityTypes: MutableMap<String, Supplier<TypeTemplate>>
    ) {
        schema.registerType(true, GenerationsReferences.TERRAIUM_FORGE_INVENTORY) {
            DSL.optionalFields("ForgeCaps", DSL.optionalFields("botarium:item", DSL.optionalFields("Items", DSL.list(References.ITEM_STACK.`in`(schema))) ))
        }

        schema.registerType(true, GenerationsReferences.TERRAIUM_FABRIC_INVENTORY) {
            DSL.optionalFields("Items", DSL.list(References.ITEM_STACK.`in`(schema)))
        }

        //I'm not 100% sure what a recursive type is, but at least one is required
        //Maybe a type that can contain itself?
        schema.registerType(true, References.ENTITY) {
            DSL.taggedChoiceLazy("id", NamespacedSchema.namespacedString(), entityTypes)
        }
        schema.registerType(true, References.BLOCK_ENTITY) {
            //Tagged choice makes it so that the type is determined by a key of some other type
            //So the "id" of a block entity determines what type the actual block entity is
            DSL.taggedChoiceLazy(
                "id",
                NamespacedSchema.namespacedString(),
                blockEntityTypes
            )
        }

        schema.registerType(false, References.CHUNK) {
            DSL.optionalFields(
                "block_entities",
                DSL.list(
                    //Remainder basically passes through anything not previously matched to a type
                    //I believe this is necessary because the "block_entities" type wont be parsed properly if there is leftover data.
                    //So if we only have a gilded chest and a display case defined as block entities (because those are the only ones we need to fix)
                    //Any other block entity data gets put in this remainder tag.
                    //So basically, this is either a "block_entity" or something else we dont care about
                    DSL.or(
                        References.BLOCK_ENTITY.`in`(schema),
                        DSL.remainder()
                    )
                )
            )
        }
        schema.registerType(false, References.ENTITY_CHUNK) {
            DSL.optionalFields(
                "Entities",
                DSL.or(
                    DSL.list(References.ENTITY.`in`(schema)),
                    DSL.remainder()
                )
            )
        }
        //We steal the definition of the item stack type from the vanilla schema
        //DO NOT CHANGE THE NUMBER IN HERE.
        //This is the version of the schema that was being used when THIS schema was made.
        //Specifically, this schema was pre item components
        //If you need a different number, make a new schema
        val vanillaSchema = getDataFixer().getSchema(38180)
        schema.registerType(true, References.ITEM_STACK) {
            vanillaSchema.getType(References.ITEM_STACK).buildTemplate()
        }
    }

    override fun registerBlockEntities(schema: Schema): MutableMap<String, Supplier<TypeTemplate>> = mutableMapOf<String, Supplier<TypeTemplate>>().apply {
        val botarium = when(Cobblemon.implementation.modAPI) {
            ModAPI.FABRIC -> GenerationsReferences.TERRAIUM_FABRIC_INVENTORY
            ModAPI.NEOFORGE -> GenerationsReferences.TERRAIUM_FORGE_INVENTORY
            else -> throw RuntimeException("Forge isn't supported by Generations Core")
        }

        simple("pokedoll")
        simple("generic_shrine")
        simple("abundant_shrne")
        simple("celestial_altar")
        simple("lunar_shrine")
        putGens("meloetta_music_box") {
            DSL.optionalFields("RecordItem", References.ITEM_STACK.`in`(schema))
        }

        putGens("regigigas_shrine") { botarium.`in`(schema) }
        simple("tao_trio_shrine")
        simple("tapu_shrine")
        simple("interact_shrine")
        putGens("cooking_pot") { botarium.`in`(schema) }
        putGens("generic_chest") { DSL.optionalFields("Items", DSL.list(References.ITEM_STACK.`in`(schema))) }
        simple("sign_block_entity")
        simple("hanging_sign_block_entity")
        simple("breeder")
        putGens("generic_furnace") { DSL.optionalFields("Items", DSL.list(References.ITEM_STACK.`in`(schema))) }
        putGens("generic_blast_furnace") { DSL.optionalFields("Items", DSL.list(References.ITEM_STACK.`in`(schema))) }
        putGens("generic_smoker") { DSL.optionalFields("Items", DSL.list(References.ITEM_STACK.`in`(schema))) }
        simple("generic_dyed_variant")
        simple("generic_model_providing")
        simple("vending_machine")
        simple("ball_display")
        simple("pc")
        simple("dyed_pc")
        simple("poke_loot")
        putGens("rks_machine") {
            DSL.optionalFields("Inventory", DSL.optionalFields(
                "Output", References.ITEM_STACK.`in`(schema),
                DSL.optionalFields("Items", DSL.list(References.ITEM_STACK.`in`(schema)))
            ))
        }

        simple("street_lamp")
        putGens("box") { DSL.optionalFields("Items", DSL.list(References.ITEM_STACK.`in`(schema))) }
    }

    override fun registerEntities(schema: Schema): MutableMap<String, Supplier<TypeTemplate>> = mutableMapOf<String, Supplier<TypeTemplate>>().apply {
        simple("boat")
        putGens("chest_boat") { DSL.optionalFields("Items", DSL.list(References.ITEM_STACK.`in`(schema))) }
        simple("magma_crystal")
        simple("seat")
        simple("statue")
        simple("tiered_fishing_bobber")
        simple("zygarde_cell")
    }

    private fun MutableMap<String, Supplier<TypeTemplate>>.simple(name: String) = registerSimple(this, "generations_core:$name")

    private fun MutableMap<String, Supplier<TypeTemplate>>.putGens(k: String, function: () -> TypeTemplate) = put("generations_core:$k", function)
}
