package generations.gg.generations.core.generationscore.world.level.block;

import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

/**
 * @author JT122406
 * @see WoodType
 * Registers custom PokeMod wood types
 */
public class PokeModWoodTypes {
    public static final WoodType ULTRA_DARK = WoodType.register(new WoodType(GenerationsCore.MOD_ID + ":ultra_dark", BlockSetType.OAK));
    public static final WoodType ULTRA_JUNGLE = WoodType.register(new WoodType(GenerationsCore.MOD_ID + ":ultra_jungle", BlockSetType.OAK));
    public static final WoodType GHOST = WoodType.register(new WoodType(GenerationsCore.MOD_ID + ":ghost", BlockSetType.OAK));
}
