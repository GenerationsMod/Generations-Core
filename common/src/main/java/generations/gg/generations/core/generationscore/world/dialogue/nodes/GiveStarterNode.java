package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.pokemod.pokemod.api.data.pixelmon.BuiltinPixelmonSpecies;
import com.pokemod.pokemod.api.data.player.PixelmonParty;
import com.pokemod.pokemod.command.PokeModCommands;
import com.pokemod.pokemod.config.PokeModConfig;
import com.pokemod.pokemod.registries.types.EncounterInfo;
import com.pokemod.pokemod.registries.types.PixelmonData;
import com.pokemod.pokemod.world.npc.dialogue.DialogueNodeType;
import com.pokemod.pokemod.world.npc.dialogue.DialogueNodeTypes;
import com.pokemod.pokemod.world.npc.dialogue.DialoguePlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.locale.Language;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

import java.time.LocalDateTime;
import java.util.Collections;

public class GiveStarterNode extends AbstractNode {
    public static final Codec<GiveStarterNode> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("targetDim").forGetter(GiveStarterNode::getTargetDim)
    ).apply(instance, GiveStarterNode::new));

    private final ResourceLocation targetDim;

    public GiveStarterNode(ResourceLocation dimension) {
        this.targetDim = dimension;
    }

    @Override
    public void run(ServerPlayer player, DialoguePlayer dialoguePlayer) {
        //ScheduledTask.schedule(() -> System.out.println("hopefully this runs in 5 seconds. Fade to white on client then teleport. Finish starter"), 20 * 5);
        var server = player.getServer();

        if (PokeModConfig.getConfig().starterSettings.useBuiltinSelector) {
            var data = PixelmonData.of(BuiltinPixelmonSpecies.QUAQUAVAL.location(), 5);
            data.calculateStats(false);
            data.encounterInfo = new EncounterInfo(LocalDateTime.now(), player.getLevel().getBiome(player.getOnPos()).unwrapKey()
                    .map(ResourceKey::location)
                    .map(a -> a.toLanguageKey("biome"))
                    .map(Language.getInstance()::getOrDefault)
                    .map(PokeModCommands::addIndefiniteArticle)
                    .orElse("unknown"), data.levelContainer.getLevel());

            var party = PixelmonParty.of(player);
            party.addToParty(data);

            if (!player.gameMode.isCreative()) player.setGameMode(player.gameMode.getPreviousGameModeForPlayer());
            performTeleport(player, server.overworld(), 0, 0);
        }
    }

    private static void performTeleport(ServerPlayer player, ServerLevel level, float yaw, float pitch) {
        var blockpos = new BlockPos(level.getLevelData().getXSpawn(), level.getLevelData().getYSpawn(), level.getLevelData().getZSpawn());
        if (!Level.isInSpawnableBounds(blockpos)) throw new RuntimeException("Invalid Teleport Position");
        float wrapYaw = Mth.wrapDegrees(yaw);
        float wrapPitch = Mth.wrapDegrees(pitch);
        var chunkpos = new ChunkPos(blockpos);
        level.getChunkSource().addRegionTicket(TicketType.POST_TELEPORT, chunkpos, 1, player.getId());
        player.stopRiding();
        if (player.isSleeping()) player.stopSleepInBed(true, true);
        if (level == player.level) player.connection.teleport(blockpos.getX(), blockpos.getY(), blockpos.getZ(), wrapYaw, wrapPitch, Collections.emptySet());
        else player.teleportTo(level, blockpos.getX(), blockpos.getY(), blockpos.getZ(), wrapYaw, wrapPitch);
        player.setYHeadRot(wrapYaw);

        if (!player.isFallFlying()) {
            player.setDeltaMovement(player.getDeltaMovement().multiply(1.0, 0.0, 1.0));
            player.setOnGround(true);
        }
    }

    @Override
    public Codec<? extends AbstractNode> getCodec() {
        return CODEC;
    }

    @Override
    public DialogueNodeType<?> getType() {
        return DialogueNodeTypes.GIVE_STARTER;
    }

    public ResourceLocation getTargetDim() {
        return targetDim;
    }
}
