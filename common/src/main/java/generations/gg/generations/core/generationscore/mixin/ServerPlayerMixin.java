package generations.gg.generations.core.generationscore.mixin;

import com.mojang.authlib.GameProfile;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.api.player.BiomesVisited;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.legends.DistanceTraveled;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Stream;


@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player {
    @Shadow public abstract ServerLevel serverLevel();

    @Shadow public abstract ItemEntity drop(ItemStack droppedItem, boolean dropAround, boolean includeThrowerName);

    @Shadow public abstract void sendSystemMessage(Component component);

    @Unique
    ResourceKey<Biome> currentBiome = null;

    public ServerPlayerMixin(Level level, BlockPos pos, float yRot, GameProfile gameProfile) {
        super(level, pos, yRot, gameProfile);
    }

    @Override
    public void checkMovementStatistics(double distanceX, double distanceY, double distanceZ) {
        super.checkMovementStatistics(distanceX, distanceY, distanceZ);
        BiomesVisited biomesVisited = BiomesVisited.get(this);
        if(biomesVisited.numberOfVisitedBiomes() < GenerationsCore.CONFIG.eonDuoFragmentLimit)
        serverLevel().getBiome(blockPosition()).unwrapKey().filter(a -> !a.equals(currentBiome)).ifPresent(biomeResourceKey -> {
            this.currentBiome = biomeResourceKey;


            if(!biomesVisited.hasVisited(currentBiome)) {
                var stack = GenerationsItems.ENIGMA_FRAGMENT.get().getDefaultInstance();
                if(!getInventory().add(stack)) {
                    Containers.dropItemStack(level(), position().x, position().y, position().z, stack);
                }
                biomesVisited.add(currentBiome);
                this.sendSystemMessage(Component.translatable("generations_core.enigma_biome", I18n.get("biome.%s.%s".formatted(currentBiome.location().getNamespace(), currentBiome.location().getPath()))));
            }

        });

        var distance = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
        var inventory = getInventory();
        var fullInventoryList = Stream.of(inventory.items, inventory.armor, inventory.offhand).flatMap(Collection::stream).toList();

        for (var stack : fullInventoryList) {
            if(stack.getItem() instanceof DistanceTraveled distanceTraveled) distanceTraveled.incrementDistance((ServerPlayer) (Object) this, stack, distance);
        }
    }
}
