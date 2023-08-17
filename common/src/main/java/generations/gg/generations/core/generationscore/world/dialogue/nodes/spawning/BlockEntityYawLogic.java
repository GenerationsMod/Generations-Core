package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning;

import dev.architectury.utils.value.FloatSupplier;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders.AngleProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Optional;

public record BlockEntityYawLogic(ResourceKey<BlockEntityType<?>> blockEntity) implements YawLogic {
    public FloatSupplier createSupplier(Player player) {
        var world = player.level();
        var optional = BlockPos.withinManhattanStream(player.getOnPos(), 10, 10, 10).map(a -> world.getBlockEntity(a, BuiltInRegistries.BLOCK_ENTITY_TYPE.get(blockEntity)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
        if (optional.isPresent()) {
            var blockEntity = optional.get();
            var angle = blockEntity instanceof AngleProvider angleProvider ? angleProvider.getAngle() : blockEntity.getBlockState().hasProperty(HorizontalDirectionalBlock.FACING) ? blockEntity.getBlockState().getValue(HorizontalDirectionalBlock.FACING).toYRot() : 0f;
            return () -> angle;
        }
        return () -> 0f;
    }

    public static BlockEntityYawLogic of(ResourceKey<BlockEntityType<?>> key) {
        return new BlockEntityYawLogic(key);
    }
}