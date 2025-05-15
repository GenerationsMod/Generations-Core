package generations.gg.generations.core.generationscore.common.client.model

import dev.architectury.event.EventResult
import dev.architectury.event.events.client.ClientRawInputEvent
import dev.architectury.registry.client.keymappings.KeyMappingRegistry
import net.minecraft.client.KeyMapping
import net.minecraft.client.Minecraft
import org.lwjgl.glfw.GLFW

class RunnableKeybind(name: String, keyId: Int, category: String, private val runnable: Runnable) :
    KeyMapping(name, keyId, category) {
    init {
        ClientRawInputEvent.KEY_PRESSED.register(ClientRawInputEvent.KeyPressed { client: Minecraft, keyCode: Int, scanCode: Int, action: Int, modifiers: Int ->
            this.onKeyPress(
                client,
                keyCode,
                scanCode,
                action,
                modifiers
            )
        })
    }

    fun onKeyPress(client: Minecraft, keyCode: Int, scanCode: Int, action: Int, modifiers: Int): EventResult {
        if (action == GLFW.GLFW_RELEASE && keyCode == defaultKey.value) {
            if (this.consumeClick()) runnable.run()
            return EventResult.interruptFalse()
        }

        return EventResult.pass()
    }

    companion object {
        @JvmStatic
        fun create(name: String, glfwKey: Int, category: String, action: Runnable) {
            val keybind = RunnableKeybind(name, glfwKey, category, action)
            KeyMappingRegistry.register(keybind)
        }
    }
}
