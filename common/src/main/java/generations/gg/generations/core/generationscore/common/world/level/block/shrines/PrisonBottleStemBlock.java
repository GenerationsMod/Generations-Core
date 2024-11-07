package generations.gg.generations.core.generationscore.common.world.level.block.shrines;

import generations.gg.generations.core.generationscore.common.config.LegendKeys;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsShrines;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.InteractShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class PrisonBottleStemBlock extends InteractShrineBlock<InteractShrineBlockEntity> {
    public static final GenerationsVoxelShapes.GenericRotatableShapes SHAPE = GenerationsVoxelShapes.generateRotationalVoxelShape(Shapes.or(
            Shapes.box(0.5, 1.55, 0.4375, 0.96875, 1.8250000000000002, 0.5625),
            Shapes.box(0.5, 0, 0.19374999999999998, 0.80625, 0.0625, 0.80625),
            Shapes.box(0.5, 0.0625, 0.21875, 0.78125, 0.0875, 0.78125),
            Shapes.box(0.19374999999999998, 0, 0.19374999999999998, 0.5, 0.0625, 0.80625),
            Shapes.box(0.5, 0.0875, 0.26875, 0.73125, 0.11249999999999999, 0.73125),
            Shapes.box(0.21875, 0.0625, 0.21875, 0.5, 0.0875, 0.78125),
            Shapes.box(0.5, 0.1125, 0.39375, 0.60625, 1.075, 0.60625),
            Shapes.box(0.5, 0.175, 0.31875, 0.98125, 0.95, 0.68125),
            Shapes.box(0.5, 0.0875, 0.33125, 0.66875, 0.175, 0.66875),
            Shapes.box(0.26875, 0.0875, 0.26875, 0.5, 0.11249999999999999, 0.73125),
            Shapes.box(0.5, 0.7375, 0.41875, 0.58125, 1.7000000000000002, 0.58125),
            Shapes.box(0.39375, 0.1125, 0.39375, 0.5, 1.075, 0.60625),
            Shapes.box(0.25, 1.55, 0.35624999999999996, 0.51875, 1.8250000000000002, 0.64375),
            Shapes.box(0.375, 1.675, 0.375, 0.5, 2.21875, 0.625),
            Shapes.box(0.03125, 1.55, 0.4375, 0.5, 1.8250000000000002, 0.5625),
            Shapes.box(0.48125, 1.55, 0.35624999999999996, 0.75, 1.8250000000000002, 0.64375),
            Shapes.box(0.5, 1.675, 0.375, 0.625, 2.21875, 0.625),
            Shapes.box(0.41875, 0.7375, 0.41875, 0.5, 1.7000000000000002, 0.58125),
            Shapes.box(0.01874999999999999, 0.175, 0.31875, 0.49999999999999994, 0.95, 0.68125),
            Shapes.box(0.33125, 0.0875, 0.33125, 0.5, 0.175, 0.66875)),
            Direction.SOUTH, 1, 3, 1);

    public static final EnumProperty<PrisonBottleState> STATE = EnumProperty.create("state", PrisonBottleState.class);

    public PrisonBottleStemBlock(Properties materialIn) {
        super(materialIn, GenerationsBlockEntities.INTERACT_SHRINE, GenerationsBlockEntityModels.PRISON_BOTTLE, 0, 2, 0);
        this.registerDefaultState(defaultBlockState().setValue(STATE, PrisonBottleState.EMPTY));
    }

    @Override
    protected boolean interact(Level world, BlockPos pos, BlockState state, ServerPlayer player, InteractionHand hand, boolean activationState) {
        ItemStack heldItem = player.getMainHandItem();

        var baseState = world.getBlockState(pos);
        var baseBlock = this.getClass().cast(baseState.getBlock());
        var base = getBaseBlockPos(pos, state);

        var dir = state.getValue(FACING);

        if (baseState.getValue(STATE) != PrisonBottleState.UNBOUND) {
            if (!player.isCreative()) heldItem.shrink(1);

            baseState = baseState.cycle(STATE);

            for (int x = 0; x < width + 1; x++) {
                for (int y = 0; y < height + 1; y++) {
                    for (int z = 0; z < length + 1; z++) {
                        var adjustX = adjustX(x);
                        var adjustZ = adjustX(x);
                        var blockPos = base.relative(dir.getCounterClockWise(), adjustX).relative(Direction.UP, y).relative(dir, adjustZ);

                        var currentState = world.getBlockState(blockPos);

                        var stateX = baseBlock.getWidthValue(currentState);
                        var stateY = baseBlock.getHeightValue(currentState);
                        var stateZ = baseBlock.getLengthValue(currentState);

                        world.setBlock(blockPos, baseBlock.setSize(baseState.setValue(WATERLOGGED, currentState.getValue(WATERLOGGED)), stateX, stateY, stateZ), 2, 0);
                    }
                }
            }

            if (baseState.getValue(STATE) == PrisonBottleState.UNBOUND) {
                getAssoicatedBlockEntity(world, pos).ifPresent(InteractShrineBlockEntity::triggerCountDown);
            }
        }
        return true;
    }

    @Override
    public boolean revertsAfterActivation() {
        return false;
    }

    @Override
    public int waitToDeactivateTime() {
        return 40;
    }

    @Override
    public void postDeactivation(Level world, BlockPos pos, BlockState state) {
        var baseState = world.getBlockState(pos);
        var base = getBaseBlockPos(pos, state);

        var dir = state.getValue(FACING);

        if (baseState.getValue(STATE) == PrisonBottleState.UNBOUND) {
            for (int x = 0; x < width + 1; x++) {
                for (int y = 0; y < height + 1; y++) {
                    for (int z = 0; z < length + 1; z++) {
                        var adjustX = adjustX(x);
                        var adjustZ = adjustX(x);
                        var blockPos = base.relative(dir.getCounterClockWise(), adjustX).relative(Direction.UP, y).relative(dir, adjustZ);

                        world.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 2, 0);
                        if(adjustX == 0 && y == 0 && adjustZ == 0) {
                            PokemonUtil.spawn(LegendKeys.HOOPA.createPokemon(70), world, base, dir.toYRot()); //TODO: Spawn as unbound.

                            world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(GenerationsShrines.PRISON_BOTTLE.get())));
                        }

                    }
                }
            }
        }
    }

    @Override
    public boolean isStackValid(ItemStack stack) {
        return stack.is(GenerationsItems.HOOPA_RING.get());
    }

    @Override
    public String getVariant(BlockState blockState) {
        return blockState.getValue(STATE).getSerializedName();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(STATE));
    }

    @Override
    public boolean isActivatable() {
        return super.isActivatable();
    }

    public enum PrisonBottleState implements StringRepresentable {
        EMPTY("empty"),
        RING_1("ring_1"),
        RING_2("ring_2"),
        RING_3("ring_3"),
        RING_4("ring_4"),
        RING_5("ring_5"),
        UNBOUND("unbound");

        private final String variant;

        PrisonBottleState(String variant) {

            this.variant = variant;
        }

        @Override
        public String getSerializedName() {
            return variant;
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.getShape(state);
    }

}
