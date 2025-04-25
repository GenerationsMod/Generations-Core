package generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.CobblemonSounds
import com.cobblemon.mod.common.api.storage.pc.PCStore
import com.cobblemon.mod.common.api.storage.pc.link.PCLink
import com.cobblemon.mod.common.api.storage.pc.link.PCLinkManager
import com.cobblemon.mod.common.api.text.red
import com.cobblemon.mod.common.net.messages.client.storage.pc.OpenPCPacket
import com.cobblemon.mod.common.util.isInBattle
import com.cobblemon.mod.common.util.lang
import com.cobblemon.mod.common.util.playSoundServer
import com.cobblemon.mod.common.util.toVec3d
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.generateRotationalVoxelShape
import generations.gg.generations.core.generationscore.common.world.level.block.entities.DyedPcBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.ItemInteractionResult.*
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.*

abstract class DyedPcBlock(color: DyeColor, map: Map<DyeColor, Holder<Block>>, arg: Properties) : DyeableBlock<DyedPcBlockEntity, DyedPcBlock>(arg, color, map, GenerationsBlockEntities.DYED_PC, GenerationsBlockEntityModels.PC, 0, 1, 0) {
    private val SHAPE = generateRotationalVoxelShape(
        Shapes.or(
            Shapes.box(0.07500000000000001, 0.0, 0.025000000000000022, 0.925, 1.5, 0.725),
            Shapes.box(0.11249999999999999, 1.5, 0.07500000000000001, 0.5, 1.625, 0.7250000000000001),
            Shapes.box(0.11249999999999999, 1.5, 0.07500000000000001, 0.8875, 1.7375, 0.9125000000000001),
            Shapes.box(0.15625, 0.6875, 0.07500000000000001, 0.84375, 0.9875, 0.9125000000000001),
            Shapes.box(0.28125, 0.9875, 0.07500000000000001, 0.71875, 1.00625, 0.9125000000000001),
            Shapes.box(0.20625, 0.875, 0.16249999999999998, 0.79375, 0.9875, 1.0)
        ),
        Direction.SOUTH, 1, 2, 1, 0.0, 0.0
    )

    override fun createDefaultState(): BlockState {
        return super.createDefaultState().setValue(PcBlock.ON, false)
    }

    override fun isPathfindable(state: BlockState, pathComputationType: PathComputationType): Boolean = false

    override fun serverUse(
        stack: ItemStack,
        state: BlockState,
        world: ServerLevel,
        pos: BlockPos,
        player: ServerPlayer,
        handIn: InteractionHand,
        hit: BlockHitResult
    ): ItemInteractionResult {
        val basePos = getBaseBlockPos(pos, state)

        // Remove any duplicate block entities that may exist
        world.getBlockEntity(basePos.above())?.setRemoved()

        val baseEntity = world.getBlockEntity(basePos)
        if (baseEntity !is DyedPcBlockEntity) return ItemInteractionResult.SUCCESS

        if (player.isInBattle()) {
            player.sendSystemMessage(lang("pc.inbattle").red())
            return SUCCESS
        }

        val pc = Cobblemon.storage.getPC(player)
        // TODO add event to check if they can open this PC?
        PCLinkManager.addLink(ProximityPCLink(pc, player.uuid, baseEntity))
        OpenPCPacket(pc.uuid).sendToPlayer(player)
        world.playSoundServer(
            position = pos.toVec3d(),
            sound = CobblemonSounds.PC_ON,
            volume = 1F,
            pitch = 1F
        )
        return SUCCESS
    }

    override fun <T : BlockEntity?> getTicker(
        world: Level,
        blockState: BlockState,
        blockEntityType: BlockEntityType<T>,
    ): BlockEntityTicker<T>? =  createTickerHelper(blockEntityType, GenerationsBlockEntities.DYED_PC.get(), DyedPcBlockEntity.TICKER::tick)

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder.add(PcBlock.ON))
    }

    companion object {
        fun lumiance(state: BlockState): Int {
            return try {
                if (state.getValue(PcBlock.ON) && state.getValue((state.block as DyedPcBlock).heightProperty) == 1) 10 else 0
            } catch (e: IllegalArgumentException) {
                0
            }
        }
    }

    class ProximityPCLink(
        pc: PCStore,
        playerID: UUID,
        pcBlockEntity: DyedPcBlockEntity,
        val maxDistance: Double = 10.0,
    ) : PCLink(pc, playerID) {
        val world = pcBlockEntity.level
        val pos: BlockPos = pcBlockEntity.blockPos

        override fun isPermitted(player: ServerPlayer): Boolean {
            val isWithinRange = player.level() == world && player.position().closerThan(pos.toVec3d(), maxDistance)
            val pcStillStanding = player.level().getBlockEntity(pos) is DyedPcBlockEntity
            if (!isWithinRange || !pcStillStanding) {
                PCLinkManager.removeLink(playerID)
            }
            return isWithinRange && pcStillStanding
        }
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return SHAPE.getShape(state)
    }

}
