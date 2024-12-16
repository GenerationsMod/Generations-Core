package generations.gg.generations.core.generationscore.common.world.recipe;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.RksMachineBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.function.Function;

public interface RksResult<U extends RksResult<U>> {
    Codec<RksResult<?>> CODEC = RksResultType.CODEC.dispatch(RksResult::type, RksResultType::codec);

    ItemStack getStack();

    RksResultType<U> type();

    default void process(Player player, RksMachineBlockEntity rksMachineBlockEntity, ItemStack stack) {}

    default boolean consumeTimeCapsules() {
        return true;
    }

    boolean isPokemon();

    default void toBuffer(FriendlyByteBuf buffer) {
        buffer.writeResourceLocation(RksResultType.RKS_RESULT.getId(type()));
        type().toBuffer().accept(buffer, (U) this);
    }
}
