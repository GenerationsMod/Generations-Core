package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.dialogue.S2CHealDialoguePacket;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import generations.gg.generations.core.generationscore.world.level.block.entities.HealerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HealNode extends AbstractNode implements DialogueContainingNode {
    private final List<String> text;
    private final boolean healerRequired;
    private final int radius;
    private final AbstractNode next;

    public HealNode(List<String> text, boolean healerRequired, int radius, @Nullable AbstractNode next) {
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
                        if (source.level().getBlockEntity(new BlockPos(x, y, z)) instanceof HealerBlockEntity healerBlockEntity) {
                            // TODO heal player's pixelmon when the healer block gets implemented
                            System.out.println("Healing player's pixelmon through dialogue");
                        }
                    }
                }
            }
        } else {
            // TODO same delay as healer block
//            var party = HealingMachineBlockEntity.of(player);
//            for (var data : party) {
//                data.hp = data.getMaxHp();
                // do we need to heal pixelmon in-game too?
                /*var inGamePixelmon = party.findInGameCopy(data);
                if (inGamePixelmon != null) {
                    inGamePixelmon.setHealth(data.getMaxHp());
                }*/
//            }
        }
        GenerationsCore.getImplementation().getNetworkManager().sendPacketToPlayer(player, new S2CHealDialoguePacket(text, next instanceof DialogueContainingNode));
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
    public AbstractNodeType<?> type() {
        return AbstractNodeTypes.HEAL;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeCollection(text, FriendlyByteBuf::writeUtf);
        friendlyByteBuf.writeBoolean(healerRequired);
        friendlyByteBuf.writeInt(radius);
        friendlyByteBuf.writeNullable(next, AbstractNodeTypes::encode);
    }

    public static HealNode decode(FriendlyByteBuf buf) {
        return new HealNode(
                buf.readCollection(ArrayList::new, FriendlyByteBuf::readUtf),
                buf.readBoolean(),
                buf.readInt(),
                buf.readNullable(AbstractNodeTypes::decode)
        );
    }

    @Override
    public void toJson(JsonObject json) {
        json.add("text", JsonUtils.toJsonList(text, JsonArray::add));
        json.addProperty("healerRequired", healerRequired);
        json.addProperty("radius", radius);
        JsonUtils.toNullable("next", json, next, AbstractNodeTypes::toJson);
    }

    public static HealNode fromJson(JsonObject object) {
        return new HealNode(JsonUtils.fromJsonList("next", object, JsonElement::getAsString), object.getAsJsonPrimitive("healerRequired").getAsBoolean(), object.getAsJsonPrimitive("radius").getAsInt(), JsonUtils.fromNullable("next", object, AbstractNodeTypes::fromJson));
    }
}
