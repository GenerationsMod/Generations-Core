package generations.gg.generations.core.generationscore.common.datafixer

import com.google.common.util.concurrent.ThreadFactoryBuilder
import com.mojang.datafixers.DSL
import com.mojang.datafixers.DataFixer
import com.mojang.datafixers.DataFixerBuilder
import com.mojang.datafixers.schemas.Schema
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.datafixer.fixers.BotariumFix
import generations.gg.generations.core.generationscore.common.datafixer.schema.GenerationsReferences
import generations.gg.generations.core.generationscore.common.datafixer.schema.GenerationsRootSchema
import generations.gg.generations.core.generationscore.common.datafixer.schema.Generationsv1Schema
import net.minecraft.util.datafix.fixes.ItemStackComponentizationFix
import net.minecraft.util.datafix.fixes.References
import java.util.concurrent.Executors

object GenerationsSchemas {
    const val VERSION_KEY = "${GenerationsCore.MOD_ID}:data_version"

    @JvmStatic val DATA_FIXER: DataFixer get() = RESULT.fixer()

    @JvmStatic public val ADDED_TO_1_6_COBBLEMON = buildMap<String, String> {
        put("generations_core:electric_seed", "cobblemon:electric_seed")
        put("generations_core:misty_seed", "cobblemon:misty_seed")
        put("generations_core:grassy_seed", "cobblemon:grassy_seed")
        put("generations_core:psychic_seed", "cobblemon:psychic_seed")
        put("generations_core:metronome", "cobblemon:metronome")
        put("generations_core:protective_pads", "cobblemon:protective_pads")
        put("generations_core:room_service", "cobblemon:room_service")
        put("generations_core:scope_lens", "cobblemon:scope_lens")
        put("generations_core:shed_shell", "cobblemon:shed_shell")
        put("generations_core:terrain_extender", "cobblemon:terrain_extender")
        put("generations_core:throat_spray", "cobblemon:throat_spray")
        put("generations_core:utility_umbrella", "cobblemon:utility_umbrella")
        put("generations_core:wide_lens", "cobblemon:wide_lens")
        put("generations_core:zoom_lens", "cobblemon:zoom_lens")
    }

    private val SAME: (Int, Schema) -> Schema = ::Schema
    private val RESULT: DataFixerBuilder.Result = this.create()

    const val DATA_VERSION = 1

    private fun create(): DataFixerBuilder.Result {
        val builder = DataFixerBuilder(DATA_VERSION)
        this.appendSchemas(builder)
        val types = hashSetOf<DSL.TypeReference>(
            References.ENTITY ,
            GenerationsReferences.TERRAIUM_FORGE_INVENTORY,
            GenerationsReferences.TERRAIUM_FABRIC_INVENTORY)

        val result = builder.build()
        if (types.isEmpty()) {
            return result
        }
        val executor = Executors.newSingleThreadExecutor(
            ThreadFactoryBuilder()
                .setNameFormat("${GenerationsCore.MOD_ID} Datafixer Bootstrap")
                .setDaemon(true)
                .setPriority(1)
                .build()
        )
        result.optimize(types, executor).join()
        return result
    }

    private fun appendSchemas(builder: DataFixerBuilder) {
        builder.addSchema(0, ::GenerationsRootSchema)
        val v1 = builder.addSchema(1, ::Generationsv1Schema)
        builder.addFixer(BotariumFix(v1))
//        builder.addFixer(ItemRenameFix.create(v1, "Remove items from generations added in 1.6 cobblemon") { name ->
//            print("Converted $name to ")
//
//            val newName = ADDED_TO_1_6_COBBLE.getOrDefault(name, name)
//
//            println(newName)
//            return@create newName
//        })
        builder.addFixer(ItemStackComponentizationFix(v1))
    }
}