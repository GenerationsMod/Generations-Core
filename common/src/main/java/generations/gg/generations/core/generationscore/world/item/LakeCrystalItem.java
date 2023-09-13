package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.google.common.collect.Streams;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LakeCrystalItem extends EnchantableItem implements PostBattleUpdatingItem {

    private final PokemonProperties pokemonProperties;

    public LakeCrystalItem(Properties properties, String speciesId) {
        super(properties);
        this.pokemonProperties = GenerationsUtils.parseProperties(speciesId);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (!level.isClientSide()) {
            ItemStack stack = player.getItemInHand(usedHand);

            if (!isEnchanted(stack) && stack.getDamageValue() >= getMaxDamage()) {
                PokemonUtil.spawn(pokemonProperties, level, player.getOnPos());
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
}
