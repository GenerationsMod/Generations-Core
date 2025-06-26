package generations.gg.generations.core.generationscore.common.world.recipe

import com.mojang.serialization.Codec
import generations.gg.generations.core.generationscore.common.world.level.block.entities.RksMachineBlockEntity
import generations.gg.generations.core.generationscore.common.world.recipe.RksResultType
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import java.util.function.Function

interface RksResult<U : RksResult<U>> {
    val stack: ItemStack

    fun type(): RksResultType<U>

    fun process(player: Player, rksMachineBlockEntity: RksMachineBlockEntity, stack: ItemStack) {}

    fun consumeTimeCapsules(): Boolean {
        return true
    }

    val isPokemon: Boolean

}
