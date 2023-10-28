package generations.gg.generations.core.generationscore.world.item.legends;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ElementalPostBattleUpdateItemImpl extends ElementalPostBattleUpdateItem {
    private final List<ElementalType> types;

    public ElementalPostBattleUpdateItemImpl(Properties properties, ElementalType... types) {
        super(properties);
        this.types = List.of(types);
    }

    @Override
    public boolean checkType(PlayerBattleActor actor, ItemStack stack, ElementalType type) {
        return types.contains(type);
    }
}
