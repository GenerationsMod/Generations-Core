package generations.gg.generations.core.generationscore.world.entity.block;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class PokemonUtil {
    public static void spawn(String species, Level level, Vec3 pos) {
        var properties = new PokemonProperties();
        properties.setSpecies(species);
        var entity = properties.createEntity(level);
        entity.setPos(pos);
        level.addFreshEntity(entity);
    }

    public static void spawn(String species, Level level, Vec3i pos) {
        spawn(species, level, Vec3.atCenterOf(pos));
    }
}
