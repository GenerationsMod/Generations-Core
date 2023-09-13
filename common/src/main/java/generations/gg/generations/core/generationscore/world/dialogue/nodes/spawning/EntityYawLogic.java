package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning;

import com.google.gson.JsonObject;
import dev.architectury.utils.value.FloatSupplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

public record EntityYawLogic(ResourceKey<EntityType<?>> key) implements YawLogic {
    @Override
    public FloatSupplier createSupplier(Player player) {
        var world = player.level();
        var type = BuiltInRegistries.ENTITY_TYPE.getOrThrow(key());
        var entity = world.getEntities(player, AABB.ofSize(player.getOnPos().getCenter(), 10, 10, 10)).stream().filter(obj -> type.equals(obj.getType())).findFirst().orElse(player);
        return entity::getYRot;
    }

    public void toJson(JsonObject json) {
        json.addProperty("entry", key.location().toString());
    }

    public static EntityYawLogic fromJson(JsonObject object) {
        return new EntityYawLogic(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.tryParse(object.getAsJsonPrimitive("entry").getAsString())));
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buffer) {
        buffer.writeResourceKey(key);
    }

    @Override
    public YawLogicType<EntityYawLogic> type() {
        return YawLogicTypes.ENTITY;
    }

    public static EntityYawLogic decode(FriendlyByteBuf buffer) {
        return new EntityYawLogic(buffer.readResourceKey(Registries.ENTITY_TYPE));
    }
}