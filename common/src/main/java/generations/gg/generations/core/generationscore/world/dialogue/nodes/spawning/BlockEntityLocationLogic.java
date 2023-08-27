package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;


 record BlockEntityLocationLogic(ResourceKey<BlockEntityType<?>> key) implements LocationLogic {
     public Supplier<Vec3> createSupplier(Player player) {
         var world = player.level();
         var pos = BlockPos.withinManhattanStream(player.getOnPos(), 10, 10, 10)
                 .filter(it -> world.getBlockEntity(it, BuiltInRegistries.BLOCK_ENTITY_TYPE.getHolderOrThrow(key).value()).isPresent())
                 .findFirst().orElse(player.getOnPos()).getCenter();
         return () -> pos;
     }

     public static BlockEntityLocationLogic of(ResourceKey<BlockEntityType<?>> key) {
         return new BlockEntityLocationLogic(key);
     }
 }

