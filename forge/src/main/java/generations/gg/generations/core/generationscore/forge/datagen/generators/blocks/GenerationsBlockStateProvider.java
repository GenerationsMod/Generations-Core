package generations.gg.generations.core.generationscore.forge.datagen.generators.blocks;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerationsBlockStateProvider extends BlockStateProvider {
    private final PackOutput packOutput;
    private final ExistingFileHelper existingFileHelper;
    List<Proxied> providers;
    @SafeVarargs
    public GenerationsBlockStateProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper, Function<GenerationsBlockStateProvider, Proxied>... providers) {
        super(packOutput, GenerationsCore.MOD_ID, existingFileHelper);
        this.packOutput = packOutput;
        this.existingFileHelper = existingFileHelper;
        this.providers = Stream.of(providers).map(provider -> provider.apply(this)).collect(Collectors.toList());
    }

    @Override
    protected void registerStatesAndModels() {
        for (Proxied provider : providers) {
            provider.registerStatesAndModels();
        }
    }

    public PackOutput getPackOutput() {
        return packOutput;
    }

    public ExistingFileHelper getExistingFileHelper() {
        return existingFileHelper;
    }

    public abstract static class Proxied extends BlockStateProvider {
        private final GenerationsBlockStateProvider provider;

        public Proxied(GenerationsBlockStateProvider provider) {
            super(provider.getPackOutput(), GenerationsCore.MOD_ID, provider.getExistingFileHelper());
            this.provider = provider;
        }

        @Override
        public BlockModelProvider models() {
            return provider.models();
        }

        @Override
        public ItemModelProvider itemModels() {
            return provider.itemModels();
        }

        public VariantBlockStateBuilder getVariantBuilder(Block b) {
            return provider.getVariantBuilder(b);
        }

        public MultiPartBlockStateBuilder getMultipartBuilder(Block b) {
            return provider.getMultipartBuilder(b);
        }

        public ResourceLocation key(Block block) {
            return ForgeRegistries.BLOCKS.getKey(block);
        }

        public boolean registered(@Nullable Block block) {
            return provider.registeredBlocks.containsKey(block);
        }

        public abstract void registerStatesAndModels();
    }

}
