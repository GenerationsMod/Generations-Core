package generations.gg.generations.core.generationscore.mixin.client;

import com.cobblemon.mod.common.client.render.item.CobblemonBuiltinItemRenderer;
import com.cobblemon.mod.common.client.render.item.PokemonItemRenderer;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext;
import com.cobblemon.mod.common.entity.PoseType;
import com.cobblemon.mod.common.item.PokemonItem;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import static org.joml.Math.cos;
import static org.joml.Math.sin;

@Mixin(PokemonItemRenderer.class)
public class PokemonItemRendererMixin implements CobblemonBuiltinItemRenderer {
    public void render(ItemStack stack, ItemDisplayContext mode, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        if(stack.getItem() instanceof PokemonItem pokemonItem) {
            var pair = pokemonItem.getSpeciesAndAspects(stack);
            if(pair == null) return;
            matrices.pushPose();

            var model = PokemonModelRepository.INSTANCE.getPoser(pair.getFirst().getResourceIdentifier(), pair.getSecond());
            var context = model.getContext();
            context.put(RenderContext.Companion.getSPECIES(), pair.getFirst().resourceIdentifier);
            context.put(RenderContext.Companion.getASPECTS(), pair.getSecond());

            var renderLayer = model.getLayer(PokemonModelRepository.INSTANCE.getTexture(pair.getFirst().getResourceIdentifier(), pair.getSecond(), 0F), false, false);

            var transformations = PokemonItemRenderer.Companion.getPositions().get(mode);

            Lighting.setupFor3DItems();
            matrices.scale(transformations.getScale().getX(), transformations.getScale().getY(), transformations.getScale().getZ());
            matrices.translate(transformations.getTranslation().getX(), transformations.getTranslation().getY(), transformations.getTranslation().getZ());
            model.setupAnimStateless(PoseType.PROFILE, 0, 0, 0, 0, 0);
            matrices.translate(model.getProfileTranslation().x, model.getProfileTranslation().y, model.getProfileTranslation().z - 4f);
            matrices.scale(model.getProfileScale(), model.getProfileScale(), 0.15f);

            var rotation = generations_Core$fromEulerXYZDegrees(new Vector3f(transformations.getRotation().getX(), transformations.getRotation().getY(), transformations.getRotation().getZ()));
            matrices.mulPose(rotation);
            rotation.conjugate();
            var vertexConsumer = vertexConsumers.getBuffer(renderLayer);
            matrices.pushPose();
            var packedLight = mode == ItemDisplayContext.GUI ? LightTexture.pack(13, 13) : light;

            var tint = pokemonItem.tint(stack);

            model.withLayerContext(vertexConsumers, null, PokemonModelRepository.INSTANCE.getLayers(pair.getFirst().getResourceIdentifier(), pair.getSecond()), new Function0<Unit>() {
                @Override
                public Unit invoke() {
                    model.render(model.getContext(), matrices, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, tint.x, tint.y, tint.z, tint.w);
                    return Unit.INSTANCE;
                }
            });

            model.setDefault();
            matrices.popPose();
            matrices.popPose();
            Lighting.setupForFlatItems();
        }
    }

    @Unique
    private static Quaternionf generations_Core$fromEulerXYZDegrees(Vector3f vector) {
        return generations_Core$fromEulerXYZ((float) Math.toRadians(vector.x), (float) Math.toRadians(vector.y), (float) Math.toRadians(vector.z));
    }

    @Unique
    private static Quaternionf generations_Core$fromEulerXYZ(float x, float y, float z) {
        var rotation = new Quaternionf();

        generations_Core$hamiltonProduct(rotation, new Quaternionf(sin(x / 2F), 0F, 0F, cos(x / 2F)));
        generations_Core$hamiltonProduct(rotation, new Quaternionf(0F, sin(y / 2F), 0F, cos(y / 2F)));
        generations_Core$hamiltonProduct(rotation, new Quaternionf(0F, 0F, sin(z / 2F), cos(z / 2F)));
        return rotation;
    }

    @Unique
    private static void generations_Core$hamiltonProduct(Quaternionf target, Quaternionf other) {
        var f = target.x;
        var g = target.y;
        var h = target.z;
        var i = target.w;
        var j = other.x;
        var k = other.y;
        var l = other.z;
        var m = other.w;
        target.x = i * j + f * m + g * l - h * k;
        target.y = i * k - f * l + g * m + h * j;
        target.z = i * l + f * k - g * j + h * m;
        target.w = i * m - f * j - g * k - h * l;
    }
}
