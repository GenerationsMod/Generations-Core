package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.utils.GameInstance;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeLootBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.stream.Collectors;

public class PokeLootBlock extends GenericRotatableModelBlock<PokeLootBlockEntity> {
    private final String name;
    private final ResourceLocation lootTable;

    protected PokeLootBlock(String name, Properties properties) {
        super(properties, GenerationsBlockEntities.POKE_LOOT, GenerationsBlockEntityModels.POKEBALL);
        this.name = name;
        this.lootTable = GenerationsCore.id("chests/%s_ball".formatted(name));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(!level.isClientSide) {
            unpackLootTable((ServerLevel) level, pos, player);

            return InteractionResult.CONSUME;
        }

        return super.use(state, level, pos, player, hand, hit);
    }

    public String getType() {
        return name;
    }

    public ResourceLocation getLootTableId() {
        return lootTable;
    }

    public void unpackLootTable(ServerLevel level, BlockPos pos, Player player) {
        var lootTableId = this.getLootTableId();
        LootTable lootTable = GameInstance.getServer().getLootData().getLootTable(lootTableId);

        if (player instanceof ServerPlayer) {
            CriteriaTriggers.GENERATE_LOOT.trigger((ServerPlayer)player, lootTableId);getLootTable();
        }

        LootParams.Builder builder = (new LootParams.Builder(level)).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos));
        if (player != null) {
            builder.withLuck(player.getLuck()).withParameter(LootContextParams.THIS_ENTITY, player);
        }

        var list = lootTable.getRandomItems(builder.create(LootContextParamSets.CHEST)).stream().collect(Collectors.toCollection(NonNullList::create));

        Containers.dropContents(level, pos.above(), list);
    }
}