package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class MeteoriteItem extends EnchantableItem {
    private static final PokemonProperties properties = GenerationsUtils.parseProperties("deoxys attack");

    public MeteoriteItem(Properties arg) {
        super(arg);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (!level.isClientSide()) {
            ItemStack stack = player.getItemInHand(usedHand);

            if (EnchantableItem.isEnchanted(stack)) {
                PokemonUtil.spawn(properties, level, player.getOnPos());
                stack.shrink(1);

                return InteractionResultHolder.success(stack);
            }
        }

        return super.use(level, player, usedHand);
    }
}
