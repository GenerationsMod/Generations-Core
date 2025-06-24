package generations.gg.generations.core.generationscore.common.world.sound

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance
import net.minecraft.core.Holder
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundSource
import net.minecraft.util.RandomSource

class SoundInstanceWrapper(
    holder: Holder<SoundEvent>,
    volume: Float,
    pitch: Float
) : AbstractTickableSoundInstance(holder.value(), SoundSource.RECORDS, RandomSource.create()) {

    init {
        this.volume = volume
        this.pitch = pitch
        this.looping = false
        this.delay = 0
    }

    override fun tick() {
        // optional fade logic or distance update
    }
}