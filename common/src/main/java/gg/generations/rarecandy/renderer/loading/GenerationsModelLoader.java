package gg.generations.rarecandy.renderer.loading;

import generations.gg.generations.core.generationscore.client.render.rarecandy.CompiledModel;
import generations.gg.generations.core.generationscore.client.render.rarecandy.CobblemonInstance;
import gg.generations.rarecandy.pokeutils.PixelAsset;
import gg.generations.rarecandy.renderer.components.MeshObject;
import gg.generations.rarecandy.renderer.components.MultiRenderObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.function.Supplier;

public class GenerationsModelLoader extends ModelLoader {
    public GenerationsModelLoader(int numThreads) {
        super(numThreads);
    }
    public MultiRenderObject<MeshObject> compiledModelMethod(CompiledModel model, InputStream stream, Supplier<MeshObject> supplier, String name, boolean requiresVariantTexture) {
        return createObject(
                () -> new PixelAsset(stream, name),
                (gltfModel, smdFileMap, pkxFileMap, gfFileMap, textures, config, object) -> {
                    var glCalls = new ArrayList<Runnable>();
                    try {
                        ModelLoader.create2(object, gltfModel, smdFileMap, pkxFileMap, gfFileMap, textures, config, glCalls, supplier);
                    } catch (Exception e) {
                        System.out.println("Oh no! Model : " + name + " didn't properly load!");
                        e.printStackTrace();
                    }
                    return glCalls;
                },
                object -> {
                    var manager = Minecraft.getInstance().getTextureManager();

                    if(requiresVariantTexture) {
                        if(!object.objects.isEmpty() && !object.objects.get(0).availableVariants().isEmpty()) {

                            object.availableVariants().forEach(s -> manager.register(new ResourceLocation("pk:" + s), new AbstractTexture() {
                                @Override
                                public void load(@NotNull ResourceManager resourceManager) {

                                }
                            }));
                        } else {
                            System.out.println("Warning %s lacks variants: " + name.toString());
                        }
                    }
                    model.guiInstance = new CobblemonInstance(new Matrix4f(), new Matrix4f(), null);
                    model.guiInstance.link(object);
                    if(object.scale == 0f) object.scale = 1.0f;
                }
        );
    }
}