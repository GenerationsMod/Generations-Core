package generations.gg.generations.core.generationscore.common.world.entity

import com.cobblemon.mod.common.api.text.text
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockObjectInstance
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.ZygardeCubeItem
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
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


    override fun defineSynchedData() {
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        var player = source.entity.instanceOrNull<ServerPlayer>() ?: return true

        var stack = player.mainHandItem.takeIf { it.`is`(GenerationsItems.ZYGARDE_CUBE.get()) } ?: return true

        if (stack.damageValue != ZygardeCubeItem.FULL) {
            stack.damageValue += 1
            player.displayClientMessage(
                Component.translatable("item.generations_core.zygarde_cube.tooltip.cell_add"),
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
        } else {
            player.displayClientMessage(
                Component.translatable("item.generations_core.zygarde_cube.tooltip.cell_full"),
                false
            )
        }

        return false
    }

    override fun isPushable(): Boolean {
        return true
    }

    override fun isPickable(): Boolean {
        return true
    }
}
