package generations.gg.generations.core.generationscore.common.world.level.block.decorations

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.util.extensions.toInteractionResult
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult

class BeanBagBlock(properties: Properties) : GenericRotatableModelBlock(
        properties = properties,
        model = GenerationsBlockEntityModels.SNORLAX_BEAN_BAG
    ), SittableBlock {
    override val blockEntityType
        get() = GenerationsBlockEntities.GENERIC_MODEL_PROVIDING

    override fun codec(): MapCodec<BeanBagBlock> {
        return CODEC
    }

    override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hitResult: BlockHitResult
    ): InteractionResult {
        return super<SittableBlock>.sit(state, level, pos, player, hitResult).toInteractionResult()
    }

    override val offset: Double = 0.9

    override fun getYaw(state: BlockState): Float = state.getValue(FACING).toYRot()

    companion object {
        val CODEC: MapCodec<BeanBagBlock> = simpleCodec(::BeanBagBlock)
    }
}