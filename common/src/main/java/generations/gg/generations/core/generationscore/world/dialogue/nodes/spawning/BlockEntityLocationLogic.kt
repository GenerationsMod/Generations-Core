package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning

import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.phys.Vec3
import java.util.function.Supplier

private fun Level.getBlockEntity(it: BlockPos?, key: ResourceKey<BlockEntityType<*>>) = BuiltInRegistries.BLOCK_ENTITY_TYPE.getHolderOrThrow(key)

@JvmRecord
data class BlockEntityLocationLogic(val key: ResourceKey<BlockEntityType<*>>) : LocationLogic {
    override fun createSupplier(player: Player): Supplier<Vec3> {
        val world = player.getLevel()
        val pos = BlockPos.withinManhattanStream(player.onPos, 10, 10, 10)
            .filter { world.getBlockEntity(it, key.value()).isPresent }
            .findFirst().orElse(player.onPos).center
        return Supplier { pos }
    }

    companion object {
        var TYPE = "block_entity"

        fun of(key: ResourceKey<BlockEntityType<*>>): BlockEntityLocationLogic {
            return BlockEntityLocationLogic(key)
        }
    }
}

fun ResourceKey<BlockEntityType<*>>.value(): BlockEntityType<*> = BuiltInRegistries.BLOCK_ENTITY_TYPE.getHolderOrThrow(this).value()
