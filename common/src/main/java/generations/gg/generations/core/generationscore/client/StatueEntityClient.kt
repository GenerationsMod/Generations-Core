package generations.gg.generations.core.generationscore.client

import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import generations.gg.generations.core.generationscore.client.render.CobblemonInstanceProvider
import generations.gg.generations.core.generationscore.client.render.rarecandy.CobblemonInstance
import generations.gg.generations.core.generationscore.client.render.rarecandy.StatueInstance
import generations.gg.generations.core.generationscore.world.entity.StatueEntity
import net.minecraft.resources.ResourceLocation
import org.joml.Matrix4f

class StatueEntityClient(var statueEntity: StatueEntity) : PoseableEntityState<PokemonEntity>(),
    CobblemonInstanceProvider {
    private var trueAge: Int = 0
    private var cobblemonInstance: StatueInstance? = null
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

        age = if( isStatic) 0 else trueAge
    }

    override fun getInstance(): CobblemonInstance {
        if (cobblemonInstance == null) cobblemonInstance =
            StatueInstance(
                Matrix4f(),
                Matrix4f(),
                null
            )
        return cobblemonInstance as CobblemonInstance
    }

    override fun setInstance(instance: CobblemonInstance?) {
        instance?.takeIf { it is StatueInstance }?.let { it as StatueInstance }.let { cobblemonInstance = it }
    }

    override fun species(): ResourceLocation? {
        return null
    }

    override fun aspects(): Set<String>? {
        return null
    }

    override fun getEntity(): PokemonEntity? {
        return null
    }
}
