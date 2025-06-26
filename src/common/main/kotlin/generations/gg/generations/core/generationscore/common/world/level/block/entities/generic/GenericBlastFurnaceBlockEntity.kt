package generations.gg.generations.core.generationscore.common.world.level.block.entities.generic

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsCookers
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.world.level.block.entity.BlastFurnaceBlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

class GenericBlastFurnaceBlockEntity(arg: BlockPos, arg2: BlockState) :
    BlastFurnaceBlockEntity(arg, arg2), GenerationsCookers {
    private val name = arg2.block.descriptionId.replace("block.generations_core.", "")

    override fun getDefaultName(): Component = Component.translatable("container.$name")

    override fun getType(): BlockEntityType<*> = GenerationsBlockEntities.GENERIC_BLAST_FURNACE.value()

    override fun isValidBlockState(blockState: BlockState): Boolean = GenerationsBlockEntities.GENERIC_BLAST_FURNACE.value().isValid(blockState)
}