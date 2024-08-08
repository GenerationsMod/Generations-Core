package generations.gg.generations.core.generationscore.common.world.item.legends;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.google.common.collect.Streams;
import generations.gg.generations.core.generationscore.common.config.LegendKeys;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.config.LegendKeys;
import generations.gg.generations.core.generationscore.common.config.LegendKeys;
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.common.world.item.ItemWithLangTooltipImpl;
import generations.gg.generations.core.generationscore.common.world.item.LangTooltip;
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;

public class TimeGlassItem extends ItemWithLangTooltipImpl implements PostBattleUpdatingItem, LangTooltip {

    public TimeGlassItem(Properties arg) {
        super(arg);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if(!level.isClientSide() && !GenerationsCore.CONFIG.caught.capped(player, LegendKeys.CELEBI)) {
            int damage = stack.getDamageValue();
            if (damage >= stack.getMaxDamage()) {
                if (level.getBiome(player.getOnPos()).is(Biomes.FLOWER_FOREST)) {
                    PokemonUtil.spawn(LegendKeys.CELEBI.createPokemon(70), level, player.getOnPos(), player.getYRot());
                    player.getItemInHand(usedHand).shrink(1);
                } else player.displayClientMessage(Component.translatable("generations_core.timeglass.wrongbiome"), true);
            } else {
                player.displayClientMessage(Component.translatable("generations_core.timeglass.amount", damage), true);
            }

            return InteractionResultHolder.success(stack);
        }


        return super.use(level, player, usedHand);
    }

    @Override
    public boolean checkData(PlayerBattleActor player, ItemStack stack, BattleData pixelmonData) {
        return Streams.stream(pixelmonData.pokemon().getSpecies().getTypes()).anyMatch(type -> type.equals(ElementalTypes.INSTANCE.getPSYCHIC()) || type.equals(ElementalTypes.INSTANCE.getGRASS()));
    }
}
