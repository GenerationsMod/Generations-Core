package generations.gg.generations.core.generationscore.common.client.render.rarecandy;

import org.lwjgl.opengl.GL43;

public final class RareCandyVertexArray {

    private final int vao;
    private final int vertexStride;

    // binding index used for all vertex attributes
    private static final int VERTEX_BINDING = 0;

    public RareCandyVertexArray(int strideBytes) {
        this.vertexStride = strideBytes;
        this.vao = GL43.glGenVertexArrays();
        setupAttributes();
    }

    private void setupAttributes() {
        GL43.glBindVertexArray(vao);

        // Position (vec3) @ location 0
        GL43.glEnableVertexAttribArray(0);
        GL43.glVertexAttribFormat(0, 3, GL43.GL_FLOAT, false, 0);
        GL43.glVertexAttribBinding(0, VERTEX_BINDING);

        // Color (vec4) @ location 1
        GL43.glEnableVertexAttribArray(1);
        GL43.glVertexAttribFormat(1, 4, GL43.GL_FLOAT, false, 12);
        GL43.glVertexAttribBinding(1, VERTEX_BINDING);

        // UV0 (vec2) @ location 2
        GL43.glEnableVertexAttribArray(2);
        GL43.glVertexAttribFormat(2, 2, GL43.GL_FLOAT, false, 28);
        GL43.glVertexAttribBinding(2, VERTEX_BINDING);

        // UV1 (ivec2) @ location 3
        GL43.glEnableVertexAttribArray(3);
        GL43.glVertexAttribIFormat(3, 2, GL43.GL_INT, 36);
        GL43.glVertexAttribBinding(3, VERTEX_BINDING);

        // UV2 (ivec2) @ location 4
        GL43.glEnableVertexAttribArray(4);
        GL43.glVertexAttribIFormat(4, 2, GL43.GL_INT, 44);
        GL43.glVertexAttribBinding(4, VERTEX_BINDING);

        // Normal (vec3) @ location 5
        GL43.glEnableVertexAttribArray(5);
        GL43.glVertexAttribFormat(5, 3, GL43.GL_FLOAT, false, 52);
        GL43.glVertexAttribBinding(5, VERTEX_BINDING);

        GL43.glBindVertexArray(0);
    }

    /** Bind VAO only */
    public void bind() {
        GL43.glBindVertexArray(vao);
    }

    /** Bind the actual vertex buffer (destBuffer) at draw time */
    public void bindVertexBuffer(int bufferId) {
        GL43.glBindVertexBuffer(
                VERTEX_BINDING,
                bufferId,
                0,
                vertexStride
        );
    }

    public void unbind() {
        GL43.glBindVertexArray(0);
    }

    public void delete() {
        GL43.glDeleteVertexArrays(vao);
    }
}
