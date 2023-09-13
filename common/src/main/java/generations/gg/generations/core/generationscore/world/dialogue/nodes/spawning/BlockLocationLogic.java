package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning;

import com.google.gson.JsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;


 public record BlockLocationLogic(ResourceKey<Block> key) implements LocationLogic {
     public Supplier<Vec3> createSupplier(Player player) {
         var world = player.level();
         var pos = BlockPos.withinManhattanStream(player.getOnPos(), 10, 10, 10)
                 .filter(it -> world.getBlockState(it).getBlock().arch$holder().is(key))
                 .findFirst().orElse(player.getOnPos()).getCenter();
         return () -> pos;
     }

     @Override
     public LocationLogicType<BlockLocationLogic> type() {
         return LocationLogicTypes.BLOCK;
     }

     @Override
     public void encode(@NotNull FriendlyByteBuf buffer) {
         buffer.writeResourceKey(key);
     }

     public static BlockLocationLogic decode(FriendlyByteBuf buffer) {
         return new BlockLocationLogic(buffer.readResourceKey(Registries.BLOCK));
     }

     @Override
     public void toJson(JsonObject json) {
         json.addProperty("entry", key.location().toString());
     }

     public static BlockLocationLogic fromJson(JsonObject object) {
         return new BlockLocationLogic(ResourceKey.create(Registries.BLOCK, ResourceLocation.tryParse(object.getAsJsonPrimitive("entry").getAsString())));
     }

     public static BlockLocationLogic of(ResourceKey<Block> key) {
         return new BlockLocationLogic(key);
     }
 }

