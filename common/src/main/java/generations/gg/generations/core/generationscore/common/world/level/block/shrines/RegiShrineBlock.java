package generations.gg.generations.core.generationscore.common.world.level.block.shrines;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.tags.GenerationsBlockTags;
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.common.world.item.legends.RegiKeyItem;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.common.world.level.block.UnownBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericShrineBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.ShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

@SuppressWarnings("deprecation")
public class RegiShrineBlock extends ShrineBlock<GenericShrineBlockEntity> {
    private final static GenerationsVoxelShapes.DirectionalShapes SHAPE = GenerationsVoxelShapes.generateDirectionVoxelShape(Shapes.join(Shapes.box(0, 0, 0, 1, 0.3125, 1), Shapes.box(0.125, 0.3125, 0.125, 0.875, 0.8125, 0.875), OR), Direction.NORTH);

    private final SpeciesKey species;
    private final List<String> list;

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE.getShape(state);
    }

    public RegiShrineBlock(Properties materialIn, ResourceLocation model, SpeciesKey speciesKey) {
        super(materialIn, GenerationsBlockEntities.GENERIC_SHRINE, model);
        var cipher = "-" + speciesKey.species().getPath().toUpperCase() + "-";
        list = IntStream.range(0, cipher.length() - 2).boxed().map(a -> getSubSequence(cipher, a)).collect(Collectors.toList());
        this.species = speciesKey;
    }

    private static String getSymbolSequence(Level world, Direction facing, BlockPos pos) {
        return getSymbol(world, pos.relative(facing)) + getSymbol(world, pos) + getSymbol(world, pos.relative(facing.getOpposite()));
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (!level.isClientSide() && player.getItemInHand(hand).getItem() instanceof RegiKeyItem keyItem && keyItem.getSpeciesKey().equals(species) && GenerationsCore.CONFIG.caught.capped(player, species)) {
            List<BlockPos> blockPos = searchForBlock(level, pos, 15, 1, (level1, pos1) -> level1.getBlockState(pos1).getBlock() instanceof UnownBlock);

            if (!blockPos.isEmpty()) {
                List<BlockPos> list = checkForUnownSequence(level, blockPos.get(0));
                if (!list.isEmpty() && level.getBlockEntity(pos) instanceof ShrineBlockEntity shrine/* && isActive(state) == ActivationState.OFF*/) {
//                    toggleActive(level, pos);

//                    ScheduledTask.schedule(() -> {
                        list.forEach(a -> level.destroyBlock(a, false));
                        player.getItemInHand(hand).shrink(1);
                        PokemonUtil.spawn(species.createProperties(70), level, shrine.getBlockPos().above());
//                        toggleActive(level, pos);
//                    }, 200);

//                    shrine.toggleActive();

//                    shrine.toggleActive();
                }
            }

            return InteractionResult.SUCCESS;
        }

        return super.use(state, level, pos, player, hand, hit);
    }

    public List<BlockPos> checkForUnownSequence(Level world, BlockPos pos) {
        BiFunction<Direction, BlockPos, String> getIndex = (facing, blockPos) -> getSymbolSequence(world, facing, blockPos);
        return checkIfInSequence(Direction.Axis.Z, pos, getIndex)
                .orElse(checkIfInSequence(Direction.Axis.X, pos, getIndex)
                        .orElseGet(ArrayList::new));
    }

    public static List<BlockPos> searchForBlock(Level world, BlockPos pos, int radius, int amount, BiPredicate<Level, BlockPos> block) {

        List<BlockPos> states = new ArrayList<>();

        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    BlockPos blockPos = pos.offset(x, y, z);
                    if (block.test(world, blockPos)) {
                        states.add(blockPos);
                        if (states.size() == amount) return states;
                    }
                }
            }
        }

        return states;
    }


    private Optional<List<BlockPos>> checkIfInSequence(Direction.Axis axis, BlockPos pos, BiFunction<Direction, BlockPos, String> getSymbol) {
        Direction facing = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        String sequence = getSymbol.apply(facing, pos);

        if (!list.contains(sequence)) {
            sequence = new StringBuilder(sequence).reverse().toString();

            if (!list.contains(sequence)) {
                return Optional.empty();
            } else {
                facing = facing.getOpposite();
            }
        }

        BlockPos base = pos.relative(facing, list.indexOf(sequence));
        List<BlockPos> positions = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            BlockPos f = base.relative(facing.getOpposite(), i);

            if (list.contains(getSymbol.apply(facing, f))) {
                positions.add(f);
            }
        }

        return !positions.isEmpty() && positions.size() == list.size() ? Optional.of(positions) : Optional.empty();
    }

    private static boolean isPillar(Level level, BlockPos pos) {
        var block = level.getBlockState(pos);

        return block.is(GenerationsBlockTags.REGI_STANDS) && level.getBlockState(pos.above()).getBlock() instanceof UnownBlock;
    }

    private static String getSymbol(Level level, BlockPos pos) {
        return/* isPillar(level, pos) ? */symbolFromState(level.getBlockState(pos/*.above()*/)).orElse("-")/* : "-"*/;
    }

    public String getSubSequence(String cipher, Integer i) {
        return cipher.substring(i, i + 3);
    }
    public static Optional<String> symbolFromState(BlockState state) {
        return Optional.of(state.getBlock()).filter(a -> a instanceof UnownBlock).map(a -> ((UnownBlock) a).getGlyph());
    }
}
