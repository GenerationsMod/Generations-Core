package generations.gg.generations.core.generationscore.common.world.item;

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsShrines;
import generations.gg.generations.core.generationscore.common.world.container.MelodyFluteContainer;
import generations.gg.generations.core.generationscore.common.world.item.legends.ElementalPostBattleUpdateItem;
import net.minecraft.locale.Language;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MelodyFluteItem extends ElementalPostBattleUpdateItem {
    public static int MAX_DAMAGE = 250;

    public MelodyFluteItem(Properties properties) {
        super(properties.durability(MAX_DAMAGE));
    }

    public static boolean isFlute(ItemStack stack) {
        return isItem(GenerationsItems.MELODY_FLUTE, stack);
    }

    public static <T extends ItemLike> boolean isItem(RegistrySupplier<T> object, ItemStack stack) {
        return object.toOptional().map(ItemLike::asItem).filter(stack::is).isPresent();
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
        if(stack != null) {
            CompoundTag tag = stack.getOrCreateTagElement("imbued");

            if (!tag.isEmpty()) {
                return ItemStack.of(tag);
            }
        }

        return ItemStack.EMPTY;
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return !getImbuedItem(stack).isEmpty() && stack.getDamageValue() >= stack.getMaxDamage();
    }

    @Nullable
    public static ElementalType typeFromInbued(ItemStack stack) {
        return stack.getItem() instanceof WingItem wing ? wing.getType() : null;
    }

    public static String getSpeciesNameFromImbued(ItemStack stack) {
        return stack.getItem() instanceof WingItem wing ? getSpeciesNameFromImbued(wing.getKey()) : "";
    }

    public static String getWingName(ItemStack stack) {
        return stack.getItem() instanceof WingItem wing ? "." + wing : "";
    }


    public static String getSpeciesNameFromImbued(SpeciesKey key) {
        return (key.aspects().contains("galarian") ? "Galarian " : "")  + PokemonSpecies.INSTANCE.getByIdentifier(key.species()).getName();
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
            name = GenerationsShrines.HO_OH_SHRINE.getId().toLanguageKey("block");
        else if (isItem(GenerationsItems.SILVER_WING, stack))
            name = GenerationsShrines.LUGIA_SHRINE.getId().toLanguageKey("block");

        return Language.getInstance().getOrDefault(name);
    }

    public static void setImbuedItem(ItemStack stack, ItemStack imbuedStack) {
        stack.getOrCreateTag().put("imbued", imbuedStack.save(new CompoundTag()));
    }

    @Override
    public boolean checkType(PlayerBattleActor player, ItemStack stack, ElementalType t) {
        ElementalType type = typeFromInbued(MelodyFluteItem.getImbuedItem(stack));
        return t.equals(type);
    }

    @Override
    public String tooltipId(ItemStack stack) {
        return this.getDescriptionId() + getWingName(getImbuedItem(stack)) + ".tooltip";
    }

    //    @Override
//    public void appendHoverText(@NotNull ItemStack stack, @org.jetbrains.annotations.Nullable Level level, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag isAdvanced) {
//        ItemStack imbued = getImbuedItem(stack);
//
//        if (imbued.isEmpty()) {
//            tooltipComponents.add(Component.translatable("pixelmon.melody_flute.no_item"));
//        } else {
//            String type = typeFromInbued(imbued).getName();
//            String shrine = shrineFromImbued(imbued);
//            String name = getSpeciesNameFromImbued(imbued);
//
//            tooltipComponents.add(Component.translatable(imbued.getDescriptionId()));
//
//            if (stack.getDamageValue() >= stack.getMaxDamage()) {
//                tooltipComponents.add(Component.translatable("pixelmon.melody_flute.full_imbued1", type));
//                tooltipComponents.add(Component.translatable("pixelmon.melody_flute.full_imbued2", shrine));
//                tooltipComponents.add(Component.translatable("pixelmon.melody_flute.full_imbued3", name));
//            } else {
//                tooltipComponents.add(Component.translatable("pixelmon.melody_flute.not_full_imbued1", stack.getMaxDamage() - stack.getDamageValue(), type));
//                tooltipComponents.add(Component.translatable("pixelmon.melody_flute.not_full_imbued2", shrine));
//                tooltipComponents.add(Component.translatable("pixelmon.melody_flute.not_full_imbued3", name));
//            }
//        }
//    }
}
