package generations.gg.generations.core.generationscore.world.item.legends;

import com.cobblemon.mod.common.api.types.ElementalType;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ElementalPostBattleUpdateItemImplImpl extends ElementalPostBattleUpdateItemImpl {
    public static final String DEFAULT_LANG_KEY = "generations_core.orb.amountfull";

    private final String lang;
    private final SpeciesKey key;

    public ElementalPostBattleUpdateItemImplImpl(Properties properties, SpeciesKey key, ElementalType... types) {
        this(properties, DEFAULT_LANG_KEY, key, types);
    }


    public ElementalPostBattleUpdateItemImplImpl(Properties properties, String lang, SpeciesKey key, ElementalType... types) {
        super(properties, types);
        this.lang = lang;
        this.key = key;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand usedHand) {
        var stack = player.getItemInHand(usedHand);

        if (!level.isClientSide()) {
            int damage = stack.getDamageValue();

            if (damage >= getMaxDamage()) {
                stack.shrink(1);
                PokemonUtil.spawn(key.createProperties(70), level, player.getOnPos());
                postSpawn(level, player, usedHand);
            } else {
                player.displayClientMessage(Component.translatable(lang, getMaxDamage() - damage), true);
            }

            return InteractionResultHolder.success(stack);
        }

        return InteractionResultHolder.pass(stack);
    }

    protected void postSpawn(Level level, Player player, InteractionHand usedHand) {
    }
}
