package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import generations.gg.generations.core.generationscore.util.Adapter;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import net.minecraft.network.FriendlyByteBuf;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class AbstractNodeAdapter extends Adapter<AbstractNode> {
    private static final Map<String, Function<FriendlyByteBuf, AbstractNode>> decoders = new HashMap<>();
    public static final AbstractNodeAdapter INSTANCE = new AbstractNodeAdapter();

    public AbstractNodeAdapter() {
        super("type");
        registerType("choose", ChooseNode.class, ChooseNode::decode);
        registerType("heal", HealNode.class, HealNode::decode);
        registerType("spawn_pokemon", SpawnPokemonNode.class, SpawnPokemonNode::decode);
        registerType("open_shop", OpenShopNode.class, OpenShopNode::decode);
        registerType("say", SayNode.class, SayNode::decode);

    }

    public void registerType(String id, Class<? extends AbstractNode> type, Function<FriendlyByteBuf, AbstractNode> decoder) {
        decoders.put(id, decoder);
        registerType(id, type);
    }

    public static AbstractNode decode(FriendlyByteBuf buf) {
        return GenerationsUtils.processIfNotNull(decoders.get(buf.readUtf()), buf, Function::apply);
    }
}