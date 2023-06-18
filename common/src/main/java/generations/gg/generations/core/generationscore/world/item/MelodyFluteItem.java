package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.google.common.collect.Streams;
import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.container.MelodyFluteContainer;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsShrines;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MelodyFluteItem extends Item implements PostBattleUpdatingItem {
    public static int MAX_DAMAGE = 300;

    public MelodyFluteItem(Properties properties) {
        super(properties.durability(MAX_DAMAGE));
    }

    public static boolean isFlute(ItemStack stack) {
        return isItem(GenerationsItems.MELODY_FLUTE, stack);
    }

    public static <T extends ItemLike> boolean isItem(RegistrySupplier<T> object, ItemStack stack) {
        return object != null && object.stream().map(ItemLike::asItem).allMatch(stack::is);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (!level.isClientSide() && usedHand == InteractionHand.MAIN_HAND && player.getItemInHand(usedHand).getDamageValue() <= 0) {
            MelodyFluteContainer.MelodyFluteItemStackHandler handler = new MelodyFluteContainer.MelodyFluteItemStackHandler((ServerPlayer) player, usedHand);
            MenuRegistry.openExtendedMenu((ServerPlayer) player, new SimpleMenuProvider((i, arg, arg2) -> new MelodyFluteContainer(i, arg, handler), Component.translatable("container.melody_flute")), buf -> buf.writeShort(player.getInventory().selected));
        }

        return super.use(level, player, usedHand);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        return super.useOn(context);
    }

    public static ItemStack getImbuedItem(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTagElement("imbued");

        if (!tag.isEmpty()) {
            return ItemStack.of(tag);
        }

        return ItemStack.EMPTY;
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return !getImbuedItem(stack).isEmpty() && stack.getDamageValue() >= stack.getMaxDamage();
    }

    @Nullable
    public static ElementalType typeFromInbued(ItemStack stack) {
        if (stack.isEmpty()) return null;
        if (isItem(GenerationsItems.ICY_WING, stack)) return ElementalTypes.INSTANCE.getICE();
        else if (isItem(GenerationsItems.PRETTY_FEATHER, stack)) return ElementalTypes.INSTANCE.getPSYCHIC();
        else if (isItem(GenerationsItems.STATIC_WING, stack)) return ElementalTypes.INSTANCE.getELECTRIC();
        else if (isItem(GenerationsItems.BELLIGERENT_WING, stack)) return ElementalTypes.INSTANCE.getFIGHTING();
        else if (isItem(GenerationsItems.FIERY_WING, stack)) return ElementalTypes.INSTANCE.getFIRE();
        else if (isItem(GenerationsItems.SINISTER_WING, stack)) return ElementalTypes.INSTANCE.getDARK();
        else if (isItem(GenerationsItems.RAINBOW_WING, stack) || isItem(GenerationsItems.SILVER_WING, stack))
            return ElementalTypes.INSTANCE.getFLYING();
        else return null;
    }

    public static String getSpeciesNameFromImbued(ItemStack stack) {
        if (isItem(GenerationsItems.ICY_WING, stack)) return getSpeciesNameFromImbued(GenerationsCore.id("articuno"), "none");
        else if (isItem(GenerationsItems.PRETTY_FEATHER, stack))
            return getSpeciesNameFromImbued(GenerationsCore.id("articuno"), "galarian");
        else if (isItem(GenerationsItems.STATIC_WING, stack)) return getSpeciesNameFromImbued(GenerationsCore.id("zapdos"), "none");
        else if (isItem(GenerationsItems.BELLIGERENT_WING, stack))
            return getSpeciesNameFromImbued(GenerationsCore.id("zapdos"), "galarian");
        else if (isItem(GenerationsItems.FIERY_WING, stack)) return getSpeciesNameFromImbued(GenerationsCore.id("moltres"), "none");
        else if (isItem(GenerationsItems.SINISTER_WING, stack))
            return getSpeciesNameFromImbued(GenerationsCore.id("moltres"), "galarian");
        else if (isItem(GenerationsItems.RAINBOW_WING, stack)) return getSpeciesNameFromImbued(GenerationsCore.id("ho_oh"), "none");
        else if (isItem(GenerationsItems.SILVER_WING, stack)) return getSpeciesNameFromImbued(GenerationsCore.id("lugia"), "none");
        else return "";
    }

    public static String getSpeciesNameFromImbued(ResourceLocation id, String form) {
        String name = I18n.get(id.getNamespace() + "." + id.getPath() + ".name");

        if (I18n.exists("form." + form)) {
            name = I18n.get("form." + form, name);
        }

        return name;
    }

    public static String shrineFromImbued(ItemStack stack) {
        String name = "";
        if (isItem(GenerationsItems.ICY_WING, stack) || isItem(GenerationsItems.ELEGANT_WING, stack))
            name = GenerationsShrines.FROZEN_SHRINE.getId().toLanguageKey("block");
        else if (isItem(GenerationsItems.STATIC_WING, stack) || isItem(GenerationsItems.BELLIGERENT_WING, stack))
            name = GenerationsShrines.STATIC_SHRINE.getId().toLanguageKey("block");
        else if (isItem(GenerationsItems.FIERY_WING, stack) || isItem(GenerationsItems.SINISTER_WING, stack))
            name = GenerationsShrines.FIERY_SHRINE.getId().toLanguageKey("block");
        else if (isItem(GenerationsItems.RAINBOW_WING, stack))
            name = GenerationsShrines.CRYSTAL_BELL.getId().toLanguageKey("block");
        else if (isItem(GenerationsItems.SILVER_WING, stack))
            name = GenerationsShrines.LUGIA_SHRINE.getId().toLanguageKey("block");

        return I18n.get(name);
    }

    public static void setImbuedItem(ItemStack stack, ItemStack imbuedStack) {
        stack.getOrCreateTag().put("imbued", imbuedStack.save(new CompoundTag()));
    }

    @Override
    public boolean checkData(PlayerBattleActor player, ItemStack stack, BattleData pixelmonData) {
        ElementalType type = typeFromInbued(stack);
        return type != null && Streams.stream(pixelmonData.pokemon().getTypes()).anyMatch(type::equals);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @org.jetbrains.annotations.Nullable Level level, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag isAdvanced) {
        ItemStack imbued = getImbuedItem(stack);

        if (imbued.isEmpty()) {
            tooltipComponents.add(Component.translatable("pixelmon.melody_flute.no_item"));
        } else {
            ElementalType type = typeFromInbued(imbued);
            String shrine = shrineFromImbued(imbued);
            String name = getSpeciesNameFromImbued(imbued);

            tooltipComponents.add(Component.translatable(imbued.getDescriptionId()));

            if (stack.getDamageValue() >= stack.getMaxDamage()) {
                tooltipComponents.add(Component.translatable("pixelmon.melody_flute.full_imbued1", type));
                tooltipComponents.add(Component.translatable("pixelmon.melody_flute.full_imbued2", shrine));
                tooltipComponents.add(Component.translatable("pixelmon.melody_flute.full_imbued3", name));
            } else {
                tooltipComponents.add(Component.translatable("pixelmon.melody_flute.not_full_imbued1", stack.getMaxDamage() - stack.getDamageValue(), type));
                tooltipComponents.add(Component.translatable("pixelmon.melody_flute.not_full_imbued2", shrine));
                tooltipComponents.add(Component.translatable("pixelmon.melody_flute.not_full_imbued3", name));
            }
        }
    }
}
