package generations.gg.generations.core.generationscore.fabric.mixin;

import generations.gg.generations.core.generationscore.common.datafixer.GenerationsSchemas;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @ModifyArg(
            method = "parse(Lnet/minecraft/core/HolderLookup$Provider;Lnet/minecraft/nbt/Tag;)Ljava/util/Optional;",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/serialization/Codec;parse(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;"
            ),
            index = 1
    )
    //I tried to do this properly via a DFU DataFix but kept getting an error. So I need to directly modify the id at the source. - Waterpicker
    private static Object rename(Object tag) {
        if (tag instanceof CompoundTag compound) {
            var id = compound.getString("id");

            if(GenerationsSchemas.getADDED_TO_1_6_COBBLEMON().containsKey(id)) compound.putString("id", GenerationsSchemas.getADDED_TO_1_6_COBBLEMON().get(id));
        }
        return tag;
    }
}
