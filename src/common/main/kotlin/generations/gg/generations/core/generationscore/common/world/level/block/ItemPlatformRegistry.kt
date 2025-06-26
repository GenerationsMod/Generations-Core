package generations.gg.generations.core.generationscore.common.world.level.block

import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.Block

open class BlockPlatformRegistry: PlatformRegistry<Block>(Registries.BLOCK, BuiltInRegistries.BLOCK) {
}