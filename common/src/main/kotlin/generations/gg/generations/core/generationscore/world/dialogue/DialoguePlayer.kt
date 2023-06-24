package generations.gg.generations.core.generationscore.world.dialogue

import generations.gg.generations.core.generationscore.GenerationsCore
import generations.gg.generations.core.generationscore.network.packets.dialogue.S2CCloseScreenPacket
import generations.gg.generations.core.generationscore.network.packets.dialogue.S2COpenDialogueMenuPacket
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNode
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.LivingEntity
import java.util.*

class DialoguePlayer(
    graph: DialogueGraph,
    val source: LivingEntity?,
    private val player: ServerPlayer,
    private val forcedUntilEnd: Boolean
) {
    var currentNode: AbstractNode?

    constructor(
        id: ResourceLocation,
        source: LivingEntity?,
        serverPlayer: ServerPlayer,
        forcedUntilEnd: Boolean
    ) : this(
        Objects.requireNonNull<DialogueGraph?>(
            Dialogues.instance().get(
                id
            )
        ), source, serverPlayer, forcedUntilEnd
    )

    init {
        currentNode = graph!!.root
        if (DialogueManager.DIALOGUE_MAP.containsKey(player)) throw RuntimeException(player.name.toString() + "[" + player.stringUUID + "] Is already in a dialogue menu")
        DialogueManager.DIALOGUE_MAP[player] = this
        GenerationsCore.getImplementation().networkManager.sendPacketToPlayer(
            player,
            S2COpenDialogueMenuPacket(!forcedUntilEnd)
        )
        update()
    }

    fun openAndNextNode() {
        GenerationsCore.getImplementation().networkManager.sendPacketToPlayer(
            player,
            S2COpenDialogueMenuPacket(!forcedUntilEnd)
        )
        nextNode()
    }

    fun nextNode() {
        currentNode = currentNode!!.next
        update()
    }

    fun update() {
        if (currentNode == null) discard() else currentNode!!.run(player, this)
    }

    fun discard() {
        DialogueManager.DIALOGUE_MAP.remove(player)
        GenerationsCore.getImplementation().networkManager.sendPacketToPlayer(player, S2CCloseScreenPacket())
    }
}
