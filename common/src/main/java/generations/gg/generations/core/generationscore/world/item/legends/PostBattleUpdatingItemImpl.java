package generations.gg.generations.core.generationscore.world.item.legends;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem;
import generations.gg.generations.core.generationscore.world.item.TriPredicate;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PostBattleUpdatingItemImpl extends Item implements PostBattleUpdatingItem {
    private final SpeciesKey speciesId;
    private final String lang;
    private final TriPredicate<PlayerBattleActor, ItemStack, BattleData> predicate;

    public PostBattleUpdatingItemImpl(Properties settings, SpeciesKey speciesId, String lang, TriPredicate<PlayerBattleActor, ItemStack, BattleData> predicate) {
        super(settings);
        this.speciesId = speciesId;
        this.lang = lang;
        this.predicate = predicate;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand usedHand) {
        var stack = player.getItemInHand(usedHand);

        if (!level.isClientSide() && !GenerationsCore.CONFIG.caught.capped(player, speciesId)) {
            int damage = stack.getDamageValue();

            if (damage >= getMaxDamage()) {
                stack.shrink(1);
                if(spawnPokemon()) PokemonUtil.spawn(speciesId.createPokemon(spawnedLevel()), level, player.getOnPos(), player.getYRot());
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

    protected boolean spawnPokemon() {
        return true;
    }

    protected int spawnedLevel() {
        return 1;
    }

    @Override
    public boolean checkData(PlayerBattleActor player, ItemStack stack, BattleData pixelmonData) {
        return predicate.test(player, stack, pixelmonData);
    }
}
