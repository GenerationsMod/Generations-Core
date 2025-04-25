package generations.gg.generations.core.generationscore.common.world.item.components

import net.minecraft.server.network.Filterable

data class SealableMailContent(val title: Filterable<String>, val content: Filterable<String>) {
    companion object {

    }
}
