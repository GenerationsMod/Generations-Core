package generations.gg.generations.core.generationscore.world.level.block.shrines;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.entities.MutableBlockEntityType;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.ShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;

import static generations.gg.generations.core.generationscore.world.level.block.shrines.ShrineBlock.ActivationState.*;

public class ShrineBlock<T extends ShrineBlockEntity> extends GenericRotatableModelBlock<T> {
    private static final EnumProperty<ActivationState> ACTIVE = EnumProperty.create("active", ActivationState.class, ON, ActivationState.OFF);
    protected ShrineBlock(Properties materialIn, RegistrySupplier<MutableBlockEntityType<T>> blockEntityFunction, ResourceLocation model, int width, int height, int length) {
        super(materialIn, blockEntityFunction, model, width, height, length);
        reassignStateDefinition();
        this.registerDefaultState(createDefaultState());
    }

    protected ShrineBlock(Properties materialIn, RegistrySupplier<MutableBlockEntityType<T>> blockEntityFunction, ResourceLocation model) {
        super(materialIn, blockEntityFunction, model);
    }

    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        if(isActivatable()) builder.add(ACTIVE);
    }

    public boolean isActivatable() {
        return false;
    }

    @Override
    protected BlockState createDefaultState() {
        var state = super.createDefaultState();
        if(isActivatable()) state.setValue(ACTIVE, OFF);
        return state;
    }

    public static ActivationState isActive(BlockState state) {
        return state.getBlock() instanceof ShrineBlock<?> shrine ? shrine.isActivatable() ? state.getValue(ACTIVE) : NONE : NONE;
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        var state = super.getStateForPlacement(context);
        if(state != null && isActivatable()) state = state.setValue(ACTIVE, OFF);
        return state;
    }

    public String getVariant(BlockState blockState) {
        var isActivate = isActive(blockState);
        return isActivate != NONE ? isActivate == ON ? "activated" : "deactivated" : null;
    }

    public static void toggleActive(Level level, BlockPos pos) {
        var state = level.getBlockState(pos);

        if(state.getBlock() instanceof ShrineBlock<?> shrine && shrine.isActivatable()) {
            var isActive = isActive(state);

            if (isActive != NONE) {
                isActive = isActive == ON ? OFF : ON;


                pos = shrine.getBaseBlockPos(pos, state);
                var facing = state.getValue(FACING);

                for (int x = 0; x <= shrine.width; x++) {
                    for (int z = 0; z <= shrine.length; z++) {
                        for (int y = 0; y <= shrine.height; y++) {
                            if (!shrine.validPosition(x, y, z)) continue;


                            var blockPos = shrine.adjustBlockPos(pos, facing, x, y, z, true);

                            var stateCurrent = level.getBlockState(blockPos);

                            level.setBlockAndUpdate(blockPos, stateCurrent.setValue(ACTIVE, isActive));
                        }
                    }
                }
            }
        }
    }

    public static enum ActivationState implements StringRepresentable {
        NONE, ON, OFF;

        @Override
        public String getSerializedName() {
            return name().toLowerCase();
        }
    }
}
