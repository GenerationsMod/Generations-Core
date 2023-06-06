package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.mojang.math.Axis;
import de.javagl.jgltf.model.GltfModel;
import gg.generations.pokeutils.PixelAsset;
import gg.generations.rarecandy.components.MeshObject;
import gg.generations.rarecandy.components.MultiRenderObject;
import gg.generations.rarecandy.loading.ModelLoader;
import gg.generations.rarecandy.pipeline.Pipeline;
import gg.generations.rarecandy.rendering.ObjectInstance;
import gg.generations.rarecandy.storage.ObjectManager;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Represents a compiled model which can be rendered
 */
public class CompiledModel {
    public final MultiRenderObject<MeshObject> renderObject;
    public CompiledModel(ResourceLocation a, InputStream stream, Function<String, Pipeline> pipeline, Function<GltfModel, Supplier<MeshObject>> supplier) {
        var loader = ModelRegistry.getRareCandy().getLoader();
        this.renderObject = loader.createObject(
                () -> new PixelAsset(stream, a.toString()),
                (gltfModel, smdFileMap, pkxFileMap, textures, config, object) -> {
                    var glCalls = new ArrayList<Runnable>();
                    try {
                        ModelLoader.create2(object, gltfModel, smdFileMap, pkxFileMap,textures, config, glCalls, pipeline, supplier.apply(gltfModel));
                    } catch (NullPointerException e) {
//                        RareCandyTest.LOGGER.error("Catching exception reading model %s.".formatted(a));
                        e.printStackTrace();
                    }
                    return glCalls;
                },
                object -> {}
        );
    }

    public void render(ObjectInstance instance, Matrix4f projectionMatrix) {
        render(instance, projectionMatrix, false, ModelRegistry.getRareCandy().objectManager);
    }

    public void render(ObjectInstance instance, Matrix4f projectionMatrix, boolean isYNotUp, ObjectManager objectManager) {
        if (!renderObject.isReady()) return;
        Minecraft.getInstance().getProfiler().push("create_model_instance");
        MinecraftClientGameProvider.projMatrix = projectionMatrix;
        var modelMatrix = new Matrix4f();
        modelMatrix.identity();
        if (isYNotUp) modelMatrix.rotate(Axis.XP.rotationDegrees(90));
        objectManager.add(this.renderObject, instance);
        Minecraft.getInstance().getProfiler().pop();
    }
}