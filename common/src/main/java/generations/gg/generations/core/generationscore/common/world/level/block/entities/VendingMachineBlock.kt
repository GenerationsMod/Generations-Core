package generations.gg.generations.core.generationscore.common.world.level.block.entities

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.api.player.PlayerMoneyHandler.of
import generations.gg.generations.core.generationscore.common.network.packets.shop.S2COpenShopPacket
import generations.gg.generations.core.generationscore.common.network.packets.shop.S2CSyncPlayerMoneyPacket
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.DirectionalShapes
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.DyeableBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.concurrent.CompletableFuture

class VendingMachineBlock(properties: Properties, color: DyeColor, function: Map<DyeColor, Holder<Block>>) : DyeableBlock<VendingMachineBlockEntity, VendingMachineBlock>(
    properties,
    color,
    function,
    GenerationsBlockEntities.VENDING_MACHINE,
    GenerationsBlockEntityModels.VENDING_MACHINE,
    0,
    1,
    0
) {
    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return if (state.`is`(this)) (if (getHeightValue(state) == 1) UPPER else LOWER).getShape(state) else Shapes.block()
    }

    override fun serverUse(
        stack: ItemStack,
        state: BlockState,
        world: ServerLevel,
        pos: BlockPos,
        player: ServerPlayer,
        handIn: InteractionHand,
        hit: BlockHitResult
    ): ItemInteractionResult {
        val blockPos = CompletableFuture.supplyAsync { pos }
        val amount = of(player).balance()
        val playerFuture = CompletableFuture.completedFuture(player)

        blockPos.thenAccept { pos1: BlockPos? ->
            val amount1 = amount.join()
            val player1 = playerFuture.join()
            S2CSyncPlayerMoneyPacket(amount1).sendToPlayer(player1)
            S2COpenShopPacket(pos1!!).sendToPlayer(player1)
        }

        return ItemInteractionResult.SUCCESS
    }

    override fun codec(): MapCodec<VendingMachineBlock> {
        return CODEC
    } //    public static DyedBlockItem<VendingMachineBlock> getBlock(DyeColor dyeColor) {
    //        return (switch (dyeColor) {
    //            case BLACK -> GenerationsDecorationBlocks.BLACK_VENDING_MACHINE;
    //            case BLUE -> GenerationsDecorationBlocks.BLUE_VENDING_MACHINE;
    //            case BROWN -> GenerationsDecorationBlocks.BROWN_VENDING_MACHINE;
    //            case CYAN -> GenerationsDecorationBlocks.CYAN_VENDING_MACHINE;
    //            case GRAY -> GenerationsDecorationBlocks.GRAY_VENDING_MACHINE;
    //            case GREEN -> GenerationsDecorationBlocks.GREEN_VENDING_MACHINE;
    //            case LIGHT_BLUE -> GenerationsDecorationBlocks.LIGHT_BLUE_VENDING_MACHINE;
    //            case LIGHT_GRAY -> GenerationsDecorationBlocks.LIGHT_GRAY_VENDING_MACHINE;
    //            case LIME -> GenerationsDecorationBlocks.LIME_VENDING_MACHINE;
    //            case MAGENTA -> GenerationsDecorationBlocks.MAGENTA_VENDING_MACHINE;
    //            case ORANGE -> GenerationsDecorationBlocks.ORANGE_VENDING_MACHINE;
    //            case PINK -> GenerationsDecorationBlocks.PINK_VENDING_MACHINE;
    //            case PURPLE -> GenerationsDecorationBlocks.PURPLE_VENDING_MACHINE;
    //            case RED -> GenerationsDecorationBlocks.RED_VENDING_MACHINE;
    //            case WHITE -> GenerationsDecorationBlocks.WHITE_VENDING_MACHINE;
    //            case YELLOW -> GenerationsDecorationBlocks.YELLOW_VENDING_MACHINE;
    //        }).get();
    //    }

    companion object {
        var LOWER: DirectionalShapes = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(
                Shapes.join(
                    Shapes.join(
                        Shapes.box(0.0625, 0.0, 0.15625, 0.9375, 0.0625, 0.875),
                        Shapes.box(0.0, 0.026875, 0.04999999999999999, 1.0, 1.0, 0.9375),
                        BooleanOp.OR
                    ), Shapes.box(0.14687499999999998, 0.21875, 0.0, 0.865625, 0.546875, 0.0625), BooleanOp.OR
                ),
                Shapes.box(0.15000000000000002, 0.671875, 0.01874999999999999, 0.86875, 1.0, 0.08124999999999999),
                BooleanOp.OR
            ), Direction.SOUTH
        )
        var UPPER: DirectionalShapes = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(
                Shapes.join(
                    Shapes.box(
                        0.0625,
                        0.0,
                        0.15625,
                        0.9375,
                        0.0625,
                        0.875
                    ), Shapes.box(0.0, 0.0, 0.04999999999999999, 1.0, 1.0, 0.9375), BooleanOp.OR
                ),
                Shapes.box(0.15000000000000002, 0.0, 0.01874999999999999, 0.86875, 0.84375, 0.08124999999999999),
                BooleanOp.OR
            ), Direction.SOUTH
        )

        val CODEC: MapCodec<VendingMachineBlock> = RecordCodecBuilder.mapCodec { instance ->
                instance.group<Properties, DyeColor, Map<DyeColor, Holder<Block>>>(
                    propertiesCodec(),
                    DyeColor.CODEC.fieldOf("color").forGetter(VendingMachineBlock::color),
                    Codec.unboundedMap(DyeColor.CODEC, BuiltInRegistries.BLOCK.holderByNameCodec()).fieldOf("function").forGetter(VendingMachineBlock::function)
                ).apply(instance, ::VendingMachineBlock)
            }
    }
}
