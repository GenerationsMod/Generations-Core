package generations.gg.generations.core.generationscore.world.level.block.shrines;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.config.LegendKeys;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.WeatherTrioShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class WeatherTrioShrineBlock extends InteractShrineBlock<WeatherTrioShrineBlockEntity> {
    private static final GenerationsVoxelShapes.DirectionalShapes KYOGRE = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(Shapes.box(0.0625, 0, 0.375, 0.9375, 0.125, 0.75),
                    Shapes.box(0.125, 0.125, 0.4375, 0.875, 0.5625, 0.625), OR));

    private static final GenerationsVoxelShapes.DirectionalShapes GROUDON = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(Shapes.box(0, 0, 0.3125, 1, 0.125, 0.6875),
                    Shapes.box(0.125, 0.125, 0.4375, 0.875, 0.5625, 0.5625), OR));

    private final SpeciesKey speiceskey;
    private final RegistrySupplier<? extends Item> requiredItem;

    public WeatherTrioShrineBlock(BlockBehaviour.Properties properties, ResourceLocation model, SpeciesKey speiceskey, RegistrySupplier<? extends Item> requiredItem) {
        super(properties, GenerationsBlockEntities.WEATHER_TRIO, model, WeatherTrioShrineBlockEntity.class);
        this.speiceskey = speiceskey;
        this.requiredItem = requiredItem;
    }

    @Override
    public boolean isStackValid(ItemStack stack) {
        return stack.is(requiredItem.get())  && stack.getDamageValue() >= stack.getMaxDamage();
    }

    public SpeciesKey getSpecies() {
        return speiceskey;
    }

    @Override
    public String getActiveVariant(boolean active) {
        return active ? "activated" : "deactivated";
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if(speiceskey == LegendKeys.KYORGRE) return KYOGRE.getShape(state);
        else if(speiceskey == LegendKeys.GROUDON) return GROUDON.getShape(state);
        else return Shapes.block();
    }
}
