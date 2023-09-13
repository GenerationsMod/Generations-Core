package generations.gg.generations.core.generationscore.world.level.block.shrines;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.item.RegiKeyItem;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.ShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("deprecation")
public class RegiShrineBlock extends ShrineBlock<GenericShrineBlockEntity> {
    private static final BiPredicate<Level, BlockPos> IS_PILLAR_PREDICATE = (level, pos) -> level.getBlockState(pos).is(GenerationsBlocks.CASTLE_PILLAR.get()); //TODO convert into tag
    private static final BiFunction<Level, BlockPos, String> FUNCTION = (level, pos) -> IS_PILLAR_PREDICATE.test(level, pos) ? symbolFromState(level.getBlockState(pos.above())).orElse("-") : "-";
    private static final BiFunction<String, Integer, String> SUBSTRING = (cipher, i) -> cipher.substring(i, i + 3);
    private final PokemonProperties species;
    private final List<String> list;

    public RegiShrineBlock(Properties materialIn, ResourceLocation model, String species) {
        super(materialIn, GenerationsBlockEntities.GENERIC_SHRINE, model);
        var cipher = "-" + species.toUpperCase() + "-";
        list = IntStream.range(0, cipher.length() - 2).boxed().map(a -> SUBSTRING.apply(cipher, a)).collect(Collectors.toList());
        this.species = GenerationsUtils.parseProperties(species);
    }

    private static String getSymbolSequence(Level world, Direction facing, BlockPos pos) {
        return FUNCTION.apply(world, pos.relative(facing)) + FUNCTION.apply(world, pos) + FUNCTION.apply(world, pos.relative(facing.getOpposite()));
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide() && player.getItemInHand(hand).getItem() instanceof RegiKeyItem keyItem && keyItem.getSpeciesId().equals(species)) {
            List<BlockPos> blockPos = searchForBlock(level, pos, 15, 1, IS_PILLAR_PREDICATE);

            if (!blockPos.isEmpty()) {
                List<BlockPos> list = checkForUnownSequence(level, blockPos.get(0));
                if (!list.isEmpty() && level.getBlockEntity(pos) instanceof ShrineBlockEntity shrine && !shrine.isActive()) {
                    shrine.toggleActive();
                    list.forEach(a -> level.setBlockAndUpdate(a.above(), Blocks.AIR.defaultBlockState()));
                    player.getItemInHand(hand).shrink(1);
                    PokemonUtil.spawn(species, level, shrine.getBlockPos());
                    shrine.toggleActive();
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
//        return BlockPos.betweenClosedStream(pos.offset(-radius, -radius, -radius), pos.offset(radius, radius, radius)).filter(a -> block.test(world, a)).limit(amount).collect(Collectors.toList());

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


    public static Optional<String> symbolFromState(BlockState state) {
        Block block = state.getBlock();

        Predicate<RegistrySupplier<Block>> predicate = b -> b.get().equals(block);

        if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_A)) return Optional.of("A");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_B)) return Optional.of("B");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_C)) return Optional.of("C");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_D)) return Optional.of("D");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_E)) return Optional.of("E");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_F)) return Optional.of("F");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_G)) return Optional.of("G");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_H)) return Optional.of("H");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_I)) return Optional.of("I");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_J)) return Optional.of("J");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_K)) return Optional.of("K");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_L)) return Optional.of("L");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_M)) return Optional.of("M");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_N)) return Optional.of("N");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_O)) return Optional.of("O");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_P)) return Optional.of("P");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_Q)) return Optional.of("Q");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_R)) return Optional.of("R");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_S)) return Optional.of("S");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_T)) return Optional.of("T");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_U)) return Optional.of("U");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_V)) return Optional.of("V");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_W)) return Optional.of("W");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_X)) return Optional.of("X");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_Y)) return Optional.of("Y");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_Z)) return Optional.of("Z");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_BLANK)) return Optional.of(" ");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_QUESTION_MARK)) return Optional.of("?");
        else if (predicate.test(GenerationsBlocks.UNOWN_BLOCK_EXCLAMATION_MARK)) return Optional.of("!");
        else return Optional.empty();
    }

//    public static BlockState stateFromSymbol(String character) {
//        BlockUnown block;
//        int index;
//
//        if((index = findIndex(((block = ((BlockUnown) PixelmonBlocks.blockUnown)).alphabetInUse), character)) == -1) {
//            if((index = findIndex(((block = ((BlockUnown) PixelmonBlocks.blockUnown2)).alphabetInUse), character)) == -1) {
//                index = getBlankUnownBlockIndex();
//            }
//        }
//
//        return block.getDefaultState().withProperty(alphabetIndex, index);
//    }
}
