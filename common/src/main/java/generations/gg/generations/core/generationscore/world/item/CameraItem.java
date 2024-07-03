package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.item.PokemonItem;
import generations.gg.generations.core.generationscore.api.events.general.CameraEvents;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class CameraItem extends Item {
    public CameraItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if(!level.isClientSide() && usedHand == InteractionHand.MAIN_HAND && !player.getCooldowns().isOnCooldown(this) && player.getInventory().hasAnyMatching(stack -> stack.is(GenerationsItems.FILM.get()))) {
            var hit = GenerationsUtils.raycast(player, 30, 1.0f, entity -> entity instanceof PokemonEntity);

            if(hit instanceof EntityHitResult result && result.getEntity() instanceof PokemonEntity pokemon) {
                var photo = PokemonItem.from(pokemon.getPokemon());

                var changed = CameraEvents.MODIFY_PHOTO.invoker().modify((ServerPlayer) player, (ServerLevel) level, photo);

                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LEVER_CLICK, SoundSource.MASTER, 1.0f, 1.0f);
                ContainerHelper.clearOrCountMatchingItems(player.getInventory(), stack -> stack.is(GenerationsItems.FILM.get()), 1, false);
                player.addItem(changed != null ? changed : photo);
                player.getCooldowns().addCooldown(this, 20);
            }
        }

        return super.use(level, player, usedHand);
    }
}
