package generations.gg.generations.core.generationscore.common.mixin;

import generations.gg.generations.core.generationscore.common.world.level.JukeBoxExtension;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.JukeboxSongPlayer;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(JukeboxSongPlayer.class)
public abstract class JukeboxSongPlayerMixin implements JukeBoxExtension {
    @Unique private Runnable generations_Core$onFinish = null;

    @Shadow private long ticksSinceSongStarted;

    @Shadow @Nullable public abstract JukeboxSong getSong();

    @Inject(method = "stop", at = @At("HEAD"))
    public void onStop(LevelAccessor level, BlockState state, CallbackInfo ci) {
        var song = getSong();
        if (song != null && song.hasFinished(ticksSinceSongStarted)) {
            if(generations_Core$onFinish != null) generations_Core$onFinish.run();
        }
    }

    @Override
    public void onFinished(Runnable run) {
        this.generations_Core$onFinish = run;
    }
}
