package generations.gg.generations.core.generationscore.world.level.block.entities;

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
    private DyeColor color = DyeColor.RED;

    public DyedVariantBlockEntity(MutableBlockEntityType<T> arg, BlockPos arg2, BlockState arg3) {
        super(arg, arg2, arg3);
    }

    @Override
    public String getVariant() {
        return color.getSerializedName();
    }

    public void setColor(DyeColor color) {
        this.color = color;
        sync();
    }

    @Override
    protected void writeNbt(CompoundTag nbt) {
        nbt.putString("color", color.getSerializedName());
    }

    @Override
    protected void readNbt(CompoundTag nbt) {
        color = DyeColor.byName(nbt.getString("color"), DyeColor.RED);
    }

    public DyeColor getColor() {
        return color;
    }

    public static final Map<DyeColor, Vector3f> COLOR_MAP = Stream.of(DyeColor.values()).collect(Collectors.toMap(a -> a, dyeColor -> new Vector3f(dyeColor.getTextureDiffuseColors()[0], dyeColor.getTextureDiffuseColors()[1], dyeColor.getTextureDiffuseColors()[2])));
}
