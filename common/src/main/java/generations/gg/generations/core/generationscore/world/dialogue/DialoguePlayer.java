package generations.gg.generations.core.generationscore.world.dialogue;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.dialogue.S2CCloseScreenPacket;
import generations.gg.generations.core.generationscore.network.packets.dialogue.S2COpenDialogueMenuPacket;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNode;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.Objects;

public class DialoguePlayer {
    private DialogueGraph graph;
    private LivingEntity source;
    private ServerPlayer player;
    private boolean forcedUntilEnd;

    public AbstractNode currentNode = null;

    public DialoguePlayer(DialogueGraph graph, LivingEntity source, ServerPlayer player, boolean forcedUntilEnd) {
        this.graph = graph;
        this.source = source;
        this.player = player;
        this.forcedUntilEnd = forcedUntilEnd;

        currentNode = graph.root();
//        if (DialogueManager.DIALOGUE_MAP.containsKey(player)) throw new RuntimeException(player.getName().toString() + "[" + player.getStringUUID() + "] Is already in a dialogue menu");
        DialogueManager.DIALOGUE_MAP.put(player, this);
        GenerationsCore.getImplementation().getNetworkManager().sendPacketToPlayer(
                player,
                new S2COpenDialogueMenuPacket(!forcedUntilEnd)
        );
        update();
    }

    public DialoguePlayer(
            ResourceLocation id,
            LivingEntity source,
            ServerPlayer serverPlayer,
            boolean forcedUntilEnd
    ) {
        this(
                Objects.requireNonNull(
                        Dialogues.instance().get(
                                id
                        )
                ), source, serverPlayer, forcedUntilEnd
        );
    }

    public void openAndNextNode() {
        GenerationsCore.getImplementation().getNetworkManager().sendPacketToPlayer(
            player,
            new S2COpenDialogueMenuPacket(!forcedUntilEnd)
        );
        nextNode();
    }

    public void nextNode() {
        currentNode = currentNode.getNext();
        update();
    }

    public void update() {
        if (currentNode == null) discard(); else currentNode.run(player, this);
    }

    public void discard() {
        DialogueManager.DIALOGUE_MAP.remove(player);
        GenerationsCore.getImplementation().getNetworkManager().sendPacketToPlayer(player, new S2CCloseScreenPacket());
    }

    public LivingEntity getSource() {
        return source;
    }
}
