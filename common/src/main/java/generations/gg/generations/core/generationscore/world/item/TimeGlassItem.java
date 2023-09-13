package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.google.common.collect.Streams;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;

public class TimeGlassItem extends Item implements PostBattleUpdatingItem {
    private static final PokemonProperties properties = GenerationsUtils.parseProperties("celebi");

    public TimeGlassItem(Properties arg) {
        super(arg);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if(!level.isClientSide()) {
            int damage = stack.getDamageValue();
            if (damage >= stack.getMaxDamage()) {
                if (level.getBiome(player.getOnPos()).is(Biomes.FLOWER_FOREST)) {
                    PokemonUtil.spawn(properties, level, player.getOnPos());
                    player.getItemInHand(usedHand).shrink(1);
                } else player.displayClientMessage(Component.translatable("pokemod.timeglass.wrongbiome"), true);
            } else {
                player.displayClientMessage(Component.translatable("pokemod.timeglass.amount", damage), true);
            }

            return InteractionResultHolder.success(stack);
        }


        return super.use(level, player, usedHand);
    }

    @Override
    public boolean checkData(PlayerBattleActor player, ItemStack stack, BattleData pixelmonData) {
        return player.getEntity().level().getBiome(player.getEntity().getOnPos()).is(Biomes.FLOWER_FOREST) && Streams.stream(pixelmonData.pokemon().getSpecies().getTypes()).anyMatch(type -> type.equals(ElementalTypes.INSTANCE.getPSYCHIC()) || type.equals(ElementalTypes.INSTANCE.getGRASS()));
    }
}
