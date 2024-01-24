package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.MutableBlockEntityType;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class DoubleStreetLampBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    protected DoubleStreetLampBlock(Properties materialIn) {
        super(materialIn, GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, GenerationsBlockEntityModels.DOUBLE_STREET_LAMP, 2, 4, 0);
    }

    @Override
    public int getBaseX() {
        return 1;
    }

    @Override
    protected boolean validPosition(int x, int y, int z) {
        return switch (y) {
            case 0, 1, 2 -> x != 0 && x != 2;
            default -> true;
        };
    }
}
