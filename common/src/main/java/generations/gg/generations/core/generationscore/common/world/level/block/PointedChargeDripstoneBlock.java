package generations.gg.generations.core.generationscore.common.world.level.block;

import com.google.common.annotations.VisibleForTesting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@SuppressWarnings("deprecation")
public class PointedChargeDripstoneBlock extends Block implements Fallable, SimpleWaterloggedBlock {
	public static final DirectionProperty TIP_DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;
	public static final EnumProperty<DripstoneThickness> THICKNESS = BlockStateProperties.DRIPSTONE_THICKNESS;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final int MAX_SEARCH_LENGTH_WHEN_CHECKING_DRIP_TYPE = 11;
	private static final int DELAY_BEFORE_FALLING = 2;
	private static final float DRIP_PROBABILITY_PER_ANIMATE_TICK = 0.02f;
	private static final float DRIP_PROBABILITY_PER_ANIMATE_TICK_IF_UNDER_LIQUID_SOURCE = 0.12f;
	private static final int MAX_SEARCH_LENGTH_BETWEEN_STALACTITE_TIP_AND_CAULDRON = 11;
	private static final float WATER_TRANSFER_PROBABILITY_PER_RANDOM_TICK = 0.17578125f;
	private static final float LAVA_TRANSFER_PROBABILITY_PER_RANDOM_TICK = 0.05859375f;
	private static final double MIN_TRIDENT_VELOCITY_TO_BREAK_DRIPSTONE = 0.6;
	private static final float STALACTITE_DAMAGE_PER_FALL_DISTANCE_AND_SIZE = 1.0f;
	private static final int STALACTITE_MAX_DAMAGE = 40;
	private static final int MAX_STALACTITE_HEIGHT_FOR_DAMAGE_CALCULATION = 6;
	private static final float STALAGMITE_FALL_DISTANCE_OFFSET = 2.0f;
	private static final int STALAGMITE_FALL_DAMAGE_MODIFIER = 2;
	private static final float AVERAGE_DAYS_PER_GROWTH = 5.0f;
	private static final float GROWTH_PROBABILITY_PER_RANDOM_TICK = 0.011377778f;
	private static final int MAX_GROWTH_LENGTH = 7;
	private static final int MAX_STALAGMITE_SEARCH_RANGE_WHEN_GROWING = 10;
	private static final float STALACTITE_DRIP_START_PIXEL = 0.6875f;
	private static final VoxelShape TIP_MERGE_SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);
	private static final VoxelShape TIP_SHAPE_UP = Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0);
	private static final VoxelShape TIP_SHAPE_DOWN = Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0);
	private static final VoxelShape FRUSTUM_SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);
	private static final VoxelShape MIDDLE_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);
	private static final VoxelShape BASE_SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);
	private static final float MAX_HORIZONTAL_OFFSET = 0.125f;
	private static final VoxelShape REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK = Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);

	public PointedChargeDripstoneBlock(Properties arg) {
		super(arg);
		this.registerDefaultState(this.stateDefinition.any().setValue(TIP_DIRECTION, Direction.UP).setValue(THICKNESS, DripstoneThickness.TIP).setValue(WATERLOGGED, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(TIP_DIRECTION, THICKNESS, WATERLOGGED);
	}

	@Override
	public boolean canSurvive(BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
		return PointedChargeDripstoneBlock.isValidPointedDripstonePlacement(level, pos, state.getValue(TIP_DIRECTION));
	}

	@Override
	public @NotNull BlockState updateShape(BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor level, @NotNull BlockPos currentPos, @NotNull BlockPos neighborPos) {
		if (state.getValue(WATERLOGGED)) level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

		if (direction != Direction.UP && direction != Direction.DOWN) return state;

		Direction direction2 = state.getValue(TIP_DIRECTION);
		if (direction2 == Direction.DOWN && level.getBlockTicks().hasScheduledTick(currentPos, this)) return state;

		if (direction == direction2.getOpposite() && !this.canSurvive(state, level, currentPos)) {
			if (direction2 == Direction.DOWN) level.scheduleTick(currentPos, this, 2);
			else level.scheduleTick(currentPos, this, 1);

			return state;
		}
		return state.setValue(THICKNESS, PointedChargeDripstoneBlock.calculateDripstoneThickness(level, currentPos, direction2, state.getValue(THICKNESS) == DripstoneThickness.TIP_MERGE));
	}

	@Override
	public void onProjectileHit(Level level, @NotNull BlockState state, BlockHitResult hit, @NotNull Projectile projectile) {
		BlockPos blockPos = hit.getBlockPos();
		if (!level.isClientSide && projectile.mayInteract(level, blockPos) && projectile instanceof ThrownTrident && projectile.getDeltaMovement().length() > 0.6)
			level.destroyBlock(blockPos, true);
	}

	@Override
	public void fallOn(@NotNull Level level, BlockState state, @NotNull BlockPos pos, @NotNull Entity entity, float fallDistance) {
		if (state.getValue(TIP_DIRECTION) == Direction.UP && state.getValue(THICKNESS) == DripstoneThickness.TIP) entity.causeFallDamage(fallDistance + 2.0f, 2.0f, entity.damageSources().stalagmite());
		else super.fallOn(level, state, pos, entity, fallDistance);
	}

	@Override
	public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
		if (!PointedChargeDripstoneBlock.canDrip(state)) return;

		float f = random.nextFloat();
		if (f > 0.12f) return;
		PointedChargeDripstoneBlock.getFluidAboveStalactite(level, pos, state).filter(arg -> f < 0.02f || PointedChargeDripstoneBlock.canFillCauldron(arg.fluid)).ifPresent(arg4 -> PointedChargeDripstoneBlock.spawnDripParticle(level, pos, state, arg4.fluid));
	}

	@Override
	public void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
		if (PointedChargeDripstoneBlock.isStalagmite(state) && !this.canSurvive(state, level, pos)) level.destroyBlock(pos, true);
		else PointedChargeDripstoneBlock.spawnFallingStalactite(state, level, pos);
	}

	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		maybeTransferFluid(state, level, pos, random.nextFloat());
		if (random.nextFloat() < 0.011377778F && isStalactiteStartPos(state, level, pos))
			growStalactiteOrStalagmiteIfPossible(state, level, pos, random);
	}

	@VisibleForTesting
	public static void maybeTransferFluid(BlockState state, ServerLevel level, BlockPos pos, float randChance) {
		if (!(randChance > 0.17578125F) || !(randChance > 0.05859375F)) {
			if (isStalactiteStartPos(state, level, pos)) {
				Optional<FluidInfo> optional = getFluidAboveStalactite(level, pos, state);
				if (optional.isPresent()) {
					Fluid fluid = optional.get().fluid;
					float f;
					if (fluid == Fluids.WATER) f = 0.17578125F;
					else {
						if (fluid != Fluids.LAVA) return;

						f = 0.05859375F;
					}

					if (!(randChance >= f)) {
						BlockPos blockPos = findTip(state, level, pos, 11, false);
						if (blockPos != null)
							if (optional.get().sourceState.is(Blocks.MUD) && fluid == Fluids.WATER) {
								BlockState blockState = Blocks.CLAY.defaultBlockState();
								level.setBlockAndUpdate(optional.get().pos, blockState);
								Block.pushEntitiesUp((optional.get()).sourceState, blockState, level, (optional.get()).pos);
								level.gameEvent(GameEvent.BLOCK_CHANGE, (optional.get()).pos, GameEvent.Context.of(blockState));
								level.levelEvent(1504, blockPos, 0);
							} else {
								BlockPos blockPos2 = findFillableCauldronBelowStalactiteTip(level, blockPos, fluid);
								if (blockPos2 != null) {
									level.levelEvent(1504, blockPos, 0);
									int i = blockPos.getY() - blockPos2.getY();
									int j = 50 + i;
									BlockState blockState2 = level.getBlockState(blockPos2);
									level.scheduleTick(blockPos2, blockState2.getBlock(), j);
								}
							}
					}
				}
			}
		}
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos blockPos;
		Level levelAccessor = context.getLevel();
		Direction direction2 = PointedChargeDripstoneBlock.calculateTipDirection(levelAccessor, blockPos = context.getClickedPos(), context.getNearestLookingVerticalDirection().getOpposite());
		if (direction2 == null) return null;

		DripstoneThickness dripstoneThickness = PointedChargeDripstoneBlock.calculateDripstoneThickness(levelAccessor, blockPos, direction2, !context.isSecondaryUseActive());
		if (dripstoneThickness == null) return null;

		return this.defaultBlockState().setValue(TIP_DIRECTION, direction2).setValue(THICKNESS, dripstoneThickness).setValue(WATERLOGGED, levelAccessor.getFluidState(blockPos).getType() == Fluids.WATER);
	}

	@Override
	public @NotNull FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public @NotNull VoxelShape getOcclusionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
		return Shapes.empty();
	}

	@Override
	public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
		DripstoneThickness dripstoneThickness = state.getValue(THICKNESS);
		VoxelShape voxelShape = dripstoneThickness == DripstoneThickness.TIP_MERGE ? TIP_MERGE_SHAPE : (dripstoneThickness == DripstoneThickness.TIP ? (state.getValue(TIP_DIRECTION) == Direction.DOWN ? TIP_SHAPE_DOWN : TIP_SHAPE_UP) : (dripstoneThickness == DripstoneThickness.FRUSTUM ? FRUSTUM_SHAPE : (dripstoneThickness == DripstoneThickness.MIDDLE ? MIDDLE_SHAPE : BASE_SHAPE)));
		Vec3 vec3 = state.getOffset(level, pos);
		return voxelShape.move(vec3.x, 0.0, vec3.z);
	}

	@Override
	public boolean isCollisionShapeFullBlock(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
		return false;
	}

	@Override
	public float getMaxHorizontalOffset() {
		return 0.125f;
	}

	@Override
	public void onBrokenAfterFall(@NotNull Level level, @NotNull BlockPos pos, FallingBlockEntity fallingBlock) {
		if (!fallingBlock.isSilent()) level.levelEvent(1045, pos, 0);
	}

	@Override
	public @NotNull DamageSource getFallDamageSource(@NotNull Entity entity) {
		return entity.damageSources().fallingStalactite(entity);
	}

	private static void spawnFallingStalactite(BlockState state, ServerLevel level, BlockPos pos) {
		BlockPos.MutableBlockPos mutableBlockPos = pos.mutable();
		BlockState blockState = state;
		while (PointedChargeDripstoneBlock.isStalactite(blockState)) {
			FallingBlockEntity fallingBlockEntity = FallingBlockEntity.fall(level, mutableBlockPos, blockState);
			if (PointedChargeDripstoneBlock.isTip(blockState, true)) {
				fallingBlockEntity.setHurtsEntities((float) Math.max(1 + pos.getY() - mutableBlockPos.getY(), 6), 40);
				break;
			}
			mutableBlockPos.move(Direction.DOWN);
			blockState = level.getBlockState(mutableBlockPos);
		}
	}

	@VisibleForTesting
	public static void growStalactiteOrStalagmiteIfPossible(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!PointedChargeDripstoneBlock.canGrow(level.getBlockState(pos.above(1)), level.getBlockState(pos.above(2)))) return;

		BlockPos blockPos = PointedChargeDripstoneBlock.findTip(state, level, pos, 7, false);
		if (blockPos == null) return;

		BlockState blockState3 = level.getBlockState(blockPos);
		if (!PointedChargeDripstoneBlock.canDrip(blockState3) || !PointedChargeDripstoneBlock.canTipGrow(blockState3, level, blockPos)) return;

		if (random.nextBoolean()) PointedChargeDripstoneBlock.grow(level, blockPos, Direction.DOWN);
		else PointedChargeDripstoneBlock.growStalagmiteBelow(level, blockPos);
	}

	private static void growStalagmiteBelow(ServerLevel level, BlockPos pos) {
		BlockPos.MutableBlockPos mutableBlockPos = pos.mutable();
		for (int i = 0; i < 10; ++i) {
			mutableBlockPos.move(Direction.DOWN);
			BlockState blockState = level.getBlockState(mutableBlockPos);
			if (!blockState.getFluidState().isEmpty()) {
				return;
			}
			if (PointedChargeDripstoneBlock.isUnmergedTipWithDirection(blockState, Direction.UP) && PointedChargeDripstoneBlock.canTipGrow(blockState, level, mutableBlockPos)) {
				PointedChargeDripstoneBlock.grow(level, mutableBlockPos, Direction.UP);
				return;
			}
			if (PointedChargeDripstoneBlock.isValidPointedDripstonePlacement(level, mutableBlockPos, Direction.UP) && !level.isWaterAt(mutableBlockPos.below())) {
				PointedChargeDripstoneBlock.grow(level, mutableBlockPos.below(), Direction.UP);
				return;
			}
			if (PointedChargeDripstoneBlock.canDripThrough(level, mutableBlockPos, blockState)) continue;
			return;
		}
	}

	private static void grow(ServerLevel server, BlockPos pos, Direction direction) {
		BlockPos blockPos = pos.relative(direction);
		BlockState blockState = server.getBlockState(blockPos);
		if (PointedChargeDripstoneBlock.isUnmergedTipWithDirection(blockState, direction.getOpposite()))
			PointedChargeDripstoneBlock.createMergedTips(blockState, server, blockPos);
		else if (blockState.isAir() || blockState.is(Blocks.WATER))
			PointedChargeDripstoneBlock.createDripstone(server, blockPos, direction, DripstoneThickness.TIP);
	}

	private static void createDripstone(LevelAccessor level, BlockPos pos, Direction direction, DripstoneThickness thickness) {
		BlockState blockState = GenerationsBlocks.POINTED_CHARGE_DRIPSTONE.get().defaultBlockState().setValue(TIP_DIRECTION, direction).setValue(THICKNESS, thickness).setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER);
		level.setBlock(pos, blockState, 3);
	}

	private static void createMergedTips(BlockState state, LevelAccessor level, BlockPos pos) {
		BlockPos blockPos2;
		BlockPos blockPos;
		if (state.getValue(TIP_DIRECTION) == Direction.UP) {
			blockPos = pos;
			blockPos2 = pos.above();
		} else {
			blockPos2 = pos;
			blockPos = pos.below();
		}
		PointedChargeDripstoneBlock.createDripstone(level, blockPos2, Direction.DOWN, DripstoneThickness.TIP_MERGE);
		PointedChargeDripstoneBlock.createDripstone(level, blockPos, Direction.UP, DripstoneThickness.TIP_MERGE);
	}

	private static void spawnDripParticle(Level level, BlockPos pos, BlockState state, Fluid fluid) {
		SimpleParticleType particleOptions = PointedChargeDripstoneBlock.getDripFluid(level, fluid).is(FluidTags.LAVA) ? ParticleTypes.DRIPPING_DRIPSTONE_LAVA : ParticleTypes.DRIPPING_DRIPSTONE_WATER;
		Vec3 vec3 = state.getOffset(level, pos);
		level.addParticle(particleOptions, (double)pos.getX() + 0.5 + vec3.x, (double)((float)(pos.getY() + 1) - 0.6875f) - 0.0625, (double)pos.getZ() + 0.5 + vec3.z, 0.0, 0.0, 0.0);
	}

	@Nullable
	private static BlockPos findTip(BlockState state, LevelAccessor level, BlockPos pos, int maxIterations, boolean isTipMerge) {
		if (PointedChargeDripstoneBlock.isTip(state, isTipMerge)) return pos;

		Direction direction = state.getValue(TIP_DIRECTION);
		BiPredicate<BlockPos, BlockState> biPredicate = (arg2, arg3) -> arg3.is(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE.get()) && arg3.getValue(TIP_DIRECTION) == direction;
		return PointedChargeDripstoneBlock.findBlockVertical(level, pos, direction.getAxisDirection(), biPredicate, arg -> PointedChargeDripstoneBlock.isTip(arg, isTipMerge), maxIterations).orElse(null);
	}

	@Nullable
	private static Direction calculateTipDirection(LevelReader level, BlockPos pos, Direction dir) {
		Direction direction;
		if (PointedChargeDripstoneBlock.isValidPointedDripstonePlacement(level, pos, dir)) direction = dir;
		else if (PointedChargeDripstoneBlock.isValidPointedDripstonePlacement(level, pos, dir.getOpposite())) direction = dir.getOpposite();
		else return null;
		return direction;
	}

	private static DripstoneThickness calculateDripstoneThickness(LevelReader level, BlockPos pos, Direction dir, boolean isTipMerge) {
		Direction direction = dir.getOpposite();
		BlockState blockState = level.getBlockState(pos.relative(dir));
		if (PointedChargeDripstoneBlock.isPointedDripstoneWithDirection(blockState, direction)) {
			if (isTipMerge || blockState.getValue(THICKNESS) == DripstoneThickness.TIP_MERGE) return DripstoneThickness.TIP_MERGE;

			return DripstoneThickness.TIP;
		}
		if (!PointedChargeDripstoneBlock.isPointedDripstoneWithDirection(blockState, dir)) return DripstoneThickness.TIP;

		DripstoneThickness dripstoneThickness = blockState.getValue(THICKNESS);
		if (dripstoneThickness == DripstoneThickness.TIP || dripstoneThickness == DripstoneThickness.TIP_MERGE) return DripstoneThickness.FRUSTUM;

		BlockState blockState2 = level.getBlockState(pos.relative(direction));
		if (!PointedChargeDripstoneBlock.isPointedDripstoneWithDirection(blockState2, dir)) return DripstoneThickness.BASE;

		return DripstoneThickness.MIDDLE;
	}

	public static boolean canDrip(BlockState state) {
		return PointedChargeDripstoneBlock.isStalactite(state) && state.getValue(THICKNESS) == DripstoneThickness.TIP && !state.getValue(WATERLOGGED);
	}

	private static boolean canTipGrow(BlockState state, ServerLevel level, BlockPos pos) {
		Direction direction = state.getValue(TIP_DIRECTION);
		BlockPos blockPos = pos.relative(direction);
		BlockState blockState = level.getBlockState(blockPos);
		if (!blockState.getFluidState().isEmpty()) return false;

		if (blockState.isAir()) return true;

		return PointedChargeDripstoneBlock.isUnmergedTipWithDirection(blockState, direction.getOpposite());
	}

	private static Optional<BlockPos> findRootBlock(Level level, BlockPos pos, BlockState state, int maxIterations) {
		Direction direction = state.getValue(TIP_DIRECTION);
		BiPredicate<BlockPos, BlockState> biPredicate = (arg2, arg3) -> arg3.is(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE.get()) && arg3.getValue(TIP_DIRECTION) == direction;
		return PointedChargeDripstoneBlock.findBlockVertical(level, pos, direction.getOpposite().getAxisDirection(), biPredicate, arg -> !arg.is(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE.get()), maxIterations);
	}

	private static boolean isValidPointedDripstonePlacement(LevelReader level, BlockPos pos, Direction dir) {
		BlockPos blockPos = pos.relative(dir.getOpposite());
		BlockState blockState = level.getBlockState(blockPos);
		return blockState.isFaceSturdy(level, blockPos, dir) || PointedChargeDripstoneBlock.isPointedDripstoneWithDirection(blockState, dir);
	}

	private static boolean isTip(BlockState state, boolean isTipMerge) {
		if (!state.is(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE.get())) return false;

		DripstoneThickness dripstoneThickness = state.getValue(THICKNESS);
		return dripstoneThickness == DripstoneThickness.TIP || isTipMerge && dripstoneThickness == DripstoneThickness.TIP_MERGE;
	}

	private static boolean isUnmergedTipWithDirection(BlockState state, Direction dir) {
		return PointedChargeDripstoneBlock.isTip(state, false) && state.getValue(TIP_DIRECTION) == dir;
	}

	private static boolean isStalactite(BlockState state) {
		return PointedChargeDripstoneBlock.isPointedDripstoneWithDirection(state, Direction.DOWN);
	}

	private static boolean isStalagmite(BlockState state) {
		return PointedChargeDripstoneBlock.isPointedDripstoneWithDirection(state, Direction.UP);
	}

	private static boolean isStalactiteStartPos(BlockState state, LevelReader level, BlockPos pos) {
		return PointedChargeDripstoneBlock.isStalactite(state) && !level.getBlockState(pos.above()).is(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE.get());
	}

	@Override
	public boolean isPathfindable(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull PathComputationType type) {
		return false;
	}

	private static boolean isPointedDripstoneWithDirection(BlockState state, Direction dir) {
		return state.is(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE.get()) && state.getValue(TIP_DIRECTION) == dir;
	}

	@Nullable
	private static BlockPos findFillableCauldronBelowStalactiteTip(Level level, BlockPos pos, Fluid fluid) {
		Predicate<BlockState> predicate = arg2 -> arg2.getBlock() instanceof AbstractCauldronBlock && ((AbstractCauldronBlock)arg2.getBlock()).canReceiveStalactiteDrip(fluid);
		BiPredicate<BlockPos, BlockState> biPredicate = (arg2, arg3) -> PointedChargeDripstoneBlock.canDripThrough(level, arg2, arg3);
		return PointedChargeDripstoneBlock.findBlockVertical(level, pos, Direction.DOWN.getAxisDirection(), biPredicate, predicate, 11).orElse(null);
	}

	@Nullable
	public static BlockPos findStalactiteTipAboveCauldron(Level level, BlockPos pos) {
		BiPredicate<BlockPos, BlockState> biPredicate = (arg2, arg3) -> PointedChargeDripstoneBlock.canDripThrough(level, arg2, arg3);
		return PointedChargeDripstoneBlock.findBlockVertical(level, pos, Direction.UP.getAxisDirection(), biPredicate, PointedChargeDripstoneBlock::canDrip, 11).orElse(null);
	}

	public static Fluid getCauldronFillFluidType(ServerLevel level, BlockPos pos) {
		return PointedChargeDripstoneBlock.getFluidAboveStalactite(level, pos, level.getBlockState(pos)).map(arg -> arg.fluid).filter(PointedChargeDripstoneBlock::canFillCauldron).orElse(Fluids.EMPTY);
	}

	private static Optional<FluidInfo> getFluidAboveStalactite(Level level, BlockPos pos, BlockState state) {
		if (!PointedChargeDripstoneBlock.isStalactite(state)) {
			return Optional.empty();
		}
		return PointedChargeDripstoneBlock.findRootBlock(level, pos, state, 11).map(arg2 -> {
			BlockPos blockPos = arg2.above();
			BlockState blockState = level.getBlockState(blockPos);
			Fluid fluid = blockState.is(Blocks.MUD) && !level.dimensionType().ultraWarm() ? Fluids.WATER : level.getFluidState(blockPos).getType();
			return new FluidInfo(blockPos, fluid, blockState);
		});
	}

	private static boolean canFillCauldron(Fluid fluid) {
		return fluid == Fluids.LAVA || fluid == Fluids.WATER;
	}

	private static boolean canGrow(BlockState dripstoneState, BlockState state) {
		return dripstoneState.is(GenerationsBlocks.CHARGE_DRIPSTONE_BLOCK.get()) && state.is(Blocks.WATER) && state.getFluidState().isSource();
	}

	private static Fluid getDripFluid(Level level, Fluid fluid) {
		if (fluid.isSame(Fluids.EMPTY)) return level.dimensionType().ultraWarm() ? Fluids.LAVA : Fluids.WATER;
		return fluid;
	}

	private static Optional<BlockPos> findBlockVertical(LevelAccessor level, BlockPos pos, Direction.AxisDirection axis, BiPredicate<BlockPos, BlockState> positionalStatePredicate, Predicate<BlockState> statePredicate, int maxIterations) {
		Direction direction = Direction.get(axis, Direction.Axis.Y);
		BlockPos.MutableBlockPos mutableBlockPos = pos.mutable();
		for (int i = 1; i < maxIterations; ++i) {
			mutableBlockPos.move(direction);
			BlockState blockState = level.getBlockState(mutableBlockPos);
			if (statePredicate.test(blockState)) {
				return Optional.of(mutableBlockPos.immutable());
			}
			if (!level.isOutsideBuildHeight(mutableBlockPos.getY()) && positionalStatePredicate.test(mutableBlockPos, blockState)) continue;
			return Optional.empty();
		}
		return Optional.empty();
	}

	private static boolean canDripThrough(BlockGetter level, BlockPos pos, BlockState state) {
		if (state.isAir()) return true;

		if (state.isSolidRender(level, pos) || !state.getFluidState().isEmpty()) return false;

		return !Shapes.joinIsNotEmpty(REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK, state.getCollisionShape(level, pos), BooleanOp.AND);
	}

	record FluidInfo(BlockPos pos, Fluid fluid, BlockState sourceState) {
	}
}
