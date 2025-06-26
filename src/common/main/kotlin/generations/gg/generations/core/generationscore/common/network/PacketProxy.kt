package generations.gg.generations.core.generationscore.common.network

import generations.gg.generations.core.generationscore.common.network.packets.S2COpenMailEditScreenPacket
import generations.gg.generations.core.generationscore.common.network.packets.S2COpenMailPacket
import generations.gg.generations.core.generationscore.common.network.packets.S2CPlaySoundPacket
import generations.gg.generations.core.generationscore.common.network.packets.S2CUnlockReloadPacket
import generations.gg.generations.core.generationscore.common.network.packets.shop.S2COpenShopPacket
import generations.gg.generations.core.generationscore.common.network.packets.shop.S2CSyncPlayerMoneyPacket
import generations.gg.generations.core.generationscore.common.network.packets.statue.S2COpenStatueEditorScreenPacket
import generations.gg.generations.core.generationscore.common.network.packets.statue.UpdateStatueHandler
import generations.gg.generations.core.generationscore.common.network.packets.statue.UpdateStatuePacket
import generations.gg.generations.core.generationscore.common.network.spawn.SpawnStatuePacket
import generations.gg.generations.core.generationscore.common.world.shop.ShopPresetRegistrySyncPacket
import generations.gg.generations.core.generationscore.common.world.shop.ShopRegistrySyncPacket
import java.util.function.BiConsumer
import java.util.function.Consumer

open class PacketProxy {

    open val processS2COpenMailEditScreenPacket : Consumer<S2COpenMailEditScreenPacket> = Consumer {}
    open val processS2COpenMailPacket : Consumer<S2COpenMailPacket> = Consumer {}
    open val processS2CUnlockReloadPacket : Consumer<S2CUnlockReloadPacket> = Consumer {}
    open val processS2COpenStatueEditorScreenPacket : Consumer<S2COpenStatueEditorScreenPacket> = Consumer {}
    open val processShopRegistrySyncPacket : Consumer<ShopRegistrySyncPacket> = Consumer {}
    open val processShopPresetRegistrySyncPacket : Consumer<ShopPresetRegistrySyncPacket> = Consumer {}
    open val processS2COpenShopPacket : Consumer<S2COpenShopPacket> = Consumer {}
    open val processS2CSyncPlayerMoneyPacket : Consumer<S2CSyncPlayerMoneyPacket> = Consumer {}
    open val processSpawnStatuePacket : Consumer<SpawnStatuePacket> = Consumer {}
    open val processS2CPlaySoundPacket : Consumer<S2CPlaySoundPacket> = Consumer {}

    open fun <V, T: UpdateStatuePacket<V, T>> processStatueUpdate(packet: T, handler: UpdateStatueHandler<V, T>) {}
}
