package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.state.BlockState

class WeatherTrioShrineBlockEntity(pos: BlockPos, state: BlockState) : InteractShrineBlockEntity(GenerationsBlockEntities.WEATHER_TRIO.get(), pos, state)
