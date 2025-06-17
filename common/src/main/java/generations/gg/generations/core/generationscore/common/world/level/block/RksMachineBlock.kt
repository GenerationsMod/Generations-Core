package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.GenericRotatableShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.RksMachineBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.stats.Stats
import net.minecraft.world.Containers
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class RksMachineBlock(copy: Properties) : GenericRotatableModelBlock(
        copy,
        model = GenerationsBlockEntityModels.RKS_MACHINE,
        width = 1,
        height = 1,
        length = 1
    ) {
    override val blockEntityType
        get() = GenerationsBlockEntities.RKS_MACHINE

    override fun codec(): MapCodec<out BaseEntityBlock> = CODEC

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape {
        return SHAPE.getShape(state)
    }

    public override fun onRemove(
        oldState: BlockState,
        worldIn: Level,
        pos: BlockPos,
        newState: BlockState,
        isMoving: Boolean,
    ) {
        if (!oldState.`is`(newState.block)) {
            val tileEntity = worldIn.getBlockEntity(pos)
            if (tileEntity is RksMachineBlockEntity) {
                val inventory = tileEntity.inventory
                Containers.dropContents(worldIn, pos, inventory)

                worldIn.updateNeighbourForOutputSignal(pos, this)
            }
        }
        super.onRemove(oldState, worldIn, pos, newState, isMoving)
    }

    override fun <T : BlockEntity> getTicker(
        level: Level,
        state: BlockState,
        blockEntityType: BlockEntityType<T>,
    ): BlockEntityTicker<T>? = createTickerHelper(blockEntityType, GenerationsBlockEntities.RKS_MACHINE.value() as BlockEntityType<RksMachineBlockEntity>, RksMachineBlockEntity::serverTick)

    protected fun openContainer(level: Level, bpos: BlockPos, player: Player) {
        val rksMachine = getAssoicatedBlockEntity<RksMachineBlockEntity>(level, bpos) ?: run { throw IllegalStateException("Our named container provider is missing!") }
        player.openMenu(rksMachine)
        player.awardStat(Stats.INTERACT_WITH_FURNACE)
    }

    override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hitResult: BlockHitResult,
    ): InteractionResult {
        if (!level.isClientSide()) {
            this.openContainer(level, pos, player)
        }

        return InteractionResult.SUCCESS
    }

    public override fun hasAnalogOutputSignal(state: BlockState): Boolean {
        return true
    }

    public override fun getAnalogOutputSignal(blockState: BlockState, level: Level, blockPos: BlockPos): Int {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(blockPos));
    }

    public override fun getRenderShape(blockState: BlockState): RenderShape {
        return if (getWidthValue(blockState) == 0 && getHeightValue(blockState) == 0 && getLengthValue(blockState) == 0) RenderShape.ENTITYBLOCK_ANIMATED
        else RenderShape.INVISIBLE
    }

    companion object {
        private val SHAPE: GenericRotatableShapes = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.or(
                Shapes.box(0.5, 0.0, -0.25625, 1.371875, 0.04375, 1.25625),
                Shapes.box(-0.37187499999999996, 0.0, -0.25625, 0.5, 0.04375, 1.25625),
                Shapes.box(-0.26875000000000004, 0.0, -0.16874999999999996, 0.5812499999999999, 0.65625, 1.16875),
                Shapes.box(0.41875000000000007, 0.0, -0.16874999999999996, 1.26875, 0.65625, 1.16875),
                Shapes.box(-0.49375, 0.0, 0.8125, -0.24375000000000002, 0.4125, 1.125),
                Shapes.box(1.24375, 0.0, -0.125, 1.49375, 0.4125, 0.1875),
                Shapes.box(-0.49375, 0.0, -0.125, -0.24375000000000002, 0.4125, 0.1875),
                Shapes.box(1.24375, 0.0, 0.8125, 1.49375, 0.4125, 1.125),
                Shapes.box(-0.17500000000000004, 0.625, -0.07499999999999996, 0.5125, 0.771875, 1.075),
                Shapes.box(0.48750000000000004, 0.625, -0.07499999999999996, 1.175, 0.771875, 1.075),
                Shapes.box(0.4375, 0.6875, 0.025000000000000022, 1.0375, 1.4375, 0.975),
                Shapes.box(-0.03749999999999998, 0.6875, 0.025000000000000022, 0.5625, 1.4375, 0.975),
                Shapes.box(-0.11250000000000004, 1.415625, -0.03125, 0.5, 1.60625, 1.03125),
                Shapes.box(0.5, 1.415625, -0.03125, 1.1125, 1.60625, 1.03125),
                Shapes.box(0.5, 1.5625, 0.16562500000000002, 0.88125, 1.6875, 0.834375),
                Shapes.box(0.11875000000000002, 1.5625, 0.16562500000000002, 0.5, 1.6875, 0.834375),
                Shapes.box(0.2, 1.6875, 0.2375, 0.5125, 1.765625, 0.7625),
                Shapes.box(0.4875, 1.6875, 0.2375, 0.8, 1.765625, 0.7625)
            ), Direction.SOUTH, 2, 2, 2, 0.5, -0.5
        )

        val CODEC = simpleCodec(::RksMachineBlock)
    }
}
