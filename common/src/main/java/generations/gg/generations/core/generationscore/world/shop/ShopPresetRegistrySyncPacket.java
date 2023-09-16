package generations.gg.generations.core.generationscore.world.shop;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.DataRegistrySyncPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ShopPresetRegistrySyncPacket extends DataRegistrySyncPacket<ShopPreset, ShopPresetRegistrySyncPacket> {
    public ShopPresetRegistrySyncPacket(Map<ResourceLocation, ShopPreset> graphs) {
        super(graphs);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encodeEntry(FriendlyByteBuf buffer, ShopPreset entry) {
        entry.encode(buffer);
    }

    @Override
    public ShopPreset decodeEntry(FriendlyByteBuf buffer) {
        return new ShopPreset(buffer);
    }

    @Override
    public void synchronizeDecoded(Map<ResourceLocation, ShopPreset> entries) {
        ShopPresets.instance().receiveSyncPacket(entries);
    }

    public static final ResourceLocation ID = GenerationsCore.id("shop_registry_sync");

    public static ShopPresetRegistrySyncPacket decode(FriendlyByteBuf buffer) {
        var graph = new ShopPresetRegistrySyncPacket(new HashMap<>());
        graph.decodeBuffer(buffer);
        return graph;
    }
}