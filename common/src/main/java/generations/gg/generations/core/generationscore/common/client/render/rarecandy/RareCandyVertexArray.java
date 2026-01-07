package generations.gg.generations.core.generationscore.common.client.render.rarecandy;

import org.lwjgl.opengl.GL43;

public final class RareCandyVertexArray {

    private static final int STRIDE = 36;
    private static final int BINDING = 0;

    private final int vao;

    public RareCandyVertexArray() {
        vao = GL43.glGenVertexArrays();
        GL43.glBindVertexArray(vao);

        // Position
        GL43.glEnableVertexAttribArray(0);
        GL43.glVertexAttribFormat(0, 3, GL43.GL_FLOAT, false, 0);
        GL43.glVertexAttribBinding(0, BINDING);

        // Color
        GL43.glEnableVertexAttribArray(1);
        GL43.glVertexAttribFormat(1, 4, GL43.GL_UNSIGNED_BYTE, true, 12);
        GL43.glVertexAttribBinding(1, BINDING);

        // UV0
        GL43.glEnableVertexAttribArray(2);
        GL43.glVertexAttribFormat(2, 2, GL43.GL_FLOAT, false, 16);
        GL43.glVertexAttribBinding(2, BINDING);

        // UV1
        GL43.glEnableVertexAttribArray(3);
        GL43.glVertexAttribIFormat(3, 2, GL43.GL_SHORT, 24);
        GL43.glVertexAttribBinding(3, BINDING);

        // UV2
        GL43.glEnableVertexAttribArray(4);
        GL43.glVertexAttribIFormat(4, 2, GL43.GL_SHORT, 28);
        GL43.glVertexAttribBinding(4, BINDING);

        // Normal
        GL43.glEnableVertexAttribArray(5);
        GL43.glVertexAttribFormat(5, 3, GL43.GL_BYTE, true, 32);
        GL43.glVertexAttribBinding(5, BINDING);

        GL43.glBindVertexArray(0);
    }

    public void bind(int vertexBuffer) {
        GL43.glBindVertexArray(vao);
        GL43.glBindVertexBuffer(BINDING, vertexBuffer, 0, STRIDE);
    }

    public void unbind() {
        GL43.glBindVertexArray(0);
    }

    public void delete() {
        GL43.glDeleteVertexArrays(vao);
    }
}
