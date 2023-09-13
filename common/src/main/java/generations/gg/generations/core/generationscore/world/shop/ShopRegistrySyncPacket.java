package generations.gg.generations.core.generationscore.world.shop;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.DataRegistrySyncPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ShopRegistrySyncPacket extends DataRegistrySyncPacket<Shop, ShopRegistrySyncPacket> {
    public ShopRegistrySyncPacket(Map<ResourceLocation, Shop> graphs) {
        super(graphs);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encodeEntry(FriendlyByteBuf buffer, Shop entry) {
        entry.encode(buffer);
    }

    @Override
    public Shop decodeEntry(FriendlyByteBuf buffer) {
        return new Shop(buffer);
    }

    @Override
    public void synchronizeDecoded(Map<ResourceLocation, Shop> entries) {
        Shops.instance().receiveSyncPacket(entries);
    }

    public static final ResourceLocation ID = GenerationsCore.id("shop_registry_sync");

    public static ShopRegistrySyncPacket decode(FriendlyByteBuf buffer) {
        var graph = new ShopRegistrySyncPacket(new HashMap<>());
        graph.decodeBuffer(buffer);
        return graph;
    }
}