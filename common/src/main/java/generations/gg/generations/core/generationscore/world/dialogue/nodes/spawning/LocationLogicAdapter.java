package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning;

import generations.gg.generations.core.generationscore.util.Adapter;

public class LocationLogicAdapter extends Adapter<LocationLogic> {
    public static final LocationLogicAdapter INSTANCE = new LocationLogicAdapter();
    public LocationLogicAdapter() {
        super("type");
        registerType("block_entity", BlockEntityLocationLogic.class);
        registerType("entity", EntityLocationLogic.class);

    }
}