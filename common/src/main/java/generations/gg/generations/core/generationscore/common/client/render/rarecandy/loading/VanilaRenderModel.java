package generations.gg.generations.core.generationscore.common.client.render.rarecandy.loading;

import gg.generations.rarecandy.pokeutils.BlendType;
import gg.generations.rarecandy.renderer.components.RenderObject;
import gg.generations.rarecandy.renderer.model.RenderModel;
import gg.generations.rarecandy.renderer.model.material.Material;
import gg.generations.rarecandy.renderer.pipeline.Pipeline;
import gg.generations.rarecandy.renderer.rendering.ObjectInstance;
import gg.generations.rarecandy.renderer.rendering.RenderStage;
import net.minecraft.client.renderer.MultiBufferSource;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class VanilaRenderModel implements RenderModel {
    public static final Matrix3f UV_TRANSFORM = new Matrix3f();
    public static final Vector3f UV_VECTOR = new Vector3f();

    public static final Matrix4f SKELETAL_TRANSFORM = new Matrix4f();
    public static final Vector4f SKELETAL_VECTOR = new Vector4f();

    private MultiBufferSource.BufferSource bufferSource;

    @Override
    public void runDrawCalls() {

    }

    @Override
    public Vector3f getDimensions() {
        return null;
    }

    @Override
    public <T extends RenderObject> void render(List<ObjectInstance> instances, T object) {
        if(bufferSource != null) {
            Map<RenderStage, Map<Material, List<Consumer<Pipeline>>>> map = new HashMap<>();

//            bufferSource.getBuffer(RenderType.entityTranslucentEmissive())

            for (ObjectInstance instance : instances) {

                if (!object.shouldRender(instance)) {
                    Material material = object.getMaterial(instance.variant());
                    RenderStage stage = RenderStage.SOLID;
                    if (material.blendType() != BlendType.None) {
                        stage = RenderStage.TRANSPARENT;
                    }

                    Map<Material, List<Consumer<Pipeline>>> stages = map.computeIfAbsent(stage, (s) -> new HashMap<>());
                    (stages.computeIfAbsent(material, (a) -> new ArrayList<>())).add((pipeline) -> {
                        pipeline.updateOtherUniforms(instance, object);
                        pipeline.updateTexUniforms(instance, object);
                        this.runDrawCalls();
                    });
                }
            }

//            map.getOrDefault(RenderStage.SOLID, Collections.emptyMap()).forEach(GLModel::render);
//            map.getOrDefault(RenderStage.TRANSPARENT, Collections.emptyMap()).forEach(GLModel::render);
        }
    }

    @Override
    public <T extends RenderObject> void render(ObjectInstance objectInstance, T t) {

    }

    @Override
    public void close() throws IOException {

    }

    public MultiBufferSource.BufferSource getBufferSource() {
        return bufferSource;
    }

    public void setBufferSource(MultiBufferSource.BufferSource bufferSource) {
        this.bufferSource = bufferSource;
    }
}
