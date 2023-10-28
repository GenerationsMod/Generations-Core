package generations.gg.generations.core.generationscore.client.fabric;

import com.chocohead.mm.api.ClassTinkerers;
import com.mojang.datafixers.util.Pair;
import generations.gg.generations.core.generationscore.fabric.mixin.RecipeBookSettingsAccessor;
import net.fabricmc.fabric.impl.content.registry.util.ImmutableCollectionUtils;
import net.minecraft.world.inventory.RecipeBookType;

public class ModRecipeBookTypesImpl {
    public static RecipeBookType getRecipeBookType(String name) {
        var type = ClassTinkerers.getEnum(RecipeBookType.class, name);
        ImmutableCollectionUtils.getAsMutableMap(RecipeBookSettingsAccessor::getTagFields, RecipeBookSettingsAccessor::setTagFields).put(type, Pair.of("isRksGui", "isRksFilteringCraftable"));
        return type;
    }
}
