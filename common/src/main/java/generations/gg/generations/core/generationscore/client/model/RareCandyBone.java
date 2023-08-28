package generations.gg.generations.core.generationscore.client.model;

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.client.render.models.blockbench.pose.Bone;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Species;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.client.render.PixelmonInstanceProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.CompiledModel;
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class RareCandyBone implements Supplier<Bone>, Bone {
    private  static Vector3f temp = new Vector3f();
    private static final Quaternionf ROTATION_CORRECTION = Axis.YP.rotationDegrees(180);
    private final Supplier<CompiledModel> objectSupplier;

    private static final Map<String, Bone> DUMMY = Collections.emptyMap();

    public RareCandyBone(ResourceLocation location) {
        ResourceLocation location1 = location.withPrefix("bedrock/pokemon/models/");
        objectSupplier = () -> ModelRegistry.get(location, "pixelmon");
    }


    @Override
    public Map<String, Bone> getChildren() {
        return DUMMY;
    }

    @Override
    public <T extends Entity> void render(T entity, PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay, float r, float g, float b, float a) {
        var model = objectSupplier.get();

        var instance = entity != null ? ((PixelmonInstanceProvider) entity).getInstance() : null;

        boolean isGui = false;

        if(instance == null) {
            instance = ModelRegistry.getGuiInstance();
            instance.viewMatrix().set(RenderSystem.getModelViewMatrix());
            isGui = true;
        }

        if(instance != null) {
            var scale = model.renderObject.scale;

            Species species = null;
            Set<String> aspects = null;

            if(entity instanceof PokemonEntity pokemon) {
                species = pokemon.getPokemon().getSpecies();
                aspects = pokemon.getPokemon().getAspects();
                scale *= species.getBaseScale();
            } else {
                species = PokemonSpecies.INSTANCE.getByIdentifier(PokemonModelRepository.INSTANCE.getPreviousSpecies());
                aspects = PokemonModelRepository.INSTANCE.getPreviousAspects();
            }


            if(model.renderObject.isReady()) {
                var id = PokemonModelRepository.INSTANCE.getVariations().get(species.getResourceIdentifier()).getResolvedTexture(aspects, 0F);
                if(id.getNamespace().equals("pk")) instance.setVariant(id.getPath());

                stack.pushPose();

                stack.mulPose(ROTATION_CORRECTION);
                stack.scale(-1, -1, 1);
                stack.translate(0, -1.501, 0);
                stack.scale(scale, scale, scale);

                instance.transformationMatrix().set(stack.last().pose());
                stack.popPose();

                if(!isGui) {
                    model.render(instance, RenderSystem.getProjectionMatrix());
                } else {
                    model.renderGui(instance, RenderSystem.getProjectionMatrix());
                }
            }
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