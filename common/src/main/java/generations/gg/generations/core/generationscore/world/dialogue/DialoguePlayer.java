package generations.gg.generations.core.generationscore.world.dialogue;

import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNode;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nullable;

public class DialoguePlayer {

    @Nullable
    private final LivingEntity source;
    private final ServerPlayer player;
    private final boolean forcedUntilEnd;
    public AbstractNode currentNode;

    public DialoguePlayer(ResourceLocation id, @Nullable LivingEntity source, ServerPlayer serverPlayer, boolean forcedUntilEnd) {
        this(PokeModRegistries.DIALOGUES.get(id), source, serverPlayer, forcedUntilEnd);
    }

    public DialoguePlayer(DialogueGraph graph, @Nullable LivingEntity source, ServerPlayer player, boolean forcedUntilEnd) {
        this.player = player;
        this.source = source;
        this.currentNode = graph.root();
        this.forcedUntilEnd = forcedUntilEnd;

        if (DialogueManager.DIALOGUE_MAP.containsKey(player))
            throw new RuntimeException(player.getName() + "[" + player.getStringUUID() + "] Is already in a dialogue menu");

        DialogueManager.DIALOGUE_MAP.put(player, this);
        PokeModNetworking.sendPacket(new S2COpenDialogueMenuPacket(!forcedUntilEnd), player);
        update();
    }

    public void openAndNextNode() {
        PokeModNetworking.sendPacket(new S2COpenDialogueMenuPacket(!forcedUntilEnd), player);
        nextNode();
    }

    @Nullable
    public LivingEntity getSource() {
        return source;
    }

    public void nextNode() {
        this.currentNode = currentNode.getNext();
        update();
    }

    public void update() {
        if (currentNode == null) discard();
        else currentNode.run(player, this);
    }

    public void discard() {
        DialogueManager.DIALOGUE_MAP.remove(player);
        PokeModNetworking.sendPacket(new S2CCloseScreenPacket(), player);
    }
}
