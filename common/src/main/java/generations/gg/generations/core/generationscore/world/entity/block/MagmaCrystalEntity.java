package generations.gg.generations.core.generationscore.world.entity.block;

import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class MagmaCrystalEntity extends ThrowableItemProjectile {
    public MagmaCrystalEntity(Level level) {
        super(GenerationsEntities.MAGMA_CRYSTAL.get(), level);
    }

    public MagmaCrystalEntity(Level level, LivingEntity entity) {
        super(GenerationsEntities.MAGMA_CRYSTAL.get(), entity, level);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return GenerationsItems.MAGMA_CRYSTAL.get();
    }

    @Override
    protected void onHit(HitResult result) {
        if (result.getType() == HitResult.Type.BLOCK && !level().isClientSide()) {
            if (this.getOwner() instanceof ServerPlayer player) {
                Block mat = this.level().getBlockState(((BlockHitResult) result).getBlockPos()).getBlock();

                if ((mat == Blocks.LAVA || this.level().getBlockState(((BlockHitResult) result).getBlockPos().above()).getBlock() == Blocks.LAVA)) {
//                    level.addFreshEntity(new PixelmonEntity(level, PixelmonData.of(BuiltinPixelmonSpecies.HEATRAN.location()), getOwner().getOnPos())); TODO: Enable later
                    discard();
                } else if (!level().isClientSide() && mat != Blocks.AIR) {
                    player.addItem(new ItemStack(GenerationsItems.MAGMA_CRYSTAL.get()));
                    discard();
                }
            }
        }
    }
}
