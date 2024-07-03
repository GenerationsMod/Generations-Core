package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.config.LegendKeys;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.item.legends.RegiOrbItem;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.RegigigasShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.OptionalInt;

import static generations.gg.generations.core.generationscore.world.level.block.entities.shrines.RegigigasShrineBlockEntity.getRegiOrbIndex;
import static net.minecraft.core.Direction.SOUTH;
import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class RegigigasShrineBlock extends InteractShrineBlock<RegigigasShrineBlockEntity> {
    private static final GenerationsVoxelShapes.DirectionalShapes SHAPE = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(Shapes.box(0, 0, 0, 1, 0.3125, 1),
                    Shapes.box(0.125, 0, 0.25, 0.875, 0.875, 0.75), OR), SOUTH);

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE.getShape(state);
    }

    public RegigigasShrineBlock(BlockBehaviour.Properties materialIn) {
        super(materialIn, GenerationsBlockEntities.REGIGIGAS_SHRINE, GenerationsBlockEntityModels.REGIGIGAS_SHRINE);
    }

    @Override
    protected boolean activate(Level level, BlockPos pos, BlockState state, ServerPlayer player, InteractionHand hand, ActivationState activationState) {
        ItemStack stack = player.getItemInHand(hand);

        var entity = getAssoicatedBlockEntity(level, pos).orElse(null);

        if (entity == null) return false;

        var handler = entity.getContainer();

        var succeeded = false;

        if (stack.getItem() instanceof RegiOrbItem item && !handler.contains(item)) {
            player.setItemInHand(hand, handler.insertItem(OptionalInt.of(getRegiOrbIndex(item)).getAsInt(), stack, false));

            if (handler.isFull()) {
                PokemonUtil.spawn(LegendKeys.REGIGIGAS.createProperties(70), level, pos.above());
                handler.clear();
            }

            succeeded = true;
        } else {
            for (int i = 0; i < 5; i++) {
                if (!handler.getItem(i).isEmpty()) {
                    player.getInventory().placeItemBackInInventory(handler.extractItem(i, 1, false));
                    succeeded = true;
                    break;
                }
            }
        }

        if(succeeded) entity.sync();

        return succeeded;
    }

    @Override
    public boolean isStackValid(ItemStack stack) {
        return stack.getItem() instanceof RegiOrbItem;
    }
}
