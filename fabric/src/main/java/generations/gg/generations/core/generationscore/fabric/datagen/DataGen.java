package generations.gg.generations.core.generationscore.fabric.datagen;

import generations.gg.generations.core.generationscore.common.world.item.RecordSongs;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class DataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        FabricTagsDataGen.init(pack);
        pack.addProvider(ArmorModelProvider::new);
//        System.out.println("Outputting: " + Path.of("../../common/src/generated/resources").toAbsolutePath());
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.JUKEBOX_SONG, RecordSongs::bootstrap);
    }
}
