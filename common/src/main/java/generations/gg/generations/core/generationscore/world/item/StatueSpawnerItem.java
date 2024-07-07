package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.api.pokemon.PokemonPropertyExtractor;
import com.cobblemon.mod.common.pokemon.Pokemon;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import generations.gg.generations.core.generationscore.network.GenerationsNetwork;
import generations.gg.generations.core.generationscore.network.packets.statue.S2CUpdateStatueInfoPacket;
import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StatueSpawnerItem extends Item {
    private final SpeciesKey key;
    private Pokemon pokemon;

    public StatueSpawnerItem(Properties properties, SpeciesKey key) {
        super(properties);
        this.key = key;
    }

    public StatueSpawnerItem(Properties properties) {
        this(properties, null);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null && context.getPlayer() instanceof ServerPlayer serverPlayer) {
            BlockPos pos = context.getClickedPos();
            StatueEntity statueEntity = new StatueEntity(GenerationsEntities.STATUE_ENTITY.get(), serverPlayer.level());

            var data = statueEntity.getStatueData();
            data.setOrientation(context.getHorizontalDirection().toYRot());

            if(key != null) {
                data.setProperties(key.createPokemon(70).createPokemonProperties(PokemonPropertyExtractor.SPECIES, PokemonPropertyExtractor.FORM, PokemonPropertyExtractor.ASPECTS));
                data.setSacredAshInteractable(true);
                data.setMaterial("concrete");
                data.setIsStatic(true);
                serverPlayer.setItemInHand(context.getHand(), ItemStack.EMPTY);
            }


            statueEntity.setPos(Vec3.upFromBottomCenterOf(pos, 1));
            serverPlayer.level().addFreshEntity(statueEntity);

            statueEntity.setStatueInfo(data);
            GenerationsNetwork.INSTANCE.sendToAllTracking(new S2CUpdateStatueInfoPacket(statueEntity.getId(), data), serverPlayer);
        }
        return InteractionResult.PASS;
    }

    @Nullable
    public Pokemon getPokemon() {
        return pokemon == null ? pokemon = key != null ? key.createPokemon(70) : null : pokemon;
    }
}
