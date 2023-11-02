package generations.gg.generations.core.generationscore.world.item.legends;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.google.common.collect.Streams;
import generations.gg.generations.core.generationscore.world.item.ItemWithLangTooltipImpl;
import generations.gg.generations.core.generationscore.world.item.LangTooltip;
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem;
import net.minecraft.world.item.ItemStack;

public class SingleElmentPostUpdatingItem extends ItemWithLangTooltipImpl implements PostBattleUpdatingItem, LangTooltip {

    private final ElementalType type;

    public SingleElmentPostUpdatingItem(Properties properties, ElementalType type) {
        super(properties);
        this.type = type;
    }

    @Override
    public boolean checkData(PlayerBattleActor player, ItemStack stack, BattleData pixelmonData) {
         return Streams.stream(pixelmonData.pokemon().getTypes()).anyMatch(elementalType -> elementalType.equals(type));
    }
}
