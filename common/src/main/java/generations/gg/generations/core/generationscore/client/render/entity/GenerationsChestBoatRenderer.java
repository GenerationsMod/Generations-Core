package generations.gg.generations.core.generationscore.client.render.entity;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.entity.GenerationsChestBoatEntity;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.stream.Stream;

public class GenerationsChestBoatRenderer extends BoatRenderer {
    private final Map<GenerationsChestBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public GenerationsChestBoatRenderer(EntityRendererProvider.Context context) {
        super(context, false);
        this.shadowRadius = 0.8F;
        this.boatResources = Stream.of(GenerationsChestBoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap((p_173938_) -> p_173938_,
                (type) -> Pair.of(GenerationsCore.id("textures/key/chest_boat/"+ type.getName() + ".png"),
                        new BoatModel(context.bakeLayer(new ModelLayerLocation(new ResourceLocation("minecraft", "chest_boat/oak"), "main"))))));
    }

    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(@NotNull Boat boat) {
        if(boat instanceof GenerationsChestBoatEntity modBoat) return this.boatResources.get(modBoat.getModBoatType());
        return null;
    }
}

