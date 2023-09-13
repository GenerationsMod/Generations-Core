package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AbstractNodeTypes {
    public static final BiMap<ResourceLocation, AbstractNodeType<?>> ABSTRACT_NODES = HashBiMap.create();

    public static final AbstractNodeType<ChooseNode> CHOOSE = register("choose", ChooseNode::fromJson, ChooseNode::decode);
    public static final AbstractNodeType<HealNode> HEAL = register("heal", HealNode::fromJson, HealNode::decode);
    public static final AbstractNodeType<SpawnPokemonNode> SPAWN_POKEMON = register("spawn_pokemon", SpawnPokemonNode::fromJson, SpawnPokemonNode::decode);
    public static final AbstractNodeType<OpenShopNode> OPEN_SHOP = register("open_shop", OpenShopNode::fromJson, OpenShopNode::decode);
    public static final AbstractNodeType<SayNode> SAY = register("say", SayNode::fromJson, SayNode::decode);

    public static <T extends AbstractNode> AbstractNodeType<T> register(String name, Function<JsonObject, T> codec, Function<FriendlyByteBuf, T> decoder) {
        return register(GenerationsCore.id(name), codec, decoder);
    }
    public static <T extends AbstractNode> AbstractNodeType<T> register(ResourceLocation name, Function<JsonObject, T> codec, Function<FriendlyByteBuf, T> decoder) {
        return (AbstractNodeType<T>) GenerationsUtils.ensureMapReturn(ABSTRACT_NODES, name, new AbstractNodeType<T>(name, codec, decoder));
    }

    public static <T extends AbstractNode> void encode(FriendlyByteBuf buf, T t) {
        buf.writeResourceLocation(ABSTRACT_NODES.inverse().get(t.type()));
        t.encode(buf);
    }

    public static AbstractNode decode(FriendlyByteBuf buf) {
        return GenerationsUtils.processIfNotNull(ABSTRACT_NODES.get(buf.readResourceLocation()), buf, (BiFunction<AbstractNodeType<?>, FriendlyByteBuf, AbstractNode>) (locationLogicType, buf1) -> locationLogicType.decoder().apply(buf1));
    }

    public static <T extends AbstractNode> JsonObject toJson(T t) {
        var json = new JsonObject();
        json.addProperty("type", ABSTRACT_NODES.inverse().get(t.type()).toString());
        t.toJson(json);
        return json;
    }

    public static AbstractNode fromJson(JsonObject buf) {
        return GenerationsUtils.processIfNotNull(ABSTRACT_NODES.get(ResourceLocation.tryParse(buf.getAsJsonPrimitive("type").getAsString())), buf, (BiFunction<AbstractNodeType<? extends AbstractNode>, JsonObject, AbstractNode>) (locationLogicType, object) -> locationLogicType.codec().apply(object));
    }

    public static int getSize() {
        return ABSTRACT_NODES.size();
    }

    public static Collection<String> getIds() {
        return ABSTRACT_NODES.keySet().stream().map(ResourceLocation::toString).collect(Collectors.toSet());
    }

    public static void init() {

    }
}