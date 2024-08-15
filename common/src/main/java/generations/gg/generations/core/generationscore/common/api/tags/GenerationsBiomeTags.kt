package generations.gg.generations.core.generationscore.common.api.tags

import com.cobblemon.mod.common.util.cobblemonResource
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome

object GenerationsBiomeTags {

    @JvmField val IS_FLORAL = cobblemon("is_floral")
    @JvmField val IS_CAVE = cobblemon("is_cave")
    @JvmField val IS_MAGICAL = cobblemon("is_magical")
    @JvmField val IS_SNOWY_FOREST = cobblemon("is_snowy_forest")
    @JvmField val IS_TAIGA = cobblemon("is_taiga")
    @JvmField val IS_DESERT = cobblemon("is_desert")
    @JvmField val IS_RIVER = cobblemon("is_river")
    @JvmField val IS_TUNDRA = cobblemon("is_tundra")
    @JvmField val IS_PLATEAU = cobblemon("is_plateau")
    @JvmField val IS_SAVANNA = cobblemon("is_savanna")
    @JvmField val IS_JUNGLE = cobblemon("is_jungle")
    @JvmField val IS_ISLAND = cobblemon("is_island")
    @JvmField val IS_SPOOKY = cobblemon("is_spooky")
    @JvmField val IS_FOREST = cobblemon("is_forest")
    @JvmField val IS_COAST = cobblemon("is_coast")
    @JvmField val IS_PLAINS = cobblemon("is_plains")
    @JvmField val IS_SWAMP = cobblemon("is_swamp")
    @JvmField val IS_GLACIAL = cobblemon("is_glacial")
    @JvmField val IS_HILLS = cobblemon("is_hills")
    @JvmField val IS_BAMBOO = cobblemon("is_bamboo")
    @JvmField val IS_GRASSLAND = cobblemon("is_grassland")
    @JvmField val IS_MOUNTAIN = cobblemon("is_mountain")
    @JvmField val IS_DRIPSTONE = cobblemon("is_dripstone")
    @JvmField val IS_BADLANDS = cobblemon("is_badlands")
    @JvmField val IS_PEAK = cobblemon("is_peak")


    private fun generations(path: String) : TagKey<Biome> = TagKey.create(Registries.BIOME, cobblemonResource(path))
    private fun cobblemon(path: String) : TagKey<Biome> = TagKey.create(Registries.BIOME, GenerationsCore.id(path))

}