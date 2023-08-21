package generations.gg.generations.core.generationscore.client.model;

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.client.render.models.blockbench.pose.Bone;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.client.render.PixelmonInstanceProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.CompiledModel;
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class RareCandyBone implements Supplier<Bone>, Bone {
    public static StatueEntity statueProxy = null; //This is needed for statues right now sadly.

    private static final Quaternionf ROTATION_CORRECTION = Axis.YP.rotationDegrees(180);
    private final Supplier<CompiledModel> objectSupplier;

    private static final Map<String, Bone> DUMMY = Collections.emptyMap();

    public RareCandyBone(ResourceLocation location) {
        ResourceLocation location1 = location.withPrefix("bedrock/pokemon/models/");
        objectSupplier = () -> ModelRegistry.get(location, "animated_block");
    }


    @Override
    public Map<String, Bone> getChildren() {
        return DUMMY;
    }

    @Override
    public <T extends Entity> void render(T entity, PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay, float r, float g, float b, float a) {
        var model = objectSupplier.get();

        var entity1 = entity != null ? entity : statueProxy != null ? statueProxy : null;

        var instance = entity1 instanceof PixelmonInstanceProvider provider ? provider.getInstance() : null;

        if(instance != null) {
            var scale = model.renderObject.scale;

            instance.setLight(packedLight);

            if (entity1 instanceof PokemonEntity pokemon) {

                scale *= pokemon.getPokemon().getSpecies().getHitbox().height; //TODO: Turn to actual height eventually.

                if (model.renderObject.isReady()) {
                    var id = PokemonModelRepository.INSTANCE.getVariations().get(pokemon.getPokemon().getSpecies().getResourceIdentifier()).getResolvedTexture(pokemon.getPokemon().getAspects(), 0F);
                    if (id.getNamespace().equals("pk")) instance.setVariant(id.getPath());
                }

            } else if (entity1 instanceof StatueEntity statue) {
                var species = statue.getStatueData().getProperties().asRenderablePokemon().getSpecies();
                scale *= species.getHitbox().height; //TODO: Turn to actual height eventually.

                if (model.renderObject.isReady()) {
                    var id = PokemonModelRepository.INSTANCE.getVariations().get(species.getResourceIdentifier()).getResolvedTexture(statue.getStatueData().getProperties().getAspects(), 0F);
                    if (id.getNamespace().equals("pk")) instance.setVariant(id.getPath());
                }

            }

            stack.pushPose();
            stack.mulPose(ROTATION_CORRECTION);

            if (entity1 != null) {
                stack.scale(-1, -1, 1);
                stack.translate(0, -1.501, 0);
            }

            stack.scale(scale, scale, scale);
            instance.viewMatrix().set(stack.last().pose());
            stack.popPose();
            model.render(instance, RenderSystem.getProjectionMatrix());
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