package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies.getByIdentifier
import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import dev.architectury.registry.registries.RegistrySupplier
import earth.terrarium.common_storage_lib.resources.ResourceStack
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import generations.gg.generations.core.generationscore.common.GenerationsStorage.IMBUED
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.world.container.MelodyFluteContainer
import generations.gg.generations.core.generationscore.common.world.item.legends.ElementalPostBattleUpdateItem
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsShrines
import net.minecraft.locale.Language
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.MenuConstructor
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.Level

class MelodyFluteItem(properties: Properties) : ElementalPostBattleUpdateItem(properties.durability(MAX_DAMAGE)) {
    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!level.isClientSide() && usedHand == InteractionHand.MAIN_HAND && player.getItemInHand(usedHand).damageValue <= 0) {
            player.openMenu(SimpleMenuProvider(::MelodyFluteContainer, Component.translatable("container.melody_flute")))
        }

        return super.use(level, player, usedHand)
    }

    override fun useOn(context: UseOnContext): InteractionResult {
        return super.useOn(context)
    }

    override fun isFoil(stack: ItemStack): Boolean {
        return !getImbuedItem(stack).isEmpty && stack.damageValue >= stack.maxDamage
    }

    override fun checkType(player: PlayerBattleActor, stack: ItemStack, t: ElementalType): Boolean {
        val type = typeFromInbued(getImbuedItem(stack))
        return t == type
    }

    override fun tooltipId(stack: ItemStack?): String {
        return this.descriptionId + getWingName(getImbuedItem(stack)) + ".tooltip"
    } //    @Override
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

    companion object {
        var MAX_DAMAGE: Int = 250

        fun isFlute(stack: ItemStack): Boolean {
            return isItem(GenerationsItems.MELODY_FLUTE, stack)
        }

        fun <T : ItemLike?> isItem(`object`: RegistrySupplier<T>, stack: ItemStack): Boolean {
            return `object`.toOptional().map { obj: T -> obj!!.asItem() }.filter { item: Item? ->
                stack.`is`(
                    item
                )
            }.isPresent
        }

        @JvmStatic
        fun getImbuedItem(stack: ItemStack?): ItemStack {
            if (stack != null) {
                val lists = stack.get(IMBUED.componentType())?.stacks()

                if (!lists.isNullOrEmpty()) {
                    return lists[0].resource().cachedStack
                }
            }

            return ItemStack.EMPTY
        }

        fun typeFromInbued(stack: ItemStack): ElementalType? {
            return stack.item.instanceOrNull<WingItem>()?.type
        }

        fun getSpeciesNameFromImbued(stack: ItemStack): String {
            return stack.item.instanceOrNull<WingItem>()?.key?.let(::getSpeciesNameFromImbued) ?: ""
        }


        fun getWingName(stack: ItemStack): String {
            return stack.item.instanceOrNull<WingItem>()?.let { ".$it" } ?: ""
        }

        fun getSpeciesNameFromImbued(key: SpeciesKey): String {
            return (if (key.aspects!!.contains("galarian")) "Galarian " else "") + getByIdentifier(
                key.species
            )!!.name
        }

        fun shrineFromImbued(stack: ItemStack): String {
            var name = ""
            if (isItem(GenerationsItems.ICY_WING, stack) || isItem(GenerationsItems.ELEGANT_WING, stack)) name =
                GenerationsShrines.FROZEN_SHRINE.id.toLanguageKey("block")
            else if (isItem(GenerationsItems.STATIC_WING, stack) || isItem(
                    GenerationsItems.BELLIGERENT_WING,
                    stack
                )
            ) name = GenerationsShrines.STATIC_SHRINE.id.toLanguageKey("block")
            else if (isItem(GenerationsItems.FIERY_WING, stack) || isItem(GenerationsItems.SINISTER_WING, stack)) name =
                GenerationsShrines.FIERY_SHRINE.id.toLanguageKey("block")
            else if (isItem(GenerationsItems.RAINBOW_WING, stack)) name =
                GenerationsShrines.HO_OH_SHRINE.id.toLanguageKey("block")
            else if (isItem(GenerationsItems.SILVER_WING, stack)) name =
                GenerationsShrines.LUGIA_SHRINE.id.toLanguageKey("block")

            return Language.getInstance().getOrDefault(name)
        }

        fun setImbuedItem(stack: ItemStack, imbuedStack: ItemStack) {
            val storage = stack.get(IMBUED.componentType())
            storage!!.stacks()[0] =
                ResourceStack(
                    ItemResource.of(imbuedStack),
                    1
                )
            stack.set(IMBUED.componentType(), storage)
        }
    }
}
