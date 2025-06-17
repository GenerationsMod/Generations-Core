package generations.gg.generations.core.generationscore.common.world.level.block

import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.Direction
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

object GenerationsVoxelShapes {
    fun rotateShape(from: Direction, to: Direction, shape: VoxelShape): VoxelShape {
        val buffer = arrayOf(shape, Shapes.empty())
        val times = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4
        for (i in 0 until times) {
            buffer[0].forAllBoxes { minX: Double, minY: Double, minZ: Double, maxX: Double, maxY: Double, maxZ: Double ->
                buffer[1] = Shapes.join(
                    buffer[1],
                    Shapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX),
                    BooleanOp.OR
                )
            }
            buffer[0] = buffer[1]
            buffer[1] = Shapes.empty()
        }
        return buffer[0]
    }

    @JvmStatic
    @JvmOverloads
    fun generateDirectionVoxelShape(shape: VoxelShape, source: Direction = Direction.NORTH): DirectionalShapes {
        return DirectionalShapes(
            rotateShape(source, Direction.NORTH, shape),
            rotateShape(source, Direction.EAST, shape),
            rotateShape(source, Direction.SOUTH, shape),
            rotateShape(source, Direction.WEST, shape)
        )
    }

    @JvmOverloads
    fun generateRotationalVoxelShape(
        shape: VoxelShape,
        source: Direction,
        width: Int,
        height: Int,
        length: Int,
        xOffset: Double = 0.0,
        zOffset: Double = 0.0
    ): GenericRotatableShapes {
        var shape = shape
        val array = arrayOfNulls<DirectionalShapes>(width * height * length)

        shape = shape.move(xOffset, 0.0, zOffset)

        for (x in 0 until width) {
            for (y in 0 until height) {
                for (z in 0 until length) {
                    val index = x + width * (y + height * z)

                    //                    var mask = Shapes.block().move(x, y, z);
                    array[index] = generateDirectionVoxelShape(
                        Shapes.join(shape, shape, BooleanOp.AND).move(-x.toDouble(), -y.toDouble(), z.toDouble()),
                        source
                    )
                }
            }
        }


        return GenericRotatableShapes(array, width, height, length)
    }

    @JvmRecord
    data class GenericRotatableShapes(
        val array: Array<DirectionalShapes?>,
        val width: Int,
        val height: Int,
        val length: Int
    ) {
        fun getShape(state: BlockState): VoxelShape {
            return state.block.instanceOrNull<GenericRotatableModelBlock>()?.let { block ->
                val x: Int = block.getWidthValue(state)
                val y: Int = block.getHeightValue(state)
                val z: Int = block.getLengthValue(state)

                val index = x + width * (y + height * z)

                return array[index]!!.getShape(state)
            } ?: Shapes.empty()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is GenericRotatableShapes) return false

            if (!array.contentEquals(other.array)) return false
            if (width != other.width) return false
            if (height != other.height) return false
            if (length != other.length) return false

            return true
        }

        override fun hashCode(): Int {
            var result = array.contentHashCode()
            result = 31 * result + width
            result = 31 * result + height
            result = 31 * result + length
            return result
        }
    }

    @JvmRecord
    data class DirectionalShapes(
        val north: VoxelShape,
        val east: VoxelShape,
        val south: VoxelShape,
        val west: VoxelShape
    ) {
        fun getShape(state: BlockState): VoxelShape {
            return when (state.getValue(HorizontalDirectionalBlock.FACING)) {
                Direction.EAST -> east
                Direction.SOUTH -> south
                Direction.WEST -> west
                else -> north
            }
        }
    }
}
