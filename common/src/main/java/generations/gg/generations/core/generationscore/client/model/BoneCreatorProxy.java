package generations.gg.generations.core.generationscore.client.model;

import com.cobblemon.mod.common.client.entity.PokemonClientDelegate;
import com.cobblemon.mod.common.client.render.models.blockbench.pose.Bone;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.client.render.PixelmonInstanceProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.CompiledModel;
import generations.gg.generations.core.generationscore.client.render.rarecandy.MinecraftClientGameProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

public class BoneCreatorProxy implements Supplier<Bone>, Bone {
    private final ResourceLocation location;
    private final Supplier<CompiledModel> objectSupplier;

    private static final Map<String, Bone> DUMMY = Collections.emptyMap();

    public BoneCreatorProxy(ResourceLocation location) {
        this.location = location.withPrefix("bedrock/pokemon/models/");
        objectSupplier = () -> ModelRegistry.get(location, "pixelmon");
    }


    @Override
    public Map<String, Bone> getChildren() {
        return DUMMY;
    }

    @Override
    public <T extends Entity> void render(T entity, PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay, float r, float g, float b, float a) {
        var model = objectSupplier.get();

        var instance = entity != null ? ((PixelmonInstanceProvider) entity).getInstance() : ModelRegistry.getInstance();

        if(instance != null) {
            var scale = model.renderObject.scale;

            if(entity instanceof PokemonEntity pokemon) {
                var id = PokemonModelRepository.INSTANCE.getTexture(pokemon.getPokemon().getSpecies().getResourceIdentifier(), pokemon.getAspects().get(), (PokemonClientDelegate) pokemon.getDelegate());

                scale *= pokemon.getPokemon().getSpecies().getBaseScale();

                if(id.getNamespace().equals("pk")) instance.setVariant(id.getPath());
            }

            stack.pushPose();
            if(entity != null) {
                stack.mulPose(Axis.YN.rotationDegrees(180f));
                stack.mulPose(Axis.ZN.rotationDegrees(180f));
                stack.translate(0, -1.5, 0);
            }
            stack.scale(scale, scale, scale);
            instance.viewMatrix().set(stack.last().pose());
            stack.popPose();
            model.render(instance, MinecraftClientGameProvider.projMatrix);
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