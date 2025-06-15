package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.util.extensions.toInteractionResult
import generations.gg.generations.core.generationscore.common.world.level.block.decorations.SittableBlock
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class CushionBlock(properties: Properties, private val variant: String) :
    GenericRotatableModelBlock<GenericModelProvidingBlockEntity>(
        properties = properties,
        model = GenerationsBlockEntityModels.FLOOR_CUSHION
    ), SittableBlock {
    override val blockEntityType: BlockEntityType<GenericModelProvidingBlockEntity>
        get() = GenerationsBlockEntities.GENERIC_MODEL_PROVIDING

    override fun codec(): MapCodec<CushionBlock> = CODEC

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape = SHAPE

    override fun getVariant(): String {
        return variant
    }

    override val offset: Double = 0.4

    override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hit: BlockHitResult
    ): InteractionResult {
        return super.sit(state, level, pos, player, hit).toInteractionResult()
    }

    override fun getYaw(state: BlockState): Float {
        return state.getValue(FACING).toYRot()
    }

    companion object {
        private val SHAPE: VoxelShape = Shapes.box(0.0, 0.0, 0.0, 1.0, 0.4375, 1.0)
        val CODEC = RecordCodecBuilder.mapCodec { instance: RecordCodecBuilder.Instance<CushionBlock> ->
                instance.group(
                    propertiesCodec(),
                    Codec.STRING.fieldOf("variant").forGetter(CushionBlock::variant)
                ).apply(instance, ::CushionBlock)
            }
    }
}
