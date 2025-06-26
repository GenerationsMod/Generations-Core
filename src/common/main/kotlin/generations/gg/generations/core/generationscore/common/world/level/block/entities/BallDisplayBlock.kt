package generations.gg.generations.core.generationscore.common.world.level.block.entities

import com.cobblemon.mod.common.CobblemonItems.CHERISH_BALL
import com.cobblemon.mod.common.CobblemonItems.DIVE_BALL
import com.cobblemon.mod.common.CobblemonItems.DUSK_BALL
import com.cobblemon.mod.common.CobblemonItems.FAST_BALL
import com.cobblemon.mod.common.CobblemonItems.FRIEND_BALL
import com.cobblemon.mod.common.CobblemonItems.GREAT_BALL
import com.cobblemon.mod.common.CobblemonItems.HEAL_BALL
import com.cobblemon.mod.common.CobblemonItems.HEAVY_BALL
import com.cobblemon.mod.common.CobblemonItems.LEVEL_BALL
import com.cobblemon.mod.common.CobblemonItems.LOVE_BALL
import com.cobblemon.mod.common.CobblemonItems.LURE_BALL
import com.cobblemon.mod.common.CobblemonItems.LUXURY_BALL
import com.cobblemon.mod.common.CobblemonItems.MASTER_BALL
import com.cobblemon.mod.common.CobblemonItems.MOON_BALL
import com.cobblemon.mod.common.CobblemonItems.NEST_BALL
import com.cobblemon.mod.common.CobblemonItems.NET_BALL
import com.cobblemon.mod.common.CobblemonItems.PARK_BALL
import com.cobblemon.mod.common.CobblemonItems.POKE_BALL
import com.cobblemon.mod.common.CobblemonItems.PREMIER_BALL
import com.cobblemon.mod.common.CobblemonItems.QUICK_BALL
import com.cobblemon.mod.common.CobblemonItems.REPEAT_BALL
import com.cobblemon.mod.common.CobblemonItems.SAFARI_BALL
import com.cobblemon.mod.common.CobblemonItems.SPORT_BALL
import com.cobblemon.mod.common.CobblemonItems.TIMER_BALL
import com.cobblemon.mod.common.CobblemonItems.ULTRA_BALL
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsDecorationBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class BallDisplayBlock(properties: Properties, val state: DisplayState) : GenericRotatableModelBlock(
    properties = properties,
    model = GenerationsBlockEntityModels.BALL_DISPLAY
) {
    constructor(state: DisplayState) : this(Properties.ofFullCopy(Blocks.IRON_BLOCK), state)

    override val blockEntityType
        get() = GenerationsBlockEntities.BALL_DISPLAY

    private val variant = state.name.lowercase()

    override fun getVariant(): String? {
        return variant
    }

    override fun codec(): MapCodec<BallDisplayBlock> = CODEC

    public override fun getShape(
        blockState: BlockState,
        blockGetter: BlockGetter,
        blockPos: BlockPos,
        collisionContext: CollisionContext
    ): VoxelShape {
        return if (state == DisplayState.EMPTY) EMPTY else FULL
    }

    public override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        world: Level,
        pos: BlockPos,
        player: Player,
        handIn: InteractionHand,
        hit: BlockHitResult
    ): ItemInteractionResult {
//        var stack = player.getItemInHand(handIn);
        val block = (state.block as BallDisplayBlock).state
        val ball = DisplayState.getState(stack)

        if (!world.isClientSide() && ball != null && ball != block) {
            world.setBlockAndUpdate(
                pos, ball.block.invoke().defaultBlockState().setValue(
                    FACING, state.getValue(
                        FACING
                    )
                )
            )
            stack.shrink(1)
            player.addItem(block.ball.invoke().invoke().defaultInstance)
        }

        return ItemInteractionResult.SUCCESS
    }

    enum class DisplayState(
        val ball: () -> () -> Item,
        internal val block: () -> BallDisplayBlock
    ) {
        EMPTY({ { Items.AIR.asItem() } }, { GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.value() as BallDisplayBlock }),
        POKE({ { POKE_BALL.asItem() } }, { GenerationsDecorationBlocks.POKE_BALL_DISPLAY.value() as BallDisplayBlock }),
        GREAT({ { GREAT_BALL.asItem() } }, { GenerationsDecorationBlocks.GREAT_BALL_DISPLAY.value() as BallDisplayBlock }),
        ULTRA({ { ULTRA_BALL.asItem() } }, { GenerationsDecorationBlocks.ULTRA_BALL_DISPLAY.value() as BallDisplayBlock }),
        MASTER({ { MASTER_BALL.asItem() } }, { GenerationsDecorationBlocks.MASTER_BALL_DISPLAY.value() as BallDisplayBlock }),
        CHERISH({ { CHERISH_BALL.asItem() } }, { GenerationsDecorationBlocks.CHERISH_BALL_DISPLAY.value() as BallDisplayBlock }),
        DIVE({ { DIVE_BALL.asItem() } }, { GenerationsDecorationBlocks.DIVE_BALL_DISPLAY.value() as BallDisplayBlock }),
        DUSK({ { DUSK_BALL.asItem() } }, { GenerationsDecorationBlocks.DUSK_BALL_DISPLAY.value() as BallDisplayBlock }),
        FAST({ { FAST_BALL.asItem() } }, { GenerationsDecorationBlocks.FAST_BALL_DISPLAY.value() as BallDisplayBlock }),
        FRIEND({ { FRIEND_BALL.asItem() } }, { GenerationsDecorationBlocks.FRIEND_BALL_DISPLAY.value() as BallDisplayBlock }),

        //GS(() -> CobblemonItems.GS_BALL::asItem, () -> GenerationsDecorationBlocks.GS_BALL_DISPLAY),
        HEAL({ { HEAL_BALL.asItem() } }, { GenerationsDecorationBlocks.HEAL_BALL_DISPLAY.value() as BallDisplayBlock }),
        HEAVY({ { HEAVY_BALL.asItem() } }, { GenerationsDecorationBlocks.HEAVY_BALL_DISPLAY.value() as BallDisplayBlock }),
        LEVEL({ { LEVEL_BALL.asItem() } }, { GenerationsDecorationBlocks.LEVEL_BALL_DISPLAY.value() as BallDisplayBlock }),
        LOVE({ { LOVE_BALL.asItem() } }, { GenerationsDecorationBlocks.LOVE_BALL_DISPLAY.value() as BallDisplayBlock }),
        LURE({ { LURE_BALL.asItem() } }, { GenerationsDecorationBlocks.LURE_BALL_DISPLAY.value() as BallDisplayBlock }),
        LUXURY({ { LUXURY_BALL.asItem() } }, { GenerationsDecorationBlocks.LUXURY_BALL_DISPLAY.value() as BallDisplayBlock }),
        MOON({ { MOON_BALL.asItem() } }, { GenerationsDecorationBlocks.MOON_BALL_DISPLAY.value() as BallDisplayBlock }),
        NEST({ { NEST_BALL.asItem() } }, { GenerationsDecorationBlocks.NEST_BALL_DISPLAY.value() as BallDisplayBlock }),
        NET({ { NET_BALL.asItem() } }, { GenerationsDecorationBlocks.NET_BALL_DISPLAY.value() as BallDisplayBlock }),
        PARK({ { PARK_BALL.asItem() } }, { GenerationsDecorationBlocks.PARK_BALL_DISPLAY.value() as BallDisplayBlock }),
        PREMIER({ { PREMIER_BALL.asItem() } }, { GenerationsDecorationBlocks.PREMIER_BALL_DISPLAY.value() as BallDisplayBlock }),
        QUICK({ { QUICK_BALL.asItem() } }, { GenerationsDecorationBlocks.QUICK_BALL_DISPLAY.value() as BallDisplayBlock }),
        REPEAT({ { REPEAT_BALL.asItem() } }, { GenerationsDecorationBlocks.REPEAT_BALL_DISPLAY.value() as BallDisplayBlock }),
        SAFARI({ { SAFARI_BALL.asItem() } }, { GenerationsDecorationBlocks.SAFARI_BALL_DISPLAY.value() as BallDisplayBlock }),
        SPORT({ { SPORT_BALL.asItem() } }, { GenerationsDecorationBlocks.SPORT_BALL_DISPLAY.value() as BallDisplayBlock }),
        TIMER({ { TIMER_BALL.asItem() } }, { GenerationsDecorationBlocks.TIMER_BALL_DISPLAY.value() as BallDisplayBlock });

        companion object {
            fun getState(stack: ItemStack): DisplayState? {
                for (state in entries) {
                    if (stack.`is`(state.ball.invoke().invoke())) return state
                }

                return null
            }
        }
    }

    companion object {
        private val FULL: VoxelShape = Shapes.box(0.25, 0.0, 0.25, 0.75, 0.625, 0.75)
        private val EMPTY: VoxelShape = Shapes.box(0.25, 0.0, 0.25, 0.75, 0.125, 0.75)
        val CODEC = RecordCodecBuilder.mapCodec { it.group(
            propertiesCodec(),
            Codec.STRING.xmap(String::uppercase, String::lowercase).xmap({ DisplayState.valueOf(it) }, { it.name }).fieldOf("state").forGetter(BallDisplayBlock::state)
        ).apply(it, ::BallDisplayBlock) }
    }
}
