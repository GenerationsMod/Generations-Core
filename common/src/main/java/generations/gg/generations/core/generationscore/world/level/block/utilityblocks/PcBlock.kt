package generations.gg.generations.core.generationscore.world.level.block.utilityblocks

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.CobblemonSounds
import com.cobblemon.mod.common.api.storage.pc.PCStore
import com.cobblemon.mod.common.api.storage.pc.link.PCLink
import com.cobblemon.mod.common.api.storage.pc.link.PCLinkManager
import com.cobblemon.mod.common.api.text.red
import com.cobblemon.mod.common.block.entity.PCBlockEntity
import com.cobblemon.mod.common.net.messages.client.storage.pc.OpenPCPacket
import com.cobblemon.mod.common.util.isInBattle
import com.cobblemon.mod.common.util.lang
import com.cobblemon.mod.common.util.playSoundServer
import com.cobblemon.mod.common.util.toVec3d
import generations.gg.generations.core.generationscore.world.item.DyedBlockItem
import generations.gg.generations.core.generationscore.world.level.block.GenerationsUtilityBlocks
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.world.level.block.entities.PcBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResult.SUCCESS
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.DyeColor
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.BlockHitResult
import java.util.*

class PcBlock(arg: Properties) : DoubleDyeableBlock<PcBlockEntity, PcBlock>({ color: DyeColor -> getBlock(color) }, GenerationsBlockEntities.PC, arg, GenerationsBlockEntityModels.PC
) {
    override fun createDefaultState(): BlockState {
        return super.createDefaultState().setValue(ON, false)
    }

    override fun isPathfindable(
        blockState: BlockState,
        blockGetter: BlockGetter,
        blockPos: BlockPos,
        pathComputationType: PathComputationType
    ): Boolean = false

    override fun serverUse(
        blockState: BlockState,
        world: Level,
        blockPos: BlockPos,
        player: Player,
        interactionHand: InteractionHand,
        blockHitResult: BlockHitResult
    ): InteractionResult {
        if (player !is ServerPlayer) return SUCCESS

        val basePos = getBaseBlockPos(blockPos, blockState)

        // Remove any duplicate block entities that may exist
        world.getBlockEntity(basePos.above())?.setRemoved()

        val baseEntity = world.getBlockEntity(basePos)
        if (baseEntity !is PcBlockEntity) return SUCCESS

        if (player.isInBattle()) {
            player.sendSystemMessage(lang("pc.inbattle").red())
            return SUCCESS
        }

        val pc = Cobblemon.storage.getPC(player.uuid) ?: return SUCCESS
        // TODO add event to check if they can open this PC?
        PCLinkManager.addLink(ProximityPCLink(pc, player.uuid, baseEntity))
        OpenPCPacket(pc.uuid).sendToPlayer(player)
        world.playSoundServer(
            position = blockPos.toVec3d(),
            sound = CobblemonSounds.PC_ON,
            volume = 1F,
            pitch = 1F
        )
        return SUCCESS
    }

    override fun <T : BlockEntity> getTicker(world: Level, blockState: BlockState, BlockWithEntityType: BlockEntityType<T>) =  createTickerHelper(BlockWithEntityType, GenerationsBlockEntities.PC.get(), PcBlockEntity.TICKER::tick)

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder.add(ON))
    }

    companion object {
        val ON = BooleanProperty.create("on")

        @JvmStatic
        fun getBlock(color: DyeColor): DyedBlockItem<PcBlock> = when (color) {
            DyeColor.WHITE -> GenerationsUtilityBlocks.WHITE_PC
            DyeColor.ORANGE -> GenerationsUtilityBlocks.ORANGE_PC
            DyeColor.MAGENTA -> GenerationsUtilityBlocks.MAGENTA_PC
            DyeColor.LIGHT_BLUE -> GenerationsUtilityBlocks.LIGHT_BLUE_PC
            DyeColor.YELLOW -> GenerationsUtilityBlocks.YELLOW_PC
            DyeColor.LIME -> GenerationsUtilityBlocks.LIME_PC
            DyeColor.PINK -> GenerationsUtilityBlocks.PINK_PC
            DyeColor.GRAY -> GenerationsUtilityBlocks.GRAY_PC
            DyeColor.LIGHT_GRAY -> GenerationsUtilityBlocks.LIGHT_GRAY_PC
            DyeColor.CYAN -> GenerationsUtilityBlocks.CYAN_PC
            DyeColor.PURPLE -> GenerationsUtilityBlocks.PURPLE_PC
            DyeColor.BLUE -> GenerationsUtilityBlocks.BLUE_PC
            DyeColor.BROWN -> GenerationsUtilityBlocks.BROWN_PC
            DyeColor.GREEN -> GenerationsUtilityBlocks.GREEN_PC
            DyeColor.RED -> GenerationsUtilityBlocks.RED_PC
            DyeColor.BLACK -> GenerationsUtilityBlocks.BLACK_PC
        }.get()

        fun lumiance(state: BlockState): Int {
            return try {
                if (state.getValue(ON) && state.getValue((state.block as PcBlock).heightProperty) == 1) 10 else 0
            } catch (e: IllegalArgumentException) {
                0;
            }
        }
    }

    class ProximityPCLink(
        pc: PCStore,
        playerID: UUID,
        pcBlockEntity: PcBlockEntity,
        val maxDistance: Double = 10.0
    ) : PCLink(pc, playerID) {
        val world = pcBlockEntity.level
        val pos = pcBlockEntity.blockPos

        override fun isPermitted(player: ServerPlayer): Boolean {
            val isWithinRange = player.level == world && player.position().closerThan(pos.toVec3d(), maxDistance)
            val pcStillStanding = player.level.getBlockEntity(pos) is PcBlockEntity
            if (!isWithinRange || !pcStillStanding) {
                PCLinkManager.removeLink(playerID)
            }
            return isWithinRange && pcStillStanding
        }
    }
}
