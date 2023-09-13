package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning;

import com.google.gson.JsonObject;
import dev.architectury.utils.value.FloatSupplier;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders.AngleProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public record BlockYawLogic(ResourceKey<Block> key) implements YawLogic {
    public FloatSupplier createSupplier(Player player) {
        var world = player.level();
        var optional = BlockPos.withinManhattanStream(player.getOnPos(), 10, 10, 10).filter(a -> world.getBlockState(a).getBlock().arch$holder().is(key))
                .findFirst();
        if (optional.isPresent()) {
            var blockEntity = world.getBlockEntity(optional.get());
            float angle;
            if (blockEntity instanceof AngleProvider angleProvider) {
                angle = angleProvider.getAngle();
            } else {
                BlockState state = blockEntity != null ? blockEntity.getBlockState() : world.getBlockState(optional.get());
                angle = state.hasProperty(HorizontalDirectionalBlock.FACING) ? state.getValue(HorizontalDirectionalBlock.FACING).toYRot() : 0f;
            }
            return () -> angle;
        }
        return () -> 0f;
    }

    public static BlockYawLogic of(ResourceKey<Block> key) {
        return new BlockYawLogic(key);
    }

    @Override
    public YawLogicType<BlockYawLogic> type() {
        return YawLogicTypes.BLOCK;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buffer) {
        buffer.writeResourceKey(key);
    }

    public void toJson(JsonObject json) {
        json.addProperty("entry", key.location().toString());
    }

    public static BlockYawLogic fromJson(JsonObject object) {
        return new BlockYawLogic(ResourceKey.create(Registries.BLOCK, ResourceLocation.tryParse(object.getAsJsonPrimitive("entry").getAsString())));
    }

    public static BlockYawLogic decode(FriendlyByteBuf buffer) {
        return new BlockYawLogic(buffer.readResourceKey(Registries.BLOCK));
    }
}