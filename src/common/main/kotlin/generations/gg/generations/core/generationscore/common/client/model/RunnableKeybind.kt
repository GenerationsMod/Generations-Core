package generations.gg.generations.core.generationscore.common.client.model

import generations.gg.generations.core.generationscore.common.client.render.rarecandy.Pipelines
import net.minecraft.client.KeyMapping
import org.lwjgl.glfw.GLFW

object Keybinds {
    val keybindings = mutableListOf<RunnableKeybind>()

    val TOGGLE_RENDERING = register("toggleShaderRendering", GLFW.GLFW_KEY_P, "rendering", Pipelines::toggleRendering)

    private fun register(name: String, keyId: Int, category: String, runnable: Runnable) = RunnableKeybind(name, keyId, category, runnable).also(keybindings::add)

    fun pressDown(keyCode: Int, scanCode: Int, action: Int, modifiers: Int): Boolean {
        for(keybinding in keybindings) {
            if(keybinding.onKeyPress(keyCode, scanCode, action, modifiers)) return true
        }

        return false
    }

    class RunnableKeybind(name: String, keyId: Int, category: String, private val runnable: Runnable) : KeyMapping(name, keyId, category) {
        fun onKeyPress(keyCode: Int, scanCode: Int, action: Int, modifiers: Int): Boolean {
            if (action == GLFW.GLFW_RELEASE && keyCode == defaultKey.value) {
                if (this.consumeClick()) runnable.run()
                return true
            }

            return false
        }
    }
}
