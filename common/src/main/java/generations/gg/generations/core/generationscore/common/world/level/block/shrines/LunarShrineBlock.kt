package generations.gg.generations.core.generationscore.common.world.level.block.shrines

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsShrines
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.DirectionalShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.InteractShrineBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.LunarShrineBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
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
import java.util.function.Consumer

class LunarShrineBlock(properties: Properties) : ShrineBlock<LunarShrineBlockEntity>(properties = properties, blockEntityFunction = GenerationsBlockEntities.LUNAR_SHRINE) {

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(IS_LIGHT)
    }

    override fun codec(): MapCodec<LunarShrineBlock> = CODEC 
    
    override fun createDefaultState(): BlockState = super.createDefaultState().setValue(IS_LIGHT, false)

    override fun <T : BlockEntity?> getTicker(
        level: Level,
        state: BlockState,
        blockEntityType: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return createTickerHelper(
            blockEntityType,
            GenerationsBlockEntities.LUNAR_SHRINE.get(),
            if (level.isClientSide) BlockEntityTicker { _, _, _, _ -> } else BlockEntityTicker { world: Level, pos: BlockPos, _, _ ->
                val blockstate = world.getBlockState(pos).setValue(
                    IS_LIGHT, world.getMaxLocalRawBrightness(pos) >= 10
                )
                world.setBlockAndUpdate(pos, blockstate)
            })
    }
    
    override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hit: BlockHitResult
    ): ItemInteractionResult {
        if (!level.isClientSide()) {
            val key = getSpecies(state)

            val block =
                (if (key === LegendKeys.CRESSELIA) GenerationsShrines.LIGHT_CRYSTAL else GenerationsShrines.DARK_CRYSTAL).get()

            val list = RegiShrineBlock.searchForBlock(
                level, pos, 15, 5
            ) { level1: Level, blockPos: BlockPos ->
                level1.getBlockState(blockPos).`is`(block)
            }

            val shrine = level.getBlockEntity(pos).instanceOrNull<InteractShrineBlockEntity>()
            
            if (list.isNotEmpty() && shrine != null) {
                if (list.size == 5) {
                    list.forEach(Consumer { a -> level.destroyBlock(a, false) })
                    player.getItemInHand(hand).shrink(1)
                    PokemonUtil.spawn(key.createProperties(70), level, shrine.blockPos.above())

                    return ItemInteractionResult.SUCCESS
                }
            }
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hit)
    }

    fun getSpecies(state: BlockState): SpeciesKey {
        return if (state.getValue(IS_LIGHT)) LegendKeys.CRESSELIA else LegendKeys.DARKRAI
    }

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return (if (state.getValue(IS_LIGHT)) LIGHT else DARK).getShape(state)
    }

    companion object {
        private val LIGHT: DirectionalShapes =
            GenerationsVoxelShapes.generateDirectionVoxelShape(Shapes.box(0.0, 0.0, 0.1875, 1.0, 0.4375, 0.8125))
        private val DARK: DirectionalShapes =
            GenerationsVoxelShapes.generateDirectionVoxelShape(Shapes.box(0.0, 0.0, 0.0, 1.0, 0.5625, 1.0))

        val IS_LIGHT: BooleanProperty = BooleanProperty.create("is_light")
        
        val CODEC: MapCodec<LunarShrineBlock> = simpleCodec(::LunarShrineBlock)        
    }
}
