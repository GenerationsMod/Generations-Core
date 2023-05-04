package generations.gg.generations.core.generationscore.world.sound;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class PokeModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.SOUND_EVENT);
    public static final Map<ResourceLocation, SoundEvent> POKEMON_AMBIENT_SOUNDS = new HashMap<>();
    public static final Map<ResourceLocation, SoundEvent> POKEMON_FAINT_SOUNDS = new HashMap<>();

    // Battle Tracks
    public static final RegistrySupplier<SoundEvent> BATTLE_1_TRACK = registerSound("pixelmon.music.battle1");
    public static final RegistrySupplier<SoundEvent> BATTLE_2_TRACK = registerSound("pixelmon.music.battle2");
    public static final RegistrySupplier<SoundEvent> BATTLE_3_TRACK = registerSound("pixelmon.music.battle3");
    public static final RegistrySupplier<SoundEvent> BATTLE_4_TRACK = registerSound("pixelmon.music.battle4");
    public static final RegistrySupplier<SoundEvent> BATTLE_5_TRACK = registerSound("pixelmon.music.battle5");
    public static final RegistrySupplier<SoundEvent> BATTLE_6_TRACK = registerSound("pixelmon.music.battle6");
    public static final RegistrySupplier<SoundEvent> BATTLE_7_TRACK = registerSound("pixelmon.music.battle7");
    public static final RegistrySupplier<SoundEvent> BATTLE_8_TRACK = registerSound("pixelmon.music.battle8");
    public static final RegistrySupplier<SoundEvent> BATTLE_9_TRACK = registerSound("pixelmon.music.battle9");
    public static final RegistrySupplier<SoundEvent> BATTLE_10_TRACK = registerSound("pixelmon.music.battle10");
    public static final RegistrySupplier<SoundEvent> BATTLE_11_TRACK = registerSound("pixelmon.music.battle11");
    public static final RegistrySupplier<SoundEvent> BATTLE_12_TRACK = registerSound("pixelmon.music.battle12");
    public static final RegistrySupplier<SoundEvent> BATTLE_13_TRACK = registerSound("pixelmon.music.battle13");
    public static final RegistrySupplier<SoundEvent> BATTLE_14_TRACK = registerSound("pixelmon.music.battle14");
    public static final RegistrySupplier<SoundEvent> BATTLE_15_TRACK = registerSound("pixelmon.music.battle15");
    public static final RegistrySupplier<SoundEvent> BATTLE_16_TRACK = registerSound("pixelmon.music.battle16");
    public static final RegistrySupplier<SoundEvent> BATTLE_17_TRACK = registerSound("pixelmon.music.battle17");
    public static final RegistrySupplier<SoundEvent> BATTLE_18_TRACK = registerSound("pixelmon.music.battle18");
    public static final RegistrySupplier<SoundEvent> BATTLE_19_TRACK = registerSound("pixelmon.music.battle19");
    public static final RegistrySupplier<SoundEvent> BATTLE_20_TRACK = registerSound("pixelmon.music.battle20");
    public static final RegistrySupplier<SoundEvent> BATTLE_21_TRACK = registerSound("pixelmon.music.battle21");
    public static final RegistrySupplier<SoundEvent> BATTLE_22_TRACK = registerSound("pixelmon.music.battle22");
    public static final RegistrySupplier<SoundEvent> BATTLE_23_TRACK = registerSound("pixelmon.music.battle23");
    public static final RegistrySupplier<SoundEvent> BATTLE_24_TRACK = registerSound("pixelmon.music.battle24");
    public static final RegistrySupplier<SoundEvent> BATTLE_25_TRACK = registerSound("pixelmon.music.battle25");
    public static final RegistrySupplier<SoundEvent> BATTLE_26_TRACK = registerSound("pixelmon.music.battle26");
    public static final RegistrySupplier<SoundEvent> BATTLE_27_TRACK = registerSound("pixelmon.music.battle27");
    public static final List<RegistrySupplier<SoundEvent>> BATTLE_TRACKS = new ArrayList<>(List.of(
            BATTLE_1_TRACK,
            BATTLE_2_TRACK,
            BATTLE_3_TRACK,
            BATTLE_4_TRACK,
            BATTLE_5_TRACK,
            BATTLE_6_TRACK,
            BATTLE_7_TRACK,
            BATTLE_8_TRACK,
            BATTLE_9_TRACK,
            BATTLE_10_TRACK,
            BATTLE_11_TRACK,
            BATTLE_12_TRACK,
            BATTLE_13_TRACK,
            BATTLE_14_TRACK,
            BATTLE_15_TRACK,
            BATTLE_16_TRACK,
            BATTLE_17_TRACK,
            BATTLE_18_TRACK,
            BATTLE_19_TRACK,
            BATTLE_20_TRACK,
            BATTLE_21_TRACK,
            BATTLE_22_TRACK,
            BATTLE_23_TRACK,
            BATTLE_24_TRACK,
            BATTLE_25_TRACK,
            BATTLE_26_TRACK,
            BATTLE_27_TRACK
    )); // Wrapped in arrayList so music can be added or removed by side mods

    // Battle Sound Effects
    public static final RegistrySupplier<SoundEvent> SEND_OUT = registerSound("pixelmon.battle.send_out_normal");
    public static final RegistrySupplier<SoundEvent> SEND_OUT_GMAX = registerSound("pixelmon.battle.send_out_gmax");
    public static final RegistrySupplier<SoundEvent> RELEASE_PIXELMON = registerSound("pixelmon.battle.release_pixelmon");
    public static final RegistrySupplier<SoundEvent> COME_BACK = registerSound("pixelmon.battle.come_back_normal");
    public static final RegistrySupplier<SoundEvent> LOW_HP = registerSound("pixelmon.battle.low_hp");
    public static final RegistrySupplier<SoundEvent> STAT_UP = registerSound("pixelmon.battle.stat_up");
    public static final RegistrySupplier<SoundEvent> STAT_DOWN = registerSound("pixelmon.battle.stat_down");
    public static final RegistrySupplier<SoundEvent> SUPER_EFFECTIVE = registerSound("pixelmon.battle.super_effective");
    public static final RegistrySupplier<SoundEvent> CONFUSION = registerSound("pixelmon.battle.confusion_status");

    // Ambient Music
    public static final RegistrySupplier<SoundEvent> GAME_MUSIC = registerSound("pixelmon.music.game");

    // Other
    public static final RegistrySupplier<SoundEvent> UI_CLICK = registerSound("pokemod.other.ui_click");

    // Blocks
    public static final RegistrySupplier<SoundEvent> PC = registerSound("pixelmon.block.pc");
    public static final RegistrySupplier<SoundEvent> PC_OPEN = registerSound("pixelmon.block.pc_open");
    public static final RegistrySupplier<SoundEvent> PC_CLOSE = registerSound("pixelmon.block.pc_close");
    public static final RegistrySupplier<SoundEvent> HEALER_ACTIVATE = registerSound("pixelmon.block.healer.activate");
    public static final RegistrySupplier<SoundEvent> HEALER_IDLE = registerSound("pixelmon.block.healer.idle");

    // Pokeballs
    public static final RegistrySupplier<SoundEvent> POKEBALL_CAPTURE = registerSound("pixelmon.item.pokeball.capture");
    public static final RegistrySupplier<SoundEvent> POKEBALL_CAPTURE_SUCCESS = registerSound("pixelmon.item.pokeball.capture_success");
    public static final RegistrySupplier<SoundEvent> POKEBALL_CLOSE = registerSound("pixelmon.item.pokeball.close");
    public static final RegistrySupplier<SoundEvent> POKEBALL_RELEASE = registerSound("pixelmon.item.pokeball.release");

    // Music
    public static final Supplier<Music> GAME = () -> new Music(Holder.direct(PokeModSounds.GAME_MUSIC.get()), 300, 1200, true);

    /**
     * Our mixin will call this method to decide what music to play. For now, this method just overwrites vanillas "Game" music. Biome music should be untouched.
     */
    public static Music getCurrentGameMusic() {
        return GAME.get();
    }

    /**
     * Retrieves a random bit of battle music.
     */
    public static RegistrySupplier<SoundEvent> getRandomBattleMusic() {
        return BATTLE_TRACKS.get(GenerationsCore.RANDOM_SOURCE.nextInt(BATTLE_TRACKS.size()));
    }

    public static SoundEvent getPokemonAmbientSound(ResourceLocation id) {
        return POKEMON_AMBIENT_SOUNDS.computeIfAbsent(id, location -> SoundEvent.createVariableRangeEvent(new ResourceLocation(id.getNamespace(), "pixelmon.mob." + id.getPath())));
    }

    public static SoundEvent getPokemonFaintSound(ResourceLocation id) {
        return POKEMON_FAINT_SOUNDS.computeIfAbsent(id, location -> SoundEvent.createVariableRangeEvent(new ResourceLocation(id.getNamespace(), "pixelmon.mob." + id.getPath() + ".fainted")));
    }

    private static RegistrySupplier<SoundEvent> registerSound(String id) {
        return SOUNDS.register(id, () -> SoundEvent.createVariableRangeEvent(GenerationsCore.id(id)));
    }

    public static void onInit() {
        SOUNDS.register();
    }
}
