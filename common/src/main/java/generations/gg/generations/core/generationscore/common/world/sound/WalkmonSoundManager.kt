package generations.gg.generations.core.generationscore.common.world.sound

import com.cobblemon.mod.common.util.MovingSoundInstance
import net.minecraft.client.Minecraft
import net.minecraft.core.Holder
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundSource
import java.util.*

object WalkmonSoundManager {
    var currentTick = 0;

    private data class WalkmonSound(
        val instance: MovingSoundInstance,
        val endTick: Int,
        val trackId: Any?
    )

    private val activeSounds: MutableMap<UUID, WalkmonSound> = mutableMapOf()

    /** Must be called client-side only */
    fun tick() {
        val soundManager = Minecraft.getInstance().soundManager
        val iterator = activeSounds.iterator()

        while (iterator.hasNext()) {
            val (uuid, sound) = iterator.next()
            if (currentTick >= sound.endTick || !soundManager.isActive(sound.instance)) {
                println("[WalkmonSoundManager] Expiring sound for $uuid")
                soundManager.stop(sound.instance)
                iterator.remove()
            }
        }

        ++currentTick
    }

    /** Must be called on client thread */
    fun playSound(uuid: UUID, sound: Holder<SoundEvent>, durationTicks: Int, trackId: Any?) {
        val mc = Minecraft.getInstance()
        val currentTick = currentTick
        val existing = activeSounds[uuid]

        if (existing != null && existing.trackId == trackId) {
            println("[WalkmonSoundManager] Skipping replay for identical trackId: $trackId")
            return
        }

        stopSound(uuid)

        val instance = MovingSoundInstance(
            sound = sound.value(),
            category = SoundSource.RECORDS,
            pos = { Minecraft.getInstance().player?.position() },
            startingVol = 1.0f,
            pitch = 1.0f,
            loop = false,
            duration = durationTicks
        )

        activeSounds[uuid] = WalkmonSound(instance, currentTick + durationTicks, trackId)

        println("[WalkmonSoundManager] Playing sound for $uuid → ${sound.unwrapKey().orElseThrow()} @ $durationTicks ticks")
        mc.soundManager.play(instance)
    }

    fun stopSound(uuid: UUID) {
        val existing = activeSounds.remove(uuid) ?: return
        println("[WalkmonSoundManager] Stopping sound for $uuid")
        Minecraft.getInstance().soundManager.stop(existing.instance)
    }

    fun isPlaying(uuid: UUID): Boolean {
        return activeSounds[uuid]?.let {
            Minecraft.getInstance().soundManager.isActive(it.instance)
        } ?: false
    }
}