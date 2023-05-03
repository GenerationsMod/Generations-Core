package com.pokemod.pokemod.world.item;

import com.pokemod.pokemod.PokeMod;
import com.pokemod.pokemod.network.api.PokeModNetworking;
import com.pokemod.pokemod.network.packets.npc.S2COpenNpcCustomizationScreenPacket;
import com.pokemod.pokemod.world.entity.PlayerNpcEntity;
import com.pokemod.pokemod.world.entity.PokeModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class NpcEditorItem extends Item {
    public NpcEditorItem(Properties arg) {
        super(arg);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null && context.getPlayer() instanceof ServerPlayer serverPlayer) {
            BlockPos pos = context.getClickedPos();
            PlayerNpcEntity npcEntity = new PlayerNpcEntity(PokeModEntities.PLAYER_NPC.get(), serverPlayer.level);
            npcEntity.loadPreset(PokeMod.id("npc_default"));
            VoxelShape collisionShape = serverPlayer.level.getBlockState(pos).getBlockSupportShape(serverPlayer.level, pos);
            double blockHeight = collisionShape.isEmpty() ? 0 : collisionShape.bounds().maxY;
            npcEntity.setPos(pos.getX() + 0.5, pos.getY() + blockHeight, pos.getZ() + 0.5);
            serverPlayer.level.addFreshEntity(npcEntity);
            PokeModNetworking.sendPacket(new S2COpenNpcCustomizationScreenPacket(npcEntity.getId()), serverPlayer);
        }
        return InteractionResult.PASS;
    }
}
