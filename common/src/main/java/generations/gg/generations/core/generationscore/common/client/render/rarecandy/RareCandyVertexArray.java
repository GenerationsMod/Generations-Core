package generations.gg.generations.core.generationscore.common.client.render.rarecandy;

import org.lwjgl.opengl.GL43;

public class RareCandyVertexArray {
    private final int vao;
    private final int vertexStride;
    
    public RareCandyVertexArray(int strideBytes) {
        this.vertexStride = strideBytes;
        this.vao = GL43.glGenVertexArrays();
        
        setupAttributes();
    }
    
    private void setupAttributes() {
        GL43.glBindVertexArray(vao);
        
        // Position (vec3)
        GL43.glEnableVertexAttribArray(0);
        GL43.glVertexAttribPointer(0, 3, GL43.GL_FLOAT, false, vertexStride, 0);
        
        // Color (vec4)
        GL43.glEnableVertexAttribArray(1);
        GL43.glVertexAttribPointer(1, 4, GL43.GL_FLOAT, false, vertexStride, 12);
        
        // UV0 (vec2)
        GL43.glEnableVertexAttribArray(2);
        GL43.glVertexAttribPointer(2, 2, GL43.GL_FLOAT, false, vertexStride, 28);
        
        // UV1 (ivec2)
        GL43.glEnableVertexAttribArray(3);
        GL43.glVertexAttribIPointer(3, 2, GL43.GL_INT, vertexStride, 36);
        
        // UV2 (ivec2)
        GL43.glEnableVertexAttribArray(4);
        GL43.glVertexAttribIPointer(4, 2, GL43.GL_INT, vertexStride, 44);
        
        // Normal (vec3)
        GL43.glEnableVertexAttribArray(5);
        GL43.glVertexAttribPointer(5, 3, GL43.GL_FLOAT, false, vertexStride, 52);
        
        GL43.glBindVertexArray(0);
    }
    
    public void bind() {
        GL43.glBindVertexArray(vao);
    }
    
    public void unbind() {
        GL43.glBindVertexArray(0);
    }
    
    public void delete() {
        GL43.glDeleteVertexArrays(vao);
    }
}