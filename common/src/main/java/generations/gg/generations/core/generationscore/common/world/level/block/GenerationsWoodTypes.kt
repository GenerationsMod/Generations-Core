package generations.gg.generations.core.generationscore.common.world.level.block

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.level.block.state.properties.GenerationsBlockSetTypes
import net.minecraft.world.level.block.state.properties.WoodType

/**
 * @author JT122406
 * @see WoodType
 * Registers custom Generations wood types
 */
object GenerationsWoodTypes {
    @JvmField val ULTRA_DARK: WoodType = WoodType.register(WoodType(GenerationsCore.MOD_ID + ":ultra_dark", GenerationsBlockSetTypes.ULTRA_DARK))
    @JvmField val ULTRA_JUNGLE: WoodType = WoodType.register(WoodType(GenerationsCore.MOD_ID + ":ultra_jungle", GenerationsBlockSetTypes.ULTRA_JUNGLE))
    @JvmField val GHOST: WoodType = WoodType.register(WoodType(GenerationsCore.MOD_ID + ":ghost", GenerationsBlockSetTypes.GHOST))
}
