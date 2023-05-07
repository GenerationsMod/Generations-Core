package generations.gg.generations.core.generationscore.world.item.tools;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class GenerationsHammerItem extends DiggerItem {
    public final Set<ToolEffect> toolEffects = new HashSet<>();

    public GenerationsHammerItem(Tier tier, float attackDamageBonus, float attackSpeed, Properties properties) {
        super((int) attackDamageBonus, attackSpeed, tier, BlockTags.ANVIL, properties);
    }

    public GenerationsHammerItem addToolEffect(ToolEffect toolEffect) {
        this.toolEffects.add(toolEffect);
        return this;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player player, @NotNull InteractionHand usedHand) {
        boolean result = false;
        for (ToolEffect toolEffect : toolEffects)
            result = result || toolEffect.use(world, player, usedHand);

        return result ?
                InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), world.isClientSide()) :
                super.use(world, player, usedHand);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        boolean result = false;
        for (ToolEffect toolEffect : toolEffects)
            result = result || toolEffect.useOn(context);

        return result ?
                InteractionResult.sidedSuccess(context.getLevel().isClientSide()) :
                super.useOn(context);
    }
}
