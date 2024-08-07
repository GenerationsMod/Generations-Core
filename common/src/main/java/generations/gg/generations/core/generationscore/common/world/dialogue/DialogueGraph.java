package generations.gg.generations.core.generationscore.common.world.dialogue;

import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.AbstractNode;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.AbstractNodeTypes;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.AbstractNode;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.AbstractNodeTypes;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.AbstractNode;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.AbstractNodeTypes;

public record DialogueGraph(AbstractNode root) {
    public DialogueGraph(JsonObject root) {
        this(AbstractNodeTypes.fromJson(root));
    }

    public JsonObject toJson() {
        var json = new JsonObject();
        json.add("root", AbstractNodeTypes.toJson(root));
        return json;
    }
}
