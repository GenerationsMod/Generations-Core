package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.entities.*;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class StreetLampBlock extends DyeableBlock<StreetLampBlockEntity, StreetLampBlock> {
    private static final GenerationsVoxelShapes.GenericRotatableShapes SHAPE = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.or(Shapes.box(0.34375, 0, 0.34375, 0.65625, 0.25, 0.65625),
                    Shapes.box(0.40625, 0.25, 0.40625, 0.59375, 0.34375, 0.59375),
                    Shapes.box(0.4375, 0.3125, 0.4375, 0.5625, 2, 0.5625),
                    Shapes.box(0.35, 1.45, 0.35, 0.65, 1.58125, 0.65),
                    Shapes.box(0.375, 1.575, 0.375, 0.625, 1.89375, 0.625),
                    Shapes.box(0.3, 1.80625, 0.3, 0.7, 1.9375, 0.7),
                    Shapes.box(0.35, 1.84375, 0.35, 0.65, 1.975, 0.65)),
                    Direction.NORTH, 1, 2, 1);

    public StreetLampBlock(DyeColor color, Map<DyeColor, RegistrySupplier<StreetLampBlock>> function, Properties properties) {
        super(color, function, GenerationsBlockEntities.STREET_LAMP, properties, GenerationsBlockEntityModels.STREET_LAMP, 0, 1, 0);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.getShape(state);
    }
}
