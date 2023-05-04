package generations.gg.generations.core.generationscore.world.item;

import net.minecraft.world.item.Item;

public class EvolutionItem extends Item /*implements PixelmonInteractions.PixelmonInteraction*/ {
    public EvolutionItem(Properties arg) {
        super(arg);
    }

//    @Override
//    public InteractionResult interact(PixelmonEntity pixelmonEntity, Player player, ItemStack itemInHand) {
//        if(pixelmonEntity.level.isClientSide()) return InteractionResult.PASS;
//
//        PixelmonData data = pixelmonEntity.getPixelmonData();
//        Level level = pixelmonEntity.level;
//        Vec3 pos = pixelmonEntity.getEyePosition();
//
//        List<Evolution> evolutionList = data.getPokemonForm().evolutions();
//
//        for (Evolution evolution : evolutionList) {
//            EvolutionInfo from = evolution.from;
//            EvolutionInfo to = evolution.to;
//
//            if(!from.isSimilar(data)) continue;
//
//            if(evolution instanceof InteractEvolution interactEvolution) {
//                if(itemInHand.is(a -> a.is(interactEvolution.item()))) {
//                    if(evolution.conditions.stream().allMatch(a -> a.test(level, pos, data, (ServerPlayer) player))) {
//                        player.sendSystemMessage(Component.literal("Oh?"));
//                        var name = data.displayName();
//
//                        player.sendSystemMessage(Component.literal(name + " is evolving!"));
//                        data.setSpecies(to.getSpecies());
//                        data.setForm(to.getFormId());
//                        data.setSkin(to.getSkinId());
//                        pixelmonEntity.replaceData(data);
//                        player.sendSystemMessage(Component.literal(name + " has evolved into " + to.displayName()));
//                        return InteractionResult.SUCCESS;
//                    }
//                }
//            }
//        }
//
//        return InteractionResult.PASS;
//    }
}
