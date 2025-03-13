package generations.gg.generations.core.generationscore.common.client.render.rarecandy.loading;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import gg.generations.rarecandy.renderer.components.RenderObject;
import gg.generations.rarecandy.renderer.model.RenderModel;
import gg.generations.rarecandy.renderer.model.material.PipelineRegistry;
import gg.generations.rarecandy.renderer.rendering.ObjectInstance;
import gg.generations.rarecandy.renderer.rendering.RenderStage;
import org.joml.Vector3f;

import java.util.List;

public class VanillaRenderModel implements RenderModel {
    private int[] indicies;
    private float[] positions;
    private float[] uvs;
    private float[] normals;
    private int[] boneIds;
    private float[] boneWeights;
    private int indexSize;

    public VanillaRenderModel(
            int[] indicies,
            float[] positions,
            float[] uvs,
            float[] normals,
            int[] boneIds,
            float[] boneWeights,
            int indexSize
    ) {
        this.indicies = indicies;
        this.positions = positions;
        this.uvs = uvs;
        this.normals = normals;
        this.boneIds = boneIds;
        this.boneWeights = boneWeights;
        this.indexSize = indexSize;
    }

    private static int index = 0;

    private static GenerationsBufferBuilder getBuffer() {
        return buffers[index];
    }

    private static void nextBuffer() {
        index = (index + 1) % 2;
    }



    public void runDrawCalls() {
    }

    public void render(GenerationsBufferBuilder consumer) {

        var i = 0;
        while (i < indexSize) {
            addVertex(consumer, i);
            addVertex(consumer, i + 1);
            addVertex(consumer, i + 2);
            i += 3;
        }
    }

    private void addVertex(
            GenerationsBufferBuilder consumer,
            int i
    ) {
        var bufferIndex = indicies[i];

        var posIndex = bufferIndex * 3;
        var boneIndex = bufferIndex * 4;
        var uvIndex = bufferIndex * 2;

        consumer
            .vertex(positions[posIndex], positions[posIndex + 1], positions[posIndex + 2])
            .uv(uvs[uvIndex], uvs[uvIndex + 1])
            .normal(normals[posIndex], normals[posIndex + 1], normals[posIndex + 2])
            .joints(boneIds[boneIndex], boneIds[boneIndex + 1], boneIds[boneIndex + 2], boneIds[boneIndex + 3])
            .weights(boneWeights[boneIndex], boneWeights[boneIndex + 1], boneWeights[boneIndex + 2], boneWeights[boneIndex + 3])
            .endVertex();
    }

    public Vector3f getDimensions() {
        return null;
    }

    public <T extends RenderObject> void render(RenderStage renderStage, List<ObjectInstance> list, T t) {}

    public <T extends RenderObject> void render(ObjectInstance instance, T object) {
        if (!object.shouldRender(instance)) {
            var material = object.getMaterial(instance.variant());

            var pipeline = PipelineRegistry.get(material.getPipeline());

            pipeline.bind(material);
            pipeline.updateOtherUniforms(instance, object);
            pipeline.updateTexUniforms(instance, object);

            var activeConsumer = getBuffer();

            activeConsumer.begin(VertexFormat.Mode.TRIANGLES, CUSTOM_FORMAT);
            render(activeConsumer);
            BufferUploader.draw(activeConsumer.end());
            nextBuffer();
            pipeline.unbind(material);
        }
    }

    public void close() {
    }

    private static GenerationsBufferBuilder[] buffers;

    private static final VertexFormatElement ELEMENT_POSITION = new VertexFormatElement(0, VertexFormatElement.Type.FLOAT, VertexFormatElement.Usage.POSITION, 3);
    private static final VertexFormatElement ELEMENT_UV = new VertexFormatElement(0, VertexFormatElement.Type.FLOAT, VertexFormatElement.Usage.UV, 2);
    private static final VertexFormatElement ELEMENT_NORMAL = new VertexFormatElement(0, VertexFormatElement.Type.BYTE, VertexFormatElement.Usage.NORMAL, 3);
    private static final VertexFormatElement ELEMENT_BONE_INDICES = new VertexFormatElement(0, VertexFormatElement.Type.BYTE, VertexFormatElement.Usage.GENERIC, 4);
    private static final VertexFormatElement ELEMENT_BONE_WEIGHTS = new VertexFormatElement(0, VertexFormatElement.Type.FLOAT, VertexFormatElement.Usage.GENERIC, 4);

    VertexFormat CUSTOM_FORMAT = new VertexFormat(
            ImmutableMap.<String, VertexFormatElement>builder()
                .put("positions", ELEMENT_POSITION)
                .put("texcoords", ELEMENT_UV)
                .put("inNormal", ELEMENT_NORMAL)
                .put("joints", ELEMENT_BONE_INDICES)
                .put("weights", ELEMENT_BONE_WEIGHTS)
                .build()
        );

        public static void initBuffers() {
            buffers = new GenerationsBufferBuilder[] { new GenerationsBufferBuilder(349526), new GenerationsBufferBuilder(349526) };
        }
        public static final Vector3f WHITE = new Vector3f(1.0f, 1.0f, 1.0f);
}
