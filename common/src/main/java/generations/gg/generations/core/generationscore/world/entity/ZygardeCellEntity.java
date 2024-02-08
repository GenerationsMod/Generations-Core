package generations.gg.generations.core.generationscore.world.entity;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.client.render.rarecandy.BlockObjectInstance;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.ZygardeCubeItem;
import generations.gg.generations.core.generationscore.world.sound.GenerationsSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class ZygardeCellEntity extends Entity {
    public BlockObjectInstance instance = new BlockObjectInstance(new Matrix4f(), new Matrix4f(), null);
    public ZygardeCellEntity(Level level) {
        super(GenerationsEntities.ZYGARDE_CELL.get(), level);
    }

    public ZygardeCellEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }


    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {

    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        System.out.println("rawr: :(");

        var stack = player.getItemInHand(hand);

        if(stack.is(GenerationsItems.ZYGARDE_CUBE.get())) {
            if(stack.getDamageValue() != ZygardeCubeItem.FULL) {
                stack.setDamageValue(stack.getDamageValue() + 1);
                player.displayClientMessage(Component.translatable("item.generations_core.zygarde_cube.add"), false);
                level().playSound(null, blockPosition(), GenerationsSounds.ZYGARDE_CELL.get(), SoundSource.BLOCKS, 0.5f, 1.0f);
                remove(RemovalReason.DISCARDED);

                return InteractionResult.SUCCESS;
            } else {
                player.displayClientMessage(Component.translatable("item.generations_core.zygarde_cube.full"), false);
            }
        }

        return InteractionResult.FAIL;
    }


    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }
}
