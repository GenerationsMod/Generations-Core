package generations.gg.generations.core.generationscore.common.client.render.rarecandy.loading;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.client.GenerationsTextureLoader;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CompiledModel;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CobblemonInstance;
import gg.generations.rarecandy.pokeutils.MaterialReference;
import gg.generations.rarecandy.pokeutils.PixelAsset;
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader;
import gg.generations.rarecandy.renderer.components.MultiRenderObject;
import gg.generations.rarecandy.renderer.loading.ModelLoader;
import gg.generations.rarecandy.renderer.model.material.Material;
import gg.generations.rarecandy.renderer.textures.Texture;
import org.joml.Matrix4f;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GenerationsModelLoader {

    public static GenerationsMultiRenderObject compiledModelMethod(CompiledModel model, InputStream stream, String name) {
        return (GenerationsMultiRenderObject) ModelLoader.createObject(
                GenerationsMultiRenderObject::new,
                () -> new PixelAsset(stream, name),
                GenerationsModelLoader::readImages,
                GenerationsModelLoader::process, object -> {
                    model.guiInstance = new CobblemonInstance();
                    model.guiInstance.link(object);
                    if (object.scale == 0f) object.scale = 1.0f;

                    if (GenerationsCore.CONFIG.client.logModelLoading)
                        GenerationsCore.LOGGER.info("Done Loading: " + name);
                }
        );
    }

    public static void readImages(PixelAsset asset, List<String> imageNames) {
        var images = asset.getImageFiles();


        for (Map.Entry<String, byte[]> entry : images) {
            var key = entry.getKey();

            var index = imageNames.contains(key);

            if (!index) continue;

            ITextureLoader.instance().register(key, key, entry.getValue());
        }
    }


    public static Material process(MaterialReference reference, List<String> imageNames) {
        var images = reference.images.toArray(imageNames);

        int method = 0;
        if (reference.shader != null) {
            method = switch (reference.shader) {
                case "layered" -> 1;
                case "masked" -> 2;
                default -> 0;
            };
        } else {
            System.out.println();
        }

        return new Material(
                images,
                reference.values,
                reference.cull,
                reference.blend,
                method
        );
    }
}