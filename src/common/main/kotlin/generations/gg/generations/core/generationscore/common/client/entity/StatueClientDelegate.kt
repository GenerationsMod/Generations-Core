package generations.gg.generations.core.generationscore.common.client.entity

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.client.render.models.blockbench.PosableState
import com.cobblemon.mod.common.client.render.models.blockbench.pokemon.PokemonPosableModel
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.entity.PosableEntity
import com.cobblemon.mod.common.util.asIdentifierDefaultingNamespace
import generations.gg.generations.core.generationscore.common.client.render.CobblemonInstanceProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CobblemonInstance
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.StatueInstance
import generations.gg.generations.core.generationscore.common.world.entity.StatueSideDelegate
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity
import net.minecraft.world.entity.Entity
import org.joml.Matrix4f

class StatueClientDelegate(entity: StatueEntity) : StatueSideDelegate, PosableState(), CobblemonInstanceProvider {
    override var instance = StatueInstance(Matrix4f(), Matrix4f(), null)
    var currentEntity: StatueEntity = entity


    var trueAge: Int = 0

    val activeAge: Int
        get() = if(currentEntity.staticToggle) currentEntity.staticAge else trueAge

    override val schedulingTracker
        get() = getEntity().schedulingTracker



    override fun getEntity() = currentEntity

    override fun initialize(entity: StatueEntity) {
        super.initialize(entity)
        this.currentEntity = entity
        this.age = entity.tickCount

        var properties = entity.properties

        var species = properties.species?.asIdentifierDefaultingNamespace() ?: return

        this.currentModel = PokemonModelRepository.getPoser(species, this)
        currentModel!!.updateLocators(entity, this)

        val currentPoseType = entity.getCurrentPoseType()
        val pose = this.currentModel!!.getFirstSuitablePose(this, currentPoseType)
        doLater { setPose(pose.poseName) }
    }

    override fun updatePoke(properties: PokemonProperties) {
        currentEntity.properties = properties

        var species = properties.species?.asIdentifierDefaultingNamespace()

        currentModel = species?.let { PokemonModelRepository.getPoser(it, this) }

        if(currentModel != null) {
            this.currentAspects = properties.aspects
        } else {
            this.currentAspects = emptySet()
        }

        updateLocatorPosition(currentEntity.position())

        val currentPoseType = currentEntity.getCurrentPoseType()
        // Doing this awful thing because otherwise evolution particle won't start until the client looks at it. Which sucks slightly more than this.
        val pose = this.currentModel?.getFirstSuitablePose(this, currentPoseType) ?: return
        doLater { setPose(pose.poseName) }
    }

    override fun updateMaterial(value: String?) {
        instance.material = value
    }

    override fun tick(entity: StatueEntity) {
        incrementAge(entity)
    }

    override fun incrementAge(entity: Entity) {
        val previousAge = this.trueAge
        updateAge(trueAge + 1)

        age = this.activeAge

        currentModel?.let {
            updateLocatorPosition(entity.position())
            it.validatePose(entity as? PosableEntity, this)
        }

        tickEffects(entity, previousAge, age)

        val primaryAnimation = primaryAnimation ?: return
        if (primaryAnimation.started + primaryAnimation.duration <= animationSeconds) {
            this.primaryAnimation = null
            primaryAnimation.afterAction.accept(Unit)
        }
    }

    override fun updateAge(age: Int) {
        trueAge = age
    }

    override fun updatePartialTicks(partialTicks: Float) {
        this.currentPartialTicks = if(currentEntity.staticToggle) currentEntity.staticPartial else partialTicks
    }

    fun getAge(): Float = age + currentPartialTicks
}