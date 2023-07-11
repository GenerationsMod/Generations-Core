package generations.gg.generations.core.generationscore.world.level.block.entities

import com.cobblemon.mod.common.CobblemonBlocks
import com.cobblemon.mod.common.api.storage.pc.link.PCLinkManager
import com.cobblemon.mod.common.api.storage.pc.link.ProximityPCLink
import com.cobblemon.mod.common.block.PCBlock
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.PcBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.entity.EntityTypeTest
import net.minecraft.world.phys.AABB

class PcBlockEntity(blockPos: BlockPos, blockState: BlockState
) : DyedVariantBlockEntity<PcBlockEntity>(GenerationsBlockEntities.PC.get(), blockPos, blockState) {

    companion object {
        internal val TICKER = BlockEntityTicker<PcBlockEntity> { world, _, _, blockEntity ->
            if (world.isClientSide) return@BlockEntityTicker

            blockEntity.togglePCOn(blockEntity.getInRangeViewerCount(world, blockEntity.blockPos) > 0)
        }
    }

    private fun togglePCOn(on: Boolean) {
        val pcBlock = blockState.block as PcBlock

        if (level != null && !level!!.isClientSide) {
            val world = level!!
            val posBottom = pcBlock.getBaseBlockPos(blockPos, blockState)
            val stateBottom = world.getBlockState(posBottom)

            val dye = (world.getBlockEntity(posBottom) as? PcBlockEntity)?.color

            val posTop = posBottom.above()

            if (stateBottom.getValue(PcBlock.ON) != on) {
                world.setBlockAndUpdate(posTop, stateBottom.setValue(PcBlock.ON, on).setValue(pcBlock.heightProperty, 1))
                world.setBlockAndUpdate(posBottom, stateBottom.setValue(PcBlock.ON, on))
                (world.getBlockEntity(posBottom) as? PcBlockEntity)?.color = dye
            }
        }
    }

    private fun isPlayerViewing(player: Player): Boolean {
        val pcLink = PCLinkManager.getLink(player.uuid)
        return pcLink != null
                && pcLink is PcBlock.ProximityPCLink
                && pcLink.pos == blockPos
                && pcLink.world!!.dimension() == player.level().dimension()
    }

    private fun getInRangeViewerCount(world: Level, pos: BlockPos, range: Double = 5.0): Int {
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
        return super.getVariant() + "_" + (if(blockState.getValue(PcBlock.ON)) "on" else "off")
    }
}