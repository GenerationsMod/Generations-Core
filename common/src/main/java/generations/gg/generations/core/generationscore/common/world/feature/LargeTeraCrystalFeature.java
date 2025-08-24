package generations.gg.generations.core.generationscore.common.world.feature;

import com.mojang.serialization.Codec;
import generations.gg.generations.core.generationscore.common.world.feature.configurations.LargeTeraCrystalConfiguration;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class LargeTeraCrystalFeature extends Feature<LargeTeraCrystalConfiguration> {
    public LargeTeraCrystalFeature(Codec<LargeTeraCrystalConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<LargeTeraCrystalConfiguration> context) {
        WorldGenLevel worldGenLevel = context.level();
        BlockPos blockPos = context.origin();
        LargeTeraCrystalConfiguration largeTeraCrystalConfiguration = (LargeTeraCrystalConfiguration)context.config();
        RandomSource randomSource = context.random();
        if (!TeraCrystalUtils.isEmptyOrWater(worldGenLevel, blockPos)) {
            return false;
        } else {
            Optional<Column> optional = Column.scan(worldGenLevel, blockPos, largeTeraCrystalConfiguration.floorToCeilingSearchRange, TeraCrystalUtils::isEmptyOrWater, TeraCrystalUtils::isDripstoneBaseOrLava);
            if (!optional.isEmpty() && optional.get() instanceof Column.Range) {
                Column.Range range = (Column.Range)optional.get();
                if (range.height() < 4) {
                    return false;
                } else {
                    int i = (int)((float)range.height() * largeTeraCrystalConfiguration.maxColumnRadiusToCaveHeightRatio);
                    int j = Mth.clamp(i, largeTeraCrystalConfiguration.columnRadius.getMinValue(), largeTeraCrystalConfiguration.columnRadius.getMaxValue());
                    int k = Mth.randomBetweenInclusive(randomSource, largeTeraCrystalConfiguration.columnRadius.getMinValue(), j);
                    LargeDripstone largeDripstone = makeDripstone(blockPos.atY(range.ceiling() - 1), false, randomSource, k, largeTeraCrystalConfiguration.stalactiteBluntness, largeTeraCrystalConfiguration.heightScale);
                    LargeDripstone largeDripstone2 = makeDripstone(blockPos.atY(range.floor() + 1), true, randomSource, k, largeTeraCrystalConfiguration.stalagmiteBluntness, largeTeraCrystalConfiguration.heightScale);
                    WindOffsetter windOffsetter;
                    if (largeDripstone.isSuitableForWind(largeTeraCrystalConfiguration) && largeDripstone2.isSuitableForWind(largeTeraCrystalConfiguration)) {
                        windOffsetter = new WindOffsetter(blockPos.getY(), randomSource, largeTeraCrystalConfiguration.windSpeed);
                    } else {
                        windOffsetter = LargeTeraCrystalFeature.WindOffsetter.noWind();
                    }

                    boolean bl = largeDripstone.moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(worldGenLevel, windOffsetter);
                    boolean bl2 = largeDripstone2.moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(worldGenLevel, windOffsetter);
                    if (bl) {
                        largeDripstone.placeBlocks(worldGenLevel, randomSource, windOffsetter);
                    }

                    if (bl2) {
                        largeDripstone2.placeBlocks(worldGenLevel, randomSource, windOffsetter);
                    }

                    return true;
                }
            } else {
                return false;
            }
        }
    }

    private static LargeDripstone makeDripstone(BlockPos root, boolean pointingUp, RandomSource random, int radius, FloatProvider bluntnessBase, FloatProvider scaleBase) {
        return new LargeDripstone(root, pointingUp, radius, (double)bluntnessBase.sample(random), (double)scaleBase.sample(random));
    }

    private void placeDebugMarkers(WorldGenLevel level, BlockPos pos, Column.Range range, WindOffsetter windOffsetter) {
        level.setBlock(windOffsetter.offset(pos.atY(range.ceiling() - 1)), Blocks.DIAMOND_BLOCK.defaultBlockState(), 2);
        level.setBlock(windOffsetter.offset(pos.atY(range.floor() + 1)), Blocks.GOLD_BLOCK.defaultBlockState(), 2);

        for(BlockPos.MutableBlockPos mutableBlockPos = pos.atY(range.floor() + 2).mutable(); mutableBlockPos.getY() < range.ceiling() - 1; mutableBlockPos.move(Direction.UP)) {
            BlockPos blockPos = windOffsetter.offset(mutableBlockPos);
            if (TeraCrystalUtils.isEmptyOrWater(level, blockPos) || level.getBlockState(blockPos).is(GenerationsBlocks.INSTANCE.getTERA_CRYSTAL_BLOCK().value())) {
                level.setBlock(blockPos, Blocks.CREEPER_HEAD.defaultBlockState(), 2);
            }
        }

    }

    static final class LargeDripstone {
        private BlockPos root;
        private final boolean pointingUp;
        private int radius;
        private final double bluntness;
        private final double scale;

        LargeDripstone(BlockPos root, boolean pointingUp, int radius, double bluntness, double scale) {
            this.root = root;
            this.pointingUp = pointingUp;
            this.radius = radius;
            this.bluntness = bluntness;
            this.scale = scale;
        }

        private int getHeight() {
            return this.getHeightAtRadius(0.0F);
        }

        private int getMinY() {
            return this.pointingUp ? this.root.getY() : this.root.getY() - this.getHeight();
        }

        private int getMaxY() {
            return !this.pointingUp ? this.root.getY() : this.root.getY() + this.getHeight();
        }

        boolean moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(WorldGenLevel level, WindOffsetter windOffsetter) {
            while(this.radius > 1) {
                BlockPos.MutableBlockPos mutableBlockPos = this.root.mutable();
                int i = Math.min(10, this.getHeight());

                for(int j = 0; j < i; ++j) {
                    if (level.getBlockState(mutableBlockPos).is(Blocks.LAVA)) {
                        return false;
                    }

                    if (TeraCrystalUtils.isCircleMostlyEmbeddedInStone(level, windOffsetter.offset(mutableBlockPos), this.radius)) {
                        this.root = mutableBlockPos;
                        return true;
                    }

                    mutableBlockPos.move(this.pointingUp ? Direction.DOWN : Direction.UP);
                }

                this.radius /= 2;
            }

            return false;
        }

        private int getHeightAtRadius(float radius) {
            return (int)TeraCrystalUtils.getDripstoneHeight((double)radius, (double)this.radius, this.scale, this.bluntness);
        }

        void placeBlocks(WorldGenLevel level, RandomSource random, WindOffsetter windOffsetter) {
            for(int i = -this.radius; i <= this.radius; ++i) {
                for(int j = -this.radius; j <= this.radius; ++j) {
                    float f = Mth.sqrt((float)(i * i + j * j));
                    if (!(f > (float)this.radius)) {
                        int k = this.getHeightAtRadius(f);
                        if (k > 0) {
                            if ((double)random.nextFloat() < 0.2) {
                                k = (int)((float)k * Mth.randomBetween(random, 0.8F, 1.0F));
                            }

                            BlockPos.MutableBlockPos mutableBlockPos = this.root.offset(i, 0, j).mutable();
                            boolean bl = false;
                            int l = this.pointingUp ? level.getHeight(Types.WORLD_SURFACE_WG, mutableBlockPos.getX(), mutableBlockPos.getZ()) : Integer.MAX_VALUE;

                            for(int m = 0; m < k && mutableBlockPos.getY() < l; ++m) {
                                BlockPos blockPos = windOffsetter.offset(mutableBlockPos);
                                if (TeraCrystalUtils.isEmptyOrWaterOrLava(level, blockPos)) {
                                    bl = true;
                                    Block block = GenerationsBlocks.INSTANCE.getTERA_CRYSTAL_BLOCK().value();
                                    level.setBlock(blockPos, block.defaultBlockState(), 2);
                                } else if (bl && level.getBlockState(blockPos).is(BlockTags.BASE_STONE_OVERWORLD)) {
                                    break;
                                }

                                mutableBlockPos.move(this.pointingUp ? Direction.UP : Direction.DOWN);
                            }
                        }
                    }
                }
            }

        }

        boolean isSuitableForWind(LargeTeraCrystalConfiguration config) {
            return this.radius >= config.minRadiusForWind && this.bluntness >= (double)config.minBluntnessForWind;
        }
    }

    static final class WindOffsetter {
        private final int originY;
        @Nullable
        private final Vec3 windSpeed;

        WindOffsetter(int originY, RandomSource random, FloatProvider magnitude) {
            this.originY = originY;
            float f = magnitude.sample(random);
            float g = Mth.randomBetween(random, 0.0F, (float)Math.PI);
            this.windSpeed = new Vec3((double)(Mth.cos(g) * f), (double)0.0F, (double)(Mth.sin(g) * f));
        }

        private WindOffsetter() {
            this.originY = 0;
            this.windSpeed = null;
        }

        static WindOffsetter noWind() {
            return new WindOffsetter();
        }

        BlockPos offset(BlockPos pos) {
            if (this.windSpeed == null) {
                return pos;
            } else {
                int i = this.originY - pos.getY();
                Vec3 vec3 = this.windSpeed.scale((double)i);
                return pos.offset(Mth.floor(vec3.x), 0, Mth.floor(vec3.z));
            }
        }
    }
}
