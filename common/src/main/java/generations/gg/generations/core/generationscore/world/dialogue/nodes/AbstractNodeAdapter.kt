package generations.gg.generations.core.generationscore.world.dialogue.nodes

import generations.gg.generations.core.generationscore.util.Adapter
import net.minecraft.network.FriendlyByteBuf
import kotlin.reflect.KClass

object AbstractNodeAdapter: Adapter<AbstractNode>("type") {
    val decoders: MutableMap<String, (FriendlyByteBuf) -> AbstractNode> = mutableMapOf()
    init {
        registerType("choose", ChooseNode::class, ChooseNode::decode)
        registerType("heal", HealNode::class, HealNode::decode)
        registerType("spawn_pokemon", SpawnPokemonNode::class, SpawnPokemonNode::decode)
        registerType("open_shop", OpenShopNode::class, OpenShopNode::decode)
        registerType("say", SayNode::class, SayNode::decode)
    }

    fun <T: AbstractNode> registerType(id: String, type: KClass<T>, decoder: (FriendlyByteBuf) -> AbstractNode) {
        decoders[id] = decoder
        return registerType(id, type)
    }

    @JvmStatic
    fun decode(buf: FriendlyByteBuf): AbstractNode {
        return decoders[buf.readUtf()]?.invoke(buf)!!
    }
}