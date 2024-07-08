package generations.gg.generations.core.generationscore.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskQueue {
    private final BlockingQueue<Runnable> taskQueue;
    private final Thread workerThread;
    private final AtomicBoolean running;

    public TaskQueue() {
        taskQueue = new LinkedBlockingQueue<>();
        running = new AtomicBoolean(true);
        workerThread = new Thread(this::processTasks);
        workerThread.start();
    }

    private void processTasks() {
        while (true) {
            try {
                if (!taskQueue.isEmpty()) {
                    Runnable task = taskQueue.poll(100, TimeUnit.MILLISECONDS);
                    if(task != null) task.run();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void addTask(Runnable task) {
        taskQueue.offer(task);
    }

    public void start() {
        running.set(true);
    }

}

