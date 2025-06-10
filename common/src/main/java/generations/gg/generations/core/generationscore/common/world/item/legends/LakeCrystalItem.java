package generations.gg.generations.core.generationscore.common.world.item.legends;

import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.google.common.collect.Streams;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.common.world.item.LangTooltip;
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem;
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents;
import net.minecraft.server.level.ServerPlayer;
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
    public int neededEnchantmentLevel(ServerPlayer player) {
        return 0;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (!level.isClientSide() && GenerationsCore.CONFIG.caught.capped((ServerPlayer) player, speciesKey)) {
            ItemStack stack = player.getItemInHand(usedHand);

            if (!isEnchanted(stack) && stack.getDamageValue() >= stack.getMaxDamage()) {
                PokemonUtil.spawn(pokemonProperties.createPokemon(70), level, player.getOnPos(), player.getYRot());
                stack.set(GenerationsDataComponents.INSTANCE.getENCHANTED().get(), true);
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
