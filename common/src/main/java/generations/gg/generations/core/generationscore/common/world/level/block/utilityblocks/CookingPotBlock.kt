package generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.generateDirectionVoxelShape
import generations.gg.generations.core.generationscore.common.world.level.block.asValue
import generations.gg.generations.core.generationscore.common.world.level.block.entities.CookingPotBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.Containers
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.stream.Stream

class CookingPotBlock(materialIn: Properties) : GenericRotatableModelBlock(
        materialIn,
        model = GenerationsBlockEntityModels.COOKING_POT
    ) {
    override val blockEntityType
        get() = GenerationsBlockEntities.COOKING_POT

    public override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        worldIn: Level,
        pos: BlockPos,
        player: Player,
        handIn: InteractionHand,
        hit: BlockHitResult
    ): ItemInteractionResult {
        val tileEntity = worldIn.getBlockEntity(pos).instanceOrNull<CookingPotBlockEntity>()

        if (tileEntity !is CookingPotBlockEntity || player.isShiftKeyDown) return ItemInteractionResult.FAIL

        if (worldIn.isClientSide) return ItemInteractionResult.SUCCESS

        player.openMenu(tileEntity)

        return ItemInteractionResult.SUCCESS
    }

    override fun codec(): MapCodec<CookingPotBlock> = CODEC

    public override fun onRemove(
        oldState: BlockState,
        worldIn: Level,
        pos: BlockPos,
        newState: BlockState,
        isMoving: Boolean
    ) {
        if (!oldState.`is`(newState.block)) {
            val tileEntity = worldIn.getBlockEntity(pos)
            if (tileEntity is CookingPotBlockEntity) {
                val inventory = tileEntity.getItems()

                val x = pos.x + 0.5
                val y = pos.y + 0.5
                val z = pos.z + 0.5

                for (i in 0 until inventory.containerSize) {
                    val stack = inventory.getItem(i)
                    Containers.dropItemStack(worldIn, x, y, z, stack)
                }

                worldIn.updateNeighbourForOutputSignal(pos, this)
            }
        }
        super.onRemove(oldState, worldIn, pos, newState, isMoving)
    }


    override fun <T : BlockEntity?> getTicker(
        level: Level,
        state: BlockState,
        blockEntityType: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return if (level.isClientSide) null else createTickerHelper(
            blockEntityType, GenerationsBlockEntities.COOKING_POT.asValue<CookingPotBlockEntity>()
        ) { _, _, _, pot -> pot.serverTick() }
    }

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return (if (level.getBlockEntity(pos, GenerationsBlockEntities.COOKING_POT.asValue<CookingPotBlockEntity>())
                .filter { obj: CookingPotBlockEntity -> obj.hasLogs() }.isPresent
        ) WITH_CAMPFIRE else WITHOUT_CAMPFIRE).getShape(state)
    }

    companion object {
        private val WITH_CAMPFIRE = generateDirectionVoxelShape(
            Stream.of(
                Shapes.box(0.125, 0.0, 0.25, 0.875, 0.3125, 0.75),
                Shapes.box(0.0, 0.5375, 0.375, 1.0, 0.56875, 0.625),
                Shapes.box(0.125, 0.3125, 0.125, 0.875, 0.625, 0.875),
                Shapes.box(0.15625, 0.0, 0.15625, 0.84375, 0.125, 0.84375)
            ).reduce(Shapes.empty()) { a: VoxelShape?, b: VoxelShape? -> Shapes.join(a, b, BooleanOp.OR) },
            Direction.SOUTH
        )

        private val WITHOUT_CAMPFIRE = generateDirectionVoxelShape(
            Stream.of(
                Shapes.box(0.125, 0.0, 0.25, 0.875, 0.3125, 0.75),
                Shapes.box(0.0, 0.5375, 0.375, 1.0, 0.56875, 0.625),
                Shapes.box(0.125, 0.3125, 0.125, 0.875, 0.625, 0.875)
            ).reduce(Shapes.empty()) { a: VoxelShape?, b: VoxelShape? -> Shapes.join(a, b, BooleanOp.OR) },
            Direction.SOUTH
        )

        val CODEC = simpleCodec(::CookingPotBlock)
    }
}
