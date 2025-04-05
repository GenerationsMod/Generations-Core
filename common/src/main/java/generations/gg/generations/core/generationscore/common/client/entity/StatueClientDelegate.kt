package generations.gg.generations.core.generationscore.common.client.entity

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.client.render.models.blockbench.PosableState
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import generations.gg.generations.core.generationscore.common.client.render.CobblemonInstanceProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CobblemonInstance
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.StatueInstance
import generations.gg.generations.core.generationscore.common.world.entity.StatueSideDelegate
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity
import net.minecraft.client.Minecraft
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import org.joml.Matrix4f

class StatueClientDelegate : StatueSideDelegate, PosableState(), CobblemonInstanceProvider {
    private var instance = StatueInstance(Matrix4f(), Matrix4f(), null)
    lateinit var currentEntity: StatueEntity

    var trueAge: Int = 0

    val activeAge: Int
        get() = if(currentEntity.staticToggle) currentEntity.staticAge else trueAge

    override val schedulingTracker
        get() = currentEntity.schedulingTracker

    override fun getEntity() = currentEntity
    override fun updatePoke(properties: PokemonProperties) {

        modelEntity.pokemon = properties.create()
    }

    override fun updateMaterial(value: String?) {
        instance.material = value
    }

    override fun onSyncedDataUpdated(data: EntityDataAccessor<*>) {
        super.onSyncedDataUpdated(data)

    }

    override fun initialize(entity: StatueEntity) {
        super.initialize(entity)
        this.currentEntity = entity
        this.age = entity.tickCount
        this.currentModel = PokemonModelRepository.getPoser(modelEntity.pokemon.species.resourceIdentifier, this)

        val model = currentModel!!
        model.context.put(RenderContext.ENTITY, entity)
        currentModel!!.updateLocators(entity, this)
        updateLocatorPosition(entity.position())

        val currentPoseType = entity.getCurrentPoseType()
        val pose = this.currentModel!!.poses.values.firstOrNull { currentPoseType in it.poseTypes && (it.condition == null || it.condition?.invoke(this) == true) }
        if (pose != null) {
            doLater { setPose(pose.poseName) }
        }
    }

    override fun tick(entity: StatueEntity) {
        super.tick(entity)
        updateLocatorPosition(entity.position())
        incrementAge(modelEntity)
    }

    override fun incrementAge(entity: Entity) {
        val previousAge = this.activeAge
        updateAge(trueAge + 1)
        runEffects(entity, previousAge.toFloat(), this.activeAge.toFloat()) //TODO: Check if this goes bad. I get a funny feeling

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