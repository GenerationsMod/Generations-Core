package generations.gg.generations.core.generationscore.common.world.level.block

import com.cobblemon.mod.common.pokeball.PokeBall
import com.cobblemon.mod.common.util.asTranslated
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.architectury.event.events.common.LifecycleEvent
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.level.block.entities.BallLootBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.entities.BallLootBlockEntity.LootMode
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds
import net.minecraft.core.BlockPos
import net.minecraft.core.NonNullList
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.registries.Registries
import net.minecraft.locale.Language
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundSource
import net.minecraft.util.RandomSource
import net.minecraft.world.Containers
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.loot.LootParams
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraft.world.level.storage.loot.parameters.LootContextParams
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.*
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collectors

class BallLootBlock(properties: Properties, val type: String, private val ball: PokeBall) : GenericRotatableModelBlock<BallLootBlockEntity>(
        properties = properties,
        blockEntityFunction = GenerationsBlockEntities.BALL_LOOT,
        model = GenerationsBlockEntityModels.DESK
    ) {
    val lootTableId: ResourceKey<LootTable> = ResourceKey.create(Registries.LOOT_TABLE, GenerationsCore.id("chests/${type}_ball"))

    override fun codec(): MapCodec<out BaseEntityBlock> {
        return CODEC
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
        if (level.isClientSide || hand == InteractionHand.OFF_HAND) {
            return ItemInteractionResult.CONSUME
        }

        val lootEntity = level.getBlockEntity(pos, GenerationsBlockEntities.BALL_LOOT.get())

        if (lootEntity.isPresent) {
            val be = lootEntity.get()

            val playerUUID = player.uuid

            if (playerUUID != be.owner) {
                if (be.canClaim(playerUUID)) {
                    if (be.shouldBreakBlock()) {
                        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState())
                    }

                    (if (be.isCustomDrop) be.customDrops else getDrops(level as ServerLevel, pos, player))?.run {
                        this.forEach { item: ItemStack ->
                            player.displayClientMessage("generations_core.blocks.lootfound".asTranslated(item.hoverName), false)
                        }
                        Containers.dropContents(level, pos.above(), this)
                    }

                    level.playSound(
                        null,
                        player.x,
                        player.y,
                        player.z,
                        GenerationsSounds.LUGIA_SHRINE_SONG,
                        SoundSource.BLOCKS,
                        0.2f,
                        1.0f
                    )

                    player.awardStat(if(be.isCustomDrop) GenerationsCoreStats.HIDDEN_LOOT_FOUND else GenerationsCoreStats.NORMAL_LOOT_FOUND, 1)
                } else if (be.lootMode.isTimeEnabled) {
                    player.displayClientMessage("generations_core.blocks.timedclaim".asTranslated(), false)
                } else {
                    player.displayClientMessage("generations_core.blocks.claimedloot".asTranslated(), false)
                }
            } else {
                val shiftClick = player.isShiftKeyDown
                if (shiftClick) {
                    be.owner = null
                    player.displayClientMessage(Component.translatable("generations_core.blocks.ownerchanged"), false)
                } else {
                    val itemStack = player.mainHandItem
                    if (!itemStack.isEmpty) {
                        be.addCustomDrop(itemStack)
                        player.displayClientMessage(
                            Component.translatable(
                                "generations_core.blocks.balllootset",
                                itemStack.displayName
                            ), false
                        )
                    } else {
                        val visible = !be.visible
                        be.visible = visible
                        val metaMode = if (visible) "Normal" else "Hidden"
                        player.displayClientMessage(
                            Component.translatable("generations_core.blocks.visible", metaMode),
                            false
                        )
                        return ItemInteractionResult.FAIL
                    }
                }
            }
        }

        return ItemInteractionResult.CONSUME
    }

    public override fun attack(state: BlockState, level: Level, pos: BlockPos, player: Player) {
        val be = level.getBlockEntity(pos);

        if (!level.isClientSide() && be is BallLootBlockEntity && player.uuid == be.owner) {
            var mode = "generations_core.blocks.lootmode."

            var lootMode: LootMode = be.lootMode

            lootMode = when (lootMode) {
                LootMode.ONCE_PER_PLAYER -> LootMode.TIMED
                LootMode.TIMED -> LootMode.ONCE
                LootMode.ONCE -> LootMode.UNLIMITED
                LootMode.UNLIMITED -> LootMode.ONCE_PER_PLAYER
            }

            be.lootMode = lootMode
            mode += lootMode.name.uppercase(Locale.getDefault())

            player.displayClientMessage(
                Component.translatable(
                    "generations_core.blocks.lootmode",
                    Language.getInstance().getOrDefault(mode)
                ), false
            )
        }
    }

    fun getDrops(level: ServerLevel, pos: BlockPos, player: Player?): NonNullList<ItemStack> {
        val builder = (LootParams.Builder(level)).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
        if (player != null) builder.withLuck(player.luck).withParameter(LootContextParams.THIS_ENTITY, player)


        return level.server.reloadableRegistries().getLootTable(this.lootTableId).getRandomItems(
            builder.create(
                LootContextParamSets.CHEST
            )
        ).stream().limit(1).collect(
            Collectors.toCollection(
                Supplier { NonNullList.create() })
        )
    }

    public override fun randomTick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        val be = level.getBlockEntity(pos)

        if (be is BallLootBlockEntity && !be.visible) {
            val rand = random.nextFloat() * 0.5f + 1.0f
            val xVel = 0.1
            val yVel = 0.2
            val zVel = 0.1
            level.addParticle(
                ParticleTypes.EFFECT,
                pos.x + 0.5,
                pos.y + 0.5,
                pos.z + 0.5,
                xVel * rand,
                yVel * rand,
                zVel * rand
            )
        }
    }

    override fun setPlacedBy(level: Level, pos: BlockPos, state: BlockState, placer: LivingEntity?, stack: ItemStack) {
        super.setPlacedBy(level, pos, state, placer, stack)

        val be = level.getBlockEntity(pos)
        if (be is BallLootBlockEntity && placer is ServerPlayer) be.owner = placer.getUUID()
    }

    fun ball(): PokeBall {
        return this.ball
    }

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return shape
    }

    companion object {
        private val shape: VoxelShape = Shapes.box(0.25, 0.0, 0.25, 0.75, 0.5, 0.75)
        private val CODEC: MapCodec<BallLootBlock> = RecordCodecBuilder.mapCodec { it.group(
            propertiesCodec(),
            Codec.STRING.fieldOf("type").forGetter({ it.type}),
            PokeBall.BY_IDENTIFIER_CODEC.fieldOf("ball").forGetter({ it.ball })
        ).apply(it, ::BallLootBlock) }
    }
}