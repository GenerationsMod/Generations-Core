package generations.gg.generations.core.generationscore.common.world.item

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds
import net.minecraft.Util
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

    private fun <T : Item> song(item: Holder<T>, holder: Holder<SoundEvent>, ticks: Int): JukeboxSong = JukeboxSong(holder, Component.translatable(item.value().descriptionId + ".desc"), ticks.toFloat(), 0)

    private fun BootstrapContext<JukeboxSong>.register(
        key: ResourceKey<JukeboxSong>,
        soundEvent: Holder<SoundEvent>,
        lengthInSeconds: Int,
        comparatorOutput: Int = 0,
    ) = this.register(
            key,
            JukeboxSong(
                soundEvent,
                Component.translatable("item.${key.location().namespace}.${key.location().path}_disc.desc"),
                lengthInSeconds.toFloat(),
                comparatorOutput
            )
        )


    @JvmStatic
    fun bootstrap(bootstrap: BootstrapContext<JukeboxSong>) {
        bootstrap.register(MELOETTAS_RELIC_SONG, GenerationsSounds.MELOETTAS_RELIC_SONG, 30)

        bootstrap.register(AZALEA_TOWN, GenerationsSounds.AZALEA_TOWN, 200)
        bootstrap.register(CASCARRAFA_CITY, GenerationsSounds.CASCARRAFA_CITY, 169)
        bootstrap.register(CERULEAN_CITY, GenerationsSounds.CERULEAN_CITY, 184)
        bootstrap.register(ETERNA_CITY, GenerationsSounds.ETERNA_CITY, 136)
        bootstrap.register(GOLDENROD_CITY, GenerationsSounds.GOLDENROD_CITY, 182)
        bootstrap.register(ICIRRUS_CITY, GenerationsSounds.ICIRRUS_CITY, 148)
        bootstrap.register(JUBILIFE_VILLAGE, GenerationsSounds.JUBILIFE_VILLAGE, 202)
        bootstrap.register(LAKE_OF_RAGE, GenerationsSounds.LAKE_OF_RAGE, 139)
        bootstrap.register(LAVERRE_CITY, GenerationsSounds.LAVERRE_CITY, 281)
        bootstrap.register(LILLIE, GenerationsSounds.LILLIE, 312)
        bootstrap.register(POKEMON_CENTER, GenerationsSounds.POKEMON_CENTER, 240)
        bootstrap.register(ROUTE_228, GenerationsSounds.ROUTE_228, 418)
        bootstrap.register(SLUMBERING_WEALD, GenerationsSounds.SLUMBERING_WEALD, 262)
        bootstrap.register(SURF, GenerationsSounds.SURF, 261)
        bootstrap.register(VERMILION_CITY, GenerationsSounds.VERMILION_CITY, 216)
        bootstrap.register(CYNTHIA, GenerationsSounds.CYNTHIA, 385)
        bootstrap.register(DEOXYS, GenerationsSounds.DEOXYS, 414)
        bootstrap.register(IRIS, GenerationsSounds.IRIS, 291)
        bootstrap.register(KANTO, GenerationsSounds.KANTO, 337)
        bootstrap.register(LUSAMINE, GenerationsSounds.LUSAMINE, 337)
        bootstrap.register(NEMONA, GenerationsSounds.NEMONA, 158)
        bootstrap.register(NESSA, GenerationsSounds.NESSA, 263)
        bootstrap.register(PENNY, GenerationsSounds.PENNY, 267)
        bootstrap.register(RIVAL, GenerationsSounds.RIVAL, 221)
        bootstrap.register(SADA_AND_TURO, GenerationsSounds.SADA_AND_TURO, 349)
        bootstrap.register(SOUTH_PROVINCE, GenerationsSounds.SOUTH_PROVINCE, 214)
        bootstrap.register(TEAM_ROCKET, GenerationsSounds.TEAM_ROCKET, 186)
        bootstrap.register(ULTRA_NECROZMA, GenerationsSounds.ULTRA_NECROZMA, 296)
        bootstrap.register(XY_LEGENDARY, GenerationsSounds.XY_LEGENDARY, 261)
        bootstrap.register(ZINNIA, GenerationsSounds.ZINNIA, 320)
        bootstrap.register(LAVENDER_TOWN, GenerationsSounds.LAVENDER_TOWN, 369)
        bootstrap.register(LUGIA, GenerationsSounds.LUGIA, 341)
        bootstrap.register(MT_PYRE, GenerationsSounds.MT_PYRE, 219)
    }
}