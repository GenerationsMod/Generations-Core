package generations.gg.generations.core.generationscore.common.world.level.block.shrines

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.AbundantShrineBlockEntity
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.entity.BlockEntityType
import java.util.function.Consumer

class AbundantShrineBlock(properties: Properties) : ShrineBlock<AbundantShrineBlockEntity>(
    properties,
    GenerationsBlockEntityModels.ABUNDANT_SHRINE
) {
    override val blockEntityType: BlockEntityType<AbundantShrineBlockEntity>
        get() = GenerationsBlockEntities.ABUNDANT_SHRINE

    override fun codec(): MapCodec<AbundantShrineBlock> = CODEC

    companion object {
        private val playerConsumer = Consumer { p: ServerPlayer -> GenerationsUtils.giveItem(p, GenerationsItems.REVEAL_GLASS.defaultInstance) }
        val CODEC = simpleCodec(::AbundantShrineBlock)
    }
}
