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
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.level.block.entities.PcBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResult.SUCCESS
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.BlockHitResult
import java.util.*

abstract class PcBlock<T : PcBlockEntity<T>, V : PcBlock<T, V>>(
    type: BlockEntityType<T>,
    private val blockEntityClass: Class<T>,
    arg: Properties,
    model: ResourceLocation,
    width: Int = 0,
    height: Int = 0,
    length: Int = 0
) : GenericRotatableModelBlock<T>(arg, type, model = model, width = width, height = height, length = length) {
    override fun createDefaultState(): BlockState {
        return super.createDefaultState().setValue(ON, false)
    }

    override fun isPathfindable(state: BlockState, pathComputationType: PathComputationType): Boolean = false


    override fun useWithoutItem(
        blockState: BlockState,
        world: Level,
        blockPos: BlockPos,
        player: Player,
        hit: BlockHitResult
    ): InteractionResult {
        if (player !is ServerPlayer) return SUCCESS

        val basePos = getBaseBlockPos(blockPos, blockState)

        // Remove any duplicate block entities that may exist
        world.getBlockEntity(basePos.above())?.setRemoved()

        val baseEntity = world.getBlockEntity(basePos)
        if (!blockEntityClass.isInstance(baseEntity)) return SUCCESS

        if (player.isInBattle()) {
            player.sendSystemMessage(lang("pc.inbattle").red())
            return SUCCESS
        }

        val pc = Cobblemon.storage.getPC(player)
        // TODO add event to check if they can open this PC?
        PCLinkManager.addLink(ProximityPCLink(blockEntityClass, pc, player.uuid, blockEntityClass.cast(baseEntity)))
        OpenPCPacket(pc.uuid).sendToPlayer(player)
        world.playSoundServer(
            position = blockPos.toVec3d(),
            sound = CobblemonSounds.PC_ON,
            volume = 1F,
            pitch = 1F
        )
        return SUCCESS
    }

    override fun <T : BlockEntity> getTicker(world: Level, blockState: BlockState, BlockWithEntityType: BlockEntityType<T>) =  createTickerHelper(BlockWithEntityType, blockEntityFunction, getTicker())

    abstract fun getTicker() :BlockEntityTicker<T>

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder.add(ON))
    }

    companion object {
        val ON: BooleanProperty = BooleanProperty.create("on")

        fun lumiance(state: BlockState): Int {
            return try {
                if (state.getValue(ON) && (state.block.instanceOrNull<GenericRotatableModelBlock<*>>()?.lengthProperty?.let { state.getValue(it) } ?: 0) == 1) 10 else 0
            } catch (e: IllegalArgumentException) {
                0
            }
        }
    }

    class ProximityPCLink<T : PcBlockEntity<T>>(
        val clazz: Class<T>,
        pc: PCStore,
        playerID: UUID,
        pcBlockEntity: T,
        val maxDistance: Double = 10.0
    ) : PCLink(pc, playerID) {
        val world = pcBlockEntity.level
        val pos: BlockPos = pcBlockEntity.blockPos

        override fun isPermitted(player: ServerPlayer): Boolean {
            val isWithinRange = player.level() == world && player.position().closerThan(pos.toVec3d(), maxDistance)
            val pcStillStanding = clazz.isInstance(player.level().getBlockEntity(pos))
            if (!isWithinRange || !pcStillStanding) {
                PCLinkManager.removeLink(playerID)
            }
            return isWithinRange && pcStillStanding
        }
    }
}
