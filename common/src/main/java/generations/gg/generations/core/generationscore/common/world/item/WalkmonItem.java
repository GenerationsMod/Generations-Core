package generations.gg.generations.core.generationscore.common.world.item;

import com.cobblemon.mod.common.api.events.CobblemonEvents;
import generations.gg.generations.core.generationscore.common.network.GenerationsNetwork;
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer;
import generations.gg.generations.core.generationscore.common.world.container.WalkmonContainer;
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer;
import generations.gg.generations.core.generationscore.common.world.container.WalkmonContainer;
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer;
import generations.gg.generations.core.generationscore.common.world.container.WalkmonContainer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class WalkmonItem extends Item {
    private final int row;
    private final String defaultTranslation;
    //TODO use actual song duration here!
    private static final int SONG_DURATION = 2400;  // 2 minutes at 20 ticks/second
    private static final String SONG_TIME_TAG = "SongTimeRemaining";

    public WalkmonItem(Properties properties, int row, String type) {
        super(properties);
        this.defaultTranslation = "container." + type;
        this.row = row;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (!level.isClientSide() && usedHand == InteractionHand.MAIN_HAND) {
            getDiscs(player.getItemInHand(usedHand)).openScreen(player, player.getInventory().selected);
        }

        return super.use(level, player, usedHand);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Player player, int slot, boolean isSelected) {
        if (!world.isClientSide && player instanceof ServerPlayer serverPlayer) {
            CompoundTag tag = stack.getOrCreateTag();

            // Check if the song has started
            if (!tag.contains(SONG_TIME_TAG)) {
                tag.putInt(SONG_TIME_TAG, SONG_DURATION);  // Initialize the song duration
                startPlayingSong(serverPlayer);
            } else {
                int timeRemaining = tag.getInt(SONG_TIME_TAG);
                if (timeRemaining > 0) {
                    tag.putInt(SONG_TIME_TAG, timeRemaining - 1);  // Decrease the time remaining
                } else {
                    stopPlayingSong(serverPlayer);  // Stop the song when time runs out
                    startPlayingSong(serverPlayer);  // Start the next song if necessary
                }
            }
        }
    }

    //TODO sounds/custom-disc need to be the path of the actual music disc sounds!

    // Start playing a song for the player
    private void startPlayingSong(ServerPlayer player) {
        System.out.println("Started playing a song for player " + player.getUUID());

        // Send a packet to the client to start the song
        ResourceLocation soundLocation = new ResourceLocation("generations", "sounds/custom-disc");
        GenerationsNetwork.INSTANCE.sendPacketToPlayer(player, new PlaySoundPacket(soundLocation, true));
    }

    // Stop playing the current song for the player
    private void stopPlayingSong(ServerPlayer player) {
        System.out.println("Stopped playing a song for player " + player.getUUID());

        // Send a packet to the client to stop the song
        ResourceLocation soundLocation = new ResourceLocation("generation", "sounds/custom-disc");
        GenerationsNetwork.INSTANCE.sendPacketToPlayer(player, new PlaySoundPacket(soundLocation, false));
    }

    public GenericContainer.SimpleGenericContainer getDiscs(ItemStack stack) {
        var container = new DiscHolder();
        container.fromTag(stack.getOrCreateTag().getList("inventory", Tag.TAG_COMPOUND));
        return container;
    }

    public void saveDiscs(GenericContainer.SimpleGenericContainer container, ItemStack stack) {
        stack.getOrCreateTag().put("inventory", container.createTag());
    }

    public class DiscHolder extends GenericContainer.SimpleGenericContainer {
        public DiscHolder() {
            super(9, WalkmonItem.this.row);
        }

        @Override
        public @NotNull Component getDisplayName() {
            return Component.translatable(WalkmonItem.this.defaultTranslation);
        }

        @Override
        public @Nullable AbstractContainerMenu createMenu(int i, @NotNull Inventory arg, @NotNull Player arg2) {
            return new WalkmonContainer(i, arg, this, arg2.getInventory().selected);
        }

        public Optional<ItemStack> getDisc() {
            return Optional.empty();
        }
    }
}
