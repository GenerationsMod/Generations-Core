package generations.gg.generations.core.generationscore.common.util

import net.minecraft.core.Holder

abstract class EntryRegister<T: Any> {
    abstract fun holder(name: String, supplier: () -> T): Holder<T>
}
