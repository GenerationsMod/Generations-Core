package generations.gg.generations.core.generationscore.common.world.sound

import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.core.registries.Registries
import net.minecraft.sounds.SoundEvent

object GenerationsSounds {
    val SOUNDS: DeferredRegister<SoundEvent> = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.SOUND_EVENT)

    @JvmField val LUGIA_SHRINE_SONG: RegistrySupplier<SoundEvent> = registerSound("generations_core.item.lugia_shrine_song")
    val MELOETTAS_RELIC_SONG: RegistrySupplier<SoundEvent> = registerSound("generations_core.item.meloettas_relic_song")
    @JvmField val RKS_MACHINE: RegistrySupplier<SoundEvent> = registerSound("generations_core.block.rks_machine")
    @JvmField val BOX_OPEN: RegistrySupplier<SoundEvent> = registerSound("generations_core.block.box_open")
    @JvmField val BOX_CLOSED: RegistrySupplier<SoundEvent> = registerSound("generations_core.block.box_closed")
    @JvmField val ELEVATOR: RegistrySupplier<SoundEvent> = registerSound("generations_core.block.elevator")

    val UI_CLICK: RegistrySupplier<SoundEvent> = registerSound("generations_core.other.ui_click")
    @JvmField val CAMERA_SHUTTER: RegistrySupplier<SoundEvent> = registerSound("generations_core.other.camera_shutter")
    val ZYGARDE_CELL: RegistrySupplier<SoundEvent> = registerSound("generations_core.other.zygarde_cell")

    val AZALEA_TOWN: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.ambient.azalea_town")
    val CASCARRAFA_CITY: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.ambient.cascarrafa_city")
    val CERULEAN_CITY: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.ambient.cerulean_city")
    val ETERNA_CITY: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.ambient.eterna_city")
    val GOLDENROD_CITY: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.ambient.goldenrod_city")
    val ICIRRUS_CITY: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.ambient.icirrus_city")
    val JUBILIFE_VILLAGE: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.ambient.jubilife_village")
    val LAKE_OF_RAGE: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.ambient.lake_of_rage")
    val LAVERRE_CITY: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.ambient.laverre_city")
    val LILLIE: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.ambient.lillie")
    val POKEMON_CENTER: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.ambient.pokemon_center")
    val ROUTE_228: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.ambient.route_228")
    val SLUMBERING_WEALD: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.ambient.slumbering_weald")
    val SURF: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.ambient.surf")
    val VERMILION_CITY: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.ambient.vermilion_city")
    val CYNTHIA: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.battle.cynthia")
    val DEOXYS: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.battle.deoxys")
    val IRIS: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.battle.iris")
    val KANTO: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.battle.kanto")
    val LUSAMINE: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.battle.lusamine")
    val NEMONA: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.battle.nemona")
    val NESSA: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.battle.nessa")
    val PENNY: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.battle.penny")
    val RIVAL: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.battle.rival")
    val SADA_AND_TURO: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.battle.sada_and_turo")
    val SOUTH_PROVINCE: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.battle.south_province")
    val TEAM_ROCKET: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.battle.team_rocket")
    val ULTRA_NECROZMA: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.battle.ultra_necrozma")
    val XY_LEGENDARY: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.battle.xy_legendary")
    val ZINNIA: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.battle.zinnia")
    val LAVENDER_TOWN: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.special.lavender_town")
    val LUGIA: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.special.lugia")
    val MT_PYRE: RegistrySupplier<SoundEvent> = registerSound("generations_core.music.special.mt_pyre")

    private fun registerSound(id: String): RegistrySupplier<SoundEvent> = SOUNDS.register(id) { SoundEvent.createVariableRangeEvent(GenerationsCore.id(id)) }

    @JvmStatic
    fun init() {
        SOUNDS.register()
    }
}
