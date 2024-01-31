package generations.gg.generations.core.generationscore.world.level.block.decorations;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.world.entity.block.SittableEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.DyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericDyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.Map;

@SuppressWarnings("deprecation")
public class PastelBeanBagBlock extends DyeableBlock<GenericDyedVariantBlockEntity, PastelBeanBagBlock> {
    public PastelBeanBagBlock(DyeColor color, Map<DyeColor, RegistrySupplier<DyeableBlock<GenericDyedVariantBlockEntity, PastelBeanBagBlock>>> function, Properties props) {
        super(color, function, GenerationsBlockEntities.GENERIC_DYED_VARIANT, props, GenerationsBlockEntityModels.PASTEL_BEAN_BAG);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Shapes.box(0.1, 0.0, 0.1, 0.9, 0.5, 0.9);
    }

    @Override
    protected InteractionResult serverUse(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!world.isClientSide && !player.isShiftKeyDown())
            return SittableEntity.mount(world, pos, 0.5f, player);

        return InteractionResult.PASS;
    }
}
