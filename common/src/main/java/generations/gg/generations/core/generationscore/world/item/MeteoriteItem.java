package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.config.LegendKeys;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MeteoriteItem extends EnchantableItem implements LangTooltip {
    private final Supplier<PokemonProperties> properties = Suppliers.memoize(() -> GenerationsUtils.parseProperties("deoxys form=normal level=70"));

    public MeteoriteItem(Properties arg) {
        super(arg);
    }

    @Override
    public int neededEnchantmentLevel(Player player) {
        var caught = GenerationsCore.CONFIG.caught;
        if(caught.capped(player, LegendKeys.DEOXYS)) return super.neededEnchantmentLevel(player);
        else return 0;
    }
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (!level.isClientSide()) {
            ItemStack stack = player.getItemInHand(usedHand);

            if (EnchantableItem.isEnchanted(stack)) {
                PokemonUtil.spawn(properties.get(), level, player.getOnPos());
                stack.shrink(1);

                return InteractionResultHolder.success(stack);
            }
        }

        return super.use(level, player, usedHand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        LangTooltip.super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }
}
