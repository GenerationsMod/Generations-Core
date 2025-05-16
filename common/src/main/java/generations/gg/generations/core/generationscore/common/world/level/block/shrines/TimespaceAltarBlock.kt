package generations.gg.generations.core.generationscore.common.world.level.block.shrines

import com.mojang.serialization.MapCodec
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.item.legends.CreationTrioItem
import generations.gg.generations.core.generationscore.common.world.item.legends.RedChainItem
import generations.gg.generations.core.generationscore.common.world.item.legends.RedChainItem.Companion.getUses
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.DirectionalShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.altar.TimeSpaceAltarBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.altar.TimeSpaceAltarBlockEntity.TimeSpaceAltarItemStackHandler
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class TimespaceAltarBlock(properties: Properties) :
    InteractShrineBlock<TimeSpaceAltarBlockEntity>(
        properties,
        GenerationsBlockEntities.TIMESPACE_ALTAR,
        GenerationsBlockEntityModels.TIME_SPACE_ALTAR
    ) {
    override fun codec(): MapCodec<TimespaceAltarBlock> = CODEC

    override fun isStackValid(stack: ItemStack): Boolean {
        return stack.item is RedChainItem || stack.item is CreationTrioItem
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

        val entity = getAssoicatedBlockEntity(level, pos).orElse(null) ?: return false

        val handler = entity.container

        if (stack.item is RedChainItem && !handler.hasRedChain()) {
            val chain = handler.insertItem(1, stack, false)

            //            if (ItemStack.isSameItem(stack, chain)) return false;
            player.setItemInHand(hand, chain)

            val succeeded = trySpawn(state, level, pos, handler, player)
            if (succeeded) entity.sync()
            return succeeded
        } else if (stack.item is CreationTrioItem && !handler.hasOrb(player)) {
            val chain = handler.insertItem(0, stack, false)

            //            if (ItemStack.isSameItem(stack, chain)) return false;
            player.setItemInHand(hand, chain)

            val succeeded = trySpawn(state, level, pos, handler, player)
            if (succeeded) entity.sync()
            return succeeded
        } else if (stack.isEmpty) {
            player.inventory.placeItemBackInInventory(handler.extractItem())
            return true
        } else return false
    }

    companion object {
        private val SHAPE: DirectionalShapes = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.or(
                Shapes.box(0.0, 0.0, 0.1875, 1.0, 0.125, 0.75),
                Shapes.box(0.0, 0.125, 0.3125, 1.0, 0.1875, 0.6875),
                Shapes.box(0.03749999999999998, 0.1875, 0.3125, 0.975, 0.89375, 0.625)
            )
        )

        val CODEC: MapCodec<TimespaceAltarBlock> = simpleCodec { properties: Properties ->
            TimespaceAltarBlock(
                properties
            )
        }

        fun trySpawn(
            state: BlockState,
            level: Level?,
            pos: BlockPos?,
            handler: TimeSpaceAltarItemStackHandler,
            player: ServerPlayer
        ): Boolean {
            if (handler.shouldSpawn(player)) {
                val id = (handler.getResource(0).item as CreationTrioItem).speciesId
                PokemonUtil.spawn(id.createPokemon(70), level, pos, state.getValue(FACING).toYRot())
                handler.updateStack(1, RedChainItem.Companion::incrementUsage)
                if (getUses(handler[1].toItemStack()) >= RedChainItem.MAX_USES) handler[1].resource =
                    ItemResource.BLANK
                handler[0].resource = ItemResource.BLANK
                handler.dumpAllIntoPlayerInventory(player)
                return true
            } else {
                return false
            }
        }
    }
}
