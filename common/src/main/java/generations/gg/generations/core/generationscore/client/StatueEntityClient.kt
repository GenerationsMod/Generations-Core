package generations.gg.generations.core.generationscore.client

import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import generations.gg.generations.core.generationscore.client.model.RareCandyBone
import generations.gg.generations.core.generationscore.client.render.PixelmonInstanceProvider
import generations.gg.generations.core.generationscore.client.render.rarecandy.CompiledModel
import generations.gg.generations.core.generationscore.client.render.rarecandy.GenerationsObjectInstance
import generations.gg.generations.core.generationscore.world.entity.StatueEntity
import net.minecraft.resources.ResourceLocation
import org.joml.Matrix4f

class StatueEntityClient(var statueEntity: StatueEntity) : PoseableEntityState<PokemonEntity>(), PixelmonInstanceProvider {
    private var trueAge: Int = 0
    private var instance: GenerationsObjectInstance? = null

    private var currentSpecies  = ResourceLocation("")
    private var currentAspects: Set<String> = HashSet()

    private val staticFrame get() = statueEntity.statueData.frame

    private val isStatic get() = statueEntity.statueData.isStatic

    override fun updatePartialTicks(v: Float) {
        currentPartialTicks = if(isStatic) {
            staticFrame
        } else {
            v
        }
    }

    fun setCurrentTicks(v: Float) {
        currentPartialTicks = v
    }

    fun tick() {
        updateAge()
    }

    private fun updateAge() {
        trueAge += 1

        age = if(isStatic) 0 else trueAge
    }

    override fun getInstance(): GenerationsObjectInstance? {
        if (currentSpecies != species() || currentAspects != aspects()) instance = null
        if (instance == null) {
            val model: CompiledModel? = PokemonModelRepository.getPoser(species()!!, aspects()!!).rootPart.takeIf { it is RareCandyBone }?.let { it as RareCandyBone }?.compiledModel()


            if (model != null && model.isReady) {
                instance = GenerationsObjectInstance(model.renderObject, Matrix4f(), null)
                currentSpecies = species()!!
                currentAspects = aspects()!!
            }
        }
        return instance
    }
    override fun setInstance(instance: GenerationsObjectInstance?) {
        this.instance = instance
    }

    override fun species(): ResourceLocation? {
        return statueEntity.species();
    }

    override fun aspects(): Set<String>? {
        return statueEntity.aspects()
    }

    override fun getEntity(): PokemonEntity? {
        return null
    }
}
