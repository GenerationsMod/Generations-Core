package generations.gg.generations.core.generationscore.world.level.block.shrines;

import com.google.common.collect.ImmutableMap;
import generations.gg.generations.core.generationscore.config.LegendKeys;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsShrines;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.LunarShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.ShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ConduitBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ConduitBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class LunarShrineBlock extends ShrineBlock<LunarShrineBlockEntity> {
    private static final GenerationsVoxelShapes.DirectionalShapes LIGHT = GenerationsVoxelShapes.generateDirectionVoxelShape(Shapes.box(0, 0, 0.1875, 1, 0.4375, 0.8125));
    private static final GenerationsVoxelShapes.DirectionalShapes DARK = GenerationsVoxelShapes.generateDirectionVoxelShape(Shapes.box(0, 0, 0, 1, 0.5625, 1));

    public static final BooleanProperty IS_LIGHT = BooleanProperty.create("is_light");

    public LunarShrineBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.LUNAR_SHRINE, null);
    }

    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(IS_LIGHT);
    }

    @Override
    protected BlockState createDefaultState() {
        return super.createDefaultState().setValue(IS_LIGHT, false);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return ConduitBlock.createTickerHelper(blockEntityType, GenerationsBlockEntities.LUNAR_SHRINE.get(), level.isClientSide ? (level1, blockPos, blockState, blockEntity) -> {} : (level12, pos, blockState, blockEntity) -> {
            var state1 = level12.getBlockState(pos).setValue(IS_LIGHT, level12.getMaxLocalRawBrightness(pos) >= 10);
            level12.setBlockAndUpdate(pos, state1);
        });
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (!level.isClientSide()) {
            var key = getSpecies(state);

            var block = (key == LegendKeys.CRESSELIA ? GenerationsShrines.LIGHT_CRYSTAL : GenerationsShrines.DARK_CRYSTAL).get();

            var list = RegiShrineBlock.searchForBlock(level, pos, 15, 5, (level1, blockPos) -> level1.getBlockState(blockPos).is(block));

            if (!list.isEmpty() && level.getBlockEntity(pos) instanceof ShrineBlockEntity shrine && !shrine.isActive()) {
                if(list.size() == 5) {
                    shrine.toggleActive();
                    list.forEach(a -> level.destroyBlock(a, false));
                    player.getItemInHand(hand).shrink(1);
                    PokemonUtil.spawn(key.createProperties(70), level, shrine.getBlockPos());
                    shrine.toggleActive();

                    return InteractionResult.SUCCESS;
                }
            }
        }

        return super.use(state, level, pos, player, hand, hit);
    }

    public SpeciesKey getSpecies(BlockState state) {
        return state.getValue(IS_LIGHT) ? LegendKeys.CRESSELIA : LegendKeys.DARKRAI;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (state.getValue(IS_LIGHT) ? LIGHT : DARK).getShape(state);
    }
}
