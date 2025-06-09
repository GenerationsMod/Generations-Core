package generations.gg.generations.core.generationscore.common.world.level.block.entities.generic

import com.cobblemon.mod.common.util.asTranslated
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsCookers
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.SmokerBlockEntity
import net.minecraft.world.level.block.state.BlockState

class GenericSmokerBlockEntity(pos: BlockPos, state: BlockState) : SmokerBlockEntity(pos, state), GenerationsCookers {
    private val name = state.block.descriptionId.replace("block.generations_core.", "")

    override fun getDefaultName(): Component = "container.$name".asTranslated()

    override fun getType(): BlockEntityType<*> = GenerationsBlockEntities.GENERIC_SMOKER.get()

    override fun isValidBlockState(blockState: BlockState): Boolean = GenerationsBlockEntities.GENERIC_SMOKER.get().isValid(blockState)
}