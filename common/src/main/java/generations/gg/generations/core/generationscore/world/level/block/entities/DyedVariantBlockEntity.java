package generations.gg.generations.core.generationscore.world.level.block.entities;

import com.pokemod.pokemod.client.model.ModelContextProviders;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class DyedVariantBlockEntity extends ModelProvidingBlockEntity implements ModelContextProviders.VariantProvider {
    private DyeColor color = DyeColor.RED;

    public DyedVariantBlockEntity(BlockEntityType<? extends DyedVariantBlockEntity> arg, BlockPos arg2, BlockState arg3) {
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
        super.writeNbt(nbt);
        nbt.putString("color", color.getSerializedName());
    }

    @Override
    protected void readNbt(CompoundTag nbt) {
        super.readNbt(nbt);
        color = DyeColor.byName(nbt.getString("color"), DyeColor.RED);
    }

    public DyeColor getColor() {
        return color;
    }
}
