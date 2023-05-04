package generations.gg.generations.core.generationscore.world.item;

import net.minecraft.world.item.Item;

public class PpUpItem extends Item/* implements PixelmonInteractions.PixelmonInteraction*/ {

    public PpUpItem(Properties arg) {
        this(arg, false);
    }

    public PpUpItem(Properties of, boolean max) {
        super(of);
    }

//    @Override
//    public InteractionResult interact(PixelmonEntity pixelmonEntity, Player player, ItemStack itemInHand) {
//        return InteractionResult.PASS;
//    }
}
