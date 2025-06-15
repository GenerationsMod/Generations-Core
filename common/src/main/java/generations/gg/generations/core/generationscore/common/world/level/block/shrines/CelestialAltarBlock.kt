package generations.gg.generations.core.generationscore.common.world.level.block.shrines

import com.cobblemon.mod.common.api.spawning.TimeRange.Companion.timeRanges
import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.GenericRotatableShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.altar.CelestialAltarBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class CelestialAltarBlock(properties: Properties) :
    GenericRotatableModelBlock<CelestialAltarBlockEntity>(
        properties = properties,
        model = GenerationsBlockEntityModels.CELESTIAL_ALTAR
    ) {
    override val blockEntityType: BlockEntityType<CelestialAltarBlockEntity>
        get() = GenerationsBlockEntities.CELESTIAL_ALTAR

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(IS_SUN)
    }

    override fun codec(): MapCodec<CelestialAltarBlock> = CODEC

    override fun createDefaultState(): BlockState {
        return super.createDefaultState().setValue(IS_SUN, true)
    }

    override fun <T : BlockEntity?> getTicker(
        level: Level,
        state: BlockState,
        blockEntityType: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return createTickerHelper(
            blockEntityType,
            GenerationsBlockEntities.CELESTIAL_ALTAR,
            if (level.isClientSide) BlockEntityTicker { level1: Level?, blockPos: BlockPos?, blockState: BlockState?, blockEntity: CelestialAltarBlockEntity? -> } else BlockEntityTicker { level12: Level, pos: BlockPos?, blockState: BlockState?, blockEntity: CelestialAltarBlockEntity? ->
                val state1 = level12.getBlockState(pos).setValue(
                    IS_SUN, timeRanges["day"]!!
                        .contains(level12.dayTime().toInt())
                )
                level12.setBlockAndUpdate(pos, state1)
            })
    }

    public override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hit: BlockHitResult
    ): ItemInteractionResult {
        if (level.isClientSide) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION

        player.sendSystemMessage(Component.translatable("generations_core.blocks.celestial_altar.hint"))

        return ItemInteractionResult.SUCCESS
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
        val IS_SUN: BooleanProperty = BooleanProperty.create("is_sun")

        val SHAPE: GenericRotatableShapes = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.or(
                Shapes.box(0.03749999999999998, 0.0, 0.03749999999999998, 0.9625, 0.2875, 0.9625),
                Shapes.box(0.38125, 0.2875, 0.38125, 0.61875, 0.396875, 0.61875),
                Shapes.box(0.3375, 0.48125, 0.4875, 0.6625, 0.8125, 0.50625),
                Shapes.box(0.44375, 0.2875, 0.19374999999999998, 0.5812499999999999, 0.871875, 0.36875),
                Shapes.box(0.60625, 0.2875, 0.51875, 0.775, 0.684375, 0.6875),
                Shapes.box(0.19374999999999998, 0.2875, 0.525, 0.35624999999999996, 1.0375, 0.68125)
            ), Direction.SOUTH, 1, 1, 1, 0.0, 0.0
        )
        val CODEC = simpleCodec(::CelestialAltarBlock)
    }
}
