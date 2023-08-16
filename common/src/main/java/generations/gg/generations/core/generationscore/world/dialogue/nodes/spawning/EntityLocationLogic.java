package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public record EntityLocationLogic(ResourceKey<EntityType<?>> entity) implements LocationLogic {
    public static Codec<EntityLocationLogic> CODEC = RecordCodecBuilder.create(instance -> instance.group(ResourceKey.codec(Registries.ENTITY_TYPE).fieldOf("entry").forGetter(EntityLocationLogic::entity)).apply(instance, EntityLocationLogic::new));

    @Override
    public Supplier<Vec3> createSupplier(Player player) {
        var world = player.level();
        var type = BuiltInRegistries.ENTITY_TYPE.getOrThrow(entity());

        var pos = world.getEntities(player, AABB.ofSize(player.getOnPos().getCenter(), 10, 10, 10)).stream().filter(obj -> type.equals(obj.getType())).findFirst().orElse(player).getOnPos().getCenter();
        return () -> pos;
    }
}
