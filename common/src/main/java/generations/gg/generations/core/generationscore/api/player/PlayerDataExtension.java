package generations.gg.generations.core.generationscore.api.player;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public abstract class PlayerDataExtension implements com.cobblemon.mod.common.api.storage.player.PlayerDataExtension {
    @NotNull
    @Override
    public JsonObject serialize() {
        var json = new JsonObject();
        json.addProperty(PlayerDataExtension.Companion.getNAME_KEY(), name());
        return json;
    }
}
