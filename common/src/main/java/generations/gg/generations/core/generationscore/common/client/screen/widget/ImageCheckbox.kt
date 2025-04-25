package generations.gg.generations.core.generationscore.common.client.screen.widget

import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.AbstractButton
import net.minecraft.client.gui.narration.NarratedElementType
import net.minecraft.client.gui.narration.NarrationElementOutput
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation

class ImageCheckbox(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    private val texture: ResourceLocation,
    private val u: Int,
    private val v: Int,
    private val onSelected: Runnable,
    private val onDeselected: Runnable,
    private var selected: Boolean
) :
    AbstractButton(x, y, width, height, Component.empty()) {
    override fun onPress() {
        this.selected = !this.selected
        if (this.selected) onSelected.run()
        else onDeselected.run()
    }

    fun selected(): Boolean {
        return this.selected
    }

    public override fun updateWidgetNarration(narrationElementOutput: NarrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.TITLE, this.createNarrationMessage())
        if (this.active) {
            if (this.isFocused) {
                narrationElementOutput.add(
                    NarratedElementType.USAGE,
                    Component.translatable("narration.checkbox.usage.focused")
                )
            } else {
                narrationElementOutput.add(
                    NarratedElementType.USAGE,
                    Component.translatable("narration.checkbox.usage.hovered")
                )
            }
        }
    }

    override fun renderWidget(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        val minecraft = Minecraft.getInstance()
        RenderSystem.enableDepthTest()
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, this.alpha)
        RenderSystem.enableBlend()
        RenderSystem.defaultBlendFunc()
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA)
        guiGraphics.blit(
            texture,
            x, y, u + (if (isFocused) width else 0), v + (if (selected) height else 0), width, height
        )
        //        this.renderBg(poseStack, minecraft, mouseX, mouseY);
    }
}