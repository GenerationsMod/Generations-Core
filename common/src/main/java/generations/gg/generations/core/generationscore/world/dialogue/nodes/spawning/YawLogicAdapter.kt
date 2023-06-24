package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning

import generations.gg.generations.core.generationscore.util.Adapter

object YawLogicAdapter: Adapter<YawLogic>("type") {
    init {
        registerType("block_entity", BlockEntityYawLogic::class)
        registerType("entity", EntityYawLogic::class)
    }
}