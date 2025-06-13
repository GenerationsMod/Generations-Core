package generations.gg.generations.core.generationscore.common.world.item

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.item.Item
import net.minecraft.world.item.JukeboxSong

object RecordSongs {
    var MELOETTAS_RELIC_SONG = create("meloettas_relic_song")
    val AZALEA_TOWN = create("azalea_town")
    val CASCARRAFA_CITY = create("cascarrafa_city")
    val CERULEAN_CITY = create("cerulean_city")
    val ETERNA_CITY = create("eterna_city")
    val GOLDENROD_CITY = create("goldenrod_city")
    val ICIRRUS_CITY = create("icirrus_city")
    val JUBILIFE_VILLAGE = create("jubilife_village")
    val LAKE_OF_RAGE = create("lake_of_rage")
    val LAVERRE_CITY = create("laverre_city")
    val LILLIE = create("lillie")
    val POKEMON_CENTER = create("pokemon_center")
    val ROUTE_228 = create("route_228")
    val SLUMBERING_WEALD = create("slumbering_weald")
    val SURF = create("surf")
    val VERMILION_CITY = create("vermilion_city")
    val CYNTHIA = create("cynthia")
    val DEOXYS = create("deoxys")
    val IRIS = create("iris")
    val KANTO = create("kanto")
    val LUSAMINE = create("lusamine")
    val NEMONA = create("nemona")
    val NESSA = create("nessa")
    val PENNY = create("penny")
    val RIVAL = create("rival")
    val SADA_AND_TURO = create("sada_and_turo")
    val SOUTH_PROVINCE = create("south_province")
    val TEAM_ROCKET = create("team_rocket")
    val ULTRA_NECROZMA = create("ultra_necrozma")
    val XY_LEGENDARY = create("xy_legendary")
    val ZINNIA = create("zinnia")
    val LAVENDER_TOWN = create("lavender_town")
    val LUGIA = create("lugia")
    val MT_PYRE = create("mt_pyre")

    private fun create(name: String): ResourceKey<JukeboxSong> = ResourceKey.create(Registries.JUKEBOX_SONG, GenerationsCore.id(name))

    private fun <T : Item> song(item: T, holder: SoundEvent, ticks: Int): JukeboxSong = JukeboxSong(Holder.direct(holder), Component.translatable(item.descriptionId + ".desc"), ticks.toFloat(), 0)

    @JvmStatic
    fun bootstrap(bootstrap: BootstrapContext<JukeboxSong>) {
        bootstrap.register(MELOETTAS_RELIC_SONG, song(GenerationsItems.INERT_RELIC_SONG, GenerationsSounds.MELOETTAS_RELIC_SONG, 30))

        bootstrap.register(AZALEA_TOWN, song(GenerationsItems.AZALEA_TOWN_DISC, GenerationsSounds.AZALEA_TOWN, 200))
        bootstrap.register(CASCARRAFA_CITY, song(GenerationsItems.CASCARRAFA_CITY_DISC, GenerationsSounds.CASCARRAFA_CITY, 169))
        bootstrap.register(CERULEAN_CITY, song(GenerationsItems.CERULEAN_CITY_DISC, GenerationsSounds.CERULEAN_CITY, 184))
        bootstrap.register(ETERNA_CITY, song(GenerationsItems.ETERNA_CITY_DISC, GenerationsSounds.ETERNA_CITY, 136))
        bootstrap.register(GOLDENROD_CITY, song(GenerationsItems.GOLDENROD_CITY_DISC, GenerationsSounds.GOLDENROD_CITY, 182))
        bootstrap.register(ICIRRUS_CITY, song(GenerationsItems.ICIRRUS_CITY_DISC, GenerationsSounds.ICIRRUS_CITY, 148))
        bootstrap.register(JUBILIFE_VILLAGE, song(GenerationsItems.JUBILIFE_VILLAGE_DISC, GenerationsSounds.JUBILIFE_VILLAGE, 202))
        bootstrap.register(LAKE_OF_RAGE, song(GenerationsItems.LAKE_OF_RAGE_DISC, GenerationsSounds.LAKE_OF_RAGE, 139))
        bootstrap.register(LAVERRE_CITY, song(GenerationsItems.LAVERRE_CITY_DISC, GenerationsSounds.LAVERRE_CITY, 281))
        bootstrap.register(LILLIE, song(GenerationsItems.LILLIE_DISC, GenerationsSounds.LILLIE, 312))
        bootstrap.register(POKEMON_CENTER, song(GenerationsItems.POKEMON_CENTER_DISC, GenerationsSounds.POKEMON_CENTER, 240))
        bootstrap.register(ROUTE_228, song(GenerationsItems.ROUTE_228_DISC, GenerationsSounds.ROUTE_228, 418))
        bootstrap.register(SLUMBERING_WEALD, song(GenerationsItems.SLUMBERING_WEALD_DISC, GenerationsSounds.SLUMBERING_WEALD, 262))
        bootstrap.register(SURF, song(GenerationsItems.SURF_DISC, GenerationsSounds.SURF, 261))
        bootstrap.register(VERMILION_CITY, song(GenerationsItems.VERMILION_CITY_DISC, GenerationsSounds.VERMILION_CITY, 216))
        bootstrap.register(CYNTHIA, song(GenerationsItems.CYNTHIA_DISC, GenerationsSounds.CYNTHIA, 385))
        bootstrap.register(DEOXYS, song(GenerationsItems.DEOXYS_DISC, GenerationsSounds.DEOXYS, 414))
        bootstrap.register(IRIS, song(GenerationsItems.IRIS_DISC, GenerationsSounds.IRIS, 291))
        bootstrap.register(KANTO, song(GenerationsItems.KANTO_DISC, GenerationsSounds.KANTO, 337))
        bootstrap.register(LUSAMINE, song(GenerationsItems.LUSAMINE_DISC, GenerationsSounds.LUSAMINE, 337))
        bootstrap.register(NEMONA, song(GenerationsItems.NEMONA_DISC, GenerationsSounds.NEMONA, 158))
        bootstrap.register(NESSA, song(GenerationsItems.NESSA_DISC, GenerationsSounds.NESSA, 263))
        bootstrap.register(PENNY, song(GenerationsItems.PENNY_DISC, GenerationsSounds.PENNY, 267))
        bootstrap.register(RIVAL, song(GenerationsItems.RIVAL_DISC, GenerationsSounds.RIVAL, 221))
        bootstrap.register(SADA_AND_TURO, song(GenerationsItems.SADA_AND_TURO_DISC, GenerationsSounds.SADA_AND_TURO, 349))
        bootstrap.register(SOUTH_PROVINCE, song(GenerationsItems.SOUTH_PROVINCE_DISC, GenerationsSounds.SOUTH_PROVINCE, 214))
        bootstrap.register(TEAM_ROCKET, song(GenerationsItems.TEAM_ROCKET_DISC, GenerationsSounds.TEAM_ROCKET, 186))
        bootstrap.register(ULTRA_NECROZMA, song(GenerationsItems.ULTRA_NECROZMA_DISC, GenerationsSounds.ULTRA_NECROZMA, 296))
        bootstrap.register(XY_LEGENDARY, song(GenerationsItems.XY_LEGENDARY_DISC, GenerationsSounds.XY_LEGENDARY, 261))
        bootstrap.register(ZINNIA, song(GenerationsItems.ZINNIA_DISC, GenerationsSounds.ZINNIA, 320))
        bootstrap.register(LAVENDER_TOWN, song(GenerationsItems.LAVENDER_TOWN_DISC, GenerationsSounds.LAVENDER_TOWN, 369))
        bootstrap.register(LUGIA, song(GenerationsItems.LUGIA_DISC, GenerationsSounds.LUGIA, 341))
        bootstrap.register(MT_PYRE, song(GenerationsItems.MT_PYRE_DISC, GenerationsSounds.MT_PYRE, 219))
    }
}