package generations.gg.generations.core.generationscore.mixin;

import com.mojang.authlib.GameProfile;
import generations.gg.generations.core.generationscore.world.item.DistanceTraveled;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Collection;
import java.util.stream.Stream;


@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player {
    public ServerPlayerMixin(Level level, BlockPos pos, float yRot, GameProfile gameProfile) {
        super(level, pos, yRot, gameProfile);
    }

    @Override
    public void checkMovementStatistics(double distanceX, double distanceY, double distanceZ) {
        super.checkMovementStatistics(distanceX, distanceY, distanceZ);

        var distance = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
        var inventory = getInventory();
        var fullInventoryList = Stream.of(inventory.items, inventory.armor, inventory.offhand).flatMap(Collection::stream).toList();

        for (var stack : fullInventoryList) {
            if(stack.getItem() instanceof DistanceTraveled distanceTraveled) distanceTraveled.incrementDistance((ServerPlayer) (Object) this, stack, distance);
        }
    }
}
