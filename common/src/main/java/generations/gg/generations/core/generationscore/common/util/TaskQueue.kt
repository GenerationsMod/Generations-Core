package generations.gg.generations.core.generationscore.common.util

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

class TaskQueue {
    private val taskQueue: BlockingQueue<Runnable> = LinkedBlockingQueue()
    private val workerThread: Thread
    private val running = AtomicBoolean(true)

    init {
        workerThread = Thread { this.processTasks() }
        workerThread.start()
    }

    private fun processTasks() {
        while (true) {
            try {
                if (!taskQueue.isEmpty()) {
                    val task = taskQueue.poll(100, TimeUnit.MILLISECONDS)
                    if (task != null) {
                        try {
                            task.run()
                        } catch (e: Exception) {
                            println("Error!")
                            e.printStackTrace()
                        }
                    }
                }
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                break
            }
        }
    }

    fun addTask(task: Runnable) {
        taskQueue.offer(task)
    }

    fun start() {
        running.set(true)
    }
}

