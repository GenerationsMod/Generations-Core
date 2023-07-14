package generations.gg.generations.core.generationscore.world.level.block.entities;

import dev.architectury.utils.GameInstance;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.world.level.block.PokeLootBlock;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Collectors;

public class PokeLootBlockEntity extends ModelProvidingBlockEntity implements ModelContextProviders.VariantProvider {
    public PokeLootBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(GenerationsBlockEntities.POKE_LOOT.get(), pPos, pBlockState);
    }

    @Override
    public String getVariant() {
        return getBlockState().getBlock() instanceof PokeLootBlock loot ? loot.getType() : "poke";
    }
}