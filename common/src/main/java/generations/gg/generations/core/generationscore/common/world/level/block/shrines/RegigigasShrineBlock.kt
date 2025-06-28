package generations.gg.generations.core.generationscore.common.world.level.block.shrines

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.item.legends.RegiOrbItem
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.DirectionalShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.RegigigasShrineBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.*

class RegigigasShrineBlock(materialIn: Properties) : InteractShrineBlock(
        materialIn,
        GenerationsBlockEntityModels.REGIGIGAS_SHRINE
    ) {
    override val blockEntityType
        get() = GenerationsBlockEntities.REGIGIGAS_SHRINE

    override fun codec(): MapCodec<RegigigasShrineBlock> {
        return CODEC
    }

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE.getShape(state)
    }

    override fun interact(
        level: Level,
        pos: BlockPos,
        state: BlockState,
        player: ServerPlayer,
        hand: InteractionHand,
        activationState: Boolean
    ): Boolean {
        val stack = player.getItemInHand(hand)

        val entity = getAssoicatedBlockEntity<RegigigasShrineBlockEntity>(level, pos) ?: return false

        val handler = entity.container

        var succeeded = false

        val item = stack.item

        if(!handler.hasAnyMatching { it.`is`(item) } && handler.addItem(ItemStack(item, 1)).isEmpty) {

            stack.shrink(1)

            if (handler.isFull()) {
                PokemonUtil.spawn(LegendKeys.REGIGIGAS.createProperties(70), level, pos.above())
                handler.clearContent()
            }

            entity.sync()

            succeeded = true
        } else {
            for (i in 0..4) {
                val extracted = handler.removeItem(i, 1).takeUnless { it.isEmpty } ?: continue
                player.inventory.placeItemBackInInventory(extracted)
                entity.sync()
                succeeded = true
                break
            }
        }

        if (succeeded) entity.sync()

        return succeeded
    }

    override fun isStackValid(stack: ItemStack): Boolean {
        return stack.item is RegiOrbItem || stack.isEmpty
    }

    override fun getVariant(blockState: BlockState): String? {
        return null
    }

    companion object {
        private val SHAPE: DirectionalShapes = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(
                Shapes.box(0.0, 0.0, 0.0, 1.0, 0.3125, 1.0),
                Shapes.box(0.125, 0.0, 0.25, 0.875, 0.875, 0.75), BooleanOp.OR
            ), Direction.SOUTH
        )

        private val CODEC: MapCodec<RegigigasShrineBlock> = simpleCodec { materialIn: Properties ->
            RegigigasShrineBlock(
                materialIn
            )
        }
    }
}