package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning;

import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public record EntityLocationLogic(ResourceKey<EntityType<?>> key) implements LocationLogic {
    @Override
    public Supplier<Vec3> createSupplier(Player player) {
        var world = player.level();
        var type = BuiltInRegistries.ENTITY_TYPE.getOrThrow(key());

        var pos = world.getEntities(player, AABB.ofSize(player.getOnPos().getCenter(), 10, 10, 10)).stream().filter(obj -> type.equals(obj.getType())).findFirst().orElse(player).getOnPos().getCenter();
        return () -> pos;
    }

    @Override
    public LocationLogicType<EntityLocationLogic> type() {
        return LocationLogicTypes.ENTITY;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buffer) {
        buffer.writeResourceKey(key);
    }

    @Override
    public void toJson(JsonObject json) {
        json.addProperty("entry", key.location().toString());
    }

    public static EntityLocationLogic fromJson(JsonObject object) {
        return new EntityLocationLogic(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.tryParse(object.getAsJsonPrimitive("entry").getAsString())));
    }

    public static EntityLocationLogic decode(FriendlyByteBuf buffer) {
        return new EntityLocationLogic(buffer.readResourceKey(Registries.ENTITY_TYPE));
    }
}
