package generations.gg.generations.core.generationscore.common.client.render.tera

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import net.minecraft.client.Minecraft
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource

object TeraVisualEffectHandler {
    @JvmStatic
    fun spawnTeraParticles(entity: PokemonEntity, teraType: String) {
        val level = Minecraft.getInstance().level ?: return
        val pos = entity.position()

        for (i in 0..99) {
            level.addParticle(
                ParticleTypes.END_ROD,
                entity.getX() + (Math.random() - 0.5),
                entity.getY() + (Math.random() - 0.5),
                entity.getZ() + (Math.random() - 0.5),
                (Math.random() - 0.5) * 1,
                (Math.random() - 0.5) * 1,
                (Math.random() - 0.5) * 1
            )
        }
    }

    @JvmStatic
    fun playTeraAmbient(entity: PokemonEntity) {
        val level = Minecraft.getInstance().level ?: return
        level.playLocalSound(
            entity,
            SoundEvents.AMETHYST_BLOCK_CHIME,
            SoundSource.PLAYERS,
            3.0f,
            0.1f
        )
        level.playLocalSound(
            entity,
            SoundEvents.AMETHYST_BLOCK_RESONATE,
            SoundSource.PLAYERS,
            0.5f,
            0.1f
        )
    }

    @JvmStatic
    fun playTeraSounds(entity: PokemonEntity) {
        val level = Minecraft.getInstance().level ?: return
        level.playLocalSound(
            entity,
            SoundEvents.GLASS_BREAK,
            SoundSource.PLAYERS,
            1.0f,
            0.1f
        )
        level.playLocalSound(
            entity,
            SoundEvents.AMETHYST_BLOCK_BREAK,
            SoundSource.PLAYERS,
            1.0f,
            0.1f
        )
    }
}