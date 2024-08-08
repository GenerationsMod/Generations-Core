package generations.gg.generations.core.generationscore.common.world.level.block.entities;

import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockAnimatedObjectInstance;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockObjectInstance;
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockAnimatedObjectInstance;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockObjectInstance;
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockAnimatedObjectInstance;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockObjectInstance;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericModelBlock;
import gg.generations.rarecandy.renderer.rendering.ObjectInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.joml.Matrix4f;

public abstract class ModelProvidingBlockEntity extends SimpleBlockEntity implements ModelContextProviders.ModelProvider, ModelContextProviders.VariantProvider {
    public ObjectInstance[] objectInstance;
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

    public ObjectInstance generateInstance() {
        return isAnimated() ? new BlockAnimatedObjectInstance(new Matrix4f(), new Matrix4f(), null): new BlockObjectInstance(new Matrix4f(), new Matrix4f(), null);
    }
}
