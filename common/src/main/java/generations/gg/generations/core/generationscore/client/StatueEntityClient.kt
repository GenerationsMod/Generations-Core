package generations.gg.generations.core.generationscore.client

import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import generations.gg.generations.core.generationscore.client.render.PixelmonInstanceProvider
import generations.gg.generations.core.generationscore.client.render.rarecandy.PixelmonInstance
import generations.gg.generations.core.generationscore.world.entity.StatueEntity
import net.minecraft.resources.ResourceLocation
import org.joml.Matrix4f

class StatueEntityClient(var statueEntity: StatueEntity) : PoseableEntityState<PokemonEntity>(), PixelmonInstanceProvider {
    private var trueAge: Int = 0;
    private var pixelmonInstance: PixelmonInstance? = null
    private val staticFrame get() = statueEntity.statueData.frame

    private val isStatic get() = statueEntity.statueData.isStatic

    override fun updatePartialTicks(v: Float) {
        currentPartialTicks = if(isStatic) {
            staticFrame;
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

        age = if(isStatic) 0 else trueAge;
    }

    override fun getInstance(): PixelmonInstance {
        if (pixelmonInstance == null) pixelmonInstance = PixelmonInstance(Matrix4f(), Matrix4f(), null)
        return pixelmonInstance as PixelmonInstance
    }

    override fun setInstance(instance: PixelmonInstance?) {
        pixelmonInstance = instance
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
