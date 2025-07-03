package generations.gg.generations.core.generationscore.common.world.level.block.entities

import generations.gg.generations.core.generationscore.common.client.model.InstanceProvider
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.AngleProvider
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.ModelProvider
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.VariantProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockObjectInstance
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.JukeBoxExtension
import gg.generations.rarecandy.renderer.rendering.ObjectInstance
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.Clearable
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.JukeboxBlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.ticks.ContainerSingleItem
import org.joml.Matrix4f

class MeloettaMusicBoxBlockEntity(pos: BlockPos, state: BlockState) : JukeboxBlockEntity(pos, state), Clearable, ContainerSingleItem, AngleProvider, VariantProvider, ModelProvider, InstanceProvider {

    init {
        this.songPlayer.instanceOrNull<JukeBoxExtension>()?.onFinished {
            if (theItem.`is`(GenerationsItems.RELIC_SONG)) {
                PokemonUtil.spawn(LegendKeys.MELOETTA.createPokemon(70), level, blockPos, angle)
                this.setSongItemWithoutPlaying(ItemStack(GenerationsItems.INERT_RELIC_SONG))
            }
        }
    }

    override fun getType(): BlockEntityType<*> {
        return GenerationsBlockEntities.MELOETTA_MUSIC_BOX.value()
    }

    override fun isValidBlockState(blockState: BlockState): Boolean = GenerationsBlockEntities.MELOETTA_MUSIC_BOX.value().isValid(blockState)


    override fun getAngle(): Float {
        if (blockState.hasProperty(HorizontalDirectionalBlock.FACING)) return blockState.getValue(
            HorizontalDirectionalBlock.FACING
        ).toYRot()
        return 0f
    }

    override fun getModel(): ResourceLocation = GenerationsBlockEntityModels.MELOETTA_MUSIC_BOX

    override fun getVariant(): String? = null

    override fun generateInstance(): ObjectInstance = BlockObjectInstance(Matrix4f(), null)

    var objectInstance: Array<ObjectInstance?>? = null

    override var instanceArray: Array<ObjectInstance?>?
        get() = objectInstance
        set(value) {
            objectInstance = value
        }
}
