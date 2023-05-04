package generations.gg.generations.core.generationscore.world.item.curry;

import generations.gg.generations.core.generationscore.api.data.curry.Flavor;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemCurry extends Item {

    public ItemCurry(Properties properties) {
        super(properties.stacksTo(64));
    }



    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        CurryData data = ItemCurry.getData(stack);
        String name = BuiltInRegistries.ITEM.getKey(this).toString();

        if(data.getCurryType() != CurryType.None) name = data.getCurryType().getLocalizedName() + " " + name;
        if(data.getFlavor() != Flavor.NONE) name = data.getFlavor().getLocalizedName() + " " + name;

        return Component.nullToEmpty(name);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag isAdvanced) {
        String info = ""; //Language.getInstance().getOrDefault("gui.shopkeeper." + this.getDescriptionId().getTranslationKey());
        if(!hasHideFlag(stack)) {
            if (Screen.hasShiftDown()) {
                tooltipComponents.add(Component.nullToEmpty(info));
                tooltipComponents.add(Component.nullToEmpty("Rating: " + ItemCurry.getData(stack).getRating().getName()));
            } else {
                tooltipComponents.add(Component.nullToEmpty("Hold shift for more info."));
            }
        }
    }

    public boolean hasHideFlag(ItemStack stack) {
        return stack.hasTag() && stack.getTag().getBoolean("hide_tooltip");
    }

    public static ItemStack createStack(CurryData data) {
        ItemStack stack = new ItemStack(GenerationsItems.CURRY.get());
        setData(stack, data);
        return stack;
    }

    public static void setData(ItemStack stack, CurryData data) {
        stack.addTagElement("data", data.toNbt());
    }

    public static CurryData getData(ItemStack stack) {
        return CurryData.fromNbt(stack.getOrCreateTagElement("data"));
    }

    @Override
    public void onCraftedBy(@NotNull ItemStack stack, @NotNull Level level, Player player) {
        if(!player.isLocalPlayer()) { //TODO: Currydex
//            CurryTasteRating rating = CurryDex.of(player).getCurrentTaste();
//
//            CurryData data = getData(stack);
//
//            data.setRating(rating);
//            rating.configureData(data);
//            setData(stack, data);
//
//            CurryDex.add((ServerPlayer) player, data);
        }
    }
}
