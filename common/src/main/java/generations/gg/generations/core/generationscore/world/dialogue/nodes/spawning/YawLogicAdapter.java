package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning;

import generations.gg.generations.core.generationscore.util.Adapter;

public class YawLogicAdapter extends Adapter<YawLogic> {

    public YawLogicAdapter() {
        super("type");
        registerType("block_entity", BlockEntityYawLogic.class);
        registerType("entity", EntityYawLogic.class);
    }
}