package generations.gg.generations.core.generationscore.world.dialogue.network;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.DataRegistrySyncPacket;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueGraph;
import generations.gg.generations.core.generationscore.world.dialogue.Dialogues;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNodeTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DialogueGraphRegistrySyncPacket extends DataRegistrySyncPacket<DialogueGraph, DialogueGraphRegistrySyncPacket> {
    public DialogueGraphRegistrySyncPacket(Map<ResourceLocation, DialogueGraph> graphs) {
        super(graphs);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encodeEntry(FriendlyByteBuf buffer, DialogueGraph entry) {
        AbstractNodeTypes.encode(buffer, entry.root());
    }

    @Override
    public DialogueGraph decodeEntry(FriendlyByteBuf buffer) {
        return new DialogueGraph(AbstractNodeTypes.decode(buffer));
    }

    @Override
    public void synchronizeDecoded(Map<ResourceLocation, DialogueGraph> entries) {
        Dialogues.instance().receiveSyncPacket(entries);
    }

    public static final ResourceLocation ID = GenerationsCore.id("dialogue_graph_registry_sync");
    public static DialogueGraphRegistrySyncPacket decode(FriendlyByteBuf buffer) {
        var graph = new DialogueGraphRegistrySyncPacket(new HashMap<>());
        graph.decodeBuffer(buffer);
        return graph;
    }

    private static <T> T toJson(FriendlyByteBuf buffer, Codec<T> codec) {
        return JsonOps.COMPRESSED.withParser(codec).andThen(DataResult::result).andThen(Optional::get).apply(JsonParser.parseString(buffer.readUtf()));
    }

    private static <T> void fromJson(FriendlyByteBuf buffer, Codec<T> codec, T u) {
        buffer.writeUtf(JsonOps.COMPRESSED.withEncoder(codec).andThen(DataResult::result).andThen(Optional::get).andThen(JsonElement::toString).apply(u));
    }
}
