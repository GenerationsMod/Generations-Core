package generations.gg.generations.core.generationscore.common.world.level.block.decorations

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.api.data.Codecs
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.DirectionalShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.CouchBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.entities.DyedVariantBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.DyeableBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.StringRepresentable
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.function.Function

class VariantCouchBlock(
    properties: Properties,
    color: DyeColor,
    function: Map<DyeColor, Holder<Block>>,
    private val variant: Variant
) :
    DyeableBlock<CouchBlockEntity, VariantCouchBlock>(
        properties, color, function, GenerationsBlockEntities.COUCH,
        variant.model, 0, 0, 0
    ),
    SittableBlock {

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return variant.shape.getShape(state)
    }

    override fun serverUse(
        stack: ItemStack,
        state: BlockState,
        world: ServerLevel,
        pos: BlockPos,
        player: ServerPlayer,
        handIn: InteractionHand,
        hit: BlockHitResult
    ): ItemInteractionResult {
        return super<SittableBlock>.use(state, world, pos, player, handIn, hit)
    }

    override fun getOffset(): Double {
        return 0.4375
    }


    override fun getYaw(state: BlockState): Float {
        return state.getValue(FACING).toYRot()
    }

    override fun codec(): MapCodec<VariantCouchBlock> = CODEC

    enum class Variant(val type: String, shape: VoxelShape): StringRepresentable {
        OTTOMAN("ottoman", Shapes.box(0.0, 0.0, 0.0, 1.0, 0.4375, 1.0)),
        ARM_LEFT(
            "arm_left",
            Shapes.join(
                Shapes.box(0.0, 0.0, 0.0, 1.0, 0.4375, 1.0),
                Shapes.join(
                    Shapes.box(0.0, 0.0, 0.75, 1.0, 1.0, 1.0),
                    Shapes.box(0.75, 0.0, 0.0, 1.0, 0.71875, 1.0),
                    BooleanOp.OR
                ),
                BooleanOp.OR
            )
        ),
        ARM_RIGHT(
            "arm_right",
            Shapes.join(
                Shapes.box(0.0, 0.0, 0.0, 1.0, 0.4375, 1.0),
                Shapes.join(
                    Shapes.box(0.0, 0.0, 0.75, 1.0, 1.0, 1.0),
                    Shapes.box(0.0, 0.0, 0.0, 0.25, 0.71875, 1.0),
                    BooleanOp.OR
                ),
                BooleanOp.OR
            )
        ),
        CORNER_LEFT(
            "corner_left",
            Shapes.join(
                Shapes.box(0.0, 0.0, 0.0, 1.0, 0.4375, 1.0),
                Shapes.join(
                    Shapes.box(0.0, 0.0, 0.75, 1.0, 1.0, 1.0),
                    Shapes.box(0.75, 0.0, 0.0, 1.0, 1.0, 1.0),
                    BooleanOp.OR
                ),
                BooleanOp.OR
            )
        ),
        CORNER_RIGHT(
            "corner_right",
            Shapes.join(
                Shapes.box(0.0, 0.0, 0.0, 1.0, 0.4375, 1.0),
                Shapes.join(
                    Shapes.box(0.0, 0.0, 0.75, 1.0, 1.0, 1.0),
                    Shapes.box(0.0, 0.0, 0.0, 0.25, 1.0, 1.0),
                    BooleanOp.OR
                ),
                BooleanOp.OR
            )
        ),
        MIDDLE(
            "middle",
            Shapes.join(
                Shapes.box(0.0, 0.0, 0.0, 1.0, 0.4375, 1.0),
                Shapes.box(0.0, 0.0, 0.75, 1.0, 1.0, 1.0),
                BooleanOp.OR
            )
        );

        override fun getSerializedName(): String = type

        val shape: DirectionalShapes = GenerationsVoxelShapes.generateDirectionVoxelShape(shape)
        val model: ResourceLocation = GenerationsBlockEntityModels.block("decorations/couch/couch_$type.pk")

        companion object {
            val CODEC = StringRepresentable.fromEnum { Variant.entries.toTypedArray() }
        }
    }

    companion object {
        val CODEC = RecordCodecBuilder.mapCodec {
            return@mapCodec dyedCodecBuilder<VariantCouchBlock>(it).and(
                Variant.CODEC.fieldOf("variant").forGetter(VariantCouchBlock::variant)
            ).apply(it, ::VariantCouchBlock)
        }
    }
}
