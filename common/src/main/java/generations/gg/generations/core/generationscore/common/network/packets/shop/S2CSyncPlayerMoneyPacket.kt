package generations.gg.generations.core.generationscore.common.network.packets.shop

import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import java.math.BigDecimal

class S2CSyncPlayerMoneyPacket : NetworkPacket<S2CSyncPlayerMoneyPacket> {
    override val id: ResourceLocation = ID

    val balance: BigDecimal

    constructor(balance: BigDecimal) {
        this.balance = balance
    }

    constructor(buf: RegistryFriendlyByteBuf) {
        this.balance = GenerationsUtils.readBigDecimal(buf)
    }

    override fun encode(buf: RegistryFriendlyByteBuf) {
        GenerationsUtils.writeBigDecimal(buf, balance)
    }

    companion object {
        var ID: ResourceLocation = GenerationsCore.id("player_money_sync")
    }
}