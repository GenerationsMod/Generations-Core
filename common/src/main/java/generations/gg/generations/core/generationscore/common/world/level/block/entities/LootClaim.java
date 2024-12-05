package generations.gg.generations.core.generationscore.common.world.level.block.entities;

import java.time.Instant;
import java.util.UUID;

public record LootClaim(UUID uuid, Instant time) {
    public boolean isExpired() {
        return Instant.now().isAfter(time);
    }
}
