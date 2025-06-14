package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines

import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.ModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.InteractShrineBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

open class InteractShrineBlockEntity : ShrineBlockEntity {
    private var countdown = 0

    constructor(blockEntityType: BlockEntityType<out ModelProvidingBlockEntity>, pos: BlockPos, state: BlockState) : super(blockEntityType, pos, state)

    constructor(
        pos: BlockPos,
        state: BlockState
    ) : this(GenerationsBlockEntities.INTERACT_SHRINE, pos, state)

    override fun getVariant(): String? = blockState.block.instanceOrNull<InteractShrineBlock<*>>()?.getVariant(blockState)

    fun triggerCountDown() = blockState.block.instanceOrNull<InteractShrineBlock<*>>()?.takeIf { it.waitToDeactivateTime() > 0 }?.run { this@InteractShrineBlockEntity.countdown = this.waitToDeactivateTime() }

    fun tick() {
        blockState.block.instanceOrNull<InteractShrineBlock<*>>()?.takeIf { this.countdown > 0 }?.let { shrine ->

            if (this.countdown > 0) {
//                System.out.println(getClass().getSimpleName() + ": " + countdown);

                countdown--

                if (countdown == 0) {
                    shrine.deactivate(getLevel()!!, blockPos, blockState)
                }
            }
        }
    }
}
