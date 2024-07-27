package generations.gg.generations.core.generationscore.client.model;

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.client.render.models.blockbench.pose.Bone;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext;
import com.cobblemon.mod.common.net.messages.client.data.SpeciesRegistrySyncPacket;
import com.cobblemon.mod.common.pokemon.Species;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.client.GenerationsTextureLoader;
import generations.gg.generations.core.generationscore.client.render.CobblemonInstanceProvider;
import generations.gg.generations.core.generationscore.client.render.entity.StatueEntityRenderer;
import generations.gg.generations.core.generationscore.client.render.rarecandy.*;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class RareCandyBone implements Supplier<Bone>, Bone {
    private  static Vector3f temp = new Vector3f();
    private static final Quaternionf ROTATION_CORRECTION = Axis.YP.rotationDegrees(180);
    private final Supplier<CompiledModel> objectSupplier;

    private static final Map<String, Bone> DUMMY = Collections.emptyMap();
    private final BiFunction<RenderContext.RenderState, String, ResourceLocation> spriteProvider;

    public RareCandyBone(ResourceLocation location) {
        var spriteLoc = Objects.requireNonNull(ResourceLocation.tryBuild(location.getNamespace(), location.getPath().replace("bedrock/pokemon/models/", "").replace(".pk", "")));
        objectSupplier = () -> ModelRegistry.get(location);
        spriteProvider = (state, s) -> {
            var sprite = SpriteRegistry.INSTANCE.getPokemonSprite(state, Objects.requireNonNull(spriteLoc), s);
            if(sprite != null) return sprite;
            else return MissingTextureAtlasSprite.getLocation();
        };
    }


    @Override
    public Map<String, Bone> getChildren() {
        return DUMMY;
    }

    @Override
    public void render(RenderContext context, PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay, float r, float g, float b, float a) {
//        if(context.request(Pipelines.INSTANCE) == null && context.request(RenderContext.Companion.getENTITY()) == null) {
//            renderSprite(context, stack, packedLight, packedOverlay, r, g, b, a);
//        } else {
            renderModel(context, stack, packedLight);
//        }
    }

    public void renderSprite(RenderContext context, PoseStack stack, int packedLight, int packedOverlay, float r, float g, float b, float a) {
        renderSprite(context, stack, packedLight, packedOverlay, r, g, b, a, false);
    }

    public void renderSprite(RenderContext context, PoseStack stack, int packedLight, int packedOverlay, float r, float g, float b, float a, boolean isSprite) {
        var id = getTexture(context);

        if (id != null) {
            if (id.getNamespace().equals("pk")) {
                id = spriteProvider.apply(context.requires(RenderContext.Companion.getRENDER_STATE()), id.getPath());
            }
        }

        var sources = Minecraft.getInstance().renderBuffers().bufferSource();

        var buffer = sources.getBuffer(RenderType.entityCutout(id));

        var scale = isSprite ? 1f : 2f;

        var matrix = stack.last();
        matrix.pose().translate(-scale/2f, 0, 0);


        buffer.vertex(matrix.pose(), scale,  0f, 0.0f).color(r,g,b,a).uv(1, 0).overlayCoords(packedOverlay).uv2(packedLight).normal(matrix.normal(), 0, 1,0).endVertex();
        buffer.vertex(matrix.pose(), 0f,  0f, 0.0f).color(r,g,b,a).uv(0, 0).overlayCoords(packedOverlay).uv2(packedLight).normal(matrix.normal(), 0, 1,0).endVertex();
        buffer.vertex(matrix.pose(), 0f,  scale, 0.0f).color(r,g,b,a).uv(0, 1).overlayCoords(packedOverlay).uv2(packedLight).normal(matrix.normal(), 0, 1,0).endVertex();
        buffer.vertex(matrix.pose(), scale, scale, 0.0f).color(r,g,b,a).uv(1, 1).overlayCoords(packedOverlay).uv2(packedLight).normal(matrix.normal(), 0, 1,0).endVertex();
        sources.endBatch();
    }

    private void renderModel(RenderContext context, PoseStack stack, int packedLight) {
        var model = objectSupplier.get();

        if(model == null || model.renderObject == null) return;

        CobblemonInstance instance = context.request(Pipelines.INSTANCE);

        if (instance == null && context.request(RenderContext.Companion.getENTITY()) instanceof CobblemonInstanceProvider provider) {
            instance = provider.getInstance();
        }

        var isStatue = instance instanceof StatueInstance;

        var scale = model.renderObject.scale;

        if(instance == null) {
            return;
        } else {
            if(isStatue) {
                if(model.guiInstance == null) return;
                instance.matrixTransforms = model.guiInstance.matrixTransforms;
                instance.offsets = model.guiInstance.offsets;
            }

            scale *= PokemonSpecies.INSTANCE.getByIdentifier(context.requires(RenderContext.Companion.getSPECIES())).getForm(context.requires(RenderContext.Companion.getASPECTS())).getHitbox().height;
        }

        if(model.renderObject.isReady()) {
            instance.setLight(packedLight);

            var id = getTexture(context);

            if (id != null) {
                String namespace = id.getNamespace();
                if (namespace.equals("pk")) instance.setVariant(id.getPath());
            }

            stack.pushPose();

            stack.mulPose(ROTATION_CORRECTION);
            stack.scale(-1, -1, 1);
            stack.translate(0, -1.501, 0);
            stack.scale(scale, scale, scale);

            instance.transformationMatrix().set(stack.last().pose());
            stack.popPose();

//            if(!isGui) {
                model.render(instance);
//            } else {
//                model.renderGui(instance);
//            }
        }
    }

    public CompiledModel getCompiledModel() {
        return objectSupplier.get();
    }

    private ResourceLocation getTexture(RenderContext context) {
        try {
            var aspects = context.request(RenderContext.Companion.getASPECTS());
            var species = context.request(RenderContext.Companion.getSPECIES());

            return PokemonModelRepository.INSTANCE.getVariations().get(species).getTexture(aspects != null ? aspects : Collections.emptySet(), 0.0f);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void transform(PoseStack poseStack) {

    }

    @NotNull
    @Override
    public Bone get() {
        return this;
    }
}