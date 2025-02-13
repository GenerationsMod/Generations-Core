package generations.gg.generations.core.generationscore.common.util.extensions

import com.cobblemon.mod.common.api.text.text
import net.minecraft.network.chat.Component

object ComponentListDSL {
    private lateinit var list: MutableList<Component>

    fun update(list: MutableList<Component>, block: ComponentListDSL.() -> Unit) {
        this.list = list
        block.invoke(this)
    }

    operator fun MutableList<Component>.plusAssign(text: String) {
        list += Component.literal(text)
    }

    operator fun String.unaryPlus() {
        list.add(this.text())
    }

}

fun MutableList<Component>.dsl(block: ComponentListDSL.() -> Unit): MutableList<Component> {
    ComponentListDSL.update(this, block)
    return this
}