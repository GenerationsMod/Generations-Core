package generations.gg.generations.core.generationscore.common.world.level.schedule

import com.cobblemon.mod.common.util.server
import net.minecraft.server.MinecraftServer
import net.minecraft.world.level.timers.TimerCallback
import net.minecraft.world.level.timers.TimerQueue

class ScheduledTask private constructor() : TimerCallback<MinecraftServer> {
    override fun handle(obj: MinecraftServer, manager: TimerQueue<MinecraftServer>, gameTime: Long) {
    }

    companion object {
        fun schedule(run: Runnable, triggerTime: Int) {
            val server = server() ?: return
            server.worldData.overworldData().scheduledEvents.schedule(
                "misc", server.overworld().gameTime + triggerTime
            ) { _, _, _ -> run.run() }
        }
    }
}
