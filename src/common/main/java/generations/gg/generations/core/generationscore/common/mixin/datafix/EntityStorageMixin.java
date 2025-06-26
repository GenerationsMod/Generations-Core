package generations.gg.generations.core.generationscore.common.mixin.datafix;

import com.cobblemon.mod.common.datafixer.CobblemonSchemas;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import generations.gg.generations.core.generationscore.common.datafixer.GenerationsSchemas;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.chunk.storage.EntityStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static generations.gg.generations.core.generationscore.common.datafixer.GenerationsSchemas.VERSION_KEY;

@Mixin(EntityStorage.class)
public class EntityStorageMixin {
    //Modifies the NBT returned by the vanilla upgradeChunkTag to also incorporate cobblemon fixup stuff
    @ModifyExpressionValue(
        method = "method_31731(Lnet/minecraft/world/level/ChunkPos;Ljava/util/Optional;)Lnet/minecraft/world/level/entity/ChunkEntities;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/chunk/storage/SimpleRegionStorage;upgradeChunkTag(Lnet/minecraft/nbt/CompoundTag;I)Lnet/minecraft/nbt/CompoundTag;"
        )
    )
    public CompoundTag generations$doEntityFix(
        CompoundTag vanillaFixed
    ) {
        int curVersion = vanillaFixed.contains(VERSION_KEY) ? vanillaFixed.getInt(VERSION_KEY) : 0;
        CompoundTag newTag =  DataFixTypes.ENTITY_CHUNK.update(GenerationsSchemas.getDATA_FIXER(), vanillaFixed, curVersion, GenerationsSchemas.DATA_VERSION);
        newTag.put(VERSION_KEY, IntTag.valueOf(GenerationsSchemas.DATA_VERSION));
        return newTag;
    }
}
