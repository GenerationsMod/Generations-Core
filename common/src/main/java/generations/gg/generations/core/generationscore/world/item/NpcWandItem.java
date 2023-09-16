package generations.gg.generations.core.generationscore.world.item;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.GenerationsNetwork;
import generations.gg.generations.core.generationscore.network.packets.npc.S2COpenNpcCustomizationScreenPacket;
import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class NpcWandItem extends Item {
    public NpcWandItem(Properties arg) {
        super(arg);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null && context.getPlayer() instanceof ServerPlayer serverPlayer) {
            BlockPos pos = context.getClickedPos();
            PlayerNpcEntity npcEntity = new PlayerNpcEntity(GenerationsEntities.PLAYER_NPC.get(), serverPlayer.level());
            npcEntity.loadPreset(GenerationsCore.id("npc_default"));
            VoxelShape collisionShape = serverPlayer.level().getBlockState(pos).getBlockSupportShape(serverPlayer.level(), pos);
            double blockHeight = collisionShape.isEmpty() ? 0 : collisionShape.bounds().maxY;
            npcEntity.setPos(pos.getX() + 0.5, pos.getY() + blockHeight, pos.getZ() + 0.5);
            serverPlayer.level().addFreshEntity(npcEntity);
            GenerationsNetwork.INSTANCE.sendPacketToPlayer(serverPlayer, new S2COpenNpcCustomizationScreenPacket(npcEntity.getId()));
        }
        return InteractionResult.PASS;
    }
}