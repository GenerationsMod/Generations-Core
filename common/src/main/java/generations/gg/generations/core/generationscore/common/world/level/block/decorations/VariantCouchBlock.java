package generations.gg.generations.core.generationscore.common.world.level.block.decorations;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.CouchBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class VariantCouchBlock extends DyeableBlock<CouchBlockEntity, VariantCouchBlock> implements SittableBlock {
    private final GenerationsVoxelShapes.DirectionalShapes shapes;

    public VariantCouchBlock(DyeColor color, Map<DyeColor, RegistrySupplier<VariantCouchBlock>> function, Properties arg, Variant variant) {
        super(color, function, GenerationsBlockEntities.COUCH, arg, variant.getModel(), 0, 0, 0);
        this.shapes = variant.getShape();
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return shapes.getShape(state);
    }

    @Override
    protected InteractionResult serverUse(BlockState state, ServerLevel world, BlockPos pos, ServerPlayer player, InteractionHand handIn, BlockHitResult hit) {
        return SittableBlock.super.use(state, world, pos, player, handIn, hit);
    }

    @Override
    public double getOffset() {
        return 0.4375;
    }


    @Override
    public float getYaw(BlockState state) {
        return state.getValue(FACING).toYRot();
    }

    public enum Variant {
        OTTOMAN("ottoman", Shapes.box(0, 0, 0, 1, 0.4375, 1)),
        ARM_LEFT("arm_left", Shapes.join(Shapes.box(0, 0, 0, 1, 0.4375, 1), Shapes.join(Shapes.box(0, 0, 0.75, 1, 1, 1), Shapes.box(0.75, 0, 0, 1, 0.71875, 1), OR), OR)),
        ARM_RIGHT("arm_right", Shapes.join(Shapes.box(0, 0, 0, 1, 0.4375, 1), Shapes.join(Shapes.box(0, 0, 0.75, 1, 1, 1), Shapes.box(0, 0, 0, 0.25, 0.71875, 1), OR), OR)),
        CORNER_LEFT("corner_left", Shapes.join(Shapes.box(0, 0, 0, 1, 0.4375, 1), Shapes.join(Shapes.box(0, 0, 0.75, 1, 1, 1), Shapes.box(0.75, 0, 0, 1, 1, 1), OR), OR)),
        CORNER_RIGHT("corner_right", Shapes.join(Shapes.box(0, 0, 0, 1, 0.4375, 1), Shapes.join(Shapes.box(0, 0, 0.75, 1, 1, 1), Shapes.box(0, 0, 0, 0.25, 1, 1), OR), OR)),
        MIDDLE("middle", Shapes.join(Shapes.box(0, 0, 0, 1, 0.4375, 1),  Shapes.box(0, 0, 0.75, 1, 1, 1), OR));
        private final GenerationsVoxelShapes.DirectionalShapes shape;
        private final ResourceLocation model;

        Variant(String model, VoxelShape shape) {
            this.shape = GenerationsVoxelShapes.generateDirectionVoxelShape(shape);
            this.model = GenerationsBlockEntityModels.block("decorations/couch/couch_" + model + ".pk");
        }

        public ResourceLocation getModel() {
            return model;
        }

        public GenerationsVoxelShapes.DirectionalShapes getShape() {
            return shape;
        }
    }
}
