package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.pokemod.pokemod.api.data.Codecs;
import com.pokemod.pokemod.api.data.player.PixelmonParty;
import com.pokemod.pokemod.network.api.PokeModNetworking;
import com.pokemod.pokemod.network.packets.dialogue.S2CHealDialoguePacket;
import com.pokemod.pokemod.world.level.block.entities.HealerBlockEntity;
import com.pokemod.pokemod.world.npc.dialogue.DialogueNodeType;
import com.pokemod.pokemod.world.npc.dialogue.DialogueNodeTypes;
import com.pokemod.pokemod.world.npc.dialogue.DialoguePlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
import java.util.Optional;

public class HealNode extends AbstractNode implements DialogueContainingNode{
    public static final Codec<HealNode> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codecs.listCodec(Codec.STRING).fieldOf("text").forGetter(node -> node.text),
            Codec.BOOL.fieldOf("healer_required").forGetter(node -> node.healerRequired),
            Codec.INT.optionalFieldOf("radius").forGetter(node -> node.healerRequired ? Optional.of(node.radius) : Optional.empty()),
            AbstractNode.CODEC_BY_NAME.optionalFieldOf("next").forGetter(node -> Optional.ofNullable(node.next)))
            .apply(instance, (text, healerRequired, radius, next) -> new HealNode(text, healerRequired, radius.orElse(0), next.orElse(null))));

    private final List<String> text;
    private final boolean healerRequired;
    private final int radius;
    private final AbstractNode next;

    public HealNode(List<String> text, boolean healerRequired, int radius, AbstractNode next) {
        this.text = text;
        this.healerRequired = healerRequired;
        this.radius = radius;
        this.next = next;
    }

    @Override
    public void run(ServerPlayer player, DialoguePlayer dialoguePlayer) {
        if (healerRequired) {
            LivingEntity source = dialoguePlayer.getSource() == null ? player : dialoguePlayer.getSource();
            BlockPos center = source.blockPosition();
            int maxX = center.getX() + radius;
            int maxY = center.getY() + radius;
            int maxZ = center.getZ() + radius;
            for (int x = center.getX() - radius; x < maxX; x++) {
                for (int y = center.getY() - radius; y < maxY; y++) {
                    for (int z = center.getZ() - radius; z < maxZ; z++) {
                        if (source.getLevel().getBlockEntity(new BlockPos(x, y, z)) instanceof HealerBlockEntity healerBlockEntity) {
                            // TODO heal player's pixelmon when the healer block gets implemented
                            System.out.println("Healing player's pixelmon through dialogue");
                        }
                    }
                }
            }
        } else {
            // TODO same delay as healer block
            var party = PixelmonParty.of(player);
            for (var data : party) {
                data.hp = data.getMaxHp();
                // do we need to heal pixelmon in-game too?
                /*var inGamePixelmon = party.findInGameCopy(data);
                if (inGamePixelmon != null) {
                    inGamePixelmon.setHealth(data.getMaxHp());
                }*/
            }
        }
        PokeModNetworking.sendPacket(new S2CHealDialoguePacket(text, next instanceof DialogueContainingNode), player);
    }

    @Override
    public AbstractNode getNext() {
        return next;
    }

    @Override
    public void setParent(AbstractNode parent) {
        super.setParent(parent);
        if (next != null) {
            next.setParent(this);
        }
    }

    @Override
    public DialogueNodeType<?> getType() {
        return DialogueNodeTypes.HEAL;
    }

    @Override
    public Codec<? extends AbstractNode> getCodec() {
        return CODEC;
    }
}
