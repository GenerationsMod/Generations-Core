package generations.gg.generations.core.generationscore.client.model;

import org.lwjgl.opengl.GL15C;
import org.lwjgl.opengl.GL43C;

/**
 * Represents a Persistently mapped Storage Buffer
 */
public record StaticStorageBuffer(int id, long size, long pointer) {

    public void bind() {
        GL15C.glBindBuffer(GL43C.GL_SHADER_STORAGE_BUFFER, id);
    }

    public void free() {
        bind();
        GL15C.glBindBuffer(GL43C.GL_SHADER_STORAGE_BUFFER, 0);
    }
}
