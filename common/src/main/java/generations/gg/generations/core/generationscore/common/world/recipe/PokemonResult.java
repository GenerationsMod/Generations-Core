package generations.gg.generations.core.generationscore.common.world.recipe;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.CobblemonEntities;
import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.api.storage.NoPokemonStoreException;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.item.PokemonItem;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.common.world.level.block.RksMachineBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.RksMachineBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public record PokemonResult(ResourceLocation species, Set<String> aspects, int level, boolean spawnInWorld,
                            boolean usePokemonInCapsule) implements RksResult<PokemonResult> {

    public static final Codec<PokemonResult> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("species").forGetter(PokemonResult::species),
            Codec.STRING.listOf().<Set<String>>xmap(HashSet::new, ArrayList::new).optionalFieldOf("aspects", new HashSet<>()).forGetter(PokemonResult::aspects),
            Codec.INT.optionalFieldOf("level", 1).forGetter(PokemonResult::level),
            Codec.BOOL.optionalFieldOf("spawnInWorld", true).forGetter(PokemonResult::spawnInWorld),
            Codec.BOOL.optionalFieldOf("usePokemonInCapsule", false).forGetter(PokemonResult::usePokemonInCapsule))
            .apply(instance, PokemonResult::new));

    public static final Function<FriendlyByteBuf, PokemonResult> FROM_BUFFER = buffer -> {
        var species = buffer.readResourceLocation();
        var aspects = buffer.readCollection(HashSet::new, FriendlyByteBuf::readUtf);
        var level = buffer.readVarInt();
        var spawnInWorld = buffer.readBoolean();
        var usePokemonInCapsule = buffer.readBoolean();
        return new PokemonResult(species, aspects, level, spawnInWorld, usePokemonInCapsule);
    };

    public static final BiConsumer<FriendlyByteBuf, PokemonResult> TO_BUFFER = (buffer, result) -> {
        buffer.writeResourceLocation(result.species);
        buffer.writeCollection(result.aspects, FriendlyByteBuf::writeUtf);
        buffer.writeVarInt(result.level);
        buffer.writeBoolean(result.spawnInWorld());
        buffer.writeBoolean(result.usePokemonInCapsule());
    };

    @Override
    public ItemStack getStack() {
        return PokemonItem.from(PokemonSpecies.INSTANCE.getByIdentifier(species), aspects, 1, new Vector4f(1, 1, 1, 1));
    }

    @Override
    public RksResultType<PokemonResult> type() {
        return RksResultType.POKEMON.get();
    }

    @Override
    public void process(Player player, RksMachineBlockEntity rksMachineBlockEntity, ItemStack stack) {
        Pokemon pokemon = null;

        if (usePokemonInCapsule() && rksMachineBlockEntity.pokemon.isPresent()) {
            var properties = new PokemonProperties();
            properties.setAspects(aspects);
            properties.setSpecies(species.toString());

            pokemon = rksMachineBlockEntity.pokemon.get();
            properties.apply(pokemon);
        } else {
            var properties = new PokemonProperties();
            properties.setAspects(aspects);
            properties.setSpecies(species.toString());
            properties.setLevel(level);
            pokemon = properties.create();
        }

        if (spawnInWorld()) {
            var pos = rksMachineBlockEntity.getBlockPos();
            var dir = rksMachineBlockEntity.getBlockState().getValue(RksMachineBlock.FACING);
            PokemonUtil.spawn(new PokemonEntity(rksMachineBlockEntity.getLevel(), pokemon, CobblemonEntities.POKEMON), rksMachineBlockEntity.getLevel(), Vec3.atCenterOf(pos.above(2)), dir.toYRot());
        } else {
            try {
                Cobblemon.INSTANCE.getStorage().getParty(player.getUUID()).add(pokemon);
            } catch (NoPokemonStoreException e) {
                e.printStackTrace();
            }
        }

        stack.setCount(0);
    }

    @Override
    public boolean isPokemon() {
        return true;
    }
}
