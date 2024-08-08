package generations.gg.generations.core.generationscore.common.network.packets;

import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.common.world.item.MailItem;
import net.minecraft.nbt.StringTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;

import java.util.Optional;

public class C2SEditMailHandler implements ServerNetworkPacketHandler<C2SEditMailPacket> {
    public void handle(C2SEditMailPacket packet, MinecraftServer server, ServerPlayer player) {
        server.execute(() -> handleEditMail(
                player,
                packet.slot(),
                packet.contents(),
                packet.title()
        ));
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
