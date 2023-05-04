package generations.gg.generations.core.generationscore.world.item;

import generations.gg.generations.core.generationscore.GenerationsCore;

public class TechnicalMachineItem extends MoveTeachingItem/* implements PixelmonInteractions.PixelmonInteraction*/ {
    public TechnicalMachineItem(String move, Properties properties) {
        super(GenerationsCore.id(move), properties);
    }

//    @Override
//    public boolean isConsumed() {
//        return false;
//    }
}
