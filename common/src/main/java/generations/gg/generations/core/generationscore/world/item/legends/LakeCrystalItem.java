package generations.gg.generations.core.generationscore.world.item.legends;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.google.common.collect.Streams;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.item.LangTooltip;
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LakeCrystalItem extends EnchantableItem implements PostBattleUpdatingItem, LangTooltip {

    private final SpeciesKey pokemonProperties;
    private final SpeciesKey speciesKey;

    public
    LakeCrystalItem(Properties properties, SpeciesKey speciesKey) {
        super(properties);
        this.pokemonProperties = speciesKey;
        this.speciesKey = speciesKey;
    }

    @Override
    public int neededEnchantmentLevel(Player player) {
        return 0;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (!level.isClientSide() || !GenerationsCore.CONFIG.caught.capped(player, speciesKey)) {
            ItemStack stack = player.getItemInHand(usedHand);

            if (!isEnchanted(stack) && stack.getDamageValue() >= getMaxDamage()) {
                PokemonUtil.spawn(pokemonProperties.createPokemon(70), level, player.getOnPos(), player.getYRot());
                stack.getOrCreateTag().putBoolean("enchanted", true);
                return InteractionResultHolder.success(stack);
            }
        }

        return super.use(level, player, usedHand);
    }

    @Override
    public boolean checkData(PlayerBattleActor player, ItemStack stack, BattleData pixelmonData) {
        return !isEnchanted(stack) && Streams.stream(pixelmonData.pokemon().getTypes()).anyMatch(type -> type.equals(ElementalTypes.INSTANCE.getPSYCHIC()));
    }

    @Override
    public String tooltipId(ItemStack stack) {
        return this.getDescriptionId() + (isEnchanted(stack) ? ".enchanted" : "") + ".tooltip";
    }
}
