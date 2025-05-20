package generations.gg.generations.core.generationscore.common.world.level.block

import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ContainerLevelAccess
import net.minecraft.world.inventory.CraftingMenu
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CraftingTableBlock
import net.minecraft.world.level.block.state.BlockState

class GenerationsCraftingTableBlock : CraftingTableBlock(Properties.ofFullCopy(Blocks.CRAFTING_TABLE)) {
    public override fun getMenuProvider(state: BlockState, level: Level, pos: BlockPos): MenuProvider {
        return SimpleMenuProvider({ i: Int, arg3: Inventory, _: Player ->
            GenerationsWorkBenchContainer(
                i, arg3, ContainerLevelAccess.create(level, pos),
                this
            )
        }, CONTAINER_TITLE)
    }

    companion object {
        private val CONTAINER_TITLE: Component = Component.translatable("container.crafting")
    }
}

internal class GenerationsWorkBenchContainer(
    id: Int,
    playerInv: Inventory,
    private val worldPos: ContainerLevelAccess,
    private val workbench: Block
) :
    CraftingMenu(id, playerInv, worldPos) {
    override fun stillValid(playerIn: Player): Boolean {
        return isWithinUsableDistance(this.worldPos, playerIn, this.workbench)
    }

    companion object {
        protected fun isWithinUsableDistance(
            worldPos: ContainerLevelAccess,
            playerIn: Player,
            targetBlock: Block
        ): Boolean {
            return worldPos.evaluate({ world: Level, pos: BlockPos ->
                world.getBlockState(pos).block === targetBlock && playerIn.distanceToSqr(
                    pos.x.toDouble() + 0.5,
                    pos.y.toDouble() + 0.5,
                    pos.z.toDouble() + 0.5
                ) <= 64.0
            }, true)
        }
    }
}
