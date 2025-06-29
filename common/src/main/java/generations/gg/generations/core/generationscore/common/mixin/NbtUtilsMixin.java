package generations.gg.generations.core.generationscore.common.mixin;

import generations.gg.generations.core.generationscore.common.datafixer.GenerationsSchemas;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NbtUtils.class)
public class NbtUtilsMixin {
    @Inject(
        method = "addCurrentDataVersion(Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/nbt/CompoundTag;",
        at = @At("TAIL")
    )
    private static void addCobblemonDataVersion(
        CompoundTag tag,
        CallbackInfoReturnable<CompoundTag> cir
    ) {
        tag.putInt(GenerationsSchemas.VERSION_KEY, GenerationsSchemas.DATA_VERSION);
    }
}
