package generations.gg.generations.core.generationscore.common.world.container

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.MenuProvider

interface ExtendedMenuProvider : MenuProvider {
    fun saveExtraData(buffer: FriendlyByteBuf)
}
