package generations.gg.generations.core.generationscore.common.client.render

import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.systems.RenderSystem

object RenderStateRecord {
    var blendEnabled: Boolean = false
    var srcRgb: Int = 0
    var dstRgb: Int = 0
    var srcAlpha: Int = 0
    var dstAlpha: Int = 0

    var depthTestEnabled: Boolean = false
    var depthMask: Boolean = false
    var depthFunc: Int = 0

    var cullEnabled: Boolean = false

    fun push() {
        // Blend
        blendEnabled = GlStateManager.BLEND.mode.enabled
        srcRgb = GlStateManager.BLEND.srcRgb
        dstRgb = GlStateManager.BLEND.dstRgb
        srcAlpha = GlStateManager.BLEND.srcAlpha
        dstAlpha = GlStateManager.BLEND.dstAlpha

        // Depth
        depthTestEnabled = GlStateManager.DEPTH.mode.enabled
        depthMask = GlStateManager.DEPTH.mask
        depthFunc = GlStateManager.DEPTH.func

        // Cull
        cullEnabled = GlStateManager.CULL.enable.enabled
    }

    fun pop() {
        // Blend
        if (blendEnabled) RenderSystem.enableBlend() else RenderSystem.disableBlend()
        RenderSystem.blendFuncSeparate(srcRgb, dstRgb, srcAlpha, dstAlpha)

        // Depth
        if (depthTestEnabled) RenderSystem.enableDepthTest() else RenderSystem.disableDepthTest()
        RenderSystem.depthMask(depthMask)
        RenderSystem.depthFunc(depthFunc)

        // Cull
        if (cullEnabled) RenderSystem.enableCull() else RenderSystem.disableCull()
    }
}