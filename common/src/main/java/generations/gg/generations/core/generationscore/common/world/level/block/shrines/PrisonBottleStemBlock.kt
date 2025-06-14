package generations.gg.generations.core.generationscore.common.world.level.block.shrines

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsShrines
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.GenericRotatableShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.InteractShrineBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.StringRepresentable
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class PrisonBottleStemBlock(materialIn: Properties) :
    InteractShrineBlock<InteractShrineBlockEntity>(
        materialIn,
        GenerationsBlockEntities.INTERACT_SHRINE,
        GenerationsBlockEntityModels.PRISON_BOTTLE,
        0,
        2,
        0
    ) {
    init {
        this.registerDefaultState(defaultBlockState().setValue(STATE, PrisonBottleState.EMPTY))
    }

    override fun codec(): MapCodec<PrisonBottleStemBlock> = CODEC

    override fun interact(
        world: Level,
        pos: BlockPos,
        state: BlockState,
        player: ServerPlayer,
        hand: InteractionHand,
        activationState: Boolean
    ): Boolean {
        val heldItem = player.mainHandItem

        var baseState = world.getBlockState(pos)
        val baseBlock = javaClass.cast(baseState.block)
        val base = getBaseBlockPos(pos, state)

        val dir = state.getValue(FACING)

        if (baseState.getValue<PrisonBottleState>(STATE) != PrisonBottleState.UNBOUND) {
            if (!player.isCreative) heldItem.shrink(1)

            baseState = baseState.cycle(STATE)

            for (x in 0 until width + 1) {
                for (y in 0 until height + 1) {
                    for (z in 0 until length + 1) {
                        val adjustX = adjustX(x)
                        val adjustZ = adjustX(x)
                        val blockPos = base.relative(dir.counterClockWise, adjustX).relative(Direction.UP, y)
                            .relative(dir, adjustZ)

                        val currentState = world.getBlockState(blockPos)

                        val stateX = baseBlock.getWidthValue(currentState)
                        val stateY = baseBlock.getHeightValue(currentState)
                        val stateZ = baseBlock.getLengthValue(currentState)

                        world.setBlock(
                            blockPos, baseBlock.setSize(
                                baseState.setValue(
                                    WATERLOGGED, currentState.getValue(
                                        WATERLOGGED
                                    )
                                ), stateX, stateY, stateZ
                            ), 2, 0
                        )
                    }
                }
            }

            if (baseState.getValue(STATE) == PrisonBottleState.UNBOUND) {
                getAssoicatedBlockEntity(
                    world,
                    pos
                )?.triggerCountDown()
            }
        }
        return true
    }

    override fun revertsAfterActivation(): Boolean {
        return false
    }

    override fun waitToDeactivateTime(): Int {
        return 40
    }

    override fun postDeactivation(world: Level, pos: BlockPos, state: BlockState) {
        val baseState = world.getBlockState(pos)
        val base = getBaseBlockPos(pos, state)

        val dir = state.getValue(FACING)

        if (baseState.getValue<PrisonBottleState>(STATE) == PrisonBottleState.UNBOUND) {
            for (x in 0 until width + 1) {
                for (y in 0 until height + 1) {
                    for (z in 0 until length + 1) {
                        val adjustX = adjustX(x)
                        val adjustZ = adjustX(x)
                        val blockPos = base.relative(dir.counterClockWise, adjustX).relative(Direction.UP, y)
                            .relative(dir, adjustZ)

                        world.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 2, 0)
                        if (adjustX == 0 && y == 0 && adjustZ == 0) {
                            PokemonUtil.spawn(
                                LegendKeys.HOOPA.createPokemon(70),
                                world,
                                base,
                                dir.toYRot()
                            ) //TODO: Spawn as unbound.

                            world.addFreshEntity(
                                ItemEntity(
                                    world,
                                    pos.x + 0.5,
                                    pos.y + 0.5,
                                    pos.z + 0.5,
                                    ItemStack(GenerationsShrines.PRISON_BOTTLE)
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    override fun isStackValid(stack: ItemStack): Boolean {
        return stack.`is`(GenerationsItems.HOOPA_RING)
    }

    override fun getVariant(blockState: BlockState): String? {
        return blockState.getValue(STATE).serializedName
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder.add(STATE))
    }

    override val isActivatable: Boolean
        get() = super.isActivatable

    enum class PrisonBottleState(private val variant: String) : StringRepresentable {
        EMPTY("empty"),
        RING_1("ring_1"),
        RING_2("ring_2"),
        RING_3("ring_3"),
        RING_4("ring_4"),
        RING_5("ring_5"),
        UNBOUND("unbound");

        override fun getSerializedName(): String {
            return variant
        }
    }

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE.getShape(state)
    }

    companion object {
        @JvmField
        val SHAPE: GenericRotatableShapes = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.or(
                Shapes.box(0.5, 1.55, 0.4375, 0.96875, 1.8250000000000002, 0.5625),
                Shapes.box(0.5, 0.0, 0.19374999999999998, 0.80625, 0.0625, 0.80625),
                Shapes.box(0.5, 0.0625, 0.21875, 0.78125, 0.0875, 0.78125),
                Shapes.box(0.19374999999999998, 0.0, 0.19374999999999998, 0.5, 0.0625, 0.80625),
                Shapes.box(0.5, 0.0875, 0.26875, 0.73125, 0.11249999999999999, 0.73125),
                Shapes.box(0.21875, 0.0625, 0.21875, 0.5, 0.0875, 0.78125),
                Shapes.box(0.5, 0.1125, 0.39375, 0.60625, 1.075, 0.60625),
                Shapes.box(0.5, 0.175, 0.31875, 0.98125, 0.95, 0.68125),
                Shapes.box(0.5, 0.0875, 0.33125, 0.66875, 0.175, 0.66875),
                Shapes.box(0.26875, 0.0875, 0.26875, 0.5, 0.11249999999999999, 0.73125),
                Shapes.box(0.5, 0.7375, 0.41875, 0.58125, 1.7000000000000002, 0.58125),
                Shapes.box(0.39375, 0.1125, 0.39375, 0.5, 1.075, 0.60625),
                Shapes.box(0.25, 1.55, 0.35624999999999996, 0.51875, 1.8250000000000002, 0.64375),
                Shapes.box(0.375, 1.675, 0.375, 0.5, 2.21875, 0.625),
                Shapes.box(0.03125, 1.55, 0.4375, 0.5, 1.8250000000000002, 0.5625),
                Shapes.box(0.48125, 1.55, 0.35624999999999996, 0.75, 1.8250000000000002, 0.64375),
                Shapes.box(0.5, 1.675, 0.375, 0.625, 2.21875, 0.625),
                Shapes.box(0.41875, 0.7375, 0.41875, 0.5, 1.7000000000000002, 0.58125),
                Shapes.box(0.01874999999999999, 0.175, 0.31875, 0.49999999999999994, 0.95, 0.68125),
                Shapes.box(0.33125, 0.0875, 0.33125, 0.5, 0.175, 0.66875)
            ),
            Direction.SOUTH, 1, 3, 1
        )

        val STATE: EnumProperty<PrisonBottleState> = EnumProperty.create(
            "state",
            PrisonBottleState::class.java
        )

        val CODEC = simpleCodec(::PrisonBottleStemBlock)
    }
}
