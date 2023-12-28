package generations.gg.generations.core.generationscore.fabric.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.stats.RecipeBookSettings;
import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(RecipeBookSettings.class)
public interface RecipeBookSettingsAccessor {
    @Accessor("TAG_FIELDS")
    static Map<RecipeBookType, Pair<String, String>> getTagFields() {
        throw new AssertionError("Untransformed @Accessor");
    }

    @Accessor("TAG_FIELDS")
    @Mutable
    static void setTagFields(Map<RecipeBookType, Pair<String, String>> tagFields) {
        throw new AssertionError("Untransformed @Accessor");
    }

}