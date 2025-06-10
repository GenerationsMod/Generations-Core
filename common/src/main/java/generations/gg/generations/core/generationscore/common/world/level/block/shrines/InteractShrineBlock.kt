package generations.gg.generations.core.generationscore.common.world.level.block.shrines

import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MutableBlockEntityType
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.InteractShrineBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.phys.BlockHitResult

abstract class InteractShrineBlock<T : InteractShrineBlockEntity> : ShrineBlock<T> {
    protected constructor(
        materialIn: Properties,
        blockEntityFunction: RegistrySupplier<BlockEntityType<T>>,
        model: ResourceLocation
    ) : super(materialIn, blockEntityFunction, model)

    protected constructor(
        materialIn: Properties,
        blockEntityFunction: RegistrySupplier<BlockEntityType<T>>,
        model: ResourceLocation,
        width: Int,
        height: Int,
        length: Int
    ) : super(materialIn, blockEntityFunction, model, width, height, length)

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(ACTIVE)
    }

    override fun createDefaultState(): BlockState {
        return super.createDefaultState().setValue(ACTIVE, false)
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        return super.getStateForPlacement(context)?.setValue(ACTIVE, false)
    }

    open fun getVariant(blockState: BlockState): String? {
        return if (isActive(blockState)) "activated" else "deactivated"
    }

    override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hitResult: BlockHitResult
    ): InteractionResult {
        return super.useWithoutItem(state, level, pos, player, hitResult)
    }

    override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hit: BlockHitResult
    ): ItemInteractionResult {
        if (level.isClientSide() || hand == InteractionHand.OFF_HAND) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION

        val activeState = isActive(state)

        if ((!activeState) && isStackValid(player.getItemInHand(hand)) && interact(
                level,
                pos,
                state,
                player as ServerPlayer,
                hand,
                activeState
            )
        ) {
            return ItemInteractionResult.SUCCESS
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION
    }

    override fun <T : BlockEntity> getTicker(
        level: Level,
        state: BlockState,
        blockEntityType: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return if (level.isClientSide || waitToDeactivateTime() == 0) null else createTickerHelper(
            blockEntityType, blockEntityType) { _, _, _, t -> t.instanceOrNull<InteractShrineBlockEntity>()?.run(InteractShrineBlockEntity::tick)
        }
    }



    protected abstract fun interact(
        level: Level,
        pos: BlockPos,
        state: BlockState,
        player: ServerPlayer,
        hand: InteractionHand,
        activationState: Boolean
    ): Boolean

    protected fun activate(level: Level, pos: BlockPos) {
        if (waitToDeactivateTime() == 0) getAssoicatedBlockEntity(
            level,
            pos
        )?.triggerCountDown()
    }

    fun deactivate(level: Level, pos: BlockPos, state: BlockState) {
        if (revertsAfterActivation()) toggleActive(level, pos)
        postDeactivation(level, pos, state)
    }

    abstract fun isStackValid(stack: ItemStack): Boolean

    open fun waitToDeactivateTime(): Int {
        return 0
    }

    open fun revertsAfterActivation(): Boolean {
        return true
    }

    open fun postDeactivation(level: Level, pos: BlockPos, state: BlockState) {
    }

    companion object {
        private val ACTIVE: BooleanProperty = BooleanProperty.create("active")


        @JvmStatic
        fun isActive(state: BlockState): Boolean {
            return state.block is InteractShrineBlock<*> && state.getValue(ACTIVE)
        }

        @JvmStatic
        fun toggleActive(level: Level, pos: BlockPos) {
            var pos = pos
            val state = level.getBlockState(pos)

            var shrine = state.block.instanceOrNull<InteractShrineBlock<*>>() ?: return

            if (shrine.isActivatable) {
                var isActive = isActive(state)

                isActive = !isActive


                pos = shrine.getBaseBlockPos(pos, state)
                val facing = state.getValue(FACING)

                for (x in 0..shrine.width) {
                    for (z in 0..shrine.length) {
                        for (y in 0..shrine.height) {
                            if (!shrine.validPosition(x, y, z)) continue


                            val blockPos: BlockPos = shrine.adjustBlockPos(pos, facing, x, y, z, true)

                            val stateCurrent = level.getBlockState(blockPos)

                            level.setBlockAndUpdate(blockPos, stateCurrent.setValue(ACTIVE, isActive))
                        }
                    }
                }
            }
        }
    }
}
