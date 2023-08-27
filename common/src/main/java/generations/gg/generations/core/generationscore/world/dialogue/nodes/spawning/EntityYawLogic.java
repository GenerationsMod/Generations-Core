package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning;

import dev.architectury.utils.value.FloatSupplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

public record EntityYawLogic(ResourceKey<EntityType<?>> entity) implements YawLogic {
    @Override
    public FloatSupplier createSupplier(Player player) {
        var world = player.level();
        var type = BuiltInRegistries.ENTITY_TYPE.getOrThrow(entity());
        var entity = world.getEntities(player, AABB.ofSize(player.getOnPos().getCenter(), 10, 10, 10)).stream().filter(obj -> type.equals(obj.getType())).findFirst().orElse(player);
        return entity::getYRot;
    }
}