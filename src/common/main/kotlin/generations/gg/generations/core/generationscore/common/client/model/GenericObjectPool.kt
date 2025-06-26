package generations.gg.generations.core.generationscore.common.client.model

import java.util.*
import java.util.function.Supplier

class GenericObjectPool<T>(objectFactory: Supplier<T>, maxPoolSize: Int) {
    private val availableObjects: Queue<T> = ArrayDeque(maxPoolSize)
    private val usedObjects: Queue<T> = ArrayDeque(maxPoolSize)

    init {
        for (i in 0 until maxPoolSize) availableObjects.add(objectFactory.get())
    }

    fun acquire(): T? {
        if (availableObjects.isEmpty()) return null
        else {
            val obj = availableObjects.poll()
            usedObjects.add(obj)
            return obj
        }
    }

    fun freeAll() {
        availableObjects.addAll(usedObjects)
        usedObjects.clear()
    }
}