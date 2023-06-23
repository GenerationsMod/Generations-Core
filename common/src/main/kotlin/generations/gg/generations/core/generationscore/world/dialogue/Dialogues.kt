package generations.gg.generations.core.generationscore.world.dialogue

import com.cobblemon.mod.common.api.data.JsonDataRegistry
import com.cobblemon.mod.common.api.moves.MoveTemplate
import com.cobblemon.mod.common.api.reactive.SimpleObservable
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import generations.gg.generations.core.generationscore.GenerationsCore
import generations.gg.generations.core.generationscore.world.dialogue.network.DialogueGraphRegistrySyncPacket
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNode
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.packs.PackType

class Dialogues : JsonDataRegistry<DialogueGraph> {
    override val id = GenerationsCore.id("dialogues")
    override val gson: Gson = GsonBuilder().create()
    override val type: PackType = PackType.SERVER_DATA //TODO: Figure out if need client.
    override val typeToken = TypeToken.get(DialogueGraph::class.java)
    override val resourcePath = id.path
    override val observable = SimpleObservable<Dialogues>()
    override fun sync(player: ServerPlayer) {
        DialogueGraphRegistrySyncPacket(graphsByIdentifier).sendToPlayer(player)
    }

    private val graphsByIdentifier = hashMapOf<ResourceLocation, DialogueGraph>()
    override fun reload(data: Map<ResourceLocation, DialogueGraph>) {
        graphsByIdentifier.clear()
        data.mapNotNull { it }.forEach { graphsByIdentifier[it.key] = it.value }
    }

    fun getOrElse(location: ResourceLocation, dialogueGraph: DialogueGraph): DialogueGraph {
        return graphsByIdentifier[location] ?: dialogueGraph
    }

    fun get(id: ResourceLocation): DialogueGraph? = graphsByIdentifier[id]

    internal fun receiveSyncPacket(graphs: Map<ResourceLocation, DialogueGraph>) = graphsByIdentifier.putAll(graphs)

    companion object {
        fun instance() = Dialogues()
    }
}
