package generations.gg.generations.core.generationscore.network.packets.dialogue;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.dialgoue.configure.ConfigureDialogueScreen;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueGraph;
import generations.gg.generations.core.generationscore.world.dialogue.Dialogues;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.SayNode;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public record S2COpenDialogueEditorScreenPacket(ResourceLocation location) implements GenerationsNetworkPacket<S2COpenDialogueEditorScreenPacket> {
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {
        buf.writeResourceLocation(location);
    }

    public static ResourceLocation ID = GenerationsCore.id("open_dialogue_editor_screen");

    public static S2COpenDialogueEditorScreenPacket decode(FriendlyByteBuf buf) {
        return new S2COpenDialogueEditorScreenPacket(buf.readResourceLocation());
    }


    public static class Handler implements ClientNetworkPacketHandler<S2COpenDialogueEditorScreenPacket> {
        public static final Handler INSTANCE = new Handler();
        public void handle(S2COpenDialogueEditorScreenPacket packet) {
            Minecraft.getInstance().setScreen(
                new ConfigureDialogueScreen(
                    Dialogues.instance().getOrElse(
                        packet.location, new DialogueGraph(
                            new SayNode(
                                    List.of("Hi @p!"), null
                            )
                        )
                    )
                )
            );
        }
    }
}
