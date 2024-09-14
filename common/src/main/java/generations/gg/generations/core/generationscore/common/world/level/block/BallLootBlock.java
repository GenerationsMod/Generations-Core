package generations.gg.generations.core.generationscore.common.world.level.block;

import com.cobblemon.mod.common.pokeball.PokeBall;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.BallLootBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Collectors;

public class BallLootBlock extends GenericRotatableModelBlock<BallLootBlockEntity> {
    private static final VoxelShape shape = Shapes.box(0.25f, 0.0f, 0.25f, 0.75, 0.5f, 0.75f);
    private final String name;
    private final ResourceLocation lootTable;
    private final PokeBall ball;

    protected BallLootBlock(String name, PokeBall ball) {
        super(BlockBehaviour.Properties.of().randomTicks().sound(SoundType.METAL).strength(-1.0f, 3600000.0f).noOcclusion(), GenerationsBlockEntities.BALL_LOOT, null);
        this.name = name;
        this.ball = ball;
        this.lootTable = GenerationsCore.id("chests/%s_ball".formatted(name));
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if(level.isClientSide || hand == InteractionHand.OFF_HAND) {
            return InteractionResult.CONSUME;
        }

        var lootEntity = level.getBlockEntity(pos, GenerationsBlockEntities.BALL_LOOT.get());

        if(lootEntity.isPresent()) {
            var be = lootEntity.get();

            var playerUUID = player.getUUID();

            if(!playerUUID.equals(be.getOwner())) {

                if(be.canClaim(playerUUID)) {
                    if (be.shouldBreakBlock()) {
                        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                    }

                    var list = be.isCustomDrop() ? be.getCustomDrops() : getDrops((ServerLevel) level, pos, player);
                    Containers.dropContents(level, pos.above(), list);

                    be.removeClaimer(playerUUID);
                    be.addClaimer(playerUUID);
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), GenerationsSounds.LUGIA_SHRINE_SONG.get(), SoundSource.BLOCKS, 0.2f, 1.0f);

                    if (be.isVisible()) {
                        player.awardStat(GenerationsCoreStats.HIDDEN_LOOT_FOUND, 1);
                    } else {
                        player.awardStat(GenerationsCoreStats.NORMAL_LOOT_FOUND, 1);
                    }
                } else if(be.getLootMode().isTimeEnabled()) {
                    player.displayClientMessage(Component.translatable("generations_core.blocks.timedclaim"), false);
                } else {
                    player.displayClientMessage(Component.translatable("generations_core.blocks.claimedloot"), false);
                }
            } else {
                boolean shiftClick = player.isShiftKeyDown();
                if (shiftClick) {
                    be.setOwner(null);
                    player.displayClientMessage(Component.translatable("generations_core.blocks.ownerchanged"), false);
                } else {
                    ItemStack itemStack = player.getMainHandItem();
                    if (!itemStack.isEmpty()) {
                        be.addCustomDrop(itemStack);
                        player.displayClientMessage(Component.translatable("generations_core.blocks.balllootset", itemStack.getDisplayName()), false);
                    } else {
                        var visible = !be.isVisible();
                        be.setVisible(visible);
                        String metaMode = visible ? "Normal" : "Hidden";
                        player.displayClientMessage(Component.translatable("generations_core.blocks.visible", metaMode), false);
                        return InteractionResult.FAIL;
                    }
                }
            }
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public void attack(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player) {
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof BallLootBlockEntity be && player.getUUID().equals(be.getOwner())) {
            String mode = "generations_core.blocks.lootmode.";

            BallLootBlockEntity.LootMode lootMode = be.getLootMode();

            lootMode = switch (lootMode) {
                case ONCE_PER_PLAYER -> BallLootBlockEntity.LootMode.TIMED;
                case TIMED -> BallLootBlockEntity.LootMode.ONCE;
                case ONCE -> BallLootBlockEntity.LootMode.UNLIMITED;
                case UNLIMITED -> BallLootBlockEntity.LootMode.ONCE_PER_PLAYER;
            };

            be.setLootMode(lootMode);
            mode += lootMode.name().toUpperCase();

            player.displayClientMessage(Component.translatable("generations_core.blocks.lootmode", Language.getInstance().getOrDefault(mode)), false);
        }
    }
    public String getType() {
        return name;
    }

    public ResourceLocation getLootTableId() {
        return lootTable;
    }

    public NonNullList<ItemStack> getDrops(ServerLevel level, BlockPos pos, Player player) {
        LootParams.Builder builder = (new LootParams.Builder(level)).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos));
        if (player != null)
            builder.withLuck(player.getLuck()).withParameter(LootContextParams.THIS_ENTITY, player);


        return level.getServer().getLootData().getLootTable(this.getLootTableId()).getRandomItems(builder.create(LootContextParamSets.CHEST)).stream().limit(1).collect(Collectors.toCollection(NonNullList::create));
    }

    @Override
    public void randomTick(@NotNull BlockState state, ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (level.getBlockEntity(pos) instanceof BallLootBlockEntity be && !be.isVisible()) {
            float rand = random.nextFloat() * 0.5f + 1.0f;
            double xVel = 0.1;
            double yVel = 0.2;
            double zVel = 0.1;
            level.addParticle(ParticleTypes.EFFECT, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, xVel * rand, yVel * rand, zVel * rand);
        }
    }

    @Override
    public void setPlacedBy(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable LivingEntity placer, @NotNull ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if(level.getBlockEntity(pos) instanceof BallLootBlockEntity lootBlockEntity && placer instanceof ServerPlayer player) lootBlockEntity.setOwner(placer.getUUID());
    }

    public PokeBall ball() {
        return this.ball;
    }

    @Override
    public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return shape;
    }
}