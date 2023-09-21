package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.google.common.collect.Streams;
import net.minecraft.world.item.ItemStack;

public class WeatherTrioItem extends ItemWithLangTooltipImpl implements PostBattleUpdatingItem, LangTooltip {

    private final ElementalType type;

    public WeatherTrioItem(Properties properties, ElementalType type) {
        super(properties);
        this.type = type;
    }

    @Override
    public boolean checkData(PlayerBattleActor player, ItemStack stack, BattleData pixelmonData) {
         return Streams.stream(pixelmonData.pokemon().getTypes()).anyMatch(elementalType -> elementalType.equals(type));
    }
}
