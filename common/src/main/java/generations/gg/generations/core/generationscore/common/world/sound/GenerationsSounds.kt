package generations.gg.generations.core.generationscore.common.world.sound

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.sounds.SoundEvent

object GenerationsSounds: PlatformRegistry<SoundEvent>() {
    override val registry: Registry<SoundEvent> = BuiltInRegistries.SOUND_EVENT
    override val resourceKey: ResourceKey<Registry<SoundEvent>> = Registries.SOUND_EVENT

    val LUGIA_SHRINE_SONG = registerSound("generations_core.item.lugia_shrine_song")
    val MELOETTAS_RELIC_SONG = registerSound("generations_core.item.meloettas_relic_song")
    val RKS_MACHINE = registerSound("generations_core.block.rks_machine")
    val BOX_OPEN = registerSound("generations_core.block.box_open")
    val BOX_CLOSED = registerSound("generations_core.block.box_closed")
    val ELEVATOR = registerSound("generations_core.block.elevator")

    val UI_CLICK = registerSound("generations_core.other.ui_click")
    val CAMERA_SHUTTER = registerSound("generations_core.other.camera_shutter")
    val ZYGARDE_CELL = registerSound("generations_core.other.zygarde_cell")

    val AZALEA_TOWN = registerSound("generations_core.music.ambient.azalea_town")
    val CASCARRAFA_CITY = registerSound("generations_core.music.ambient.cascarrafa_city")
    val CERULEAN_CITY = registerSound("generations_core.music.ambient.cerulean_city")
    val ETERNA_CITY = registerSound("generations_core.music.ambient.eterna_city")
    val GOLDENROD_CITY = registerSound("generations_core.music.ambient.goldenrod_city")
    val ICIRRUS_CITY = registerSound("generations_core.music.ambient.icirrus_city")
    val JUBILIFE_VILLAGE = registerSound("generations_core.music.ambient.jubilife_village")
    val LAKE_OF_RAGE = registerSound("generations_core.music.ambient.lake_of_rage")
    val LAVERRE_CITY = registerSound("generations_core.music.ambient.laverre_city")
    val LILLIE = registerSound("generations_core.music.ambient.lillie")
    val POKEMON_CENTER = registerSound("generations_core.music.ambient.pokemon_center")
    val ROUTE_228 = registerSound("generations_core.music.ambient.route_228")
    val SLUMBERING_WEALD = registerSound("generations_core.music.ambient.slumbering_weald")
    val SURF = registerSound("generations_core.music.ambient.surf")
    val VERMILION_CITY = registerSound("generations_core.music.ambient.vermilion_city")
    val CYNTHIA = registerSound("generations_core.music.battle.cynthia")
    val DEOXYS = registerSound("generations_core.music.battle.deoxys")
    val IRIS = registerSound("generations_core.music.battle.iris")
    val KANTO = registerSound("generations_core.music.battle.kanto")
    val LUSAMINE = registerSound("generations_core.music.battle.lusamine")
    val NEMONA = registerSound("generations_core.music.battle.nemona")
    val NESSA = registerSound("generations_core.music.battle.nessa")
    val PENNY = registerSound("generations_core.music.battle.penny")
    val RIVAL = registerSound("generations_core.music.battle.rival")
    val SADA_AND_TURO = registerSound("generations_core.music.battle.sada_and_turo")
    val SOUTH_PROVINCE = registerSound("generations_core.music.battle.south_province")
    val TEAM_ROCKET = registerSound("generations_core.music.battle.team_rocket")
    val ULTRA_NECROZMA = registerSound("generations_core.music.battle.ultra_necrozma")
    val XY_LEGENDARY = registerSound("generations_core.music.battle.xy_legendary")
    val ZINNIA = registerSound("generations_core.music.battle.zinnia")
    val LAVENDER_TOWN = registerSound("generations_core.music.special.lavender_town")
    val LUGIA = registerSound("generations_core.music.special.lugia")
    val MT_PYRE = registerSound("generations_core.music.special.mt_pyre")

    private fun registerSound(id: String): SoundEvent = create(GenerationsCore.id(id), SoundEvent.createVariableRangeEvent(GenerationsCore.id(id)))
}
