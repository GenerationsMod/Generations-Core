package generations.gg.generations.core.generationscore.fabric.datagen

import generations.gg.generations.core.generationscore.common.world.item.RecordSongs
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsPaintings
import generations.gg.generations.core.generationscore.fabric.datagen.lang.GeneralLang
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.item.JukeboxSong

class DataGen : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack = fabricDataGenerator.createPack()
        FabricTagsDataGen.init(pack)

        pack.addProvider { fabricDataOutput, _ -> ArmorModelProvider(fabricDataOutput) }
//        pack.addProvider { output, lookup -> GeneralLang(output, lookup) }
        //        System.out.println("Outputting: " + Path.of("../../common/src/generated/resources").toAbsolutePath());
    }

    override fun buildRegistry(registryBuilder: RegistrySetBuilder) {
        registryBuilder.add(Registries.JUKEBOX_SONG, RecordSongs::bootstrap)
        registryBuilder.add(Registries.PAINTING_VARIANT, GenerationsPaintings::init)
    }
}
