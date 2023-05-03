package com.pokemod.pokemod.world.item;

import com.pixelmongenerations.mimikyu.Battle;
import com.pokemod.pokemod.PokeMod;
import com.pokemod.pokemod.api.battle.BattleController;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TaoTrioStoneItem extends Item implements PostBattleUpdatingItem {
    private final ResourceLocation species;

    public TaoTrioStoneItem(Properties arg, String species) {
        super(arg);
        this.species = PokeMod.id(species);
    }

    public ResourceLocation getSpecies() {
        return species;
    }

    @Override
    public void onBattleFinish(ServerPlayer player, ItemStack stack, Battle<BattleController> battle) {
        // return pixelmonData.data().getFirst().getPokemonForm().types().contains(ElementType.DRAGON);
    }
}
