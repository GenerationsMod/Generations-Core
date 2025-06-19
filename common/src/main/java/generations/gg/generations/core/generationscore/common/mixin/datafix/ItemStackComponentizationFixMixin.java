package generations.gg.generations.core.generationscore.common.mixin.datafix;

import com.cobblemon.mod.common.api.berry.Flavor;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import generations.gg.generations.core.generationscore.common.datafixer.GenerationsDataFixUtils;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryTasteRating;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryType;
import net.minecraft.util.datafix.fixes.ItemStackComponentizationFix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Mixin({ItemStackComponentizationFix.class})
public class ItemStackComponentizationFixMixin {

    @Inject(
            method = {"fixItemStack(Lnet/minecraft/util/datafix/fixes/ItemStackComponentizationFix$ItemStackData;Lcom/mojang/serialization/Dynamic;)V"},
            at = {@At("TAIL")})
    private static void fixItemStackAddition(ItemStackComponentizationFix.ItemStackData itemStackData, Dynamic<?> dynamic, CallbackInfo ci) {
        if (itemStackData.item.startsWith("generationscore:pokemail_")) {
            fixMail(itemStackData, dynamic);
        }

        if (itemStackData.is("generations_core:ruby_rod")) {
            itemStackData.moveTagToComponent("fished_shards", "generations_core:fished_shards");
        }

        if (itemStackData.is("generations_core:melody_flute")) {
            itemStackData.removeTag("imbued")
                    .map(GenerationsDataFixUtils::fixStack)
                    .map(a -> a.renameField("Count", "amount"))
                    .map(a -> a.createList(a.asStream()))
                    .ifSuccess(dynamic1 -> {
                        itemStackData.setComponent("generations_core:imbued", dynamic1);
                    });
        }

        if (itemStackData.item.startsWith("generations_core:") && itemStackData.item.endsWith("walkmon")) {
            System.out.println("Walkmon detected! " + itemStackData.item + " " + itemStackData.tag);

            itemStackData.removeTag("DiscHolder").result().ifPresent(holder -> {
                holder.get("Discs").map(GenerationsDataFixUtils::getDiscHolder).result().ifPresent(new Consumer<Dynamic<?>>() {
                    @Override
                    public void accept(Dynamic<?> dynamic) {
                        itemStackData.setComponent("generations_core:inventory", dynamic);
                        System.out.println("Discs: " + dynamic);
                    }
                });
                var newHolder = holder.emptyMap();
                newHolder = newHolder.set("playing", holder.createBoolean(holder.get("Playing").asBoolean(false)));
                newHolder = newHolder.set("current_slot", holder.createInt(holder.get("CurrentSlot").asInt(0)));
                newHolder = newHolder.set("time_until_next_song", holder.createInt(holder.get("TimeUntilNextSong").asInt(0)));
                newHolder = newHolder.set("title", holder.createString("Walkmon"));
                itemStackData.setComponent("generations_core:walkmon_data", newHolder);
                System.out.println("Holder: " + newHolder);
            });
        }

        if (itemStackData.is(Set.of("generations_core:wonder_egg", "generations_core_phione_egg"))) {
            itemStackData.moveTagToComponent("Distance", "generations_core:distance");
        }

        if (itemStackData.is(Set.of("generations_core:time_capsule", "generations_core:dna_splicer", "generations_core:reins_of_unity"))) {
            itemStackData.moveTagToComponent("pokemon", "generations_core:embedded_pokemon");
        }

        if (itemStackData.is("generations_core:tm")) {
            fixTM(itemStackData, dynamic);
        }

        if (itemStackData.is("generations_core:red_chain")) {
            itemStackData.moveTagToComponent("uses", "generations_core:uses");
            itemStackData.moveTagToComponent("enchanted", "generations_core:enchanted");
        }

        if (itemStackData.is("generations_core:meteorite")) {
            itemStackData.moveTagToComponent("used", "generations_core:used");
            itemStackData.moveTagToComponent("enchanted", "generations_core:enchanted");
        }

        if (itemStackData.is("generations_core:curry")) {
            fixCurry(itemStackData, dynamic);
        }
    }

    private static void fixCurry(ItemStackComponentizationFix.ItemStackData itemStackData, Dynamic<?> dynamic) {
        var curryData = dynamic.emptyMap();
        var flavorInt = itemStackData.removeTag("flavor").asInt(-1);
        if(flavorInt > -1) curryData = curryData.set("flavor", dynamic.createString(Flavor.values()[flavorInt].name().toLowerCase()));
        curryData = curryData.set("type", dynamic.createString(CurryType.getEntries().get(itemStackData.removeTag("type").asInt(0)).getSerializedName()));
        curryData = curryData.set("experience", dynamic.createInt(itemStackData.removeTag("experience").asInt(0)));
        curryData = curryData.set("health_percentage", dynamic.createDouble(itemStackData.removeTag("healthPercentage").asDouble(0.0)));
        curryData = curryData.set("heal_status", dynamic.createBoolean(itemStackData.removeTag("canHealStatus").asBoolean(false)));
        curryData = curryData.set("restore_pp", dynamic.createBoolean(itemStackData.removeTag("canRestorePP").asBoolean(false)));
        curryData = curryData.set("friendship", dynamic.createInt(itemStackData.removeTag("friendship").asInt(0)));
        curryData = curryData.set("rating", dynamic.createString(CurryTasteRating.getEntries().get(itemStackData.removeTag("rating").asInt(0)).getSerializedName()));
        itemStackData.setComponent("generations_core:curry_data", curryData);
    }

    private static void fixTM(ItemStackComponentizationFix.ItemStackData itemStackData, Dynamic<?> dynamic) {
        var tmDetails = dynamic.emptyMap();
        tmDetails = tmDetails.set("move", dynamic.createString(itemStackData.removeTag("move").asString("")));
        tmDetails = tmDetails.set("number", dynamic.createInt(itemStackData.removeTag("number").asInt(0)));
        itemStackData.setComponent("generations_core:tm_details", tmDetails);
    }

    private static void fixMail(ItemStackComponentizationFix.ItemStackData itemStackData, Dynamic<?> dynamic) {
        var mailData = dynamic.emptyMap();

        mailData = mailData.set("contents", dynamic.createString(itemStackData.removeTag("contents").asString("")));
        mailData = mailData.set("author", dynamic.createString(itemStackData.removeTag("author").asString("")));
        mailData = mailData.set("title", dynamic.createString(""));

        itemStackData.setComponent("generations_core:mail_data", mailData);
    }
}