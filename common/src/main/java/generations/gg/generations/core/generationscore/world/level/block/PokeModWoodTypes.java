package generations.gg.generations.core.generationscore.world.level.block;

import com.pokemod.pokemod.PokeMod;
import net.minecraft.world.level.block.state.properties.WoodType;

/**
 * @author JT122406
 * @see WoodType
 * Registers custom PokeMod wood types
 */
public class PokeModWoodTypes {
    public static final WoodType ULTRA_DARK = WoodType.register(WoodType.create(PokeMod.MOD_ID + ":ultra_dark"));
    public static final WoodType ULTRA_JUNGLE = WoodType.register(WoodType.create(PokeMod.MOD_ID + ":ultra_jungle"));
    public static final WoodType GHOST = WoodType.register(WoodType.create(PokeMod.MOD_ID + ":ghost"));
}
