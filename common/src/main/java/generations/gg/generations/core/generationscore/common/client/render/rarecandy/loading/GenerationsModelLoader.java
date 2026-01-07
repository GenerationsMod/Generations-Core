package generations.gg.generations.core.generationscore.common.client.render.rarecandy.loading;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CompiledModel;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CobblemonInstance;
import gg.generations.rarecandy.pokeutils.MaterialReference;
import gg.generations.rarecandy.pokeutils.PixelAsset;
import gg.generations.rarecandy.renderer.components.MultiRenderObject;
import gg.generations.rarecandy.renderer.loading.ModelLoader;
import gg.generations.rarecandy.renderer.model.material.Material;
import org.joml.Matrix4f;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GenerationsModelLoader {

    public static GenerationsMultiRenderObject compiledModelMethod(CompiledModel model, InputStream stream, String name) {
        return (GenerationsMultiRenderObject) ModelLoader.createObject(
                GenerationsMultiRenderObject::new,
                () -> new PixelAsset(stream, name),
                GenerationsModelLoader::process, object -> {
                    model.guiInstance = new CobblemonInstance();
                    model.guiInstance.link(object);
                    if(object.scale == 0f) object.scale = 1.0f;

                    if(GenerationsCore.CONFIG.client.logModelLoading) GenerationsCore.LOGGER.info("Done Loading: " + name);
                }
        );
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

        int effect = 0;
        if (reference.effect != null) {
            effect = switch (reference.effect) {
                case "galaxy" -> 1;
                case "pastel" -> 2;
                case "shadow" -> 3;
                case "sketch" -> 4;
                case "vintage" -> 5;
                default -> 0;
            };
        }

        return new Material(
                images,
                reference.values,
                reference.cull,
                reference.blend,
                method,
                effect
        );
    }
}