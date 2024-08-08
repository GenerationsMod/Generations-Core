package generations.gg.generations.core.generationscore.common.client.model;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Supplier;

public class GenericObjectPool<T> {
    private final Queue<T> availableObjects;
    private final Queue<T> usedObjects;

    public GenericObjectPool(Supplier<T> objectFactory, int maxPoolSize) {
        this.availableObjects = new ArrayDeque<>(maxPoolSize);
        this.usedObjects = new ArrayDeque<>(maxPoolSize);
        for (int i = 0; i < maxPoolSize; i++) availableObjects.add(objectFactory.get());
    }

    public T acquire() {
        if (availableObjects.isEmpty()) return null;
        else {
            T obj = availableObjects.poll();
            usedObjects.add(obj);
            return obj;
        }
    }

    public void freeAll() {
        availableObjects.addAll(usedObjects);
        usedObjects.clear();
    }
}