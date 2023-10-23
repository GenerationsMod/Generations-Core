package generations.gg.generations.core.generationscore.world.item.legends;

import generations.gg.generations.core.generationscore.world.item.ItemWithLangTooltipImpl;

public class SacredAshItem extends ItemWithLangTooltipImpl/* implements PostBattleUpdatingItem*/ {

    public SacredAshItem(Properties properties) {
        super(properties);
    }

//    public static boolean isFullyCharged(ItemStack stack) {
//        return stack.getDamageValue() >= stack.getMaxDamage();
//    }
//
//    @Override
//    public boolean isFoil(@NotNull ItemStack stack) {
//        return isFullyCharged(stack);
//    }

//    @Override
//    public boolean isHeld() {
//        return true;
//    }
//
//    @Override
//    public boolean checkData(PlayerBattleActor player, ItemStack stack, BattleData pixelmonData) {
//         return true; // pixelmonData.move().equals(("sacred_fire")); //TODO: This will not work as is due to lack of proper event. Need to implment on cobblemon side or rethink.
//    }
}
