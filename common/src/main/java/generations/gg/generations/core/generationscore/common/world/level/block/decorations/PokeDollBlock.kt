package generations.gg.generations.core.generationscore.common.world.level.block.decorations

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.PokeDollBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Vec3i
import net.minecraft.util.Mth
import net.minecraft.util.StringRepresentable
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.Vec3
import org.joml.Math

@Suppress("deprecation")
class PokeDollBlock(val name: String, private val isShiny: Boolean, val scale: Float) : GenericModelBlock<PokeDollBlockEntity>(Properties.of().sound(SoundType.WOOL).strength(1.0f), modelResource = GenerationsCore.id("models/block/pokedolls/$name.pk")) {
    override val blockEntityType: BlockEntityType<PokeDollBlockEntity>
        get() = GenerationsBlockEntities.POKE_DOLL

    private val variant = if (isShiny) "shiny" else "regular"

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(CARDINAL).add(WATERLOGGED)
    }

    public override fun mirror(state: BlockState, mirrorIn: Mirror): BlockState {
        return state.setValue(CARDINAL, state.getValue(CARDINAL).mirror(mirrorIn))
    }

    public override fun rotate(state: BlockState, rot: Rotation): BlockState {
        return state.setValue(CARDINAL, state.getValue(CARDINAL).rotation(rot))
    }

    override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hitResult: BlockHitResult
    ): InteractionResult {
        if (!level.isClientSide() && player.isShiftKeyDown) level.setBlockAndUpdate(
            pos, state.setValue(
                CARDINAL,
                Cardinal.entries[(state.getValue(CARDINAL).ordinal + 1) % 8]
            )
        )
        return InteractionResult.SUCCESS
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val cardinal = if (context.player != null) {
            Cardinal.getFromAngle((context.rotation - 180) % 360)
        } else {
            Cardinal.NORTH
        }
        return defaultBlockState().setValue(CARDINAL, cardinal)
    }

    override fun getVariant(): String? {
        return variant
    }

    override fun codec(): MapCodec<out BaseEntityBlock> {
        TODO("Not yet implemented")
    }

    enum class Cardinal(
        private val angle: Int,
        val representableName: String,
        private val offset: Vec3i,
        vararg directions: Direction?
    ) :
        StringRepresentable {
        SOUTH(0, "south", Vec3i(0, 0, 1), Direction.NORTH),
        SOUTH_WEST(45, "south_west", Vec3i(-1, 0, 1), Direction.NORTH, Direction.EAST),
        WEST(90, "west", Vec3i(-1, 0, 0), Direction.EAST),
        NORTH_WEST(135, "north_west", Vec3i(-1, 0, -1), Direction.SOUTH, Direction.EAST),
        NORTH(180, "north", Vec3i(0, 0, -1), Direction.SOUTH),
        NORTH_EAST(225, "north_east", Vec3i(1, 0, -1), Direction.SOUTH, Direction.WEST),
        EAST(270, "east", Vec3i(1, 0, 0), Direction.WEST),
        SOUTH_EAST(315, "south_east", Vec3i(1, 0, 1), Direction.NORTH, Direction.WEST),
        NONE(
            -1,
            "none",
            Vec3i(0, 0, 0)
        ); //This one can be ignored for rotating. It is just filler for multiblock stuff.

        fun mirror(mirror: Mirror): Cardinal {
            if (this == NONE) return this
            return if (mirror == Mirror.NONE) {
                entries[(this.ordinal + 4) % 8]
            } else {
                this
            }
        }

        fun rotation(rotation: Rotation): Cardinal {
            return when (rotation) {
                Rotation.CLOCKWISE_90 -> entries[(this.ordinal + 2) % 8]
                Rotation.CLOCKWISE_180 -> entries[(this.ordinal + 4) % 8]
                Rotation.COUNTERCLOCKWISE_90 -> entries[(this.ordinal + 6) % 8]
                else -> this
            }
        }

        override fun getSerializedName(): String {
            return representableName
        }

        fun getAngle(): Float {
            return angle.toFloat()
        }

        fun offset(): Vec3i {
            return offset
        }

        companion object {
            fun getFromAngle(angle: Float): Cardinal {
                return entries[Mth.floor(angle / 45.0 + 0.5) and 7]
            }

            fun fromDirection(direction: Direction): Cardinal {
                return when (direction) {
                    Direction.NORTH -> NORTH
                    Direction.EAST -> EAST
                    Direction.SOUTH -> SOUTH
                    Direction.WEST -> WEST
                    else -> NONE
                }
            }
        }
    }

    companion object {
        @JvmField
        val CARDINAL: EnumProperty<Cardinal> = EnumProperty.create(
            "cardinal",
            Cardinal::class.java
        )

        fun calculateAngle(source: Vec3, target: Vec3): Float {
            return Math.toDegrees(Math.atan2((target.x - source.x), (target.z - source.z))).toFloat()
        }
    }
}