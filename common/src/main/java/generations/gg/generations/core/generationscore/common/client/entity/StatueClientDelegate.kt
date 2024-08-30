package generations.gg.generations.core.generationscore.common.client.entity

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import generations.gg.generations.core.generationscore.common.client.render.CobblemonInstanceProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CobblemonInstance
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.StatueInstance
import generations.gg.generations.core.generationscore.common.world.entity.StatueSideDelegate
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity
import net.minecraft.client.Minecraft
import net.minecraft.resources.ResourceLocation
import org.joml.Matrix4f

class StatueClientDelegate : StatueSideDelegate, PoseableEntityState<PokemonEntity>(), CobblemonInstanceProvider {
    private var modelEntity: PokemonEntity = PokemonEntity(Minecraft.getInstance().level!!)
    private var instance = StatueInstance(Matrix4f(), Matrix4f(), null)
    lateinit var currentEntity: StatueEntity

    var trueAge = 0;

    val activeAge: Int
        get() = if(currentEntity.staticToggle) currentEntity.staticAge else trueAge

    override val schedulingTracker
        get() = currentEntity.schedulingTracker

    override fun getEntity() = modelEntity
    override fun updatePoke(properties: PokemonProperties) {

        modelEntity.pokemon = properties.create()
    }

    override fun updateMaterial(value: String?) {
        instance.material = value
    }


    override fun initialize(entity: StatueEntity) {
        super.initialize(entity)
        this.currentEntity = entity
        this.age = entity.tickCount
        this.currentModel = PokemonModelRepository.getPoser(modelEntity.pokemon.species.resourceIdentifier, modelEntity.pokemon.aspects)

        val model = currentModel!!
        model.context.put(RenderContext.ENTITY, entity)
        currentModel!!.updateLocators(this)
        updateLocatorPosition(entity.position())

        val currentPoseType = entity.getCurrentPoseType()
        val pose = this.currentModel!!.poses.values.firstOrNull { currentPoseType in it.poseTypes && (it.condition == null || it.condition?.invoke(modelEntity) == true) }
        if (pose != null) {
            doLater { setPose(pose.poseName) }
        }
    }

    override fun tick(entity: StatueEntity) {
        super.tick(entity)
        updateLocatorPosition(entity.position())
        incrementAge(modelEntity)
    }

    override fun incrementAge(entity: PokemonEntity) {
        val previousAge = this.activeAge
        updateAge(trueAge + 1)
        runEffects(entity, previousAge, this.activeAge)

        age = this.activeAge

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

    override fun getInstance(): CobblemonInstance = instance

    override fun species(): ResourceLocation = modelEntity.exposedSpecies.resourceIdentifier

    override fun aspects(): Set<String> = modelEntity.aspects
    fun getAge(): Float = age + currentPartialTicks
}