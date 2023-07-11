package generations.gg.generations.core.generationscore.client.model;

import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityModel;
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState;
import com.cobblemon.mod.common.client.render.models.blockbench.animation.StatefulAnimation;
import com.cobblemon.mod.common.client.render.models.blockbench.animation.StatelessAnimation;
import com.cobblemon.mod.common.client.render.models.blockbench.frame.ModelFrame;
import com.cobblemon.mod.common.client.render.models.blockbench.pokemon.AnimationReferenceFactory;
import com.cobblemon.mod.common.client.render.models.blockbench.pokemon.JsonPokemonPoseableModel;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import generations.gg.generations.core.generationscore.client.render.PixelmonInstanceProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry;
import gg.generations.rarecandy.animation.Animation;
import gg.generations.rarecandy.components.AnimatedMeshObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class RareCandyAnimationFactory implements AnimationReferenceFactory {
    public static final float animation_factor = 40f;

    @Override
    public @NotNull StatefulAnimation<PokemonEntity, ModelFrame> stateful(@NotNull JsonPokemonPoseableModel jsonPokemonPoseableModel, @NotNull String s) {
        var split = s.replace("pk(", "").replace(")", "").split(",");
        var location = new ResourceLocation(split[0]).withPrefix("bedrock/pokemon/models/");
        var name = split[1].trim();

        return new StatefulAnimationRareCandy(() -> {
            var objects = ModelRegistry.get(location, "pixelmon").renderObject;
            return objects.isReady() ? ((AnimatedMeshObject) objects.objects.get(0)).animations.get(name) : null;
        });
    }

    @NotNull
    @Override
    public StatelessAnimation<PokemonEntity, ModelFrame> stateless(@NotNull JsonPokemonPoseableModel jsonPokemonPoseableModel, @NotNull String s) {
        var split = s.replace("pk(", "").replace(")", "").split(",");
        var location = new ResourceLocation(split[0]).withPrefix("bedrock/pokemon/models/");
        var name = split[1].trim();

        return new StatelessAnimationRareCandy(jsonPokemonPoseableModel, () -> {
            var objects = ModelRegistry.get(location, "pixelmon").renderObject;
            return objects.isReady() ? ((AnimatedMeshObject) objects.objects.get(0)).animations.get(name) : null;
        });
    }

    public static class StatefulAnimationRareCandy implements StatefulAnimation<PokemonEntity, ModelFrame> {
        private float secondsPassed = 0f;
        private final Supplier<gg.generations.rarecandy.animation.Animation> animationSuppler;

        public StatefulAnimationRareCandy(Supplier<Animation> animationSuppler) {
            this.animationSuppler = animationSuppler;
        }


        @Override
        public boolean isTransform() {
            return false;
        }

        @Override
        public boolean preventsIdle(@Nullable PokemonEntity t, @NotNull PoseableEntityState<PokemonEntity> poseableEntityState, @NotNull StatelessAnimation<PokemonEntity, ?> statelessAnimation) {
            return false;
        }

        @Override
        public boolean run(@Nullable PokemonEntity t, @NotNull PoseableEntityModel<PokemonEntity> poseableEntityModel, @NotNull PoseableEntityState<PokemonEntity> poseableEntityState, float v, float v1, float v2, float v3, float v4) {
            secondsPassed += poseableEntityState.getAnimationSeconds();

            var instance = t != null ? ((PixelmonInstanceProvider) (LivingEntity) t).getInstance() : null;
            var animation = animationSuppler.get();

            if(instance != null && animation != null) instance.matrixTransforms = animation.getFrameTransform(secondsPassed/animation_factor);

            return true;
        }

        @Override
        public void applyEffects(@NotNull PokemonEntity pokemon, @NotNull PoseableEntityState<PokemonEntity> poseableEntityState, float v, float v1) {

        }
    }

    private static class StatelessAnimationRareCandy extends StatelessAnimation<PokemonEntity, ModelFrame> {

        private final Supplier<gg.generations.rarecandy.animation.Animation> animationSupplier;

        public StatelessAnimationRareCandy(JsonPokemonPoseableModel jsonPokemonPoseableModel, Supplier<Animation> animationSupplier) {
            super(jsonPokemonPoseableModel);
            this.animationSupplier = animationSupplier;
        }

        @NotNull
        @Override
        public Class<ModelFrame> getTargetFrame() {
            return (Class<ModelFrame>) getFrame().getClass();
        }

        @Override
        protected void setAngles(@Nullable PokemonEntity pokemonEntity, @NotNull PoseableEntityModel<PokemonEntity> poseableEntityModel, @Nullable PoseableEntityState<PokemonEntity> state, float v, float v1, float v2, float v3, float v4) {
//            val prev = if (state == null) 0F else (state.previousAnimationSeconds - state.timeEnteredPose)
            var cur = state == null ? 0F : (state.getAnimationSeconds() - state.getTimeEnteredPose())/animation_factor;
            var instance = pokemonEntity != null ? ((PixelmonInstanceProvider) (LivingEntity) pokemonEntity).getInstance() : null;
            var animation = animationSupplier.get();

            if(instance != null && animation != null) instance.matrixTransforms = animationSupplier.get().getFrameTransform(cur);
        }
    }
}