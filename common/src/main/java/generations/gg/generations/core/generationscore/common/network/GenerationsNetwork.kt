package generations.gg.generations.core.generationscore.common.network

import com.cobblemon.mod.common.client.net.spawn.SpawnExtraDataEntityHandler
import com.cobblemon.mod.common.net.PacketRegisterInfo
import generations.gg.generations.core.generationscore.common.network.packets.*
import generations.gg.generations.core.generationscore.common.network.packets.shop.*
import generations.gg.generations.core.generationscore.common.network.packets.statue.S2COpenStatueEditorScreenHandler
import generations.gg.generations.core.generationscore.common.network.packets.statue.S2COpenStatueEditorScreenPacket
import generations.gg.generations.core.generationscore.common.network.packets.statue.UpdateStatueHandler
import generations.gg.generations.core.generationscore.common.network.packets.statue.UpdateStatuePacket
import generations.gg.generations.core.generationscore.common.network.spawn.SpawnStatuePacket
import generations.gg.generations.core.generationscore.common.world.shop.ShopPresetRegistrySyncPacket
import generations.gg.generations.core.generationscore.common.world.shop.ShopRegistrySyncPacket

object GenerationsNetwork {

    val s2cPayloads = generateS2CPacketInfoList()
    val c2sPayloads = generateC2SPacketInfoList()

    private fun generateS2CPacketInfoList(): List<PacketRegisterInfo<*>> {
        val list = mutableListOf<PacketRegisterInfo<*>>()

        list.add(PacketRegisterInfo(S2COpenMailEditScreenPacket.ID, S2COpenMailEditScreenPacket::decode, S2COpenMailEditScreenPacketHandler))
        list.add(PacketRegisterInfo(S2COpenMailPacket.ID, S2COpenMailPacket::decode, S2COpenMailPacketHandler))
        list.add(PacketRegisterInfo(S2CUnlockReloadPacket.ID, S2CUnlockReloadPacket::decode, UnlockReloadPacketHandler))
        list.add(PacketRegisterInfo(S2COpenStatueEditorScreenPacket.ID, S2COpenStatueEditorScreenPacket::decode, S2COpenStatueEditorScreenHandler))
//        list.add(PacketRegisterInfo(ShopRegistrySyncPacket.ID, ShopRegistrySyncPacket::decode, DataRegistrySyncPacketHandler()))
//        list.add(PacketRegisterInfo(ShopPresetRegistrySyncPacket.ID, ShopPresetRegistrySyncPacket::decode, DataRegistrySyncPacketHandler()))
        list.add(PacketRegisterInfo(S2COpenShopPacket.ID, S2COpenShopPacket::decode, S2COpenShopHandler))
        list.add(PacketRegisterInfo(S2CSyncPlayerMoneyPacket.ID, ::S2CSyncPlayerMoneyPacket, S2CSyncPlayerMoneyHandler))
        list.add(PacketRegisterInfo(SpawnStatuePacket.ID, SpawnStatuePacket::decode, SpawnExtraDataEntityHandler()))
        list.add(PacketRegisterInfo(S2CPlaySoundPacket.ID, S2CPlaySoundPacket::decode, S2CPlaySoundHandler))

        return list;
    }

    private fun generateC2SPacketInfoList(): MutableList<PacketRegisterInfo<*>> {
        val list = mutableListOf<PacketRegisterInfo<*>>()


        list.add(PacketRegisterInfo(C2STogglePacket.ID, C2STogglePacket::decode, C2SToggleHandler))
        list.add(PacketRegisterInfo(C2SEditMailPacket.ID, C2SEditMailPacket::decode, C2SEditMailHandler))
        list.add(PacketRegisterInfo(C2SShopItemPacket.ID, C2SShopItemPacket::decode, C2SShopItemHandler))
        list.add(PacketRegisterInfo(HeadPatPacket.ID, ::HeadPatPacket, HeadPatPacketHandler))

        list.add(PacketRegisterInfo(UpdateStatuePacket.PROPERTIES_ID, UpdateStatuePacket.Companion::propertiesDecode, UpdateStatueHandler.Properties))
        list.add(PacketRegisterInfo(UpdateStatuePacket.LABEL_ID, UpdateStatuePacket.Companion::labelDecode, UpdateStatueHandler.Label))
        list.add(PacketRegisterInfo(UpdateStatuePacket.SCALE_ID, UpdateStatuePacket.Companion::scaleDecode, UpdateStatueHandler.Scale))
        list.add(PacketRegisterInfo(UpdateStatuePacket.POSE_TYPE_ID, UpdateStatuePacket.Companion::poseTypeDecode, UpdateStatueHandler.PoseType))
        list.add(PacketRegisterInfo(UpdateStatuePacket.STATIC_TOGGLE_ID, UpdateStatuePacket.Companion::staticToggleDecode, UpdateStatueHandler.StaticToggle))
        list.add(PacketRegisterInfo(UpdateStatuePacket.STATIC_PARTIAL_ID, UpdateStatuePacket.Companion::staticPartialDecode, UpdateStatueHandler.StaticPartial))
        list.add(PacketRegisterInfo(UpdateStatuePacket.STATIC_AGE_ID, UpdateStatuePacket.Companion::staticAgeDecode, UpdateStatueHandler.StaticAge))
        list.add(PacketRegisterInfo(UpdateStatuePacket.INTERACTABLE_ID, UpdateStatuePacket.Companion::interactableDecode, UpdateStatueHandler.Interactable))
        list.add(PacketRegisterInfo(UpdateStatuePacket.MATERIAL_ID, UpdateStatuePacket.Companion::materialDecode, UpdateStatueHandler.Material))
        list.add(PacketRegisterInfo(UpdateStatuePacket.ORIENTATION_ID, UpdateStatuePacket.Companion::orientationDecode, UpdateStatueHandler.Orientation))
        return list
    }
}