package generations.gg.generations.core.generationscore.client.model;

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.client.render.models.blockbench.pose.Bone;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext;
import com.cobblemon.mod.common.net.messages.client.data.SpeciesRegistrySyncPacket;
import com.cobblemon.mod.common.pokemon.Species;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.client.render.CobblemonInstanceProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.*;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

public class RareCandyBone implements Supplier<Bone>, Bone {
    private  static Vector3f temp = new Vector3f();
    private static final Quaternionf ROTATION_CORRECTION = Axis.YP.rotationDegrees(180);
    private final Supplier<CompiledModel> objectSupplier;

    private static final Map<String, Bone> DUMMY = Collections.emptyMap();

    public RareCandyBone(ResourceLocation location) {
        objectSupplier = () -> ModelRegistry.get(location);
    }


    @Override
    public Map<String, Bone> getChildren() {
        return DUMMY;
    }

    @Override
    public void render(RenderContext context, PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay, float r, float g, float b, float a) {
        var model = objectSupplier.get();

        if(model == null || model.renderObject == null) return;

        var entity = context.request(RenderContext.Companion.getENTITY());


        boolean isGui = false;
        CobblemonInstance instance = context.request(Pipelines.INSTANCE);

        if (instance != null && context.request(RenderContext.Companion.getENTITY()) instanceof CobblemonInstanceProvider provider) {
            instance = provider.getInstance();
        }

        var isStatue = instance instanceof StatueInstance;

        var scale = model.renderObject.scale;

        if(instance == null && !isStatue) {
            instance = model.guiInstance;
            if(instance == null) return;
            isGui = true;
            instance.viewMatrix().set(RenderSystem.getModelViewMatrix());
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

            if(!isGui) {
                model.render(instance);
            } else {
                model.renderGui(instance);
            }
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