package generations.gg.generations.core.generationscore.world.recipe;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.item.PokemonItem;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.level.block.RksMachineBlock;
import generations.gg.generations.core.generationscore.world.level.block.entities.RksMachineBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector4f;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public interface RksResult<U extends RksResult<U>> {
    Codec<RksResult<?>> CODEC = ResourceLocation.CODEC.dispatch(rksResult -> RksResultType.REGISTRAR.getId(rksResult.type()), location -> RksResultType.REGISTRAR.get(location).codec());

    static RksResult<?> fromBuffer(FriendlyByteBuf buffer) {
        return RksResultType.fromBuffer(buffer).fromBuffer().apply(buffer);
    }

    static <T extends RksResult<T>> void toBuffer(FriendlyByteBuf buffer, T result) {
        result.type().toBuffer().accept(buffer, result);
    }

    ItemStack getStack();

    RksResultType<U> type();

    default void process(RksMachineBlockEntity rksMachineBlockEntity, ItemStack stack) {}

    default boolean consumeTimeCapsules() {
        return true;
    }

    record ItemResult(ItemStack item) implements RksResult<ItemResult> {
        public static final Codec<ItemResult> CODEC = Codec.either(BuiltInRegistries.ITEM.byNameCodec(), ItemStack.CODEC).xmap(a -> a.map(Item::getDefaultInstance, b -> b), a -> a.getCount() > 1 || a.hasTag() ? Either.right(a) : Either.left(a.getItem())).xmap(ItemResult::new, ItemResult::item);

        public static final Function<FriendlyByteBuf, ItemResult> FROM_BUFFER = buffer -> new ItemResult(buffer.readItem());

        public static final BiConsumer<FriendlyByteBuf, ItemResult> TO_BUFFER = (buffer, result) -> buffer.writeItem(result.item());

        @Override
        public ItemStack getStack() {
            return item;
        }

        @Override
        public RksResultType<ItemResult> type() {
            return RksResultType.ITEM.get();
        }
    }
    record PokemonResult(ResourceLocation species, Set<String> aspects, int level) implements RksResult<PokemonResult> {

        public static final Codec<PokemonResult> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ResourceLocation.CODEC.fieldOf("species").forGetter(PokemonResult::species),
                Codec.STRING.listOf().<Set<String>>xmap(HashSet::new, ArrayList::new).optionalFieldOf("aspects", new HashSet<>()).forGetter(PokemonResult::aspects),
                Codec.INT.optionalFieldOf("level", 1).forGetter(PokemonResult::level)).apply(instance, PokemonResult::new));
        public static final Function<FriendlyByteBuf, PokemonResult> FROM_BUFFER = buffer -> {
            var species = buffer.readResourceLocation();
            var aspects = buffer.readCollection(HashSet::new, FriendlyByteBuf::readUtf);
            var level = buffer.readVarInt();
            return new PokemonResult(species, aspects, level);
        };

        public static final BiConsumer<FriendlyByteBuf, PokemonResult> TO_BUFFER = (buffer, result) -> {
            buffer.writeResourceLocation(result.species);
            buffer.writeCollection(result.aspects, FriendlyByteBuf::writeUtf);
            buffer.writeVarInt(result.level);
        };

        @Override
        public ItemStack getStack() {
            return PokemonItem.from(PokemonSpecies.INSTANCE.getByIdentifier(species), aspects, 1, new Vector4f(1,1,1,1));
        }

        @Override
        public RksResultType<PokemonResult> type() {
            return RksResultType.POKEMON.get();
        }

        @Override
        public void process(RksMachineBlockEntity rksMachineBlockEntity, ItemStack stack) {
            var pos = rksMachineBlockEntity.getBlockPos();
            var dir = rksMachineBlockEntity.getBlockState().getValue(RksMachineBlock.FACING);

            var properties = new PokemonProperties();
            properties.setSpecies(species().toString());
            properties.setAspects(aspects());
            properties.setLevel(level());
            PokemonUtil.spawn(properties, rksMachineBlockEntity.getLevel(), pos.above(2), dir.toYRot());
            stack.setCount(0);
        }
    }
}
