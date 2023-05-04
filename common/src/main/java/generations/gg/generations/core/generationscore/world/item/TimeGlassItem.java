package generations.gg.generations.core.generationscore.world.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TimeGlassItem extends Item implements PostBattleUpdatingItem {
    public TimeGlassItem(Properties arg) {
        super(arg);
    }

//    @Override
//    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand usedHand) {
//        ItemStack stack = player.getItemInHand(usedHand);
//        if(!level.isClientSide()) {
//            int damage = stack.getDamageValue();
//            if (damage >= stack.getMaxDamage()) {
//                if (level.getBiome(player.getOnPos()).is(Biomes.FLOWER_FOREST)) {
//                    level.addFreshEntity(new PixelmonEntity(level, BuiltinPixelmonSpecies.CELEBI.location(), player.getOnPos(), (int) player.getYRot()));
//                    player.getItemInHand(usedHand).shrink(1);
//                } else player.displayClientMessage(Component.translatable("pokemod.timeglass.wrongbiome"), true);
//            } else {
//                player.displayClientMessage(Component.translatable("pokemod.timeglass.amount", damage), true);
//            }
//
//            return InteractionResultHolder.success(stack);
//        }
//
//
//        return super.use(level, player, usedHand);
//    }

    @Override
    public void onBattleFinish(ServerPlayer player, ItemStack stack/*, Battle<BattleController> battle*/) {
        // return player.getLevel().getBiome(player.getOnPos()).is(Biomes.FLOWER_FOREST) && (pixelmonData.data().getFirst().getElements().contains(ElementType.FAIRY) || pixelmonData.data().getFirst().getElements().contains(ElementType.PSYCHIC) || pixelmonData.data().getFirst().getElements().contains(ElementType.GRASS));
    }
}
