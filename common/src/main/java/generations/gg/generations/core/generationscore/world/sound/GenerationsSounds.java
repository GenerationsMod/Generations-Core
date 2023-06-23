package generations.gg.generations.core.generationscore.world.sound;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

public class GenerationsSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.SOUND_EVENT);

    public static RegistrySupplier<SoundEvent> LUGIA_SHRINE_SONG = registerSound("generations.item.lugia_shrine_song");
    public static RegistrySupplier<SoundEvent> UI_CLICK = registerSound("generations.other.ui_click");

    private static RegistrySupplier<SoundEvent> registerSound(String id) {
        return SOUNDS.register(id, () -> SoundEvent.createVariableRangeEvent(GenerationsCore.id(id)));
    }

    public static void init() {
        SOUNDS.register();
    }
}
