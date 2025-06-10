package generations.gg.generations.core.generationscore.common

import earth.terrarium.common_storage_lib.data.DataManagerRegistry
import earth.terrarium.common_storage_lib.item.util.ItemStorageData

object GenerationsStorage {
    val registry = DataManagerRegistry(GenerationsCore.MOD_ID)

    val REGI_ORBS = registry.builder(ItemStorageData.DEFAULT).serialize(ItemStorageData.CODEC).networkSerializer(ItemStorageData.NETWORK_CODEC).buildAndRegister("regi_orbs")
    val ITEM_CONTENTS = registry.builder(ItemStorageData.DEFAULT).serialize(ItemStorageData.CODEC).withDataComponent().buildAndRegister("inventory")
    val IMBUED = registry.builder(ItemStorageData.DEFAULT).serialize(ItemStorageData.CODEC).withDataComponent().buildAndRegister("imbued")

    fun init() {
        registry.init()
    }
}