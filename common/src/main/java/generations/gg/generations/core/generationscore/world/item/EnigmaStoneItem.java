package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EnigmaStoneItem extends ItemWithLangTooltipImpl {
    public EnigmaStoneItem(Item.Properties properties) {
        super(properties);
    }

    private PokemonProperties latias = GenerationsUtils.parseProperties("latias");
    private PokemonProperties latios = GenerationsUtils.parseProperties("latios");

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if(!level.isClientSide) {
            var species = Math.random() > 0.5 ? latias : latios;
            PokemonUtil.spawn(species, level, player.getOnPos());
        }
        return super.use(level, player, usedHand);
    }
}
