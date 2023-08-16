package generations.gg.generations.core.generationscore.world.sound;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

public class GenerationsSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> LUGIA_SHRINE_SONG = registerSound("generations_core.item.lugia_shrine_song");
    public static final RegistrySupplier<SoundEvent> UI_CLICK = registerSound("generations_core.other.ui_click");
    public static final RegistrySupplier<SoundEvent> AZALEA_TOWN = registerSound("generations_core.music.ambient.azalea_town");
    public static final RegistrySupplier<SoundEvent> CASCARRAFA_CITY = registerSound("generations_core.music.ambient.cascarrafa_city");
    public static final RegistrySupplier<SoundEvent> CERULEAN_CITY = registerSound("generations_core.music.ambient.cerulean_city");
    public static final RegistrySupplier<SoundEvent> ETERNA_CITY = registerSound("generations_core.music.ambient.eterna_city");
    public static final RegistrySupplier<SoundEvent> GOLDENROD_CITY = registerSound("generations_core.music.ambient.goldenrod_city");
    public static final RegistrySupplier<SoundEvent> ICIRRUS_CITY = registerSound("generations_core.music.ambient.icirrus_city");
    public static final RegistrySupplier<SoundEvent> JUBILIFE_VILLAGE = registerSound("generations_core.music.ambient.jubilife_villag");
    public static final RegistrySupplier<SoundEvent> LAKE_OF_RAGE = registerSound("generations_core.music.ambient.lake_of_rage");
    public static final RegistrySupplier<SoundEvent> LAVERRE_CITY = registerSound("generations_core.music.ambient.laverre_city");
    public static final RegistrySupplier<SoundEvent> LILLIE = registerSound("generations_core.music.ambient.lillie");
    public static final RegistrySupplier<SoundEvent> POKEMON_CENTER = registerSound("generations_core.music.ambient.pokemon_center");
    public static final RegistrySupplier<SoundEvent> ROUTE_228 = registerSound("generations_core.music.ambient.route_228");
    public static final RegistrySupplier<SoundEvent> SLUMBERING_WEALD = registerSound("generations_core.music.ambient.slumbering_weal");
    public static final RegistrySupplier<SoundEvent> SURF = registerSound("generations_core.music.ambient.surf");
    public static final RegistrySupplier<SoundEvent> VERMILION_CITY = registerSound("generations_core.music.ambient.vermilion_city");
    public static final RegistrySupplier<SoundEvent> CYNTHIA = registerSound("generations_core.music.battle.cynthia");
    public static final RegistrySupplier<SoundEvent> DEOXYS = registerSound("generations_core.music.battle.deoxys");
    public static final RegistrySupplier<SoundEvent> IRIS = registerSound("generations_core.music.battle.iris");
    public static final RegistrySupplier<SoundEvent> KANTO = registerSound("generations_core.music.battle.kanto");
    public static final RegistrySupplier<SoundEvent> LUSAMINE = registerSound("generations_core.music.battle.lusamine");
    public static final RegistrySupplier<SoundEvent> NEMONA = registerSound("generations_core.music.battle.nemona");
    public static final RegistrySupplier<SoundEvent> NESSA = registerSound("generations_core.music.battle.nessa");
    public static final RegistrySupplier<SoundEvent> PENNY = registerSound("generations_core.music.battle.penny");
    public static final RegistrySupplier<SoundEvent> RIVAL = registerSound("generations_core.music.battle.rival");
    public static final RegistrySupplier<SoundEvent> SADA_AND_TURO = registerSound("generations_core.music.battle.sada_and_turo");
    public static final RegistrySupplier<SoundEvent> SOUTH_PROVINCE = registerSound("generations_core.music.battle.south_province");
    public static final RegistrySupplier<SoundEvent> TEAM_ROCKET = registerSound("generations_core.music.battle.team_rocket");
    public static final RegistrySupplier<SoundEvent> ULTRA_NECROZMA = registerSound("generations_core.music.battle.ultra_necrozma");
    public static final RegistrySupplier<SoundEvent> XY_LEGENDARY = registerSound("generations_core.music.battle.xy_legendary");
    public static final RegistrySupplier<SoundEvent> ZINNIA = registerSound("generations_core.music.battle.zinnia");
    public static final RegistrySupplier<SoundEvent> LAVENDER_TOWN = registerSound("generations_core.music.special.lavender_town");
    public static final RegistrySupplier<SoundEvent> LUGIA = registerSound("generations_core.music.special.lugia");
    public static final RegistrySupplier<SoundEvent> MT_PYRE = registerSound("generations_core.music.special.mt_pyre");

    private static RegistrySupplier<SoundEvent> registerSound(String id) {
        return SOUNDS.register(id, () -> SoundEvent.createVariableRangeEvent(GenerationsCore.id(id)));
    }

    public static void init() {
        SOUNDS.register();
    }
}
