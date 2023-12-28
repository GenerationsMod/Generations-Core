package generations.gg.generations.core.generationscore.world.level.block.utilityblocks

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.api.text.gray
import com.cobblemon.mod.common.api.text.green
import com.cobblemon.mod.common.api.text.red
import com.cobblemon.mod.common.util.asTranslated
import com.cobblemon.mod.common.util.isInBattle
import com.cobblemon.mod.common.util.lang
import com.cobblemon.mod.common.util.party
import generations.gg.generations.core.generationscore.world.item.DyedBlockItem
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.world.level.block.entities.HealerBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Explosion
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.BlockHitResult

class HealerBlock(function: (DyeColor) -> DyedBlockItem<HealerBlockEntity, HealerBlock>, props: Properties?) : DyeableBlock<HealerBlockEntity, HealerBlock>(
    function,
    GenerationsBlockEntities.HEALER,
    props,
    GenerationsBlockEntityModels.HEALER
) {
    companion object {
/*        fun getBlock(color: DyeColor): DyedBlockItem<HealerBlock?> = when (color) {
            DyeColor.RED -> GenerationsUtilityBlocks.RED_HEALER
            DyeColor.WHITE -> GenerationsUtilityBlocks.WHITE_HEALER
            DyeColor.ORANGE -> GenerationsUtilityBlocks.ORANGE_HEALER
            DyeColor.PINK -> GenerationsUtilityBlocks.PINK_HEALER
            DyeColor.YELLOW -> GenerationsUtilityBlocks.YELLOW_HEALER
            DyeColor.LIME -> GenerationsUtilityBlocks.LIME_HEALER
            DyeColor.GREEN -> GenerationsUtilityBlocks.GREEN_HEALER
            DyeColor.LIGHT_BLUE -> GenerationsUtilityBlocks.LIGHT_BLUE_HEALER
            DyeColor.CYAN -> GenerationsUtilityBlocks.CYAN_HEALER
            DyeColor.BLUE -> GenerationsUtilityBlocks.BLUE_HEALER
            DyeColor.MAGENTA -> GenerationsUtilityBlocks.MAGENTA_HEALER
            DyeColor.PURPLE -> GenerationsUtilityBlocks.PURPLE_HEALER
            DyeColor.BROWN -> GenerationsUtilityBlocks.BROWN_HEALER
            DyeColor.GRAY -> GenerationsUtilityBlocks.GRAY_HEALER
            DyeColor.LIGHT_GRAY -> GenerationsUtilityBlocks.LIGHT_GRAY_HEALER
            DyeColor.BLACK -> GenerationsUtilityBlocks.BLACK_HEALER
        }.get()*/


        // Charge level 6 is used only when healing machine is active
        const val MAX_CHARGE_LEVEL = 5
        val CHARGE_LEVEL: IntegerProperty = IntegerProperty.create("charge", 0, MAX_CHARGE_LEVEL + 1)
    }

    override fun isPathfindable(blockState: BlockState, blockGetter: BlockGetter, blockPos: BlockPos, pathComputationType: PathComputationType
    ): Boolean {
        return false
    }

    override fun serverUse(
        state: BlockState,
        world: Level,
        blockPos: BlockPos,
        player: Player,
        interactionHand: InteractionHand,
        hit: BlockHitResult
    ): InteractionResult {
        if (world.isClientSide || interactionHand == InteractionHand.OFF_HAND) {
            return InteractionResult.SUCCESS
        }

        val blockEntity = world.getBlockEntity(blockPos)
        if (blockEntity !is HealerBlockEntity) {
            return InteractionResult.SUCCESS
        }

        if (blockEntity.isInUse) {
            player.sendSystemMessage(lang("healingmachine.alreadyinuse").red())
            return InteractionResult.SUCCESS
        }

        val serverPlayerEntity = player as ServerPlayer
        if (serverPlayerEntity.isInBattle()) {
            player.sendSystemMessage(lang("healingmachine.inbattle").red())
            return InteractionResult.SUCCESS
        }
        val party = serverPlayerEntity.party()
        if (party.none()) {
            player.sendSystemMessage(lang("healingmachine.nopokemon").red())
            return InteractionResult.SUCCESS
        }

        if (party.none { pokemon -> pokemon.canBeHealed() }) {
            player.sendSystemMessage(lang("healingmachine.alreadyhealed").red())
            return InteractionResult.SUCCESS
        }

        if (HealerBlockEntity.isUsingHealer(player)) {
            player.sendSystemMessage(lang("healingmachine.alreadyhealing").red())
            return InteractionResult.SUCCESS
        }

        if (blockEntity.canHeal(player)) {
            blockEntity.activate(player)
            player.sendSystemMessage(                                                                        lang("healingmachine.healing").green())
        } else {
            val neededCharge = player.party().getHealingRemainderPercent() - blockEntity.healingCharge
            player.sendSystemMessage(lang("healingmachine.notenoughcharge", "${((neededCharge/party.count())*100f).toInt()}%").red())
        }
        party.forEach { it.tryRecallWithAnimation() }
        return InteractionResult.CONSUME
    }

    override fun setPlacedBy(world: Level, pos: BlockPos, state: BlockState, placer: LivingEntity?, stack: ItemStack) {
        super.setPlacedBy(world, pos, state, placer, stack)

        if (!world.isClientSide && placer is ServerPlayer && placer.isCreative) {
            val blockEntity = world.getBlockEntity(pos)
            if (blockEntity !is HealerBlockEntity) return
            blockEntity.infinite = true
        }
    }

    override fun playerWillDestroy(world: Level, pos: BlockPos, state: BlockState, player: Player) {
        this.handleBreak(world, pos)
        super.playerWillDestroy(world, pos, state, player)
    }

    override fun wasExploded(world: Level, pos: BlockPos, explosion: Explosion) {
        this.handleBreak(world, pos)
        super.wasExploded(world, pos, explosion)
    }



    override fun randomTick(
        blockState: BlockState,
        world: ServerLevel,
        pos: BlockPos,
        random: RandomSource
    ) {
        val blockEntity = world.getBlockEntity(pos)
        if (blockEntity !is HealerBlockEntity) return

        if (random.nextInt(2) == 0 && blockEntity.healTimeLeft > 0) {
            val posX = pos.x.toDouble() + 0.5 + ((random.nextFloat() * 0.3F) * (if (random.nextInt(2) > 0) 1 else (-1))).toDouble()
            val posY = pos.y.toDouble() + 0.9
            val posZ = pos.z.toDouble() + 0.5 + ((random.nextFloat() * 0.3F) * (if (random.nextInt(2) > 0) 1 else (-1))).toDouble()
            world.addParticle(ParticleTypes.HAPPY_VILLAGER, posX, posY, posZ, 0.0, 0.0, 0.0)
        }
    }

    override fun hasAnalogOutputSignal(state: BlockState) = true

    override fun getAnalogOutputSignal(state: BlockState, world: Level, pos: BlockPos): Int = (world.getBlockEntity(pos) as? HealerBlockEntity)?.currentSignal ?: 0

    override fun <T : BlockEntity> getTicker(world: Level, blockState: BlockState, blockWithEntityType: BlockEntityType<T>): BlockEntityTicker<T>? = createTickerHelper(blockWithEntityType, GenerationsBlockEntities.HEALER.get(), HealerBlockEntity.TICKER::tick)

    override fun getRenderShape(blockState: BlockState): RenderShape {
        return RenderShape.ENTITYBLOCK_ANIMATED
    }



    override fun appendHoverText(stack: ItemStack?, world: BlockGetter?, tooltip: MutableList<Component>, options: TooltipFlag?) {
        tooltip.add("block.${Cobblemon.MODID}.healing_machine.tooltip1".asTranslated().gray())
        tooltip.add("block.${Cobblemon.MODID}.healing_machine.tooltip2".asTranslated().gray())
    }

    private fun handleBreak(world: Level, pos: BlockPos) {
        val blockEntity = world.getBlockEntity(pos)
        if (blockEntity is HealerBlockEntity) {
            blockEntity.clearData()
        }
    }
}
