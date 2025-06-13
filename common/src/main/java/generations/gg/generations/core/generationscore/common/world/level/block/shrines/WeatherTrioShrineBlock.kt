package generations.gg.generations.core.generationscore.common.world.level.block.shrines

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.util.Codecs
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes.DirectionalShapes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.WeatherTrioShrineBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class WeatherTrioShrineBlock(
    properties: Properties,
    model: ResourceLocation,
    val species: SpeciesKey,
    private val requiredItem: Item
) : InteractShrineBlock<WeatherTrioShrineBlockEntity>(properties, GenerationsBlockEntities.WEATHER_TRIO, model) {

    override fun isStackValid(stack: ItemStack): Boolean {
        return stack.`is`(requiredItem) && stack.damageValue >= stack.maxDamage
    }

    override fun codec(): MapCodec<WeatherTrioShrineBlock> = CODEC

    override fun interact(
        level: Level,
        pos: BlockPos,
        state: BlockState,
        player: ServerPlayer,
        hand: InteractionHand,
        activationState: Boolean
    ): Boolean {
        player.mainHandItem.shrink(1)

        toggleActive(level, pos)
        getAssoicatedBlockEntity(level, pos)?.triggerCountDown()

        //        ScheduledTask.schedule(() -> {
//            PokemonUtil.spawn(getSpecies().createPokemon(70), level, pos, getAngle(state));
//            InteractShrineBlock.toggleActive(level, pos);
//        }, 150);
        return true
    }

    override val isActivatable: Boolean
        get() = true

    public override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return if (species === LegendKeys.KYOGRE) KYOGRE.getShape(state)
        else if (species === LegendKeys.GROUDON) GROUDON.getShape(state)
        else Shapes.block()
    }

    override fun revertsAfterActivation(): Boolean {
        return true
    }

    override fun waitToDeactivateTime(): Int {
        return 150
    }

    override fun postDeactivation(level: Level, pos: BlockPos, state: BlockState) {
        PokemonUtil.spawn(species.createPokemon(70), level, pos, getAngle(state))
    }

    companion object {
        private val KYOGRE: DirectionalShapes = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(
                Shapes.box(0.0625, 0.0, 0.375, 0.9375, 0.125, 0.75),
                Shapes.box(0.125, 0.125, 0.4375, 0.875, 0.5625, 0.625), BooleanOp.OR
            )
        )

        private val GROUDON: DirectionalShapes = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(
                Shapes.box(0.0, 0.0, 0.3125, 1.0, 0.125, 0.6875),
                Shapes.box(0.125, 0.125, 0.4375, 0.875, 0.5625, 0.5625), BooleanOp.OR
            )
        )

        val CODEC: MapCodec<WeatherTrioShrineBlock> = Codecs.mapCodec { group(
            propertiesCodec(),
            Codecs.modelCodec(),
            SpeciesKey.CODEC.fieldOf("species").forGetter { it.species },
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("requiredItem").forGetter { it.requiredItem }
        ).apply(this, ::WeatherTrioShrineBlock) }
    }
}
