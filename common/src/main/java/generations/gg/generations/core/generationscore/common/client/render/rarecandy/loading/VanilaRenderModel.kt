package generations.gg.generations.core.generationscore.common.client.render.rarecandy.loading

import com.mojang.blaze3d.vertex.BufferVertexConsumer
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.blaze3d.vertex.VertexFormatElement
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.TintProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockLightValueProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ITextureWithResourceLocation
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.VanillShaders
import gg.generations.rarecandy.renderer.animation.Transform
import gg.generations.rarecandy.renderer.components.RenderObject
import gg.generations.rarecandy.renderer.model.RenderModel
import gg.generations.rarecandy.renderer.rendering.ObjectInstance
import gg.generations.rarecandy.renderer.storage.AnimatedObjectInstance
import net.minecraft.client.renderer.LightTexture
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.texture.OverlayTexture
import org.joml.Matrix4f
import org.joml.Vector3f
import java.io.IOException

class VanilaRenderModel(
    private val indicies: IntArray,
    private val positions: FloatArray,
    private val uvs: FloatArray,
    private val normals: FloatArray,
    private val boneIds: IntArray,
    private val boneWeights: FloatArray,
    private val indexSize: Int,
) :
    RenderModel {
    var bufferSource: MultiBufferSource? = null


    override fun runDrawCalls() {
    }

    fun render(consumer: VertexConsumer, instance: ObjectInstance, transform: Transform) {
        if(consumer !is BufferVertexConsumer) return

        var tint = if (instance is TintProvider) instance.tint else WHITE
        val light = if (instance is BlockLightValueProvider) instance.light else LightTexture.FULL_BRIGHT

        if (tint == null) tint = WHITE
        val modelMatrix = instance.transformationMatrix()
        val viewMatrix = instance.viewMatrix()

        var i = 0
        while (i < indexSize) {
            addVertex(consumer, i, tint, light, modelMatrix, viewMatrix, transform)
            addVertex(consumer, i + 1, tint, light, modelMatrix, viewMatrix, transform)
            addVertex(consumer, i + 2, tint, light, modelMatrix, viewMatrix, transform)
            i += 3
        }
    }

    private fun addVertex(
        consumer: BufferVertexConsumer,
        i: Int,
        tint: Vector3f,
        light: Int,
        modelMatrix: Matrix4f,
        viewMatrix: Matrix4f,
        transform: Transform,
    ) {
        val bufferIndex = indicies[i]

        val posIndex = bufferIndex * 3
        val boneIndex = bufferIndex * 4
        val uvIndex = bufferIndex * 2

        consumer.vertex(modelMatrix, positions[posIndex], positions[posIndex + 1], positions[posIndex + 2])
        consumer.color(tint.x, tint.y, tint.z, 1.0f)
        consumer.uv(uvs[uvIndex], uvs[uvIndex + 1])
        consumer.overlayCoords(OverlayTexture.NO_OVERLAY)
        consumer.uv2(light)
        consumer.normal(normals[posIndex], normals[posIndex + 1], normals[posIndex + 2])
        consumer.joints(boneIds[boneIndex].toShort(), boneIds[boneIndex + 1].toShort(), boneIds[boneIndex + 2].toShort(), boneIds[boneIndex + 3].toShort())
        consumer.weights(boneWeights[boneIndex], boneWeights[boneIndex + 1], boneWeights[boneIndex + 2], boneWeights[boneIndex + 3]);
        consumer.endVertex()
    }

    //    private Matrix4f getBoneTransform(Matrix4f[] boneTransforms, int boneIndex) {
    //        var boneTransform = boneTransforms[boneIds[boneIndex]].scale(boneWeights[boneIndex], new Matrix4f()).add(boneTransforms[boneIds[boneIndex+1]].scale(boneWeights[boneIndex+1], new Matrix4f()).add(boneTransforms[boneIds[boneIndex+2]].scale(boneWeights[boneIndex+2], new Matrix4f()).add(boneTransforms[boneIds[boneIndex+3]].scale(boneWeights[boneIndex+2], new Matrix4f()))));
    //
    //        return boneTransform;
    //    }
    override fun getDimensions(): Vector3f? {
        return null
    }

    override fun <T : RenderObject?> render(instances: List<ObjectInstance>, `object`: T) {
    }

    override fun <T : RenderObject?> render(instance: ObjectInstance, `object`: T) {
        if (bufferSource != null) {
            if (!`object`!!.shouldRender(instance)) {
                val material = `object`.getMaterial(instance.variant())
                val texture = material.diffuseTexture

                if (texture is ITextureWithResourceLocation) {
                    var transform = `object`.getTransform(instance.variant())

                    if (instance is AnimatedObjectInstance) {
                        val t = instance.getTransform(material.materialName)

                        if (t != null) {
                            transform = t
                        }
                    }

                    //                    var activeConsumer = bufferSource.getBuffer(material.blendType() != BlendType.None ? RenderType.entitySolid(textureWithResourceLocation.getLocation()) : RenderType.entityTranslucent(textureWithResourceLocation.getLocation()));
                    val activeConsumer = bufferSource!!.getBuffer(VanillShaders.RARE_CANDY_SOLID.apply(texture.location))

                    render(activeConsumer, instance, transform)
                }
            }
        }
    }

    @Throws(IOException::class)
    override fun close() {
    }

    companion object {
        val WHITE: Vector3f = Vector3f(1.0f, 1.0f, 1.0f)
    }
}

private fun BufferVertexConsumer.joints(x: Short, y: Short, z: Short, w: Short): BufferVertexConsumer {
    val vertexFormatElement = this.currentElement()

    if (vertexFormatElement.usage != VertexFormatElement.Usage.GENERIC) {
        return this
    } else if (vertexFormatElement.type == VertexFormatElement.Type.SHORT && vertexFormatElement.count == 4) {
        this.putShort(0, x)
        this.putShort(2, y)
        this.putShort(4, z)
        this.putShort(6, w)
        this.nextElement()
        return this
    } else {
        throw IllegalStateException()
    }
}

private fun BufferVertexConsumer.weights(x: Float, y: Float, z: Float, w: Float): BufferVertexConsumer {
    val vertexFormatElement = this.currentElement()

    if (vertexFormatElement.usage != VertexFormatElement.Usage.GENERIC) {
        return this
    } else if (vertexFormatElement.type == VertexFormatElement.Type.SHORT && vertexFormatElement.count == 4) {
        this.putFloat(0, x)
        this.putFloat(4, y)
        this.putFloat(8, z)
        this.putFloat(12, w)
        this.nextElement()
        return this
    } else {
        throw IllegalStateException()
    }
}
