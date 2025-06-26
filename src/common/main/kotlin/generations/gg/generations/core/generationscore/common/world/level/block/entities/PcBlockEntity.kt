package generations.gg.generations.core.generationscore.common.world.level.block.entities

import com.cobblemon.mod.common.api.storage.pc.link.PCLinkManager
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.VariantProvider
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.PcBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.entity.EntityTypeTest
import net.minecraft.world.phys.AABB

open class PcBlockEntity(
    type: Holder<BlockEntityType<*>>, blockPos: BlockPos, blockState: BlockState,
) : ModelProvidingBlockEntity(type, blockPos, blockState), VariantProvider {
    fun togglePCOn(on: Boolean) {
        val pcBlock = blockState.block as GenericRotatableModelBlock

        if (level != null && !level!!.isClientSide) {
            val world = level!!
            val posBottom = pcBlock.getBaseBlockPos(blockPos, blockState)
            val stateBottom = world.getBlockState(posBottom)

            val posTop = posBottom.above()

            if (stateBottom.getValue(PcBlock.ON) != on) {
                world.setBlockAndUpdate(posBottom, stateBottom.setValue(PcBlock.ON, on))
                if((stateBottom.block as GenericRotatableModelBlock).height == 1) world.setBlockAndUpdate(posTop, stateBottom.setValue(PcBlock.ON, on).setValue(pcBlock.heightProperty, 1))
            }
        }
    }

    private fun isPlayerViewing(player: Player): Boolean {
        val pcLink = PCLinkManager.getLink(player.uuid)
        return pcLink != null
                && pcLink is PcBlock.ProximityPCLink<*>
                && pcLink.pos == blockPos
                && pcLink.world!!.dimension() == player.level().dimension()
    }

    fun getInRangeViewerCount(world: Level, pos: BlockPos, range: Double = 5.0): Int {
        val box = AABB(
            pos.x.toDouble() - range,
            pos.y.toDouble() - range,
            pos.z.toDouble() - range,
            (pos.x + 1).toDouble() + range,
            (pos.y + 1).toDouble() + range,
            (pos.z + 1).toDouble() + range
        )

        return world.getEntities(EntityTypeTest.forClass(Player::class.java), box) { player: Player? -> isPlayerViewing(player!!) }.size
    }

    override fun getVariant(): String {
        return (if(blockState.getValue(PcBlock.ON)) "on" else "off")
    }
}