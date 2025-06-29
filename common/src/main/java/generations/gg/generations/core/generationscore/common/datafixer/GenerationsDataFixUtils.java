package generations.gg.generations.core.generationscore.common.datafixer;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import net.minecraft.util.datafix.fixes.ItemStackComponentizationFix;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static net.minecraft.util.datafix.fixes.ItemStackComponentizationFix.fixItemStack;

public class GenerationsDataFixUtils {
    public static Dynamic<?> getDiscHolder(Dynamic<?> holder) {
        var list = holder.asStream().map(dynamic -> Pair.of(dynamic.get("Slot").asInt(-1), dynamic.remove("slot")))
                .filter(pair -> pair.getFirst() <= -1).map(a -> a.getSecond()).map(a -> a.renameField("Count", "count"));

        return holder.createList(list);
    }

    public static Dynamic<?> fixStack(Dynamic<?> dynamic) {
        Optional<? extends Dynamic<?>> optional = ItemStackComponentizationFix.ItemStackData.read(dynamic).map((itemStackData) -> {
            fixItemStack(itemStackData, itemStackData.tag);
            return itemStackData.write();
        });
        return DataFixUtils.orElse(optional, dynamic);
    }

}
