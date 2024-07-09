package generations.gg.generations.core.generationscore.world.entity.statue

import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import generations.gg.generations.core.generationscore.world.entity.StatueEntity

import com.cobblemon.mod.common.api.entity.EntitySideDelegate
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import generations.gg.generations.core.generationscore.client.render.CobblemonInstanceProvider

class StatueClientDelegate : EntitySideDelegate<StatueEntity>, PoseableEntityState<PokemonEntity>(), CobblemonInstanceProvider {
    lateinit var currentEntity: StatueEntity
    lateinit var proxyEntity: PokemonEntity
    private var trueAge = 0

    override val schedulingTracker
        get() = getEntity().schedulingTracker
    override fun getEntity() = proxyEntity

    override fun initialize(entity: StatueEntity) {
        super.initialize(entity)
        this.currentEntity = entity
        this.age = entity.tickCount

        this.currentModel = PokemonModelRepository.getPoser(entity.species, entity.aspects)
        this.proxyEntity = PokemonEntity(currentEntity.level(), currentEntity.pokemon)

        val model = currentModel!!
        model.context.put(RenderContext.ENTITY, entity)
        currentModel!!.updateLocators(this)
        updateLocatorPosition(entity.position())

        val currentPoseType = entity.getCurrentPoseType()
        val pose = this.currentModel!!.poses.values.firstOrNull { currentPoseType in it.poseTypes /*&& (it.condition == null || it.condition(proxyEntity))*/ }
        if (pose != null) {
            doLater { setPose(pose.poseName) }
        }
    }

    override fun tick(entity: StatueEntity) {
        updateLocatorPosition(entity.position())

        proxyEntity.tickCount = entity.tickCount

        incrementAge(proxyEntity)
    }

    override fun incrementAge(entity: PokemonEntity) {
        val previousAge = if(this.currentEntity.static) 0 else trueAge
        trueAge += 1
        updateAge(if(this.currentEntity.static) 0 else trueAge)
        runEffects(entity, previousAge, age)
        val primaryAnimation = primaryAnimation ?: return
        if (primaryAnimation.started + primaryAnimation.duration <= animationSeconds) {
            this.primaryAnimation = null
            primaryAnimation.afterAction.accept(Unit)
        }
    }

    override fun updatePartialTicks(partialTicks: Float) {
        this.currentPartialTicks = if(currentEntity.static) currentEntity.frame else partialTicks
    }
}