package generations.gg.generations.core.generationscore.world.item;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

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
//        if (!level.isClientSide() && usedHand == InteractionHand.MAIN_HAND && getDamage(player.getItemInHand(usedHand)) <= 0) {
//            MelodyFluteContainer.MelodyFluteItemStackHandler handler = new MelodyFluteContainer.MelodyFluteItemStackHandler((ServerPlayer) player, usedHand);
//            NetworkHooks.openScreen((ServerPlayer) player, new SimpleMenuProvider((i, arg, arg2) -> new MelodyFluteContainer(i, arg, handler), Component.translatable("container.melody_flute")), buf -> buf.writeShort(player.getInventory().selected));
//        }

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

//    @Nullable
//    public static ElementType typeFromInbued(ItemStack stack) {
//        if (stack.isEmpty()) return null;
//        if (isItem(PokeModItems.ICY_WING, stack)) return ElementType.ICE;
//        else if (isItem(PokeModItems.PRETTY_FEATHER, stack)) return ElementType.PSYCHIC;
//        else if (isItem(PokeModItems.STATIC_WING, stack)) return ElementType.ELECTRIC;
//        else if (isItem(PokeModItems.BELLIGERENT_WING, stack)) return ElementType.FIGHTING;
//        else if (isItem(PokeModItems.FIERY_WING, stack)) return ElementType.FIRE;
//        else if (isItem(PokeModItems.SINISTER_WING, stack)) return ElementType.DARK;
//        else if (isItem(PokeModItems.RAINBOW_WING, stack) || isItem(PokeModItems.SILVER_WING, stack))
//            return ElementType.FLYING;
//        else return null;
//    }
//
//    public static String getSpeciesNameFromImbued(ItemStack stack) {
//        if (isItem(PokeModItems.ICY_WING, stack)) return getSpeciesNameFromImbued(PokeMod.id("articuno"), "none");
//        else if (isItem(PokeModItems.PRETTY_FEATHER, stack))
//            return getSpeciesNameFromImbued(PokeMod.id("articuno"), "galarian");
//        else if (isItem(PokeModItems.STATIC_WING, stack)) return getSpeciesNameFromImbued(PokeMod.id("zapdos"), "none");
//        else if (isItem(PokeModItems.BELLIGERENT_WING, stack))
//            return getSpeciesNameFromImbued(PokeMod.id("zapdos"), "galarian");
//        else if (isItem(PokeModItems.FIERY_WING, stack)) return getSpeciesNameFromImbued(PokeMod.id("moltres"), "none");
//        else if (isItem(PokeModItems.SINISTER_WING, stack))
//            return getSpeciesNameFromImbued(PokeMod.id("moltres"), "galarian");
//        else if (isItem(PokeModItems.RAINBOW_WING, stack)) return getSpeciesNameFromImbued(PokeMod.id("ho_oh"), "none");
//        else if (isItem(PokeModItems.SILVER_WING, stack)) return getSpeciesNameFromImbued(PokeMod.id("lugia"), "none");
//        else return "";
//    }
//
//    public static String getSpeciesNameFromImbued(ResourceLocation id, String form) {
//        String name = I18n.get(id.getNamespace() + "." + id.getPath() + ".name");
//
//        if (I18n.exists("form." + form)) {
//            name = I18n.get("form." + form, name);
//        }
//
//        return name;
//    }
//
//    public static String shrineFromImbued(ItemStack stack) {
//        String name = "";
//        if (isItem(PokeModItems.ICY_WING, stack) || isItem(PokeModItems.ELEGANT_WING, stack))
//            name = PokeModShrines.FROZEN_SHRINE.getId().toLanguageKey("block");
//        else if (isItem(PokeModItems.STATIC_WING, stack) || isItem(PokeModItems.BELLIGERENT_WING, stack))
//            name = PokeModShrines.STATIC_SHRINE.getId().toLanguageKey("block");
//        else if (isItem(PokeModItems.FIERY_WING, stack) || isItem(PokeModItems.SINISTER_WING, stack))
//            name = PokeModShrines.FIERY_SHRINE.getId().toLanguageKey("block");
//        else if (isItem(PokeModItems.RAINBOW_WING, stack))
//            name = PokeModShrines.CRYSTAL_BELL.getId().toLanguageKey("block");
//        else if (isItem(PokeModItems.SILVER_WING, stack))
//            name = PokeModShrines.LUGIA_SHRINE.getId().toLanguageKey("block");
//
//        return I18n.get(name);
//    }
//
//    public static void setImbuedItem(ItemStack stack, ItemStack imbuedStack) {
//        stack.getOrCreateTag().put("imbued", imbuedStack.serializeNBT());
//    }
//
//
    @Override
    public void onBattleFinish(ServerPlayer player, ItemStack stack/*, Battle<BattleController> battle*/) {
//        var type = typeFromInbued(getImbuedItem(stack));
//        var participant = battle.controller.getParticipant(player).orElseThrow();
//
//        if (type != null && type.anyMatch(((PixelmonData) participant.getParty().fieldPixelmon).getPokemonForm().types()))
//            addDamage(stack, 1);
    }

//    @Override
//    public void appendHoverText(@NotNull ItemStack stack, @org.jetbrains.annotations.Nullable Level level, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag isAdvanced) {
//        ItemStack imbued = getImbuedItem(stack);
//
//        if (imbued.isEmpty()) {
//            tooltipComponents.add(Component.translatable("pixelmon.melody_flute.no_item"));
//        } else {
//            ElementType type = typeFromInbued(imbued);
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
//        }
//
//    @Override
//    public boolean isDamageable(ItemStack stack) {
//        return true;
//    }
}
