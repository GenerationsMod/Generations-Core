package generations.gg.generations.core.generationscore.world.level.block.entities;

import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3f;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class DyedVariantBlockEntity<T extends DyedVariantBlockEntity<?>> extends ModelProvidingBlockEntity {
    public DyedVariantBlockEntity(MutableBlockEntityType<T> arg, BlockPos arg2, BlockState arg3) {
        super(arg, arg2, arg3);
    }

    @Override
    public String getVariant() {
        return getColor().getSerializedName();
    }

    public DyeColor getColor() {
        return ((DyeableBlock<?, ?>) getBlockState().getBlock()).getColor();
    }

    public static final Map<DyeColor, Vector3f> COLOR_MAP = Stream.of(DyeColor.values()).collect(Collectors.toMap(a -> a, dyeColor -> new Vector3f(dyeColor.getTextureDiffuseColors()[0], dyeColor.getTextureDiffuseColors()[1], dyeColor.getTextureDiffuseColors()[2])));
}
