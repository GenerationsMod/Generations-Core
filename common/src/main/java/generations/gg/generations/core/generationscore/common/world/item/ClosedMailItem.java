package generations.gg.generations.core.generationscore.common.world.item;

import com.cobblemon.mod.common.Cobblemon;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.network.packets.S2COpenMailPacket;
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.util.StringUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ClosedMailItem extends Item {
    public static final int TITLE_LENGTH = 16;
    public static final int TITLE_MAX_LENGTH = 32;
    public static final int PAGE_EDIT_LENGTH = 1024;
    public static final int PAGE_LENGTH = Short.MAX_VALUE;
    public static final String TAG_TITLE = "title";
    public static final String TAG_AUTHOR = "author";
    public static final String TAG_CONTENTS = "contents";
    public static final String TAG_RESOLVED = "resolved";

    private final MailType type;

    public ClosedMailItem(MailType type, Properties arg) {
        super(arg);
        this.type = type;
    }

    public static boolean makeSureTagIsValid(@Nullable CompoundTag compoundTag) {
        if (!MailItem.makeSureTagIsValid(compoundTag)) {
            return false;
        }

        return compoundTag.contains(TAG_AUTHOR, 8);
    }


    @Override
    public @NotNull Component getName(ItemStack stack) {
        String string;
        CompoundTag compoundTag = stack.getTag();
        if (compoundTag != null && !StringUtil.isNullOrEmpty(string = compoundTag.getString(TAG_TITLE))) {
            return Component.literal(string);
        }
        return super.getName(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag isAdvanced) {
        if (stack.hasTag()) {
            CompoundTag compoundTag = stack.getTag();
            String string = compoundTag.getString(TAG_AUTHOR);
            if (!StringUtil.isNullOrEmpty(string)) {
                tooltipComponents.add(Component.translatable("book.byAuthor", string).withStyle(ChatFormatting.GRAY));
            }
        }
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        if (player instanceof ServerPlayer serverPlayer && itemStack.is(GenerationsItemTags.CLOSED_POKEMAIL)) {
            if(resolveBookComponents(itemStack, serverPlayer.createCommandSourceStack(), serverPlayer)) {
                serverPlayer.containerMenu.broadcastChanges();
            }

            Cobblemon.INSTANCE.getImplementation().getNetworkManager().sendPacketToPlayer(serverPlayer, new S2COpenMailPacket(usedHand));
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    public static boolean resolveBookComponents(ItemStack bookStack, @Nullable CommandSourceStack resolvingSource, @Nullable Player resolvingPlayer) {
        CompoundTag compoundTag = bookStack.getTag();
        if (compoundTag == null || compoundTag.getBoolean(TAG_RESOLVED)) {
            return false;
        }
        compoundTag.putBoolean(TAG_RESOLVED, true);
        if (!ClosedMailItem.makeSureTagIsValid(compoundTag)) {
            return false;
        }
        String s = compoundTag.getString(TAG_CONTENTS);
        String string = resolvePage(resolvingSource, resolvingPlayer, s);

        compoundTag.putString(TAG_CONTENTS, string);
        return true;
    }

    private static String resolvePage(@Nullable CommandSourceStack resolvingSource, @Nullable Player resolvingPlayer, String resolvingPageContents) {
        return resolvingPageContents;
        //TODO: Determine if we want Component Support
        /*        MutableComponent component;
        try {
            component = Component.Serializer.fromJsonLenient(resolvingPageContents);
            component = ComponentUtils.updateForEntity(resolvingSource, component, (Entity)resolvingPlayer, 0);
        }
        catch (Exception exception) {
            component = Component.literal(resolvingPageContents);
        }
        return Component.Serializer.toJson(component);*/
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }

    public MailType getType() {
        return type;
    }
}


