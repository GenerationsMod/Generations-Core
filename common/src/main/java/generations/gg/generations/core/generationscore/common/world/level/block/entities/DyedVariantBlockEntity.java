package generations.gg.generations.core.generationscore.common.world.level.block.entities;

import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.DyeableBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.DyeableBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3f;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class DyedVariantBlockEntity<T extends DyedVariantBlockEntity<?>> extends ModelProvidingBlockEntity implements ModelContextProviders.TintProvider {
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

    @Override
    public Vector3f getTint() {
        return COLOR_MAP.getOrDefault(getColor(), null);
    }

    public static final Map<DyeColor, Vector3f> COLOR_MAP = Stream.of(DyeColor.values()).collect(Collectors.toMap(a -> a, dyeColor -> new Vector3f(dyeColor.getTextureDiffuseColors()[0], dyeColor.getTextureDiffuseColors()[1], dyeColor.getTextureDiffuseColors()[2])));
}
