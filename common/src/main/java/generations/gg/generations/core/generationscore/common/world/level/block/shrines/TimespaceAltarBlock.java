package generations.gg.generations.core.generationscore.common.world.level.block.shrines;

import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.common.world.item.legends.CreationTrioItem;
import generations.gg.generations.core.generationscore.common.world.item.legends.RedChainItem;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.altar.TimeSpaceAltarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.function.Function;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class TimespaceAltarBlock extends InteractShrineBlock<TimeSpaceAltarBlockEntity> {
    private static final GenerationsVoxelShapes.DirectionalShapes SHAPE = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.or(
                    Shapes.box(0, 0, 0.1875, 1, 0.125, 0.75),
                    Shapes.box(0, 0.125, 0.3125, 1, 0.1875, 0.6875),
                    Shapes.box(0.03749999999999998, 0.1875, 0.3125, 0.975, 0.89375, 0.625)
            )
    );

    public static final MapCodec<TimespaceAltarBlock> CODEC = simpleCodec(TimespaceAltarBlock::new);

    public TimespaceAltarBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.TIMESPACE_ALTAR, GenerationsBlockEntityModels.TIME_SPACE_ALTAR);
    }

    @Override
    protected MapCodec<TimespaceAltarBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean isStackValid(ItemStack stack) {
        return stack.getItem() instanceof RedChainItem || stack.getItem() instanceof CreationTrioItem;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE.getShape(state);
    }

    @Override
    protected boolean interact(Level level, BlockPos pos, BlockState state, ServerPlayer player, InteractionHand hand, boolean activationState) {
        ItemStack stack = player.getItemInHand(hand);

        var entity = getAssoicatedBlockEntity(level, pos).orElse(null);

        if(entity == null) return false;

        var handler = entity.getContainer();

        if (stack.getItem() instanceof RedChainItem && !handler.hasRedChain()) {
            ItemStack chain = handler.insertItem(1, stack, false);

//            if (ItemStack.isSameItem(stack, chain)) return false;
            player.setItemInHand(hand, chain);

            var succeeded = trySpawn(state, level, pos, handler, player);
            if(succeeded) entity.sync();
            return succeeded;
        } else if (stack.getItem() instanceof CreationTrioItem && !handler.hasOrb(player)) {
            ItemStack chain = handler.insertItem(0, stack, false);

//            if (ItemStack.isSameItem(stack, chain)) return false;
            player.setItemInHand(hand, chain);

            var succeeded = trySpawn(state, level, pos, handler, player);
            if(succeeded) entity.sync();
            return succeeded;
        } else if (stack.isEmpty()) {
            player.getInventory().placeItemBackInInventory(handler.extractItem());
            return true;
        } else return false;
    }

    public static boolean trySpawn(BlockState state, Level level, BlockPos pos, TimeSpaceAltarBlockEntity.TimeSpaceAltarItemStackHandler handler, ServerPlayer player) {
        if (handler.shouldSpawn(player)) {
            var id = ((CreationTrioItem) handler.getItem(0).getItem()).getSpeciesId();
            PokemonUtil.spawn(id.createPokemon(70), level, pos, state.getValue(FACING).toYRot());
            RedChainItem.incrementUsage(handler.getItem(1));
            if (RedChainItem.getUses(handler.getItem(1)) >= RedChainItem.MAX_USES)
                handler.setItem(1, ItemStack.EMPTY);
            handler.setItem(0, ItemStack.EMPTY);
            handler.dumpAllIntoPlayerInventory(player);
            return true;
        }

        else {
            return false;
        }
    }
}
