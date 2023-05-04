package generations.gg.generations.core.generationscore.world.level.schedule;

import dev.architectury.utils.GameInstance;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.timers.TimerCallback;
import net.minecraft.world.level.timers.TimerQueue;
import org.jetbrains.annotations.NotNull;

public class ScheduledTask implements TimerCallback<MinecraftServer> {

    private ScheduledTask() {}

    @Override
    public void handle(@NotNull MinecraftServer obj, @NotNull TimerQueue<MinecraftServer> manager, long gameTime) {
    }

    public static void schedule(Runnable run, int triggerTime) {
        var scheduler = GameInstance.getServer().getWorldData().overworldData().getScheduledEvents();
        scheduler.schedule("misc", GameInstance.getServer().overworld().getGameTime() + triggerTime, (obj, manager, gameTime) -> run.run());
    }

    public static class Serializer extends TimerCallback.Serializer<net.minecraft.server.MinecraftServer, ScheduledTask> {
        public Serializer() {
            super(GenerationsCore.id("misc"), ScheduledTask.class);
        }

        @Override
        public void serialize(@NotNull CompoundTag tag, @NotNull ScheduledTask callback) {

        }

        @Override
        public @NotNull ScheduledTask deserialize(@NotNull CompoundTag tag) {
            return new ScheduledTask();
        }
    }
}
