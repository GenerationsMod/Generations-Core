package generations.gg.generations.core.generationscore.client.model;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientRawInputEvent;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class RunnableKeybind extends KeyMapping {

    private final Runnable runnable;

    public static void create(String name, int glfwKey, String category, Runnable action) {
        RunnableKeybind keybind = new RunnableKeybind(name, glfwKey, category, action);
        KeyMappingRegistry.register(keybind);
    }

    public RunnableKeybind(String name, int keyId, String category, Runnable runnable) {
        super(name, keyId, category);
        this.runnable = runnable;
        ClientRawInputEvent.KEY_PRESSED.register((client, keyCode, scanCode, action1, modifiers) -> onKeyPress(client, keyCode, scanCode, action1, modifiers));
    }

    public EventResult onKeyPress(Minecraft client, int keyCode, int scanCode, int action, int modifiers) {
        if (action == GLFW.GLFW_RELEASE && keyCode == this.getDefaultKey().getValue()) {
            if(this.consumeClick()) runnable.run();
            return EventResult.interruptFalse();
        }

        return EventResult.pass();
    }
}
