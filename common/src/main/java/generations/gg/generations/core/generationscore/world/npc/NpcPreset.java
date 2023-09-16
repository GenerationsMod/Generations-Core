package generations.gg.generations.core.generationscore.world.npc;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import generations.gg.generations.core.generationscore.world.npc.display.NpcDisplayData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class NpcPreset {

    public static Codec<NpcPreset> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            NpcDisplayData.CODEC.fieldOf("display").forGetter(NpcPreset::getDisplayData),
            ResourceLocation.CODEC.optionalFieldOf("shop").forGetter(preset -> Optional.ofNullable(preset.shopKey)),
            ResourceLocation.CODEC.optionalFieldOf("dialogue").forGetter(preset -> Optional.ofNullable(preset.dialogueKey))
    ).apply(instance, (display, shop, dialogue) -> new NpcPreset(display, shop.orElse(null), dialogue.orElse(null))));

    private final NpcDisplayData displayData;
    @Nullable
    private final ResourceLocation shopKey;
    @Nullable
    private final ResourceLocation dialogueKey;

    public NpcPreset(NpcDisplayData displayData, @Nullable ResourceLocation shopKey, @Nullable ResourceLocation dialogueKey) {
        this.displayData = displayData;
        this.shopKey = shopKey;
        this.dialogueKey = dialogueKey;
    }

    public NpcPreset(FriendlyByteBuf buf) {
        this(new NpcDisplayData(buf), buf.readNullable(FriendlyByteBuf::readResourceLocation), buf.readNullable(FriendlyByteBuf::readResourceLocation));
    }

    public NpcDisplayData getDisplayData() {
        return displayData;
    }

    @Nullable
    public ResourceLocation getShopKey() {
        return shopKey;
    }

    @Nullable
    public ResourceLocation getDialogueKey() {
        return dialogueKey;
    }

    public void encode(FriendlyByteBuf buffer) {
        displayData.serializeToByteBuf(buffer);
        buffer.writeNullable(shopKey, FriendlyByteBuf::writeResourceLocation);
        buffer.writeNullable(dialogueKey, FriendlyByteBuf::writeResourceLocation);
    }
}