package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.google.common.collect.Streams;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TaoTrioStoneItem extends Item implements PostBattleUpdatingItem {
    private final String species;

    public TaoTrioStoneItem(Properties arg, String species) {
        super(arg);
        this.species = species;
    }

    public String getSpecies() {
        return species;
    }

    @Override
    public boolean checkData(PlayerBattleActor player, ItemStack stack, BattleData pixelmonData) {
        return Streams.stream(pixelmonData.pokemon().getTypes()).anyMatch(ElementalTypes.INSTANCE.getDRAGON()::equals);
    }
}
