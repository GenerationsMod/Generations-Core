package generations.gg.generations.core.generationscore.common.world.level.block.shrines

import com.mojang.serialization.MapCodec
import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import earth.terrarium.common_storage_lib.resources.item.ItemResource
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

class RegigigasShrineBlock(materialIn: Properties) :
    InteractShrineBlock<RegigigasShrineBlockEntity>(
        materialIn,
        GenerationsBlockEntities.REGIGIGAS_SHRINE,
        GenerationsBlockEntityModels.REGIGIGAS_SHRINE
    ) {
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

        val entity = getAssoicatedBlockEntity(level, pos) ?: return false

        val handler = entity.container

        var succeeded = false

        val item = stack.item

        if(!handler.has(item) && handler.insert(stack.asResouce(), 1, false) > 0) {

            stack.shrink(1)

            if (handler.isFull()) {
                PokemonUtil.spawn(LegendKeys.REGIGIGAS.createProperties(70), level, pos.above())
                handler.clear()
            }

            handler.update()

            succeeded = true
        } else {
            for (i in 0..4) {
                val slot = handler.get(i).takeUnless { it.isEmpty } ?: continue

                var resource = slot.resource

                val extracted = handler.extract(resource, 1, false)

                if(extracted > 0) {
                    player.inventory.placeItemBackInInventory(resource.toStack(extracted.toInt()))

                    handler.update()
                    succeeded = true
                    break
                }
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

private fun SimpleItemStorage.clear() {
    (0..< size()).map(this::get).forEach { it.resource =  ItemResource.BLANK }
}

private fun SimpleItemStorage.isFull(): Boolean = (0..< size()).map(this::getResource).none { it.isBlank }

private fun ItemStack.asResouce(): ItemResource = ItemResource.of(this)

private fun SimpleItemStorage.has(item: Item): Boolean = (0..< size()).map(this::getResource).any { it.isOf(item) }
