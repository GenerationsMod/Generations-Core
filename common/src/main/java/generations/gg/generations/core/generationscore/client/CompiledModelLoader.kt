package generations.gg.generations.core.generationscore.client

import generations.gg.generations.core.generationscore.client.render.rarecandy.CompiledModel
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry
import kotlinx.coroutines.*
import net.minecraft.resources.FileToIdConverter
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.ResourceManagerReloadListener

class CompiledModelLoader : ResourceManagerReloadListener {
    var loading: Job? = null


    override fun onResourceManagerReload(resourceManager: ResourceManager) {
        loading?.cancel("Reloading in progress.")

        GenerationsTextureLoader.initialize(resourceManager)
        ModelRegistry.LOADER.invalidateAll()
        ModelRegistry.LOADER.cleanUp()



//        var list = (MODEL.listMatchingResources(resourceManager).toList() + MODEL_COBBLEMON.listMatchingResources(resourceManager).toList())
//
//        loading = CoroutineScope(Dispatchers.IO).launch {

//            println("Loading ${list.size} Models")

//            var time = System.currentTimeMillis();
//
//            var models = mutableMapOf<ResourceLocation, CompiledModel>()
//
//            var size = models.size - 1
//
//            list.forEach {pair ->
//                var time1 = System.currentTimeMillis();
//                println("Loading $i/$size ${pair.first}")
//                CompiledModel.of(pair.first, pair.second).run { ModelRegistry.LOADER.put(pair.first, this) }
//                println("${pair.first} completed in ${(System.currentTimeMillis() - time1) / 1000f}")
//            }

//            val difference = System.currentTimeMillis() - time;

//            println("Models completed in ${difference / 1000f}")
//        }
    }

//    companion object {
//        var MODEL = FileToIdConverter("models", "pk")
//        var MODEL_COBBLEMON = FileToIdConverter("bedrock/pokemon/models", "pk")
//    }
}
