package generations.gg.generations.core.generationscore.common.client.render.rarecandy;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import dev.architectury.event.events.client.ClientReloadShadersEvent;
import net.minecraft.Util;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceProvider;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.mojang.blaze3d.vertex.DefaultVertexFormat.*;
import static net.minecraft.client.renderer.RenderType.BIG_BUFFER_SIZE;

public class VanillShaders {
    public static final VertexFormatElement ELEMENT_JOINTS = new VertexFormatElement(0, VertexFormatElement.Type.SHORT, VertexFormatElement.Usage.GENERIC, 4);
    public static final VertexFormatElement ELEMENT_WEIGHTS = new VertexFormatElement(0, VertexFormatElement.Type.SHORT, VertexFormatElement.Usage.GENERIC, 4);

    public static final VertexFormat RARE_CANDY_FORMAT = new VertexFormat(ImmutableMap.<String, VertexFormatElement>builder()
            .put("Position", ELEMENT_POSITION)
            .put("Color", ELEMENT_COLOR)
            .put("UV0", ELEMENT_UV0)
            .put("UV1", ELEMENT_UV1)
            .put("UV2", ELEMENT_UV2)
            .put("Normal", ELEMENT_NORMAL)
            .put("Joints", ELEMENT_JOINTS)
            .put("Weights", ELEMENT_WEIGHTS)
            .put("Padding", ELEMENT_PADDING).build());

    private static GenerationsShaderInstance solidShader;

    protected static final RenderStateShard.ShaderStateShard RARE_CANDY_SOLID_SHADER = new RenderStateShard.ShaderStateShard(VanillShaders::getSolidShader);

    public static final Function<ResourceLocation, RenderType> RARE_CANDY_SOLID = Util.memoize((resourceLocation) -> {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder().setShaderState(RARE_CANDY_SOLID_SHADER).setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false)).setTransparencyState(RenderStateShard.NO_TRANSPARENCY).setLightmapState(RenderStateShard.LIGHTMAP).setOverlayState(RenderStateShard.OVERLAY).createCompositeState(true);
        return RenderType.create("rarecandy_solid", RARE_CANDY_FORMAT, VertexFormat.Mode.TRIANGLES, BIG_BUFFER_SIZE, true, false, compositeState);
    });

    public static void onShaderRegister(ResourceProvider provider, ClientReloadShadersEvent.ShadersSink sink) {
        try {
            sink.registerShader(new GenerationsShaderInstance(provider, "rarecandy_solid", RARE_CANDY_FORMAT), shaderInstance -> VanillShaders.setSolidShader((GenerationsShaderInstance) shaderInstance));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setSolidShader(GenerationsShaderInstance shaderInstance) {
        solidShader = shaderInstance;
    }

    public static GenerationsShaderInstance getSolidShader() {
        return solidShader;
    }

    public static class GenerationsShaderInstance extends ShaderInstance {
        public static final float[] TEMP = new float[220 * 16];

        @Nullable
        public final Uniform BONE_TRANSFORMS;

        public GenerationsShaderInstance(ResourceProvider resourceProvider, String name, VertexFormat vertexFormat) throws IOException {
            super(resourceProvider, name, vertexFormat);

            BONE_TRANSFORMS = getUniform("BoneTransforms");
        }

        public void setupBoneTransforms(Matrix4f[] boneTransforms) {
            if (BONE_TRANSFORMS == null) return;

            for (int i = 0; i < boneTransforms.length; i++) {
                Matrix4f boneTransform = boneTransforms[i];
                boneTransform.get(TEMP, i*16);
            }

            BONE_TRANSFORMS.set(TEMP);
        }
    }
}
