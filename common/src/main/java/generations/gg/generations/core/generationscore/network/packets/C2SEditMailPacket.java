package generations.gg.generations.core.generationscore.network.packets;

import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.world.item.MailItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public record C2SEditMailPacket(int slot, String contents, Optional<String> title) {
    public C2SEditMailPacket(FriendlyByteBuf buf) {
        this(buf.readVarInt(), buf.readUtf(8192), buf.readOptional(arg -> arg.readUtf(128)));
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeVarInt(slot);
        buf.writeUtf(contents, 8192);
        buf.writeOptional(title, ((byteBuf, s) -> buf.writeUtf(s, 128)));
    }

    public void handleEditMail(ServerPlayer sender, int slot, String contents, Optional<String> title) {
        if (Inventory.isHotbarSlot(slot) || slot == 40) {
            title.ifPresentOrElse(s -> sealMail(sender, slot, contents, s), () -> updateMailContents(sender, slot, contents));
        }
    }

    private void updateMailContents(ServerPlayer sender, int slot, String contents) {
        var itemStack = sender.getInventory().getItem(slot);

        if (itemStack.is(GenerationsItemTags.POKEMAIL)) {
            itemStack.addTagElement("contents", StringTag.valueOf(contents));
        }
    }

    private void sealMail(ServerPlayer sender, int slot, String contents, String title) {
        ItemStack itemStack = sender.getInventory().getItem(slot);

        if (itemStack.is(GenerationsItemTags.POKEMAIL)) {
            ItemStack itemStack1 = MailItem.getSealed(itemStack.getItem());

            CompoundTag compoundTag = itemStack.getTag();

            if (compoundTag != null) {
                itemStack1.setTag(compoundTag);
            }

            itemStack1.addTagElement("author", StringTag.valueOf(sender.getName().getString()));
            itemStack1.addTagElement("contents", StringTag.valueOf(contents));

            sender.getInventory().setItem(slot, itemStack1);
        }
    }
}