package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning

import generations.gg.generations.core.generationscore.util.Adapter

object LocationLogicAdpter: Adapter<LocationLogic>("type") {
    init {
        registerType("block_entity", BlockEntityLocationLogic::class)
        registerType("entity", EntityLocationLogic::class)
    }
}