package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.mojang.serialization.JsonOps
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe
import generations.gg.generations.core.generationscore.common.world.recipe.RksResult
import net.minecraft.advancements.*
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger
import net.minecraft.data.recipes.RecipeBuilder
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.RecipeSerializer
import java.util.*

abstract class RksRecipeJsonBuilder<V : RksRecipe, T>(@JvmField protected val result: RksResult<*>) : RecipeBuilder {

    @JvmField
	protected var consumesTimeCapsules: Boolean = true
    @JvmField
	protected val advancementBuilder: Advancement.Builder = Advancement.Builder.advancement()

    @JvmField
	protected var group: String? = null

    @JvmField
	protected var experience: Float = 1.0f
    @JvmField
	protected var processingTime: Int = 240

    @JvmField
	protected var speciesKey: SpeciesKey? = null

    @JvmField
	val criteria: MutableMap<String, Criterion<*>> = LinkedHashMap()


    fun key(key: SpeciesKey): RksRecipeJsonBuilder<V, T> {
        this.speciesKey = key
        return this
    }

    fun criterion(string: String, criterionConditions: Criterion<*>): RksRecipeJsonBuilder<V, T> {
        advancementBuilder.addCriterion(string, criterionConditions)
        return this
    }

    override fun group(string: String?): RksRecipeJsonBuilder<V, T> {
        this.group = string
        return this
    }

    fun experience(experience: Float): RksRecipeJsonBuilder<V, T> {
        this.experience = experience
        return this
    }

    fun processingTime(weavingTime: Int): RksRecipeJsonBuilder<V, T> {
        this.processingTime = weavingTime
        return this
    }

    override fun unlockedBy(name: String, criterion: Criterion<*>): RksRecipeJsonBuilder<V, T> {
        criteria[name] = criterion
        return this
    }

    val outputResult: RksResult<*>
        get() = this.result


    override fun getResult(): Item {
        return Items.AIR
    }

    override fun save(recipeOutput: RecipeOutput, id: ResourceLocation) {
        val t = this.validate(id)
        val advancementBuilder =
            recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(
                AdvancementRewards.Builder.recipe(id)
            ).requirements(AdvancementRequirements.Strategy.OR)
        Objects.requireNonNull(advancementBuilder)
        criteria.forEach { (key: String?, criterion: Criterion<*>?) ->
            advancementBuilder.addCriterion(
                key,
                criterion
            )
        }
        val shapedrecipe = create(id, t)
        recipeOutput.accept(id, shapedrecipe, advancementBuilder.build(id.withPrefix("recipes/rks/")))
    }

    protected abstract fun create(id: ResourceLocation, addition: T): V

    protected abstract fun validate(recipeId: ResourceLocation): T

    fun doesntConsumeTimeCapsules(): RksRecipeJsonBuilder<V, T> {
        this.consumesTimeCapsules = false
        return this
    }

    internal abstract class RksRecipeJsonProvider(
        val id: ResourceLocation, output: RksResult<*>,
        private val consumesTimeCapsules: Boolean,
        private val group: String,
        private val advancementBuilder: Advancement.Builder,
        private val advancementId: ResourceLocation,
        private val experience: Float,
        private val processingTime: Int, speciesKey: SpeciesKey?
    ) :
        RecipeOutput {
        private val output: RksResult<*> = output
        private val speciesKey: SpeciesKey? = speciesKey

        init {
            this.accept()
        }

        override fun serializeRecipeData(json: JsonObject) {
            if (!group.isEmpty()) {
                json.addProperty("group", this.group)
            }

            if (speciesKey != null) json.addProperty("speciesKey", speciesKey.toString())

            json.add("result", RksResult.CODEC.encodeStart<JsonElement>(JsonOps.INSTANCE, output).result().get())

            json.addProperty("experience", experience)
            json.addProperty("processingTime", processingTime)
            if (speciesKey != null) json.addProperty("speciesKey", speciesKey.toString())
            json.addProperty("consumesTimeCapsules", consumesTimeCapsules)
        }

        abstract val type: RecipeSerializer<*>?

        override fun serializeAdvancement(): JsonObject? {
            return advancementBuilder.serializeToJson()
        }

        fun getAdvancementId(): ResourceLocation? {
            return this.advancementId
        }
    }
}
