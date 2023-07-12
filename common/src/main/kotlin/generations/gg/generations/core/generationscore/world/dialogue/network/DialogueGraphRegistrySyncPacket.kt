package generations.gg.generations.core.generationscore.world.dialogue.network

import com.google.gson.JsonParser
import com.mojang.serialization.Codec
import com.mojang.serialization.JsonOps
import generations.gg.generations.core.generationscore.GenerationsCore
import generations.gg.generations.core.generationscore.network.packets.DataRegistrySyncPacket
import generations.gg.generations.core.generationscore.world.dialogue.DialogueGraph
import generations.gg.generations.core.generationscore.world.dialogue.Dialogues
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNode
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class DialogueGraphRegistrySyncPacket(graphs: MutableMap<ResourceLocation, DialogueGraph>): DataRegistrySyncPacket<DialogueGraph, DialogueGraphRegistrySyncPacket>(graphs) {
    override val id = ID

    override fun encodeEntry(buffer: FriendlyByteBuf, entry: DialogueGraph) {
        entry.root.encode(buffer)
    }

    override fun decodeEntry(buffer: FriendlyByteBuf): DialogueGraph = DialogueGraph(AbstractNode.decode(buffer))

    override fun synchronizeDecoded(entries: Map<ResourceLocation, DialogueGraph>) = Dialogues.instance().receiveSyncPacket(entries)

    companion object {
        val ID = GenerationsCore.id("dialogue_graph_registry_sync")
        fun decode(buffer: FriendlyByteBuf) = DialogueGraphRegistrySyncPacket(mutableMapOf()).apply { decodeBuffer(buffer) }
    }
}

private fun <T: Any> FriendlyByteBuf.toJson(codec: Codec<T>): T = JsonOps.COMPRESSED.withParser(codec).andThen { it.result() }.andThen { it.get() }.apply(JsonParser.parseString(readUtf()))

private fun <T: Any> FriendlyByteBuf.fromJson(codec: Codec<T>, u: T) = writeUtf(JsonOps.COMPRESSED.withEncoder(codec).andThen { it.result() }.andThen { it.get() }.andThen { it.toString() }.apply(u))
