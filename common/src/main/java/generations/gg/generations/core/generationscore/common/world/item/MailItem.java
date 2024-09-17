package generations.gg.generations.core.generationscore.common.world.item;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.network.packets.S2COpenMailEditScreenPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MailItem extends Item {
    private final MailType type;

    public MailItem(MailType type, Properties arg) {
        super(arg);
        this.type = type;
    }

    public static ItemStack getSealed(Item item) {
        if (item instanceof MailItem mailItem)
            return new ItemStack(mailItem.type.sealed);
        else return ItemStack.EMPTY;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand usedHand) {
        var itemStack = player.getItemInHand(usedHand);
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer)
            GenerationsCore.getImplementation().getNetworkManager().sendPacketToPlayer(serverPlayer, new S2COpenMailEditScreenPacket(usedHand));
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    /**
     * Returns {@code true} if the book's NBT Tag List "pages" is valid.
     */
    public static boolean makeSureTagIsValid(@Nullable CompoundTag compoundTag) {
        if (compoundTag == null) return false;
        if (!compoundTag.contains("contents", Tag.TAG_STRING)) return false;

        var contents = compoundTag.getString("contents");
        return contents.length() <= Short.MAX_VALUE;
    }

    public MailType getType() {
        return type;
    }
}
