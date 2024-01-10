package generations.gg.generations.core.generationscore.world.level.block.decorations;

import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.world.level.block.entities.CouchBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Function;

public class VariantCouchBlock extends DyeableBlock<CouchBlockEntity, VariantCouchBlock> {
    private final GenerationsVoxelShapes.DirectionalShapes shapes;

    public VariantCouchBlock(Function<DyeColor, DyedBlockItem<CouchBlockEntity, VariantCouchBlock>> function, Properties arg, Variant variant) {
        super(function, GenerationsBlockEntities.COUCH, arg, variant.getModel(), 0, 0, 0);
        this.shapes = variant.getShape();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shapes.getShape(state);
    }

    public static enum Variant {
        OTTOMAN("ottoman", Shapes.block()),
        ARM_LEFT("arm_left", Shapes.block()),
        ARM_RIGHT("arm_right", Shapes.block()),
        CORNER_LEFT("corner_left", Shapes.block()),
        CORNER_RIGHT("corner_right", Shapes.block()),
        MIDDLE("middle", Shapes.block());
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
