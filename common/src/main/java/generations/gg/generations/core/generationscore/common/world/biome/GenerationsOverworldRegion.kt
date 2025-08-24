package generations.gg.generations.core.generationscore.common.world.biome

import com.mojang.datafixers.util.Pair
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Climate
import terrablender.api.ParameterUtils
import terrablender.api.Region
import terrablender.api.RegionType
import terrablender.api.VanillaParameterOverlayBuilder
import java.util.function.Consumer

class GenerationsOverworldRegion(name: ResourceLocation, weight: Int) : Region(name, RegionType.OVERWORLD, weight) {
    override fun addBiomes(
        registry: Registry<Biome?>?,
        mapper: Consumer<Pair<Climate.ParameterPoint?, ResourceKey<Biome?>?>?>?
    ) {
        val builder = VanillaParameterOverlayBuilder()

        ParameterUtils.ParameterPointListBuilder()
            .temperature(ParameterUtils.Temperature.FULL_RANGE)
            .humidity(ParameterUtils.Humidity.span(
                ParameterUtils.Humidity.ARID,
                ParameterUtils.Humidity.NEUTRAL
            ))
            .continentalness(ParameterUtils.Continentalness.span(
                ParameterUtils.Continentalness.INLAND,
                ParameterUtils.Continentalness.FAR_INLAND
            ))
            .erosion(ParameterUtils.Erosion.span(
                ParameterUtils.Erosion.EROSION_0,
                ParameterUtils.Erosion.EROSION_1
            ))
            .depth(ParameterUtils.Depth.UNDERGROUND)
            .weirdness(ParameterUtils.Weirdness.span(
                ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING,
                ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING
            ))
            .build()
            .forEach { point -> builder.add(point, GenerationsBiomes.TERASTAL_CAVES) }

        builder.build().forEach(mapper)
    }
}
