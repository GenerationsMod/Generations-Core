package generations.gg.generations.core.generationscore.common.client.render.rarecandy.loading;

import com.mojang.blaze3d.vertex.VertexConsumer;
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockLightValueProvider;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ITextureWithResourceLocation;
import gg.generations.rarecandy.pokeutils.BlendType;
import gg.generations.rarecandy.renderer.animation.AnimationController;
import gg.generations.rarecandy.renderer.animation.Transform;
import gg.generations.rarecandy.renderer.components.RenderObject;
import gg.generations.rarecandy.renderer.model.RenderModel;
import gg.generations.rarecandy.renderer.model.material.Material;
import gg.generations.rarecandy.renderer.rendering.ObjectInstance;
import gg.generations.rarecandy.renderer.storage.AnimatedObjectInstance;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.joml.*;

import java.io.IOException;
import java.util.List;

public class VanilaRenderModel implements RenderModel {
    public static final Matrix3f UV_TRANSFORM = new Matrix3f();
    public static final Vector2f UV_VECTOR = new Vector2f();

    public static final Matrix4f SKELETAL_TRANSFORM = new Matrix4f();
    public static final Matrix4f SKELETAL_TEMP = new Matrix4f();
    public static final Vector4f SKELETAL_VECTOR = new Vector4f();

    public static final Vector3f WHITE = new Vector3f(1.0f, 1.0f, 1.0f);

    private final int[] indicies;
    private final float[] positions;
    private final float[] uvs;
    private final float[] normals;
    private final int[] boneIds;
    private final float[] boneWeights;
    private final int indexSize;

    private MultiBufferSource bufferSource;


    public VanilaRenderModel(int[] indicies, float[] positions, float[] uvs, float[] normals, int[] boneIds, float[] boneWeights, int indexSize) {
        this.indicies = indicies;
        this.positions = positions;
        this.uvs = uvs;
        this.normals = normals;
        this.boneIds = boneIds;
        this.boneWeights = boneWeights;
        this.indexSize = indexSize;
    }

    @Override
    public void runDrawCalls() {
    }

    public void render(VertexConsumer consumer, ObjectInstance instance, Transform transform, Matrix4f[] transforms) {
        var tint = instance instanceof ModelContextProviders.TintProvider provider ? provider.getTint() : WHITE;
        var light = instance instanceof BlockLightValueProvider provider ? provider.getLight() : LightTexture.FULL_BRIGHT;

        if(tint == null) tint = WHITE;
        var matrix = instance.transformationMatrix();

        for (int i = 0; i < indexSize; i += 3) {
            addVertex(consumer, i, tint, light, matrix, transform, transforms);
            addVertex(consumer, i, tint, light, matrix, transform, transforms);
            addVertex(consumer, i + 1, tint, light, matrix, transform, transforms);
            addVertex(consumer, i + 2, tint, light, matrix, transform, transforms);
        }
    }

    private void addVertex(VertexConsumer consumer, int i, Vector3f tint, int light, Matrix4f matrix4f, Transform transform, Matrix4f[] transforms) {
        var bufferIndex = indicies[i];

        var posIndex = bufferIndex * 3;
        var boneIndex = bufferIndex * 4;
        var uvIndex = bufferIndex * 2;

        UV_VECTOR.set(uvs[uvIndex], uvs[uvIndex+1]).mul(transform.scale()).add(transform.offset());

        SKELETAL_VECTOR.set(positions[posIndex],
                positions[posIndex + 1],
                positions[posIndex + 2], 1.0);

        SKELETAL_TRANSFORM.set(transforms[boneIds[boneIndex]]).scale(boneWeights[boneIndex]);
        SKELETAL_TEMP.identity().set(SKELETAL_TRANSFORM);
        SKELETAL_TRANSFORM.set(transforms[boneIds[boneIndex+1]]).scale(boneWeights[boneIndex+1]);
        SKELETAL_TEMP.add(SKELETAL_TRANSFORM);
        SKELETAL_TRANSFORM.set(transforms[boneIds[boneIndex+2]]).scale(boneWeights[boneIndex+2]);
        SKELETAL_TEMP.add(SKELETAL_TRANSFORM);
        SKELETAL_TRANSFORM.set(transforms[boneIds[boneIndex+3]]).scale(boneWeights[boneIndex+3]);
        SKELETAL_TEMP.add(SKELETAL_TRANSFORM);

        SKELETAL_TRANSFORM.set(matrix4f);

        SKELETAL_TRANSFORM.mul(SKELETAL_TEMP);

        SKELETAL_TRANSFORM.transform(SKELETAL_VECTOR);

        consumer.vertex(SKELETAL_VECTOR.x(),
                        SKELETAL_VECTOR.y(),
                        SKELETAL_VECTOR.z())
                .color(tint.x, tint.y, tint.z, 1.0f)
                .uv(UV_VECTOR.x(), UV_VECTOR.y())
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(light)
                .normal(normals[posIndex], normals[posIndex+1], normals[posIndex+2])
                .endVertex();

        TextComponent
    }

    @Override
    public Vector3f getDimensions() {
        return null;
    }

    @Override
    public <T extends RenderObject> void render(List<ObjectInstance> instances, T object) {

    }

    @Override
    public <T extends RenderObject> void render(ObjectInstance instance, T object) {

    }

    public <T extends RenderObject> void render(ObjectInstance instance, T object, Matrix4f[] transforms) {
        if(bufferSource != null) {

            if (!object.shouldRender(instance)) {

                Material material = object.getMaterial(instance.variant());
                var texture = material.getDiffuseTexture();

                if(texture instanceof ITextureWithResourceLocation textureWithResourceLocation) {
                    Transform transform = object.getTransform(instance.variant());

                    if (instance instanceof AnimatedObjectInstance animatedInstance) {
                        var t = animatedInstance.getTransform(material.getMaterialName());

                        if (t != null) {
                            transform = t;
                        }
                    }

                    var activeConsumer = bufferSource.getBuffer(material.blendType() != BlendType.None ? RenderType.entitySolid(textureWithResourceLocation.getLocation()) : RenderType.entityTranslucent(textureWithResourceLocation.getLocation()));
                    render(activeConsumer, instance, transform, transforms);

                }
            }
        }
    }

    @Override
    public void close() throws IOException {
    }

    public MultiBufferSource getBufferSource() {
        return bufferSource;
    }

    public void setBufferSource(MultiBufferSource bufferSource) {
        this.bufferSource = bufferSource;
    }
}
