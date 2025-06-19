package generations.gg.generations.core.generationscore.common.mixin.datafix;

import com.cobblemon.mod.common.datafixer.CobblemonSchemas;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.chunk.storage.ChunkStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static generations.gg.generations.core.generationscore.common.datafixer.GenerationsSchemas.VERSION_KEY;

@Mixin(ChunkStorage.class)
public class ChunkStorageMixin {
    //Modifies the NBT returned by the vanilla upgradeChunkTag to also incorporate cobblemon fixup stuff
    @ModifyExpressionValue(
        method = "upgradeChunkTag(Lnet/minecraft/resources/ResourceKey;Ljava/util/function/Supplier;Lnet/minecraft/nbt/CompoundTag;Ljava/util/Optional;)Lnet/minecraft/nbt/CompoundTag;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/datafix/DataFixTypes;updateToCurrentVersion(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/nbt/CompoundTag;I)Lnet/minecraft/nbt/CompoundTag;"
        )
    )
    public CompoundTag cobblemon$doChunkFix(
        CompoundTag vanillaFixed
    ) {
        int curVersion = vanillaFixed.contains(VERSION_KEY) ? vanillaFixed.getInt(VERSION_KEY) : 0;
        return DataFixTypes.CHUNK.update(CobblemonSchemas.getDATA_FIXER(), vanillaFixed, curVersion, CobblemonSchemas.DATA_VERSION);
    }
}
