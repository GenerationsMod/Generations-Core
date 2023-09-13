package generations.gg.generations.core.generationscore.world.entity.block;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class PokemonUtil {
    public static void spawn(PokemonProperties species, Level level, Vec3 pos, float yaw) {
        var entity = species.createEntity(level);
        entity.setPos(pos);
        entity.setYRot(yaw);
        level.addFreshEntity(entity);
    }

    public static void spawn(PokemonProperties species, Level level, Vec3i pos, float yaw) {
        spawn(species, level, Vec3.atCenterOf(pos), yaw);
    }

    public static void spawn(PokemonProperties species, Level level, Vec3i pos) {
        spawn(species, level, pos, 0);
    }

    public static void spawn(PokemonProperties species, Level level, Vec3 pos) {
        spawn(species, level, pos, 0);
    }
}
