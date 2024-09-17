## Step 1: **Server-Side Song Management Using `inventoryTick`**

The server tracks how long the current song has been playing and decides when to stop the song or move to the next one. This is done in the `inventoryTick` method of the record player item.

### Code: `RecordPlayerItem.java`

```java
package com.example.testmod.items;

import com.example.testmod.network.ModNetwork;
import com.example.testmod.network.PlaySoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.PacketDistributor;

public class RecordPlayerItem extends Item {

    private static final int SONG_DURATION = 2400;  // 2 minutes at 20 ticks/second
    private static final String SONG_TIME_TAG = "SongTimeRemaining";

    public RecordPlayerItem(Properties properties) {
        super(properties);
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

    // Start playing a song for the player
    private void startPlayingSong(ServerPlayer player) {
        System.out.println("Started playing a song for player " + player.getUUID());
        
        // Send a packet to the client to start the song
        ResourceLocation soundLocation = new ResourceLocation("testmod", "sounds/my_custom_record");
        ModNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PlaySoundPacket(soundLocation, true));
    }

    // Stop playing the current song for the player
    private void stopPlayingSong(ServerPlayer player) {
        System.out.println("Stopped playing a song for player " + player.getUUID());
        
        // Send a packet to the client to stop the song
        ResourceLocation soundLocation = new ResourceLocation("testmod", "sounds/my_custom_record");
        ModNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PlaySoundPacket(soundLocation, false));
    }
}
```

### What this does:
1. **`inventoryTick`**: Manages the countdown of the song timer while the record player item is in the player’s inventory.
2. **Server-Side Sound Management**: When the song ends, the server automatically stops it and starts the next song (if necessary) by sending packets to the client.
3. **Client Receives Packets**: The client plays or stops the sound when instructed by the server.


## Step 2: **Client-Side Sound Playback**

The client plays or stops sounds based on packets sent by the server. The client has no timing logic and just follows the server's instructions.

### Code: `PlaySoundPacket.java`

```java
package com.example.testmod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import net.minecraft.sounds.SoundSource;

import java.util.function.Supplier;

public class PlaySoundPacket {

    private final ResourceLocation soundLocation;
    private final boolean play;  // true = start playing, false = stop playing

    public PlaySoundPacket(ResourceLocation soundLocation, boolean play) {
        this.soundLocation = soundLocation;
        this.play = play;
    }

    public static void encode(PlaySoundPacket packet, FriendlyByteBuf buf) {
        buf.writeResourceLocation(packet.soundLocation);
        buf.writeBoolean(packet.play);
    }

    public static PlaySoundPacket decode(FriendlyByteBuf buf) {
        return new PlaySoundPacket(buf.readResourceLocation(), buf.readBoolean());
    }

    public static void handle(PlaySoundPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Handle client-side sound playback
            if (ctx.get().getDirection().getReceptionSide().isClient()) {
                if (packet.play) {
                    // Start playing the sound
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forMusic(packet.soundLocation));
                } else {
                    // Stop playing the sound
                    Minecraft.getInstance().getSoundManager().stop(packet.soundLocation, SoundSource.RECORDS);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
```

### What this does:
1. **Client Playback**: The client receives a packet from the server, telling it whether to start or stop the sound.
2. **No Client-Side Timing**: The client doesn’t manage any timing or logic for playing or stopping songs—it only reacts to the server’s packets.


## Step 3: **Register the Packet in the Network**

Make sure the `PlaySoundPacket` is registered in your mod's network so that the client and server can communicate.

### Code: `ModNetwork.java`

```java
package com.example.testmod.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetwork {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation("testmod", "network"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );

    public static void registerPackets() {
        int id = 0;
        INSTANCE.registerMessage(id++, PlaySoundPacket.class, PlaySoundPacket::encode, PlaySoundPacket::decode, PlaySoundPacket::handle);
    }
}
```

### What this does:
1. Packet Registration: Ensures that the server and client can communicate via PlaySoundPacket.
