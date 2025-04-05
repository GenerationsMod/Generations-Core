package generations.gg.generations.core.generationscore.common.world.entity

import com.cobblemon.mod.common.api.text.text
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockObjectInstance
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.ZygardeCubeItem
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import org.joml.Matrix4f

class ZygardeCellEntity : Entity {
    @JvmField
    var instance: BlockObjectInstance = BlockObjectInstance(Matrix4f(), Matrix4f(), null)

    constructor(level: Level) : super(GenerationsEntities.ZYGARDE_CELL.get(), level)

    constructor(entityType: EntityType<*>, level: Level) : super(entityType, level)


    override fun defineSynchedData(builder: SynchedEntityData.Builder) {}

    override fun readAdditionalSaveData(compound: CompoundTag) {
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
    }

    override fun interact(player: Player, hand: InteractionHand): InteractionResult {
        if(hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;
        val serverPlayer = player.instanceOrNull<ServerPlayer>() ?: return InteractionResult.PASS;
        val stack = player.mainHandItem.takeIf { it.`is`(GenerationsItems.ZYGARDE_CUBE.get()) } ?: return InteractionResult.PASS

        //Note: I'm treating Boolean? like a tristate here. True and null allow while false doesn't. This is to allow an alternate overflow message when taking a cell when full.
        val allowed = if(stack.damageValue != ZygardeCubeItem.FULL) true else if(GenerationsCore.CONFIG.legendary.enableZygardeCubeOverflow) null else false
        if (allowed != false) {
            stack.damageValue += 1
            serverPlayer.displayClientMessage(
                Component.translatable(if(allowed != null) "item.generations_core.zygarde_cube.tooltip.cell_add" else "item.generations_core.zygarde_cube.tooltip.cell_overflow"),
                false
            )
            level().playSound(
                null,
                blockPosition(),
                GenerationsSounds.ZYGARDE_CELL.get(),
                SoundSource.BLOCKS,
                0.2f,
                1.0f
            )


            remove(RemovalReason.DISCARDED)

            player.cooldowns.addCooldown(stack.item, 20)

            return InteractionResult.SUCCESS
        } else {
            player.displayClientMessage(
                Component.translatable("item.generations_core.zygarde_cube.tooltip.cell_full"),
                false
            )
        }

        return InteractionResult.PASS
    }

    override fun isPushable(): Boolean {
        return false
    }

    override fun isPickable(): Boolean {
        return true
    }
}
