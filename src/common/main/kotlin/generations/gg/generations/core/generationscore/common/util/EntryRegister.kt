package generations.gg.generations.core.generationscore.common.util

import net.minecraft.core.Holder

abstract class EntryRegister<T> {
    abstract fun <V : T> holder(name: String, supplier: () -> V): Holder<V>
}
