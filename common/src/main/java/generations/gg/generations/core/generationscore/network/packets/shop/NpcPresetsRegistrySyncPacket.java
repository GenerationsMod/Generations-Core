package generations.gg.generations.core.generationscore.network.packets.shop;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.DataRegistrySyncPacket;
import generations.gg.generations.core.generationscore.world.npc.NpcPreset;
import generations.gg.generations.core.generationscore.world.npc.NpcPresets;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class NpcPresetsRegistrySyncPacket extends DataRegistrySyncPacket<NpcPreset, NpcPresetsRegistrySyncPacket> {
    public NpcPresetsRegistrySyncPacket(Map<ResourceLocation, NpcPreset> graphs) {
        super(graphs);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encodeEntry(FriendlyByteBuf buffer, NpcPreset entry) {
        entry.encode(buffer);
    }

    @Override
    public NpcPreset decodeEntry(FriendlyByteBuf buffer) {
        return new NpcPreset(buffer);
    }

    @Override
    public void synchronizeDecoded(Map<ResourceLocation, NpcPreset> entries) {
        NpcPresets.instance().receiveSyncPacket(entries);
    }

    public static final ResourceLocation ID = GenerationsCore.id("npc_preset_registry_sync");
    public static NpcPresetsRegistrySyncPacket decode(FriendlyByteBuf buffer) {
        var graph = new NpcPresetsRegistrySyncPacket(new HashMap<>());
        graph.decodeBuffer(buffer);
        return graph;
    }
}
