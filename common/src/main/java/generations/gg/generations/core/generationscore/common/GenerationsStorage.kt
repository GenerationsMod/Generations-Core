package generations.gg.generations.core.generationscore.common

import earth.terrarium.common_storage_lib.data.DataManagerRegistry
import earth.terrarium.common_storage_lib.item.util.ItemStorageData

object GenerationsStorage {
    val registry = DataManagerRegistry(GenerationsCore.MOD_ID)

    val item_contents = registry.builder(ItemStorageData.DEFAULT).serialize(ItemStorageData.CODEC).buildAndRegister().componentType()
}