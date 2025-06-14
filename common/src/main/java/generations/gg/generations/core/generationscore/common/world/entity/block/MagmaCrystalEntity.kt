package generations.gg.generations.core.generationscore.common.world.entity.block

import generations.gg.generations.core.generationscore.common.util.GenerationsUtils.parseProperties
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.ThrowableItemProjectile
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult

class MagmaCrystalEntity : ThrowableItemProjectile {
    constructor(type: EntityType<MagmaCrystalEntity>, level: Level) : super(type, level)

    constructor(level: Level, entity: LivingEntity) : super(GenerationsEntities.MAGMA_CRYSTAL, entity, level)

    override fun getDefaultItem(): Item {
        return GenerationsItems.MAGMA_CRYSTAL
    }

    override fun onHit(result: HitResult) {
        if (result.type == HitResult.Type.BLOCK && !level().isClientSide()) {
            val player = this.owner
            if (player is ServerPlayer) {
                val mat = level().getBlockState((result as BlockHitResult).blockPos).block

                if ((mat === Blocks.LAVA || level().getBlockState(result.blockPos.above()).block === Blocks.LAVA)) {
                    PokemonUtil.spawn(properties, level(), result.getLocation())
                    discard()
                } else if (!level().isClientSide() && mat !== Blocks.AIR) {
                    player.addItem(ItemStack(GenerationsItems.MAGMA_CRYSTAL))
                    discard()
                }
            }
        }
    }

    companion object {
        private val properties = parseProperties("heatran level=70")
    }
}
