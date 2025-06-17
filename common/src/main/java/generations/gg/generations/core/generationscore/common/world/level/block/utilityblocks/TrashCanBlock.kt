package generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.container.TrashCanContainer
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.DirectionalShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.generateDirectionVoxelShape
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class TrashCanBlock(props: Properties) : GenericRotatableModelBlock(
        props,
        model = GenerationsBlockEntityModels.TRASH_CAN
    ) {
    override val blockEntityType
        get() = GenerationsBlockEntities.TRASH_CAN

    public override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE.getShape(state)
    }

    override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        worldIn: Level,
        pos: BlockPos,
        player: Player,
        handIn: InteractionHand,
        hit: BlockHitResult
    ): ItemInteractionResult {
        if (player.isShiftKeyDown) return ItemInteractionResult.FAIL

        if (worldIn.isClientSide) return ItemInteractionResult.FAIL

        val serverPlayer = player.instanceOrNull<ServerPlayer>() ?: return ItemInteractionResult.FAIL

        serverPlayer.openMenu(SimpleMenuProvider(
                { i: Int, arg: Inventory, arg2: Player ->
                    TrashCanContainer(
                        i,
                        arg
                    )
                }, Component.translatable("container.trashcan")
            )
        )

        return ItemInteractionResult.SUCCESS
    }

    override fun codec(): MapCodec<TrashCanBlock> = CODEC

    companion object {
        val SHAPE: DirectionalShapes = generateDirectionVoxelShape(
            Shapes.or(
                Shapes.box(0.14, 0.0, 0.14, 0.86, 0.725, 0.86),
                Shapes.box(0.09312500000000001, 0.625, 0.09312500000000001, 0.906875, 0.6625, 0.906875),
                Shapes.box(0.328125, 0.0, 0.328125, 0.671875, 0.75, 0.671875),
                Shapes.box(0.398125, 0.0, 0.475, 0.6012500000000001, 0.80625, 0.525)
            ),
            Direction.SOUTH
        )
        val CODEC = simpleCodec(::TrashCanBlock)
    }
}
