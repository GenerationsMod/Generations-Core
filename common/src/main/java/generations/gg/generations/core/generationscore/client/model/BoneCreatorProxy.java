package generations.gg.generations.core.generationscore.client.model;

import com.cobblemon.mod.common.client.render.models.blockbench.BoneCreator;
import com.cobblemon.mod.common.client.render.models.blockbench.pose.Bone;
import com.mojang.blaze3d.vertex.PoseStack;
import generations.gg.generations.core.generationscore.client.render.PixelmonInstanceProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.CompiledModel;
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

public class BoneCreatorProxy implements BoneCreator, Bone {
    private final ResourceLocation location;
    private final Supplier<CompiledModel> objectSupplier;

    private static final Map<String, Bone> DUMMY = Collections.emptyMap();

    public BoneCreatorProxy(ResourceLocation location) {
        this.location = location.withPrefix("bedrock/pokemon/models/");

        objectSupplier = () -> ModelRegistry.get(location, "animated");
    }


    @Override
    public Map<String, Bone> getChildren() {
        return DUMMY;
    }

    @Override
    public <T extends Entity> void renderBone(T entity, PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay, float r, float g, float b, float a) {
        var model = objectSupplier.get();

        var instance = entity != null ? ((PixelmonInstanceProvider) entity).getInstance() : RareCandyTestClient.getInstance();

        if(instance != null) {
            if(entity instanceof PokemonEntity pokemon) {
                var id = PokemonModelRepository.INSTANCE.getTexture(pokemon.getPokemon().getSpecies().getResourceIdentifier(), pokemon.getAspects().get(), (PokemonClientDelegate) pokemon.getDelegate());

                pokemon.getPokemon().getSpecies().getBaseScale();

                if(id.getNamespace().equals("pk")) instance.setVariant(id.getPath());
            }

//            var height = -model.renderObject.getDimensions().y;

//            System.out.println(height);

            stack.pushPose();
//            stack.scale(-1, -1, 1);
            stack.mulPose(Axis.YN.rotationDegrees(180f));
            stack.mulPose(Axis.ZN.rotationDegrees(180f));
            stack.translate(0, -1.5, 0);
            instance.viewMatrix().set(stack.last().pose());
            stack.popPose();
            model.render(instance, MinecraftClientGameProvider.projMatrix);
        }
    }

    @Override
    public Bone getChildBone(String piece) {
        return this;
    }

    @NotNull
    @Override
    public Bone create() {
        System.out.println("Blep: " + location);
        return this;
    }
}