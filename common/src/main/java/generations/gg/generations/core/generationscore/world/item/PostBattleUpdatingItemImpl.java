package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PostBattleUpdatingItemImpl extends Item implements PostBattleUpdatingItem {
    private final PokemonProperties speciesId;
    private final String lang;
    private final TriPredicate<PlayerBattleActor, ItemStack, BattleData> predicate;

    public PostBattleUpdatingItemImpl(Properties settings, String speciesId, String lang, TriPredicate<PlayerBattleActor, ItemStack, BattleData> predicate) {
        super(settings);
        this.speciesId = GenerationsUtils.parseProperties(speciesId);
        this.lang = lang;
        this.predicate = predicate;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand usedHand) {
        var stack = player.getItemInHand(usedHand);

        if (!level.isClientSide()) {
            int damage = stack.getDamageValue();

            if (damage >= getMaxDamage()) {
                stack.shrink(1);
                PokemonUtil.spawn(speciesId, level, player.getOnPos());
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

    @Override
    public boolean checkData(PlayerBattleActor player, ItemStack stack, BattleData pixelmonData) {
        return predicate.test(player, stack, pixelmonData);
    }
}
