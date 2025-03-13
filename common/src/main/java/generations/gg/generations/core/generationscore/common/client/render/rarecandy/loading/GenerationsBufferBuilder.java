package generations.gg.generations.core.generationscore.common.client.render.rarecandy.loading;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class GenerationsBufferBuilder extends BufferBuilder {
    public GenerationsBufferBuilder(int capacity) {
        super(capacity);
    }

    public GenerationsBufferBuilder weights(float x, float y, float z, float w) {
        VertexFormatElement vertexFormatElement = this.currentElement();
        if (vertexFormatElement.getUsage() != VertexFormatElement.Usage.GENERIC) {
            return this;
        } else if (vertexFormatElement.getType() == VertexFormatElement.Type.FLOAT && vertexFormatElement.getCount() == 4) {
            this.putFloat(0, (byte)x);
            this.putByte(4, (byte)y);
            this.putByte(8, (byte)z);
            this.putByte(12, (byte)w);
            this.nextElement();
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public GenerationsBufferBuilder joints(int x, int y, int z, int w) {
        VertexFormatElement vertexFormatElement = this.currentElement();
        if (vertexFormatElement.getUsage() != VertexFormatElement.Usage.GENERIC) {
            return this;
        } else if (vertexFormatElement.getType() == VertexFormatElement.Type.BYTE && vertexFormatElement.getCount() == 4) {
            this.putByte(0, (byte)x);
            this.putByte(1, (byte)y);
            this.putByte(2, (byte)z);
            this.putByte(3, (byte)w);
            this.nextElement();
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public @NotNull GenerationsBufferBuilder normal(float x, float y, float z) {
        super.normal(x, y, z);

        return this;
    }

    public @NotNull GenerationsBufferBuilder vertex(float x, float y, float z) {
        super.vertex(x, y, z);

        return this;
    }

    public GenerationsBufferBuilder uv(float x, float y) {
        super.uv(x, y);
        return this;
    }
}
