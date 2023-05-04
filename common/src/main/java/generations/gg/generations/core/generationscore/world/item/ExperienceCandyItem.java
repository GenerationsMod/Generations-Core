package generations.gg.generations.core.generationscore.world.item;

import net.minecraft.world.item.Item;

public class ExperienceCandyItem extends Item /*implements PixelmonInteractions.PixelmonInteraction*/ {
    private final int experience;

    public ExperienceCandyItem(Properties arg, int experience) {
        super(arg);
        this.experience = experience;
    }

//    @Override
//    public InteractionResult interact(PixelmonEntity pixelmonEntity, Player player, ItemStack itemInHand) {
//        var data = pixelmonEntity.getPixelmonData();
//        var bool = data.levelContainer.awardEXP(experience, level -> player.displayClientMessage(Component.literal("%s has leveled up to level %s!".formatted(pixelmonEntity.getDisplayName(), level)), false));
//        pixelmonEntity.replaceData(data);
//        return bool ? InteractionResult.SUCCESS : InteractionResult.FAIL;
//    }
}
