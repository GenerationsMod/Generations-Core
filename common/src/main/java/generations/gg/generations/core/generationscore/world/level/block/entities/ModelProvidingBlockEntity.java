package generations.gg.generations.core.generationscore.world.level.block.entities;

import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.client.render.rarecandy.BlockObjectInstance;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericModelBlock;
import gg.generations.rarecandy.arceus.model.pk.MultiRenderObject;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.joml.Matrix4f;

public abstract class ModelProvidingBlockEntity extends SimpleBlockEntity implements ModelContextProviders.ModelProvider, ModelContextProviders.VariantProvider {
    public BlockObjectInstance[] objectInstance;
    private AABB boundingBox;

    public ModelProvidingBlockEntity(BlockEntityType<? extends ModelProvidingBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public ResourceLocation getModel() {
        return (getBlockState().getBlock() instanceof GenericModelBlock<?> block ? block.getModel() : GenerationsBlockEntityModels.DEFAULT);
    }

    public AABB getRenderBoundingBox() {
        if(boundingBox == null) boundingBox = getBlockState().getBlock() instanceof GenericModelBlock<?> block ? block.computeRenderBoundingBox(getLevel(), getBlockPos(), getBlockState()) : defaultAABB(getBlockPos());
        return boundingBox;
    }

    @Override
    public String getVariant() {
        return getBlockState().getBlock() instanceof ModelContextProviders.VariantProvider provider ? provider.getVariant() : null;
    }

    public static AABB defaultAABB(BlockPos pos) {
        return new AABB(pos, pos.offset(1,1,1));
    }

    public BlockObjectInstance generateInstance(MultiRenderObject<?> object) {
        return new BlockObjectInstance(object, new Matrix4f(), null);
    }
}
