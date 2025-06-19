package generations.gg.generations.core.generationscore.common.mixin.datafix;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.serialization.Dynamic;
import net.minecraft.util.datafix.fixes.ItemStackComponentizationFix;

import java.util.Comparator;
import java.util.Optional;

import static net.minecraft.util.datafix.fixes.ItemStackComponentizationFix.fixItemStack;

class GenerationsDataFixUtils {
    public static Dynamic<?> getDiscHolder(Dynamic<?> holder) {
        return holder.createList(holder.asStream().filter(d -> d.get("Slot").asInt(-1) > -1).sorted(Comparator.comparingInt(value -> value.get("Slot").asInt(-1))).map(dynamic -> dynamic.remove("Slot")).map(GenerationsDataFixUtils::fixStack).map(a -> a.renameField("Count", "amount")));
    }

    public static Dynamic<?> fixStack(Dynamic<?> dynamic) {
        Optional<? extends Dynamic<?>> optional = ItemStackComponentizationFix.ItemStackData.read(dynamic).map((itemStackData) -> {
            fixItemStack(itemStackData, itemStackData.tag);
            return itemStackData.write();
        });
        return DataFixUtils.orElse(optional, dynamic);
    }

}
