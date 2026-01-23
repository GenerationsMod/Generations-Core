package generations.gg.generations.core.generationscore.common.client.render.rarecandy

import gg.generations.rarecandy.renderer.animation.Animation
import gg.generations.rarecandy.renderer.animation.AnimationController
import gg.generations.rarecandy.renderer.animation.AnimationInstance
import gg.generations.rarecandy.renderer.animation.Transform
import gg.generations.rarecandy.renderer.storage.AnimatedObjectInstance
import gg.generations.rarecandy.renderer.storage.SSBOBuffer
import net.minecraft.util.FastColor.ABGR32
import org.joml.Matrix4f
import org.joml.Vector3f
import org.lwjgl.system.MemoryUtil

open class CobblemonInstance(var light: Int = 0, var overlay: Int = 0, var tint: Int = 0, var teraActive: Boolean = false, var teraTint: Vector3f = Vector3f()) : AnimatedObjectInstance(Matrix4f(), 0) {
    companion object {
        val SIZE = ANIMATED_SIZE + (Float.SIZE_BYTES * 7) + (Int.SIZE_BYTES * 5)
    }

    init {
        currentAnimation = StaticAnimationInstance();
    }

    override fun update(instanceBuffer: SSBOBuffer) {
        super.update(instanceBuffer)

        instanceBuffer.putRgba(tint)
        instanceBuffer.put(teraTint.x)
        instanceBuffer.put(teraTint.y)
        instanceBuffer.put(teraTint.z)
        instanceBuffer.put(if (teraActive) 1 else 0)  // uint teraActive

        instanceBuffer.putPackedUv(light)
        instanceBuffer.putPackedUv(overlay)
    }

    override fun changeAnimation(newAnimation: AnimationInstance?) = Unit

    fun setAnimation(animation: Animation?) = (currentAnimation as StaticAnimationInstance).setAnimation(animation)

    private class StaticAnimationInstance(animation: Animation? = null) : AnimationInstance(animation) {
        override fun getOffset(material: Int): Transform {

            return when {
                offsets == null -> AnimationController.NO_OFFSET
                material >= offsets.size -> AnimationController.NO_OFFSET
                else -> offsets[material]
            }

        }

        fun setAnimation(animation: Animation?) {
            this.animation = animation

            this.animation?.also {
                val size = offsets?.size ?: 0

                if(size < it.offsets.size) {
                    this.offsets = Array(it.offsets.size) { Transform() }
                }
            }
        }
    }
}

fun SSBOBuffer.putRgba(color: Int) {
    this.put(ABGR32.fromArgb32(color))
}

fun SSBOBuffer.putPackedUv(uv: Int) {
    this.put((uv and '\uffff'.code).toShort())
    this.put((uv shr 16 and '\uffff'.code).toShort())
}