package generations.gg.generations.core.generationscore.common.world.level.block;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.level.block.state.properties.GenerationsBlockSetTypes;
import net.minecraft.world.level.block.state.properties.WoodType;

/**
 * @author JT122406
 * @see WoodType
 * Registers custom Generations wood types
 */
public class GenerationsWoodTypes {
    public static final WoodType ULTRA_DARK = WoodType.register(new WoodType(GenerationsCore.MOD_ID + ":ultra_dark", GenerationsBlockSetTypes.ULTRA_DARK));
    public static final WoodType ULTRA_JUNGLE = WoodType.register(new WoodType(GenerationsCore.MOD_ID + ":ultra_jungle", GenerationsBlockSetTypes.ULTRA_JUNGLE));
    public static final WoodType GHOST = WoodType.register(new WoodType(GenerationsCore.MOD_ID + ":ghost", GenerationsBlockSetTypes.GHOST));
}
