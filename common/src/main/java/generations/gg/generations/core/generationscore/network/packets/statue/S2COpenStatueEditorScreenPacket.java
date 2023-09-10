package generations.gg.generations.core.generationscore.network.packets.statue;

import dev.architectury.utils.EnvExecutor;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.statue.StatueEditorScreen;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.network.packets.dialogue.S2CCloseScreenPacket;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import static dev.architectury.utils.Env.CLIENT;

public record S2COpenStatueEditorScreenPacket(int entityId) implements GenerationsNetworkPacket<S2COpenStatueEditorScreenPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("open_statue_editor_screen");

    public static S2COpenStatueEditorScreenPacket decode(FriendlyByteBuf buf) {
        return new S2COpenStatueEditorScreenPacket(buf.readInt());
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(entityId());
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public static class Handler implements ClientNetworkPacketHandler<S2COpenStatueEditorScreenPacket> {
        public static final Handler INSTANCE = new Handler();


        @Override
        public void handle(S2COpenStatueEditorScreenPacket packet) {
            EnvExecutor.runInEnv(CLIENT, () -> () -> Minecraft.getInstance().tell(() -> {
                var statueEntity = (StatueEntity) Minecraft.getInstance().level.getEntity(packet.entityId());
                Minecraft.getInstance().setScreen(new StatueEditorScreen(statueEntity));
            }));
        }
    }
}