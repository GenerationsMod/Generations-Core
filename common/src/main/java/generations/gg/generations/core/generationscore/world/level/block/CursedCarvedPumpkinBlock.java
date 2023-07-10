package generations.gg.generations.core.generationscore.world.level.block;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * @author JT122406
 * @see CarvedPumpkinBlock
 * Custom Cursed Carved Pumpkin Block
 */
public class CursedCarvedPumpkinBlock extends CarvedPumpkinBlock {

    public static final DirectionProperty FACING;
    @Nullable
    private BlockPattern snowGolemBase;
    @Nullable
    private BlockPattern snowGolemFull;
    @Nullable
    private BlockPattern ironGolemBase;
    @Nullable
    private BlockPattern ironGolemFull;
    private static final Predicate<BlockState> CURSED_PUMPKINS_PREDICATE;


    public CursedCarvedPumpkinBlock(Properties arg) {
        super(arg);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public void onPlace(BlockState state, @NotNull Level level, @NotNull BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.is(state.getBlock())) this.trySpawnGolem(level, pos);

    }

    @Override
    public boolean canSpawnGolem(@NotNull LevelReader level, @NotNull BlockPos pos) {
        return this.getOrCreateSnowGolemBase().find(level, pos) != null || this.getOrCreateIronGolemBase().find(level, pos) != null;
    }

    private void trySpawnGolem(Level level, BlockPos pos) {
        BlockPattern.BlockPatternMatch blockPatternMatch = this.getOrCreateSnowGolemFull().find(level, pos);
        int i;
        Iterator<ServerPlayer> var6;
        int j;
        if (blockPatternMatch != null) {
            for(i = 0; i < this.getOrCreateSnowGolemFull().getHeight(); ++i) {
                BlockInWorld blockInWorld = blockPatternMatch.getBlock(0, i, 0);
                level.setBlock(blockInWorld.getPos(), Blocks.AIR.defaultBlockState(), 2);
                level.levelEvent(2001, blockInWorld.getPos(), Block.getId(blockInWorld.getState()));
            }

            SnowGolem snowGolem = EntityType.SNOW_GOLEM.create(level);
            BlockPos blockPos = blockPatternMatch.getBlock(0, 2, 0).getPos();
            assert snowGolem != null;
            snowGolem.moveTo((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.05, (double)blockPos.getZ() + 0.5, 0.0F, 0.0F);
            level.addFreshEntity(snowGolem);
            var6 = level.getEntitiesOfClass(ServerPlayer.class, snowGolem.getBoundingBox().inflate(5.0)).iterator();

            while(var6.hasNext())
                CriteriaTriggers.SUMMONED_ENTITY.trigger(var6.next(), snowGolem);


            for(j = 0; j < this.getOrCreateSnowGolemFull().getHeight(); ++j)
                level.blockUpdated(blockPatternMatch.getBlock(0, j, 0).getPos(), Blocks.AIR);

        } else {
            blockPatternMatch = this.getOrCreateIronGolemFull().find(level, pos);
            if (blockPatternMatch != null) {
                for(i = 0; i < this.getOrCreateIronGolemFull().getWidth(); ++i)
                    for(int k = 0; k < this.getOrCreateIronGolemFull().getHeight(); ++k) {
                        BlockInWorld blockInWorld3 = blockPatternMatch.getBlock(i, k, 0);
                        level.setBlock(blockInWorld3.getPos(), Blocks.AIR.defaultBlockState(), 2);
                        level.levelEvent(2001, blockInWorld3.getPos(), Block.getId(blockInWorld3.getState()));
                    }

                BlockPos blockPos2 = blockPatternMatch.getBlock(1, 2, 0).getPos();
                IronGolem ironGolem = EntityType.IRON_GOLEM.create(level);
                assert ironGolem != null;
                ironGolem.setPlayerCreated(true);
                ironGolem.moveTo((double)blockPos2.getX() + 0.5, (double)blockPos2.getY() + 0.05, (double)blockPos2.getZ() + 0.5, 0.0F, 0.0F);
                level.addFreshEntity(ironGolem);
                var6 = level.getEntitiesOfClass(ServerPlayer.class, ironGolem.getBoundingBox().inflate(5.0)).iterator();

                while(var6.hasNext())
                    CriteriaTriggers.SUMMONED_ENTITY.trigger(var6.next(), ironGolem);

                for(j = 0; j < this.getOrCreateIronGolemFull().getWidth(); ++j)
                    for(int l = 0; l < this.getOrCreateIronGolemFull().getHeight(); ++l)
                        level.blockUpdated(blockPatternMatch.getBlock(j, l, 0).getPos(), Blocks.AIR);
            }
        }

    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    private BlockPattern getOrCreateSnowGolemBase() {
        if (this.snowGolemBase == null)
            this.snowGolemBase = BlockPatternBuilder.start().aisle(" ", "#", "#").where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK))).build();

        return this.snowGolemBase;
    }

    private BlockPattern getOrCreateSnowGolemFull() {
        if (this.snowGolemFull == null)
            this.snowGolemFull = BlockPatternBuilder.start().aisle("^", "#", "#").where('^', BlockInWorld.hasState(CURSED_PUMPKINS_PREDICATE)).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK))).build();

        return this.snowGolemFull;
    }


    private BlockPattern getOrCreateIronGolemBase() {
        if (this.ironGolemBase == null)
            this.ironGolemBase = BlockPatternBuilder.start().aisle("~ ~", "###", "~#~").where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK))).where('~', (blockInWorld) -> blockInWorld.getState().isAir()).build();

        return this.ironGolemBase;
    }

    private BlockPattern getOrCreateIronGolemFull() {
        if (this.ironGolemFull == null)
            this.ironGolemFull = BlockPatternBuilder.start().aisle("~^~", "###", "~#~").where('^', BlockInWorld.hasState(CURSED_PUMPKINS_PREDICATE)).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK))).where('~', (blockInWorld) -> blockInWorld.getState().isAir()).build();

        return this.ironGolemFull;
    }

    static {
        FACING = HorizontalDirectionalBlock.FACING;
        CURSED_PUMPKINS_PREDICATE = (arg) -> arg != null && (arg.is(GenerationsBlocks.CURSED_CARVED_PUMPKIN.get()) || arg.is(GenerationsBlocks.CURSED_JACK_O_LANTERN.get()));
    }
}