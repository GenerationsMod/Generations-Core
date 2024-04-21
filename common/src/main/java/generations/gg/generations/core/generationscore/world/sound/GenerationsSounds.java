package generations.gg.generations.core.generationscore.world.sound;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.kyori.adventure.sound.Sound;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

public class GenerationsSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> LUGIA_SHRINE_SONG = registerSound("generations_core.item.lugia_shrine_song");
    public static final RegistrySupplier<SoundEvent> MELOETTAS_RELIC_SONG = registerSound("generations_core.item.meloettas_relic_song");

    public static final RegistrySupplier<SoundEvent> RKS_MACHINE = registerSound("generations_core.block.rks_machine");
    public static final RegistrySupplier<SoundEvent> BOX_OPEN = registerSound("generations_core.block.box_open");
    public static final RegistrySupplier<SoundEvent> BOX_CLOSED = registerSound("generations_core.block.box_closed");
    public static final RegistrySupplier<SoundEvent> ELEVATOR = registerSound("generations_core.block.elevator");


    public static final RegistrySupplier<SoundEvent> UI_CLICK = registerSound("generations_core.other.ui_click");
    public static final RegistrySupplier<SoundEvent> ZYGARDE_CELL = registerSound("generations_core.other.zygarde_cell");

    private static RegistrySupplier<SoundEvent> registerSound(String id) {
        return SOUNDS.register(id, () -> SoundEvent.createVariableRangeEvent(GenerationsCore.id(id)));
    }

    public static void init() {
        SOUNDS.register();
    }
}
