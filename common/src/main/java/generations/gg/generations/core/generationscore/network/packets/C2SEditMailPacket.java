package generations.gg.generations.core.generationscore.network.packets;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.world.item.MailItem;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;

import java.util.Optional;

public record C2SEditMailPacket(int slot, String contents, Optional<String> title) implements GenerationsNetworkPacket<C2SEditMailPacket> {
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeVarInt(slot);
        buf.writeUtf(contents, 8192);
        buf.writeOptional(title, (byteBuf, s) -> buf.writeUtf(s, 128));
    }
    public static ResourceLocation ID = GenerationsCore.id("edit_mail");

    public static C2SEditMailPacket decode(FriendlyByteBuf buffer) {
        return new C2SEditMailPacket(buffer.readVarInt(), buffer.readUtf(8192), buffer.readOptional(arg -> arg.readUtf(128)));
    }

    public static void handleEditMail(ServerPlayer sender, int slot, String contents, Optional<String> title) {
        if (Inventory.isHotbarSlot(slot) || slot == 40) {
            title.ifPresentOrElse(s -> sealMail(sender, slot, contents, s), () -> updateMailContents(
                    sender,
                    slot,
                    contents
                ));
            }
        }

        private static void updateMailContents(ServerPlayer sender, int slot, String contents) {
            var itemStack = sender.getInventory().getItem(slot);
            if (itemStack.is(GenerationsItemTags.POKEMAIL)) {
                itemStack.addTagElement("contents", StringTag.valueOf(contents));
            }
        }

        private static void sealMail(ServerPlayer sender, int slot, String contents, String title) {
            var itemStack = sender.getInventory().getItem(slot);
            if (itemStack.is(GenerationsItemTags.POKEMAIL)) {
                var itemStack1 = MailItem.getSealed(itemStack.getItem());
                var compoundTag = itemStack.getTag();
                if (compoundTag != null) {
                itemStack1.setTag(compoundTag);
            }
            itemStack1.addTagElement("author", StringTag.valueOf(sender.getName().getString()));
            itemStack1.addTagElement("contents", StringTag.valueOf(contents));
            sender.getInventory().setItem(slot, itemStack1);
        }
    }
}