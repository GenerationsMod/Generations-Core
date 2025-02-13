package generations.gg.generations.core.generationscore.common.util.extensions

import net.minecraft.network.chat.Component

// Overloaded `+` operator for adding text components
operator fun MutableList<Component>.plusAssign(text: String) {
    this += Component.literal(text)
}

// Infix function for adding a translatable component
infix fun MutableList<Component>.translation(key: String) {
    this += Component.translatable(key)
}

// Function for adding styled text
fun MutableList<Component>.text(content: String, block: Component.() -> Component) {
    this += Component.literal(content).block()
}

fun MutableList<Component>.addComponent(component: Component) {
    this.add(component)
}

fun MutableList<Component>.addText(text: String) {
    this.add(Component.literal(text))
}
