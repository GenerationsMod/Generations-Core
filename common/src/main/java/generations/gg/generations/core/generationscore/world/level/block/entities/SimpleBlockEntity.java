package generations.gg.generations.core.generationscore.world.level.block.entities;

import com.pokemod.pokemod.api.data.Syncable;
import com.pokemod.pokemod.client.model.ModelContextProviders;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class SimpleBlockEntity extends BlockEntity implements Syncable, ModelContextProviders.AngleProvider {

    public SimpleBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    protected void readNbt(CompoundTag nbt) {}
    protected void writeNbt(CompoundTag nbt) {}

    public void sync() {
        setChanged();
        if(getLevel() != null)
            getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        readNbt(nbt);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);
        writeNbt(nbt);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public float getAngle() {
        if (getBlockState().hasProperty(HorizontalDirectionalBlock.FACING))
            return getBlockState().getValue(HorizontalDirectionalBlock.FACING).toYRot();
        return 0f;
    }
}